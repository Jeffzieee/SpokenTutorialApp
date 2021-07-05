package com.example.spokentutorialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

//#Tony STARK is the BEST
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fossSpinner = findViewById<Spinner>(R.id.fossSpinner)
        val lanSpinner = findViewById<Spinner>(R.id.lanSpinner)
        val btnSearch = findViewById<Button>(R.id.btnSeach)


        var fossVSfossID = HashMap<String, Int>()
        fossVSfossID.put("Java", 10)
        fossVSfossID.put("Cpp", 57)
        fossVSfossID.put("Python", 26)
        fossVSfossID.put("RDBMS", 92)
        
        var lanVSlanID = HashMap<String, Int>()
        lanVSlanID.put("English", 22)
        lanVSlanID.put("Hindi", 6)
        lanVSlanID.put("Gujarati", 5)
        lanVSlanID.put("Tamil", 18)
        lanVSlanID.put("Marathi", 12)
        lanVSlanID.put("Kannada", 7)

        var fossVSlan = HashMap<String, ArrayList<String>>()
        var availableLan : ArrayList<String>

        availableLan = arrayListOf("English","Gujarati","Hindi","Kannada")
        fossVSlan.put("Java", availableLan)

        availableLan = arrayListOf("English","Gujarati","Hindi","Kannada","Marathi","Tamil")
        fossVSlan.put("Cpp",availableLan)

        availableLan = arrayListOf("English", "Hindi")
        fossVSlan.put("Python", availableLan)

        availableLan = arrayListOf("English")
        fossVSlan.put("RDBMS",availableLan)

        var selectedFoss = "none"
        var selectedLan = "none"

        var fossList = fossVSfossID.keys.toList()
        var lanList = lanVSlanID.keys.toMutableList()

        var fossAdapter = ArrayAdapter<String>(applicationContext,R.layout.spinner_layout,fossList)
        var lanAdapter = ArrayAdapter<String>(applicationContext,R.layout.spinner_layout,lanList)
        fossAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        lanAdapter.setDropDownViewResource(R.layout.spinner_dropdown)

        fossSpinner.adapter = fossAdapter
        lanSpinner.adapter = lanAdapter

        fossSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                lanList.clear()
                selectedFoss = fossList.get(position)
                lanList.addAll(ArrayList(fossVSlan.get(selectedFoss)))
                lanAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        lanSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 selectedLan = lanList.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        btnSearch.setOnClickListener{
           if(selectedFoss == "none" || selectedLan == "none"){
              Toast.makeText(applicationContext,"Please Select FOSS and Language",Toast.LENGTH_SHORT).show()
           }
           else{
               var fossID = fossVSfossID.get(selectedFoss)
               var lanID = lanVSlanID.get(selectedLan)

               var search = Intent(this, SecondActivity::class.java).apply {
                     putExtra("fossID",""+fossID)
                   putExtra("languageID",""+lanID)
               }
               startActivity(search)
           }
        }

    }
}