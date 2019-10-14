package com.example.introscreen


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.*

class MainActivity : AppCompatActivity() {

    private var viewPager2: ViewPager? = null
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var layouts: IntArray? = null
    private var dot0: TextView? = null
    private var dot1: TextView? = null
    private var dot2: TextView? = null
    private var headline: TextView? = null
    private var getStarted: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager2 = findViewById(R.id.pager)

        getStarted = findViewById(R.id.getstarted)
        headline = findViewById(R.id.headline)
        dot0 = findViewById(R.id.dot_0)
        dot1 = findViewById(R.id.dot_1)
        dot2 = findViewById(R.id.dot_2)

        layouts = intArrayOf(R.layout.slide_1, R.layout.slide_2, R.layout.slide_3)

        addBottomDotsAndText(0)

        val handler = Handler()
        val update = Runnable { viewPager2!!.currentItem = (viewPager2!!.currentItem + 1) % 3 }

        getStarted!!.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_from_bottom)
        }
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 2000, 2000)
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager2!!.adapter = myViewPagerAdapter


        val viewPagerPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                addBottomDotsAndText(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }

        viewPager2!!.addOnPageChangeListener(viewPagerPageChangeListener)

    }

    private fun addBottomDotsAndText(i: Int) {
        when (i) {
            0 -> {
                headline!!.text = resources.getString(R.string.slide_1_title)
                dot0!!.setTextColor(resources.getColor(R.color.colorAccent))
                dot1!!.setTextColor(resources.getColor(R.color.dot_inactive))
                dot2!!.setTextColor(resources.getColor(R.color.dot_inactive))
            }
            1 -> {
                headline!!.text = resources.getString(R.string.slide_2_title)
                dot0!!.setTextColor(resources.getColor(R.color.dot_inactive))
                dot1!!.setTextColor(resources.getColor(R.color.colorAccent))
                dot2!!.setTextColor(resources.getColor(R.color.dot_inactive))
            }
            2 -> {
                headline!!.text = resources.getString(R.string.slide_3_title)
                dot0!!.setTextColor(resources.getColor(R.color.dot_inactive))
                dot1!!.setTextColor(resources.getColor(R.color.dot_inactive))
                dot2!!.setTextColor(resources.getColor(R.color.colorAccent))
            }
        }
    }


    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater!!.inflate(layouts!![position], container, false)
            container.addView(view)

            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

}
