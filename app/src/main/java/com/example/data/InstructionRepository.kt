package com.example.data

import com.example.localization.AppLanguage
import com.example.model.InstructionCategory
import com.example.model.SmaliInstruction
import com.example.model.localized

object InstructionRepository {
    private val coreInstructions = listOf(
        // CONSTANTS (6)
        SmaliInstruction(
            opcode = "const/4",
            hexCode = "0x12",
            category = InstructionCategory.CONSTANTS,
            syntax = "const/4 vA, #+B",
            javaEquivalent = "int x = 1; // or boolean b = true; Object o = null;",
            kotlinEquivalent = "val x = 1 // or val b = true",
            purposeContent = localized(
                en = "Loads a tiny 4-bit signed constant (-8 to +7) into v0..v15.",
                bn = "ছোট ৪-বিট সংখ্যা (-8 থেকে +7), true (0x1), false বা null (0x0) রেজিস্টারে লোড করে।",
                ur = "چھوٹا 4-بٹ عدد (-8 سے +7)، true یا false/null رجسٹر میں لوڈ کرتا ہے۔",
                hi = "छोटा 4-बिट मान (-8 से +7), true (0x1) या false/null (0x0) रजिस्टर में लोड करता है।",
                ar = "تحميل قيمة ثابتة صغيرة 4-بت (-8 إلى +7) أو true/false في المسجل.",
                fa_af = "بارگذاری عدد کوچک ۴ بیتی (-8 تا +7) یا true/false در رجستر v0..v15.",
                in_id = "Memuat konstanta 4-bit (-8 s/d +7), true (0x1), atau false/null (0x0)."
            ),
            detailedExplanationContent = localized(
                en = "Most common opcode in Smali! Because both register and value fit in 4 bits each, it uses only 2 bytes in DEX. Used for booleans (`0x1` = true, `0x0` = false) and `null` (`0x0`).",
                bn = "স্মালির সবচেয়ে বেশি ব্যবহৃত অপকোড! এটি মাত্র ২ বাইট জায়গা নেয়। স্মালিতে আলাদা `true`, `false` বা `null` কীওয়ার্ড নেই—সবই `const/4 v0, 0x1` এবং `const/4 v0, 0x0` দিয়ে লেখা হয়।",
                ur = "سمالی کا سب سے عام اوپ کوڈ! سمالی میں `true` کے لیے `0x1` اور `false` یا `null` کے لیے `0x0` اسی سے لوڈ کیا جاتا ہے۔",
                hi = "स्माली का सबसे आम ऑपकोड! स्माली में `true` के लिए `0x1` और `false` या `null` के लिए `0x0` इसी से लोड होता है।",
                ar = "التعليمة الأكثر شيوعاً في سمالي! تُستخدم أيضاً للقيم المنطقية `0x1` و `0x0` والمؤشر الفارغ `null`.",
                fa_af = "پراستفاده‌ترین دستور سمالی! مقادیر بولین (`0x1` و `0x0`) و `null` نیز با همین دستور بارگذاری می‌شوند.",
                in_id = "Opcode paling umum di Smali! Dipakai juga untuk boolean (`0x1`/`0x0`) dan referensi `null` (`0x0`)."
            ),
            smaliExample = "const/4 v0, 0x1    # true\nconst/4 v1, 0x0    # false or null",
            commonMistakesContent = localized(
                en = "Writing `const/4 v0, 0x8` or using `v16` fails assembly—use `const/16` for numbers >= 8 or registers >= v16.",
                bn = "`0x7`-এর চেয়ে বড় সংখ্যা (যেমন `0x8`) বা `v15`-এর পরের রেজিস্টারে `const/4` কাজ করে না—তখন `const/16` ব্যবহার করুন।",
                ur = "`0x7` سے بڑے نمبر یا `v16` رجسٹر پر یہ کام نہیں کرتا—وہاں `const/16` لکھیں۔",
                hi = "`0x7` से बड़ी संख्या या `v16+` रजिस्टर पर यह काम नहीं करता—वहाँ `const/16` लिखें।",
                ar = "لا تقبل أعداداً أكبر من 7 أو مسجلات بعد v15؛ استخدم `const/16` بدلاً منها.",
                fa_af = "برای اعداد بزرگ‌تر از ۷ یا رجسترهای بالاتر از v15 خطا می‌دهد؛ از `const/16` استفاده کنید.",
                in_id = "Gagal jika nilai > 7 atau memakai v16+; gunakan `const/16` sebagai gantinya."
            )
        ),
        SmaliInstruction(
            opcode = "const/16",
            hexCode = "0x13",
            category = InstructionCategory.CONSTANTS,
            syntax = "const/16 vAA, #+BBBB",
            javaEquivalent = "int port = 8080;",
            kotlinEquivalent = "val port = 8080",
            purposeContent = localized(
                en = "Loads a 16-bit signed integer (-32,768 to +32,767) into any register v0..v255.",
                bn = "১৬-বিট পূর্ণসংখ্যা (-32,768 থেকে +32,767) যেকোনো রেজিস্টারে (v0..v255) লোড করে।",
                ur = "16-بٹ عدد (-32768 سے +32767) کسی بھی رجسٹر میں لوڈ کرتا ہے۔",
                hi = "16-बिट पूर्णांक (-32,768 से +32,767) किसी भी रजिस्टर में लोड करता है।",
                ar = "تحميل عدد صحيح موقع 16-بت في أي مسجل من v0 إلى v255.",
                fa_af = "بارگذاری عدد صحیح ۱۶ بیتی (-32768 تا +32767) در رجستر.",
                in_id = "Memuat bilangan bulat 16-bit (-32.768 s/d +32.767) ke register."
            ),
            detailedExplanationContent = localized(
                en = "Sign-extends the 16-bit literal to fill all 32 bits of the destination register.",
                bn = "মাঝারি আকারের সংখ্যা যেমন ১০০ (`0x64`), ১০০০ (`0x3e8`) বা পোর্ট নাম্বার লোড করার আদর্শ অপকোড।",
                ur = "درمیانے سائز کے نمبروں جیسے 100 (`0x64`) یا 1000 (`0x3e8`) کے لیے بہترین اوپ کوڈ۔",
                hi = "मध्यम आकार की संख्याओं जैसे 100 (`0x64`) या 1000 (`0x3e8`) के लिए आदर्श ऑपकोड।",
                ar = "تُستخدم للأعداد المتوسطة مثل 100 أو 1000 مع تمديد الإشارة إلى 32-بت.",
                fa_af = "مناسب‌ترین دستور برای بارگذاری اعداد متوسط مانند ۱۰۰ (`0x64`) یا ۱۰۰۰ (`0x3e8`).",
                in_id = "Ideal untuk memuat angka menengah seperti 100 (`0x64`) atau 1000 (`0x3e8`)."
            ),
            smaliExample = "const/16 v0, 0x3e8    # 1000",
            commonMistakesContent = localized(
                en = "Android resource IDs (`0x7f080012`) exceed 16 bits—use full `const` for R.id values.",
                bn = "অ্যান্ড্রয়েডের Resource ID (`0x7f...`) ১৬-বিটের বড় হওয়ায় সেখানে `const/16` নয়, পূর্ণ `const` লিখতে হয়।",
                ur = "اینڈرائیڈ ریسورس آئی ڈیز (`0x7f...`) کے لیے مکمل `const` استعمال کریں۔",
                hi = "एंड्रॉइड रिसोर्स आईडी (`0x7f...`) 16-बिट से बड़ी होती हैं—उनके लिए `const` लिखें।",
                ar = "معرفات الموارد (`0x7f...`) أكبر من 16-بت لذا تتطلب `const` الكاملة.",
                fa_af = "شناسه‌های منابع اندروید (`0x7f...`) بزرگ‌تر از ۱۶ بیت هستند و نیاز به `const` دارند.",
                in_id = "ID resource Android (`0x7f...`) melebihi 16-bit—gunakan `const` penuh."
            )
        ),
        SmaliInstruction(
            opcode = "const",
            hexCode = "0x14",
            category = InstructionCategory.CONSTANTS,
            syntax = "const vAA, #+BBBBBBBB",
            javaEquivalent = "int resId = 0x7f0a0042;",
            kotlinEquivalent = "val resId = 0x7f0a0042",
            purposeContent = localized(
                en = "Loads any full 32-bit integer or IEEE-754 float bit pattern into a register.",
                bn = "যেকোনো পূর্ণ ৩২-বিট সংখ্যা, কালার কোড বা R.id রিসোর্স আইডি রেজিস্টারে লোড করে।",
                ur = "کوئی بھی مکمل 32-بٹ عدد، کلر کوڈ یا R.id رجسٹر میں لوڈ کرتا ہے۔",
                hi = "कोई भी पूर्ण 32-बिट पूर्णांक, रंग कोड या R.id रिसोर्स मान लोड करता है।",
                ar = "تحميل أي قيمة كاملة بحجم 32-بت أو معرف مورد في المسجل.",
                fa_af = "بارگذاری هر عدد کامل ۳۲ بیتی، کد رنگ یا شناسه R.id در رجستر.",
                in_id = "Memuat bilangan penuh 32-bit, kode warna, atau ID resource R.id."
            ),
            detailedExplanationContent = localized(
                en = "Unrestricted 32-bit constant loader. Every XML layout/string/drawable ID in `R.java` is loaded with `const`.",
                bn = "কোনো সীমাবদ্ধতা ছাড়াই যেকোনো ৩২-বিট সংখ্যা লোড করতে পারে। অ্যাপের সব XML ভিউ ও স্ট্রিং আইডি লোড করতে এটি ব্যবহৃত হয়।",
                ur = "بغیر کسی حد کے کوئی بھی 32-بٹ قیمت لوڈ کرتا ہے، خاص طور پر XML کی تمام آئی ڈیز۔",
                hi = "बिना किसी सीमा के कोई भी 32-बिट मान लोड करता है, विशेषकर सभी XML R.id मान।",
                ar = "تحمل أي ثابت 32-بت بلا قيود، وتُشاهد دائماً مع معرفات واجهات XML.",
                fa_af = "بدون محدودیت هر عدد ۳۲ بیتی را بارگذاری می‌کند و برای تمام شناسه‌های XML کاربرد دارد.",
                in_id = "Memuat nilai 32-bit tanpa batas, terutama seluruh ID tampilan XML."
            ),
            smaliExample = "const v0, 0x7f08005a    # R.id.submit_btn",
            commonMistakesContent = localized(
                en = "Cannot load 64-bit `long` values; use `const-wide` with an `L` suffix for 64-bit values.",
                bn = "৬৪-বিট `long` সংখ্যার জন্য এটি কাজ করবে না—তার জন্য `const-wide` ও শেষে `L` দিতে হবে।",
                ur = "64-بٹ `long` کے لیے یہ کام نہیں کرتا، وہاں `const-wide` استعمال کریں۔",
                hi = "64-बिट `long` मान के लिए `const-wide` का उपयोग करें।",
                ar = "لا تصلح للأعداد الطويلة 64-بت؛ استخدم `const-wide` مع اللاحقة `L`.",
                fa_af = "برای اعداد ۶۴ بیتی کاربرد ندارد؛ از `const-wide` با پسوند `L` استفاده کنید.",
                in_id = "Tidak bisa untuk `long` 64-bit; gunakan `const-wide` dengan akhiran `L`."
            )
        ),
        SmaliInstruction(
            opcode = "const/high16",
            hexCode = "0x15",
            category = InstructionCategory.CONSTANTS,
            syntax = "const/high16 vAA, #+BBBB0000",
            javaEquivalent = "float alpha = 1.0f;",
            kotlinEquivalent = "val alpha = 1.0f",
            purposeContent = localized(
                en = "Loads top 16 bits into a 32-bit register (most common way to load clean floats like 1.0f).",
                bn = "উপরের ১৬-বিট লোড করে—বিশেষ করে `1.0f`, `2.0f` এর মতো দশমিক Float সংখ্যা লোড করতে ব্যবহৃত হয়।",
                ur = "اوپری 16 بٹس لوڈ کرتا ہے—خاص طور پر `1.0f` جیسے فلوٹ نمبروں کے لیے۔",
                hi = "शीर्ष 16 बिट लोड करता है—विशेष रूप से `1.0f` जैसे फ़्लोट मान लोड करने का मानक तरीका।",
                ar = "تحميل أعلى 16-بت وتصفير الباقي، وهي الطريقة القياسية لتحميل الأعداد العشرية مثل 1.0f.",
                fa_af = "بارگذاری ۱۶ بیت بالایی؛ روش استاندارد بارگذاری اعداد اعشاری رُند مانند `1.0f`.",
                in_id = "Memuat 16-bit teratas; cara paling umum memuat float bulat seperti `1.0f`."
            ),
            detailedExplanationContent = localized(
                en = "IEEE-754 floats like `1.0f` (`0x3f800000`) and `0.5f` (`0x3f000000`) have all zeros in the bottom 16 bits, letting Dalvik store them in half the space!",
                bn = "`1.0f` (`0x3f800000`) বা `0.5f` (`0x3f000000`) ফ্লোট সংখ্যার নিচের ১৬ বিট শূন্য থাকে, তাই জায়গা বাঁচাতে ডালভিক `const/high16` ব্যবহার করে।",
                ur = "`1.0f` (`0x3f800000`) کے نچلے 16 بٹس صفر ہوتے ہیں، اس لیے جگہ بچانے کے لیے یہ استعمال ہوتا ہے۔",
                hi = "`1.0f` (`0x3f800000`) के निचले 16 बिट शून्य होते हैं, इसलिए जगह बचाने हेतु यह उपयोग होता है।",
                ar = "الأعداد العشرية مثل `1.0f` تنتهي بأصفار في أسفل 16-بت مما يوفر نصف المساحة.",
                fa_af = "اعداد اعشاری مثل `1.0f` در ۱۶ بیت پایینی صفر هستند، لذا با این دستور کم‌حجم ذخیره می‌شوند.",
                in_id = "Float seperti `1.0f` (`0x3f800000`) memiliki 16-bit bawah nol sehingga hemat ukuran DEX."
            ),
            smaliExample = "const/high16 v0, 0x3f800000    # 1.0f",
            commonMistakesContent = localized(
                en = "Bottom 4 hex digits MUST be `0000`; if non-zero (like `3.14f`), use standard `const`.",
                bn = "শেষ ৪টি হেক্স অঙ্ক অবশ্যই `0000` হতে হবে; `3.14f`-এর মতো সংখ্যার জন্য সাধারণ `const` লাগবে।",
                ur = "آخری چار ہندسے `0000` ہونا ضروری ہیں؛ ورنہ عام `const` استعمال کریں۔",
                hi = "अंतिम 4 हेक्स अंक `0000` होने चाहिए; अन्यथा सामान्य `const` का उपयोग करें।",
                ar = "يجب أن تكون آخر أربع خانات أصفاراً `0000`، وإلا فاستخدم `const`.",
                fa_af = "چهار رقم آخر هگز باید حتماً `0000` باشد؛ در غیر این صورت از `const` استفاده کنید.",
                in_id = "4 digit hex terakhir wajib `0000`; jika tidak, gunakan `const` biasa."
            )
        ),
        SmaliInstruction(
            opcode = "const-wide",
            hexCode = "0x18",
            category = InstructionCategory.CONSTANTS,
            syntax = "const-wide vAA, #+BBBBBBBBBBBBBBBB",
            javaEquivalent = "long timeout = 5000000000L;",
            kotlinEquivalent = "val timeout = 5000000000L",
            purposeContent = localized(
                en = "Loads a 64-bit long or double into TWO consecutive registers (vA and vA+1).",
                bn = "৬৪-বিট বড় সংখ্যা (`long` বা `double`) পরপর দুটি রেজিস্টারে (`vA` ও `vA+1`) লোড করে।",
                ur = "64-بٹ `long` یا `double` کو دو لگاتار رجسٹرز (`vA` اور `vA+1`) میں لوڈ کرتا ہے۔",
                hi = "64-बिट `long` या `double` मान को दो लगातार रजिस्टरों (`vA` और `vA+1`) में लोड करता है।",
                ar = "تحميل قيمة 64-بت في زوج مسجلات متتاليين (`vA` و `vA+1`).",
                fa_af = "بارگذاری عدد ۶۴ بیتی (`long` یا `double`) در دو رجستر متوالی (`vA` و `vA+1`).",
                in_id = "Memuat nilai 64-bit (`long`/`double`) ke dua register berurutan (`vA` & `vA+1`)."
            ),
            detailedExplanationContent = localized(
                en = "Variants include `const-wide/16`, `const-wide/32`, `const-wide`, and `const-wide/high16`. Always writes BOTH `vA` and `vA+1`.",
                bn = "`const-wide v0, 0x100L` চালালে `v0` এবং `v1` দুটি রেজিস্টারই একসাথে ব্যবহৃত হয়, তাই `.locals` কমপক্ষে ২ হতে হবে!",
                ur = "جب `const-wide v0` چلتا ہے تو `v0` اور `v1` دونوں بھر جاتے ہیں، لہٰذا `.locals` کا خیال رکھیں!",
                hi = "`const-wide v0` चलाने पर `v0` और `v1` दोनों भर जाते हैं, इसलिए `.locals` कम से कम 2 रखें!",
                ar = "تكتب القيمة في `v0` و `v1` معاً، لذا تأكد من أن `.locals` يكفي للمسجلين.",
                fa_af = "اجرای `const-wide v0` هر دو خانه `v0` و `v1` را پر می‌کند، پس `.locals` باید کافی باشد.",
                in_id = "Menulis ke `v0` sekaligus `v1`, jadi pastikan `.locals` mencukupi kedua slot."
            ),
            smaliExample = "const-wide v0, 0x12a05f200L    # 5,000,000,000L in (v0, v1)",
            commonMistakesContent = localized(
                en = "Forgetting the `L` suffix at the end of the hex literal or clobbering `v1` before returning.",
                bn = "হেক্স সংখ্যার শেষে `L` না দিলে কম্পাইল এরর হবে এবং ভুল করে `v1`-এ অন্য মান রাখলে ডেটা নষ্ট হয়ে যাবে।",
                ur = "آخر میں `L` نہ لگانا یا غلطی سے `v1` میں کوئی اور قیمت رکھ دینا کریش کا باعث بنتا ہے۔",
                hi = "अंत में `L` न लगाना या गलती से `v1` को ओवरराइट करना `VerifyError` देता है।",
                ar = "نسيان حرف `L` في نهاية الرقم أو الكتابة فوق المسجل الثاني `v1` بالخطأ.",
                fa_af = "فراموش کردن حرف `L` در انتهای عدد یا بازنویسی اشتباهی رجستر دوم (`v1`).",
                in_id = "Lupa menambahkan huruf `L` di akhir angka atau menimpa `v1` secara tidak sengaja."
            )
        ),
        SmaliInstruction(
            opcode = "const-string",
            hexCode = "0x1a",
            category = InstructionCategory.CONSTANTS,
            syntax = "const-string vAA, \"text\"",
            javaEquivalent = "String msg = \"Verified\";",
            kotlinEquivalent = "val msg = \"Verified\"",
            purposeContent = localized(
                en = "Loads a String object reference from the DEX string pool into register vAA.",
                bn = "DEX স্ট্রিং পুল থেকে যেকোনো টেক্সট (`String`) রেজিস্টারে লোড করে।",
                ur = "DEX اسٹرنگ پول سے کوئی بھی عبارت (`String`) رجسٹر میں لوڈ کرتا ہے۔",
                hi = "DEX स्ट्रिंग पूल से किसी `String` ऑब्जेक्ट का रेफरेंस रजिस्टर में लोड करता है।",
                ar = "تحميل مرجع نص `String` من جدول النصوص إلى المسجل.",
                fa_af = "بارگذاری یک متن (`String`) از جدول رشته‌های DEX درون رجستر.",
                in_id = "Memuat referensi objek `String` dari pool string DEX ke register."
            ),
            detailedExplanationContent = localized(
                en = "If an APK has over 65,536 strings, `baksmali` automatically upgrades this to `const-string/jumbo` (`0x1b`).",
                bn = "অ্যাপে ৬৫,৫৩৬টির বেশি টেক্সট থাকলে এটি স্বয়ংক্রিয়ভাবে `const-string/jumbo` হিসেবে লেখা হয়। এটি যেহেতু অবজেক্ট, তাই রিটার্ন করতে `return-object` লাগে।",
                ur = "اگر ایپ میں 65,536 سے زائد اسٹرنگز ہوں تو `const-string/jumbo` استعمال ہوتا ہے۔",
                hi = "यदि ऐप में 65,536 से अधिक स्ट्रिंग हों तो `const-string/jumbo` उपयोग होता है।",
                ar = "إذا زادت نصوص التطبيق عن 65,536 نصاً تتحول تلقائياً إلى `const-string/jumbo`.",
                fa_af = "در صورت وجود بیش از ۶۵٬۵۳۶ رشته در برنامه، از نسخه `const-string/jumbo` استفاده می‌شود.",
                in_id = "Jika APK memiliki >65.536 string, otomatis memakai varian `const-string/jumbo`."
            ),
            smaliExample = "const-string v0, \"Welcome to Smali Pro\"\nreturn-object v0",
            commonMistakesContent = localized(
                en = "Using `return v0` instead of `return-object v0` for a String crashes the ART verifier.",
                bn = "স্ট্রিং একটি অবজেক্ট—তাই ভুল করে `return v0` লিখলে ক্র্যাশ করবে; অবশ্যই `return-object v0` লিখতে হবে।",
                ur = "اسٹرنگ ایک آبجیکٹ ہے، اس کے لیے `return-object` استعمال کرنا لازمی ہے۔",
                hi = "स्ट्रिंग एक ऑब्जेक्ट है—इसके लिए हमेशा `return-object` का ही प्रयोग करें।",
                ar = "النص كائن مرجعي لذا يجب إرجاعه عبر `return-object` وليس `return`.",
                fa_af = "رشته یک شیء است و باید حتماً با `return-object` بازگردانده شود نه `return`.",
                in_id = "String adalah objek—wajib memakai `return-object`, bukan `return`."
            )
        ),

        // MOVES (4)
        SmaliInstruction(
            opcode = "move",
            hexCode = "0x01",
            category = InstructionCategory.MOVES,
            syntax = "move vA, vB",
            javaEquivalent = "int copy = original;",
            kotlinEquivalent = "val copy = original",
            purposeContent = localized(
                en = "Copies a 32-bit primitive value from register vB into register vA.",
                bn = "একটি ৩২-বিট সাধারণ সংখ্যা (`int`, `boolean`, `float`) `vB` থেকে `vA` রেজিস্টারে কপি করে।",
                ur = "32-بٹ سادہ قیمت کو رجسٹر `vB` سے `vA` میں کاپی کرتا ہے۔",
                hi = "32-बिट प्रिमिटिव मान को रजिस्टर `vB` से `vA` में कॉपी करता है।",
                ar = "نسخ قيمة أولية بحجم 32-بت من المسجل `vB` إلى `vA`.",
                fa_af = "کپی کردن یک مقدار ۳۲ بیتی اولیه از رجستر `vB` به درون `vA`.",
                in_id = "Menyalin nilai primitif 32-bit dari register `vB` ke `vA`."
            ),
            detailedExplanationContent = localized(
                en = "Only copies 32-bit non-object values between `v0..v15`. For higher registers use `move/from16`; for objects use `move-object`; for 64-bit use `move-wide`.",
                bn = "কেবল সাধারণ সংখ্যার জন্য প্রযোজ্য। অবজেক্ট কপি করতে `move-object` এবং ৬৪-বিট `long` কপি করতে `move-wide` ব্যবহার করতে হয়।",
                ur = "صرف سادہ نمبروں کے لیے؛ آبجیکٹ کے لیے `move-object` اور 64-بٹ کے لیے `move-wide` لکھیں۔",
                hi = "केवल प्रिमिटिव मान के लिए; ऑब्जेक्ट हेतु `move-object` और 64-बिट हेतु `move-wide` लिखें।",
                ar = "للأنواع الأولية فقط؛ للكائنات استخدم `move-object` وللأنواع العريضة `move-wide`.",
                fa_af = "فقط مخصوص اعداد اولیه است؛ برای اشیاء `move-object` و برای ۶۴ بیتی `move-wide` بنویسید.",
                in_id = "Khusus primitif 32-bit; gunakan `move-object` untuk objek dan `move-wide` untuk 64-bit."
            ),
            smaliExample = "move v0, p0    # copy int parameter p0 into local v0",
            commonMistakesContent = localized(
                en = "Copying a String or Object with `move` instead of `move-object` fails type verification.",
                bn = "`String` বা অবজেক্ট কপি করতে `move` লিখলে `VerifyError` হবে—অবশ্যই `move-object` লিখুন।",
                ur = "آبجیکٹ کو `move` سے کاپی کرنے پر `VerifyError` آتا ہے—ہمیشہ `move-object` لکھیں۔",
                hi = "ऑब्जेक्ट पर `move` चलाने से `VerifyError` आता है—हमेशा `move-object` लिखें।",
                ar = "نسخ كائن بتعليمة `move` يسبب فشل الفحص؛ استخدم `move-object`.",
                fa_af = "کپی کردن شیء با `move` باعث خطای VerifyError می‌شود؛ از `move-object` استفاده کنید.",
                in_id = "Menyalin objek dengan `move` memicu `VerifyError`; wajib `move-object`."
            )
        ),
        SmaliInstruction(
            opcode = "move-object",
            hexCode = "0x07",
            category = InstructionCategory.MOVES,
            syntax = "move-object vA, vB",
            javaEquivalent = "User target = currentUser;",
            kotlinEquivalent = "val target = currentUser",
            purposeContent = localized(
                en = "Copies an object or array reference from register vB into vA.",
                bn = "যেকোনো অবজেক্ট বা অ্যারের রেফারেন্স এক রেজিস্টার থেকে অন্য রেজিস্টারে কপি করে।",
                ur = "کسی آبجیکٹ یا ایرے کا ریفرنس ایک رجسٹر سے دوسرے میں کاپی کرتا ہے۔",
                hi = "किसी ऑब्जेक्ट या ऐरे के रेफरेंस को एक रजिस्टर से दूसरे में कॉपी करता है।",
                ar = "نسخ مرجع كائن أو مصفوفة من المسجل `vB` إلى `vA`.",
                fa_af = "کپی کردن ارجاع یک شیء یا آرایه از رجستر `vB` به `vA`.",
                in_id = "Menyalin referensi objek atau array dari register `vB` ke `vA`."
            ),
            detailedExplanationContent = localized(
                en = "Does not clone the object on the heap—both registers now point to the exact same object instance.",
                bn = "এটি নতুন অবজেক্ট বানায় না, বরং দুটি রেজিস্টারই মেমোরির একই অবজেক্টকে নির্দেশ করে। `v16+` রেজিস্টারের জন্য `move-object/from16` ব্যবহৃত হয়।",
                ur = "یہ نیا آبجیکٹ نہیں بناتا بلکہ دونوں رجسٹرز ایک ہی آبجیکٹ کی طرف اشارہ کرتے ہیں۔",
                hi = "यह नया ऑब्जेक्ट नहीं बनाता, बल्कि दोनों रजिस्टर एक ही हीप ऑब्जेक्ट को इंगित करते हैं।",
                ar = "لا تنسخ الكائن في الذاكرة بل يشير المسجلان إلى نفس الكائن.",
                fa_af = "شیء جدید نمی‌سازد بلکه هر دو رجستر به یک شیء واحد در حافظه اشاره می‌کنند.",
                in_id = "Tidak menggandakan objek di heap; kedua register menunjuk ke objek yang sama."
            ),
            smaliExample = "move-object v0, p1    # copy String param p1 into v0",
            commonMistakesContent = localized(
                en = "Using `move-object` when registers exceed v15—switch to `move-object/from16`.",
                bn = "রেজিস্টার `v15`-এর বড় হলে `move-object/from16` ব্যবহার করতে হয়।",
                ur = "رجسٹر `v15` سے بڑا ہو تو `move-object/from16` استعمال کریں۔",
                hi = "रजिस्टर `v15` से बड़ा होने पर `move-object/from16` का उपयोग करें।",
                ar = "للمسجلات الأكبر من v15 استخدم `move-object/from16`.",
                fa_af = "اگر شماره رجستر بالاتر از v15 است از `move-object/from16` استفاده کنید.",
                in_id = "Jika nomor register > v15, gunakan `move-object/from16`."
            )
        ),
        SmaliInstruction(
            opcode = "move-result",
            hexCode = "0x0a",
            category = InstructionCategory.MOVES,
            syntax = "move-result vAA",
            javaEquivalent = "int len = text.length();",
            kotlinEquivalent = "val len = text.length",
            purposeContent = localized(
                en = "Captures a 32-bit primitive return value immediately after an invoke-* instruction.",
                bn = "মেথড কল (`invoke-*`) থেকে ফেরত আসা ৩২-বিট ফলাফল (`int`, `boolean`, `float`) রেজিস্টারে ধরে রাখে।",
                ur = "میتھڈ کال (`invoke-*`) سے واپس آنے والا 32-بٹ نتیجہ رجسٹر میں محفوظ کرتا ہے۔",
                hi = "मेथड कॉल (`invoke-*`) से लौटे 32-बिट प्रिमिटिव परिणाम को तुरंत रजिस्टर में पकड़ता है।",
                ar = "التقاط القيمة الأولية الراجعة (32-بت) مباشرة بعد استدعاء دالة `invoke-*`.",
                fa_af = "دریافت خروجی ۳۲ بیتی متد بلافاصله پس از دستور `invoke-*`.",
                in_id = "Menangkap hasil return primitif 32-bit tepat setelah instruksi `invoke-*`."
            ),
            detailedExplanationContent = localized(
                en = "Must appear as the VERY NEXT instruction after `invoke-*`. If you don't need a method's return value, you can omit `move-result` completely.",
                bn = "এটি অবশ্যই `invoke-*` লাইনের **ঠিক পরের লাইনেই** থাকতে হবে। অবশ্য আপনার যদি রিটার্ন মানটি দরকার না থাকে, তবে `move-result` না লিখলেও কোনো সমস্যা নেই।",
                ur = "یہ لازمی طور پر `invoke-*` کے فوراً بعد اگلی لائن میں ہونا چاہیے۔",
                hi = "यह अनिवार्य रूप से `invoke-*` की ठीक अगली लाइन में होना चाहिए।",
                ar = "يجب أن تكون التعليمة التالية مباشرة بعد سطر `invoke-*` دون أي فاصل.",
                fa_af = "حتماً باید بلافاصله در خط بعدی `invoke-*` نوشته شود.",
                in_id = "Wajib berada tepat di baris setelah `invoke-*` tanpa diselingi instruksi lain."
            ),
            smaliExample = "invoke-virtual {p0}, Ljava/lang/String;->length()I\nmove-result v0",
            commonMistakesContent = localized(
                en = "Using `move-result` for a method returning `Ljava/lang/String;`—objects require `move-result-object`!",
                bn = "মেথড যদি `String` বা অবজেক্ট রিটার্ন করে তবে `move-result` নয়, অবশ্যই `move-result-object` লিখতে হবে!",
                ur = "اگر میتھڈ آبجیکٹ واپس کرے تو `move-result-object` لکھنا لازمی ہے!",
                hi = "यदि मेथड ऑब्जेक्ट लौटाए तो `move-result-object` लिखना अनिवार्य है!",
                ar = "إذا كانت الدالة ترجع كائناً فيجب استخدام `move-result-object`!",
                fa_af = "اگر خروجی متد شیء باشد باید حتماً `move-result-object` بنویسید!",
                in_id = "Jika metode mengembalikan objek, wajib memakai `move-result-object`!"
            )
        ),
        SmaliInstruction(
            opcode = "move-result-object",
            hexCode = "0x0c",
            category = InstructionCategory.MOVES,
            syntax = "move-result-object vAA",
            javaEquivalent = "String upper = name.toUpperCase();",
            kotlinEquivalent = "val upper = name.uppercase()",
            purposeContent = localized(
                en = "Captures an Object or Array returned by the immediately preceding invoke-* or filled-new-array.",
                bn = "`invoke-*` মেথড কল থেকে ফেরত আসা যেকোনো অবজেক্ট (`String`, `Intent`, `List`) বা অ্যারে রেজিস্টারে ধরে।",
                ur = "پچھلی `invoke-*` کال سے واپس آنے والے آبجیکٹ یا ایرے کو رجسٹر میں محفوظ کرتا ہے۔",
                hi = "पिछले `invoke-*` से लौटे किसी भी ऑब्जेक्ट या ऐरे रेफरेंस को रजिस्टर में पकड़ता है।",
                ar = "التقاط الكائن أو المصفوفة الراجعة من استدعاء الدالة السابق مباشرة.",
                fa_af = "دریافت شیء یا آرایه بازگشتی از متد فراخوانی‌شده در خط قبلی.",
                in_id = "Menangkap objek atau array hasil pemanggilan `invoke-*` tepat sebelumnya."
            ),
            detailedExplanationContent = localized(
                en = "Whenever a called method's signature ends with `;` (an object) or starts with `[` (an array), capture its output with `move-result-object`.",
                bn = "চেনার সহজ বুদ্ধি: কল করা মেথডের সিগনেচারের একদম শেষে যদি সেমিকোলন `;` বা `[` থাকে, তবেই চোখ বন্ধ করে `move-result-object` বসবে!",
                ur = "آسان پہچان: اگر میتھڈ سگنیچر کے آخر میں `;` یا `[` ہو تو ہمیشہ `move-result-object` آئے گا!",
                hi = "सरल पहचान: यदि मेथड सिग्नेचर के अंत में `;` या `[` है, तो हमेशा `move-result-object` लगेगा!",
                ar = "علامة سريعة: إذا انتهى توقيع الدالة بفاصلة منقوطة `;` أو بدأ بـ `[` فاستخدم `move-result-object`.",
                fa_af = "نشانه سریع: هرگاه انتهای امضای متد `;` یا `[` داشت، حتماً از `move-result-object` استفاده کنید.",
                in_id = "Trik cepat: jika tanda tangan metode diakhiri `;` atau `[`, pasti gunakan `move-result-object`."
            ),
            smaliExample = "invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;\nmove-result-object v1",
            commonMistakesContent = localized(
                en = "Inserting a `const` or `Log.d` line between `invoke-*` and `move-result-object` breaks verification.",
                bn = "`invoke-*` এবং `move-result-object`-এর মাঝখানে অন্য কোনো লাইন লিখলে কোড নষ্ট হয়ে যাবে।",
                ur = "`invoke-*` اور `move-result-object` کے درمیان کوئی اور لائن نہ لکھیں۔",
                hi = "`invoke-*` और `move-result-object` के बीच कोई अन्य लाइन न डालें।",
                ar = "لا تضع أي سطر تنفيذي بين `invoke-*` و `move-result-object`.",
                fa_af = "هرگز بین `invoke-*` و `move-result-object` دستور دیگری قرار ندهید.",
                in_id = "Jangan menyisipkan baris apa pun di antara `invoke-*` dan `move-result-object`."
            )
        ),

        // RETURNS (3)
        SmaliInstruction(
            opcode = "return-void",
            hexCode = "0x0e",
            category = InstructionCategory.RETURNS,
            syntax = "return-void",
            javaEquivalent = "return; // inside void method",
            kotlinEquivalent = "return // Unit",
            purposeContent = localized(
                en = "Exits a void ()V method cleanly without returning any value.",
                bn = "যে মেথডের শেষে `V` (void) আছে, সেখান থেকে কোনো মান ছাড়াই কাজ শেষ করে বের হয়ে আসে।",
                ur = "`void` (`V`) میتھڈ کو بغیر کسی واپسی قیمت کے مکمل کر کے باہر آتا ہے۔",
                hi = "`void` (`V`) मेथड को बिना कोई मान लौटाए सुरक्षित रूप से समाप्त करता है।",
                ar = "إنهاء الدالة ذات النوع `V` (بلا إرجاع) والخروج منها بأمان.",
                fa_af = "پایان دادن به متدهای بدون خروجی (`V`) و بازگشت به فراخواننده‌.",
                in_id = "Mengakhiri metode `void` (`V`) tanpa mengembalikan nilai apa pun."
            ),
            detailedExplanationContent = localized(
                en = "Every constructor `<init>()V`, `<clinit>()V`, and `onCreate(...)V` ends with `return-void`.",
                bn = "প্রতিটি কনস্ট্রাক্টর `<init>()V` এবং `onCreate(...)V` মেথডের শেষে `return-void` থাকা বাধ্যতামূলক।",
                ur = "ہر کنسٹرکٹر `<init>()V` اور `onCreate(...)V` کے آخر میں `return-void` لازمی ہوتا ہے۔",
                hi = "हर कंस्ट्रक्टर `<init>()V` और `onCreate(...)V` के अंत में `return-void` अनिवार्य होता है।",
                ar = "تنتهي جميع البانيات `<init>()V` ودوال `onCreate` بتعليمة `return-void`.",
                fa_af = "تمامی سازنده‌ها `<init>()V` و متدهای `onCreate` با `return-void` تمام می‌شوند.",
                in_id = "Semua konstruktor `<init>()V` dan `onCreate` wajib diakhiri `return-void`."
            ),
            smaliExample = ".method public reset()V\n    .locals 0\n    return-void\n.end method",
            commonMistakesContent = localized(
                en = "Using `return-void` inside a method declared to return `Z` or `I` triggers a VerifyError.",
                bn = "মেথডের শেষে `Z` বা `I` লেখা থাকলে সেখানে `return-void` দিলে অ্যাপ ক্র্যাশ করবে।",
                ur = "اگر میتھڈ کی واپسی قسم `Z` یا `I` ہو تو `return-void` لکھنے سے کریش ہوگا۔",
                hi = "यदि मेथड का रिटर्न टाइप `Z` या `I` है तो `return-void` लिखने पर क्रैश होगा।",
                ar = "استخدامها داخل دالة ترجع `Z` أو `I` يؤدي إلى خطأ فحص فوري.",
                fa_af = "استفاده از آن در متدی که خروجی `Z` یا `I` دارد باعث کرش می‌شود.",
                in_id = "Memakai `return-void` pada metode bertipe return `Z` atau `I` memicu VerifyError."
            )
        ),
        SmaliInstruction(
            opcode = "return",
            hexCode = "0x0f",
            category = InstructionCategory.RETURNS,
            syntax = "return vAA",
            javaEquivalent = "return count; // int, boolean, float, char, byte, short",
            kotlinEquivalent = "return count",
            purposeContent = localized(
                en = "Returns a 32-bit primitive value (I, Z, B, S, C, F) from the current method.",
                bn = "মেথড থেকে ৩২-বিট প্রিমিটিভ মান (`I, Z, B, S, C, F`) ফলাফল হিসেবে ফেরত পাঠায়।",
                ur = "میتھڈ سے 32-بٹ سادہ قیمت (`I, Z, B, S, C, F`) واپس بھیجتا ہے۔",
                hi = "मेथड से 32-बिट प्रिमिटिव मान (`I, Z, B, S, C, F`) वापस लौटाता है।",
                ar = "إرجاع قيمة أولية بحجم 32-بت (`I, Z, B, S, C, F`) من الدالة الحالية.",
                fa_af = "بازگرداندن مقدار ۳۲ بیتی اولیه (`I, Z, B, S, C, F`) از متد جاری.",
                in_id = "Mengembalikan nilai primitif 32-bit (`I, Z, B, S, C, F`) dari metode."
            ),
            detailedExplanationContent = localized(
                en = "Covers booleans (`0x1`/`0x0`), integers, characters, and 32-bit floats. For 64-bit `J`/`D` use `return-wide vAA`.",
                bn = "বুলিয়ান (`Z`), পূর্ণসংখ্যা (`I`) ও ফ্লোট (`F`) রিটার্ন করতে এটি ব্যবহৃত হয়। আর ৬৪-বিট `J` বা `D` হলে `return-wide v0` লিখতে হয়।",
                ur = "بولین (`Z`)، انٹیجر (`I`) اور فلوٹ (`F`) کے لیے؛ جبکہ 64-بٹ کے لیے `return-wide` استعمال کریں۔",
                hi = "बूलियन (`Z`), पूर्णांक (`I`) और फ़्लोट (`F`) के लिए; 64-बिट के लिए `return-wide` लिखें।",
                ar = "تشمل الأعداد الصحيحة والمنطقية والعشرية؛ وللأنواع 64-بت استخدم `return-wide`.",
                fa_af = "شامل بولین (`Z`)، عدد صحیح (`I`) و اعشاری (`F`) است؛ برای ۶۴ بیتی از `return-wide` استفاده کنید.",
                in_id = "Mencakup boolean, int, dan float; untuk 64-bit (`J`/`D`) gunakan `return-wide`."
            ),
            smaliExample = "const/4 v0, 0x1\nreturn v0    # returns true for ()Z method",
            commonMistakesContent = localized(
                en = "Returning a 64-bit long (`J`) with `return` instead of `return-wide` fails verification.",
                bn = "`long` (`J`) মেথডে `return` লিখলে ভুল হবে—সেখানে `return-wide` লিখতে হবে।",
                ur = "`long` (`J`) میتھڈ میں ہمیشہ `return-wide` لکھیں۔",
                hi = "`long` (`J`) मेथड में `return` नहीं बल्कि `return-wide` लिखें।",
                ar = "إرجاع نوع `J` بتعليمة `return` بدلاً من `return-wide` يفشل التحقق.",
                fa_af = "بازگرداندن نوع `J` با `return` به‌جای `return-wide` باعث خطا می‌شود.",
                in_id = "Mengembalikan `long` (`J`) dengan `return` bukan `return-wide` akan gagal."
            )
        ),
        SmaliInstruction(
            opcode = "return-object",
            hexCode = "0x11",
            category = InstructionCategory.RETURNS,
            syntax = "return-object vAA",
            javaEquivalent = "return \"Success\";",
            kotlinEquivalent = "return \"Success\"",
            purposeContent = localized(
                en = "Returns an Object reference (L...;) or Array ([...) from the current method.",
                bn = "মেথড থেকে যেকোনো অবজেক্ট (`String`, `List`, ক্লাস) বা অ্যারে ফলাফল হিসেবে ফেরত পাঠায়।",
                ur = "میتھڈ سے کوئی بھی آبجیکٹ (`L...;`) یا ایرے (`[...`) واپس بھیجتا ہے۔",
                hi = "मेथड से कोई ऑब्जेक्ट रेफरेंस (`L...;`) या ऐरे (`[...`) वापस लौटाता है।",
                ar = "إرجاع مرجع كائن (`L...;`) أو مصفوفة (`[...`) من الدالة.",
                fa_af = "بازگرداندن ارجاع یک شیء (`L...;`) یا آرایه (`[...`) از متد.",
                in_id = "Mengembalikan referensi objek (`L...;`) atau array (`[...]`) dari metode."
            ),
            detailedExplanationContent = localized(
                en = "Even if you want to return `null`, load `const/4 v0, 0x0` and return it using `return-object v0`!",
                bn = "এমনকি আপনি যদি `null` রিটার্ন করতে চান, তবুও `const/4 v0, 0x0` লিখে `return-object v0` দিয়ে রিটার্ন করতে হবে!",
                ur = "یہاں تک کہ اگر `null` واپس کرنا ہو تب بھی `const/4 v0, 0x0` کے بعد `return-object v0` لکھیں!",
                hi = "यहाँ तक कि `null` लौटाने के लिए भी `const/4 v0, 0x0` के बाद `return-object v0` ही लिखना होता है!",
                ar = "حتى عند إرجاع `null`، ضع `const/4 v0, 0x0` ثم أرجعها عبر `return-object v0`!",
                fa_af = "حتی برای بازگرداندن `null` نیز باید پس از `const/4 v0, 0x0` از `return-object v0` استفاده کنید!",
                in_id = "Bahkan untuk mengembalikan `null`, muat `const/4 v0, 0x0` lalu panggil `return-object v0`!"
            ),
            smaliExample = "const-string v0, \"Pro Tier\"\nreturn-object v0",
            commonMistakesContent = localized(
                en = "Returning a non-matching class type that does not inherit from the declared return type.",
                bn = "মেথডে ঘোষিত ক্লাস টাইপের সাথে অমিল কোনো অবজেক্ট রিটার্ন করলে ভেরিফায়ার এরর দেয়।",
                ur = "میتھڈ کی مقررہ کلاس سے مختلف آبجیکٹ واپس کرنے پر ویریفائر ایرر دیتا ہے۔",
                hi = "मेथड के घोषित रिटर्न क्लास से असंगत ऑब्जेक्ट लौटाने पर त्रुटि आती है।",
                ar = "إرجاع كائن لا يتوافق مع نوع الإرجاع المعلن في ترويسة الدالة.",
                fa_af = "بازگرداندن شیئی که با نوع خروجی تعریف‌شده در هدر متد همخوانی ندارد.",
                in_id = "Mengembalikan objek yang tipenya tidak cocok dengan deklarasi metode."
            )
        ),

        // FIELDS (3)
        SmaliInstruction(
            opcode = "iget",
            hexCode = "0x52",
            category = InstructionCategory.FIELDS,
            syntax = "iget vDest, vObj, LClass;->field:I",
            javaEquivalent = "int c = this.coins;",
            kotlinEquivalent = "val c = this.coins",
            purposeContent = localized(
                en = "Reads a 32-bit primitive instance field from object vObj into vDest.",
                bn = "`vObj` অবজেক্ট (যেমন `p0`) থেকে একটি ৩২-বিট ইনস্ট্যান্স ফিল্ডের মান `vDest`-এ পড়ে আনে।",
                ur = "`vObj` آبجیکٹ سے 32-بٹ انسٹانس فیلڈ کی قیمت `vDest` میں پڑھتا ہے۔",
                hi = "ऑब्जेक्ट `vObj` से 32-बिट इंस्टेंस फ़ील्ड का मान `vDest` में पढ़ता है।",
                ar = "قراءة حقل كائن أولي (32-بت) من الكائن `vObj` وحفظه في `vDest`.",
                fa_af = "خواندن مقدار یک فیلد ۳۲ بیتی از روی شیء `vObj` و ذخیره در `vDest`.",
                in_id = "Membaca field instance primitif 32-bit dari objek `vObj` ke `vDest`."
            ),
            detailedExplanationContent = localized(
                en = "Type-specialized siblings: `iget-object` (objects/arrays), `iget-boolean` (`Z`), `iget-wide` (`J`/`D`), `iget-byte`, `iget-char`, `iget-short`.",
                bn = "ফিল্ডের টাইপ অনুযায়ী এর ভাইবোন রয়েছে: অবজেক্ট হলে `iget-object`, বুলিয়ান হলে `iget-boolean`, আর ৬৪-বিট হলে `iget-wide`।",
                ur = "ٹائپ کے مطابق اس کی اقسام: آبجیکٹ کے لیے `iget-object`، بولین کے لیے `iget-boolean` اور 64-بٹ کے لیے `iget-wide`۔",
                hi = "टाइप के अनुसार इसके रूप: ऑब्जेक्ट हेतु `iget-object`, बूलियन हेतु `iget-boolean` और 64-बिट हेतु `iget-wide`।",
                ar = "أخواتها حسب النوع: `iget-object` للكائنات، و `iget-boolean` للمنطقي، و `iget-wide` لـ 64-بت.",
                fa_af = "انواع هم‌خانواده بر اساس نوع فیلد: `iget-object`، `iget-boolean` و `iget-wide`.",
                in_id = "Varian sesuai tipe: `iget-object`, `iget-boolean` (`Z`), dan `iget-wide` (`J`/`D`)."
            ),
            smaliExample = "iget v0, p0, Lcom/example/Player;->health:I",
            commonMistakesContent = localized(
                en = "If `vObj` is null at runtime, `iget` immediately throws `NullPointerException`.",
                bn = "`vObj` যদি খালি (`null`) থাকে তবে `iget` চালালে `NullPointerException` ক্র্যাশ হবে।",
                ur = "اگر `vObj` نل ہو تو `NullPointerException` آتا ہے۔",
                hi = "यदि `vObj` null है तो `iget` तुरंत `NullPointerException` फेंकता है।",
                ar = "إذا كان الكائن `vObj` فارغاً (`null`) فسيُرمى استثناء `NullPointerException`.",
                fa_af = "اگر شیء `vObj` برابر `null` باشد خطای `NullPointerException` رخ می‌دهد.",
                in_id = "Jika `vObj` bernilai `null`, `iget` langsung melempar `NullPointerException`."
            )
        ),
        SmaliInstruction(
            opcode = "iput",
            hexCode = "0x59",
            category = InstructionCategory.FIELDS,
            syntax = "iput vSrc, vObj, LClass;->field:I",
            javaEquivalent = "this.coins = 500;",
            kotlinEquivalent = "this.coins = 500",
            purposeContent = localized(
                en = "Writes 32-bit value vSrc into an instance field on object vObj.",
                bn = "`vSrc` রেজিস্টারের মানটি `vObj` অবজেক্টের নির্দিষ্ট ইনস্ট্যান্স ফিল্ডে জমা রাখে।",
                ur = "`vSrc` کی قیمت کو `vObj` آبجیکٹ کے انسٹانس فیلڈ میں محفوظ کرتا ہے۔",
                hi = "`vSrc` रजिस्टर का मान ऑब्जेक्ट `vObj` के इंस्टेंस फ़ील्ड में लिखता है।",
                ar = "كتابة القيمة الموجودة في `vSrc` داخل حقل الكائن `vObj`.",
                fa_af = "نوشتن مقدار رجستر `vSrc` در فیلد شیء `vObj`.",
                in_id = "Menulis nilai `vSrc` ke dalam field instance milik objek `vObj`."
            ),
            detailedExplanationContent = localized(
                en = "Notice the operand order matches `iget`: `vValue, vObject, LClass;->field:Type`. Use `iput-object` for String/Object fields.",
                bn = "ক্রমটি মনে রাখুন: প্রথমে যে মানটি রাখবেন (`vSrc`), তারপরে যে অবজেক্টে রাখবেন (`p0`), শেষে ফিল্ডের নাম। অবজেক্ট ফিল্ডের জন্য `iput-object`।",
                ur = "ترتیب یاد رکھیں: پہلے قیمت والا رجسٹر (`vSrc`)، پھر آبجیکٹ (`p0`) اور آخر میں فیلڈ کا نام۔",
                hi = "क्रम याद रखें: पहले मान (`vSrc`), फिर ऑब्जेक्ट (`p0`), और अंत में फ़ील्ड नाम।",
                ar = "تذكر الترتيب: سجل القيمة أولاً، ثم سجل الكائن (`p0`)، ثم مسار الحقل.",
                fa_af = "ترتیب را به خاطر بسپارید: ابتدا رجستر مقدار (`vSrc`)، سپس رجستر شیء (`p0`) و در پایان نام فیلد.",
                in_id = "Ingat urutannya: register nilai (`vSrc`), register objek (`p0`), lalu nama field."
            ),
            smaliExample = "const/16 v0, 0x1f4\niput v0, p0, Lcom/example/Player;->coins:I",
            commonMistakesContent = localized(
                en = "Swapping `vSrc` and `p0` (`iput p0, v0, ...`) fails verification.",
                bn = "ভুল করে `v0` এবং `p0`-এর জায়গা উল্টে দিলে কোড কাজ করবে না।",
                ur = "`v0` اور `p0` کی جگہ الٹ دینے سے کوڈ ناکام ہو جاتا ہے۔",
                hi = "`v0` और `p0` का क्रम उलटने से वेरिफ़िकेशन विफल हो जाता है।",
                ar = "عكس ترتيب سجل القيمة وسجل الكائن يسبب خطأ تحقق مباشر.",
                fa_af = "جابه‌جا نوشتن `v0` و `p0` باعث خطای بررسی‌کننده بایت‌کد می‌شود.",
                in_id = "Menukar posisi `v0` dan `p0` akan menggagalkan verifikasi."
            )
        ),
        SmaliInstruction(
            opcode = "sget-object",
            hexCode = "0x62",
            category = InstructionCategory.FIELDS,
            syntax = "sget-object vDest, LClass;->STATIC_FIELD:LType;",
            javaEquivalent = "PrintStream out = System.out;",
            kotlinEquivalent = "val model = Build.MODEL",
            purposeContent = localized(
                en = "Reads a static Object field from a class into vDest (no instance object needed).",
                bn = "কোনো অবজেক্ট ছাড়াই সরাসরি ক্লাসের `static` ফিল্ড (যেমন `Build.MODEL` বা `System.out`) পড়ে আনে।",
                ur = "بغیر کسی آبجیکٹ کے کلاس کا `static` آبجیکٹ فیلڈ `vDest` میں پڑھتا ہے۔",
                hi = "बिना किसी ऑब्जेक्ट रजिस्टर के सीधे क्लास का `static` ऑब्जेक्ट फ़ील्ड पढ़ता है।",
                ar = "قراءة حقل كائن ثابت `static` مباشرة من الفئة إلى `vDest` دون كائن.",
                fa_af = "خواندن مستقیم فیلد شیء `static` از کلاس درون `vDest` بدون نیاز به شیء.",
                in_id = "Membaca field objek `static` langsung dari kelas ke `vDest`."
            ),
            detailedExplanationContent = localized(
                en = "Sibling opcodes: `sget` (`I`), `sget-boolean` (`Z`), `sget-wide` (`J`/`D`), and writers `sput`, `sput-object`, `sput-boolean`.",
                bn = "এর অন্যান্য রূপ: পূর্ণসংখ্যার জন্য `sget`, বুলিয়ানের জন্য `sget-boolean`, আর মান লেখার জন্য `sput` এবং `sput-object`।",
                ur = "دیگر اقسام: انٹیجر کے لیے `sget`، بولین کے لیے `sget-boolean` اور لکھنے کے لیے `sput`/`sput-object`۔",
                hi = "अन्य रूप: पूर्णांक हेतु `sget`, बूलियन हेतु `sget-boolean` और लिखने हेतु `sput`/`sput-object`।",
                ar = "صيغها الشقيقة: `sget` و `sget-boolean` وللكتابة نستخدم `sput` و `sput-object`.",
                fa_af = "سایر نسخه‌ها: `sget` برای عدد صحیح، `sget-boolean` برای بولین و `sput`/`sput-object` برای نوشتن.",
                in_id = "Varian lain: `sget` (`I`), `sget-boolean` (`Z`), serta penulis `sput` & `sput-object`."
            ),
            smaliExample = "sget-object v0, Landroid/os/Build;->MODEL:Ljava/lang/String;",
            commonMistakesContent = localized(
                en = "Passing `p0` into `sget-object`—static fields take ONLY 1 register!",
                bn = "`sget`-এ ভুল করে `p0` অবজেক্ট রেজিস্টার দেবেন না—এতে মাত্র ১টি রেজিস্টার বসে!",
                ur = "`sget` میں `p0` نہ لکھیں—اسٹیٹک فیلڈ میں صرف ایک رجسٹر آتا ہے!",
                hi = "`sget` में `p0` न दें—स्टैटिक फ़ील्ड में केवल 1 रजिस्टर लगता है!",
                ar = "لا تمرر `p0` مع الحقول الثابتة؛ فهي تأخذ مسجلاً واحداً فقط!",
                fa_af = "در `sget` رجستر `p0` نگذارید؛ دستورات استاتیک فقط ۱ رجستر می‌گیرند!",
                in_id = "Jangan memasukkan `p0` pada `sget`; field statis hanya memakai 1 register!"
            )
        ),

        // OBJECTS & ARRAYS (2 here + more in ExtraData)
        SmaliInstruction(
            opcode = "new-instance",
            hexCode = "0x22",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "new-instance vAA, LPackage/ClassName;",
            javaEquivalent = "new StringBuilder() // step 1: allocate",
            kotlinEquivalent = "StringBuilder() // step 1: allocate",
            purposeContent = localized(
                en = "Allocates raw heap memory for a new object instance and places its reference in vAA.",
                bn = "নতুন অবজেক্টের জন্য হিপ মেমোরিতে জায়গা তৈরি করে `vAA` রেজিস্টারে রেফারেন্স রাখে।",
                ur = "نئے آبجیکٹ کے لیے میموری مختص کر کے اس کا ریفرنس `vAA` میں رکھتا ہے۔",
                hi = "नए ऑब्जेक्ट के लिए हीप मेमोरी आवंटित कर उसका रेफरेंस `vAA` में रखता है।",
                ar = "حجز مساحة ذاكرة لكائن جديد ووضع مرجعه في المسجل `vAA`.",
                fa_af = "تخصیص حافظه هیپ برای یک شیء جدید و قرار دادن ارجاع آن در `vAA`.",
                in_id = "Mengalokasikan memori heap untuk objek baru dan menaruhnya di `vAA`."
            ),
            detailedExplanationContent = localized(
                en = "MANDATORY PAIR: `new-instance` ONLY allocates blank bytes. You MUST call `invoke-direct {vAA, ...}, LClass;-><init>(...)V` before calling any methods on `vAA`!",
                bn = "বাধ্যতামূলক জোড়া: `new-instance` শুধু ফাঁকা জায়গা বানায়। এটি ব্যবহারের আগে অবশ্যই পরের লাইনে `invoke-direct` দিয়ে `<init>` কনস্ট্রাক্টর কল করতে হবে!",
                ur = "لازمی جوڑا: `new-instance` کے بعد آبجیکٹ استعمال کرنے سے پہلے `invoke-direct` کے ذریعے `<init>` چلانا فرض ہے!",
                hi = "अनिवार्य जोड़ा: `new-instance` के बाद उपयोग करने से पहले `invoke-direct` से `<init>` कॉल करना अनिवार्य है!",
                ar = "قاعدة حتمية: بعد `new-instance` يجب استدعاء الباني `<init>` عبر `invoke-direct` قبل استخدام الكائن!",
                fa_af = "قانون قطعی: پس از `new-instance` حتماً باید سازنده `<init>` با `invoke-direct` فراخوانی شود!",
                in_id = "Pasangan wajib: setelah `new-instance`, wajib memanggil `<init>` lewat `invoke-direct`!"
            ),
            smaliExample = "new-instance v0, Ljava/lang/StringBuilder;\ninvoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V",
            commonMistakesContent = localized(
                en = "Forgetting `invoke-direct <init>` leaves an uninitialized reference that ART rejects immediately.",
                bn = "`<init>` কনস্ট্রাক্টর কল করতে ভুলে গেলে ART ভেরিফায়ার সাথে সাথে কোডটি বাতিল করে দেয়।",
                ur = "`<init>` کال کرنا بھول جانے سے ART ویریفائر کوڈ مسترد کر دیتا ہے۔",
                hi = "`<init>` कॉल करना भूल जाने पर ART वेरिफ़ायर तुरंत कोड अस्वीकार कर देता है।",
                ar = "نسيان استدعاء `<init>` يجعل المرجع غير مهيأ ويرفضه محرك ART فوراً.",
                fa_af = "فراموش کردن فراخوانی `<init>` باعث رد شدن فوری کد توسط ART می‌شود.",
                in_id = "Lupa memanggil `<init>` membuat referensi ditolak langsung oleh verifier ART."
            )
        ),
        SmaliInstruction(
            opcode = "check-cast",
            hexCode = "0x1f",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "check-cast vAA, LPackage/TargetType;",
            javaEquivalent = "Button btn = (Button) view;",
            kotlinEquivalent = "val btn = view as Button",
            purposeContent = localized(
                en = "Verifies at runtime that object in vAA can be safely cast to LTargetType; (or is null).",
                bn = "`vAA` রেজিস্টারে থাকা অবজেক্টটি আসলেই নির্দিষ্ট ক্লাসের কি না তা যাচাই করে টাইপ কাস্ট করে।",
                ur = "رن ٹائم پر تصدیق کرتا ہے کہ `vAA` کا آبجیکٹ مطلوبہ کلاس میں کاسٹ ہو سکتا ہے یا نہیں۔",
                hi = "रनटाइम पर जांचता है कि `vAA` में मौजूद ऑब्जेक्ट को लक्ष्य क्लास में बदला जा सकता है या नहीं।",
                ar = "التحقق أثناء التشغيل من إمكانية تحويل الكائن في `vAA` إلى النوع المطلوب.",
                fa_af = "بررسی در زمان اجرا که آیا شیء درون `vAA` به نوع هدف قابل تبدیل است یا خیر.",
                in_id = "Memastikan saat runtime bahwa objek di `vAA` dapat di-cast ke tipe tujuan."
            ),
            detailedExplanationContent = localized(
                en = "Seen right after `findViewById` or `List.get(i)` (because generics erase to `Ljava/lang/Object;`). Throws `ClassCastException` on mismatch.",
                bn = "`findViewById` বা `List.get()` করার পর এটি সবসময় দেখা যায়। টাইপ না মিললে `ClassCastException` এরর দেয় (কিন্তু `null` হলে পাস করে যায়)।",
                ur = "`findViewById` یا `List.get()` کے فوراً بعد نظر آتا ہے۔ قسم نہ ملنے پر `ClassCastException` دیتا ہے۔",
                hi = "`findViewById` या `List.get()` के ठीक बाद दिखता है। टाइप न मिलने पर `ClassCastException` फेंकता है।",
                ar = "تظهر دائماً بعد `findViewById` أو القوائم العامة، وترمي `ClassCastException` عند عدم التطابق.",
                fa_af = "معمولاً بلافاصله پس از `findViewById` یا `List.get()` دیده می‌شود و در صورت عدم تطابق خطا می‌دهد.",
                in_id = "Selalu muncul setelah `findViewById` atau `List.get()`; melempar `ClassCastException` jika salah tipe."
            ),
            smaliExample = "invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;\nmove-result-object v1\ncheck-cast v1, Ljava/lang/String;",
            commonMistakesContent = localized(
                en = "Attempting to `check-cast` primitive ints—primitives use `int-to-float` / `int-to-long` instead.",
                bn = "সাধারণ সংখ্যার (`int`, `float`) ওপর `check-cast` চলে না—সেখানে `int-to-float` ব্যবহার করুন।",
                ur = "سادہ نمبروں پر `check-cast` کام نہیں کرتا، وہاں `int-to-float` لکھیں۔",
                hi = "प्रिमिटिव संख्याओं पर `check-cast` नहीं चलता—वहाँ `int-to-float` लिखें।",
                ar = "لا تُستخدم مع الأنواع الأولية؛ استخدم `int-to-float` بدلاً منها.",
                fa_af = "روی اعداد اولیه کار نمی‌کند؛ برای آن‌ها از `int-to-float` استفاده کنید.",
                in_id = "Tidak berlaku untuk primitif; gunakan `int-to-float` atau `int-to-long`."
            )
        )
    )

    val instructions: List<SmaliInstruction>
        get() = coreInstructions + InstructionExtraData.extraInstructions

    fun search(
        query: String,
        category: InstructionCategory?,
        language: AppLanguage = AppLanguage.ENGLISH
    ): List<SmaliInstruction> {
        val q = query.trim().lowercase()
        return instructions.filter { item ->
            val matchesCategory = category == null || item.category == category
            val matchesQuery = q.isEmpty() ||
                item.opcode.lowercase().contains(q) ||
                item.hexCode.lowercase().contains(q) ||
                item.syntax.lowercase().contains(q) ||
                item.javaEquivalent.lowercase().contains(q) ||
                item.kotlinEquivalent.lowercase().contains(q) ||
                item.purposeContent.get(language).lowercase().contains(q) ||
                item.purposeContent.en.lowercase().contains(q) ||
                item.detailedExplanationContent.get(language).lowercase().contains(q)
            matchesCategory && matchesQuery
        }
    }
}
