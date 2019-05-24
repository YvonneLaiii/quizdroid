package edu.us.ischool.hlai98.quizdroid

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val INTERNET_REQUEST_CODE = 1
    private val JSON_URL = "http://tednewardsandbox.site44.com/questions.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPermission()

        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetworkInfo == null) {
            Toast.makeText(this, "Internet Connection Failed.", Toast.LENGTH_LONG)
        }
        if (Settings.Global.AIRPLANE_MODE_ON == "airplane_mode_on") {
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle("Turn off Airplane Mode?")
                setMessage("Would you like to go to Settings and turn off Airplane Mode?")
                setNegativeButton("No") { dialog, which ->  Log.i("airplane", "No")}
                setPositiveButton("Yes") { dialog, which -> startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0) }
            }
            builder.create().show()
        }
        val url = findViewById<EditText>(R.id.editText_url)
        val mode = findViewById<EditText>(R.id.editText_mode)

        val preference = getPreferences(Context.MODE_PRIVATE)
        val saveBtn = findViewById<Button>(R.id.btn_save)
        val stopBtn = findViewById<Button>(R.id.btn_stop)


        saveBtn.setOnClickListener {

            var inputUrl = url.text.toString()
            var inputMode = mode.text.toString()
            if (inputUrl.isEmpty() || inputMode.isEmpty()) {
                if (inputUrl.isEmpty() && inputMode.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Please Enter url and mode", Toast.LENGTH_LONG).show()
                }else if (inputUrl.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Please Enter url", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Please Enter a mode(minute)", Toast.LENGTH_LONG).show()
                }
            }else {

                preference.edit().putString("url", inputUrl).apply()
                preference.edit().putInt("min", inputMode.toInt()).apply()
                btn_save.isEnabled = false
                btn_stop.isEnabled = true
                val intent = Intent(this@MainActivity, urlService::class.java)
                intent.putExtra("url",preference.getString("url", JSON_URL))
                intent.putExtra("time",preference.getInt("min", 1))
                Log.i("123123", "1")
                startService(intent)
            }
        }
        stopBtn.setOnClickListener{
            stopService(Intent(this@MainActivity, urlService::class.java))
        }


        val singleton = QuizApp.getSingleton()
        //Log.i("trytrytry", singleton.repo.getTopics().shortDescription)
        val topics_list = singleton.repo.getTopics().map { "${it.title} (${it.shortDescription})" }
        //val topics_list = listOf("Math", "Physics", "Marvel Super Heroes")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics_list)
        val list = findViewById<ListView>(R.id.topicList)
        val all = singleton.repo.getTopics()
        val title = arrayListOf<String>()
        val shortD = arrayListOf<String>()
        val longD = arrayListOf<String>()
        val questions = arrayListOf<List<Quiz>>()
        for (i in all) {

            title.add(i.title)
            shortD.add(i.shortDescription)
            longD.add(i.longDescription)
            questions.add(i.getAll())
        }

        list.adapter = adapter
        list.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            val temp = list.getItemAtPosition(position).toString().split(" (")[0].trim()
            Log.i("user", temp)
            val titleChosen = title[position]
            val shortChosen = shortD[position]
            val longChosen = longD[position]
            val questionChosen = questions[position]
            startIntent(position,titleChosen, shortChosen,longChosen,questionChosen)
        }
    }

    private fun startIntent(position: Int,title:String, short:String, long:String,question:List<Quiz>) {
        var text = arrayListOf<String>()
        var options = arrayListOf<Array<String>>()
        var answer = arrayListOf<Int>()

        for (i in question) {
            text.add(i.question)
            options.add(i.answers)
            answer.add(i.correct)
        }

        val intent = Intent(this@MainActivity, QuizActivity::class.java)
        intent.putExtra(QuizActivity.TITLE, title)
        intent.putExtra(QuizActivity.LONGDESCRIPTION, long)
        intent.putExtra(QuizActivity.QUESTIONTEXT, text)
        intent.putExtra(QuizActivity.QUESTIONOPTIONS, options)
        intent.putExtra(QuizActivity.QUESTIONANSWER, answer)
        intent.putExtra(QuizActivity.TID,(position + 1))
        startActivity(intent)
    }

    private fun getPermission() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.INTERNET)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            request()
        } else {
            Toast.makeText(this, "Internet Permission ok!", Toast.LENGTH_LONG).show()
        }
    }
    private fun request() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), INTERNET_REQUEST_CODE)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            INTERNET_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("main", "Permission denied")
                } else {
                    Log.i("main", "Permission granted")
                }
            }
        }
    }
}

