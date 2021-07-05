package com.example.spokentutorialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_second.*
import org.json.JSONArray
import org.json.JSONObject

class SecondActivity : AppCompatActivity() {
    var finalResultsArrayList = ArrayList<VideoItem>()
    lateinit var recycleAdapter: RecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        var foss = intent.getStringExtra("fossID")
        var language = intent.getStringExtra("languageID")
        idView.text = "Foss ID : " + foss + "\nLanguage ID : " + language

        var myRecyclerView = findViewById<RecyclerView>(R.id.vidView)

        recycleAdapter = RecycleAdapter(applicationContext, finalResultsArrayList)

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.adapter = recycleAdapter



        val queue = Volley.newRequestQueue(this)
        val url = "https://spoken-tutorial.org/api/get_tutorials/" + foss+ "/" + language


        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

            Toast.makeText(applicationContext,"Received server response!", Toast.LENGTH_SHORT).show()

            extractJsonData(response)

        }, Response.ErrorListener {
            Toast.makeText(applicationContext, "Unable to connect to the server", Toast.LENGTH_SHORT).show()
        })
        Toast.makeText(applicationContext,
                "Contacting Server...", Toast.LENGTH_SHORT).show()
        queue.add(stringRequest)

    }

    private fun extractJsonData(jsonResponse: String) {
        var videosDataArray = JSONArray(jsonResponse)
        var singleVideoJsonObject: JSONObject
        var singleVideoItem: VideoItem
        var i = 0
        var size = videosDataArray.length()

        while (i < size) {
            singleVideoJsonObject = videosDataArray.getJSONObject(i)
            singleVideoItem =
                    VideoItem(singleVideoJsonObject.getString("video_id"),
                            singleVideoJsonObject.getString("tutorial_name"),
                            singleVideoJsonObject.getString("tutorial_level"))
            finalResultsArrayList.add(singleVideoItem)
            i++
        }
        println("The parsed videoItems are :")
        finalResultsArrayList.forEach { println(it) }
        recycleAdapter.notifyDataSetChanged()

    }
}