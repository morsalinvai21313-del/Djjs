package com.example.data

import com.example.model.SmaliCheatItem
import com.example.model.SmaliDecodedResult
import com.example.model.localized

object SmaliReferenceData {

    val typeDescriptors: List<SmaliCheatItem> = listOf(
        SmaliCheatItem(
            symbol = "V",
            javaType = "void / Unit",
            title = localized("Void (No Return)", "ভয়েড (কিছু ফেরত দেয় না)", "خالی (Void)", "वॉइड (कुछ नहीं लौटाता)", "بلا إرجاع (void)", "بدون خروجی (void)", "Void (Tanpa Return)"),
            detail = localized(
                "Used only for method return types when nothing is returned (`return-void`).",
                "শুধুমাত্র মেথডের রিটার্ন টাইপ হিসেবে বসে যখন মেথড কোনো মান ফেরত দেয় না (`return-void`)।",
                "صرف میتھڈ کی واپسی قسم کے طور پر آتا ہے جب کوئی قیمت واپس نہ ہو۔",
                "केवल मेथड रिटर्न टाइप में प्रयुक्त जब कुछ भी नहीं लौटता (`return-void`)।",
                "تُستخدم فقط كنوع إرجاع للدوال التي لا تعيد قيمة.",
                "فقط برای نوع خروجی متدهایی که مقداری برنمی‌گردانند کاربرد دارد.",
                "Hanya dipakai sebagai tipe return metode yang tidak mengembalikan nilai."
            ),
            example = ".method public onDestroy()V"
        ),
        SmaliCheatItem(
            symbol = "Z",
            javaType = "boolean / Boolean",
            title = localized("Boolean (true / false)", "বুলিয়ান (সত্য / মিথ্যা)", "بولین (سچ / جھوٹ)", "बूलियन (true / false)", "منطقي (صح / خطأ)", "بولین (درست / نادرست)", "Boolean (true / false)"),
            detail = localized(
                "1 = true (`0x1`), 0 = false (`0x0`). Uses 'Z' because 'B' is reserved for byte.",
                "`0x1` মানে `true` এবং `0x0` মানে `false`। `B` অক্ষরটি `byte` নিয়ে নেওয়ায় বুলিয়ানে `Z` ব্যবহৃত হয়।",
                "`0x1` بمعنی `true` اور `0x0` بمعنی `false`۔",
                "`0x1` = true और `0x0` = false (`B` बाइट के लिए आरक्षित है)।",
                "`0x1` تعني صح و `0x0` تعني خطأ.",
                "`0x1` یعنی درست و `0x0` یعنی نادرست.",
                "`0x1` = true, `0x0` = false (`B` sudah dipakai byte)."
            ),
            example = ".method public isPremium()Z"
        ),
        SmaliCheatItem(
            symbol = "I",
            javaType = "int / Int",
            title = localized("32-Bit Signed Integer", "৩২-বিট পূর্ণসংখ্যা (int)", "32-بٹ عدد (int)", "32-बिट पूर्णांक (int)", "عدد صحيح 32-بت", "عدد صحیح ۳۲ بیتی (int)", "Integer 32-Bit"),
            detail = localized(
                "Fits in 1 register. Hex literals are common in Smali (`0xa` = 10, `0x64` = 100).",
                "১টি রেজিস্টারে বসে। স্মালিতে সংখ্যা সাধারণত হেক্সাডেসিমালে লেখা থাকে (`0xa` = ১০, `0x64` = ১০০)।",
                "ایک رجسٹر میں آتا ہے؛ ہیکس میں لکھا جاتا ہے (`0xa` = 10)۔",
                "1 रजिस्टर लेता है; हेक्स में लिखा जाता है (`0xa` = 10, `0x64` = 100)।",
                "يشغل مسجلاً واحداً ويُكتب غالباً بالنظام السداسي عشري.",
                "یک رجستر می‌گیرد و معمولاً به صورت هگز نوشته می‌شود (`0xa` = ۱۰).",
                " menempati 1 register; ditulis dalam hex (`0xa` = 10)."
            ),
            example = "const/16 v0, 0x64    # 100"
        ),
        SmaliCheatItem(
            symbol = "J",
            javaType = "long / Long",
            title = localized("64-Bit Long (2 Registers!)", "৬৪-বিট বড় সংখ্যা (২টি রেজিস্টার!)", "64-بٹ Long (دو رجسٹرز!)", "64-बिट Long (2 रजिस्टर!)", "عدد طويل 64-بت (مسجلان!)", "عدد ۶۴ بیتی Long (دو رجستر!)", "Long 64-Bit (2 Register!)"),
            detail = localized(
                "Uses 'J' because 'L' means Class Object. Occupies a register pair `(v0, v1)` and ends with `L`.",
                "`L` অক্ষরটি ক্লাসের জন্য থাকায় এখানে `J` বসে। এটি সবসময় পাশাপাশি ২টি রেজিস্টার (`v0, v1`) দখল করে!",
                "ہمیشہ دو لگاتار رجسٹرز (`v0, v1`) لیتا ہے اور عدد کے آخر میں `L` آتا ہے۔",
                "हमेशा दो लगातार रजिस्टर (`v0, v1`) लेता है और अंत में `L` लगता है।",
                "يحجز دائماً زوج مسجلات متتاليين (`v0, v1`) وينتهي بالحرف `L`.",
                "همیشه دو رجستر متوالی (`v0, v1`) اشغال می‌کند و با `L` ختم می‌شود.",
                "Selalu memakan sepasang register (`v0, v1`) dan diakhiri `L`."
            ),
            example = "const-wide v0, 0x3e8L"
        ),
        SmaliCheatItem(
            symbol = "F / D",
            javaType = "float / double",
            title = localized("Floating-Point Decimals", "দশমিক সংখ্যা (float ও double)", "اعشاریہ اعداد (float / double)", "दशमलव संख्याएँ (float / double)", "الأعداد العشرية (F و D)", "اعداد اعشاری (float و double)", "Bilangan Desimal (F / D)"),
            detail = localized(
                "`F` (float) uses 1 register; `D` (double) is 64-bit and uses 2 registers `(v0, v1)`.",
                "`F` (float) ১টি রেজিস্টার নেয়, আর `D` (double) ৬৪-বিট হওয়ায় ২টি রেজিস্টার নেয়।",
                "`F` ایک رجسٹر لیتا ہے جبکہ `D` دو رجسٹرز لیتا ہے۔",
                "`F` 1 रजिस्टर लेता है जबकि `D` 64-बिट होने से 2 रजिस्टर लेता है।",
                "`F` يشغل مسجلاً واحداً بينما `D` يشغل مسجلين متجاورين.",
                "`F` یک رجستر و `D` دو رجستر متوالی اشغال می‌کند.",
                "`F` memakai 1 register; `D` 64-bit memakai 2 register."
            ),
            example = "const/high16 v0, 0x3f800000    # 1.0f"
        ),
        SmaliCheatItem(
            symbol = "B / S / C",
            javaType = "byte / short / char",
            title = localized("Byte, Short & UTF-16 Char", "বাইট, শর্ট ও ক্যারেক্টার (B, S, C)", "بائٹ، شارٹ اور کریکٹر", "बाइट, शॉर्ट और कैरेक्टर", "بايت وقصير وحرف", "بایت، عدد کوتاه و کاراکتر", "Byte, Short & Karakter"),
            detail = localized(
                "`B` = 8-bit signed byte, `S` = 16-bit signed short, `C` = 16-bit Unicode character.",
                "`B` = ৮-বিট বাইট, `S` = ১৬-বিট ছোট পূর্ণসংখ্যা, `C` = ১৬-বিট ইউনিকোড অক্ষর।",
                "`B` = بائٹ، `S` = شارٹ، `C` = یونیکوڈ حرف۔",
                "`B` = बाइट, `S` = शॉर्ट, `C` = 16-बिट यूनिकोड अक्षर।",
                "`B` بايت، `S` عدد قصير، `C` حرف يونيكود.",
                "`B` بایت، `S` عدد کوتاه ۱۶ بیتی و `C` کاراکتر یونیکد.",
                "`B` = byte, `S` = short, `C` = karakter Unicode."
            ),
            example = ".field private tagChar:C"
        ),
        SmaliCheatItem(
            symbol = "Lpkg/Cls;",
            javaType = "Object / Class",
            title = localized("Class / Object Reference", "ক্লাস বা অবজেক্ট রেফারেন্স", "کلاس / آبجیکٹ ریفرنس", "क्लास / ऑब्जेक्ट रेफरेंस", "مرجع فئة أو كائن", "ارجاع به کلاس یا شیء", "Referensi Kelas / Objek"),
            detail = localized(
                "Starts with `L`, uses `/` between packages, and MUST end with a semicolon `;`!",
                "শুরুতে বড় হাতের `L`, প্যাকেজের মাঝে `/` স্ল্যাশ এবং শেষে সেমিকোলন `;` দেওয়া ১০০% বাধ্যতামূলক!",
                "`L` سے شروع ہوتا ہے، درمیان میں `/` اور آخر میں سیمی کولن `;` لازمی ہے!",
                "`L` से शुरू होता है, पैकेज में `/` और अंत में सेमीकोलन `;` अनिवार्य है!",
                "تبدأ بحرف `L` وتفصل بـ `/` ويجب أن تنتهي بفاصلة منقوطة `;`!",
                "با `L` شروع شده، با `/` جدا می‌شود و حتماً با `;` پایان می‌یابد!",
                "Diawali `L`, dipisah `/`, dan wajib diakhiri titik koma `;`!"
            ),
            example = "Ljava/lang/String;"
        ),
        SmaliCheatItem(
            symbol = "[Type",
            javaType = "Type[] (Array)",
            title = localized("1D / 2D Array Prefix", "অ্যারে চিহ্ন ([ ও [[)", "ایرے علامت ([)", "ऐरे उपसर्ग ([ और [[)", "بادئة المصفوفات ([)", "پیشوند آرایه ([ و [[)", "Awalan Array ([ & [[)"),
            detail = localized(
                "Each leading `[` adds one array dimension: `[I` = `int[]`, `[[B` = `byte[][]`, `[Ljava/lang/String;` = `String[]`.",
                "প্রতিটি `[` একটি মাত্রা যোগ করে: `[I` মানে `int[]`, আর `[Ljava/lang/String;` মানে `String[]`।",
                "ہر `[` ایک جہت بڑھاتا ہے: `[I` یعنی `int[]` اور `[Ljava/lang/String;` یعنی `String[]`۔",
                "प्रत्येक `[` एक आयाम जोड़ता है: `[I` = `int[]`, `[Ljava/lang/String;` = `String[]`।",
                "كل `[` تضيف بُعداً للمصفوفة مثل `[I` و `[Ljava/lang/String;`.",
                "هر علامت `[` یک بعد آرایه اضافه می‌کند؛ مثل `[I` و `[Ljava/lang/String;`.",
                "Setiap `[` menambah dimensi array: `[I` = `int[]`, `[Ljava/lang/String;` = `String[]`."
            ),
            example = "new-array v0, p0, [Ljava/lang/String;"
        )
    )

    val directives: List<SmaliCheatItem> = listOf(
        SmaliCheatItem(
            symbol = ".class / .super",
            javaType = "class X extends Y",
            title = localized("Class & Parent Header", "ক্লাসের নাম ও প্যারেন্ট হেডার", "کلاس اور پیرنٹ ہیڈر", "क्लास और पैरेंट हेडर", "ترويسة الفئة والأب", "تعریف کلاس و کلاس پدر", "Header Kelas & Induk"),
            detail = localized(
                "First two lines of every `.smali` file declaring its full descriptor and parent class.",
                "প্রতিটি `.smali` ফাইলের প্রথম দুটি লাইন—যেখানে নিজের নাম ও প্যারেন্ট ক্লাসের নাম থাকে।",
                "ہر `.smali` فائل کی پہلی دو لائنیں جو کلاس اور اس کے والد کو ظاہر کرتی ہیں۔",
                "हर `.smali` फ़ाइल की पहली दो पंक्तियाँ जो क्लास और उसके पैरेंट को घोषित करती हैं।",
                "أول سطرين في كل ملف `.smali` لتحديد اسم الفئة والفئة الأب.",
                "دو خط اول هر فایل `.smali` برای مشخص کردن نام کلاس و والد آن.",
                "Dua baris pertama setiap file `.smali` yang menyatakan kelas dan induknya."
            ),
            example = ".class public Lcom/app/Main;\n.super Landroid/app/Activity;"
        ),
        SmaliCheatItem(
            symbol = ".implements",
            javaType = "implements Interface",
            title = localized("Implemented Interface", "ইন্টারফেস সংযোগ (.implements)", "انٹرفیس کا نفاذ", "इंटरफ़ेस घोषणा", "تنفيذ واجهة برمجية", "پیاده‌سازی اینترفیس", "Implementasi Interface"),
            detail = localized(
                "Declares each Java/Kotlin interface implemented by this class (one `.implements` per line).",
                "ক্লাসটি যেসব Interface ব্যবহার করেছে তা লাইন বাই লাইন ঘোষণা করে।",
                "کلاس جن انٹرفیسز کو نافذ کرتی ہے ان کا اعلان۔",
                "क्लास द्वारा लागू किए गए प्रत्येक इंटरफ़ेस की घोषणा करता है।",
                "تعلن عن كل واجهة برمجية تطبقها هذه الفئة.",
                "اعلام اینترفیس‌هایی که این کلاس پیاده‌سازی کرده است.",
                "Menyatakan interface yang diimplementasikan oleh kelas ini."
            ),
            example = ".implements Landroid/view/View\$OnClickListener;"
        ),
        SmaliCheatItem(
            symbol = ".field",
            javaType = "private int count;",
            title = localized("Member / Static Variable", "ক্লাসের ভেরিয়েবল ঘোষণা (.field)", "کلاس کا متغیر (.field)", "क्लास वेरिएबल (.field)", "تعريف حقل في الفئة", "تعریف متغیر کلاس (.field)", "Deklarasi Variabel Kelas"),
            detail = localized(
                "Defines static or instance variables stored inside the class or object.",
                "ক্লাসের ভেতরে থাকা `static` অথবা সাধারণ ইনস্ট্যান্স ভেরিয়েবল তৈরি করে।",
                "کلاس کے اسٹیٹک یا انسٹانس متغیرات کی تعریف کرتا ہے۔",
                "क्लास के भीतर `static` या इंस्टेंस वेरिएबल परिभाषित करता है।",
                "تعرف المتغيرات الثابتة أو الخاصة بالكائن داخل الفئة.",
                "متغیرهای استاتیک یا معمولی داخل کلاس را تعریف می‌کند.",
                "Mendefinisikan variabel statis atau instance di dalam kelas."
            ),
            example = ".field private final token:Ljava/lang/String;"
        ),
        SmaliCheatItem(
            symbol = ".method .. .end method",
            javaType = "fun compute() { ... }",
            title = localized("Executable Method Block", "মেথডের শুরু ও শেষ সীমানা", "میتھڈ کا مکمل بلاک", "मेथड ब्लॉक की सीमा", "كتلة الدالة التنفيذية", "بلوک شروع و پایان متد", "Blok Metode Eksekusi"),
            detail = localized(
                "Wraps all registers, labels, and bytecode instructions belonging to one method.",
                "একটি মেথডের সমস্ত রেজিস্টার ও কোড `.method` দিয়ে শুরু হয়ে `.end method` দিয়ে শেষ হয়।",
                "ایک میتھڈ کا تمام کوڈ `.method` سے شروع ہو کر `.end method` پر ختم ہوتا ہے۔",
                "एक मेथड के सभी निर्देश `.method` से शुरू होकर `.end method` पर समाप्त होते हैं।",
                "تحيط بجميع مسجلات وتعليمات الدالة من البداية للنهاية.",
                "تمام کدهای یک متد بین `.method` و `.end method` قرار می‌گیرند.",
                "Membungkus seluruh register, label, dan instruksi milik satu metode."
            ),
            example = ".method public run()V\n    .locals 0\n    return-void\n.end method"
        ),
        SmaliCheatItem(
            symbol = ".locals",
            javaType = "Local register count",
            title = localized("Non-Parameter Registers", "লোকাল রেজিস্টার সংখ্যা (.locals)", "لوکل رجسٹرز کی تعداد", "लोकल रजिस्टर संख्या (.locals)", "عدد المسجلات المحلية", "تعداد رجسترهای محلی (.locals)", "Jumlah Register Lokal"),
            detail = localized(
                "Reserves `v0` through `v(N-1)` for scratch work, leaving `p0..pM` untouched!",
                "মেথডের নিজস্ব কাজের জন্য `v0` থেকে `v(N-1)` পর্যন্ত কয়টি রেজিস্টার লাগবে তা নির্ধারণ করে।",
                "میتھڈ کے اندرونی کام کے لیے `v0` سے `v(N-1)` تک رجسٹرز مختص کرتا ہے۔",
                "मेथड के आंतरिक कार्य हेतु `v0` से `v(N-1)` तक रजिस्टर आरक्षित करता है।",
                "تحجز المسجلات المحلية من `v0` حتى `v(N-1)` للعمليات الداخلية.",
                "تعداد خانه‌های محلی `v0` تا `v(N-1)` را برای محاسبات متد رزرو می‌کند.",
                "Menyediakan `v0` sampai `v(N-1)` untuk variabel kerja lokal."
            ),
            example = ".locals 3    # allocates v0, v1, v2"
        ),
        SmaliCheatItem(
            symbol = ".catch / .catchall",
            javaType = "try { .. } catch / finally",
            title = localized("Exception Guard Range", "এরর ধরার সুরক্ষা রেঞ্জ (.catch)", "ایرر پکڑنے کی حد (.catch)", "एक्सेप्शन सुरक्षा रेंज (.catch)", "نطاق معالجة الاستثناءات", "محدوده مدیریت خطا (.catch)", "Rentang Penangkap Eksepsi"),
            detail = localized(
                "Routes any exception thrown between `:try_start` and `:try_end` to `:catch_label`.",
                "`:try_start` ও `:try_end`-এর মাঝে কোনো এরর ঘটলে কোডকে সরাসরি `:catch` লেবেলে পাঠিয়ে দেয়।",
                "`:try_start` اور `:try_end` کے درمیان آنے والے ایرر کو `:catch` لیبل پر بھیجتا ہے۔",
                "`:try_start` और `:try_end` के बीच आई त्रुटि को `:catch` लेबल पर भेजता है।",
                "توجه أي خطأ يقع بين علامتي البداية والنهاية إلى علامة المعالجة.",
                "هر خطایی بین برچسب شروع و پایان رخ دهد را به بخش `:catch` هدایت می‌کند.",
                "Mengarahkan error di antara `:try_start` dan `:try_end` ke `:catch`."
            ),
            example = ".catch Ljava/io/IOException; {:try_0 .. :end_0} :catch_0"
        )
    )

    val accessFlags: List<SmaliCheatItem> = listOf(
        SmaliCheatItem(
            symbol = "public / private / protected",
            javaType = "Visibility Modifiers",
            title = localized("Access Visibility", "অ্যাক্সেস সীমা (public / private)", "رسائی کی حدود", "एक्सेस दृश्यता", "محددات مستوى الوصول", "سطح دسترسی اعضا", "Visibilitas Akses"),
            detail = localized(
                "Controls whether any class (`public`), only this class (`private`), or subclasses (`protected`) can access it.",
                "বাইরের ক্লাস (`public`), কেবল নিজের ক্লাস (`private`), নাকি সাবক্লাস (`protected`) ব্যবহার করতে পারবে তা ঠিক করে।",
                "طے کرتا ہے کہ کون سی کلاس اس میتھڈ یا فیلڈ کو استعمال کر سکتی ہے۔",
                "तय करता है कि कौन-सी क्लास इस मेथड या फ़ील्ड को एक्सेस कर सकती है।",
                "تحدد من يمكنه استدعاء الدالة أو قراءة الحقل.",
                "مشخص می‌کند چه کلاس‌هایی اجازه دسترسی به این متد یا فیلد را دارند.",
                "Mengatur kelas mana saja yang boleh mengakses anggota ini."
            ),
            example = ".method private validatePin(I)Z"
        ),
        SmaliCheatItem(
            symbol = "static",
            javaType = "static / @JvmStatic",
            title = localized("Class-Level (No 'this')", "ক্লাস-লেভেল মেম্বার (static)", "کلاس لیول (بغیر this)", "क्लास-स्तरीय (static, बिना this)", "ثابت على مستوى الفئة", "سطح کلاس (بدون this)", "Tingkat Kelas (Tanpa 'this')"),
            detail = localized(
                "Belongs to the class itself: `p0` is the FIRST argument (NOT `this`!), called with `invoke-static`.",
                "কোনো অবজেক্ট ছাড়াই চলে: এতে `p0` মানে `this` নয় বরং ১ম আর্গুমেন্ট, আর কল হয় `invoke-static` দিয়ে!",
                "اس میں `this` نہیں ہوتا، `p0` پہلا آرگومنٹ ہوتا ہے اور `invoke-static` سے چلتا ہے!",
                "इसमें `this` नहीं होता; `p0` पहला पैरामीटर होता है और `invoke-static` से कॉल होता है!",
                "لا يوجد مؤشر `this`؛ يكون `p0` هو المعامل الأول وتُستدعى بـ `invoke-static`!",
                "فاقد `this` است؛ `p0` اولین آرگومان ورودی بوده و با `invoke-static` اجرا می‌شود!",
                "Tanpa `this`: `p0` adalah argumen pertama dan dipanggil lewat `invoke-static`!"
            ),
            example = ".method public static sum(II)I"
        ),
        SmaliCheatItem(
            symbol = "final",
            javaType = "final / Kotlin default",
            title = localized("Immutable / Non-Overridable", "অপরিবর্তনীয় ফ্ল্যাগ (final)", "ناقابل تبدیل (final)", "अपरिवर्तनीय (final)", "نهائي غير قابل للتعديل", "غیرقابل تغییر یا بازنویسی", "Tetap / Tidak Bisa Override"),
            detail = localized(
                "On fields: value set once. On methods/classes (Kotlin's default!): cannot be subclassed or overridden.",
                "কোটলিনে সব ক্লাস ও মেথড ডিফল্টভাবে `final` থাকে—অর্থাৎ সাবক্লাস বা ওভাররাইড করা যায় না।",
                "کوٹلن میں تمام کلاسز اور میتھڈز بنیادی طور پر `final` ہوتے ہیں۔",
                "कोटलिन में सभी क्लास व मेथड डिफ़ॉल्ट रूप से `final` होते हैं।",
                "في كوتلن تكون الفئات والدوال `final` افتراضياً فلا يمكن تجاوزها.",
                "در کاتلین کلاس‌ها و متدها به طور پیش‌فرض `final` هستند.",
                "Di Kotlin semua kelas & metode secara bawaan bersifat `final`."
            ),
            example = ".class public final Lcom/example/Token;"
        ),
        SmaliCheatItem(
            symbol = "synthetic / bridge",
            javaType = "Compiler-Generated",
            title = localized("Compiler-Generated Glue", "কম্পাইলারের তৈরি গোপন মেথড", "کمپائلر کا خودکار کوڈ", "कंपाइलर-जनित सहायक कोड", "دوال يولدها المترجم تلقائياً", "متدهای تولیدی کامپایلر", "Kode Buatan Kompiler"),
            detail = localized(
                "Auto-created by Java/Kotlin/D8 for lambdas, inner-class accessors (`access\$000`), and generic type erasure.",
                "ল্যাম্বডা, ইনার ক্লাস (`access\$000`) ও জেনেরিক টাইপ মেলানোর জন্য কম্পাইলার নিজেই এগুলো তৈরি করে।",
                "لیمبڈا اور انر کلاسز کے لیے کمپائلر خودکار طور پر یہ میتھڈز بناتا ہے۔",
                "लैम्ब्डा और इनर क्लास एक्सेस के लिए कंपाइलर इन्हें स्वतः बनाता है।",
                "ينشئها المترجم تلقائياً لربط الدوال السهمية والفئات الداخلية.",
                "کامپایلر برای اتصال لامبداها و کلاس‌های داخلی به طور خودکار آن‌ها را می‌سازد.",
                "Dibuat otomatis oleh kompiler untuk lambda, inner class, dan generics."
            ),
            example = ".method public static synthetic lambda\$onClick\$0(Landroid/view/View;)V"
        ),
        SmaliCheatItem(
            symbol = "native",
            javaType = "external fun (JNI C/C++)",
            title = localized("JNI C/C++ Native Method", "নেটিভ C/C++ মেথড (native)", "نیٹو C/C++ میتھڈ", "नेटिव C/C++ मेथड (JNI)", "دالة أصلية بلغة C/C++", "متد بومی C/C++ (JNI)", "Metode Native C/C++ (JNI)"),
            detail = localized(
                "Has NO Smali body (`.locals` omitted)—implemented inside a compiled `.so` library in `lib/arm64-v8a/`!",
                "এর ভেতরে কোনো স্মালি কোড থাকে না—আসল কোড থাকে অ্যাপের `lib/arm64-v8a/*.so` ফাইলের ভেতরে C/C++ ভাষায়!",
                "اس کے اندر سمالی کوڈ نہیں ہوتا بلکہ یہ `.so` فائل کے C/C++ کوڈ سے چلتا ہے!",
                "इसमें स्माली बॉडी नहीं होती—इसका कोड `lib/arm64-v8a/*.so` C/C++ लाइब्रेरी में होता है!",
                "ليس لها جسم في سمالي، بل تُنفذ داخل مكتبة `.so` المترجمة بلغة C/C++!",
                "بدنه سمالی ندارد و کد اصلی آن به زبان C/C++ درون فایل `.so` قرار دارد!",
                "Tidak punya isi Smali; diimplementasikan di pustaka C/C++ `.so`!"
            ),
            example = ".method public native decryptPayload([B)[B\n.end method"
        )
    )

    val sampleSignatures: List<String> = listOf(
        "invoke-virtual {p0, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;",
        ".method public static verifyToken(Ljava/lang/String;IJ)Z",
        "iget-object v0, p0, Lcom/example/MainActivity;->username:Ljava/lang/String;",
        "invoke-direct {v0, p0, v1}, Landroid/widget/Toast;-><init>(Landroid/content/Context;I)V",
        "invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I",
        ".field public static final API_TIMEOUT_MS:J = 0x1388L"
    )

    fun formatSingleDescriptor(desc: String): String {
        var arrayDepth = 0
        var i = 0
        while (i < desc.length && desc[i] == '[') {
            arrayDepth++
            i++
        }
        val core = desc.substring(i)
        val baseName = when {
            core == "V" -> "void"
            core == "Z" -> "boolean"
            core == "B" -> "byte"
            core == "S" -> "short"
            core == "C" -> "char"
            core == "I" -> "int"
            core == "J" -> "long (64-bit)"
            core == "F" -> "float"
            core == "D" -> "double (64-bit)"
            core.startsWith("L") && core.endsWith(";") -> {
                core.substring(1, core.length - 1).replace('/', '.')
            }
            else -> core
        }
        return baseName + "[]".repeat(arrayDepth)
    }

    fun parseParamList(rawParams: String): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        while (i < rawParams.length) {
            val start = i
            while (i < rawParams.length && rawParams[i] == '[') {
                i++
            }
            if (i >= rawParams.length) break
            if (rawParams[i] == 'L') {
                val semi = rawParams.indexOf(';', i)
                i = if (semi != -1) semi + 1 else rawParams.length
            } else {
                i++
            }
            val token = rawParams.substring(start, i)
            result.add("${formatSingleDescriptor(token)} ($token)")
        }
        return result
    }

    fun decodeSmaliLine(rawInput: String): SmaliDecodedResult {
        val trimmed = rawInput.trim()
        if (trimmed.isEmpty()) {
            return SmaliDecodedResult(
                rawInput = "",
                kind = localized("Empty Input", "খালি ইনপুট", "خالی ان پٹ", "खाली इनपुट", "مدخل فارغ", "ورودی خالی", "Input Kosong"),
                flagsOrOpcode = "-",
                ownerClass = "-",
                memberName = "-",
                parameters = emptyList(),
                returnOrFieldType = "-",
                registersUsed = "-",
                plainSummary = localized(
                    "Type or select a Smali line above to inspect every part.",
                    "উপরে যেকোনো স্মালি লাইন লিখুন অথবা উদাহরণে ট্যাপ করুন।",
                    "اوپر کوئی سمالی لائن لکھیں یا مثال منتخب کریں۔",
                    "ऊपर कोई स्माली लाइन लिखें या उदाहरण चुनें।",
                    "اكتب أو اختر سطراً في الأعلى لتفكيكه.",
                    "یک خط سمالی در بالا وارد یا انتخاب کنید.",
                    "Ketik atau pilih baris Smali di atas."
                )
            )
        }

        // Case 1: invoke-* instruction
        if (trimmed.startsWith("invoke-")) {
            val opcode = trimmed.substringBefore(" ").trim()
            val regs = Regex("\\{([^}]*)\\}").find(trimmed)?.groupValues?.get(1)?.trim().orEmpty()
            val ref = trimmed.substringAfter("},", "").trim()
            val ownerRaw = ref.substringBefore("->", "-")
            val ownerPretty = formatSingleDescriptor(ownerRaw)
            val afterArrow = ref.substringAfter("->", "")
            val methodName = afterArrow.substringBefore("(", afterArrow)
            val paramsRaw = afterArrow.substringAfter("(", "").substringBefore(")", "")
            val retRaw = afterArrow.substringAfter(")", "V")
            val params = parseParamList(paramsRaw)
            val retPretty = "${formatSingleDescriptor(retRaw)} ($retRaw)"
            val isStatic = opcode.startsWith("invoke-static")

            return SmaliDecodedResult(
                rawInput = trimmed,
                kind = localized("Method Call Instruction", "মেথড কল নির্দেশনা (invoke)", "میتھڈ کال ہدایت", "मेथड कॉल निर्देश", "تعليمة استدعاء دالة", "دستور فراخوانی متد", "Instruksi Pemanggilan Metode"),
                flagsOrOpcode = opcode,
                ownerClass = ownerPretty,
                memberName = if (methodName == "<init>") "<init> (Constructor)" else methodName,
                parameters = params,
                returnOrFieldType = retPretty,
                registersUsed = if (regs.isEmpty()) "None {}" else "{$regs}" + (if (!isStatic && regs.isNotEmpty()) " (1st = target object)" else " (all args, no 'this')"),
                plainSummary = localized(
                    en = "Calls ${if (isStatic) "static" else "instance"} method `$methodName` on `$ownerPretty` with ${params.size} parameter(s) using `{$regs}`, returning `${formatSingleDescriptor(retRaw)}`.",
                    bn = "`$ownerPretty` ক্লাসের `${if (isStatic) "static" else "instance"}` মেথড `$methodName` কল করছে `{$regs}` রেজিস্টার দিয়ে (${params.size}টি ইনপুট প্যারামিটার) এবং ফলাফল হিসেবে `${formatSingleDescriptor(retRaw)}` ফেরত দেবে।",
                    ur = "`$ownerPretty` کلاس کا `$methodName` میتھڈ `{$regs}` رجسٹرز کے ساتھ کال کر رہا ہے اور `${formatSingleDescriptor(retRaw)}` واپس کرے گا۔",
                    hi = "`$ownerPretty` क्लास का `$methodName` मेथड `{$regs}` रजिस्टरों के साथ कॉल करता है और `${formatSingleDescriptor(retRaw)}` लौटाता है।",
                    ar = "يستدعي الدالة `$methodName` من الفئة `$ownerPretty` عبر المسجلات `{$regs}` وبعدد ${params.size} معاملات، ويرجع `${formatSingleDescriptor(retRaw)}`.",
                    fa_af = "متد `$methodName` از کلاس `$ownerPretty` را با رجسترهای `{$regs}` فراخوانی کرده و مقدار `${formatSingleDescriptor(retRaw)}` برمی‌گرداند.",
                    in_id = "Memanggil metode `$methodName` milik `$ownerPretty` memakai register `{$regs}` dan mengembalikan `${formatSingleDescriptor(retRaw)}`."
                )
            )
        }

        // Case 2: .method declaration
        if (trimmed.startsWith(".method")) {
            val withoutDirective = trimmed.removePrefix(".method").trim()
            val sigToken = withoutDirective.substringAfterLast(" ")
            val modifiers = withoutDirective.removeSuffix(sigToken).trim().ifEmpty { "(package-private)" }
            val name = sigToken.substringBefore("(")
            val paramsRaw = sigToken.substringAfter("(", "").substringBefore(")", "")
            val retRaw = sigToken.substringAfter(")", "V")
            val params = parseParamList(paramsRaw)
            val retPretty = "${formatSingleDescriptor(retRaw)} ($retRaw)"
            val isStatic = modifiers.split(" ").contains("static")

            return SmaliDecodedResult(
                rawInput = trimmed,
                kind = localized("Method Header Declaration", "মেথড হেডার ঘোষণা (.method)", "میتھڈ ہیڈر کا اعلان", "मेथड हेडर घोषणा", "ترويسة تعريف دالة", "تعریف هدر متد (.method)", "Deklarasi Header Metode"),
                flagsOrOpcode = modifiers,
                ownerClass = "(Current .class file)",
                memberName = if (name == "<init>") "<init> (Constructor)" else if (name == "<clinit>") "<clinit> (Static Block)" else name,
                parameters = params,
                returnOrFieldType = retPretty,
                registersUsed = if (isStatic) "p0.. (Static: p0 = 1st arg)" else "p0 = this, p1.. = args",
                plainSummary = localized(
                    en = "Declares `$modifiers` method `$name` taking ${params.size} parameter(s) and returning `${formatSingleDescriptor(retRaw)}`. ${if (isStatic) "Static: p0 holds Argument #1." else "Instance: p0 holds 'this' object."}",
                    bn = "এটি `$modifiers` ফ্ল্যাগযুক্ত `$name` মেথড—যাতে ${params.size}টি ইনপুট প্যারামিটার আছে এবং এটি `${formatSingleDescriptor(retRaw)}` রিটার্ন করে। ${if (isStatic) "যেহেতু এটি static, তাই p0-তে সরাসরি ১ম ইনপুট বসবে!" else "যেহেতু এটি সাধারণ মেথড, তাই p0-তে 'this' অবজেক্ট থাকবে!"}",
                    ur = "یہ `$modifiers` میتھڈ `$name` کا اعلان ہے جو ${params.size} ان پٹ لیتا ہے اور `${formatSingleDescriptor(retRaw)}` واپس کرتا ہے۔",
                    hi = "यह `$modifiers` मेथड `$name` घोषित करता है जो ${params.size} पैरामीटर लेता है और `${formatSingleDescriptor(retRaw)}` लौटाता है।",
                    ar = "يعرف الدالة `$name` بالمحددات `$modifiers` وتأخذ ${params.size} معاملات وترجع `${formatSingleDescriptor(retRaw)}`.",
                    fa_af = "متد `$name` با پرچم‌های `$modifiers` تعریف شده که ${params.size} ورودی می‌گیرد و `${formatSingleDescriptor(retRaw)}` برمی‌گرداند.",
                    in_id = "Mendeklarasikan metode `$name` (`$modifiers`) dengan ${params.size} parameter dan return `${formatSingleDescriptor(retRaw)}`."
                )
            )
        }

        // Case 3: Field instruction (iget*, iput*, sget*, sput*)
        if (trimmed.contains("->") && trimmed.contains(":")) {
            val opcode = trimmed.substringBefore(" ").trim()
            val middle = trimmed.substringAfter(" ").substringBeforeLast(",").trim()
            val ref = trimmed.substringAfterLast(",").trim()
            val ownerRaw = ref.substringBefore("->", "-")
            val ownerPretty = formatSingleDescriptor(ownerRaw)
            val fieldAndType = ref.substringAfter("->", "")
            val fieldName = fieldAndType.substringBefore(":")
            val typeRaw = fieldAndType.substringAfter(":")
            val typePretty = "${formatSingleDescriptor(typeRaw)} ($typeRaw)"
            val isRead = opcode.contains("get")

            return SmaliDecodedResult(
                rawInput = trimmed,
                kind = localized("Field Access Instruction", "ফিল্ড রিড/রাইট নির্দেশনা", "فیلڈ رسائی ہدایت", "फ़ील्ड एक्सेस निर्देश", "تعليمة حقل (قراءة/كتابة)", "دستور دسترسی به فیلد", "Instruksi Akses Field"),
                flagsOrOpcode = opcode,
                ownerClass = ownerPretty,
                memberName = fieldName,
                parameters = emptyList(),
                returnOrFieldType = typePretty,
                registersUsed = middle,
                plainSummary = localized(
                    en = "${if (isRead) "Reads" else "Writes"} field `$fieldName` of type `${formatSingleDescriptor(typeRaw)}` on `$ownerPretty` using register(s) `$middle`.",
                    bn = "`$ownerPretty` ক্লাসের `${formatSingleDescriptor(typeRaw)}` টাইপের `$fieldName` ফিল্ডে `$middle` রেজিস্টার ব্যবহার করে মান ${if (isRead) "পড়ে আনছে (Read)" else "জমা রাখছে (Write)"}।",
                    ur = "`$ownerPretty` کے فیلڈ `$fieldName` میں رجسٹر `$middle` کے ذریعے قیمت ${if (isRead) "پڑھ رہا ہے" else "لکھ رہا ہے"}۔",
                    hi = "`$ownerPretty` के `$fieldName` फ़ील्ड में रजिस्टर `$middle` द्वारा मान ${if (isRead) "पढ़ रहा है" else "लिख रहा है"}।",
                    ar = "يقوم بـ${if (isRead) "قراءة" else "كتابة"} الحقل `$fieldName` من الفئة `$ownerPretty` عبر المسجلات `$middle`.",
                    fa_af = "مقدار فیلد `$fieldName` از کلاس `$ownerPretty` را با استفاده از `$middle` ${if (isRead) "می‌خواند" else "می‌نویسد"}.",
                    in_id = "${if (isRead) "Membaca" else "Menulis"} field `$fieldName` (`${formatSingleDescriptor(typeRaw)}`) milik `$ownerPretty` memakai `$middle`."
                )
            )
        }

        // Case 4: .field directive or general fallback
        if (trimmed.startsWith(".field")) {
            val body = trimmed.removePrefix(".field").trim()
            val beforeEq = body.substringBefore("=").trim()
            val initVal = if (body.contains("=")) " = " + body.substringAfter("=").trim() else ""
            val nameType = beforeEq.substringAfterLast(" ")
            val flags = beforeEq.removeSuffix(nameType).trim().ifEmpty { "(default)" }
            val fName = nameType.substringBefore(":")
            val fType = nameType.substringAfter(":", "I")
            return SmaliDecodedResult(
                rawInput = trimmed,
                kind = localized("Field Declaration (.field)", "ফিল্ড ঘোষণা (.field)", "فیلڈ کا اعلان (.field)", "फ़ील्ड घोषणा (.field)", "تعريف حقل (.field)", "اعلان فیلد (.field)", "Deklarasi Field (.field)"),
                flagsOrOpcode = flags,
                ownerClass = "(Current .class file)",
                memberName = fName,
                parameters = emptyList(),
                returnOrFieldType = "${formatSingleDescriptor(fType)} ($fType)$initVal",
                registersUsed = "-",
                plainSummary = localized(
                    en = "Declares `$flags` field `$fName` of type `${formatSingleDescriptor(fType)}`$initVal.",
                    bn = "বর্তমান ক্লাসে `${formatSingleDescriptor(fType)}` টাইপের `$flags` ভেরিয়েবল `$fName`$initVal ঘোষণা করছে।",
                    ur = "موجودہ کلاس میں `${formatSingleDescriptor(fType)}` قسم کا `$flags` متغیر `$fName` بنا رہا ہے۔",
                    hi = "वर्तमान क्लास में `${formatSingleDescriptor(fType)}` प्रकार का `$flags` वेरिएबल `$fName` घोषित करता है।",
                    ar = "يعرف الحقل `$fName` بالمحددات `$flags` ومن النوع `${formatSingleDescriptor(fType)}`.",
                    fa_af = "فیلد `$fName` از نوع `${formatSingleDescriptor(fType)}` با سطح دسترسی `$flags` را تعریف می‌کند.",
                    in_id = "Mendeklarasikan field `$fName` (`$flags`) bertipe `${formatSingleDescriptor(fType)}`$initVal."
                )
            )
        }

        val firstToken = trimmed.substringBefore(" ")
        return SmaliDecodedResult(
            rawInput = trimmed,
            kind = localized("Smali Bytecode Statement", "স্মালি কোড স্টেটমেন্ট", "سمالی کوڈ لائن", "स्माली कथन", "سطر شيفرة سمالي", "دستور کد سمالی", "Baris Kode Smali"),
            flagsOrOpcode = firstToken,
            ownerClass = "-",
            memberName = "-",
            parameters = emptyList(),
            returnOrFieldType = "-",
            registersUsed = Regex("\\b[vp][0-9]+\\b").findAll(trimmed).joinToString(", ") { it.value }.ifEmpty { "-" },
            plainSummary = localized(
                en = "Executes `$firstToken` instruction on operands `${trimmed.substringAfter(" ", "")}`.",
                bn = "`$firstToken` নির্দেশনাটি `${trimmed.substringAfter(" ", "")}` অপারেন্ডের ওপর কাজ করছে।",
                ur = "`$firstToken` ہدایت `${trimmed.substringAfter(" ", "")}` پر عمل کر رہی ہے۔",
                hi = "`$firstToken` निर्देश `${trimmed.substringAfter(" ", "")}` पर कार्य कर रहा है।",
                ar = "تنفذ التعليمة `$firstToken` على المعاملات المحددة.",
                fa_af = "دستور `$firstToken` روی عملوندهای مشخص‌شده اجرا می‌شود.",
                in_id = "Menjalankan instruksi `$firstToken` pada operan `${trimmed.substringAfter(" ", "")}`."
            )
        )
    }
}
