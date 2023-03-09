package com.example.gameofthrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.APIServices.ApiInterface
import com.example.data.DataModelItem
import com.example.gameofthrones.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* API Used --> https://thronesapi.com/api/v2/Characters
* */

const val API: String = "https://thronesapi.com/api/v2/"

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        recyclerView = binding.rv

        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API).build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        adapter = DataAdapter()


        retrofitData.enqueue(object : Callback<List<DataModelItem>?> {
            override fun onResponse(
                call: Call<List<DataModelItem>?>,
                response: Response<List<DataModelItem>?>
            ) {
                if(response.isSuccessful){
                    adapter.setData(response.body() as ArrayList<DataModelItem>?)
                    recyclerView.adapter = adapter
                }else{
                    Log.d("MainActivity", "Response Unsuccessful")
                }

            }

            override fun onFailure(call: Call<List<DataModelItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })
    }
}


