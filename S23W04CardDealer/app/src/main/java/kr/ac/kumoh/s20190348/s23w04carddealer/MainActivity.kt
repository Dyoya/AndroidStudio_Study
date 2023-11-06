package kr.ac.kumoh.s20190348.s23w04carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.s20190348.s23w04carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)

        setContentView(main.root)

        model = ViewModelProvider(this)[CardViewModel::class.java]
        model.cards.observe(this, Observer {
            val res = IntArray(5)
            for(i in it.indices) {
                    res[i] = resources.getIdentifier(
                    getCardName(it[i]),
                    "drawable",
                    packageName
                )
            }

            main.card1.setImageResource(res[0])
            main.card2.setImageResource(res[1])
            main.card3.setImageResource(res[2])
            main.card4.setImageResource(res[3])
            main.card5.setImageResource(res[4])

            main.text.setText(model.genealogy())
        })

        main.btn.setOnClickListener {
            //model.test()
            model.shuffle()
            main.text.setText(model.genealogy())
        }
    }

    private fun getCardName(c: Int) : String {
        val shape = when (c % 4) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c / 4) {
            0 -> "ace"
            in 1..9 -> (c / 4 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }

        if(c == -1)
            return "c_red_joker"
        else
            return "c_${number}_of_${shape}"
    }
}