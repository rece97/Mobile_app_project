package com.example.test.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private val BASE_URL = "https://acnhapi.com/v1a/"

    private val TAG = "BugListFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val bugList = ArrayList<Bug>()


        return view
    }


}