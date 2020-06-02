package com.example.introscreen

import `in`.foodbee.intro_screen.IntroActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class MainActivity : IntroActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val titleArray = arrayOf(
        getString(R.string.slide_1_title), getString(R.string.slide_2_title),
        getString(R.string.slide_3_title)
    )

    val imageArray = arrayOf(
        getDrawable(R.drawable.driver),
        getDrawable(R.drawable.food1),
        getDrawable(R.drawable.food2)
    )

    val dotInActiveColor = ContextCompat.getColor(this, R.color.dot_inactive)
    val dotActiveColor = ContextCompat.getColor(this, R.color.colorAccent)

    val subtitleText = getString(R.string.ready_to_order_from_your_favourite_restaurants)

    initSlide(titleArray, imageArray, subtitleText, dotInActiveColor, dotActiveColor)



    binding.getStartedBtn.setOnClickListener {

    }
  }

}
