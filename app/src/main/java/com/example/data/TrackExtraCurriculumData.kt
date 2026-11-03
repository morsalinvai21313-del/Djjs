package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object TrackExtraCurriculumData {

    val extraAndroidLessons: List<Lesson> = listOf(
        Lesson(
            id = "and_05",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "05. RecyclerView, ViewHolder & Dynamic Lists",
                bn = "০৫. ডায়নামিক লিস্ট ও RecyclerView আর্কিটেকচার",
                ur = "05. ڈائنامک لسٹ اور RecyclerView آرکیٹیکچر",
                hi = "05. डायनामिक लिस्ट और RecyclerView आर्किटेक्चर",
                ar = "05. القوائم الديناميكية وهندسة RecyclerView",
                fa_af = "۰۵. لیست‌های پویا و معماری RecyclerView",
                in_id = "05. Daftar Dinamis & Arsitektur RecyclerView"
            ),
            subtitle = localized(
                en = "Render thousands of items smoothly at 60fps by recycling ViewHolders.",
                bn = "হাজার হাজার আইটেম র‍্যাম নষ্ট না করে মসৃণভাবে স্ক্রল করানোর প্রফেশনাল পদ্ধতি।",
                ur = "میموری ضائع کیے بغیر ہزاروں آئٹمز کو ہموار اسکرول کرنے کا طریقہ۔",
                hi = "मेमोरी बचाते हुए हज़ारों आइटम को 60fps पर स्मूद स्क्रॉल करने की तकनीक।",
                ar = "عرض آلاف العناصر بسلاسة تامة عبر إعادة تدوير حاملات العرض ViewHolder.",
                fa_af = "نمایش روان هزاران آیتم بدون هدررفت رم با بازیافت ViewHolder.",
                in_id = "Tampilkan ribuan item secara mulus dengan mendaur ulang ViewHolder."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "How ViewHolder Recycling Saves RAM",
                        bn = "কীভাবে ViewHolder রিসাইক্লিং মেমোরি বাঁচায়",
                        ur = "ViewHolder ری سائیکلنگ میموری کیسے بچاتی ہے",
                        hi = "ViewHolder रीसाइक्लिंग RAM कैसे बचाती है",
                        ar = "كيف يوفر تدوير ViewHolder استهلاك الذاكرة",
                        fa_af = "چگونه بازیافت ViewHolder مصرف حافظه رم را کاهش می‌دهد",
                        in_id = "Bagaimana Daur Ulang ViewHolder Menghemat RAM"
                    ),
                    childAnalogy = localized(
                        en = "Instead of buying 10,000 plates for 10,000 guests, a restaurant washes and reuses 12 plates as guests rotate!",
                        bn = "১০,০০০ অতিথির জন্য ১০,০০০ প্লেট না কিনে রেস্তোরাঁ যেমন মাত্র ১২টি প্লেট ধুয়ে বারবার খাবার পরিবেশন করে, `RecyclerView` ঠিক তেমনি কাজ করে!",
                        ur = "دس ہزار مہمانوں کے لیے دس ہزار پلیٹیں خریدنے کے بجائے ریستوران 12 پلیٹیں دھو کر دوبارہ استعمال کرتا ہے!",
                        hi = "10,000 मेहमानों के लिए 10,000 प्लेट खरीदने के बजाय रेस्तरां 12 प्लेटों को साफ़ कर बार-बार उपयोग करता है!",
                        ar = "بدلاً من شراء 10 آلاف طبق، يعيد المطعم استخدام 12 طبقاً فقط مع تبدل الضيوف!",
                        fa_af = "به‌جای خرید ده هزار بشقاب، رستوران تنها ۱۲ بشقاب را شسته و دوباره برای مهمانان بعدی پر می‌کند!",
                        in_id = "Alih-alih membeli 10.000 piring, restoran cukup mencuci dan memakai ulang 12 piring secara bergantian!"
                    ),
                    content = localized(
                        en = "`onCreateViewHolder` inflates only enough row views to fill the screen (~10), while `onBindViewHolder` swaps fresh text/icons into existing cached references as you scroll.",
                        bn = "`onCreateViewHolder` স্ক্রিনে যতটুকু ধরে কেবল সেই ১০-১২টি কার্ড তৈরি করে, আর স্ক্রল করার সময় `onBindViewHolder` পুরোনো কার্ডেই নতুন লেখা বসিয়ে দেয়।",
                        ur = "`onCreateViewHolder` صرف اسکرین بھرنے جتنے کارڈز بناتا ہے اور `onBindViewHolder` اسکرول کے وقت ان میں نیا متن بھرتا ہے۔",
                        hi = "`onCreateViewHolder` केवल स्क्रीन भरने जितने कार्ड बनाता है और `onBindViewHolder` स्क्रॉल पर नया डेटा भरता है।",
                        ar = "تنشئ `onCreateViewHolder` صفوف الشاشة فقط، وتحدّث `onBindViewHolder` محتواها أثناء التمرير.",
                        fa_af = "`onCreateViewHolder` فقط به اندازه صفحه کارت می‌سازد و `onBindViewHolder` هنگام اسکرول داده جدید را جایگزین می‌کند.",
                        in_id = "`onCreateViewHolder` hanya membuat tampilan seukuran layar, lalu `onBindViewHolder` mengisi ulang datanya saat digulir."
                    ),
                    javaCode = """public final class LessonHolder extends RecyclerView.ViewHolder {
    final TextView titleView;
    public LessonHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.tvTitle);
    }
}"""
                )
            )
        ),
        Lesson(
            id = "and_06",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "06. Runtime Permissions & Hardened WebView Security",
                bn = "০৬. রানটাইম পারমিশন ও নিরাপদ WebView আর্কিটেকচার",
                ur = "06. رن ٹائم پرمیشنز اور محفوظ WebView آرکیٹیکچر",
                hi = "06. रनटाइम अनुमतियां और सुरक्षित WebView आर्किटेक्चर",
                ar = "06. أذونات التشغيل وتأمين WebView الاحترافي",
                fa_af = "۰۶. مجوزهای زمان اجرا و امنیت حرفه‌ای WebView",
                in_id = "06. Izin Runtime & Keamanan WebView Profesional"
            ),
            subtitle = localized(
                en = "Protect Android apps against exported Intent hijacking and unsafe JavaScript bridges.",
                bn = "অ্যান্ড্রয়েড অ্যাপকে অনিরাপদ JavaScript ব্রিজ এবং এক্সপোর্টেড Intent ঝুঁকি থেকে সুরক্ষিত রাখুন।",
                ur = "اپنی ایپ کو غیر محفوظ جاوا اسکرپٹ برج اور ایکسپورٹڈ انٹینٹ خطرات سے محفوظ رکھیں۔",
                hi = "असुरक्षित JavaScript ब्रिज और एक्सपोर्टेड Intent जोखिमों से ऐप की रक्षा करें।",
                ar = "احمِ تطبيقك من ثغرات جسور JavaScript والواجهات المكشوفة غير المحمية.",
                fa_af = "محافظت از برنامه در برابر آسیب‌پذیری‌های WebView و کامپوننت‌های اکسپورت‌شده.",
                in_id = "Lindungi aplikasi dari celah jembatan JavaScript dan komponen exported yang rawan."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Hardening WebSettings & Explicit Export Flags",
                        bn = "WebSettings সুরক্ষা এবং android:exported=\"false\" নিয়ম",
                        ur = "WebSettings کی حفاظت اور android:exported کے اصول",
                        hi = "WebSettings सुरक्षा और android:exported=\"false\" नियम",
                        ar = "تحصين إعدادات WebSettings وضبط android:exported بدقة",
                        fa_af = "ایمن‌سازی تنظیمات WebSettings و قانون android:exported",
                        in_id = "Memperketat WebSettings & Aturan android:exported"
                    ),
                    content = localized(
                        en = "On Android 12+ (API 31+), every Activity/Receiver with an `<intent-filter>` must explicitly declare `android:exported`. In `WebView`, disable `setAllowFileAccess(false)` and restrict `addJavascriptInterface` strictly to verified HTTPS origins.",
                        bn = "অ্যান্ড্রয়েড ১২+ থেকে প্রতিটি Activity-তে স্পষ্টভাবে `android:exported` লেখা বাধ্যতামূলক। আর `WebView` ব্যবহারের সময় অবশ্যই `setAllowFileAccess(false)` রাখুন এবং শুধুমাত্র বিশ্বস্ত `https://` ডোমেইন লোড করুন।",
                        ur = "اینڈرائیڈ 12+ میں `android:exported` لکھنا لازمی ہے، اور `WebView` میں `setAllowFileAccess(false)` رکھ کر صرف محفوظ HTTPS لوڈ کریں۔",
                        hi = "एंड्रॉइड 12+ में `android:exported` अनिवार्य है, और `WebView` में `setAllowFileAccess(false)` रखकर केवल सत्यापित HTTPS लोड करें।",
                        ar = "في أندرويد 12+ يلزم تحديد `android:exported`، وفي `WebView` عطّل الوصول للملفات واقتصر على نطاقات HTTPS الموثوقة.",
                        fa_af = "در اندروید ۱۲ به بالا تعیین `android:exported` الزامی است و در `WebView` باید دسترسی به فایل‌ها را بست و فقط HTTPS معتبر بارگذاری کرد.",
                        in_id = "Di Android 12+, `android:exported` wajib ditulis eksplisit, dan pada `WebView` matikan `setAllowFileAccess(false)`."
                    ),
                    javaCode = """WebSettings ws = webView.getSettings();
ws.setAllowFileAccess(false);
ws.setAllowContentAccess(false);
webView.loadUrl("https://developer.android.com");""",
                    smaliCode = """const/4 v1, 0x0
invoke-virtual {v0, v1}, Landroid/webkit/WebSettings;->setAllowFileAccess(Z)V
invoke-virtual {v0, v1}, Landroid/webkit/WebSettings;->setAllowContentAccess(Z)V"""
                )
            )
        )
    )

    val extraKotlinLessons: List<Lesson> = listOf(
        Lesson(
            id = "kt_03",
            track = TrackType.KOTLIN,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "03. Extension Functions & Functional Collections (map / filter)",
                bn = "০৩. এক্সটেনশন ফাংশন এবং কালেকশন পাইপলাইন (map / filter)",
                ur = "03. ایکسٹینشن فنکشنز اور کلیکشن پائپ لائن (map / filter)",
                hi = "03. एक्सटेंशन फ़ंक्शन और कलेक्शन पाइपलाइन (map / filter)",
                ar = "03. دوال التوسيع ومعالجة القوائم (map / filter)",
                fa_af = "۰۳. توابع اکستنشن و پردازش لیست‌ها (map / filter)",
                in_id = "03. Extension Function & Koleksi Fungsional (map / filter)"
            ),
            subtitle = localized(
                en = "Add clean helper methods to existing classes and see how Smali compiles them as static methods.",
                bn = "যেকোনো ক্লাসে নতুন মেথড যোগ করুন এবং দেখুন স্মালিতে সেটি কীভাবে স্ট্যাটিক মেথড হয়ে যায়!",
                ur = "موجودہ کلاسز میں نئے مددگار میتھڈز جوڑیں اور سمالی میں ان کی اسٹیٹک شکل دیکھیں۔",
                hi = "मौजूदा क्लासों में नए हेल्पर मेथड जोड़ें और स्माली में उनका स्टैटिक रूप देखें।",
                ar = "أضف دوال مساعدة أنيقة للفئات وشاهد كيف يترجمها سمالي إلى دوال ثابتة.",
                fa_af = "افزودن متدهای کمکی به کلاس‌ها و مشاهده کامپایل آن‌ها به متد استاتیک در سمالی.",
                in_id = "Tambahkan metode bantu ke kelas yang ada dan amati hasil statisnya di Smali."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Extension Receivers Become p0 in Static Smali",
                        bn = "এক্সটেনশন ফাংশন স্মালিতে কীভাবে invoke-static হয়ে যায়",
                        ur = "ایکسٹینشن فنکشن سمالی میں invoke-static کیسے بنتا ہے",
                        hi = "एक्सटेंशन फ़ंक्शन स्माली में invoke-static कैसे बनता है",
                        ar = "كيف تتحول دالة التوسيع إلى invoke-static في سمالي",
                        fa_af = "تبدیل تابع اکستنشن کاتلین به invoke-static در بایت‌کد سمالی",
                        in_id = "Bagaimana Extension Function Menjadi invoke-static di Smali"
                    ),
                    content = localized(
                        en = "Writing `fun String.isHex(): Boolean` does NOT modify `java.lang.String`. Instead, Kotlin compiles a `public static final boolean isHex(String \$this)` method where the receiver string is passed in `p0`!",
                        bn = "কোটলিনে `fun String.isHex()` লিখলে আসল `String` ক্লাস বদলায় না—বরং কম্পাইলার পেছনে একটি `public static` মেথড বানায় যার প্রথম আর্গুমেন্ট `p0`-তে টেক্সটটি পাঠানো হয়!",
                        ur = "`fun String.isHex()` اصل اسٹرنگ کلاس کو نہیں بدلتا بلکہ سمالی میں ایک `static` میتھڈ بناتا ہے جس کے `p0` میں وہ اسٹرنگ بھیجا جاتا ہے!",
                        hi = "`fun String.isHex()` मूल `String` क्लास को नहीं बदलता, बल्कि स्माली में `static` मेथड बनाता है जहां `p0` रिसीवर होता है!",
                        ar = "كتابة `fun String.isHex()` لا تعدل فئة النص الأصلية، بل تولد دالة `static` تستقبل النص في `p0`!",
                        fa_af = "تعریف `fun String.isHex()` کلاس اصلی رشته را تغییر نمی‌دهد بلکه یک متد `static` می‌سازد که رشته را در `p0` می‌گیرد!",
                        in_id = "`fun String.isHex()` tidak mengubah kelas `String`, melainkan menghasilkan metode `static` dengan string penerima di `p0`!"
                    ),
                    kotlinCode = """fun String.isHexOpcode(): Boolean = startsWith("0x")

val valid = listOf("0x12", "nope", "0x6e").filter { it.isHexOpcode() }""",
                    smaliCode = """.method public static final isHexOpcode(Ljava/lang/String;)Z
    .locals 3
    const-string v0, "0x"
    const/4 v1, 0x0
    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z
    move-result v2
    return v2
.end method"""
                )
            )
        ),
        Lesson(
            id = "kt_04",
            track = TrackType.KOTLIN,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "04. Sealed Classes & Exhaustive UI State Modeling",
                bn = "০৪. Sealed Class ও প্রফেশনাল UI State মডেলিং",
                ur = "04. Sealed Class اور مکمل UI اسٹیٹ ماڈلنگ",
                hi = "04. Sealed Class और सुरक्षित UI State मॉडलिंग",
                ar = "04. الفئات المختومة Sealed Classes ونمذجة حالة الواجهة",
                fa_af = "۰۴. کلاس‌های Sealed و مدل‌سازی حرفه‌ای وضعیت رابط کاربری",
                in_id = "04. Sealed Class & Pemodelan UI State yang Aman"
            ),
            subtitle = localized(
                en = "Represent Loading, Success, and Error states with compile-time completeness.",
                bn = "Loading, Success এবং Error অবস্থাকে একদম বাগ-মুক্তভাবে মডেল করার আধুনিক কৌশল।",
                ur = "لوڈنگ، کامیابی اور ایرر کی حالتوں کو بغیر غلطی کے ماڈل کرنے کا جدید طریقہ۔",
                hi = "Loading, Success और Error अवस्थाओं को कंपाइल-टाइम सुरक्षा के साथ मॉडल करें।",
                ar = "مثّل حالات التحميل والنجاح والخطأ بأمان تام يتحقق منه المترجم.",
                fa_af = "مدل‌سازی وضعیت‌های بارگذاری، موفقیت و خطا با ایمنی کامل در زمان کامپایل.",
                in_id = "Modelkan status Loading, Success, dan Error dengan jaminan kelengkapan kompilasi."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Why Sealed Hierarchies Beat Nullable Flags",
                        bn = "কেন আলাদা বুলিয়ান ফ্ল্যাগের চেয়ে Sealed Interface অনেক সেরা",
                        ur = "متعدد بولین فلیگز سے Sealed Interface کیوں بہتر ہے",
                        hi = "अलग-अलग बूलियन फ़्लैग से Sealed Interface क्यों बेहतर है",
                        ar = "لماذا تتفوق الواجهات المختومة على المتغيرات المنطقية المتفرقة",
                        fa_af = "برتری Sealed Interface نسبت به متغیرهای بولین پراکنده",
                        in_id = "Mengapa Sealed Interface Jauh Lebih Rapi daripada Banyak Flag Boolean"
                    ),
                    content = localized(
                        en = "A `sealed interface UiState` guarantees all possible screen states are known at compile time—so `when (state)` never needs an `else` branch and smart-casts `state.data` automatically!",
                        bn = "`sealed interface UiState` ব্যবহার করলে কম্পাইলার সব সম্ভাব্য অবস্থা আগে থেকেই জানে—ফলে `when (state)` লেখার সময় `else` লাগে না এবং ডাটা অটোমেটিক Smart-cast হয়ে যায়!",
                        ur = "`sealed interface` میں تمام ممکنہ حالتیں کمپائلر کو معلوم ہوتی ہیں، اس لیے `when` میں `else` کی ضرورت نہیں پڑتی!",
                        hi = "`sealed interface` से सभी अवस्थाएं कंपाइलर को ज्ञात रहती हैं, अतः `when` में `else` की आवश्यकता नहीं होती!",
                        ar = "تضمن `sealed interface` معرفة كل الحالات وقت الترجمة، فيغنيك تعبير `when` عن فرع `else` مع تحويل ذكي للنوع!",
                        fa_af = "با `sealed interface` تمام حالت‌ها در زمان کامپایل مشخص است و در `when` بدون نیاز به `else` نوع داده هوشمندانه تشخیص داده می‌شود!",
                        in_id = "`sealed interface` memastikan semua kemungkinan state diketahui saat kompilasi sehingga `when (state)` otomatis melakukan smart-cast!"
                    ),
                    kotlinCode = """sealed interface ScreenState {
    data object Loading : ScreenState
    data class Success(val items: List<String>) : ScreenState
    data class Error(val reason: String) : ScreenState
}"""
                )
            )
        ),
        Lesson(
            id = "kt_05",
            track = TrackType.KOTLIN,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "05. Kotlin Coroutines, Dispatchers & suspend Internals",
                bn = "০৫. কোটলিন Coroutines, Dispatchers ও suspend মেকানিজম",
                ur = "05. کوٹلن Coroutines، Dispatchers اور suspend کا اندرونی نظام",
                hi = "05. कोटलिन Coroutines, Dispatchers और suspend की आंतरिक कार्यप्रणाली",
                ar = "05. الكوروتينات Coroutines وآلية عمل دوال suspend داخلياً",
                fa_af = "۰۵. کوروتین‌های کاتلین، Dispatchers و ساختار داخلی suspend",
                in_id = "05. Kotlin Coroutines, Dispatchers & Cara Kerja suspend"
            ),
            subtitle = localized(
                en = "Run database and network work off the UI thread and see the Continuation parameter in Smali.",
                bn = "UI থ্রেড ফ্রিজ না করে ব্যাকগ্রাউন্ডে কাজ চালান এবং স্মালিতে Continuation প্যারামিটার দেখুন।",
                ur = "یو آئی کو روکے بغیر بیک گراؤنڈ کام چلائیں اور سمالی میں Continuation پیرامیٹر دیکھیں۔",
                hi = "UI थ्रेड रोके बिना बैकग्राउंड कार्य चलाएं और स्माली में Continuation पैरामीटर देखें।",
                ar = "نفّذ مهام قواعد البيانات والشبكة بالخلفية وشاهد معامل Continuation في سمالي.",
                fa_af = "اجرای عملیات دیتابیس در پس‌زمینه بدون قفل شدن صفحه و مشاهده Continuation در سمالی.",
                in_id = "Jalankan tugas database di latar belakang dan lihat parameter Continuation di Smali."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "How suspend Compiles to a Continuation State Machine",
                        bn = "কীভাবে suspend ফাংশন স্মালিতে Continuation স্টেট-মেশিন হয়",
                        ur = "suspend فنکشن سمالی میں Continuation اسٹیٹ مشین کیسے بنتا ہے",
                        hi = "suspend फ़ंक्शन स्माली में Continuation स्टेट मशीन कैसे बनता है",
                        ar = "كيف تتحول دالة suspend إلى آلة حالة Continuation في سمالي",
                        fa_af = "تبدیل تابع suspend به ماشین حالت Continuation در بایت‌کد سمالی",
                        in_id = "Bagaimana Fungsi suspend Menjadi Mesin Status Continuation di Smali"
                    ),
                    content = localized(
                        en = "In Android DEX bytecode, there is no `suspend` keyword! The Kotlin compiler appends a hidden `kotlin.coroutines.Continuation` parameter to the method and uses a `packed-switch` state machine to pause and resume without blocking threads.",
                        bn = "স্মালি বাইটকোডে `suspend` বলে আলাদা কিছু নেই! কোটলিন কম্পাইলার মেথডের শেষে গোপনে একটি `Continuation` প্যারামিটার যোগ করে এবং `packed-switch` দিয়ে থ্রেড ব্লক না করেই কাজ থামায় ও পুনরায় শুরু করে!",
                        ur = "سمالی میں `suspend` نامی کوئی لفظ نہیں! کمپائلر آخر میں ایک خفیہ `Continuation` پیرامیٹر جوڑ کر اسٹیٹ مشین بناتا ہے!",
                        hi = "स्माली बाइटकोड में `suspend` नहीं होता! कंपाइलर अंत में एक छिपा `Continuation` पैरामीटर जोड़कर स्टेट मशीन बनाता है!",
                        ar = "لا توجد كلمة `suspend` في سمالي؛ يضيف المترجم معامل `Continuation` مخفياً مع آلة حالة للإيقاف والاستئناف!",
                        fa_af = "در سمالی کلمه `suspend` وجود ندارد؛ کامپایلر یک پارامتر پنهان `Continuation` افزوده و با ماشین حالت اجرا را مدیریت می‌کند!",
                        in_id = "Di Smali tidak ada kata `suspend`; kompiler menambahkan parameter `Continuation` tersembunyi beserta mesin status `packed-switch`!"
                    ),
                    kotlinCode = """suspend fun loadLessons(): List<String> =
    kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        listOf("Smali", "Java", "Kotlin")
    }""",
                    smaliCode = """.method public final loadLessons(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"""
                )
            )
        ),
        Lesson(
            id = "kt_06",
            track = TrackType.KOTLIN,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "06. StateFlow & Seamless Java / Smali Interop (@JvmStatic)",
                bn = "০৬. StateFlow এবং জাভা/স্মালি ইন্টারঅপারেবিলিটি (@JvmStatic)",
                ur = "06. StateFlow اور جاوا/سمالی انٹرآپریبلٹی (@JvmStatic)",
                hi = "06. StateFlow और जावा/स्माली इंटरऑपरेबिलिटी (@JvmStatic)",
                ar = "06. تدفق الحالة StateFlow والتوافق مع جافا وسمالي (@JvmStatic)",
                fa_af = "۰۶. جریان StateFlow و تعامل بی‌نقص با جاوا و سمالی (@JvmStatic)",
                in_id = "06. StateFlow & Interoperabilitas Java / Smali (@JvmStatic)"
            ),
            subtitle = localized(
                en = "Control exact bytecode signatures generated by Kotlin companion objects for clean Java calls.",
                bn = "কোটলিন কোড যেন জাভা ও স্মালিতে একদম পরিষ্কার সিগনেচার তৈরি করে তার জন্য `@JvmStatic` ও `@JvmField` শিখুন।",
                ur = "کوٹلن کوڈ سے صاف جاوا اور سمالی سگنیچر بنانے کے لیے `@JvmStatic` اور `StateFlow` سیکھیں۔",
                hi = "साफ़ जावा और स्माली सिग्नेचर जनरेट करने के लिए `@JvmStatic` और `StateFlow` सीखें।",
                ar = "تحكم في تواقيع البايت كود المولدة من كوتلن عبر `@JvmStatic` وتدفقات `StateFlow`.",
                fa_af = "کنترل دقیق امضای بایت‌کد خروجی کاتلین برای جاوا و سمالی با `@JvmStatic` و `StateFlow`.",
                in_id = "Kendalikan tanda tangan bytecode Kotlin agar bersih saat dipanggil dari Java & Smali."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Companion INSTANCE vs Real @JvmStatic Methods",
                        bn = "Companion অবজেক্ট বনাম আসল @JvmStatic বাইটকোড মেথড",
                        ur = "Companion آبجیکٹ بمقابلہ حقیقی @JvmStatic میتھڈ",
                        hi = "Companion ऑब्जेक्ट बनाम वास्तविक @JvmStatic बाइटकोड मेथड",
                        ar = "كائن Companion مقابل الدوال الثابتة الحقيقية بـ @JvmStatic",
                        fa_af = "تفاوت شیء Companion با متدهای استاتیک واقعی @JvmStatic در سمالی",
                        in_id = "Objek Companion vs Metode Statis Asli @JvmStatic di Bytecode"
                    ),
                    content = localized(
                        en = "Without `@JvmStatic`, a Kotlin `companion object` function compiles inside `Class\$Companion`! Adding `@JvmStatic` emits a direct top-level `public static` method callable cleanly from Java (`Config.create()`) and Smali (`invoke-static`).",
                        bn = "`@JvmStatic` না দিলে কোটলিনের `companion object` ফাংশন ভেতরে `Class\$Companion` নামে আলাদা ক্লাসে চলে যায়! কিন্তু `@JvmStatic` দিলে সরাসরি মূল ক্লাসেই পরিষ্কার `public static` মেথড তৈরি হয়।",
                        ur = "`@JvmStatic` کے بغیر فنکشن `Class\$Companion` میں بنتا ہے، جبکہ `@JvmStatic` لگانے سے براہِ راست اصل کلاس میں `public static` میتھڈ بنتا ہے!",
                        hi = "`@JvmStatic` के बिना फ़ंक्शन `Class\$Companion` में जाता है; `@JvmStatic` लगाने पर मुख्य क्लास में सीधा `public static` मेथड बनता है!",
                        ar = "بدون `@JvmStatic` توضع الدالة داخل فئة `Companion` فرعية؛ أما إضافتها فتولد دالة `public static` مباشرة!",
                        fa_af = "بدون `@JvmStatic` متد داخل کلاس فرعی `Companion` قرار می‌گیرد، اما با `@JvmStatic` مستقیماً متد `public static` در کلاس اصلی ساخته می‌شود!",
                        in_id = "Tanpa `@JvmStatic` fungsi ditaruh di `Class\$Companion`; menambahkan `@JvmStatic` menghasilkan metode `public static` langsung di kelas utama!"
                    ),
                    kotlinCode = """class SdkConfig {
    companion object {
        @JvmStatic
        fun defaultTimeoutMs(): Long = 5000L
    }
}"""
                )
            )
        )
    )

    val extraPythonLessons: List<Lesson> = listOf(
        Lesson(
            id = "py_03",
            track = TrackType.PYTHON,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "03. Parsing the Binary DEX Header & String Count with struct",
                bn = "০৩. পাইথনের struct মডিউল দিয়ে বাইনারি DEX হেডার ও স্ট্রিং সংখ্যা পড়া",
                ur = "03. پائتھن struct ماڈیول سے بائنری DEX ہیڈر پڑھنا",
                hi = "03. पायथन struct से बाइनरी DEX हेडर और स्ट्रिंग संख्या पढ़ना",
                ar = "03. قراءة ترويسة DEX الثنائية وعدد النصوص عبر مكتبة struct",
                fa_af = "۰۳. خواندن هدر باینری DEX و تعداد رشته‌ها با ماژول struct پایتون",
                in_id = "03. Membaca Header Biner DEX & Jumlah String dengan struct"
            ),
            subtitle = localized(
                en = "Unpack little-endian 32-bit integers straight from classes.dex at byte offset 0x38.",
                bn = "`classes.dex` ফাইলের `0x38` বাইট অফসেট থেকে সরাসরি মোট স্ট্রিং ও ক্লাসের সংখ্যা বের করুন।",
                ur = "`classes.dex` کے بائٹ آفسیٹ `0x38` سے براہِ راست کل اسٹرنگز اور کلاسز کی تعداد نکالیں۔",
                hi = "`classes.dex` के ऑफ़सेट `0x38` से सीधे कुल स्ट्रिंग और क्लास की गिनती निकालें।",
                ar = "فك تشفير الأعداد الصحيحة مباشرة من ملف classes.dex عند الإزاحة 0x38.",
                fa_af = "استخراج مستقیم تعداد کل رشته‌ها و کلاس‌ها از آفست `0x38` فایل classes.dex.",
                in_id = "Ekstrak jumlah string dan kelas langsung dari offset `0x38` pada `classes.dex`."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Unpacking DEX Table Counts Without External Tools",
                        bn = "কোনো থার্ড-পার্টি টুল ছাড়াই পাইথনে DEX টেবিল বিশ্লেষণ",
                        ur = "بغیر کسی بیرونی ٹول کے پائتھن سے DEX ٹیبل کا تجزیہ",
                        hi = "बिना बाहरी टूल के पायथन से DEX तालिका गणना पढ़ना",
                        ar = "استخراج إحصاءات جداول DEX برمجياً بدون أدوات خارجية",
                        fa_af = "واکاوی جدول‌های فایل DEX صرفاً با کتابخانه استاندارد پایتون",
                        in_id = "Mengekstrak Statistik Tabel DEX Murni dengan Pustaka Standar Python"
                    ),
                    content = localized(
                        en = "In Android's DEX specification, offset `0x38` (56) stores `string_ids_size`, `0x40` stores `type_ids_size`, `0x58` stores `method_ids_size` (capped at 65,536 per DEX!), and `0x60` stores `class_defs_size`.",
                        bn = "অ্যান্ড্রয়েডের DEX ফরম্যাট অনুযায়ী ফাইলের `0x38` (৫৬তম বাইট) পজিশনে মোট স্ট্রিং সংখ্যা এবং `0x58` পজিশনে মোট মেথড সংখ্যা (সর্বোচ্চ ৬৫,৫৩৬ সীমা!) লেখা থাকে। পাইথনের `struct.unpack_from('<I', ...)` দিয়ে ১ সেকেন্ডেই তা পড়া যায়!",
                        ur = "DEX فارمیٹ میں آفسیٹ `0x38` پر کل اسٹرنگز اور `0x58` پر کل میتھڈز کی تعداد لکھی ہوتی ہے جسے `struct.unpack_from` فوراً پڑھ لیتا ہے!",
                        hi = "DEX प्रारूप में ऑफ़सेट `0x38` पर स्ट्रिंग संख्या और `0x58` पर मेथड संख्या (65,536 सीमा) होती है जिसे `struct` तुरंत पढ़ लेता है!",
                        ar = "في مواصفات DEX تقع سعة النصوص عند `0x38` وعدد الدوال عند `0x58` (حد 65536)، وتقرأها `struct.unpack_from` فوراً!",
                        fa_af = "در استاندارد DEX آفست `0x38` تعداد رشته‌ها و `0x58` تعداد متدها را نگه می‌دارد که با `struct` بلافاصله خوانده می‌شود!",
                        in_id = "Dalam spesifikasi DEX, offset `0x38` menyimpan jumlah string dan `0x58` menyimpan jumlah metode (batas 65.536 per DEX)!"
                    ),
                    pythonCode = """import struct

def inspect_dex_counts(dex_bytes: bytes) -> dict:
    strings_count, = struct.unpack_from("<I", dex_bytes, 0x38)
    methods_count, = struct.unpack_from("<I", dex_bytes, 0x58)
    classes_count, = struct.unpack_from("<I", dex_bytes, 0x60)
    return {"strings": strings_count, "methods": methods_count, "classes": classes_count}"""
                )
            )
        ),
        Lesson(
            id = "py_04",
            track = TrackType.PYTHON,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "04. Auditing Decoded AndroidManifest.xml with xml.etree",
                bn = "০৪. পাইথন দিয়ে AndroidManifest.xml পারমিশন ও সিকিউরিটি চেক",
                ur = "04. پائتھن سے AndroidManifest.xml پرمیشنز اور سیکیورٹی آڈٹ",
                hi = "04. पायथन से AndroidManifest.xml अनुमतियां और सुरक्षा जांच",
                ar = "04. تدقيق أذونات ومكونات AndroidManifest.xml بلغة بايثون",
                fa_af = "۰۴. بررسی خودکار مجوزها و امنیت AndroidManifest.xml با پایتون",
                in_id = "04. Mengaudit Izin & Komponen AndroidManifest.xml dengan Python"
            ),
            subtitle = localized(
                en = "Automatically list requested permissions, debuggable flags, and exported Activities.",
                bn = "অ্যাপে কী কী পারমিশন নেওয়া হয়েছে এবং ভুল করে `debuggable=true` রয়ে গেছে কি না তা স্বয়ংক্রিয়ভাবে ধরুন।",
                ur = "ایپ کی تمام پرمیشنز اور `debuggable=true` جیسی غلطیوں کو خودکار اسکرپٹ سے پکڑیں۔",
                hi = "ऐप की सभी अनुमतियों और `debuggable=true` जैसी सुरक्षा चूकों को स्वतः पकड़ें।",
                ar = "استخرج قائمة الأذونات واكشف إعدادات التصحيح المكشوفة تلقائياً قبل النشر.",
                fa_af = "استخراج خودکار لیست مجوزها و شناسایی پرچم خطرناک `debuggable=true` پیش از انتشار.",
                in_id = "Daftar otomatis semua izin aplikasi dan deteksi flag `debuggable=true` sebelum rilis."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Parsing Android Namespace Attributes Cleanly",
                        bn = "XML নেমস্পেস থেকে পারমিশন ও এক্সপোর্টেড অ্যাক্টিভিটি বের করা",
                        ur = "XML نیم اسپیس سے پرمیشنز اور ایکسپورٹڈ ایکٹیویٹیز نکالنا",
                        hi = "XML नेमस्पेस से अनुमतियां और एक्सपोर्टेड एक्टिविटी निकालना",
                        ar = "استخراج الأذونات والواجهات المصدرة عبر نطاق أسماء أندرويد",
                        fa_af = "خواندن مجوزها و کامپوننت‌های اکسپورت‌شده از فضای نام XML اندروید",
                        in_id = "Membaca Atribut Namespace Android secara Rapi"
                    ),
                    content = localized(
                        en = "Once APKTool decodes `AndroidManifest.xml`, Python's built-in `xml.etree.ElementTree` can verify in CI/CD pipelines that release builds never ship with `android:debuggable=\"true\"` or `usesCleartextTraffic=\"true\"`.",
                        bn = "APKTool দিয়ে ডিকোড করা `AndroidManifest.xml` ফাইলটি পাইথনের `ElementTree` দিয়ে চেক করলে রিলিজ দেওয়ার আগেই নিশ্চিত হওয়া যায় যেন কোথাও ভুল করে `debuggable=\"true\"` না থাকে।",
                        ur = "پائتھن کے `ElementTree` سے ریلیز سے پہلے خودکار تصدیق ہو جاتی ہے کہ کہیں `debuggable=\"true\"` تو نہیں رہ گیا۔",
                        hi = "पायथन के `ElementTree` से रिलीज़ से पहले जांच सकते हैं कि कहीं `debuggable=\"true\"` तो नहीं छूट गया।",
                        ar = "تتيح مكتبة `ElementTree` التحقق التلقائي قبل النشر من خلو التطبيق من `debuggable=\"true\"`.",
                        fa_af = "با کتابخانه داخلی `ElementTree` می‌توان در پایپ‌لاین انتشار مطمئن شد که `debuggable=\"true\"` فعال نمانده باشد.",
                        in_id = "Dengan `ElementTree` bawaan Python, pipeline rilis dapat memastikan `debuggable=\"true\"` tidak ikut terkirim."
                    ),
                    pythonCode = """import xml.etree.ElementTree as ET

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"

def list_permissions(manifest_path: str) -> list[str]:
    root = ET.parse(manifest_path).getroot()
    return [el.attrib.get(ANDROID_NS + "name", "") for el in root.findall("uses-permission")]"""
                )
            )
        ),
        Lesson(
            id = "py_05",
            track = TrackType.PYTHON,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "05. Building an Automated JSON Smali Audit Report CLI",
                bn = "০৫. সম্পূর্ণ স্বয়ংক্রিয় JSON স্মালি অডিট রিপোর্ট টুল তৈরি",
                ur = "05. خودکار JSON سمالی آڈٹ رپورٹ ٹول بنانا",
                hi = "05. स्वचालित JSON स्माली सुरक्षा ऑडिट रिपोर्ट टूल बनाना",
                ar = "05. بناء أداة سطر أوامر لتوليد تقارير تدقيق سمالي بصيغة JSON",
                fa_af = "۰۵. ساخت ابزار خط فرمان تولید گزارش JSON تحلیل سمالی",
                in_id = "05. Membangun Alat CLI Laporan Audit Smali Berformat JSON"
            ),
            subtitle = localized(
                en = "Combine regex opcode matching, dataclasses, and json export into a professional CLI tool.",
                bn = "রেজেক্স, `dataclass` এবং `json` মডিউল মিলিয়ে একটি পূর্ণাঙ্গ প্রফেশনাল সিকিউরিটি রিপোর্ট জেনারেটর তৈরি করুন।",
                ur = "ریجیکس، `dataclass` اور `json` ملا کر ایک مکمل پروفیشنل آڈٹ ٹول تیار کریں۔",
                hi = "रेजेक्स, `dataclass` और `json` मिलाकर एक प्रोफेशनल सुरक्षा रिपोर्ट टूल तैयार करें।",
                ar = "اجمع التعابير النمطية مع `json` لبناء أداة فحص احترافية متكاملة.",
                fa_af = "ترکیب عبارت‌های باقاعده و `json` برای ساخت ابزار حرفه‌ای گزارش‌گیری امنیتی.",
                in_id = "Gabungkan pencocokan regex dan ekspor `json` menjadi alat audit CLI profesional."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Structured JSON Findings for CI Security Gates",
                        bn = "স্ট্রাকচার্ড JSON রিপোর্ট তৈরি ও এক্সপোর্ট করা",
                        ur = "منظم JSON آڈٹ رپورٹ تیار اور ایکسپورٹ کرنا",
                        hi = "संरचित JSON ऑडिट रिपोर्ट तैयार और एक्सपोर्ट करना",
                        ar = "تصدير نتائج الفحص في ملف JSON منظم لخطوط الإنتاج",
                        fa_af = "تولید خروجی استاندارد JSON از یافته‌های تحلیل کد سمالی",
                        in_id = "Mengekspor Temuan Audit Smali ke Format JSON Terstruktur"
                    ),
                    content = localized(
                        en = "Professional AppSec teams output machine-readable `.json` reports listing exact file names, line numbers, and matched Smali signatures so CI builds can block regressions automatically.",
                        bn = "প্রফেশনাল অ্যাপ সিকিউরিটি ইঞ্জিনিয়াররা স্ক্যানের ফলাফল `.json` ফাইলে সংরক্ষণ করেন—যাতে ফাইলের নাম, লাইন নম্বর ও পাওয়া সিগনেচার একসাথে সাজানো থাকে।",
                        ur = "پروفیشنل سیکیورٹی ٹیمیں فائل نام اور لائن نمبر سمیت `.json` رپورٹ بناتی ہیں تاکہ خودکار جانچ آسان ہو۔",
                        hi = "प्रोफेशनल सुरक्षा टीमें फ़ाइल नाम और लाइन नंबर सहित `.json` रिपोर्ट जनरेट करती हैं।",
                        ar = "تصدر فرق الأمان تقارير `.json` دقيقة بأرقام الأسطر والتواقيع لأتمتة الحماية.",
                        fa_af = "تیم‌های حرفه‌ای امنیت خروجی اسکن را همراه با نام فایل و شماره خط در قالب `.json` ذخیره می‌کنند.",
                        in_id = "Tim keamanan profesional menghasilkan laporan `.json` berisi nama file dan nomor baris secara presisi."
                    ),
                    pythonCode = """import json, re
from pathlib import Path

INVOKE_RE = re.compile(r"invoke-\w+\s+\{[^}]*\},\s+(\S+)")

def export_invoke_summary(smali_dir: str, out_json: str):
    hits = []
    for f in Path(smali_dir).rglob("*.smali"):
        for ln, text in enumerate(f.read_text(errors="ignore").splitlines(), 1):
            m = INVOKE_RE.search(text)
            if m and "WebView" in m.group(1):
                hits.append({"file": f.name, "line": ln, "target": m.group(1)})
    Path(out_json).write_text(json.dumps(hits, indent=2))"""
                )
            )
        )
    )
}
