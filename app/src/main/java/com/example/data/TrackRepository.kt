package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object TrackRepository {

    val androidLessons: List<Lesson> = listOf(
        Lesson(
            id = "and_01",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "01. Connecting XML Buttons & Click Listeners",
                bn = "০১. এক্সএমএল বাটন কানেকশন ও ক্লিক লিসেনার",
                ur = "01. XML بٹن کنکشن اور کلک لسنر",
                hi = "01. XML बटन कनेक्शन और क्लिक लिसनर",
                ar = "01. ربط أزرار XML ومستمعات النقر",
                fa_af = "۰۱. اتصال دکمه‌های XML و رویداد کلیک",
                in_id = "01. Menghubungkan Tombol XML & Pendengar Klik"
            ),
            subtitle = localized(
                en = "Link screen buttons to Kotlin/Java code and inspect the compiled Smali OnClickListener.",
                bn = "স্ক্রিনের বাটনকে কোটলিন/জাভা কোডের সাথে যুক্ত করুন এবং কম্পাইল হওয়া স্মালি কোড দেখুন।",
                ur = "اسکرین کے بٹن کو کوڈ سے جوڑیں اور اس کا سمالی بائٹ کوڈ دیکھیں۔",
                hi = "स्क्रीन बटन को कोड से जोड़ें और उसका संकलित स्माली बाइटकोड देखें।",
                ar = "اربط أزرار الشاشة بالكود البرمجي وتفحص شيفرة سمالي الناتجة.",
                fa_af = "اتصال دکمه‌های صفحه به کد برنامه و مشاهده خروجی سمالی آن.",
                in_id = "Hubungkan tombol layar ke kode dan amati bytecode Smali-nya."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "From XML ID to Toast & Smali Bytecode",
                        bn = "XML আইডি থেকে Toast মেসেজ ও স্মালি বাইটকোড",
                        ur = "XML آئی ڈی سے ٹوسٹ پیغام اور سمالی کوڈ تک",
                        hi = "XML आईडी से Toast संदेश और स्माली बाइटकोड तक",
                        ar = "من معرف XML إلى رسالة Toast وشيفرة سمالي",
                        fa_af = "از شناسه XML تا پیام Toast و کد سمالی",
                        in_id = "Dari ID XML ke Pesan Toast & Bytecode Smali"
                    ),
                    childAnalogy = localized(
                        en = "XML is the doorbell on the wall; `setOnClickListener` is the wire connecting that button to the chime inside the house!",
                        bn = "XML হলো দেয়ালে লাগানো কলিং বেলের সুইচ, আর `setOnClickListener` হলো ঘরের ভেতরের ঘণ্টার সাথে তারের সংযোগ!",
                        ur = "XML دیوار پر لگا گھنٹی کا بٹن ہے اور `setOnClickListener` وہ تار ہے جو اندر گھنٹی بجاتی ہے!",
                        hi = "XML दीवार पर लगा घंटी का बटन है और `setOnClickListener` वह तार है जो अंदर घंटी बजाता है!",
                        ar = "ملف XML هو زر الجرس على الحائط، و `setOnClickListener` هو السلك الموصل للجرس بالداخل!",
                        fa_af = "فایل XML دکمه زنگ روی دیوار است و `setOnClickListener` سیمی است که زنگ داخل خانه را به صدا درمی‌آورد!",
                        in_id = "XML adalah tombol bel di dinding, sedangkan `setOnClickListener` adalah kabel penghubungnya!"
                    ),
                    content = localized(
                        en = "Android assigns every `@+id/...` an integer resource ID (`0x7f08...`). `findViewById` fetches the `Button` instance, and `setOnClickListener` attaches a callback.",
                        bn = "অ্যান্ড্রয়েড প্রতিটি `@+id/...`-কে একটি হেক্সাডেসিমাল সংখ্যা দেয় (`0x7f08...`)। `findViewById` সেই বাটনটি খুঁজে আনে এবং `setOnClickListener` ট্যাপ করার কাজটি যুক্ত করে।",
                        ur = "اینڈرائیڈ ہر `@+id/...` کو ایک ہیکس نمبر دیتا ہے۔ `findViewById` بٹن تلاش کرتا ہے اور `setOnClickListener` کلک ایکشن جوڑتا ہے۔",
                        hi = "एंड्रॉइड हर `@+id/...` को एक हेक्स नंबर देता है। `findViewById` बटन ढूंढता है और `setOnClickListener` क्लिक एक्शन जोड़ता है।",
                        ar = "يمنح أندرويد كل معرف رقماً سداسياً، وتجلب `findViewById` الزر بينما تربط `setOnClickListener` الحدث.",
                        fa_af = "اندروید به هر شناسه یک عدد هگز اختصاص می‌دهد؛ `findViewById` دکمه را یافته و `setOnClickListener` عملیات کلیک را وصل می‌کند.",
                        in_id = "Android memberi setiap `@+id/...` angka heksadesimal. `findViewById` mengambil tombol dan `setOnClickListener` memasang aksi."
                    ),
                    xmlCode = """<Button
    android:id="@+id/btnAction"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Verify Account" />""",
                    kotlinCode = """val btn = findViewById<Button>(R.id.btnAction)
btn.setOnClickListener {
    Toast.makeText(this, "Verified!", Toast.LENGTH_SHORT).show()
}""",
                    smaliCode = """const v0, 0x7f080042
invoke-virtual {p0, v0}, LMainActivity;->findViewById(I)Landroid/view/View;
move-result-object v0
check-cast v0, Landroid/widget/Button;"""
                )
            )
        ),
        Lesson(
            id = "and_02",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "02. Navigating Screens with Explicit Intents",
                bn = "০২. এক স্ক্রিন থেকে অন্য স্ক্রিনে যাওয়া (Intent)",
                ur = "02. ایک اسکرین سے دوسری اسکرین پر جانا (Intent)",
                hi = "02. एक स्क्रीन से दूसरी स्क्रीन पर जाना (Intent)",
                ar = "02. الانتقال بين الشاشات باستخدام Intent",
                fa_af = "۰۲. جابه‌جایی بین صفحات با استفاده از Intent",
                in_id = "02. Berpindah Layar Menggunakan Explicit Intent"
            ),
            subtitle = localized(
                en = "Pass extras safely between Activities using Intent and putExtra.",
                bn = "Intent এবং putExtra ব্যবহার করে ডাটা সহ নতুন স্ক্রিন চালু করার নিয়ম।",
                ur = "Intent اور putExtra کے ذریعے ڈیٹا کے ساتھ نئی اسکرین کھولنا۔",
                hi = "Intent और putExtra के साथ डेटा भेजकर नई स्क्रीन खोलना।",
                ar = "تمرير البيانات بأمان بين الواجهات عبر Intent و putExtra.",
                fa_af = "ارسال داده بین صفحات برنامه با استفاده از Intent و putExtra.",
                in_id = "Kirim data antar Activity dengan aman memakai Intent dan putExtra."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Creating & Launching an Activity Intent",
                        bn = "Intent তৈরি এবং নতুন Activity চালু করা",
                        ur = "Intent بنانا اور نئی Activity چلانا",
                        hi = "Intent बनाना और नई Activity शुरू करना",
                        ar = "إنشاء وإطلاق Intent لفتح شاشة جديدة",
                        fa_af = "ساخت و اجرای Intent برای باز کردن صفحه جدید",
                        in_id = "Membuat & Menjalankan Intent Activity"
                    ),
                    content = localized(
                        en = "An `Intent` is a message envelope telling Android which target `Activity` class to open and carrying key-value `Bundle` extras.",
                        bn = "`Intent` হলো একটি ডিজিটাল খাম—যার ওপরে গন্তব্য স্ক্রিনের নাম লেখা থাকে এবং ভেতরে `putExtra` দিয়ে যেকোনো তথ্য ভরে পাঠানো যায়।",
                        ur = "`Intent` ایک ڈیجیٹل لفافہ ہے جس پر اگلی اسکرین کا نام درج ہوتا ہے اور اندر `putExtra` کے ذریعے معلومات رکھی جاتی ہیں۔",
                        hi = "`Intent` एक डिजिटल लिफ़ाफ़ा है जिस पर अगली स्क्रीन का नाम होता है और भीतर `putExtra` से डेटा रखा जाता है।",
                        ar = "يمثل `Intent` ظرف رسالة يحدد الشاشة الهدف ويحمل البيانات الإضافية عبر `putExtra`.",
                        fa_af = "`Intent` مانند پاکت نامه‌ای است که مقصد صفحه بعدی را مشخص کرده و داده‌ها را با `putExtra` منتقل می‌کند.",
                        in_id = "`Intent` adalah amplop pesan yang memberi tahu Android kelas `Activity` tujuan beserta data `putExtra`."
                    ),
                    kotlinCode = """val intent = Intent(this, ProfileActivity::class.java)
intent.putExtra("USER_NAME", "Morsalin")
startActivity(intent)""",
                    smaliCode = """new-instance v0, Landroid/content/Intent;
const-class v1, Lcom/example/ProfileActivity;
invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V
const-string v1, "USER_NAME"
const-string v2, "Morsalin"
invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
invoke-virtual {p0, v0}, LMainActivity;->startActivity(Landroid/content/Intent;)V"""
                )
            )
        ),
        Lesson(
            id = "and_03",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "03. Activity Lifecycle (onCreate to onDestroy)",
                bn = "০৩. অ্যাক্টিভিটি লাইফসাইকেল (onCreate থেকে onDestroy)",
                ur = "03. ایکٹیویٹی لائف سائیکل (onCreate سے onDestroy)",
                hi = "03. एक्टिविटी लाइफसाइकिल (onCreate से onDestroy)",
                ar = "03. دورة حياة الواجهة (من onCreate إلى onDestroy)",
                fa_af = "۰۳. چرخه حیات اکتیویتی (از onCreate تا onDestroy)",
                in_id = "03. Siklus Hidup Activity (onCreate hingga onDestroy)"
            ),
            subtitle = localized(
                en = "Understand why super.onCreate is mandatory in both Kotlin and Smali.",
                bn = "অ্যাপ চালু, মিনিমাইজ ও বন্ধ হওয়ার ধাপ এবং কেন super.onCreate না দিলে অ্যাপ ক্র্যাশ করে।",
                ur = "ایپ کے کھلنے، پس منظر میں جانے اور بند ہونے کے مراحل اور super.onCreate کی اہمیت۔",
                hi = "ऐप खुलने, बैकग्राउंड में जाने और बंद होने के चरण तथा super.onCreate की अनिवार्यता।",
                ar = "افهم مراحل عمل الشاشة ولماذا يُعد استدعاء super.onCreate إلزامياً.",
                fa_af = "درک مراحل باز شدن، توقف و بسته شدن صفحه و الزام فراخوانی super.onCreate.",
                in_id = "Pahami tahapan hidup layar dan mengapa super.onCreate wajib dipanggil."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Lifecycle Callbacks & invoke-super",
                        bn = "লাইফসাইকেল ধাপসমূহ ও invoke-super এর ভূমিকা",
                        ur = "لائف سائیکل کال بیکس اور invoke-super کا کردار",
                        hi = "लाइफसाइकिल कॉलबैक और invoke-super की भूमिका",
                        ar = "دوال دورة الحياة وتعليمة invoke-super",
                        fa_af = "متدهای چرخه حیات و نقش دستور invoke-super",
                        in_id = "Callback Siklus Hidup & Peran invoke-super"
                    ),
                    content = localized(
                        en = "Android triggers `onCreate` → `onStart` → `onResume` when opening a screen, and `onPause` → `onStop` → `onDestroy` when leaving. Omitting `invoke-super` inside `onCreate` throws `SuperNotCalledException`!",
                        bn = "স্ক্রিন চালু হলে ক্রমান্বয়ে `onCreate` → `onStart` → `onResume` চলে, আর বের হয়ে গেলে `onPause` → `onStop` → `onDestroy` চলে। স্মালিতে `onCreate`-এর শুরুতে `invoke-super` মুছে ফেললে সাথে সাথে অ্যাপ ক্র্যাশ করে!",
                        ur = "اسکرین کھلتے وقت `onCreate` → `onStart` → `onResume` چلتا ہے۔ اگر `invoke-super` ہٹا دیا جائے تو ایپ فوراً کریش ہو جاتی ہے!",
                        hi = "स्क्रीन खुलते समय `onCreate` → `onStart` → `onResume` चलता है। यदि `invoke-super` हटा दिया जाए तो तुरंत क्रैश होता है!",
                        ar = "يستدعي النظام `onCreate` ثم `onStart` ثم `onResume`، وحذف `invoke-super` يؤدي إلى انهيار فوري للتطبيق.",
                        fa_af = "هنگام باز شدن صفحه به ترتیب `onCreate`، `onStart` و `onResume` اجرا می‌شوند و حذف `invoke-super` باعث کرش فوری می‌شود.",
                        in_id = "Android memanggil `onCreate` → `onStart` → `onResume`. Menghapus `invoke-super` akan memicu `SuperNotCalledException`."
                    ),
                    smaliCode = """.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    invoke-super {p0, p1}, Landroidx/appcompat/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V
    const v0, 0x7f0b001c
    invoke-virtual {p0, v0}, LMainActivity;->setContentView(I)V
    return-void
.end method"""
                )
            )
        ),
        Lesson(
            id = "and_04",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "04. Saving Preferences Locally (SharedPreferences)",
                bn = "০৪. ফোনে স্থায়ীভাবে ডাটা সেভ করা (SharedPreferences)",
                ur = "04. فون میں مستقل ڈیٹا محفوظ کرنا (SharedPreferences)",
                hi = "04. फ़ोन में स्थायी डेटा सहेजना (SharedPreferences)",
                ar = "04. حفظ الإعدادات محلياً (SharedPreferences)",
                fa_af = "۰۴. ذخیره‌سازی دائمی تنظیمات در گوشی (SharedPreferences)",
                in_id = "04. Menyimpan Data Lokal (SharedPreferences)"
            ),
            subtitle = localized(
                en = "Store theme, language, and flags persistently across app restarts.",
                bn = "অ্যাপ বন্ধ করলেও যেন থিম, ভাষা ও সেটিংস মুছে না যায় তার পূর্ণাঙ্গ পদ্ধতি।",
                ur = "ایپ بند ہونے کے بعد بھی زبان، تھیم اور سیٹنگز محفوظ رکھنے کا طریقہ۔",
                hi = "ऐप बंद होने के बाद भी थीम, भाषा और सेटिंग्स सुरक्षित रखने की विधि।",
                ar = "احفظ اللغة والمظهر والخيارات بحيث تبقى بعد إعادة تشغيل التطبيق.",
                fa_af = "ذخیره زبان، پوسته و تنظیمات به شکلی که پس از بستن برنامه باقی بماند.",
                in_id = "Simpan tema, bahasa, dan opsi agar tetap bertahan saat aplikasi dibuka ulang."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Key-Value XML Storage Under the Hood",
                        bn = "কী-ভ্যালু আকারে ডাটা সেভ ও রিড করার নিয়ম",
                        ur = "کی-ویلیو کی صورت میں ڈیٹا لکھنا اور پڑھنا",
                        hi = "की-वैल्यू के रूप में डेटा सहेजना और पढ़ना",
                        ar = "تخزين واسترجاع أزواج المفتاح والقيمة",
                        fa_af = "ذخیره و بازیابی داده‌ها به صورت کلید-مقدار",
                        in_id = "Menyimpan & Membaca Pasangan Kunci-Nilai"
                    ),
                    content = localized(
                        en = "`SharedPreferences` writes lightweight XML files inside `/data/data/<package>/shared_prefs/`. Always call `.apply()` to commit asynchronously without freezing the UI thread.",
                        bn = "`SharedPreferences` অ্যাপের প্রাইভেট ফোল্ডারে ছোট XML ফাইলে ডাটা সেভ করে। UI যেন আটকে না যায় তাই সবসময় `.apply()` ব্যবহার করা উচিত।",
                        ur = "`SharedPreferences` ایپ کے نجی فولڈر میں چھوٹی XML فائل بناتا ہے۔ بغیر رکاوٹ محفوظ کرنے کے لیے `.apply()` استعمال کریں۔",
                        hi = "`SharedPreferences` ऐप के निजी फ़ोल्डर में छोटी XML फ़ाइल सहेजता है। बिना रुकावट सहेजने के लिए `.apply()` का उपयोग करें।",
                        ar = "تحفظ `SharedPreferences` البيانات في ملف XML خاص بالتطبيق، ويُفضّل استدعاء `.apply()` للحفظ غير المتزامن.",
                        fa_af = "`SharedPreferences` داده‌ها را در پوشه خصوصی برنامه ذخیره می‌کند و `.apply()` بدون قفل کردن صفحه آن‌ها را ثبت می‌نماید.",
                        in_id = "`SharedPreferences` menyimpan file XML ringan di folder privat aplikasi; gunakan `.apply()` agar UI tetap mulus."
                    ),
                    kotlinCode = """val prefs = getSharedPreferences("app_cfg", MODE_PRIVATE)
prefs.edit().putBoolean("dark_mode", true).apply()
val isDark = prefs.getBoolean("dark_mode", true)"""
                )
            )
        )
    ) + TrackExtraCurriculumData.extraAndroidLessons

    val javaLessons: List<Lesson> =
        JavaBeginnerCurriculumData.lessons + JavaAdvancedCurriculumData.lessons

    val kotlinLessons: List<Lesson> = listOf(
        Lesson(
            id = "kt_01",
            track = TrackType.KOTLIN,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "01. Kotlin val vs var & Null Safety (? and ?:)",
                bn = "০১. কোটলিন val বনাম var এবং নাল-সেফটি (? ও ?:)",
                ur = "01. کوٹلن val بمقابلہ var اور نل سیفٹی (? اور ?:)",
                hi = "01. कोटलिन val बनाम var और नल-सेफ्टी (? व ?:)",
                ar = "01. الفرق بين val و var والأمان من القيم الفارغة في كوتلن",
                fa_af = "۰۱. تفاوت val و var و امنیت نال در کاتلین (? و ?:)",
                in_id = "01. Kotlin val vs var & Keamanan Null (? dan ?:)"
            ),
            subtitle = localized(
                en = "Eliminate NullPointerException crashes using Kotlin's type system.",
                bn = "অ্যাপ ক্র্যাশ বন্ধ করতে কোটলিনের স্মার্ট নাল-সেফটি ও অপরিবর্তনীয় val শিখুন।",
                ur = "ایپ کریش روکنے کے لیے کوٹلن کی نل سیفٹی اور val کا استعمال۔",
                hi = "क्रैश रोकने के लिए कोटलिन की नल-सेफ्टी और अपरिवर्तनीय val सीखें।",
                ar = "امنع انهيار التطبيقات بسبب القيم الفارغة عبر نظام الأنواع في كوتلن.",
                fa_af = "جلوگیری کامل از کرش NullPointerException با سیستم نوع داده کاتلین.",
                in_id = "Hindari crash NullPointerException dengan sistem tipe aman Kotlin."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Immutable val & Elvis Operator (?:)",
                        bn = "অপরিবর্তনীয় val এবং এলভিস অপারেটর (?:)",
                        ur = "ناقابل تبدیل val اور ایلوس آپریٹر (?:)",
                        hi = "अपरिवर्तनीय val और एल्विस ऑपरेटर (?:)",
                        ar = "الثوابت val ومعامل القيمة البديلة (?:)",
                        fa_af = "متغیر ثابت val و عملگر الویس (?:)",
                        in_id = "Nilai Tetap val & Operator Elvis (?:)"
                    ),
                    content = localized(
                        en = "`val` creates a read-only reference (`final` in bytecode), while `String?` explicitly marks nullable text. The Elvis operator `?:` supplies an instant fallback when a value is null.",
                        bn = "`val` দিয়ে তৈরি মান পরে বদলানো যায় না (বাইটকোডে `final` হয়)। আর কোনো ভেরিয়েবল খালি (`null`) হতে পারলে টাইপের শেষে `?` দিতে হয় এবং `?:` দিয়ে ডিফল্ট মান দেওয়া যায়।",
                        ur = "`val` کی قیمت دوبارہ تبدیل نہیں ہو سکتی، جبکہ `?` نل ہونے کی اجازت دیتا ہے اور `?:` متبادل قیمت فراہم کرتا ہے۔",
                        hi = "`val` का मान बदला नहीं जा सकता, जबकि `?` नल की अनुमति देता है और `?:` डिफ़ॉल्ट मान देता है।",
                        ar = "تعرف `val` مرجعاً للقراءة فقط، بينما تسمح `?` بالقيمة الفارغة ويوفر `?:` قيمة بديلة آمنة.",
                        fa_af = "`val` مقدار فقط‌خواندنی می‌سازد، علامت `?` اجازه نال بودن می‌دهد و `?:` مقدار پیش‌فرض جایگزین تعیین می‌کند.",
                        in_id = "`val` bersifat baca-saja (`final`), `String?` mengizinkan null, dan `?:` memberi nilai cadangan."
                    ),
                    kotlinCode = """val appTitle: String = "Smali Learn Pro"
var nickname: String? = null
val display = nickname ?: "Guest Learner" // Safe!"""
                )
            )
        ),
        Lesson(
            id = "kt_02",
            track = TrackType.KOTLIN,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "02. Data Classes, when Expressions & Lambdas",
                bn = "০২. কোটলিন Data Class, when এবং ল্যাম্বডা ফাংশন",
                ur = "02. کوٹلن Data Class، when اور لیمبڈا فنکشنز",
                hi = "02. कोटलिन Data Class, when और लैम्ब्डा फ़ंक्शन",
                ar = "02. فئات البيانات Data Class وتعبير when والدوال السهمية",
                fa_af = "۰۲. دیتا کلاس‌ها، عبارت when و توابع لامبدا در کاتلین",
                in_id = "02. Data Class, Ekspresi when & Fungsi Lambda"
            ),
            subtitle = localized(
                en = "See what Kotlin auto-generates inside Smali for a 1-line data class.",
                bn = "মাত্র ১ লাইনের data class কম্পাইল হয়ে স্মালিতে কী কী মেথড তৈরি করে তা দেখুন।",
                ur = "دیکھیں کہ ایک لائن کی data class سمالی میں کون سے میتھڈز خودکار بناتی ہے۔",
                hi = "देखें कि 1 लाइन की data class स्माली में कौन-कौन से मेथड स्वतः बनाती है।",
                ar = "شاهد ما يولده مترجم كوتلن تلقائياً في سمالي لفئة بيانات من سطر واحد.",
                fa_af = "مشاهده متدهایی که کاتلین به طور خودکار در سمالی برای یک data class می‌سازد.",
                in_id = "Lihat metode apa saja yang otomatis dibuat Kotlin di Smali untuk data class."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Auto-Generated copy, equals, hashCode & component1",
                        bn = "স্বয়ংক্রিয়ভাবে তৈরি copy, equals, hashCode ও component1 মেথড",
                        ur = "خودکار بننے والے copy، equals، hashCode اور component1 میتھڈز",
                        hi = "स्वतः निर्मित copy, equals, hashCode और component1 मेथड",
                        ar = "التوليد التلقائي لدوال copy و equals و hashCode و component1",
                        fa_af = "تولید خودکار متدهای copy، equals، hashCode و component1",
                        in_id = "Pembuatan Otomatis copy, equals, hashCode & component1"
                    ),
                    content = localized(
                        en = "Writing `data class Opcode(val name: String, val hex: Int)` generates getters, `equals`, `hashCode`, `toString`, `copy`, and destructuring `component1()`/`component2()` methods in Smali!",
                        bn = "কোটলিনে মাত্র এক লাইনে `data class` লিখলে কম্পাইলার নিজে থেকেই স্মালি ফাইলে গেটার, `equals`, `hashCode`, `toString`, `copy` এবং `component1()` মেথড তৈরি করে দেয়!",
                        ur = "کوٹلن میں ایک لائن کی `data class` لکھنے پر کمپائلر خودکار طور پر سمالی میں `equals`، `hashCode`، `toString` اور `copy` بنا دیتا ہے!",
                        hi = "कोटलिन में एक लाइन की `data class` लिखने पर कंपाइलर स्माली में स्वतः `equals`, `hashCode`, `toString` और `copy` बना देता है!",
                        ar = "كتابة `data class` في سطر واحد تولد تلقائياً دوال `equals` و `hashCode` و `toString` و `copy` في ملف سمالي!",
                        fa_af = "نوشتن یک خط `data class` باعث تولید خودکار متدهای `equals`، `hashCode`، `toString` و `copy` در سمالی می‌شود!",
                        in_id = "Menulis 1 baris `data class` otomatis menghasilkan `equals`, `hashCode`, `toString`, `copy`, dan `component1()` di Smali!"
                    ),
                    kotlinCode = """data class OpcodeInfo(val name: String, val hex: Int)

fun describeCategory(code: Int): String = when (code) {
    0x12 -> "const/4"
    0x6e -> "invoke-virtual"
    else -> "other"
}"""
                )
            )
        )
    ) + TrackExtraCurriculumData.extraKotlinLessons

    val pythonLessons: List<Lesson> = listOf(
        Lesson(
            id = "py_01",
            track = TrackType.PYTHON,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "01. Inspecting APK Archives & DEX Headers with Python",
                bn = "০১. পাইথন দিয়ে APK ফাইল ও DEX হেডার বিশ্লেষণ",
                ur = "01. پائتھن کے ذریعے APK اور DEX ہیڈر کا تجزیہ",
                hi = "01. पायथन से APK आर्काइव और DEX हेडर का निरीक्षण",
                ar = "01. فحص حزم APK وترويسات DEX بلغة بايثون",
                fa_af = "۰۱. بررسی ساختار فایل APK و هدر DEX با پایتون",
                in_id = "01. Memeriksa Arsip APK & Header DEX dengan Python"
            ),
            subtitle = localized(
                en = "Automate listing classes.dex files and verifying the 'dex\\n035' magic header.",
                bn = "মাত্র কয়েক লাইনের পাইথন স্ক্রিপ্ট দিয়ে APK-এর ভেতরের DEX ফাইল ও ম্যাজিক বাইট চেক করুন।",
                ur = "چند لائنوں کے پائتھن اسکرپٹ سے APK کے اندر موجود DEX فائلوں کی جانچ کریں۔",
                hi = "कुछ लाइनों की पायथन स्क्रिप्ट से APK के भीतर DEX फ़ाइलों और मैजिक बाइट की जांच करें।",
                ar = "أتمتة استخراج ملفات classes.dex والتحقق من البايتات السحرية للتطبيق.",
                fa_af = "خودکارسازی بررسی فایل‌های classes.dex و امضای جادویی هدر با پایتون.",
                in_id = "Otomasi pemeriksaan file classes.dex dan magic byte header DEX."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Reading APK ZIP Entries & DEX Magic Bytes",
                        bn = "APK জিপ এন্ট্রি এবং DEX ম্যাজিক বাইট পড়া",
                        ur = "APK زپ انٹریز اور DEX میجک بائٹس پڑھنا",
                        hi = "APK ज़िप एंट्री और DEX मैजिक बाइट्स पढ़ना",
                        ar = "قراءة محتويات حزمة APK وتوقيع ملف DEX",
                        fa_af = "خواندن محتویات فشرده APK و بایت‌های آغازین DEX",
                        in_id = "Membaca Entri ZIP APK & Magic Byte DEX"
                    ),
                    content = localized(
                        en = "Every `.apk` is a standard ZIP archive, and every valid `classes.dex` starts with the 8-byte ASCII signature `dex\\n035\\x00` (or `039` on newer Android builds).",
                        bn = "প্রতিটি `.apk` মূলত একটি ZIP ফাইল, আর তার ভেতরের প্রতিটি আসল `classes.dex` ফাইলের প্রথম ৮ বাইটে `dex\\n035\\x00` (বা `039`) সিগনেচার লেখা থাকে। পাইথনের `zipfile` মডিউল দিয়ে মুহূর্তেই এটি পরীক্ষা করা যায়।",
                        ur = "ہر `.apk` دراصل ایک ZIP فائل ہوتی ہے اور ہر درست `classes.dex` کے شروع میں `dex\\n035` دستخط موجود ہوتا ہے۔",
                        hi = "प्रत्येक `.apk` वास्तव में एक ZIP आर्काइव होता है, और हर वैध `classes.dex` के शुरू में `dex\\n035` सिग्नेचर होता है।",
                        ar = "كل ملف `.apk` هو أرشيف ZIP قياسي، ويبدأ كل ملف `classes.dex` صالح بالتوقيع `dex\\n035`.",
                        fa_af = "هر فایل `.apk` یک آرشیو استاندارد ZIP است و هر فایل معتبر `classes.dex` با امضای `dex\\n035` آغاز می‌شود.",
                        in_id = "Setiap `.apk` adalah arsip ZIP standar, dan setiap `classes.dex` valid diawali tanda `dex\\n035`."
                    ),
                    pythonCode = """import zipfile

def audit_apk(apk_path: str):
    with zipfile.ZipFile(apk_path, "r") as zf:
        dex_files = [n for n in zf.namelist() if n.endswith(".dex")]
        print(f"Found {len(dex_files)} DEX files:", dex_files)
        magic = zf.read("classes.dex")[:8]
        print("DEX Header Magic:", magic)"""
                )
            )
        ),
        Lesson(
            id = "py_02",
            track = TrackType.PYTHON,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "02. Scanning Smali Folders for Opcodes & Security Patterns",
                bn = "০২. পাইথন দিয়ে হাজার হাজার স্মালি ফাইল স্বয়ংক্রিয়ভাবে সার্চ করা",
                ur = "02. پائتھن سے ہزاروں سمالی فائلوں میں خودکار تلاش",
                hi = "02. पायथन से हज़ारों स्माली फ़ाइलों में ऑपकोड व पैटर्न खोजना",
                ar = "02. مسح مجلدات سمالي تلقائياً للبحث عن التعليمات والأنماط",
                fa_af = "۰۲. اسکن خودکار هزاران فایل سمالی با اسکریپت پایتون",
                in_id = "02. Memindai Ribuan File Smali Otomatis dengan Python"
            ),
            subtitle = localized(
                en = "Write a clean pathlib script to audit WebView settings or hardcoded URLs across Smali trees.",
                bn = "বড় প্রজেক্টের সব `.smali` ফাইল স্ক্যান করে মুহূর্তেই নির্দিষ্ট মেথড কল খুঁজে বের করার স্ক্রিপ্ট।",
                ur = "بڑے پروجیکٹ کی تمام `.smali` فائلوں کو اسکین کر کے مخصوص میتھڈ کالز تلاش کریں۔",
                hi = "बड़े प्रोजेक्ट की सभी `.smali` फ़ाइलों को स्कैन कर विशिष्ट मेथड कॉल तुरंत खोजें।",
                ar = "اكتب سكربت بايثون لفحص إعدادات الأمان واستدعاءات الدوال عبر شجرة ملفات سمالي.",
                fa_af = "نوشتن اسکریپت تمیز برای یافتن فراخوانی‌های خاص در میان تمام پوشه‌های سمالی.",
                in_id = "Gunakan skrip pathlib untuk mengaudit pemanggilan metode di seluruh pohon folder Smali."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Automated Smali Static Security Scanner",
                        bn = "স্বয়ংক্রিয় স্মালি সিকিউরিটি অডিট স্ক্যানার",
                        ur = "خودکار سمالی سیکیورٹی آڈٹ اسکینر",
                        hi = "स्वचालित स्माली सुरक्षा ऑडिट स्कैनर",
                        ar = "ماسح أمني ثابت وذكي لملفات سمالي",
                        fa_af = "اسکنر خودکار بررسی امنیتی فایل‌های سمالی",
                        in_id = "Pemindai Audit Keamanan Statis Smali"
                    ),
                    content = localized(
                        en = "Security engineers audit their own release APKs by scanning decompiled `.smali` files for risky API calls such as `setJavaScriptEnabled` or cleartext `http://` endpoints.",
                        bn = "সিকিউরিটি ইঞ্জিনিয়াররা নিজেদের অ্যাপ রিলিজ দেওয়ার আগে পাইথন স্ক্রিপ্ট দিয়ে সব `.smali` ফাইল স্ক্যান করে দেখেন কোথাও অনিরাপদ `http://` লিংক বা `setJavaScriptEnabled` রয়ে গেছে কি না।",
                        ur = "سیکیورٹی انجینئرز ریلیز سے پہلے پائتھن کے ذریعے تمام `.smali` فائلوں میں غیر محفوظ `http://` لنکس چیک کرتے ہیں۔",
                        hi = "सुरक्षा इंजीनियर रिलीज़ से पहले पायथन द्वारा सभी `.smali` फ़ाइलों में असुरक्षित `http://` लिंक या API कॉल जांचते हैं।",
                        ar = "يفحص مهندسو الأمان ملفات `.smali` برمجياً لاكتشاف الروابط غير المشفرة أو الإعدادات الحساسة قبل النشر.",
                        fa_af = "مهندسان امنیت پیش از انتشار برنامه با اسکریپت پایتون تمام فایل‌های `.smali` را برای یافتن لینک‌های ناامن بررسی می‌کنند.",
                        in_id = "Insinyur keamanan memindai `.smali` untuk mendeteksi URL `http://` atau API sensitif sebelum rilis."
                    ),
                    pythonCode = """from pathlib import Path

def scan_smali_tree(root_dir: str, needle: str):
    for smali_file in Path(root_dir).rglob("*.smali"):
        for idx, line in enumerate(smali_file.read_text(errors="ignore").splitlines(), 1):
            if needle in line:
                print(f"{smali_file.name}:{idx} -> {line.strip()}")"""
                )
            )
        )
    ) + TrackExtraCurriculumData.extraPythonLessons

    val allLessons: List<Lesson> = androidLessons + javaLessons + kotlinLessons + pythonLessons
}
