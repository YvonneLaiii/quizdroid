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

class urlService : IntentService("urlService") {


    private var ok = true
    private var myHandler = Handler()
    override fun onHandleIntent(p0: Intent?) {
        var time: Int
        var url: String
        p0?.extras.apply {
            url = this!!.getString("url")
            time = this!!.getInt("time")
        }
        while (ok) {
            myHandler.post{
                Toast.makeText(this@urlService, "Downloading from $url every $time minutes.", Toast.LENGTH_LONG)
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
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = StringRequest (
            Request.Method.GET, url,
            Response.Listener<String> { response -> Toast.makeText(this,"Downloading", Toast.LENGTH_LONG).show()
                try {
                    val fos = this.openFileOutput("questions.json", Context.MODE_PRIVATE)
                    if (response != null) {
                        fos.write(response.toByteArray())
                    }
                    fos.flush()
                    fos.close()
                    Toast.makeText(this,"Saved to ${this.filesDir}/questions.json", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    e.stackTrace
                }
            },
            Response.ErrorListener { error -> Toast.makeText(this, "ERROR:".format(error.toString()), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
    override fun onDestroy() {
        Toast.makeText(this@urlService, "Stop download", Toast.LENGTH_LONG).show()
        ok = false
        super.onDestroy()
    }
}