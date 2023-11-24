package my.booking.bookhotel.mobile.base

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.booking.bookhotel.MainActivity
import my.booking.bookhotel.local.AppPreference
import javax.inject.Inject


class SecurityService @Inject constructor(
    val application: Application,
    val appPreferences: AppPreference
) {

}