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
    ARABIC("ar", "العربية", "مصر والشرق الأوسط", true, "🇪🇬"),
    DARI("fa_af", "دری", "افغانستان", true, "🇦🇫"),
    INDONESIAN("in", "Bahasa Indonesia", "Indonesia", false, "🇮🇩");

    val layoutDirection: LayoutDirection
        get() = if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
}

val LocalAppLanguage = compositionLocalOf { AppLanguage.ENGLISH }

object LocalizationManager {
    private const val PREF_NAME = "smali_learn_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    private const val KEY_FIRST_LAUNCH = "first_launch_done"

    fun getSavedLanguage(context: Context): AppLanguage {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val code = prefs.getString(KEY_LANGUAGE, AppLanguage.ENGLISH.code) ?: AppLanguage.ENGLISH.code
        return AppLanguage.entries.find { it.code == code } ?: AppLanguage.ENGLISH
    }

    fun saveLanguage(context: Context, language: AppLanguage) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, language.code).apply()
    }

    fun isFirstLaunch(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return !prefs.getBoolean(KEY_FIRST_LAUNCH, false)
    }

    fun setFirstLaunchCompleted(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, true).apply()
    }

    val translations: Map<String, Map<AppLanguage, String>> = mapOf(
        "app_title" to mapOf(
            AppLanguage.ENGLISH to "CODE & SMALI ACADEMY",
            AppLanguage.BENGALI to "কোড ও স্মালি একাডেমি",
            AppLanguage.URDU to "کوڈ اور سمالی اکیڈمی",
            AppLanguage.HINDI to "कोड और स्माली अकादमी",
            AppLanguage.ARABIC to "أكاديمية الكود وسمالي",
            AppLanguage.DARI to "اکادمی کد و سمالی",
            AppLanguage.INDONESIAN to "AKADEMI KODE & SMALI"
        ),
        "tagline" to mapOf(
            AppLanguage.ENGLISH to "Master Smali, Java, Kotlin, Python & Android Dev from Zero like a Pro.",
            AppLanguage.BENGALI to "জিরো থেকে বাচ্চাদের মতো সহজ করে স্মালি, জাভা, কোটলিন, পাইথন ও অ্যাপ তৈরি শিখুন।",
            AppLanguage.URDU to "سمالی، جاوا، کوٹلن، ازگر اور اینڈرائیڈ ایپ کو بچوں کی طرح آسان انداز میں سیکھیں۔",
            AppLanguage.HINDI to "शून्य से बच्चों की तरह सरल भाषा में स्माली, जावा, कोटलिन, पायथन और ऐप डेवलपमेंट सीखें।",
            AppLanguage.ARABIC to "تعلم سمالي، جافا، كوتلن، بايثون وتطوير أندرويد من الصفر بأسلوب ممتع وبسيط كالأطفال.",
            AppLanguage.DARI to "سمالی، جاوا، کاتلین، پایتون و ساخت اپلیکیشن اندروید را از صفر بیاموزید.",
            AppLanguage.INDONESIAN to "Kuasai Smali, Java, Kotlin, Python & Dev Android dari nol seperti pro."
        ),
        "nav_home" to mapOf(
            AppLanguage.ENGLISH to "Home",
            AppLanguage.BENGALI to "হোম",
            AppLanguage.URDU to "ہوم",
            AppLanguage.HINDI to "होम",
            AppLanguage.ARABIC to "الرئيسية",
            AppLanguage.DARI to "خانه",
            AppLanguage.INDONESIAN to "Beranda"
        ),
        "nav_tracks" to mapOf(
            AppLanguage.ENGLISH to "Tracks",
            AppLanguage.BENGALI to "কোর্স ট্র্যাক",
            AppLanguage.URDU to "ٹریکس",
            AppLanguage.HINDI to "ट्रैक",
            AppLanguage.ARABIC to "المسارات",
            AppLanguage.DARI to "مسیرها",
            AppLanguage.INDONESIAN to "Jalur Belajar"
        ),
        "nav_curriculum" to mapOf(
            AppLanguage.ENGLISH to "Curriculum",
            AppLanguage.BENGALI to "পাঠ্যক্রম",
            AppLanguage.URDU to "نصاب",
            AppLanguage.HINDI to "पाठ्यक्रम",
            AppLanguage.ARABIC to "المنهج",
            AppLanguage.DARI to "نصاب درسی",
            AppLanguage.INDONESIAN to "Kurikulum"
        ),
        "nav_compare" to mapOf(
            AppLanguage.ENGLISH to "Studio",
            AppLanguage.BENGALI to "স্টুডিও",
            AppLanguage.URDU to "اسٹوڈیو",
            AppLanguage.HINDI to "स्टूडियो",
            AppLanguage.ARABIC to "الاستوديو",
            AppLanguage.DARI to "استودیو",
            AppLanguage.INDONESIAN to "Studio"
        ),
        "nav_visualizer" to mapOf(
            AppLanguage.ENGLISH to "Registers",
            AppLanguage.BENGALI to "রেজিস্টার",
            AppLanguage.URDU to "رجسٹرز",
            AppLanguage.HINDI to "रजिस्टर्स",
            AppLanguage.ARABIC to "المسجلات",
            AppLanguage.DARI to "رجسترها",
            AppLanguage.INDONESIAN to "Register"
        ),
        "nav_playground" to mapOf(
            AppLanguage.ENGLISH to "Sandbox",
            AppLanguage.BENGALI to "স্যান্ডবক্স",
            AppLanguage.URDU to "سینڈ بکس",
            AppLanguage.HINDI to "सैंडबॉक्स",
            AppLanguage.ARABIC to "المختبر",
            AppLanguage.DARI to "جعبه شن",
            AppLanguage.INDONESIAN to "Sandbox"
        ),
        "nav_quiz" to mapOf(
            AppLanguage.ENGLISH to "Quiz",
            AppLanguage.BENGALI to "কুইজ",
            AppLanguage.URDU to "کوئز",
            AppLanguage.HINDI to "क्विज़",
            AppLanguage.ARABIC to "الاختبار",
            AppLanguage.DARI to "آزمون",
            AppLanguage.INDONESIAN to "Kuis"
        ),
        "nav_settings" to mapOf(
            AppLanguage.ENGLISH to "Settings",
            AppLanguage.BENGALI to "সেটিংস",
            AppLanguage.URDU to "ترتیبات",
            AppLanguage.HINDI to "सेटिंग्स",
            AppLanguage.ARABIC to "الإعدادات",
            AppLanguage.DARI to "تنظیمات",
            AppLanguage.INDONESIAN to "Pengaturan"
        ),

        // Track Titles & Descriptions
        "track_smali_title" to mapOf(
            AppLanguage.ENGLISH to "Smali Bytecode (Zero to Pro)",
            AppLanguage.BENGALI to "স্মালি ল্যাঙ্গুয়েজ (জিরো থেকে প্রো)",
            AppLanguage.URDU to "سمالی بائٹ کوڈ (صفر سے پرو)",
            AppLanguage.HINDI to "स्माली बाइटकोड (शून्य से प्रो)",
            AppLanguage.ARABIC to "لغة سمالي (من الصفر للاحتراف)",
            AppLanguage.DARI to "بایت‌کد سمالی (از صفر تا حرفه‌ای)",
            AppLanguage.INDONESIAN to "Bytecode Smali (Nol ke Pro)"
        ),
        "track_smali_desc" to mapOf(
            AppLanguage.ENGLISH to "Understand registers as toy boxes, opcodes as commands, and APK DEX internals.",
            AppLanguage.BENGALI to "রেজিস্টারকে খেলনার বাক্সের মতো বুঝে স্মালি কোড ও এপিকে গঠন সহজে আয়ত্ত করুন।",
            AppLanguage.URDU to "رجسٹرز کو کھلونوں کے ڈبے کی طرح سمجھیں اور سمالی کوڈ سیکھیں۔",
            AppLanguage.HINDI to "रजिस्टर को खिलौनों के डिब्बे की तरह समझें और स्माली सीखें।",
            AppLanguage.ARABIC to "افهم المسجلات كصناديق ألعاب والتعليمات كأوامر فك الشفرة وتعديل APK.",
            AppLanguage.DARI to "رجسترها را مانند جعبه اسباب‌بازی درک کنید و سمالی را یاد بگیرید.",
            AppLanguage.INDONESIAN to "Pahami register seperti kotak mainan dan opcode sebagai perintah modding."
        ),
        "track_java_title" to mapOf(
            AppLanguage.ENGLISH to "Java Programming (Zero to Pro)",
            AppLanguage.BENGALI to "জাভা প্রোগ্রামিং (জিরো থেকে প্রো)",
            AppLanguage.URDU to "جاوا پروگرامنگ (صفر سے پرو)",
            AppLanguage.HINDI to "जावा प्रोग्रामिंग (शून्य से प्रो)",
            AppLanguage.ARABIC to "برمجة جافا (من الصفر للاحتراف)",
            AppLanguage.DARI to "برنامه‌نویسی جاوا (از صفر تا حرفه‌ای)",
            AppLanguage.INDONESIAN to "Pemrograman Java (Nol ke Pro)"
        ),
        "track_java_desc" to mapOf(
            AppLanguage.ENGLISH to "Variables as labeled boxes, if-else as decision trees, loops, and OOP classes.",
            AppLanguage.BENGALI to "লেবেল লাগানো বাক্সের মতো ভেরিয়েবল, শর্তাবলী, লুপ ও অবজেক্ট ওরিয়েন্টেড প্রোগ্রামিং।",
            AppLanguage.URDU to "متغیرات، شرائط، لوپس اور آبجیکٹ اورینٹڈ پروگرامنگ بالکل آسان انداز میں۔",
            AppLanguage.HINDI to "वेरिएबल्स, शर्तें, लूप्स और ऑब्जेक्ट-ओरिएंटेड क्लासेस को आसानी से सीखें।",
            AppLanguage.ARABIC to "المتغيرات كصناديق معنونة، الشروط، الحلقات، والكائنات والبرمجة الشيئية.",
            AppLanguage.DARI to "متغیرها، شرط‌ها، حلقه‌ها و مفاهیم شی‌گرایی به زبان ساده.",
            AppLanguage.INDONESIAN to "Variabel, if-else, perulangan, dan OOP dengan analogi kehidupan nyata."
        ),
        "track_kotlin_title" to mapOf(
            AppLanguage.ENGLISH to "Kotlin for Android (Zero to Pro)",
            AppLanguage.BENGALI to "অ্যান্ড্রয়েডের জন্য কোটলিন (জিরো থেকে প্রো)",
            AppLanguage.URDU to "اینڈرائیڈ کے لیے کوٹلن (صفر سے پرو)",
            AppLanguage.HINDI to "एंड्रॉइड के लिए कोटलिन (शून्य से प्रो)",
            AppLanguage.ARABIC to "لغة كوتلن لأندرويد (من الصفر للاحتراف)",
            AppLanguage.DARI to "کاتلین برای اندروید (از صفر تا حرفه‌ای)",
            AppLanguage.INDONESIAN to "Kotlin untuk Android (Nol ke Pro)"
        ),
        "track_kotlin_desc" to mapOf(
            AppLanguage.ENGLISH to "val vs var, null safety mystery solved with safe boxes, lambdas, and data classes.",
            AppLanguage.BENGALI to "স্থায়ী ও পরিবর্তনযোগ্য বাক্স (val বনাম var), নাল সেফটি, ল্যাম্বডা ও ডেটা ক্লাস।",
            AppLanguage.URDU to "val بمقابلہ var، نل سیفٹی، لیمبڈاز اور ڈیٹا کلاسز کا آسان ترین فہم۔",
            AppLanguage.HINDI to "val बनाम var, नल सुरक्षा, लैम्ब्डा और आधुनिक एंड्रॉइड कोडिंग।",
            AppLanguage.ARABIC to "الفرق بين val و var، أمان القيمة الفارغة (Null-Safety)، والوظائف المتقدمة.",
            AppLanguage.DARI to "تفاوت val و var، امنیت نال، لامبداها و کلاس‌های داده به زبان ساده.",
            AppLanguage.INDONESIAN to "val vs var, null safety, fungsi lambda, dan kelas data modern."
        ),
        "track_python_title" to mapOf(
            AppLanguage.ENGLISH to "Python Automation (Zero to Pro)",
            AppLanguage.BENGALI to "পাইথন প্রোগ্রামিং ও অটোমেশন (জিরো থেকে প্রো)",
            AppLanguage.URDU to "ازگر (پائتھن) آٹومیشن (صفر سے پرو)",
            AppLanguage.HINDI to "पायथन ऑटोमेशन (शून्य से प्रो)",
            AppLanguage.ARABIC to "لغة بايثون والأتمتة (من الصفر للاحتراف)",
            AppLanguage.DARI to "پایتون و اسکریپت‌نویسی (از صفر تا حرفه‌ای)",
            AppLanguage.INDONESIAN to "Python & Otomasi (Nol ke Pro)"
        ),
        "track_python_desc" to mapOf(
            AppLanguage.ENGLISH to "Super clean syntax, lists, dictionaries, string parsing, and APK automation scripts.",
            AppLanguage.BENGALI to "সহজ সিনট্যাক্স, লিস্ট, ডিকশনারি, ফাইল হ্যান্ডলিং ও এপিকে অডিট স্ক্রিপ্ট তৈরি।",
            AppLanguage.URDU to "آسان ترین نحو، لسٹیں، ڈکشنری، اور APK کے خودکار اسکرپٹس بنانا۔",
            AppLanguage.HINDI to "सरल वाक्य रचना, लिस्ट, डिक्शनरी और स्वचालित स्क्रिप्ट्स का निर्माण।",
            AppLanguage.ARABIC to "بناء الجمل البرمجية البسيط، القوائم، القواميس، وسكربتات فحص وتعديل التطبيقات.",
            AppLanguage.DARI to "ساختار بسیار خوانا، لیست‌ها، دیکشنری‌ها و ساخت اسکریپت‌های کاربردی.",
            AppLanguage.INDONESIAN to "Sintaks bersih, list, dictionary, dan skrip otomasi bedah APK."
        ),
        "track_android_title" to mapOf(
            AppLanguage.ENGLISH to "Android App Building (Connect UI & Code)",
            AppLanguage.BENGALI to "বাটন ও অ্যাক্টিভিটি কানেক্ট করে অ্যান্ড্রয়েড অ্যাপ তৈরি",
            AppLanguage.URDU to "بٹن اور ایکٹیویٹی جوڑ کر ایپ بنانا",
            AppLanguage.HINDI to "बटन और एक्टिविटी कनेक्ट करके एंड्रॉइड ऐप बनाना",
            AppLanguage.ARABIC to "بناء تطبيقات أندرويد (ربط الواجهة والأزرار والأنشطة)",
            AppLanguage.DARI to "ساخت اپلیکیشن اندروید (اتصال دکمه‌ها و فعالیت‌ها)",
            AppLanguage.INDONESIAN to "Membangun Aplikasi Android (Hubungkan Tombol & Layar)"
        ),
        "track_android_desc" to mapOf(
            AppLanguage.ENGLISH to "How buttons in XML/Compose connect to Java/Kotlin, handle clicks, show Toasts, and navigate between Activities using Intents.",
            AppLanguage.BENGALI to "এক্সএমএল ডিজাইনের বাটন জাভা/কোটলিনের সাথে কীভাবে কানেক্ট করে ক্লিক অ্যাকশন ও নতুন পেজে যাওয়া যায়।",
            AppLanguage.URDU to "کس طرح بٹن کو جاوا یا کوٹلن میں جوڑا جاتا ہے اور ایک اسکرین سے دوسری اسکرین پر جایا جاتا ہے۔",
            AppLanguage.HINDI to "कैसे बटन को कोड से कनेक्ट करें, क्लिक एक्शन संभालें और इंटेंट से नई स्क्रीन पर जाएं।",
            AppLanguage.ARABIC to "كيفية ربط أزرار الواجهة بكود جافا وكوتلن والتنقل بين الشاشات عبر النوايا (Intents).",
            AppLanguage.DARI to "چگونگی اتصال دکمه‌ها به کد و جابجایی میان صفحه‌ها با اینتنت.",
            AppLanguage.INDONESIAN to "Cara menghubungkan tombol XML ke Java/Kotlin, menangani klik, dan berpindah Activity."
        ),

        // Child Analogy Badge
        "kid_analogy_badge" to mapOf(
            AppLanguage.ENGLISH to "👶 Easy Child Explanation",
            AppLanguage.BENGALI to "👶 বাচ্চাদের মতো সহজ ব্যাখ্যা",
            AppLanguage.URDU to "👶 بچوں کی طرح آسان وضاحت",
            AppLanguage.HINDI to "👶 बच्चों जैसी सरल व्याख्या",
            AppLanguage.ARABIC to "👶 شرح مبسط للأطفال",
            AppLanguage.DARI to "👶 توضیح بسیار ساده و کودکانه",
            AppLanguage.INDONESIAN to "👶 Penjelasan Super Mudah"
        ),
        "code_example_badge" to mapOf(
            AppLanguage.ENGLISH to "💻 Code in Action",
            AppLanguage.BENGALI to "💻 কোড উদাহরণ",
            AppLanguage.URDU to "💻 کوڈ کی مثال",
            AppLanguage.HINDI to "💻 कोड उदाहरण",
            AppLanguage.ARABIC to "💻 الشيفرة البرمجية",
            AppLanguage.DARI to "💻 نمونه کد کاربردی",
            AppLanguage.INDONESIAN to "💻 Contoh Kode Langsung"
        ),
        "practical_tip_badge" to mapOf(
            AppLanguage.ENGLISH to "⚡ How it works in Real Android",
            AppLanguage.BENGALI to "⚡ আসল অ্যান্ড্রয়েড ফোনে যেভাবে কাজ করে",
            AppLanguage.URDU to "⚡ اصلی اینڈرائیڈ فون میں یہ کیسے کام کرتا ہے",
            AppLanguage.HINDI to "⚡ वास्तविक एंड्रॉइड फोन में यह कैसे काम करता है",
            AppLanguage.ARABIC to "⚡ كيف يعمل داخل هواتف أندرويد الحقيقية",
            AppLanguage.DARI to "⚡ چگونه در موبایل واقعی کار می‌کند",
            AppLanguage.INDONESIAN to "⚡ Cara kerjanya di Android Nyata"
        ),

        // UI & Common
        "select_language" to mapOf(
            AppLanguage.ENGLISH to "Choose Your Language",
            AppLanguage.BENGALI to "আপনার ভাষা নির্বাচন করুন",
            AppLanguage.URDU to "اپنی زبان کا انتخاب کریں",
            AppLanguage.HINDI to "अपनी भाषा चुनें",
            AppLanguage.ARABIC to "اختر لغة التطبيق",
            AppLanguage.DARI to "زبان برنامه را انتخاب کنید",
            AppLanguage.INDONESIAN to "Pilih Bahasa Anda"
        ),
        "language_dialog_subtitle" to mapOf(
            AppLanguage.ENGLISH to "Every lesson, child-friendly explanation, and menu will adapt instantly.",
            AppLanguage.BENGALI to "প্রতিটি পাঠ, শিশুদের মতো সহজ ব্যাখ্যা এবং মেনু এই ভাষায় রূপান্তরিত হবে।",
            AppLanguage.URDU to "ہر سبق اور آسان وضاحت فوری طور پر منتخب زبان میں بدل جائے گی۔",
            AppLanguage.HINDI to "हर पाठ और सरल व्याख्या तुरंत इस भाषा में बदल जाएगी।",
            AppLanguage.ARABIC to "ستتحول كافة الشروحات والقوائم والأمثلة فوراً إلى اللغة المختارة.",
            AppLanguage.DARI to "تمام آموزش‌ها و توضیحات فوراً به زبان انتخابی تغییر خواهد کرد.",
            AppLanguage.INDONESIAN to "Setiap pelajaran, analogi mudah, dan menu akan langsung berubah."
        ),
        "confirm_language" to mapOf(
            AppLanguage.ENGLISH to "Apply Language",
            AppLanguage.BENGALI to "ভাষা প্রয়োগ করুন",
            AppLanguage.URDU to "زبان لاگو کریں",
            AppLanguage.HINDI to "भाषा लागू करें",
            AppLanguage.ARABIC to "تطبيق اللغة",
            AppLanguage.DARI to "اعمال زبان",
            AppLanguage.INDONESIAN to "Terapkan Bahasa"
        ),
        "explore_tracks" to mapOf(
            AppLanguage.ENGLISH to "Choose a Learning Track",
            AppLanguage.BENGALI to "একটি শেখার ট্র্যাক বেছে নিন",
            AppLanguage.URDU to "سیکھنے کا راستہ منتخب کریں",
            AppLanguage.HINDI to "सीखने का ट्रैक चुनें",
            AppLanguage.ARABIC to "اختر مسار التعلم",
            AppLanguage.DARI to "مسیر یادگیری را انتخاب کنید",
            AppLanguage.INDONESIAN to "Pilih Jalur Belajar"
        ),
        "all_tracks" to mapOf(
            AppLanguage.ENGLISH to "All 5 Academy Tracks",
            AppLanguage.BENGALI to "সবগুলো ৫টি ট্র্যাক",
            AppLanguage.URDU to "تمام 5 ٹریکس",
            AppLanguage.HINDI to "सभी 5 ट्रैक",
            AppLanguage.ARABIC to "كافة المسارات الخمسة",
            AppLanguage.DARI to "هر ۵ مسیر آموزشی",
            AppLanguage.INDONESIAN to "Semua 5 Jalur"
        ),
        "continue_reading" to mapOf(
            AppLanguage.ENGLISH to "Start Lesson",
            AppLanguage.BENGALI to "পাঠ শুরু করুন",
            AppLanguage.URDU to "سبق شروع کریں",
            AppLanguage.HINDI to "पाठ शुरू करें",
            AppLanguage.ARABIC to "بدء الدرس",
            AppLanguage.DARI to "شروع درس",
            AppLanguage.INDONESIAN to "Mulai Pelajaran"
        ),
        "mark_completed" to mapOf(
            AppLanguage.ENGLISH to "Mark Completed",
            AppLanguage.BENGALI to "সম্পন্ন চিহ্নিত করুন",
            AppLanguage.URDU to "مکمل نشان زد کریں",
            AppLanguage.HINDI to "पूर्ण चिह्नित करें",
            AppLanguage.ARABIC to "تم الإكمال",
            AppLanguage.DARI to "تکمیل شد",
            AppLanguage.INDONESIAN to "Tandai Selesai"
        ),
        "completed_badge" to mapOf(
            AppLanguage.ENGLISH to "Finished ✓",
            AppLanguage.BENGALI to "সম্পন্ন ✓",
            AppLanguage.URDU to "مکمل ✓",
            AppLanguage.HINDI to "पूर्ण ✓",
            AppLanguage.ARABIC to "منتهي ✓",
            AppLanguage.DARI to "انجام شد ✓",
            AppLanguage.INDONESIAN to "Selesai ✓"
        )
    )

    fun getString(key: String, language: AppLanguage): String {
        return translations[key]?.get(language) ?: translations[key]?.get(AppLanguage.ENGLISH) ?: key
    }
}

@Composable
fun localizedString(key: String): String {
    val currentLang = LocalAppLanguage.current
    return LocalizationManager.getString(key, currentLang)
}
