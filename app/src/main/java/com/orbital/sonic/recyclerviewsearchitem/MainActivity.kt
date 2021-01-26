package com.orbital.sonic.recyclerviewsearchitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

   private lateinit var mRecyclerView: RecyclerView
   private lateinit var mCountryList: ArrayList<CountryItem>
   private lateinit var mLayoutManager: RecyclerView.LayoutManager
   private lateinit var mAdapter: CountryAdapter
   private lateinit var countryName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createCountryList()
        buildRecyclerView()

        val editText = findViewById<EditText>(R.id.edittext)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                textFilter(s.toString())
            }
        })
    }

    private fun buildRecyclerView() {
        mRecyclerView= findViewById(R.id.recyclerView)
        mLayoutManager= LinearLayoutManager(this)
        mAdapter= CountryAdapter(mCountryList)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener(object : CountryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,  mAdapter.getCountryItem(position).countryName, Toast.LENGTH_SHORT).show()

            }

            override fun onDeleteClick(position: Int) {
                mAdapter.removeCountryItem(position)
            }
        })
    }

    private fun createCountryList() {
        countryName = resources.getStringArray(R.array.country_name_list)
        mCountryList = ArrayList()

        for (index in this.countryName){
            mCountryList.add(CountryItem( index))
        }

    }


    private fun textFilter(text: String) {
        val filteredList = ArrayList<CountryItem>()
        for (item in mCountryList) {
            if (item.countryName.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        mAdapter.filterList(filteredList)
    }
}