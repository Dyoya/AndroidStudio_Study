package kr.ac.kumoh.s20190348.s23w04carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {
    private val _cards = MutableLiveData<IntArray>(IntArray(5) { -1 })
    val cards: LiveData<IntArray>
        get() = _cards

    fun shuffle() {
        var num = 0
        var newCards = IntArray(5) { -1 }
        for (i in newCards.indices) {
            // 중복 검사
            do {
                num = Random.nextInt(52)
            } while (newCards.contains(num))
            newCards[i] = num
        }

        newCards.sort()

        _cards.value = newCards
    }
}