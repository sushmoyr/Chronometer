package com.sushmoyr.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    private var isRunning:Boolean = false
    private lateinit var chronometer: Chronometer
    private var offset : Long = 0L
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var lottie: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lottie = findViewById(R.id.lottie)
        chronometer = findViewById(R.id.chronometer)
        startButton = findViewById(R.id.start)
        pauseButton = findViewById(R.id.pause)
        pauseButton.alpha = 0.5f
        pauseButton.isClickable = false
        lottie.pauseAnimation()
        lottie.progress = 0.75f

    }

    fun reset(view: View) {
        chronometer.base = SystemClock.elapsedRealtime()
        offset = 0
        pauseButton.alpha = 0.5f
        pauseButton.isClickable = false
        startButton.text = getString(R.string.start)
        startButton.isClickable = true
        startButton.alpha = 1f
        isRunning = false
        chronometer.stop()
        lottie.pauseAnimation()
        lottie.progress = 0.75f
    }
    fun pauseChronometer(view: View) {
        if(isRunning)
        {
            offset = SystemClock.elapsedRealtime() - chronometer.base
            chronometer.stop()
            startButton.text = getString(R.string.resume)
            pauseButton.animate().alpha(0.5f).duration = 500
            pauseButton.isClickable = false

            startButton.animate().alpha(1f).duration = 500
            startButton.isClickable = true
            isRunning = false
            lottie.pauseAnimation()
        }

    }
    fun startChronometer(view: View) {
        if(!isRunning)
        {
            chronometer.base = SystemClock.elapsedRealtime() - offset
            chronometer.start()
            isRunning = true
            startButton.animate().alpha(0.5f).duration = 500
            startButton.isClickable = false

            pauseButton.animate().alpha(1f).duration = 500
            pauseButton.isClickable = true
            lottie.resumeAnimation()
        }
    }
}