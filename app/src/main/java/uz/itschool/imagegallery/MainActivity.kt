package uz.itschool.imagegallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() , OnClickListener {

    private lateinit var img0 : ImageView
    private lateinit var img1 : ImageView
    private lateinit var img2 : ImageView
    private lateinit var img3 : ImageView
    private lateinit var img4 : ImageView
    private lateinit var img5 : ImageView

    private lateinit var buttonBackArrow : Button
    private lateinit var buttonBack : Button
    private lateinit var buttonForward : Button

    private lateinit var open : ConstraintLayout
    private lateinit var frontImage : ImageView
    private lateinit var scrollView: ScrollView



    private var images = arrayOf(R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5)
    private var myImageId : Int = 0

    private lateinit var animation :Animation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        open = findViewById(R.id.open)
        frontImage = findViewById(R.id.frontIMG)

        buttonBackArrow = findViewById(R.id.back_arrow)
        buttonBack = findViewById(R.id.back)
        buttonForward = findViewById(R.id.forward)


        scrollView = findViewById(R.id.scroll)

        img0 = findViewById(R.id.img0)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)

        img0.setOnClickListener(this)
        img1.setOnClickListener(this)
        img2.setOnClickListener(this)
        img3.setOnClickListener(this)
        img4.setOnClickListener(this)
        img5.setOnClickListener(this)

        buttonBackArrow.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.goaway)
            open.startAnimation(animation)
            open.visibility = GONE
            scrollView.visibility = VISIBLE
        }

        buttonForward.setOnClickListener {
            if (myImageId < images.size - 1) myImageId++
            update(1)
        }
        buttonBack.setOnClickListener {
            if (myImageId > 0) myImageId--
            update(-1)
        }


    }

    override fun onClick(view: View?) {
        myImageId = view!!.tag.toString().toInt()
        open.visibility = VISIBLE
        scrollView.visibility = GONE
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.come)
        update(0)
    }

    private fun update(a:Int){
        buttonBack.visibility = if (myImageId > 0) VISIBLE else GONE
        buttonForward.visibility = if (myImageId == images.size - 1) GONE else VISIBLE


        frontImage.setImageResource(images[myImageId])
        if (a != 0){
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.comeright)
            if (a == -1) animation = AnimationUtils.loadAnimation(applicationContext, R.anim.comeleft)

            frontImage.startAnimation(animation)
        }
    }
}