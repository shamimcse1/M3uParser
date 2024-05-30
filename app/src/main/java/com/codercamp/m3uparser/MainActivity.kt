package com.codercamp.m3uparser

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


var `is`: InputStream? = null
val parser = M3UParser()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loader()
    }


    private fun loader() {
        `is`= assets.open("data.db") // if u r trying to open file from asstes InputStream is = getassets.open(); InputStream
        val playlist = parser.parseFile(`is`)
        Log.d("Data",playlist.toString())
        val filename = "DataList.xlsx"

        val directoryDownload = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val logDir = File(directoryDownload, "bpReader") //Creates a new folder in DOWNLOAD directory
        logDir.mkdirs()
        val file = File(logDir, filename)

        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file, true)
           // outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            var i = 0
            if(playlist.playlistItems != null){
                while (i < playlist.playlistItems!!.size) {

                    if(playlist.playlistItems!![i].itemName != null){
                        outputStream.write((playlist.playlistItems!![i].itemName + ",").toByteArray())
                        outputStream.write((playlist.playlistItems!![i + 1].itemUrl + ",").toByteArray())
                        outputStream.write((playlist.playlistItems!![i + 2].itemIcon + "\n").toByteArray())
                        i += 3
                    }

                }
                outputStream.close()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}