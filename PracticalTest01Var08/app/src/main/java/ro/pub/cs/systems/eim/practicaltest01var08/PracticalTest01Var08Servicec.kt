package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.lang.Thread.sleep
import java.util.Date
import kotlin.math.sqrt

class PracticalTest01Var08Servicec : Service() {

    private var thread: Thread? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.d("PracticalTest01Service", "onStartCommand() method was invoked")

        thread = Thread {
            while (true) {

            }
        }

        thread?.start()

        return START_NOT_STICKY
    }
}