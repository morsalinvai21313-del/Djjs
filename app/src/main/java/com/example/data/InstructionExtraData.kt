package com.example.data

import com.example.model.InstructionCategory
import com.example.model.SmaliInstruction
import com.example.model.localized

object InstructionExtraData {
    val extraInstructions: List<SmaliInstruction> = listOf(
        // MORE OBJECTS & ARRAYS (3)
        SmaliInstruction(
            opcode = "instance-of",
            hexCode = "0x20",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "instance-of vDest, vObj, LPackage/Type;",
            javaEquivalent = "boolean ok = obj instanceof String;",
            kotlinEquivalent = "val ok = obj is String",
            purposeContent = localized(
                en = "Stores 1 in vDest if vObj is a non-null instance of LType;, otherwise stores 0.",
                bn = "`vObj` যদি নির্দিষ্ট ক্লাসের অবজেক্ট হয় তবে `vDest`-এ `1` রাখে, নাহলে `0` রাখে।",
                ur = "اگر `vObj` مطلوبہ کلاس کا آبجیکٹ ہو تو `vDest` میں `1` ورنہ `0` رکھتا ہے۔",
                hi = "यदि `vObj` उस क्लास का ऑब्जेक्ट है तो `vDest` में `1`, अन्यथा `0` रखता है।",
                ar = "تضع 1 في `vDest` إذا كان الكائن من النوع المحدد، وإلا تضع 0.",
                fa_af = "اگر شیء `vObj` از نوع مشخص‌شده باشد مقدار `1` وگرنه `0` در `vDest` می‌گذارد.",
                in_id = "Menyimpan `1` ke `vDest` jika `vObj` bertipe tersebut, atau `0` jika bukan."
            ),
            detailedExplanationContent = localized(
                en = "Unlike `check-cast`, `instance-of` NEVER throws an exception—even if `vObj` is null (returns 0).",
                bn = "`check-cast`-এর মতো এটি কখনো ক্র্যাশ করে না; `vObj` খালি (`null`) হলে নিরাপদে `0` দেয়।",
                ur = "یہ کبھی کریش نہیں کرتا؛ اگر آبجیکٹ `null` ہو تو محفوظ طریقے سے `0` دیتا ہے۔",
                hi = "यह कभी क्रैश नहीं करता; `null` होने पर सुरक्षित रूप से `0` देता है।",
                ar = "لا ترمي استثناءً أبداً؛ إذا كان الكائن فارغاً ترجع 0 بأمان.",
                fa_af = "هرگز کرش نمی‌کند و اگر شیء `null` باشد با امنیت کامل `0` برمی‌گرداند.",
                in_id = "Tidak pernah melempar error; jika `vObj` null hasilnya aman `0`."
            ),
            smaliExample = "instance-of v0, p1, Ljava/lang/String;\nif-eqz v0, :not_string",
            commonMistakesContent = localized(
                en = "Overwriting `p1` by using `instance-of p1, p1, ...` destroys your object reference!",
                bn = "`vDest` হিসেবে একই অবজেক্ট রেজিস্টার দিলে আসল অবজেক্ট মুছে `0/1` হয়ে যাবে!",
                ur = "نتیجے کے لیے وہی آبجیکٹ رجسٹر استعمال کرنے سے آبجیکٹ ضائع ہو جاتا ہے!",
                hi = "`vDest` में वही ऑब्जेक्ट रजिस्टर लिखने से ऑब्जेक्ट संदर्भ मिट जाता है!",
                ar = "وضع النتيجة في نفس مسجل الكائن يمسح مرجع الكائن الأصلي!",
                fa_af = "قرار دادن خروجی در همان رجستر شیء باعث پاک شدن ارجاع اصلی شیء می‌شود!",
                in_id = "Menaruh hasil di register objek yang sama akan menghapus referensi objeknya!"
            )
        ),
        SmaliInstruction(
            opcode = "new-array",
            hexCode = "0x23",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "new-array vDest, vSize, [Type",
            javaEquivalent = "int[] scores = new int[5];",
            kotlinEquivalent = "val scores = IntArray(5)",
            purposeContent = localized(
                en = "Allocates a new array of length vSize and stores the array reference in vDest.",
                bn = "`vSize` দৈর্ঘ্যের নতুন অ্যারে তৈরি করে `vDest` রেজিস্টারে রাখে।",
                ur = "`vSize` لمبائی کی نئی ایرے بنا کر ریفرنس `vDest` میں رکھتا ہے۔",
                hi = "`vSize` लंबाई का नया ऐरे बनाकर रेफरेंस `vDest` में रखता है।",
                ar = "إنشاء مصفوفة جديدة بطول `vSize` وحفظ مرجعها في `vDest`.",
                fa_af = "ساخت یک آرایه جدید به طول `vSize` و ذخیره ارجاع آن در `vDest`.",
                in_id = "Membuat array baru sepanjang `vSize` dan menyimpannya ke `vDest`."
            ),
            detailedExplanationContent = localized(
                en = "No `<init>` call is required for arrays! Elements default to `0`, `false`, or `null`. Pair with `array-length`, `aget`, and `aput`.",
                bn = "অ্যারে তৈরির পর `<init>` লাগে না! সব ঘর স্বয়ংক্রিয়ভাবে `0` বা `null` থাকে। দৈর্ঘ্য মাপতে `array-length` এবং মান পড়তে/লিখতে `aget`/`aput` ব্যবহৃত হয়।",
                ur = "ایرے کے لیے `<init>` کی ضرورت نہیں ہوتی! تمام خانے خودکار `0` یا `null` ہوتے ہیں۔",
                hi = "ऐरे के लिए `<init>` की आवश्यकता नहीं होती! सभी तत्व स्वतः `0` या `null` होते हैं।",
                ar = "لا تحتاج المصفوفات إلى `<init>`، وتُهيأ عناصرها تلقائياً بالصفر أو `null`.",
                fa_af = "آرایه‌ها نیازی به `<init>` ندارند و خانه‌های آن به صورت خودکار صفر می‌شوند.",
                in_id = "Array tidak memerlukan `<init>`; elemen otomatis bernilai `0` atau `null`."
            ),
            smaliExample = "const/4 v0, 0x5\nnew-array v1, v0, [I\narray-length v2, v1",
            commonMistakesContent = localized(
                en = "Forgetting the `[` bracket in `[I`—writing `I` instead of `[I` fails assembly.",
                bn = "টাইপের আগে `[` চিহ্ন না দিলে (যেমন `[I` না লিখে `I` লিখলে) কম্পাইল এরর হবে।",
                ur = "ٹائپ سے پہلے `[` لگانا نہ بھولیں جیسے `[I`۔",
                hi = "टाइप से पहले `[` ब्रैकेट लगाना न भूलें (जैसे `[I`)।",
                ar = "نسيان قوس المصفوفة `[` قبل رمز النوع مثل `[I`.",
                fa_af = "فراموش کردن علامت `[` پیش از نوع داده مانند `[I`.",
                in_id = "Lupa menulis tanda `[` di depan tipe array seperti `[I`."
            )
        ),
        SmaliInstruction(
            opcode = "aget",
            hexCode = "0x44",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "aget vDest, vArray, vIndex",
            javaEquivalent = "int item = arr[i];",
            kotlinEquivalent = "val item = arr[i]",
            purposeContent = localized(
                en = "Reads element at index vIndex from array vArray into vDest (use aput to write).",
                bn = "`vArray` অ্যারের `vIndex` পজিশনের মানটি পড়ে `vDest` রেজিস্টারে আনে (লেখার জন্য `aput`)।",
                ur = "`vArray` کے انڈیکس `vIndex` سے قیمت پڑھ کر `vDest` میں لاتا ہے (لکھنے کے لیے `aput`)۔",
                hi = "`vArray` के `vIndex` स्थान से मान पढ़कर `vDest` में रखता है (लिखने हेतु `aput`)।",
                ar = "قراءة العنصر ذي الفهرس `vIndex` من المصفوفة `vArray` إلى `vDest`.",
                fa_af = "خواندن عنصر خانه `vIndex` از آرایه `vArray` در `vDest` (برای نوشتن: `aput`).",
                in_id = "Membaca elemen indeks `vIndex` dari `vArray` ke `vDest` (tulis dengan `aput`)."
            ),
            detailedExplanationContent = localized(
                en = "Variants: `aget-object` (`String[]`), `aget-byte` (`byte[]`), `aget-boolean` (`boolean[]`), `aget-wide` (`long[]`/`double[]`).",
                bn = "অ্যারের ধরন অনুযায়ী রূপ: `String[]` হলে `aget-object`, `byte[]` হলে `aget-byte`, আর মান বসানোর জন্য `aput` / `aput-object`।",
                ur = "`String[]` کے لیے `aget-object`، `byte[]` کے لیے `aget-byte` اور لکھنے کے لیے `aput-object`۔",
                hi = "`String[]` हेतु `aget-object`, `byte[]` हेतु `aget-byte`, और लिखने हेतु `aput-object`।",
                ar = "للمصفوفات النصية `aget-object` وللبايت `aget-byte` وللكتابة `aput-object`.",
                fa_af = "برای آرایه اشیاء `aget-object`، بایت `aget-byte` و برای نوشتن `aput-object`.",
                in_id = "Varian: `aget-object`, `aget-byte`, `aget-boolean`, `aget-wide`, serta `aput*`."
            ),
            smaliExample = "const/4 v0, 0x0\naget v1, p0, v0    # v1 = p0[0]",
            commonMistakesContent = localized(
                en = "Passing a literal number like `0x0` instead of a register for `vIndex`—index MUST be in a register.",
                bn = "ইনডেক্স হিসেবে সরাসরি `0x0` লেখা যায় না—আগে রেজিস্টারে সংখ্যাটি লোড করে সেই রেজিস্টার দিতে হয়।",
                ur = "انڈیکس کے طور پر براہ راست نمبر نہیں بلکہ رجسٹر دینا لازمی ہے۔",
                hi = "इंडेक्स में सीधे `0x0` नहीं लिखा जा सकता—उसे पहले किसी रजिस्टर में लोड करें।",
                ar = "لا يمكن كتابة رقم الفهرس مباشرة بل يجب تحميله في مسجل أولاً.",
                fa_af = "نمی‌توان عدد اندیس را مستقیم نوشت؛ ابتدا باید آن را در یک رجستر بارگذاری کرد.",
                in_id = "Indeks tidak boleh berupa angka langsung; wajib dimuat ke register dulu."
            )
        ),

        // BRANCHES (4)
        SmaliInstruction(
            opcode = "if-eqz",
            hexCode = "0x38",
            category = InstructionCategory.BRANCHES,
            syntax = "if-eqz vAA, :target_label",
            javaEquivalent = "if (flag == false) / if (obj == null)",
            kotlinEquivalent = "if (!flag) / if (obj == null)",
            purposeContent = localized(
                en = "Jumps to :target_label if vAA equals 0 (0, false, or null).",
                bn = "`vAA`-এর মান যদি `0` (`false` বা `null`) হয়, তবে লাফ দিয়ে `:target_label`-এ চলে যায়।",
                ur = "اگر `vAA` کی قیمت `0` (`false` یا `null`) ہو تو `:target_label` پر کود جاتا ہے۔",
                hi = "यदि `vAA` का मान `0` (`false` या `null`) है, तो `:target_label` पर कूद जाता है।",
                ar = "القفز إلى العلامة `:target_label` إذا كانت قيمة المسجل تساوي صفراً أو `false` أو `null`.",
                fa_af = "پرش به برچسب `:target_label` در صورتی که مقدار رجستر `0` (`false` یا `null`) باشد.",
                in_id = "Melompat ke `:target_label` jika `vAA` bernilai `0` (`false` atau `null`)."
            ),
            detailedExplanationContent = localized(
                en = "Opposite of `if-nez` (`!= 0`). Used constantly for null checks (`if (user == null)`) and boolean gating.",
                bn = "এর ঠিক বিপরীত হলো `if-nez` (মান শূন্য না হলে জাম্প)। কোনো অবজেক্ট `null` কি না বা ইউজার প্রিমিয়াম কি না তা চেক করতে এটি সবচেয়ে বেশি দেখা যায়।",
                ur = "اس کا الٹ `if-nez` ہے۔ نل چیک اور بولین شرائط کے لیے سب سے زیادہ استعمال ہوتا ہے۔",
                hi = "इसका विपरीत `if-nez` है। नल जांच और बूलियन शर्तों में यह सबसे अधिक दिखता है।",
                ar = "عكسها `if-nez`. تُستخدم بكثرة لفحص القيم الفارغة والشروط المنطقية.",
                fa_af = "برعکس دستور `if-nez` است و بیشترین کاربرد را در بررسی `null` و شرط‌های بولین دارد.",
                in_id = "Kebalikan dari `if-nez`. Sangat sering dipakai untuk cek `null` dan boolean."
            ),
            smaliExample = "if-eqz p0, :is_null\nconst-string v0, \"Valid\"\nreturn-object v0\n:is_null\nconst-string v0, \"Empty\"\nreturn-object v0",
            commonMistakesContent = localized(
                en = "Jumping to a `:label` name that does not exist inside the same `.method` block.",
                bn = "একই `.method`-এর ভেতরে `:label`-টি না থাকলে অ্যাসেম্বল করার সময় এরর দেবে।",
                ur = "ایسے `:label` پر جمپ کرنا جو اس میتھڈ میں موجود نہ ہو ایرر دیتا ہے۔",
                hi = "ऐसे `:label` पर कूदना जो उसी `.method` में मौजूद न हो, त्रुटि देता है।",
                ar = "القفز إلى علامة `:label` غير معرفة داخل نفس الدالة.",
                fa_af = "پرش به برچسبی که در داخل همان بلوک `.method` تعریف نشده باشد خطا می‌دهد.",
                in_id = "Melompat ke `:label` yang tidak didefinisikan di dalam `.method` yang sama."
            )
        ),
        SmaliInstruction(
            opcode = "if-nez",
            hexCode = "0x39",
            category = InstructionCategory.BRANCHES,
            syntax = "if-nez vAA, :target_label",
            javaEquivalent = "if (flag == true) / if (obj != null)",
            kotlinEquivalent = "if (flag) / if (obj != null)",
            purposeContent = localized(
                en = "Jumps to :target_label if vAA is NOT zero (true, non-zero int, or non-null object).",
                bn = "`vAA`-এর মান শূন্য না হলে (`true` বা নন-নাল অবজেক্ট হলে) `:target_label`-এ জাম্প করে।",
                ur = "اگر `vAA` صفر نہ ہو (`true` یا غیر نل آبجیکٹ) تو `:target_label` پر جمپ کرتا ہے۔",
                hi = "यदि `vAA` शून्य नहीं है (`true` या गैर-नल ऑब्जेक्ट), तो `:target_label` पर कूदता है।",
                ar = "القفز إلى `:target_label` إذا لم تكن قيمة المسجل صفراً (`true` أو كائن غير فارغ).",
                fa_af = "پرش به `:target_label` اگر مقدار رجستر مخالف صفر (`true` یا غیر نال) باشد.",
                in_id = "Melompat ke `:target_label` jika `vAA` TIDAK nol (`true` atau objek tidak null)."
            ),
            detailedExplanationContent = localized(
                en = "Also part of the zero-comparison family alongside `if-ltz` (`<0`), `if-gez` (`>=0`), `if-gtz` (`>0`), and `if-lez` (`<=0`).",
                bn = "জিরো-তুলনা পরিবারের অন্য সদস্যরা হলো: `if-ltz` (`<0`), `if-gez` (`>=0`), `if-gtz` (`>0`), এবং `if-lez` (`<=0`)।",
                ur = "اسی خاندان میں `if-ltz` (`<0`)، `if-gez` (`>=0`)، `if-gtz` (`>0`) اور `if-lez` (`<=0`) شامل ہیں۔",
                hi = "इसी परिवार में `if-ltz` (`<0`), `if-gez` (`>=0`), `if-gtz` (`>0`) और `if-lez` (`<=0`) शामिल हैं।",
                ar = "تنتمي لعائلة المقارنة بالصفر مع `if-ltz` و `if-gez` و `if-gtz` و `if-lez`.",
                fa_af = "هم‌خانواده با دستورات `if-ltz` (`<0`)، `if-gez` (`>=0`)، `if-gtz` (`>0`) و `if-lez` (`<=0`).",
                in_id = "Sekeluarga dengan `if-ltz` (`<0`), `if-gez` (`>=0`), `if-gtz` (`>0`), dan `if-lez` (`<=0`)."
            ),
            smaliExample = "if-nez p1, :skip_default\nconst-string p1, \"Guest\"\n:skip_default",
            commonMistakesContent = localized(
                en = "Using `if-nez` on 64-bit `long` or `float` directly—first run `cmp-long` or `cmpl-float`!",
                bn = "সরাসরি ৬৪-বিট `long` বা `float`-এর ওপর `if-nez` চলে না—আগে `cmp-long` বা `cmpl-float` চালাতে হয়!",
                ur = "64-بٹ `long` یا `float` پر براہ راست نہیں چلتا—پہلے `cmp-long` یا `cmpl-float` چلائیں!",
                hi = "64-बिट `long` या `float` पर सीधे नहीं चलता—पहले `cmp-long` या `cmpl-float` चलाएँ!",
                ar = "لا تعمل مباشرة على `long` أو `float`؛ نفذ `cmp-long` أو `cmpl-float` أولاً!",
                fa_af = "مستقیماً روی `long` یا `float` کار نمی‌کند؛ ابتدا باید `cmp-long` یا `cmpl-float` بزنید!",
                in_id = "Tidak bisa langsung pada `long`/`float`; jalankan `cmp-long` atau `cmpl-float` dulu!"
            )
        ),
        SmaliInstruction(
            opcode = "if-ge",
            hexCode = "0x35",
            category = InstructionCategory.BRANCHES,
            syntax = "if-ge vA, vB, :target_label",
            javaEquivalent = "if (a >= b) // jumps when vA >= vB",
            kotlinEquivalent = "if (a >= b)",
            purposeContent = localized(
                en = "Compares two 32-bit registers and jumps to :target_label if vA >= vB.",
                bn = "দুটি রেজিস্টার তুলনা করে `vA >= vB` সত্য হলে `:target_label`-এ লাফ দেয়।",
                ur = "دو رجسٹرز کا موازنہ کر کے اگر `vA >= vB` ہو تو `:target_label` پر جمپ کرتا ہے۔",
                hi = "दो रजिस्टरों की तुलना कर यदि `vA >= vB` हो तो `:target_label` पर कूदता है।",
                ar = "مقارنة مسجلين والقفز إلى `:target_label` إذا كان `vA >= vB`.",
                fa_af = "مقایسه دو رجستر و پرش به `:target_label` در صورت برقراری شرط `vA >= vB`.",
                in_id = "Membandingkan dua register dan melompat ke `:target_label` jika `vA >= vB`."
            ),
            detailedExplanationContent = localized(
                en = "Full 2-register set: `if-eq` (`==`), `if-ne` (`!=`), `if-lt` (`<`), `if-ge` (`>=`), `if-gt` (`>`), `if-le` (`<=`). Standard loop exit condition (`if (i >= len) break`)!",
                bn = "২-রেজিস্টারের সব শর্ত: `if-eq` (`==`), `if-ne` (`!=`), `if-lt` (`<`), `if-ge` (`>=`), `if-gt` (`>`), `if-le` (`<=`)। `for (i = 0; i < n; i++)` লুপ শেষ করতে `if-ge i, n, :loop_end` ব্যবহৃত হয়!",
                ur = "دیگر اقسام: `if-eq`، `if-ne`، `if-lt`، `if-gt`، `if-le`۔ لوپ ختم کرنے کی شرط میں ہمیشہ نظر آتا ہے!",
                hi = "अन्य रूप: `if-eq`, `if-ne`, `if-lt`, `if-gt`, `if-le`। `for` लूप समाप्त करने में हमेशा दिखता है!",
                ar = "بقية العائلة: `if-eq`, `if-ne`, `if-lt`, `if-gt`, `if-le`، وتُشاهد دائماً كشرط خروج من الحلقات!",
                fa_af = "سایر اعضا: `if-eq`, `if-ne`, `if-lt`, `if-gt`, `if-le`؛ شرط استاندارد خروج از حلقه `for`!",
                in_id = "Keluarga lengkap: `if-eq`, `if-ne`, `if-lt`, `if-gt`, `if-le`; standar keluar loop `for`!"
            ),
            smaliExample = "if-ge v0, v1, :loop_done    # exit loop once index v0 >= length v1",
            commonMistakesContent = localized(
                en = "Only `v0..v15` can be encoded directly in 2-register `if-*` instructions.",
                bn = "২-রেজিস্টারের `if-*` অপকোডে কেবল `v0` থেকে `v15` পর্যন্ত রেজিস্টার ব্যবহার করা যায়।",
                ur = "اس میں صرف `v0` سے `v15` تک کے رجسٹرز استعمال ہو سکتے ہیں۔",
                hi = "दो-रजिस्टर `if-*` में केवल `v0..v15` रजिस्टर ही सीधे उपयोग हो सकते हैं।",
                ar = "تقبل هذه التعليمات المسجلات من `v0` إلى `v15` فقط.",
                fa_af = "در دستورات شرطی دورجستری فقط می‌توان از `v0` تا `v15` استفاده کرد.",
                in_id = "Hanya register `v0..v15` yang bisa dipakai langsung di instruksi `if-*`."
            )
        ),
        SmaliInstruction(
            opcode = "goto",
            hexCode = "0x28",
            category = InstructionCategory.BRANCHES,
            syntax = "goto :target_label",
            javaEquivalent = "continue / break / unconditional jump",
            kotlinEquivalent = "continue / break",
            purposeContent = localized(
                en = "Unconditionally jumps execution to :target_label (forward or backward).",
                bn = "কোনো শর্ত ছাড়াই সরাসরি `:target_label` লাইনে লাফ দেয় (লুপ ঘোরানো বা else এড়াতে)।",
                ur = "بغیر کسی شرط کے براہ راست `:target_label` پر جمپ کرتا ہے۔",
                hi = "बिना किसी शर्त के सीधे `:target_label` पर कूदता है (लूप या शाखा स्किप करने हेतु)।",
                ar = "قفز غير مشروط مباشرة إلى العلامة `:target_label` للأمام أو للخلف.",
                fa_af = "پرش بدون قید و شرط به برچسب `:target_label` (به جلو یا عقب برای حلقه).",
                in_id = "Melompat tanpa syarat ke `:target_label` (maju atau mundur untuk loop)."
            ),
            detailedExplanationContent = localized(
                en = "`baksmali`/`smali` automatically widens `goto` (`0x28`) to `goto/16` (`0x29`) or `goto/32` (`0x2a`) for long methods.",
                bn = "লম্বা মেথডে দূরত্ব বেশি হলে স্মালি অ্যাসেম্বলার নিজেই `goto/16` বা `goto/32` ব্যবহার করে।",
                ur = "لمبے میتھڈز میں فاصلہ زیادہ ہو تو `goto/16` یا `goto/32` استعمال ہوتا ہے۔",
                hi = "लंबे मेथड में दूरी अधिक होने पर `goto/16` या `goto/32` उपयोग होता है।",
                ar = "في الدوال الطويلة تتحول تلقائياً إلى `goto/16` أو `goto/32`.",
                fa_af = "در متدهای طولانی به صورت خودکار از `goto/16` یا `goto/32` استفاده می‌شود.",
                in_id = "Pada metode panjang otomatis melebar menjadi `goto/16` atau `goto/32`."
            ),
            smaliExample = ":loop_head\nadd-int/lit8 v0, v0, 0x1\nif-lt v0, p0, :done\ngoto :loop_head\n:done",
            commonMistakesContent = localized(
                en = "Writing `goto :loop_head` without an exit check creates an infinite UI-freezing loop!",
                bn = "বের হওয়ার শর্ত ছাড়া পেছনের লেবেলে `goto` দিলে অনন্ত লুপ (Infinite Loop) হয়ে অ্যাপ হ্যাং করবে!",
                ur = "باہر نکلنے کی شرط کے بغیر `goto` لگانے سے لامحدود لوپ بن کر ایپ ہینگ ہو جاتی ہے!",
                hi = "निकास शर्त के बिना `goto` लगाने से अनंत लूप बनकर ऐप फ़्रीज़ हो जाता है!",
                ar = "وضع `goto` للخلف دون شرط خروج يخلق حلقة لا نهائية تجمد التطبيق!",
                fa_af = "پرش به عقب بدون شرط خروج باعث حلقه بی‌نهایت و قفل شدن برنامه می‌شود!",
                in_id = "Menulis `goto` mundur tanpa syarat keluar membuat infinite loop yang membekukan UI!"
            )
        ),

        // INVOCATIONS (5)
        SmaliInstruction(
            opcode = "invoke-virtual",
            hexCode = "0x6e",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-virtual {vObj, args...}, LClass;->method(Params)Ret",
            javaEquivalent = "builder.append(\"Hi\");",
            kotlinEquivalent = "builder.append(\"Hi\")",
            purposeContent = localized(
                en = "Calls a normal public/protected overridable method on object vObj (1st register in braces).",
                bn = "`vObj` অবজেক্টের ওপর যেকোনো সাধারণ public/protected মেথড কল করে (`{}`-এর প্রথম রেজিস্টারটি অবজেক্ট)।",
                ur = "`vObj` آبجیکٹ پر عام پبلک یا پروٹیکٹڈ میتھڈ کال کرتا ہے (پہلا رجسٹر آبجیکٹ ہوتا ہے)۔",
                hi = "ऑब्जेक्ट `vObj` पर सामान्य पब्लिक/प्रोटेक्टेड मेथड कॉल करता है (`{}` में पहला रजिस्टर ऑब्जेक्ट है)।",
                ar = "استدعاء دالة عامة أو محمية قابلة للتجاوز على الكائن `vObj`.",
                fa_af = "فراخوانی متد معمولی عمومی روی شیء `vObj` (اولین رجستر داخل آکولاد شیء است).",
                in_id = "Memanggil metode publik/protected normal pada objek `vObj` (register pertama di `{}`)."
            ),
            detailedExplanationContent = localized(
                en = "Uses dynamic vtable dispatch so overridden subclass methods run automatically. If passing >5 register slots, use `invoke-virtual/range {v0 .. v5}`.",
                bn = "পলিমরফিজম মেনে সাবক্লাসের ওভাররাইড করা মেথড স্বয়ংক্রিয়ভাবে চালায়। ৫টির বেশি রেজিস্টার স্লট হলে `invoke-virtual/range` লিখতে হয়।",
                ur = "اوور رائیڈ شدہ میتھڈ خودکار چلاتا ہے۔ 5 سے زیادہ رجسٹر سلاٹس ہوں تو `invoke-virtual/range` لکھیں۔",
                hi = "ओवरराइड किए गए सबक्लास मेथड को स्वतः चलाता है। >5 स्लॉट होने पर `invoke-virtual/range` लिखें।",
                ar = "تدعم تعدد الأشكال تلقائياً؛ وعند تجاوز 5 مسجلات استخدم `invoke-virtual/range`.",
                fa_af = "متدهای بازنویسی‌شده زیرکلاس را خودکار اجرا می‌کند؛ برای بیش از ۵ رجستر از `/range` استفاده کنید.",
                in_id = "Mendukung polimorfisme subclass; jika >5 slot register gunakan `invoke-virtual/range`."
            ),
            smaliExample = "invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            commonMistakesContent = localized(
                en = "Forgetting to pass the target object as the FIRST register inside `{vObj, ...}`.",
                bn = "`{}` ব্র্যাকেটের ভেতরে একদম প্রথমে টার্গেট অবজেক্ট রেজিস্টার না দিলে ভেরিফায়ার এরর দেবে।",
                ur = "`{}` کے اندر سب سے پہلے آبجیکٹ رجسٹر دینا نہ بھولیں۔",
                hi = "`{}` के भीतर सबसे पहले टारगेट ऑब्जेक्ट रजिस्टर देना न भूलें।",
                ar = "نسيان تمرير مسجل الكائن كأول عنصر داخل القوسين `{vObj, ...}`.",
                fa_af = "فراموش کردن قرار دادن رجستر شیء به عنوان اولین عضو داخل `{vObj, ...}`.",
                in_id = "Lupa menyertakan register objek sebagai elemen pertama di dalam `{vObj, ...}`."
            )
        ),
        SmaliInstruction(
            opcode = "invoke-direct",
            hexCode = "0x70",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-direct {vObj, args...}, LClass;->method(Params)Ret",
            javaEquivalent = "new Intent() / this.privateHelper()",
            kotlinEquivalent = "Intent() / privateHelper()",
            purposeContent = localized(
                en = "Calls constructors (<init>) and private non-overridable instance methods.",
                bn = "যেকোনো কনস্ট্রাক্টর (`<init>`) এবং ক্লাসের নিজস্ব `private` মেথড কল করতে ব্যবহৃত হয়।",
                ur = "کنسٹرکٹرز (`<init>`) اور کلاس کے `private` میتھڈز کال کرنے کے لیے استعمال ہوتا ہے۔",
                hi = "कंस्ट्रक्टर (`<init>`) और क्लास के `private` मेथड कॉल करने के लिए उपयोग होता है।",
                ar = "استدعاء دوال البناء (`<init>`) والدوال الخاصة (`private`) غير القابلة للتجاوز.",
                fa_af = "فراخوانی سازنده‌ها (`<init>`) و متدهای خصوصی (`private`) کلاس.",
                in_id = "Memanggil konstruktor (`<init>`) dan metode `private` milik kelas."
            ),
            detailedExplanationContent = localized(
                en = "Skips vtable lookup because constructors and `private` methods cannot be overridden by subclasses.",
                bn = "কনস্ট্রাক্টর ও `private` মেথড কখনো সাবক্লাসে ওভাররাইড হয় না, তাই ডালভিক সরাসরি দ্রুতগতিতে এগুলো কল করে।",
                ur = "چونکہ کنسٹرکٹر اور پرائیویٹ میتھڈ اوور رائیڈ نہیں ہوتے اس لیے یہ براہ راست تیز رفتاری سے چلتا ہے۔",
                hi = "चूंकि कंस्ट्रक्टर और `private` मेथड ओवरराइड नहीं होते, इसलिए यह सीधे तेज़ी से चलता है।",
                ar = "تتجاوز البحث في جدول الدوال الافتراضية لسرعة تنفيذ البانيات والدوال الخاصة.",
                fa_af = "چون سازنده‌ها و متدهای خصوصی بازنویسی نمی‌شوند، مستقیم و سریع اجرا می‌گردد.",
                in_id = "Melewati pencarian vtable karena `<init>` dan `private` tidak bisa di-override."
            ),
            smaliExample = "new-instance v0, Landroid/os/Bundle;\ninvoke-direct {v0}, Landroid/os/Bundle;-><init>()V",
            commonMistakesContent = localized(
                en = "Calling `<init>` with `invoke-virtual` instead of `invoke-direct` is rejected by ART.",
                bn = "`<init>` কনস্ট্রাক্টরে ভুল করে `invoke-virtual` দিলে অ্যাপ চালু হওয়ার আগেই রিজেক্ট হবে।",
                ur = "`<init>` کے ساتھ `invoke-virtual` لکھنے سے کوڈ مسترد ہو جاتا ہے۔",
                hi = "`<init>` पर `invoke-virtual` लगाने से ART तुरंत अस्वीकार कर देता है।",
                ar = "استدعاء `<init>` عبر `invoke-virtual` يرفضه النظام فوراً.",
                fa_af = "فراخوانی `<init>` با `invoke-virtual` توسط اندروید رد می‌شود.",
                in_id = "Memanggil `<init>` dengan `invoke-virtual` pasti ditolak oleh ART."
            )
        ),
        SmaliInstruction(
            opcode = "invoke-static",
            hexCode = "0x71",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-static {args...}, LClass;->staticMethod(Params)Ret",
            javaEquivalent = "Log.d(\"TAG\", \"Hello\");",
            kotlinEquivalent = "Log.d(\"TAG\", \"Hello\")",
            purposeContent = localized(
                en = "Calls a static class method (NO 'this' object register in braces!).",
                bn = "ক্লাসের যেকোনো `static` মেথড কল করে (এখানে `{}` ব্র্যাকেটে কোনো `this` অবজেক্ট থাকে না!)।",
                ur = "کلاس کا `static` میتھڈ کال کرتا ہے (`{}` میں کوئی آبجیکٹ رجسٹر نہیں ہوتا!)۔",
                hi = "क्लास का `static` मेथड कॉल करता है (`{}` में कोई `this` ऑब्जेक्ट रजिस्टर नहीं होता!)।",
                ar = "استدعاء دالة ثابتة `static` مباشرة (لا يوجد مسجل كائن `this` داخل الأقواس!).",
                fa_af = "فراخوانی متد `static` کلاس (بدون وجود رجستر شیء `this` در داخل آکولاد!).",
                in_id = "Memanggil metode `static` kelas (TIDAK ada register objek `this` di dalam `{}`!)."
            ),
            detailedExplanationContent = localized(
                en = "Every register inside `{...}` maps 1-to-1 to the method's parameter list `(Params)`.",
                bn = "`{v0, v1}`-এর ভেতরের প্রতিটি রেজিস্টার সরাসরি মেথডের প্যারামিটার লিস্টের সাথে ১-টু-১ মিলে যায়। যেমন `Log.d`, `Integer.parseInt`, `Math.max`।",
                ur = "`{}` کا ہر رجسٹر براہ راست میتھڈ کے پیرامیٹرز سے میل کھاتا ہے جیسے `Log.d`۔",
                hi = "`{}` के भीतर का हर रजिस्टर सीधे मेथड पैरामीटर से मेल खाता है, जैसे `Log.d` या `Math.max`।",
                ar = "كل مسجل داخل `{...}` يقابل معاملات الدالة مباشرة مثل `Log.d` و `Math.max`.",
                fa_af = "هر رجستر داخل `{...}` مستقیماً متناظر با پارامترهای ورودی متد است، مانند `Log.d`.",
                in_id = "Setiap register di `{...}` langsung memetakan 1-ke-1 dengan daftar parameter `(Params)`."
            ),
            smaliExample = "const-string v0, \"Audit\"\nconst-string v1, \"Step OK\"\ninvoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I",
            commonMistakesContent = localized(
                en = "Accidentally passing `p0` (`this`) before arguments causes an extra-register count mismatch.",
                bn = "ভুল করে আর্গুমেন্টের আগে `p0` ঢুকিয়ে দিলে প্যারামিটার সংখ্যা বেড়ে গিয়ে এরর হবে।",
                ur = "غلطی سے `p0` شامل کر دینے سے پیرامیٹرز کی تعداد غلط ہو جاتی ہے۔",
                hi = "गलती से आर्ग्यूमेंट से पहले `p0` देने से रजिस्टर गिनती गलत हो जाती है।",
                ar = "إدراج `p0` بالخطأ قبل المعاملات يسبب عدم تطابق عدد المعاملات.",
                fa_af = "اضافه کردن اشتباهی `p0` پیش از آرگومان‌ها باعث عدم تطابق تعداد پارامترها می‌شود.",
                in_id = "Tidak sengaja menyertakan `p0` sebelum argumen membuat jumlah parameter salah."
            )
        ),
        SmaliInstruction(
            opcode = "invoke-super",
            hexCode = "0x6f",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-super {p0, args...}, LParentClass;->method(Params)Ret",
            javaEquivalent = "super.onCreate(savedInstanceState);",
            kotlinEquivalent = "super.onCreate(savedInstanceState)",
            purposeContent = localized(
                en = "Calls the parent superclass's implementation of an overridden method.",
                bn = "ওভাররাইড করা মেথডের ভেতর থেকে প্যারেন্ট ক্লাসের (`super`) আসল মেথড কল করে।",
                ur = "اوور رائیڈ میتھڈ کے اندر سے پیرنٹ کلاس (`super`) کا میتھڈ کال کرتا ہے۔",
                hi = "ओवरराइड किए गए मेथड के भीतर से पैरेंट क्लास (`super`) का मेथड कॉल करता है।",
                ar = "استدعاء نسخة الفئة الأب (`super`) من دالة تم تجاوزها.",
                fa_af = "فراخوانی نسخه کلاس پدر (`super`) از یک متد بازنویسی‌شده.",
                in_id = "Memanggil implementasi kelas induk (`super`) dari metode yang di-override."
            ),
            detailedExplanationContent = localized(
                en = "Essential in Android Activity/Fragment lifecycle overrides (`onCreate`, `onResume`, `onDestroy`).",
                bn = "অ্যান্ড্রয়েডের প্রতিটি Activity/Fragment-এর `onCreate`, `onResume` মেথডে প্যারেন্ট ক্লাস সচল রাখতে এটি অপরিহার্য।",
                ur = "اینڈرائیڈ ایکٹیویٹی کے `onCreate` اور دیگر لائف سائیکل میتھڈز میں لازمی ہوتا ہے۔",
                hi = "एंड्रॉइड एक्टिविटी के `onCreate` व अन्य लाइफसाइकिल मेथड में यह अनिवार्य होता है।",
                ar = "ضرورية جداً في دوال دورة حياة الواجهات مثل `onCreate` في أندرويد.",
                fa_af = "در متدهای چرخه حیات اندروید مانند `onCreate` کاملاً ضروری است.",
                in_id = "Sangat penting pada override siklus hidup Android seperti `onCreate`."
            ),
            smaliExample = "invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V",
            commonMistakesContent = localized(
                en = "Replacing `invoke-super` with `invoke-virtual` inside `onCreate` causes infinite recursion (`StackOverflowError`)!",
                bn = "`onCreate`-এর ভেতরে `invoke-super` বদলে `invoke-virtual` দিলে মেথড নিজেকেই বারবার কল করে অ্যাপ ক্র্যাশ করাবে!",
                ur = "`invoke-super` کی جگہ `invoke-virtual` لکھنے سے لامحدود کالز (`StackOverflowError`) ہو جاتی ہیں!",
                hi = "`invoke-super` की जगह `invoke-virtual` लिखने से अनंत रिकर्शन (`StackOverflowError`) हो जाता है!",
                ar = "استبدالها بـ `invoke-virtual` داخل نفس الدالة يسبب استدعاءً ذاتياً لا نهائياً!",
                fa_af = "تبدیل `invoke-super` به `invoke-virtual` باعث فراخوانی بازگشتی بی‌نهایت می‌شود!",
                in_id = "Mengganti `invoke-super` dengan `invoke-virtual` memicu rekursi tak terbatas!"
            )
        ),
        SmaliInstruction(
            opcode = "invoke-interface",
            hexCode = "0x72",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-interface {vObj, args...}, LInterface;->method(Params)Ret",
            javaEquivalent = "list.size(); / prefs.getBoolean(...);",
            kotlinEquivalent = "list.size / prefs.getBoolean(...)",
            purposeContent = localized(
                en = "Calls a method through an interface type such as List, Map, SharedPreferences, or Runnable.",
                bn = "`List`, `Map`, `SharedPreferences` বা যেকোনো ইন্টারফেস টাইপের মেথড কল করে।",
                ur = "`List`، `Map`، `SharedPreferences` یا کسی بھی انٹرفیس کے میتھڈ کو کال کرتا ہے۔",
                hi = "`List`, `Map`, `SharedPreferences` जैसे किसी इंटरफ़ेस के माध्यम से मेथड कॉल करता है।",
                ar = "استدعاء دالة عبر مرجع واجهة برمجية مثل `List` أو `SharedPreferences`.",
                fa_af = "فراخوانی متد از طریق یک اینترفیس مانند `List`، `Map` یا `SharedPreferences`.",
                in_id = "Memanggil metode melalui referensi interface seperti `List` atau `SharedPreferences`."
            ),
            detailedExplanationContent = localized(
                en = "Because `SharedPreferences` and `java.util.List` are interfaces (not concrete classes), Android dispatches their methods via interface table (`itable`) lookup.",
                bn = "`SharedPreferences` এবং `java.util.List` যেহেতু ক্লাস নয় বরং Interface, তাই এগুলোর মেথড কল করতে `invoke-virtual` নয়, `invoke-interface` লাগে!",
                ur = "چونکہ `List` اور `SharedPreferences` انٹرفیس ہیں، اس لیے ان پر `invoke-interface` لگتا ہے!",
                hi = "चूंकि `List` और `SharedPreferences` इंटरफ़ेस हैं, इसलिए उन पर `invoke-interface` लगता है!",
                ar = "بما أن `List` و `SharedPreferences` واجهات وليست فئات، فيلزم استخدام `invoke-interface`!",
                fa_af = "چون `List` و `SharedPreferences` اینترفیس هستند نه کلاس، باید از `invoke-interface` استفاده شود!",
                in_id = "Karena `List` & `SharedPreferences` adalah interface, wajib memakai `invoke-interface`!"
            ),
            smaliExample = "invoke-interface {v0}, Ljava/util/List;->size()I\nmove-result v1",
            commonMistakesContent = localized(
                en = "Using `invoke-virtual` on `Ljava/util/List;` throws `IncompatibleClassChangeError`!",
                bn = "`Ljava/util/List;`-এর ওপর `invoke-virtual` লিখলে রানটাইমে `IncompatibleClassChangeError` ক্র্যাশ হবে!",
                ur = "انٹرفیس پر `invoke-virtual` لکھنے سے `IncompatibleClassChangeError` آتا ہے!",
                hi = "इंटरफ़ेस पर `invoke-virtual` चलाने से `IncompatibleClassChangeError` आता है!",
                ar = "استخدام `invoke-virtual` مع واجهة يرمي خطأ `IncompatibleClassChangeError`!",
                fa_af = "استفاده از `invoke-virtual` روی اینترفیس باعث خطای `IncompatibleClassChangeError` می‌شود!",
                in_id = "Memakai `invoke-virtual` pada interface memicu `IncompatibleClassChangeError`!"
            )
        ),

        // ARITHMETIC & BITWISE (4)
        SmaliInstruction(
            opcode = "add-int",
            hexCode = "0x90",
            category = InstructionCategory.ARITHMETIC,
            syntax = "add-int vDest, vA, vB",
            javaEquivalent = "int sum = a + b;",
            kotlinEquivalent = "val sum = a + b",
            purposeContent = localized(
                en = "Adds two 32-bit integers (vA + vB) and writes the sum into vDest.",
                bn = "দুটি ৩২-বিট পূর্ণসংখ্যা (`vA + vB`) যোগ করে যোগফল `vDest` রেজিস্টারে রাখে।",
                ur = "دو 32-بٹ نمبروں (`vA + vB`) کو جمع کر کے نتیجہ `vDest` میں رکھتا ہے۔",
                hi = "दो 32-बिट पूर्णांकों (`vA + vB`) को जोड़कर योगफल `vDest` में रखता है।",
                ar = "جمع عددين صحيحين (`vA + vB`) وتخزين الناتج في المسجل `vDest`.",
                fa_af = "جمع کردن دو عدد ۳۲ بیتی (`vA + vB`) و ذخیره حاصل در `vDest`.",
                in_id = "Menjumlahkan dua integer 32-bit (`vA + vB`) dan menyimpan hasilnya di `vDest`."
            ),
            detailedExplanationContent = localized(
                en = "Same pattern applies to `sub-int`, `mul-int`, `div-int`, and `rem-int` (`%`), plus `add-long`, `add-float`, and `add-double`.",
                bn = "একই নিয়মে বিয়োগ `sub-int`, গুণ `mul-int`, ভাগ `div-int` এবং ভাগশেষ `rem-int` (`%`) কাজ করে।",
                ur = "اسی اصول پر تفریق `sub-int`، ضرب `mul-int`، تقسیم `div-int` اور باقی `rem-int` کام کرتے ہیں۔",
                hi = "इसी नियम पर घटाव `sub-int`, गुणा `mul-int`, भाग `div-int` और शेषफल `rem-int` काम करते हैं।",
                ar = "نفس القاعدة تنطبق على الطرح `sub-int` والضرب `mul-int` والقسمة `div-int` والباقي `rem-int`.",
                fa_af = "همین الگو برای تفریق `sub-int`، ضرب `mul-int`، تقسیم `div-int` و باقیمانده `rem-int` برقرار است.",
                in_id = "Pola yang sama berlaku untuk `sub-int`, `mul-int`, `div-int`, dan `rem-int` (`%`)."
            ),
            smaliExample = "add-int v0, p0, p1    # v0 = p0 + p1",
            commonMistakesContent = localized(
                en = "Adding floats with `add-int` corrupts IEEE-754 bits—use `add-float` for `F` values.",
                bn = "দশমিক `float` সংখ্যার ওপর `add-int` চালালে ভুল মান আসবে—তার জন্য `add-float` লিখুন।",
                ur = "فلوٹ نمبروں پر `add-int` نہ چلائیں بلکہ `add-float` استعمال کریں۔",
                hi = "फ़्लोट मानों पर `add-int` न चलाएँ—उनके लिए `add-float` लिखें।",
                ar = "استخدام `add-int` مع الأعداد العشرية يفسد النتيجة؛ استخدم `add-float`.",
                fa_af = "جمع اعداد اعشاری با `add-int` مقدار را خراب می‌کند؛ از `add-float` استفاده کنید.",
                in_id = "Menjumlahkan float dengan `add-int` merusak bit; gunakan `add-float`."
            )
        ),
        SmaliInstruction(
            opcode = "add-int/lit8",
            hexCode = "0xd8",
            category = InstructionCategory.ARITHMETIC,
            syntax = "add-int/lit8 vDest, vSrc, #+CC",
            javaEquivalent = "i++; // or int next = x + 10;",
            kotlinEquivalent = "i++ // or val next = x + 10",
            purposeContent = localized(
                en = "Adds an 8-bit signed constant (-128..127) directly to vSrc and stores in vDest.",
                bn = "অতিরিক্ত রেজিস্টার ছাড়াই সরাসরি ছোট সংখ্যা (-128..127) যোগ করে (`i++` বা `i--` করতে সেরা!)।",
                ur = "اضافی رجسٹر کے بغیر چھوٹا عدد (-128..127) براہ راست جمع کرتا ہے (`i++` کے لیے بہترین)۔",
                hi = "अतिरिक्त रजिस्टर के बिना छोटा स्थिरांक (-128..127) सीधे जोड़ता है (`i++` हेतु सर्वोत्तम)।",
                ar = "إضافة رقم ثابت صغير (-128..127) مباشرة دون الحاجة لمسجل إضافي (`i++`).",
                fa_af = "جمع مستقیم عدد ثابت کوچک (-128..127) بدون نیاز به رجستر اضافی (`i++`).",
                in_id = "Menambahkan konstanta 8-bit (-128..127) langsung tanpa register tambahan (`i++`)."
            ),
            detailedExplanationContent = localized(
                en = "Notice there is NO `sub-int/lit8`! Compilers subtract constants by adding a negative literal (`add-int/lit8 v0, v0, -0x1` for `i--`). `rsub-int/lit8` computes `constant - vSrc`!",
                bn = "মজার তথ্য: স্মালিতে কোনো `sub-int/lit8` নেই! কম্পাইলার १ কমানোর (`i--`) জন্য ঋণাত্মক সংখ্যা যোগ করে: `add-int/lit8 v0, v0, -0x1`!",
                ur = "دلچسپ بات: سمالی میں `sub-int/lit8` نہیں ہوتا! کم کرنے (`i--`) کے لیے منفی عدد جمع کیا جاتا ہے: `add-int/lit8 v0, v0, -0x1`!",
                hi = "रोचक तथ्य: स्माली में `sub-int/lit8` नहीं होता! `i--` करने के लिए ऋणात्मक मान जोड़ा जाता है: `add-int/lit8 v0, v0, -0x1`!",
                ar = "معلومة مهمة: لا توجد تعليمة `sub-int/lit8`، بل يُطرح الثابت بإضافة رقم سالب مثل `-0x1`!",
                fa_af = "نکته جالب: در سمالی دستور `sub-int/lit8` وجود ندارد و برای تفریق (`i--`) عدد منفی `-0x1` جمع می‌شود!",
                in_id = "Fakta menarik: tidak ada `sub-int/lit8`; untuk `i--` kompiler menulis `add-int/lit8 v0, v0, -0x1`!"
            ),
            smaliExample = "add-int/lit8 v0, v0, 0x1     # v0++\nadd-int/lit8 v1, v1, -0x5    # v1 -= 5",
            commonMistakesContent = localized(
                en = "Constants outside -128..127 require `add-int/lit16` instead of `add-int/lit8`.",
                bn = "সংখ্যাটি `-128` থেকে `127`-এর বাইরে হলে `add-int/lit16` ব্যবহার করতে হবে।",
                ur = "اگر عدد `-128..127` سے باہر ہو تو `add-int/lit16` استعمال کریں۔",
                hi = "`-128..127` से बाहर के मान के लिए `add-int/lit16` का उपयोग करें।",
                ar = "للثوابت خارج النطاق -128..127 استخدم `add-int/lit16`.",
                fa_af = "برای اعداد خارج از بازه `-128..127` از دستور `add-int/lit16` استفاده کنید.",
                in_id = "Untuk angka di luar -128..127 gunakan `add-int/lit16`."
            )
        ),
        SmaliInstruction(
            opcode = "xor-int/lit8",
            hexCode = "0xdf",
            category = InstructionCategory.ARITHMETIC,
            syntax = "xor-int/lit8 vDest, vSrc, #+CC",
            javaEquivalent = "boolean inverted = !isActive; // or x ^ mask",
            kotlinEquivalent = "val inverted = !isActive",
            purposeContent = localized(
                en = "Performs bitwise XOR with an 8-bit mask (with 0x1, cleanly flips a boolean true <-> false!).",
                bn = "বিটওয়াইজ XOR অপারেশন করে (`0x1` দিলে বুলিয়ান `true` উল্টে `false` এবং `false` উল্টে `true` হয়ে যায়!)।",
                ur = "بٹ وائز XOR کرتا ہے (`0x1` کے ساتھ بولین `true` کو `false` اور `false` کو `true` بنا دیتا ہے!)۔",
                hi = "बिटवाइज़ XOR करता है (`0x1` के साथ बूलियन `true` को `false` और `false` को `true` में पलट देता है!)।",
                ar = "عملية XOR المنطقية (مع `0x1` تعكس القيمة المنطقية بين صح وخطأ فوراً!).",
                fa_af = "عملیات XOR بیتی (با عدد `0x1` مقدار بولین را بین `true` و `false` معکوس می‌کند!).",
                in_id = "Operasi XOR bitwise (dengan `0x1` membalik boolean `true` <-> `false`!)."
            ),
            detailedExplanationContent = localized(
                en = "Bitwise family also includes `and-int`, `or-int`, `shl-int` (`<<`), `shr-int` (`>>`), and `ushr-int` (`>>>`).",
                bn = "বিটওয়াইজ পরিবারের অন্য অপকোডগুলো হলো: `and-int` (`&`), `or-int` (`|`), `shl-int` (`<<`), এবং `shr-int` (`>>`)।",
                ur = "اسی خاندان میں `and-int`، `or-int`، `shl-int` (`<<`) اور `shr-int` (`>>`) شامل ہیں۔",
                hi = "बिटवाइज़ परिवार में `and-int`, `or-int`, `shl-int` (`<<`) और `shr-int` (`>>`) भी हैं।",
                ar = "تشمل العائلة المنطقية أيضاً `and-int` و `or-int` والإزاحة `shl-int` و `shr-int`.",
                fa_af = "سایر دستورات بیتی شامل `and-int`، `or-int` و شیفت‌های `shl-int` و `shr-int` هستند.",
                in_id = "Keluarga bitwise juga mencakup `and-int`, `or-int`, `shl-int` (`<<`), dan `shr-int` (`>>`)."
            ),
            smaliExample = "xor-int/lit8 v0, p0, 0x1    # v0 = !p0",
            commonMistakesContent = localized(
                en = "Using `not-int` on a boolean gives `-2` (`0xfffffffe`), NOT `0`! Always flip booleans with `xor-int/lit8 ..., 0x1`.",
                bn = "বুলিয়ান উল্টাতে কখনো `not-int` দেবেন না (তাতে `-2` আসবে)—সবসময় `xor-int/lit8 ..., 0x1` ব্যবহার করবেন!",
                ur = "بولین الٹنے کے لیے `not-int` نہ لگائیں بلکہ ہمیشہ `xor-int/lit8 ..., 0x1` استعمال کریں!",
                hi = "बूलियन पलटने के लिए कभी `not-int` न लगाएँ—हमेशा `xor-int/lit8 ..., 0x1` लिखें!",
                ar = "لا تعكس القيمة المنطقية بـ `not-int` بل استخدم دائماً `xor-int/lit8 ..., 0x1`!",
                fa_af = "هرگز برای معکوس کردن بولین از `not-int` استفاده نکنید؛ همیشه `xor-int/lit8 ..., 0x1` بنویسید!",
                in_id = "Jangan membalik boolean dengan `not-int`; selalu gunakan `xor-int/lit8 ..., 0x1`!"
            )
        ),

        // CONVERSIONS (1)
        SmaliInstruction(
            opcode = "int-to-long",
            hexCode = "0x81",
            category = InstructionCategory.CONVERSIONS,
            syntax = "int-to-long vDestPair, vSrcInt",
            javaEquivalent = "long wide = (long) count;",
            kotlinEquivalent = "val wide = count.toLong()",
            purposeContent = localized(
                en = "Widens a 32-bit int in vSrcInt into a 64-bit long register pair (vDest, vDest+1).",
                bn = "`vSrcInt`-এর ৩২-বিট পূর্ণসংখ্যাকে ৬৪-বিট `long` জোড়ায় (`vDest, vDest+1`) রূপান্তর করে।",
                ur = "32-بٹ انٹیجر کو 64-بٹ `long` رجسٹر جوڑے (`vDest, vDest+1`) میں تبدیل کرتا ہے۔",
                hi = "32-बिट `int` को 64-बिट `long` रजिस्टर जोड़े (`vDest, vDest+1`) में बदलता है।",
                ar = "توسيع عدد صحيح 32-بت إلى عدد طويل 64-بت في زوج مسجلات متتاليين.",
                fa_af = "تبدیل عدد صحیح ۳۲ بیتی به عدد ۶۴ بیتی `long` در جفت رجستر (`vDest, vDest+1`).",
                in_id = "Melebarkan `int` 32-bit menjadi pasangan register `long` 64-bit (`vDest, vDest+1`)."
            ),
            detailedExplanationContent = localized(
                en = "Other primitive casts: `int-to-float`, `int-to-double`, `long-to-int`, `float-to-int`, `double-to-int`, `int-to-byte`, `int-to-char`, `int-to-short`.",
                bn = "অন্যান্য প্রিমিটিভ রূপান্তর অপকোড: `int-to-float`, `int-to-double`, `long-to-int`, `float-to-int`, `int-to-byte`, `int-to-char`।",
                ur = "دیگر ٹائپ تبدیلیاں: `int-to-float`، `long-to-int`، `float-to-int`، `int-to-byte` وغیرہ۔",
                hi = "अन्य प्रिमिटिव कास्ट: `int-to-float`, `long-to-int`, `float-to-int`, `int-to-byte`, `int-to-char`।",
                ar = "تحويلات أخرى: `int-to-float`, `long-to-int`, `float-to-int`, `int-to-byte`, `int-to-char`.",
                fa_af = "سایر دستورات تبدیل نوع: `int-to-float`, `long-to-int`, `float-to-int`, `int-to-byte`.",
                in_id = "Konversi lain: `int-to-float`, `long-to-int`, `float-to-int`, `int-to-byte`, `int-to-char`."
            ),
            smaliExample = "int-to-long v0, p0    # fills BOTH v0 and v1!\nreturn-wide v0",
            commonMistakesContent = localized(
                en = "Remember `vDest` now occupies TWO slots (`v0` and `v1`), so `.locals` must be at least 2!",
                bn = "মনে রাখবেন ফলাফলটি ২টি স্লট (`v0` ও `v1`) দখল করে, তাই `.locals` অন্তত ২ হতে হবে!",
                ur = "یاد رکھیں نتیجہ دو سلاٹس (`v0` اور `v1`) لیتا ہے، اس لیے `.locals` کم از کم 2 رکھیں!",
                hi = "याद रखें परिणाम दो स्लॉट (`v0` और `v1`) लेता है, इसलिए `.locals` कम से कम 2 रखें!",
                ar = "تذكر أن النتيجة تشغل مسجلين (`v0` و `v1`)، فيجب أن يكون `.locals` على الأقل 2!",
                fa_af = "به یاد داشته باشید خروجی دو خانه (`v0` و `v1`) می‌گیرد، پس `.locals` حداقل باید ۲ باشد!",
                in_id = "Ingat hasilnya memakan 2 slot (`v0` & `v1`), sehingga `.locals` minimal harus 2!"
            )
        ),

        // EXCEPTIONS & LOCKS (3)
        SmaliInstruction(
            opcode = "move-exception",
            hexCode = "0x0d",
            category = InstructionCategory.EXCEPTIONS,
            syntax = "move-exception vAA",
            javaEquivalent = "catch (Exception e) { ... }",
            kotlinEquivalent = "catch (e: Exception) { ... }",
            purposeContent = localized(
                en = "Captures the thrown Throwable object at the VERY FIRST line of a :catch handler.",
                bn = "`:catch` ব্লকের একদম প্রথম লাইনে থ্রো হওয়া এরর (`Exception`) অবজেক্টটিকে `vAA`-তে ধরে।",
                ur = "`:catch` بلاک کی بالکل پہلی لائن پر آنے والے `Exception` آبجیکٹ کو پکڑتا ہے۔",
                hi = "`:catch` हैंडलर की सबसे पहली लाइन पर फेंके गए `Exception` ऑब्जेक्ट को `vAA` में पकड़ता है।",
                ar = "التقاط كائن الاستثناء المرمي في أول سطر تماماً من معالج `:catch`.",
                fa_af = "دریافت شیء خطای پرتاب‌شده در اولین خط بخش مدیریت خطا (`:catch`).",
                in_id = "Menangkap objek `Throwable` tepat di baris pertama blok `:catch`."
            ),
            detailedExplanationContent = localized(
                en = "Paired with `.catch LExceptionType; {:try_start .. :try_end} :catch_label`.",
                bn = "`.catch` ডিরেক্টিভ যখন এরর ধরে `:catch_0` লেবেলে পাঠায়, তখন প্রথম লাইনে `move-exception v0` দিয়ে সেটি রিসিভ করতে হয়।",
                ur = "جب `.catch` ایرر پکڑ کر `:catch_0` پر بھیجتا ہے تو پہلی لائن میں اسے لکھا جاتا ہے۔",
                hi = "जब `.catch` किसी त्रुटि को `:catch_0` पर भेजता है, तो पहली लाइन में इसे लिखा जाता है।",
                ar = "تقترن دائماً بتوجيه `.catch` الذي يحول التنفيذ إلى علامة المعالجة عند حدوث خطأ.",
                fa_af = "همراه با دایرکتیو `.catch` کار می‌کند و خطای رخ‌داده را در ابتدای برچسب تحویل می‌گیرد.",
                in_id = "Dipasangkan dengan direktif `.catch {:try_start .. :try_end} :catch_0`."
            ),
            smaliExample = ":catch_0\nmove-exception v0\ninvoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V",
            commonMistakesContent = localized(
                en = "Placing ANY instruction before `move-exception` at `:catch_0` fails bytecode verification.",
                bn = "`:catch_0` লেবেলের পর `move-exception`-এর আগে অন্য কোনো লাইন লিখলে ভেরিফায়ার এরর দেবে।",
                ur = "`:catch_0` کے بعد `move-exception` سے پہلے کوئی اور لائن لکھنے سے ایرر آتا ہے۔",
                hi = "`:catch_0` पर `move-exception` से पहले कोई भी अन्य निर्देश लिखना अवैध है।",
                ar = "وضع أي تعليمة قبل `move-exception` عند علامة المعالجة يرفضه الفاحص.",
                fa_af = "نوشتن هر دستوری قبل از `move-exception` در ابتدای `:catch_0` باعث خطا می‌شود.",
                in_id = "Menaruh instruksi apa pun sebelum `move-exception` di `:catch_0` akan gagal."
            )
        ),
        SmaliInstruction(
            opcode = "throw",
            hexCode = "0x27",
            category = InstructionCategory.EXCEPTIONS,
            syntax = "throw vAA",
            javaEquivalent = "throw new IllegalArgumentException(\"Bad input\");",
            kotlinEquivalent = "throw IllegalArgumentException(\"Bad input\")",
            purposeContent = localized(
                en = "Throws the Throwable exception object stored in register vAA.",
                bn = "`vAA` রেজিস্টারে থাকা এরর (`Exception`) অবজেক্টটিকে থ্রো করে মেথড থামিয়ে দেয়।",
                ur = "`vAA` رجسٹر میں موجود ایکسپشن آبجیکٹ کو تھرو کر کے میتھڈ روک دیتا ہے۔",
                hi = "`vAA` रजिस्टर में रखे `Throwable` एक्सेप्शन ऑब्जेक्ट को फेंकता है।",
                ar = "رمي كائن الاستثناء المخزن في المسجل `vAA` وإيقاف المسار الحالي.",
                fa_af = "پرتاب کردن شیء خطای موجود در رجستر `vAA` و توقف اجرای عادی متد.",
                in_id = "Melempar objek eksepsi `Throwable` yang tersimpan di register `vAA`."
            ),
            detailedExplanationContent = localized(
                en = "Like `return*`, `throw` terminates the current execution branch cleanly for the ART verifier.",
                bn = "`return`-এর মতোই `throw` বর্তমান কোড শাখাটি সমাপ্ত করে; এর নিচে (নতুন লেবেল ছাড়া) কোনো কোড চলবে না।",
                ur = "`return` کی طرح `throw` بھی موجودہ شاخ کو مکمل طور پر ختم کر دیتا ہے۔",
                hi = "`return` की तरह `throw` भी वर्तमान निष्पादन शाखा को समाप्त करता है।",
                ar = "مثل تعليمات الإرجاع، تنهي `throw` المسار البرمجي الحالي أمام فاحص ART.",
                fa_af = "مانند `return`، دستور `throw` نیز مسیر فعلی اجرای کد را پایان می‌دهد.",
                in_id = "Seperti `return*`, `throw` mengakhiri jalur eksekusi cabang saat ini."
            ),
            smaliExample = "new-instance v0, Ljava/lang/IllegalStateException;\nconst-string v1, \"Locked\"\ninvoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V\nthrow v0",
            commonMistakesContent = localized(
                en = "Throwing a register holding `0x0` (`null`) throws a `NullPointerException` instead!",
                bn = "`vAA`-তে যদি `null` থাকে তবে সেটি নিজেই `NullPointerException` থ্রো করবে!",
                ur = "اگر رجسٹر میں `null` ہو تو `NullPointerException` آ جاتا ہے!",
                hi = "यदि रजिस्टर में `null` हो तो वह स्वयं `NullPointerException` फेंकता है!",
                ar = "إذا كان المسجل يحمل `null` فسيُرمى `NullPointerException` بدلاً منه!",
                fa_af = "اگر مقدار درون رجستر `null` باشد، خطای `NullPointerException` پرتاب می‌شود!",
                in_id = "Jika `vAA` berisi `null`, runtime akan melempar `NullPointerException`!"
            )
        ),
        SmaliInstruction(
            opcode = "monitor-enter",
            hexCode = "0x1d",
            category = InstructionCategory.EXCEPTIONS,
            syntax = "monitor-enter vAA  /  monitor-exit vAA",
            javaEquivalent = "synchronized (lockObj) { ... }",
            kotlinEquivalent = "synchronized(lockObj) { ... }",
            purposeContent = localized(
                en = "Acquires (and releases via monitor-exit) a thread synchronization lock on object vAA.",
                bn = "মাল্টি-থ্রেডিংয়ে নিরাপদ কাজের জন্য `vAA` অবজেক্টে থ্রেড লক বসায় (`synchronized` ব্লক)।",
                ur = "ملٹی تھریڈنگ میں حفاظت کے لیے `vAA` آبجیکٹ پر لاک لگاتا ہے (`synchronized`)۔",
                hi = "मल्टी-थ्रेडिंग सुरक्षा के लिए ऑब्जेक्ट `vAA` पर `synchronized` लॉक लगाता है।",
                ar = "حجز قفل تزامن الخيوط على الكائن `vAA` (وتحريره عبر `monitor-exit`).",
                fa_af = "قفل کردن شیء `vAA` برای همگام‌سازی تردها (`synchronized`) و آزادسازی با `monitor-exit`.",
                in_id = "Mengunci sinkronisasi thread pada objek `vAA` (dilepas dengan `monitor-exit`)."
            ),
            detailedExplanationContent = localized(
                en = "Compilers always wrap `monitor-exit` inside a `.catchall` block so an exception never leaves the lock stuck forever (deadlock).",
                bn = "কোনো এরর ঘটলেও লক যেন চিরতরে আটকে না থাকে (Deadlock), তাই কম্পাইলার সবসময় `.catchall` ব্লকের ভেতরে `monitor-exit` রাখে।",
                ur = "ڈیڈ لاک سے بچنے کے لیے کمپائلر ہمیشہ `.catchall` کے اندر بھی `monitor-exit` رکھتا ہے۔",
                hi = "डेडलॉक से बचने के लिए कंपाइलर हमेशा `.catchall` ब्लॉक में भी `monitor-exit` रखता है।",
                ar = "يحيط المترجم دائماً `monitor-exit` بكتلة `.catchall` لمنع تجمد القفل عند حدوث خطأ.",
                fa_af = "کامپایلر همیشه `monitor-exit` را داخل `.catchall` هم قرار می‌دهد تا قفل هرگز گیر نکند.",
                in_id = "Kompiler selalu membungkus `monitor-exit` dengan `.catchall` guna mencegah deadlock."
            ),
            smaliExample = "monitor-enter p0\n# thread-safe critical section\nmonitor-exit p0",
            commonMistakesContent = localized(
                en = "Forgetting `monitor-exit` on an early `return` path freezes waiting threads permanently!",
                bn = "`monitor-exit` না দিয়ে মেথড থেকে `return` করলে অ্যাপ চিরতরে ফ্রিজ (ANR) হয়ে যাবে!",
                ur = "`monitor-exit` کیے بغیر `return` کرنے سے ایپ مستقل فریز ہو جاتی ہے!",
                hi = "`monitor-exit` किए बिना `return` करने से अन्य थ्रेड हमेशा के लिए अटक जाते हैं!",
                ar = "الخروج من الدالة دون استدعاء `monitor-exit` يجمد الخيوط الأخرى للأبد!",
                fa_af = "خروج از متد بدون اجرای `monitor-exit` باعث قفل دائمی برنامه (ANR) می‌شود!",
                in_id = "Keluar metode tanpa `monitor-exit` akan membekukan thread lain selamanya!"
            )
        )
    )
}
