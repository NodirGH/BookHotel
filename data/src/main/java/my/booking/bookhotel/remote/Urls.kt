package my.booking.bookhotel.remote

import my.booking.bookhotel.remote.BaseUrl.V

object BaseUrl{
    const val DEV_URL = "https://run.mocky.io/"
    const val V = "v3/"
}

object Home {
    const val getHotelDetails = "${V}d144777c-a67f-4e35-867a-cacc3b827473"
    const val getRoomDetails = "${V}8b532701-709e-4194-a41c-1a903af00195"
}