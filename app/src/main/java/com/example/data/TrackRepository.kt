package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection
import com.example.model.TrackType
import com.example.model.localized

object TrackRepository {

    val allLessons: List<Lesson> = listOf(
        // =========================================================================
        // TRACK 1: ANDROID DEV (বাটন ও অ্যাক্টিভিটি কানেক্ট করে অ্যাপ তৈরি)
        // =========================================================================
        Lesson(
            id = "and_dev_btn_click",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "1. Connecting a Button & Showing Toast Message",
                bn = "১. বাটন কানেক্ট করা ও টোস্ট মেসেজ দেখানো (findViewById & Toast)"
            ),
            subtitle = localized(
                en = "How to wire an XML button to Java/Kotlin and trigger a click action",
                bn = "এক্সএমএল ডিজাইনের বাটন জাভা ও কোটলিনের সাথে কানেক্ট করে ক্লিকে কাজ করানোর নিয়ম"
            ),
            estimatedMinutes = 6,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "How Buttons Work: The Doorbell Analogy",
                        bn = "বাটন কীভাবে কাজ করে: দরজার কলিং বেলের মতো সহজ ব্যাখ্যা"
                    ),
                    childAnalogy = localized(
                        en = "Think of an XML Button like a plastic doorbell on the wall, and the Java/Kotlin code like the electric bell inside your house. If you do not wire the plastic button to the bell using a wire (ID), pressing it does nothing! findViewById() is the wire that connects them.",
                        bn = "মনে করুন, এক্সএমএল (XML) বাটনটি হলো আপনার ঘরের দরজার প্লাস্টিকের কলিং বেল, আর জাভা বা কোটলিন কোডটি হলো ঘরের ভেতরের বৈদ্যুতিক ঘণ্টার তার! আপনি যদি প্লাস্টিকের সুইচের সাথে ভেতরের ঘণ্টার তারকে আইডি (ID) দিয়ে কানেক্ট না করেন, তবে সুইচে চাপ দিলেও ঘণ্টা বাজবে না। findViewById হলো সেই কানেকশনের তার!"
                    ),
                    content = localized(
                        en = "In Android development, follow these 3 golden steps:\n1. Create the button in activity_main.xml with android:id=\"@+id/btnSubmit\"\n2. In your Activity, find it with findViewById(R.id.btnSubmit)\n3. Attach an OnClickListener so your code runs when tapped!",
                        bn = "অ্যান্ড্রয়েড অ্যাপে বাটন নিয়ে কাজ করার ৩টি মূল ধাপ রয়েছে:\n১. activity_main.xml ফাইলে বাটন তৈরি করে একটি ইউনিক আইডি দিন: android:id=\"@+id/btnSubmit\"\n২. অ্যাক্টিভিটি ফাইলে এসে সেই আইডি দিয়ে বাটনটিকে ধরুন: findViewById(R.id.btnSubmit)\n৩. setOnClickListener যুক্ত করুন যাতে ব্যবহারকারী ট্যাপ করলেই আপনার কোড কাজ করে!"
                    ),
                    xmlCode = """<!-- res/layout/activity_main.xml -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <TextView
        android:id="@+id/txtStatus"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="বাটনে ক্লিক করুন!"
        android:textSize="18sp" />

    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="ক্লিক করুন" />
</LinearLayout>""",
                    javaCode = """// Java: MainActivity.java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // এক্সএমএল লোড

        Button btn = findViewById(R.id.btnSubmit);
        TextView txt = findViewById(R.id.txtStatus);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("বাটনে সফলভাবে ক্লিক হয়েছে! 🎉");
                Toast.makeText(MainActivity.this, "স্বাগতম!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}""",
                    kotlinCode = """// Kotlin: MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnSubmit)
        val txt: TextView = findViewById(R.id.txtStatus)

        btn.setOnClickListener {
            txt.text = "বাটনে সফলভাবে ক্লিক হয়েছে! 🎉"
            Toast.makeText(this, "স্বাগতম!", Toast.LENGTH_SHORT).show()
        }
    }
}""",
                    practicalExampleNote = localized(
                        en = "Toast is a quick popup alert that disappears after 2 seconds. Use Toast.LENGTH_LONG for 3.5 seconds.",
                        bn = "Toast হলো ফোনের স্ক্রিনে নিচে ভেসে ওঠা একটি সংক্ষিপ্ত মেসেজ যা ২ সেকেন্ড পর একা একাই গায়েব হয়ে যায়।"
                    )
                )
            )
        ),
        Lesson(
            id = "and_dev_intent_switch",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "2. Opening a New Screen (Switching Activities with Intent)",
                bn = "২. এক পেজ থেকে অন্য পেজে যাওয়া (Switching Activities with Intent)"
            ),
            subtitle = localized(
                en = "How to click a button and navigate from Screen 1 to Screen 2 with extra data",
                bn = "একটি বাটনে ক্লিক করে প্রথম পেজ থেকে দ্বিতীয় পেজে যাওয়া এবং সাথে তথ্য পাঠানো"
            ),
            estimatedMinutes = 7,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "What is an Intent? The Delivery Envelope Analogy",
                        bn = "ইনটেন্ট (Intent) কী? চিঠি পাঠানো ও দরজা খোলার সহজ উপমা"
                    ),
                    childAnalogy = localized(
                        en = "Imagine you want to go from the Living Room into the Bedroom. In Android, each room is an 'Activity'. To open the door to Room 2, you write an 'Intent' letter to the Android OS saying: 'Please take me from MainActivity to SecondActivity, and give this secret message to Room 2!'",
                        bn = "মনে করুন, আপনি ড্রয়িং রুম থেকে বেডরুমে যেতে চান। অ্যান্ড্রয়েডে প্রতিটি রুমকে বলা হয় 'অ্যাক্টিভিটি' (Activity)। ড্রয়িং রুমের দরজা খুলে বেডরুমে যাওয়ার জন্য অ্যান্ড্রয়েড অপারেটিং সিস্টেমের কাছে একটি চিঠি লিখে পাঠাতে হয়, যাকে বলে 'Intent'। চিঠিতে আপনি লিখে দিতে পারেন: 'আমাকে সেকেন্ড অ্যাক্টিভিটিতে নিয়ে চলো এবং সাথে এই ডাটা নিয়ে যাও!'"
                    ),
                    content = localized(
                        en = "1. Create SecondActivity in your app\n2. Declare it in AndroidManifest.xml: <activity android:name=\".SecondActivity\" />\n3. Create Intent and call startActivity(intent)!",
                        bn = "১. প্রথমে প্রজেক্টে SecondActivity নামের নতুন ক্লাস ও লেআউট তৈরি করুন\n২. AndroidManifest.xml ফাইলে ঘোষণা দিন: <activity android:name=\".SecondActivity\" />\n৩. বাটনের ভেতরে Intent তৈরি করে startActivity(intent) কল করুন!"
                    ),
                    javaCode = """// Java: MainActivity.java -> SecondActivity
Button btnNext = findViewById(R.id.btnGoNext);
btnNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("user_name", "মোরসালিন"); // ডাটা পাঠানো
        startActivity(intent);
    }
});""",
                    kotlinCode = """// Kotlin: MainActivity.kt -> SecondActivity
val btnNext: Button = findViewById(R.id.btnGoNext)
btnNext.setOnClickListener {
    val intent = Intent(this, SecondActivity::class.java).apply {
        putExtra("user_name", "মোরসালিন")
    }
    startActivity(intent)
}""",
                    xmlCode = """<!-- SecondActivity.java: ডাটা রিসিভ করা -->
String name = getIntent().getStringExtra("user_name");
// name পাবে "মোরসালিন"!""",
                    practicalExampleNote = localized(
                        en = "Always register every Activity in AndroidManifest.xml. If omitted, Android will throw ActivityNotFoundException!",
                        bn = "মনে রাখবেন: নতুন যেকোনো অ্যাক্টিভিটি তৈরি করলে তা AndroidManifest.xml-এ ঘোষণা করতে হয়, না হলে অ্যাপ ক্র্যাশ করবে!"
                    )
                )
            )
        ),
        Lesson(
            id = "and_dev_edittext_input",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "3. Reading User Input from EditText to TextView",
                bn = "৩. ইনপুট বক্স থেকে লেখা পড়া ও টেক্সটভিউতে দেখানো (EditText to TextView)"
            ),
            subtitle = localized(
                en = "Take user's name or number, validate it, and display a personalized greeting",
                bn = "ইউজারের কাছ থেকে নাম বা নম্বর লিখে নিয়ে বাটনে চাপ দিলে তা স্ক্রিনে সুন্দরভাবে প্রদর্শন"
            ),
            estimatedMinutes = 6,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Blank Blackboard & Chalk Analogy",
                        bn = "খাতায় লিখে ব্ল্যাকবোর্ডে টানিয়ে দেওয়ার মতো সহজ ব্যাখ্যা"
                    ),
                    childAnalogy = localized(
                        en = "EditText is like a blank notebook where the user types with their keyboard. When they tap 'Submit', your code reads the ink from the notebook and writes it in giant glowing letters on the Blackboard (TextView)!",
                        bn = "EditText হলো একটি খালি খাতা যেখানে ইউজার তার কিবোর্ড দিয়ে কিছু লিখে। আর বাটন চাপার সাথে সাথে কোড খাতা থেকে লেখাটি পড়ে নিয়ে বড় ব্ল্যাকবোর্ডে (TextView) সুন্দরভাবে সাজিয়ে সবাইকে দেখায়!"
                    ),
                    content = localized(
                        en = "Use input.getText().toString() to get text. Always check if the input is empty with input.isEmpty() before processing to avoid blank results!",
                        bn = "লেখা পাওয়ার জন্য input.getText().toString() ব্যবহার করুন। ব্যবহারকারী কিছু না লিখে বাটন চাপলে এরর মেসেজ দেওয়ার জন্য if (name.isEmpty()) দিয়ে চেক করতে হয়।"
                    ),
                    xmlCode = """<EditText
    android:id="@+id/etName"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="আপনার নাম লিখুন..." />

<Button
    android:id="@+id/btnGreet"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="জমা দিন" />

<TextView
    android:id="@+id/tvWelcome"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="20sp" />""",
                    kotlinCode = """val etName: EditText = findViewById(R.id.etName)
val btnGreet: Button = findViewById(R.id.btnGreet)
val tvWelcome: TextView = findViewById(R.id.tvWelcome)

btnGreet.setOnClickListener {
    val enteredName = etName.text.toString().trim()
    if (enteredName.isEmpty()) {
        etName.error = "অনুগ্রহ করে আপনার নাম লিখুন!"
    } else {
        tvWelcome.text = "স্বাগতম, ${'$'}enteredName! শুভ দিন! 🌟"
    }
}"""
                )
            )
        ),
        Lesson(
            id = "and_dev_lifecycle",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "4. The Activity Lifecycle: From Birth to Sleep",
                bn = "৪. অ্যাক্টিভিটি লাইফসাইকেল: জন্ম থেকে ঘুম (onCreate to onDestroy)"
            ),
            subtitle = localized(
                en = "Understanding what happens when you rotate phone or press the home button",
                bn = "ফোন রোটেট করলে বা হোম বাটন চাপলে অ্যাপের ভেতরে কীভাবে জীবনচক্র কাজ করে"
            ),
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Human Day Routine Analogy",
                        bn = "মানুষের প্রতিদিনের ঘুম থেকে ওঠার মতো সহজ উপমা"
                    ),
                    childAnalogy = localized(
                        en = "Just like humans: You wake up in the morning (onCreate), stand up on your feet (onStart), start playing with friends (onResume), take a quick pause when someone calls (onPause), walk away (onStop), and go to sleep at night (onDestroy).",
                        bn = "ঠিক মানুষের দৈনন্দিন নিয়মের মতো: সকালে ঘুম থেকে বিছানা ছেড়ে ওঠা (onCreate), ঘরের দরজায় এসে দাঁড়ানো (onStart), মাঠে বন্ধুদের সাথে হাত নেড়ে খেলাধুলা করা (onResume), কেউ ডাকলে খেলার মাঝে ক্ষণিক থামা (onPause), মাঠ থেকে ঘরে ফিরে যাওয়া (onStop), এবং রাতে শান্তিতে ঘুমাতে যাওয়া (onDestroy)।"
                    ),
                    content = localized(
                        en = "• onCreate(): Sets up XML layout & views\n• onStart(): Screen becomes visible\n• onResume(): App is interactive\n• onPause(): Dialog or call interrupts\n• onStop(): Screen is hidden\n• onDestroy(): Activity destroyed",
                        bn = "• onCreate(): যখন প্রথম অ্যাক্টিভিটি তৈরি হয়। এখানে লেআউট সেট ও বাটন কানেক্ট করতে হয়।\n• onStart(): স্ক্রিনে অ্যাপ দৃশ্যমান হতে শুরু করে।\n• onResume(): স্ক্রিন সচল এবং ইউজার বাটনে ক্লিক করতে পারেন।\n• onPause(): অন্য কিছু সামনে এলে সাময়িক বিরতি নেয়।\n• onStop(): অ্যাপ যখন ব্যাকগ্রাউন্ডে পুরোপুরি লুকিয়ে যায়।\n• onDestroy(): অ্যাপ মেমোরি থেকে সম্পূর্ণ খালাস হয়ে যায়।"
                    ),
                    kotlinCode = """class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("অ্যাপ জেগে উঠেছে!")
    }

    override fun onResume() {
        super.onResume()
        println("ব্যবহারকারী স্ক্রিনে হাত দিয়ে কাজ করছেন!")
    }

    override fun onPause() {
        super.onPause()
        println("অ্যাপে সাময়িক বিরতি!")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("অ্যাপ পুরোপুরি বন্ধ হয়ে গেল!")
    }
}"""
                )
            )
        ),
        Lesson(
            id = "and_dev_alert_dialog",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "5. Popups & Confirmation Dialogs (AlertDialog)",
                bn = "৫. পপআপ ও অ্যালার্ট ডায়ালগ (AlertDialog Confirmation)"
            ),
            subtitle = localized(
                en = "Ask 'Are you sure you want to delete or exit?' with Yes/No buttons",
                bn = "কোনো ফাইল ডিলিট বা অ্যাপ বন্ধ করার আগে 'হ্যাঁ' বা 'না' ডায়ালগ বক্স তৈরি"
            ),
            estimatedMinutes = 7,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Security Gatekeeper Analogy",
                        bn = "গেটকিপারের অনুমতির মতো সহজ ব্যাখ্যা"
                    ),
                    childAnalogy = localized(
                        en = "AlertDialog is like a security guard stepping in front of you asking: 'Stop! Are you 100% sure you want to delete your progress?' You must tap Yes to proceed or No to stay safe.",
                        bn = "অ্যালার্ট ডায়ালগ হলো দরজার দারোয়ানের মতো, যে সামনে দাঁড়িয়ে জিজ্ঞাসা করে: 'থামুন! আপনি কি সত্যিই এই কাজ করতে চান?' আপনি 'হ্যাঁ' চাপলে কাজটি হবে, আর 'না' চাপলে কোনো ক্ষতি ছাড়াই ডায়ালগ বন্ধ হয়ে যাবে।"
                    ),
                    content = localized(
                        en = "Use AlertDialog.Builder(this) to create title, message, positive button, and negative button.",
                        bn = "অ্যান্ড্রয়েডে AlertDialog.Builder(this) ব্যবহার করে টাইটেল, মেসেজ এবং ইতিবাচক ও নেতিবাচক বাটন সেট করতে হয়।"
                    ),
                    kotlinCode = """val builder = AlertDialog.Builder(this)
builder.setTitle("সতর্কবার্তা!")
builder.setMessage("আপনি কি নিশ্চিত যে ডেটা মুছে ফেলতে চান?")

builder.setPositiveButton("হ্যাঁ, ডিলিট করুন") { dialog, which ->
    Toast.makeText(this, "সফলভাবে মুছে ফেলা হয়েছে!", Toast.LENGTH_SHORT).show()
}

builder.setNegativeButton("না, বাতিল") { dialog, which ->
    dialog.dismiss()
}

val alertDialog = builder.create()
alertDialog.show()"""
                )
            )
        ),
        Lesson(
            id = "and_dev_shared_prefs",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "6. Saving Data Permanently (SharedPreferences)",
                bn = "৬. অ্যাপ বন্ধ করলেও ডাটা সেভ রাখা (SharedPreferences Storage)"
            ),
            subtitle = localized(
                en = "Store login state, score, dark mode toggle, and load it on next launch",
                bn = "ইউজারনেম, গেম স্কোর বা ডার্ক মুড সেভ রাখা যাতে অ্যাপ কেটে দিলেও তা হারিয়ে না যায়"
            ),
            estimatedMinutes = 7,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Secret Pocket Notebook Analogy",
                        bn = "পকেটের ডায়েরিতে পেন্সিল দিয়ে লিখে রাখার মতো সহজ উপমা"
                    ),
                    childAnalogy = localized(
                        en = "When you close your app, RAM forgets everything! SharedPreferences is like a tiny pocket notebook stored on phone's hard drive where you write: 'HighScore = 250'. Next time you open the app, it reads the notebook immediately!",
                        bn = "অ্যাপ কেটে দিলে র‍্যামের (RAM) সব তথ্য হারিয়ে যায়। SharedPreferences হলো ফোনের হার্ডড্রাইভে থাকা একটি জাদুকরি ছোট ডায়েরি, যেখানে আপনি লিখে রাখেন: 'HighScore = 250'। পরের বার অ্যাপ চালু করলে এটি ডায়েরি খুলে মুহূর্তেই আগের ডাটা পড়ে নেয়!"
                    ),
                    content = localized(
                        en = "Save with prefs.edit().putString().apply(). Read with prefs.getString().",
                        bn = "ডাটা সেভ করতে prefs.edit().putString(\"name\", value).apply() এবং ডাটা পড়তে prefs.getString(\"name\", \"ডিফল্ট\") ব্যবহার করুন।"
                    ),
                    kotlinCode = """// ১. ডাটা সেভ করা
val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
prefs.edit().putString("username", "মোরসালিন").apply()

// ২. ডাটা পড়া
val savedUser = prefs.getString("username", "কোনো নাম নেই")
println("সেভ করা নাম: ${'$'}savedUser")"""
                )
            )
        ),
        Lesson(
            id = "and_dev_recyclerview",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.ADVANCED,
            title = localized(
                en = "7. Dynamic Scrolling Lists (RecyclerView & Cards)",
                bn = "৭. স্ক্রলযোগ্য কার্ড লিস্ট তৈরি (RecyclerView & Custom Adapter)"
            ),
            subtitle = localized(
                en = "Display thousands of items smoothly without lagging the device memory",
                bn = "হাজার হাজার পোস্ট বা মেসেজের লিস্ট ফোন হ্যাং না করিয়ে মাখনের মতো স্ক্রল করানো"
            ),
            estimatedMinutes = 9,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Ferris Wheel Analogy",
                        bn = "নাগরদোলার চেয়ারের মতো রিসাইকেল হওয়ার সহজ ব্যাখ্যা"
                    ),
                    childAnalogy = localized(
                        en = "If you have 1,000 photos, creating 1,000 views crashes your phone! A Ferris wheel only has 10 seats that spin around. As items scroll off screen, RecyclerView re-uses the same view for the new item. Zero wasted memory!",
                        bn = "ফোনে ১০০০ আইটেম থাকলে ১০০০টি আলাদা ভিউ বানালে ফোন হ্যাং করবে! RecyclerView নাগরদোলার মতো মাত্র ১০-১২টি ভিউ তৈরি করে। ওপরের আইটেম যখন স্ক্রিনের বাইরে চলে যায়, সেই একই কার্ডকে নিচে এনে নতুন ডাটা বসিয়ে দেয়। ফলে কোনো মেমোরি নষ্ট হয় না!"
                    ),
                    content = localized(
                        en = "Components:\n1. RecyclerView in XML\n2. item_card.xml layout\n3. Adapter & ViewHolder class\n4. Set LayoutManager (LinearLayoutManager)",
                        bn = "প্রয়োজনীয় উপাদান:\n১. মূল এক্সএমএলে RecyclerView যোগ করা\n২. একটি কার্ডের ডিজাইন item_card.xml\n৩. Adapter ও ViewHolder ক্লাস তৈরি করে লিস্ট ডাটা বাইন্ড করা\n৪. recyclerView.layoutManager = LinearLayoutManager(this)"
                    ),
                    kotlinCode = """class MessageAdapter(private val list: List<String>) : 
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvText: TextView = view.findViewById(R.id.tvItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvText.text = list[position]
    }

    override fun getItemCount() = list.size
}"""
                )
            )
        ),
        Lesson(
            id = "and_dev_webview",
            track = TrackType.ANDROID_DEV,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "8. Showing Websites Inside App (WebView Integration)",
                bn = "৮. অ্যাপের ভেতরে ওয়েবসাইট ব্রাউজ করা (WebView Integration)"
            ),
            subtitle = localized(
                en = "Load any URL or local HTML page directly inside your Android Activity",
                bn = "ব্রাউজার ছাড়া সরাসরি অ্যাপের স্ক্রিনে যেকোনো ওয়েবসাইট বা এইচটিএমএল পেজ ওপেন করা"
            ),
            estimatedMinutes = 6,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "The Magic Glass Window Analogy",
                        bn = "দেয়ালে কাঁচের জানালা বসানোর মতো সহজ উপমা"
                    ),
                    childAnalogy = localized(
                        en = "WebView is like cutting a square window in your living room wall looking directly into the Internet! Instead of opening Chrome, the website appears right inside your app.",
                        bn = "WebView হলো আপনার অ্যাপের স্ক্রিনের ভেতরে একটি কাঁচের জানালার মতো। ব্যবহারকারীকে ক্রোম ব্রাউজারে না পাঠিয়ে সরাসরি অ্যাপের ভেতরেই সম্পূর্ণ ওয়েবসাইট দেখিয়ে দেওয়া যায়!"
                    ),
                    content = localized(
                        en = "1. Add <uses-permission android:name=\"android.permission.INTERNET\" /> in manifest\n2. Add <WebView> in XML\n3. Enable JavaScript and loadUrl()",
                        bn = "১. AndroidManifest.xml-এ ইন্টারনেট পারমিশন দিন\n২. এক্সএমএলে WebView যোগ করুন\n৩. জাভায় webView.getSettings().setJavaScriptEnabled(true) দিয়ে URL লোড করুন!"
                    ),
                    kotlinCode = """val webView: WebView = findViewById(R.id.myWebView)
webView.settings.javaScriptEnabled = true
webView.webViewClient = WebViewClient() // অ্যাপের ভেতরেই ওপেন রাখবে
webView.loadUrl("https://google.com")"""
                )
            )
        ),

        // =========================================================================
        // TRACK 2: SMALI (জিরো থেকে বাচ্চাদের মতো বোঝানো)
        // =========================================================================
        Lesson(
            id = "smali_zero_1",
            track = TrackType.SMALI,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "Smali 101: The Mystery of Dalvik Registers (Toy Boxes)",
                bn = "স্মালি ১০১: খেলনার বাক্সের রহস্য (রেজিস্টার v0, v1, p0)"
            ),
            subtitle = localized(
                en = "Understanding v0, v1, and p0 like putting blocks into numbered toy boxes",
                bn = "রেজিস্টারকে তাকের ওপর সাজানো খেলনার বাক্সের মতো বুঝে স্মালি কোড জিরো থেকে শেখা"
            ),
            estimatedMinutes = 6,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "1. What is a Register? Imagine a Row of Toy Boxes!",
                        bn = "১. রেজিস্টার কী? তাকের ওপর সাজানো খেলনার বাক্সের সহজ ব্যাখ্যা"
                    ),
                    childAnalogy = localized(
                        en = "Imagine your room has a shelf with small numbered boxes: v0, v1, v2... Your robot assistant can only hold items if it puts them into one of these boxes. If you want the robot to calculate 5 + 3, it puts 5 into box v0, puts 3 into box v1, and pours both together into box v0!",
                        bn = "মনে করুন, আপনার পড়ার ঘরের তাকে কয়েকটি লেবেল লাগানো ছোট বাক্স রয়েছে: v0, v1, v2... আপনার রোবট সহকারী কোনো সংখ্যা মনে রাখতে হলে এই বাক্সে এনে রাখে। আপনি যদি রোবটকে ৫ আর ৩ যোগ করতে বলেন, সে v0 বাক্সে ৫ রাখবে, v1 বাক্সে ৩ রাখবে, তারপর দুটিকে যোগ করে রেজাল্ট v0 বাক্সে জমা রাখবে!"
                    ),
                    content = localized(
                        en = "In Android Dalvik bytecode, registers are 32-bit workspace slots declared with .locals.",
                        bn = "অ্যান্ড্রয়েড ডালভিক ভার্চুয়াল মেশিনে রেজিস্টারগুলো হলো দ্রুততম মেমোরি স্লট। একটি মেথডের শুরুতে .locals 2 লিখে বলে দেওয়া হয় এই মেথডে ২টি বাক্স (v0 ও v1) দরকার হবে।"
                    ),
                    smaliCode = """.method public static calculate()I
    .locals 2    # আমরা ২টি বাক্স নিলাম: v0 এবং v1

    const/4 v0, 0x5     # v0 বাক্সে ৫ রাখলাম
    const/4 v1, 0x3     # v1 বাক্সে ৩ রাখলাম
    add-int v0, v0, v1  # দুটির যোগফল ৮ করে v0 তে রাখলাম

    return v0           # রেজাল্ট ফেরত দিলাম!
.end method"""
                )
            )
        ),
        Lesson(
            id = "smali_zero_2",
            track = TrackType.SMALI,
            level = LearningLevel.INTERMEDIATE,
            title = localized(
                en = "Smali 102: The Traffic Light (if-eqz and Branching)",
                bn = "স্মালি ১০২: ট্র্যাফিক লাইট ও ব্রাঞ্চিং (if-eqz এবং জাম্পিং)"
            ),
            subtitle = localized(
                en = "How Android makes decisions and jumps across labels like hopscotch",
                bn = "শর্ত পূরণ হলে বা না হলে স্মালি কোড কীভাবে লাফ দিয়ে অন্য লাইনে চলে যায়"
            ),
            estimatedMinutes = 7,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "How If-Else Works: Inverted Logic!",
                        bn = "শর্ত কীভাবে কাজ করে: উল্টো জাম্পিংয়ের খেলা!"
                    ),
                    childAnalogy = localized(
                        en = "A guard says: 'If your ticket is 0 (invalid), jump straight to EXIT! Otherwise, walk right in!' That is what if-eqz does: if equal to zero, jump away!",
                        bn = "গেটের প্রহরী বলে: 'যদি তোমার টিকিটের মান শূন্য (০ বা মিথ্যা) হয়, তবে লাফ দিয়ে এক্সিট গেটে চলে যাও! আর টিকিট থাকলে সোজা ভেতরে চলো!' স্মালিতে if-eqz ঠিক এই কাজটিই করে: মান ০ হলে লাফ দেয়!"
                    ),
                    content = localized(
                        en = "In Java you write 'if (isVip)'. Smali inverts this to 'if-eqz v0, :not_vip' to minimize jumps.",
                        bn = "জাভায় আপনি লেখেন if (isVip)। কিন্তু ডালভিক কম্পাইলার এটাকে উল্টে দিয়ে বলে: if-eqz v0, :cond_not_vip (যদি শূন্য হয়, তবে ফ্রিতে চলে যাও)!"
                    ),
                    smaliCode = """    # v0 এর মান ১ (VIP) অথবা ০ (Normal)
    if-eqz v0, :cond_not_vip

    # ভিআইপি হলে এখানে কোড চলবে
    invoke-virtual {p0}, LApp;->showVip()V
    goto :cond_finish

    :cond_not_vip
    invoke-virtual {p0}, LApp;->showAds()V

    :cond_finish
    return-void"""
                )
            )
        ),

        // =========================================================================
        // TRACK 3: JAVA (জিরো থেকে একদম প্রো)
        // =========================================================================
        Lesson(
            id = "java_zero_1",
            track = TrackType.JAVA,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "Java 101: Variables & Data Types (Kitchen Jars)",
                bn = "জাভা ১০১: ভেরিয়েবল ও ডেটা টাইপ (লেবেল লাগানো চিনির জার)"
            ),
            subtitle = localized(
                en = "What is a variable? Putting numbers, text, and booleans into named jars",
                bn = "ভেরিয়েবল কীভাবে বানাতে হয়, int, double, boolean ও String-এর সহজ পরিচয়"
            ),
            estimatedMinutes = 6,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "What is a Variable? Kitchen Jars Analogy",
                        bn = "ভেরিয়েবল কী? রান্নাঘরের লেবেল লাগানো জারের সহজ উপমা"
                    ),
                    childAnalogy = localized(
                        en = "In your kitchen, you have jars with labels: 'Sugar' only holds sugar, 'Cookies' holds cookies. In Java, a variable is a named jar holding one specific type of data!",
                        bn = "আপনার রান্নাঘরে বিভিন্ন জার থাকে: একটি জারে লেখা থাকে 'চিনি' (সেখানে শুধুই চিনি রাখা যায়), আরেকটিতে লেখা 'বিস্কুট'। জাভায় একটি ভেরিয়েবল হলো নাম দেওয়া একটি জার, যাতে নির্দিষ্ট ধরনের ডেটা রাখা যায়!"
                    ),
                    content = localized(
                        en = "• int: whole numbers (e.g., 25)\n• double: decimal numbers (e.g., 3.14)\n• boolean: true or false\n• String: words in quotes (\"Hello\")",
                        bn = "• int: পূর্ণসংখ্যা (যেমন: ১০, ২০)\n• double: দশমিক সংখ্যা (যেমন: ৩.১৪, ৯৯.৫)\n• boolean: সত্য (true) অথবা মিথ্যা (false)\n• String: কোটেশনের ভেতরে থাকা যেকোনো লেখা (\"মোরসালিন\")"
                    ),
                    javaCode = """public class Main {
    public static void main(String[] args) {
        int age = 15;                  // পূর্ণসংখ্যার জার
        double rating = 4.8;           // দশমিক জার
        boolean isCoder = true;        // সত্য/মিথ্যা
        String name = "মোরসালিন";       // টেক্সট জার

        System.out.println("নাম: " + name + ", বয়স: " + age);
    }
}"""
                )
            )
        ),

        // =========================================================================
        // TRACK 4: KOTLIN (অ্যান্ড্রয়েডের আধুনিক কোডিং)
        // =========================================================================
        Lesson(
            id = "kotlin_zero_1",
            track = TrackType.KOTLIN,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "Kotlin 101: val vs var (Permanent vs Erasable Box)",
                bn = "কোটলিন ১০১: val বনাম var (পাথরের খোদাই বনাম হোয়াইটবোর্ড)"
            ),
            subtitle = localized(
                en = "Why Google chose Kotlin for Android: safe, expressive, and clean",
                bn = "কখন val ব্যবহার করবেন আর কখন var ব্যবহার করবেন তা পানির মতো সহজে বোঝা"
            ),
            estimatedMinutes = 5,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Stone Tablet vs Whiteboard Analogy",
                        bn = "পাথরের ফলক বনাম মোছা যায় এমন হোয়াইটবোর্ড"
                    ),
                    childAnalogy = localized(
                        en = "'val' is like carving in stone: once written, it can never be changed! 'var' is writing on a whiteboard with an eraser marker: you can change it anytime.",
                        bn = "'val' হলো পাথরে খোদাই করে লেখার মতো: একবার মান বসিয়ে দিলে কেউ আর পরিবর্তন করতে পারবে না! আর 'var' হলো মোছা যায় এমন মার্কার দিয়ে লেখার মতো: যখন খুশি মুছে নতুন মান বসাতে পারবেন।"
                    ),
                    content = localized(
                        en = "Google recommends using 'val' by default everywhere to prevent accidental bugs.",
                        bn = "গুগল রিকমেন্ড করে কোডে সবসময় val ব্যবহার করতে, যাতে কোনো বাগ বা অনাকাঙ্ক্ষিত পরিবর্তন না ঘটে।"
                    ),
                    kotlinCode = """fun main() {
    val birthYear = 2005   // পাথরে খোদাই: পরিবর্তন অসম্ভব!
    var coinCount = 100    // হোয়াইটবোর্ড: পরিবর্তনযোগ্য!

    coinCount = coinCount + 20 // এখন ১২০ হলো!
    // birthYear = 2008       // ভুল! কোটলিন এরর দেবে!

    println("কয়েন সংখ্যা: ${'$'}coinCount")
}"""
                )
            )
        ),

        // =========================================================================
        // TRACK 5: PYTHON (সহজ স্ক্রিপ্টিং ও অটোমেশন)
        // =========================================================================
        Lesson(
            id = "python_zero_1",
            track = TrackType.PYTHON,
            level = LearningLevel.BEGINNER,
            title = localized(
                en = "Python 101: Clean English-Like Code & APK Tools",
                bn = "পাইথন ১০১: সহজ ইংরেজির মতো কোডিং ও এপিকে অটোমেশন"
            ),
            subtitle = localized(
                en = "How security researchers and developers automate APK analysis in Python",
                bn = "কোনো সেমিকোলন বা জটিল ব্র্যাকেট ছাড়া কয়েক লাইনে এপিকে ফাইল আনপ্যাক করা"
            ),
            estimatedMinutes = 6,
            sections = listOf(
                LessonSection(
                    title = localized(
                        en = "Talking to Computer in Plain English",
                        bn = "কম্পিউটারের সাথে সাধারণ মানুষের ভাষায় কথা বলার মতো"
                    ),
                    childAnalogy = localized(
                        en = "Python is so simple that reading it feels like reading an English story book! No curly brackets or semicolons required.",
                        bn = "পাইথন এতই সহজ ও চমৎকার যে এর কোড পড়লে মনে হয় সাধারণ গল্পের বই পড়া হচ্ছে! কোনো সেমিকোলন বা জটিল ব্র্যাকেট লাগে না।"
                    ),
                    content = localized(
                        en = "Security engineers use Python to unzip APKs, scan classes.dex, and find vulnerability patterns in seconds.",
                        bn = "সিকিউরিটি ইঞ্জিনিয়াররা পাইথন দিয়ে স্বয়ংক্রিয়ভাবে APK আনজিপ করে সেকেন্ডের মধ্যে classes.dex ফাইল বের করে ফেলে।"
                    ),
                    pythonCode = """import zipfile

def extract_dex_from_apk(apk_file):
    with zipfile.ZipFile(apk_file, 'r') as archive:
        archive.extract('classes.dex', './extracted_files')
        print("সফলভাবে APK থেকে DEX ফাইল বের করা হয়েছে! 🎉")

# extract_dex_from_apk("game.apk")"""
                )
            )
        )
    )
}
