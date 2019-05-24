package edu.us.ischool.hlai98.quizdroid


import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.lang.Exception
import android.R.attr.path
import java.io.FileOutputStream


class urlService : IntentService("urlService") {


    private var continueDownload = true
    private var myHandler = Handler()
    override fun onHandleIntent(p0: Intent?) {
        var time: Int
        var url: String
        p0?.extras.apply {
            url = this!!.getString("url")
            time = this!!.getInt("time")
            Log.i("123123", "2")
        }
        while (continueDownload) {
            Log.i("123123", "3")
            myHandler.post{
                Log.i("123123", "4")
                Toast.makeText(this@urlService, "Downloading from $url every $time minutes.", Toast.LENGTH_LONG).show()
                download(url)

            }
            try {
                Thread.sleep((time*60000).toLong())
            } catch (e: InterruptedException){
                Thread.currentThread().interrupt()
            }
        }
    }
    private fun download(url:String) {
        Log.i("123123", "5")
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = StringRequest (
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Downloading", Toast.LENGTH_LONG).show()
                //Log.i("123123", "6")
                val fos = FileOutputStream("/")
                fos.write(response.toByteArray())
                fos.close()

                Toast.makeText(this,"Success! Saved to ${this.filesDir}/questions.json", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error -> Toast.makeText(this, "Download Failed".format(error.toString()), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
    override fun onDestroy() {
        Toast.makeText(this@urlService, "Stop download", Toast.LENGTH_LONG).show()
        continueDownload = false
        super.onDestroy()
    }
}