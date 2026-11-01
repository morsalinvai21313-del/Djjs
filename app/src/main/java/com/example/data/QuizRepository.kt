package com.example.data

import com.example.model.QuizDifficulty
import com.example.model.QuizQuestion
import com.example.model.QuizType
import com.example.model.localized

object QuizRepository {
    val questions = listOf(
        QuizQuestion(
            id = "q_01",
            lessonId = "smali_03",
            questionContent = localized(
                en = "In a non-static (instance) Smali method, what is ALWAYS stored inside register p0 at entry?",
                bn = "একটি নন-স্ট্যাটিক (Instance) স্মালি মেথডের শুরুতে p0 রেজিস্টারে সবসময় কী থাকে?",
                ur = "ایک غیر اسٹیٹک (Instance) سمالی میتھڈ کے آغاز پر p0 رجسٹر میں ہمیشہ کیا ہوتا ہے؟",
                hi = "एक गैर-स्टैटिक (Instance) स्माली मेथड की शुरुआत में p0 रजिस्टर में हमेशा क्या रहता है?",
                ar = "في دالة سمالي غير الثابتة (Instance)، ماذا يُخزن دائماً في المسجل p0 عند البداية؟",
                fa_af = "در یک متد غیر استاتیک (Instance) سمالی، در ابتدای ورود همیشه چه چیزی درون رجستر p0 قرار دارد؟",
                in_id = "Pada metode Smali non-statis (instance), apa yang SELALU disimpan di register p0 saat masuk?"
            ),
            optionsContent = listOf(
                localized("The first method argument", "মেথডের প্রথম ইনপুট আর্গুমেন্ট", "پہلا ان پٹ آرگومنٹ", "पहला इनपुट पैरामीटर", "المعامل الأول للدالة", "اولین آرگومان ورودی", "Argumen pertama metode"),
                localized("The 'this' current object reference", "বর্তমান ক্লাসের 'this' অবজেক্ট রেফারেন্স", "موجودہ کلاس کا 'this' آبجیکٹ ریفرنس", "वर्तमान ऑब्जेक्ट का 'this' रेफरेंस", "مرجع الكائن الحالي 'this'", "اشاره‌گر شیء جاری 'this'", "Referensi objek saat ini 'this'"),
                localized("Integer 0", "পূর্ণসংখ্যা 0", "عدد 0", "पूर्णांक 0", "الرقم الصفر 0", "عدد صفر 0", "Angka bulat 0"),
                localized("The method return value", "মেথডের রিটার্ন ফলাফল", "میتھڈ کی واپسی قیمت", "मेथड का रिटर्न मान", "قيمة إرجاع الدالة", "مقدار بازگشتی متد", "Nilai return metode")
            ),
            correctIndex = 1,
            explanationContent = localized(
                en = "In instance methods, Dalvik passes the implicit `this` object in `p0`, and explicit parameters start at `p1`.",
                bn = "Instance মেথডে ডালভিক সবসময় বর্তমান অবজেক্ট (`this`)-কে `p0`-তে পাঠায়, আর মেথডের প্রথম প্যারামিটার শুরু হয় `p1` থেকে।",
                ur = "انسٹانس میتھڈ میں ڈالوک ہمیشہ `this` آبجیکٹ کو `p0` میں رکھتا ہے اور پیرامیٹرز `p1` سے شروع ہوتے ہیں۔",
                hi = "इंस्टेंस मेथड में डाल्विक हमेशा `this` ऑब्जेक्ट को `p0` में भेजता है और पैरामीटर `p1` से शुरू होते हैं।",
                ar = "في دوال الكائنات يمرر دالفيك مؤشر `this` في `p0` وتبدأ المعاملات من `p1`.",
                fa_af = "در متدهای شیء، دالویک همیشه اشاره‌گر `this` را در `p0` قرار می‌دهد و پارامترها از `p1` شروع می‌شوند.",
                in_id = "Pada metode instance, Dalvik menaruh `this` di `p0` dan parameter dimulai dari `p1`."
            ),
            whyIncorrectContent = localized(
                en = "`p0` only holds the first explicit argument when the method has the `static` modifier.",
                bn = "কেবলমাত্র মেথডে `static` লেখা থাকলেই `p0`-তে প্রথম ইনপুট আর্গুমেন্ট বসে।",
                ur = "`p0` صرف `static` میتھڈ میں پہلا آرگومنٹ رکھتا ہے۔",
                hi = "`p0` केवल `static` मेथड में ही पहला आर्ग्यूमेंट रखता है।",
                ar = "يحمل `p0` المعامل الأول فقط عندما تكون الدالة ثابتة `static`.",
                fa_af = "رجستر `p0` فقط در متدهای `static` حاوی اولین آرگومان ورودی است.",
                in_id = "`p0` hanya berisi argumen pertama jika metode memiliki modifier `static`."
            ),
            difficulty = QuizDifficulty.EASY,
            type = QuizType.MULTIPLE_CHOICE
        ),

        QuizQuestion(
            id = "q_02",
            lessonId = "smali_04",
            questionContent = localized(
                en = "Which Smali type descriptor pair represents `boolean` and 64-bit `long`?",
                bn = "স্মালিতে `boolean` এবং ৬৪-বিট `long` বোঝাতে কোন দুটি অক্ষর ব্যবহৃত হয়?",
                ur = "سمالی میں `boolean` اور 64-بٹ `long` کے لیے کون سے دو حروف استعمال ہوتے ہیں؟",
                hi = "स्माली में `boolean` और 64-बिट `long` को कौन-सा अक्षर जोड़ा दर्शाता है?",
                ar = "أي زوج رموز يمثل النوع المنطقي `boolean` والعدد الطويل `long` في سمالي؟",
                fa_af = "کدام جفت حرف در سمالی نشان‌دهنده `boolean` و عدد ۶۴ بیتی `long` است؟",
                in_id = "Pasangan simbol manakah yang mewakili `boolean` dan `long` 64-bit di Smali?"
            ),
            optionsContent = listOf(
                localized("B and L", "B এবং L", "B اور L", "B और L", "B و L", "B و L", "B dan L"),
                localized("Z and J", "Z এবং J", "Z اور J", "Z और J", "Z و J", "Z و J", "Z dan J"),
                localized("B and J", "B এবং J", "B اور J", "B और J", "B و J", "B و J", "B dan J"),
                localized("Z and L", "Z এবং L", "Z اور L", "Z और L", "Z و L", "Z و L", "Z dan L")
            ),
            correctIndex = 1,
            explanationContent = localized(
                en = "Because `B` is `byte` and `L` starts class descriptors (`Ljava/lang/Object;`), Dalvik uses `Z` for boolean and `J` for 64-bit long!",
                bn = "যেহেতু `B` মানে `byte` এবং `L` দিয়ে ক্লাসের নাম শুরু হয়, তাই স্মালিতে বুলিয়ানের জন্য `Z` এবং ৬৪-বিট `long`-এর জন্য `J` ব্যবহৃত হয়!",
                ur = "`B` بائٹ کے لیے اور `L` کلاس کے لیے ہے، اس لیے بولین کے لیے `Z` اور لانگ کے لیے `J` آتا ہے!",
                hi = "`B` बाइट के लिए और `L` क्लास के लिए है, इसलिए बूलियन हेतु `Z` और `long` हेतु `J` प्रयुक्त होता है!",
                ar = "حرف `B` للبايت و `L` للفئات، لذا يُستخدم `Z` للمنطقي و `J` للعدد الطويل!",
                fa_af = "حرف `B` برای بایت و `L` برای کلاس است، بنابراین `Z` برای بولین و `J` برای `long` به کار می‌رود!",
                in_id = "Karena `B` dipakai `byte` dan `L` dipakai kelas, Dalvik memakai `Z` (boolean) dan `J` (long)!"
            ),
            whyIncorrectContent = localized(
                en = "Confusing `B` with boolean or `L` with long causes instant signature mismatches.",
                bn = "`B`-কে বুলিয়ান বা `L`-কে long ভাবলে সিগনেচার ভুল হয়ে কোড ক্র্যাশ করবে।",
                ur = "`B` کو بولین یا `L` کو لانگ سمجھنا عام غلطی ہے۔",
                hi = "`B` को बूलियन या `L` को long समझना सिग्नेचर त्रुटि पैदा करता है।",
                ar = "الخلط بين `B` و `Z` أو `L` و `J` يسبب خطأ في التوقيع.",
                fa_af = "اشتباه گرفتن `B` با بولین یا `L` با long باعث خطای امضای متد می‌شود.",
                in_id = "Tertukar `B` dengan boolean atau `L` dengan long merusak tanda tangan metode."
            ),
            difficulty = QuizDifficulty.EASY,
            type = QuizType.MULTIPLE_CHOICE
        ),

        QuizQuestion(
            id = "q_03",
            lessonId = "smali_06",
            questionContent = localized(
                en = "Spot the assembly error in the following Smali method:",
                bn = "নিচের স্মালি মেথডটিতে কী ভুল রয়েছে তা খুঁজে বের করুন:",
                ur = "درج ذیل سمالی میتھڈ میں غلطی تلاش کریں:",
                hi = "नीचे दिए गए स्माली मेथड में त्रुटि पहचानें:",
                ar = "اكتشف الخطأ البرمجي في دالة سمالي التالية:",
                fa_af = "خطای موجود در متد سمالی زیر را پیدا کنید:",
                in_id = "Temukan kesalahan pada metode Smali berikut:"
            ),
            codeSnippet = """.method public static getLimit()I
    .locals 1
    const/4 v0, 0x10
    return v0
.end method""",
            optionsContent = listOf(
                localized("0x10 (16) exceeds const/4's range (-8 to +7)", "0x10 (১৬) সংখ্যাটি const/4-এর সীমা (-8 থেকে +7) ছাড়িয়ে গেছে", "0x10 عدد const/4 کی حد (-8 سے +7) سے بڑا ہے", "0x10 (16) मान const/4 की सीमा (-8 से +7) से बाहर है", "القيمة 0x10 تتجاوز نطاق const/4 (-8 إلى +7)", "مقدار 0x10 خارج از بازه مجاز const/4 (-8 تا +7) است", "Nilai 0x10 (16) melebihi batas const/4 (-8 s/d +7)"),
                localized("return v0 should be return-object v0", "return v0-এর বদলে return-object v0 হবে", "return-object v0 ہونا چاہیے", "return-object v0 होना चाहिए", "يجب استخدام return-object", "باید return-object نوشته شود", "Seharusnya return-object v0"),
                localized(".locals must be 0", ".locals অবশ্যই 0 হতে হবে", ".locals کو 0 ہونا چاہیے", ".locals 0 होना चाहिए", "يجب أن يكون .locals صفراً", ".locals باید ۰ باشد", ".locals harus 0"),
                localized("Static methods cannot return I", "Static মেথড I রিটার্ন করতে পারে না", "اسٹیٹک میتھڈ I واپس نہیں کر سکتا", "स्टैटिक मेथड I नहीं लौटा सकता", "الدالة الثابتة لا ترجع I", "متد استاتیک نمی‌تواند I برگرداند", "Metode static tidak bisa return I")
            ),
            correctIndex = 0,
            explanationContent = localized(
                en = "`const/4` encodes a 4-bit signed nibble (`-8` to `+7`). Hex `0x10` is decimal `16`, which requires `const/16 v0, 0x10`!",
                bn = "`const/4` কেবল `-8` থেকে `+7` পর্যন্ত সংখ্যা নিতে পারে। `0x10` মানে দশমিকে `16`, তাই এখানে `const/16 v0, 0x10` লিখতে হবে!",
                ur = "`const/4` صرف `-8` سے `+7` تک کام کرتا ہے؛ `0x10` (16) کے لیے `const/16` ضروری ہے!",
                hi = "`const/4` केवल `-8` से `+7` तक लेता है; `0x10` (16) के लिए `const/16` आवश्यक है!",
                ar = "تقبل `const/4` القيم من `-8` إلى `+7` فقط؛ القيمة `0x10` تتطلب `const/16`!",
                fa_af = "`const/4` فقط اعداد `-8` تا `+7` را می‌پذیرد؛ برای `0x10` باید از `const/16` استفاده کرد!",
                in_id = "`const/4` hanya memuat `-8` s/d `+7`; nilai `0x10` (16) memerlukan `const/16`!"
            ),
            whyIncorrectContent = localized(
                en = "Integer return `return v0` and `.locals 1` are both completely valid.",
                bn = "পূর্ণসংখ্যার জন্য `return v0` এবং `.locals 1` সম্পূর্ণ সঠিক আছে।",
                ur = "`return v0` اور `.locals 1` بالکل درست ہیں۔",
                hi = "`return v0` और `.locals 1` दोनों बिल्कुल सही हैं।",
                ar = "كل من `return v0` و `.locals 1` صحيح تماماً.",
                fa_af = "دستور `return v0` و `.locals 1` هر دو کاملاً درست هستند.",
                in_id = "`return v0` dan `.locals 1` keduanya sudah benar."
            ),
            difficulty = QuizDifficulty.EASY,
            type = QuizType.FIND_ERROR
        ),

        QuizQuestion(
            id = "q_04",
            lessonId = "smali_09",
            questionContent = localized(
                en = "What integer does `calc(0)` return when executed?",
                bn = "`calc(0)` কল করলে মেথডটি কত সংখ্যা রিটার্ন করবে?",
                ur = "`calc(0)` چلانے پر میتھڈ کیا عدد واپس کرے گا؟",
                hi = "`calc(0)` चलाने पर यह मेथड कौन-सा पूर्णांक लौटाएगा?",
                ar = "ما العدد الذي ستعيده الدالة عند استدعاء `calc(0)`؟",
                fa_af = "با فراخوانی `calc(0)` این متد چه عددی برمی‌گرداند؟",
                in_id = "Angka berapa yang dikembalikan saat `calc(0)` dijalankan?"
            ),
            codeSnippet = """.method public static calc(I)I
    .locals 1
    if-eqz p0, :non_zero
    const/16 v0, 0x64
    return v0
    :non_zero
    const/4 v0, 0x1
    return v0
.end method""",
            optionsContent = listOf(
                localized("1", "১ (1)", "1", "1", "1", "۱ (1)", "1"),
                localized("100", "১০০ (100)", "100", "100", "100", "۱۰۰ (100)", "100"),
                localized("64", "৬৪ (64)", "64", "64", "64", "۶۴ (64)", "64"),
                localized("0", "০ (0)", "0", "0", "0", "۰ (0)", "0")
            ),
            correctIndex = 0,
            explanationContent = localized(
                en = "WAIT—look closely! `p0` is `0`, so `if-eqz p0, :non_zero` IS TRUE (`p0 == 0`)! Execution jumps straight to `:non_zero` and returns `0x1` (`1`)!",
                bn = "খেয়াল করুন! ইনপুট `p0 = 0`, তাই `if-eqz p0, :non_zero` শর্তটি সত্য (`p0 == 0`)! ফলে কোড সরাসরি লাফ দিয়ে `:non_zero` লেবেলে চলে যাবে এবং `0x1` (`1`) রিটার্ন করবে!",
                ur = "غور کریں! `p0 = 0` ہے اس لیے `if-eqz` سچ ہو کر سیدھا `:non_zero` لیبل پر کود جائے گا اور `1` واپس کرے گا!",
                hi = "ध्यान से देखें! `p0 = 0` है, इसलिए `if-eqz` सत्य होकर सीधे `:non_zero` लेबल पर कूद जाएगा और `1` लौटाएगा!",
                ar = "انتبه للاسم المخادع! بما أن `p0 = 0` فإن شرط `if-eqz` يتحقق ويقفز إلى `:non_zero` مرجعاً `1`!",
                fa_af = "دقت کنید! چون `p0 = 0` است شرط `if-eqz` برقرار شده، به `:non_zero` می‌پرد و عدد `1` برمی‌گرداند!",
                in_id = "Perhatikan! Karena `p0 = 0`, kondisi `if-eqz` terpenuhi dan melompat ke `:non_zero`, mengembalikan `1`!"
            ),
            whyIncorrectContent = localized(
                en = "Never trust label names blindly—always evaluate the actual opcode (`if-eqz` jumps when value == 0).",
                bn = "লেবেলের নাম দেখে বিভ্রান্ত হবেন না—সবসময় অপকোডের কাজ দেখুন (`if-eqz` মানে শূন্য হলেই জাম্প!)।",
                ur = "لیبل کے نام سے دھوکہ نہ کھائیں، ہمیشہ اوپ کوڈ (`if-eqz`) کا اصول دیکھیں۔",
                hi = "लेबल के नाम पर अंधविश्वास न करें—हमेशा ऑपकोड (`if-eqz` = शून्य पर जंप) जांचें।",
                ar = "لا تنخدع باسم العلامة؛ افحص دائماً شرط التعليمة الفعلي (`if-eqz`).",
                fa_af = "فریب نام برچسب را نخورید؛ همیشه منطق واقعی آپ‌کد (`if-eqz`) را بررسی کنید.",
                in_id = "Jangan tertipu nama label; selalu evaluasi logika opcode (`if-eqz` = lompat saat 0)."
            ),
            difficulty = QuizDifficulty.MEDIUM,
            type = QuizType.PREDICT_OUTPUT
        ),

        QuizQuestion(
            id = "q_05",
            lessonId = "smali_07",
            questionContent = localized(
                en = "Which instruction MUST immediately follow `invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;` to store the output in v1?",
                bn = "`invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;`-এর ফলাফল `v1`-এ ধরতে ঠিক পরের লাইনে কী লিখতে হবে?",
                ur = "`toString()Ljava/lang/String;` کا نتیجہ `v1` میں رکھنے کے لیے فوراً اگلی لائن کیا ہونی چاہیے؟",
                hi = "`toString()Ljava/lang/String;` का परिणाम `v1` में पकड़ने हेतु ठीक अगली लाइन कौन-सी होनी चाहिए?",
                ar = "ما التعليمة الواجب كتابتها مباشرة بعد `toString()Ljava/lang/String;` لحفظ الناتج في v1؟",
                fa_af = "بلافاصله پس از `toString()Ljava/lang/String;` برای ذخیره خروجی در v1 چه دستوری باید نوشت؟",
                in_id = "Instruksi apa yang WAJIB tepat mengikuti `toString()Ljava/lang/String;` untuk menyimpan hasil ke v1?"
            ),
            optionsContent = listOf(
                localized("move-result v1", "move-result v1", "move-result v1", "move-result v1", "move-result v1", "move-result v1", "move-result v1"),
                localized("move-result-object v1", "move-result-object v1", "move-result-object v1", "move-result-object v1", "move-result-object v1", "move-result-object v1", "move-result-object v1"),
                localized("move-object v1, v0", "move-object v1, v0", "move-object v1, v0", "move-object v1, v0", "move-object v1, v0", "move-object v1, v0", "move-object v1, v0"),
                localized("return-object v1", "return-object v1", "return-object v1", "return-object v1", "return-object v1", "return-object v1", "return-object v1")
            ),
            correctIndex = 1,
            explanationContent = localized(
                en = "`Ljava/lang/String;` is an object reference (ends in `;`), so `move-result-object v1` is mandatory.",
                bn = "`Ljava/lang/String;` একটি অবজেক্ট রেফারেন্স (শেষে `;` আছে), তাই অবশ্যই `move-result-object v1` লিখতে হবে।",
                ur = "`Ljava/lang/String;` ایک آبجیکٹ ہے اس لیے `move-result-object v1` لازمی ہے۔",
                hi = "`Ljava/lang/String;` एक ऑब्जेक्ट रेफरेंस है, इसलिए `move-result-object v1` अनिवार्य है।",
                ar = "`Ljava/lang/String;` مرجع كائن ينتهي بـ `;` لذا يلزم `move-result-object v1`.",
                fa_af = "`Ljava/lang/String;` یک شیء است، بنابراین استفاده از `move-result-object v1` الزامی است.",
                in_id = "`Ljava/lang/String;` adalah referensi objek, sehingga wajib `move-result-object v1`."
            ),
            whyIncorrectContent = localized(
                en = "`move-result` only works for 32-bit primitives (`I, Z, F, C, B, S`), causing `VerifyError` on Strings.",
                bn = "`move-result` শুধু সাধারণ সংখ্যার (`I, Z, F`) জন্য কাজ করে; স্ট্রিংয়ে দিলে `VerifyError` হবে।",
                ur = "`move-result` صرف سادہ نمبروں کے لیے ہے، اسٹرنگ پر `VerifyError` دیتا ہے۔",
                hi = "`move-result` केवल प्रिमिटिव मानों के लिए है; स्ट्रिंग पर `VerifyError` देगा।",
                ar = "`move-result` للأنواع الأولية فقط وتسبب فشل التحقق مع النصوص.",
                fa_af = "`move-result` فقط برای مقادیر اولیه است و روی رشته باعث `VerifyError` می‌شود.",
                in_id = "`move-result` khusus primitif 32-bit dan memicu `VerifyError` pada String."
            ),
            difficulty = QuizDifficulty.MEDIUM,
            type = QuizType.MULTIPLE_CHOICE
        ),

        QuizQuestion(
            id = "q_06",
            lessonId = "smali_03",
            questionContent = localized(
                en = "In `.method public send(JI)V`, which register holds the `I` (int) parameter?",
                bn = "`.method public send(JI)V` মেথডে দ্বিতীয় প্যারামিটার `I` (int) কোন রেজিস্টারে বসবে?",
                ur = "`.method public send(JI)V` میں دوسرا پیرامیٹر `I` (int) کس رجسٹر میں ہو گا؟",
                hi = "`.method public send(JI)V` में दूसरा पैरामीटर `I` (int) किस रजिस्टर में होगा?",
                ar = "في الدالة `.method public send(JI)V`، أي مسجل يحمل المعامل الصحيح `I`؟",
                fa_af = "در متد `.method public send(JI)V`، پارامتر `I` (عدد صحیح) در کدام رجستر قرار می‌گیرد؟",
                in_id = "Pada `.method public send(JI)V`, register mana yang memegang parameter `I`?"
            ),
            optionsContent = listOf(
                localized("p1", "p1", "p1", "p1", "p1", "p1", "p1"),
                localized("p2", "p2", "p2", "p2", "p2", "p2", "p2"),
                localized("p3", "p3", "p3", "p3", "p3", "p3", "p3"),
                localized("v0", "v0", "v0", "v0", "v0", "v0", "v0")
            ),
            correctIndex = 2,
            explanationContent = localized(
                en = "Count the slots carefully: `p0` = `this` (instance method), `(p1, p2)` = 64-bit `J` (`long` takes 2 registers!), so `I` lands in `p3`!",
                bn = "স্লটগুলো গুনুন: `p0` = `this` অবজেক্ট, আর ৬৪-বিট `J` (`long`) একা নিয়ে নেয় `p1` ও `p2` দুটি স্লট! তাই পরের `I` প্যারামিটারটি বসে `p3`-তে!",
                ur = "سلاٹس گنیں: `p0` = `this`، اور 64-بٹ `J` اکیلا `p1` اور `p2` لے لیتا ہے، لہٰذا `I` سیدھا `p3` میں آتا ہے!",
                hi = "स्लॉट गिनें: `p0` = `this`, और 64-बिट `J` अकेले `p1` व `p2` ले लेता है, इसलिए `I` सीधे `p3` में आता है!",
                ar = "احسب الخانات: `p0` للكائن `this`، والنوع `J` يشغل `p1` و `p2` معاً، فيقع `I` في `p3`!",
                fa_af = "خانه‌ها را بشمارید: `p0` برای `this` است و `J` دو خانه `p1` و `p2` را می‌گیرد، پس `I` در `p3` می‌نشیند!",
                in_id = "Hitung slotnya: `p0` = `this`, `J` 64-bit memakan `p1` & `p2`, sehingga `I` berada di `p3`!"
            ),
            whyIncorrectContent = localized(
                en = "Reading `p2` would read the upper half of the 64-bit `long` and trigger an instant `VerifyError`!",
                bn = "`p2` পড়ার চেষ্টা করলে `long`-এর অর্ধেক অংশ পড়া হবে এবং সাথে সাথে `VerifyError` ক্র্যাশ ঘটবে!",
                ur = "`p2` پڑھنے کی کوشش سے `long` کا آدھا حصہ آئے گا اور `VerifyError` ہو گا!",
                hi = "`p2` पढ़ने से 64-बिट `long` का आधा भाग पढ़ लिया जाएगा जिससे `VerifyError` आएगा!",
                ar = "قراءة `p2` تقتطع نصف العدد الطويل وتسبب خطأ فحص فوري!",
                fa_af = "خواندن `p2` نیمی از عدد ۶۴ بیتی را می‌خواند و فوراً خطای `VerifyError` می‌دهد!",
                in_id = "Membaca `p2` berarti menyentuh paruh kedua `long` dan langsung memicu `VerifyError`!"
            ),
            difficulty = QuizDifficulty.HARD,
            type = QuizType.REGISTER_TRACE
        ),

        QuizQuestion(
            id = "q_07",
            lessonId = "smali_13",
            questionContent = localized(
                en = "Which `invoke-*` opcode MUST be used to call a constructor `<init>` or a `private` method?",
                bn = "কনস্ট্রাক্টর `<init>` অথবা ক্লাসের `private` মেথড কল করতে কোন `invoke-*` অপকোড ব্যবহার করা বাধ্যতামূলক?",
                ur = "کنسٹرکٹر `<init>` یا `private` میتھڈ کال کرنے کے لیے کون سا `invoke-*` اوپ کوڈ لازمی ہے؟",
                hi = "कंस्ट्रक्टर `<init>` या `private` मेथड कॉल करने के लिए कौन-सा `invoke-*` ऑपकोड अनिवार्य है?",
                ar = "أي تعليمة `invoke-*` يجب استخدامها لاستدعاء الباني `<init>` أو دالة `private`؟",
                fa_af = "برای فراخوانی سازنده `<init>` یا یک متد `private` کدام دستور `invoke-*` الزامی است؟",
                in_id = "Opcode `invoke-*` mana yang WAJIB dipakai untuk memanggil `<init>` atau metode `private`?"
            ),
            optionsContent = listOf(
                localized("invoke-virtual", "invoke-virtual", "invoke-virtual", "invoke-virtual", "invoke-virtual", "invoke-virtual", "invoke-virtual"),
                localized("invoke-static", "invoke-static", "invoke-static", "invoke-static", "invoke-static", "invoke-static", "invoke-static"),
                localized("invoke-direct", "invoke-direct", "invoke-direct", "invoke-direct", "invoke-direct", "invoke-direct", "invoke-direct"),
                localized("invoke-interface", "invoke-interface", "invoke-interface", "invoke-interface", "invoke-interface", "invoke-interface", "invoke-interface")
            ),
            correctIndex = 2,
            explanationContent = localized(
                en = "Constructors (`<init>`) and `private` instance methods cannot be overridden dynamically, so Dalvik always dispatches them via `invoke-direct`.",
                bn = "কনস্ট্রাক্টর (`<init>`) এবং `private` মেথড কখনো সাবক্লাসে ওভাররাইড হয় না, তাই ডালভিক সবসময় `invoke-direct` ব্যবহার করে।",
                ur = "کنسٹرکٹرز اور `private` میتھڈز اوور رائیڈ نہیں ہوتے، اس لیے ان کے لیے ہمیشہ `invoke-direct` استعمال ہوتا ہے۔",
                hi = "कंस्ट्रक्टर (`<init>`) और `private` मेथड ओवरराइड नहीं होते, इसलिए उनके लिए हमेशा `invoke-direct` लगता है।",
                ar = "البانيات والدوال الخاصة غير قابلة للتجاوز، لذا تُستدعى دائماً عبر `invoke-direct`.",
                fa_af = "سازنده‌ها و متدهای خصوصی قابل بازنویسی نیستند، بنابراین همیشه با `invoke-direct` اجرا می‌شوند.",
                in_id = "Konstruktor (`<init>`) dan metode `private` tidak bisa di-override, sehingga wajib `invoke-direct`."
            ),
            whyIncorrectContent = localized(
                en = "Using `invoke-virtual` on `<init>` fails bytecode verification immediately.",
                bn = "`<init>`-এ `invoke-virtual` দিলে ART ভেরিফায়ার সাথে সাথে কোড বাতিল করে দেয়।",
                ur = "`<init>` پر `invoke-virtual` لگانے سے ویریفائر کوڈ مسترد کر دیتا ہے۔",
                hi = "`<init>` पर `invoke-virtual` लगाने से बाइटकोड वेरिफ़िकेशन विफल हो जाता है।",
                ar = "استخدام `invoke-virtual` مع `<init>` يفشل فحص البايت كود فوراً.",
                fa_af = "استفاده از `invoke-virtual` برای `<init>` باعث رد شدن فوری کد می‌شود.",
                in_id = "Memakai `invoke-virtual` pada `<init>` langsung ditolak oleh verifier."
            ),
            difficulty = QuizDifficulty.MEDIUM,
            type = QuizType.MULTIPLE_CHOICE
        ),

        QuizQuestion(
            id = "q_08",
            lessonId = "smali_16",
            questionContent = localized(
                en = "Why will Android ART reject this Smali method with a `VerifyError`?",
                bn = "কেন অ্যান্ড্রয়েড ART এই স্মালি মেথডটিকে `VerifyError` দেখিয়ে বাতিল করে দেবে?",
                ur = "اینڈرائیڈ ART اس سمالی میتھڈ کو `VerifyError` دے کر کیوں مسترد کر دے گا؟",
                hi = "एंड्रॉइड ART इस स्माली मेथड को `VerifyError` के साथ क्यों अस्वीकार कर देगा?",
                ar = "لماذا سيرفض محرك أندرويد ART هذه الدالة بخطأ `VerifyError`؟",
                fa_af = "چرا موتور ART اندروید این متد سمالی را با خطای `VerifyError` رد می‌کند؟",
                in_id = "Mengapa Android ART menolak metode Smali ini dengan `VerifyError`?"
            ),
            codeSnippet = """.method public static sumThree()I
    .locals 2
    const/4 v0, 0x2
    const/4 v1, 0x3
    add-int v2, v0, v1
    return v2
.end method""",
            optionsContent = listOf(
                localized("v2 is out of bounds because .locals 2 only allocates v0 and v1", ".locals 2 কেবল v0 ও v1 বরাদ্দ করে—তাই v2 সীমার বাইরে!", ".locals 2 صرف v0 اور v1 دیتا ہے، اس لیے v2 حد سے باہر ہے!", ".locals 2 केवल v0 और v1 देता है—इसलिए v2 सीमा से बाहर है!", "المسجل v2 خارج النطاق لأن .locals 2 تحجز v0 و v1 فقط!", ".locals 2 فقط v0 و v1 را می‌سازد و v2 خارج از محدوده است!", "v2 di luar batas karena .locals 2 hanya menyediakan v0 & v1!"),
                localized("add-int cannot add v0 and v1", "add-int দিয়ে v0 ও v1 যোগ করা যায় না", "add-int سے جمع نہیں ہو سکتا", "add-int से v0 व v1 नहीं जुड़ सकते", "لا يمكن جمع v0 و v1", "نمی‌توان v0 و v1 را با add-int جمع کرد", "add-int tidak bisa menjumlahkan v0 & v1"),
                localized("const/4 cannot load 0x2 or 0x3", "const/4 দিয়ে 0x2 বা 0x3 লোড হয় না", "const/4 سے 2 لوڈ نہیں ہوتا", "const/4 से 2 लोड नहीं होता", "لا تقبل const/4 الرقم 2", "با const/4 عدد ۲ بارگذاری نمی‌شود", "const/4 tidak bisa memuat 0x2"),
                localized("Method name must start with get", "মেথডের নাম get দিয়ে শুরু হতে হবে", "نام get سے شروع ہونا چاہیے", "नाम get से शुरू होना चाहिए", "يجب أن يبدأ الاسم بـ get", "نام متد باید با get شروع شود", "Nama metode harus diawali get")
            ),
            correctIndex = 0,
            explanationContent = localized(
                en = "`.locals 2` allocates 0-indexed registers `v0` and `v1`. Writing to `v2` in a 0-argument static method accesses unallocated memory! Fix: `.locals 3` (or `add-int v0, v0, v1`).",
                bn = "`.locals 2` মানে ০ থেকে শুরু করে মোট ২টি রেজিস্টার (`v0` এবং `v1`)। এখানে কোনো প্যারামিটার নেই, তাই `v2` ব্যবহার করলে সীমা লঙ্ঘন হয়! সমাধান: `.locals 3` দেওয়া।",
                ur = "`.locals 2` صرف `v0` اور `v1` بناتا ہے؛ `v2` استعمال کرنے کے لیے `.locals 3` ضروری ہے!",
                hi = "`.locals 2` केवल `v0` और `v1` आवंटित करता है; `v2` के लिए `.locals 3` करना आवश्यक है!",
                ar = "تحجز `.locals 2` المسجلين `v0` و `v1` فقط؛ لاستخدام `v2` يجب رفع العدد إلى `.locals 3`!",
                fa_af = "دستور `.locals 2` فقط خانه‌های `v0` و `v1` را می‌سازد؛ برای استفاده از `v2` باید `.locals 3` بنویسید!",
                in_id = "`.locals 2` hanya mengalokasikan `v0` dan `v1`; ubah menjadi `.locals 3` agar `v2` valid!"
            ),
            whyIncorrectContent = localized(
                en = "Off-by-one `.locals` counts are the #1 cause of `VerifyError` when editing Smali by hand.",
                bn = "হাতে স্মালি কোড লেখার সময় `.locals` ১ কম দেওয়াই `VerifyError` হওয়ার সবচেয়ে প্রধান কারণ।",
                ur = "ہاتھ سے سمالی لکھتے وقت `.locals` کی گنتی میں غلطی سب سے عام وجہ ہے۔",
                hi = "हाथ से स्माली लिखते समय `.locals` की गिनती में चूक `VerifyError` का सबसे बड़ा कारण है।",
                ar = "نقصان `.locals` بمقدار واحد هو السبب الأول لأخطاء الفاحص عند التعديل اليدوي.",
                fa_af = "کم نوشتن تعداد `.locals` رایج‌ترین علت بروز `VerifyError` هنگام ویرایش دستی سمالی است.",
                in_id = "Kurang menghitung `.locals` adalah penyebab #1 `VerifyError` saat menulis Smali manual."
            ),
            difficulty = QuizDifficulty.EXPERT,
            type = QuizType.FIND_ERROR
        ),

        QuizQuestion(
            id = "q_09",
            lessonId = "smali_08",
            questionContent = localized(
                en = "How do Java/Kotlin compilers cleanly flip a boolean (`!flag`) in a single Smali instruction?",
                bn = "স্মালিতে মাত্র এক লাইনে বুলিয়ান মান উল্টে দিতে (`true` ↔ `false`, অর্থাৎ `!flag`) কোন অপকোড ব্যবহৃত হয়?",
                ur = "سمالی میں ایک ہی لائن میں بولین قیمت الٹنے (`!flag`) کے لیے کون سا اوپ کوڈ استعمال ہوتا ہے؟",
                hi = "स्माली में एक ही लाइन में बूलियन मान पलटने (`!flag`) के लिए कौन-सा ऑपकोड प्रयुक्त होता है?",
                ar = "كيف يعكس المترجم القيمة المنطقية (`!flag`) في سطر سمالي واحد بنظافة؟",
                fa_af = "در سمالی برای معکوس کردن مقدار بولین (`!flag`) در یک خط از چه دستوری استفاده می‌شود؟",
                in_id = "Bagaimana kompiler membalik nilai boolean (`!flag`) cukup dalam satu baris Smali?"
            ),
            optionsContent = listOf(
                localized("not-int v0, p0", "not-int v0, p0", "not-int v0, p0", "not-int v0, p0", "not-int v0, p0", "not-int v0, p0", "not-int v0, p0"),
                localized("xor-int/lit8 v0, p0, 0x1", "xor-int/lit8 v0, p0, 0x1", "xor-int/lit8 v0, p0, 0x1", "xor-int/lit8 v0, p0, 0x1", "xor-int/lit8 v0, p0, 0x1", "xor-int/lit8 v0, p0, 0x1", "xor-int/lit8 v0, p0, 0x1"),
                localized("neg-int v0, p0", "neg-int v0, p0", "neg-int v0, p0", "neg-int v0, p0", "neg-int v0, p0", "neg-int v0, p0", "neg-int v0, p0"),
                localized("sub-int/2addr v0, p0", "sub-int/2addr v0, p0", "sub-int/2addr v0, p0", "sub-int/2addr v0, p0", "sub-int/2addr v0, p0", "sub-int/2addr v0, p0", "sub-int/2addr v0, p0")
            ),
            correctIndex = 1,
            explanationContent = localized(
                en = "`1 XOR 1 = 0` and `0 XOR 1 = 1`! Therefore `xor-int/lit8 v0, p0, 0x1` cleanly toggles `1` and `0` in one instruction.",
                bn = "`1 XOR 1 = 0` এবং `0 XOR 1 = 1`! তাই `xor-int/lit8 v0, p0, 0x1` মাত্র এক লাইনে নিখুঁতভাবে `true` ও `false` উল্টে দেয়।",
                ur = "`1 XOR 1 = 0` اور `0 XOR 1 = 1`! اس لیے `xor-int/lit8 v0, p0, 0x1` ایک لائن میں بولین الٹ دیتا ہے۔",
                hi = "`1 XOR 1 = 0` और `0 XOR 1 = 1`! इसलिए `xor-int/lit8 v0, p0, 0x1` एक ही निर्देश में बूलियन पलट देता है।",
                ar = "لأن `1 XOR 1 = 0` و `0 XOR 1 = 1`، فإن `xor-int/lit8 v0, p0, 0x1` تبدل القيمة المنطقية بدقة.",
                fa_af = "چون `1 XOR 1 = 0` و `0 XOR 1 = 1` است، دستور `xor-int/lit8 v0, p0, 0x1` بولین را دقیق برعکس می‌کند.",
                in_id = "Karena `1 XOR 1 = 0` dan `0 XOR 1 = 1`, `xor-int/lit8 v0, p0, 0x1` membalik boolean dengan bersih."
            ),
            whyIncorrectContent = localized(
                en = "`not-int` flips ALL 32 bits (`~1 == -2`), which is still non-zero (truthy)!",
                bn = "`not-int` পুরো ৩২টি বিট উল্টে `-2` বানিয়ে ফেলে, যা শূন্য নয়!",
                ur = "`not-int` تمام 32 بٹس الٹ کر `-2` بنا دیتا ہے جو غلط ہے!",
                hi = "`not-int` सभी 32 बिट पलटकर `-2` बना देता है जो शून्य नहीं है!",
                ar = "`not-int` تعكس جميع البتات الـ 32 فتنتج `-2` وليس `0`!",
                fa_af = "`not-int` تمام ۳۲ بیت را برعکس کرده و `-2` تولید می‌کند نه صفر!",
                in_id = "`not-int` membalik seluruh 32 bit menjadi `-2`, bukan `0`!"
            ),
            difficulty = QuizDifficulty.EXPERT,
            type = QuizType.MULTIPLE_CHOICE
        ),

        QuizQuestion(
            id = "q_10",
            lessonId = "smali_13",
            questionContent = localized(
                en = "When is `invoke-virtual/range {v0 .. v5}, ...` REQUIRED instead of normal `invoke-virtual`?",
                bn = "সাধারণ `invoke-virtual`-এর বদলে কখন `invoke-virtual/range {v0 .. v5}, ...` ব্যবহার করা বাধ্যতামূলক?",
                ur = "عام `invoke-virtual` کے بجائے `invoke-virtual/range` کب لازمی ہوتا ہے؟",
                hi = "सामान्य `invoke-virtual` के स्थान पर `invoke-virtual/range` कब अनिवार्य होता है?",
                ar = "متى يكون استخدام `invoke-virtual/range` إلزامياً بدلاً من الصيغة العادية؟",
                fa_af = "چه زمانی استفاده از `invoke-virtual/range` به‌جای حالت معمولی الزامی است؟",
                in_id = "Kapan `invoke-virtual/range` WAJIB digunakan menggantikan `invoke-virtual` biasa?"
            ),
            optionsContent = listOf(
                localized("Whenever returning a String", "যখন মেথড String রিটার্ন করে", "جب میتھڈ String واپس کرے", "जब मेथड String लौटाए", "عند إرجاع نص String", "وقتی خروجی متد String باشد", "Saat mengembalikan String"),
                localized("When passing >5 register slots OR any register >= v16", "যখন ৫টির বেশি রেজিস্টার স্লট পাঠানো হয় অথবা v16+ রেজিস্টার থাকে", "جب 5 سے زائد سلاٹس ہوں یا v16+ رجسٹر استعمال ہو", "जब >5 रजिस्टर स्लॉट हों या कोई रजिस्टर >= v16 हो", "عند تمرير أكثر من 5 خانات أو استخدام مسجل >= v16", "وقتی بیش از ۵ اسلات رجستر یا رجستر v16 به بالا ارسال شود", "Saat mengirim >5 slot register ATAU memakai register >= v16"),
                localized("Only inside constructors", "শুধুমাত্র কনস্ট্রাক্টরের ভেতরে", "صرف کنسٹرکٹر کے اندر", "केवल कंस्ट्रक्टर के भीतर", "فقط داخل دوال البناء", "فقط در داخل سازنده‌ها", "Hanya di dalam konstruktor"),
                localized("Only inside loops", "শুধুমাত্র লুপের ভেতরে", "صرف لوپ کے اندر", "केवल लूप के भीतर", "فقط داخل الحلقات", "فقط درون حلقه‌ها", "Hanya di dalam perulangan")
            ),
            correctIndex = 1,
            explanationContent = localized(
                en = "Standard 35c `invoke-*` encodes at most 5 four-bit register IDs (`v0..v15`). Passing 6+ slots (remember `J`/`D` count as 2!) or using `v16+` requires `/range`.",
                bn = "সাধারণ `invoke-*` সর্বোচ্চ ৫টি ৪-বিট রেজিস্টার (`v0..v15`) নিতে পারে। ৬ বা তার বেশি স্লট হলে কিংবা `v16` বা তার বড় রেজিস্টার হলে অবশ্যই `/range` দিতে হয়!",
                ur = "عام `invoke-*` صرف 5 سلاٹس (`v0..v15`) لے سکتا ہے؛ 6 سلاٹس یا `v16+` کے لیے `/range` لازمی ہے!",
                hi = "सामान्य `invoke-*` अधिकतम 5 चार-बिट स्लॉट (`v0..v15`) लेता है; 6+ स्लॉट या `v16+` पर `/range` अनिवार्य है!",
                ar = "الصيغة القياسية تتسع لـ 5 خانات فقط من `v0..v15`؛ ما زاد عن ذلك يتطلب `/range`!",
                fa_af = "فرم عادی حداکثر ۵ خانه از `v0..v15` را می‌پذیرد؛ برای بیش از آن یا `v16+` حالت `/range` الزامی است!",
                in_id = "Format standar maks memuat 5 slot (`v0..v15`); >5 slot atau `v16+` wajib memakai `/range`!"
            ),
            whyIncorrectContent = localized(
                en = "Return types, loops, and constructors have nothing to do with `/range` encoding.",
                bn = "রিটার্ন টাইপ বা লুপের সাথে `/range`-এর কোনো সম্পর্ক নেই।",
                ur = "ریٹرن ٹائپ یا لوپ کا `/range` سے کوئی تعلق نہیں۔",
                hi = "रिटर्न टाइप या लूप का `/range` से कोई संबंध नहीं है।",
                ar = "لا علاقة لنوع الإرجاع أو الحلقات بصيغة `/range`.",
                fa_af = "نوع خروجی یا حلقه‌ها ارتباطی با `/range` ندارند.",
                in_id = "Tipe return dan loop tidak berkaitan dengan `/range`."
            ),
            difficulty = QuizDifficulty.EXPERT,
            type = QuizType.MULTIPLE_CHOICE
        )
    )
}
