package kr.ac.kumoh.s20190348.a23w07intentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.s20190348.a23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        
        main.btnMountain.setOnClickListener {
            Toast.makeText(this, "산 눌림", Toast.LENGTH_SHORT).show()
        }

        main.btnSea.setOnClickListener {
            Toast.makeText(this, "바다 눌림", Toast.LENGTH_SHORT).show()
        }
    }
}