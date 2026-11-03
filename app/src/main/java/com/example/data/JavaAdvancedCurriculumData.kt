package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object JavaAdvancedCurriculumData {

    val lessons: List<Lesson> = listOf(
        // MODULE 09
        Lesson(
            id = "java_09",
            track = TrackType.JAVA,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "09. Inheritance (extends), super & Polymorphism",
                bn = "০৯. ইনহেরিট্যান্স (extends), super ও পলিমরফিজম",
                ur = "09. وراثت (extends)، super اور پولی مورفزم",
                hi = "09. इनहेरिटेंस (extends), super और पॉलीमॉर्फ़िज़्म",
                ar = "09. الوراثة (extends) والكلمة super وتعدد الأشكال",
                fa_af = "۰۹. ارث‌بری (extends)، کلیدواژه super و چندریختی",
                in_id = "09. Pewarisan (extends), super & Polimorfisme"
            ),
            subtitle = localized(
                en = "Extend parent classes, override methods with @Override, and understand virtual method tables.",
                bn = "প্যারেন্ট ক্লাসের বৈশিষ্ট্য পাওয়া, @Override দিয়ে মেথড বদলানো এবং invoke-super বনাম invoke-virtual।",
                ur = "پیرنٹ کلاس کی خصوصیات لینا، @Override کرنا اور invoke-super بمقابلہ invoke-virtual سمجھنا۔",
                hi = "पैरेंट क्लास का विस्तार, @Override मेथड और वर्चुअल मेथड टेबल की समझ।",
                ar = "توسيع الفئات الأبوية وإعادة تعريف الدوال وفهم جداول الاستدعاء الافتراضي.",
                fa_af = "گسترش کلاس والد، بازنویسی متدها با @Override و درک جدول متدهای مجازی.",
                in_id = "Perluas kelas induk, override metode dengan @Override, dan pahami tabel metode virtual."
            ),
            estimatedMinutes = 12,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Parent-Child Hierarchy & Runtime Dynamic Dispatch",
                        bn = "প্যারেন্ট-চাইল্ড সম্পর্ক এবং রানটাইম পলিমরফিজম",
                        ur = "پیرنٹ چائلڈ تعلق اور رن ٹائم پولی مورفزم",
                        hi = "पैरेंट-चाइल्ड पदानुक्रम और रनटाइम डायनामिक डिस्पैच",
                        ar = "علاقة الأب والابن والربط الديناميكي وقت التشغيل",
                        fa_af = "سلسله‌مراتب والد و فرزند و فراخوانی پویا در زمان اجرا",
                        in_id = "Hierarki Induk-Anak & Dynamic Dispatch Saat Runtime"
                    ),
                    childAnalogy = localized(
                        en = "Every Android `MainActivity extends AppCompatActivity` inherits screen superpowers from its parent—and uses `super.onCreate()` to let the parent set up the window first!",
                        bn = "অ্যান্ড্রয়েডের প্রতিটি `MainActivity extends AppCompatActivity` তার অভিভাবক ক্লাস থেকে স্ক্রিন চালানোর ক্ষমতা পায়—আর `super.onCreate()` ডেকে আগে প্যারেন্টকে জানালা সাজাতে দেয়!",
                        ur = "ہر `MainActivity` اپنی پیرنٹ کلاس سے اسکرین کی طاقت حاصل کرتی ہے اور `super.onCreate()` کے ذریعے پہلے پیرنٹ سیٹ اپ چلاتی ہے!",
                        hi = "प्रत्येक `MainActivity` अपनी पैरेंट क्लास से स्क्रीन क्षमता पाती है और `super.onCreate()` से पहले पैरेंट सेटअप चलाती है!",
                        ar = "ترث كل شاشة في أندرويد قدراتها من فئتها الأب عبر `extends` وتستدعي `super.onCreate()` لتهيئة النافذة أولاً!",
                        fa_af = "هر اکتیویتی در اندروید توانایی‌هایش را با `extends` از کلاس والد به ارث می‌برد و با `super.onCreate()` ابتدا تنظیمات والد را اجرا می‌کند!",
                        in_id = "Setiap `MainActivity` mewarisi kemampuan layar lewat `extends` dan memanggil `super.onCreate()` terlebih dahulu!"
                    ),
                    content = localized(
                        en = "`extends` establishes single class inheritance (`.super` in Smali). When a child overrides a method with `@Override`, calling `animal.speak()` uses `invoke-virtual` to dynamically run the child's version, while `super.speak()` uses `invoke-super` to call the parent directly.",
                        bn = "জাভায় একটি ক্লাস একটিই ক্লাসকে `extends` করতে পারে (স্মালির ২য় লাইনে `.super` হিসেবে লেখা থাকে)। সাব-ক্লাস `@Override` দিয়ে মেথড নতুন করে লিখলে `invoke-virtual` স্বয়ংক্রিয়ভাবে চাইল্ড মেথডটি চালায়, আর প্যারেন্টের মেথড ডাকতে `super.` (`invoke-super`) ব্যবহৃত হয়।",
                        ur = "جاوا میں `extends` سمالی کی `.super` لائن بنتا ہے۔ `@Override` شدہ میتھڈ `invoke-virtual` سے چلتا ہے جبکہ پیرنٹ میتھڈ `invoke-super` سے۔",
                        hi = "जावा में `extends` स्माली की `.super` लाइन बनता है। `@Override` मेथड `invoke-virtual` से और पैरेंट कॉल `invoke-super` से चलती है।",
                        ar = "تتحول `extends` إلى التوجيه `.super` في سمالي، ويعمل الاستدعاء المتعدد الأشكال عبر `invoke-virtual` بينما يستدعي `super` الأب عبر `invoke-super`.",
                        fa_af = "کلیدواژه `extends` در سمالی به `.super` تبدیل می‌شود؛ متد بازنویسی‌شده با `invoke-virtual` و متد والد با `invoke-super` اجرا می‌گردد.",
                        in_id = "`extends` menjadi direktif `.super` di Smali; metode override dipanggil lewat `invoke-virtual` dan induknya lewat `invoke-super`."
                    ),
                    javaCode = """public class ProAccount extends BaseAccount {
    @Override
    public int feePercent() {
        return super.feePercent() / 2;
    }
}""",
                    smaliCode = """.class public Lcom/example/ProAccount;
.super Lcom/example/BaseAccount;

.method public feePercent()I
    .locals 2
    invoke-super {p0}, Lcom/example/BaseAccount;->feePercent()I
    move-result v0
    div-int/lit8 v1, v0, 0x2
    return v1
.end method"""
                )
            )
        ),

        // MODULE 10
        Lesson(
            id = "java_10",
            track = TrackType.JAVA,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "10. Interfaces (implements), Abstract Classes & Lambdas",
                bn = "১০. ইন্টারফেস (implements), অ্যাবস্ট্রাক্ট ক্লাস ও ল্যাম্বডা",
                ur = "10. انٹرفیس (implements)، ایبسٹریکٹ کلاس اور لیمبڈا",
                hi = "10. इंटरफ़ेस (implements), एब्स्ट्रैक्ट क्लास और लैम्ब्डा",
                ar = "10. الواجهات (implements) والفئات المجردة وتعابير لامدا",
                fa_af = "۱۰. اینترفیس‌ها (implements)، کلاس انتزاعی و لامبدا",
                in_id = "10. Interface (implements), Kelas Abstrak & Lambda"
            ),
            subtitle = localized(
                en = "Design clean plug-and-play contracts, callbacks, and Java 8 -> lambda expressions.",
                bn = "কলব্যাক কন্ট্রাক্ট, একাধিক ইন্টারফেস ইমপ্লিমেন্টেশন এবং আধুনিক `->` ল্যাম্বডা এক্সপ্রেশন।",
                ur = "کال بیک کنٹریکٹس، ملٹی انٹرفیسز اور جدید `->` لیمبڈا فنکشنز لکھنے کا فن۔",
                hi = "कॉलबैक अनुबंध, एकाधिक इंटरफ़ेस और आधुनिक जावा `->` लैम्ब्डा अभिव्यक्तियाँ।",
                ar = "تصميم العقود البرمجية المرنة ومستمعات الأحداث والدوال السهمية الحديثة.",
                fa_af = "طراحی قراردادهای منعطف، کال‌بک‌ها و عبارات لامبدای مدرن در جاوا.",
                in_id = "Rancang kontrak callback bersih, multi-interface, dan ekspresi lambda Java 8."
            ),
            estimatedMinutes = 12,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Contract Programming & Synthetic Lambda Classes",
                        bn = "কন্ট্রাক্ট প্রোগ্রামিং এবং ল্যাম্বডা যেভাবে স্মালিতে কম্পাইল হয়",
                        ur = "کنٹریکٹ پروگرامنگ اور سمالی میں لیمبڈا کی تبدیلی",
                        hi = "कॉन्ट्रैक्ट प्रोग्रामिंग और सिंथेटिक लैम्ब्डा क्लास",
                        ar = "البرمجة بالعقود وكيف تتحول تعابير لامدا في سمالي",
                        fa_af = "برنامه‌نویسی قراردادی و تبدیل لامبدا به کلاس‌های ترکیبی در سمالی",
                        in_id = "Pemrograman Kontrak & Kelas Sintetis Lambda di Smali"
                    ),
                    content = localized(
                        en = "A class can only `extends` ONE parent class, but can `implements` MANY interfaces (`.implements` in Smali). Single-method interfaces (`FunctionalInterface` like `View.OnClickListener` or `Runnable`) can be written with concise `v -> ...` lambda syntax, which D8 compiles into `\$\$ExternalSyntheticLambda` classes!",
                        bn = "জাভায় একটি ক্লাস কেবল একটি ক্লাসকেই `extends` করতে পারে, কিন্তু অসংখ্য `interface`-কে `implements` করতে পারে! যে ইন্টারফেসে মাত্র একটিই মেথড থাকে (যেমন `OnClickListener` বা `Runnable`), সেটিকে লম্বা কোডের বদলে `v -> ...` ল্যাম্বডা দিয়ে এক লাইনে লেখা যায়—যাকে D8 কম্পাইলার `\$\$ExternalSyntheticLambda` ক্লাসে রূপান্তর করে!",
                        ur = "ایک کلاس صرف ایک کلاس کو `extends` کر سکتی ہے مگر کئی انٹرفیسز کو `implements` کر سکتی ہے! ایک میتھڈ والے انٹرفیس کو `v -> ...` لیمبڈا میں لکھا جا سکتا ہے۔",
                        hi = "एक क्लास केवल एक पैरेंट को `extends` कर सकती है, पर कई इंटरफ़ेस `implements` कर सकती है! एकल-मेथड इंटरफ़ेस को `v -> ...` लैम्ब्डा से संक्षिप्त लिख सकते हैं।",
                        ar = "يمكن للفئة وراثة فئة واحدة فقط لكنها تستطيع تطبيق عدة واجهات `implements`، وتُكتب الواجهات أحادية الدالة بصيغة لامدا `->`.",
                        fa_af = "یک کلاس تنها یک والد را `extends` می‌کند اما می‌تواند چندین `interface` را پیاده‌سازی نماید؛ اینترفیس‌های تک‌متدی را می‌توان با لامبدای `->` نوشت.",
                        in_id = "Sebuah kelas hanya bisa `extends` satu induk, namun bisa `implements` banyak interface; interface satu metode dapat ditulis memakai lambda `->`."
                    ),
                    javaCode = """public interface AuthCallback {
    void onVerified(boolean success);
}

// Concise Java Lambda usage:
AuthCallback cb = ok -> System.out.println("Status: " + ok);""",
                    smaliCode = """.class public interface abstract Lcom/example/AuthCallback;
.super Ljava/lang/Object;

.method public abstract onVerified(Z)V
.end method"""
                )
            )
        ),

        // MODULE 11
        Lesson(
            id = "java_11",
            track = TrackType.JAVA,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "11. Collections & Generics (ArrayList, HashMap, HashSet)",
                bn = "১১. কালেকশন ফ্রেমওয়ার্ক ও জেনেরিক্স (ArrayList, HashMap)",
                ur = "11. کلیکشنز اور جینرکس (ArrayList، HashMap، HashSet)",
                hi = "11. कलेक्शंस और जेनेरिक्स (ArrayList, HashMap, HashSet)",
                ar = "11. إطار المجموعات والأنواع العامة (ArrayList و HashMap)",
                fa_af = "۱۱. کالکشن‌ها و جنریک‌ها (ArrayList، HashMap و HashSet)",
                in_id = "11. Collections & Generics (ArrayList, HashMap, HashSet)"
            ),
            subtitle = localized(
                en = "Master dynamic lists, O(1) hash maps, autoboxing, and Java Generic Type Erasure in DEX.",
                bn = "ডায়নামিক লিস্ট, দ্রুতগতির কী-ভ্যালু ম্যাপ, অটোবক্সিং এবং জেনেরিক Type Erasure রহস্য।",
                ur = "متحرک لسٹیں، تیز ترین ہیش میپ، آٹو باکسنگ اور Type Erasure کا راز۔",
                hi = "डायनामिक लिस्ट, तेज़ हैश मैप, ऑटोबॉक्सिंग और जेनेरिक Type Erasure का रहस्य।",
                ar = "أتقن القوائم الديناميكية وخرائط التجزئة السريعة ومحو الأنواع العامة وقت الترجمة.",
                fa_af = "تسلط بر لیست‌های پویا، جدول‌های هش سریع و پدیده حذف نوع جنریک در بایت‌کد.",
                in_id = "Kuasai list dinamis, hash map O(1), autoboxing, dan Type Erasure di DEX."
            ),
            estimatedMinutes = 13,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Dynamic Collections & Why <String> Vanishes into check-cast",
                        bn = "ডায়নামিক কালেকশন এবং কেন <String> স্মালিতে check-cast হয়ে যায়",
                        ur = "متحرک کلیکشنز اور سمالی میں <String> کا check-cast بننا",
                        hi = "डायनामिक कलेक्शन और <String> का स्माली में check-cast बनना",
                        ar = "المجموعات الديناميكية ولماذا تتحول <String> إلى تعليمة check-cast",
                        fa_af = "مجموعه‌های پویا و علت تبدیل `<String>` به دستور `check-cast` در سمالی",
                        in_id = "Koleksi Dinamis & Mengapa <String> Menjadi check-cast di Smali"
                    ),
                    content = localized(
                        en = "• `List<String> names = new ArrayList<>()` grows automatically as items are added.\n• `Map<String, Integer> scores = new HashMap<>()` gives `O(1)` instant key lookup (boxing `int` into `Ljava/lang/Integer;` via `Integer.valueOf`).\n• **Type Erasure**: At runtime, `ArrayList<String>` stores plain `Ljava/lang/Object;` references—so Java inserts a `check-cast v0, Ljava/lang/String;` whenever you call `.get(0)`!",
                        bn = "• `ArrayList<String>` নতুন ডাটা যোগ করলে নিজে থেকেই বড় হয়।\n• `HashMap<String, Integer>` কী-ভ্যালু আকারে মুহূর্তেই (`O(1)`) তথ্য খুঁজে বের করে (এখানে `int` অটোমেটিক `Integer.valueOf` হয়ে অবজেক্টে রূপ নেয়)।\n• **Type Erasure রহস্য**: রানটাইমে জেনেরিক `<String>` মুছে `Object` হয়ে যায়, তাই `.get(0)` কল করলে কম্পাইলার স্মালিতে `check-cast v0, Ljava/lang/String;` বসিয়ে দেয়!",
                        ur = "• `ArrayList` خودکار بڑی ہوتی ہے اور `HashMap` تیز ترین تلاش فراہم کرتا ہے۔\n• رن ٹائم پر `<String>` مٹ کر `Object` رہ جاتا ہے، اس لیے سمالی میں `check-cast` لگتا ہے!",
                        hi = "• `ArrayList` स्वतः बढ़ता है और `HashMap` तुरंत `O(1)` खोज देता है।\n• **Type Erasure**: रनटाइम पर `<String>` मिटकर `Object` रहता है, इसलिए `.get(0)` के बाद `check-cast` जुड़ता है!",
                        ar = "• تنمو `ArrayList` تلقائياً وتوفر `HashMap` بحثاً فورياً.\n• بسبب محو الأنواع (Type Erasure) يدرج المترجم تعليمة `check-cast` عند استرجاع العناصر!",
                        fa_af = "• `ArrayList` به‌طور خودکار بزرگ می‌شود و `HashMap` جستجوی آنی دارد.\n• به دلیل Type Erasure، کامپایلر هنگام `.get(0)` دستور `check-cast` را در سمالی قرار می‌دهد!",
                        in_id = "• `ArrayList` membesar otomatis dan `HashMap` memberi pencarian `O(1)`.\n• Akibat Type Erasure, kompiler menyisipkan `check-cast` saat membaca elemen!"
                    ),
                    javaCode = """List<String> list = new ArrayList<>();
list.add("Dex");
String first = list.get(0);""",
                    smaliCode = """invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;
move-result-object v2
check-cast v2, Ljava/lang/String;   # Inserted by Generic Type Erasure!"""
                )
            )
        ),

        // MODULE 12
        Lesson(
            id = "java_12",
            track = TrackType.JAVA,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "12. Exception Handling (try, catch, finally & throw)",
                bn = "১২. এক্সেপশন ও এরর হ্যান্ডলিং (try, catch, finally, throw)",
                ur = "12. ایکسپشن اور ایرر ہینڈلنگ (try، catch، finally، throw)",
                hi = "12. एक्सेप्शन हैंडलिंग (try, catch, finally व throw)",
                ar = "12. معالجة الاستثناءات والأخطاء (try و catch و finally)",
                fa_af = "۱۲. مدیریت خطا و استثناء (try، catch، finally و throw)",
                in_id = "12. Penanganan Eksepsi (try, catch, finally & throw)"
            ),
            subtitle = localized(
                en = "Prevent crashes gracefully, understand Checked vs Runtime exceptions, and inspect DEX .catch ranges.",
                bn = "অ্যাপ ক্র্যাশ ঠেকানো, Checked বনাম Runtime এক্সেপশন এবং স্মালির .catch টেবিল বোঝা।",
                ur = "ایپ کو کریش ہونے سے بچانا، چیکڈ بمقابلہ رن ٹائم ایررز اور سمالی کا .catch ٹیبل۔",
                hi = "क्रैश रोकना, Checked बनाम Runtime एक्सेप्शन और स्माली की .catch रेंज समझना।",
                ar = "منع انهيار التطبيق والتمييز بين الاستثناءات المفحوصة والفورية وجداول .catch.",
                fa_af = "جلوگیری حرفه‌ای از کرش برنامه، تفاوت استثناءهای Checked و Runtime و جدول `.catch`.",
                in_id = "Cegah aplikasi crash, pahami Checked vs Runtime exception, dan tabel .catch DEX."
            ),
            estimatedMinutes = 11,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Zero-Cost Try Blocks & move-exception",
                        bn = "নিরাপদ try-catch ব্লক এবং স্মালির move-exception",
                        ur = "محفوظ try-catch بلاک اور سمالی کا move-exception",
                        hi = "सुरक्षित try-catch ब्लॉक और स्माली का move-exception",
                        ar = "كتل try الآمنة وتعليمة move-exception في سمالي",
                        fa_af = "بلوک‌های ایمن try-catch و دستور move-exception در سمالی",
                        in_id = "Blok try-catch Aman & Instruksi move-exception"
                    ),
                    content = localized(
                        en = "Wrap risky parsing or I/O inside `try { ... } catch (Exception e) { ... } finally { ... }`. `finally` ALWAYS executes (even if `try` returns early!) to close files/sockets cleanly. In DEX, `.catch` defines address labels (`:try_start .. :try_end`) with zero CPU overhead unless an exception is thrown!",
                        bn = "ঝুঁকিপূর্ণ কোড (যেমন ভুল সংখ্যা পার্স করা বা ফাইল পড়া) `try { ... } catch (...)` এর ভেতরে রাখলে এরর হলেও অ্যাপ ক্র্যাশ করে না। আর `finally` ব্লক সবসময় চলে যাতে ফাইল বা কানেকশন নিরাপদে বন্ধ হয়। স্মালিতে এটি `:try_start .. :try_end` লেবেল দিয়ে কাজ করে!",
                        ur = "خطرناک کوڈ کو `try/catch` میں رکھنے سے ایپ کریش نہیں ہوتی اور `finally` ہمیشہ چلتا ہے۔ سمالی میں یہ `:try_start .. :try_end` لیبلز سے کام کرتا ہے!",
                        hi = "जोखिम भरे कोड को `try/catch` में रखने से क्रैश रुकता है और `finally` हमेशा चलता है। स्माली में यह `:try_start .. :try_end` लेबल बनाता है!",
                        ar = "تحمي كتلة `try/catch` التطبيق من الانهيار، وتنفذ `finally` دائماً لإغلاق الموارد، وتُمثل في سمالي بعلامات `:try_start .. :try_end`.",
                        fa_af = "قرار دادن کدهای حساس در `try/catch` مانع کرش می‌شود و `finally` همیشه اجرا می‌گردد؛ در سمالی این کار با برچسب‌های `:try_start .. :try_end` انجام می‌شود.",
                        in_id = "Blok `try/catch` mencegah crash dan `finally` selalu dieksekusi; di Smali dipetakan ke label `:try_start .. :try_end`."
                    ),
                    javaCode = """public static int safeParse(String text) {
    try {
        return Integer.parseInt(text);
    } catch (NumberFormatException e) {
        return 0;
    }
}""",
                    smaliCode = """:try_start
invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
move-result v0
return v0
:try_end
.catch Ljava/lang/NumberFormatException; {:try_start .. :try_end} :catch_err

:catch_err
move-exception v0
const/4 v0, 0x0
return v0"""
                )
            )
        ),

        // MODULE 13
        Lesson(
            id = "java_13",
            track = TrackType.JAVA,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "13. File Streams, try-with-resources & JSON Parsing",
                bn = "১৩. ফাইল রিড/রাইট, try-with-resources ও JSON পার্সিং",
                ur = "13. فائل اسٹریمز، try-with-resources اور JSON پارسنگ",
                hi = "13. फ़ाइल स्ट्रीम, try-with-resources और JSON पार्सिंग",
                ar = "13. التعامل مع الملفات و try-with-resources وتحليل JSON",
                fa_af = "۱۳. خواندن و نوشتن فایل، try-with-resources و تجزیه JSON",
                in_id = "13. Aliran File, try-with-resources & Parsing JSON"
            ),
            subtitle = localized(
                en = "Read/write UTF-8 streams leak-free and parse Android org.json payloads cleanly.",
                bn = "মেমোরি লিক ছাড়াই ফাইল পড়া/লেখা এবং অ্যান্ড্রয়েডের বিল্ট-ইন JSONObject দিয়ে API ডাটা পার্স করা।",
                ur = "میموری لیک کے بغیر فাইল پڑھنا/لکھنا اور اینڈرائیڈ کے JSONObject سے ڈیٹا نکالنا۔",
                hi = "बिना रिसोर्स लीक फ़ाइल पढ़ना/लिखना और एंड्रॉइड JSONObject से पेलोड पार्स करना।",
                ar = "قراءة وكتابة الملفات دون تسريب للموارد وتحليل بيانات JSON باحترافية.",
                fa_af = "خواندن و نوشتن ایمن فایل‌ها بدون نشت منابع و پارس کردن داده‌های JSON در اندروید.",
                in_id = "Baca/tulis stream UTF-8 bebas bocor dan uraikan data JSONObject Android."
            ),
            estimatedMinutes = 12,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Auto-Closing Streams & Safe optString JSON Parsing",
                        bn = "স্বয়ংক্রিয় স্ট্রিম ক্লোজিং এবং নিরাপদ optString JSON পার্সিং",
                        ur = "خودکار اسٹریم بند کرنا اور محفوظ optString جیسن پارسنگ",
                        hi = "ऑटो-क्लोज़िंग स्ट्रीम और सुरक्षित optString JSON पार्सिंग",
                        ar = "الإغلاق التلقائي للتدفقات والتحليل الآمن عبر optString",
                        fa_af = "بستن خودکار جریان فایل و خواندن ایمن JSON با optString",
                        in_id = "Penutupan Otomatis Stream & Parsing Aman optString"
                    ),
                    content = localized(
                        en = "Always use `try (BufferedReader br = ...)` (`try-with-resources`) so Java automatically calls `.close()` even on failure. When parsing `JSONObject` on Android, prefer `json.optString(\"name\", \"Guest\")` over `getString(...)` so missing keys return a fallback instead of throwing `JSONException`!",
                        bn = "ফাইল বা নেটওয়ার্ক স্ট্রিম খুললে সবসময় `try (BufferedReader br = ...)` লিখবেন—এতে কাজ শেষে জাভা নিজে থেকেই `.close()` কল করে দেয়। আর অ্যান্ড্রয়েডে `JSONObject` পড়ার সময় `getString`-এর বদলে `optString(\"name\", \"Guest\")` ব্যবহার করলে কোনো কী না থাকলেও এরর দেয় না!",
                        ur = "فائل کھولتے وقت `try (...)` استعمال کریں تاکہ `.close()` خودکار چلے، اور JSON میں `optString` استعمال کریں تاکہ کلید نہ ملنے پر کریش نہ ہو!",
                        hi = "फ़ाइल खोलते समय `try (...)` का उपयोग करें ताकि `.close()` स्वतः चले, और JSON में `optString` चुनें ताकि कुंजी न होने पर अपवाद न आए!",
                        ar = "استخدم `try (...)` لإغلاق الملفات تلقائياً، واستخدم `optString` مع `JSONObject` لتجنب الأخطاء عند غياب المفتاح!",
                        fa_af = "همیشه از `try (...)` استفاده کنید تا فایل به‌طور خودکار بسته شود و در `JSONObject` از `optString` بهره ببرید تا نبود کلید باعث خطا نشود!",
                        in_id = "Gunakan `try (...)` agar `.close()` dipanggil otomatis, dan pilih `optString` pada `JSONObject` agar aman dari key kosong!"
                    ),
                    javaCode = """import org.json.JSONObject;

public static String extractUser(String rawJson) throws Exception {
    JSONObject obj = new JSONObject(rawJson);
    return obj.optString("username", "Anonymous");
}""",
                    smaliCode = """new-instance v0, Lorg/json/JSONObject;
invoke-direct {v0, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
const-string v1, "username"
const-string v2, "Anonymous"
invoke-virtual {v0, v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
move-result-object v0
return-object v0"""
                )
            )
        ),

        // MODULE 14
        Lesson(
            id = "java_14",
            track = TrackType.JAVA,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "14. Multithreading, synchronized Locks & ExecutorService",
                bn = "১৪. মাল্টিথ্রেডিং, synchronized লক ও ব্যাকগ্রাউন্ড টাস্ক",
                ur = "14. ملٹی تھریڈنگ، synchronized لاک اور بیک گراؤنڈ ٹاسک",
                hi = "14. मल्टीथ्रेडिंग, synchronized लॉक और ExecutorService",
                ar = "14. تعدد الخيوط وأقفال synchronized والمهام الخلفية",
                fa_af = "۱۴. چندنخی (Multithreading)، قفل synchronized و اجرای پس‌زمینه",
                in_id = "14. Multithreading, Kunci synchronized & ExecutorService"
            ),
            subtitle = localized(
                en = "Keep Android UI at 60/120fps, prevent race conditions, and see monitor-enter in Smali.",
                bn = "অ্যান্ড্রয়েড UI হ্যাং হওয়া (ANR) বন্ধ করুন, রেস কন্ডিশন ঠেকান এবং স্মালির monitor-enter দেখুন।",
                ur = "اینڈرائیڈ اسکرین منجمد ہونے سے بچائیں، ریس کنڈیشن روکیں اور سمالی کا monitor-enter سمجھیں۔",
                hi = "एंड्रॉइड UI फ्रीज़ (ANR) रोकें, रेस कंडीशन से बचें और स्माली में monitor-enter देखें।",
                ar = "حافظ على سلاسة واجهة أندرويد وامنع تضارب الخيوط وتعرف على تعليمة monitor-enter.",
                fa_af = "جلوگیری از قفل شدن صفحه اندروید (ANR)، مهار تداخل نخ‌ها و مشاهده monitor-enter در سمالی.",
                in_id = "Jaga UI Android tetap mulus bebas ANR, cegah race condition, dan amati monitor-enter."
            ),
            estimatedMinutes = 13,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Thread Safety & Mutex Locks (monitor-enter / monitor-exit)",
                        bn = "থ্রেড সেফটি এবং স্মালিতে মিউটেক্স লক (monitor-enter / monitor-exit)",
                        ur = "تھریڈ سیفٹی اور سمالی میں میوٹیکس لاک (monitor-enter / monitor-exit)",
                        hi = "थ्रेड सुरक्षा और स्माली में म्यूटेक्स लॉक (monitor-enter / monitor-exit)",
                        ar = "أمان الخيوط وأقفال التزامن (monitor-enter / monitor-exit)",
                        fa_af = "ایمنی نخ‌ها و قفل‌های همگام‌سازی (monitor-enter / monitor-exit)",
                        in_id = "Keamanan Thread & Kunci Mutex (monitor-enter / monitor-exit)"
                    ),
                    content = localized(
                        en = "Heavy work on Android's Main Thread freezes the UI (`Application Not Responding`). Offload work using `Executors.newSingleThreadExecutor()`, and guard shared variables with `synchronized (lock)`—which compiles into `monitor-enter` and `monitor-exit` in DEX!",
                        bn = "অ্যান্ড্রয়েডের Main Thread-এ ভারী কাজ চালালে অ্যাপ আটকে গিয়ে `ANR` দেয়। তাই ভারী কাজ `ExecutorService` দিয়ে ব্যাকগ্রাউন্ড থ্রেডে পাঠান, আর একাধিক থ্রেড একই ডাটা বদলালে `synchronized (lock)` ব্যবহার করুন—যা স্মালিতে `monitor-enter` এবং `monitor-exit` অপকোডে রূপান্তরিত হয়!",
                        ur = "مین تھریڈ پر بھاری کام چلانے سے ایپ منجمد ہو جاتی ہے۔ بیک گراؤنڈ کے لیے `ExecutorService` اور مشترکہ ڈیٹا کی حفاظت کے لیے `synchronized` استعمال کریں جو سمالی میں `monitor-enter` بنتا ہے!",
                        hi = "मेन थ्रेड पर भारी काम करने से ANR आता है। बैकग्राउंड के लिए `ExecutorService` और साझा डेटा के लिए `synchronized` चुनें जो `monitor-enter` बनता है!",
                        ar = "العمليات الثقيلة على الخيط الرئيسي تجمد الواجهة؛ انقلها إلى `ExecutorService` واحمِ البيانات المشتركة بـ `synchronized` التي تترجم إلى `monitor-enter`.",
                        fa_af = "کار سنگین روی نخ اصلی باعث هنگ برنامه می‌شود؛ از `ExecutorService` استفاده کنید و داده‌های مشترک را با `synchronized` قفل نمایید که به `monitor-enter` تبدیل می‌شود.",
                        in_id = "Kerja berat di Main Thread memicu ANR; gunakan `ExecutorService` dan lindungi variabel bersama dengan `synchronized` (`monitor-enter`)."
                    ),
                    javaCode = """public class SafeCounter {
    private final Object lock = new Object();
    private int count = 0;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}""",
                    smaliCode = """iget-object v0, p0, Lcom/example/SafeCounter;->lock:Ljava/lang/Object;
monitor-enter v0
:try_start
iget v1, p0, Lcom/example/SafeCounter;->count:I
add-int/lit8 v1, v1, 0x1
iput v1, p0, Lcom/example/SafeCounter;->count:I
monitor-exit v0
return-void
:try_end
.catchall {:try_start .. :try_end} :unlock
:unlock
move-exception v1
monitor-exit v0
throw v1"""
                )
            )
        ),

        // MODULE 15
        Lesson(
            id = "java_15",
            track = TrackType.JAVA,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "15. Java Reflection, Annotations & Dynamic Invocation",
                bn = "১৫. জাভা রিফ্লেকশন (Reflection), অ্যানোটেশন ও ডায়নামিক কল",
                ur = "15. جاوا ریفلیکشن، اینوٹیشنز اور ڈائنامک میتھڈ کال",
                hi = "15. जावा रिफ़्लेक्शन, एनोटेशन और डायनामिक इनवोकेशन",
                ar = "15. الانعكاس Reflection والتعليقات التوضيحية والاستدعاء الديناميكي",
                fa_af = "۱۵. بازتاب (Reflection)، انوتیشن‌ها و فراخوانی پویا در جاوا",
                in_id = "15. Refleksi Java, Anotasi & Pemanggilan Dinamis"
            ),
            subtitle = localized(
                en = "Understand how Gson, Retrofit, Room, and security tools inspect classes at runtime.",
                bn = "জানুন কীভাবে Retrofit, Gson এবং সিকিউরিটি টুল রানটাইমে ক্লাস ও প্রাইভেট মেথড পরিদর্শন করে।",
                ur = "سمجھیں کہ لائبریریاں اور سیکیورٹی ٹولز رن ٹائم پر کلاسز اور میتھڈز کو کیسے پڑھتے ہیں۔",
                hi = "समझें कि लाइब्रेरी और सुरक्षा टूल रनटाइम पर क्लास और मेथड का निरीक्षण कैसे करते हैं।",
                ar = "افهم كيف تفحص المكتبات الاحترافية وأدوات الفحص الفئات والدوال أثناء التشغيل.",
                fa_af = "درک کنید کتابخانه‌های حرفه‌ای و ابزارهای امنیتی چگونه در زمان اجرا کلاس‌ها را کاوش می‌کنند.",
                in_id = "Pahami bagaimana pustaka modern dan alat audit memeriksa kelas saat runtime."
            ),
            estimatedMinutes = 13,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Class.forName & Method.invoke Under the Microscope",
                        bn = "Class.forName এবং Method.invoke যেভাবে কাজ করে",
                        ur = "Class.forName اور Method.invoke کے کام کرنے کا طریقہ",
                        hi = "Class.forName और Method.invoke की आंतरिक कार्यप्रणाली",
                        ar = "آلية عمل Class.forName و Method.invoke بالتفصيل",
                        fa_af = "نحوه عملکرد دقیق Class.forName و Method.invoke",
                        in_id = "Cara Kerja Class.forName & Method.invoke Secara Mendalam"
                    ),
                    content = localized(
                        en = "Reflection (`java.lang.reflect`) allows Java code to look up classes, fields, and methods by String name at runtime (`Class.forName`, `getDeclaredMethod`). When ProGuard/R8 shrinks an APK, classes accessed via Reflection MUST have `@Keep` rules or `ClassNotFoundException` will occur!",
                        bn = "রিফ্লেকশন (`java.lang.reflect`) দিয়ে রানটাইমে শুধুমাত্র স্ট্রিং নাম ব্যবহার করে যেকোনো ক্লাস বা মেথড খুঁজে চালানো যায় (`Class.forName`, `getDeclaredMethod`)। মনে রাখবেন: R8/ProGuard চালু থাকলে রিফ্লেকশনে ব্যবহৃত ক্লাসে `@Keep` অ্যানোটেশন না দিলে নাম বদলে গিয়ে `ClassNotFoundException` হতে পারে!",
                        ur = "ریفلیکشن کے ذریعے رن ٹائم پر اسٹرنگ نام سے کلاس یا میتھڈ تلاش کر کے چلایا جا سکتا ہے۔ R8 آن ہونے کی صورت میں `@Keep` لگانا ضروری ہوتا ہے!",
                        hi = "रिफ़्लेक्शन से रनटाइम पर स्ट्रिंग नाम द्वारा क्लास व मेथड खोजकर चलाया जा सकता है। R8 मिनीफ़िकेशन चालू होने पर `@Keep` लगाना आवश्यक है!",
                        ar = "يتيح الانعكاس البحث عن الفئات والدوال بالاسم النصي وقت التشغيل، ويجب حمايتها بـ `@Keep` عند تفعيل تصغير الشيفرة بـ R8!",
                        fa_af = "با Reflection می‌توان در زمان اجرا کلاس‌ها و متدها را با نام رشته‌ای فراخوانی کرد؛ در زمان فعال بودن R8 باید از `@Keep` استفاده شود!",
                        in_id = "Refleksi memungkinkan pencarian kelas & metode lewat teks saat runtime; wajib diberi `@Keep` jika minifikasi R8 aktif!"
                    ),
                    javaCode = """Class<?> cls = Class.forName("android.os.Build");
java.lang.reflect.Field f = cls.getDeclaredField("MODEL");
String deviceModel = (String) f.get(null);""",
                    smaliCode = """const-string v0, "android.os.Build"
invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;
move-result-object v0
const-string v1, "MODEL"
invoke-virtual {v0, v1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;
move-result-object v1"""
                )
            )
        ),

        // MODULE 16
        Lesson(
            id = "java_16",
            track = TrackType.JAVA,
            level = LearningLevel.PRO_MODDING,
            title = localized(
                en = "16. Pro Design Patterns, Memory Leaks & R8 Optimization",
                bn = "১৬. প্রো ডিজাইন প্যাটার্ন (Singleton, Builder) ও মেমোরি লিক রোধ",
                ur = "16. پرو ڈیزائن پیٹرنز (Singleton، Builder) اور میموری لیک سے بچاؤ",
                hi = "16. प्रो डिज़ाइन पैटर्न (Singleton, Builder) और मेमोरी लीक बचाव",
                ar = "16. أنماط التصميم الاحترافية ومنع تسرب الذاكرة وتحسين R8",
                fa_af = "۱۶. الگوهای طراحی حرفه‌ای (Singleton، Builder) و رفع نشت حافظه",
                in_id = "16. Pola Desain Pro (Singleton, Builder) & Pencegahan Memory Leak"
            ),
            subtitle = localized(
                en = "Write production-grade Android Java using thread-safe Singletons, Builders, and leak-free Context.",
                bn = "থ্রেড-সেফ Singleton, ক্লিন Builder প্যাটার্ন এবং অ্যান্ড্রয়েডে Context মেমোরি লিক ঠেকানোর কৌশল।",
                ur = "تھریڈ سیف سنگلٹن، بلڈر پیٹرن اور اینڈرائیڈ میں Context میموری لیک روکنے کے طریقے۔",
                hi = "थ्रेड-सुरक्षित Singleton, साफ़ Builder पैटर्न और एंड्रॉइड Context मेमोरी लीक से बचाव।",
                ar = "اكتب شيفرة جافا إنتاجية بأنماط Singleton و Builder مع تجنب تسريب سياق الشاشة.",
                fa_af = "نوشتن کد تجاری جاوا با الگوهای Singleton و Builder و جلوگیری از نشت حافظه Context.",
                in_id = "Tulis Java kelas produksi dengan Singleton aman-thread, Builder, dan Context bebas bocor."
            ),
            estimatedMinutes = 14,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Thread-Safe Singleton, Builder & Never Storing Activity in static",
                        bn = "থ্রেড-সেফ Singleton, Builder এবং static ফিল্ডে Activity না রাখার গোল্ডেন নিয়ম",
                        ur = "تھریڈ سیف سنگلٹن، بلڈر اور static میں Activity نہ رکھنے کا سنہری اصول",
                        hi = "थ्रेड-सेफ Singleton, Builder और static में Activity न रखने का सुनहरा नियम",
                        ar = "نمط Singleton الآمن والقاعدة الذهبية لعدم تخزين Activity في حقل ثابت",
                        fa_af = "الگوی Singleton ایمن و قانون طلایی عدم نگهداری Activity در متغیرهای static",
                        in_id = "Singleton Aman-Thread, Builder & Jangan Simpan Activity di static"
                    ),
                    childAnalogy = localized(
                        en = "Storing an `Activity` inside a `static` variable is like chaining an entire hotel room to the front desk—even after the guest checks out, the Garbage Collector can never free that room!",
                        bn = "একটি `Activity`-কে `static` ভেরিয়েবলের ভেতর রেখে দেওয়া মানে অতিথির চেক-আউটের পরেও পুরো হোটেল রুমটাকে শেকল দিয়ে বেঁধে রাখা—Garbage Collector আর কখনোই সেই রুমের র‍্যাম খালি করতে পারে না!",
                        ur = "کسی `Activity` کو `static` متغیر میں رکھنا ایسا ہے جیسے مہمان جانے کے بعد بھی پورے کمرے کو زنجیر سے باندھ دیا جائے—میموری کبھی خالی نہیں ہو پاتی!",
                        hi = "`static` वेरिएबल में `Activity` रखना ऐसा है जैसे मेहमान जाने के बाद भी पूरे कमरे को जंजीर से बाँध देना—Garbage Collector मेमोरी मुक्त नहीं कर पाता!",
                        ar = "حفظ `Activity` في متغير `static` يمنع جامع القمامة من تحرير ذاكرة الشاشة حتى بعد إغلاقها تماماً!",
                        fa_af = "نگهداری یک `Activity` در متغیر `static` مانع آزادسازی حافظه صفحه توسط زباله‌جمع‌کن حتی پس از بسته شدن آن می‌شود!",
                        in_id = "Menyimpan `Activity` di variabel `static` membuat Garbage Collector tidak pernah bisa membebaskan memori layar tersebut!"
                    ),
                    content = localized(
                        en = "1. **Singleton**: Use `volatile` + double-checked locking (or an initialization holder class) for single shared managers.\n2. **Builder Pattern**: Chain `.setHost(...).setTimeout(...).build()` cleanly instead of 8-argument constructors.\n3. **Leak Prevention**: If a Singleton needs Context, ALWAYS store `context.getApplicationContext()`, NEVER an `Activity` reference!",
                        bn = "১. **Singleton প্যাটার্ন**: পুরো অ্যাপে একটিমাত্র ম্যানেজার অবজেক্ট রাখতে `volatile` ও ডাবল-চেকড লকিং ব্যবহার করুন।\n২. **Builder প্যাটার্ন**: ৮-১০টি প্যারামিটারের কনস্ট্রাক্টরের বদলে `.setUrl(...).setTimeout(...).build()` চেইন ব্যবহার করুন (ঠিক `AlertDialog.Builder`-এর মতো!)।\n৩. **মেমোরি লিক রোধ**: Singleton-এ কখনোই `Activity` সেভ করবেন না—সবসময় `context.getApplicationContext()` ব্যবহার করবেন!",
                        ur = "1. **Singleton**: پورے ایپ میں ایک ہی انسٹانس رکھنے کے لیے `volatile` استعمال کریں۔\n2. **Builder**: لمبے کنسٹرکٹر کی بجائے `.build()` چین استعمال کریں۔\n3. ہمیشہ `context.getApplicationContext()` محفوظ کریں!",
                        hi = "1. **Singleton**: एकल साझा मैनेजर हेतु `volatile` डबल-चेक्ड लॉकिंग अपनाएं।\n2. **Builder**: लंबे कंस्ट्रक्टर के बजाय `.build()` चेन लिखें।\n3. हमेशा `context.getApplicationContext()` ही सहेजें!",
                        ar = "1. نمط **Singleton** بـ `volatile` للإدارة الموحدة.\n2. نمط **Builder** لبناء الكائنات المعقدة بوضوح.\n3. خزّن دائماً `getApplicationContext()` لتجنب تسرب الذاكرة!",
                        fa_af = "۱. الگوی **Singleton** با `volatile` برای مدیریت واحد.\n۲. الگوی **Builder** به‌جای سازنده‌های طولانی.\n۳. همیشه فقط `getApplicationContext()` را ذخیره کنید تا نشت حافظه رخ ندهد!",
                        in_id = "1. **Singleton** memakai `volatile`.\n2. **Builder** merangkai konfigurasi rapi.\n3. Selalu simpan `context.getApplicationContext()` agar bebas memory leak!"
                    ),
                    javaCode = """public final class SessionManager {
    private static volatile SessionManager instance;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }
}""",
                    smaliCode = """.field private static volatile instance:Lcom/example/SessionManager;"""
                )
            )
        )
    )
}
