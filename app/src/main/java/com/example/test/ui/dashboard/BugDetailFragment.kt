package com.example.test.ui.dashboard
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.test.ACNHService
import com.example.test.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BugDetailFragment : Fragment() {

    private val BASE_URL = "https://acnhapi.com/v1a/"

    lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_bug_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
        viewModel.selected.observe(viewLifecycleOwner, Observer<Bug> {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val acnhAPI = retrofit.create(ACNHService::class.java)

            acnhAPI.getBug(viewModel.selected.value!!.id).enqueue(object : Callback<Bug> {


                override fun onFailure(call: Call<Bug>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }

                override fun onResponse(
                    call: Call<Bug>,
                    response: Response<Bug>
                ) {
                    Log.d(TAG, "onResponse: $response")

                    val body = response.body()
                    if(body == null){
                        Log.w(TAG, "Valid response was not received")
                        return
                    }

                    //set values of TextViews
                    view.findViewById<TextView>(R.id.bug_detail_name).text = body.name.name
                    view.findViewById<TextView>(R.id.bug_detail_rarity).text = body.availability.rarity
                    view.findViewById<TextView>(R.id.bug_detail_catchphrase).text = body.catchphrase

                    val monthsAvailable = view.findViewById<TextView>(R.id.bug_availability_months)
                    if(body.availability.isAllYear){
                        monthsAvailable.text = "Months: All year!"
                    }
                    else{
                        monthsAvailable.text = "Months: ${body.availability.months}"
                    }

                    val hoursAvailable = view.findViewById<TextView>(R.id.bug_availability_time)
                    if(body.availability.isAllDay){
                        hoursAvailable.text = "Hours: All day!"
                    }
                    else{
                        hoursAvailable.text = "Hours: ${body.availability.time}"
                    }

                    view.findViewById<TextView>(R.id.bug_detail_price).text = body.price.toString()
                    view.findViewById<TextView>(R.id.bug_detail_price_flick).text = body.price_flick.toString()
                    view.findViewById<TextView>(R.id.bug_detail_museum_phrase).text = body.museum_phrase

                    val context = view.context

                    Glide.with(context)
                        .load(body.icon_url)
                        .placeholder(R.drawable.bug)
                        .circleCrop()
                        .into(view.findViewById<ImageView>(R.id.bug_detail_image))
                }
            })
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}