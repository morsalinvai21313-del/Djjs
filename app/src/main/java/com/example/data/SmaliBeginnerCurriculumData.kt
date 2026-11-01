package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object SmaliBeginnerCurriculumData {
    val lessons: List<Lesson> = listOf(
        // MODULE 1
        Lesson(
            id = "smali_01",
            track = TrackType.SMALI,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "01. What is Smali, DEX & Dalvik/ART?",
                bn = "০১. স্মালি (Smali), DEX ও ডালভিক কী? (একদম শুরু)",
                ur = "01. سمالی، DEX اور ڈالوک/ART کیا ہے؟",
                hi = "01. स्माली (Smali), DEX और डाल्विक/ART क्या है?",
                ar = "01. ما هي لغة سمالي و DEX ومحرك ART؟",
                fa_af = "۰۱. سمالی، DEX و ماشین دالویک/ART چیست؟",
                in_id = "01. Apa Itu Smali, DEX & Dalvik/ART?"
            ),
            subtitle = localized(
                en = "Start from absolute zero: how Java & Kotlin become classes.dex and human-readable Smali.",
                bn = "একদম জিরো থেকে বুঝুন কীভাবে জাভা ও কোটলিন কোড classes.dex এবং মানুষের পড়ার যোগ্য স্মালি কোডে পরিণত হয়।",
                ur = "بالکل صفر سے سمجھیں کہ جاوا اور کوٹلن کوڈ کس طرح classes.dex اور سمالی میں بدلتا ہے۔",
                hi = "शून्य से समझें कि जावा और कोटलिन कोड कैसे classes.dex और पठनीय स्माली कोड में बदलता है।",
                ar = "ابدأ من الصفر تماماً: كيف يتحول كود جافا وكوتلن إلى ملفات DEX وشيفرة سمالي المقروءة.",
                fa_af = "از صفر مطلق بیاموزید چگونه کدهای جاوا و کاتلین به classes.dex و کد سمالی تبدیل می‌شوند.",
                in_id = "Mulai dari nol: bagaimana kode Java & Kotlin berubah menjadi classes.dex dan Smali."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "From App Source Code to Smali",
                        bn = "সোর্স কোড থেকে স্মালি তৈরির সম্পূর্ণ যাত্রা",
                        ur = "سورس کوڈ سے سمالی بننے کا مکمل سفر",
                        hi = "सोर्स कोड से स्माली बनने की पूरी यात्रा",
                        ar = "رحلة الكود من المصدر إلى شيفرة سمالي",
                        fa_af = "مسیر تبدیل کد منبع برنامه به سمالی",
                        in_id = "Perjalanan Kode Sumber Menjadi Smali"
                    ),
                    childAnalogy = localized(
                        en = "Imagine writing a recipe in English (Java/Kotlin). To bake it fast, the kitchen robot compresses it into binary numbers (classes.dex). 'Baksmali' is a smart translator lens that turns those robot numbers into neat step-by-step cards called Smali!",
                        bn = "ধরুন আপনি বাংলায় একটি রান্নার রেসিপি লিখলেন (এটি হলো Java/Kotlin)। ফোনের প্রসেসর দ্রুত কাজ করার জন্য সেটিকে বাইনারি সংখ্যার ফাইলে (`classes.dex`) রূপান্তর করে। আর সেই বাইনারি ফাইলটিকে মানুষের চোখে পড়ার মতো পরিষ্কার লাইনে সাজিয়ে দেখানোর ভাষাই হলো স্মালি (Smali)!",
                        ur = "فرض کریں آپ نے ایک ترکیب لکھی (Java/Kotlin)۔ فون اسے بائنری نمبروں (`classes.dex`) میں محفوظ کرتا ہے۔ سمالی وہ صاف زبان ہے جو ان مشین نمبروں کو انسان کے پڑھنے کے قابل بناتی ہے!",
                        hi = "मान लीजिए आपने एक रेसिपी लिखी (Java/Kotlin)। फोन इसे तेज़ी से चलाने के लिए बाइनरी फ़ाइल (`classes.dex`) में बदल देता है। उन मशीन नंबरों को इंसान के पढ़ने लायक साफ़ लाइनों में दिखाने वाली भाषा ही स्माली (Smali) है!",
                        ar = "تخيل أنك كتبت وصفة بلغة مفهومة (جافا/كوتلن)، ثم ضغطها الهاتف إلى أرقام ثنائية (classes.dex). لغة سمالي هي العدسة التي تحول تلك الأرقام إلى بطاقات مقروءة بوضوح!",
                        fa_af = "تصور کنید یک دستور پخت نوشته‌اید (جاوا/کاتلین) و گوشی آن را به اعداد باینری (classes.dex) فشرده می‌کند. سمالی همان مترجم شفافی است که آن اعداد ماشینی را به خطوط خوانا تبدیل می‌کند!",
                        in_id = "Bayangkan menulis resep dalam bahasa manusia (Java/Kotlin), lalu ponsel memadatkannya menjadi biner (classes.dex). Smali adalah bahasa yang menerjemahkan biner itu menjadi baris instruksi yang mudah dibaca!"
                    ),
                    content = localized(
                        en = "Android apps (.apk) do not run raw Java or Kotlin text. During compilation, D8/R8 compiles all classes into Dalvik Executable (DEX) files (`classes.dex`). Because raw DEX bytes are hexadecimal numbers, JesusFreke created `baksmali` (disassembler) and `smali` (assembler)—Icelandic words for 'disassembler' and 'assembler'. Every single instruction in Smali maps 1-to-1 with real DEX bytecode.",
                        bn = "অ্যান্ড্রয়েড অ্যাপ (.apk) সরাসরি জাভা বা কোটলিন ফাইল চালায় না। কম্পাইল করার সময় D8/R8 কম্পাইলার সব কোডকে `classes.dex` (Dalvik Executable) ফাইলে রূপান্তর করে। কাঁচা DEX ফাইল হেক্সাডেসিমাল সংখ্যায় ভরা থাকে যা সরাসরি পড়া অসম্ভব। তাই `baksmali` টুল দিয়ে DEX ভাঙলে যে পরিষ্কার অ্যাসেম্বলি কোড পাওয়া যায় সেটিই হলো Smali, আর আবার জোড়া লাগালে সেটি পুনরায় খাঁটি DEX হয়ে যায়।",
                        ur = "اینڈرائیڈ ایپس براہ راست جاوا یا کوٹلن نہیں چلاتیں۔ کمپائلر تمام کوڈ کو `classes.dex` میں تبدیل کرتا ہے۔ `baksmali` اس بائنری فائل کو پڑھنے کے قابل سمالی کوڈ میں کھولتا ہے، جہاں ہر لائن براہ راست حقیقی بائٹ کوڈ کی نمائندگی کرتی ہے۔",
                        hi = "एंड्रॉइड ऐप्स (.apk) सीधे जावा या कोटलिन टेक्स्ट नहीं चलाते। D8/R8 कंपाइलर सभी कोड को `classes.dex` में बदलता है। `baksmali` इसे पठनीय स्माली कोड में खोलता है, जहाँ हर एक निर्देश सीधे वास्तविक DEX बाइटकोड के बराबर होता है।",
                        ar = "تطبيقات أندرويد لا تشغل نصوص جافا أو كوتلن مباشرة، بل يجمعها المترجم D8/R8 داخل ملف `classes.dex`. تقوم أداة `baksmali` بتحويل هذا الملف الثنائي إلى لغة سمالي حيث يطابق كل سطر تعليمة حقيقية.",
                        fa_af = "برنامه‌های اندروید مستقیماً کد جاوا یا کاتلین را اجرا نمی‌کنند؛ کامپایلر D8/R8 آن‌ها را به فایل `classes.dex` تبدیل می‌کند. ابزار `baksmali` این بایت‌کد را به زبان سمالی تبدیل می‌کند که رابطه‌ای یک‌به‌یک با دستورات اجرایی دارد.",
                        in_id = "Aplikasi Android (.apk) tidak menjalankan teks Java atau Kotlin mentah. Kompiler D8/R8 mengubahnya menjadi `classes.dex`. Alat `baksmali` menguraikan biner tersebut menjadi kode Smali yang memetakan 1-banding-1 dengan bytecode DEX."
                    ),
                    javaCode = """public class Greeter {
    public static int doubleValue(int x) {
        return x * 2;
    }
}""",
                    smaliCode = """.class public Lcom/example/Greeter;
.super Ljava/lang/Object;

.method public static doubleValue(I)I
    .locals 1

    mul-int/lit8 v0, p0, 0x2
    return v0
.end method""",
                    practicalExampleNote = localized(
                        en = "Notice how 5 lines of Java become exact register instructions: multiply parameter p0 by 2, store in v0, and return v0.",
                        bn = "লক্ষ্য করুন: ইনপুট `p0`-কে `0x2` (মানে ২) দিয়ে গুণ করে `v0` রেজিস্টারে রাখা হয়েছে এবং `return v0` দিয়ে ফেরত পাঠানো হয়েছে—কোনো লুকানো জাদু নেই!",
                        ur = "غور کریں کہ ان پٹ `p0` کو 2 سے ضرب دے کر `v0` میں رکھا گیا اور `return v0` سے واپس بھیج دیا گیا۔",
                        hi = "ध्यान दें कि इनपुट `p0` को 2 से गुणा करके `v0` में रखा गया और `return v0` से लौटा दिया गया।",
                        ar = "لاحظ كيف تم ضرب المعامل `p0` في 2 وحفظ النتيجة في `v0` ثم إرجاعها مباشرة.",
                        fa_af = "ببینید چگونه ورودی `p0` در ۲ ضرب شده، در `v0` ذخیره گشته و با `return v0` بازگردانده می‌شود.",
                        in_id = "Perhatikan bagaimana parameter `p0` dikalikan 2, disimpan ke `v0`, lalu dikembalikan dengan `return v0`."
                    )
                ),
                LessonSection(
                    title = localized(
                        en = "Register Machine vs Stack Machine",
                        bn = "রেজিস্টার মেশিন (Dalvik) বনাম স্ট্যাক মেশিন (JVM)",
                        ur = "رجسٹر مشین بمقابلہ اسٹیک مشین",
                        hi = "रजिस्टर मशीन बनाम स्टैक मशीन",
                        ar = "آلة المسجلات مقابل آلة المكدس",
                        fa_af = "ماشین مبتنی بر رجستر در برابر ماشین پشته‌ای",
                        in_id = "Mesin Register vs Mesin Stack"
                    ),
                    content = localized(
                        en = "Standard desktop Java (JVM) pushes and pops values on a temporary stack. Android's Dalvik/ART uses numbered virtual registers (`v0`, `v1`, `p0`, `p1`) directly mimicking ARM CPU registers on mobile chips—reducing instruction count and saving battery!",
                        bn = "কম্পিউটারের সাধারণ Java JVM ডাটা স্ট্যাকের (Stack) ওপর ওঠানামা করিয়ে কাজ করে। কিন্তু মোবাইলের ব্যাটারি ও গতি বাঁচাতে অ্যান্ড্রয়েডের Dalvik/ART সরাসরি নাম্বারযুক্ত রেজিস্টার (`v0`, `v1`, `p0`, `p1`) ব্যবহার করে—যা ফোনের ARM প্রসেসরের সাথে সরাসরি মিলে যায়!",
                        ur = "کمپیوٹر کا جاوا JVM اسٹیک استعمال کرتا ہے، جبکہ اینڈرائیڈ کا Dalvik/ART موبائل بیٹری اور رفتار بچانے کے لیے براہ راست نمبر والے رجسٹرز (`v0`, `v1`, `p0`) استعمال کرتا ہے۔",
                        hi = "डेस्कटॉप JVM स्टैक का उपयोग करता है, जबकि एंड्रॉइड का Dalvik/ART मोबाइल CPU और बैटरी दक्षता के लिए सीधे नामित रजिस्टरों (`v0`, `v1`, `p0`) का उपयोग करता है।",
                        ar = "تعتمد آلة جافا المكتبية على المكدس، بينما يستخدم محرك أندرويد مسجلات مرقمة (`v0`, `v1`, `p0`) تحاكي معالجات الهواتف لتقليل عدد التعليمات وتوفير الطاقة.",
                        fa_af = "ماشین جاوای معمولی از پشته استفاده می‌کند، اما دالویک/ART در اندروید از رجسترهای شماره‌گذاری‌شده (`v0`, `v1`, `p0`) بهره می‌برد تا مصرف باتری کم و سرعت اجرا بسیار بالا باشد.",
                        in_id = "JVM desktop memakai tumpukan (stack), sedangkan Dalvik/ART Android menggunakan register bernomor (`v0`, `v1`, `p0`) agar hemat instruksi dan baterai."
                    )
                )
            )
        ),

        // MODULE 2
        Lesson(
            id = "smali_02",
            track = TrackType.SMALI,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "02. Anatomy of a Complete .smali File",
                bn = "০২. একটি পূর্ণাঙ্গ .smali ফাইলের গঠন ও অংশসমূহ",
                ur = "02. ایک مکمل .smali فائل کی ساخت",
                hi = "02. एक संपूर्ण .smali फ़ाइल की संरचना",
                ar = "02. تشريح وبنية ملف .smali الكامل",
                fa_af = "۰۲. کالبدشکافی کامل ساختار یک فایل .smali",
                in_id = "02. Anatomi Lengkap File .smali"
            ),
            subtitle = localized(
                en = "Read top-to-bottom headers: .class, .super, .source, .implements, fields, and method blocks.",
                bn = "শুরু থেকে শেষ পর্যন্ত চিনে নিন: .class, .super, .source, .implements, ফিল্ড এবং মেথড ব্লক।",
                ur = "اوپر سے نیچے تک ہر حصے کو پہچانیں: .class، .super، فیلڈز اور میتھڈ بلاکس۔",
                hi = "ऊपर से नीचे तक पहचानें: .class, .super, .source, फ़ील्ड और मेथड ब्लॉक।",
                ar = "تعرف على ترويسات الملف من الأعلى للأسفل: الفئة، الأب، الواجهات، الحقول والدوال.",
                fa_af = "آشنایی با تمام بخش‌های فایل از بالا به پایین: .class، .super، فیلدها و متدها.",
                in_id = "Kenali struktur dari atas ke bawah: .class, .super, .source, field & blok metode."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The 5 Structural Zones of Every Smali File",
                        bn = "প্রতিটি স্মালি ফাইলের ৫টি প্রধান অংশ",
                        ur = "ہر سمالی فائل کے 5 بنیادی حصے",
                        hi = "प्रत्येक स्माली फ़ाइल के 5 मुख्य भाग",
                        ar = "المناطق الخمس الأساسية في كل ملف سمالي",
                        fa_af = "۵ بخش اصلی در هر فایل سمالی",
                        in_id = "5 Zona Struktural di Setiap File Smali"
                    ),
                    childAnalogy = localized(
                        en = "Think of a Smali file like an ID card & manual: Top = Who am I (.class) and who is my parent (.super); Middle = What items I carry (.field); Bottom = What skills I can perform (.method ... .end method).",
                        bn = "একটি স্মালি ফাইলকে মানুষের পরিচয়পত্রের মতো ভাবুন: একদম ওপরে নিজের নাম (`.class`) ও বাবার নাম (`.super`), মাঝখানে তার কাছে থাকা জিনিসপত্র (`.field`), আর নিচে সে কী কী কাজ করতে পারে (`.method ... .end method`)!",
                        ur = "سمالی فائل کو شناختی کارڈ سمجھیں: اوپر اپنا نام (`.class`) اور والد کا نام (`.super`)، درمیان میں سامان (`.field`)، اور نیچے کام کرنے کے طریقے (`.method`)۔",
                        hi = "स्माली फ़ाइल को पहचान-पत्र समझें: सबसे ऊपर अपना नाम (`.class`) और माता-पिता (`.super`), बीच में सामान (`.field`), और नीचे कार्यक्षमताएँ (`.method`)!",
                        ar = "تخيل ملف سمالي كبطاقة تعريف: في الأعلى اسم الفئة والأب، وفي الوسط ممتلكاتها (.field)، وفي الأسفل المهارات التي تنفذها (.method).",
                        fa_af = "فایل سمالی مانند کارت شناسایی است: در بالا نام کلاس و والد آن، در میانه دارایی‌ها (.field) و در پایین مهارت‌ها و متدها (.method).",
                        in_id = "Anggap file Smali seperti kartu identitas: atas = nama & induk (.class/.super), tengah = atribut (.field), bawah = aksi (.method)."
                    ),
                    content = localized(
                        en = "Every `.smali` file represents exactly ONE compiled class (even inner classes get their own `Outer\$Inner.smali` file). Lines starting with a dot (`.`) are assembler directives, and lines starting with `#` are comments.",
                        bn = "প্রতিটি `.smali` ফাইল ঠিক একটি ক্লাসকে নির্দেশ করে (এমনকি ভেতরের Inner Class-ও আলাদা `Outer\$Inner.smali` ফাইল হিসেবে তৈরি হয়)। ডট (`.`) দিয়ে শুরু হওয়া শব্দগুলো হলো ডিরেক্টিভ এবং `#` দিয়ে শুরু হওয়া লাইনগুলো হলো কমেন্ট বা নোট।",
                        ur = "ہر `.smali` فائل صرف ایک کلاس کی نمائندگی کرتی ہے۔ ڈاٹ (`.`) سے شروع ہونے والی لائنیں ڈائریکٹوز ہیں اور `#` سے شروع ہونے والی لائنیں تبصرے (Comments) ہیں۔",
                        hi = "प्रत्येक `.smali` फ़ाइल ठीक एक क्लास को दर्शाती है। डॉट (`.`) से शुरू होने वाली पंक्तियाँ डायरेक्टिव होती हैं और `#` से शुरू होने वाली पंक्तियाँ कमेंट होती हैं।",
                        ar = "يمثل كل ملف `.smali` فئة واحدة فقط. الأسطر التي تبدأ بنقطة (`.`) هي توجيهات للمجمع، والتي تبدأ بـ `#` هي تعليقات توضيحية.",
                        fa_af = "هر فایل `.smali` دقیقاً نماینده یک کلاس است. خطوطی که با نقطه (`.`) آغاز می‌شوند دایرکتیو و خطوط دارای `#` توضیحات (کامنت) هستند.",
                        in_id = "Setiap file `.smali` mewakili tepat satu kelas. Baris diawali titik (`.`) adalah direktif, sedangkan `#` adalah komentar."
                    ),
                    smaliCode = """.class public final Lcom/example/UserCard;
.super Ljava/lang/Object;
.source "UserCard.kt"
.implements Ljava/io/Serializable;

# static fields
.field public static final MAX_SCORE:I = 0x64

# instance fields
.field private username:Ljava/lang/String;

# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    iput-object p1, p0, Lcom/example/UserCard;->username:Ljava/lang/String;
    return-void
.end method""",
                    practicalExampleNote = localized(
                        en = "Notice how packages use forward slashes `/` instead of dots, start with `L`, and ALWAYS end with a semicolon `;`.",
                        bn = "খেয়াল করুন: স্মালিতে ক্লাসের নামের শুরুতে বড় হাতের `L` থাকে, ডটের বদলে স্ল্যাশ `/` বসে এবং শেষে সেমিকোলন `;` দেওয়া বাধ্যতামূলক (`Lcom/example/UserCard;`)।",
                        ur = "نوٹ کریں: کلاس کا نام ہمیشہ `L` سے شروع ہوتا ہے، ڈاٹ کی جگہ `/` آتا ہے اور آخر میں سیمی کولن `;` لگانا لازمی ہے۔",
                        hi = "ध्यान दें: क्लास का नाम हमेशा `L` से शुरू होता है, डॉट के बजाय `/` लगता है और अंत में सेमीकोलन `;` अनिवार्य होता है।",
                        ar = "انتبه: تبدأ أسماء الفئات دائماً بحرف `L` وتفصل الحزم بعلامة `/` وتنتهي حتماً بفاصلة منقوطة `;`.",
                        fa_af = "دقت کنید: نام کلاس‌ها همیشه با `L` شروع شده، به‌جای نقطه از `/` استفاده می‌کنند و حتماً با سمی‌کالن `;` پایان می‌یابند.",
                        in_id = "Perhatikan: nama kelas selalu diawali `L`, memakai garis miring `/`, dan wajib diakhiri titik koma `;`."
                    )
                )
            )
        ),

        // MODULE 3
        Lesson(
            id = "smali_03",
            track = TrackType.SMALI,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "03. Registers Deep Dive: v vs p, 'this' & 64-Bit Pairs",
                bn = "০৩. রেজিস্টারের পূর্ণাঙ্গ নিয়ম: v বনাম p, this এবং ৬৪-বিট জোড়া",
                ur = "03. رجسٹرز کی مکمل تفصیل: v بمقابلہ p، this اور 64-بٹ جوڑے",
                hi = "03. रजिस्टर का संपूर्ण नियम: v बनाम p, this और 64-बिट जोड़े",
                ar = "03. شرح المسجلات بالتفصيل: v مقابل p ومؤشر this وأزواج 64-بت",
                fa_af = "۰۳. کالبدشکافی رجسترها: تفاوت v و p، اشاره‌گر this و جفت‌های ۶۴ بیتی",
                in_id = "03. Kupas Tuntas Register: v vs p, 'this' & Pasangan 64-Bit"
            ),
            subtitle = localized(
                en = "Master .locals, parameter aliasing, why p0 changes meaning in static methods, and wide registers.",
                bn = ".locals কীভাবে কাজ করে, কেন Static মেথডে p0 বদলে যায় এবং কেন Long/Double দুটি রেজিস্টার নেয় তা শিখুন।",
                ur = ".locals، اسٹیٹک میتھڈ میں p0 کا کردار اور Long/Double کے دو رجسٹرز کا اصول سیکھیں۔",
                hi = ".locals कैसे काम करता है, स्टैटिक मेथड में p0 क्यों बदलता है और Long/Double दो स्लॉट क्यों लेते हैं।",
                ar = "أتقن .locals ولماذا يتغير معنى p0 في الدوال الثابتة وكيف تحجز الأنواع العريضة مسجلين متجاورين.",
                fa_af = "درک دقیق .locals، تغییر نقش p0 در متدهای Static و دلیل اشغال دو رجستر توسط Long و Double.",
                in_id = "Kuasai .locals, mengapa arti p0 berubah di metode static, dan pasangan register 64-bit."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "How v0..vN and p0..pM Share One Frame",
                        bn = "কীভাবে v0..vN এবং p0..pM একই মেমোরি ফ্রেমে বসে",
                        ur = "v0..vN اور p0..pM ایک ہی فریم میں کیسے بیٹھتے ہیں",
                        hi = "v0..vN और p0..pM एक ही मेमोरी फ़्रेम में कैसे बैठते हैं",
                        ar = "كيف تتشارك مسجلات v و p إطار ذاكرة واحد",
                        fa_af = "نحوه قرارگیری v0..vN و p0..pM در یک قاب حافظه واحد",
                        in_id = "Bagaimana v0..vN dan p0..pM Berbagi Satu Frame"
                    ),
                    childAnalogy = localized(
                        en = "Imagine 5numbered lockers in a row: first 2 lockers are your private scratchpads (`v0`, `v1`), and the remaining 3 lockers hold packages handed to you (`p0`, `p1`, `p2`). Under the hood, `p0` is actually locker #2 (`v2`)!",
                        bn = "ভাবুন সারিবদ্ধ ৫টি লকার আছে: প্রথম ২টি লকার আপনার নিজস্ব রাফ খাতা (`v0`, `v1` অর্থাৎ `.locals 2`), আর পরের ৩টি লকারে বাইরে থেকে আসা ইনপুট রাখা আছে (`p0`, `p1`, `p2`)। আসল মেমোরিতে `p0` হলো ৩ নম্বর লকার (`v2`)!",
                        ur = "سوچیں قطار میں 5 لاکرز ہیں: پہلے 2 آپ کے ذاتی کام کے لیے (`v0`, `v1`) اور اگلے 3 باہر سے ملنے والے ان پٹ کے لیے (`p0`, `p1`, `p2`)۔ دراصل `p0` ہی `v2` ہے!",
                        hi = "कल्पना करें कतार में 5 लॉकर हैं: पहले 2 आपके निजी काम के लिए (`v0`, `v1`) और अगले 3 बाहर से आए इनपुट के लिए (`p0`, `p1`, `p2`)। वास्तव में मेमोरी में `p0` ही `v2` है!",
                        ar = "تخيل 5 خزائن متتالية: أول خزانتين لمسوداتك الخاصة (`v0`, `v1`)، والثلاث الباقية للمعاملات القادمة من الخارج (`p0`, `p1`, `p2`). فعلياً `p0` هي الخزانة رقم `v2`!",
                        fa_af = "۵ کمد ردیفی را تصور کنید: ۲ کمد اول چک‌نویس محلی شما (`v0`, `v1`) و ۳ کمد بعدی ورودی‌های متد (`p0`, `p1`, `p2`) هستند؛ در واقع `p0` همان `v2` است!",
                        in_id = "Bayangkan 5 loker berurutan: 2 loker pertama untuk kerja lokal (`v0`, `v1`) dan 3 berikutnya untuk argumen masuk (`p0`, `p1`, `p2`)."
                    ),
                    content = localized(
                        en = "1) In an INSTANCE method (not `static`), `p0` ALWAYS holds `this` (the current object), `p1` is the 1st parameter, `p2` is the 2nd.\n2) In a STATIC method, there is NO `this` object, so `p0` is immediately the 1st parameter!\n3) Each register is 32 bits wide. Therefore, 64-bit types (`J` = long, `D` = double) ALWAYS occupy TWO consecutive registers (e.g., `v0` + `v1`, or `p1` + `p2`).",
                        bn = "১) সাধারণ (Instance) মেথডে `p0`-তে সবসময় বর্তমান ক্লাসের `this` অবজেক্ট থাকে, আর ১ম ইনপুট থাকে `p1`-এ।\n২) কিন্তু `static` মেথডে কোনো `this` থাকে না—তাই `p0`-তেই সরাসরি ১ম ইনপুট বসে!\n৩) প্রতিটি রেজিস্টার ৩২-বিটের। তাই ৬৪-বিট ডেটা (`J` = long এবং `D` = double) সবসময় পাশাপাশি ২টি রেজিস্টার দখল করে (যেমন `v0` ও `v1` একসাথে)!",
                        ur = "1) انسٹانس میتھڈ میں `p0` ہمیشہ `this` آبجیکٹ ہوتا ہے اور پہلا آرگومنٹ `p1` میں آتا ہے۔\n2) `static` میتھڈ میں `this` نہیں ہوتا، اس لیے پہلا آرگومنٹ براہ راست `p0` ہوتا ہے۔\n3) ہر رجسٹر 32 بٹ کا ہے، لہٰذا `long` (`J`) اور `double` (`D`) دو لگاتار رجسٹرز لیتے ہیں۔",
                        hi = "1) इंस्टेंस मेथड में `p0` हमेशा `this` ऑब्जेक्ट होता है और पहला पैरामीटर `p1` होता है।\n2) `static` मेथड में `this` नहीं होता, इसलिए `p0` ही पहला पैरामीटर होता है।\n3) प्रत्येक रजिस्टर 32-बिट का होता है, इसलिए 64-बिट (`J` = long, `D` = double) हमेशा दो लगातार रजिस्टर लेते हैं।",
                        ar = "1) في دوال الكائنات العادية يحمل `p0` دائماً مؤشر `this` ويكون `p1` هو المعامل الأول.\n2) في الدوال الثابتة `static` لا يوجد `this` فيكون `p0` هو المعامل الأول مباشرة.\n3) عرض المسجل 32-بت، لذا تشغل الأنواع ذات 64-بت (`J` و `D`) مسجلين متتاليين دائماً.",
                        fa_af = "۱) در متدهای معمولی (Instance)، رجستر `p0` همیشه اشاره‌گر `this` است و آرگومان اول در `p1` قرار می‌گیرد.\n۲) در متدهای `static` اشاره‌گر `this` وجود ندارد و `p0` مستقیماً آرگومان اول است.\n۳) هر رجستر ۳۲ بیت است، پس انواع ۶۴ بیتی (`J` و `D`) دو رجستر متوالی اشغال می‌کنند.",
                        in_id = "1) Pada metode instance, `p0` selalu berisi `this` dan `p1` adalah argumen pertama.\n2) Pada metode `static`, tidak ada `this` sehingga `p0` langsung argumen pertama.\n3) Tipe 64-bit (`J` & `D`) wajib memakai 2 register berurutan."
                    ),
                    smaliCode = """# Instance method: p0 = this, (p1,p2) = 64-bit long 'timestamp', p3 = int 'bonus'
.method public calcScore(JI)J
    .locals 2    # v0, v1 reserved for 64-bit local result

    int-to-long v0, p3       # convert 32-bit p3 into 64-bit pair (v0, v1)
    add-long v0, p1, v0      # (p1,p2) + (v0,v1) -> stored in (v0,v1)
    return-wide v0           # return 64-bit pair starting at v0
.end method""",
                    practicalExampleNote = localized(
                        en = "Look closely at `calcScore(JI)J`: because `J` takes `p1` AND `p2`, the second parameter `I` lands in `p3`, not `p2`! Forgetting this causes instant VerifyError crashes.",
                        bn = "দারুণ গুরুত্বপূর্ণ পয়েন্ট: যেহেতু ১ম প্যারামিটার `J` (long) একা `p1` ও `p2` দুটি স্লট নিয়ে নিয়েছে, তাই ২য় প্যারামিটার `I` (int) বসেছে `p3`-তে (`p2`-তে নয়)! স্মালি বোঝার সবচেয়ে বড় গোপন সূত্র এটি।",
                        ur = "انتہائی اہم نکتہ: چونکہ پہلا پیرامیٹر `J` اکیلے `p1` اور `p2` دونوں سلاٹ لے لیتا ہے، اس لیے اگلا `I` پیرامیٹر `p3` میں آتا ہے!",
                        hi = "बेहद महत्वपूर्ण बिंदु: चूंकि पहला पैरामीटर `J` अकेले `p1` और `p2` दोनों स्लॉट ले लेता है, इसलिए दूसरा पैरामीटर `I` सीधे `p3` में जाता है!",
                        ar = "نقطة ذهبية: بما أن المعامل الأول `J` يشغل `p1` و `p2` معاً، فإن المعامل الثاني `I` يوضع في `p3` وليس `p2`!",
                        fa_af = "نکته طلایی: از آنجا که پارامتر اول `J` دو خانه `p1` و `p2` را با هم اشغال می‌کند، پارامتر دوم `I` در `p3` قرار می‌گیرد نه `p2`!",
                        in_id = "Penting: karena parameter pertama `J` memakan `p1` dan `p2`, maka parameter kedua `I` berada di `p3`, bukan `p2`!"
                    )
                )
            )
        ),

        // MODULE 4
        Lesson(
            id = "smali_04",
            track = TrackType.SMALI,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "04. All Smali Type Descriptors (V, Z, I, J, L...; & Arrays)",
                bn = "০৪. স্মালির সকল ডেটা টাইপ চিহ্ন (V, Z, I, J, L...; ও অ্যারে)",
                ur = "04. سمالی کے تمام ٹائپ ڈسکرپٹرز (V, Z, I, J, L...; اور ایرے)",
                hi = "04. स्माली के सभी डेटा टाइप चिन्ह (V, Z, I, J, L...; और ऐरे)",
                ar = "04. جميع رموز أنواع البيانات والمصفوفات في سمالي",
                fa_af = "۰۴. تمامی شناسه‌های نوع داده در سمالی (اولیه، شیء و آرایه)",
                in_id = "04. Semua Deskriptor Tipe Smali (Primitif, Objek & Array)"
            ),
            subtitle = localized(
                en = "Memorize the 9 single-letter primitives, object references, and multi-dimensional array prefixes.",
                bn = "মাত্র ২ মিনিটে চিনে নিন ৯টি এক-অক্ষরের প্রিমিটিভ টাইপ, অবজেক্ট রেফারেন্স এবং অ্যারে লেখার নিয়ম।",
                ur = "9 یک حرفی بنیادی ٹائپس، آبجیکٹ ریفرنسز اور ایرے لکھنے کا مکمل طریقہ۔",
                hi = "9 एक-अक्षर प्रिमिटिव टाइप, ऑब्जेक्ट रेफरेंस और ऐरे लिखने के नियम आसानी से याद करें।",
                ar = "احفظ الرموز التسعة للأنواع الأولية ومراجع الكائنات والمصفوفات بسهولة.",
                fa_af = "یادگیری آسان ۹ حرف انواع اولیه، ارجاع به اشیاء و پیشوند آرایه‌ها در سمالی.",
                in_id = "Kuasai 9 huruf tipe primitif, referensi objek, dan awalan array dengan mudah."
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Complete Descriptor Table & Memory Tricks",
                        bn = "একনজরে সব টাইপ চিহ্ন মনে রাখার সহজ কৌশল",
                        ur = "تمام ٹائپ علامات یاد رکھنے کا آسان طریقہ",
                        hi = "सभी टाइप चिन्ह याद रखने की आसान ट्रिक",
                        ar = "جدول الرموز الكامل مع حيل التذكر السريع",
                        fa_af = "جدول کامل نمادهای نوع داده و ترفند به‌خاطر سپردن",
                        in_id = "Tabel Lengkap Deskriptor & Trik Menghafal"
                    ),
                    content = localized(
                        en = "• `V` = void (returns nothing)\n• `Z` = boolean (true/false — B was taken by byte!)\n• `B` = byte, `S` = short, `C` = char, `I` = int\n• `J` = long (64-bit — L was taken by Object!)\n• `F` = float, `D` = double (64-bit)\n• `Lpackage/Name;` = Any Java/Kotlin Class Object\n• `[` prefix = 1D Array (`[I` = `int[]`, `[[B` = `byte[][]`)",
                        bn = "• `V` = void (কিছু রিটার্ন করে না)\n• `Z` = boolean (`true`/`false` — কারণ `B` দিয়ে byte বোঝায়!)\n• `B` = byte, `S` = short, `C` = char, `I` = int (পূর্ণসংখ্যা)\n• `J` = long (৬৪-বিট বড় সংখ্যা — কারণ `L` দিয়ে ক্লাস অবজেক্ট বোঝায়!)\n• `F` = float, `D` = double (৬৪-বিট দশমিক)\n• `Lpackage/ClassName;` = যেকোনো অবজেক্ট (যেমন `Ljava/lang/String;`)\n• `[` চিহ্ন = অ্যারে (`[I` মানে `int[]`, `[Ljava/lang/String;` মানে `String[]`)",
                        ur = "• `V` = void (خالی واپسی)\n• `Z` = boolean (سچ/جھوٹ)\n• `B` = byte، `S` = short، `C` = char، `I` = int\n• `J` = long (64-بٹ عدد)\n• `F` = float، `D` = double (64-بٹ اعشاریہ)\n• `Lpkg/Cls;` = آبجیکٹ ریفرنس\n• `[` = ایرے (`[I` یعنی `int[]`)",
                        hi = "• `V` = void (कुछ नहीं लौटाता)\n• `Z` = boolean (क्योंकि `B` byte के लिए है!)\n• `B` = byte, `S` = short, `C` = char, `I` = int\n• `J` = long (64-बिट, क्योंकि `L` क्लास के लिए है!)\n• `F` = float, `D` = double (64-बिट)\n• `Lpkg/Cls;` = क्लास ऑब्जेक्ट\n• `[` = ऐरे (`[I` = `int[]`)",
                        ar = "• `V` = بلا إرجاع void\n• `Z` = منطقي boolean\n• `B` = بايت، `S` = قصير، `C` = حرف، `I` = عدد صحيح\n• `J` = عدد طويل 64-بت long\n• `F` = عشري float، `D` = عشري مضاعف double\n• `Lpkg/Cls;` = كائن فئة\n• `[` = مصفوفة (`[I` تعني `int[]`)",
                        fa_af = "• `V` = بدون خروجی (void)\n• `Z` = بولین (چون B برای byte است)\n• `B` = بایت، `S` = کوتاه، `C` = کاراکتر، `I` = عدد صحیح\n• `J` = عدد ۶۴ بیتی long (چون L برای کلاس است)\n• `F` = اعشاری، `D` = اعشاری ۶۴ بیتی\n• `Lpkg/Cls;` = شیء کلاس\n• `[` = آرایه (`[I` یعنی `int[]`)",
                        in_id = "• `V` = void\n• `Z` = boolean (`B` dipakai byte)\n• `B` = byte, `S` = short, `C` = char, `I` = int\n• `J` = long 64-bit (`L` dipakai kelas)\n• `F` = float, `D` = double\n• `Lpkg/Cls;` = Objek kelas\n• `[` = Array (`[I` berarti `int[]`)"
                    ),
                    smaliCode = """# Notice how packed primitives have NO spaces between them!
# Java: boolean verify(int id, boolean active, String token, byte[] hash)
.method public verify(IZLjava/lang/String;[B)Z""",
                    practicalExampleNote = localized(
                        en = "In `(IZLjava/lang/String;[B)Z`, Dalvik parses left-to-right: `I` (int), `Z` (boolean), `Ljava/lang/String;` (reads until `;`), `[B` (byte array), returns `Z` (boolean)!",
                        bn = "`(IZLjava/lang/String;[B)Z` পড়ার নিয়ম খুব সহজ: বাম থেকে ডানে পড়ুন—প্রথমে `I` (int), এরপর `Z` (boolean), এরপর `Ljava/lang/String;` (`;` পর্যন্ত একটি অবজেক্ট), এরপর `[B` (`byte[]`), আর ব্র্যাকেটের বাইরে `Z` মানে রিটার্ন করবে boolean!",
                        ur = "`(IZLjava/lang/String;[B)Z` کو بائیں سے دائیں پڑھیں: `I` (int)، `Z` (boolean)، `Ljava/lang/String;`، اور `[B` (بائٹ ایرے)، جبکہ باہر والا `Z` واپسی کی قسم ہے!",
                        hi = "`(IZLjava/lang/String;[B)Z` को बाएँ से दाएँ पढ़ें: `I` (int), `Z` (boolean), `Ljava/lang/String;`, और `[B` (`byte[]`), तथा कोष्ठक के बाहर `Z` रिटर्न टाइप है!",
                        ar = "لقراءة `(IZLjava/lang/String;[B)Z` اقرأ من اليسار لليمين: عدد صحيح `I`، منطقي `Z`، نص ينتهي بـ `;`، مصفوفة بايت `[B`، ونوع الإرجاع بعد القوس هو `Z`!",
                        fa_af = "برای خواندن `(IZLjava/lang/String;[B)Z` از چپ به راست حرکت کنید: `I` عدد صحیح، `Z` بولین، `Ljava/lang/String;` رشته، `[B` آرایه بایت و `Z` پایانی نوع خروجی است!",
                        in_id = "Baca `(IZLjava/lang/String;[B)Z` dari kiri ke kanan: `I` (int), `Z` (boolean), `Ljava/lang/String;`, `[B` (byte[]), dan return `Z`!"
                    )
                )
            )
        ),

        // MODULE 5
        Lesson(
            id = "smali_05",
            track = TrackType.SMALI,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "05. Method & Field Signatures (<init>, <clinit> & ->)",
                bn = "০৫. মেথড ও ফিল্ড সিগনেচার (<init>, <clinit> এবং -> চিহ্ন)",
                ur = "05. میتھڈ اور فیلڈ سگنیچرز (<init>، <clinit> اور ->)",
                hi = "05. मेथड और फ़ील्ड सिग्नेचर (<init>, <clinit> और ->)",
                ar = "05. تواقيع الدوال والحقول وبانيات الفئات (<init> و <clinit>)",
                fa_af = "۰۵. امضای متدها و فیلدها (<init>، <clinit> و علامت ->)",
                in_id = "05. Tanda Tangan Metode & Field (<init>, <clinit> & ->)"
            ),
            subtitle = localized(
                en = "Decode full class->member arrows, constructors, static initializers, and field colons.",
                bn = "ক্লাস->মেথড অ্যারো (`->`), ফিল্ড কোলন (`:`), কনস্ট্রাক্টর `<init>` এবং `<clinit>` নিখুঁতভাবে চিনুন।",
                ur = "کلاس->میتھڈ تیر (`->`)، فیلڈ کولن (`:`)، کنسٹرکٹر `<init>` اور `<clinit>` کو پہچانیں۔",
                hi = "क्लास->मेथड तीर (`->`), फ़ील्ड कोलन (`:`), कंस्ट्रक्टर `<init>` और `<clinit>` को पहचानें।",
                ar = "فكك صيغة السهم `->` والنقطتين `:` ودوال البناء `<init>` والتهيئة الثابتة `<clinit>`.",
                fa_af = "شناخت کامل علامت پیکان `->`، دونقطه فیلد `:`، سازنده `<init>` و مقداردهی استاتیک `<clinit>`.",
                in_id = "Uraikan panah kelas->anggota (`->`), titik dua field (`:`), `<init>`, dan `<clinit>`."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Field Colon (:) vs Method Parentheses ()",
                        bn = "ফিল্ডের কোলন (:) বনাম মেথডের ব্র্যাকেট () চেনার উপায়",
                        ur = "فیلڈ کے کولن (:) اور میتھڈ کے بریکٹ () میں فرق",
                        hi = "फ़ील्ड के कोलन (:) और मेथड के कोष्ठक () में अंतर",
                        ar = "الفرق الفوري بين نقطتي الحقل (:) وأقواس الدالة ()",
                        fa_af = "تفاوت فوری دونقطه فیلد (:) با پرانتز متد ()",
                        in_id = "Perbedaan Titik Dua Field (:) vs Kurung Metode ()"
                    ),
                    content = localized(
                        en = "Every external reference in Smali includes the full owner class before `->`:\n• **Field Reference**: `LOwnerClass;->fieldName:Type` (uses `:` before type).\n• **Method Reference**: `LOwnerClass;->methodName(ParamTypes)ReturnType` (uses `(...)`).\n• **`<init>`**: Instance constructor called right after `new-instance`.\n• **`<clinit>`**: Static class initializer block (`static { ... }`), run automatically once when the class loads.",
                        bn = "স্মালিতে যেকোনো মেথড বা ভেরিয়েবল কল করার সময় `->` চিহ্নের বাম পাশে মালিক ক্লাসের পুরো নাম লেখা থাকে:\n• **ফিল্ড চেনার উপায়**: নামের পরে কোলন `:` থাকে — `LClass;->age:I`\n• **মেথড চেনার উপায়**: নামের পরে ব্র্যাকেট `()` থাকে — `LClass;->getAge()I`\n• **`<init>`**: অবজেক্ট তৈরির কনস্ট্রাক্টর মেথড।\n• **`<clinit>`**: ক্লাসের `static { ... }` ব্লক যা ক্লাস লোড হওয়ার সময় স্বয়ংক্রিয়ভাবে একবার চলে।",
                        ur = "سمالی میں ہر ریفرنس میں `->` سے پہلے مکمل کلاس لکھی ہوتی ہے:\n• **فیلڈ**: نام کے بعد کولن `:` آتا ہے (`LClass;->age:I`)۔\n• **میتھڈ**: نام کے بعد بریکٹ `()` آتے ہیں (`LClass;->getAge()I`)۔\n• **`<init>`**: آبجیکٹ کنسٹرکٹر۔\n• **`<clinit>`**: اسٹیٹک انیشلائزر بلاک۔",
                        hi = "स्माली में हर रेफरेंस में `->` से पहले मालिक क्लास का पूरा नाम होता है:\n• **फ़ील्ड**: नाम के बाद कोलन `:` आता है (`LClass;->age:I`)।\n• **मेथड**: नाम के बाद कोष्ठक `()` आते हैं (`LClass;->getAge()I`)।\n• **`<init>`**: ऑब्जेक्ट कंस्ट्रक्टर।\n• **`<clinit>`**: स्टैटिक इनिशियलाइज़र ब्लॉक।",
                        ar = "كل مرجع في سمالي يحدد الفئة المالكة قبل السهم `->`:\n• **الحقل**: يتبع الاسم نقطتان رأسيتان `:` ثم النوع.\n• **الدالة**: يتبع الاسم قوسان `()` ثم نوع الإرجاع.\n• **`<init>`**: دالة بناء الكائن.\n• **`<clinit>`**: كتلة التهيئة الثابتة للفئة.",
                        fa_af = "در سمالی همیشه قبل از `->` نام کامل کلاس مالک نوشته می‌شود:\n• **فیلد**: بعد از نام علامت `:` می‌آید (`LClass;->age:I`).\n• **متد**: بعد از نام پرانتز `()` می‌آید (`LClass;->getAge()I`).\n• **`<init>`**: سازنده شیء.\n• **`<clinit>`**: بلوک مقداردهی اولیه استاتیک کلاس.",
                        in_id = "Setiap referensi menyertakan kelas pemilik sebelum `->`:\n• **Field**: memakai titik dua `:` (`LClass;->age:I`).\n• **Metode**: memakai tanda kurung `()` (`LClass;->getAge()I`).\n• **`<init>`**: konstruktor objek.\n• **`<clinit>`**: blok inisialisasi statis kelas."
                    ),
                    smaliCode = """# Static initializer (<clinit>) setting a static field
.method static constructor <clinit>()V
    .locals 1
    const-string v0, "v2.0-stable"
    sput-object v0, Lcom/example/AppConfig;->VERSION:Ljava/lang/String;
    return-void
.end method"""
                )
            )
        ),

        // MODULE 6
        Lesson(
            id = "smali_06",
            track = TrackType.SMALI,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "06. Loading Constants (const/4 to const-wide & Strings)",
                bn = "০৬. কনস্ট্যান্ট মান লোড করা (const/4 থেকে const-wide ও স্ট্রিং)",
                ur = "06. کانسٹنٹ ویلیوز لوڈ کرنا (const/4 سے const-wide اور اسٹرنگز)",
                hi = "06. कॉन्स्टेंट मान लोड करना (const/4 से const-wide और स्ट्रिंग)",
                ar = "06. تحميل القيم الثابتة والنصوص (من const/4 إلى const-wide)",
                fa_af = "۰۶. بارگذاری مقادیر ثابت و رشته‌ها (از const/4 تا const-wide)",
                in_id = "06. Memuat Konstanta (const/4 hingga const-wide & String)"
            ),
            subtitle = localized(
                en = "Why Smali has const/4, const/16, const, const/high16, const-wide, const-string & const-class.",
                bn = "কেন স্মালিতে const/4, const/16, const, const/high16, const-wide ও const-string আলাদা থাকে এবং কীভাবে ব্যবহার করবেন।",
                ur = "جانیں کہ سمالی میں مختلف سائز کے const اوپ کوڈز کیوں ہوتے ہیں اور کب کون سا استعمال کرنا ہے۔",
                hi = "जानें कि स्माली में अलग-अलग आकार के const ऑपकोड क्यों होते हैं और किसे कब चुनना चाहिए।",
                ar = "لماذا توجد عدة صيغ لتعليمة const ومتى تختار كلاً منها للأعداد والنصوص والفئات.",
                fa_af = "علت وجود انواع دستورات const در سمالی و نحوه انتخاب دستور مناسب برای هر مقدار.",
                in_id = "Mengapa Smali memiliki banyak varian const dan kapan menggunakan masing-masing."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Picking the Exact Constant Instruction",
                        bn = "সংখ্যার আকার অনুযায়ী সঠিক const অপকোড নির্বাচন",
                        ur = "عدد کے سائز کے مطابق درست const اوپ کوڈ کا انتخاب",
                        hi = "संख्या के आकार के अनुसार सही const ऑपकोड चुनना",
                        ar = "اختيار تعليمة الثابت المناسبة حسب حجم القيمة",
                        fa_af = "انتخاب دقیق دستور const بر اساس اندازه عدد",
                        in_id = "Memilih Instruksi Konstanta Sesuai Ukuran Nilai"
                    ),
                    content = localized(
                        en = "To keep `.dex` files tiny, Dalvik uses compact opcodes for small numbers:\n• `const/4 v0, 0x1` → 4-bit signed (`-8` to `7`) AND register must be `v0..v15`. Also used for `true` (`0x1`), `false` (`0x0`), and `null` (`0x0`)!\n• `const/16 v0, 0x3e8` → 16-bit signed (`-32768` to `32767`), e.g. `1000`.\n• `const v0, 0x186a0` → Any full 32-bit int or float.\n• `const/high16 v0, 0x3f800000` → Loads top 16 bits (`1.0f` IEEE-754 float!).\n• `const-wide v0, 0x1dcd6500L` → 64-bit `long` (must end with `L`!) into pair `(v0, v1)`.",
                        bn = "APK সাইজ ছোট রাখতে ডালভিক ছোট সংখ্যার জন্য ছোট অপকোড ব্যবহার করে:\n• `const/4 v0, 0x1` → `-8` থেকে `+7` পর্যন্ত সংখ্যার জন্য (রেজিস্টার অবশ্যই `v0..v15` হতে হবে)। স্মালিতে `true` = `0x1`, `false` = `0x0`, এবং `null` = `0x0` এভাবেই লোড হয়!\n• `const/16 v0, 0x3e8` → `-32768` থেকে `32767` পর্যন্ত সংখ্যা (যেমন `0x3e8` = ১০০০)।\n• `const v0, 0x186a0` → যেকোনো বড় ৩২-বিট পূর্ণসংখ্যা।\n• `const/high16 v0, 0x3f800000` → দশমিক ফ্লোট লোড করতে ব্যবহৃত হয় (`0x3f800000` = `1.0f`)।\n• `const-wide v0, 0x1dcd6500L` → ৬৪-বিট `long` লোড করে `(v0, v1)` জোড়ায় (শেষে `L` লেখা বাধ্যতামূলক!)।",
                        ur = "• `const/4` → `-8` سے `7` تک (اور `true=0x1`، `false/null=0x0`)۔\n• `const/16` → `-32768` سے `32767` تک۔\n• `const` → مکمل 32-بٹ عدد۔\n• `const/high16` → فلوٹ نمبروں جیسے `1.0f` (`0x3f800000`) کے لیے۔\n• `const-wide` → 64-بٹ `long` جو `(v0, v1)` جوڑے میں لوڈ ہوتا ہے (آخر میں `L` لازمی ہے)۔",
                        hi = "• `const/4` → `-8` से `7` तक (`true=0x1`, `false/null=0x0` भी इसी से लोड होते हैं)।\n• `const/16` → `-32768` से `32767` तक।\n• `const` → पूर्ण 32-बिट संख्या।\n• `const/high16` → फ़्लोट मान जैसे `1.0f` (`0x3f800000`)।\n• `const-wide` → 64-बिट `long` जो `(v0, v1)` जोड़े में लोड होता है (अंत में `L` अनिवार्य)।",
                        ar = "• `const/4`: للأعداد من `-8` إلى `7` (وكذلك `true=0x1` و `false/null=0x0`).\n• `const/16`: للأعداد الموقعة 16-بت.\n• `const`: لأي قيمة 32-بت.\n• `const/high16`: للأعداد العشرية مثل `1.0f`.\n• `const-wide`: للقيم الطويلة 64-بت مع لاحقة `L` في زوج مسجلات.",
                        fa_af = "• `const/4`: اعداد `-8` تا `7` (و مقادیر `true=0x1` و `false/null=0x0`).\n• `const/16`: اعداد ۱۶ بیتی.\n• `const`: هر عدد کامل ۳۲ بیتی.\n• `const/high16`: اعداد اعشاری مانند `1.0f`.\n• `const-wide`: اعداد ۶۴ بیتی با پسوند `L` در جفت رجستر `(v0, v1)`.",
                        in_id = "• `const/4`: angka `-8` s/d `7` (juga `true=0x1`, `false/null=0x0`).\n• `const/16`: bilangan 16-bit.\n• `const`: bilangan penuh 32-bit.\n• `const/high16`: float seperti `1.0f` (`0x3f800000`).\n• `const-wide`: 64-bit dengan akhiran `L` ke pasangan `(v0, v1)`."
                    ),
                    smaliCode = """const/4 v0, 0x1                 # boolean true (or int 1)
const/16 v1, 0x3e8              # int 1000
const/high16 v2, 0x3f800000     # float 1.0f
const-wide/16 v4, 0x3cL         # long 60L -> fills v4 AND v5
const-string v3, "Smali Pro"    # String reference from DEX pool
const-class v6, Landroid/os/Build; # Class<?> literal"""
                )
            )
        ),

        // MODULE 7
        Lesson(
            id = "smali_07",
            track = TrackType.SMALI,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "07. Register Moves & The 'move-result' Golden Rule",
                bn = "০৭. রেজিস্টার মুভ ও 'move-result' এর সোনালী নিয়ম",
                ur = "07. رجسٹر موو اور 'move-result' کا سنہری اصول",
                hi = "07. रजिस्टर मूव और 'move-result' का सुनहरा नियम",
                ar = "07. نقل المسجلات والقاعدة الذهبية لتعليمة move-result",
                fa_af = "۰۷. انتقال داده بین رجسترها و قانون طلایی move-result",
                in_id = "07. Pemindahan Register & Aturan Emas 'move-result'"
            ),
            subtitle = localized(
                en = "Copy primitives, objects, and 64-bit pairs safely—and capture method return values without VerifyError.",
                bn = "এক রেজিস্টার থেকে অন্য রেজিস্টারে মান কপি করা এবং মেথডের ফলাফল ধরার নির্ভুল নিয়ম শিখুন।",
                ur = "ایک رجسٹر سے دوسرے میں ویلیو کاپی کرنا اور میتھڈ کا نتیجہ محفوظ کرنے کا درست طریقہ۔",
                hi = "एक रजिस्टर से दूसरे में मान कॉपी करना और मेथड रिटर्न वैल्यू पकड़ने का सटीक नियम।",
                ar = "انسخ القيم والكائنات بأمان والتقط مخرجات الدوال دون الوقوع في أخطاء الفاحص.",
                fa_af = "کپی ایمن مقادیر بین رجسترها و دریافت خروجی متدها بدون خطای VerifyError.",
                in_id = "Salin primitif, objek, pasangan 64-bit & tangkap hasil metode tanpa VerifyError."
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Why move-result Must Come Immediately After invoke-*",
                        bn = "কেন invoke-* এর ঠিক পরের লাইনেই move-result লিখতে হয়?",
                        ur = "invoke-* کے فوراً بعد move-result لکھنا کیوں لازمی ہے؟",
                        hi = "invoke-* के ठीक अगली लाइन में move-result लिखना क्यों अनिवार्य है?",
                        ar = "لماذا يجب وضع move-result مباشرة بعد سطر الاستدعاء invoke-*؟",
                        fa_af = "چرا move-result باید بلافاصله بعد از خط invoke-* قرار بگیرد؟",
                        in_id = "Mengapa move-result Wajib Tepat Setelah invoke-*?"
                    ),
                    content = localized(
                        en = "When you call a non-void method (`invoke-virtual`, `invoke-static`, etc.), Dalvik places the returned value into an invisible one-slot CPU latch. You MUST capture it on the VERY NEXT executable line using:\n• `move-result vX` → for 32-bit primitives (`I, Z, B, S, C, F`)\n• `move-result-wide vX` → for 64-bit `J` / `D`\n• `move-result-object vX` → for any Object `L...;` or Array `[...`\nPutting even a single `const` instruction in between invalidates the result latch and triggers `VerifyError`!",
                        bn = "যখন কোনো মেথড কিছু রিটার্ন করে, ডালভিক সেই ফলাফলটি একটি অস্থায়ী গোপন বাফারে রাখে। তাই `invoke-*` লাইনের ঠিক পরের লাইনেই সেটিকে ধরে ফেলতে হয়:\n• `move-result v0` → ৩২-বিট সাধারণ মানের জন্য (`I, Z, F` ইত্যাদি)\n• `move-result-wide v0` → ৬৪-বিট `long` বা `double` এর জন্য\n• `move-result-object v0` → যেকোনো অবজেক্ট (`String` ইত্যাদি) বা অ্যারের জন্য।\nমাঝখানে অন্য একটিও `const` লাইন ঢোকালে অ্যাপ সাথে সাথে `VerifyError` দিয়ে ক্র্যাশ করবে!",
                        ur = "جب کوئی میتھڈ ویلیو واپس کرتا ہے تو اسے `invoke-*` کے فوراً بعد اگلی لائن میں پکڑنا لازمی ہے:\n• `move-result` (32-بٹ کے لیے)\n• `move-result-wide` (64-بٹ کے لیے)\n• `move-result-object` (آبجیکٹ یا ایرے کے لیے)۔\nدرمیان میں کوئی اور لائن لکھنے سے `VerifyError` آ جاتا ہے!",
                        hi = "जब कोई मेथड मान लौटाता है, तो उसे `invoke-*` की ठीक अगली लाइन में पकड़ना अनिवार्य है:\n• `move-result` (32-बिट मान के लिए)\n• `move-result-wide` (64-बिट मान के लिए)\n• `move-result-object` (ऑब्जेक्ट या ऐरे के लिए)।\nबीच में एक भी अन्य निर्देश लिखने पर तुरंत `VerifyError` आता है!",
                        ar = "عند استدعاء دالة ترجع قيمة، يجب التقاط النتيجة في السطر التنفيذي التالي مباشرة:\n• `move-result` للأنواع الأولية 32-بت\n• `move-result-wide` للأنواع 64-بت\n• `move-result-object` للكائنات والمصفوفات.",
                        fa_af = "پس از فراخوانی متدی که خروجی دارد، باید بلافاصله در خط بعدی آن را دریافت کنید:\n• `move-result` برای مقادیر ۳۲ بیتی\n• `move-result-wide` برای مقادیر ۶۴ بیتی\n• `move-result-object` برای اشیاء و آرایه‌ها.",
                        in_id = "Saat memanggil metode non-void, tangkap hasilnya tepat di baris berikutnya dengan `move-result`, `move-result-wide`, atau `move-result-object`."
                    ),
                    smaliCode = """# Calling String.trim() -> returns Ljava/lang/String; (an object!)
invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;
move-result-object v0

# Calling String.length() -> returns I (32-bit primitive int!)
invoke-virtual {v0}, Ljava/lang/String;->length()I
move-result v1"""
                )
            )
        ),

        // MODULE 8
        Lesson(
            id = "smali_08",
            track = TrackType.SMALI,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "08. Math, Bitwise Ops, /2addr, /lit8 & Type Casts",
                bn = "০৮. গাণিতিক হিসাব, বিটওয়াইজ অপারেশন, /2addr, /lit8 ও টাইপ রূপান্তর",
                ur = "08. حساب کتاب، بٹ وائز، /2addr، /lit8 اور ٹائپ تبدیلی",
                hi = "08. गणित, बिटवाइज़ लॉजिक, /2addr, /lit8 और टाइप रूपांतरण",
                ar = "08. العمليات الحسابية والمنطقية وصيغ /2addr و /lit8 وتحويل الأنواع",
                fa_af = "۰۸. محاسبات ریاضی، بیت‌وایز، /2addr، /lit8 و تبدیل نوع داده",
                in_id = "08. Matematika, Bitwise, /2addr, /lit8 & Konversi Tipe"
            ),
            subtitle = localized(
                en = "Understand 3-register math, in-place /2addr shortcuts, literal /lit8 math, and primitive conversions.",
                bn = "যোগ-বিয়োগ-গুণ-ভাগ, শর্টকাট `/2addr` ও `/lit8` এবং এক ডেটা টাইপ থেকে অন্য টাইপে রূপান্তরের নিয়ম।",
                ur = "جمع تفریق ضرب تقسیم، `/2addr` اور `/lit8` شارٹ کٹس اور ڈیٹا ٹائپ تبدیل کرنے کا طریقہ۔",
                hi = "जोड़-घटाव-गुणा-भाग, `/2addr` और `/lit8` शॉर्टकट तथा डेटा टाइप बदलने के नियम।",
                ar = "افهم الحساب ثلاثي المسجلات، اختصارات `/2addr` و `/lit8`، وتعليمات تحويل الأنواع الأولية.",
                fa_af = "درک دستورات حسابی سه‌رجستری، میانبرهای `/2addr` و `/lit8` و تبدیل انواع اولیه.",
                in_id = "Pahami matematika 3-register, pintasan `/2addr` & `/lit8`, serta konversi tipe primitif."
            ),
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Standard, /2addr, and /lit8 Math Forms",
                        bn = "সাধারণ, /2addr এবং /lit8 গাণিতিক ফর্মের পার্থক্য",
                        ur = "عام، /2addr اور /lit8 حسابی طریقوں میں فرق",
                        hi = "सामान्य, /2addr और /lit8 गणितीय रूपों में अंतर",
                        ar = "الفرق بين الصيغة القياسية و /2addr و /lit8",
                        fa_af = "تفاوت فرم استاندارد، /2addr و /lit8 در دستورات ریاضی",
                        in_id = "Perbedaan Bentuk Standar, /2addr, dan /lit8"
                    ),
                    content = localized(
                        en = "Smali always writes the DESTINATION register first:\n1) **3-Register Form**: `add-int vDest, vA, vB` (`vDest = vA + vB`).\n2) **In-Place `/2addr` Form**: `add-int/2addr v0, v1` (`v0 = v0 + v1`) — saves extra registers!\n3) **Immediate `/lit8` or `/lit16` Form**: `add-int/lit8 v0, v1, 0x1` (`v0 = v1 + 1`) — adds a constant directly without loading another register!\n4) **Type Conversions**: Smali requires explicit casts like `int-to-long`, `int-to-float`, `double-to-int`, and `int-to-byte`.",
                        bn = "স্মালির গাণিতিক লাইনে সবসময় **ফলাফল রাখার রেজিস্টারটি প্রথমে** বসে:\n১) **৩-রেজিস্টার রূপ**: `add-int v0, v1, v2` (মানে `v0 = v1 + v2`)।\n২) **শর্টকাট `/2addr` রূপ**: `add-int/2addr v0, v1` (মানে `v0 = v0 + v1`)।\n৩) **সরাসরি সংখ্যা `/lit8` রূপ**: `add-int/lit8 v0, v0, 0x1` (মানে `v0 = v0 + 1`, লুপের `i++` করতে এটি সবচেয়ে বেশি ব্যবহৃত হয়!)।\n৪) **টাইপ রূপান্তর**: `int` থেকে `long` বা `float` করতে `int-to-long`, `int-to-float`, `float-to-int` ব্যবহৃত হয়।",
                        ur = "سمالی میں ہمیشہ منزل والا رجسٹر سب سے پہلے آتا ہے:\n1) `add-int v0, v1, v2` (`v0 = v1 + v2`)\n2) `add-int/2addr v0, v1` (`v0 = v0 + v1`)\n3) `add-int/lit8 v0, v0, 0x1` (`v0++` کے لیے بہترین!)\n4) ٹائپ تبدیلی کے لیے `int-to-long`، `int-to-float` وغیرہ استعمال ہوتے ہیں۔",
                        hi = "स्माली में हमेशा परिणाम वाला रजिस्टर सबसे पहले लिखा जाता है:\n1) `add-int v0, v1, v2` (`v0 = v1 + v2`)\n2) `add-int/2addr v0, v1` (`v0 = v0 + v1`)\n3) `add-int/lit8 v0, v0, 0x1` (`i++` के लिए सबसे अधिक प्रयुक्त!)\n4) टाइप बदलने के लिए `int-to-long`, `int-to-float` आदि का उपयोग होता है।",
                        ar = "يُكتب مسجل الوجهة دائماً أولاً:\n1) `add-int v0, v1, v2` تعني `v0 = v1 + v2`.\n2) `add-int/2addr v0, v1` تعني `v0 += v1`.\n3) `add-int/lit8 v0, v0, 0x1` تضيف ثابتاً مباشرة (`v0++`).\n4) التحويل الصريح يتم عبر `int-to-long` و `int-to-float`.",
                        fa_af = "در سمالی همیشه رجستر مقصد اول نوشته می‌شود:\n۱) `add-int v0, v1, v2` یعنی `v0 = v1 + v2`.\n۲) `add-int/2addr v0, v1` یعنی `v0 += v1`.\n۳) `add-int/lit8 v0, v0, 0x1` عدد ثابت را مستقیم جمع می‌کند (`i++`).\n۴) تبدیل نوع با `int-to-long` و `int-to-float` انجام می‌شود.",
                        in_id = "Register tujuan selalu ditulis pertama:\n1) `add-int v0, v1, v2` (`v0 = v1 + v2`).\n2) `add-int/2addr v0, v1` (`v0 += v1`).\n3) `add-int/lit8 v0, v0, 0x1` (`v0++`).\n4) Konversi eksplisit memakai `int-to-long`, `int-to-float`, dll."
                    ),
                    smaliCode = """add-int v0, p0, p1          # v0 = p0 + p1
mul-int/lit8 v0, v0, 0xa    # v0 = v0 * 10
xor-int/lit8 v1, p2, 0x1    # flips boolean p2 (!flag)
int-to-float v2, v0         # converts int v0 -> float v2"""
                )
            )
        )
    )
}
