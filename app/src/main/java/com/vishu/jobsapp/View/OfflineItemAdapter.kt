package com.vishu.jobsapp.View

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishu.jobsapp.Models.JobDetails
import com.vishu.jobsapp.Models.JobsEntity
import com.vishu.jobsapp.databinding.JobItemBinding
import com.vishu.jobsapp.databinding.OfflineJobItemBinding

class OfflineItemAdapter(private val onClick : (JobsEntity) -> Unit) : ListAdapter<JobsEntity, OfflineItemAdapter.ListViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = OfflineJobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }


    inner class ListViewHolder(private val binding: OfflineJobItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: JobsEntity) {
            binding.jobTitle.text = result.title
            binding.jobCompany.text = result.companyName

            binding.root.setOnClickListener{
                onClick(result)

            }
        }
    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<JobsEntity>() {

        override fun areItemsTheSame(oldItem: JobsEntity, newItem: JobsEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: JobsEntity, newItem: JobsEntity): Boolean {
            return oldItem == newItem
        }
    }
}