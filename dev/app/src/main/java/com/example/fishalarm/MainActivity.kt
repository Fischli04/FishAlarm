package com.example.fishalarm

import android.content.Intent
import android.database.Cursor
import android.media.RingtoneManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private var times: Array<String> = arrayOf("06:00", "06:30", "06:45", "07:00", "08:00")
    private lateinit var ringtones: Map<String, String>
    private lateinit var selectedRingtone: String
    private var calendar = Calendar.getInstance();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ringtones = getNotifications()

        val select: Spinner = findViewById(R.id.RingtoneSelect)

        select.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedRingtone = ringtones[select.selectedItem.toString()].toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }



        RingtoneManager.ACTION_RINGTONE_PICKER

        val array: Array<String> = ringtones.keys.toTypedArray()

        val a = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)
        select.adapter = a




        val calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        if(calendar.time.time < System.currentTimeMillis()){
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1)
        }

        val timer = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                ring()
            }
        }
        // repeat every hour
        timer.schedule(task, calendar.timeInMillis - System.currentTimeMillis())






        val timePicker: TimePicker = findViewById(R.id.timePicker1);

        timePicker.setOnTimeChangedListener { timePicker: TimePicker, i: Int, i1: Int ->
            val task: TimerTask = object : TimerTask() {
                override fun run() {
                    ring()
                    timer.cancel()
                }
            }

            setCalendar(calendar, i, i1)

            timer.schedule(task, calendar.timeInMillis - System.currentTimeMillis())
        }

    }

    private fun ring() {
        var isEnabled: Switch = findViewById(R.id.alarmActive)
        if(isEnabled.isChecked){
            val intent = Intent(this@MainActivity, AlarmActivity::class.java)
            intent.putExtra("uri", selectedRingtone)
            startActivity(intent)
        }

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

    private fun setCalendar(calendar: Calendar, hours: Int, minutes: Int){
        calendar.timeInMillis = System.currentTimeMillis();
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        calendar.set(Calendar.SECOND, 0)
        if(calendar.time.time < System.currentTimeMillis()){
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1)
        }
    }
}
