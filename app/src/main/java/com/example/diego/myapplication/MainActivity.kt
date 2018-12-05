package com.example.diego.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tap_me_button :Button
    internal lateinit var score_text_view :TextView
    internal lateinit var time_text_view :TextView
    internal var score = 0
    internal var start_game = false
    internal lateinit var countDownTimer: CountDownTimer
    internal var countInitial: Long = 30000
    internal var countInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tap_me_button = findViewById<Button>(R.id.tap_me_button)
        score_text_view = findViewById<TextView>(R.id.score_text_view)
        time_text_view = findViewById<TextView>(R.id.time_text_view)
        resetGame()

        tap_me_button.setOnClickListener { view ->
            setIncrement()
        }
    }

    private fun resetGame(){
        score = 0
        score_text_view.text  = getString(R.string.score_game, score.toString())
        time_text_view.text = getString(R.string.time_game, score.toString())
        val initial_time =  countInitial/1000
        time_text_view.text = getString(R.string.time_game, initial_time.toString())
        countDownTimer = object: CountDownTimer(countInitial, countInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val time =  millisUntilFinished / 1000
                time_text_view.text = getString(R.string.time_game, time.toString())
            }

            override fun onFinish() {
                endGame()
            }
        }
        start_game = false
    }

    private fun startGame(){
        start_game = true
        countDownTimer.start()
    }

    private fun setIncrement() {
        if (!start_game){
            startGame()
        }
        score = score + 1
        val new_score = getString(R.string.score_game, score.toString())
        score_text_view.text = new_score

    }

    private  fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message, score.toString()), Toast.LENGTH_LONG).show()
        resetGame()
    }
}
