package kr.ac.kumoh.s20190348.s23w04carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class CardViewModel {
    private val _cards = MutableLiveData<IntArray>(IntArray(5) { 0 })
    val cards: LiveData<IntArray>
        get() = _cards

    fun generate() {
        var num = 0
        var newCards = IntArray(5) { 0 }
        for (i in newCards.indices) {
            // 중복 검사
            do {
                num = Random.nextInt(1, 46)
            } while (newCards.contains(num))
            newCards[i] = num
        }
    }
}