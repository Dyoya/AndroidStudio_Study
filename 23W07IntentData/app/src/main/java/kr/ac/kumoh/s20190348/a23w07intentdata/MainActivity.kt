package kr.ac.kumoh.s20190348.a23w07intentdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20190348.a23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object{ // static
        const val KEY_NAME = "location"
        const val MOUNTAIN = "mountain"
        const val SEA = "sea"
    }

    private lateinit var main: ActivityMainBinding

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult

        val result = it.data?.getIntExtra(ImageActivity.IMAGE_RESULT, ImageActivity.NONE)
        val str = when (result) {
            ImageActivity.LIKE -> "좋아요"
            ImageActivity.DISLIKE -> "싫어요"
            else -> ""
        }
        when (it.data?.getStringExtra(ImageActivity.IMAGE_NAME)) {
            MOUNTAIN -> main.btnMountain.text = "산 ($str)"
            SEA -> main.btnSea.text = "바다 ($str)"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)
        
        main.btnMountain.setOnClickListener(this)
        main.btnSea.setOnClickListener(this)

        Log.i("Lifecycle!!!", "onCreate")
    }

    override fun onClick(v: View?) {
        val intent = Intent(
            this,
            //ImageActivity::class.java
            AnotherActivity::class.java
        )
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
        //startActivity(intent)
        startForResult.launch(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle!!!", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle!!!", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle!!!", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle!!!", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle!!!", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle!!!", "onDestroy")
    }
}