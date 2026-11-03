package com.example.data

import com.example.model.JavaCheatItem
import com.example.model.JavaDecodedResult
import com.example.model.JavaMemoryScenario
import com.example.model.localized

object JavaReferenceData {

    // 1. Interactive Stack vs Heap Memory Scenarios (4 Scenarios)
    val memoryScenarios: List<JavaMemoryScenario> = listOf(
        JavaMemoryScenario(
            id = "mem_primitives",
            tabLabel = localized(
                en = "1. Primitives",
                bn = "১. প্রিমিটিভ মান",
                ur = "1. بنیادی ٹائپس",
                hi = "1. प्रिमिटिव मान",
                ar = "1. الأنواع الأساسية",
                fa_af = "۱. انواع پایه",
                in_id = "1. Tipe Primitif"
            ),
            title = localized(
                en = "Primitives Live Directly Inside Stack / Register Slots",
                bn = "প্রিমিটিভ সংখ্যা সরাসরি Stack বা রেজিস্টার বক্সেই জমা থাকে",
                ur = "پریمیٹو اعداد براہِ راست اسٹیک یا رجسٹر میں رہتے ہیں",
                hi = "प्रिमिटिव मान सीधे Stack या रजिस्टर स्लॉट में रहते हैं",
                ar = "تُحفظ الأنواع الأساسية مباشرة داخل خانات المكدس Stack",
                fa_af = "مقادیر پایه مستقیماً داخل خانه‌های Stack و رجستر قرار می‌گیرند",
                in_id = "Nilai Primitif Disimpan Langsung di Slot Stack / Register"
            ),
            javaSnippet = """int a = 25;
int b = a;      // Copies the number 25!
b = 99;         // Changing b does NOT touch a!""",
            stackFrameSlots = listOf(
                "v0 (int a)" to "25 (0x19)",
                "v1 (int b)" to "99 (0x63)"
            ),
            heapObjects = emptyList(),
            smaliEquivalent = """const/16 v0, 0x19
move v1, v0
const/16 v1, 0x63""",
            explanation = localized(
                en = "Zero Heap allocation! Copying `int b = a` copies the raw 32-bit value `25`, so changing `b = 99` leaves `a` untouched at `25`.",
                bn = "এখানে Heap মেমোরিতে কোনো অবজেক্টই তৈরি হয়নি! `int b = a` লিখলে `25` সংখ্যাটির আলাদা কপি তৈরি হয়, তাই পরে `b = 99` করলেও `a`-এর মান `25`-ই থাকে।",
                ur = "یہاں ہیپ میں کچھ نہیں بنتا! `int b = a` صرف نمبر `25` کی نقل بناتا ہے، لہٰذا `b` بدلنے سے `a` متاثر نہیں ہوتا۔",
                hi = "यहां Heap में कोई ऑब्जेक्ट नहीं बनता! `int b = a` संख्या `25` की स्वतंत्र प्रति बनाता है, अतः `b` बदलने पर `a` वही रहता है।",
                ar = "لا يُحجز شيء في Heap! إسناد `int b = a` ينسخ الرقم `25` نفسه، فتعديل `b` لا يؤثر على `a` إطلاقاً.",
                fa_af = "هیچ فضایی در Heap اشغال نمی‌شود! عبارت `int b = a` کپی مستقلی از عدد `25` می‌سازد و تغییر `b` روی `a` اثری ندارد.",
                in_id = "Tanpa alokasi Heap! Menyalin `int b = a` menyalin angka `25` secara terpisah sehingga mengubah `b` tidak mengubah `a`."
            )
        ),

        JavaMemoryScenario(
            id = "mem_string_pool",
            tabLabel = localized(
                en = "2. String Pool",
                bn = "২. String Pool",
                ur = "2. اسٹرنگ پول",
                hi = "2. स्ट्रिंग पूल",
                ar = "2. تجمع النصوص",
                fa_af = "۲. استخر رشته‌ها",
                in_id = "2. String Pool"
            ),
            title = localized(
                en = "Why == Fails on new String() While .equals() Succeeds",
                bn = "কেন String তুলনায় == ভুল দেয় কিন্তু .equals() সঠিক দেয়",
                ur = "اسٹرنگ میں == کیوں ناکام ہوتا ہے اور .equals() کیوں کامیاب",
                hi = "new String() पर == क्यों विफल होता है और .equals() क्यों सफल",
                ar = "لماذا تفشل == مع new String() بينما تنجح .equals()",
                fa_af = "چرا == روی new String() شکست می‌خورد اما .equals() درست کار می‌کند",
                in_id = "Mengapa == Gagal pada new String() Sedangkan .equals() Berhasil"
            ),
            javaSnippet = """String s1 = "PRO";             // Shared Pool @0x10A0
String s2 = new String("PRO"); // Fresh Heap  @0x4F20
boolean refCheck = (s1 == s2);      // false!
boolean txtCheck = s1.equals(s2);   // true!""",
            stackFrameSlots = listOf(
                "v0 (s1 ref)" to "@0x10A0 (Pool)",
                "v1 (s2 ref)" to "@0x4F20 (Heap)",
                "v2 (s1 == s2)" to "false (0x0)",
                "v3 (equals)" to "true (0x1)"
            ),
            heapObjects = listOf(
                "@0x10A0 [StringPool]" to "String(\"PRO\")",
                "@0x4F20 [Heap Object]" to "String(\"PRO\")"
            ),
            smaliEquivalent = """const-string v0, "PRO"
new-instance v1, Ljava/lang/String;
invoke-direct {v1, v0}, Ljava/lang/String;-><init>(Ljava/lang/String;)V
invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z""",
            explanation = localized(
                en = "`s1` points to `@0x10A0` in the String Pool, whereas `new String(\"PRO\")` forces a second Heap object at `@0x4F20`. `==` compares addresses (`false`), while `.equals()` compares characters (`true`)!",
                bn = "`s1` পয়েন্ট করছে `@0x10A0` ঠিকানায়, আর `new String` নতুন `@0x4F20` ঠিকানায় আরেকটি অবজেক্ট বানিয়েছে। তাই `==` ঠিকানা মিলিয়ে `false` দেয়, কিন্তু `.equals()` ভেতরের অক্ষর মিলিয়ে `true` দেয়!",
                ur = "`s1` اور `s2` کے میموری ایڈریس مختلف ہیں اس لیے `==` غلط (`false`) دیتا ہے، جبکہ `.equals()` اندر کا متن ملا کر `true` دیتا ہے!",
                hi = "`s1` और `s2` के RAM पते अलग हैं इसलिए `==` असत्य (`false`) देता है, जबकि `.equals()` अक्षरों को मिलाकर `true` देता है!",
                ar = "يشير `s1` و `s2` إلى عنوانين مختلفين فتفشل `==`، بينما تفحص `.equals()` تطابق الأحرف فتعطي `true`!",
                fa_af = "آدرس‌های `s1` و `s2` متفاوت است پس `==` مقدار `false` می‌دهد، اما `.equals()` حروف داخل رشته را مقایسه کرده و `true` برمی‌گرداند!",
                in_id = "`s1` dan `s2` menunjuk ke alamat RAM berbeda sehingga `==` bernilai `false`, sedangkan `.equals()` memeriksa isi huruf (`true`)!"
            )
        ),

        JavaMemoryScenario(
            id = "mem_heap_object",
            tabLabel = localized(
                en = "3. new Object",
                bn = "৩. new অবজেক্ট",
                ur = "3. نیا آبجیکٹ",
                hi = "3. new ऑब्जेक्ट",
                ar = "3. كائن جديد",
                fa_af = "۳. شیء جدید",
                in_id = "3. Objek new"
            ),
            title = localized(
                en = "Stack Holds the Remote Control; Heap Holds the Real Object",
                bn = "Stack-এ থাকে শুধু রিমোট কন্ট্রোল (ঠিকানা), আর আসল অবজেক্ট থাকে Heap-এ!",
                ur = "اسٹیک میں صرف ایڈریس رہتا ہے جبکہ اصل آبجیکٹ ہیپ میں بنتا ہے",
                hi = "Stack में केवल पता (रेफरेंस) रहता है और असली ऑब्जेक्ट Heap में",
                ar = "المكدس يحمل المرجع فقط بينما يعيش الكائن الفعلي في Heap",
                fa_af = "استک فقط آدرس مرجع را نگه می‌دارد و خود شیء داخل Heap است",
                in_id = "Stack Menyimpan Alamat Referensi; Heap Menyimpan Objek Asli"
            ),
            javaSnippet = """Wallet w = new Wallet("Morsalin", 500);
w.deposit(250); // Updates Heap field balance -> 750""",
            stackFrameSlots = listOf(
                "v0 (Wallet w)" to "ref -> @0x88C0"
            ),
            heapObjects = listOf(
                "@0x88C0 [Wallet]" to "owner = \"Morsalin\", balance = 750"
            ),
            smaliEquivalent = """new-instance v0, Lcom/example/Wallet;
const-string v1, "Morsalin"
const/16 v2, 0x1f4
invoke-direct {v0, v1, v2}, Lcom/example/Wallet;-><init>(Ljava/lang/String;I)V""",
            explanation = localized(
                en = "Local variable `w` (`v0`) only stores a compact pointer (`@0x88C0`). All instance fields (`owner`, `balance`) live inside the `@0x88C0` block on the Heap and are accessed via `iget` / `iput`.",
                bn = "লোকাল ভেরিয়েবল `w` (`v0` রেজিস্টার) কেবল মেমোরি ঠিকানা `@0x88C0` ধরে রাখে। অবজেক্টের ভেতরের `owner` ও `balance` ফিল্ডগুলো Heap মেমোরিতে থাকে এবং `iget`/`iput` দিয়ে আপডেট হয়।",
                ur = "لوکل متغیر `w` صرف ہیپ ایڈریس `@0x88C0` رکھتا ہے جہاں تمام فیلڈز موجود ہوتے ہیں اور `iget`/`iput` سے بدلتے ہیں۔",
                hi = "लोकल वेरिएबल `w` केवल `@0x88C0` पता रखता है; सारे फ़ील्ड Heap में रहते हैं और `iget`/`iput` से अपडेट होते हैं।",
                ar = "يحفظ المتغير `w` مؤشر العنوان `@0x88C0` فقط، بينما تقيم الحقول داخل Heap وتُقرأ وتُكتب بـ `iget`/`iput`.",
                fa_af = "متغیر محلی `w` فقط آدرس `@0x88C0` را دارد و تمام فیلدهای شیء در Heap با `iget` و `iput` مدیریت می‌شوند.",
                in_id = "Variabel lokal `w` hanya menyimpan pointer `@0x88C0`, sedangkan seluruh field objek berada di Heap (`iget`/`iput`)."
            )
        ),

        JavaMemoryScenario(
            id = "mem_shared_alias",
            tabLabel = localized(
                en = "4. Aliasing",
                bn = "৪. শেয়ারড রেফারেন্স",
                ur = "4. مشترکہ حوالہ",
                hi = "4. साझा रेफरेंस",
                ar = "4. مشاركة المرجع",
                fa_af = "۴. مرجع مشترک",
                in_id = "4. Referensi Sama"
            ),
            title = localized(
                en = "Two Variables Pointing to the SAME Heap Object (w2 = w1)",
                bn = "দুটি ভেরিয়েবল একই অবজেক্টকে ধরলে একটি বদলালে অন্যটিও বদলে যায়!",
                ur = "جب دو متغیرات ایک ہی ہیپ آبجیکٹ کی طرف اشارہ کریں (w2 = w1)",
                hi = "जब दो वेरिएबल एक ही Heap ऑब्जेक्ट को इंगित करें (w2 = w1)",
                ar = "متغيران يشيران إلى نفس الكائن في الذاكرة (w2 = w1)",
                fa_af = "اشاره دو متغیر به یک شیء واحد در حافظه Heap (w2 = w1)",
                in_id = "Dua Variabel Menunjuk ke Objek Heap yang SAMA (w2 = w1)"
            ),
            javaSnippet = """Wallet w1 = new Wallet("User", 100);
Wallet w2 = w1;      // Copies ONLY the address!
w2.setBalance(999);  // w1.getBalance() is ALSO 999!""",
            stackFrameSlots = listOf(
                "v0 (Wallet w1)" to "ref -> @0x9200",
                "v1 (Wallet w2)" to "ref -> @0x9200 (Same!)"
            ),
            heapObjects = listOf(
                "@0x9200 [Single Wallet]" to "owner = \"User\", balance = 999"
            ),
            smaliEquivalent = """move-object v1, v0
const/16 v2, 0x3e7
invoke-virtual {v1, v2}, Lcom/example/Wallet;->setBalance(I)V""",
            explanation = localized(
                en = "`Wallet w2 = w1` (`move-object v1, v0`) does NOT clone the object—both `w1` and `w2` hold `@0x9200`! Mutating via `w2` immediately reflects in `w1`.",
                bn = "`Wallet w2 = w1` লিখলে নতুন অবজেক্ট তৈরি হয় না, বরং দুটি রিমোটই একই `@0x9200` অবজেক্টকে নিয়ন্ত্রণ করে! তাই `w2` দিয়ে ব্যালেন্স বদলালে `w1`-এও `999` দেখা যায়!",
                ur = "`w2 = w1` نیا آبجیکٹ نہیں بناتا بلکہ دونوں ایک ہی ایڈریس `@0x9200` کو کنٹرول کرتے ہیں، اس لیے `w2` کی تبدیلی `w1` میں بھی نظر آتی ہے!",
                hi = "`w2 = w1` नया ऑब्जेक्ट नहीं बनाता—दोनों एक ही `@0x9200` पते को नियंत्रित करते हैं, इसलिए `w2` बदलने पर `w1` भी बदल जाता है!",
                ar = "لا تنسخ `w2 = w1` الكائن بل تنسخ العنوان `@0x9200` فقط؛ فأي تعديل عبر `w2` يظهر فوراً في `w1`!",
                fa_af = "`w2 = w1` شیء جدیدی نمی‌سازد بلکه هر دو به آدرس `@0x9200` اشاره می‌کنند؛ پس تغییر در `w2` مستقیماً در `w1` دیده می‌شود!",
                in_id = "`w2 = w1` (`move-object`) tidak menggandakan objek; keduanya menunjuk ke `@0x9200` sehingga perubahan di `w2` terlihat di `w1`!"
            )
        )
    )

    // 2. Cheat Sheet 1: All 8 Java Primitive Types
    val primitiveCheatSheet: List<JavaCheatItem> = listOf(
        JavaCheatItem(
            symbol = "byte",
            badge = "8-bit • B",
            title = localized("Signed 8-bit integer (-128 to 127)", "৮-বিট পূর্ণসংখ্যা (-১২৮ থেকে ১২৭)", "8-بٹ عدد (-128 تا 127)", "8-बिट पूर्णांक (-128 से 127)", "عدد صحيح 8-بت (-128 إلى 127)", "عدد صحیح ۸ بیتی (-۱۲۸ تا ۱۲۷)", "Bulat 8-bit (-128 s/d 127)"),
            detail = localized("Default: 0 • Ideal for binary file streams & network packets (`byte[]`).", "ডিফল্ট: 0 • ফাইল ও নেটওয়ার্ক বাইনারি ডাটার (`byte[]`) জন্য আদর্শ।", "ڈیفالٹ: 0 • فائل اور نیٹ ورک بائٹس کے لیے۔", "डिफ़ॉल्ट: 0 • फ़ाइल व नेटवर्क बाइट्स (`byte[]`) हेतु।", "الافتراضي: 0 • مثالي للملفات والشبكات (`byte[]`).", "پیش‌فرض: 0 • مناسب فایل و پکت‌های شبکه (`byte[]`).", "Default: 0 • Untuk stream biner & jaringan (`byte[]`)."),
            example = "byte header = 0x64;"
        ),
        JavaCheatItem(
            symbol = "short",
            badge = "16-bit • S",
            title = localized("Signed 16-bit integer (-32,768 to 32,767)", "১৬-বিট পূর্ণসংখ্যা (-৩২,৭৬৮ থেকে ৩২,৭৬৭)", "16-بٹ عدد (-32,768 تا 32,767)", "16-बिट पूर्णांक (-32,768 से 32,767)", "عدد صحيح 16-بت (-32,768 إلى 32,767)", "عدد صحیح ۱۶ بیتی (-۳۲,۷۶۸ تا ۳۲,۷۶۷)", "Bulat 16-bit (-32.768 s/d 32.767)"),
            detail = localized("Default: 0 • Used in PCM audio buffers and compact binary headers.", "ডিফল্ট: 0 • অডিও বাফার ও কমপ্যাক্ট ডাটা হেডারে ব্যবহৃত হয়।", "ڈیفالٹ: 0 • آڈیو بفرز میں مستعمل۔", "डिफ़ॉल्ट: 0 • ऑडियो बफ़र में उपयोगी।", "الافتراضي: 0 • يستخدم في مخازن الصوت الرقمي.", "پیش‌فرض: 0 • کاربرد در بافرهای صوتی PCM.", "Default: 0 • Dipakai pada buffer audio PCM."),
            example = "short sample = 1024;"
        ),
        JavaCheatItem(
            symbol = "int",
            badge = "32-bit • I",
            title = localized("Standard 32-bit integer (-2.14B to +2.14B)", "স্ট্যান্ডার্ড ৩২-বিট পূর্ণসংখ্যা (-২.১৪ বিলিয়ন থেকে +২.১৪ বিলিয়ন)", "معیاری 32-بٹ عدد", "मानक 32-बिट पूर्णांक", "العدد الصحيح القياسي 32-بت", "عدد صحیح استاندارد ۳۲ بیتی", "Bulat standar 32-bit"),
            detail = localized("Default: 0 • Primary Java integer type; takes 1 Dalvik register.", "ডিফল্ট: 0 • জাভার প্রধান পূর্ণসংখ্যা; ১টি ডালভিক রেজিস্টার নেয়।", "ڈیفالٹ: 0 • جاوا کی بنیادی عددی ٹائپ؛ 1 رجسٹر۔", "डिफ़ॉल्ट: 0 • मुख्य पूर्णांक प्रकार; 1 रजिस्टर लेता है।", "الافتراضي: 0 • النوع الرقمي الرئيسي؛ يأخذ مسجلاً واحداً.", "پیش‌فرض: 0 • نوع اصلی اعداد صحیح؛ ۱ رجستر می‌گیرد.", "Default: 0 • Tipe bulat utama; memakai 1 register."),
            example = "int score = 2500;"
        ),
        JavaCheatItem(
            symbol = "long",
            badge = "64-bit • J",
            title = localized("Wide 64-bit integer (±9.22 Quintillion)", "বড় ৬৪-বিট পূর্ণসংখ্যা (বিশাল হিসাব ও Timestamp)", "وسیع 64-بٹ عدد", "विस्तृत 64-बिट पूर्णांक", "عدد صحيح عريض 64-بت", "عدد صحیح عریض ۶۴ بیتی", "Bulat lebar 64-bit"),
            detail = localized("Default: 0L • Suffix `L` required; takes 2 registers (`v0, v1`).", "ডিফল্ট: 0L • শেষে `L` দিতে হয়; স্মালিতে ২টি রেজিস্টার (`v0, v1`) নেয়।", "ڈیفالٹ: 0L • آخر میں `L` لازمی؛ 2 رجسٹر لیتا ہے۔", "डिफ़ॉल्ट: 0L • अंत में `L` आवश्यक; 2 रजिस्टर लेता है।", "الافتراضي: 0L • يتطلب `L` ويأخذ مسجلين متتاليين.", "پیش‌فرض: 0L • نیازمند پسوند `L`؛ ۲ رجستر می‌گیرد.", "Default: 0L • Wajib akhiran `L`; memakai 2 register."),
            example = "long id = 9876543210L;"
        ),
        JavaCheatItem(
            symbol = "float",
            badge = "32-bit • F",
            title = localized("Single-precision decimal (~6-7 digits)", "৩২-বিট দশমিক সংখ্যা (UI ও অ্যানিমেশনে ব্যবহৃত)", "32-بٹ اعشاریہ عدد", "32-बिट दशमलव संख्या", "عدد عشري أحادي الدقة 32-بت", "عدد اعشاری ۳۲ بیتی", "Desimal 32-bit"),
            detail = localized("Default: 0.0f • Suffix `f` required; common in Android Canvas coordinates.", "ডিফল্ট: 0.0f • শেষে `f` দিতে হয়; অ্যান্ড্রয়েড গ্রাফিক্স ও অ্যানিমেশনে জনপ্রিয়।", "ڈیفالٹ: 0.0f • آخر میں `f` لازمی؛ گرافکس میں مستعمل۔", "डिफ़ॉल्ट: 0.0f • अंत में `f` आवश्यक; कैनवास ग्राफ़िक्स में आम।", "الافتراضي: 0.0f • يتطلب `f` وشائع في رسوميات أندرويد.", "پیش‌فرض: 0.0f • نیازمند پسوند `f`؛ رایج در گرافیک اندروید.", "Default: 0.0f • Wajib akhiran `f`; umum di Canvas UI."),
            example = "float alpha = 0.85f;"
        ),
        JavaCheatItem(
            symbol = "double",
            badge = "64-bit • D",
            title = localized("Double-precision decimal (~15-16 digits)", "৬৪-বিট নিখুঁত দশমিক সংখ্যা (ডিফল্ট দশমিক)", "64-بٹ درست اعشاریہ عدد", "64-बिट उच्च-सटीकता दशमलव", "عدد عشري مزدوج الدقة 64-بت", "عدد اعشاری دقیق ۶۴ بیتی", "Desimal presisi ganda 64-bit"),
            detail = localized("Default: 0.0d • Default decimal type in Java; takes 2 registers.", "ডিফল্ট: 0.0d • জাভার ডিফল্ট দশমিক টাইপ; স্মালিতে ২টি রেজিস্টার নেয়।", "ڈیفالٹ: 0.0d • جاوا کی ڈیفالٹ اعشاریہ ٹائپ؛ 2 رجسٹر۔", "डिफ़ॉल्ट: 0.0d • डिफ़ॉल्ट दशमलव प्रकार; 2 रजिस्टर लेता है।", "الافتراضي: 0.0d • النوع العشري الافتراضي؛ يأخذ مسجلين.", "پیش‌فرض: 0.0d • نوع پیش‌فرض اعشاری؛ ۲ رجستر می‌گیرد.", "Default: 0.0d • Desimal bawaan Java; memakai 2 register."),
            example = "double lat = 23.8103;"
        ),
        JavaCheatItem(
            symbol = "boolean",
            badge = "Logic • Z",
            title = localized("True or False condition flag", "সত্য (`true`) অথবা মিথ্যা (`false`) লজিক ফ্ল্যাগ", "سچ یا جھوٹ کا فلیگ", "सत्य या असत्य तार्किक फ़्लैग", "علم منطقي صح أو خطأ", "پرچم منطقی درست یا نادرست", "Flag logika true / false"),
            detail = localized("Default: false • Compiles to `0x1` (`true`) and `0x0` (`false`) in Smali.", "ডিফল্ট: false • স্মালিতে কম্পাইল হলে `true = 0x1` এবং `false = 0x0` হয়।", "ڈیفالٹ: false • سمالی میں `0x1` اور `0x0` بنتا ہے۔", "डिफ़ॉल्ट: false • स्माली में `0x1` और `0x0` बनता है।", "الافتراضي: false • تترجم إلى `0x1` و `0x0` في سمالي.", "پیش‌فرض: false • در سمالی به `0x1` و `0x0` تبدیل می‌شود.", "Default: false • Menjadi `0x1` dan `0x0` di Smali."),
            example = "boolean active = true;"
        ),
        JavaCheatItem(
            symbol = "char",
            badge = "16-bit • C",
            title = localized("Unsigned 16-bit UTF-16 character ('\\u0000'..'\\uffff')", "১৬-বিট ইউনিকোড অক্ষর (বাংলা, আরবি, ইংরেজি সব সাপোর্ট করে)", "16-بٹ یونیکوڈ حرف", "16-बिट यूनिकोड अक्षर", "محرف يونيكود 16-بت", "کاراکتر ۱۶ بیتی یونیکد", "Karakter UTF-16 16-bit"),
            detail = localized("Default: '\\u0000' • Always enclosed in single quotes `'A'`.", "ডিফল্ট: '\\u0000' • সবসময় সিঙ্গেল কোটেশনের `'ক'` ভেতরে লিখতে হয়।", "ڈیفالٹ: '\\u0000' • ہمیشہ سنگل کوٹ `'A'` میں لکھیں۔", "डिफ़ॉल्ट: '\\u0000' • हमेशा सिंगल कोट `'A'` में लिखें।", "الافتراضي: '\\u0000' • يُكتب دائماً بين علامتي اقتباس مفردتين.", "پیش‌فرض: '\\u0000' • همیشه داخل گیومه تکی نوشته می‌شود.", "Default: '\\u0000' • Selalu diapit kutip tunggal `'A'`."),
            example = "char ch = 'S';"
        )
    )

    // 3. Cheat Sheet 2: Access & Member Modifiers
    val modifierCheatSheet: List<JavaCheatItem> = listOf(
        JavaCheatItem(
            symbol = "public",
            badge = "Everywhere",
            title = localized("Class ✓ • Package ✓ • Subclass ✓ • World ✓", "নিজ ক্লাস ✓ • নিজ প্যাকেজ ✓ • চাইল্ড ক্লাস ✓ • পুরো প্রজেক্ট ✓", "کلاس ✓ • پیکج ✓ • ذیلی کلاس ✓ • ہر جگہ ✓", "क्लास ✓ • पैकेज ✓ • सब-क्लास ✓ • सर्वत्र ✓", "الفئة ✓ • الحزمة ✓ • الفرعية ✓ • الجميع ✓", "کلاس ✓ • پکیج ✓ • زیرکلاس ✓ • همه‌جا ✓", "Kelas ✓ • Paket ✓ • Subclass ✓ • Semua ✓"),
            detail = localized("Open visibility across all packages and modules.", "প্রজেক্টের যেকোনো জায়গা থেকে অ্যাক্সেস করা যায়।", "پروجیکٹ میں ہر جگہ سے قابلِ رسائی۔", "प्रोजेक्ट में कहीं से भी सुलभ।", "متاح من جميع الحزم والملفات.", "قابل دسترسی از تمام بخش‌های برنامه.", "Terbuka dari seluruh paket."),
            example = "public void launch()"
        ),
        JavaCheatItem(
            symbol = "protected",
            badge = "Pkg + Child",
            title = localized("Class ✓ • Package ✓ • Subclass ✓ • World ✗", "নিজ ক্লাস ✓ • নিজ প্যাকেজ ✓ • চাইল্ড ক্লাস ✓ • বাইরে ✗", "کلاس ✓ • پیکج ✓ • ذیلی کلاس ✓ • باہر ✗", "क्लास ✓ • पैकेज ✓ • सब-क्लास ✓ • बाहर ✗", "الفئة ✓ • الحزمة ✓ • الفرعية ✓ • الخارج ✗", "کلاس ✓ • پکیج ✓ • زیرکلاس ✓ • بیرون ✗", "Kelas ✓ • Paket ✓ • Subclass ✓ • Luar ✗"),
            detail = localized("Used heavily by Android lifecycle methods like `onCreate`.", "অ্যান্ড্রয়েডের `onCreate`-এর মতো ইনহেরিটেড মেথডে ব্যবহৃত হয়।", "اینڈرائیڈ لائف سائیکل میتھڈز جیسے `onCreate` میں مستعمل۔", "`onCreate` जैसे लाइफसाइकिल मेथड में प्रयुक्त।", "تستخدم في دوال دورة الحياة مثل `onCreate`.", "رایج در متدهای چرخه حیات مانند `onCreate`.", "Dipakai pada metode siklus hidup seperti `onCreate`."),
            example = "protected void onStop()"
        ),
        JavaCheatItem(
            symbol = "(default)",
            badge = "Package Only",
            title = localized("Class ✓ • Package ✓ • Subclass ✗ • World ✗", "নিজ ক্লাস ✓ • নিজ প্যাকেজ ✓ • চাইল্ড ক্লাস ✗ • বাইরে ✗", "کلاس ✓ • پیکج ✓ • ذیلی کلاس ✗ • باہر ✗", "क्लास ✓ • पैकेज ✓ • सब-क्लास ✗ • बाहर ✗", "الفئة ✓ • الحزمة ✓ • الفرعية ✗ • الخارج ✗", "کلاس ✓ • پکیج ✓ • زیرکلاس ✗ • بیرون ✗", "Kelas ✓ • Paket ✓ • Subclass ✗ • Luar ✗"),
            detail = localized("No modifier written = package-private internal helper.", "কোনো মডিফায়ার না লিখলে শুধু একই প্যাকেজের ক্লাসগুলো দেখতে পায়।", "کوئی لفظ نہ لکھنے پر صرف ایک ہی پیکج میں نظر آتا ہے۔", "कुछ न लिखने पर केवल समान पैकेज के भीतर दिखता है।", "عدم كتابة محدد يحصر الرؤية داخل نفس الحزمة.", "ننوشتن سطح دسترسی یعنی فقط داخل همان پکیج دیده شود.", "Tanpa kata kunci = hanya terlihat di paket yang sama."),
            example = "class InternalHelper"
        ),
        JavaCheatItem(
            symbol = "private",
            badge = "Class Only",
            title = localized("Class ✓ • Package ✗ • Subclass ✗ • World ✗", "নিজ ক্লাস ✓ • নিজ প্যাকেজ ✗ • চাইল্ড ক্লাস ✗ • বাইরে ✗", "کلاس ✓ • پیکج ✗ • ذیلی کلاس ✗ • باہر ✗", "क्लास ✓ • पैकेज ✗ • सब-क्लास ✗ • बाहर ✗", "الفئة ✓ • الحزمة ✗ • الفرعية ✗ • الخارج ✗", "کلاس ✓ • پکیج ✗ • زیرکلاس ✗ • بیرون ✗", "Kelas ✓ • Paket ✗ • Subclass ✗ • Luar ✗"),
            detail = localized("Best practice for all internal fields; calls compile to `invoke-direct`.", "সব ডাটা ফিল্ড `private` রাখা প্রফেশনাল নিয়ম; স্মালিতে `invoke-direct` হয়।", "تمام اندرونی فیلڈز کے لیے بہترین؛ سمالی میں `invoke-direct`۔", "सभी आंतरिक फ़ील्ड्स हेतु सर्वोत्तम; स्माली में `invoke-direct`।", "الأفضل لجميع الحقول الداخلية؛ تترجم إلى `invoke-direct`.", "بهترین روش برای فیلدها؛ کامپایل به `invoke-direct`.", "Praktik terbaik untuk field; dikompilasi ke `invoke-direct`."),
            example = "private int secretPin;"
        )
    )

    // 4. Cheat Sheet 3: Golden OOP & Architecture Rules
    val oopRulesCheatSheet: List<JavaCheatItem> = listOf(
        JavaCheatItem(
            symbol = "==  vs  .equals()",
            badge = "Rule #1",
            title = localized("Reference Address vs Content Value", "মেমোরি ঠিকানা তুলনা বনাম ভেতরের আসল মান তুলনা", "میموری ایڈریس بمقابلہ اصل مواد", "मेमोरी पता बनाम वास्तविक सामग्री", "عنوان الذاكرة مقابل المحتوى الفعلي", "آدرس حافظه در برابر محتوای واقعی", "Alamat Referensi vs Isi Nilai"),
            detail = localized("Use `==` ONLY for primitives (`int`, `boolean`) & `enum`. Always use `.equals()` for `String` and objects!", "শুধু প্রিমিটিভ ও `enum`-এ `==` চলবে; `String` বা অবজেক্টের জন্য সবসময় `.equals()`!", "صرف پریمیٹو اور `enum` میں `==` چلے گا؛ `String` میں ہمیشہ `.equals()`!", "केवल प्रिमिटिव व `enum` में `==`; `String` के लिए सदैव `.equals()`!", "استخدم `==` للأنواع الأساسية فقط، و `.equals()` للنصوص دائماً!", "فقط برای انواع پایه از `==` و برای رشته‌ها همیشه از `.equals()` استفاده کنید!", "Pakai `==` hanya untuk primitif/`enum`, dan `.equals()` untuk `String`!"),
            example = "\"ADMIN\".equals(role)"
        ),
        JavaCheatItem(
            symbol = "extends vs implements",
            badge = "Rule #2",
            title = localized("Single Class Inheritance vs Multiple Interfaces", "১টি প্যারেন্ট ক্লাস বনাম অসংখ্য ইন্টারফেস ইমপ্লিমেন্টেশন", "ایک پیرنٹ کلاس بمقابلہ متعدد انٹرفیس", "एकल क्लास विरासत बनाम अनेक इंटरफ़ेस", "وراثة فئة واحدة مقابل تطبيق عدة واجهات", "تک‌وراثتی کلاس در برابر چند اینترفیس", "Satu Induk Kelas vs Banyak Interface"),
            detail = localized("A Java class can `extends` only ONE class, but can `implements` unlimited interfaces.", "জাভায় কেবল ১টি ক্লাস `extends` করা যায়, কিন্তু যত খুশি `interface` ইমপ্লিমেন্ট করা যায়।", "جاوا میں صرف ایک کلاس `extends` ہو سکتی ہے مگر کئی انٹرفیس `implements`۔", "जावा में केवल 1 क्लास `extends` होती है, पर अनेक इंटरफ़ेस `implements`।", "ترث الفئة أبًا واحداً فقط، لكنها تطبق عدداً غير محدود من الواجهات.", "یک کلاس فقط یک والد را `extends` می‌کند اما بی‌نهایت اینترفیس را `implements` می‌نماید.", "Kelas hanya bisa `extends` 1 induk, namun bebas `implements` banyak interface."),
            example = "class A extends B implements C, D"
        ),
        JavaCheatItem(
            symbol = "Overload vs Override",
            badge = "Rule #3",
            title = localized("Same Class Different Params vs Subclass Same Signature", "একই ক্লাসে ভিন্ন প্যারামিটার বনাম চাইল্ড ক্লাসে হুবহু এক সিগনেচার", "مختلف پیرامیٹرز بمقابلہ ذیلی کلاس میں یکساں نام", "भिन्न पैरामीटर बनाम सब-क्लास में समान सिग्नेचर", "تعدد المعاملات مقابل إعادة التعريف في الابن", "پارامتر متفاوت در برابر بازنویسی در فرزند", "Beda Parameter vs Override di Subclass"),
            detail = localized("Overloading resolves at compile time; `@Override` dispatches dynamically at runtime via `invoke-virtual`.", "Overloading কম্পাইল সময়ে ঠিক হয়, আর `@Override` রানটাইমে `invoke-virtual` দিয়ে চলে।", "اوور لوڈنگ کمپائل وقت طے ہوتی ہے جبکہ `@Override` رن ٹائم پر `invoke-virtual` سے۔", "ओवरलोडिंग कंपाइल समय तय होती है, जबकि `@Override` रनटाइम पर `invoke-virtual` से।", "يُحسم التحميل الزائد عند الترجمة، بينما يعمل `@Override` وقت التشغيل بـ `invoke-virtual`.", "اورلود در زمان کامپایل مشخص می‌شود اما `@Override` در زمان اجرا با `invoke-virtual` کار می‌کند.", "Overload ditentukan saat kompilasi; `@Override` dijalankan dinamis lewat `invoke-virtual`."),
            example = "@Override public String toString()"
        ),
        JavaCheatItem(
            symbol = "String vs StringBuilder",
            badge = "Rule #4",
            title = localized("Immutable Thread-Safe Text vs Fast Loop Buffer", "অপরিবর্তনীয় নিরাপদ টেক্সট বনাম লুপের দ্রুতগতির বাফার", "ناقابلِ تغیر متن بمقابلہ تیز لوپ بفر", "अपरिवर्तनीय टेक्स्ट बनाम तेज़ लूप बफ़र", "نص ثابت آمن مقابل مخزن حلقات سريع", "متن تغییرناپذیر در برابر بافر سریع حلقه", "Teks Tetap vs Buffer Loop Cepat"),
            detail = localized("Use `String` for normal values & keys; switch to `StringBuilder` whenever concatenating inside loops.", "সাধারণ কাজে `String` সেরা, কিন্তু লুপের ভেতরে জোড়া লাগাতে অবশ্যই `StringBuilder` নিন।", "عام استعمال میں `String` اور لوپ کے اندر جوڑنے کے لیے `StringBuilder`۔", "सामान्य मानों हेतु `String` और लूप में जोड़ने हेतु `StringBuilder`।", "استخدم `String` للقيم العادية و `StringBuilder` للدمج داخل الحلقات.", "برای مقادیر عادی `String` و برای چسباندن متن داخل حلقه حتماً `StringBuilder`.", "Gunakan `String` untuk teks biasa dan `StringBuilder` saat menggabung di dalam loop."),
            example = "sb.append(line).append('\\n');"
        )
    )

    // 5. Presets for Live Java -> Smali Converter
    val sampleJavaDeclarations: List<String> = listOf(
        "public boolean verifyToken(String token, int pin)",
        "public static long sumScores(int[] items, long bonus)",
        "private final String secretApiKey = \"PRO_KEY\";",
        "public UserAccount(String username, boolean isVip)",
        "public final class PaymentService extends BaseService implements Runnable"
    )

    // 6. Live Java Declaration -> Smali Bytecode Skeleton Converter
    fun compileJavaToSmali(rawInput: String): JavaDecodedResult {
        val cleaned = rawInput
            .trim()
            .removeSuffix("{")
            .removeSuffix(";")
            .trim()

        if (cleaned.isEmpty()) {
            return JavaDecodedResult(
                rawJava = rawInput,
                statementKind = localized("Empty Input", "খালি ইনপুট", "خالی ان پٹ", "खाली इनपुट", "مدخل فارغ", "ورودی خالی", "Input Kosong"),
                modifiersSummary = "-",
                memberName = "-",
                returnOrFieldType = "-",
                parametersSummary = "-",
                generatedSmali = "# Type any Java class, field, or method declaration above",
                plainExplanation = localized(
                    en = "Enter a Java method, constructor, field, or class header to generate its Android DEX Smali bytecode.",
                    bn = "স্মালি বাইটকোড তৈরি করতে উপরে যেকোনো একটি জাভা মেথড, কনস্ট্রাক্টর, ভেরিয়েবল বা ক্লাসের লাইন লিখুন।",
                    ur = "سمالی بائٹ کوڈ بنانے کے لیے اوپر کوئی جاوا میتھڈ، فیلڈ یا کلاس لکھیں۔",
                    hi = "स्माली बाइटकोड जनरेट करने के लिए ऊपर कोई जावा मेथड, फ़ील्ड या क्लास लिखें।",
                    ar = "اكتب تصريح دالة أو حقل أو فئة بلغة جافا أعلاه لتوليد شيفرة سمالي المقابلة.",
                    fa_af = "یک متد، فیلد یا کلاس جاوا در بالا بنویسید تا کد سمالی آن تولید شود.",
                    in_id = "Ketik deklarasi metode, field, atau kelas Java di atas untuk menghasilkan bytecode Smali."
                )
            )
        }

        val knownMods = setOf(
            "public", "private", "protected", "static", "final",
            "abstract", "synchronized", "volatile", "transient", "native"
        )

        // CASE A: Class or Interface declaration
        if (Regex("""\b(class|interface|enum)\b""").containsMatchIn(cleaned)) {
            val tokens = cleaned.split(Regex("""\s+""")).filter { it.isNotBlank() }
            val mods = tokens.takeWhile { it in knownMods }
            val kindKeyword = tokens.firstOrNull { it == "class" || it == "interface" || it == "enum" } ?: "class"
            val nameIdx = tokens.indexOf(kindKeyword) + 1
            val rawClassName = tokens.getOrNull(nameIdx)?.substringBefore("<") ?: "SampleClass"

            val extendsMatch = Regex("""\bextends\s+([A-Za-z0-9_$.]+)""").find(cleaned)
            val superJava = extendsMatch?.groupValues?.get(1) ?: when (kindKeyword) {
                "enum" -> "java.lang.Enum"
                else -> "java.lang.Object"
            }

            val implMatch = Regex("""\bimplements\s+(.+)$""").find(cleaned)
            val interfaces = implMatch?.groupValues?.get(1)
                ?.split(",")
                ?.map { it.trim().substringBefore("<") }
                ?.filter { it.isNotEmpty() }
                .orEmpty()

            val smaliMods = buildList {
                addAll(mods)
                if (kindKeyword == "interface") {
                    if ("interface" !in this) add("interface")
                    if ("abstract" !in this) add("abstract")
                } else if (kindKeyword == "enum") {
                    if ("final" !in this) add("final")
                    add("enum")
                }
            }.joinToString(" ")

            val classDesc = javaTypeToSmali("com.example.$rawClassName")
            val superDesc = javaTypeToSmali(superJava)
            val implLines = interfaces.joinToString("\n") { ".implements ${javaTypeToSmali(it)}" }

            val smaliOut = buildString {
                append(".class ")
                if (smaliMods.isNotEmpty()) append("$smaliMods ")
                append(classDesc)
                append("\n.super $superDesc")
                if (implLines.isNotEmpty()) {
                    append("\n$implLines")
                }
                if (kindKeyword == "class") {
                    append("\n\n# Default constructor <init>\n.method public constructor <init>()V\n    .locals 0\n    invoke-direct {p0}, $superDesc-><init>()V\n    return-void\n.end method")
                }
            }

            return JavaDecodedResult(
                rawJava = rawInput,
                statementKind = localized(
                    "Java ${kindKeyword.replaceFirstChar { it.uppercase() }} Header",
                    "জাভা ${kindKeyword.uppercase()} হেডার",
                    "جاوا ${kindKeyword.uppercase()} ہیڈر",
                    "जावा ${kindKeyword.uppercase()} हेडर",
                    "تصريح ${kindKeyword.uppercase()} في جافا",
                    "اعلان ${kindKeyword.uppercase()} جاوا",
                    "Header ${kindKeyword.uppercase()} Java"
                ),
                modifiersSummary = smaliMods.ifEmpty { "(package-private)" },
                memberName = rawClassName,
                returnOrFieldType = "super: $superJava",
                parametersSummary = if (interfaces.isEmpty()) "-" else interfaces.joinToString(", "),
                generatedSmali = smaliOut,
                plainExplanation = localized(
                    en = "Compiles `$rawClassName` into `$classDesc` inheriting from `$superDesc`" +
                        (if (interfaces.isNotEmpty()) " and implementing ${interfaces.size} interface(s)." else "."),
                    bn = "এটি `$rawClassName` ক্লাসকে স্মালির `$classDesc` ফাইলে রূপান্তর করে, যার প্যারেন্ট ক্লাস `$superDesc`" +
                        (if (interfaces.isNotEmpty()) " এবং এতে ${interfaces.size}টি ইন্টারফেস যুক্ত আছে।" else "।"),
                    ur = "یہ `$rawClassName` کو سمالی کے `$classDesc` میں بدلتا ہے جس کی پیرنٹ کلاس `$superDesc` ہے۔",
                    hi = "यह `$rawClassName` को स्माली के `$classDesc` में बदलता है जिसकी पैरेंट क्लास `$superDesc` है।",
                    ar = "تترجم الفئة `$rawClassName` إلى `$classDesc` مع وراثة `$superDesc`.",
                    fa_af = "کلاس `$rawClassName` را به `$classDesc` با کلاس والد `$superDesc` کامپایل می‌کند.",
                    in_id = "Mengompilasi `$rawClassName` menjadi `$classDesc` dengan induk `$superDesc`."
                )
            )
        }

        // CASE B: Method or Constructor declaration (contains parentheses before '=')
        val beforeEquals = cleaned.substringBefore("=")
        if (beforeEquals.contains("(") && cleaned.contains(")")) {
            val headerPart = cleaned.substringBefore("throws").trim()
            val openParen = headerPart.indexOf('(')
            val closeParen = headerPart.lastIndexOf(')')
            val prefixTokens = headerPart.substring(0, openParen).trim()
                .split(Regex("""\s+"""))
                .filter { it.isNotBlank() }
            val rawParamsInside = headerPart.substring(openParen + 1, closeParen).trim()

            val mods = prefixTokens.takeWhile { it in knownMods }
            val remaining = prefixTokens.drop(mods.size)

            val isConstructor = remaining.size == 1
            val methodName = if (isConstructor) "<init>" else (remaining.lastOrNull() ?: "method")
            val javaOwnerOrReturn = if (isConstructor) "void" else remaining.dropLast(1).joinToString(" ").ifBlank { "void" }
            val returnSmali = if (isConstructor) "V" else javaTypeToSmali(javaOwnerOrReturn)

            val parsedParams: List<Pair<String, String>> = if (rawParamsInside.isEmpty()) {
                emptyList()
            } else {
                splitGenericParams(rawParamsInside).mapIndexed { idx, p ->
                    val pClean = p.trim().removePrefix("final ").trim()
                    val parts = pClean.split(Regex("""\s+""")).filter { it.isNotBlank() }
                    if (parts.size >= 2) {
                        val pType = parts.dropLast(1).joinToString(" ")
                        val pName = parts.last()
                        pType to pName
                    } else {
                        parts.firstOrNull().orEmpty() to "arg$idx"
                    }
                }
            }

            val smaliParamDescriptors = parsedParams.joinToString("") { (pType, _) ->
                javaTypeToSmali(pType)
            }

            val isStatic = "static" in mods
            val isAbstract = "abstract" in mods || "native" in mods
            val smaliMods = buildList {
                addAll(mods)
                if (isConstructor) add("constructor")
            }.joinToString(" ")

            val paramLines = buildList {
                var regIdx = if (isStatic) 0 else 1
                parsedParams.forEach { (pType, pName) ->
                    val desc = javaTypeToSmali(pType)
                    add("    .param p$regIdx, \"$pName\"    # $desc")
                    regIdx += if (desc == "J" || desc == "D") 2 else 1
                }
            }

            val localsCount = when {
                returnSmali == "V" -> 0
                returnSmali == "J" || returnSmali == "D" -> 2
                else -> 1
            }

            val bodyLines = when {
                isAbstract -> emptyList()
                isConstructor -> listOf(
                    "    .locals 0",
                    "    # p0 holds 'this' instance",
                    "    invoke-direct {p0}, Ljava/lang/Object;-><init>()V",
                    "    return-void"
                )
                returnSmali == "V" -> listOf(
                    "    .locals 0",
                    if (isStatic) "    # static method: p0 is first argument (no 'this')" else "    # instance method: p0 = this",
                    "    return-void"
                )
                returnSmali == "J" || returnSmali == "D" -> listOf(
                    "    .locals 2",
                    "    const-wide/16 v0, 0x0",
                    "    return-wide v0"
                )
                returnSmali.startsWith("L") || returnSmali.startsWith("[") -> listOf(
                    "    .locals $localsCount",
                    "    const/4 v0, 0x0",
                    "    return-object v0"
                )
                else -> listOf(
                    "    .locals $localsCount",
                    "    const/4 v0, 0x0",
                    "    return v0"
                )
            }

            val smaliOut = buildString {
                append(".method ")
                if (smaliMods.isNotEmpty()) append("$smaliMods ")
                append("$methodName($smaliParamDescriptors)$returnSmali\n")
                if (paramLines.isNotEmpty()) {
                    append(paramLines.joinToString("\n"))
                    append("\n")
                }
                if (bodyLines.isNotEmpty()) {
                    append(bodyLines.joinToString("\n"))
                    append("\n")
                }
                append(".end method")
            }

            val paramsDisplay = if (parsedParams.isEmpty()) {
                "0 params"
            } else {
                parsedParams.joinToString(", ") { (t, n) -> "$n: ${javaTypeToSmali(t)}" }
            }

            return JavaDecodedResult(
                rawJava = rawInput,
                statementKind = if (isConstructor) {
                    localized("Java Constructor (<init>)", "জাভা কনস্ট্রাক্টর (<init>)", "جاوا کنسٹرکٹر (<init>)", "जावा कंस्ट्रक्टर (<init>)", "باني جافا (<init>)", "سازنده جاوا (<init>)", "Konstruktor Java (<init>)")
                } else {
                    localized("Java Method Declaration", "জাভা মেথড ডিক্লারেশন", "جاوا میتھڈ ہیڈر", "जावा मेथड घोषणा", "تصريح دالة جافا", "اعلان متد جاوا", "Deklarasi Metode Java")
                },
                modifiersSummary = smaliMods.ifEmpty { "(package-private)" },
                memberName = if (isConstructor) "${remaining.first()} -> <init>" else methodName,
                returnOrFieldType = "$javaOwnerOrReturn ($returnSmali)",
                parametersSummary = paramsDisplay,
                generatedSmali = smaliOut,
                plainExplanation = localized(
                    en = (if (isStatic) "Static method (no `this`, `p0` = 1st arg)" else "Instance method (`p0` = `this`)") +
                        " accepting ${parsedParams.size} parameter(s) `($smaliParamDescriptors)` and returning `$returnSmali`.",
                    bn = (if (isStatic) "এটি একটি Static মেথড (এতে `this` নেই, `p0` হলো প্রথম ইনপুট)" else "এটি একটি Instance মেথড (যেখানে `p0` রেজিস্টারে `this` অবজেক্ট থাকে)") +
                        " — এতে ${parsedParams.size}টি প্যারামিটার `($smaliParamDescriptors)` আছে এবং রিটার্ন টাইপ `$returnSmali`।",
                    ur = (if (isStatic) "یہ اسٹیٹک میتھڈ ہے (`p0` پہلا آرگومنٹ ہے)" else "یہ انسٹنس میتھڈ ہے (`p0` میں `this` ہے)") +
                        " جو `($smaliParamDescriptors)` لے کر `$returnSmali` واپس کرتا ہے۔",
                    hi = (if (isStatic) "यह Static मेथड है (`p0` पहला इनपुट है)" else "यह Instance मेथड है (`p0` में `this` है)") +
                        " जो `($smaliParamDescriptors)` लेकर `$returnSmali` लौटाता है।",
                    ar = (if (isStatic) "دالة ثابتة (`p0` هو المعامل الأول)" else "دالة كائن (`p0` يحمل `this`)") +
                        " تستقبل `($smaliParamDescriptors)` وتعيد `$returnSmali`.",
                    fa_af = (if (isStatic) "متد استاتیک (`p0` اولین آرگومان است)" else "متد شیء (`p0` همان `this` است)") +
                        " با ورودی `($smaliParamDescriptors)` و خروجی `$returnSmali`.",
                    in_id = (if (isStatic) "Metode statis (`p0` = argumen ke-1)" else "Metode objek (`p0` = `this`)") +
                        " dengan parameter `($smaliParamDescriptors)` dan return `$returnSmali`."
                )
            )
        }

        // CASE C: Java Field / Variable declaration
        val lhs = cleaned.substringBefore("=").trim()
        val rhs = if (cleaned.contains("=")) cleaned.substringAfter("=").trim() else null
        val tokens = lhs.split(Regex("""\s+""")).filter { it.isNotBlank() }
        val mods = tokens.takeWhile { it in knownMods }
        val rest = tokens.drop(mods.size)
        val fieldName = rest.lastOrNull() ?: "value"
        val javaType = rest.dropLast(1).joinToString(" ").ifBlank { "int" }
        val desc = javaTypeToSmali(javaType)
        val isStatic = "static" in mods
        val isFinal = "final" in mods
        val smaliMods = mods.joinToString(" ")

        val literalSuffix = if (isStatic && isFinal && rhs != null && (desc == "I" || desc == "Z" || desc == "Ljava/lang/String;")) {
            " = $rhs"
        } else ""

        val accessHint = when {
            isStatic && (desc == "J" || desc == "D") -> "# Read/Write via: sget-wide / sput-wide v0, Lcom/example/Demo;->$fieldName:$desc"
            isStatic && (desc.startsWith("L") || desc.startsWith("[")) -> "# Read/Write via: sget-object / sput-object v0, Lcom/example/Demo;->$fieldName:$desc"
            isStatic -> "# Read/Write via: sget / sput v0, Lcom/example/Demo;->$fieldName:$desc"
            desc == "J" || desc == "D" -> "# Read/Write via: iget-wide / iput-wide v0, p0, Lcom/example/Demo;->$fieldName:$desc"
            desc.startsWith("L") || desc.startsWith("[") -> "# Read/Write via: iget-object / iput-object v0, p0, Lcom/example/Demo;->$fieldName:$desc"
            else -> "# Read/Write via: iget / iput v0, p0, Lcom/example/Demo;->$fieldName:$desc"
        }

        val smaliOut = buildString {
            append(".field ")
            if (smaliMods.isNotEmpty()) append("$smaliMods ")
            append("$fieldName:$desc$literalSuffix\n$accessHint")
        }

        return JavaDecodedResult(
            rawJava = rawInput,
            statementKind = localized("Java Field Declaration", "জাভা ফিল্ড / ভেরিয়েবল", "جاوا فیلڈ متغیر", "जावा फ़ील्ड घोषणा", "تصريح حقل جافا", "اعلان فیلد جاوا", "Deklarasi Field Java"),
            modifiersSummary = smaliMods.ifEmpty { "(package-private)" },
            memberName = fieldName,
            returnOrFieldType = "$javaType ($desc)",
            parametersSummary = if (isStatic) "Class Field (sget/sput)" else "Instance Field (iget/iput)",
            generatedSmali = smaliOut,
            plainExplanation = localized(
                en = "Declares ${if (isStatic) "static class" else "instance"} field `$fieldName` of Smali type `$desc`.",
                bn = "এটি `$desc` স্মালি টাইপের একটি ${if (isStatic) "স্ট্যাটিক ক্লাস" else "ইনস্ট্যান্স"} ফিল্ড `$fieldName` তৈরি করে।",
                ur = "یہ `$desc` ٹائپ کا ایک ${if (isStatic) "اسٹیٹک" else "انسٹنس"} فیلڈ `$fieldName` بناتا ہے۔",
                hi = "यह `$desc` प्रकार का ${if (isStatic) "स्टैटिक" else "इंस्टेंस"} फ़ील्ड `$fieldName` घोषित करता है।",
                ar = "يعرّف حقل ${if (isStatic) "فئة ثابت" else "كائن"} باسم `$fieldName` ونوع سمالي `$desc`.",
                fa_af = "فیلد ${if (isStatic) "استاتیک" else "شیء"} به نام `$fieldName` با نوع سمالی `$desc` تعریف می‌کند.",
                in_id = "Mendeklarasikan field ${if (isStatic) "statis" else "objek"} `$fieldName` dengan deskriptor Smali `$desc`."
            )
        )
    }

    private fun splitGenericParams(params: String): List<String> {
        val result = mutableListOf<String>()
        val sb = StringBuilder()
        var depth = 0
        for (ch in params) {
            when (ch) {
                '<' -> {
                    depth++
                    sb.append(ch)
                }
                '>' -> {
                    depth = (depth - 1).coerceAtLeast(0)
                    sb.append(ch)
                }
                ',' -> if (depth == 0) {
                    result.add(sb.toString())
                    sb.clear()
                } else {
                    sb.append(ch)
                }
                else -> sb.append(ch)
            }
        }
        if (sb.isNotBlank()) result.add(sb.toString())
        return result
    }

    fun javaTypeToSmali(rawType: String): String {
        var t = rawType.trim()
        if (t.endsWith("...")) {
            t = t.removeSuffix("...") + "[]"
        }
        var arrayDims = 0
        while (t.endsWith("[]")) {
            arrayDims++
            t = t.removeSuffix("[]").trim()
        }
        // Erase generics (<...>) as DEX bytecode does
        val base = t.substringBefore("<").trim()
        val core = when (base) {
            "void" -> "V"
            "boolean" -> "Z"
            "byte" -> "B"
            "short" -> "S"
            "char" -> "C"
            "int" -> "I"
            "long" -> "J"
            "float" -> "F"
            "double" -> "D"
            "String" -> "Ljava/lang/String;"
            "Object" -> "Ljava/lang/Object;"
            "Integer" -> "Ljava/lang/Integer;"
            "Long" -> "Ljava/lang/Long;"
            "Boolean" -> "Ljava/lang/Boolean;"
            "Double" -> "Ljava/lang/Double;"
            "Float" -> "Ljava/lang/Float;"
            "Runnable" -> "Ljava/lang/Runnable;"
            "Exception" -> "Ljava/lang/Exception;"
            "Throwable" -> "Ljava/lang/Throwable;"
            "StringBuilder" -> "Ljava/lang/StringBuilder;"
            "List" -> "Ljava/util/List;"
            "ArrayList" -> "Ljava/util/ArrayList;"
            "Map" -> "Ljava/util/Map;"
            "HashMap" -> "Ljava/util/HashMap;"
            "Set" -> "Ljava/util/Set;"
            "HashSet" -> "Ljava/util/HashSet;"
            "Optional" -> "Ljava/util/Optional;"
            "Bundle" -> "Landroid/os/Bundle;"
            "Context" -> "Landroid/content/Context;"
            "Intent" -> "Landroid/content/Intent;"
            "View" -> "Landroid/view/View;"
            else -> if (base.contains(".")) {
                "L${base.replace('.', '/')};"
            } else {
                "Lcom/example/$base;"
            }
        }
        return "[".repeat(arrayDims) + core
    }
}
