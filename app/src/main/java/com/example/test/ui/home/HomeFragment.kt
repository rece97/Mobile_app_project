package com.example.test.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.ui.dashboard.FishDetailFragment
import com.example.test.ui.dashboard.FishlistFragment

class HomeFragment : Fragment() {

    private val TAG = "FishListFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            // We are in portrait orientation
            // Load Detail fragment, i.e., replace listview fragment with detail fragment
            Log.d(TAG, "onCreateView: ORIENTATION_PORTRAIT")
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fishListContainer, FishlistFragment())
                .addToBackStack(null)
                .commit()
        }
        else {

            Log.d(TAG, "onCreateView: ORIENTATION_LANDSCAPE")
            // We are in landscape orientation
            // Load Detail fragment, i.e., replace the current detail fragment
            // with detail fragment containing the selected item's details
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.FishDetailContainer, FishDetailFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }


}