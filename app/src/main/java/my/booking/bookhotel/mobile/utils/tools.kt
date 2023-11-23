package my.booking.bookhotel.mobile.utils

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import my.booking.bookhotel.BuildConfig
import my.booking.bookhotel.R
import my.booking.bookhotel.remote.BaseUrl.DEV_URL
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
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

fun Long.formatBalance(): SpannableString {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply { groupingSeparator = ' ' }
        .apply { decimalSeparator = '.' }
    val decimalFormat = DecimalFormat("#,###.##", symbols)
    var formatBalance = decimalFormat.format(this)
    if (formatBalance.substringAfter(".", "").length == 1) {
        formatBalance += "0"
    }
    val spannable = SpannableString(formatBalance)
    if (formatBalance.contains(".")) {
        spannable.setSpan(
            RelativeSizeSpan(0.8f),
            formatBalance.indexOf("."),
            formatBalance.length,
            0
        )
    }
    return spannable
}