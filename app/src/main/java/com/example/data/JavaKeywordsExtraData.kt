package com.example.data

import com.example.model.JavaKeywordCategory
import com.example.model.JavaKeywordItem
import com.example.model.localized

object JavaKeywordsExtraData {

    val extraKeywords: List<JavaKeywordItem> = listOf(
        // ==================== MODIFIERS (6) ====================
        JavaKeywordItem(
            keyword = "public",
            badge = "Global Access",
            category = JavaKeywordCategory.MODIFIERS,
            syntax = "public int getScore() { return score; }",
            smaliEquivalent = ".method public getScore()I",
            purposeContent = localized(
                en = "Makes a class, method, constructor, or field accessible from ANY package in the project.",
                bn = "ক্লাস, মেথড বা ভেরিয়েবলকে প্রজেক্টের যেকোনো ফাইল ও প্যাকেজ থেকে ব্যবহারযোগ্য করে।",
                ur = "کلاس، میتھڈ یا فیلڈ کو پورے پروجیکٹ کے کسی بھی پیکج سے قابلِ رسائی بناتا ہے۔",
                hi = "क्लास, मेथड या फ़ील्ड को प्रोजेक्ट के किसी भी पैकेज से सुलभ बनाता है।",
                ar = "يجعل الفئة أو الدالة أو الحقل متاحاً للوصول من أي حزمة في المشروع.",
                fa_af = "کلاس، متد یا فیلد را از تمام پکیج‌های پروژه قابل دسترس می‌کند.",
                in_id = "Membuat kelas, metode, atau field dapat diakses dari paket mana pun."
            ),
            detailedExplanationContent = localized(
                en = "Use `public` for clean API entry points (`Activity` classes, getters, service interfaces), while keeping internal mutable fields `private`.",
                bn = "বাইরে থেকে যে মেথডগুলো কল করা দরকার শুধু সেগুলোকেই `public` রাখুন, কিন্তু ক্লাসের ভেতরের আসল ডাটা ফিল্ডগুলোকে `private` রাখাই প্রফেশনাল নিয়ম।",
                ur = "صرف ان میتھڈز کو `public` رکھیں جنہیں باہر سے کال کرنا ضروری ہو، جبکہ اندرونی ڈیٹا کو `private` رکھیں۔",
                hi = "केवल बाहरी उपयोग वाले API मेथड को `public` रखें, और आंतरिक डेटा फ़ील्ड को `private` रखें।",
                ar = "اجعل واجهات الاستخدام فقط `public`، واحتفظ بالحقول الداخلية خاصة `private` لحماية البيانات.",
                fa_af = "تنها متدهای مورد نیاز بیرون کلاس را `public` تعریف کنید و فیلدهای داخلی را `private` نگه دارید.",
                in_id = "Gunakan `public` hanya untuk metode API luar, dan jaga field internal tetap `private`."
            ),
            javaExample = """public class ScoreBoard {
    private int points;
    public int getPoints() { return points; }
}""",
            commonMistakeContent = localized(
                en = "Making mutable fields `public` (`public int balance;`) breaks encapsulation—any class can set `balance = -9999` without validation!",
                bn = "ভেরিয়েবলকে সরাসরি `public` করে দিলে যেকোনো জায়গা থেকে ভুল মান (`-9999`) বসিয়ে ডাটা নষ্ট করা সম্ভব হয়!",
                ur = "فیلڈ کو براہِ راست `public` بنانے سے کوئی بھی بغیر چیک کیے غلط قیمت درج کر سکتا ہے!",
                hi = "फ़ील्ड को `public` बनाने से कोई भी बिना जांच के गलत मान (`-9999`) डाल सकता है!",
                ar = "جعل الحقول المتغيرة `public` يكسر التغليف ويسمح لأي كود بوضع قيم خاطئة دون تحقق!",
                fa_af = "عمومی کردن فیلدها (`public`) اصل کپسوله‌سازی را شکسته و اجازه ثبت مقادیر نامعتبر را می‌دهد!",
                in_id = "Membuat field `public` merusak enkapsulasi karena kelas mana pun bisa mengubah nilainya tanpa validasi!"
            )
        ),

        JavaKeywordItem(
            keyword = "private",
            badge = "Encapsulated",
            category = JavaKeywordCategory.MODIFIERS,
            syntax = "private String apiKey;",
            smaliEquivalent = ".field private apiKey:Ljava/lang/String;\ninvoke-direct {p0}, LAuth;->check()Z",
            purposeContent = localized(
                en = "Restricts visibility strictly to the declaring class—the foundation of OOP Encapsulation.",
                bn = "ভেরিয়েবল বা মেথডকে শুধুমাত্র নিজের ক্লাসের ভেতরেই সীমাবদ্ধ রাখে (Encapsulation)।",
                ur = "فیلڈ یا میتھڈ تک رسائی صرف اسی کلاس کے اندر محدود رکھتا ہے۔",
                hi = "फ़ील्ड या मेथड की पहुंच केवल उसी क्लास के भीतर सीमित रखता है।",
                ar = "يحصر الوصول داخل نفس الفئة فقط، وهو حجر الأساس للتغليف في OOP.",
                fa_af = "دسترسی را صرفاً به داخل همان کلاس محدود می‌کند؛ پایه اصلی کپسوله‌سازی.",
                in_id = "Membatasi akses hanya di dalam kelas itu sendiri—fondasi utama Enkapsulasi OOP."
            ),
            detailedExplanationContent = localized(
                en = "Because `private` methods cannot be overridden by subclasses, Android compiles calls to them as non-virtual `invoke-direct` in Smali rather than `invoke-virtual`!",
                bn = "যেহেতু `private` মেথডকে কোনো চাইল্ড ক্লাস ওভাররাইড করতে পারে না, তাই অ্যান্ড্রয়েডে এগুলো দ্রুতগতির `invoke-direct` অপকোড দিয়ে কল হয়!",
                ur = "چونکہ `private` میتھڈ اوور رائیڈ نہیں ہو سکتا، اس لیے سمالی میں یہ `invoke-virtual` کی بجائے `invoke-direct` سے چلتا ہے!",
                hi = "चूंकि `private` मेथड ओवरराइड नहीं हो सकता, स्माली में यह `invoke-direct` से कॉल होता है!",
                ar = "لأن الدوال `private` لا تقبل إعادة التعريف، يترجمها أندرويد إلى `invoke-direct` المباشرة في سمالي!",
                fa_af = "چون متدهای `private` قابل بازنویسی نیستند، در سمالی با `invoke-direct` فراخوانی می‌شوند!",
                in_id = "Karena metode `private` tidak bisa di-override, Smali memanggilnya memakai `invoke-direct`!"
            ),
            javaExample = """public class Vault {
    private String pin = "4821";
    private boolean verify(String input) {
        return pin.equals(input);
    }
}""",
            commonMistakeContent = localized(
                en = "`private` hides data from other Java classes at compile time, NOT from APK reverse engineers—never hardcode real secrets in Java strings!",
                bn = "`private` শুধু অন্য জাভা ক্লাস থেকে ডাটা লুকায়, কিন্তু ডিকম্পাইল করা `.smali` ফাইলে সব পরিষ্কার দেখা যায়—তাই গোপন কী হার্ডকোড করবেন না!",
                ur = "`private` صرف دوسری جاوا کلاسز سے چھپاتا ہے، سمالی فائل میں یہ صاف نظر آتا ہے—سیکرٹ کیز ہارڈ کوڈ نہ کریں!",
                hi = "`private` केवल कंपाइल समय पर छुपाता है, डीकंपाइल्ड `.smali` में सब दिखता है—सीक्रेट की हार्डकोड न करें!",
                ar = "تحمي `private` الكود أثناء الترجمة فقط، لكنها تظهر بوضوح عند تفكيك `.smali`؛ لا تضع مفاتيح سرية ثابتة!",
                fa_af = "`private` فقط در زمان کامپایل محدودیت ایجاد می‌کند و در فایل `.smali` کاملاً قابل مشاهده است!",
                in_id = "`private` hanya menyembunyikan akses antar kelas Java, namun tetap terlihat jelas di `.smali`—jangan hardcode kunci rahasia!"
            )
        ),

        JavaKeywordItem(
            keyword = "protected",
            badge = "Subclass + Pkg",
            category = JavaKeywordCategory.MODIFIERS,
            syntax = "@Override protected void onCreate(Bundle state)",
            smaliEquivalent = ".method protected onCreate(Landroid/os/Bundle;)V",
            purposeContent = localized(
                en = "Visible to subclasses (`extends`) anywhere AND to other classes inside the same package.",
                bn = "নিজের প্যাকেজের ক্লাস এবং যেকোনো চাইল্ড ক্লাস (`extends`) থেকে ব্যবহার করার অনুমতি দেয়।",
                ur = "اسی پیکج کی کلاسز اور تمام ذیلی کلاسز (`extends`) کو رسائی دیتا ہے۔",
                hi = "समान पैकेज की क्लासों और सभी सब-क्लासों (`extends`) को पहुंच देता है।",
                ar = "متاح للفئات الفرعية المشتقة ولجميع الفئات داخل نفس الحزمة.",
                fa_af = "قابل دسترس برای زیرکلاس‌ها (`extends`) و سایر کلاس‌های هم‌پکیج.",
                in_id = "Dapat diakses oleh subclass (`extends`) serta kelas lain di paket yang sama."
            ),
            detailedExplanationContent = localized(
                en = "Android framework lifecycle hooks (`onCreate`, `onStart`, `onResume`, `onDestroy`) are `protected` so your `Activity` subclass can override them without exposing them to unrelated classes.",
                bn = "অ্যান্ড্রয়েডের `onCreate`, `onResume` ইত্যাদি মেথড `protected` থাকে যেন আপনার `Activity` ক্লাস সেগুলো ওভাররাইড করতে পারে কিন্তু বাইরের অচেনা ক্লাস কল করতে না পারে।",
                ur = "اینڈرائیڈ کے `onCreate` اور `onResume` میتھڈز `protected` ہوتے ہیں تاکہ صرف آپ کی ذیلی `Activity` انہیں استعمال کرے۔",
                hi = "एंड्रॉइड के `onCreate`, `onResume` आदि `protected` होते हैं ताकि केवल आपकी सब-क्लास उन्हें ओवरराइड कर सके।",
                ar = "دوال دورة حياة أندرويد مثل `onCreate` تكون `protected` لكي تعيد فئتك تعريفها بأمان.",
                fa_af = "متدهای چرخه حیات اندروید مانند `onCreate` از نوع `protected` هستند تا زیرکلاس اکتیویتی شما آن‌ها را بازنویسی کند.",
                in_id = "Metode siklus hidup Android seperti `onCreate` bersifat `protected` agar hanya subclass `Activity` yang meng-override-nya."
            ),
            javaExample = """public abstract class BaseWorker {
    protected abstract void executeTask();
}""",
            commonMistakeContent = localized(
                en = "Remember that in Java (unlike Kotlin!), `protected` ALSO grants access to non-subclasses sitting in the exact same package.",
                bn = "মনে রাখবেন, কোটলিনের চেয়ে আলাদা হয়ে জাভায় `protected` দিলে একই প্যাকেজের অন্য ক্লাসগুলোও সেটি দেখতে পায়!",
                ur = "یاد رکھیں کہ جاوا میں `protected` ایک ہی پیکج کی دوسری کلاسز کو بھی رسائی دے دیتا ہے!",
                hi = "ध्यान रहे कि जावा में (कोटलिन के विपरीत) `protected` समान पैकेज की अन्य क्लासों को भी पहुंच देता है!",
                ar = "انتبه أن `protected` في جافا (عكس كوتلن) تمنح الوصول أيضاً للفئات الموجودة في نفس الحزمة!",
                fa_af = "توجه کنید که در جاوا (برخلاف کاتلین) سطح `protected` به کلاس‌های هم‌پکیج نیز اجازه دسترسی می‌دهد!",
                in_id = "Ingat, di Java (berbeda dari Kotlin), `protected` JUGA mengizinkan akses bagi kelas lain dalam paket yang sama!"
            )
        ),

        JavaKeywordItem(
            keyword = "static",
            badge = "Class-Level",
            category = JavaKeywordCategory.MODIFIERS,
            syntax = "public static final int MAX_RETRIES = 3;",
            smaliEquivalent = "sget / sput  &  invoke-static {p0}  # No 'this'!",
            purposeContent = localized(
                en = "Belongs to the Class itself rather than any `new` object instance—shared once in memory.",
                bn = "কোনো নির্দিষ্ট অবজেক্টের বদলে সরাসরি পুরো ক্লাসের সাথে যুক্ত—তাই `new` ছাড়াই কল করা যায়।",
                ur = "کسی آبجیکٹ کی بجائے براہِ راست کلاس سے تعلق رکھتا ہے—بغیر `new` کے استعمال ہوتا ہے۔",
                hi = "किसी ऑब्जेक्ट के बजाय सीधे क्लास से जुड़ा होता है—बिना `new` के बुलाया जा सकता है।",
                ar = "ينتمي للفئة نفسها مباشرة دون الحاجة لإنشاء كائن بـ `new`.",
                fa_af = "متعلق به خود کلاس است نه شیء خاص و بدون نیاز به `new` فراخوانی می‌شود.",
                in_id = "Milik Kelas itu sendiri (bukan objek `new`), sehingga langsung dipanggil lewat nama kelas."
            ),
            detailedExplanationContent = localized(
                en = "In Smali, `static` fields use `sget`/`sput` instead of `iget`/`iput`, and `static` methods use `invoke-static` where `p0` is the FIRST argument (NOT `this`)!",
                bn = "স্মালিতে `static` ভেরিয়েবলের জন্য `sget`/`sput` এবং মেথডের জন্য `invoke-static` বসে। আর স্ট্যাটিক মেথডে `p0` মানে `this` নয়, বরং প্রথম প্যারামিটার!",
                ur = "سمالی میں `static` فیلڈز کے لیے `sget`/`sput` اور میتھڈز کے لیے `invoke-static` استعمال ہوتا ہے جہاں `p0` پہلا پیرامیٹر ہوتا ہے!",
                hi = "स्माली में `static` फ़ील्ड हेतु `sget`/`sput` और मेथड हेतु `invoke-static` लगता है, जहां `p0` पहला आर्ग्युमेंट होता है!",
                ar = "في سمالي تستخدم الحقول الثابتة `sget`/`sput` والدوال `invoke-static` حيث يمثل `p0` المعامل الأول وليس `this`!",
                fa_af = "در سمالی فیلد استاتیک با `sget`/`sput` و متد استاتیک با `invoke-static` کار می‌کند و `p0` اولین آرگومان است نه `this`!",
                in_id = "Di Smali, `static` memakai `sget`/`sput` serta `invoke-static` di mana `p0` adalah argumen pertama (bukan `this`)!"
            ),
            javaExample = """public final class MathUtil {
    public static int square(int x) {
        return x * x;
    }
}
int n = MathUtil.square(6); // 36""",
            commonMistakeContent = localized(
                en = "NEVER store an Android `Activity` or `View` inside a `static` field—it causes a severe memory leak when the screen rotates!",
                bn = "কখনোই অ্যান্ড্রয়েডের `Activity` বা `Context`-কে `static` ভেরিয়েবলে রাখবেন না—এতে মারাত্মক Memory Leak হয়!",
                ur = "کبھی بھی اینڈرائیڈ `Activity` یا `View` کو `static` متغیر میں محفوظ نہ کریں، اس سے شدید میموری لیک ہوتا ہے!",
                hi = "कभी भी Android `Activity` या `View` को `static` फ़ील्ड में न रखें—इससे गंभीर मेमोरी लीक होता है!",
                ar = "إياك وتخزين `Activity` أو `View` في متغير `static` لأن ذلك يسبب تسريباً خطيراً للذاكرة!",
                fa_af = "هرگز `Activity` یا `View` اندروید را در متغیر `static` نگه ندارید زیرا نشت شدید حافظه ایجاد می‌کند!",
                in_id = "JANGAN PERNAH menyimpan `Activity` atau `View` Android di field `static` karena menyebabkan kebocoran memori (memory leak)!"
            )
        ),

        JavaKeywordItem(
            keyword = "final",
            badge = "Immutable / Lock",
            category = JavaKeywordCategory.MODIFIERS,
            syntax = "private final String userId;",
            smaliEquivalent = ".field private final userId:Ljava/lang/String;",
            purposeContent = localized(
                en = "Prevents reassignment on variables, prevents overriding on methods, and prevents subclassing on classes.",
                bn = "ভেরিয়েবলের মান পুনরায় বদলানো, মেথড ওভাররাইড করা অথবা ক্লাস ইনহেরিট করা বন্ধ করে।",
                ur = "متغیر کی قیمت بدلنے، میتھڈ اوور رائیڈ کرنے یا کلاس وراثت میں دینے سے روکتا ہے۔",
                hi = "वेरिएबल का मान बदलने, मेथड ओवरराइड करने या क्लास एक्सटेंड करने पर रोक लगाता है।",
                ar = "يمنع إعادة إسناد المتغير، أو إعادة تعريف الدالة، أو وراثة الفئة.",
                fa_af = "از تغییر مجدد مقدار متغیر، بازنویسی متد یا ارث‌بری از کلاس جلوگیری می‌کند.",
                in_id = "Mencegah pengubahan ulang variabel, override metode, atau pewarisan kelas."
            ),
            detailedExplanationContent = localized(
                en = "Marking fields `private final` makes objects thread-safe and bug-resistant (just like Kotlin's `val`). Note: `final List<String>` locks the list reference, though items can still be added unless using `List.of(...)`.",
                bn = "ভেরিয়েবলে `final` দেওয়া কোটলিনের `val`-এর সমান—একবার মান বসালে আর ভুল করে বদলানো যায় না। তবে `final ArrayList` মানে লিস্ট রেফারেন্স স্থির, ভেতরে `.add()` করা যাবে।",
                ur = "`final` لگانا کوٹلن کے `val` جیسا ہے جو کوڈ کو محفوظ بناتا ہے، البتہ `final List` کے اندر عناصر شامل کیے جا سکتے ہیں۔",
                hi = "`final` फ़ील्ड कोटलिन के `val` जैसा सुरक्षित होता है; हालांकि `final ArrayList` में `.add()` से तत्व जोड़े जा सकते हैं।",
                ar = "استخدام `final` يماثل `val` في كوتلن ويزيد أمان الخيوط، علماً أن القائمة `final` تقبل إضافة عناصر داخلها.",
                fa_af = "`final` معادل `val` در کاتلین است و امنیت کد را بالا می‌برد؛ هرچند به یک لیست `final` همچنان می‌توان عضو اضافه کرد.",
                in_id = "`final` setara `val` di Kotlin sehingga aman dari perubahan tak sengaja (meski isi `final ArrayList` tetap bisa ditambah)."
            ),
            javaExample = """public final class Token {
    public final String raw;
    public Token(String raw) { this.raw = raw; }
}""",
            commonMistakeContent = localized(
                en = "Confusing `final` (immutability keyword), `finally` (cleanup block after `try`), and `finalize()` (deprecated GC hook)!",
                bn = "`final` (স্থির মান), `finally` (`try-catch`-এর শেষ ব্লক) এবং `finalize()`—এই তিনটি এক জিনিস নয়, গুলিয়ে ফেলবেন না!",
                ur = "`final`، `finally` اور `finalize()` تینوں بالکل مختلف الفاظ ہیں—ان میں الجھن کا شکار نہ ہوں!",
                hi = "`final`, `finally` और `finalize()` तीनों बिल्कुल अलग हैं—इन्हें आपस में न मिलाएं!",
                ar = "احذر الخلط بين الكلمات الثلاث المختلفة تماماً: `final` و `finally` و `finalize()`!",
                fa_af = "مواظب باشید سه مفهوم کاملاً متفاوت `final`، `finally` و `finalize()` را با هم اشتباه نگیرید!",
                in_id = "Jangan tertukar antara `final` (konstanta), `finally` (blok pembersih `try`), dan `finalize()`!"
            )
        ),

        JavaKeywordItem(
            keyword = "abstract",
            badge = "Base Template",
            category = JavaKeywordCategory.MODIFIERS,
            syntax = "public abstract class Shape { abstract double area(); }",
            smaliEquivalent = ".class public abstract LShape;\n.method abstract area()D\n.end method",
            purposeContent = localized(
                en = "Declares a partial base class that cannot be instantiated with `new` and forces subclasses to implement abstract methods.",
                bn = "এমন বেস ক্লাস যা সরাসরি `new` দিয়ে বানানো যায় না এবং চাইল্ড ক্লাসকে অসমাপ্ত মেথড পূরণ করতে বাধ্য করে।",
                ur = "ایسی بنیادی کلاس جس کا براہِ راست `new` آبجیکٹ نہیں بن سکتا اور ذیلی کلاس پر میتھڈ مکمل کرنا لازم کرتی ہے۔",
                hi = "ऐसी आधार क्लास जिसका `new` ऑब्जेक्ट नहीं बन सकता और जो सब-क्लास को अधूरे मेथड पूरे करने को बाध्य करती है।",
                ar = "فئة أساسية جزئية لا يمكن إنشاء كائن منها بـ `new` وتلزم الفئات الفرعية بتطبيق دوالها المجردة.",
                fa_af = "کلاس پایه ناتمام که نمی‌توان با `new` از آن شیء ساخت و زیرکلاس‌ها را ملزم به پیاده‌سازی متدها می‌کند.",
                in_id = "Kelas dasar parsial yang tidak bisa dibuat dengan `new` dan mewajibkan subclass melengkapi metode abstraknya."
            ),
            detailedExplanationContent = localized(
                en = "Unlike pure interfaces, an `abstract class` can hold non-static mutable fields, `protected` state, and constructors. In Smali, an `abstract` method has NO `.locals` or instructions between `.method` and `.end method`.",
                bn = "ইন্টারফেসের চেয়ে আলাদা হয়ে `abstract class`-এ সাধারণ ভেরিয়েবল ফিল্ড ও কনস্ট্রাক্টর রাখা যায়। স্মালিতে `abstract` মেথডের ভেতরে কোনো কোড বা `.locals` থাকে না।",
                ur = "انٹرفیس کے برعکس `abstract class` میں فیلڈز اور کنسٹرکٹر رکھے جا سکتے ہیں۔ سمالی میں اس میتھڈ کے اندر کوئی کوڈ نہیں ہوتا۔",
                hi = "इंटरफ़ेस के विपरीत `abstract class` में सामान्य फ़ील्ड और कंस्ट्रक्टर हो सकते हैं। स्माली में इसके मेथड बॉडी में कोड नहीं होता।",
                ar = "تتميز الفئة المجردة عن الواجهة بقدرتها على حمل حقول حالة وبانيات، وتخلو دالتها في سمالي من التعليمات.",
                fa_af = "برخلاف اینترفیس، کلاس `abstract` می‌تواند فیلد و سازنده داشته باشد و متد انتزاعی در سمالی بدون بدنه است.",
                in_id = "Berbeda dari interface, `abstract class` dapat menyimpan field state dan konstruktor; di Smali metodenya tanpa isi."
            ),
            javaExample = """public abstract class PaymentGateway {
    public abstract boolean charge(int cents);
}""",
            commonMistakeContent = localized(
                en = "Combining `abstract` with `final` or `private` is illegal—a method that cannot be seen or overridden can never be implemented!",
                bn = "`abstract`-এর সাথে কখনোই `private` বা `final` একসাথে লেখা যায় না, কারণ তাহলে চাইল্ড ক্লাস সেটি ওভাররাইডই করতে পারবে না!",
                ur = "`abstract` کے ساتھ `private` یا `final` لگانا غیر قانونی ہے کیونکہ پھر ذیلی کلاس اسے اوور رائیڈ نہیں کر سکے گی!",
                hi = "`abstract` के साथ `private` या `final` लगाना अवैध है क्योंकि तब सब-क्लास उसे लागू ही नहीं कर पाएगी!",
                ar = "لا يجوز جمع `abstract` مع `private` أو `final` لأن ذلك يمنع الفئات الفرعية من تطبيقها!",
                fa_af = "ترکیب `abstract` با `private` یا `final` خطای کامپایل است زیرا امکان بازنویسی را از بین می‌برد!",
                in_id = "Menggabungkan `abstract` dengan `private` atau `final` dilarang karena metode tersebut tidak akan pernah bisa di-override!"
            )
        ),

        // ==================== ERRORS & THREADS (5) ====================
        JavaKeywordItem(
            keyword = "try / catch",
            badge = "Crash Shield",
            category = JavaKeywordCategory.ERRORS_THREADS,
            syntax = "try { parse(raw); } catch (NumberFormatException e) { ... }",
            smaliEquivalent = ":try_start_0 ... :try_end_0\n.catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0",
            purposeContent = localized(
                en = "Intercepts runtime exceptions gracefully so invalid files, network drops, or bad inputs don't crash the app.",
                bn = "রানটাইম এরর বা ব্যতিক্রম সামলে নিয়ে অ্যাপকে হঠাৎ ক্র্যাশ হওয়া থেকে রক্ষা করে।",
                ur = "رن ٹائم غلطیوں کو بحفاظت پکڑ کر ایپ کو اچانک بند ہونے (کریش) سے بچاتا ہے۔",
                hi = "रनटाइम अपवादों को संभालकर ऐप को अचानक क्रैश होने से बचाता है।",
                ar = "يلتقط الاستثناءات أثناء التشغيل بأمان لمنع انهيار التطبيق عند حدوث أخطاء.",
                fa_af = "خطاهای زمان اجرا را مدیریت کرده و مانع کرش ناگهانی برنامه می‌شود.",
                in_id = "Menangkap eksepsi saat runtime dengan aman agar aplikasi tidak langsung crash."
            ),
            detailedExplanationContent = localized(
                en = "In Android DEX bytecode, `try/catch` costs ZERO CPU overhead when no error occurs—ART simply consults the `.catch` address range table (`:try_start_0 .. :try_end_0`) when an exception is thrown!",
                bn = "স্মালিতে `try` ব্লকের চারপাশে `:try_start_0` ও `:try_end_0` লেবেল এবং নিচে `.catch` নির্দেশ থাকে—কোনো এরর না ঘটলে এতে অতিরিক্ত একটুও গতি কমে না!",
                ur = "سمالی میں اس کے گرد `:try_start_0` اور `:try_end_0` لیبلز اور `.catch` ٹیبل بنتا ہے جو عام رفتار کم نہیں کرتا۔",
                hi = "स्माली में यह `:try_start_0 .. :try_end_0` और `.catch` तालिका बनाता है जिससे सामान्य गति पर कोई असर नहीं पड़ता।",
                ar = "في سمالي تُحدد علامات `:try_start_0` و `:try_end_0` مع جدول `.catch` دون أي بطء في الحالة الطبيعية.",
                fa_af = "در سمالی با برچسب‌های `:try_start_0 .. :try_end_0` و دستور `.catch` پیاده‌سازی می‌شود و سربار سرعتی ندارد.",
                in_id = "Di Smali, blok ini memakai label `:try_start_0 .. :try_end_0` dan tabel `.catch` tanpa beban CPU saat normal."
            ),
            javaExample = """try {
    int port = Integer.parseInt(userInput.trim());
} catch (NumberFormatException e) {
    showError("Invalid port number");
}""",
            commonMistakeContent = localized(
                en = "Never write an empty `catch (Exception ignored) { }`—swallowing errors silently makes bugs nearly impossible to diagnose!",
                bn = "কখনোই খালি `catch (Exception e) { }` রেখে দেবেন না—এতে আসল বাগ চাপা পড়ে যায় এবং সমস্যা খুঁজে পাওয়া অসম্ভব হয়ে ওঠে!",
                ur = "کبھی بھی خالی `catch (Exception e) {}` نہ چھوڑیں، اس سے اصل خرابی چھپ جاتی ہے!",
                hi = "कभी भी खाली `catch (Exception e) {}` न छोड़ें—इससे असली बग छिप जाते हैं!",
                ar = "لا تترك كتلة `catch` فارغة أبداً لأن ابتلاع الأخطاء بصمت يجعل تشخيص الأعطال مستحيلاً!",
                fa_af = "هرگز بلوک `catch` را خالی رها نکنید؛ بلعیدن بی‌صدا خطاها عیب‌یابی برنامه را غیرممکن می‌کند!",
                in_id = "Jangan pernah membiarkan `catch (Exception e) {}` kosong tanpa log karena membuat bug mustahil dilacak!"
            )
        ),

        JavaKeywordItem(
            keyword = "finally",
            badge = "Guaranteed Cleanup",
            category = JavaKeywordCategory.ERRORS_THREADS,
            syntax = "try { ... } finally { cursor.close(); }",
            smaliEquivalent = ".catchall {:try_start_0 .. :try_end_0} :catchall_0",
            purposeContent = localized(
                en = "Runs cleanup code (closing files, cursors, locks) GUARANTEED whether `try` succeeds, returns early, or throws!",
                bn = "`try` সফল হোক, মাঝপথে `return` করুক বা এরর ঘটুক—ফাইল বা ডাটাবেস বন্ধ করার কোড ১০০% নিশ্চিতভাবে চালায়!",
                ur = "چاہے `try` کامیاب ہو یا ایرر آئے، فائل اور وسائل بند کرنے کا کوڈ لازماً چلاتا ہے!",
                hi = "`try` सफल हो, `return` करे या त्रुटि फेंके—संसाधन बंद करने का कोड हर हाल में चलाता है!",
                ar = "ينفذ كود التنظيف وإغلاق الموارد بشكل مضمون سواء نجحت المحاولة أو حدث استثناء!",
                fa_af = "کدهای پاک‌سازی و بستن منابع را در هر شرایطی (موفقیت یا بروز خطا) تضمینی اجرا می‌کند!",
                in_id = "Menjalankan kode pembersihan (menutup file/koneksi) secara PASTI baik sukses maupun error!"
            ),
            detailedExplanationContent = localized(
                en = "Even better on Java 7+ / Android: `try (BufferedReader br = ...)` (`try-with-resources`) automatically generates a safe `finally { br.close(); }` block for any `AutoCloseable`!",
                bn = "আধুনিক জাভায় `try (FileInputStream in = ...)` (`try-with-resources`) লিখলে কম্পাইলার নিজেই নিখুঁত `finally` ও `.catchall` তৈরি করে ফাইল বন্ধ করে দেয়!",
                ur = "جدید جاوا میں `try (...)` (`try-with-resources`) لکھنے سے کمپائلر خودکار طور پر محفوظ `finally` بنا دیتا ہے!",
                hi = "आधुनिक जावा में `try (...)` (`try-with-resources`) लिखने पर कंपाइलर स्वतः सुरक्षित `finally` जोड़ देता है!",
                ar = "في جافا الحديثة توفر صيغة `try (...)` إغلاق الموارد تلقائياً عبر توليد `.catchall` آمن في سمالي.",
                fa_af = "در جاوای مدرن ساختار `try (...)` به‌طور خودکار بلوک `finally` و `.catchall` امن برای بستن منابع تولید می‌کند.",
                in_id = "Lebih rapi lagi: gunakan `try (InputStream in = ...)` agar kompiler otomatis membuat blok `finally` penutup!"
            ),
            javaExample = """try (java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream()) {
    out.write(65);
} // Automatically closed!""",
            commonMistakeContent = localized(
                en = "NEVER put a `return` statement inside `finally`—it silently discards any exception that was thrown inside `try`!",
                bn = "`finally` ব্লকের ভেতরে কখনোই `return` লিখবেন না—এতে `try` ব্লকের ভেতরে ঘটা আসল এরর গায়েব হয়ে যায়!",
                ur = "`finally` کے اندر کبھی `return` نہ لکھیں ورنہ `try` کا اصل ایرر غائب ہو جاتا ہے!",
                hi = "`finally` के भीतर कभी `return` न लिखें—यह `try` में आए अपवाद को चुपचाप मिटा देता है!",
                ar = "لا تكتب `return` داخل `finally` أبداً لأنها تمحو أي استثناء حدث في كتلة `try`!",
                fa_af = "هرگز داخل `finally` دستور `return` ننویسید زیرا خطای رخ‌داده در `try` را کاملاً پنهان می‌کند!",
                in_id = "JANGAN menulis `return` di dalam `finally` karena akan menelan eksepsi yang dilempar dari `try`!"
            )
        ),

        JavaKeywordItem(
            keyword = "throw / throws",
            badge = "Signal Error",
            category = JavaKeywordCategory.ERRORS_THREADS,
            syntax = "if (amount <= 0) throw new IllegalArgumentException(\"Bad amount\");",
            smaliEquivalent = "throw v0  # Opcode 0x27",
            purposeContent = localized(
                en = "`throw` actively raises an exception object; `throws` declares checked exceptions on a method header.",
                bn = "`throw` দিয়ে ইচ্ছাকৃতভাবে এরর অবজেক্ট ছুঁড়ে দেওয়া হয়, আর মেথডের মাথায় `throws` দিয়ে সম্ভাব্য এররের ঘোষণা থাকে।",
                ur = "`throw` ایرر آبجیکٹ پھینکتا ہے جبکہ `throws` میتھڈ ہیڈر میں ممکنہ ایرر کا اعلان کرتا ہے۔",
                hi = "`throw` अपवाद ऑब्जेक्ट फेंकता है जबकि `throws` मेथड सिग्नेचर में संभावित अपवाद घोषित करता है।",
                ar = "`throw` تطلق كائن استثناء فعلياً، بينما تعلن `throws` في توقيع الدالة عن الاستثناءات المتوقعة.",
                fa_af = "`throw` یک شیء خطا پرتاب می‌کند و `throws` در امضای متد خطاهای احتمالی را اعلام می‌نماید.",
                in_id = "`throw` melempar objek eksepsi secara aktif; `throws` mendeklarasikan potensi eksepsi pada metode."
            ),
            detailedExplanationContent = localized(
                en = "Fail-fast validation (`if (id == null) throw new IllegalArgumentException(...)`) catches bad arguments right at the entry gate instead of corrupting database state later.",
                bn = "মেথডের শুরুতেই ভুল ইনপুট পেলে `throw new IllegalArgumentException(...)` ছুঁড়ে দেওয়া প্রফেশনাল কোডিংয়ের নিয়ম—এতে পরে ডাটাবেসে ভুল তথ্য ঢোকে না।",
                ur = "میتھڈ کے شروع میں ہی غلط ان پٹ پر `IllegalArgumentException` پھینکنا ڈیٹا کو خراب ہونے سے بچاتا ہے۔",
                hi = "मेथड के प्रारंभ में ही गलत इनपुट पर `IllegalArgumentException` फेंकना डेटा भ्रष्टाचार रोकता है।",
                ar = "التحقق المبكر ورمي `IllegalArgumentException` عند المدخل يحمي قاعدة البيانات من القيم الفاسدة.",
                fa_af = "اعتبارسنجی سریع در ابتدای متد با `throw` از ذخیره شدن داده‌های خراب در ادامه برنامه جلوگیری می‌کند.",
                in_id = "Validasi cepat di awal metode dengan `throw` mencegah data rusak masuk lebih jauh ke database."
            ),
            javaExample = """public void withdraw(int amount) throws java.io.IOException {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be > 0");
    }
}""",
            commonMistakeContent = localized(
                en = "Throw specific exceptions (`IllegalArgumentException`, `IllegalStateException`) rather than generic `new RuntimeException(\"error\")`.",
                bn = "সাধারণ `RuntimeException` না ছুঁড়ে সবসময় নির্দিষ্ট কারণ অনুযায়ী `IllegalArgumentException` বা `IllegalStateException` ব্যবহার করুন।",
                ur = "عام `RuntimeException` کی بجائے مخصوص اور واضح ایکسپشن استعمال کریں۔",
                hi = "सामान्य `RuntimeException` के बजाय विशिष्ट `IllegalArgumentException` फेंकें।",
                ar = "استخدم استثناءات محددة مثل `IllegalArgumentException` بدلاً من `RuntimeException` العام.",
                fa_af = "به‌جای `RuntimeException` کلی، همیشه خطای دقیق مانند `IllegalArgumentException` پرتاب کنید.",
                in_id = "Lempar eksepsi spesifik (`IllegalArgumentException`) daripada `RuntimeException` umum."
            )
        ),

        JavaKeywordItem(
            keyword = "synchronized",
            badge = "Thread Lock",
            category = JavaKeywordCategory.ERRORS_THREADS,
            syntax = "synchronized (lock) { sharedBalance += delta; }",
            smaliEquivalent = "monitor-enter v0  ...  monitor-exit v0",
            purposeContent = localized(
                en = "Acquires an exclusive object monitor lock so only ONE thread can execute the critical section at a time.",
                bn = "একসাথে যেন কেবল একটি থ্রেডই শেয়ারড ডাটা পরিবর্তন করতে পারে তার জন্য অবজেক্ট লক (Monitor) দেয়।",
                ur = "ایک وقت میں صرف ایک تھریڈ کو مشترکہ ڈیٹا تبدیل کرنے کی اجازت دینے والا لاک۔",
                hi = "एक समय में केवल एक थ्रेड को साझा डेटा बदलने देने के लिए मॉनिटर लॉक लगाता है।",
                ar = "يفرض قفلاً حصرياً لضمان دخول خيط معالجة واحد فقط إلى القسم الحرج في كل مرة.",
                fa_af = "قفل انحصاری ایجاد می‌کند تا هم‌زمان فقط یک نخ (Thread) بتواند بخش حساس را اجرا کند.",
                in_id = "Mengunci monitor objek sehingga hanya SATU thread yang boleh menjalankan bagian kritis pada satu waktu."
            ),
            detailedExplanationContent = localized(
                en = "Compiles directly to Smali's `monitor-enter` and `monitor-exit` wrapped inside a `.catchall` block so the lock is always released even if an exception occurs!",
                bn = "এটি স্মালিতে `monitor-enter` ও `monitor-exit` অপকোডে রূপান্তরিত হয় এবং কম্পাইলার `.catchall` যোগ করে যেন এরর হলেও লকটি খুলে যায়!",
                ur = "یہ سمالی میں `monitor-enter` اور `monitor-exit` میں بدلتا ہے اور `.catchall` کے ذریعے ایرر کی صورت میں بھی لاک کھول دیتا ہے۔",
                hi = "यह स्माली में `monitor-enter` और `monitor-exit` बनता है तथा `.catchall` द्वारा त्रुटि होने पर भी लॉक खोल देता है।",
                ar = "تترجم إلى `monitor-enter` و `monitor-exit` محاطة بـ `.catchall` لضمان تحرير القفل دائماً.",
                fa_af = "در سمالی به `monitor-enter` و `monitor-exit` همراه با `.catchall` تبدیل می‌شود تا قفل همیشه آزاد گردد.",
                in_id = "Dikompilasi menjadi `monitor-enter` dan `monitor-exit` dengan `.catchall` agar kunci selalu dilepas meski terjadi error."
            ),
            javaExample = """private final Object lock = new Object();
private int counter = 0;

public void incrementSafe() {
    synchronized (lock) { counter++; }
}""",
            commonMistakeContent = localized(
                en = "Locking two objects in opposite orders across threads causes a permanent **Deadlock** where both threads wait forever!",
                bn = "দুটি থ্রেডে উল্টো ক্রমে দুটি লক ধরলে **Deadlock** হয়ে অ্যাপ চিরতরে আটকে যেতে পারে!",
                ur = "دو تھریڈز میں الٹ ترتیب سے لاک لگانے پر **Deadlock** ہو جاتا ہے اور دونوں ہمیشہ کے لیے رک جاتے ہیں!",
                hi = "दो थ्रेड्स में विपरीत क्रम में लॉक लेने से **Deadlock** हो जाता है जहां दोनों थ्रेड सदा के लिए अटक जाते हैं!",
                ar = "قفل كائنين بترتيب متعاكس بين خيطين يسبب جموداً دائماً (**Deadlock**)!",
                fa_af = "قفل کردن دو شیء با ترتیب معکوس در دو نخ باعث بن‌بست دائمی (**Deadlock**) می‌شود!",
                in_id = "Mengunci dua objek dengan urutan terbalik antar thread menyebabkan **Deadlock** permanen!"
            )
        ),

        JavaKeywordItem(
            keyword = "volatile",
            badge = "RAM Visibility",
            category = JavaKeywordCategory.ERRORS_THREADS,
            syntax = "private volatile boolean isRunning = true;",
            smaliEquivalent = ".field private volatile isRunning:Z",
            purposeContent = localized(
                en = "Forces every thread to read/write the variable directly from main RAM instead of stale CPU core caches.",
                bn = "প্রতিটি থ্রেডকে সিপিইউ ক্যাশের বদলে সরাসরি আসল র‍্যাম থেকে সর্বশেষ মান পড়তে ও লিখতে বাধ্য করে।",
                ur = "ہر تھریڈ کو پابند کرتا ہے کہ متغیر کی تازہ ترین قیمت براہِ راست مین ریم سے پڑھے۔",
                hi = "प्रत्येक थ्रेड को CPU कैश के बजाय सीधे मुख्य RAM से नवीनतम मान पढ़ने/लिखने को बाध्य करता है।",
                ar = "يجبر جميع الخيوط على قراءة وكتابة أحدث قيمة مباشرة من الذاكرة الرئيسية بدلاً من مخبأ المعالج.",
                fa_af = "تمام نخ‌ها را ملزم می‌کند مقدار متغیر را مستقیماً از رم اصلی بخوانند نه کش قدیمی پردازنده.",
                in_id = "Memaksa semua thread membaca/menulis nilai terbaru langsung dari RAM utama, bukan cache CPU."
            ),
            detailedExplanationContent = localized(
                en = "Essential for background worker stop flags (`while (isRunning)`) and double-checked locking Singletons (`AppDatabase.INSTANCE`).",
                bn = "ব্যাকগ্রাউন্ড থ্রেড থামানোর ফ্ল্যাগ (`isRunning`) এবং ডাটাবেস Singleton তৈরিতে `volatile` অত্যন্ত জরুরি।",
                ur = "بیک گراؤنڈ تھریڈ روکنے والے فلیگ (`isRunning`) اور سنگلٹن پیٹرن کے لیے `volatile` نہایت اہم ہے۔",
                hi = "बैकग्राउंड थ्रेड स्टॉप फ़्लैग (`isRunning`) और सिंगलटन इंस्टेंस के लिए `volatile` अनिवार्य है।",
                ar = "ضرورية لأعلام إيقاف الخيوط الخلفية ولنمط Singleton الآمن متعدد الخيوط.",
                fa_af = "برای پرچم توقف نخ‌های پس‌زمینه و الگوی Singleton ایمن در برابر چندنخی ضروری است.",
                in_id = "Sangat penting untuk flag penghenti thread latar (`isRunning`) serta pola Singleton thread-safe."
            ),
            javaExample = """private volatile boolean cancelled = false;
public void cancel() { cancelled = true; }""",
            commonMistakeContent = localized(
                en = "`volatile` guarantees visibility, NOT atomicity—`count++` is still NOT thread-safe on a `volatile int` (use `AtomicInteger` instead)!",
                bn = "`volatile` শুধু সর্বশেষ মান দেখায়, কিন্তু `count++` নিরাপদ করে না—কাউন্টারের জন্য `AtomicInteger` ব্যবহার করুন!",
                ur = "`volatile` صرف تازہ قیمت دکھاتا ہے مگر `count++` کو محفوظ نہیں بناتا—اس کے لیے `AtomicInteger` استعمال کریں!",
                hi = "`volatile` केवल दृश्यता देता है, `count++` को सुरक्षित नहीं करता—उसके लिए `AtomicInteger` चुनें!",
                ar = "تضمن `volatile` رؤية القيمة فقط ولا تحمي عملية `count++`؛ استخدم `AtomicInteger` للعدادات!",
                fa_af = "`volatile` تنها دیده‌شدن آخرین مقدار را تضمین می‌کند اما `count++` را ایمن نمی‌سازد؛ از `AtomicInteger` استفاده کنید!",
                in_id = "`volatile` hanya menjamin visibilitas, BUKAN atomisitas `count++`—gunakan `AtomicInteger` untuk penghitung!"
            )
        ),

        // ==================== COLLECTIONS & CORE APIS (5) ====================
        JavaKeywordItem(
            keyword = "ArrayList<E>",
            badge = "Dynamic Array",
            category = JavaKeywordCategory.COLLECTIONS_APIS,
            syntax = "List<String> items = new ArrayList<>();",
            smaliEquivalent = "invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z",
            purposeContent = localized(
                en = "Resizable index-based list offering instant `O(1)` `.get(i)` lookup and fast `.add(item)`.",
                bn = "প্রয়োজন অনুযায়ী ছোট-বড় হওয়া ডায়নামিক লিস্ট, যেখানে ইনডেক্স দিয়ে মুহূর্তেই `.get(i)` ও `.add()` করা যায়।",
                ur = "خودکار سائز بدلنے والی فہرست جس میں `.get(i)` اور `.add()` انتہائی تیز کام کرتے ہیں۔",
                hi = "आकार बदलने वाली डायनामिक सूची जिसमें `.get(i)` और `.add()` तुरंत काम करते हैं।",
                ar = "قائمة ديناميكية قابلة للتمدد توفر وصولاً فورياً بالفهرس `.get(i)` وإضافة سريعة.",
                fa_af = "لیست پویا با قابلیت تغییر اندازه خودکار و دسترسی فوری با اندیس `.get(i)`.",
                in_id = "Daftar dinamis yang ukurannya otomatis menyesuaikan dengan akses instan `.get(i)`."
            ),
            detailedExplanationContent = localized(
                en = "Generics (`<String>`) are checked at compile time and erased to `Object` in DEX bytecode, so Smali inserts a `check-cast v0, Ljava/lang/String;` when reading elements back!",
                bn = "জাভার জেনেরিক্স (`<String>`) কম্পাইলের পর মুছে `Object` হয়ে যায় (Type Erasure)—তাই স্মালিতে লিস্ট থেকে ডাটা বের করার সময় `check-cast` বসে!",
                ur = "کمپائل ہونے پر `<String>` مٹ کر `Object` رہ جاتا ہے، اس لیے سمالی واپسی پر `check-cast` لگاتا ہے!",
                hi = "संकलन पर `<String>` मिटकर `Object` बन जाता है, इसलिए स्माली तत्व निकालते समय `check-cast` जोड़ता है!",
                ar = "تُمسح الأنواع العامة `<String>` عند الترجمة لتصبح `Object`، فيضيف سمالي تعليمة `check-cast` عند القراءة!",
                fa_af = "نوع جنریک `<String>` هنگام کامپایل حذف شده و در سمالی هنگام خواندن عضو دستور `check-cast` قرار می‌گیرد!",
                in_id = "Tipe generik `<String>` dihapus saat kompilasi (Type Erasure), sehingga Smali menyisipkan `check-cast` saat membaca elemen!"
            ),
            javaExample = """List<String> modules = new ArrayList<>();
modules.add("Variables");
modules.add("OOP");
String first = modules.get(0);""",
            commonMistakeContent = localized(
                en = "Removing items directly with `list.remove(x)` inside a `for-each` loop throws `ConcurrentModificationException`—use `removeIf(...)` or `Iterator.remove()`!",
                bn = "`for-each` লুপ চলাকালীন সরাসরি `list.remove()` কল করলে অ্যাপ সাথে সাথে ক্র্যাশ করে—তাই `removeIf` বা `Iterator` ব্যবহার করুন!",
                ur = "`for-each` لوپ کے دوران براہِ راست `list.remove()` چلانے سے `ConcurrentModificationException` کریش آتا ہے!",
                hi = "`for-each` लूप के अंदर सीधे `list.remove()` करने से `ConcurrentModificationException` क्रैश होता है!",
                ar = "حذف عنصر مباشرة بـ `list.remove` داخل حلقة `for-each` يسبب انهيار `ConcurrentModificationException`!",
                fa_af = "حذف مستقیم عضو با `list.remove` در میانه حلقه `for-each` باعث کرش `ConcurrentModificationException` می‌شود!",
                in_id = "Menghapus elemen langsung dengan `list.remove()` di dalam `for-each` memicu `ConcurrentModificationException`!"
            )
        ),

        JavaKeywordItem(
            keyword = "HashMap<K, V>",
            badge = "O(1) Key-Value",
            category = JavaKeywordCategory.COLLECTIONS_APIS,
            syntax = "Map<String, Integer> scores = new HashMap<>();",
            smaliEquivalent = "invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
            purposeContent = localized(
                en = "Lightning-fast `O(1)` dictionary mapping unique keys (`K`) to values (`V`) via `hashCode()` buckets.",
                bn = "ইউনিক চাবি (`Key`) দিয়ে মুহূর্তের মধ্যে (`O(1)`) মান (`Value`) খুঁজে বের করার হ্যাশ ডিকশনারি।",
                ur = "منفرد کلید (Key) کے ذریعے پلک جھپکتے قیمت (Value) تلاش کرنے والی ڈکشنری۔",
                hi = "अद्वितीय कुंजी (`Key`) से तुरंत (`O(1)`) मान (`Value`) खोजने वाला हैश शब्दकोश।",
                ar = "قاموس فائق السرعة يربط المفاتيح الفريدة بالقيم عبر تجزئة `hashCode()`.",
                fa_af = "دیکشنری فوق‌سریع با زمان دسترسی `O(1)` برای نگاشت کلیدهای یکتا به مقادیر.",
                in_id = "Kamus pasangan Kunci-Nilai super cepat `O(1)` berbasis bucket `hashCode()`."
            ),
            detailedExplanationContent = localized(
                en = "Because Java generics don't accept primitives (`Map<String, int>` is illegal), use wrapper types like `Integer`, `Long`, or `Boolean`—Java auto-boxes `scores.put(\"Ali\", 95)` via `Integer.valueOf(95)`!",
                bn = "জেনেরিক্সে `int` লেখা যায় না বলে `Integer` দিতে হয়। আপনি `put(\"Ali\", 95)` লিখলে জাভা নিজেই `Integer.valueOf(95)` দিয়ে সেটিকে বক্স করে নেয় (Autoboxing)!",
                ur = "جینرکس میں `int` نہیں چلتا اس لیے `Integer` لکھا جاتا ہے اور جاوا خودکار طور پر آٹو باکسنگ کر لیتا ہے۔",
                hi = "जेनेरिक्स में `int` मान्य नहीं है, इसलिए `Integer` का उपयोग होता है जिसे जावा स्वतः ऑटो-बॉक्स करता है।",
                ar = "لا تقبل الأنواع العامة `int`، لذا نستخدم `Integer` وتقوم جافا بالتغليف التلقائي عبر `Integer.valueOf`.",
                fa_af = "در جنریک‌ها `int` مجاز نیست، بنابراین از `Integer` استفاده شده و جاوا به‌طور خودکار Autoboxing انجام می‌دهد.",
                in_id = "Generik tidak menerima primitif `int`, sehingga dipakai `Integer` dengan autoboxing `Integer.valueOf()`."
            ),
            javaExample = """Map<String, Integer> xpMap = new HashMap<>();
xpMap.put("Morsalin", 1200);
int xp = xpMap.getOrDefault("Morsalin", 0);""",
            commonMistakeContent = localized(
                en = "Unwrapping `int x = map.get(\"missing\")` throws `NullPointerException` because `get` returns `null`! Always use `getOrDefault(key, 0)`.",
                bn = "ম্যাপে চাবি না থাকলে `map.get(...)` নাল (`null`) ফেরত দেয়, যা সরাসরি `int`-এ রাখলে ক্র্যাশ করে! তাই সবসময় `getOrDefault(key, 0)` লিখুন।",
                ur = "کلید نہ ملنے پر `map.get` نل لوٹاتا ہے جو `int` میں رکھتے ہی کریش کرتا ہے! ہمیشہ `getOrDefault` استعمال کریں۔",
                hi = "कुंजी न मिलने पर `map.get` से `null` आता है जो `int` में रखते ही क्रैश करता है! हमेशा `getOrDefault` लिखें।",
                ar = "وضع نتيجة `map.get` المفقودة في `int` يسبب انهياراً فورياً؛ استخدم دائماً `getOrDefault(key, 0)`!",
                fa_af = "نبود کلید در `map.get` مقدار `null` برمی‌گرداند که انتسابش به `int` کرش می‌دهد؛ از `getOrDefault` استفاده کنید!",
                in_id = "Menyimpan hasil `map.get()` yang kosong ke `int` memicu crash `NullPointerException`; selalu pakai `getOrDefault(k, 0)`!"
            )
        ),

        JavaKeywordItem(
            keyword = "StringBuilder",
            badge = "Mutable Text",
            category = JavaKeywordCategory.COLLECTIONS_APIS,
            syntax = "StringBuilder sb = new StringBuilder(); sb.append(\"v0\");",
            smaliEquivalent = "new-instance v0, Ljava/lang/StringBuilder;\ninvoke-virtual {v0, v1}, ...->append(...)...",
            purposeContent = localized(
                en = "High-speed mutable character buffer for assembling strings inside loops without Heap waste.",
                bn = "লুপের ভেতরে মেমোরি নষ্ট না করে অতি দ্রুত টেক্সট জোড়া লাগানোর পরিবর্তনশীল বাফার।",
                ur = "لوپ کے اندر میموری ضائع کیے بغیر تیزی سے متن جوڑنے والا بفر۔",
                hi = "लूप के भीतर बिना मेमोरी बर्बाद किए तेज़ी से टेक्स्ट जोड़ने वाला परिवर्तनशील बफ़र।",
                ar = "مخزن نصي قابل للتعديل فائق السرعة لتجميع النصوص داخل الحلقات دون هدر الذاكرة.",
                fa_af = "بافر متنی تغییرپذیر و پرسرعت برای چسباندن رشته‌ها در حلقه‌ها بدون هدررفت حافظه.",
                in_id = "Buffer karakter yang dapat diubah dengan cepat untuk merangkai teks di dalam loop."
            ),
            detailedExplanationContent = localized(
                en = "In fact, even a simple `\"Hello \" + name` in Java is compiled by `javac` into `StringBuilder.append(...)` (or `makeConcatWithConstants`) inside Smali!",
                bn = "আপনি জাভায় সাধারণ `\"Hi \" + name` লিখলেও কম্পাইলার পেছনে সেটিকে স্মালির `StringBuilder.append(...)`-এই রূপান্তর করে!",
                ur = "یہاں تک کہ سادہ `\"Hi \" + name` کو بھی کمپائلر سمالی میں `StringBuilder.append` میں بدل دیتا ہے!",
                hi = "यहां तक कि साधारण `\"Hi \" + name` को भी कंपाइलर स्माली में `StringBuilder.append` में बदल देता है!",
                ar = "حتى عملية الدمج البسيطة بـ `+` يحولها المترجم خلف الكواليس إلى `StringBuilder.append` في سمالي!",
                fa_af = "حتی عبارت ساده `\"Hi \" + name` نیز توسط کامپایلر در پشت صحنه به `StringBuilder.append` در سمالی تبدیل می‌شود!",
                in_id = "Bahkan penggabungan `\"Hi \" + name` biasa pun dikompilasi menjadi `StringBuilder.append` di Smali!"
            ),
            javaExample = """StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 5; i++) {
    sb.append("Step ").append(i).append('\n');
}
String report = sb.toString();""",
            commonMistakeContent = localized(
                en = "Concatenating `res = res + line` inside a 10,000-line loop copies the entire string 10,000 times (`O(N²)` freeze)—always create ONE `StringBuilder` outside the loop!",
                bn = "বড় লুপের ভেতরে `s = s + line` লিখলে অ্যাপ মারাত্মক স্লো হয়ে যায়—সবসময় লুপের বাইরে একটি `StringBuilder` বানিয়ে ভেতরে `.append()` করুন!",
                ur = "بڑے لوپ کے اندر `+` سے متن جوڑنا ایپ کو سست کر دیتا ہے—ہمیشہ لوپ سے باہر ایک `StringBuilder` بنائیں!",
                hi = "बड़े लूप में `+` से टेक्स्ट जोड़ना ऐप को धीमा कर देता है—लूप के बाहर एक `StringBuilder` बनाएं!",
                ar = "دمج النصوص بـ `+` داخل حلقة كبيرة يبطئ التطبيق بشدة؛ أنشئ `StringBuilder` واحداً خارج الحلقة!",
                fa_af = "الحاق رشته با `+` در حلقه‌های بزرگ برنامه را به شدت کند می‌کند؛ یک `StringBuilder` بیرون حلقه بسازید!",
                in_id = "Menggabung `s = s + line` di dalam loop ribuan baris membuat aplikasi membeku (`O(N²)`); buat satu `StringBuilder` di luar loop!"
            )
        ),

        JavaKeywordItem(
            keyword = "HashSet<E>",
            badge = "Unique Items",
            category = JavaKeywordCategory.COLLECTIONS_APIS,
            syntax = "Set<String> seenIds = new HashSet<>();",
            smaliEquivalent = "invoke-interface {v0, v1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z",
            purposeContent = localized(
                en = "Unordered collection that automatically rejects duplicates and checks `.contains(x)` in `O(1)` instant time.",
                bn = "ডুপ্লিকেট বা একই মান দুবার ঢুকতে দেয় না এবং মুহূর্তের মধ্যে (`O(1)`) `.contains()` চেক করে।",
                ur = "ایسا مجموعہ جو ڈپلیکیٹ عناصر کو خودکار روکتا ہے اور پلک جھپکتے `.contains()` چیک کرتا ہے۔",
                hi = "डुप्लिकेट मानों को स्वतः रोकने और `O(1)` समय में `.contains()` जांचने वाला संग्रह।",
                ar = "مجموعة تمنع التكرار تلقائياً وتفحص وجود العنصر `.contains()` في زمن فوري `O(1)`.",
                fa_af = "مجموعه‌ای که مقادیر تکراری را نمی‌پذیرد و وجود عضو را در زمان فوری `O(1)` بررسی می‌کند.",
                in_id = "Koleksi elemen unik tanpa duplikat dengan pemeriksaan `.contains()` instan `O(1)`."
            ),
            detailedExplanationContent = localized(
                en = "`ArrayList.contains(x)` scans item-by-item (`O(N)`), whereas `HashSet.contains(x)` jumps straight to the hash bucket (`O(1)`)—ideal for completed lesson IDs or visited URLs!",
                bn = "`ArrayList`-এ কোনো কিছু খুঁজতে শুরু থেকে শেষ পর্যন্ত ঘুরতে হয় (`O(N)`), কিন্তু `HashSet` হ্যাশ কোড দিয়ে এক লাফে (`O(1)`) খুঁজে বের করে!",
                ur = "`ArrayList` میں تلاش کرنے پر پورا لسٹ دیکھنا پڑتا ہے جبکہ `HashSet` ہیش کوڈ سے فوراً تلاش کر لیتا ہے!",
                hi = "`ArrayList` एक-एक कर खोजता है (`O(N)`), जबकि `HashSet` सीधे हैश बकेट से तुरंत (`O(1)`) ढूंढ लेता है!",
                ar = "تبحث `ArrayList` بالتسلسل البطيء، بينما تقفز `HashSet` مباشرة إلى موقع العنصر بسرعة فائقة!",
                fa_af = "`ArrayList` تک‌به‌تک جستجو می‌کند اما `HashSet` مستقیماً با کد هش در لحظه عضو را می‌یابد!",
                in_id = "`ArrayList.contains()` memindai satu-satu, sedangkan `HashSet.contains()` langsung melompat ke bucket hash secara instan!"
            ),
            javaExample = """Set<String> completed = new HashSet<>();
completed.add("java_01");
completed.add("java_01"); // Ignored duplicate!
int count = completed.size(); // 1""",
            commonMistakeContent = localized(
                en = "Custom objects placed in a `HashSet` MUST override BOTH `equals()` and `hashCode()`—otherwise duplicate objects won't be detected!",
                bn = "নিজের বানানো ক্লাসের অবজেক্ট `HashSet`-এ রাখলে অবশ্যই `equals()` ও `hashCode()` দুটোই ওভাররাইড করতে হবে!",
                ur = "اپنی کلاس کے آبجیکٹ کو `HashSet` میں رکھتے وقت `equals()` اور `hashCode()` دونوں لکھنا لازمی ہے!",
                hi = "कस्टम क्लास को `HashSet` में रखने के लिए `equals()` और `hashCode()` दोनों ओवरराइड करना आवश्यक है!",
                ar = "عند وضع كائنات مخصصة في `HashSet` يجب إعادة تعريف `equals()` و `hashCode()` معاً!",
                fa_af = "برای قرار دادن اشیاء سفارشی در `HashSet` باید هر دو متد `equals()` و `hashCode()` بازنویسی شوند!",
                in_id = "Objek kelas kustom di dalam `HashSet` WAJIB meng-override `equals()` sekaligus `hashCode()`!"
            )
        ),

        JavaKeywordItem(
            keyword = "Stream / Optional",
            badge = "Modern Java",
            category = JavaKeywordCategory.COLLECTIONS_APIS,
            syntax = "Optional.ofNullable(name).orElse(\"Guest\");",
            smaliEquivalent = "invoke-static {p0}, Ljava/util/Optional;->ofNullable(...)...",
            purposeContent = localized(
                en = "Declarative functional pipelines (`stream().filter().map()`) and explicit null-safe containers (`Optional<T>`).",
                bn = "আধুনিক ফাংশনাল ডাটা প্রসেসিং (`stream().filter()`) এবং নাল-মুক্ত নিরাপদ বক্স (`Optional<T>`)।",
                ur = "جدید فنکشنل ڈیٹا پائپ لائن (`stream`) اور نل سے محفوظ باکس (`Optional<T>`)۔",
                hi = "आधुनिक फ़ंक्शनल पाइपलाइन (`stream`) और नल-सुरक्षित कंटेनर (`Optional<T>`)।",
                ar = "أنابيب المعالجة الوظيفية الحديثة (`stream`) والحاويات الآمنة من القيم الفارغة (`Optional`).",
                fa_af = "خط لوله پردازش تابعی داده‌ها (`stream`) و ظرف ایمن در برابر نال (`Optional<T>`).",
                in_id = "Pipeline fungsional deklaratif (`stream`) dan pembungkus aman-null (`Optional<T>`)."
            ),
            detailedExplanationContent = localized(
                en = "`Optional<T>` communicates clearly in a method return type that a result might be absent, replacing surprise `null` crashes with `.map(...)` and `.orElse(...)`.",
                bn = "কোনো মেথডে উত্তর নাও পাওয়া যেতে পারে বোঝাতে `null` রিটার্ন করার বদলে `Optional<User>` ব্যবহার করলে কোড অনেক নিরাপদ ও পরিষ্কার হয়।",
                ur = "`null` واپس بھیجنے کے بجائے `Optional<User>` استعمال کرنے سے کوڈ زیادہ محفوظ اور واضح ہو جاتا ہے۔",
                hi = "`null` लौटाने के स्थान पर `Optional<User>` लौटाने से कोड कहीं अधिक सुरक्षित और स्पष्ट बनता है।",
                ar = "استخدام `Optional` كنوع إرجاع يوضح احتمال غياب القيمة ويحمي المستدعي من مفاجآت `null`.",
                fa_af = "برگرداندن `Optional` به‌جای `null` کد را خواناتر کرده و از خطاهای ناگهانی جلوگیری می‌کند.",
                in_id = "Mengembalikan `Optional<T>` jauh lebih aman daripada `null` karena memaksa pemanggil menangani nilai kosong dengan rapi."
            ),
            javaExample = """String label = java.util.Optional.ofNullable(rawTitle)
    .map(String::trim)
    .filter(s -> !s.isEmpty())
    .orElse("Untitled");""",
            commonMistakeContent = localized(
                en = "Calling `optional.get()` directly without checking throws `NoSuchElementException`—always prefer `.orElse(...)` or `.ifPresent(...)`!",
                bn = "যাচাই ছাড়া সরাসরি `optional.get()` কল করলে `null`-এর মতোই ক্র্যাশ করে—তাই সবসময় `.orElse(...)` ব্যবহার করুন!",
                ur = "بغیر چیک کیے براہِ راست `optional.get()` چلانے سے کریش آتا ہے—ہمیشہ `.orElse(...)` استعمال کریں!",
                hi = "बिना जांचे सीधे `optional.get()` बुलाने से क्रैश होता है—हमेशा `.orElse(...)` चुनें!",
                ar = "استدعاء `optional.get()` مباشرة دون فحص يرمي استثناءً؛ استخدم `.orElse(...)` دائماً!",
                fa_af = "فراخوانی مستقیم `optional.get()` بدون بررسی باعث کرش می‌شود؛ همیشه از `.orElse(...)` استفاده کنید!",
                in_id = "Memanggil `optional.get()` langsung tanpa cek akan melempar error; selalu gunakan `.orElse(...)`!"
            )
        )
    )
}
