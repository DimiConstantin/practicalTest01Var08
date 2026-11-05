package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var riddleTextView: EditText
    private lateinit var answerEditText: EditText
    private lateinit var playButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        riddleTextView = findViewById(R.id.riddle)
        answerEditText = findViewById(R.id.answer)
        playButton = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            val answer = answerEditText.text.toString()
            val riddle = riddleTextView.text.toString()
            if (answer != null && riddle != null) {
                Log.d("MainActivity", "Starting PracticalTest01Var08PlayActivity")
                val intent = Intent(this, PracticalTest01Var08PlayActivity::class.java)
                intent.putExtra("answer", answer)
                intent.putExtra("riddle", riddle)
                startActivity(intent)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}