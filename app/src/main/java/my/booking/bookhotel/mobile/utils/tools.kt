package my.booking.bookhotel.mobile.utils

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.booking.bookhotel.R
import my.booking.bookhotel.remote.BaseUrl.DEV_URL
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

//fun ImageView.loadFromUrl(
//    url: String?,
//    @DrawableRes placeHolder: Int = R.drawable.ic_launcher_background,
//) {
//    try {
//        Glide.with(this)
//            .load("${DEV_URL}$url")
//            .centerCrop()
//            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//            .placeholder(R.drawable.avatar_placeholder)
//            .error(placeHolder)
//            .into(this)
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//}

fun ImageView.loadFromUrl(url: String, placeholderResId: Int? = null) {
    val requestOptions = RequestOptions()
        .placeholder(placeholderResId ?: R.drawable.hotel_placeholder)
        .error(placeholderResId ?: android.R.drawable.ic_menu_close_clear_cancel)

    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun View.show() {
    this.visibility = (View.VISIBLE)
}
fun View.hide(): View {
    this.visibility = (View.GONE)
    return this
}

fun formatNumberWithCurrency(number: Long): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))

    if (currencyFormat is DecimalFormat) {
        currencyFormat.positivePrefix = ""
        currencyFormat.positiveSuffix = " ₽"
        currencyFormat.maximumFractionDigits = 0
    }

    return currencyFormat.format(number)
}

fun formatNumberWithCurrency(number: Int): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))

    if (currencyFormat is DecimalFormat) {
        currencyFormat.positivePrefix = ""
        currencyFormat.positiveSuffix = " ₽"
        currencyFormat.maximumFractionDigits = 0
    }

    return currencyFormat.format(number)
}

private fun ViewPager2.setCurrentItem(
    item: Int,
    duration: Long,
    interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
    pagePxWidth: Int = width,
) {
    try {
        val pxToDrag: Int = pagePxWidth * (item - currentItem)
        val animator = ValueAnimator.ofInt(0, pxToDrag)
        var previousValue = 0
        animator.addUpdateListener { valueAnimator ->
            val currentValue = valueAnimator.animatedValue as Int
            val currentPxToDrag = (currentValue - previousValue).toFloat()
            fakeDragBy(-currentPxToDrag)
            previousValue = currentValue
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                beginFakeDrag()
            }

            override fun onAnimationEnd(animation: Animator?) {
                endFakeDrag()
            }

            override fun onAnimationCancel(animation: Animator?) { /* Ignored */
            }

            override fun onAnimationRepeat(animation: Animator?) { /* Ignored */
            }
        })
        animator.interpolator = interpolator
        animator.duration = duration
        animator.start()
    } catch (e: Exception) {

    }
}

fun ViewPager2.setAutoScroll(delay: Long, count: Int, scrollDuration: Long) {
    try {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                var current = 0
                current = currentItem + 1
                if (current == count) current = 0
                setCurrentItem(current, if (current == 0) 0 else scrollDuration)
                delay(delay)
            }
        }
    } catch (e: Exception) {

    }
}