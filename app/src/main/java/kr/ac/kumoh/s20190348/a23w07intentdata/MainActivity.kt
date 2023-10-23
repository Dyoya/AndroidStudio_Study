package kr.ac.kumoh.s20190348.a23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kr.ac.kumoh.s20190348.a23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object{ // static
        const val KEY_NAME = "location"
        const val MOUNTAIN = "mountain"
        const val SEA = "sea"
    }
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)
        
        main.btnMountain.setOnClickListener(this)
        main.btnSea.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when (v?.id) {
            main.btnMountain.id -> {
                Toast.makeText(this, "산 눌림", Toast.LENGTH_SHORT).show()
                MOUNTAIN
            }
            main.btnSea.id -> {
                Toast.makeText(this, "바다 눌림", Toast.LENGTH_SHORT).show()
                SEA
            }
            else -> return
        }
        intent.putExtra(KEY_NAME, value)
        startActivity(intent)
    }
}