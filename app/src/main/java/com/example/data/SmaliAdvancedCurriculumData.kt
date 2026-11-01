package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object SmaliAdvancedCurriculumData {
    val lessons: List<Lesson> = listOf(
        // MODULE 9
        Lesson(
            id = "smali_09",
            track = TrackType.SMALI,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "09. Conditionals (if-eqz, if-ge) & Logic Inversion",
                bn = "০৯. শর্তসাপেক্ষ জাম্প (if-eqz, if-ge) ও উল্টো লজিকের রহস্য",
                ur = "09. مشروط جمپس (if-eqz، if-ge) اور الٹی لاجک کا راز",
                hi = "09. शर्त जंप (if-eqz, if-ge) और उल्टे लॉजिक का रहस्य",
                ar = "09. التفرع الشرطي (if-eqz, if-ge) وسر عكس المنطق البرمجي",
                fa_af = "۰۹. پرش‌های شرطی (if-eqz, if-ge) و راز وارونه‌سازی منطق",
                in_id = "09. Percabangan (if-eqz, if-ge) & Pembalikan Logika"
            ),
            subtitle = localized(
                en = "Why Java 'if (isPro)' becomes 'if-eqz p0, :cond_0' in Smali, plus all 12 conditional opcodes.",
                bn = "কেন জাভার `if (isPro)` স্মালিতে উল্টে `if-eqz p0, :cond_0` হয়ে যায় এবং ১২টি শর্ত অপকোডের তালিকা।",
                ur = "جانیں کہ جاوا کا `if (isPro)` سمالی میں الٹ کر `if-eqz` کیوں بن جاتا ہے اور تمام 12 شرطیہ اوپ کوڈز۔",
                hi = "जानें कि जावा का `if (isPro)` स्माली में उलटकर `if-eqz` क्यों बन जाता है, साथ ही सभी 12 शर्त ऑपकोड।",
                ar = "لماذا يتحول شرط `if (isPro)` في جافا إلى عكسه `if-eqz` في سمالي، مع شرح جميع التعليمات الـ 12.",
                fa_af = "چرا شرط `if (isPro)` در جاوا به شکل معکوس `if-eqz` در سمالی ترجمه می‌شود + ۱۲ دستور شرطی.",
                in_id = "Mengapa `if (isPro)` di Java dibalik menjadi `if-eqz` di Smali, beserta 12 opcode kondisi."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Zero-Tests (*z) vs Two-Register Comparisons",
                        bn = "জিরো-টেস্ট (*z) বনাম দুই-রেজিস্টারের তুলনা এবং উল্টো জাম্প",
                        ur = "زیرو ٹیسٹ (*z) بمقابلہ دو رجسٹرز کا موازنہ",
                        hi = "ज़ीरो-टेस्ट (*z) बनाम दो-रजिस्टर तुलना और उल्टा जंप",
                        ar = "اختبارات الصفر (*z) مقابل مقارنة مسجلين وعكس القفز",
                        fa_af = "مقایسه با صفر (*z) در برابر مقایسه دو رجستر و پرش معکوس",
                        in_id = "Uji-Nol (*z) vs Perbandingan Dua Register"
                    ),
                    childAnalogy = localized(
                        en = "In Java you say: 'If you have a ticket, enter the cinema.' Compilers write Smali the opposite way: 'If your hands are EMPTY (`if-eqz`), jump OUTSIDE (`:no_ticket`); otherwise walk straight inside!'",
                        bn = "জাভায় আমরা বলি: 'টিকিট থাকলে সিনেমা হলে ঢুকো।' কিন্তু কম্পাইলার স্মালিতে উল্টোভাবে লেখে: 'যদি হাত খালি থাকে (`if-eqz`), তবে লাফ দিয়ে বাইরে চলে যাও (`:else`); নাহলে সোজা ভেতরে হেঁটে ঢুকে পড়ো!'",
                        ur = "جاوا میں ہم کہتے ہیں 'ٹکٹ ہو تو اندر آؤ'، مگر کمپائلر سمالی میں لکھتا ہے: 'اگر ہاتھ خالی ہیں (`if-eqz`) تو باہر کود جاؤ، ورنہ سیدھا اندر چلے آؤ!'",
                        hi = "जावा में हम कहते हैं 'टिकट है तो अंदर आओ', लेकिन कंपाइलर स्माली में उल्टा लिखता है: 'यदि हाथ खाली हैं (`if-eqz`), तो बाहर कूद जाओ (`:else`), वरना सीधे अंदर चले जाओ!'",
                        ar = "في جافا نقول: إن كنت تملك تذكرة ادخل. أما المترجم في سمالي فيكتب العكس: إن لم تملك تذكرة (`if-eqz`) اقفز للخارج، وإلا فتابع الدخول مباشرة!",
                        fa_af = "در جاوا می‌گوییم: «اگر بلیت داری وارد شو»، اما کامپایلر در سمالی می‌نویسد: «اگر دستت خالی است (`if-eqz`) به بیرون بپر، وگرنه مستقیم وارد شو!»",
                        in_id = "Di Java kita berkata 'Jika punya tiket, masuk'. Kompiler menulis Smali sebaliknya: 'Jika TIDAK punya tiket (`if-eqz`), lompat ke luar!'."
                    ),
                    content = localized(
                        en = "Smali provides two families of conditional jumps to `:label`:\n• **Compare 1 register against 0 (`null`/`false`)**: `if-eqz` (`==0`), `if-nez` (`!=0`), `if-ltz` (`<0`), `if-gez` (`>=0`), `if-gtz` (`>0`), `if-lez` (`<=0`).\n• **Compare 2 registers (`vA` vs `vB`)**: `if-eq` (`==`), `if-ne` (`!=`), `if-lt` (`<`), `if-ge` (`>=`), `if-gt` (`>`), `if-le` (`<=`).",
                        bn = "স্মালিতে `:label`-এ লাফ দেওয়ার জন্য ২ ধরনের শর্ত আছে:\n• **১টি রেজিস্টারের সাথে শূন্য (`0`/`false`/`null`) তুলনা**: `if-eqz` (`==0`), `if-nez` (`!=0`), `if-ltz` (`<0`), `if-gez` (`>=0`), `if-gtz` (`>0`), `if-lez` (`<=0`)।\n• **২টি রেজিস্টারের মধ্যে তুলনা (`vA` ও `vB`)**: `if-eq` (`==`), `if-ne` (`!=`), `if-lt` (`<`), `if-ge` (`>=`), `if-gt` (`>`), `if-le` (`<=`)।",
                        ur = "سمالی میں مشروط جمپ کی دو اقسام ہیں:\n• **صفر سے موازنہ**: `if-eqz`, `if-nez`, `if-ltz`, `if-gez`, `if-gtz`, `if-lez`۔\n• **دو رجسٹرز کا موازنہ**: `if-eq`, `if-ne`, `if-lt`, `if-ge`, `if-gt`, `if-le`۔",
                        hi = "स्माली में `:label` पर कूदने के दो समूह हैं:\n• **शून्य (`0`/`false`/`null`) से तुलना**: `if-eqz`, `if-nez`, `if-ltz`, `if-gez`, `if-gtz`, `if-lez`।\n• **दो रजिस्टरों की तुलना**: `if-eq`, `if-ne`, `if-lt`, `if-ge`, `if-gt`, `if-le`।",
                        ar = "توجد مجموعتان من القفز الشرطي إلى `:label`:\n• **مقارنة مسجل بالصفر**: `if-eqz`, `if-nez`, `if-ltz`, `if-gez`, `if-gtz`, `if-lez`.\n• **مقارنة مسجلين معاً**: `if-eq`, `if-ne`, `if-lt`, `if-ge`, `if-gt`, `if-le`.",
                        fa_af = "دو گروه دستور شرطی برای پرش به `:label` وجود دارد:\n• **مقایسه با صفر (`0`/`false`/`null`)**: `if-eqz`, `if-nez`, `if-ltz`, `if-gez`, `if-gtz`, `if-lez`.\n• **مقایسه دو رجستر با هم**: `if-eq`, `if-ne`, `if-lt`, `if-ge`, `if-gt`, `if-le`.",
                        in_id = "Ada 2 keluarga lompatan bersyarat ke `:label`:\n• **Bandingkan dengan 0 (`false`/`null`)**: `if-eqz`, `if-nez`, `if-ltz`, `if-gez`, `if-gtz`, `if-lez`.\n• **Bandingkan 2 register**: `if-eq`, `if-ne`, `if-lt`, `if-ge`, `if-gt`, `if-le`."
                    ),
                    javaCode = """public static String grade(int marks) {
    if (marks >= 80) {
        return "A+";
    }
    return "Pass";
}""",
                    smaliCode = """.method public static grade(I)Ljava/lang/String;
    .locals 1
    const/16 v0, 0x50          # 0x50 = 80
    if-lt p0, v0, :below_80    # Inverted! Jump if marks < 80

    const-string v0, "A+"
    return-object v0

    :below_80
    const-string v0, "Pass"
    return-object v0
.end method"""
                )
            )
        ),

        // MODULE 10
        Lesson(
            id = "smali_10",
            track = TrackType.SMALI,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "10. Loops (for/while) & Switch Tables (packed/sparse)",
                bn = "১০. লুপ (for/while) এবং সুইচ টেবিল (packed ও sparse-switch)",
                ur = "10. لوپس (for/while) اور سوئچ ٹیبلز (packed/sparse)",
                hi = "10. लूप (for/while) और स्विच टेबल (packed/sparse-switch)",
                ar = "10. الحلقات التكرارية وجداول التبديل (packed و sparse-switch)",
                fa_af = "۱۰. حلقه‌های تکرار (for/while) و جداول سوئیچ در سمالی",
                in_id = "10. Perulangan (for/while) & Tabel Switch (packed/sparse)"
            ),
            subtitle = localized(
                en = "Spot backward goto loops instantly and read .packed-switch & .sparse-switch jump tables.",
                bn = "স্মালিতে কোনো `for` বা `while` শব্দ থাকে না—কীভাবে `goto` ও সুইচ টেবিল দিয়ে লুপ তৈরি হয় দেখুন।",
                ur = "سمالی میں `for` یا `while` کا لفظ نہیں ہوتا—جانیں کہ `goto` اور سوئچ ٹیبلز کیسے کام کرتے ہیں۔",
                hi = "स्माली में `for` या `while` शब्द नहीं होता—देखें कि `goto` और स्विच टेबल से लूप कैसे बनते हैं।",
                ar = "لا توجد كلمة `for` في سمالي: تعلم قراءة قفزات `goto` الخلفية وجداول التبديل.",
                fa_af = "در سمالی کلمه `for` وجود ندارد؛ نحوه ساخت حلقه‌ها با `goto` و جداول سوئیچ را بیاموزید.",
                in_id = "Tidak ada kata `for` di Smali: kenali lompatan mundur `goto` dan tabel switch."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Backward goto Loops & Jump Tables",
                        bn = "পেছনের দিকে goto জাম্প এবং সুইচ টেবিলের গঠন",
                        ur = "پیچھے کی طرف goto جمپ اور سوئچ ٹیبل کی ساخت",
                        hi = "पीछे की ओर goto जंप और स्विच टेबल की संरचना",
                        ar = "قفزات goto الخلفية وبنية جداول packed-switch",
                        fa_af = "پرش به عقب با goto و ساختار جدول packed-switch",
                        in_id = "Lompatan Mundur goto & Struktur Tabel Switch"
                    ),
                    content = localized(
                        en = "• **Recognizing Loops**: Whenever a `goto :loop_head` jumps UPWARD to an earlier label, you are looking at a `while` or `for` loop!\n• **`packed-switch`**: Used when `switch` cases are consecutive integers (`0, 1, 2`). Super fast O(1) table jump.\n• **`sparse-switch`**: Used when cases have big gaps (`10, 500, 9999`), storing `key -> :label` pairs.",
                        bn = "• **লুপ চেনার উপায়**: যখনই দেখবেন নিচের কোনো `goto :loop_start` লাইন ওপরের কোনো লেবেলে ফিরে যাচ্ছে, বুঝবেন এটি একটি `for` বা `while` লুপ!\n• **`packed-switch`**: যখন `switch`-এর কেসগুলো পরপর থাকে (`0, 1, 2`)।\n• **`sparse-switch`**: যখন কেসগুলোর সংখ্যায় বড় ব্যবধান থাকে (`10, 500, 9000`), তখন `মান -> :লেবেল` আকারে লেখা থাকে।",
                        ur = "• **لوپ کی پہچان**: جب `goto` اوپر والے لیبل پر واپس کودتا ہے تو وہ `for` یا `while` لوپ ہوتا ہے!\n• **`packed-switch`**: لگاتار نمبروں (`0, 1, 2`) کے لیے۔\n• **`sparse-switch`**: فاصلے والے نمبروں (`10, 500`) کے لیے۔",
                        hi = "• **लूप पहचानें**: जब भी `goto` ऊपर स्थित किसी लेबल पर वापस कूदता है, तो वह `for` या `while` लूप है!\n• **`packed-switch`**: लगातार संख्याओं (`0, 1, 2`) के लिए।\n• **`sparse-switch`**: दूर-दूर की संख्याओं (`10, 500`) के लिए।",
                        ar = "• **تمييز الحلقات**: أي تعليمة `goto` تقفز للأعلى نحو علامة سابقة تمثل حلقة تكرار!\n• **`packed-switch`**: للقيم المتتالية (`0, 1, 2`).\n• **`sparse-switch`**: للقيم المتباعدة (`10, 500`).",
                        fa_af = "• **تشخیص حلقه**: هرگاه دستور `goto` به یک برچسب در خطوط بالاتر بپرد، یک حلقه تکرار دارید!\n• **`packed-switch`**: برای اعداد متوالی (`0, 1, 2`).\n• **`sparse-switch`**: برای اعداد پراکنده (`10, 500`).",
                        in_id = "• **Mengenali Loop**: Saat `goto` melompat ke label di atasnya, itu adalah loop `for`/`while`!\n• **`packed-switch`**: untuk angka berurutan (`0, 1, 2`).\n• **`sparse-switch`**: untuk angka berjauhan (`10, 500`)."
                    ),
                    smaliCode = """.method public static menuLabel(I)Ljava/lang/String;
    .locals 1
    packed-switch p0, :pswitch_data_0
    const-string v0, "Other"
    return-object v0

    :pswitch_0
    const-string v0, "Home"
    return-object v0

    :pswitch_1
    const-string v0, "Settings"
    return-object v0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method"""
                )
            )
        ),

        // MODULE 11
        Lesson(
            id = "smali_11",
            track = TrackType.SMALI,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "11. Instance & Static Fields (iget/iput vs sget/sput)",
                bn = "১১. ইনস্ট্যান্স ও স্ট্যাটিক ফিল্ড (iget/iput বনাম sget/sput)",
                ur = "11. انسٹانس اور اسٹیٹک فیلڈز (iget/iput بمقابلہ sget/sput)",
                hi = "11. इंस्टेंस और स्टैटिक फ़ील्ड (iget/iput बनाम sget/sput)",
                ar = "11. حقول الكائنات والحقول الثابتة (iget/iput مقابل sget/sput)",
                fa_af = "۱۱. فیلدهای شیء و استاتیک (تفاوت iget/iput با sget/sput)",
                in_id = "11. Field Instance & Static (iget/iput vs sget/sput)"
            ),
            subtitle = localized(
                en = "Read and modify class properties safely using exact type suffixes (-object, -boolean, -wide).",
                bn = "ক্লাসের ভেতরের ভেরিয়েবল পড়া (`get`) ও পরিবর্তন করার (`put`) সম্পূর্ণ নিয়ম এবং টাইপ সাফিক্স।",
                ur = "کلاس کے متغیرات پڑھنے (`get`) اور بدلنے (`put`) کا مکمل طریقہ۔",
                hi = "क्लास के वेरिएबल पढ़ने (`get`) और बदलने (`put`) का पूरा नियम तथा टाइप सफ़िक्स।",
                ar = "قراءة وتعديل خصائص الفئات بأمان باستخدام لواحق الأنواع الدقيقة.",
                fa_af = "خواندن و تغییر متغیرهای کلاس با استفاده صحیح از پسوندهای نوع داده.",
                in_id = "Baca dan ubah properti kelas secara aman memakai akhiran tipe yang tepat."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "4 Field Opcode Families You Will See Everywhere",
                        bn = "ফিল্ডের ৪টি অপকোড পরিবার যা প্রতিটি অ্যাপে দেখবেন",
                        ur = "فیلڈز کے 4 خاندان جو ہر ایپ میں نظر آتے ہیں",
                        hi = "फ़ील्ड के 4 ऑपकोड समूह जो हर ऐप में दिखते हैं",
                        ar = "العائلات الأربع لتعليمات الحقول في كل تطبيق",
                        fa_af = "۴ خانواده اصلی دستورات فیلد در تمامی برنامه‌ها",
                        in_id = "4 Keluarga Opcode Field di Setiap Aplikasi"
                    ),
                    content = localized(
                        en = "• `iget` / `iget-object` / `iget-boolean` / `iget-wide` vDest, vObj, Field → Reads an instance field from `vObj` (often `p0`).\n• `iput*` vSrc, vObj, Field → Writes `vSrc` into `vObj`'s instance field.\n• `sget*` vDest, Field → Reads a `static` field (NO object register needed!).\n• `sput*` vSrc, Field → Writes `vSrc` into a `static` field.",
                        bn = "• `iget` / `iget-object` / `iget-boolean` / `iget-wide` → নির্দিষ্ট অবজেক্ট (`p0`) থেকে ফিল্ডের মান পড়ে আনে।\n• `iput*` → অবজেক্টের ফিল্ডে নতুন মান জমা রাখে।\n• `sget*` → `static` ফিল্ড থেকে মান পড়ে (এতে কোনো অবজেক্ট রেজিস্টার লাগে না!)।\n• `sput*` → `static` ফিল্ডে নতুন মান লেখে।",
                        ur = "• `iget*` → آبجیکٹ (`p0`) سے فیلڈ کی قیمت پڑھتا ہے۔\n• `iput*` → آبجیکٹ کے فیلڈ میں قیمت لکھتا ہے۔\n• `sget*` → `static` فیلڈ پڑھتا ہے (آبجیکٹ رجسٹر کی ضرورت نہیں)۔\n• `sput*` → `static` فیلڈ میں قیمت لکھتا ہے۔",
                        hi = "• `iget*` → किसी ऑब्जेक्ट (`p0`) से फ़ील्ड पढ़ता है।\n• `iput*` → ऑब्जेक्ट के फ़ील्ड में मान लिखता है।\n• `sget*` → `static` फ़ील्ड पढ़ता है (ऑब्जेक्ट रजिस्टर नहीं लगता)।\n• `sput*` → `static` फ़ील्ड में मान लिखता है।",
                        ar = "• `iget*`: قراءة حقل من كائن (`p0`).\n• `iput*`: كتابة قيمة داخل حقل كائن.\n• `sget*`: قراءة حقل مشترك `static` دون مسجل كائن.\n• `sput*`: كتابة قيمة داخل حقل `static`.",
                        fa_af = "• `iget*`: خواندن فیلد از روی شیء (`p0`).\n• `iput*`: نوشتن مقدار در فیلد شیء.\n• `sget*`: خواندن فیلد `static` (بدون نیاز به رجستر شیء).\n• `sput*`: نوشتن مقدار در فیلد `static`.",
                        in_id = "• `iget*`: baca field dari objek (`p0`).\n• `iput*`: tulis nilai ke field objek.\n• `sget*`: baca field `static` tanpa register objek.\n• `sput*`: tulis nilai ke field `static`."
                    ),
                    smaliCode = """# Instance field read & increment: this.coins += 10
iget v0, p0, Lcom/example/Wallet;->coins:I
add-int/lit8 v0, v0, 0xa
iput v0, p0, Lcom/example/Wallet;->coins:I

# Static field read: Config.IS_DEBUG
sget-boolean v1, Lcom/example/Config;->IS_DEBUG:Z"""
                )
            )
        ),

        // MODULE 12
        Lesson(
            id = "smali_12",
            track = TrackType.SMALI,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "12. Heap Objects (<init>), Arrays & Type Casting",
                bn = "১২. নতুন অবজেক্ট তৈরি (<init>), অ্যারে এবং টাইপ কাস্টিং",
                ur = "12. نیا آبجیکٹ بنانا (<init>)، ایرے اور ٹائپ کاسٹنگ",
                hi = "12. नया ऑब्जेक्ट बनाना (<init>), ऐरे और टाइप कास्टिंग",
                ar = "12. إنشاء الكائنات (<init>) والمصفوفات وفحص الأنواع",
                fa_af = "۱۲. ساخت اشیاء در هیپ (<init>)، آرایه‌ها و تغییر نوع",
                in_id = "12. Objek Heap (<init>), Array & Pemeriksaan Tipe"
            ),
            subtitle = localized(
                en = "Why new-instance ALWAYS requires invoke-direct <init>, plus new-array, aget/aput, instance-of & check-cast.",
                bn = "কেন `new-instance`-এর পর `invoke-direct <init>` দেওয়া বাধ্যতামূলক এবং অ্যারে ও `check-cast` এর ব্যবহার।",
                ur = "جانیں کہ `new-instance` کے بعد `<init>` کال کرنا کیوں ضروری ہے، نیز ایرے اور `check-cast`۔",
                hi = "जानें कि `new-instance` के बाद `<init>` कॉल करना क्यों अनिवार्य है, साथ ही ऐरे और `check-cast`।",
                ar = "لماذا يتطلب `new-instance` دائماً استدعاء `<init>`، مع شرح المصفوفات و `check-cast`.",
                fa_af = "الزام فراخوانی `<init>` پس از `new-instance`، کار با آرایه‌ها و دستورات `check-cast`.",
                in_id = "Mengapa `new-instance` wajib diikuti `<init>`, serta penggunaan array dan `check-cast`."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Two-Step Object Creation Law & Array Access",
                        bn = "অবজেক্ট তৈরির ২-ধাপের নিয়ম এবং অ্যারে রিড/রাইট",
                        ur = "آبجیکٹ بنانے کے دو لازمی مراحل اور ایرے تک رسائی",
                        hi = "ऑब्जेक्ट निर्माण के दो अनिवार्य चरण और ऐरे एक्सेस",
                        ar = "قانون الخطوتين لإنشاء الكائنات والتعامل مع المصفوفات",
                        fa_af = "قانون دو مرحله‌ای ساخت شیء و خواندن/نوشتن آرایه",
                        in_id = "Aturan 2 Langkah Pembuatan Objek & Akses Array"
                    ),
                    content = localized(
                        en = "1) **Creating an Object (`new File(\"a.txt\")`)**:\n   • Step A: `new-instance v0, Ljava/io/File;` allocates raw uninitialized memory.\n   • Step B: `invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V` initializes it. Using `v0` before `<init>` is rejected by Android's verifier!\n2) **Arrays**: `new-array v0, vSize, [I` creates `int[size]`; `array-length v1, v0` gets length; `aget`/`aput` reads/writes elements.\n3) **Safe Casting**: `instance-of vRes, vObj, LType;` sets `1` or `0`; `check-cast vObj, LType;` throws `ClassCastException` if incompatible.",
                        bn = "১) **নতুন অবজেক্ট তৈরির ২টি বাধ্যতামূলক ধাপ**:\n   • ধাপ ক: `new-instance v0, Ljava/io/File;` মেমোরিতে ফাঁকা জায়গা তৈরি করে।\n   • ধাপ খ: `invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V` কনস্ট্রাক্টর চালিয়ে অবজেক্টটি সচল করে। `<init>` ছাড়া `v0` ব্যবহার করলে অ্যাপ ক্র্যাশ করবে!\n২) **অ্যারে**: `new-array` দিয়ে তৈরি, `array-length` দিয়ে দৈর্ঘ্য মাপা, আর `aget`/`aput` দিয়ে ইনডেক্সের মান পড়া ও লেখা হয়।\n৩) **কাস্টিং**: `instance-of` টাইপ চেক করে `0/1` দেয়, আর `check-cast` অবজেক্টের টাইপ নিশ্চিত করে।",
                        ur = "1) نیا آبجیکٹ دو قدموں میں بنتا ہے: پہلے `new-instance` میموری لیتا ہے اور پھر `invoke-direct` سے `<init>` کنسٹرکٹر چلتا ہے۔\n2) ایرے کے لیے `new-array`، `array-length` اور `aget`/`aput` استعمال ہوتے ہیں۔\n3) ٹائپ چیکنگ کے لیے `instance-of` اور `check-cast` استعمال ہوتے ہیں۔",
                        hi = "1) नया ऑब्जेक्ट हमेशा 2 चरणों में बनता है: `new-instance` मेमोरी बनाता है और `invoke-direct <init>` कंस्ट्रक्टर चलाता है।\n2) ऐरे के लिए `new-array`, `array-length`, `aget` और `aput` होते हैं।\n3) कास्टिंग के लिए `instance-of` और `check-cast` उपयोग होते हैं।",
                        ar = "1) يُنشأ الكائن بخطوتين إلزاميتين: `new-instance` لحجز الذاكرة ثم `invoke-direct` لاستدعاء `<init>`.\n2) للمصفوفات نستخدم `new-array` و `array-length` و `aget`/`aput`.\n3) لفحص النوع وتحويله نستخدم `instance-of` و `check-cast`.",
                        fa_af = "۱) ساخت شیء همیشه ۲ مرحله دارد: تخصیص حافظه با `new-instance` و اجرای سازنده `<init>` با `invoke-direct`.\n۲) برای آرایه از `new-array`، `array-length` و `aget`/`aput` استفاده می‌شود.\n۳) بررسی نوع با `instance-of` و `check-cast` انجام می‌گیرد.",
                        in_id = "1) Objek dibuat dalam 2 langkah wajib: `new-instance` lalu `invoke-direct` ke `<init>`.\n2) Array memakai `new-array`, `array-length`, `aget`/`aput`.\n3) Casting memakai `instance-of` & `check-cast`."
                    ),
                    smaliCode = """# Create int[3] and store 99 at index 0
const/4 v0, 0x3
new-array v1, v0, [I
const/4 v2, 0x0
const/16 v3, 0x63
aput v3, v1, v2"""
                )
            )
        ),

        // MODULE 13
        Lesson(
            id = "smali_13",
            track = TrackType.SMALI,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "13. All 5 invoke-* Calls & /range Register Frames",
                bn = "১৩. ৫ ধরনের invoke-* মেথড কল এবং /range এর নিয়ম",
                ur = "13. تمام 5 invoke-* کالز اور /range رجسٹر فریمز",
                hi = "13. सभी 5 invoke-* मेथड कॉल और /range रजिस्टर नियम",
                ar = "13. أنواع الاستدعاء الخمسة invoke-* وصيغة النطاق /range",
                fa_af = "۱۳. هر ۵ نوع فراخوانی متد invoke-* و حالت /range",
                in_id = "13. Kelima Jenis invoke-* & Frame Register /range"
            ),
            subtitle = localized(
                en = "Know exactly when Dalvik uses virtual, direct, static, super, interface, and invoke-*/range.",
                bn = "কখন virtual, direct, static, super, interface বসে এবং কখন ৫টির বেশি স্লট হলে `/range` লাগে তা শিখুন।",
                ur = "جانیں کہ کب virtual، direct، static، super، interface اور `/range` استعمال ہوتا ہے۔",
                hi = "जानें कि कब virtual, direct, static, super, interface और 5 से अधिक स्लॉट पर `/range` लगता है।",
                ar = "اعرف بدقة متى يستخدم دالفيك كلاً من أنواع الاستدعاء الخمسة ومتى يلزم `/range`.",
                fa_af = "تشخیص دقیق زمان استفاده از virtual، direct، static، super، interface و `/range`.",
                in_id = "Ketahui kapan Dalvik memakai virtual, direct, static, super, interface & `/range`."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The 5 Invocation Modes + The 5-Slot /range Rule",
                        bn = "৫টি invoke মোড এবং ৫-রেজিস্টারের `/range` নিয়ম",
                        ur = "5 invoke طریقے اور 5-رجسٹر والا `/range` اصول",
                        hi = "5 invoke मोड और 5-रजिस्टर `/range` नियम",
                        ar = "أنماط الاستدعاء الخمسة وقاعدة تجاوز 5 مسجلات",
                        fa_af = "۵ حالت فراخوانی متد و قانون بیش از ۵ رجستر در `/range`",
                        in_id = "5 Mode Pemanggilan & Aturan >5 Slot `/range`"
                    ),
                    content = localized(
                        en = "• `invoke-virtual`: Normal overridable public/protected/package methods (`{vObj, args...}`).\n• `invoke-direct`: Constructors (`<init>`) AND `private` methods (non-overridable).\n• `invoke-static`: `static` methods (NO `this` object in `{...}`!).\n• `invoke-super`: Calling parent implementation (`super.onCreate(savedInstanceState)`).\n• `invoke-interface`: Calling methods through a Java/Kotlin `interface` reference.\n• **`/range` Rule**: Standard `invoke-*` encodes at most **5 register slots** (and only `v0..v15`). If you pass >5 slots OR use `v16+`, you MUST use `invoke-virtual/range {v0 .. v6}, ...` with contiguous registers!",
                        bn = "• `invoke-virtual`: সাধারণ সব public/protected মেথড (প্রথম রেজিস্টারটি হলো অবজেক্ট)।\n• `invoke-direct`: যেকোনো কনস্ট্রাক্টর (`<init>`) এবং `private` মেথড।\n• `invoke-static`: `static` মেথড (এখানে `{}` এর ভেতর কোনো `this` অবজেক্ট থাকে না!)।\n• `invoke-super`: প্যারেন্ট ক্লাসের মেথড কল (`super.onCreate`)।\n• `invoke-interface`: ইন্টারফেসের মাধ্যমে মেথড কল (যেমন `List;->size()I`)।\n• **`/range` নিয়ম**: সাধারণ `invoke-*` সর্বোচ্চ **৫টি রেজিস্টার স্লট** (`v0..v15` এর মধ্যে) পাঠাতে পারে। স্লট ৫টির বেশি হলে বা `v16+` রেজিস্টার হলে অবশ্যই ধারাবাহিক রেজিস্টার দিয়ে `invoke-virtual/range {v0 .. v5}` লিখতে হয়!",
                        ur = "• `invoke-virtual`: عام پبلک میتھڈز۔\n• `invoke-direct`: کنسٹرکٹرز (`<init>`) اور `private` میتھڈز۔\n• `invoke-static`: اسٹیٹک میتھڈز (بغیر `this` کے)۔\n• `invoke-super`: پیرنٹ کلاس کا میتھڈ۔\n• `invoke-interface`: انٹرفیس میتھڈز۔\n• **`/range` اصول**: اگر 5 سے زیادہ رجسٹر سلاٹس یا `v16+` استعمال ہوں تو `invoke-*/range {v0 .. v5}` لازمی ہے!",
                        hi = "• `invoke-virtual`: सामान्य ओवरराइडेबल मेथड।\n• `invoke-direct`: कंस्ट्रक्टर (`<init>`) और `private` मेथड।\n• `invoke-static`: `static` मेथड (`this` नहीं होता)।\n• `invoke-super`: पैरेंट मेथड (`super.onCreate`)।\n• `invoke-interface`: इंटरफ़ेस मेथड।\n• **`/range` नियम**: 5 से अधिक स्लॉट या `v16+` होने पर `invoke-*/range {v0 .. v5}` अनिवार्य है!",
                        ar = "• `invoke-virtual`: للدوال العامة القابلة للتجاوز.\n• `invoke-direct`: للبانيات `<init>` والدوال الخاصة `private`.\n• `invoke-static`: للدوال الثابتة بلا `this`.\n• `invoke-super`: لاستدعاء دالة الأب.\n• `invoke-interface`: لدوال الواجهات.\n• **قاعدة `/range`**: عند تمرير أكثر من 5 خانات أو استخدام `v16+` يجب استخدام `invoke-*/range` بمسجلات متجاورة.",
                        fa_af = "• `invoke-virtual`: متدهای معمولی عمومی.\n• `invoke-direct`: سازنده‌ها (`<init>`) و متدهای `private`.\n• `invoke-static`: متدهای استاتیک بدون `this`.\n• `invoke-super`: فراخوانی متد کلاس پدر.\n• `invoke-interface`: متدهای اینترفیس.\n• **قانون `/range`**: برای بیش از ۵ خانه یا رجسترهای `v16` به بالا باید از `invoke-*/range {v0 .. v5}` استفاده شود.",
                        in_id = "• `invoke-virtual`: metode publik biasa.\n• `invoke-direct`: `<init>` & metode `private`.\n• `invoke-static`: metode statis.\n• `invoke-super`: `super.method()`.\n• `invoke-interface`: metode interface.\n• **Aturan `/range`**: lebih dari 5 slot atau memakai `v16+` wajib memakai `invoke-*/range`."
                    ),
                    smaliCode = """# Standard invoke-interface (1 slot: v0)
invoke-interface {v0}, Ljava/util/List;->size()I
move-result v1

# /range call with 6 contiguous register slots (v2..v7)
invoke-static/range {v2 .. v7}, Lcom/example/MathEngine;->blend(IIIIII)I
move-result v0"""
                )
            )
        ),

        // MODULE 14
        Lesson(
            id = "smali_14",
            track = TrackType.SMALI,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "14. Exceptions (.catch / .catchall), throw & Locks",
                bn = "১৪. এক্সেপশন হ্যান্ডলিং (.catch / .catchall), throw ও লক",
                ur = "14. ایکسپشن ہینڈلنگ (.catch / .catchall)، throw اور لاکس",
                hi = "14. एक्सेप्शन हैंडलिंग (.catch / .catchall), throw और लॉक",
                ar = "14. معالجة الاستثناءات (.catch / .catchall) والرمي والأقفال",
                fa_af = "۱۴. مدیریت خطا (.catch / .catchall)، دستور throw و قفل‌ها",
                in_id = "14. Penanganan Eksepsi (.catch / .catchall), throw & Lock"
            ),
            subtitle = localized(
                en = "Trace try-catch label boundaries, capture thrown errors with move-exception, and understand synchronized monitors.",
                bn = "স্মালিতে try-catch ব্লকের বাউন্ডারি লেবেল, `move-exception` এবং থ্রেড লক (`monitor-enter`) কীভাবে কাজ করে।",
                ur = "سمالی میں try-catch لیبلز، `move-exception` اور تھریڈ لاک (`monitor-enter`) کا مکمل طریقہ۔",
                hi = "स्माली में try-catch लेबल सीमाएँ, `move-exception` और `synchronized` मॉनिटर लॉक समझें।",
                ar = "تتبع حدود try-catch والتقط الأخطاء عبر `move-exception` وافهم أقفال التزامن.",
                fa_af = "ردیابی محدوده try-catch، دریافت خطا با `move-exception` و قفل‌های همگام‌سازی.",
                in_id = "Lacak batas label try-catch, `move-exception`, dan monitor sinkronisasi."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "How .catch Directives Map Protected Label Ranges",
                        bn = "কীভাবে .catch ডিরেক্টিভ কোডের সুরক্ষিত অংশ পাহারা দেয়",
                        ur = ".catch ڈائریکٹو کوڈ کے حصے کی حفاظت کیسے کرتا ہے",
                        hi = ".catch डायरेक्टिव संरक्षित कोड रेंज को कैसे जोड़ता है",
                        ar = "كيف تربط توجيهات .catch نطاق الأسطر المحمية بمعالج الخطأ",
                        fa_af = "نحوه محافظت از محدوده کد توسط دایرکتیو .catch",
                        in_id = "Bagaimana Direktif .catch Memetakan Rentang Label"
                    ),
                    content = localized(
                        en = "Smali has no `try { }` curly braces! Instead, it wraps risky instructions between `:try_start_0` and `:try_end_0` and adds a `.catch` rule pointing to `:catch_0`. The VERY FIRST instruction at `:catch_0` (if you need the error object) MUST be `move-exception v0`.\nFor `finally` blocks, compilers emit `.catchall {:try_start_0 .. :try_end_0} :catchall_0`.",
                        bn = "স্মালিতে `try { }` ব্র্যাকেট থাকে না! এর বদলে ঝুঁকিপূর্ণ কোডের শুরুতে `:try_start_0` এবং শেষে `:try_end_0` লেবেল বসানো হয় এবং `.catch` ডিরেক্টিভ দিয়ে বলে দেওয়া হয় এরর ঘটলে `:catch_0`-তে লাফ দিতে। আর `:catch_0` লেবেলের **একদম প্রথম লাইনেই** `move-exception v0` লিখে এরর অবজেক্টটি ধরতে হয়। `finally` ব্লকের জন্য `.catchall` ব্যবহৃত হয়।",
                        ur = "سمالی میں `try` بریکٹس نہیں ہوتے بلکہ `:try_start_0` اور `:try_end_0` لیبلز کے گرد `.catch` لگایا جاتا ہے، اور `:catch_0` کی پہلی لائن میں `move-exception v0` سے ایرر پکڑا جاتا ہے۔",
                        hi = "स्माली में `try { }` ब्रैकेट नहीं होते! इसके बजाय `:try_start_0` और `:try_end_0` के बीच कोड रखकर `.catch` लगाया जाता है, और `:catch_0` की पहली लाइन में `move-exception v0` लिखा जाता है।",
                        ar = "لا توجد أقواس `try` في سمالي، بل تُحاط التعليمات بعلامتي `:try_start_0` و `:try_end_0` مع توجيه `.catch`، ويجب أن يبدأ المعالج بـ `move-exception`.",
                        fa_af = "در سمالی بلوک `try` با برچسب‌های `:try_start_0` و `:try_end_0` و دستور `.catch` مشخص می‌شود و اولین خط در بخش مدیریت خطا `move-exception` است.",
                        in_id = "Smali membungkus kode berisiko di antara `:try_start_0` dan `:try_end_0` dengan `.catch`, lalu menangkap objek error memakai `move-exception`."
                    ),
                    smaliCode = """.method public static safeParse(Ljava/lang/String;)I
    .locals 1
    :try_start_0
    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    move-result v0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0
    return v0

    :catch_0
    move-exception v0
    const/4 v0, -0x1
    return v0
.end method"""
                )
            )
        ),

        // MODULE 15
        Lesson(
            id = "smali_15",
            track = TrackType.SMALI,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "15. Inner Classes ($), Lambda Synthetics & Kotlin Smali",
                bn = "১৫. ইনার ক্লাস ($), ল্যাম্বডা সিনথেটিক ও কোটলিন স্মালি চেনার নিয়ম",
                ur = "15. انر کلاسز ($)، لیمبڈا سنتھیٹکس اور کوٹلن سمالی",
                hi = "15. इनर क्लास ($), लैम्ब्डा सिंथेटिक और कोटलिन स्माली",
                ar = "15. الفئات الداخلية ($) والدوال الاصطناعية وبصمة كوتلن في سمالي",
                fa_af = "۱۵. کلاس‌های داخلی ($)، متدهای مصنوعی لامبدا و سمالی کاتلین",
                in_id = "15. Inner Class ($), Lambda Synthetic & Ciri Smali Kotlin"
            ),
            subtitle = localized(
                en = "Understand MainActivity$1, this$0, ExternalSyntheticLambda, Companion objects, and Intrinsics checks.",
                bn = "আসল প্রফেশনাল অ্যাপে `MainActivity$1`, `this$0`, `ExternalSyntheticLambda` ও কোটলিনের `Intrinsics` কেন থাকে তা বুঝুন।",
                ur = "حقیقی ایپس میں `MainActivity$1`، `this$0`، `ExternalSyntheticLambda` اور کوٹلن کے `Intrinsics` کو سمجھیں۔",
                hi = "असली ऐप्स में `MainActivity$1`, `this$0`, `ExternalSyntheticLambda` और कोटलिन `Intrinsics` को समझें।",
                ar = "افهم ملفات `MainActivity$1` وحقل `this$0` وكائنات Companion وفحوصات كوتلن `Intrinsics`.",
                fa_af = "شناخت فایل‌های `MainActivity$1`، فیلد `this$0`، کلاس‌های لامبدا و بررسی‌های `Intrinsics` در کاتلین.",
                in_id = "Pahami `MainActivity$1`, `this$0`, `ExternalSyntheticLambda`, Companion & `Intrinsics` Kotlin."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Decoding Real-World Kotlin & Compiler-Generated Smali",
                        bn = "আসল অ্যাপের কোটলিন ও কম্পাইলার-তৈরি স্মালি বোঝার গোপন কৌশল",
                        ur = "حقیقی ایپس کے کوٹلن اور کمپائلر سے بنے سمالی کو سمجھنا",
                        hi = "वास्तविक ऐप्स के कोटलिन और कंपाइलर-जनित स्माली को समझना",
                        ar = "فك شيفرة الأنماط التي يولدها مترجم كوتلن و D8 تلقائياً",
                        fa_af = "رمزگشایی الگوهای تولیدشده توسط کامپایلر کاتلین و D8 در برنامه‌های واقعی",
                        in_id = "Menguraikan Pola Smali Hasil Kompilasi Kotlin & D8"
                    ),
                    content = localized(
                        en = "When reading modern Android APKs, you will constantly spot these 4 patterns:\n1) **`Outer\$Inner.smali` & `this\$0`**: DEX has no nested classes—inner classes become separate files holding a hidden `this\$0` field pointing back to the parent class.\n2) **`\$\$ExternalSyntheticLambda0`**: D8 compiles Java/Kotlin lambdas `{ view -> ... }` into lightweight synthetic classes.\n3) **`Intrinsics;->checkNotNullParameter`**: Kotlin automatically inserts null checks at the start of public methods accepting non-null objects!\n4) **`Class\$Companion`**: Kotlin `companion object` compiles to a nested `Companion` singleton field.",
                        bn = "আধুনিক অ্যান্ড্রয়েড অ্যাপ খুললে আপনি সবসময় এই ৪টি জিনিস দেখবেন:\n১) **`MainActivity\$1.smali` ও `this\$0`**: DEX-এ ক্লাসের ভেতর ক্লাস থাকে না; তাই Inner বা Anonymous ক্লাসগুলো `\$` চিহ্ন দিয়ে আলাদা ফাইল হয় এবং প্যারেন্ট ক্লাসকে ধরার জন্য ভেতরে গোপন `this\$0` ফিল্ড রাখে।\n২) **`\$\$ExternalSyntheticLambda0`**: বাটনের `{ v -> ... }` ল্যাম্বডা কোডকে D8 কম্পাইলার এরকম সিনথেটিক ক্লাসে রূপান্তর করে।\n৩) **`Intrinsics;->checkNotNullParameter`**: কোটলিন মেথডের শুরুতে নাল-সেফটি নিশ্চিত করতে স্বয়ংক্রিয়ভাবে এই চেক বসায়!\n৪) **`Companion`**: কোটলিনের `companion object` আলাদা `Class\$Companion` হিসেবে কম্পাইল হয়।",
                        ur = "جدید ایپس میں یہ 4 چیزیں ہمیشہ نظر آتی ہیں:\n1) `Outer\$Inner` اور پوشیدہ `this\$0` فیلڈ۔\n2) لیمبڈا کے لیے `\$\$ExternalSyntheticLambda0` کلاسز۔\n3) کوٹلن کی نل سیفٹی چیک `Intrinsics;->checkNotNullParameter`۔\n4) کوٹلن کا `Companion` سنگلٹن آبجیکٹ۔",
                        hi = "आधुनिक एंड्रॉइड ऐप्स में ये 4 पैटर्न हमेशा दिखते हैं:\n1) `Outer\$Inner` फ़ाइलें और छिपा `this\$0` फ़ील्ड।\n2) लैम्ब्डा के लिए `\$\$ExternalSyntheticLambda0` क्लास।\n3) कोटलिन नल-सेफ्टी जांच `Intrinsics;->checkNotNullParameter`।\n4) कोटलिन `Companion` ऑब्जेक्ट।",
                        ar = "في التطبيقات الحديثة ستصادف دائماً 4 أنماط:\n1) الفئات الفرعية بعلامة `\$` وحقل `this\$0` المخفي.\n2) فئات `\$\$ExternalSyntheticLambda0` للدوال السهمية.\n3) فحص القيم الفارغة التلقائي `Intrinsics;->checkNotNullParameter` في كوتلن.\n4) كائن `Companion` الثابت.",
                        fa_af = "در برنامه‌های مدرن اندروید همواره این ۴ الگو را می‌بینید:\n۱) فایل‌های `Outer\$Inner` و فیلد مخفی `this\$0`.\n۲) کلاس‌های `\$\$ExternalSyntheticLambda0` برای لامبداها.\n۳) بررسی خودکار نال در کاتلین با `Intrinsics;->checkNotNullParameter`.\n۴) شیء `Companion` در کاتلین.",
                        in_id = "Pada APK modern Anda akan selalu melihat 4 pola ini:\n1) File `Outer\$Inner` & field `this\$0`.\n2) Kelas `\$\$ExternalSyntheticLambda0`.\n3) Pemeriksaan null Kotlin `Intrinsics;->checkNotNullParameter`.\n4) Objek `Class\$Companion`."
                    ),
                    kotlinCode = """fun greetUser(user: String): String {
    return "Welcome, " + user
}""",
                    smaliCode = """.method public final greetUser(Ljava/lang/String;)Ljava/lang/String;
    .locals 2
    const-string v0, "user"
    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    new-instance v0, Ljava/lang/StringBuilder;
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V
    const-string v1, "Welcome, "
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v0
    return-object v0
.end method"""
                )
            )
        ),

        // MODULE 16
        Lesson(
            id = "smali_16",
            track = TrackType.SMALI,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "16. Pro Architecture: Multidex, Log.d Tracing & ART Verifier",
                bn = "১৬. প্রো আর্কিটেকচার: মাল্টিডেক্স, Log.d ডিবাগিং ও ART ভেরিফায়ার আইন",
                ur = "16. پرو آرکیٹیکچر: ملٹی ڈیکس، Log.d ٹریسنگ اور ART ویریفائر قوانین",
                hi = "16. प्रो आर्किटेक्चर: मल्टीडेक्स, Log.d ट्रेसिंग और ART वेरिफ़ायर नियम",
                ar = "16. الاحتراف: تعدد ملفات DEX وتتبع Log.d وقوانين فاحص ART",
                fa_af = "۱۶. معماری حرفه‌ای: مالتی‌دکس، دیباگ با Log.d و قوانین ART Verifier",
                in_id = "16. Arsitektur Pro: Multidex, Pelacakan Log.d & Verifier ART"
            ),
            subtitle = localized(
                en = "Why 65,536 methods split into classes2.dex, safe Log.d tracing, and the 5 rules that prevent VerifyError.",
                bn = "কেন ৬৫,৫৩৬ মেথডের পর classes2.dex তৈরি হয়, নিরাপদে Log.d বসানো এবং VerifyError এড়ানোর ৫টি মহা-আইন।",
                ur = "65,536 میتھڈز کی حد، محفوظ Log.d ڈیبگنگ اور VerifyError سے بچنے کے 5 بنیادی قوانین۔",
                hi = "65,536 मेथड सीमा (classes2.dex), सुरक्षित Log.d डीबगिंग और VerifyError रोकने के 5 स्वर्ण नियम।",
                ar = "سبب انقسام DEX عند 65,536 دالة، حقن سجلات Log.d بأمان، والقوانين الخمسة لتجنب VerifyError.",
                fa_af = "محدودیت ۶۵٬۵۳۶ متد در DEX، ثبت لاگ ایمن با Log.d و ۵ قانون طلایی جلوگیری از VerifyError.",
                in_id = "Batas 65.536 metode (classes2.dex), pelacakan Log.d aman & 5 hukum pencegah VerifyError."
            ),
            estimatedMinutes = 12,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "64K Multidex Limit & The 5 ART Bytecode Verifier Laws",
                        bn = "৬৪ হাজার মেথডের সীমা এবং অ্যান্ড্রয়েড ART ভেরিফায়ারের ৫টি অলঙ্ঘনীয় আইন",
                        ur = "64K ملٹی ڈیکس کی حد اور ART بائٹ کوڈ ویریفائر کے 5 قوانین",
                        hi = "64K मल्टीडेक्स सीमा और ART बाइटकोड वेरिफ़ायर के 5 अटल नियम",
                        ar = "حد 64 ألف دالة والقوانين الخمسة الصارمة لفاحص ART",
                        fa_af = "مرز ۶۴ هزار متد و ۵ قانون سخت‌گیرانه بررسی‌کننده بایت‌کد ART",
                        in_id = "Batas 64K Multidex & 5 Hukum Ketat Bytecode Verifier ART"
                    ),
                    content = localized(
                        en = "A single DEX file indexes method IDs with 16-bit unsigned integers (`65,536` max). Large apps split classes across `classes.dex`, `classes2.dex` (`smali_classes2/`), etc.\n\nBefore Android ART runs ANY method, its static **Bytecode Verifier** checks 5 strict laws:\n1) **Register Bounds**: Never reference `vN` >= `.locals` without bumping `.locals` (and remember bumping `.locals` shifts `v` indices of parameters if not using `pX`!).\n2) **Type Safety**: Never pass an `I` register into `return-object` or `invoke-*` expecting `L...;`.\n3) **Wide Integrity**: Reading half of a 64-bit `(v0, v1)` pair after overwriting `v1` fails verification.\n4) **All Paths Must Return/Throw**: Every branch path must end in `return*`, `goto`, or `throw`.\n5) **4-Bit Opcode Limit**: Standard `const/4` and 5-arg `invoke-*` only accept `v0..v15`!",
                        bn = "একটি `.dex` ফাইলে সর্বোচ্চ `65,536`টি মেথড থাকতে পারে। বড় অ্যাপে তাই `classes.dex`, `classes2.dex` (`smali_classes2/` ফোল্ডার) তৈরি হয়।\n\nঅ্যান্ড্রয়েড ART কোনো মেথড চালানোর আগেই তার **Bytecode Verifier** এই ৫টি কঠোর আইন পরীক্ষা করে:\n১) **রেজিস্টার সীমা**: `.locals`-এর চেয়ে বড় `v` রেজিস্টার ছোঁয়া যাবে না (নতুন রেজিস্টার লাগলে `.locals` বাড়ান)।\n২) **সঠিক রিটার্ন টাইপ**: অবজেক্ট রিটার্ন মেথডে `return v0` নয়, অবশ্যই `return-object v0` লিখতে হবে।\n৩) **৬৪-বিট জোড়া অক্ষত রাখা**: `long`/`double` জোড়ার অর্ধেক মুছে ফেলা যাবে না।\n৪) **প্রতিটি রাস্তার সমাপ্তি**: শর্তের প্রতিটি শাখা `return*`, `goto` বা `throw` দিয়ে শেষ হতে হবে।\n৫) **`v15` সীমা**: সাধারণ `const/4` এবং সাধারণ `invoke-*` কেবল `v0..v15` পর্যন্ত সাপোর্ট করে (`v16+` হলে `const/16` বা `invoke-*/range` লাগে)!",
                        ur = "ایک `.dex` فائل میں زیادہ سے زیادہ `65,536` میتھڈز آ سکتے ہیں، اس سے زیادہ ہونے پر `classes2.dex` بنتی ہے۔\n\nاینڈرائیڈ ART ویریفائر کے 5 لازمی اصول:\n1) `.locals` کی حد سے بڑا رجسٹر استعمال نہ کریں۔\n2) آبجیکٹ کے لیے ہمیشہ `return-object` اور `move-result-object` لکھیں۔\n3) 64-بٹ جوڑے کو ادھورا خراب نہ کریں۔\n4) ہر مشروط راستہ `return*`، `goto` یا `throw` پر ختم ہو۔\n5) `v16` یا اس سے بڑے رجسٹر پر `invoke-*/range` استعمال کریں۔",
                        hi = "एक `.dex` फ़ाइल में अधिकतम `65,536` मेथड हो सकते हैं, अधिक होने पर `classes2.dex` बनती है।\n\nएंड्रॉइड ART वेरिफ़ायर के 5 कड़े नियम:\n1) `.locals` सीमा से बाहर का रजिस्टर कभी उपयोग न करें।\n2) ऑब्जेक्ट के लिए हमेशा `return-object` और `move-result-object` लिखें।\n3) 64-बिट रजिस्टर जोड़े को अधूरा न तोड़ें।\n4) हर शाखा `return*`, `goto` या `throw` पर समाप्त हो।\n5) `v16+` रजिस्टर होने पर `invoke-*/range` का उपयोग करें।",
                        ar = "يستوعب ملف DEX الواحد 65,536 دالة كحد أقصى، ثم ينقسم إلى `classes2.dex`.\n\nالقوانين الخمسة لفاحص ART:\n1) عدم تجاوز عدد `.locals`.\n2) مطابقة نوع الإرجاع (`return-object` للكائنات).\n3) الحفاظ على سلامة أزواج 64-بت.\n4) انتهاء كل مسار برمجي بإرجاع أو قفز أو رمي استثناء.\n5) التزام حد `v0..v15` أو استخدام `/range`.",
                        fa_af = "هر فایل DEX حداکثر ۶۵٬۵۳۶ متد جا می‌دهد و در برنامه‌های بزرگ `classes2.dex` ساخته می‌شود.\n\n۵ قانون بررسی‌کننده ART:\n۱) عدم تجاوز از محدوده `.locals`.\n۲) تطابق دقیق نوع بازگشتی (`return-object` برای اشیاء).\n۳) حفظ یکپارچگی جفت رجسترهای ۶۴ بیتی.\n۴) پایان یافتن همه مسیرها با `return*`، `goto` یا `throw`.\n۵) رعایت محدودیت `v0..v15` یا استفاده از `/range`.",
                        in_id = "Satu file DEX menampung maks 65.536 metode, selebihnya masuk `classes2.dex`.\n\n5 Hukum Verifier ART:\n1) Jangan melebihi batas `.locals`.\n2) Cocokkan tipe return (`return-object` untuk objek).\n3) Jaga keutuhan pasangan 64-bit.\n4) Semua jalur cabang wajib berakhir di `return*`/`goto`/`throw`.\n5) Gunakan `/range` jika memakai register `v16+`."
                    ),
                    smaliCode = """# Clean Logcat trace in Smali without corrupting parameters
.method public onPayClicked(Ljava/lang/String;)V
    .locals 2    # reserve v0, v1 safely without touching p0/p1
    const-string v0, "SmaliAudit"
    invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    return-void
.end method"""
                )
            )
        )
    )
}
