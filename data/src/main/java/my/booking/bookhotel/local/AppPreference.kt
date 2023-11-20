package my.booking.bookhotel.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreference @Inject constructor(val context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences("BookHotelPref", Context.MODE_PRIVATE)

    private var devicePreference: SharedPreferences =
        context.getSharedPreferences("BookHotelDevicePref", Context.MODE_PRIVATE)

    fun clearAll() {
        preferences.edit().clear().apply()
    }
}