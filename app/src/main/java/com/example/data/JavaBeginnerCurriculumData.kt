package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object JavaBeginnerCurriculumData {

    val lessons: List<Lesson> = listOf(
        // MODULE 01
        Lesson(
            id = "java_01",
            track = TrackType.JAVA,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "01. How Java Works (Source → javac → D8 → DEX)",
                bn = "০১. জাভা কীভাবে কাজ করে (কোড থেকে APK ও DEX)",
                ur = "01. جاوا کیسے کام کرتا ہے (کوڈ سے APK اور DEX تک)",
                hi = "01. जावा कैसे काम करता है (सोर्स से D8 व DEX तक)",
                ar = "01. كيف تعمل لغة جافا (من المصدر إلى ملف DEX)",
                fa_af = "۰۱. جاوا چگونه کار می‌کند (از کد منبع تا فایل DEX)",
                in_id = "01. Cara Kerja Java (Source → javac → D8 → DEX)"
            ),
            subtitle = localized(
                en = "Understand Java compilation, JVM vs Android ART, class structure, and entry points from zero.",
                bn = "একদম শূন্য থেকে বুঝুন জাভা কোড কীভাবে কম্পাইল হয়ে অ্যান্ড্রয়েড ফোনের ভেতরে চলে।",
                ur = "بالکل صفر سے سمجھیں کہ جاوا کوڈ کس طرح کمپائل ہو کر اینڈرائیڈ فون میں چلتا ہے۔",
                hi = "शून्य से समझें कि जावा कोड कैसे संकलित होकर एंड्रॉइड फ़ोन के भीतर चलता है।",
                ar = "افهم من الصفر كيف تُترجم شيفرة جافا لتعمل داخل محرك أندرويد ART.",
                fa_af = "از صفر درک کنید کد جاوا چگونه کامپایل شده و در موتور ART اندروید اجرا می‌شود.",
                in_id = "Pahami dari nol bagaimana kode Java dikompilasi dan dijalankan oleh Android ART."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Anatomy of Your First Java Class & Compilation Pipeline",
                        bn = "জাভা ক্লাসের গঠন এবং কম্পাইলেশন পাইপলাইন",
                        ur = "پہلی جاوا کلاس کی ساخت اور کمپائلیشن کا سفر",
                        hi = "पहली जावा क्लास की संरचना और कंपाइलेशन पाइपलाइन",
                        ar = "تشريح أول فئة جافا ومسار الترجمة البرمجية",
                        fa_af = "کالبدشکافی اولین کلاس جاوا و مسیر کامپایل",
                        in_id = "Anatomi Kelas Java Pertama & Alur Kompilasi"
                    ),
                    childAnalogy = localized(
                        en = "Writing `.java` is like writing an architect's blueprint in human words; `javac` + `D8` translate that blueprint into compact machine instructions (`classes.dex`) that every Android phone understands!",
                        bn = "`.java` ফাইল হলো মানুষের ভাষায় লেখা বাড়ির নকশা; আর `javac` ও `D8` কম্পাইলার হলো দোভাষী—যারা সেই নকশাকে `classes.dex` ফাইলে রূপান্তর করে যাতে যেকোনো অ্যান্ড্রয়েড ফোন তা বুঝতে পারে!",
                        ur = "`.java` فائل انسان کی زبان میں لکھا نقشہ ہے اور `javac` و `D8` اسے `classes.dex` میں بدلتے ہیں جسے ہر اینڈرائیڈ فون سمجھتا ہے!",
                        hi = "`.java` फ़ाइल इंसानी भाषा में लिखा नक्शा है, और `javac` व `D8` उसे `classes.dex` में बदलते हैं जिसे हर फ़ोन समझता है!",
                        ar = "ملف `.java` يشبه مخططاً هندسياً بلغة البشر، بينما يحوله المترجم `D8` إلى ملف `classes.dex` يفهمه كل هاتف أندرويد!",
                        fa_af = "فایل `.java` مانند نقشه ساختمانی به زبان انسان است و کامپایلر `D8` آن را به `classes.dex` تبدیل می‌کند تا هر گوشی اندرویدی بفهمد!",
                        in_id = "File `.java` ibarat cetak biru manusia, sedangkan `javac` + `D8` menerjemahkannya menjadi `classes.dex` yang dipahami ponsel Android!"
                    ),
                    content = localized(
                        en = "Every piece of Java code lives inside a `class`. On desktop Java, execution starts at `public static void main(String[] args)`. On Android, the OS launches Activity lifecycle methods like `onCreate(Bundle)` after `javac` compiles `.java → .class` and `D8` packs `.class → classes.dex`.",
                        bn = "জাভায় সব কোড একটি `class`-এর ভেতরে থাকে। কম্পিউটারে প্রোগ্রাম শুরু হয় `public static void main(String[] args)` দিয়ে, আর অ্যান্ড্রয়েডে শুরু হয় `onCreate()` মেথড দিয়ে। প্রথমে `javac` জাভা কোডকে `.class` বানায়, এরপর অ্যান্ড্রয়েডের `D8` কম্পাইলার সেটিকে রেজিস্টার-ভিত্তিক `classes.dex` (স্মালি) ফাইলে রূপান্তর করে।",
                        ur = "جاوا کا ہر کوڈ کسی `class` کے اندر لکھا جاتا ہے۔ کمپیوٹر پر پروگرام `main` سے شروع ہوتا ہے جبکہ اینڈرائیڈ میں `D8` کمپائلر `.class` کو `classes.dex` میں بدل دیتا ہے۔",
                        hi = "जावा का सारा कोड `class` के भीतर रहता है। डेस्कटॉप पर निष्पादन `main` से शुरू होता है, जबकि एंड्रॉइड में `D8` कंपाइलर `.class` को `classes.dex` में बदलता है।",
                        ar = "تُكتب كل شيفرة جافا داخل `class`. يحول `javac` الملف إلى `.class` ثم يدمجه `D8` في `classes.dex` ليعمل على مسجلات دالفيك.",
                        fa_af = "تمام کدهای جاوا درون `class` قرار می‌گیرند. ابتدا `javac` فایل `.class` می‌سازد و سپس `D8` آن را به بایت‌کد رجستری `classes.dex` تبدیل می‌کند.",
                        in_id = "Semua kode Java berada di dalam `class`. Kompiler `javac` menghasilkan `.class`, lalu `D8` mengubahnya menjadi `classes.dex`."
                    ),
                    javaCode = """package com.example.demo;

public class HelloEngine {
    public static String bootMessage() {
        return "Java Engine Ready";
    }
}""",
                    smaliCode = """.class public Lcom/example/demo/HelloEngine;
.super Ljava/lang/Object;

.method public static bootMessage()Ljava/lang/String;
    .locals 1
    const-string v0, "Java Engine Ready"
    return-object v0
.end method""",
                    practicalExampleNote = localized(
                        en = "Notice how `package com.example.demo;` + `class HelloEngine` directly becomes `.class public Lcom/example/demo/HelloEngine;` in Smali!",
                        bn = "লক্ষ্য করুন জাভার `package com.example.demo;` এবং `class HelloEngine` সরাসরি স্মালিতে `.class public Lcom/example/demo/HelloEngine;` হয়ে গেছে!",
                        ur = "غور کریں کہ جاوا کا پیکج اور کلاس نام براہِ راست سمالی میں `Lcom/example/demo/HelloEngine;` بن گیا ہے!",
                        hi = "ध्यान दें कि जावा का पैकेज और क्लास नाम सीधे स्माली में `Lcom/example/demo/HelloEngine;` बन गया है!",
                        ar = "لاحظ كيف تحولت الحزمة واسم الفئة مباشرة إلى التوقيع الكامل `Lcom/example/demo/HelloEngine;` في سمالي!",
                        fa_af = "دقت کنید چگونه نام پکیج و کلاس جاوا مستقیماً در سمالی به `Lcom/example/demo/HelloEngine;` تبدیل شده است!",
                        in_id = "Perhatikan bagaimana paket dan nama kelas Java langsung menjadi `.class public Lcom/example/demo/HelloEngine;` di Smali!"
                    )
                )
            )
        ),

        // MODULE 02
        Lesson(
            id = "java_02",
            track = TrackType.JAVA,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "02. Variables, All 8 Primitive Types & Strings",
                bn = "০২. ভেরিয়েবল, ৮টি প্রিমিটিভ ডেটা টাইপ ও String",
                ur = "02. متغیرات، تمام 8 بنیادی ٹائپس اور String",
                hi = "02. वेरिएबल, सभी 8 प्रिमिटिव डेटा टाइप और String",
                ar = "02. المتغيرات والأنواع الأساسية الثمانية والنصوص",
                fa_af = "۰۲. متغیرها، ۸ نوع داده پایه و رشته String",
                in_id = "02. Variabel, 8 Tipe Primitif & String"
            ),
            subtitle = localized(
                en = "Master byte, short, int, long, float, double, char, boolean, type casting, and Dalvik width.",
                bn = "byte, short, int, long, float, double, char, boolean এবং স্মালি রেজিস্টারে এদের মেমোরি হিসাব।",
                ur = "جاوا کی تمام 8 بنیادی ٹائپس، ٹائپ کاسٹنگ اور رجسٹر میموری کا مکمل حساب۔",
                hi = "सभी 8 प्रिमिटिव टाइप, टाइप कास्टिंग और डाल्विक रजिस्टर चौड़ाई की पूरी समझ।",
                ar = "أتقن الأنواع الثمانية والتحويل بينها وعرضها في مسجلات دالفيك 32-بت و 64-بت.",
                fa_af = "تسلط کامل بر ۸ نوع داده پایه، تبدیل نوع و اشغال رجسترهای ۳۲ و ۶۴ بیتی.",
                in_id = "Kuasai ke-8 tipe primitif, konversi tipe, dan lebar register Dalvik."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "32-Bit vs 64-Bit Primitives & Reference Strings",
                        bn = "৩২-বিট বনাম ৬৪-বিট ডেটা টাইপ এবং রেফারেন্স String",
                        ur = "32-بٹ بمقابلہ 64-بٹ ٹائپس اور ریفرنس String",
                        hi = "32-बिट बनाम 64-बिट प्रिमिटिव और रेफ़रेंस String",
                        ar = "الأنواع ذات 32-بت مقابل 64-بت وكائنات النصوص",
                        fa_af = "تفاوت انواع ۳۲ بیتی و ۶۴ بیتی و رشته‌های مرجع",
                        in_id = "Primitif 32-Bit vs 64-Bit & Objek String"
                    ),
                    childAnalogy = localized(
                        en = "`int` and `boolean` fit inside one standard locker (`v0`), whereas `long` and `double` are giant suitcases requiring TWO adjacent lockers (`v0, v1`)!",
                        bn = "`int` বা `boolean` একটি সাধারণ লকারে (`v0`) এঁটে যায়, কিন্তু `long` এবং `double` হলো বড় স্যুটকেস—যাদের রাখার জন্য পরপর দুটি লকার (`v0, v1`) লাগে!",
                        ur = "`int` اور `boolean` ایک لاکر (`v0`) میں سما جاتے ہیں، لیکن `long` اور `double` کو دو مسلسل لاکرز (`v0, v1`) درکار ہوتے ہیں!",
                        hi = "`int` और `boolean` एक लॉकर (`v0`) में आ जाते हैं, जबकि `long` और `double` के लिए दो लगातार लॉकर (`v0, v1`) चाहिए!",
                        ar = "النوع `int` يتسع في خزانة واحدة (`v0`)، أما `long` و `double` فيحتاجان خزانتين متجاورتين (`v0, v1`)!",
                        fa_af = "نوع `int` در یک کمد (`v0`) جا می‌شود، اما `long` و `double` به دو کمد پشت‌سرهم (`v0, v1`) نیاز دارند!",
                        in_id = "Tipe `int` muat di satu loker (`v0`), sedangkan `long` dan `double` membutuhkan dua loker berurutan (`v0, v1`)!"
                    ),
                    content = localized(
                        en = "Java has exactly 8 primitives:\n• Integers: `byte` (8-bit `B`), `short` (16-bit `S`), `int` (32-bit `I`), `long` (64-bit `J` — ends with `L`).\n• Decimals: `float` (32-bit `F` — ends with `f`), `double` (64-bit `D`).\n• Character & Logic: `char` (16-bit Unicode `C`), `boolean` (`true`/`false` `Z`).\n`String` is NOT a primitive—it is an immutable `Ljava/lang/String;` object on the Heap!",
                        bn = "জাভায় মোট ৮টি মৌলিক (Primitive) ডেটা টাইপ আছে:\n• পূর্ণসংখ্যা: `byte` (`B`), `short` (`S`), `int` (`I` - ৩২ বিট), `long` (`J` - ৬৪ বিট, শেষে `L` বসে)।\n• দশমিক সংখ্যা: `float` (`F` - শেষে `f` বসে), `double` (`D` - ৬৪ বিট)।\n• অক্ষর ও সত্য/মিথ্যা: `char` (`C`), `boolean` (`Z` - স্মালিতে `0x1` মানে true, `0x0` মানে false)।\nমনে রাখবেন: `String` প্রিমিটিভ নয়, এটি Heap মেমোরিতে থাকা একটি অপরিবর্তনীয় অবজেক্ট (`Ljava/lang/String;`)!",
                        ur = "جاوا میں کل 8 بنیادی ٹائپس ہیں: `byte`، `short`، `int`، `long`، `float`، `double`، `char` اور `boolean`۔ یاد رکھیں کہ `String` بنیادی ٹائپ نہیں بلکہ Heap میں موجود آبجیکٹ ہے!",
                        hi = "जावा में कुल 8 प्रिमिटिव टाइप हैं: `byte`, `short`, `int`, `long`, `float`, `double`, `char` और `boolean`। `String` प्रिमिटिव नहीं बल्कि एक अपरिवर्तनीय ऑब्जेक्ट है!",
                        ar = "تمتلك جافا 8 أنواع أساسية فقط (`byte, short, int, long, float, double, char, boolean`)، بينما `String` هو كائن غير قابل للتعديل في الذاكرة.",
                        fa_af = "جاوا دقیقاً ۸ نوع داده پایه دارد (`byte, short, int, long, float, double, char, boolean`)، در حالی که `String` یک شیء تغییرناپذیر در حافظه Heap است.",
                        in_id = "Java memiliki 8 tipe primitif (`byte, short, int, long, float, double, char, boolean`), sedangkan `String` adalah objek immutable di Heap."
                    ),
                    javaCode = """int coins = 250;
long timestamp = 1735689600000L; // 64-bit
boolean unlocked = true;
String badge = "VIP";""",
                    smaliCode = """const/16 v0, 0xfa
const-wide v1, 0x1941f29a800L   # uses v1 & v2!
const/4 v3, 0x1
const-string v4, "VIP""""
                )
            )
        ),

        // MODULE 03
        Lesson(
            id = "java_03",
            track = TrackType.JAVA,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "03. Operators, == vs .equals() & StringBuilder",
                bn = "০৩. অপারেটরসমূহ, == বনাম .equals() এবং String জোড়া দেওয়া",
                ur = "03. آپریٹرز، == بمقابلہ .equals() اور String جوڑنا",
                hi = "03. ऑपरेटर, == बनाम .equals() और String जोड़ना",
                ar = "03. المعاملات والفرق بين == و .equals() ودمج النصوص",
                fa_af = "۰۳. عملگرها، تفاوت == با .equals() و چسباندن رشته‌ها",
                in_id = "03. Operator, == vs .equals() & Penggabungan String"
            ),
            subtitle = localized(
                en = "Avoid Java's #1 bug when comparing strings and master arithmetic & bitwise operators.",
                bn = "জাভার সবচেয়ে বড় ভুল (== দিয়ে String তুলনা) এড়িয়ে চলুন এবং গাণিতিক ও বিটওয়াইজ অপারেটর শিখুন।",
                ur = "جاوا کی سب سے بڑی غلطی (== سے String موازنہ) سے بچیں اور حسابی و بٹ وائز آپریٹرز سیکھیں۔",
                hi = "जावा की सबसे बड़ी गलती (== से String तुलना) से बचें और अंकगणितीय व बिटवाइज़ ऑपरेटर सीखें।",
                ar = "تجنب أشهر خطأ في مقارنة النصوص بـ == وأتقن المعاملات الحسابية والمنطقية والبتية.",
                fa_af = "از رایج‌ترین باگ مقایسه رشته‌ها با == پرهیز کنید و عملگرهای محاسباتی و بیتی را بیاموزید.",
                in_id = "Hindari bug terbesar Java saat membandingkan String dan kuasai operator matematika & bitwise."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Why Strings Must Use .equals() Instead of ==",
                        bn = "কেন String তুলনা করতে == এর বদলে .equals() ব্যবহার করতেই হবে",
                        ur = "اسٹرنگ موازنے کے لیے == کی بجائے .equals() کیوں ضروری ہے",
                        hi = "String की तुलना में == के बजाय .equals() क्यों अनिवार्य है",
                        ar = "لماذا يجب مقارنة النصوص بدالة .equals() بدلاً من ==",
                        fa_af = "چرا برای مقایسه رشته‌ها باید از .equals() به‌جای == استفاده کرد",
                        in_id = "Mengapa Membandingkan String Wajib Memakai .equals()"
                    ),
                    childAnalogy = localized(
                        en = "`a == b` asks 'Are these the exact same physical piece of paper?', whereas `a.equals(b)` asks 'Are the words written on both papers identical?'",
                        bn = "`a == b` প্রশ্ন করে 'দুটো কি একই কাগজের টুকরো?', কিন্তু `a.equals(b)` প্রশ্ন করে 'দুই কাগজের ভেতরের লেখা কি হুবহু এক?'!",
                        ur = "`a == b` پوچھتا ہے 'کیا یہ ایک ہی کاغذ ہے؟' جبکہ `a.equals(b)` پوچھتا ہے 'کیا دونوں پر لکھے الفاظ یکساں ہیں؟'!",
                        hi = "`a == b` पूछता है 'क्या ये एक ही कागज़ हैं?', जबकि `a.equals(b)` पूछता है 'क्या दोनों पर लिखे अक्षर समान हैं?'!",
                        ar = "`a == b` تسأل: هل هما نفس الورقة المادية؟ بينما `a.equals(b)` تسأل: هل النص المكتوب عليهما متطابق؟",
                        fa_af = "`a == b` می‌پرسد «آیا هر دو دقیقاً یک برگه کاغذ هستند؟»، اما `a.equals(b)` می‌پرسد «آیا متن نوشته شده روی هر دو یکسان است؟»",
                        in_id = "`a == b` memeriksa apakah alamat kertasnya sama, sedangkan `a.equals(b)` memeriksa apakah isi tulisannya sama!"
                    ),
                    content = localized(
                        en = "For primitives (`int`, `boolean`), `==` compares actual numbers (`if-eq` in Smali). For objects like `String`, `==` compares memory addresses! Always call `\"ADMIN\".equals(role)` (putting the constant first also prevents `NullPointerException` if `role` is null!).",
                        bn = "প্রিমিটিভ সংখ্যার ক্ষেত্রে (`int`) `==` আসল মান তুলনা করে। কিন্তু `String` বা অবজেক্টের ক্ষেত্রে `==` শুধু মেমোরি ঠিকানা মিলায়! তাই সবসময় `\"ADMIN\".equals(role)` লিখবেন—এতে `role` যদি `null`-ও হয় তবু অ্যাপ ক্র্যাশ করবে না!",
                        ur = "اعداد کے لیے `==` قیمت چیک کرتا ہے مگر `String` کے لیے میموری ایڈریس دیکھتا ہے! ہمیشہ `\"ADMIN\".equals(role)` استعمال کریں۔",
                        hi = "प्रिमिटिव के लिए `==` मान जांचता है, पर `String` के लिए मेमोरी पता जांचता है! हमेशा `\"ADMIN\".equals(role)` लिखें।",
                        ar = "في الأنواع الأساسية تقارن `==` القيم، أما في الكائنات فتقارن عناوين الذاكرة؛ لذا استخدم دائماً `\"ADMIN\".equals(role)`.",
                        fa_af = "برای اعداد `==` مقدار را مقایسه می‌کند اما برای `String` آدرس حافظه را می‌سنجد؛ همیشه `\"ADMIN\".equals(role)` بنویسید.",
                        in_id = "Untuk primitif `==` membandingkan nilai, tetapi untuk objek membandingkan alamat RAM. Selalu gunakan `\"ADMIN\".equals(role)`."
                    ),
                    javaCode = """public static boolean isAdmin(String role) {
    return "ADMIN".equals(role);
}""",
                    smaliCode = """const-string v0, "ADMIN"
invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z
move-result v0
return v0"""
                )
            )
        ),

        // MODULE 04
        Lesson(
            id = "java_04",
            track = TrackType.JAVA,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "04. Control Flow (if-else, Ternary ? : & switch)",
                bn = "০৪. কন্ট্রোল ফ্লো (if-else, টার্নারি ? : এবং switch)",
                ur = "04. کنٹرول فلو (if-else، ٹرنری ? : اور switch)",
                hi = "04. कंट्रोल फ़्लो (if-else, टर्नरी ? : और switch)",
                ar = "04. التحكم في المسار (if-else والمعامل الثلاثي و switch)",
                fa_af = "۰۴. کنترل جریان برنامه (if-else، عملگر سه‌تایی و switch)",
                in_id = "04. Alur Kontrol (if-else, Ternary ? : & switch)"
            ),
            subtitle = localized(
                en = "Write clean conditional logic and see why compilers invert Java if conditions in bytecode.",
                bn = "শর্তসাপেক্ষ লজিক লেখার নিয়ম এবং কেন কম্পাইলার জাভার if শর্তকে স্মালিতে উল্টে দেয়।",
                ur = "شرطیہ کوڈ لکھنے کا طریقہ اور کمپائلر سمالی میں شرط کو الٹ کیوں دیتا ہے۔",
                hi = "शर्तीय लॉजिक लिखने का तरीका और कंपाइलर बाइटकोड में if शर्त को उल्टा क्यों करता है।",
                ar = "كتابة الشروط الاحترافية ولماذا يعكس المترجم شروط جافا عند تحويلها إلى سمالي.",
                fa_af = "نوشتن شرط‌های تمیز و علت وارونه شدن شرط‌های جاوا در بایت‌کد سمالی.",
                in_id = "Tulis logika percabangan rapi dan pahami mengapa kompiler membalik kondisi if di Smali."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Branching Logic & Switch Dispatch Tables",
                        bn = "শর্তের শাখা এবং switch জাম্প টেবিল",
                        ur = "شاخوں کا انتخاب اور switch جمپ ٹیبل",
                        hi = "शाखा निर्णय और switch जंप टेबल",
                        ar = "التفرع الشرطي وجداول القفز السريعة switch",
                        fa_af = "تصمیم‌گیری شرطی و جدول پرش سریع switch",
                        in_id = "Percabangan & Tabel Lompatan switch"
                    ),
                    content = localized(
                        en = "Use `if / else if / else` for range checks (`score >= 80`), the ternary operator `cond ? a : b` for concise inline expressions, and `switch` when matching discrete constants (which compiles to an ultra-fast `O(1)` `packed-switch` jump table in DEX!).",
                        bn = "সীমা চেক করার জন্য (`score >= 80`) `if / else`, ছোট এক লাইনের শর্তের জন্য টার্নারি `cond ? a : b`, এবং নির্দিষ্ট মান মেলানোর জন্য `switch` ব্যবহার করুন—যা স্মালিতে অতি দ্রুত `packed-switch` জাম্প টেবিলে কম্পাইল হয়!",
                        ur = "رینج چیک کے لیے `if/else`، مختصر شرط کے لیے `? :` اور مخصوص نمبروں کے لیے `switch` استعمال کریں جو سمالی میں تیز ترین `packed-switch` بنتا ہے۔",
                        hi = "रेंज जांच के लिए `if/else`, संक्षिप्त मान के लिए `? :`, और निश्चित मानों के लिए `switch` चुनें जो `packed-switch` बनता है।",
                        ar = "استخدم `if/else` للنطاقات، والمعامل الثلاثي `? :` للسطر الواحد، و `switch` للقيم الثابتة التي تتحول إلى `packed-switch` سريع.",
                        fa_af = "برای بازه‌ها از `if/else`، برای عبارت تک‌خطی از `? :` و برای مقادیر ثابت از `switch` استفاده کنید که به `packed-switch` سریع تبدیل می‌شود.",
                        in_id = "Gunakan `if/else` untuk rentang, ternary `? :` untuk ekspresi singkat, dan `switch` untuk konstanta cepat `packed-switch`."
                    ),
                    javaCode = """public static String gradeBadge(int score) {
    if (score >= 80) {
        return "A+";
    }
    return (score >= 50) ? "PASS" : "RETRY";
}""",
                    smaliCode = """const/16 v0, 0x50
if-ge p0, v0, :check_pass   # Inverted: if score < 80 jump!
const-string v0, "A+"
return-object v0

:check_pass
const/16 v0, 0x32
if-lt p0, v0, :retry
const-string v0, "PASS"
return-object v0

:retry
const-string v0, "RETRY"
return-object v0"""
                )
            )
        ),

        // MODULE 05
        Lesson(
            id = "java_05",
            track = TrackType.JAVA,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "05. Loops & Fixed Arrays (for, while, for-each, int[])",
                bn = "০৫. লুপ ও অ্যারে (for, while, for-each এবং int[])",
                ur = "05. لوپس اور ایریز (for، while، for-each اور int[])",
                hi = "05. लूप और ऐरे (for, while, for-each व int[])",
                ar = "05. الحلقات التكرارية والمصفوفات الثابتة في جافا",
                fa_af = "۰۵. حلقه‌های تکرار و آرایه‌ها (for، while و int[])",
                in_id = "05. Perulangan & Array (for, while, for-each, int[])"
            ),
            subtitle = localized(
                en = "Iterate cleanly with index bounds safety, break/continue, and contiguous array memory.",
                bn = "নির্ভুলভাবে লুপ চালানো, break/continue এবং অ্যারের ইনডেক্স বাউন্ড ও মেমোরি ব্যবস্থাপনা।",
                ur = "محفوظ طریقے سے لوپ چلانا، break/continue اور ایرے میموری کو سمجھنا۔",
                hi = "सुरक्षित रूप से लूप चलाना, break/continue और ऐरे मेमोरी प्रबंधन।",
                ar = "التكرار الآمن عبر العناصر وفهم حدود المصفوفات وتعليمات break و continue.",
                fa_af = "پیمایش ایمن آرایه‌ها، مدیریت اندیس‌ها و کاربرد دقیق break و continue.",
                in_id = "Iterasi aman dengan batas indeks, break/continue, dan memori array."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Zero-Indexed Arrays & Loop Lowering",
                        bn = "০-ইনডেক্স অ্যারে এবং লুপ কীভাবে স্মালিতে চলে",
                        ur = "زیرو انڈیکس ایریز اور سمالی میں لوپ کی شکل",
                        hi = "शून्य-आधारित ऐरे और बाइटकोड में लूप रूपांतरण",
                        ar = "المصفوفات المرقمة من الصفر وتحول الحلقات في البايت كود",
                        fa_af = "آرایه‌های با اندیس صفر و نحوه تبدیل حلقه‌ها در سمالی",
                        in_id = "Array Berindeks Nol & Konversi Loop ke Bytecode"
                    ),
                    content = localized(
                        en = "A Java array `int[] scores = new int[4]` has fixed length `scores.length` with valid indices `0..3`. Enhanced `for (int s : scores)` avoids off-by-one `ArrayIndexOutOfBoundsException` bugs automatically!",
                        bn = "জাভায় `int[] scores = new int[4]` তৈরি করলে এর সাইজ ৪টি ফিক্সড হয়ে যায় এবং ইনডেক্স হয় `0` থেকে `3` পর্যন্ত। ভুল ইনডেক্সের ক্র্যাশ এড়াতে যখন ইনডেক্স দরকার নেই তখন `for (int s : scores)` (for-each লুপ) ব্যবহার করা সবচেয়ে নিরাপদ!",
                        ur = "جاوا میں `new int[4]` کی لمبائی مقرر ہوتی ہے اور انڈیکس `0` سے `3` تک ہوتے ہیں۔ غلطی سے بچنے کے لیے `for (int s : scores)` بہترین ہے!",
                        hi = "जावा में `new int[4]` की लंबाई स्थिर होती है और इंडेक्स `0..3` होते हैं। ऑफ़-बाय-वन त्रुटि से बचने के लिए `for (int s : scores)` सर्वोत्तम है!",
                        ar = "مصفوفة `new int[4]` لها طول ثابت وفهارس من `0` إلى `3`. حلقة `for-each` تمنع تجاوز الحدود تلقائياً!",
                        fa_af = "آرایه `new int[4]` طول ثابتی دارد با اندیس‌های `0` تا `3`. حلقه `for-each` جلوی خطای خروج از مرز آرایه را می‌گیرد!",
                        in_id = "Array `new int[4]` memiliki indeks `0..3`. Loop `for (int s : scores)` mencegah error batas indeks secara otomatis!"
                    ),
                    javaCode = """public static int sumScores(int[] scores) {
    int total = 0;
    for (int s : scores) {
        total += s;
    }
    return total;
}""",
                    smaliCode = """const/4 v0, 0x0          # total = 0
array-length v1, p0      # scores.length
const/4 v2, 0x0          # idx = 0

:loop
if-ge v2, v1, :done
aget v3, p0, v2          # s = scores[idx]
add-int/2addr v0, v3     # total += s
add-int/lit8 v2, v2, 0x1
goto :loop

:done
return v0"""
                )
            )
        ),

        // MODULE 06
        Lesson(
            id = "java_06",
            track = TrackType.JAVA,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "06. Methods, Pass-by-Value & Method Overloading",
                bn = "০৬. মেথড তৈরি, প্যারামিটার, রিটার্ন ও ওভারলোডিং",
                ur = "06. میتھڈز، پیرامیٹرز، ریٹرن اور اوور لوڈنگ",
                hi = "06. मेथड, पास-बाय-वैल्यू और मेथड ओवरलोडिंग",
                ar = "06. الدوال وتمرير القيم والتحميل الزائد Overloading",
                fa_af = "۰۶. متدها، ارسال با مقدار و سربارگذاری (Overloading)",
                in_id = "06. Metode, Pass-by-Value & Method Overloading"
            ),
            subtitle = localized(
                en = "Design clean reusable methods and understand how overloading creates distinct DEX signatures.",
                bn = "পুনঃব্যবহারযোগ্য মেথড ডিজাইন এবং একই নামের মেথড কীভাবে আলাদা DEX সিগনেচার পায়।",
                ur = "میتھڈز ڈیزائن کرنا اور اوور لوڈنگ سے الگ DEX سگنیچرز بننے کا اصول۔",
                hi = "पुनः प्रयोज्य मेथड डिज़ाइन और ओवरलोडिंग से अलग DEX सिग्नेचर बनने का नियम।",
                ar = "تصميم الدوال القابلة لإعادة الاستخدام وكيف ينشئ التحميل الزائد تواقيع DEX مستقلة.",
                fa_af = "طراحی متدهای تمیز و نحوه ایجاد امضاهای مستقل در DEX هنگام سربارگذاری متد.",
                in_id = "Rancang metode modular dan lihat bagaimana overloading menghasilkan tanda tangan DEX unik."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Method Overloading & Java Pass-by-Value Rule",
                        bn = "মেথড ওভারলোডিং এবং জাভার Pass-by-Value নিয়ম",
                        ur = "میتھڈ اوور لوڈنگ اور جاوا کا Pass-by-Value اصول",
                        hi = "मेथड ओवरलोडिंग और जावा का Pass-by-Value नियम",
                        ar = "التحميل الزائد للدوال وقاعدة التمرير بالقيمة في جافا",
                        fa_af = "سربارگذاری متدها و قانون ارسال با مقدار در جاوا",
                        in_id = "Overloading Metode & Aturan Pass-by-Value Java"
                    ),
                    content = localized(
                        en = "Java lets two methods share the exact same name if their parameter types differ (`format(int)` vs `format(String)`). Because Smali includes full parameter descriptors in every call (`format(I)` vs `format(Ljava/lang/String;)`), there is zero ambiguity at runtime!",
                        bn = "জাভায় প্যারামিটারের টাইপ বা সংখ্যা আলাদা হলে একই নামে একাধিক মেথড রাখা যায়—একে **Method Overloading** বলে। স্মালিতে মেথডের নামের সাথে প্যারামিটারের টাইপ যুক্ত থাকে (`format(I)` বনাম `format(Ljava/lang/String;)`), তাই রানটাইমে কখনোই সংঘর্ষ হয় না!",
                        ur = "جاوا میں پیرامیٹرز مختلف ہوں تو ایک ہی نام کے کئی میتھڈز رکھے جا سکتے ہیں جسے Overloading کہتے ہیں۔ سمالی میں مکمل ٹائپ سگنیچر کی وجہ سے کوئی ابہام نہیں ہوتا!",
                        hi = "जावा में पैरामीटर प्रकार अलग होने पर एक ही नाम के कई मेथड हो सकते हैं (Overloading)। स्माली में पूर्ण सिग्नेचर होने से कोई भ्रम नहीं होता!",
                        ar = "تسمح جافا بتكرار اسم الدالة مع اختلاف المعاملات (Overloading)، وفي سمالي تتمايز كلياً بتواقيعها الكاملة.",
                        fa_af = "در جاوا می‌توان چند متد هم‌نام با پارامترهای متفاوت نوشت (Overloading) که در سمالی با امضای دقیقشان کاملاً متمایز می‌شوند.",
                        in_id = "Java mengizinkan nama metode sama dengan parameter berbeda (Overloading), yang di Smali dibedakan lewat deskriptor penuh."
                    ),
                    javaCode = """public class Formatter {
    public static String tag(int id) {
        return "ID#" + id;
    }
    public static String tag(String code) {
        return "CODE#" + code;
    }
}""",
                    smaliCode = """# Two completely distinct methods in DEX:
.method public static tag(I)Ljava/lang/String;
.method public static tag(Ljava/lang/String;)Ljava/lang/String;"""
                )
            )
        ),

        // MODULE 07
        Lesson(
            id = "java_07",
            track = TrackType.JAVA,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "07. Classes, Objects, this & Constructors (<init>)",
                bn = "০৭. ক্লাস, অবজেক্ট, this পয়েন্টার ও কনস্ট্রাক্টর (<init>)",
                ur = "07. کلاس، آبجیکٹ، this اور کنسٹرکٹر (<init>)",
                hi = "07. क्लास, ऑब्जेक्ट, this और कंस्ट्रक्टर (<init>)",
                ar = "07. الفئات والكائنات والمؤشر this والبانيات (<init>)",
                fa_af = "۰۷. کلاس‌ها، اشیاء، اشاره‌گر this و سازنده (<init>)",
                in_id = "07. Kelas, Objek, Pointer this & Konstruktor (<init>)"
            ),
            subtitle = localized(
                en = "See how new allocates Heap memory, runs <init>, and binds p0 to this.",
                bn = "দেখুন কীভাবে new কীওয়ার্ড Heap মেমোরিতে অবজেক্ট বানায়, <init> চালায় এবং p0-তে this রাখে।",
                ur = "دیکھیں کس طرح new ہیپ میں آبجیکٹ بناتا ہے، <init> چلاتا ہے اور p0 میں this رکھتا ہے۔",
                hi = "देखें कैसे new हीप में ऑब्जेक्ट बनाता है, <init> चलाता है और p0 को this से जोड़ता है।",
                ar = "شاهد كيف تحجز new الذاكرة وتشغل الباني <init> وتربط p0 بالكائن الحالي this.",
                fa_af = "ببینید کلیدواژه new چگونه در Heap حافظه می‌گیرد، `<init>` را اجرا کرده و `this` را در `p0` می‌گذارد.",
                in_id = "Lihat bagaimana new mengalokasikan Heap, menjalankan <init>, dan mengikat p0 ke this."
            ),
            estimatedMinutes = 12,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Two-Step Object Birth: Allocation + Initialization",
                        bn = "অবজেক্ট তৈরির দুটি ধাপ: মেমোরি বরাদ্দ ও কনস্ট্রাক্টর কল",
                        ur = "آبجیکٹ بننے کے دو مراحل: میموری مختص کرنا اور کنسٹرکٹر چلانا",
                        hi = "ऑब्जेक्ट निर्माण के दो चरण: मेमोरी आवंटन और कंस्ट्रक्टर कॉल",
                        ar = "ولادة الكائن على مرحلتين: حجز الذاكرة ثم تشغيل الباني",
                        fa_af = "دو مرحله تولد شیء: تخصیص حافظه و اجرای سازنده",
                        in_id = "Dua Langkah Kelahiran Objek: Alokasi & Konstruktor"
                    ),
                    childAnalogy = localized(
                        en = "A `class` is a cookie cutter; every `new Wallet(...)` stamps out a fresh cookie on the Heap with its own `balance` field!",
                        bn = "`class` হলো বিস্কুটের ছাঁচ; আর যতবার `new Wallet(...)` লিখবেন ততবার Heap মেমোরিতে আলাদা নতুন একটি ওয়ালেট অবজেক্ট তৈরি হবে যার নিজস্ব `balance` থাকবে!",
                        ur = "`class` بسکٹ کا سانچہ ہے اور ہر `new Wallet(...)` ہیپ میموری میں ایک نیا الگ آبجیکٹ تیار کرتا ہے!",
                        hi = "`class` बिस्कुट का साँचा है और हर `new Wallet(...)` हीप में अपना अलग `balance` रखने वाला नया ऑब्जेक्ट बनाता है!",
                        ar = "الفئة `class` هي القالب، وكل استدعاء لـ `new Wallet(...)` يصنع نسخة مستقلة في الذاكرة برصيدها الخاص!",
                        fa_af = "`class` قالب شیرینی‌پزی است و هر بار نوشتن `new Wallet(...)` یک شیء مستقل با موجودی مجزا در حافظه می‌سازد!",
                        in_id = "`class` adalah cetakan kue; setiap `new Wallet(...)` mencetak objek baru di Heap dengan `balance` masing-masing!"
                    ),
                    content = localized(
                        en = "Inside any non-static Java method or constructor, `this` points to the current object instance. When compiled to Android DEX, `this` is ALWAYS passed silently in parameter register `p0`, and field writes (`this.balance = amount`) become `iput` instructions!",
                        bn = "যেকোনো নন-স্ট্যাটিক জাভা মেথড বা কনস্ট্রাক্টরের ভেতর `this` মানে বর্তমান অবজেক্ট। অ্যান্ড্রয়েড স্মালিতে এই `this` সবসময় গোপনে প্রথম প্যারামিটার রেজিস্টার `p0`-তে থাকে, আর `this.balance = amount` কম্পাইল হয়ে `iput` অপকোডে পরিণত হয়!",
                        ur = "کسی بھی انسٹانس میتھڈ میں `this` موجودہ آبجیکٹ کو ظاہر کرتا ہے جو سمالی میں ہمیشہ `p0` رجسٹر ہوتا ہے اور فیلڈ میں لکھنا `iput` بن جاتا ہے!",
                        hi = "किसी भी इंस्टेंस मेथड में `this` वर्तमान ऑब्जेक्ट होता है जो स्माली में हमेशा `p0` रजिस्टर में रहता है और फ़ील्ड असाइनमेंट `iput` बनता है!",
                        ar = "داخل أي دالة غير ثابتة يشير `this` إلى الكائن الحالي ويُمرر دائماً في المسجل `p0`، بينما تتحول كتابة الحقل إلى `iput`.",
                        fa_af = "در هر متد غیر استاتیک، `this` به شیء جاری اشاره دارد و در سمالی همیشه در `p0` قرار می‌گیرد و مقداردهی فیلد به `iput` تبدیل می‌شود.",
                        in_id = "Dalam metode instance Java, `this` selalu dipetakan ke register `p0` di Smali, dan pengisian field menjadi `iput`."
                    ),
                    javaCode = """public class Wallet {
    private int balance;

    public Wallet(int initial) {
        this.balance = initial;
    }

    public int getBalance() {
        return this.balance;
    }
}""",
                    smaliCode = """.field private balance:I

.method public constructor <init>(I)V
    .locals 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    iput p1, p0, Lcom/example/Wallet;->balance:I
    return-void
.end method

.method public getBalance()I
    .locals 1
    iget v0, p0, Lcom/example/Wallet;->balance:I
    return v0
.end method"""
                )
            )
        ),

        // MODULE 08
        Lesson(
            id = "java_08",
            track = TrackType.JAVA,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "08. Encapsulation, Access Modifiers, static & final",
                bn = "০৮. এনক্যাপসুলেশন, public/private, static ও final",
                ur = "08. اینکیپسولیشن، public/private، static اور final",
                hi = "08. एनकैप्सुलेशन, public/private, static और final",
                ar = "08. التغليف ومحددات الوصول والكلمتان static و final",
                fa_af = "۰۸. کپسوله‌سازی، سطح دسترسی، static و final",
                in_id = "08. Enkapsulasi, Access Modifier, static & final"
            ),
            subtitle = localized(
                en = "Protect internal state with private fields, class-wide static members (<clinit>), and final constants.",
                bn = "private দিয়ে ডাটা সুরক্ষা, ক্লাস-লেভেল static মেম্বার (<clinit>) এবং অপরিবর্তনীয় final কনস্ট্যান্ট।",
                ur = "private کے ذریعے ڈیٹا کی حفاظت، static ممبرز (<clinit>) اور مستقل final کانسٹنٹس۔",
                hi = "private फ़ील्ड से डेटा सुरक्षा, static सदस्य (<clinit>) और अपरिवर्तनीय final स्थिरांक।",
                ar = "حماية البيانات بـ private وفهم الأعضاء الثابتة static (<clinit>) والثوابت final.",
                fa_af = "محافظت از داده‌ها با private، اعضای مشترک static (<clinit>) و ثابت‌های final.",
                in_id = "Lindungi data dengan private, anggota kelas static (<clinit>), dan konstanta final."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Instance vs static Fields & Compile-Time final Inlining",
                        bn = "Instance বনাম static ফিল্ড এবং final কনস্ট্যান্টের বিশেষ নিয়ম",
                        ur = "انسٹانس بمقابلہ static فیلڈز اور final ان لائننگ کا راز",
                        hi = "इंस्टेंस बनाम static फ़ील्ड और final इनलाइनिंग का रहस्य",
                        ar = "الحقول العادية مقابل الثابتة static ودمج الثوابت final وقت الترجمة",
                        fa_af = "تفاوت فیلدهای شیء با static و راز جایگذاری مستقیم مقادیر final",
                        in_id = "Field Instance vs static & Inlining Konstanta final"
                    ),
                    content = localized(
                        en = "• `private` hides fields inside the same class; `protected` allows subclasses; `public` opens access everywhere.\n• `static` belongs to the class itself (initialized once inside `static constructor <clinit>()V` using `sput` / `sget`).\n• Pro insight: `public static final int MAX = 100;` is inlined directly as `const/16 v0, 0x64` at every call site by `javac`!",
                        bn = "• `private` দিলে শুধু নিজের ক্লাসে দেখা যায়; `protected` সাব-ক্লাসে দেয়; আর `public` সব জায়গা থেকে অ্যাক্সেস দেয়।\n• `static` কোনো নির্দিষ্ট অবজেক্টের নয়, পুরো ক্লাসের একটিই কপি থাকে (স্মালিতে `<clinit>` মেথডে `sput` দিয়ে শুরু হয়)।\n• প্রো টিপ: `static final int MAX = 100;` লিখলে কম্পাইলার `sget` না করে সরাসরি ব্যবহারের জায়গায় `const/16 v0, 0x64` বসিয়ে দেয়!",
                        ur = "• `private` صرف اپنی کلاس تک محدود رکھتا ہے جبکہ `public` ہر جگہ رسائی دیتا ہے۔\n• `static` پوری کلاس کی مشترکہ کاپی ہوتی ہے جو سمالی میں `<clinit>` کے اندر سیٹ ہوتی ہے۔",
                        hi = "• `private` केवल उसी क्लास तक सीमित रखता है जबकि `public` सर्वत्र खुला रहता है।\n• `static` पूरी क्लास का साझा सदस्य होता है जो `<clinit>` में `sput` से सेट होता है।",
                        ar = "• يخفي `private` الحقل داخل الفئة، بينما ينتمي `static` للفئة ككل ويُهيأ مرة واحدة داخل `<clinit>`.",
                        fa_af = "• `private` فیلد را به همان کلاس محدود می‌کند و `static` متعلق به کل کلاس است که در `<clinit>` با `sput` مقداردهی می‌شود.",
                        in_id = "• `private` membatasi akses di dalam kelas, sedangkan `static` milik kelas secara global dan diinisialisasi di `<clinit>`."
                    ),
                    javaCode = """public class AppConfig {
    public static final int MAX_RETRIES = 3;
    private static String activeEnv = "PROD";
}""",
                    smaliCode = """.field public static final MAX_RETRIES:I = 0x3
.field private static activeEnv:Ljava/lang/String;

.method static constructor <clinit>()V
    .locals 1
    const-string v0, "PROD"
    sput-object v0, Lcom/example/AppConfig;->activeEnv:Ljava/lang/String;
    return-void
.end method"""
                )
            )
        )
    )
}
