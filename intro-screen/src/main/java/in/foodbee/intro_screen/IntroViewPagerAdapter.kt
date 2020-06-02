package `in`.foodbee.intro_screen

import `in`.foodbee.intro_screen.databinding.SlideBinding
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class IntroViewPagerAdapter(
  private val imageArray: Array<Drawable?>?,
  private val context: Context
) : PagerAdapter() {

  override fun instantiateItem(
    container: ViewGroup,
    position: Int
  ): View {

    val layoutInflater =
      LayoutInflater.from(context)
    val view = SlideBinding.inflate(layoutInflater, container, false)
    view.slideImage
        .setImageDrawable(imageArray?.get(position))
    container.addView(view.root)
    return view.root

  }

  override fun getCount(): Int {
    return 3
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