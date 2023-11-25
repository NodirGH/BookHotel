package my.booking.bookhotel.mobile.ui.booking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.R
import my.booking.bookhotel.databinding.FragmentBookingBinding
import my.booking.bookhotel.dto.BookingDto
import my.booking.bookhotel.mobile.base.BaseFragment
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.utils.PhoneNumberFormattingTextWatcher
import my.booking.bookhotel.mobile.utils.formatNumberWithCurrency
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.show

@AndroidEntryPoint
class BookingFragment : BaseFragment<FragmentBookingBinding>(FragmentBookingBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.booking()

        binding.etPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        viewModel.getBookingDetails.observe(viewLifecycleOwner) { booking ->
            setBookingDetails(booking)
        }

        binding.llPhoneNumber.setOnClickListener {
            binding.etPhoneNumber.requestFocus()
        }

        binding.rlBtnPay.setOnClickListener {
            findNavController().navigate(BookingFragmentDirections.actionBookingFragmentToBookingReadyFragment())
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setBookingDetails(booking: BookingDto) {
        binding.shimmerFrameLayout.hide()
        binding.scrollView2.show()
        binding.rlBtnPay.show()
        binding.tvRatingNumber.text = booking.horating.toString()
        binding.tvRatingText.text = booking.ratingName
        binding.tvHotelName.text = booking.hotelName
        binding.tvHotelAddress.text = booking.hotelAddress
        binding.tvTicketFrom.text = booking.departure
        binding.tvCountyCity.text = booking.arrivalCountry
        binding.tvDate.text = "${booking.tourDateStart} - ${booking.tourDateStop}"
        binding.tvNightAmount.text = "${booking.numberOfNights} ночей"
        binding.tvHotel.text = booking.hotelName
        binding.tvHotelRoom.text = booking.room
        binding.tvFood.text = booking.nutrition
        binding.tvTour.text = formatNumberWithCurrency(booking.tourPrice)
        binding.tvFuelFee.text = formatNumberWithCurrency(booking.fuelCharge)
        binding.tvServiceFee.text = formatNumberWithCurrency(booking.serviceCharge)
        val payment = booking.tourPrice + booking.fuelCharge + booking.serviceCharge
        binding.tvPayment.text = formatNumberWithCurrency(payment)
        binding.tvBtnPay.text = "${getString(R.string.Pay)} ${formatNumberWithCurrency(payment)}"
    }

}