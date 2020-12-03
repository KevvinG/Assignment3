package sheridan.grzelake.assignment3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import sheridan.grzelake.assignment3.R
import sheridan.grzelake.assignment3.databinding.FragmentDetailsBinding
import sheridan.grzelake.assignment3.databinding.FragmentFlowerListBinding
import sheridan.grzelake.assignment3.model.Flower

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val data = arguments?.getSerializable("flowerDetails") as Flower

        val binding = FragmentDetailsBinding.inflate(inflater)

        //binding.textviewSecond.text = data.label

        val imgurl = "http://tetervak.dev.fast.sheridanc.on.ca/Examples/jQuery/Flowers3/images/flowers/" + data.picture
        var flower = data
        flower.picture = imgurl

        binding.flower = flower
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_flowerListFragment)
        }
    }
}