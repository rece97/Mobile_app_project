package com.example.test.ui.dashboard

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ACNHService
import com.example.test.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuglistFragment : Fragment() {

    private val BASE_URL = "https://acnhapi.com/v1a/"

    private  val TAG = "BuglistFragment"
    lateinit var viewModel: DashboardViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_buglist, container, false)


        Log.d(TAG, "onCreateView: BuglistFragment")
        val bugList = ArrayList<Bug>()

        val adapter = BugRowItemAdapter(bugList)

        val recyclerView = view.findViewById<RecyclerView>(R.id.bug_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter.onItemClick = { bug ->
            viewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
            viewModel.selected.value = bug
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                // We are in portrait orientation
                // Load Detail fragment, i.e., replace listview fragment with detail fragment
                Log.d(TAG, "onCreateView: ORIENTATION_PORTRAIT")
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.bugListContainer, BugDetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
            else {

                Log.d(TAG, "onCreateView: ORIENTATION_LANDSCAPE")
                // We are in landscape orientation
                // Load Detail fragment, i.e., replace the current detail fragment
                // with detail fragment containing the selected item's details
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.BugDetailContainer, BugDetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val acnhAPI = retrofit.create(ACNHService::class.java)

        acnhAPI.getAllBugs().enqueue(object : Callback<List<Bug>> {


            override fun onFailure(call: Call<List<Bug>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

            override fun onResponse(
                call: Call<List<Bug>>,
                response: Response<List<Bug>>
            ) {
                Log.d(TAG, "onResponse: $response")

                val body = response.body()
                if(body == null){
                    Log.w(TAG, "Valid response was not received")
                    return
                }

                val bodyList: List<Bug> = body.sortedWith(compareBy { it.name.name.lowercase() })

                bugList.addAll(bodyList)
                adapter.notifyDataSetChanged()
            }
        })
        return view
    }

}