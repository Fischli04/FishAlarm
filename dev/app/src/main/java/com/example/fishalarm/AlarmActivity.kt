package com.example.fishalarm

import android.content.Intent
import android.database.Cursor
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AlarmActivity : AppCompatActivity() {
    private lateinit var currentPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        //var nfcReader: NfcAdapter? = NfcAdapter.getDefaultAdapter(this)?.let { it }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)


        currentPlayer = MediaPlayer.create(this, Uri.parse(intent.getStringExtra("uri")))

        currentPlayer.isLooping = true
        currentPlayer.start()

        val offButton: Button = findViewById(R.id.stopButton )


        offButton.setOnClickListener {
            if( this::currentPlayer.isInitialized ){
                currentPlayer.stop()
            }
            finish();
        }
    }
}