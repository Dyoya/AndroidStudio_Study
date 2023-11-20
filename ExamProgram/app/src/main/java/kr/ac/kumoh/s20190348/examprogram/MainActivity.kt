package kr.ac.kumoh.s20190348.examprogram

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20190348.examprogram.databinding.ActivityExamBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val NEUVI_KEY = "KeyName"
        const val NEUVI_IMAGE = 100
        const val NEUVI_BUTTON = 200
        const val NEUVI_NONE = 0
    }
    private lateinit var view: ActivityExamBinding
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode != RESULT_OK)
            return@registerForActivityResult
        val pressed = it.data?.getBooleanExtra(
            SecondActivity.BUTTON_PRESSED, false) ?: false
        if (pressed)
            view.btnFourth.text.toString().plus("(눌림)")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityExamBinding.inflate(layoutInflater)
        setContentView(view.root)
        view.btnThird.setOnClickListener(this)
        view.btnFourth.setOnClickListener(this)
    }
    override fun onClick(v : View?) {
        val second = Intent(this, SecondActivity::class.java)
        when (v?.id) {
            view.btnThird.id -> second.putExtra(NEUVI_KEY, NEUVI_IMAGE)
            view.btnFourth.id -> second.putExtra(NEUVI_KEY, NEUVI_BUTTON)
        }
        startForResult.launch(second)
    }
}