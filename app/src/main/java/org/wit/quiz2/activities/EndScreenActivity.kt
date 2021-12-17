package org.wit.quiz2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.wit.quiz2.R

class EndScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,SplashScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}