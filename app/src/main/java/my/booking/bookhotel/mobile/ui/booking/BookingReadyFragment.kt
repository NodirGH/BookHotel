package my.booking.bookhotel.mobile.ui.booking

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import my.booking.bookhotel.databinding.FragmentBookingReadyBinding
import my.booking.bookhotel.mobile.base.BaseFragment

class BookingReadyFragment : BaseFragment<FragmentBookingReadyBinding>(FragmentBookingReadyBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.llBtnSuper.setOnClickListener {
            findNavController().navigate(BookingReadyFragmentDirections.actionBookingReadyFragmentToMainFragment())
        }
    }
}