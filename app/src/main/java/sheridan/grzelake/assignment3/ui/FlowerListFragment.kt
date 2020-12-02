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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FlowerListFragment : Fragment() {

//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        view.findViewById<Button>(R.id.button_first).setOnClickListener {
////            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
////        }
//    }

    private val viewModel: FlowerListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = FlowerListFragmentBinding.inflate(inflater)

        val binding = FragmentFlowerListBinding.inflate(inflater)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        val adapter = FlowerListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.getFlowers().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        return binding.root
    }

}