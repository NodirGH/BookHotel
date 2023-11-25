package my.booking.bookhotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.databinding.FragmentBookRoomBinding
import my.booking.bookhotel.databinding.FragmentMainBinding
import my.booking.bookhotel.dto.RoomDetailsDto
import my.booking.bookhotel.mobile.ui.adapter.BannerAdapter
import my.booking.bookhotel.mobile.ui.adapter.PeculiarityAdapter
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.ui.room.BookRoomAdapter
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.show
import javax.inject.Inject

@AndroidEntryPoint
class BookRoomFragment : Fragment(), BookRoomAdapter.HotelActionListener {

    @Inject
    lateinit var adapter: BookRoomAdapter

    private lateinit var binding: FragmentBookRoomBinding
    private val viewModel: HomeViewModel by viewModels()
    private val args : BookRoomFragmentArgs by navArgs()
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var peculiarityAdapter: PeculiarityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookRoomBinding.inflate(inflater, container, false)
        bannerAdapter = BannerAdapter()
        peculiarityAdapter = PeculiarityAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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