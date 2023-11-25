package my.booking.bookhotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import my.booking.bookhotel.databinding.FragmentBookingReadyBinding
import my.booking.bookhotel.mobile.BookingFragmentDirections
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