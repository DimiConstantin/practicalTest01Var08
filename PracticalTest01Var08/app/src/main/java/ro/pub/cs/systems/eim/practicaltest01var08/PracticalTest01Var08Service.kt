package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class PracticalTest01Var08Service : Service() {

    private var thread: Thread? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("MainActivity", "onStartCommand() method was invoked")

        val answer = intent?.getStringExtra("answer")
        val riddle = intent?.getStringExtra("riddle")

        thread = Thread {
            while (!Thread.currentThread().isInterrupted) {
                try {
                    Log.d("MainActivity", "Answer: $answer, Riddle: $riddle")

                    val action = "ro.pub.cs.systems.eim.practicaltest01var08.ANSWER"

                    val answerlen = answer?.length ?: 0
                    if (answerlen > 0 && answer != null) {
                        val randomIndex = (0 until answerlen).random()
                        val answerClone = answer.toCharArray()

                        for (i in 0 until answerlen) {
                            if (i != randomIndex) {
                                answerClone[i] = '*'
                            }
                        }

                        val hiddenAnswer = String(answerClone)

                        Log.d("MainActivity", "Hidden answer is $hiddenAnswer")

                        val broadcast = Intent(action).apply {
                            setPackage(packageName)
                            putExtra("answer", hiddenAnswer)
                        }
                        sendBroadcast(broadcast)
                    }

                    Thread.sleep(5000)
                } catch (_: InterruptedException) {
                    break
                }
            }
        }

        thread?.start()

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        thread?.interrupt()
        super.onDestroy()
    }
}