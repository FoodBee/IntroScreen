package com.example.introscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.introscreen.R.color
import com.example.introscreen.R.layout
import com.example.introscreen.R.string
import com.example.introscreen.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

  private var layouts: IntArray = intArrayOf(
      layout.slide_1,
      layout.slide_2,
      layout.slide_3
  )

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)


    setUpClickListeners()

    setUpViewPager()
  }

  private fun setUpViewPager() {
    addBottomDotsAndText(0)
    val handler = Handler()
    val update = Runnable {
      binding.sliderViewPager.currentItem = (binding.sliderViewPager.currentItem + 1) % 3
    }
    val timer = Timer()
    timer.schedule(object : TimerTask() {
      override fun run() {
        handler.post(update)
      }
    }, 2000, 2000)
    val myViewPagerAdapter = IntroViewPagerAdapter()
    binding.sliderViewPager.adapter = myViewPagerAdapter
    val viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
      }

      override fun onPageSelected(position: Int) {
        addBottomDotsAndText(position)
      }

      override fun onPageScrollStateChanged(state: Int) {}
    }
    binding.sliderViewPager.addOnPageChangeListener(viewPagerPageChangeListener)
  }

  private fun setUpClickListeners() {
    binding.getStartedBtn.setOnClickListener { v: View? ->
      startActivity(Intent(this, LoginActivity::class.java))
      finish()
    }
  }

  private fun addBottomDotsAndText(i: Int) {
    when (i) {
      0 -> {
        binding.apply {
          headlineTv.text = getString(string.slide_1_title)
          dot0Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.colorAccent))
          dot1Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.dot_inactive))
          dot2Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.dot_inactive))
        }
      }
      1 -> {
        binding.apply {
          headlineTv.text = getString(string.slide_2_title)
          dot0Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.dot_inactive))
          dot1Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.colorAccent))
          dot2Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.dot_inactive))
        }
      }
      2 -> {
        binding.apply {
          headlineTv.text = getString(string.slide_3_title)
          dot0Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.dot_inactive))
          dot1Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.dot_inactive))
          dot2Tv.setTextColor(ContextCompat.getColor(this@MainActivity, color.colorAccent))
        }
      }
    }

  }

  inner class IntroViewPagerAdapter : PagerAdapter() {
    override fun instantiateItem(
      container: ViewGroup,
      position: Int
    ): Any {
      val layoutInflater =
        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
      val view = layoutInflater.inflate(layouts[position], container, false)
      container.addView(view)
      return view
    }

    override fun getCount(): Int {
      return layouts.size
    }

    override fun isViewFromObject(
      view: View,
      obj: Any
    ): Boolean {
      return view === obj
    }

    override fun destroyItem(
      container: ViewGroup,
      position: Int,
      `object`: Any
    ) {
      val view = `object` as View
      container.removeView(view)
    }
  }

}
