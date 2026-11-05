package ro.pub.cs.systems.eim.practicaltest01var08

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var08PlayActivity : AppCompatActivity() {

    private lateinit var riddleTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_play)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        riddleTextView = findViewById(R.id.riddleSecond)
        riddleTextView.text = intent.getStringExtra("riddle")

        answerEditText = findViewById(R.id.answerSecond)

        checkButton = findViewById(R.id.checkButton)
        checkButton.setOnClickListener {
            val answer = answerEditText.text.toString()
            if (answer == intent.getStringExtra("answer")) {
                Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
            }
        }
    }
}