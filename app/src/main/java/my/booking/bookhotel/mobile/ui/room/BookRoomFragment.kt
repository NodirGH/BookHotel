package my.booking.bookhotel.mobile.ui.room

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.databinding.FragmentBookRoomBinding
import my.booking.bookhotel.dto.RoomDetailsDto
import my.booking.bookhotel.mobile.base.BaseFragment
import my.booking.bookhotel.mobile.ui.adapter.BannerAdapter
import my.booking.bookhotel.mobile.ui.adapter.PeculiarityAdapter
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.show
import javax.inject.Inject

@AndroidEntryPoint
class BookRoomFragment : BaseFragment<FragmentBookRoomBinding>(FragmentBookRoomBinding::inflate),
    BookRoomAdapter.HotelActionListener {

    @Inject
    lateinit var adapter: BookRoomAdapter

    private val viewModel: HomeViewModel by viewModels()
    private val args: BookRoomFragmentArgs by navArgs()
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var peculiarityAdapter: PeculiarityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannerAdapter = BannerAdapter()
        peculiarityAdapter = PeculiarityAdapter()
        binding.tvHotelName.text = args.hotelName


        viewModel.getRoomDetails()
        adapter.setOnActionListener(this)
        adapter.setBannerAdapter(bannerAdapter)
        adapter.setPeculiarityAdapter(peculiarityAdapter)

        binding.rvBookRoom.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookRoom.adapter = adapter

        viewModel.getRoomsDetails.observe(requireActivity()) { room ->
            binding.shimmerFrameLayout.hide()
            binding.rvBookRoom.show()
            adapter.submitList(room)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onHotelRoomClick(roomDetailsDto: RoomDetailsDto) {
        findNavController().navigate(BookRoomFragmentDirections.actionBookRoomFragmentToBookingFragment())
    }
}