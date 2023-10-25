package kr.ac.kumoh.s20190348.s23w04carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {
    private val _cards = MutableLiveData<IntArray>(IntArray(5) { -1 })
    val cards: LiveData<IntArray>
        get() = _cards

    private var cardsArray = intArrayOf()

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
        cardsArray = newCards
    }

    fun test()
    {
        var newCards = IntArray(5) { -1 }

        newCards[0] = 1
        newCards[1] = 45
        newCards[2] = 41
        newCards[3] = 37
        newCards[4] = 50

        newCards.sort()

        _cards.value = newCards
        cardsArray = newCards
    }

    fun genealogy(): String {
        val numberCounts = IntArray(13) { 0 }
        val suitCounts = IntArray(4) { 0 }

        // 카드의 숫자, 무늬를 셈
        for (card in cardsArray) {
            val number = card / 4
            val suit = card % 4
            numberCounts[number]++
            suitCounts[suit]++
        }

        if (suitCounts.max() == 5) {
            if (isStraight(numberCounts)) {
                if (numberCounts[0] > 0 && numberCounts[12] > 0) {
                    return "로열 스트레이트 플러시"
                }
                else if (numberCounts[0] > 0 && numberCounts[1] > 0) {
                    return "백 스트레이트 플러시"
                }
            }

            if (isStraight(numberCounts)) {
                return "스트레이트 플러시"
            }
        }

        if (numberCounts.contains(4)) {
            // 포 카드 판별
            for (i in 0 until numberCounts.size) {
                if (numberCounts[i] == 4) {
                    return "${getCardName(i)} 포카드"
                }
            }
        }

        if (numberCounts.contains(3) && numberCounts.contains(2)) {
            return "풀 하우스"
        }

        if (suitCounts.max() == 5) {
            return "플러시"
        }

        if (isStraight(numberCounts)) {
            if (numberCounts[0] > 0 && numberCounts[12] > 0) {
                return "마운틴"
            }
            else if (numberCounts[0] > 0 && numberCounts[1] > 0) {
                return "백 스트레이트"
            }
        }

        if (isStraight(numberCounts)) {
            return "스트레이트"
        }

        if (numberCounts.contains(3)) {
            return "${getCardName(numberCounts.indexOf(3))} 트리플"
        }

        val pairs = numberCounts.withIndex().filter { it.value == 2 }
        if (pairs.size == 2) {
            val pair0 = getCardName(pairs[0].index)
            val pair1 = getCardName(pairs[1].index)
            return "${pair0}, ${pair1} 투페어"
        }

        if (numberCounts.contains(2)) {
            return "${getCardName(numberCounts.indexOf(2))} 원 페어"
        }

        if (numberCounts[0] > 0) {
            return "A 탑 카드"
        }
        return "${getCardName(numberCounts.lastIndexOf(1))} 탑 카드"
    }

    fun isStraight(numbers: IntArray): Boolean {
        for (i in 0 until 9) {
            if (numbers[i] > 0 && numbers[i + 1] > 0 && numbers[i + 2] > 0 && numbers[i + 3] > 0 && numbers[i + 4] > 0) {
                return true
            }
        }

        if (numbers[0] > 0 && numbers[12] > 0 && numbers[11] > 0 && numbers[10] > 0 && numbers[9] > 0) {
            return true
        }

        return false
    }

    fun getCardName(number: Int): String {
        return when (number) {
            0 -> "A"
            10 -> "J"
            11 -> "Q"
            12 -> "K"
            else -> (number + 1).toString()
        }
    }

}