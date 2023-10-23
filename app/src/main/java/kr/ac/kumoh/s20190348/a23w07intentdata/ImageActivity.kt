package kr.ac.kumoh.s20190348.a23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.google.android.material.color.utilities.DislikeAnalyzer
import kr.ac.kumoh.s20190348.a23w07intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity(),OnClickListener {
    companion object {
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"

        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }
    private lateinit var main: ActivityImageBinding
    private lateinit var location: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityImageBinding.inflate(layoutInflater)
        setContentView(main.root)

        //Log.i("ImageActivity!!", intent.getStringExtra("location") ?: "없음")
        location = intent.getStringExtra(MainActivity.KEY_NAME) ?: "none"
        val res = when (location) {
            MainActivity.MOUNTAIN -> R.drawable.mountain
            MainActivity.SEA -> R.drawable.sea
            else -> R.drawable.ic_launcher_foreground
        }
        main.image.setImageResource(res)
    }

    override fun onClick(p0: View?) {
        val intent = Intent()
        val value = when(p0?.id) {
            main.btnLike.id -> LIKE
            main.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(IMAGE_NAME, location)
        intent.putExtra(IMAGE_RESULT, value)
        setResult(RESULT_OK, intent)
        finish()
    }
}