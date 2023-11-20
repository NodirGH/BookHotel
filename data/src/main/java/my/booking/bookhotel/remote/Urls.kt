package my.booking.bookhotel.remote

import my.booking.bookhotel.remote.BaseUrl.V

object BaseUrl{
    const val DEV_URL = "https://run.mocky.io/"
    const val V = "v3/"
}

object Home {
    const val getHotelDetails = "${V}d144777c-a67f-4e35-867a-cacc3b827473"
}