package com.vishu.jobsapp.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vishu.jobsapp.Models.JobDetails
import com.vishu.jobsapp.Models.JobsEntity
import com.vishu.jobsapp.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var itemData : JobDetails? = null
    private var offlineitemData : JobsEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val source = arguments?.getString("source")
        when(source){
            "JobsFragment" -> {
                setJobData()
            }
            "SavedJobsFragment" -> {
                setOfflineJobData()
            }
        }

    }

    private fun setJobData() {
        val json = arguments?.getString("jobData")
        if(json != null){
            itemData = Gson().fromJson(json, JobDetails::class.java)

            itemData?.let {item ->
                binding.tvPlace.text = "Place : ${item.primaryDetails.place}"
                binding.tvSalary.text = "Salary : ${item.primaryDetails.salary}"
                binding.tvJobType.text = "Job Type : ${item.primaryDetails.jobType}"
                binding.tvExperience.text = "Experience : ${item.primaryDetails.experience}"
                binding.tvQualification.text = "Qualification : ${item.primaryDetails.qualification}"
                binding.tvCompanyName.text = "Company : ${item.companyName}"
                binding.tvJobRole.text = "Job Role : ${item.jobRole}"

            }
        }
        else{
            Snackbar.make(binding.root, "Something went wrong!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setOfflineJobData() {
        val json = arguments?.getString("savedJobData")
        if(json != null){
            offlineitemData = Gson().fromJson(json, JobsEntity::class.java)

            offlineitemData?.let {item ->
                binding.tvPlace.text = "Place : ${item.place}"
                binding.tvSalary.text = "Salary : ${item.salary}"
                binding.tvJobType.text = "Job Type : ${item.jobType}"
                binding.tvExperience.text = "Experience : ${item.experience}"
                binding.tvQualification.text = "Qualification : ${item.qualification}"
                binding.tvCompanyName.text = "Company : ${item.companyName}"
                binding.tvJobRole.text = "Job Role : ${item.jobRole}"

            }
        }
        else{
            Snackbar.make(binding.root, "Something went wrong!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}