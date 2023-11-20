package my.booking.bookhotel.mobile

import android.app.Application
import my.booking.bookhotel.BuildConfig
import my.booking.bookhotel.mobile.utils.LiveEvent
import timber.log.Timber

class BookHotelApplication: Application() {

    companion object {
        @JvmStatic
        val fcmMessages: LiveEvent<Any> = LiveEvent()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}