package com.example.myapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapiapplication.network.ApiClient
import com.example.myapiapplication.network.CharacterResponse
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var RvData : RecyclerView
    lateinit var RvDataAdopter: RvListAdopter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RvData = findViewById(R.id.rv_item_list)
        RvData.setHasFixedSize(true)
        RvData.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val client = ApiClient.apiService.fetchCharacter("1")
        client.enqueue(object :retrofit2.Callback<CharacterResponse>{
            override fun onResponse(
                call: retrofit2.Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                try {
                    val result = response.body()?.result
                    result?.let {
                        RvDataAdopter = RvListAdopter(result)
                        RvData.adapter = RvDataAdopter
                    }
                }catch (e: Exception){
                    Log.e("exception", "onResponse: $e")
                }
            }

            override fun onFailure(call: retrofit2.Call<CharacterResponse>, t: Throwable) {
                Log.e("character_err", "onFailure: " +t.message)
            }

        })

    }
}