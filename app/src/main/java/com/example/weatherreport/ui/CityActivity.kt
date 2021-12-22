package com.example.weatherreport.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherreport.R
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity : AppCompatActivity(R.layout.activity_city) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        card_delhi.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("country","Delhi")
            startActivity(intent)
        }
        card_newYork.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("country","New York")
            startActivity(intent)
        }
        card_tokyo.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("country","Tokyo")
            startActivity(intent)
        }
        card_london.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("country","London")
            startActivity(intent)
        }
        card_mexico.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("country","Mexico")
            startActivity(intent)
        }

    }
}