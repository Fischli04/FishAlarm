package com.example.fishalarm

import android.database.Cursor
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.sql.Time


class MainActivity : AppCompatActivity() {
    private var times: Array<String> = arrayOf("06:00", "06:30", "06:45", "07:00", "08:00")
    private var ringtones: Map<String, String>? = null
    private lateinit var currentPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ringtones = getNotifications()

        val select: Spinner = findViewById(R.id.RingtoneSelect)
        RingtoneManager.ACTION_RINGTONE_PICKER

        val ringtones = getNotifications()

        val array: Array<String> = ringtones.keys.toTypedArray()

        val a = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)
        select.adapter = a

        val button: Button = findViewById(R.id.play)

        button.setOnClickListener {
            var isEnabled: Switch = findViewById(R.id.alarmActive)
            if (isEnabled.isChecked){
                val uri = ringtones[select.selectedItem.toString()]

                currentPlayer = MediaPlayer.create(this, Uri.parse(uri))

                currentPlayer.isLooping = true
                currentPlayer.start()
                button.isEnabled = false
                val button: Button = findViewById(R.id.pause)
                button.isEnabled = true
            }
        }

        val offButton: Button = findViewById(R.id.pause)
        offButton.isEnabled = false

        offButton.setOnClickListener {
            currentPlayer.stop()
            button.isEnabled = true
            val button: Button = findViewById(R.id.pause)
            button.isEnabled = false
        }

            //ArrayAdapter.createFromResource(
            //    this,
            //    0,
            //    array
            //).also { adapter ->
            //    // Specify the layout to use when the list of choices appears
            //    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //    // Apply the adapter to the spinner
            //    Select.adapter = array
            //}

        val bar: SeekBar = findViewById(R.id.AlarmTimeBar)
        bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val label: TextView = findViewById(R.id.AlarmTimeLabel)
                val bar: SeekBar = findViewById(R.id.AlarmTimeBar)
                label.text = times[bar.progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }


    private fun getNotifications(): Map<String, String> {
        val manager = RingtoneManager(this)
        manager.setType(RingtoneManager.TYPE_RINGTONE)
        val cursor: Cursor = manager.cursor
        val list: MutableMap<String, String> = HashMap()
        while (cursor.moveToNext()) {
            val notificationTitle: String = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val notificationUri: String = cursor.getString(RingtoneManager.URI_COLUMN_INDEX)
                .toString() + "/" + cursor.getString(RingtoneManager.ID_COLUMN_INDEX)
            list[notificationTitle] = notificationUri
        }
        return list
    }
}
