package com.example.debuggingchallenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView
    lateinit var fabButton: FloatingActionButton
    lateinit var alertDialogSubmitBtn: Button
    private var arrayListOfCountriesAndCapitals = arrayListOf(
        arrayListOf("Saudi Arabia", "Riyadh"),
        arrayListOf("Nigeria", "Abuja"),
        arrayListOf("Rwanda", "Kigali"),
        arrayListOf("USA", "Washington"),
        arrayListOf("China", "Beijing"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabButton = findViewById(R.id.fabBtn)
        setupRecyclerView()


        fabButton.setOnClickListener {
            val singleUserEntryList = arrayListOf<String>()

            //AlertDialog
            val (dialogView, alertDialog) = setupAlertDialog()

            //Initialize edit texts
            val countryET = dialogView.findViewById<EditText>(R.id.countryEt)
            val capitalET = dialogView.findViewById<EditText>(R.id.capitalEt)

            //Store user's input text to variables
            val countryText = countryET.text.toString()
            val capitalText = capitalET.text.toString()

            Log.d("TEST", "'$countryText' '$capitalText' ${countryText.isEmpty()} ${countryText.isBlank()}")
            if(countryText.isBlank() || capitalText.isBlank()){

            }

            alertDialogSubmitBtn.setOnClickListener {
                //Add both texts to list
                singleUserEntryList.add(countryText)
                singleUserEntryList.add(capitalText)

                //Add single entry list to Global list
                arrayListOfCountriesAndCapitals.add(singleUserEntryList)

                alertDialog.dismiss()
            }

        }
    }

    private fun setupAlertDialog(): Pair<View, AlertDialog> {
        //Inflate layout for Alert Dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_layout, null)

        val builder =
            AlertDialog.Builder(this).setView(dialogView).setTitle("Country/Capital Form")
        val alertDialog = builder.show()
        alertDialogSubmitBtn = dialogView.findViewById(R.id.submitBtn)
        return Pair(dialogView, alertDialog)
    }

    private fun setupRecyclerView() {
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter =
            ListSelectionRecyclerViewAdapter(arrayListOfCountriesAndCapitals)
    }
}