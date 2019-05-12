package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val topics_list = listOf("Math", "Physics", "Marvel Super Heroes")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics_list)
        val list = findViewById<ListView>(R.id.topicList)
        list.adapter = adapter
        list.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            val topics = findViewById<ListView>(R.id.topicList).getItemAtPosition(position).toString()
            val intent: Intent = Intent(this@MainActivity, Overview::class.java)
            intent.putExtra(Overview.topics, "$topics")
            intent.putExtra(Overview.description, "")
            intent.putExtra(Overview.tid, (position + 1))
            startActivity(intent)
        }
    }
}
