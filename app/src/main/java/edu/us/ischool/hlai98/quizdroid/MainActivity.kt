package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val math_btn = findViewById<Button>(R.id.math_btn)
        val physics_btn = findViewById<Button>(R.id.physics_btn)
        val marvel_btn = findViewById<Button>(R.id.marvel_btn)

        math_btn.setOnClickListener{
            val intent = Intent(this@MainActivity, TopicOverview::class.java)
            val topic: String = this.toString()
            intent.putExtra("topic", "Math")
            startActivity(intent)
        }
        physics_btn.setOnClickListener{
            val intent = Intent(this@MainActivity, TopicOverview::class.java)
            val topic: String = this.toString()
            intent.putExtra("topic", "Physics")
            startActivity(intent)
        }
        marvel_btn.setOnClickListener{
            val intent = Intent(this@MainActivity, TopicOverview::class.java)
            val topic: String = this.toString()
            intent.putExtra("topic", "Marvel Super Heroes")
            startActivity(intent)
        }
    }
}
