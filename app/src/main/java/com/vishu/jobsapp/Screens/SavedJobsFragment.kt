package com.vishu.jobsapp.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishu.jobsapp.Models.JobsEntity
import com.vishu.jobsapp.R
import com.vishu.jobsapp.ViewModels.JobsViewModel
import com.vishu.jobsapp.databinding.FragmentSavedJobsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedJobsFragment : Fragment() {

    private var _binding : FragmentSavedJobsBinding? = null
    private val binding get() = _binding!!

    private val offlineJobViewModel by viewModels<JobsViewModel>()

    private lateinit var adapter: OfflineItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OfflineItemAdapter(::onItemClicked)
        binding.savedJobsRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.savedJobsRecycler.adapter = adapter
        binding.savedJobsRecycler.setHasFixedSize(true)

        bindObservers()

        // Fetch locally saved products
        lifecycleScope.launch {
            offlineJobViewModel.getLocalJobs()
        }

    }

    private fun bindObservers() {
        offlineJobViewModel.localListData.observe(viewLifecycleOwner) { jobs ->
            adapter.submitList(jobs)
        }
    }

    private fun onItemClicked(jobsData : JobsEntity){
        val bundle = Bundle();
        bundle.putString("savedJobData", Gson().toJson(jobsData))
        bundle.putString("source","SavedJobsFragment")
        findNavController().navigate(R.id.action_savedJobsFragment_to_detailsFragment,bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}