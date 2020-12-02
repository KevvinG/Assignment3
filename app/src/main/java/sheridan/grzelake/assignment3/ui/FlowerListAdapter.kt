package sheridan.grzelake.assignment3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sheridan.grzelake.assignment3.R
import sheridan.grzelake.assignment3.databinding.FlowerListItemBinding
import sheridan.grzelake.assignment3.model.Flower

class FlowerListAdapter (): ListAdapter<Flower, FlowerListAdapter.ViewHolder>(FlowerDiffCallback()){

    class ViewHolder private constructor(private val binding: FlowerListItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(count: Int, flower: Flower){
            binding.count.text = binding.root.context.getString(R.string.count, count)

            binding.flower = flower
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FlowerListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position + 1, getItem(position))
    }

    class FlowerDiffCallback : DiffUtil.ItemCallback<Flower>() {
        override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem == newItem
        }
    }
}