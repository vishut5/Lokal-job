package com.vishu.jobsapp.Paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vishu.jobsapp.Models.JobDetails
import com.vishu.jobsapp.Models.JobsEntity
import com.vishu.jobsapp.ViewModels.JobsViewModel
import com.vishu.jobsapp.databinding.JobItemBinding

class JobsPagingAdapter(private val onClick : (JobDetails) -> Unit, private val viewModel: JobsViewModel) : PagingDataAdapter<JobDetails, JobsPagingAdapter.JobsViewHolder> (ComparatorDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val binding = JobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class JobsViewHolder(private val binding: JobItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(result: JobDetails) {
            binding.jobTitle.text = result.title
            binding.jobCompany.text = result.companyName

            binding.root.setOnClickListener{
                onClick(result)

            }

            binding.saveBtn.setOnClickListener {
                val savedJobs = JobsEntity(
                    title = result.title,
                    companyName = result.companyName,
                    salary = result.primaryDetails.salary,
                    place = result.primaryDetails.place,
                    jobType = result.primaryDetails.jobType,
                    experience = result.primaryDetails.experience,
                    qualification = result.primaryDetails.qualification,
                    jobRole = result.jobRole
                )
                viewModel.savedJobLocally(savedJobs)
                Toast.makeText(binding.root.context, "Job Bookmarked", Toast.LENGTH_SHORT).show()
            }
        }



    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<JobDetails>() {
        override fun areItemsTheSame(oldItem: JobDetails, newItem: JobDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: JobDetails, newItem: JobDetails): Boolean {
            return oldItem == newItem
        }
    }


}