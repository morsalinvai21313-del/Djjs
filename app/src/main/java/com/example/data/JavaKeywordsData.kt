package com.example.data

import com.example.localization.AppLanguage
import com.example.model.JavaKeywordCategory
import com.example.model.JavaKeywordItem
import com.example.model.localized

object JavaKeywordsData {

    private val coreKeywords: List<JavaKeywordItem> = listOf(
        // ==================== TYPES & VARIABLES (6) ====================
        JavaKeywordItem(
            keyword = "int",
            badge = "32-bit • I",
            category = JavaKeywordCategory.TYPES_VARS,
            syntax = "int count = 42;",
            smaliEquivalent = "const/16 v0, 0x2a  # Type descriptor: I",
            purposeContent = localized(
                en = "32-bit signed whole number (-2B to +2B) stored directly in a single stack/register slot.",
                bn = "৩২-বিট পূর্ণসংখ্যা (-২ বিলিয়ন থেকে +২ বিলিয়ন), যা সরাসরি একটি স্ট্যাক বা রেজিস্টার স্লটে থাকে।",
                ur = "32-بٹ مکمل عدد جو براہِ راست ایک اسٹیک یا رجسٹر سلاٹ میں محفوظ ہوتا ہے۔",
                hi = "32-बिट पूर्णांक संख्या जो सीधे एक स्टैक या रजिस्टर स्लॉट में संग्रहीत होती है।",
                ar = "عدد صحيح بحجم 32-بت يُخزن مباشرة داخل خانة مكدس أو مسجل واحد.",
                fa_af = "عدد صحیح ۳۲ بیتی که مستقیماً در یک خانه حافظه استک یا رجستر قرار می‌گیرد.",
                in_id = "Bilangan bulat 32-bit yang disimpan langsung di satu slot stack/register."
            ),
            detailedExplanationContent = localized(
                en = "`int` is Java's default integer type for counters, array indices, Android resource IDs (`0x7f...`), and pixel sizes. In Android DEX bytecode, `int` uses descriptor `I` and fits inside a single 32-bit register (`v0`).",
                bn = "জাভায় লুপ কাউন্টার, অ্যারে ইনডেক্স এবং অ্যান্ড্রয়েডের রিসোর্স আইডি (`0x7f...`) রাখার জন্য `int` সবচেয়ে বেশি ব্যবহৃত হয়। স্মালিতে এর টাইপ চিহ্ন `I` এবং এটি একটিমাত্র ৩২-বিট রেজিস্টারে (`v0`) বসে।",
                ur = "جاوا میں لوپ کاؤنٹر، ایرے انڈیکس اور اینڈرائیڈ ریسورس آئی ڈی کے لیے `int` سب سے عام ٹائپ ہے۔ سمالی میں اس کی علامت `I` ہے۔",
                hi = "जावा में लूप काउंटर, ऐरे इंडेक्स और एंड्रॉइड रिसोर्स आईडी के लिए `int` सबसे प्रचलित टाइप है। स्माली में इसका चिह्न `I` है।",
                ar = "يُعد `int` النوع الافتراضي للعدادات وفهارس المصفوفات ومعرفات موارد أندرويد، ويرمز له في سمالي بالحرف `I`.",
                fa_af = "`int` نوع پیش‌فرض اعداد صحیح برای شمارنده‌ها و شناسه‌های منابع اندروید است و در سمالی با حرف `I` شناخته می‌شود.",
                in_id = "`int` adalah tipe bilangan bulat utama untuk penghitung, indeks array, dan ID resource Android (`I` di Smali)."
            ),
            javaExample = """int coins = 150;
int bonus = 50;
int total = coins + bonus; // 200""",
            commonMistakeContent = localized(
                en = "Integer division truncates decimals (`5 / 2` gives `2`, not `2.5`)! Cast one side to `(double)` if you need fractions.",
                bn = "দুটি `int` ভাগ করলে দশমিক অংশ কেটে যায় (`5 / 2` করলে `2` আসে, `2.5` নয়)! দশমিক চাইলে `(double)` ব্যবহার করুন।",
                ur = "دو `int` کو تقسیم کرنے سے اعشاریہ ختم ہو جاتا ہے (`5 / 2` کا نتیجہ `2` آتا ہے)۔ اعشاریہ کے لیے `(double)` لگائیں۔",
                hi = "दो `int` का भाग दशमलव हटा देता है (`5 / 2` से `2` मिलता है)! दशमलव के लिए `(double)` कास्ट करें।",
                ar = "قسمة عددين `int` تحذف الكسور العشرية (`5 / 2` تعطي `2`)! حوّل أحدهما إلى `(double)` عند الحاجة للكسور.",
                fa_af = "تقسیم دو عدد `int` بخش اعشاری را حذف می‌کند (`5 / 2` برابر `2` می‌شود)؛ برای اعشار از `(double)` استفاده کنید.",
                in_id = "Pembagian sesama `int` membuang desimal (`5 / 2` menghasilkan `2`); gunakan `(double)` jika butuh pecahan."
            )
        ),

        JavaKeywordItem(
            keyword = "long",
            badge = "64-bit • J",
            category = JavaKeywordCategory.TYPES_VARS,
            syntax = "long timestamp = 1735689600000L;",
            smaliEquivalent = "const-wide v0, 0x1941e8ef800L  # Uses v0 + v1 (J)",
            purposeContent = localized(
                en = "64-bit wide integer for huge numbers like timestamps, file bytes, and database IDs.",
                bn = "সময় (Timestamp), ফাইলের সাইজ বা বিশাল সংখ্যার জন্য ৬৪-বিট পূর্ণসংখ্যা।",
                ur = "ٹائم اسٹیمپ، فائل سائز اور بڑے اعداد کے لیے 64-بٹ وسیع عدد۔",
                hi = "टाइमस्टैम्प, फ़ाइल आकार और विशाल संख्याओं के लिए 64-बिट पूर्णांक।",
                ar = "عدد صحيح عريض بحجم 64-بت للطوابع الزمنية وأحجام الملفات والأرقام الضخمة.",
                fa_af = "عدد صحیح ۶۴ بیتی برای مقادیر بسیار بزرگ مانند زمان سیستم و شناسه‌های دیتابیس.",
                in_id = "Bilangan bulat lebar 64-bit untuk angka besar seperti timestamp dan ukuran file."
            ),
            detailedExplanationContent = localized(
                en = "Append `L` at the end of `long` literals (`9000000000L`). In Dalvik/Smali, `long` has type descriptor `J` and occupies TWO consecutive registers (`v0` + `v1`) via `const-wide` and `return-wide`.",
                bn = "`long` সংখ্যার শেষে বড় হাতের `L` দিতে হয়। স্মালি বাইটকোডে এর টাইপ চিহ্ন `J` এবং এটি পাশাপাশি দুটি রেজিস্টার (`v0` ও `v1`) দখল করে!",
                ur = "`long` عدد کے آخر میں `L` لگانا ضروری ہے۔ سمالی میں اس کی علامت `J` ہے اور یہ دو لگاتار رجسٹرز (`v0`، `v1`) لیتا ہے۔",
                hi = "`long` मान के अंत में `L` लगाएं। स्माली में इसका चिह्न `J` है और यह लगातार दो रजिस्टर (`v0` + `v1`) लेता है।",
                ar = "أضف الحرف `L` في نهاية الرقم الطويل؛ وفي سمالي يرمز له بـ `J` ويشغل مسجلين متتاليين (`v0` و `v1`).",
                fa_af = "در انتهای عدد `long` حرف `L` بگذارید؛ در سمالی نماد آن `J` است و دو رجستر متوالی (`v0` و `v1`) اشغال می‌کند.",
                in_id = "Tambahkan akhiran `L` pada literal `long`; di Smali simbolnya `J` dan memakai dua register berurutan (`v0` + `v1`)."
            ),
            javaExample = """long nowMillis = System.currentTimeMillis();
long maxUploadBytes = 5L * 1024 * 1024 * 1024; // 5 GB""",
            commonMistakeContent = localized(
                en = "Forgetting `L` in `5 * 1024 * 1024 * 1024` overflows 32-bit `int` math BEFORE assigning to the `long` variable!",
                bn = "`5 * 1024 * 1024 * 1024` লেখার সময় শুরুতে `5L` না দিলে গুণ করার সময়ই ৩২-বিট ওভারফ্লো হয়ে ভুল সংখ্যা চলে আসে!",
                ur = "ضرب دیتے وقت پہلے عدد کے ساتھ `5L` نہ لکھنے سے 32-بٹ اوور فلو ہو کر غلط جواب آتا ہے!",
                hi = "गुणा करते समय `5L` न लिखने पर `long` में जाने से पहले ही 32-बिट ओवरफ़्लो हो जाता है!",
                ar = "نسيان حرف `L` في أول رقم أثناء الضرب يسبب طفحاً في حساب 32-بت قبل تخزين النتيجة في `long`!",
                fa_af = "فراموش کردن `L` در ابتدای عبارت ضرب باعث سرریز ۳۲ بیتی پیش از ذخیره در متغیر `long` می‌شود!",
                in_id = "Lupa menulis `5L` pada perkalian besar menyebabkan overflow 32-bit sebelum disimpan ke `long`!"
            )
        ),

        JavaKeywordItem(
            keyword = "double",
            badge = "64-bit • D",
            category = JavaKeywordCategory.TYPES_VARS,
            syntax = "double price = 99.95;",
            smaliEquivalent = "const-wide v0, 0x4058fdccccccccccL  # Type: D",
            purposeContent = localized(
                en = "64-bit IEEE-754 floating-point number for high-precision decimals and scientific math.",
                bn = "দশমিক সংখ্যা ও নিখুঁত গাণিতিক হিসাবের জন্য ৬৪-বিট ফ্লোটিং-পয়েন্ট টাইপ।",
                ur = "اعشاریہ اعداد اور درست ریاضیاتی حساب کے لیے 64-بٹ فلوٹنگ پوائنٹ ٹائپ۔",
                hi = "दशमलव संख्याओं और सटीक वैज्ञानिक गणना के लिए 64-बिट फ़्लोटिंग-पॉइंट प्रकार।",
                ar = "عدد عشري عالي الدقة بحجم 64-بت للعمليات الحسابية والكسور.",
                fa_af = "نوع اعشاری ۶۴ بیتی با دقت بالا برای محاسبات ریاضی و اعداد کسری.",
                in_id = "Angka pecahan desimal presisi tinggi 64-bit untuk perhitungan matematika."
            ),
            detailedExplanationContent = localized(
                en = "Decimal literals in Java (`3.14`) are `double` by default (whereas `float` requires `3.14f`). Like `long`, `double` (`D` in Smali) uses a register pair (`v0, v1`).",
                bn = "জাভায় যেকোনো দশমিক সংখ্যা (`3.14`) লিখলে তা ডিফল্টভাবে `double` হয় (`float` লিখতে শেষে `f` লাগে)। স্মালিতে এর টাইপ `D` এবং এটিও জোড়া রেজিস্টার নেয়।",
                ur = "جاوا میں ہر اعشاریہ عدد (`3.14`) خود بخود `double` ہوتا ہے۔ سمالی میں اس کی علامت `D` ہے اور یہ دو رجسٹر لیتا ہے۔",
                hi = "जावा में दशमलव संख्या (`3.14`) डिफ़ॉल्ट रूप से `double` होती है। स्माली में इसका चिह्न `D` है और यह 2 रजिस्टर लेता है।",
                ar = "الأرقام العشرية في جافا تكون `double` افتراضياً، ويرمز لها في سمالي بالحرف `D` وتأخذ زوج مسجلات.",
                fa_af = "اعداد اعشاری در جاوا به‌طور پیش‌فرض `double` هستند و در سمالی با نماد `D` دو رجستر اشغال می‌کنند.",
                in_id = "Angka desimal di Java otomatis bertipe `double` (`D` di Smali dan menempati sepasang register)."
            ),
            javaExample = """double radius = 4.5;
double area = Math.PI * radius * radius;""",
            commonMistakeContent = localized(
                en = "Never compare two `double` values with `==` (`0.1 + 0.2` is `0.30000000000000004`)! Check `Math.abs(a - b) < 1e-9` or use `BigDecimal` for money.",
                bn = "কখনোই দুটি `double` সংখ্যা `==` দিয়ে মিলাবেন না এবং টাকার হিসাবে `BigDecimal` বা পয়সা (`long`) ব্যবহার করুন!",
                ur = "دو `double` قدروں کا موازنہ کبھی `==` سے نہ کریں، اور مالیاتی حساب کے لیے `BigDecimal` استعمال کریں۔",
                hi = "दो `double` मानों की तुलना `==` से न करें; पैसों के हिसाब के लिए `BigDecimal` का उपयोग करें।",
                ar = "لا تقارن قيم `double` بـ `==` أبداً بسبب التقريب الثنائي، واستخدم `BigDecimal` للحسابات المالية.",
                fa_af = "هرگز دو مقدار `double` را با `==` مقایسه نکنید و برای محاسبات مالی از `BigDecimal` بهره ببرید.",
                in_id = "Jangan bandingkan `double` memakai `==`; gunakan `Math.abs(a - b) < 1e-9` atau `BigDecimal` untuk uang."
            )
        ),

        JavaKeywordItem(
            keyword = "boolean",
            badge = "true/false • Z",
            category = JavaKeywordCategory.TYPES_VARS,
            syntax = "boolean isVerified = true;",
            smaliEquivalent = "const/4 v0, 0x1  # true = 0x1, false = 0x0 (Type: Z)",
            purposeContent = localized(
                en = "Logical flag holding strictly `true` or `false` to control conditions and feature toggles.",
                bn = "শুধুমাত্র সত্য (`true`) অথবা মিথ্যা (`false`) মান রাখার লজিক্যাল ডেটা টাইপ।",
                ur = "صرف سچ (`true`) یا جھوٹ (`false`) محفوظ کرنے والی منطقی ٹائپ۔",
                hi = "केवल सत्य (`true`) या असत्य (`false`) मान रखने वाला तार्किक डेटा प्रकार।",
                ar = "نوع منطقي يحمل فقط `true` أو `false` للتحكم في الشروط والخيارات.",
                fa_af = "نوع منطقی که تنها دو مقدار درست (`true`) یا نادرست (`false`) را نگه می‌دارد.",
                in_id = "Tipe logika yang hanya bernilai `true` atau `false` untuk mengontrol kondisi."
            ),
            detailedExplanationContent = localized(
                en = "Unlike C/C++, Java does NOT allow numbers (`1` or `0`) in `if (...)`—you must pass a real `boolean`. However, once compiled to Android Smali (`Z`), `true` becomes `0x1` and `false` becomes `0x0`!",
                bn = "জাভা সোর্স কোডে `if (1)` লেখা নিষিদ্ধ—অবশ্যই `boolean` (`true`/`false`) দিতে হয়। তবে অ্যান্ড্রয়েড স্মালিতে (`Z`) কম্পাইল হওয়ার পর `true` হয়ে যায় `0x1` এবং `false` হয়ে যায় `0x0`!",
                ur = "جاوا کوڈ میں `if(1)` لکھنا منع ہے، صرف `boolean` چلتا ہے؛ لیکن سمالی (`Z`) میں `true` دراصل `0x1` اور `false` صفر `0x0` بن جاتا ہے!",
                hi = "जावा कोड में `if(1)` अवैध है, केवल `boolean` मान्य है; किंतु स्माली (`Z`) में `true` का मान `0x1` और `false` का `0x0` होता है!",
                ar = "تمنع جافا كتابة أرقام داخل `if`، لكن بعد الترجمة إلى سمالي (`Z`) تمثل `0x1` القيمة `true` وتمثل `0x0` القيمة `false`.",
                fa_af = "در جاوا نوشتن عدد داخل `if` مجاز نیست، اما پس از کامپایل به سمالی (`Z`) مقدار `true` برابر `0x1` و `false` برابر `0x0` می‌شود.",
                in_id = "Di Java `if(1)` dilarang, wajib `boolean`; namun di Smali (`Z`), `true` dikompilasi menjadi `0x1` dan `false` menjadi `0x0`."
            ),
            javaExample = """boolean hasToken = token != null && !token.isEmpty();
if (hasToken) {
    openDashboard();
}""",
            commonMistakeContent = localized(
                en = "Using the wrapper `Boolean` (capital B) instead of primitive `boolean` can throw a `NullPointerException` when unboxed if its value is `null`!",
                bn = "ছোট হাতের `boolean`-এর বদলে বড় হাতের `Boolean` অবজেক্ট নিলে সেটি `null` হতে পারে এবং `if`-এ চেক করার সময় অ্যাপ ক্র্যাশ করতে পারে!",
                ur = "چھوٹے `boolean` کی جگہ بڑا `Boolean` آبجیکٹ استعمال کرنے پر اگر وہ `null` ہو تو ایپ کریش ہو سکتی ہے!",
                hi = "`boolean` के बजाय कैपिटल `Boolean` ऑब्जेक्ट `null` हो सकता है जिससे अनबॉक्सिंग पर क्रैश हो जाता है!",
                ar = "استخدام الفئة المغلفة `Boolean` بحرف كبير قد يسبب انهيار `NullPointerException` إذا كانت قيمتها فارغة!",
                fa_af = "استفاده از کلاس `Boolean` با حرف بزرگ در صورت `null` بودن هنگام بررسی شرط باعث کرش برنامه می‌شود!",
                in_id = "Memakai objek `Boolean` (huruf besar) berisiko `NullPointerException` jika nilainya `null` saat dicek di `if`!"
            )
        ),

        JavaKeywordItem(
            keyword = "String",
            badge = "Immutable • Object",
            category = JavaKeywordCategory.TYPES_VARS,
            syntax = "String name = \"Smali Pro\";",
            smaliEquivalent = "const-string v0, \"Smali Pro\"  # Ljava/lang/String;",
            purposeContent = localized(
                en = "Immutable UTF-16 text object stored on the Heap (or shared String Pool).",
                bn = "অপরিবর্তনীয় টেক্সট অবজেক্ট যা Heap বা শেয়ারড String Pool মেমোরিতে থাকে।",
                ur = "ناقابلِ تغیر متن کا آبجیکٹ جو ہیپ یا اسٹرنگ پول میں محفوظ رہتا ہے۔",
                hi = "अपरिवर्तनीय टेक्स्ट ऑब्जेक्ट जो Heap या साझा String Pool में रहता है।",
                ar = "كائن نصي غير قابل للتعديل يُحفظ في ذاكرة Heap أو تجمع النصوص المشترك.",
                fa_af = "شیء متنی تغییرناپذیر که در حافظه Heap یا استخر رشته‌ها نگهداری می‌شود.",
                in_id = "Objek teks tetap (immutable) yang disimpan di Heap atau String Pool."
            ),
            detailedExplanationContent = localized(
                en = "`String` is a full class (`java.lang.String`), not a primitive! Every method like `.trim()`, `.replace()`, or `.substring()` returns a brand-new `String` without mutating the original.",
                bn = "`String` কোনো প্রিমিটিভ নয়, এটি `java.lang.String` ক্লাসের পূর্ণাঙ্গ অবজেক্ট! এটি Immutable, অর্থাৎ `.replace()` বা `.trim()` কল করলে পুরোনো লেখা বদলায় না, বরং নতুন `String` তৈরি হয়।",
                ur = "`String` ایک مکمل کلاس (`java.lang.String`) ہے۔ اس پر `.trim()` یا `.replace()` چلانے سے نیا اسٹرنگ بنتا ہے، اصل تبدیل نہیں ہوتا۔",
                hi = "`String` एक क्लास (`java.lang.String`) है। `.trim()` या `.replace()` चलाने पर मूल टेक्स्ट नहीं बदलता, नया `String` बनता है।",
                ar = "`String` هي فئة كاملة وليست نوعاً أساسياً؛ وكل عملية تعديل تنشئ نصاً جديداً دون المساس بالأصل.",
                fa_af = "`String` یک کلاس کامل (`java.lang.String`) و تغییرناپذیر است؛ متدهایی مثل `.trim()` رشته جدیدی برمی‌گردانند.",
                in_id = "`String` adalah kelas objek penuh (`Ljava/lang/String;`); operasi `.trim()` atau `.replace()` selalu menghasilkan `String` baru."
            ),
            javaExample = """String raw = "  admin@app.dev  ";
String clean = raw.trim().toLowerCase();
boolean valid = clean.endsWith(".dev");""",
            commonMistakeContent = localized(
                en = "NEVER compare two `String` variables with `==`! Always use `\"expected\".equals(actual)` or `Objects.equals(a, b)`.",
                bn = "কখনোই `==` দিয়ে দুটি `String` তুলনা করবেন না! সবসময় `.equals()` ব্যবহার করুন।",
                ur = "کبھی بھی دو `String` کو `==` سے مت ملائیں! ہمیشہ `.equals()` استعمال کریں۔",
                hi = "कभी भी दो `String` को `==` से न मिलाएं! सदैव `.equals()` का उपयोग करें।",
                ar = "إياك ومقارنة نصوص `String` باستخدام `==`؛ استخدم دائماً `.equals()`.",
                fa_af = "هرگز دو `String` را با `==` مقایسه نکنید؛ همیشه از `.equals()` استفاده نمایید.",
                in_id = "JANGAN PERNAH membandingkan `String` dengan `==`; selalu gunakan `.equals()`."
            )
        ),

        JavaKeywordItem(
            keyword = "char",
            badge = "16-bit • C",
            category = JavaKeywordCategory.TYPES_VARS,
            syntax = "char grade = 'A';",
            smaliEquivalent = "const/16 v0, 0x41  # 'A' Unicode 65 (Type: C)",
            purposeContent = localized(
                en = "Single 16-bit Unicode character enclosed in single quotes `'A'`.",
                bn = "সিঙ্গেল কোটেশনের `'A'` ভেতরে রাখা একটিমাত্র ১৬-বিট ইউনিকোড অক্ষর।",
                ur = "سنگل کوٹس `'A'` میں لکھا جانے والا ایک 16-بٹ یونیکوڈ حرف۔",
                hi = "सिंगल कोट्स `'A'` में लिखा जाने वाला एकल 16-बिट यूनिकोड अक्षर।",
                ar = "محرف يونيكود واحد بحجم 16-بت يُكتب بين علامتي اقتباس مفردتين `'A'`.",
                fa_af = "یک کاراکتر ۱۶ بیتی یونیکد که داخل گیومه تکی `'A'` نوشته می‌شود.",
                in_id = "Satu karakter Unicode 16-bit yang diapit tanda kutip tunggal `'A'`."
            ),
            detailedExplanationContent = localized(
                en = "Single quotes `'A'` denote a `char` (stored as its numeric Unicode code point `65` / `0x41`, descriptor `C` in Smali), whereas double quotes `\"A\"` create a Heap `String` object.",
                bn = "সিঙ্গেল কোট `'A'` মানে `char` (যার ভেতরে আসলে ইউনিকোড সংখ্যা `65` বা `0x41` থাকে, স্মালিতে `C`), আর ডাবল কোট `\"A\"` মানে `String` অবজেক্ট!",
                ur = "سنگل کوٹ `'A'` کا مطلب `char` (یونیکوڈ نمبر `0x41`، سمالی میں `C`) ہے جبکہ ڈبل کوٹ `\"A\"` ایک `String` آبجیکٹ بناتا ہے۔",
                hi = "सिंगल कोट `'A'` एक `char` (`0x41`, स्माली में `C`) है, जबकि डबल कोट `\"A\"` एक `String` ऑब्जेक्ट बनाता है।",
                ar = "الاقتباس المفرد `'A'` ينشئ `char` رقمه `0x41` (`C` في سمالي)، بينما الاقتباس المزدوج `\"A\"` ينشئ كائن `String`.",
                fa_af = "گیومه تکی `'A'` یک `char` عددی (`0x41` با نماد `C`) می‌سازد، اما گیومه جفتی `\"A\"` یک شیء `String` ایجاد می‌کند.",
                in_id = "Kutip tunggal `'A'` adalah `char` (`0x41`, tipe `C` di Smali), sedangkan kutip ganda `\"A\"` adalah objek `String`."
            ),
            javaExample = """char initial = 'M';
boolean isDigit = Character.isDigit(initial); // false""",
            commonMistakeContent = localized(
                en = "Adding two `char` values (`'A' + 'B'`) adds their integer ASCII codes (`65 + 66 = 131`) instead of joining them into `\"AB\"`!",
                bn = "দুটি `char` যোগ করলে (`'A' + 'B'`) সেগুলোর সংখ্যা যোগ হয়ে `131` হয়ে যায়, `\"AB\"` টেক্সট হয় না!",
                ur = "دو `char` جمع کرنے (`'A' + 'B'`) سے ان کے نمبر جمع ہو کر `131` بنتے ہیں، `\"AB\"` نہیں بنتا!",
                hi = "दो `char` जोड़ने (`'A' + 'B'`) पर उनके ASCII अंक जुड़कर `131` बनते हैं, `\"AB\"` नहीं!",
                ar = "جمع محرفين (`'A' + 'B'`) يجمع أرقامهما (`131`) ولا يدمجهما في نص `\"AB\"`!",
                fa_af = "جمع کردن دو `char` مانند `'A' + 'B'` کد عددی آن‌ها را جمع می‌زند (`131`) نه اینکه رشته `\"AB\"` بسازد!",
                in_id = "Menjumlahkan dua `char` (`'A' + 'B'`) menjumlahkan kode angkanya (`131`), bukan menggabung teks `\"AB\"`!"
            )
        ),

        // ==================== CONTROL FLOW (5) ====================
        JavaKeywordItem(
            keyword = "if / else",
            badge = "Branching",
            category = JavaKeywordCategory.CONTROL_FLOW,
            syntax = "if (score >= 50) { ... } else { ... }",
            smaliEquivalent = "if-lt p0, v0, :else_label  # Inverted jump!",
            purposeContent = localized(
                en = "Executes different code blocks depending on whether a boolean condition is true or false.",
                bn = "শর্ত সত্য (`true`) নাকি মিথ্যা (`false`) তার ওপর ভিত্তি করে আলাদা কোড ব্লক চালায়।",
                ur = "شرط درست یا غلط ہونے کی بنیاد پر مختلف کوڈ بلاکس چلاتا ہے۔",
                hi = "शर्त सत्य या असत्य होने के आधार पर अलग-अलग कोड ब्लॉक चलाता है।",
                ar = "ينفذ مسارات برمجية مختلفة بناءً على تحقق الشرط المنطقي من عدمه.",
                fa_af = "بسته به درست یا نادرست بودن شرط، بلوک‌های متفاوتی از کد را اجرا می‌کند.",
                in_id = "Menjalankan blok kode berbeda tergantung kondisi bernilai benar atau salah."
            ),
            detailedExplanationContent = localized(
                en = "Java evaluates `if` conditions top-to-bottom and stops at the first matching branch. When compiled to Smali, `if (a >= 50)` is inverted into `if-lt p0, v0, :else_branch` so the true body falls through naturally!",
                bn = "জাভা উপর থেকে নিচে শর্ত চেক করে। মজার বিষয় হলো, জাভার `if (a >= 50)` কম্পাইল হয়ে স্মালিতে উল্টো শর্ত `if-lt` হয়ে যায়—যেন শর্ত না মিললে `:else` লেবেলে লাফ দেয়!",
                ur = "جاوا اوپر سے نیچے شرط چیک کرتا ہے، اور سمالی میں ترجمہ ہوتے وقت `if (a >= 50)` الٹ کر `if-lt` بن جاتا ہے۔",
                hi = "जावा ऊपर से नीचे शर्त जांचता है, और स्माली में संकलित होते समय `if (a >= 50)` उलटकर `if-lt` जंप बन जाता है।",
                ar = "تفحص جافا الشروط بالترتيب، وعند الترجمة إلى سمالي يُعكس الشرط `>=` ليصبح قفزة `if-lt` نحو فرع `else`.",
                fa_af = "جاوا شرط‌ها را به ترتیب بررسی می‌کند و کامپایلر در سمالی شرط `>=` را به پرش معکوس `if-lt` تبدیل می‌نماید.",
                in_id = "Java mengevaluasi kondisi dari atas ke bawah; di Smali kondisi `>=` dibalik menjadi lompatan `if-lt` ke label `:else`."
            ),
            javaExample = """if (age >= 18) {
    status = "Adult";
} else {
    status = "Minor";
}""",
            commonMistakeContent = localized(
                en = "Omitting curly braces `{ }` on multi-line `if` blocks causes the second line to run UNCONDITIONALLY every time!",
                bn = "সেকেন্ড ব্র্যাকেট `{ }` ছাড়া একাধিক লাইন লিখলে দ্বিতীয় লাইনটি শর্ত ছাড়াই সবসময় চলে যায়—তাই সবসময় `{ }` ব্যবহার করুন!",
                ur = "بریکٹس `{ }` کے بغیر ایک سے زیادہ لائنیں لکھنے پر دوسری لائن ہمیشہ بغیر شرط کے چل جاتی ہے!",
                hi = "बिना ब्रैकेट `{ }` के एकाधिक लाइनें लिखने पर दूसरी लाइन बिना शर्त हमेशा चल जाती है!",
                ar = "إهمال الأقواس المعقوفة `{ }` يجعل السطر الثاني يعمل دائماً خارج الشرط؛ استخدم الأقواس دوماً!",
                fa_af = "نگذاشتن آکولاد `{ }` باعث می‌شود خط دوم همیشه و بدون شرط اجرا شود؛ همیشه آکولاد بگذارید!",
                in_id = "Menghilangkan kurung kurawal `{ }` membuat baris kedua selalu dijalankan tanpa syarat!"
            )
        ),

        JavaKeywordItem(
            keyword = "switch",
            badge = "Jump Table",
            category = JavaKeywordCategory.CONTROL_FLOW,
            syntax = "switch (code) { case 1: ... break; default: ... }",
            smaliEquivalent = "packed-switch p0, :pswitch_data_0",
            purposeContent = localized(
                en = "Fast multi-way branch matching an `int`, `enum`, or `String` against fixed cases.",
                bn = "একটি নির্দিষ্ট মান (`int`, `enum` বা `String`) অসংখ্য কেসের সাথে দ্রুত মিলিয়ে দেখার মাধ্যম।",
                ur = "ایک قدر کو متعدد مستقل صورتوں (cases) کے ساتھ تیزی سے ملانے کا طریقہ۔",
                hi = "किसी `int`, `enum` या `String` मान को कई निश्चित केसों से तेज़ी से मिलाने की संरचना।",
                ar = "تفرع متعدد سريع يقارن قيمة واحدة بعدة حالات ثابتة.",
                fa_af = "ساختار تصمیم‌گیری چندحالته سریع برای تطبیق `int`، `enum` یا `String` با مقادیر ثابت.",
                in_id = "Percabangan multi-arah cepat untuk mencocokkan nilai dengan banyak `case` tetap."
            ),
            detailedExplanationContent = localized(
                en = "Contiguous integer cases compile to Smali `packed-switch` (`O(1)` instant jump), while scattered values compile to `sparse-switch`. `String` switches hash the string first via `hashCode()` + `.equals()`.",
                bn = "পাশাপাশি সংখ্যার `switch` স্মালিতে `packed-switch` এবং দূরের সংখ্যার ক্ষেত্রে `sparse-switch` তৈরি করে, যা লম্বা `if-else`-এর চেয়ে অনেক দ্রুত কাজ করে!",
                ur = "مسلسل نمبروں والا `switch` سمالی میں `packed-switch` بنتا ہے جو لمبے `if-else` سے کہیں تیز ہوتا ہے۔",
                hi = "लगातार संख्याओं वाला `switch` स्माली में `packed-switch` बनता है जो लंबे `if-else` से बहुत तेज़ चलता है।",
                ar = "تتحول الحالات المتتالية إلى جدول `packed-switch` فوري في سمالي، بينما تستخدم القيم المتباعدة `sparse-switch`.",
                fa_af = "اعداد متوالی در سمالی به جدول پرش فوری `packed-switch` و اعداد پراکنده به `sparse-switch` کامپایل می‌شوند.",
                in_id = "Angka berurutan dikompilasi menjadi `packed-switch` instan di Smali, sedangkan angka acak menjadi `sparse-switch`."
            ),
            javaExample = """switch (httpCode) {
    case 200:
        return "OK";
    case 404:
        return "Not Found";
    default:
        return "Error";
}""",
            commonMistakeContent = localized(
                en = "Forgetting `break;` (or `return`) at the end of a `case` causes 'fall-through' execution right into the next `case` below it!",
                bn = "প্রতিটি `case`-এর শেষে `break;` বা `return` না দিলে কোড থামে না, বরং নিচের `case`-এর ভেতরে ঢুকে পড়ে (Fall-through)!",
                ur = "کسی `case` کے آخر میں `break;` نہ لکھنے سے کوڈ رکنے کی بجائے اگلے `case` میں بھی چل جاتا ہے!",
                hi = "`case` के अंत में `break;` भूलने से निष्पादन रुकता नहीं और अगले `case` में गिर जाता है!",
                ar = "نسيان `break;` في نهاية الحالة يؤدي إلى السقوط التلقائي وتنفيذ الحالة التي تليها!",
                fa_af = "فراموش کردن `break;` در پایان هر `case` باعث اجرای ناخواسته `case` بعدی می‌شود!",
                in_id = "Lupa menulis `break;` di akhir `case` menyebabkan eksekusi lanjut jatuh (fall-through) ke `case` berikutnya!"
            )
        ),

        JavaKeywordItem(
            keyword = "for",
            badge = "Iteration",
            category = JavaKeywordCategory.CONTROL_FLOW,
            syntax = "for (int i = 0; i < list.size(); i++) { ... }",
            smaliEquivalent = ":loop  if-ge v0, v1, :end  ...  add-int/lit8 v0, v0, 0x1  goto :loop",
            purposeContent = localized(
                en = "Repeats a block a known number of times or iterates cleanly over arrays and collections (`for-each`).",
                bn = "নির্দিষ্ট সংখ্যক বার কোড চালানো অথবা অ্যারে ও লিস্টের প্রতিটি আইটেম ঘুরে দেখার লুপ।",
                ur = "مخصوص تعداد تک کوڈ دہرانے یا ایرے اور لسٹ کے ہر رکن پر چلنے والا لوپ۔",
                hi = "निश्चित संख्या में कोड दोहराने या ऐरे/लिस्ट के प्रत्येक तत्व पर चलने वाला लूप।",
                ar = "حلقة تكرار لعدد محدد من المرات أو للمرور على عناصر المصفوفات والقوائم.",
                fa_af = "حلقه تکرار با شمارنده مشخص یا پیمایش تمیز روی عناصر آرایه و لیست.",
                in_id = "Perulangan terukur dengan penghitung indeks atau `for-each` untuk koleksi."
            ),
            detailedExplanationContent = localized(
                en = "Java supports both indexed `for (int i = 0; i < n; i++)` and enhanced `for (String item : items)`. For `List` collections, enhanced `for-each` compiles to `Iterator.hasNext()` and `Iterator.next()` in Smali.",
                bn = "জাভায় ইনডেক্স লুপ `for (int i = 0; ...)` এবং সহজ `for (String s : list)` দুটোই আছে। স্মালিতে লিস্টের `for-each` লুপ `Iterator.hasNext()` ও `next()` দিয়ে চলে।",
                ur = "جاوا میں انڈیکس والا `for` اور آسان `for (String s : list)` دونوں موجود ہیں۔ سمالی میں یہ `goto` یا `Iterator` بن جاتا ہے۔",
                hi = "जावा में इंडेक्स `for` और `for (String s : list)` दोनों हैं। स्माली में यह `Iterator` या `goto` लूप में बदलता है।",
                ar = "تدعم جافا حلقة الفهرس وحلقة `for-each` المختصرة التي تتحول في سمالي إلى `Iterator.hasNext()`.",
                fa_af = "جاوا هم حلقه شمارنده‌دار و هم `for-each` دارد که در سمالی به `goto` یا `Iterator.hasNext()` تبدیل می‌شود.",
                in_id = "Java mendukung `for` berindeks maupun `for (String s : list)` yang di Smali memakai `Iterator.hasNext()`."
            ),
            javaExample = """int sum = 0;
for (int score : scores) {
    sum += score;
}""",
            commonMistakeContent = localized(
                en = "Writing `i <= arr.length` instead of `i < arr.length` crashes on the last iteration with `ArrayIndexOutOfBoundsException`!",
                bn = "`i < arr.length`-এর বদলে `i <= arr.length` লিখলে শেষ ধাপে অ্যাপ `ArrayIndexOutOfBoundsException` দিয়ে ক্র্যাশ করে!",
                ur = "`i < arr.length` کی جگہ `i <= arr.length` لکھنے سے آخری چکر پر ایپ کریش ہو جاتی ہے!",
                hi = "`i < arr.length` के बजाय `i <= arr.length` लिखने से अंतिम चरण में `ArrayIndexOutOfBoundsException` आता है!",
                ar = "كتابة `i <= arr.length` بدلاً من `<` تسبب انهيار `ArrayIndexOutOfBoundsException` في الدورة الأخيرة!",
                fa_af = "نوشتن `i <= arr.length` به‌جای `<` در آخرین دور باعث کرش `ArrayIndexOutOfBoundsException` می‌شود!",
                in_id = "Menulis `i <= arr.length` alih-alih `<` akan memicu crash `ArrayIndexOutOfBoundsException`!"
            )
        ),

        JavaKeywordItem(
            keyword = "while",
            badge = "Condition Loop",
            category = JavaKeywordCategory.CONTROL_FLOW,
            syntax = "while ((line = reader.readLine()) != null) { ... }",
            smaliEquivalent = ":while_head  if-eqz v0, :while_exit  ...  goto :while_head",
            purposeContent = localized(
                en = "Repeats a block as long as a boolean condition stays true—ideal for streams and queues.",
                bn = "যতক্ষণ পর্যন্ত শর্ত সত্য থাকে ততক্ষণ কাজ চালিয়ে যাওয়ার লুপ—ফাইল বা নেটওয়ার্ক স্ট্রিম পড়ার জন্য সেরা।",
                ur = "جب تک شرط درست رہے کوڈ دہرانے والا لوپ—فائل یا نیٹ ورک اسٹریم پڑھنے کے لیے بہترین۔",
                hi = "जब तक शर्त सत्य रहे तब तक कोड दोहराने वाला लूप—फ़ाइल या स्ट्रीम पढ़ने हेतु उत्तम।",
                ar = "تكرر الكتلة طالما بقي الشرط محققاً، وهي مثالية لقراءة الملفات والتدفقات.",
                fa_af = "تکرار بلوک کد تا زمانی که شرط برقرار باشد؛ عالی برای خواندن فایل و جریان داده.",
                in_id = "Mengulang blok selama kondisi bernilai `true`—ideal untuk membaca stream atau antrean."
            ),
            detailedExplanationContent = localized(
                en = "`while` checks the condition BEFORE each pass (0 or more runs), whereas `do { ... } while (cond);` runs the body ONCE before checking.",
                bn = "`while` লুপ শুরুতেই আগে শর্ত চেক করে (শর্ত মিথ্যা হলে একবারও চলে না), আর `do-while` অন্তত একবার কাজ চালিয়ে তারপর শর্ত দেখে।",
                ur = "`while` پہلے شرط جانچتا ہے، جبکہ `do-while` کم از کم ایک بار چلنے کے بعد شرط چیک کرتا ہے۔",
                hi = "`while` पहले शर्त जांचता है, जबकि `do-while` कम से कम एक बार चलने के बाद शर्त जांचता है।",
                ar = "تفحص `while` الشرط قبل الدخول، بينما تنفذ `do-while` الكود مرة واحدة على الأقل قبل الفحص.",
                fa_af = "`while` ابتدا شرط را می‌سنجد، اما `do-while` حداقل یک بار بدنه را اجرا کرده و سپس شرط را بررسی می‌کند.",
                in_id = "`while` memeriksa kondisi di awal, sedangkan `do-while` menjalankan isi minimal satu kali terlebih dahulu."
            ),
            javaExample = """int retries = 0;
while (retries < 3 && !isConnected()) {
    reconnect();
    retries++;
}""",
            commonMistakeContent = localized(
                en = "Forgetting to update the loop variable inside `while` freezes the Android main thread in an infinite loop (ANR)!",
                bn = "`while`-এর ভেতরে ভেরিয়েবল আপডেট করতে ভুলে গেলে ইনফিনিট লুপ তৈরি হয়ে অ্যান্ড্রয়েড অ্যাপ হ্যাং (ANR) হয়ে যায়!",
                ur = "`while` کے اندر متغیر اپڈیٹ نہ کرنے سے لامحدود لوپ بن جاتا ہے اور ایپ ہینگ (ANR) ہو جاتی ہے!",
                hi = "`while` के भीतर वेरिएबल अपडेट करना भूलने से अनंत लूप बनता है और ऐप फ़्रीज़ (ANR) हो जाता है!",
                ar = "نسيان تحديث المتغير داخل `while` يخلق حلقة لا نهائية تجمد واجهة أندرويد (ANR)!",
                fa_af = "فراموش کردن تغییر متغیر شرط در داخل `while` حلقه بی‌نهایت ساخته و برنامه را قفل (ANR) می‌کند!",
                in_id = "Lupa memperbarui variabel kondisi di dalam `while` menyebabkan infinite loop dan aplikasi macet (ANR)!"
            )
        ),

        JavaKeywordItem(
            keyword = "return",
            badge = "Exit Method",
            category = JavaKeywordCategory.CONTROL_FLOW,
            syntax = "return result;",
            smaliEquivalent = "return-void | return v0 | return-wide v0 | return-object v0",
            purposeContent = localized(
                en = "Immediately exits the current method and hands the computed value back to the caller.",
                bn = "সাথে সাথে মেথডের কাজ শেষ করে ফলাফলটি যেখান থেকে মেথড কল হয়েছিল সেখানে ফেরত পাঠায়।",
                ur = "فوری طور پر میتھڈ سے باہر نکلتا ہے اور نتیجہ کال کرنے والے حصے کو واپس بھیجتا ہے۔",
                hi = "तुरंत मेथड समाप्त करता है और परिणाम कॉल करने वाले कोड को लौटाता है।",
                ar = "ينهي تنفيذ الدالة فوراً ويعيد القيمة الناتجة إلى المستدعي.",
                fa_af = "اجرای متد را فوراً پایان داده و مقدار خروجی را به فراخواننده برمی‌گرداند.",
                in_id = "Keluar seketika dari metode saat ini dan mengembalikan nilai hasil ke pemanggil."
            ),
            detailedExplanationContent = localized(
                en = "One Java `return` keyword maps to 4 distinct Dalvik opcodes depending on return type: `return-void` (`V`), `return v0` (`I/Z/F/B/C/S`), `return-wide v0` (`J/D`), and `return-object v0` (`L...;` or `[...`).",
                bn = "জাভায় একটাই `return` শব্দ থাকলেও স্মালিতে ডেটা টাইপ অনুযায়ী ৪টি আলাদা অপকোড হয়: `return-void`, `return v0`, `return-wide v0` এবং `return-object v0`!",
                ur = "جاوا کا ایک `return` لفظ سمالی میں قسم کے لحاظ سے 4 مختلف اوپ کوڈز (`return-void`، `return`، `return-wide`، `return-object`) بنتا ہے!",
                hi = "जावा का एक `return` शब्द स्माली में डेटा प्रकार के अनुसार 4 अलग ऑपकोड (`return-void`, `return`, `return-wide`, `return-object`) बनता है!",
                ar = "تترجم كلمة `return` الواحدة في جافا إلى 4 تعليمات مختلفة في سمالي حسب نوع القيمة المعادة!",
                fa_af = "کلمه واحد `return` در جاوا بسته به نوع خروجی به ۴ آپ‌کد مجزا در سمالی کامپایل می‌شود!",
                in_id = "Satu kata `return` di Java dipetakan menjadi 4 opcode berbeda di Smali sesuai tipe nilai kembalian!"
            ),
            javaExample = """public int clampPercent(int val) {
    if (val < 0) return 0;      // Guard clause
    if (val > 100) return 100;
    return val;
}""",
            commonMistakeContent = localized(
                en = "Any line written directly after an unconditional `return` fails to compile in Java with an 'Unreachable statement' error.",
                bn = "শর্তহীন `return`-এর নিচে কোনো লাইন লিখলে জাভা কম্পাইলার 'Unreachable statement' এরর দেয় কারণ সেই লাইনে কোড কখনোই পৌঁছাবে না।",
                ur = "بلا شرط `return` کے نیچے لکھی گئی کوئی بھی لائن کمپائل نہیں ہوتی کیونکہ کوڈ وہاں کبھی نہیں پہنچ سکتا۔",
                hi = "बिना शर्त `return` के बाद लिखी लाइन पर जावा 'Unreachable statement' कंपाइल त्रुटि देता है।",
                ar = "أي سطر يكتب مباشرة بعد `return` غير مشروطة يرفضه المترجم بخطأ 'Unreachable statement'.",
                fa_af = "هر خطی که مستقیماً بعد از `return` بدون شرط نوشته شود با خطای کامپایل 'Unreachable statement' مواجه می‌شود.",
                in_id = "Kode apa pun tepat di bawah `return` tanpa syarat akan ditolak kompiler dengan error 'Unreachable statement'."
            )
        ),

        // ==================== OOP & CLASSES (6) ====================
        JavaKeywordItem(
            keyword = "class",
            badge = "Blueprint",
            category = JavaKeywordCategory.OOP_CLASSES,
            syntax = "public class UserProfile { ... }",
            smaliEquivalent = ".class public Lcom/example/UserProfile;\n.super Ljava/lang/Object;",
            purposeContent = localized(
                en = "Defines an Object-Oriented blueprint grouping state (fields) and behavior (methods) together.",
                bn = "ভেরিয়েবল (Fields) এবং কাজ (Methods) একসাথে সাজিয়ে অবজেক্ট তৈরির মূল নকশা বা ব্লুপ্রিন্ট।",
                ur = "فیلڈز اور میتھڈز کو یکجا کر کے آبجیکٹ بنانے کا بنیادی نقشہ۔",
                hi = "फ़ील्ड्स (डेटा) और मेथड्स (व्यवहार) को एक साथ पिरोने वाला ऑब्जेक्ट ब्लूप्रिंट।",
                ar = "يعرّف قالباً كائنياً يجمع الخصائص (الحقول) والوظائف (الدوال) في وحدة واحدة.",
                fa_af = "تعریف قالب شیءگرا که داده‌ها (فیلدها) و رفتارها (متدها) را کنار هم سازماندهی می‌کند.",
                in_id = "Mendefinisikan cetak biru OOP yang menyatukan data (field) dan perilaku (method)."
            ),
            detailedExplanationContent = localized(
                en = "Every `.java` file's public class compiles to its own `.smali` file starting with `.class` and `.super`. Even if you don't write `extends`, every Java class implicitly inherits from `java.lang.Object`.",
                bn = "প্রতিটি জাভা ক্লাস কম্পাইল হয়ে আলাদা `.smali` ফাইল তৈরি করে যার শুরুতে `.class` ও `.super` লেখা থাকে। কিছু না লিখলেও প্রতিটি ক্লাস স্বয়ংক্রিয়ভাবে `java.lang.Object` থেকে উত্তরাধিকার পায়।",
                ur = "ہر جاوا کلاس اپنی الگ `.smali` فائل بناتی ہے اور ہر کلاس خودکار طور پر `java.lang.Object` کی وارث ہوتی ہے۔",
                hi = "प्रत्येक जावा क्लास अपनी `.smali` फ़ाइल बनाती है और बिना लिखे भी `java.lang.Object` से विरासत पाती है।",
                ar = "تترجم كل فئة جافا إلى ملف `.smali` مستقل، وترث كل فئة تلقائياً من `java.lang.Object`.",
                fa_af = "هر کلاس جاوا به یک فایل `.smali` مستقل کامپایل شده و به‌طور پیش‌فرض از `java.lang.Object` ارث می‌برد.",
                in_id = "Setiap kelas Java dikompilasi menjadi file `.smali` tersendiri dan otomatis mewarisi `java.lang.Object`."
            ),
            javaExample = """public class CourseBadge {
    private final String title;
    public CourseBadge(String title) {
        this.title = title;
    }
}""",
            commonMistakeContent = localized(
                en = "A `public class Foo` MUST be saved in a file named `Foo.java` with exact capitalization!",
                bn = "`public class Foo` লিখলে ফাইলের নামও হুবহু বড়-ছোট হাতের অক্ষর মিলিয়ে `Foo.java` হতে হবে!",
                ur = "`public class Foo` لکھنے کی صورت میں فائل کا نام بھی بالکل `Foo.java` ہونا لازمی ہے!",
                hi = "`public class Foo` को बिल्कुल समान अक्षरों वाले `Foo.java` नाम की फ़ाइल में ही रखना अनिवार्य है!",
                ar = "الفئة العامة `public class Foo` يجب حفظها في ملف يحمل الاسم المطابق تماماً `Foo.java`!",
                fa_af = "کلاس عمومی `public class Foo` باید حتماً در فایلی دقیقاً هم‌نام یعنی `Foo.java` ذخیره شود!",
                in_id = "`public class Foo` WAJIB disimpan dalam file bernama persis `Foo.java` sesuai huruf besar-kecilnya!"
            )
        ),

        JavaKeywordItem(
            keyword = "new",
            badge = "Heap Alloc",
            category = JavaKeywordCategory.OOP_CLASSES,
            syntax = "Wallet w = new Wallet(500);",
            smaliEquivalent = "new-instance v0, LWallet;\ninvoke-direct {v0, v1}, LWallet;-><init>(I)V",
            purposeContent = localized(
                en = "Allocates fresh memory on the Heap for an object and invokes its `<init>` constructor.",
                bn = "র‍্যামের Heap মেমোরিতে নতুন অবজেক্টের জায়গা তৈরি করে এবং তার কনস্ট্রাক্টর (`<init>`) চালায়।",
                ur = "ہیپ میموری میں نئے آبجیکٹ کے لیے جگہ مختص کرتا ہے اور اس کا کنسٹرکٹر چلاتا ہے۔",
                hi = "Heap मेमोरी में नए ऑब्जेक्ट हेतु स्थान बनाता है और उसका `<init>` कंस्ट्रक्टर चलाता है।",
                ar = "يحجز مساحة جديدة في ذاكرة Heap للكائن ويستدعي دالة البناء `<init>`.",
                fa_af = "فضای جدیدی در حافظه Heap برای شیء اختصاص داده و سازنده `<init>` آن را فراخوانی می‌کند.",
                in_id = "Mengalokasikan memori baru di Heap untuk objek lalu menjalankan konstruktor `<init>`."
            ),
            detailedExplanationContent = localized(
                en = "One Java `new Wallet(500)` statement ALWAYS splits into TWO Smali instructions: `new-instance` (allocates blank memory) followed immediately by `invoke-direct ... -> <init>` (initializes the fields).",
                bn = "জাভার এক লাইনের `new Wallet(500)` স্মালিতে সবসময় দুটি ধাপে ভেঙে যায়: প্রথমে `new-instance` দিয়ে ফাঁকা মেমোরি নেওয়া হয়, তারপর `invoke-direct` দিয়ে `<init>` কনস্ট্রাক্টর চালানো হয়!",
                ur = "جاوا کا `new` سمالی میں ہمیشہ دو ہدایات میں تقسیم ہوتا ہے: پہلے `new-instance` اور فوراً بعد `invoke-direct <init>`!",
                hi = "जावा का `new` स्माली में हमेशा दो निर्देशों में बंटता है: पहले `new-instance` और तुरंत बाद `invoke-direct <init>`!",
                ar = "تتحول تعليمة `new` في جافا دائماً إلى خطوتين في سمالي: `new-instance` ثم `invoke-direct` لدالة `<init>`!",
                fa_af = "دستور `new` در سمالی همیشه به دو خط تبدیل می‌شود: ابتدا `new-instance` و بلافاصله `invoke-direct <init>`!",
                in_id = "Satu ekspresi `new` di Java selalu dipecah menjadi dua opcode Smali: `new-instance` lalu `invoke-direct <init>`!"
            ),
            javaExample = """ArrayList<String> logs = new ArrayList<>();
logs.add("Heap object ready");""",
            commonMistakeContent = localized(
                en = "Calling `new` inside a tight `onDraw()` or 60fps UI loop creates thousands of temporary Heap objects and triggers GC frame stutter!",
                bn = "UI রেন্ডার বা লুপের ভেতরে বারবার অকারণে `new` কল করলে হাজার হাজার অবজেক্ট তৈরি হয়ে ফোনের ফ্রেম ল্যাগ (GC Pause) ঘটায়!",
                ur = "تیز لوپ یا `onDraw` کے اندر بار بار `new` چلانے سے میموری بھر جاتی ہے اور اسکرین اٹکنے لگتی ہے!",
                hi = "`onDraw()` या तेज़ लूप के भीतर बार-बार `new` करने से अनावश्यक Heap कचरा बनता है और UI अटकता है!",
                ar = "استدعاء `new` بكثرة داخل حلقات الرسم السريعة يرهق جامع المهملات (GC) ويسبب تقطع الواجهة!",
                fa_af = "فراخوانی مکرر `new` در حلقه‌های سریع رابط کاربری باعث پر شدن Heap و لگ زدن صفحه می‌شود!",
                in_id = "Memanggil `new` berulang kali di dalam loop `onDraw()` memicu Garbage Collector dan membuat animasi patah-patah!"
            )
        ),

        JavaKeywordItem(
            keyword = "this",
            badge = "Self Ref • p0",
            category = JavaKeywordCategory.OOP_CLASSES,
            syntax = "this.username = username;",
            smaliEquivalent = "iput-object p1, p0, LUser;->username:Ljava/lang/String;  # p0 = this",
            purposeContent = localized(
                en = "Refers to the current object instance on which an instance method or constructor is running.",
                bn = "বর্তমান যে অবজেক্টের ভেতরে মেথড বা কনস্ট্রাক্টর চলছে সেই অবজেক্ট নিজেকে বোঝাতে `this` ব্যবহৃত হয়।",
                ur = "موجودہ آبجیکٹ کی طرف اشارہ کرتا ہے جس کے اندر میتھڈ یا کنسٹرکٹر چل رہا ہو۔",
                hi = "वर्तमान ऑब्जेक्ट इंस्टेंस को संदर्भित करता है जिस पर मेथड या कंस्ट्रक्टर चल रहा है।",
                ar = "يشير إلى الكائن الحالي الذي تُنفَّذ بداخله الدالة أو الباني.",
                fa_af = "به نمونه شیء فعلی اشاره دارد که متد یا سازنده در حال اجرا روی آن است.",
                in_id = "Merujuk pada objek saat ini tempat metode atau konstruktor sedang dijalankan."
            ),
            detailedExplanationContent = localized(
                en = "Disambiguates instance fields (`this.name`) from parameter names (`name`). In Dalvik Smali, every non-static method receives `this` automatically as hidden register `p0`!",
                bn = "প্যারামিটার ও ক্লাসের ভেরিয়েবলের নাম এক হলে `this.name = name` লিখে পার্থক্য করা হয়। আর স্মালিতে প্রতিটি নন-স্ট্যাটিক মেথডের গোপন `p0` রেজিস্টারটিই হলো `this`!",
                ur = "پیرامیٹر اور فیلڈ کا نام یکساں ہو تو `this` فرق واضح کرتا ہے۔ سمالی میں ہر غیر اسٹیٹک میتھڈ کا `p0` رجسٹر ہی `this` ہوتا ہے!",
                hi = "फ़ील्ड और पैरामीटर का नाम समान होने पर `this` अंतर स्पष्ट करता है। स्माली में नॉन-स्टैटिक मेथड का `p0` रजिस्टर ही `this` है!",
                ar = "يميّز حقل الكائن عن المعامل بنفس الاسم، وفي سمالي يُمرر `this` تلقائياً في المسجل المخفي `p0`!",
                fa_af = "فیلد کلاس را از پارامتر هم‌نام متمایز می‌کند و در سمالی همیشه معادل رجستر پنهان `p0` در متدهای غیراستاتیک است!",
                in_id = "Membedakan field objek dari parameter bernama sama; di Smali `this` otomatis dikirim lewat register `p0`!"
            ),
            javaExample = """public class TokenBox {
    private String token;
    public void setToken(String token) {
        this.token = token; // p0.token = p1
    }
}""",
            commonMistakeContent = localized(
                en = "`this` CANNOT be used inside a `static` method because `static` methods belong to the class itself, not any object (`p0` is the first argument there)!",
                bn = "`static` মেথডের ভেতরে কখনোই `this` লেখা যায় না, কারণ স্ট্যাটিক মেথড কোনো অবজেক্ট ছাড়াই চলে!",
                ur = "`static` میتھڈ کے اندر `this` استعمال نہیں ہو سکتا کیونکہ وہ کسی مخصوص آبجیکٹ سے وابستہ نہیں ہوتا!",
                hi = "`static` मेथड के भीतर `this` का उपयोग नहीं किया जा सकता क्योंकि वहां कोई ऑब्जेक्ट इंस्टेंस नहीं होता!",
                ar = "لا يمكن استخدام `this` داخل دالة `static` لأنها تتبع الفئة نفسها وليس كائناً معيناً!",
                fa_af = "در داخل متد `static` نمی‌توان از `this` استفاده کرد زیرا متد استاتیک به شیء خاصی تعلق ندارد!",
                in_id = "`this` TIDAK BISA dipakai di dalam metode `static` karena metode statis tidak memiliki objek!"
            )
        ),

        JavaKeywordItem(
            keyword = "extends / super",
            badge = "Inheritance",
            category = JavaKeywordCategory.OOP_CLASSES,
            syntax = "public class MainActivity extends AppCompatActivity",
            smaliEquivalent = ".super Landroidx/appcompat/app/AppCompatActivity;\ninvoke-super {p0, p1}, ...",
            purposeContent = localized(
                en = "Creates a subclass inheriting parent fields/methods (`extends`) and calls parent behavior (`super`).",
                bn = "প্যারেন্ট ক্লাসের বৈশিষ্ট্য উত্তরাধিকার সূত্রে পাওয়া (`extends`) এবং প্যারেন্টের মেথড কল করা (`super`)।",
                ur = "پیرنٹ کلاس کی خصوصیات وراثت میں لینے (`extends`) اور پیرنٹ میتھڈ چلانے (`super`) کے لیے۔",
                hi = "पैरेंट क्लास के गुण विरासत में लेने (`extends`) और पैरेंट मेथड बुलाने (`super`) हेतु।",
                ar = "ينشئ فئة فرعية ترث الفئة الأب (`extends`) ويستدعي دوال الأب عبر (`super`).",
                fa_af = "ایجاد زیرکلاس با ارث‌بری از کلاس والد (`extends`) و فراخوانی متدهای والد با (`super`).",
                in_id = "Mewarisi kelas induk (`extends`) dan memanggil konstruktor/metode induk (`super`)."
            ),
            detailedExplanationContent = localized(
                en = "Every Android `Activity`, `Fragment`, or `ViewModel` uses `extends`. Inside overridden lifecycle methods like `onCreate`, `super.onCreate(savedInstanceState)` compiles to `invoke-super`.",
                bn = "অ্যান্ড্রয়েডের প্রতিটি `Activity` বা `ViewModel` তৈরিতে `extends` লাগে। আর `onCreate`-এর ভেতরে `super.onCreate(...)` স্মালিতে `invoke-super` হয়ে প্যারেন্ট ক্লাসের সেটআপ চালায়।",
                ur = "اینڈرائیڈ کی ہر `Activity` میں `extends` استعمال ہوتا ہے اور `super.onCreate` سمالی میں `invoke-super` بنتا ہے۔",
                hi = "हर एंड्रॉइड `Activity` में `extends` लगता है और `super.onCreate` स्माली में `invoke-super` में बदलता है।",
                ar = "تعتمد كل واجهات أندرويد على `extends`، وتترجم `super.onCreate` إلى تعليمة `invoke-super`.",
                fa_af = "تمام صفحات اندروید با `extends` ساخته می‌شوند و `super.onCreate` به دستور `invoke-super` کامپایل می‌شود.",
                in_id = "Semua `Activity` Android memakai `extends`, dan `super.onCreate()` dikompilasi menjadi `invoke-super`."
            ),
            javaExample = """public class ProAccount extends BaseAccount {
    public ProAccount(String id) {
        super(id); // Must be the FIRST line!
    }
}""",
            commonMistakeContent = localized(
                en = "When calling a parent constructor `super(...)`, Java requires it to be the VERY FIRST statement inside your constructor!",
                bn = "কনস্ট্রাক্টরের ভেতরে `super(...)` কল করলে সেটি অবশ্যই কনস্ট্রাক্টরের একদম প্রথম লাইন হতে হবে!",
                ur = "کنسٹرکٹر کے اندر `super(...)` کال کرنا ہو تو اس کا پہلی لائن ہونا لازمی ہے!",
                hi = "कंस्ट्रक्टर में `super(...)` को सबसे पहली लाइन पर रखना अनिवार्य होता है!",
                ar = "استدعاء باني الأب `super(...)` يجب أن يكون السطر الأول تماماً داخل الباني الفرعي!",
                fa_af = "فراخوانی سازنده والد `super(...)` باید حتماً اولین خط داخل سازنده فرزند باشد!",
                in_id = "Pemanggilan konstruktor induk `super(...)` WAJIB berada di baris paling pertama konstruktor!"
            )
        ),

        JavaKeywordItem(
            keyword = "interface / implements",
            badge = "Contract",
            category = JavaKeywordCategory.OOP_CLASSES,
            syntax = "public class ClickHandler implements View.OnClickListener",
            smaliEquivalent = ".implements Landroid/view/View\$OnClickListener;\ninvoke-interface {v0, p0}, ...",
            purposeContent = localized(
                en = "Defines a pure method contract (`interface`) that any class can promise to fulfill (`implements`).",
                bn = "কাজের চুক্তিপত্র (`interface`) তৈরি করে যা যেকোনো ক্লাস `implements` লিখে পূরণ করতে পারে।",
                ur = "میتھڈز کا معاہدہ (`interface`) طے کرتا ہے جسے کوئی بھی کلاس `implements` کے ذریعے پورا کرتی ہے۔",
                hi = "मेथड अनुबंध (`interface`) परिभाषित करता है जिसे कोई भी क्लास `implements` द्वारा पूरा करती है।",
                ar = "يحدد عقداً برمجياً (`interface`) تلتزم الفئات بتطبيقه عبر (`implements`).",
                fa_af = "تعریف قرارداد متدها (`interface`) که هر کلاسی با (`implements`) متعهد به اجرای آن می‌شود.",
                in_id = "Mendefinisikan kontrak metode (`interface`) yang dipenuhi kelas melalui (`implements`)."
            ),
            detailedExplanationContent = localized(
                en = "Interfaces decouple architecture (e.g., `Repository`, `Callback`, `Runnable`). Single-method interfaces (`@FunctionalInterface`) can be instantiated with clean `->` lambdas!",
                bn = "ক্লিন আর্কিটেকচার, কলব্যাক ও ক্লিক লিসেনারের মূল ভিত্তি হলো `interface`। এতে একটিমাত্র মেথড থাকলে জাভা ল্যাম্বডা (`->`) দিয়ে মাত্র এক লাইনেই লেখা যায়!",
                ur = "کلین آرکیٹیکچر اور کال بیکس کی بنیاد `interface` ہے۔ ایک میتھڈ والے انٹرفیس کو لیمبڈا (`->`) سے ایک لائن میں لکھ سکتے ہیں۔",
                hi = "क्लीन आर्किटेक्चर और कॉलबैक की नींव `interface` है। एकल-मेथड इंटरफ़ेस को लैम्ब्डा (`->`) से एक लाइन में लिख सकते हैं।",
                ar = "تفصل الواجهات المعمارية بين المكونات، ويمكن كتابة الواجهات أحادية الدالة كتعابير لامدا `->` مختصرة.",
                fa_af = "اینترفیس‌ها پایه معماری تمیز و کالبک‌ها هستند و نوع تک‌متدی آن‌ها با لامبدای `->` در یک خط نوشته می‌شود.",
                in_id = "Interface memisahkan kontrak dari implementasi, dan interface satu metode bisa ditulis singkat dengan lambda `->`."
            ),
            javaExample = """public interface Logger {
    void log(String msg);
}
Logger console = msg -> System.out.println(msg);""",
            commonMistakeContent = localized(
                en = "Interface methods are `public` by default—implementing them in a class WITHOUT the `public` keyword causes a 'Cannot reduce visibility' compile error!",
                bn = "ইন্টারফেসের মেথড ডিফল্টভাবেই `public` থাকে—তাই ক্লাসে ইমপ্লিমেন্ট করার সময় সামনে `public` না লিখলে কম্পাইল এরর হয়!",
                ur = "انٹرفیس کے میتھڈز خودکار `public` ہوتے ہیں، اس لیے کلاس میں ان کے آگے `public` لکھنا لازمی ہے!",
                hi = "इंटरफ़ेस मेथड डिफ़ॉल्ट रूप से `public` होते हैं—क्लास में लागू करते समय `public` न लिखने पर त्रुटि आती है!",
                ar = "دوال الواجهة عامة `public` افتراضياً؛ لذا يجب كتابة `public` صراحةً عند تطبيقها في الفئة!",
                fa_af = "متدهای اینترفیس به‌طور پیش‌فرض `public` هستند و هنگام پیاده‌سازی در کلاس نوشتن کلمه `public` الزامی است!",
                in_id = "Metode interface otomatis `public`; lupa menulis `public` saat mengimplementasikannya akan memicu error kompilasi!"
            )
        ),

        JavaKeywordItem(
            keyword = "enum",
            badge = "Type-Safe Set",
            category = JavaKeywordCategory.OOP_CLASSES,
            syntax = "public enum Role { GUEST, USER, ADMIN }",
            smaliEquivalent = ".class public final enum LRole;\n.super Ljava/lang/Enum;\nsget-object v0, LRole;->ADMIN:LRole;",
            purposeContent = localized(
                en = "Defines a fixed, type-safe set of named constants that can even carry fields and methods.",
                bn = "নির্দিষ্ট কিছু ধ্রুবক মানের টাইপ-সেফ তালিকা (যেমন স্ট্যাটাস বা রোল), যাতে ভেরিয়েবল ও মেথডও রাখা যায়।",
                ur = "مخصوص مستقل قدروں کا محفوظ مجموعہ جس میں فیلڈز اور میتھڈز بھی ہو سکتے ہیں۔",
                hi = "निश्चित नामित स्थिरांकों का टाइप-सेफ समूह जिसमें फ़ील्ड और मेथड भी हो सकते हैं।",
                ar = "مجموعة ثابتة وآمنة النوع من القيم المسماة التي يمكنها حمل خصائص ودوال.",
                fa_af = "مجموعه‌ای ثابت و ایمن از مقادیر نام‌گذاری‌شده که می‌تواند فیلد و متد نیز داشته باشد.",
                in_id = "Kumpulan konstanta bernama yang aman-tipe dan dapat memiliki field maupun metode."
            ),
            detailedExplanationContent = localized(
                en = "Unlike C integers, each Java `enum` entry is a singleton object (`public static final Role ADMIN`) initialized inside `<clinit>` and read via `sget-object` in Smali.",
                bn = "জাভায় প্রতিটি `enum` আইটেম আসলে একটি করে সিঙ্গেলটন অবজেক্ট, যা ক্লাস লোড হওয়ার সময় `<clinit>`-এ তৈরি হয় এবং স্মালিতে `sget-object` দিয়ে পড়া হয়।",
                ur = "جاوا میں ہر `enum` رکن ایک مکمل سنگلٹن آبجیکٹ ہوتا ہے جو سمالی میں `<clinit>` میں بنتا ہے اور `sget-object` سے پڑھا جاتا ہے۔",
                hi = "जावा में प्रत्येक `enum` मान एक सिंगलटन ऑब्जेक्ट होता है जो `<clinit>` में बनता है और `sget-object` से पढ़ा जाता है।",
                ar = "كل عنصر `enum` في جافا هو كائن وحيد يُنشأ في `<clinit>` ويُقرأ عبر `sget-object` في سمالي.",
                fa_af = "در جاوا هر عضو `enum` یک شیء یکتاست که در `<clinit>` ساخته شده و با `sget-object` خوانده می‌شود.",
                in_id = "Setiap anggota `enum` di Java adalah objek singleton yang dibuat di `<clinit>` dan dibaca lewat `sget-object`."
            ),
            javaExample = """public enum Status {
    IDLE, LOADING, SUCCESS, ERROR
}
if (state == Status.SUCCESS) { /* Safe == for enums! */ }""",
            commonMistakeContent = localized(
                en = "`Role.valueOf(\"unknown\")` throws `IllegalArgumentException` if the string doesn't match exact case—validate input first!",
                bn = "`Role.valueOf(...)`-এ বানান বা বড়/ছোট হাতের অক্ষর না মিললেই অ্যাপ ক্র্যাশ করে—তাই ইউজার ইনপুট আগে যাচাই করুন!",
                ur = "`valueOf` میں حروف بڑے چھوٹے ہونے کا فرق ہو تو فوراً کریش آتا ہے—پہلے ان پٹ چیک کریں!",
                hi = "`valueOf` में अक्षर केस न मिलने पर तुरंत `IllegalArgumentException` क्रैश होता है!",
                ar = "تُرمى `IllegalArgumentException` عند استدعاء `valueOf` بنص لا يطابق حالة الأحرف بدقة!",
                fa_af = "در `valueOf` اگر حروف بزرگ و کوچک دقیقاً مطابقت نداشته باشند خطای `IllegalArgumentException` رخ می‌دهد!",
                in_id = "`valueOf()` akan melempar `IllegalArgumentException` jika huruf besar-kecil string tidak cocok persis!"
            )
        )
    )

    val keywords: List<JavaKeywordItem>
        get() = coreKeywords + JavaKeywordsExtraData.extraKeywords

    fun search(
        query: String,
        category: JavaKeywordCategory?,
        language: AppLanguage
    ): List<JavaKeywordItem> {
        val q = query.trim().lowercase()
        return keywords.filter { item ->
            val matchesCat = category == null || item.category == category
            val matchesQuery = q.isEmpty() ||
                item.keyword.lowercase().contains(q) ||
                item.badge.lowercase().contains(q) ||
                item.syntax.lowercase().contains(q) ||
                item.smaliEquivalent.lowercase().contains(q) ||
                item.purposeContent.get(language).lowercase().contains(q) ||
                item.detailedExplanationContent.get(language).lowercase().contains(q) ||
                item.purposeContent.en.lowercase().contains(q)
            matchesCat && matchesQuery
        }
    }
}
