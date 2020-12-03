package sheridan.grzelake.assignment3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import sheridan.grzelake.assignment3.R
import sheridan.grzelake.assignment3.databinding.FragmentFlowerListBinding
import sheridan.grzelake.assignment3.model.Flower

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FlowerListFragment : Fragment() {

    private val viewModel: FlowerListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFlowerListBinding.inflate(inflater)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)

        val adapter = FlowerListAdapter(FlowerClickedListener { flowerId -> viewModel.onFlowerClicked(flowerId) })

        binding.recyclerView.adapter = adapter


        viewModel.getFlowers().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.selectedFlower.observe(viewLifecycleOwner) {
                flowerDetails -> flowerDetails?.let {
            findNavController().navigate (
                FlowerListFragmentDirections.actionFlowerListFragmentToDetailsFragment(flowerDetails))
            viewModel.onNavComplete()
        }
        }

        return binding.root
    }

}

