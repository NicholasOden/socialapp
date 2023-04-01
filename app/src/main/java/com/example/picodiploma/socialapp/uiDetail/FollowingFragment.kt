package com.example.picodiploma.socialapp.uiDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picodiploma.socialapp.R
import com.example.picodiploma.socialapp.Response.User
import com.example.picodiploma.socialapp.Adapter.UserListAdapter
import com.example.picodiploma.socialapp.databinding.FragmentFollowBinding

class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var userAdapter: UserListAdapter
    private lateinit var viewModel: FollowingViewModel
    private val userMap = HashMap<String, User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FollowingViewModel::class.java)
        userAdapter = UserListAdapter(emptyList()) { /* handle click here */ }

        binding.recyclerViewDetail.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userAdapter
        }

        val login = arguments?.getString(LOGIN_EXTRA)
        if (login != null) {

            binding.progressBarDetail.visibility = View.VISIBLE

            viewModel.loadFollowings(login)
            viewModel.followings.observe(viewLifecycleOwner, { following ->
                userAdapter.setUserList(following)
                binding.progressBarDetail.visibility = View.GONE
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val LOGIN_EXTRA = "login_extra"

        fun newInstance(login: String): FollowingFragment {
            val fragment = FollowingFragment()
            fragment.arguments = Bundle().apply { putString(LOGIN_EXTRA, login) }
            return fragment
        }
    }
}
