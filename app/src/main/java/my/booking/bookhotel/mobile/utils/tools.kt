package my.booking.bookhotel.mobile.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import my.booking.bookhotel.R
import my.booking.bookhotel.remote.BaseUrl.DEV_URL
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun ImageView.loadFromUrl(
    url: String?,
    @DrawableRes placeHolder: Int = R.drawable.ic_launcher_background,
) {
    try {
        Glide.with(this)
            .load("${DEV_URL}$url")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.avatar_placeholder)
            .error(placeHolder)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
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