package kr.ac.kumoh.s20190348.a23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.s20190348.a23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{ // static
        const val KEY_NAME = "location"
    }
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)
        
        main.btnMountain.setOnClickListener {
            Toast.makeText(this, "산 눌림", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(KEY_NAME, "mountain")
            startActivity(intent)
        }

        main.btnSea.setOnClickListener {
            Toast.makeText(this, "바다 눌림", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(KEY_NAME, "sea")
            startActivity(intent)
        }
    }
}