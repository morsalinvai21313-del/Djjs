package com.example.util

import com.example.localization.AppLanguage

data class SmaliAnalysisResult(
    val isValid: Boolean,
    val declaredLocals: Int,
    val highestLocalUsed: Int,
    val highestParamUsed: Int,
    val labelsFound: List<String>,
    val issues: List<SmaliIssue>,
    val summary: String
)

data class SmaliIssue(
    val lineNumber: Int,
    val severity: IssueSeverity,
    val message: String,
    val suggestion: String
)

enum class IssueSeverity {
    ERROR,
    WARNING,
    INFO
}

object SmaliAnalyzer {
    fun analyze(code: String, language: AppLanguage = AppLanguage.ENGLISH): SmaliAnalysisResult {
        val lines = code.lines()
        val issues = mutableListOf<SmaliIssue>()
        var inMethod = false
        var declaredLocals = -1
        var highestLocalUsed = -1
        var highestParamUsed = -1
        var hasReturn = false
        val definedLabels = mutableSetOf<String>()
        val referencedLabels = mutableListOf<Pair<Int, String>>()
        var methodStartLine = -1

        for ((index, line) in lines.withIndex()) {
            val lineNum = index + 1
            val activePart = line.substringBefore('#').trim()
            if (activePart.isEmpty()) continue

            if (activePart.startsWith(".method")) {
                if (inMethod) {
                    issues.add(
                        SmaliIssue(
                            lineNum,
                            IssueSeverity.ERROR,
                            msgNestedMethod(language),
                            fixNestedMethod(language)
                        )
                    )
                }
                inMethod = true
                methodStartLine = lineNum
                declaredLocals = -1
                hasReturn = false
            }

            if (activePart.startsWith(".end method")) {
                if (!inMethod) {
                    issues.add(
                        SmaliIssue(
                            lineNum,
                            IssueSeverity.ERROR,
                            msgOrphanEndMethod(language),
                            fixOrphanEndMethod(language)
                        )
                    )
                } else if (!hasReturn) {
                    issues.add(
                        SmaliIssue(
                            lineNum,
                            IssueSeverity.WARNING,
                            msgMissingReturn(language),
                            fixMissingReturn(language)
                        )
                    )
                }
                inMethod = false
            }

            if (activePart.startsWith("return") || activePart.startsWith("throw")) {
                hasReturn = true
            }

            if (activePart.startsWith(".locals") || activePart.startsWith(".registers")) {
                val parts = activePart.split(Regex("\\s+"))
                if (parts.size >= 2) {
                    declaredLocals = parts[1].toIntOrNull() ?: -1
                }
            }

            if (activePart.startsWith(":")) {
                val labelName = activePart.split(Regex("\\s+"))[0]
                definedLabels.add(labelName)
            }

            val branchTargetMatch = Regex("(:[a-zA-Z0-9_]+)").findAll(activePart)
            for (match in branchTargetMatch) {
                if (!activePart.startsWith(":")) {
                    referencedLabels.add(Pair(lineNum, match.value))
                }
            }

            val registerMatches = Regex("\\b([vp])([0-9]+)\\b").findAll(activePart)
            for (match in registerMatches) {
                val prefix = match.groupValues[1]
                val num = match.groupValues[2].toIntOrNull() ?: 0
                if (prefix == "v") {
                    if (num > highestLocalUsed) highestLocalUsed = num
                    if (declaredLocals != -1 && num >= declaredLocals) {
                        issues.add(
                            SmaliIssue(
                                lineNum,
                                IssueSeverity.ERROR,
                                msgRegOutOfBounds(language, num, declaredLocals),
                                fixRegOutOfBounds(language, num + 1, declaredLocals)
                            )
                        )
                    }
                } else if (prefix == "p") {
                    if (num > highestParamUsed) highestParamUsed = num
                }
            }
        }

        if (inMethod) {
            issues.add(
                SmaliIssue(
                    methodStartLine.coerceAtLeast(1),
                    IssueSeverity.ERROR,
                    msgUnterminatedMethod(language),
                    fixUnterminatedMethod(language)
                )
            )
        }

        for ((lineNum, label) in referencedLabels) {
            if (!definedLabels.contains(label)) {
                issues.add(
                    SmaliIssue(
                        lineNum,
                        IssueSeverity.ERROR,
                        msgUnresolvedLabel(language, label),
                        fixUnresolvedLabel(language, label)
                    )
                )
            }
        }

        if (declaredLocals == -1 && methodStartLine != -1) {
            issues.add(
                SmaliIssue(
                    methodStartLine,
                    IssueSeverity.WARNING,
                    msgMissingLocals(language),
                    fixMissingLocals(language)
                )
            )
        }

        val errorCount = issues.count { it.severity == IssueSeverity.ERROR }
        val hasErrors = errorCount > 0
        val summary = when {
            hasErrors -> summaryErrors(language, errorCount)
            issues.isNotEmpty() -> summaryWarnings(language, issues.size)
            else -> summaryPassed(language)
        }

        return SmaliAnalysisResult(
            isValid = !hasErrors,
            declaredLocals = declaredLocals,
            highestLocalUsed = highestLocalUsed,
            highestParamUsed = highestParamUsed,
            labelsFound = definedLabels.toList(),
            issues = issues,
            summary = summary
        )
    }

    private fun msgNestedMethod(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "আগের মেথডটি '.end method' দিয়ে শেষ না করেই নতুন '.method' শুরু করা হয়েছে"
        AppLanguage.URDU -> "پچھلے میتھڈ کو '.end method' سے بند کیے بغیر نیا '.method' شروع کیا گیا"
        AppLanguage.HINDI -> "पिछली मेथड को '.end method' से बंद किए बिना नई '.method' शुरू की गई"
        AppLanguage.ARABIC -> "تم بدء '.method' جديدة قبل إغلاق السابقة بـ '.end method'"
        AppLanguage.DARI -> "متد جدید بدون بستن متد قبلی با '.end method' آغاز شده است"
        AppLanguage.INDONESIAN -> ".method bersarang ditemukan tanpa menutup metode sebelumnya dengan .end method"
        AppLanguage.ENGLISH -> "Nested .method found without closing previous method with .end method"
    }

    private fun fixNestedMethod(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "নতুন মেথড শুরু করার আগে '.end method' যুক্ত করুন।"
        AppLanguage.URDU -> "نیا میتھڈ شروع کرنے سے پہلے '.end method' شامل کریں۔"
        AppLanguage.HINDI -> "नई मेथड शुरू करने से पहले '.end method' जोड़ें।"
        AppLanguage.ARABIC -> "أضف '.end method' قبل بداية الدالة الجديدة."
        AppLanguage.DARI -> "پیش از شروع متد جدید، عبارت '.end method' را اضافه کنید."
        AppLanguage.INDONESIAN -> "Tambahkan '.end method' sebelum memulai metode baru."
        AppLanguage.ENGLISH -> "Add '.end method' before starting a new method."
    }

    private fun msgOrphanEndMethod(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "কোনো শুরুর '.method' ছাড়াই '.end method' পাওয়া গেছে"
        AppLanguage.URDU -> "بغیر کسی '.method' کے '.end method' پایا گیا"
        AppLanguage.HINDI -> "बिना किसी शुरुआती '.method' के '.end method' मिला"
        AppLanguage.ARABIC -> "تم العثور على '.end method' بدون بداية '.method'"
        AppLanguage.DARI -> "عبارت '.end method' بدون '.method' آغازین یافت شد"
        AppLanguage.INDONESIAN -> "Ditemukan '.end method' tanpa pasangan '.method'"
        AppLanguage.ENGLISH -> "Found '.end method' without a matching '.method'"
    }

    private fun fixOrphanEndMethod(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "এই লাইনটি মুছে ফেলুন অথবা ওপরে '.method' হেডার যুক্ত করুন।"
        AppLanguage.URDU -> "اس لائن کو ہٹائیں یا اوپر '.method' شامل کریں۔"
        AppLanguage.HINDI -> "इस लाइन को हटाएं या ऊपर '.method' हेडर जोड़ें।"
        AppLanguage.ARABIC -> "احذف هذا السطر أو أضف ترويسة '.method' في الأعلى."
        AppLanguage.DARI -> "این خط را حذف کنید یا در بالا '.method' اضافه نمایید."
        AppLanguage.INDONESIAN -> "Hapus baris ini atau tambahkan header .method di atas."
        AppLanguage.ENGLISH -> "Remove this line or add a .method header above."
    }

    private fun msgMissingReturn(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "মেথডের ভেতরে কোনো return নির্দেশনা (যেমন return-void বা return v0) নেই"
        AppLanguage.URDU -> "میتھڈ کے اندر کوئی return انسٹرکشن موجود نہیں ہے"
        AppLanguage.HINDI -> "मेथड के अंदर कोई return निर्देश नहीं मिला"
        AppLanguage.ARABIC -> "الدالة لا تحتوي على تعليمة إرجاع return"
        AppLanguage.DARI -> "در داخل متد هیچ دستور بازگشتی (return) وجود ندارد"
        AppLanguage.INDONESIAN -> "Metode tidak memiliki instruksi return (mis. return-void)"
        AppLanguage.ENGLISH -> "Method body has no return instruction (e.g., return-void or return v0)"
    }

    private fun fixMissingReturn(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "মেথডের শেষে রিটার্ন টাইপ অনুযায়ী return-void, return v0 অথবা return-object v0 দিন।"
        AppLanguage.URDU -> "میتھڈ کے آخر میں return-void یا return v0 شامل کریں۔"
        AppLanguage.HINDI -> "मेथड के अंत में return-void या return v0 जोड़ें।"
        AppLanguage.ARABIC -> "أضف return-void أو return v0 قبل نهاية الدالة."
        AppLanguage.DARI -> "در پایان متد دستور return-void یا return v0 قرار دهید."
        AppLanguage.INDONESIAN -> "Tambahkan return-void atau return v0 sebelum .end method."
        AppLanguage.ENGLISH -> "Add return-void, return v0, or return-object v0 before .end method."
    }

    private fun msgRegOutOfBounds(lang: AppLanguage, num: Int, locals: Int) = when (lang) {
        AppLanguage.BENGALI -> "রেজিস্টার সীমার বাইরে (out of bounds): 'v$num' ব্যবহার করা হয়েছে কিন্তু '.locals $locals' ঘোষিত আছে!"
        AppLanguage.URDU -> "رجسٹر حد سے باہر: 'v$num' استعمال ہوا جبکہ صرف '.locals $locals' لکھا گیا ہے!"
        AppLanguage.HINDI -> "रजिस्टर सीमा से बाहर: 'v$num' उपयोग किया गया पर केवल '.locals $locals' घोषित है!"
        AppLanguage.ARABIC -> "المسجل خارج النطاق: تم استخدام 'v$num' بينما تم حجز '.locals $locals' فقط!"
        AppLanguage.DARI -> "رجستر خارج از محدوده: 'v$num' استفاده شده اما فقط '.locals $locals' تعریف شده است!"
        AppLanguage.INDONESIAN -> "Register di luar batas: 'v$num' dipakai tetapi hanya '.locals $locals' dideklarasikan!"
        AppLanguage.ENGLISH -> "Register out of bounds: 'v$num' used but only '.locals $locals' declared!"
    }

    private fun fixRegOutOfBounds(lang: AppLanguage, needed: Int, locals: Int) = when (lang) {
        AppLanguage.BENGALI -> "'.locals $needed' করে বাড়িয়ে দিন অথবা কম নম্বরের রেজিস্টার ব্যবহার করুন।"
        AppLanguage.URDU -> "'.locals $needed' کر دیں یا چھوٹا رجسٹر استعمال کریں۔"
        AppLanguage.HINDI -> "'.locals $needed' बढ़ाएं या छोटा रजिस्टर चुनें।"
        AppLanguage.ARABIC -> "ارفع العدد إلى '.locals $needed' أو استخدم مسجلاً أقل."
        AppLanguage.DARI -> "مقدار را به '.locals $needed' افزایش دهید."
        AppLanguage.INDONESIAN -> "Naikkan menjadi '.locals $needed' atau gunakan register lebih rendah."
        AppLanguage.ENGLISH -> "Increase '.locals $needed' or use a lower register (v0..v${(locals - 1).coerceAtLeast(0)})."
    }

    private fun msgUnterminatedMethod(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "মেথডটি শেষ করা হয়নি: ফাইলের শেষে '.end method' অনুপস্থিত"
        AppLanguage.URDU -> "نامکمل میتھڈ: آخر میں '.end method' غائب ہے"
        AppLanguage.HINDI -> "अधूरा मेथड: अंत में '.end method' अनुपस्थित है"
        AppLanguage.ARABIC -> "دالة غير مغلقة: '.end method' مفقودة في النهاية"
        AppLanguage.DARI -> "متد ناتمام: عبارت '.end method' در انتها نوشته نشده است"
        AppLanguage.INDONESIAN -> "Metode belum ditutup: '.end method' hilang di akhir kode"
        AppLanguage.ENGLISH -> "Unterminated method: Missing '.end method' at the end of the file"
    }

    private fun fixUnterminatedMethod(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "মেথডের একদম নিচে '.end method' লাইনটি যোগ করুন।"
        AppLanguage.URDU -> "میتھڈ کے آخر میں '.end method' لکھیں۔"
        AppLanguage.HINDI -> "मेथड के अंत में '.end method' जोड़ें।"
        AppLanguage.ARABIC -> "أضف '.end method' في أسفل تعريف الدالة."
        AppLanguage.DARI -> "در پایین متد عبارت '.end method' را بنویسید."
        AppLanguage.INDONESIAN -> "Tambahkan '.end method' di bagian paling bawah metode."
        AppLanguage.ENGLISH -> "Add '.end method' at the bottom of the method definition."
    }

    private fun msgUnresolvedLabel(lang: AppLanguage, label: String) = when (lang) {
        AppLanguage.BENGALI -> "অজ্ঞাত জাম্প লেবেল '$label': এই নামে কোনো লেবেল মেথডে তৈরি করা হয়নি!"
        AppLanguage.URDU -> "نامعلوم جمپ لیبل '$label': یہ لیبل کوڈ میں کہیں موجود نہیں!"
        AppLanguage.HINDI -> "अज्ञात जंप लेबल '$label': यह लेबल कोड में परिभाषित नहीं है!"
        AppLanguage.ARABIC -> "علامة قفز غير معرفة '$label': الهدف غير موجود في الدالة!"
        AppLanguage.DARI -> "برچسب پرش نامعتبر '$label': این برچسب در کد تعریف نشده است!"
        AppLanguage.INDONESIAN -> "Label lompatan '$label' tidak ditemukan di dalam metode!"
        AppLanguage.ENGLISH -> "Unresolved branch label '$label': this target is never defined!"
    }

    private fun fixUnresolvedLabel(lang: AppLanguage, label: String) = when (lang) {
        AppLanguage.BENGALI -> "কোডের সঠিক লাইনে '$label' লেবেলটি যুক্ত করুন।"
        AppLanguage.URDU -> "کوڈ میں مطلوبہ جگہ پر '$label' لیبل شامل کریں۔"
        AppLanguage.HINDI -> "कोड में सही स्थान पर '$label' लेबल जोड़ें।"
        AppLanguage.ARABIC -> "تأكد من تعريف العلامة '$label' على سطر مستقل داخل الدالة."
        AppLanguage.DARI -> "برچسب '$label' را در خط مقصد تعریف کنید."
        AppLanguage.INDONESIAN -> "Pastikan label '$label' ditulis pada baris tujuan di dalam metode."
        AppLanguage.ENGLISH -> "Ensure '$label' exists as a label in the code."
    }

    private fun msgMissingLocals(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "মেথডে '.locals' নির্দেশনা দেওয়া হয়নি"
        AppLanguage.URDU -> "میتھڈ میں '.locals' ڈائریکٹیو غائب ہے"
        AppLanguage.HINDI -> "मेथड में '.locals' डायरेक्टिव अनुपस्थित है"
        AppLanguage.ARABIC -> "التوجيه '.locals' مفقود داخل الدالة"
        AppLanguage.DARI -> "دستور '.locals' در ابتدای متد نوشته نشده است"
        AppLanguage.INDONESIAN -> "Direktif '.locals' belum ditulis di dalam metode"
        AppLanguage.ENGLISH -> "Missing '.locals' directive in method"
    }

    private fun fixMissingLocals(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "মেথড হেডারের ঠিক নিচে '.locals N' লিখে প্রয়োজনীয় রেজিস্টার বরাদ্দ করুন।"
        AppLanguage.URDU -> "میتھڈ ہیڈر کے فوراً بعد '.locals N' شامل کریں۔"
        AppLanguage.HINDI -> "मेथड हेडर के तुरंत बाद '.locals N' लिखें।"
        AppLanguage.ARABIC -> "أضف '.locals N' مباشرة بعد سطر تعريف الدالة."
        AppLanguage.DARI -> "بلافاصله پس از سطر متد، '.locals N' را بنویسید."
        AppLanguage.INDONESIAN -> "Deklarasikan '.locals N' tepat setelah baris .method."
        AppLanguage.ENGLISH -> "Declare '.locals N' right after the method header to reserve registers."
    }

    private fun summaryErrors(lang: AppLanguage, count: Int) = when (lang) {
        AppLanguage.BENGALI -> "ভেরিফিকেশন ব্যর্থ হয়েছে: স্মালি বাইটকোডে $count টি গুরুতর ত্রুটি পাওয়া গেছে।"
        AppLanguage.URDU -> "تصدیق ناکام: سمالی کوڈ میں $count غلطیاں پائی گئیں۔"
        AppLanguage.HINDI -> "सत्यापन विफल: स्माली बाइटकोड में $count त्रुटियां मिलीं।"
        AppLanguage.ARABIC -> "فشل التحقق: تم العثور على $count أخطاء في شيفرة سمالي."
        AppLanguage.DARI -> "بررسی ناموفق بود: $count خطای بایت‌کد یافت شد."
        AppLanguage.INDONESIAN -> "Validasi gagal dengan $count kesalahan bytecode Dalvik."
        AppLanguage.ENGLISH -> "Validation failed with $count Dalvik bytecode error(s)."
    }

    private fun summaryWarnings(lang: AppLanguage, count: Int) = when (lang) {
        AppLanguage.BENGALI -> "কোডটি বৈধ, তবে $count টি সতর্কবার্তা (Warning) রয়েছে।"
        AppLanguage.URDU -> "کوڈ درست ہے، لیکن $count انتباہات موجود ہیں۔"
        AppLanguage.HINDI -> "कोड मान्य है, पर $count चेतावनियां हैं।"
        AppLanguage.ARABIC -> "الشيفرة صالحة مع وجود $count تنبيهات."
        AppLanguage.DARI -> "کد معتبر است اما $count هشدار دارد."
        AppLanguage.INDONESIAN -> "Kode valid dengan $count peringatan."
        AppLanguage.ENGLISH -> "Code is valid with $count warning(s)."
    }

    private fun summaryPassed(lang: AppLanguage) = when (lang) {
        AppLanguage.BENGALI -> "অভিনন্দন! সব রেজিস্টার সীমা (.locals), লেবেল ও মেথড গঠন শতভাগ সঠিক।"
        AppLanguage.URDU -> "مبارک ہو! تمام رجسٹرز، لیبلز اور میتھڈ کی ساخت بالکل درست ہے۔"
        AppLanguage.HINDI -> "बधाई! सभी रजिस्टर सीमाएं, लेबल और मेथड संरचना पूरी तरह सही हैं।"
        AppLanguage.ARABIC -> "اجتاز الفحص بنجاح! المسجلات والعلامات وبنية الدالة متوافقة تماماً."
        AppLanguage.DARI -> "بررسی با موفقیت انجام شد! رجسترها و برچسب‌ها کاملاً استاندارد هستند."
        AppLanguage.INDONESIAN -> "Verifikasi bytecode berhasil! Register dan label selaras sempurna."
        AppLanguage.ENGLISH -> "Bytecode verification passed successfully! Registers and labels are strictly aligned."
    }
}
