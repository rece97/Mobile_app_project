package com.example.test.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ACNHService
import com.example.test.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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

        val adapter = BugRowItemAdapter(bugList)

        val recyclerView = view.findViewById<RecyclerView>(R.id.bug_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val acnhAPI = retrofit.create(ACNHService::class.java)

        acnhAPI.getAllBugs().enqueue(object : Callback<ArrayList<Bug>> {


            override fun onFailure(call: Call<ArrayList<Bug>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

            override fun onResponse(
                call: Call<ArrayList<Bug>>,
                response: Response<ArrayList<Bug>>
            ) {
                Log.d(TAG, "onResponse: $response")

                val body = response.body()
                if(body == null){
                    Log.w(TAG, "Valid response was not received")
                    return
                }

                bugList.addAll(body)
                adapter.notifyDataSetChanged()
            }
            })

        return view
    }


}