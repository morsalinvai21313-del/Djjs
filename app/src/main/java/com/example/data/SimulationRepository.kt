package com.example.data

import com.example.model.RegisterSimulation
import com.example.model.SimulationStep
import com.example.model.localized

object SimulationRepository {
    val simulations = listOf(
        RegisterSimulation(
            id = "sim_01_add",
            titleContent = localized(
                en = "01. Arithmetic Register Flow",
                bn = "০১. যোগ ও গুণের রেজিস্টার প্রবাহ",
                ur = "01. حسابی رجسٹر فلو",
                hi = "01. अंकगणितीय रजिस्टर प्रवाह",
                ar = "01. تدفق المسجلات الحسابية",
                fa_af = "۰۱. جریان محاسبات در رجسترها",
                in_id = "01. Aliran Register Aritmetika"
            ),
            descriptionContent = localized(
                en = "Watch v0, v1, p0, and p1 update step-by-step during addition and return.",
                bn = "যোগ ও রিটার্ন করার সময় প্রতিটি ধাপে v0, v1, p0 এবং p1 এর মান কীভাবে বদলায় তা দেখুন।",
                ur = "جمع اور ریٹرن کے دوران ہر قدم پر رجسٹرز کی تبدیلی دیکھیں۔",
                hi = "जोड़ और रिटर्न के दौरान हर चरण में v0, v1, p0, p1 का बदलाव देखें।",
                ar = "شاهد تغير قيم المسجلات خطوة بخطوة أثناء الجمع والإرجاع.",
                fa_af = "تغییر گام‌به‌گام مقادیر v0، v1، p0 و p1 هنگام جمع و بازگشت را ببینید.",
                in_id = "Amati perubahan v0, v1, p0, dan p1 langkah demi langkah saat penjumlahan."
            ),
            codeSnippet = """.method public static compute(II)I
    .locals 2
    const/16 v0, 0xa
    add-int v1, p0, p1
    add-int v0, v0, v1
    return v0
.end method""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = ".locals 2  # compute(20, 30)",
                    changedRegister = "p0, p1",
                    registerValue = "20, 30",
                    registersSnapshot = mapOf("v0" to "0 (uninit)", "v1" to "0 (uninit)", "p0" to "20 (0x14)", "p1" to "30 (0x1e)"),
                    explanationContent = localized(
                        en = "Frame created: v0 and v1 are empty scratch registers; static args arrive in p0=20 and p1=30.",
                        bn = "মেমোরি তৈরি হলো: `v0` ও `v1` খালি লোকাল স্লট; আর ইনপুট এসেছে `p0 = 20` এবং `p1 = 30`।",
                        ur = "فریم تیار: `v0` اور `v1` خالی ہیں جبکہ ان پٹ `p0=20` اور `p1=30` آ چکے ہیں۔",
                        hi = "फ़्रेम तैयार: `v0` व `v1` खाली हैं; स्टैटिक इनपुट `p0=20` और `p1=30` में आए हैं।",
                        ar = "تم إنشاء الإطار: `v0` و `v1` فارغان والمعاملات وصلت في `p0=20` و `p1=30`.",
                        fa_af = "قاب ساخته شد: `v0` و `v1` خالی هستند و ورودی‌ها در `p0=20` و `p1=30` قرار گرفتند.",
                        in_id = "Frame dibuat: `v0` & `v1` kosong; argumen masuk di `p0=20` dan `p1=30`."
                    )
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "const/16 v0, 0xa",
                    changedRegister = "v0",
                    registerValue = "10 (0xa)",
                    registersSnapshot = mapOf("v0" to "10 (0xa)", "v1" to "0 (uninit)", "p0" to "20 (0x14)", "p1" to "30 (0x1e)"),
                    explanationContent = localized(
                        en = "Loads hex constant 0xa (decimal 10) into local register v0.",
                        bn = "হেক্স সংখ্যা `0xa` (দশমিকে ১০) লোকাল রেজিস্টার `v0`-তে লোড হলো।",
                        ur = "ہیکس عدد `0xa` (یعنی 10) رجسٹر `v0` میں لوڈ ہوا۔",
                        hi = "हेक्स स्थिरांक `0xa` (दशमलव 10) रजिस्टर `v0` में लोड हुआ।",
                        ar = "تحميل الثابت `0xa` (أي 10) داخل المسجل المحلي `v0`.",
                        fa_af = "عدد ثابت `0xa` (معادل ۱۰) در رجستر محلی `v0` بارگذاری شد.",
                        in_id = "Memuat konstanta hex `0xa` (desimal 10) ke register lokal `v0`."
                    )
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "add-int v1, p0, p1",
                    changedRegister = "v1",
                    registerValue = "50 (0x32)",
                    registersSnapshot = mapOf("v0" to "10 (0xa)", "v1" to "50 (0x32)", "p0" to "20 (0x14)", "p1" to "30 (0x1e)"),
                    explanationContent = localized(
                        en = "Adds p0 (20) + p1 (30) and writes 50 into v1.",
                        bn = "`p0` (২০) ও `p1` (৩০) যোগ করে যোগফল ৫০ (`0x32`) রাখা হলো `v1`-এ।",
                        ur = "`p0` (20) اور `p1` (30) کو جمع کر کے 50 `v1` میں رکھا گیا۔",
                        hi = "`p0` (20) और `p1` (30) जोड़कर 50 को `v1` में लिखा गया।",
                        ar = "جمع `p0` (20) مع `p1` (30) ووضع الناتج 50 في `v1`.",
                        fa_af = "مقادیر `p0` (۲۰) و `p1` (۳۰) جمع شده و حاصل ۵۰ در `v1` قرار گرفت.",
                        in_id = "Menjumlahkan `p0` (20) + `p1` (30) dan menyimpan 50 ke `v1`."
                    )
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "add-int v0, v0, v1",
                    changedRegister = "v0",
                    registerValue = "60 (0x3c)",
                    registersSnapshot = mapOf("v0" to "60 (0x3c)", "v1" to "50 (0x32)", "p0" to "20 (0x14)", "p1" to "30 (0x1e)"),
                    explanationContent = localized(
                        en = "Adds v0 (10) + v1 (50) and overwrites v0 with 60.",
                        bn = "`v0` (১০) এবং `v1` (৫০) যোগ করে নতুন মান ৬০ (`0x3c`) বসানো হলো `v0`-তে।",
                        ur = "`v0` (10) اور `v1` (50) کو جمع کر کے 60 واپس `v0` میں لکھا گیا۔",
                        hi = "`v0` (10) और `v1` (50) जोड़कर 60 को `v0` में ओवरराइट किया गया।",
                        ar = "جمع `v0` (10) مع `v1` (50) وتحديث `v0` بالقيمة 60.",
                        fa_af = "`v0` (۱۰) با `v1` (۵۰) جمع شد و مقدار جدید ۶۰ جایگزین `v0` گردید.",
                        in_id = "Menjumlahkan `v0` (10) + `v1` (50) dan menimpa `v0` menjadi 60."
                    )
                ),
                SimulationStep(
                    stepIndex = 5,
                    codeLine = "return v0",
                    changedRegister = "RETURN",
                    registerValue = "60",
                    registersSnapshot = mapOf("v0" to "60 (0x3c)", "v1" to "50 (0x32)", "RETURN" to "60"),
                    explanationContent = localized(
                        en = "Method pops its register frame and hands integer 60 back to caller.",
                        bn = "মেথডের কাজ শেষ—ফলাফল হিসেবে পূর্ণসংখ্যা `60` কলারের কাছে ফেরত পাঠানো হলো!",
                        ur = "میتھڈ مکمل ہوا اور نتیجہ `60` واپس بھیج دیا گیا!",
                        hi = "मेथड समाप्त हुआ और परिणाम `60` कॉलर को लौटा दिया गया!",
                        ar = "انتهت الدالة وأعادت القيمة النهائية 60 للمستدعي!",
                        fa_af = "اجرای متد پایان یافت و عدد ۶۰ به عنوان خروجی بازگردانده شد!",
                        in_id = "Metode selesai dan mengembalikan nilai akhir 60 ke pemanggil!"
                    )
                )
            )
        ),

        RegisterSimulation(
            id = "sim_02_branch",
            titleContent = localized(
                en = "02. Conditional Branch Jump (if-eqz)",
                bn = "০২. শর্তসাপেক্ষ জাম্প (if-eqz ব্রাঞ্চিং)",
                ur = "02. مشروط جمپ (if-eqz)",
                hi = "02. शर्त शाखा जंप (if-eqz)",
                ar = "02. القفز الشرطي (if-eqz)",
                fa_af = "۰۲. پرش شرطی با دستور if-eqz",
                in_id = "02. Lompatan Cabang Bersyarat (if-eqz)"
            ),
            descriptionContent = localized(
                en = "See how Dalvik tests boolean 0 (false) and jumps straight over the true block.",
                bn = "ইনপুট `false` (`0`) হলে ডালভিক কীভাবে মাঝখানের লাইন টপকে সরাসরি `:is_guest` লেবেলে লাফ দেয় দেখুন।",
                ur = "دیکھیں کہ ان پٹ `0` ہونے پر ڈالوک کس طرح درمیان کی لائنیں چھوڑ کر جمپ کرتا ہے۔",
                hi = "देखें कि `false` (`0`) होने पर डाल्विक बीच का ब्लॉक छोड़कर सीधे लेबल पर कैसे कूदता है।",
                ar = "شاهد كيف يختبر دالفيك القيمة 0 ويقفز فوق الكتلة الأولى مباشرة.",
                fa_af = "ببینید وقتی ورودی صفر است دالویک چگونه از روی خطوط میانی به برچسب می‌پرد.",
                in_id = "Lihat bagaimana Dalvik menguji `0` dan melompati blok pertama."
            ),
            codeSnippet = """.method public static role(Z)Ljava/lang/String;
    .locals 1
    if-eqz p0, :is_guest
    const-string v0, "ADMIN"
    return-object v0
    :is_guest
    const-string v0, "GUEST"
    return-object v0
.end method""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = "Entry: role(false) -> p0 = 0",
                    changedRegister = "p0",
                    registerValue = "0 (false)",
                    registersSnapshot = mapOf("v0" to "null", "p0" to "0 (false)"),
                    explanationContent = localized(
                        en = "Method invoked with isAdmin = false, encoded as 0 in register p0.",
                        bn = "মেথডটি `false` ইনপুট নিয়ে চালু হয়েছে, তাই `p0` রেজিস্টারে মান আছে `0`।",
                        ur = "میتھڈ `false` کے ساتھ چلا، لہٰذا `p0` میں `0` موجود ہے۔",
                        hi = "मेथड `false` के साथ चला, इसलिए `p0` में `0` रखा है।",
                        ar = "بدأت الدالة بالقيمة `false` الممثلة بالرقم `0` في `p0`.",
                        fa_af = "متد با مقدار `false` فراخوانی شده، پس درون `p0` عدد `0` قرار دارد.",
                        in_id = "Metode dipanggil dengan `false`, direpresentasikan sebagai `0` di `p0`."
                    )
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "if-eqz p0, :is_guest",
                    changedRegister = "PC",
                    registerValue = "JUMP -> :is_guest",
                    registersSnapshot = mapOf("v0" to "null", "p0" to "0 (false)", "PC" to ":is_guest"),
                    explanationContent = localized(
                        en = "Because p0 == 0, if-eqz triggers! Execution skips ADMIN and lands on :is_guest.",
                        bn = "যেহেতু `p0 == 0` সত্য, তাই `if-eqz` কাজ করেছে! কোড ADMIN অংশ বাদ দিয়ে সোজা `:is_guest`-এ লাফ দিয়েছে।",
                        ur = "چونکہ `p0 == 0` سچ ہے، کوڈ ADMIN کو چھوڑ کر سیدھا `:is_guest` پر کود گیا۔",
                        hi = "चूंकि `p0 == 0` सत्य है, निष्पादन ADMIN को छोड़कर सीधे `:is_guest` पर कूद गया।",
                        ar = "بما أن `p0 == 0` تحقق الشرط وقفز المؤشر مباشرة إلى `:is_guest`.",
                        fa_af = "چون `p0 == 0` برقرار است، برنامه از بخش ADMIN پریده و به `:is_guest` رفت.",
                        in_id = "Karena `p0 == 0`, `if-eqz` aktif! Eksekusi melompati ADMIN menuju `:is_guest`."
                    )
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "const-string v0, \"GUEST\"",
                    changedRegister = "v0",
                    registerValue = "\"GUEST\"",
                    registersSnapshot = mapOf("v0" to "\"GUEST\"", "p0" to "0 (false)"),
                    explanationContent = localized(
                        en = "Loads String reference \"GUEST\" into v0.",
                        bn = "`\"GUEST\"` স্ট্রিং রেফারেন্সটি `v0` রেজিস্টারে লোড হলো।",
                        ur = "`\"GUEST\"` اسٹرنگ `v0` میں لوڈ ہو گئی۔",
                        hi = "`\"GUEST\"` स्ट्रिंग रेफरेंस `v0` में लोड हुआ।",
                        ar = "تم تحميل النص `\"GUEST\"` في المسجل `v0`.",
                        fa_af = "رشته `\"GUEST\"` درون رجستر `v0` بارگذاری شد.",
                        in_id = "Memuat referensi String `\"GUEST\"` ke `v0`."
                    )
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "return-object v0",
                    changedRegister = "RETURN",
                    registerValue = "\"GUEST\"",
                    registersSnapshot = mapOf("v0" to "\"GUEST\"", "RETURN" to "\"GUEST\""),
                    explanationContent = localized(
                        en = "Returns object reference \"GUEST\" via return-object.",
                        bn = "`return-object` দিয়ে `\"GUEST\"` অবজেক্টটি ফেরত পাঠানো হলো।",
                        ur = "`return-object` کے ذریعے `\"GUEST\"` واپس بھیج دیا گیا۔",
                        hi = "`return-object` द्वारा `\"GUEST\"` लौटा दिया गया।",
                        ar = "أرجعت الدالة الكائن النصي `\"GUEST\"` بنجاح.",
                        fa_af = "رشته `\"GUEST\"` با دستور `return-object` بازگردانده شد.",
                        in_id = "Mengembalikan objek `\"GUEST\"` melalui `return-object`."
                    )
                )
            )
        ),

        RegisterSimulation(
            id = "sim_03_object",
            titleContent = localized(
                en = "03. Heap Object Lifecycle (<init> + invoke)",
                bn = "০৩. হিপ অবজেক্ট তৈরি ও মেথড কল (<init>)",
                ur = "03. ہیپ آبجیکٹ کی تخلیق (<init>)",
                hi = "03. हीप ऑब्जेक्ट जीवनचक्र (<init> + invoke)",
                ar = "03. دورة حياة الكائن (<init> والاستدعاء)",
                fa_af = "۰۳. ساخت شیء در هیپ و اجرای <init>",
                in_id = "03. Siklus Objek Heap (<init> + invoke)"
            ),
            descriptionContent = localized(
                en = "Trace uninitialized memory turning into a live StringBuilder and capturing move-result-object.",
                bn = "কীভাবে ফাঁকা হিপ মেমোরি `<init>` দিয়ে সচল `StringBuilder` হয় এবং `move-result-object` ফলাফল ধরে দেখুন।",
                ur = "دیکھیں کہ خالی میموری `<init>` کے بعد کیسے فعال آبجیکٹ بنتی ہے۔",
                hi = "देखें कि कच्ची मेमोरी `<init>` के बाद कैसे सक्रिय `StringBuilder` बनती है।",
                ar = "تتبع تحول الذاكرة الخام إلى كائن مهيأ والتقاط النتيجة.",
                fa_af = "مشاهده تبدیل حافظه خام به شیء فعال پس از اجرای `<init>`.",
                in_id = "Lacak memori mentah menjadi `StringBuilder` aktif lewat `<init>`."
            ),
            codeSnippet = """new-instance v0, Ljava/lang/StringBuilder;
invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V
const-string v1, "DEX"
invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
move-result-object v2""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = "new-instance v0, Ljava/lang/StringBuilder;",
                    changedRegister = "v0",
                    registerValue = "@0x8a10 (UNINIT)",
                    registersSnapshot = mapOf("v0" to "@0x8a10 (UNINIT)", "v1" to "null", "v2" to "null"),
                    explanationContent = localized(
                        en = "Allocates raw heap address @0x8a10. Marked UNINIT until <init> runs!",
                        bn = "হিপ মেমোরিতে `@0x8a10` জায়গা তৈরি হলো, কিন্তু `<init>` না চলা পর্যন্ত এটি অসম্পূর্ণ (UNINIT)!",
                        ur = "ہیپ پر جگہ بنی، لیکن `<init>` چلنے تک یہ نامکمل (UNINIT) ہے!",
                        hi = "हीप पता `@0x8a10` बना, पर `<init>` चलने तक यह अपूर्ण (UNINIT) है!",
                        ar = "حُجز العنوان `@0x8a10` ويظل غير مهيأ حتى يعمل الباني `<init>`!",
                        fa_af = "آدرس `@0x8a10` رزرو شد اما تا پیش از اجرای `<init>` غیرقابل استفاده است!",
                        in_id = "Alamat `@0x8a10` dialokasikan namun berstatus UNINIT sebelum `<init>`!"
                    )
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "invoke-direct {v0}, ...-><init>()V",
                    changedRegister = "v0",
                    registerValue = "StringBuilder(\"\")",
                    registersSnapshot = mapOf("v0" to "StringBuilder(\"\")", "v1" to "null", "v2" to "null"),
                    explanationContent = localized(
                        en = "Constructor initializes v0 into a valid empty StringBuilder instance.",
                        bn = "কনস্ট্রাক্টর চলার পর `v0` এখন একটি বৈধ খালি `StringBuilder(\"\")` অবজেক্ট!",
                        ur = "کنسٹرکٹر چلنے کے بعد `v0` اب ایک درست `StringBuilder` بن گیا!",
                        hi = "कंस्ट्रक्टर चलने के बाद `v0` अब वैध `StringBuilder(\"\")` बन गया!",
                        ar = "هيأ الباني المسجل `v0` ليصبح كائناً صالحاً للاستخدام!",
                        fa_af = "پس از اجرای سازنده، اکنون `v0` یک شیء معتبر و آماده است!",
                        in_id = "Konstruktor mengaktifkan `v0` menjadi objek `StringBuilder` valid!"
                    )
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "const-string v1, \"DEX\" + append",
                    changedRegister = "v0, v1",
                    registerValue = "StringBuilder(\"DEX\")",
                    registersSnapshot = mapOf("v0" to "StringBuilder(\"DEX\")", "v1" to "\"DEX\"", "v2" to "null"),
                    explanationContent = localized(
                        en = "v1 loads \"DEX\" and invoke-virtual mutates v0's internal buffer to \"DEX\".",
                        bn = "`v1`-এ `\"DEX\"` লোড হয়ে `append` কলের মাধ্যমে `v0`-এর ভেতরে যুক্ত হলো।",
                        ur = "`v1` میں `\"DEX\"` لوڈ ہو کر `v0` کے اندر شامل ہو گیا۔",
                        hi = "`v1` में `\"DEX\"` लोड होकर `v0` के बफ़र में जुड़ गया।",
                        ar = "تم تحميل `\"DEX\"` في `v1` وإضافتها داخل مخزن `v0`.",
                        fa_af = "متن `\"DEX\"` در `v1` قرار گرفت و به محتوای `v0` اضافه شد.",
                        in_id = "`v1` memuat `\"DEX\"` lalu `append` menambahkannya ke buffer `v0`."
                    )
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "toString() + move-result-object v2",
                    changedRegister = "v2",
                    registerValue = "\"DEX\"",
                    registersSnapshot = mapOf("v0" to "StringBuilder(\"DEX\")", "v1" to "\"DEX\"", "v2" to "\"DEX\" (String)"),
                    explanationContent = localized(
                        en = "toString() places a new immutable String in the result latch, immediately captured into v2!",
                        bn = "`toString()` থেকে আসা চূড়ান্ত স্ট্রিংটি পরের লাইনেই `move-result-object v2` দিয়ে ধরা হলো!",
                        ur = "`toString()` کا نتیجہ فوراً اگلی لائن میں `move-result-object v2` سے پکڑ لیا گیا!",
                        hi = "`toString()` का परिणाम अगली ही पंक्ति में `move-result-object v2` द्वारा पकड़ लिया गया!",
                        ar = "التقطت `move-result-object v2` النص النهائي فور انتهاء `toString()`!",
                        fa_af = "خروجی `toString()` بلافاصله در خط بعد با `move-result-object v2` دریافت شد!",
                        in_id = "Hasil `toString()` langsung ditangkap ke `v2` oleh `move-result-object`!"
                    )
                )
            )
        ),

        RegisterSimulation(
            id = "sim_04_this_vs_static",
            titleContent = localized(
                en = "04. Instance 'p0 = this' vs Field Update",
                bn = "০৪. ইনস্ট্যান্স মেথডে 'p0 = this' ও ফিল্ড আপডেট",
                ur = "04. انسٹانس میتھڈ میں 'p0 = this' اور فیلڈ اپڈیٹ",
                hi = "04. इंस्टेंस मेथड में 'p0 = this' और फ़ील्ड अपडेट",
                ar = "04. مؤشر 'p0 = this' وتحديث حقول الكائن",
                fa_af = "۰۴. نقش 'p0 = this' و بروزرسانی فیلد شیء",
                in_id = "04. Pointer 'p0 = this' & Pembaruan Field"
            ),
            descriptionContent = localized(
                en = "Observe how p0 holds 'this' while p1 holds the first argument during iget and iput.",
                bn = "নন-স্ট্যাটিক মেথডে কীভাবে `p0`-তে `this` অবজেক্ট এবং `p1`-এ প্রথম আর্গুমেন্ট থাকে তা লাইভ দেখুন।",
                ur = "دیکھیں کہ غیر اسٹیٹک میتھڈ میں `p0` میں `this` اور `p1` میں پہلا آرگومنٹ کیسے رہتا ہے۔",
                hi = "देखें कि इंस्टेंस मेथड में `p0` में `this` और `p1` में पहला आर्ग्यूमेंट कैसे काम करता है।",
                ar = "راقب كيف يحمل `p0` الكائن الحالي بينما يحمل `p1` المعامل الأول.",
                fa_af = "ببینید چگونه در متد غیر استاتیک `p0` اشاره‌گر `this` و `p1` آرگومان اول است.",
                in_id = "Amati bagaimana `p0` memegang `this` sementara `p1` memegang argumen pertama."
            ),
            codeSnippet = """.method public addXp(I)I
    .locals 1
    iget v0, p0, LUser;->xp:I
    add-int/2addr v0, p1
    iput v0, p0, LUser;->xp:I
    return v0
.end method""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = "Entry: user.addXp(50)",
                    changedRegister = "p0, p1",
                    registerValue = "User(xp=100), 50",
                    registersSnapshot = mapOf("v0" to "0", "p0" to "this User(xp=100)", "p1" to "50"),
                    explanationContent = localized(
                        en = "Instance call! p0 receives 'this' User object (xp=100), and p1 receives bonus 50.",
                        bn = "এটি Instance কল! তাই `p0`-তে এসেছে `User(xp=100)` অবজেক্ট নিজে, আর `p1`-এ এসেছে ইনপুট `50`।",
                        ur = "انسٹانس کال! `p0` میں `User(xp=100)` آبجیکٹ آیا اور `p1` میں `50`۔",
                        hi = "इंस्टेंस कॉल! `p0` में स्वयं `User(xp=100)` आया और `p1` में इनपुट `50`।",
                        ar = "استدعاء كائن! استلم `p0` الكائن `User(xp=100)` واستلم `p1` القيمة 50.",
                        fa_af = "فراخوانی شیء! `p0` حاوی خود شیء `User(xp=100)` و `p1` حاوی عدد ۵۰ است.",
                        in_id = "Pemanggilan instance! `p0` berisi objek `User(xp=100)` dan `p1` berisi 50."
                    )
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "iget v0, p0, LUser;->xp:I",
                    changedRegister = "v0",
                    registerValue = "100",
                    registersSnapshot = mapOf("v0" to "100", "p0" to "this User(xp=100)", "p1" to "50"),
                    explanationContent = localized(
                        en = "iget reads field xp (100) from object p0 into local v0.",
                        bn = "`iget` নির্দেশনাটি `p0` অবজেক্টের ভেতর থেকে `xp` ফিল্ডের মান `100` পড়ে `v0`-তে আনল।",
                        ur = "`iget` نے `p0` آبجیکٹ سے `xp` کی قیمت `100` پڑھ کر `v0` میں رکھی۔",
                        hi = "`iget` ने `p0` ऑब्जेक्ट से `xp` का मान `100` पढ़कर `v0` में रखा।",
                        ar = "قرأت `iget` قيمة الحقل `xp` (100) من `p0` إلى `v0`.",
                        fa_af = "دستور `iget` مقدار فیلد `xp` (۱۰۰) را از `p0` خوانده و در `v0` گذاشت.",
                        in_id = "`iget` membaca field `xp` (100) dari `p0` ke register `v0`."
                    )
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "add-int/2addr v0, p1",
                    changedRegister = "v0",
                    registerValue = "150",
                    registersSnapshot = mapOf("v0" to "150", "p0" to "this User(xp=100)", "p1" to "50"),
                    explanationContent = localized(
                        en = "Adds p1 (50) directly into v0 -> v0 becomes 150. Notice User.xp is STILL 100 until iput!",
                        bn = "`v0`-এর সাথে `p1` (৫০) যোগ হয়ে `150` হলো। খেয়াল করুন: `iput` না করা পর্যন্ত অবজেক্টের ভেতরে এখনো ১০০-ই আছে!",
                        ur = "`v0` بڑھ کر `150` ہو گیا، مگر `iput` سے پہلے آبجیکٹ کے اندر ابھی 100 ہی ہے!",
                        hi = "`v0` बढ़कर `150` हो गया, पर `iput` चलने तक ऑब्जेक्ट में अभी भी 100 ही है!",
                        ar = "أصبحت قيمة `v0` تساوي 150، لكن حقل الكائن لا يتغير إلا بعد `iput`!",
                        fa_af = "`v0` برابر ۱۵۰ شد، اما تا پیش از اجرای `iput` مقدار داخل شیء هنوز ۱۰۰ است!",
                        in_id = "`v0` menjadi 150, namun field objek masih 100 sebelum `iput` dijalankan!"
                    )
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "iput v0, p0, LUser;->xp:I",
                    changedRegister = "p0",
                    registerValue = "this User(xp=150)",
                    registersSnapshot = mapOf("v0" to "150", "p0" to "this User(xp=150)", "p1" to "50"),
                    explanationContent = localized(
                        en = "iput writes 150 back into p0's xp field on the heap, then return v0 returns 150.",
                        bn = "`iput` নতুন মান `150` হিপ মেমোরির `User` অবজেক্টে সেভ করে দিল!",
                        ur = "`iput` نے نیا اسکور `150` واپس `User` آبجیکٹ میں محفوظ کر دیا!",
                        hi = "`iput` ने नया मान `150` वापस हीप के `User` ऑब्जेक्ट में सहेज दिया!",
                        ar = "كتبت `iput` القيمة الجديدة 150 داخل حقل الكائن في الذاكرة!",
                        fa_af = "دستور `iput` مقدار جدید ۱۵۰ را درون فیلد شیء `User` ثبت کرد!",
                        in_id = "`iput` menyimpan nilai 150 kembali ke field `xp` milik objek `User`!"
                    )
                )
            )
        )
    )
}
