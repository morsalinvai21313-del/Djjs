package com.example.localization

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.LayoutDirection

enum class AppLanguage(
    val code: String,
    val nativeName: String,
    val country: String,
    val isRtl: Boolean,
    val flag: String
) {
    ENGLISH("en", "English", "Global", false, "🇺🇸"),
    BENGALI("bn", "বাংলা", "বাংলাদেশ", false, "🇧🇩"),
    URDU("ur", "اردو", "پاکستان", true, "🇵🇰"),
    HINDI("hi", "हिन्दी", "भारत", false, "🇮🇳"),
    ARABIC("ar", "العربية", "الشرق الأوسط", true, "🇸🇦"),
    DARI("fa_af", "دری", "افغانستان", true, "🇦🇫"),
    INDONESIAN("in", "Bahasa Indonesia", "Indonesia", false, "🇮🇩");

    val layoutDirection: LayoutDirection
        get() = if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
}

val LocalAppLanguage = compositionLocalOf { AppLanguage.ENGLISH }

private fun tr(
    en: String,
    bn: String,
    ur: String,
    hi: String,
    ar: String,
    fa: String,
    id: String
): Map<AppLanguage, String> = mapOf(
    AppLanguage.ENGLISH to en,
    AppLanguage.BENGALI to bn,
    AppLanguage.URDU to ur,
    AppLanguage.HINDI to hi,
    AppLanguage.ARABIC to ar,
    AppLanguage.DARI to fa,
    AppLanguage.INDONESIAN to id
)

object LocalizationManager {
    private const val PREF_NAME = "smali_learn_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    private const val KEY_FIRST_LAUNCH = "first_launch_done"
    private const val KEY_DARK_THEME = "is_dark_theme"

    fun getSavedLanguage(context: Context): AppLanguage {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val code = prefs.getString(KEY_LANGUAGE, AppLanguage.ENGLISH.code) ?: AppLanguage.ENGLISH.code
        return AppLanguage.entries.find { it.code == code } ?: AppLanguage.ENGLISH
    }

    fun saveLanguage(context: Context, language: AppLanguage) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, language.code).apply()
    }

    fun getSavedDarkTheme(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_DARK_THEME, true)
    }

    fun saveDarkTheme(context: Context, isDark: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_DARK_THEME, isDark).apply()
    }

    fun isFirstLaunch(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return !prefs.getBoolean(KEY_FIRST_LAUNCH, false)
    }

    fun setFirstLaunchCompleted(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, true).apply()
    }

    fun localizeDigits(input: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.BENGALI -> input.map { ch ->
                if (ch in '0'..'9') ('০' + (ch - '0')) else ch
            }.joinToString("")
            else -> input
        }
    }

    val translations: Map<String, Map<AppLanguage, String>> = mapOf(
        // Branding & Top Bar
        "app_title" to tr(
            "Smali Learn Pro",
            "স্মালি লার্ন প্রো",
            "سمالی لرن پرو",
            "स्माली लर्न प्रो",
            "سمالي ليرن برو",
            "سمالی لرن پرو",
            "Smali Learn Pro"
        ),
        "app_subtitle_badge" to tr(
            "ACADEMY • ZERO TO PRO",
            "একাডেমি • জিরো থেকে অ্যাডভান্স",
            "اکیڈمی • صفر سے ایڈوانس",
            "अकादमी • शून्य से एडवांस",
            "الأكاديمية • من الصفر للاحتراف",
            "اکادمی • از صفر تا پیشرفته",
            "AKADEMI • NOL KE MAHIR"
        ),
        "tagline" to tr(
            "Master Smali Bytecode, Dalvik Registers, DEX Anatomy & Android Engineering with crystal-clear clarity.",
            "একদম জিরো থেকে অ্যাডভান্স পর্যন্ত স্মালি ল্যাঙ্গুয়েজ, ডালভিক রেজিস্টার, DEX গঠন ও অ্যান্ড্রয়েড কোডিং পানির মতো সহজে শিখুন।",
            "بالکل صفر سے ایڈوانس تک سمالی بائٹ کوڈ، ڈالوک رجسٹرز، DEX ساخت اور اینڈرائیڈ کوڈنگ انتہائی آسان انداز میں سیکھیں۔",
            "शून्य से एडवांस तक स्माली बाइटकोड, डाल्विक रजिस्टर, DEX संरचना और एंड्रॉइड कोडिंग बिल्कुल सरल भाषा में सीखें।",
            "أتقن لغة سمالي ومسجلات دالفيك وبنية DEX وتطوير أندرويد من الصفر حتى الاحتراف بأسلوب واضح ومنظم.",
            "از صفر تا پیشرفته زبان سمالی، رجسترهای دالویک، ساختار DEX و برنامه‌نویسی اندروید را به ساده‌ترین شکل بیاموزید.",
            "Kuasai Bytecode Smali, Register Dalvik, Anatomi DEX & Rekayasa Android dari nol hingga mahir dengan sangat rapi."
        ),

        // Bottom Navigation (5 Clean iOS Tabs + Sub-tabs)
        "nav_home" to tr("Home", "হোম", "ہوم", "होम", "الرئيسية", "خانه", "Beranda"),
        "nav_curriculum" to tr("Courses", "কোর্সসমূহ", "کورسز", "कोर्स", "الدروس", "کورس‌ها", "Kursus"),
        "nav_dictionary" to tr("Smali A–Z", "স্মালি গাইড", "سمالی گائیڈ", "स्माली गाइड", "دليل سمالي", "راهنمای سمالی", "Panduan Smali"),
        "nav_lab" to tr("Studio Lab", "স্টুডিও ল্যাব", "لیب", "स्टूडियो लैब", "المختبر", "لابراتوار", "Lab Studio"),
        "nav_compare" to tr("Compare", "কোড তুলনা", "موازنہ", "कोड तुलना", "مقارنة", "مقایسه کد", "Bandingkan"),
        "nav_visualizer" to tr("Registers", "রেজিস্টার", "رجسٹرز", "रजिस्टर", "المسجلات", "رجسترها", "Register"),
        "nav_playground" to tr("Sandbox", "স্যান্ডবক্স", "سینڈ باکس", "सैंडबॉक्स", "المحرر", "سندباکس", "Sandbox"),
        "nav_quiz" to tr("Quiz", "কুইজ", "کوئز", "क्विज़", "الاختبار", "آزمون", "Kuis"),
        "nav_settings" to tr("Settings", "সেটিংস", "ترتیبات", "सेটিংস".let { "सेटिंग्स" }, "الإعدادات", "تنظیمات", "Pengaturan"),

        // Dashboard
        "academy_progress" to tr(
            "Overall Mastery Progress",
            "আপনার শেখার অগ্রগতি",
            "آپ کی سیکھنے کی پیشرفت",
            "आपकी सीखने की प्रगति",
            "تقدم التعلم الكلي",
            "پیشرفت کلی آموزش",
            "Progres Penguasaan"
        ),
        "lessons_completed_fmt" to tr(
            "%1\$s / %2\$s lessons (%3\$s%%)",
            "%1\$s / %2\$sটি পাঠ সম্পন্ন (%3\$s%%)",
            "%1\$s / %2\$s اسباق مکمل (%3\$s%%)",
            "%1\$s / %2\$s पाठ पूर्ण (%3\$s%%)",
            "%1\$s / %2\$s درس مكتمل (%3\$s%%)",
            "%1\$s از %2\$s درس تکمیل (%3\$s%%)",
            "%1\$s / %2\$s pelajaran (%3\$s%%)"
        ),
        "streak_days" to tr("Streak", "ধারাবাহিকতা", "تسلسل", "लगातार दिन", "التتابع", "پیوستگی", "Beruntun"),
        "days_unit" to tr("%1\$s Days", "%1\$s দিন", "%1\$s دن", "%1\$s दिन", "%1\$s يوم", "%1\$s روز", "%1\$s Hari"),
        "xp_points" to tr("Total XP", "মোট পয়েন্ট", "کل پوائنٹس", "कुल अंक", "النقاط", "مجموع امتیاز", "Total XP"),
        "xp_unit" to tr("%1\$s XP", "%1\$s XP", "%1\$s XP", "%1\$s XP", "%1\$s نقطة", "%1\$s امتیاز", "%1\$s XP"),
        "daily_goal" to tr("Saved", "বুকমার্ক", "محفوظ", "बुकमार्क", "المحفوظات", "ذخیره‌شده", "Tersimpan"),
        "saved_unit" to tr("%1\$s Items", "%1\$sটি পাঠ", "%1\$s اسباق", "%1\$s पाठ", "%1\$s دروس", "%1\$s درس", "%1\$s Item"),
        "smali_hub_banner_badge" to tr(
            "COMPLETE SMALI ENCYCLOPEDIA",
            "সম্পূর্ণ স্মালি ল্যাঙ্গুয়েজ মাস্টার গাইড",
            "مکمل سمالی لینگویج گائیڈ",
            "संपूर्ण स्माली लैंग्वेज मास्टर गाइड",
            "موسوعة لغة سمالي الشاملة",
            "دانشنامه کامل زبان سمالی",
            "ENSIKLOPEDIA LENGKAP SMALI"
        ),
        "smali_hub_banner_title" to tr(
            "Understand Smali from Zero to Advanced",
            "স্মালি ল্যাঙ্গুয়েজ বুঝুন একদম জিরো থেকে অ্যাডভান্স পর্যন্ত",
            "سمالی زبان کو صفر سے ایڈوانس تک مکمل سمجھیں",
            "स्माली भाषा को शून्य से एडवांस तक पूरी तरह समझें",
            "افهم لغة سمالي بالكامل من الصفر إلى الاحتراف",
            "زبان سمالی را از صفر تا پیشرفته کامل درک کنید",
            "Pahami Bahasa Smali dari Nol hingga Mahir"
        ),
        "smali_hub_banner_subtitle" to tr(
            "16 Deep Modules • 36+ Opcodes • Register Calculator • Live Signature Decoder • Cheat Sheets",
            "১৬টি পূর্ণাঙ্গ অধ্যায় • ৩৬+ অপকোড • রেজিস্টার ক্যালকুলেটর • লাইভ সিগনেচার ডিকোডার • সিনট্যাক্স চিটশিট",
            "16 تفصیلی اسباق • 36+ اوپ کوڈز • رجسٹر کیلکولیٹر • لائیو سگنیچر ڈیکوڈر • چیٹ شیٹ",
            "16 विस्तृत अध्याय • 36+ ऑपकोड • रजिस्टर कैलकुलेटर • लाइव सिग्नेचर डिकोडर • चीट शीट",
            "16 وحدة مفصلة • +36 تعليمة • حاسبة المسجلات • محلل التواقيع الفوري • ملخصات القواعد",
            "۱۶ فصل کامل • ۳۶+ آپ‌کد • ماشین‌حساب رجستر • رمزگشای زنده امضا • خلاصه قواعد",
            "16 Modul Mendalam • 36+ Opcode • Kalkulator Register • Dekoder Tanda Tangan • Cheat Sheet"
        ),
        "smali_hub_cta" to tr(
            "Open Smali Master Hub",
            "স্মালি মাস্টার গাইড খুলুন",
            "سمالی ماسٹر گائیڈ کھولیں",
            "स्माली मास्टर गाइड खोलें",
            "افتح مركز سمالي المتكامل",
            "باز کردن راهنمای جامع سمالی",
            "Buka Pusat Master Smali"
        ),
        "explore_tracks" to tr(
            "Structured Learning Tracks",
            "সাজানো লার্নিং ট্র্যাকসমূহ",
            "منظم تعلیمی ٹریکس",
            "व्यवस्थित लर्निंग ट्रैक्स",
            "مسارات التعلم المنظمة",
            "مسیرهای آموزشی منظم",
            "Jalur Pembelajaran Terstruktur"
        ),
        "quick_labs_title" to tr(
            "Interactive Bytecode Labs",
            "ইন্টারঅ্যাক্টিভ প্র্যাকটিস ল্যাব",
            "انٹرایکٹو بائٹ کوڈ لیبز",
            "इंटरैक्टिव बाइटकोड लैब",
            "مختبرات البايت كود التفاعلية",
            "آزمایشگاه تعاملی بایت‌کد",
            "Lab Bytecode Interaktif"
        ),
        "quick_compare_sub" to tr(
            "Java ↔ Kotlin ↔ Smali",
            "জাভা ↔ কোটলিন ↔ স্মালি তুলনা",
            "جاوا ↔ کوٹلن ↔ سمالی موازنہ",
            "जावा ↔ कोटलिन ↔ स्माली तुलना",
            "مقارنة جافا وكوتلن وسمالي",
            "مقایسه جاوا، کاتلین و سمالی",
            "Java ↔ Kotlin ↔ Smali"
        ),
        "quick_visualizer_sub" to tr(
            "Step-by-step memory frame",
            "রেজিস্টার মেমোরির লাইভ দৃশ্য",
            "رجسٹر میموری کا لائیو منظر",
            "रजिस्टर मेमोरी का लाइव दृश्य",
            "محاكاة حية لذاكرة المسجلات",
            "نمایش زنده حافظه رجسترها",
            "Memori register langkah demi langkah"
        ),
        "quick_sandbox_sub" to tr(
            "Write & verify Smali code",
            "স্মালি কোড লিখে ভুল যাচাই",
            "سمالی کوڈ لکھیں اور جانچیں",
            "स्माली कोड लिखें और जांचें",
            "اكتب وافحص شيفرة سمالي",
            "کد سمالی بنویسید و بررسی کنید",
            "Tulis & verifikasi kode Smali"
        ),
        "quick_decoder_sub" to tr(
            "Decode any Smali signature",
            "যেকোনো স্মালি লাইন ভেঙে বুঝুন",
            "کسی بھی سمالی لائن کو سمجھیں",
            "किसी भी स्माली लाइन को समझें",
            "فكك أي سطر أو توقيع سمالي",
            "هر خط سمالی را تجزیه و درک کنید",
            "Uraikan tanda tangan Smali"
        ),
        "bookmarked_section_title" to tr(
            "Saved Bookmarks",
            "আপনার সেভ করা পাঠসমূহ",
            "آپ کے محفوظ کردہ اسباق",
            "आपके सहेजे गए पाठ",
            "الدروس المحفوظة",
            "درس‌های ذخیره‌شده شما",
            "Pelajaran Tersimpan"
        ),
        "ethical_disclaimer_title" to tr(
            "Educational & Security Research Notice",
            "শিক্ষামূলক ও সাইবার সিকিউরিটি নীতিমালা",
            "تعلیمی اور سیکیورٹی ریسرچ نوٹس",
            "शैक्षिक और सुरक्षा अनुसंधान सूचना",
            "تنبيه البحث الأمني والتعليمي",
            "نکته آموزشی و پژوهش امنیتی",
            "Catatan Riset Keamanan & Edukasi"
        ),
        "ethical_disclaimer_body" to tr(
            "Designed for Android developers, reverse engineering learners, and authorized app security auditing.",
            "এই অ্যাপটি শুধুমাত্র অ্যান্ড্রয়েড ডেভেলপমেন্ট শেখা, ডিবাগিং এবং বৈধ অ্যাপ সিকিউরিটি গবেষণার উদ্দেশ্যে তৈরি।",
            "یہ ایپ صرف اینڈرائیڈ ڈیولپمنٹ سیکھنے، ڈیبگنگ اور قانونی سیکیورٹی ریسرچ کے مقصد کے لیے بنائی گئی ہے۔",
            "यह ऐप केवल एंड्रॉइड डेवलपमेंट सीखने, डीबगिंग और अधिकृत सुरक्षा ऑडिटिंग के लिए बनाया गया है।",
            "صُمم هذا التطبيق لتعليم هندسة أندرويد وفحص أمان التطبيقات المصرح بها قانونياً فقط.",
            "این برنامه صرفاً برای آموزش برنامه‌نویسی اندروید، دیباگ و بررسی امنیتی مجاز طراحی شده است.",
            "Dirancang khusus untuk pembelajaran rekayasa Android, debugging, dan audit keamanan aplikasi resmi."
        ),

        // Tracks & Tags
        "track_smali_title" to tr(
            "Smali Language (Zero to Advanced)",
            "স্মালি ল্যাঙ্গুয়েজ (জিরো থেকে অ্যাডভান্স)",
            "سمالی لینگویج (صفر سے ایڈوانس)",
            "स्माली लैंग्वेज (शून्य से एडवांस)",
            "لغة سمالي (من الصفر للاحتراف)",
            "زبان سمالی (از صفر تا پیشرفته)",
            "Bahasa Smali (Nol ke Mahir)"
        ),
        "track_smali_desc" to tr(
            "Complete 16-chapter masterclass: DEX, registers, types, opcodes, loops, fields, try-catch, Kotlin bytecode & verifier rules.",
            "১৬টি পূর্ণাঙ্গ অধ্যায়ে DEX, রেজিস্টার, ডেটা টাইপ, অপকোড, লুপ, ফিল্ড, ট্রাই-ক্যাচ ও কোটলিন স্মালি একদম গোড়া থেকে শিখুন।",
            "16 مکمل ابواب میں DEX، رجسٹرز، ٹائپس، اوپ کوڈز، لوپس، فیلڈز اور کوٹلن بائٹ کوڈ تفصیل سے سیکھیں۔",
            "16 संपूर्ण अध्यायों में DEX, रजिस्टर, टाइप, ऑपकोड, लूप, फ़ील्ड, ट्राई-कैच और कोटलिन बाइटकोड गहराई से सीखें।",
            "دورة متكاملة من 16 فصلاً تغطي DEX والمسجلات والأنواع والتعليمات والحلقات والاستثناءات وفاحص أندرويد.",
            "دوره کامل ۱۶ فصلی شامل DEX، رجسترها، انواع داده، آپ‌کدها، حلقه‌ها، فیلدها و قوانین بررسی بایت‌کد.",
            "Kelas master 16 bab lengkap: DEX, register, tipe data, opcode, loop, field, try-catch & aturan verifier."
        ),
        "track_android_title" to tr(
            "Android App Building",
            "অ্যান্ড্রয়েড অ্যাপ ডেভেলপমেন্ট",
            "اینڈرائیڈ ایپ سازی",
            "एंड्रॉइड ऐप डेवलपमेंट",
            "بناء تطبيقات أندرويد",
            "ساخت اپلیکیشن اندروید",
            "Pengembangan Aplikasi Android"
        ),
        "track_android_desc" to tr(
            "Connect UI buttons, Intents, lifecycle stages, dialogs, storage, RecyclerView & WebView step by step.",
            "বাটন কানেকশন, ইনটেন্ট, লাইফসাইকেল, ডায়ালগ, ডাটা সেভ, রিসাইক্লারভিউ ও ওয়েবভিউ ধাপে ধাপে শিখুন।",
            "بٹن کنکشن، انٹینٹ، لائف سائیکل، ڈائیلاگ، ڈیٹا اسٹوریج، ری سائیکلر ویو اور ویب ویو مرحلہ وار سیکھیں۔",
            "बटन कनेक्शन, इंटेंट, लाइफसाइकिल, डायलॉग, डेटा स्टोरेज, रिसाइक्लरव्यू और वेबव्यू चरण-दर-चरण सीखें।",
            "ربط الأزرار، الانتقال بين الشاشات، دورة الحياة، النوافذ المنبثقة، التخزين والقوائم خطوة بخطوة.",
            "اتصال دکمه‌ها، اینتنت، چرخه حیات، دیالوگ‌ها، ذخیره‌سازی داده و لیست‌ها به صورت گام‌به‌گام.",
            "Hubungkan tombol UI, Intent, siklus hidup, dialog, penyimpanan, RecyclerView & WebView."
        ),
        "track_java_title" to tr(
            "Java Programming (Zero to Pro)",
            "জাভা প্রোগ্রামিং (জিরো থেকে অ্যাডভান্স)",
            "جاوا پروگرامنگ (صفر سے ایڈوانس)",
            "जावा प्रोग्रामिंग (शून्य से एडवांस)",
            "برمجة جافا (من الصفر للاحتراف)",
            "برنامه‌نویسی جاوا (از صفر تا پیشرفته)",
            "Pemrograman Java (Nol ke Mahir)"
        ),
        "track_java_desc" to tr(
            "Complete 16-chapter Java masterclass + 32+ keywords, Stack vs Heap RAM visualizer, OOP cheat sheets & live Java→Smali compiler.",
            "১৬টি পূর্ণাঙ্গ অধ্যায় + ৩২+ কীওয়ার্ড ডিকশনারি, Stack বনাম Heap মেমোরি ভিজ্যুয়ালাইজার, OOP চিটশিট ও লাইভ জাভা→স্মালি কম্পাইলার!",
            "16 مکمل ابواب + 32+ کی ورڈز، اسٹیک بمقابلہ ہیپ میموری ویژولائزر، OOP چیٹ شیٹ اور لائیو جاوا→سمالی کمپائلر۔",
            "16 संपूर्ण अध्याय + 32+ कीवर्ड डिक्शनरी, Stack बनाम Heap मेमोरी विज़ुअलाइज़र, OOP चीट शीट और लाइव जावा→स्माली कंपाइलर।",
            "دورة جافا متكاملة من 16 فصلاً + قاموس الكلمات المفتاحية ومحاكي الذاكرة ومترجم جافا إلى سمالي المباشر.",
            "دوره کامل ۱۶ فصلی جاوا + دیکشنری کلیدواژه‌ها، شبیه‌ساز حافظه Stack/Heap و مبدل زنده جاوا به سمالی.",
            "Kelas master Java 16 bab lengkap + kamus kata kunci, visualisasi RAM Stack/Heap & kompiler Java→Smali."
        ),
        "track_kotlin_title" to tr(
            "Modern Kotlin for Android",
            "আধুনিক কোটলিন প্রোগ্রামিং (জিরো থেকে প্রো)",
            "اینڈرائیڈ کے لیے جدید کوٹلن",
            "आधुनिक कोटलिन प्रोग्रामिंग",
            "لغة كوتلن الحديثة لأندرويد",
            "برنامه‌نویسی مدرن کاتلین",
            "Kotlin Modern untuk Android"
        ),
        "track_kotlin_desc" to tr(
            "Null-safety, data/sealed classes, extension functions, coroutines, StateFlow & bytecode interop.",
            "val/var, নাল সেফটি, ডেটা ও সিলড ক্লাস, এক্সটেনশন ফাংশন, কোরুটিং ও স্টেট-ফ্লো একদম সহজে শিখুন।",
            "نل سیفٹی، ڈیٹا اور سیلڈ کلاسز، ایکسٹینشن فنکشنز، کوروٹینز اور اسٹیٹ فلو نہایت آسانی سے سیکھیں۔",
            "नल-सेफ्टी, डेटा व सील्ड क्लासेस, एक्सटेंशन फ़ंक्शन, कोरूटीन और स्टेट-फ़्लो बेहद आसानी से सीखें।",
            "الأمان من القيم الفارغة، فئات البيانات، الدوال الممتدة، الكوروتينات والتدفقات التفاعلية بسهولة.",
            "امنیت نال، دیتا کلاس‌ها، توابع توسعه‌ای، کوروتین‌ها و مدیریت وضعیت مدرن به زبان ساده.",
            "Null-safety, data/sealed class, fungsi ekstensi, coroutine & StateFlow dijelaskan dengan rapi."
        ),
        "track_python_title" to tr(
            "Python APK & Security Automation",
            "পাইথন প্রোগ্রামিং ও এপিকে অটোমেশন",
            "پائتھن اور APK آٹومیشن",
            "पायथन और APK ऑटोमेशन",
            "بايثون وأتمتة فحص APK",
            "پایتون و اتوماسیون APK",
            "Otomasi APK dengan Python"
        ),
        "track_python_desc" to tr(
            "Clean Python scripts to inspect DEX headers, scan Smali trees, extract strings & audit manifests.",
            "সহজ পাইথন স্ক্রিপ্ট দিয়ে APK বিশ্লেষণ, DEX স্ট্রিং এক্সট্রাকশন, ম্যানিফেস্ট ও স্মালি অটোমেশন শিখুন।",
            "آسان پائتھن اسکرپٹس کے ذریعے APK فائل کا تجزیہ، اسٹرنگ ایکسٹریکشن اور خودکار جانچ سیکھیں۔",
            "सरल पायथन स्क्रिप्ट से APK निरीक्षण, DEX स्ट्रिंग निष्कर्षण और स्वचालित सुरक्षा ऑडिट सीखें।",
            "سكربتات بايثون نظيفة لفحص ترويسات DEX واستخراج النصوص وأتمتة التدقيق الأمني.",
            "اسکریپت‌نویسی پایتون برای تحلیل هدر DEX، استخراج رشته‌ها و بررسی خودکار فایل‌های APK.",
            "Skrip Python rapi untuk memeriksa header DEX, mengekstrak string & mengaudit APK."
        ),

        "track_short_smali" to tr("Smali", "স্মালি", "سمالی", "स्माली", "سمالي", "سمالی", "Smali"),
        "track_short_android" to tr("Android", "অ্যান্ড্রয়েড", "اینڈرائیڈ", "एंड्रॉइड", "أندرويد", "اندروید", "Android"),
        "track_short_java" to tr("Java", "জাভা", "جاوا", "जावा", "جافا", "جاوا", "Java"),
        "track_short_kotlin" to tr("Kotlin", "কোটলিন", "کوٹلن", "कोटलिन", "كوتلن", "کاتلین", "Kotlin"),
        "track_short_python" to tr("Python", "পাইথন", "پائتھن", "पायथन", "بايثون", "پایتون", "Python"),

        "track_tag_smali" to tr("Dalvik & DEX • 16 Lessons", "ডালভিক ও DEX • ১৬টি পাঠ", "ڈالوک اور DEX • 16 اسباق", "डाल्विक और DEX • 16 पाठ", "دالفيك و DEX • 16 درساً", "دالویک و DEX • ۱۶ درس", "Dalvik & DEX • 16 Modul"),
        "track_tag_android" to tr("UI, Lifecycle & Lists • 6 Lessons", "ইউআই, ইনটেন্ট ও লিস্ট • ৬টি পাঠ", "یو آئی اور لائف سائیکل • 6 اسباق", "यूआई और लाइफसाइकिल • 6 पाठ", "الواجهة ودورة الحياة • 6 دروس", "رابط کاربری و لیست‌ها • ۶ درس", "UI & Lifecycle • 6 Modul"),
        "track_tag_java" to tr("Zero to Pro OOP • 16 Lessons", "জিরো থেকে প্রো • ১৬টি পাঠ + ল্যাব", "صفر سے پرو OOP • 16 اسباق", "शून्य से प्रो OOP • 16 पाठ", "من الصفر للاحتراف • 16 درساً", "از صفر تا پیشرفته • ۱۶ درس", "Nol ke Pro OOP • 16 Modul"),
        "track_tag_kotlin" to tr("Coroutines & Modern • 6 Lessons", "নাল-সেফ ও কোরুটিং • ৬টি পাঠ", "جدید کوٹلن • 6 اسباق", "आधुनिक कोटलिन • 6 पाठ", "كوتلن الحديثة • 6 دروس", "کاتلین مدرن • ۶ درس", "Kotlin Modern • 6 Modul"),
        "track_tag_python" to tr("APK Audit Scripts • 5 Lessons", "এপিকে অটোমেশন স্ক্রিপ্ট • ৫টি পাঠ", "APK آڈٹ اسکرپٹنگ • 5 اسباق", "APK ऑडिट स्क्रिप्ट • 5 पाठ", "أتمتة فحص التطبيقات • 5 دروس", "اسکریپت بررسی APK • ۵ درس", "Skrip Audit APK • 5 Modul"),

        // Levels
        "level_1_title" to tr(
            "Stage 1 • Zero / Absolute Beginner",
            "ধাপ ১ • একদম জিরো / বেসিক শুরু",
            "مرحلہ 1 • بالکل ابتدائی (صفر سے)",
            "चरण 1 • बिल्कुल शून्य / शुरुआती",
            "المرحلة 1 • البداية من الصفر",
            "مرحله ۱ • شروع از صفر مطلق",
            "Tahap 1 • Nol / Pemula Mutlak"
        ),
        "level_2_title" to tr(
            "Stage 2 • Core Intermediate",
            "ধাপ ২ • ইন্টারমিডিয়েট (মধ্যম স্তর)",
            "مرحلہ 2 • درمیانی سطح",
            "चरण 2 • इंटरमीडिएट स्तर",
            "المرحلة 2 • المستوى المتوسط",
            "مرحله ۲ • سطح متوسط و کاربردی",
            "Tahap 2 • Tingkat Menengah"
        ),
        "level_3_title" to tr(
            "Stage 3 • Advanced Internals",
            "ধাপ ৩ • অ্যাডভান্সড (গভীর বিশ্লেষণ)",
            "مرحلہ 3 • ایڈوانسڈ سطح",
            "चरण 3 • एडवांस आंतरिक संरचना",
            "المرحلة 3 • المستوى المتقدم",
            "مرحله ۳ • سطح پیشرفته و عمیق",
            "Tahap 3 • Tingkat Lanjut"
        ),
        "level_4_title" to tr(
            "Stage 4 • Pro Architecture & Verification",
            "ধাপ ৪ • প্রো আর্কিটেকচার ও ভেরিফায়ার",
            "مرحلہ 4 • پرو آرکیٹیکچر اور ویریفائر",
            "चरण 4 • प्रो आर्किटेक्चर और वेरिफ़ायर",
            "المرحلة 4 • الاحتراف وفاحص البايت كود",
            "مرحله ۴ • معماری حرفه‌ای و بررسی‌کننده",
            "Tahap 4 • Arsitektur Pro & Verifier"
        ),
        "lessons_count_fmt" to tr(
            "%1\$s Modules",
            "%1\$sটি অধ্যায়",
            "%1\$s اسباق",
            "%1\$s अध्याय",
            "%1\$s وحدات",
            "%1\$s فصل",
            "%1\$s Modul"
        ),
        "lesson_meta_fmt" to tr(
            "%1\$s min read • %2\$s sections",
            "%1\$s মিনিট পাঠ • %2\$sটি অংশ",
            "%1\$s منٹ مطالعہ • %2\$s حصے",
            "%1\$s मिनट पठन • %2\$s भाग",
            "%1\$s دقائق قراءة • %2\$s أقسام",
            "%1\$s دقیقه مطالعه • %2\$s بخش",
            "%1\$s mnt baca • %2\$s bagian"
        ),
        "next_lesson" to tr(
            "Next Module",
            "পরবর্তী অধ্যায়ে যান",
            "اگلا سبق",
            "अगला अध्याय",
            "الدرس التالي",
            "فصل بعدی",
            "Modul Berikutnya"
        ),
        "back_to_list" to tr("Back", "ফিরে যান", "واپس", "वापस", "رجوع", "بازگشت", "Kembali"),

        // Lesson Reader & Code Headers
        "kid_analogy_badge" to tr(
            "Real-Life Simple Analogy",
            "সহজ বাস্তব উদাহরণ (একদম সহজে বোঝার জন্য)",
            "روزمرہ زندگی کی آسان مثال",
            "आसान वास्तविक उदाहरण",
            "تشبيه مبسط من الحياة اليومية",
            "مثال ساده از زندگی روزمره",
            "Analogi Sederhana Sehari-hari"
        ),
        "practical_tip_badge" to tr(
            "How Android Runtime Executes This",
            "অ্যান্ড্রয়েড ফোনের ভেতরে যেভাবে কাজ করে",
            "اینڈرائیڈ فون کے اندر یہ کیسے کام کرتا ہے",
            "एंड्रॉइड फोन के अंदर यह कैसे काम करता है",
            "كيف ينفذ نظام أندرويد هذا فعلياً",
            "در داخل سیستم اندروید چگونه اجرا می‌شود",
            "Cara Kerja di Dalam Runtime Android"
        ),
        "key_takeaway_badge" to tr(
            "Key Insight",
            "মূল শিক্ষণীয় পয়েন্ট",
            "اہم نکتہ",
            "मुख्य बिंदु",
            "خلاصة مهمة",
            "نکته کلیدی",
            "Intisari Penting"
        ),
        "code_header_xml" to tr("XML LAYOUT", "এক্সএমএল ডিজাইন (XML)", "XML لے آؤٹ", "XML लेआउट", "تصميم XML", "طراحی XML", "TATA LETAK XML"),
        "code_header_java" to tr("JAVA CODE", "জাভা কোড (JAVA)", "جاوا کوڈ", "जावा कोड", "شيفرة جافا", "کد جاوا", "KODE JAVA"),
        "code_header_kotlin" to tr("KOTLIN CODE", "কোটলিন কোড (KOTLIN)", "کوٹلن کوڈ", "कोटलिन कोड", "شيفرة كوتلن", "کد کاتلین", "KODE KOTLIN"),
        "code_header_python" to tr("PYTHON SCRIPT", "পাইথন স্ক্রিপ্ট (PYTHON)", "پائتھن اسکرپٹ", "पायथन स्क्रिप्ट", "سكربت بايثون", "اسکریپت پایتون", "SKRIP PYTHON"),
        "code_header_smali" to tr("SMALI BYTECODE", "স্মালি কোড (SMALI)", "سمالی بائٹ کوڈ", "स्माली बाइटकोड", "بايت كود سمالي", "بایت‌کد سمالی", "BYTECODE SMALI"),
        "copy_btn" to tr("Copy", "কপি", "کاپی", "कॉपी", "نسخ", "کپی", "Salin"),
        "copied_btn" to tr("Copied", "কপি হয়েছে", "کاپی ہوگیا", "कॉपी हुआ", "تم النسخ", "کپی شد", "Disalin"),
        "mark_completed" to tr("Mark Complete", "সম্পন্ন করুন", "مکمل کریں", "पूर्ण करें", "تحديد كمكتمل", "تکمیل درس", "Tandai Selesai"),
        "completed_badge" to tr("Completed ✓", "সম্পন্ন ✓", "مکمل ✓", "पूर्ण ✓", "مكتمل ✓", "تکمیل شد ✓", "Selesai ✓"),
        "continue_reading" to tr("Open Lesson", "পাঠটি খুলুন", "سبق کھولیں", "पाठ खोलें", "فتح الدرس", "باز کردن درس", "Buka Pelajaran"),

        // Smali A-to-Z Master Hub (4 Segmented Sub-views)
        "smali_tab_roadmap" to tr("Zero→Pro", "জিরো→প্রো", "صفر→پرو", "शून्य→प्रो", "المسار", "صفر تا پرو", "Nol→Pro"),
        "smali_tab_opcodes" to tr("Opcodes", "অপকোডসমূহ", "اوپ کوڈز", "ऑपकोड", "التعليمات", "آپ‌کدها", "Opcode"),
        "smali_tab_cheatsheet" to tr("Syntax", "সিনট্যাক্স ও টাইপ", "نحو اور ٹائپس", "सिंटैक्स", "القواعد", "قواعد و تایپ", "Sintaks"),
        "smali_tab_decoder" to tr("Decoder", "ডিকোডার", "ڈیکوڈر", "डिकोडर", "المحلل", "رمزگشا", "Dekoder"),

        "smali_hub_header_title" to tr(
            "Smali Language Master Center",
            "স্মালি ল্যাঙ্গুয়েজ মাস্টার গাইড",
            "سمالی لینگویج ماسٹر سینٹر",
            "स्माली लैंग्वेज मास्टर सेंटर",
            "المركز الشامل للغة سمالي",
            "مرکز جامع آموزش زبان سمالی",
            "Pusat Master Bahasa Smali"
        ),
        "smali_hub_header_desc" to tr(
            "Everything needed to read, trace, decode, and write Dalvik Smali bytecode from scratch.",
            "জিরো থেকে অ্যাডভান্স পর্যন্ত স্মালি কোড পড়া, বোঝা, অপকোড খোঁজা এবং সিগনেচার বিশ্লেষণের সম্পূর্ণ ভান্ডার।",
            "صفر سے ایڈوانس تک سمالی کوڈ پڑھنے، سمجھنے، اوپ کوڈز تلاش کرنے اور سگنیچر ڈیکوڈ کرنے کا مکمل مرکز۔",
            "शून्य से एडवांस तक स्माली कोड पढ़ने, समझने, ऑपकोड खोजने और सिग्नेचर डिकोड करने का संपूर्ण केंद्र।",
            "كل ما تحتاجه لقراءة وفهم وتحليل وكتابة شيفرة سمالي من الصفر حتى الاحتراف في مكان واحد.",
            "همه‌چیز برای خواندن، درک، جستجوی آپ‌کدها و رمزگشایی کدهای سمالی از صفر تا پیشرفته.",
            "Semua yang dibutuhkan untuk membaca, melacak, mendekode & menulis Smali dari dasar."
        ),

        // Opcode Dictionary
        "search_hint" to tr(
            "Search opcode, hex, Java/Kotlin or meaning...",
            "অপকোড, হেক্স কোড বা বাংলায় কাজ লিখে খুঁজুন...",
            "اوپ کوڈ، ہیکس یا مفہوم تلاش کریں...",
            "ऑपकोड, हेक्स या अर्थ खोजें...",
            "ابحث عن التعليمة أو الرمز السداسي أو المعنى...",
            "جستجوی آپ‌کد، کد هگز یا کاربرد...",
            "Cari opcode, hex, atau kegunaan..."
        ),
        "all_opcodes_fmt" to tr(
            "All (%1\$s)",
            "সবগুলো (%1\$s)",
            "تمام (%1\$s)",
            "सभी (%1\$s)",
            "الكل (%1\$s)",
            "همه (%1\$s)",
            "Semua (%1\$s)"
        ),
        "cat_CONSTANTS" to tr("Constants", "কনস্ট্যান্ট (মান লোড)", "کانسٹنٹس", "कॉन्स्टेंट", "الثوابت", "ثابت‌ها", "Konstanta"),
        "cat_MOVES" to tr("Moves & Results", "মুভ ও রেজাল্ট", "موو اور رزلٹ", "मूव और रिज़ल्ट", "النقل والنتائج", "انتقال داده", "Move & Hasil"),
        "cat_RETURNS" to tr("Returns", "রিটার্ন (ফেরত)", "ریٹرن", "रिटर्न", "الإرجاع", "بازگشت مقدار", "Return"),
        "cat_FIELDS" to tr("Fields (iget/sget)", "ফিল্ড (iget/sget)", "فیلڈز", "फ़ील्ड्स", "الحقول", "فیلدها", "Field"),
        "cat_OBJECTS_ARRAYS" to tr("Objects & Arrays", "অবজেক্ট ও অ্যারে", "آبجیکٹ اور ایرے", "ऑब्जेक्ट और ऐरे", "الكائنات والمصفوفات", "اشیاء و آرایه", "Objek & Array"),
        "cat_BRANCHES" to tr("If, Goto & Switch", "শর্ত ও জাম্প (if/goto)", "شرائط اور جمپ", "शर्त और जंप", "التفرع والقفز", "شرط و پرش", "Percabangan"),
        "cat_INVOCATIONS" to tr("Method Calls", "মেথড কল (invoke)", "میتھڈ کالز", "मेथड कॉल", "استدعاء الدوال", "فراخوانی متد", "Pemanggilan"),
        "cat_ARITHMETIC" to tr("Math & Bitwise", "গণিত ও লজিক", "حساب اور لاجک", "गणित और लॉजिक", "الحساب والمنطق", "محاسبات و منطق", "Aritmetika"),
        "cat_CONVERSIONS" to tr("Type Casts", "টাইপ রূপান্তর", "ٹائپ تبدیلی", "टाइप रूपांतरण", "تحويل الأنواع", "تبدیل نوع", "Konversi Tipe"),
        "cat_EXCEPTIONS" to tr("Exceptions & Locks", "এরর ও লক (throw)", "ایکسپشنز", "एक्सेप्शन", "الاستثناءات", "مدیریت خطا", "Eksepsi"),

        "syntax_label" to tr("Syntax", "সিনট্যাক্স গঠন", "نحو (ساخت)", "सिंटैक्स", "الصيغة", "ساختار دستور", "Sintaks"),
        "java_equiv_label" to tr("Java Equivalent", "জাভায় সমতুল্য কোড", "جاوا میں متبادل", "जावा समतुल्य", "المكافئ في جافا", "معادل در جاوا", "Padanan Java"),
        "kotlin_equiv_label" to tr("Kotlin Equivalent", "কোটলিনে সমতুল্য কোড", "کوٹلن میں متبادل", "कोटलिन समतुल्य", "المكافئ في كوتلن", "معادل در کاتلین", "Padanan Kotlin"),
        "smali_example_label" to tr("Smali Usage Example", "স্মালি কোডে ব্যবহারের উদাহরণ", "سمالی کوڈ کی مثال", "स्माली उपयोग उदाहरण", "مثال تطبيقي في سمالي", "نمونه کاربرد در سمالی", "Contoh Penggunaan Smali"),
        "common_trap_label" to tr("Common Mistake to Avoid", "সাধারণ ভুল ও সতর্কতা", "عام غلطی اور احتیاط", "सामान्य गलती और सावधानी", "خطأ شائع يجب تجنبه", "اشتباه رایج و هشدار", "Kesalahan Umum"),
        "close_btn" to tr("Done", "বন্ধ করুন", "بند کریں", "बंद करें", "إغلاق", "بستن", "Tutup"),
        "empty_search_title" to tr("No matching opcodes found", "কোনো অপকোড পাওয়া যায়নি", "کوئی اوپ کوڈ نہیں ملا", "कोई ऑपकोड नहीं मिला", "لم يتم العثور على تعليمات", "هیچ آپ‌کدی یافت نشد", "Opcode tidak ditemukan"),
        "empty_search_subtitle" to tr("Try another keyword or clear the category filter.", "অন্য কোনো শব্দ লিখে খুঁজুন অথবা ফিল্টার পরিবর্তন করুন।", "کوئی دوسرا لفظ آزمائیں یا فلٹر صاف کریں۔", "कोई दूसरा शब्द आज़माएं या फ़िल्टर हटाएं।", "جرب كلمة أخرى أو امسح عامل التصفية.", "واژه دیگری را امتحان کنید یا فیلتر را بردارید.", "Coba kata kunci lain atau hapus filter."),

        // Cheat Sheet & Register Frame Calculator
        "reg_calc_title" to tr(
            "Interactive Register Frame Calculator (v & p)",
            "ইন্টারঅ্যাক্টিভ রেজিস্টার ক্যালকুলেটর (v ও p কীভাবে বসে)",
            "انٹرایکٹو رجسٹر فریم کیلکولیٹر (v اور p)",
            "इंटरैक्टिव रजिस्टर फ़्रेम कैलकुलेटर (v और p)",
            "حاسبة إطار المسجلات التفاعلية (v و p)",
            "ماشین‌حساب تعاملی قاب رجسترها (v و p)",
            "Kalkulator Frame Register Interaktif (v & p)"
        ),
        "reg_calc_desc" to tr(
            "Toggle static/instance and adjust .locals & parameters to see how Dalvik maps v0..vN and p0..pM.",
            "মেথডটি Static নাকি Instance তা বেছে নিন এবং .locals ও প্যারামিটার বাড়িয়ে-কমিয়ে দেখুন কীভাবে v0..vN এবং p0..pM মেমোরিতে সাজানো থাকে।",
            "اسٹیٹک یا انسٹانس منتخب کریں اور دیکھیں کہ ڈالوک کس طرح v اور p رجسٹرز کو ترتیب دیتا ہے۔",
            "स्टैटिक या इंस्टेंस चुनें और देखें कि डाल्विक मेमोरी में v0..vN और p0..pM कैसे मैप करता है।",
            "بدّل بين الدالة الثابتة والعادية وعدّل العدد لترى كيف يرتب دالفيك مسجلات v و p فوراً.",
            "نوع متد و تعداد متغیرها را تغییر دهید تا نحوه چیدمان رجسترهای v و p را ببینید.",
            "Ubah static/instance dan atur .locals & parameter untuk melihat pemetaan v dan p."
        ),
        "reg_calc_static" to tr("Static Method", "Static মেথড", "Static میتھڈ", "Static मेथड", "دالة ثابتة Static", "متد Static", "Metode Static"),
        "reg_calc_instance" to tr("Instance (has this)", "Instance (p0 = this)", "Instance (p0 = this)", "Instance (p0 = this)", "دالة كائن (p0 = this)", "متد شیء (p0 = this)", "Instance (p0 = this)"),
        "reg_calc_locals" to tr("Local Registers (.locals)", "লোকাল রেজিস্টার (.locals)", "لوکل رجسٹرز (.locals)", "लोकल रजिस्टर (.locals)", "المسجلات المحلية (.locals)", "رجسترهای محلی (.locals)", "Register Lokal (.locals)"),
        "reg_calc_params" to tr("Method Arguments", "প্যারামিটার সংখ্যা", "پیرامیٹرز کی تعداد", "पैरामीटर संख्या", "عدد المعاملات", "تعداد پارامترها", "Jumlah Argumen"),
        "reg_calc_total_fmt" to tr(
            "Total Frame: .registers %1\$s  •  .locals %2\$s",
            "মোট মেমোরি স্লট: .registers %1\$s  •  .locals %2\$s",
            "کل رجسٹرز: .registers %1\$s  •  .locals %2\$s",
            "कुल स्लॉट: .registers %1\$s  •  .locals %2\$s",
            "إجمالي الإطار: .registers %1\$s  •  .locals %2\$s",
            "کل قاب حافظه: .registers %1\$s  •  .locals %2\$s",
            "Total Frame: .registers %1\$s  •  .locals %2\$s"
        ),
        "reg_this_pointer" to tr("Object 'this' pointer", "বর্তমান ক্লাস অবজেক্ট (this)", "موجودہ آبجیکٹ (this)", "वर्तमान ऑब्जेक्ट (this)", "مؤشر الكائن الحالي (this)", "اشاره‌گر شیء جاری (this)", "Pointer objek 'this'"),
        "reg_local_slot" to tr("Local scratchpad", "লোকাল কাজের রেজিস্টার", "لوکل ورک رجسٹر", "लोकल कार्य रजिस्टर", "مسجل عمل محلي", "رجستر کاری محلی", "Register kerja lokal"),
        "reg_param_slot" to tr("Argument #%1\$s", "ইনপুট আর্গুমেন্ট #%1\$s", "آرگومنٹ #%1\$s", "इनपुट आर्ग्यूमेंट #%1\$s", "المعامل رقم #%1\$s", "آرگومان ورودی #%1\$s", "Argumen #%1\$s"),

        "cheat_types_title" to tr(
            "1. All Smali Type Descriptors",
            "১. স্মালির সকল ডেটা টাইপ চিহ্ন (Type Descriptors)",
            "1. سمالی کی تمام ڈیٹا ٹائپس",
            "1. स्माली के सभी डेटा टाइप चिन्ह",
            "1. رموز أنواع البيانات في سمالي",
            "۱. تمام شناسه‌های نوع داده در سمالی",
            "1. Semua Deskriptor Tipe Data Smali"
        ),
        "cheat_directives_title" to tr(
            "2. Core Smali File Directives (.dot rules)",
            "২. স্মালি ফাইলের ডিরেক্টিভসমূহ (.dot নির্দেশনা)",
            "2. سمالی فائل کی اہم ہدایات (.directives)",
            "2. स्माली फ़ाइल के मुख्य डायरेक्टिव (.dot नियम)",
            "2. توجيهات ملفات سمالي الأساسية",
            "۲. دایرکتیوهای اصلی فایل سمالی",
            "2. Direktif Utama File Smali"
        ),
        "cheat_flags_title" to tr(
            "3. Access & Compiler Modifiers",
            "৩. অ্যাক্সেস ও কম্পাইলার ফ্ল্যাগসমূহ",
            "3. رسائی اور کمپائلر فلیگز",
            "3. एक्सेस और कंपाइलर फ़्लैग्स",
            "3. محددات الوصول ورايات المترجم",
            "۳. سطح دسترسی و پرچم‌های کامپایلر",
            "3. Modifier Akses & Kompiler"
        ),

        // Interactive Signature Decoder
        "decoder_title" to tr(
            "Interactive Smali Line & Signature Decoder",
            "ইন্টারঅ্যাক্টিভ স্মালি সিগনেচার ও লাইন ডিকোডার",
            "انٹرایکٹو سمالی سگنیچر اور لائن ڈیکوڈر",
            "इंटरैक्टिव स्माली सिग्नेचर और लाइन डिकोडर",
            "محلل ومفكك تواقيع وأسطر سمالي التفاعلي",
            "رمزگشای تعاملی امضا و خطوط کد سمالی",
            "Dekoder Baris & Tanda Tangan Smali Interaktif"
        ),
        "decoder_desc" to tr(
            "Paste or tap any Smali method header, invoke call, or field instruction to translate every symbol into plain words.",
            "যেকোনো জটিল স্মালি মেথড হেডার, invoke কল বা ফিল্ড লাইন নিচে লিখুন অথবা উদাহরণে ট্যাপ করুন—প্রতিটি অংশ ভেঙে সহজ ভাষায় বুঝিয়ে দেওয়া হবে!",
            "کوئی بھی پیچیدہ سمالی میتھڈ، invoke کال یا فیلڈ لائن نیچے لکھیں یا مثال منتخب کریں—ہر حصے کی آسان وضاحت دیکھیں!",
            "नीचे कोई भी स्माली मेथड हेडर, invoke कॉल या फ़ील्ड लाइन लिखें या चुनें—हर हिस्से का सरल अर्थ तुरंत देखें!",
            "اكتب أو اختر أي توقيع دالة أو استدعاء أو حقل في سمالي ليتم تفكيك كل رمز وشرحه بوضوح تام.",
            "هر امضای متد، فراخوانی یا دستور فیلد سمالی را وارد یا انتخاب کنید تا جزء‌به‌جزء به زبان ساده معنی شود.",
            "Ketik atau pilih baris metode, invoke, atau field Smali apa pun untuk menerjemahkan setiap simbolnya."
        ),
        "decoder_presets_label" to tr(
            "Tap a Sample Smali Signature",
            "পরীক্ষা করার জন্য একটি উদাহরণে ট্যাপ করুন",
            "جانچنے کے لیے کسی مثال پر ٹیپ کریں",
            "परखने के लिए किसी उदाहरण पर टैप करें",
            "اضغط على مثال جاهز للتجربة",
            "برای آزمایش روی یکی از نمونه‌ها بزنید",
            "Ketuk Contoh Baris Smali"
        ),
        "decoder_result_title" to tr(
            "Decoded Breakdown",
            "সহজ ভাষায় বিশ্লেষণ ফলাফল",
            "آسان الفاظ میں تجزیہ",
            "सरल शब्दों में विश्लेषण परिणाम",
            "نتيجة التفكيك والشرح",
            "نتیجه تجزیه به زبان ساده",
            "Hasil Penguraian"
        ),
        "decoder_part_kind" to tr("Statement Type", "লাইনের ধরন", "لائن کی قسم", "कथन का प्रकार", "نوع السطر", "نوع دستور", "Jenis Baris"),
        "decoder_part_flags" to tr("Modifiers / Opcode", "অপকোড / ফ্ল্যাগ", "اوپ کوڈ / فلیگز", "ऑपकोड / फ़्लैग", "التعليمة / المحددات", "آپ‌کد / پرچم‌ها", "Opcode / Modifier"),
        "decoder_part_owner" to tr("Target Class", "টার্গেট ক্লাস", "ہدف کلاس", "लक्ष्य क्लास", "الفئة المالكة", "کلاس هدف", "Kelas Target"),
        "decoder_part_name" to tr("Member Name", "মেথড / ফিল্ড নাম", "میتھڈ / فیلڈ نام", "मेथड / फ़ील्ड नाम", "اسم الدالة / الحقل", "نام متد / فیلد", "Nama Anggota"),
        "decoder_part_params" to tr("Input Parameters", "ইনপুট প্যারামিটার", "ان پٹ پیرامیٹرز", "इनपुट पैरामीटर", "المعاملات المدخلة", "پارامترهای ورودی", "Parameter Input"),
        "decoder_part_return" to tr("Return / Value Type", "রিটার্ন / ডেটা টাইপ", "ریٹرن / ڈیٹا ٹائپ", "रिटर्न / डेटा टाइप", "نوع الإرجاع / القيمة", "نوع خروجی / داده", "Tipe Return / Nilai"),
        "decoder_part_registers" to tr("Registers Used", "ব্যবহৃত রেজিস্টার", "استعمال شدہ رجسٹرز", "प्रयुक्त रजिस्टर", "المسجلات المستخدمة", "رجسترهای استفاده‌شده", "Register Digunakan"),
        "decoder_none" to tr("None (0 parameters)", "কোনো প্যারামিটার নেই", "کوئی نہیں", "कोई नहीं", "لا يوجد", "ندارد", "Tidak ada"),

        // Studio Lab & Comparison
        "lab_title" to tr("Interactive Bytecode Lab", "ইন্টারঅ্যাক্টিভ স্মালি ল্যাব", "انٹرایکٹو بائٹ کوڈ لیب", "इंटरैक्टिव स्माली लैब", "مختبر البايت كود التفاعلي", "آزمایشگاه تعاملی سمالی", "Lab Bytecode Interaktif"),
        "studio_title" to tr(
            "Java ↔ Kotlin ↔ Smali Compare",
            "জাভা ↔ কোটলিন ↔ স্মালি কোড তুলনা",
            "جاوا ↔ کوٹلن ↔ سمالی کوڈ موازنہ",
            "जावा ↔ कोटलिन ↔ स्माली कोड तुलना",
            "مقارنة شيفرة جافا وكوتلن وسمالي",
            "مقایسه کد جاوا، کاتلین و سمالی",
            "Perbandingan Java ↔ Kotlin ↔ Smali"
        ),
        "studio_desc" to tr(
            "Inspect how high-level Java and Kotlin compile line-by-line into Dalvik Smali instructions.",
            "জাভা এবং কোটলিন কোড কম্পাইল হয়ে কীভাবে লাইন-বাই-লাইন স্মালি কোডে রূপান্তরিত হয় তা পাশাপাশি দেখুন।",
            "دیکھیں کہ جاوا اور کوٹلن کوڈ کس طرح لائن بہ لائن سمالی بائٹ کوڈ میں تبدیل ہوتا ہے۔",
            "देखें कि जावा और कोटलिन कोड कैसे लाइन-दर-लाइन स्माली बाइटकोड में बदलता है।",
            "شاهد كيف تتحول شيفرة جافا وكوتلن سطراً بسطر إلى تعليمات سمالي التنفيذية.",
            "ببینید کدهای جاوا و کاتلین چگونه خط‌به‌خط به دستورات اسمبلی سمالی تبدیل می‌شوند.",
            "Lihat bagaimana Java dan Kotlin dikompilasi baris demi baris menjadi Smali."
        ),
        "view_all" to tr("All 3", "সবগুলো একসাথে", "تینوں ایک ساتھ", "तीनों एक साथ", "الكل معاً", "هر سه باهم", "Semua 3"),
        "view_java" to tr("Java", "জাভা", "جاوا", "जावा", "جافا", "جاوا", "Java"),
        "view_kotlin" to tr("Kotlin", "কোটলিন", "کوٹلن", "कोटलिन", "كوتلن", "کاتلین", "Kotlin"),
        "view_smali" to tr("Smali", "স্মালি", "سمالی", "स्माली", "سمالي", "سمالی", "Smali"),
        "breakdown_header" to tr(
            "Line-by-Line Bytecode Breakdown",
            "প্রতিটি স্মালি লাইনের বিস্তারিত ব্যাখ্যা",
            "سمالی کی ہر لائن کی تفصیلی وضاحت",
            "प्रत्येक स्माली लाइन की विस्तृत व्याख्या",
            "شرح تفصيلي لكل سطر في البايت كود",
            "کالبدشکافی خط‌به‌خط دستورات سمالی",
            "Uraian Bytecode Baris demi Baris"
        ),
        "reg_badge_fmt" to tr("Reg: %1\$s", "রেজিস্টার: %1\$s", "رجسٹر: %1\$s", "रजिस्टर: %1\$s", "المسجل: %1\$s", "رجستر: %1\$s", "Reg: %1\$s"),
        "internal_exec_title" to tr(
            "Under the Hood in Android ART",
            "অ্যান্ড্রয়েড ART রানটাইমের ভেতরে যা ঘটে",
            "اینڈرائیڈ ART رن ٹائم کے اندر کیا ہوتا ہے",
            "एंड्रॉइड ART रनटाइम के भीतर क्या होता है",
            "ماذا يحدث داخلياً في محرك أندرويد ART",
            "درون موتور اجرایی ART اندروید چه می‌گذرد",
            "Apa yang Terjadi di Dalam Android ART"
        ),

        // Register Visualizer
        "visualizer_title" to tr(
            "Dalvik Register Memory Visualizer",
            "ডালভিক রেজিস্টার মেমোরি ভিজ্যুয়ালাইজার",
            "ڈالوک رجسٹر میموری ویژولائزر",
            "डाल्विक रजिस्टर मेमोरी विज़ुअलाइज़र",
            "محاكي ذاكرة مسجلات دالفيك",
            "شبیه‌ساز زنده حافظه رجسترها",
            "Visualisasi Memori Register Dalvik"
        ),
        "visualizer_desc" to tr(
            "Step forward and backward through bytecode execution and watch each 32-bit register update live.",
            "প্রতিটি ধাপে ক্লিক করে দেখুন স্মালি কোড চলার সময় কীভাবে v0, v1, p0 রেজিস্টারের ভেতরের মান পরিবর্তন হয়।",
            "ہر قدم پر کلک کر کے دیکھیں کہ سمالی کوڈ چلتے وقت v0، v1، p0 رجسٹرز کی قدریں کیسے بدلتی ہیں۔",
            "प्रत्येक चरण पर क्लिक करके देखें कि स्माली कोड चलते समय v0, v1, p0 रजिस्टरों के मान कैसे बदलते हैं।",
            "تنقل خطوة بخطوة عبر تنفيذ التعليمات وشاهد تغير قيم مسجلات 32-بت مباشرة.",
            "گام‌به‌گام کد را اجرا کنید و تغییر زنده مقادیر درون رجسترهای v0، v1 و p0 را ببینید.",
            "Jalankan langkah demi langkah dan amati perubahan nilai setiap register secara langsung."
        ),
        "step_of_fmt" to tr(
            "Step %1\$s of %2\$s",
            "ধাপ %1\$s / %2\$s",
            "مرحلہ %1\$s از %2\$s",
            "चरण %1\$s / %2\$s",
            "الخطوة %1\$s من %2\$s",
            "گام %1\$s از %2\$s",
            "Langkah %1\$s dari %2\$s"
        ),
        "next_step" to tr("Next Step", "পরের ধাপ", "اگلا قدم", "अगला चरण", "الخطوة التالية", "گام بعدی", "Langkah Lanjut"),
        "sim_completed" to tr("Done ✓", "সম্পন্ন ✓", "مکمل ✓", "पूर्ण ✓", "انتهى ✓", "پایان ✓", "Selesai ✓"),
        "reg_bank_header" to tr(
            "Live Virtual Register Frame",
            "লাইভ ভার্চুয়াল রেজিস্টার মেমোরি স্লট",
            "لائیو ورچوئل رجسٹر میموری",
            "लाइव वर्चुअल रजिस्टर मेमोरी स्लॉट",
            "إطار المسجلات الافتراضية الحي",
            "وضعیت زنده قاب رجسترها",
            "Frame Register Virtual Langsung"
        ),
        "slot_param" to tr("Parameter register", "প্যারামিটার রেজিস্টার", "پیرامیٹر رجسٹر", "पैरामीटर रजिस्टर", "مسجل معامل", "رجستر پارامتر", "Register parameter"),
        "slot_local" to tr("Local register", "লোকাল রেজিস্টার", "لوکل رجسٹر", "लोकल रजिस्टर", "مسجل محلي", "رجستر محلی", "Register lokal"),
        "slot_frame" to tr("Runtime frame", "রানটাইম মেমোরি", "رن ٹائم فریم", "रनटाइम फ़्रेम", "حالة الإطار", "وضعیت حافظه", "Status frame"),
        "full_snippet_header" to tr(
            "Complete Method Snippet",
            "সম্পূর্ণ স্মালি মেথড কোড",
            "مکمل سمالی میتھڈ کوڈ",
            "संपूर्ण स्माली मेथड कोड",
            "شيفرة الدالة الكاملة",
            "کد کامل متد سمالی",
            "Potongan Kode Metode Lengkap"
        ),

        // Sandbox Playground
        "playground_title" to tr(
            "Smali Verifier Sandbox",
            "স্মালি কোড এডিটর ও ভেরিফায়ার স্যান্ডবক্স",
            "سمالی کوڈ ایڈیٹر اور ویریفائر",
            "स्माली कोड एडिटर और वेरिफ़ायर",
            "محرر وفاحص شيفرة سمالي",
            "ویرایشگر و بررسی‌کننده کد سمالی",
            "Sandbox Editor & Verifier Smali"
        ),
        "playground_desc" to tr(
            "Write or edit Smali methods and instantly verify register bounds (.locals), labels, and return rules.",
            "নিজে স্মালি কোড লিখুন বা টেমপ্লেট লোড করে পরীক্ষা করুন—রেজিস্টার সীমা (.locals), লেবেল ও মেথড ত্রুটি সাথে সাথে ধরা পড়বে।",
            "خود سمالی کوڈ لکھیں یا ٹیمپلیٹ لوڈ کریں—رجسٹر کی حدود، لیبلز اور غلطیوں کی فوری جانچ کریں۔",
            "स्वयं स्माली कोड लिखें या टेम्पलेट लोड करें—रजिस्टर सीमा (.locals), लेबल और त्रुटियों की तुरंत जांच करें।",
            "اكتب أو عدل دوال سمالي وافحص حدود المسجلات والعلامات البرمجية والأخطاء فوراً.",
            "کد سمالی بنویسید یا قالب آماده بارگذاری کنید تا محدوده رجسترها و برچسب‌ها فوراً بررسی شود.",
            "Tulis atau edit metode Smali dan verifikasi batas register (.locals) serta label secara instan."
        ),
        "playground_badge" to tr("Static Verifier", "লাইভ ভেরিফায়ার", "لائیو ویریفائر", "लाइव वेरिफ़ायर", "فاحص فوري", "بررسی‌کننده زنده", "Verifier Statis"),
        "load_templates_label" to tr(
            "Quick Templates",
            "রেডিমেড কোড টেমপ্লেট লোড করুন",
            "تیار کوڈ ٹیمپلیٹس",
            "तैयार कोड टेम्पलेट्स",
            "قوالب جاهزة سريعة",
            "قالب‌های آماده کد",
            "Templat Kode Cepat"
        ),
        "editor_label" to tr(
            "Smali Method Editor",
            "স্মালি কোড এডিটর",
            "سمالی کوڈ ایڈیٹر",
            "स्माली कोड एडिटर",
            "محرر شيفرة سمالي",
            "ویرایشگر کد سمالی",
            "Editor Kode Smali"
        ),
        "analyze_btn" to tr("Verify", "যাচাই করুন", "جانچیں", "जांचें", "فحص", "بررسی کد", "Periksa"),
        "verify_passed" to tr(
            "Bytecode Verification Passed",
            "স্মালি কোডটি সম্পূর্ণ সঠিক!",
            "سمالی کوڈ بالکل درست ہے!",
            "स्माली कोड बिल्कुल सही है!",
            "اجتازت الشيفرة الفحص بنجاح!",
            "کد سمالی کاملاً معتبر است!",
            "Verifikasi Bytecode Berhasil!"
        ),
        "verify_failed" to tr(
            "Dalvik Verifier Errors Detected",
            "স্মালি কোডে ত্রুটি পাওয়া গেছে!",
            "سمالی کوڈ میں غلطیاں ملی ہیں!",
            "स्माली कोड में त्रुटियां मिलीं!",
            "تم اكتشاف أخطاء في الشيفرة!",
            "خطا در کد سمالی شناسایی شد!",
            "Terdeteksi Kesalahan Verifier!"
        ),
        "locals_stat" to tr(".locals: %1\$s", ".locals: %1\$s", ".locals: %1\$s", ".locals: %1\$s", ".locals: %1\$s", ".locals: %1\$s", ".locals: %1\$s"),
        "highest_reg_stat" to tr("Max Reg: %1\$s", "সর্বোচ্চ রেজিস্টার: %1\$s", "زیادہ رجسٹر: %1\$s", "अधिकतम रजिस्टर: %1\$s", "أعلى مسجل: %1\$s", "بیشترین رجستر: %1\$s", "Reg Maks: %1\$s"),
        "labels_stat" to tr("Labels: %1\$s", "লেবেল: %1\$s", "لیبلز: %1\$s", "लेबल: %1\$s", "العلامات: %1\$s", "برچسب‌ها: %1\$s", "Label: %1\$s"),
        "none_label" to tr("None", "নেই", "نہیں", "नहीं", "لا يوجد", "ندارد", "Tidak ada"),
        "diagnostics_header" to tr(
            "Diagnostics & Suggested Fixes",
            "ত্রুটির বিবরণ ও সমাধানের উপায়",
            "غلطیوں کی تفصیل اور حل",
            "त्रुटि विवरण और समाधान",
            "التشخيص والحلول المقترحة",
            "جزئیات خطا و راه‌حل پیشنهادی",
            "Diagnostik & Saran Perbaikan"
        ),
        "line_fmt" to tr("Line %1\$s: %2\$s", "লাইন %1\$s: %2\$s", "لائن %1\$s: %2\$s", "लाइन %1\$s: %2\$s", "السطر %1\$s: %2\$s", "خط %1\$s: %2\$s", "Baris %1\$s: %2\$s"),
        "fix_prefix" to tr("Fix: %1\$s", "সমাধান: %1\$s", "حل: %1\$s", "समाधान: %1\$s", "الحل: %1\$s", "راه‌حل: %1\$s", "Solusi: %1\$s"),

        // Quiz Arena
        "quiz_subtitle" to tr(
            "Test your Dalvik register, opcode, and bytecode verification skills",
            "স্মালি অপকোড, রেজিস্টার ও বাইটকোড সম্পর্কে আপনার দক্ষতা যাচাই করুন",
            "سمالی اوپ کوڈز، رجسٹرز اور بائٹ کوڈ میں اپنی مہارت آزمائیں",
            "स्माली ऑपकोड, रजिस्टर और बाइटकोड में अपने कौशल का परीक्षण करें",
            "اختبر مهارتك في المسجلات والتعليمات وفحص البايت كود",
            "مهارت خود را در آپ‌کدها، رجسترها و تحلیل کد سمالی بسنجید",
            "Uji pemahaman register, opcode & verifikasi bytecode Anda"
        ),
        "question_of_fmt" to tr(
            "Challenge %1\$s of %2\$s",
            "প্রশ্ন %1\$s / %2\$s",
            "سوال %1\$s از %2\$s",
            "प्रश्न %1\$s / %2\$s",
            "السؤال %1\$s من %2\$s",
            "پرسش %1\$s از %2\$s",
            "Soal %1\$s dari %2\$s"
        ),
        "score_summary_fmt" to tr(
            "%1\$s Correct • %2\$s Answered",
            "%1\$sটি সঠিক • %2\$sটি উত্তর দেওয়া হয়েছে",
            "%1\$s درست • %2\$s حل شدہ",
            "%1\$s सही • %2\$s हल किए",
            "%1\$s صحيحة • %2\$s مجابة",
            "%1\$s صحیح • %2\$s پاسخ داده",
            "%1\$s Benar • %2\$s Dijawab"
        ),
        "diff_EASY" to tr("Beginner", "সহজ", "آسان", "आसान", "سهل", "آسان", "Mudah"),
        "diff_MEDIUM" to tr("Intermediate", "মধ্যম", "درمیانہ", "मध्यम", "متوسط", "متوسط", "Menengah"),
        "diff_HARD" to tr("Advanced", "কঠিন", "مشکل", "कठिन", "متقدم", "پیشرفته", "Sulit"),
        "diff_EXPERT" to tr("Expert", "এক্সপার্ট", "ماہرانہ", "एक्सपर्ट", "خبير", "تخصصی", "Mahir"),
        "qtype_MULTIPLE_CHOICE" to tr("Concept Check", "তাত্ত্বিক প্রশ্ন", "تصوراتی سوال", "अवधारणा प्रश्न", "اختيار متعدد", "پرسش مفهومی", "Pilihan Ganda"),
        "qtype_FIND_ERROR" to tr("Find the Bug", "কোডের ভুল ধরুন", "غلطی تلاش کریں", "त्रुटि पहचानें", "اكتشف الخطأ", "کشف خطای کد", "Temukan Bug"),
        "qtype_PREDICT_OUTPUT" to tr("Predict Output", "আউটপুট বলুন", "آؤٹ پٹ بتائیں", "आउटपुट बताएं", "توقع النتيجة", "پیش‌بینی خروجی", "Tebak Output"),
        "qtype_REGISTER_TRACE" to tr("Register Trace", "রেজিস্টার ট্রেস", "رجسٹر ٹریس", "रजिस्टर ट्रेस", "تتبع المسجلات", "ردیابی رجستر", "Lacak Register"),
        "submit_answer" to tr("Check Answer", "উত্তর যাচাই করুন", "جواب چیک کریں", "उत्तर जांचें", "تحقق من الإجابة", "بررسی پاسخ", "Periksa Jawaban"),
        "next_challenge" to tr("Next Question", "পরবর্তী প্রশ্ন", "اگلا سوال", "अगला प्रश्न", "السؤال التالي", "پرسش بعدی", "Soal Berikutnya"),
        "quiz_correct_banner" to tr("Spot On! +25 XP", "অসাধারণ! সঠিক উত্তর (+২৫ XP)", "شاندار! درست جواب (+25 XP)", "शानदार! सही उत्तर (+25 XP)", "إجابة صحيحة! +25 نقطة", "آفرین! پاسخ درست (+۲۵ امتیاز)", "Tepat Sekali! +25 XP"),
        "quiz_wrong_banner" to tr("Not Quite (+5 XP for trying)", "সঠিক হয়নি (+৫ XP চেষ্টার জন্য)", "درست نہیں (+5 XP کوشش پر)", "सही नहीं (+5 XP प्रयास के लिए)", "إجابة غير دقيقة (+5 للمحاولة)", "نادرست بود (+۵ امتیاز تلاش)", "Kurang Tepat (+5 XP)"),
        "trap_analysis_prefix" to tr("Why others fail: %1\$s", "কেন অন্য অপশন ভুল: %1\$s", "دیگر آپشنز کیوں غلط ہیں: %1\$s", "अन्य विकल्प गलत क्यों हैं: %1\$s", "لماذا الخيارات الأخرى خاطئة: %1\$s", "چرا سایر گزینه‌ها غلط هستند: %1\$s", "Mengapa opsi lain salah: %1\$s"),

        // Settings & Dialogs
        "settings_subtitle" to tr(
            "Customize language, iOS appearance, and offline study data",
            "ভাষা পরিবর্তন, আইফোন ডার্ক/লাইট থিম এবং অফলাইন ডাটা নিয়ন্ত্রণ",
            "زبان کی تبدیلی، تھیم اور آف لائن ڈیٹا کی ترتیبات",
            "भाषा बदलाव, डार्क/लाइट थीम और ऑफ़लाइन डेटा सेटिंग्स",
            "تخصيص اللغة والمظهر وبيانات الدراسة دون اتصال",
            "تغییر زبان، پوسته تاریک/روشن و مدیریت داده‌های آفلاین",
            "Sesuaikan bahasa, tampilan tema & data belajar offline"
        ),
        "settings_sec_appearance" to tr("APPEARANCE & LANGUAGE", "ভাষা ও অ্যাপের রূপ", "زبان اور ظاہری شکل", "भाषा और दिखावट", "اللغة والمظهر", "زبان و ظاهر برنامه", "TAMPILAN & BAHASA"),
        "settings_sec_data" to tr("OFFLINE PROGRESS & STORAGE", "অফলাইন অগ্রগতি ও স্টোরেজ", "آف لائن پیشرفت اور اسٹوریج", "ऑफ़लाइन प्रगति और स्टोरेज", "التقدم والتخزين المحلي", "پیشرفت و حافظه آفلاین", "PROGRES & PENYIMPANAN"),
        "settings_sec_about" to tr("ABOUT ACADEMY", "অ্যাপ সম্পর্কে", "ایپ کے بارے میں", "ऐप के बारे में", "حول الأكاديمية", "درباره برنامه", "TENTANG AKADEMI"),
        "settings_lang_title" to tr("App Language", "অ্যাপের ভাষা", "ایپ کی زبان", "ऐप की भाषा", "لغة التطبيق", "زبان برنامه", "Bahasa Aplikasi"),
        "settings_theme_title" to tr("Display Appearance", "ডিসপ্লে থিম মোড", "ڈسپلے تھیم موڈ", "डिस्प्ले थीम मोड", "مظهر الشاشة", "حالت نمایش صفحه", "Mode Tampilan"),
        "theme_dark_label" to tr("Cupertino Midnight (Dark)", "ডার্ক মোড (Cupertino Midnight)", "ڈارک موڈ (Midnight)", "डार्क मोड (Midnight)", "الوضع الداكن الفاخر", "حالت تاریک (Midnight)", "Mode Gelap (Midnight)"),
        "theme_light_label" to tr("Cupertino Pearl (Light)", "লাইট মোড (Cupertino Pearl)", "لائٹ موڈ (Pearl)", "लाइट मोड (Pearl)", "الوضع الفاتح النقي", "حالت روشن (Pearl)", "Mode Terang (Pearl)"),
        "settings_db_title" to tr("Offline Study Records", "অফলাইন লার্নিং ডাটাবেজ", "آف لائن اسٹڈی ریکارڈز", "ऑफ़लाइन अध्ययन डेटाबेस", "سجلات الدراسة المحلية", "پایگاه داده آفلاین", "Catatan Belajar Offline"),
        "settings_db_subtitle_fmt" to tr(
            "%1\$s completed • %2\$s bookmarked",
            "%1\$sটি পাঠ সম্পন্ন • %2\$sটি বুকমার্ক করা",
            "%1\$s مکمل • %2\$s بک مارک",
            "%1\$s पूर्ण • %2\$s बुकमार्क",
            "%1\$s مكتمل • %2\$s محفوظ",
            "%1\$s تکمیل‌شده • %2\$s نشان‌شده",
            "%1\$s selesai • %2\$s disimpan"
        ),
        "settings_reset_title" to tr("Reset Study Progress", "শেখার অগ্রগতি রিসেট করুন", "پیشرفت ری سیٹ کریں", "सीखने की प्रगति रीसेट करें", "إعادة ضبط التقدم", "بازنشانی پیشرفت آموزشی", "Atur Ulang Progres"),
        "settings_reset_subtitle" to tr("Reset XP, completed lessons & quiz stats", "পয়েন্ট, সম্পন্ন পাঠ ও কুইজ স্কোর শুরু থেকে সেট করুন", "پوائنٹس اور کوئز اسکور صفر کریں", "अंक, पूर्ण पाठ और क्विज़ स्कोर रीसेट करें", "مسح النقاط والدروس المكتملة ونتائج الاختبار", "پاک کردن امتیازها و شروع از ابتدا", "Hapus XP, pelajaran selesai & skor kuis"),
        "about_title" to tr("Smali Learn Pro • Studio Edition", "স্মালি লার্ন প্রো • স্টুডিও সংস্করণ", "سمالی لرن پرو • اسٹوڈیو ایڈیشن", "स्माली लर्न प्रो • स्टूडियो संस्करण", "سمالي ليرن برو • إصدار الاستوديو", "سمالی لرن پرو • نسخه استودیو", "Smali Learn Pro • Edisi Studio"),
        "about_body" to tr(
            "Version 2.0 • Crafted with precision to make Dalvik bytecode, Smali assembly, register tracing, and Android engineering effortless in 7 languages.",
            "ভার্সন ২.০ • ডালভিক বাইটকোড, স্মালি ল্যাঙ্গুয়েজ, রেজিস্টার ট্রেসিং এবং অ্যান্ড্রয়েড ইঞ্জিনিয়ারিং ৭টি ভাষায় একদম সহজ ও গোছানোভাবে শেখার প্রফেশনাল প্ল্যাটফর্ম।",
            "ورژن 2.0 • ڈالوک بائٹ کوڈ، سمالی اسمبلی اور اینڈرائیڈ انجینئرنگ کو 7 زبانوں میں انتہائی منظم اور آسان انداز میں سیکھنے کا پلیٹ فارم۔",
            "संस्करण 2.0 • डाल्विक बाइटकोड, स्माली असेंबली, रजिस्टर ट्रेसिंग और एंड्रॉइड इंजीनियरिंग को 7 भाषाओं में सरलता से सीखने का पेशेवर प्लेटफ़ॉर्म।",
            "الإصدار 2.0 • منصة احترافية مصممة بإتقان لتعلم بايت كود دالفيك ولغة سمالي وهندسة أندرويد بسبع لغات.",
            "نسخه ۲.۰ • پلتفرم حرفه‌ای برای یادگیری عمیق و منظم زبان سمالی، رجسترهای دالویک و مهندسی اندروید به ۷ زبان.",
            "Versi 2.0 • Dirancang presisi untuk mempelajari bytecode Dalvik, bahasa Smali, dan rekayasa Android dalam 7 bahasa."
        ),
        "reset_dialog_title" to tr("Reset All Progress?", "সব অগ্রগতি রিসেট করবেন?", "کیا تمام پیشرفت ری سیٹ کریں؟", "क्या सभी प्रगति रीसेट करें?", "هل تريد إعادة ضبط التقدم؟", "آیا پیشرفت بازنشانی شود؟", "Atur Ulang Semua Progres?"),
        "reset_dialog_body" to tr(
            "Your XP, completed module checkmarks, and quiz counters will be reset to zero.",
            "আপনার অর্জিত পয়েন্ট (XP), সম্পন্ন পাঠের টিক চিহ্ন এবং কুইজের হিসাব শূন্য হয়ে যাবে।",
            "آپ کے پوائنٹس، مکمل اسباق اور کوئز کا ریکارڈ صفر ہو جائے گا۔",
            "आपके अंक (XP), पूर्ण पाठ चिह्न और क्विज़ रिकॉर्ड शून्य हो जाएंगे।",
            "سيتم تصفير نقاط الخبرة وعلامات إكمال الدروس وإحصائيات الاختبارات.",
            "امتیازها، تیک تکمیل درس‌ها و آمار آزمون‌های شما صفر خواهد شد.",
            "XP, tanda selesai modul, dan skor kuis Anda akan dikembalikan ke nol."
        ),
        "cancel_btn" to tr("Cancel", "বাতিল", "منسوخ", "रद्द करें", "إلغاء", "انصراف", "Batal"),
        "reset_confirm_btn" to tr("Reset Now", "রিসেট করুন", "ری سیٹ کریں", "रीसेट करें", "إعادة ضبط", "بازنشانی", "Atur Ulang"),
        "select_language" to tr("Select Language", "ভাষা নির্বাচন করুন", "زبان منتخب کریں", "भाषा चुनें", "اختر اللغة", "انتخاب زبان", "Pilih Bahasa"),
        "language_dialog_subtitle" to tr(
            "100% of menus, lessons, opcode explanations, labs, and quizzes switch instantly.",
            "অ্যাপের প্রতিটি মেনু, পাঠ, স্মালি ব্যাখ্যা, অপকোড ডিকশনারি, ল্যাব এবং কুইজ সম্পূর্ণভাবে নির্বাচিত ভাষায় পরিবর্তিত হবে।",
            "ایپ کا ہر مینو، سبق، سمالی وضاحت، لغت، لیب اور کوئز مکمل طور پر منتخب زبان میں بدل جائے گا۔",
            "ऐप का हर मेनू, पाठ, स्माली व्याख्या, डिक्शनरी, लैब और क्विज़ पूरी तरह चुनी गई भाषा में बदल जाएगा।",
            "ستتحول جميع القوائم والدروس وشروحات التعليمات والمختبرات والاختبارات بالكامل فوراً إلى لغتك.",
            "تمامی منوها، درس‌ها، توضیحات آپ‌کدها، آزمایشگاه و آزمون‌ها کاملاً به زبان انتخابی تغییر می‌کنند.",
            "100% menu, pelajaran, penjelasan opcode, lab & kuis langsung berubah ke bahasa pilihan."
        ),
        "confirm_language" to tr("Apply Language", "ভাষা প্রয়োগ করুন", "زبان لاگو کریں", "भाषा लागू करें", "تطبيق اللغة", "اعمال زبان", "Terapkan Bahasa"),

        // Java Programming Zero-to-Pro Master Hub
        "java_hub_banner_badge" to tr(
            "COMPLETE JAVA PROGRAMMING ACADEMY",
            "সম্পূর্ণ জাভা প্রোগ্রামিং মাস্টার গাইড",
            "مکمل جاوا پروگرامنگ ماسٹر گائیڈ",
            "संपूर्ण जावा प्रोग्रामिंग मास्टर गाइड",
            "أكاديمية برمجة جافا المتكاملة",
            "دانشنامه کامل برنامه‌نویسی جاوا",
            "AKADEMI PEMROGRAMAN JAVA LENGKAP"
        ),
        "java_hub_banner_title" to tr(
            "Master Java Programming from Zero to Pro",
            "জাভা প্রোগ্রামিং শিখুন একদম জিরো থেকে অ্যাডভান্স পর্যন্ত",
            "جاوا پروگرامنگ صفر سے ایڈوانس تک مکمل سیکھیں",
            "जावा प्रोग्रामिंग शून्य से एडवांस तक पूरी तरह सीखें",
            "أتقن برمجة جافا بالكامل من الصفر إلى الاحتراف",
            "برنامه‌نویسی جاوا را از صفر تا پیشرفته کامل بیاموزید",
            "Kuasai Pemrograman Java dari Nol hingga Mahir"
        ),
        "java_hub_banner_subtitle" to tr(
            "16 Deep Modules • 32+ Java Keywords • Stack vs Heap Visualizer • OOP Cheat Sheets • Live Java→Smali Lab",
            "১৬টি পূর্ণাঙ্গ অধ্যায় • ৩২+ জাভা কীওয়ার্ড • Stack ও Heap মেমোরি সিমুলেটর • OOP চিটশিট • লাইভ জাভা→স্মালি রূপান্তর",
            "16 تفصیلی اسباق • 32+ جاوا کی ورڈز • اسٹیک اور ہیپ میموری ویژولائزر • OOP چیٹ شیٹ • لائیو جاوا→سمالی ٹول",
            "16 विस्तृत अध्याय • 32+ जावा कीवर्ड • Stack व Heap मेमोरी सिम्युलेटर • OOP चीट शीट • लाइव जावा→स्माली लैब",
            "16 وحدة شاملة • +32 كلمة مفتاحية • محاكي ذاكرة Stack و Heap • ملخصات OOP • محول جافا إلى سمالي الحي",
            "۱۶ فصل کامل • ۳۲+ کلیدواژه جاوا • شبیه‌ساز حافظه Stack و Heap • خلاصه OOP • مبدل زنده جاوا به سمالی",
            "16 Modul Mendalam • 32+ Kata Kunci Java • Visualisasi Stack/Heap • Cheat Sheet OOP • Lab Java→Smali"
        ),
        "java_hub_cta" to tr(
            "Open Java Master Hub",
            "জাভা মাস্টার গাইড খুলুন",
            "جاوا ماسٹر گائیڈ کھولیں",
            "जावा मास्टर गाइड खोलें",
            "افتح مركز جافا المتكامل",
            "باز کردن راهنمای جامع جاوا",
            "Buka Pusat Master Java"
        ),

        "java_tab_roadmap" to tr("Zero→Pro", "জিরো→প্রো", "صفر→پرو", "शून्य→प्रो", "المسار", "صفر تا پرو", "Nol→Pro"),
        "java_tab_keywords" to tr("Keywords", "কীওয়ার্ডসমূহ", "کی ورڈز", "कीवर्ड", "الكلمات", "کلیدواژه‌ها", "Kata Kunci"),
        "java_tab_memory" to tr("OOP & RAM", "মেমোরি ও চিটশিট", "میموری اور قواعد", "मेमोरी व नियम", "الذاكرة والقواعد", "حافظه و قواعد", "RAM & OOP"),
        "java_tab_compiler" to tr("Java→Smali", "জাভা→স্মালি", "جاوا→سمالی", "जावा→स्माली", "جافا←سمالي", "جاوا←سمالی", "Java→Smali"),

        "java_search_hint" to tr(
            "Search Java keyword, API, Smali or meaning...",
            "জাভা কীওয়ার্ড, ক্লাস বা বাংলায় কাজ লিখে খুঁজুন...",
            "جاوا کی ورڈ، کلاس یا مفہوم تلاش کریں...",
            "जावा कीवर्ड, क्लास या अर्थ खोजें...",
            "ابحث عن الكلمة المفتاحية أو الفئة أو المعنى...",
            "جستجوی کلیدواژه جاوا، کلاس یا کاربرد...",
            "Cari kata kunci Java, kelas, atau kegunaan..."
        ),
        "all_keywords_fmt" to tr(
            "All (%1\$s)",
            "সবগুলো (%1\$s)",
            "تمام (%1\$s)",
            "सभी (%1\$s)",
            "الكل (%1\$s)",
            "همه (%1\$s)",
            "Semua (%1\$s)"
        ),
        "jcat_TYPES_VARS" to tr("Types & Vars", "ডেটা টাইপ ও ভেরিয়েবল", "ٹائپس اور متغیرات", "डेटा टाइप व वेरिएबल", "الأنواع والمتغيرات", "نوع داده و متغیر", "Tipe & Variabel"),
        "jcat_CONTROL_FLOW" to tr("Conditions & Loops", "শর্ত ও লুপ (if/for)", "شرائط اور لوپس", "शर्तें और लूप", "الشروط والحلقات", "شرط‌ها و حلقه‌ها", "Kondisi & Loop"),
        "jcat_OOP_CLASSES" to tr("OOP & Classes", "ক্লাস ও OOP", "کلاسز اور OOP", "क्लास और OOP", "الكائنات والوراثة", "کلاس و شی‌گرایی", "OOP & Kelas"),
        "jcat_MODIFIERS" to tr("Modifiers", "মডিফায়ার (public/static)", "موڈیفائرز", "मॉडिफ़ायर", "المحددات", "سطح دسترسی", "Modifier"),
        "jcat_ERRORS_THREADS" to tr("Errors & Threads", "এরর ও থ্রেড (try/lock)", "ایرر اور تھریڈز", "त्रुटि और थ्रेड", "الأخطاء والخيوط", "خطاها و نخ‌ها", "Error & Thread"),
        "jcat_COLLECTIONS_APIS" to tr("Collections & APIs", "লিস্ট, ম্যাপ ও API", "لسٹ اور میپ", "लिस्ट, मैप व API", "المجموعات و API", "لیست، مپ و API", "Koleksi & API"),

        "smali_equiv_label" to tr("Compiled Android Smali", "অ্যান্ড্রয়েডে কম্পাইল হওয়া স্মালি রূপ", "اینڈرائیڈ سمالی میں متبادل", "संकलित एंड्रॉइड स्माली रूप", "المكافئ بعد الترجمة إلى سمالي", "خروجی کامپایل‌شده در سمالی", "Hasil Kompilasi Smali"),
        "java_usage_label" to tr("Professional Java Example", "প্রফেশনাল জাভা কোড উদাহরণ", "پروفیشنل جاوا کوڈ کی مثال", "प्रोफेशनल जावा कोड उदाहरण", "مثال احترافي بلغة جافا", "نمونه کد حرفه‌ای جاوا", "Contoh Kode Java Profesional"),
        "empty_java_search_title" to tr("No matching Java keywords found", "কোনো জাভা কীওয়ার্ড পাওয়া যায়নি", "کوئی جاوا کی ورڈ نہیں ملا", "कोई जावा कीवर्ड नहीं मिला", "لم يتم العثور على كلمات مطابقة", "هیچ کلیدواژه‌ای یافت نشد", "Kata kunci Java tidak ditemukan"),
        "empty_java_search_sub" to tr("Try another search term or reset the category filter.", "অন্য কোনো শব্দ লিখে খুঁজুন অথবা ক্যাটাগরি ফিল্টার পরিবর্তন করুন।", "کوئی دوسرا لفظ آزمائیں یا فلٹر صاف کریں۔", "कोई दूसरा शब्द आज़माएं या फ़िल्टर हटाएं।", "جرب كلمة أخرى أو امسح التصفية.", "واژه دیگری را امتحان کنید یا فیلتر را بردارید.", "Coba kata lain atau hapus filter kategori."),

        "java_mem_title" to tr(
            "Interactive Java Stack vs Heap Memory Explorer",
            "ইন্টারঅ্যাক্টিভ জাভা Stack বনাম Heap মেমোরি ভিজ্যুয়ালাইজার",
            "انٹرایکٹو جاوا اسٹیک بمقابلہ ہیپ میموری ویژولائزر",
            "इंटरैक्टिव जावा Stack बनाम Heap मेमोरी विज़ुअलाइज़र",
            "مستكشف ذاكرة Stack مقابل Heap التفاعلي في جافا",
            "شبیه‌ساز تعاملی حافظه Stack در برابر Heap در جاوا",
            "Visualisasi Interaktif Memori Stack vs Heap Java"
        ),
        "java_mem_desc" to tr(
            "Tap a scenario below to see where Java stores local primitives vs `new` objects in RAM—and how Dalvik registers reference them.",
            "নিচের যেকোনো একটি দৃশ্যপটে ট্যাপ করে দেখুন জাভা কীভাবে লোকাল ভেরিয়েবল (Stack) এবং অবজেক্ট (Heap) র‍্যামে সাজিয়ে রাখে এবং কেন `==` ও `.equals()` আলাদা কাজ করে!",
            "نیچے کوئی منظر منتخب کریں اور دیکھیں کہ جاوا متغیرات (Stack) اور آبجیکٹس (Heap) کو میموری میں کیسے رکھتا ہے۔",
            "नीचे कोई परिदृश्य चुनें और देखें कि जावा लोकल मान (Stack) और ऑब्जेक्ट (Heap) को RAM में कैसे रखता है।",
            "اختر سيناريو أدناه لترى أين تخزن جافا المتغيرات المحلية مقارنة بالكائنات في الذاكرة الحية.",
            "یکی از حالت‌های زیر را انتخاب کنید تا ببینید جاوا متغیرها و اشیاء را چگونه در Stack و Heap ذخیره می‌کند.",
            "Pilih skenario di bawah untuk melihat bagaimana Java menyimpan primitif di Stack dan objek di Heap."
        ),
        "java_stack_title" to tr("STACK FRAME (Fast Local Slots / Registers)", "STACK মেমোরি (দ্রুত লোকাল ভেরিয়েবল ও রেজিস্টার)", "STACK فریم ( تیز لوکل سلاٹس)", "STACK मेमोरी (लोकल स्लॉट / रजिस्टर)", "ذاكرة STACK (المتغيرات المحلية السريعة)", "حافظه STACK (متغیرهای محلی سریع)", "MEMORI STACK (Slot Lokal Cepat)"),
        "java_heap_title" to tr("HEAP MEMORY (Objects Created with new)", "HEAP মেমোরি (new দিয়ে তৈরি আসল অবজেক্টসমূহ)", "HEAP میموری (آبجیکٹس کا ذخیرہ)", "HEAP मेमोरी (new से बने ऑब्जेक्ट)", "ذاكرة HEAP (الكائنات المنشأة بـ new)", "حافظه HEAP (اشیاء ساخته‌شده با new)", "MEMORI HEAP (Objek di RAM)"),
        "java_cheat_primitives" to tr(
            "1. All 8 Java Primitive Types & Bit Sizes",
            "১. জাভার ৮টি প্রিমিটিভ ডেটা টাইপ, মেমোরি সাইজ ও সীমা",
            "1. جاوا کی تمام 8 بنیادی ڈیٹا ٹائپس اور سائز",
            "1. जावा के सभी 8 प्रिमिटिव डेटा टाइप और मेमोरी आकार",
            "1. الأنواع الأساسية الثمانية في جافا وأحجامها",
            "۱. هشت نوع داده پایه جاوا و اندازه حافظه آن‌ها",
            "1. 8 Tipe Data Primitif Java & Ukuran Memori"
        ),
        "java_cheat_modifiers" to tr(
            "2. Java Access Modifiers Visibility Rules",
            "২. জাভা অ্যাক্সেস মডিফায়ার (কোথা থেকে কোনটি দেখা যায়)",
            "2. جاوا ایکسس موڈیفائرز کے قوانین",
            "2. जावा एक्सेस मॉडिफ़ायर दृश्यता नियम",
            "2. قواعد محددات الوصول والرؤية في جافا",
            "۲. جدول سطح دسترسی در جاوا (Access Modifiers)",
            "2. Aturan Visibilitas Access Modifier Java"
        ),
        "java_cheat_oop" to tr(
            "3. Golden Java Interview & Architecture Rules",
            "৩. প্রফেশনাল জাভা ও OOP-এর গোল্ডেন নিয়মাবলী",
            "3. پروفیشنل جاوا اور OOP کے سنہری اصول",
            "3. प्रोफेशनल जावा और OOP के सुनहरे नियम",
            "3. القواعد الذهبية في هندسة جافا والبرمجة الكائنية",
            "۳. قوانین طلایی شی‌گرایی و معماری حرفه‌ای جاوا",
            "3. Aturan Emas OOP & Arsitektur Java Profesional"
        ),

        "java_compiler_title" to tr(
            "Live Java → Smali Signature & Method Converter",
            "লাইভ জাভা → স্মালি কোড ও সিগনেচার কনভার্টার",
            "لائیو جاوا → سمالی سگنیچر اور میتھڈ کنورٹر",
            "लाइव जावा → स्माली सिग्नेचर और मेथड कन्वर्टर",
            "محول تواقيع ودوال جافا إلى سمالي المباشر",
            "مبدل زنده امضا و متدهای جاوا به کد سمالی",
            "Konverter Langsung Metode & Tanda Tangan Java → Smali"
        ),
        "java_compiler_desc" to tr(
            "Type or pick any Java method, field, or class header below—instantly inspect its parsed structure and exact Android DEX Smali output!",
            "নিচে যেকোনো জাভা মেথড, ভেরিয়েবল ফিল্ড বা ক্লাসের লাইন লিখুন অথবা রেডিমেড উদাহরণে ট্যাপ করুন—সাথে সাথে সেটির পূর্ণাঙ্গ ব্যাখ্যা এবং কম্পাইল হওয়া স্মালি কোড তৈরি হয়ে যাবে!",
            "نیچے کوئی بھی جاوا میتھڈ، فیلڈ یا کلاس لکھیں یا مثال منتخب کریں—فوراً اس کی وضاحت اور سمالی بائٹ کوڈ دیکھیں!",
            "नीचे कोई भी जावा मेथड, फ़ील्ड या क्लास हेडर लिखें या चुनें—तुरंत उसका विश्लेषण और सटीक एंड्रॉइड स्माली कोड देखें!",
            "اكتب أو اختر أي دالة أو حقل أو فئة بلغة جافا لترى تحليلها الدقيق وشيفرة سمالي المقابلة لها فوراً!",
            "هر متد، فیلد یا کلاس جاوا را در پایین بنویسید یا انتخاب کنید تا ساختار آن و کد دقیق سمالی فوراً تولید شود!",
            "Ketik atau pilih header metode, field, atau kelas Java untuk melihat struktur dan hasil bytecode Smali secara instan!"
        ),
        "java_presets_label" to tr(
            "Tap a Sample Java Declaration",
            "পরীক্ষা করার জন্য একটি জাভা উদাহরণে ট্যাপ করুন",
            "جانچنے کے لیے کسی جاوا مثال پر ٹیپ کریں",
            "परखने के लिए किसी जावा उदाहरण पर टैप करें",
            "اضغط على مثال جافا جاهز للتجربة",
            "برای آزمایش روی یکی از نمونه‌های جاوا بزنید",
            "Ketuk Contoh Deklarasi Java"
        ),
        "java_compiled_smali_label" to tr(
            "Generated Smali Bytecode Skeleton",
            "স্বয়ংক্রিয়ভাবে তৈরি স্মালি বাইটকোড",
            "خودکار تیار شدہ سمالی بائٹ کوڈ",
            "स्वचालित जनित स्माली बाइटकोड",
            "هيكل شيفرة سمالي المولد تلقائياً",
            "اسکلت کد سمالی تولیدشده به صورت خودکار",
            "Kerangka Bytecode Smali yang Dihasilkan"
        ),
        "java_decoded_summary_label" to tr(
            "Java Declaration Breakdown",
            "জাভা স্টেটমেন্টের সহজ বিশ্লেষণ",
            "جاوا لائن کا آسان تجزیہ",
            "जावा स्टेटमेंट का सरल विश्लेषण",
            "تفكيك وشرح تصريح جافا",
            "تجزیه و کالبدشکافی دستور جاوا",
            "Uraian Deklarasi Java"
        ),
        "course_search_hint" to tr(
            "Search lessons in this track...",
            "এই কোর্সের যেকোনো অধ্যায় বা টপিক খুঁজুন...",
            "اس کورس میں کوئی بھی سبق تلاش کریں...",
            "इस कोर्स का कोई भी अध्याय खोजें...",
            "ابحث عن درس في هذا المسار...",
            "جستجوی فصل یا موضوع در این دوره...",
            "Cari modul di jalur ini..."
        )
    )

    fun getString(key: String, language: AppLanguage): String {
        return translations[key]?.get(language)
            ?: translations[key]?.get(AppLanguage.ENGLISH)
            ?: key
    }

    fun formatString(key: String, language: AppLanguage, vararg args: Any): String {
        val raw = getString(key, language)
        val localizedArgs = args.map { arg ->
            localizeDigits(arg.toString(), language)
        }.toTypedArray()
        return try {
            String.format(raw, *localizedArgs)
        } catch (_: Exception) {
            raw
        }
    }
}

@Composable
fun localizedString(key: String): String {
    val currentLang = LocalAppLanguage.current
    return LocalizationManager.getString(key, currentLang)
}

@Composable
fun localizedFormat(key: String, vararg args: Any): String {
    val currentLang = LocalAppLanguage.current
    return LocalizationManager.formatString(key, currentLang, *args)
}
