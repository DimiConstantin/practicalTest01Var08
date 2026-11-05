package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    private lateinit var riddleTextView: EditText
    private lateinit var answerEditText: EditText
    private lateinit var playButton : Button

    private lateinit var lastResult : String

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            val hiddenAnswer = intent.getStringExtra("answer")

            Toast.makeText(context, "The hint is $hiddenAnswer", Toast.LENGTH_SHORT).show()
        }
    }

    private val playActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val returnedAnswer = result.data?.getStringExtra("returned_answer")
            val originalAnswer = answerEditText.text.toString()

            if (returnedAnswer == originalAnswer) {
                Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
                lastResult = "Yes"
            } else {
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                lastResult = "No"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val filter = IntentFilter()

        filter.addAction("ro.pub.cs.systems.eim.practicaltest01var08.ANSWER")

        ContextCompat.registerReceiver(
            this,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        lastResult = ""

        riddleTextView = findViewById(R.id.riddle)
        answerEditText = findViewById(R.id.answer)
        playButton = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            val answer = answerEditText.text.toString()
            val riddle = riddleTextView.text.toString()
            if (answer.isNotEmpty() && riddle.isNotEmpty()) {
                Log.d("MainActivity", "Starting PracticalTest01Var08PlayActivity")
                val intent = Intent(this, PracticalTest01Var08PlayActivity::class.java).apply {
                    putExtra("answer", answer)
                    putExtra("riddle", riddle)
                }
                playActivityLauncher.launch(intent)

                val intent2 = Intent(this, PracticalTest01Var08Service::class.java)
                intent2.putExtra("answer", answer)
                intent2.putExtra("riddle", riddle)
                startService(intent2)
                Log.d("MainActivity", "Starting PracticalTest01Var08Service")
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("lastResult", lastResult)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("lastResult")) {
            lastResult = savedInstanceState.getString("lastResult", "")
            Log.d("MainActivity", "Last result: $lastResult")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}