package kr.ac.kumoh.s20190348.s23w04carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.kumoh.s20190348.s23w04carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        val card = Random.nextInt(52)
        Log.i("Card!!", "$card : ${getCardName(card)}")

        val res = resources.getIdentifier(
            getCardName(card),
            "drawable",
            packageName
        )

        // TODO : 카드 랜덤으로 출력되도록
        main.card1.setImageResource(res)
        main.card2.setImageResource(R.drawable.c_ace_of_hearts)
        main.card3.setImageResource(R.drawable.c_ace_of_hearts)
        main.card4.setImageResource(R.drawable.c_ace_of_hearts)
        main.card5.setImageResource(R.drawable.c_ace_of_hearts)

    }

    private fun getCardName(c: Int) : String {
        val shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }

        return "c_${number}_of_${shape}"
    }
}