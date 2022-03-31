package com.example.idleclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentEnemy: Int = 1
    private var mEnemiesList: ArrayList<Enemy>? = null
    private var time:Int = 30
    private var count:Int = 0
    private var damage:Int = 5
    private lateinit var timer:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEnemiesList = Enemies.getEnemies()

        btn_start.isVisible = true
        btn_click.isVisible = false
        btn_click.setOnClickListener(this)
        btn_start.setOnClickListener(this)

        timer = object: CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                time--;
                tv_time.setText("Time : " + time);

            }

            override fun onFinish() {
                mCurrentEnemy=1
                btn_start.isVisible = true
                btn_click.isVisible = false
                tv_time.setText("Time: 0")
            }

        }



    }
    private fun setEnemies(){


        val enemy = mEnemiesList!![mCurrentEnemy-1]



        tv_enemyName.text =enemy.name
        enemyImg.setImageResource(enemy.image)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_click -> {
                if(hp.progress==0){
                    mCurrentEnemy++
                    if(mCurrentEnemy>5){
                        tv_time.setText("Time: 0")
                        enemyImg.setImageResource(R.drawable.win)
                        tv_enemyName.text="You win!"

                        timer.cancel()
                        timer.onFinish()

                    }
                    else{
                        setEnemies()
                        hp.progress=100
                        damage=mEnemiesList!![mCurrentEnemy-1].resist
                    }
                }
                hp.progress=hp.progress-damage
                damage++
                enemyImg.animate().apply {
                    duration=50
                    scaleX(1.1F)
                    scaleY(1.1F)

                }.withEndAction{
                    enemyImg.animate().apply {
                        duration=50
                        scaleX(1F)
                        scaleY(1F)
                    }

                }
            }
            R.id.btn_start->{
                mCurrentEnemy=1
                setEnemies()
                timer.start()
                btn_start.isVisible = false
                btn_click.isVisible = true
                count = 0
                time = 30
                tv_time.setText("Time: " + time)
                hp.progress = 100

            }
        }
    }
}