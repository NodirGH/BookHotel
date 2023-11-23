package my.booking.bookhotel.mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.R
import my.booking.bookhotel.databinding.FragmentBookingBinding
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.show

@AndroidEntryPoint
class BookingFragment : Fragment() {
    private lateinit var binding: FragmentBookingBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.booking()

        viewModel.getBookingDetails.observe(requireActivity(), Observer { booking ->
            binding.shimmerFrameLayout.hide()
            binding.scrollView2.show()
            binding.llBtnPay.show()
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
            binding.tvTour.text = "${booking.tourPrice} ₽"
            binding.tvFuelFee.text = "${booking.fuelCharge} ₽"
            binding.tvServiceFee.text = "${booking.serviceCharge} ₽"
            val payment = booking.tourPrice + booking.fuelCharge + booking.serviceCharge
            binding.tvPayment.text = "$payment ₽"
            binding.tvBtnPay.text = "${getString(R.string.Pay)} $payment ₽"
        })

        binding.llPhoneNumber.setOnClickListener {
            binding.etPhoneNumber.requestFocus()
        }

        binding.llBtnPay.setOnClickListener {
            findNavController().navigate(BookingFragmentDirections.actionBookingFragmentToBookingReadyFragment())
        }
    }

}