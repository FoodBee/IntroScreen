package com.example.introscreen

import `in`.foodbee.intro_screen.IntroActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class MainActivity : IntroActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {

    titleArray = arrayOf(
        getString(R.string.slide_1_title), getString(R.string.slide_2_title),
        getString(R.string.slide_3_title)
    )

    imageArray = arrayOf(
        getDrawable(R.drawable.driver),
        getDrawable(R.drawable.food1),
        getDrawable(R.drawable.food2)
    )

    dotInactiveColor = ContextCompat.getColor(this, R.color.dot_inactive)
    dotActiveColor = ContextCompat.getColor(this, R.color.colorAccent)

    subtitleText = getString(R.string.ready_to_order_from_your_favourite_restaurants)

    super.onCreate(savedInstanceState)

    binding.getStartedBtn.setOnClickListener {

    }
  }

}
