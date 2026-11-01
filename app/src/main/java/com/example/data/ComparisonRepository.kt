package com.example.data

import com.example.model.CodeComparison
import com.example.model.ComparisonBreakdownLine
import com.example.model.localized

object ComparisonRepository {
    val comparisons = listOf(
        CodeComparison(
            id = "cmp_01_math",
            titleContent = localized(
                en = "01. Integer Addition & Multiplication",
                bn = "০১. পূর্ণসংখ্যার যোগ ও গুণ (বেসিক মেথড)",
                ur = "01. اعداد کی جمع اور ضرب",
                hi = "01. पूर्णांक जोड़ और गुणा",
                ar = "01. جمع وضرب الأعداد الصحيحة",
                fa_af = "۰۱. جمع و ضرب اعداد صحیح",
                in_id = "01. Penjumlahan & Perkalian Integer"
            ),
            categoryContent = localized(
                en = "Stage 1 • Math",
                bn = "ধাপ ১ • গণিত",
                ur = "مرحلہ 1 • حساب",
                hi = "चरण 1 • गणित",
                ar = "المرحلة 1 • حساب",
                fa_af = "مرحله ۱ • ریاضی",
                in_id = "Tahap 1 • Matematika"
            ),
            javaCode = """public static int totalPrice(int price, int tax) {
    int sum = price + tax;
    return sum * 2;
}""",
            kotlinCode = """fun totalPrice(price: Int, tax: Int): Int {
    val sum = price + tax
    return sum * 2
}""",
            smaliCode = """.method public static totalPrice(II)I
    .locals 1
    .param p0, "price"    # I
    .param p1, "tax"      # I

    add-int v0, p0, p1
    mul-int/lit8 v0, v0, 0x2
    return v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = ".locals 1",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Allocates 1 scratch register v0; static args arrive in p0 and p1.",
                        bn = "হিসাবের জন্য ১টি লোকাল রেজিস্টার `v0` বরাদ্দ করে; ইনপুট আসে `p0` ও `p1`-এ।",
                        ur = "حساب کے لیے ایک لوکل رجسٹر `v0` بناتا ہے؛ ان پٹ `p0` اور `p1` میں آتے ہیں۔",
                        hi = "गणना हेतु 1 लोकल रजिस्टर `v0` बनाता है; इनपुट `p0` व `p1` में आते हैं।",
                        ar = "يحجز مسجلاً محلياً واحداً `v0` وتصل المعاملات في `p0` و `p1`.",
                        fa_af = "یک رجستر محلی `v0` اختصاص می‌دهد و ورودی‌ها در `p0` و `p1` می‌آیند.",
                        in_id = "Mengalokasikan 1 register lokal `v0`; argumen masuk di `p0` & `p1`."
                    ),
                    javaEquivalent = "int sum;"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "add-int v0, p0, p1",
                    registersUsed = "v0, p0, p1",
                    purposeContent = localized(
                        en = "Adds p0 (price) + p1 (tax) and stores sum into v0.",
                        bn = "`p0` এবং `p1` যোগ করে যোগফল `v0` রেজিস্টারে রাখে।",
                        ur = "`p0` اور `p1` کو جمع کر کے نتیجہ `v0` میں رکھتا ہے۔",
                        hi = "`p0` और `p1` को जोड़कर परिणाम `v0` में रखता है।",
                        ar = "يجمع `p0` و `p1` ويخزن الناتج في `v0`.",
                        fa_af = "`p0` و `p1` را جمع کرده و در `v0` قرار می‌دهد.",
                        in_id = "Menjumlahkan `p0 + p1` dan menyimpannya ke `v0`."
                    ),
                    javaEquivalent = "sum = price + tax;"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "mul-int/lit8 v0, v0, 0x2",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Multiplies v0 by 8-bit literal 2 directly without needing another register.",
                        bn = "অতিরিক্ত রেজিস্টার ছাড়াই `v0`-কে সরাসরি `2` দিয়ে গুণ করে আবার `v0`-তেই রাখে।",
                        ur = "اضافی رجسٹر کے بغیر `v0` کو 2 سے ضرب دے کر `v0` میں رکھتا ہے۔",
                        hi = "अतिरिक्त रजिस्टर के बिना `v0` को 2 से गुणा कर `v0` में रखता है।",
                        ar = "يضرب `v0` في الرقم 2 مباشرة دون مسجل إضافي.",
                        fa_af = "`v0` را مستقیماً در عدد ۲ ضرب کرده و در خودش ذخیره می‌کند.",
                        in_id = "Mengalikan `v0` dengan literal `2` langsung di tempat."
                    ),
                    javaEquivalent = "sum * 2"
                )
            ),
            internalExecutionContent = localized(
                en = "D8 fuses `* 2` into `mul-int/lit8`, keeping `.locals` down to just 1 register.",
                bn = "D8 কম্পাইলার বুদ্ধি করে `* 2`-কে `mul-int/lit8`-এ রূপান্তর করেছে, ফলে মাত্র ১টি রেজিস্টারেই পুরো মেথড শেষ!",
                ur = "D8 کمپائلر `* 2` کو `mul-int/lit8` بنا دیتا ہے جس سے صرف 1 رجسٹر استعمال ہوتا ہے۔",
                hi = "D8 कंपाइलर `* 2` को `mul-int/lit8` में बदल देता है जिससे केवल 1 रजिस्टर लगता है।",
                ar = "يدمج المترجم الضرب في `mul-int/lit8` لتقليل استهلاك المسجلات إلى مسجل واحد.",
                fa_af = "کامپایلر ضرب در ۲ را به `mul-int/lit8` تبدیل می‌کند تا کل متد فقط ۱ رجستر مصرف کند.",
                in_id = "Kompiler D8 menggabungkan `* 2` ke `mul-int/lit8` sehingga cukup memakai 1 register."
            )
        ),

        CodeComparison(
            id = "cmp_02_ifelse",
            titleContent = localized(
                en = "02. Boolean Gate & Inverted If-Else Branch",
                bn = "০২. ইফ-এলস শর্ত ও উল্টো ব্রাঞ্চিং (if-eqz)",
                ur = "02. مشروط شاخ اور الٹی جمپ (if-eqz)",
                hi = "02. इफ़-एल्स शर्त और उल्टी ब्रांचिंग (if-eqz)",
                ar = "02. التفرع الشرطي وعكس القفز (if-eqz)",
                fa_af = "۰۲. شرط If-Else و پرش معکوس (if-eqz)",
                in_id = "02. Percabangan If-Else & Pembalikan if-eqz"
            ),
            categoryContent = localized(
                en = "Stage 1 • Control Flow",
                bn = "ধাপ ১ • শর্ত ও জাম্প",
                ur = "مرحلہ 1 • شرائط",
                hi = "चरण 1 • नियंत्रण प्रवाह",
                ar = "المرحلة 1 • التفرع",
                fa_af = "مرحله ۱ • کنترل جریان",
                in_id = "Tahap 1 • Alur Kontrol"
            ),
            javaCode = """public static String badge(boolean isPro) {
    if (isPro) {
        return "PRO MEMBER";
    } else {
        return "FREE USER";
    }
}""",
            kotlinCode = """fun badge(isPro: Boolean): String {
    return if (isPro) "PRO MEMBER" else "FREE USER"
}""",
            smaliCode = """.method public static badge(Z)Ljava/lang/String;
    .locals 1
    .param p0, "isPro"    # Z

    if-eqz p0, :cond_free

    const-string v0, "PRO MEMBER"
    return-object v0

    :cond_free
    const-string v0, "FREE USER"
    return-object v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "if-eqz p0, :cond_free",
                    registersUsed = "p0",
                    purposeContent = localized(
                        en = "If p0 == 0 (false), jumps to :cond_free; otherwise continues into PRO block.",
                        bn = "যদি `p0 == 0` (`false`) হয় তবে লাফ দিয়ে `:cond_free`-তে যায়; নাহলে সোজা নিচে PRO অংশে ঢুকে পড়ে।",
                        ur = "اگر `p0 == 0` (`false`) ہو تو `:cond_free` پر کودتا ہے ورنہ نیچے PRO بلاک چلتا ہے۔",
                        hi = "यदि `p0 == 0` (`false`) है तो `:cond_free` पर कूदता है, अन्यथा सीधे PRO ब्लॉक चलता है।",
                        ar = "إذا كان `p0 == 0` يقفز إلى `:cond_free`، وإلا يتابع تنفيذ كتلة PRO.",
                        fa_af = "اگر `p0 == 0` باشد به `:cond_free` می‌پرد وگرنه مستقیم وارد بخش PRO می‌شود.",
                        in_id = "Jika `p0 == 0` (`false`) lompat ke `:cond_free`, jika tidak lanjut ke blok PRO."
                    ),
                    javaEquivalent = "if (!isPro) goto cond_free;"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "return-object v0",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Returns the String reference in v0 to the caller.",
                        bn = "`v0`-এ লোড করা স্ট্রিং অবজেক্টটি কলারের কাছে ফেরত পাঠায়।",
                        ur = "`v0` میں موجود اسٹرنگ آبجیکٹ واپس بھیجتا ہے۔",
                        hi = "`v0` में लोड किया गया स्ट्रिंग ऑब्जेक्ट वापस लौटाता है।",
                        ar = "يرجع مرجع النص المخزن في `v0`.",
                        fa_af = "رشته ذخیره‌شده در `v0` را به فراخواننده بازمی‌گرداند.",
                        in_id = "Mengembalikan referensi String di `v0` ke pemanggil."
                    ),
                    javaEquivalent = "return \"...\";"
                )
            ),
            internalExecutionContent = localized(
                en = "Both branches terminate with `return-object`, satisfying ART's control-flow completeness check.",
                bn = "দুটি শাখাই নিজস্ব `return-object` দিয়ে শেষ হয়েছে, তাই কোনো অতিরিক্ত `goto` লাইনের প্রয়োজন হয়নি।",
                ur = "دونوں شاخیں `return-object` پر ختم ہوتی ہیں اس لیے اضافی `goto` کی ضرورت نہیں پڑتی۔",
                hi = "दोनों शाखाएँ `return-object` पर समाप्त होती हैं, इसलिए अतिरिक्त `goto` की आवश्यकता नहीं है।",
                ar = "ينتهي كلا الفرعين بتعليمة إرجاع صريحة مما يغني عن تعليمة `goto`.",
                fa_af = "هر دو شاخه با `return-object` پایان یافته‌اند و نیازی به `goto` اضافی نیست.",
                in_id = "Kedua cabang berakhir dengan `return-object`, sehingga tidak perlu `goto` tambahan."
            )
        ),

        CodeComparison(
            id = "cmp_03_stringbuilder",
            titleContent = localized(
                en = "03. Heap Object Creation & StringBuilder Chain",
                bn = "০৩. অবজেক্ট তৈরি (<init>) ও StringBuilder চেইন",
                ur = "03. نیا آبجیکٹ (<init>) اور StringBuilder چین",
                hi = "03. ऑब्जेक्ट निर्माण (<init>) और StringBuilder चेन",
                ar = "03. إنشاء كائن StringBuilder ودمج النصوص",
                fa_af = "۰۳. ساخت شیء (<init>) و زنجیره StringBuilder",
                in_id = "03. Pembuatan Objek (<init>) & Rantai StringBuilder"
            ),
            categoryContent = localized(
                en = "Stage 2 • Objects",
                bn = "ধাপ ২ • অবজেক্ট",
                ur = "مرحلہ 2 • آبجیکٹس",
                hi = "चरण 2 • ऑब्जेक्ट",
                ar = "المرحلة 2 • الكائنات",
                fa_af = "مرحله ۲ • اشیاء",
                in_id = "Tahap 2 • Objek"
            ),
            javaCode = """public static String tagUser(String user) {
    return "@" + user;
}""",
            kotlinCode = """fun tagUser(user: String): String {
    return "@${'$'}user"
}""",
            smaliCode = """.method public static tagUser(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "@"
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v0
    return-object v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "new-instance v0, Ljava/lang/StringBuilder;",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Allocates blank StringBuilder memory on the heap.",
                        bn = "হিপ মেমোরিতে `StringBuilder`-এর জন্য ফাঁকা জায়গা বরাদ্দ করে।",
                        ur = "ہیپ میموری میں `StringBuilder` کے لیے جگہ مختص کرتا ہے۔",
                        hi = "हीप मेमोरी में `StringBuilder` के लिए स्थान आवंटित करता है।",
                        ar = "يحجز ذاكرة فارغة لكائن `StringBuilder`.",
                        fa_af = "فضای خام حافظه برای شیء `StringBuilder` تخصیص می‌دهد.",
                        in_id = "Mengalokasikan memori kosong untuk `StringBuilder`."
                    ),
                    javaEquivalent = "new StringBuilder"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "invoke-direct {v0}, ...-><init>()V",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Runs constructor to initialize the StringBuilder instance.",
                        bn = "`<init>()` কনস্ট্রাক্টর চালিয়ে অবজেক্টটিকে ব্যবহারের উপযোগী করে।",
                        ur = "`<init>()` کنسٹرکٹر چلا کر آبجیکٹ کو فعال کرتا ہے۔",
                        hi = "`<init>()` कंस्ट्रक्टर चलाकर ऑब्जेक्ट को प्रारंभ करता है।",
                        ar = "يشغل الباني `<init>()` لتهيئة الكائن.",
                        fa_af = "سازنده `<init>()` را اجرا می‌کند تا شیء آماده کار شود.",
                        in_id = "Menjalankan konstruktor `<init>()` untuk menginisialisasi objek."
                    ),
                    javaEquivalent = "StringBuilder()"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "move-result-object v0",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Captures final String produced by toString().",
                        bn = "`toString()` থেকে তৈরি হওয়া চূড়ান্ত স্ট্রিংটি `v0`-তে ধরে রাখে।",
                        ur = "`toString()` سے بننے والی آخری اسٹرنگ کو `v0` میں محفوظ کرتا ہے۔",
                        hi = "`toString()` द्वारा निर्मित अंतिम स्ट्रिंग को `v0` में पकड़ता है।",
                        ar = "يلتقط النص النهائي الناتج من `toString()`.",
                        fa_af = "رشته نهایی تولیدشده توسط `toString()` را در `v0` می‌گیرد.",
                        in_id = "Menangkap String akhir hasil `toString()` ke `v0`."
                    ),
                    javaEquivalent = "sb.toString()"
                )
            ),
            internalExecutionContent = localized(
                en = "Both Java `+` and Kotlin `\"@\$user\"` string templates compile into `StringBuilder` chains in DEX.",
                bn = "জাভার `+` এবং কোটলিনের `\"@\$user\"`—দুটোই কম্পাইল হয়ে ভেতরে ভেতরে `StringBuilder` ব্যবহার করে!",
                ur = "جاوا کا `+` اور کوٹلن کا ٹیمپلیٹ دونوں اندرونی طور پر `StringBuilder` ہی بناتے ہیں!",
                hi = "जावा का `+` और कोटलिन का `\"@\$user\"` दोनों कंपाइल होकर `StringBuilder` ही बनाते हैं!",
                ar = "كل من علامة `+` في جافا وقوالب النصوص في كوتلن تترجم إلى `StringBuilder`!",
                fa_af = "هم عملگر `+` جاوا و هم قالب رشته‌ای کاتلین در بایت‌کد به `StringBuilder` تبدیل می‌شوند!",
                in_id = "Baik `+` di Java maupun template `\"@\$user\"` di Kotlin dikompilasi menjadi `StringBuilder`!"
            )
        ),

        CodeComparison(
            id = "cmp_04_loop",
            titleContent = localized(
                en = "04. Array For-Loop & Element Accumulation",
                bn = "০৪. অ্যারে For-Loop ও যোগফল নির্ণয় (aget + goto)",
                ur = "04. ایرے For-Loop اور عناصر کا مجموعہ",
                hi = "04. ऐरे For-Loop और तत्वों का योग (aget + goto)",
                ar = "04. حلقة تكرار المصفوفة وجمع العناصر (aget + goto)",
                fa_af = "۰۴. حلقه پیمایش آرایه و جمع عناصر (aget + goto)",
                in_id = "04. Perulangan Array & Penjumlahan Elemen"
            ),
            categoryContent = localized(
                en = "Stage 3 • Loops & Arrays",
                bn = "ধাপ ৩ • লুপ ও অ্যারে",
                ur = "مرحلہ 3 • لوپس اور ایرے",
                hi = "चरण 3 • लूप और ऐरे",
                ar = "المرحلة 3 • الحلقات والمصفوفات",
                fa_af = "مرحله ۳ • حلقه و آرایه",
                in_id = "Tahap 3 • Loop & Array"
            ),
            javaCode = """public static int sumAll(int[] nums) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
    }
    return sum;
}""",
            kotlinCode = """fun sumAll(nums: IntArray): Int {
    var sum = 0
    for (i in nums.indices) {
        sum += nums[i]
    }
    return sum
}""",
            smaliCode = """.method public static sumAll([I)I
    .locals 4

    const/4 v0, 0x0          # sum = 0
    const/4 v1, 0x0          # i = 0
    array-length v2, p0      # len = nums.length

    :loop_head
    if-ge v1, v2, :loop_exit
    aget v3, p0, v1          # v3 = nums[i]
    add-int/2addr v0, v3     # sum += v3
    add-int/lit8 v1, v1, 0x1 # i++
    goto :loop_head

    :loop_exit
    return v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "array-length v2, p0",
                    registersUsed = "v2, p0",
                    purposeContent = localized(
                        en = "Reads length of int[] p0 once into v2 before loop starts.",
                        bn = "লুপ শুরুর আগে `p0` অ্যারের মোট দৈর্ঘ্য `v2` রেজিস্টারে মেপে রাখে।",
                        ur = "لوپ سے پہلے `p0` ایرے کی لمبائی `v2` میں محفوظ کرتا ہے۔",
                        hi = "लूप से पहले `p0` ऐरे की लंबाई `v2` में पढ़ता है।",
                        ar = "يقرأ طول المصفوفة `p0` ويحفظه في `v2` قبل بدء الحلقة.",
                        fa_af = "پیش از شروع حلقه، طول آرایه `p0` را در `v2` ذخیره می‌کند.",
                        in_id = "Membaca panjang array `p0` ke `v2` sebelum loop dimulai."
                    ),
                    javaEquivalent = "nums.length"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "if-ge v1, v2, :loop_exit",
                    registersUsed = "v1, v2",
                    purposeContent = localized(
                        en = "Exits loop as soon as index v1 reaches or exceeds length v2.",
                        bn = "ইনডেক্স `v1` যখন দৈর্ঘ্য `v2`-এর সমান বা বড় হয়ে যায়, তখন লুপ ভেঙে `:loop_exit`-এ যায়।",
                        ur = "جب انڈیکس `v1` لمبائی `v2` کے برابر ہو جائے تو لوپ سے باہر نکل جاتا ہے۔",
                        hi = "जैसे ही इंडेक्स `v1` लंबाई `v2` के बराबर होता है, लूप समाप्त कर देता है।",
                        ar = "يخرج من الحلقة فور وصول الفهرس `v1` إلى الطول `v2`.",
                        fa_af = "به محض رسیدن اندیس `v1` به طول `v2` از حلقه خارج می‌شود.",
                        in_id = "Keluar dari loop begitu indeks `v1 >= v2`."
                    ),
                    javaEquivalent = "i < nums.length"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "aget v3, p0, v1",
                    registersUsed = "v3, p0, v1",
                    purposeContent = localized(
                        en = "Fetches element p0[v1] into temporary register v3.",
                        bn = "`p0` অ্যারের `v1` পজিশনের সংখ্যাটি তুলে `v3` রেজিস্টারে আনে।",
                        ur = "`p0` کے انڈیکس `v1` سے عنصر نکال کر `v3` میں رکھتا ہے۔",
                        hi = "`p0` के `v1` स्थान से तत्व निकालकर `v3` में रखता है।",
                        ar = "يجلب العنصر `p0[v1]` إلى المسجل المؤقت `v3`.",
                        fa_af = "عنصر خانه `v1` از آرایه `p0` را در `v3` می‌خواند.",
                        in_id = "Mengambil elemen `p0[v1]` ke register `v3`."
                    ),
                    javaEquivalent = "nums[i]"
                )
            ),
            internalExecutionContent = localized(
                en = "Backward `goto :loop_head` combined with forward `if-ge ..., :loop_exit` is the universal bytecode signature of every `for`/`while` loop.",
                bn = "সামনে `if-ge ..., :loop_exit` এবং পেছনে `goto :loop_head`—এই দুটি লাইন দেখলেই বুঝবেন এটি একটি নিখুঁত `for` বা `while` লুপ!",
                ur = "آگے کی طرف `if-ge` اور پیچھے کی طرف `goto` ہر `for`/`while` لوپ کی پہچان ہے!",
                hi = "आगे `if-ge` और पीछे `goto :loop_head` का जोड़ा हर `for`/`while` लूप की पहचान है!",
                ar = "اجتماع `if-ge` للأمام مع `goto` للخلف هو البصمة الثابتة لكل حلقة تكرار!",
                fa_af = "ترکیب `if-ge` به جلو و `goto` به عقب امضای همیشگی حلقه‌های `for` و `while` است!",
                in_id = "Kombinasi `if-ge` maju dan `goto` mundur adalah ciri khas semua loop `for`/`while`!"
            )
        ),

        CodeComparison(
            id = "cmp_05_fields",
            titleContent = localized(
                en = "05. Instance Fields vs Static Fields (iget/iput/sget)",
                bn = "০৫. ইনস্ট্যান্স ফিল্ড ও স্ট্যাটিক ফিল্ডের ব্যবহার",
                ur = "05. انسٹانس اور اسٹیٹک فیلڈز کا موازنہ",
                hi = "05. इंस्टेंस फ़ील्ड बनाम स्टैटिक फ़ील्ड (iget/iput/sget)",
                ar = "05. حقول الكائن مقابل الحقول الثابتة (iget/iput/sget)",
                fa_af = "۰۵. مقایسه فیلدهای شیء و استاتیک (iget/iput/sget)",
                in_id = "05. Field Instance vs Field Static (iget/iput/sget)"
            ),
            categoryContent = localized(
                en = "Stage 3 • Class State",
                bn = "ধাপ ৩ • ক্লাস ফিল্ড",
                ur = "مرحلہ 3 • فیلڈز",
                hi = "चरण 3 • क्लास फ़ील्ड",
                ar = "المرحلة 3 • الحقول",
                fa_af = "مرحله ۳ • فیلدها",
                in_id = "Tahap 3 • Field Kelas"
            ),
            javaCode = """public class Wallet {
    public static int FEE = 5;
    private int balance;

    public void deposit(int amount) {
        this.balance += (amount - FEE);
    }
}""",
            kotlinCode = """class Wallet {
    companion object { @JvmField var FEE = 5 }
    private var balance = 0

    fun deposit(amount: Int) {
        this.balance += (amount - FEE)
    }
}""",
            smaliCode = """.method public deposit(I)V
    .locals 2
    .param p1, "amount"    # I (p0 is 'this' Wallet!)

    sget v0, Lcom/example/Wallet;->FEE:I
    sub-int v0, p1, v0

    iget v1, p0, Lcom/example/Wallet;->balance:I
    add-int/2addr v1, v0
    iput v1, p0, Lcom/example/Wallet;->balance:I

    return-void
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "sget v0, ...->FEE:I",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Reads static FEE without needing an object register.",
                        bn = "কোনো অবজেক্ট রেজিস্টার ছাড়াই সরাসরি `static FEE` পড়ে `v0`-তে আনে।",
                        ur = "بغیر آبجیکٹ رجسٹر کے براہ راست `static FEE` پڑھتا ہے۔",
                        hi = "बिना ऑब्जेक्ट रजिस्टर के सीधे `static FEE` पढ़ता है।",
                        ar = "يقرأ الحقل الثابت `FEE` دون الحاجة لمسجل كائن.",
                        fa_af = "بدون نیاز به رجستر شیء، فیلد استاتیک `FEE` را می‌خواند.",
                        in_id = "Membaca `static FEE` tanpa memerlukan register objek."
                    ),
                    javaEquivalent = "Wallet.FEE"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "iget v1, p0, ...->balance:I",
                    registersUsed = "v1, p0",
                    purposeContent = localized(
                        en = "Reads current balance from 'this' instance (p0) into v1.",
                        bn = "`p0` (`this` ওয়ালেট অবজেক্ট) থেকে বর্তমান `balance` পড়ে `v1`-এ আনে।",
                        ur = "`p0` (`this`) سے موجودہ `balance` پڑھ کر `v1` میں لاتا ہے۔",
                        hi = "`p0` (`this`) से वर्तमान `balance` पढ़कर `v1` में रखता है।",
                        ar = "يقرأ الرصيد الحالي من الكائن `p0` إلى `v1`.",
                        fa_af = "موجودی فعلی را از شیء `p0` (`this`) در `v1` می‌خواند.",
                        in_id = "Membaca `balance` saat ini dari objek `p0` (`this`) ke `v1`."
                    ),
                    javaEquivalent = "this.balance"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "iput v1, p0, ...->balance:I",
                    registersUsed = "v1, p0",
                    purposeContent = localized(
                        en = "Writes updated balance in v1 back into p0's balance field.",
                        bn = "নতুন যোগফল `v1`-কে আবার `p0` অবজেক্টের `balance` ফিল্ডে সেভ করে।",
                        ur = "اپڈیٹ شدہ رقم `v1` کو واپس `p0` کے فیلڈ میں محفوظ کرتا ہے۔",
                        hi = "नए योग `v1` को वापस `p0` के `balance` फ़ील्ड में सहेजता है।",
                        ar = "يحفظ الرصيد المحدث في حقل الكائن `p0`.",
                        fa_af = "موجودی جدید در `v1` را دوباره درون فیلد شیء `p0` می‌نویسد.",
                        in_id = "Menyimpan saldo baru di `v1` kembali ke field milik `p0`."
                    ),
                    javaEquivalent = "this.balance = ..."
                )
            ),
            internalExecutionContent = localized(
                en = "Notice how `deposit(I)V` is an instance method, so `amount` is in `p1` while `p0` points to `this` Wallet!",
                bn = "লক্ষ্য করুন: মেথডটি `static` নয় বলে `p0`-তে রয়েছে `Wallet` নিজে (`this`), আর ইনপুট `amount` চলে গেছে `p1`-এ!",
                ur = "غور کریں: یہ انسٹانس میتھڈ ہے اس لیے `p0` میں `this` ہے اور `amount` پیرامیٹر `p1` میں آیا ہے!",
                hi = "ध्यान दें: यह इंस्टेंस मेथड है, इसलिए `p0` में `this` है और `amount` पैरामीटर `p1` में है!",
                ar = "لاحظ أن الدالة غير ثابتة، لذا يحمل `p0` الكائن `this` بينما يقع المعامل `amount` في `p1`!",
                fa_af = "دقت کنید چون متد غیر استاتیک است، `p0` همان `this` بوده و ورودی `amount` در `p1` قرار دارد!",
                in_id = "Perhatikan karena metode instance, `p0` berisi `this` sedangkan `amount` berada di `p1`!"
            )
        ),

        CodeComparison(
            id = "cmp_06_trycatch",
            titleContent = localized(
                en = "06. Try-Catch Exception Handling (.catch & move-exception)",
                bn = "০৬. ট্রাই-ক্যাচ এক্সেপশন হ্যান্ডলিং (.catch ও move-exception)",
                ur = "06. ٹرائی کیچ ایکسپشن ہینڈلنگ (.catch)",
                hi = "06. ट्राई-कैच एक्सेप्शन हैंडलिंग (.catch और move-exception)",
                ar = "06. معالجة الاستثناءات (.catch و move-exception)",
                fa_af = "۰۶. مدیریت خطا با Try-Catch (.catch و move-exception)",
                in_id = "06. Penanganan Eksepsi Try-Catch (.catch & move-exception)"
            ),
            categoryContent = localized(
                en = "Stage 4 • Exceptions",
                bn = "ধাপ ৪ • এক্সেপশন",
                ur = "مرحلہ 4 • ایکسپشنز",
                hi = "चरण 4 • एक्सेप्शन",
                ar = "المرحلة 4 • الاستثناءات",
                fa_af = "مرحله ۴ • مدیریت خطا",
                in_id = "Tahap 4 • Eksepsi"
            ),
            javaCode = """public static int parseSafe(String text) {
    try {
        return Integer.parseInt(text);
    } catch (NumberFormatException e) {
        return 0;
    }
}""",
            kotlinCode = """fun parseSafe(text: String): Int {
    return try {
        Integer.parseInt(text)
    } catch (e: NumberFormatException) {
        0
    }
}""",
            smaliCode = """.method public static parseSafe(Ljava/lang/String;)I
    .locals 1

    :try_start_0
    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    move-result v0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return v0

    :catch_0
    move-exception v0
    const/4 v0, 0x0
    return v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = ".catch ... {:try_start_0 .. :try_end_0} :catch_0",
                    registersUsed = "Exception Table",
                    purposeContent = localized(
                        en = "Registers DEX exception table entry mapping protected labels to :catch_0.",
                        bn = "DEX এক্সেপশন টেবিলে নিয়ম যুক্ত করে যেন এই সীমার ভেতর এরর হলেই `:catch_0`-তে জাম্প করে।",
                        ur = "DEX ٹیبل میں اصول درج کرتا ہے کہ اس حد میں ایرر آنے پر `:catch_0` پر جائے۔",
                        hi = "DEX एक्सेप्शन तालिका में नियम जोड़ता है ताकि त्रुटि होने पर `:catch_0` पर कूदे।",
                        ar = "يسجل نطاق الحماية في جدول الاستثناءات للقفز إلى `:catch_0` عند الخطأ.",
                        fa_af = "در جدول خطای DEX ثبت می‌کند که در صورت بروز خطا به `:catch_0` بپرد.",
                        in_id = "Mendaftarkan rentang label terproteksi agar melompat ke `:catch_0` saat error."
                    ),
                    javaEquivalent = "try { ... } catch (...)"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "move-exception v0",
                    registersUsed = "v0",
                    purposeContent = localized(
                        en = "Receives the caught NumberFormatException instance at the entry of :catch_0.",
                        bn = "`:catch_0`-এর শুরুতে ধরা পড়া `NumberFormatException` অবজেক্টটি `v0`-তে গ্রহণ করে।",
                        ur = "`:catch_0` کے آغاز پر پکڑے گئے ایرر آبجیکٹ کو `v0` میں وصول کرتا ہے۔",
                        hi = "`:catch_0` की शुरुआत में पकड़े गए एक्सेप्शन ऑब्जेक्ट को `v0` में प्राप्त करता है।",
                        ar = "يستلم كائن الاستثناء الملتقط في بداية `:catch_0`.",
                        fa_af = "شیء خطای گرفته‌شده را در ابتدای `:catch_0` درون `v0` تحویل می‌گیرد.",
                        in_id = "Menerima objek `NumberFormatException` di awal blok `:catch_0`."
                    ),
                    javaEquivalent = "catch (NumberFormatException e)"
                )
            ),
            internalExecutionContent = localized(
                en = "Zero runtime cost when no exception occurs! ART only consults the `.catch` table when an instruction actually throws.",
                bn = "এরর না ঘটলে try-catch কোনো গতি কমায় না! কেবল এরর ঘটলেই ART `.catch` টেবিল দেখে `:catch_0`-তে লাফ দেয়।",
                ur = "ایرر نہ آنے پر کوئی رفتار کم نہیں ہوتی؛ صرف ایرر آنے پر ہی `.catch` ٹیبل استعمال ہوتا ہے۔",
                hi = "त्रुटि न होने पर शून्य ओवरहेड रहता है; केवल अपवाद फेंके जाने पर ही ART `.catch` तालिका देखता है।",
                ar = "لا توجد أي تكلفة في الأداء ما لم يُرمَ استثناء فعلياً أثناء التشغيل.",
                fa_af = "تا زمانی که خطایی رخ ندهد هیچ هزینه اجرایی ندارد و فقط هنگام پرتاب خطا جدول بررسی می‌شود.",
                in_id = "Tanpa biaya performa jika tidak terjadi error; tabel `.catch` hanya dibaca saat eksepsi dilempar."
            )
        )
    )
}
