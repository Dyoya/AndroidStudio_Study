package kr.ac.kumoh.s20190348.s23w04carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {
    private val _cards = MutableLiveData<IntArray>(IntArray(5) { -1 })
    val cards: LiveData<IntArray>
        get() = _cards

    private var cardsArray = IntArray(5) {-1}

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
        // 초기 값이라면 안내 메세지 반환
        if(cardsArray[0] == -1) {
            return "아래 버튼을 눌러주세요"
        }

        // 숫자 및 문양 배열 선언
        val numberCounts = IntArray(13) { 0 }
        val suitCounts = IntArray(4) { 0 }

        // 카드의 숫자, 무늬 개수를 셈
        for (card in cardsArray) {
            val number = card / 4
            val suit = card % 4
            numberCounts[number]++
            suitCounts[suit]++
        }

        // 스트레이트 플러시 판별
        if (suitCounts.max() == 5) { // 한 문양이 5개(모두 같은 문양)
            if (isStraight(numberCounts)) { // 스트레이트라면
                if (numberCounts[0] > 0 && numberCounts[12] > 0) { // A와 K가 포함되어 있으면
                    return "로열 스트레이트 플러시"
                }
                else if (numberCounts[0] > 0 && numberCounts[1] > 0) { // A와 2가 포함되어 있으면
                    return "백 스트레이트 플러시"
                }
            }

            if (isStraight(numberCounts)) { // 위에 포함되지 않는 스트레이트라면
                return "스트레이트 플러시"
            }
        }

        // 포카드 판별
        if (numberCounts.contains(4)) { // 어떤 숫자가 4개가 있으면
            for (i in 0 until numberCounts.size) { // A부터 2, 3, ..., K까지 반복
                if (numberCounts[i] == 4) { // 4개인 숫자를 찾음
                    return "${getCardName(i)} 포카드"
                }
            }
        }

        // 풀하우스 판별
        // 어떤 숫자가 3개, 또 다른 어떤 숫자가 2개가 있으면
        if (numberCounts.contains(3) && numberCounts.contains(2)) {
            return "풀 하우스"
        }

        // 플러시 판별
        if (suitCounts.max() == 5) { // 어떤 문양이 5개 있으면
            return "플러시"
        }

        // 스트레이트 판별
        if (isStraight(numberCounts)) { // 스트레이트라면
            if (numberCounts[0] > 0 && numberCounts[12] > 0) { // A와 K가 포함되어 있으면
                return "마운틴"
            }
            else if (numberCounts[0] > 0 && numberCounts[1] > 0) { // A와 2가 포함되어 있으면
                return "백 스트레이트"
            }
        }

        if (isStraight(numberCounts)) { // 스트레이트라면
            return "스트레이트"
        }

        //트리플 판별
        if (numberCounts.contains(3)) { // 어떤 숫자가 3개 있으면
            return "${getCardName(numberCounts.indexOf(3))} 트리플"
        }

        // 투페어 판별
        val pairs = numberCounts.withIndex().filter {
            it.value == 2 // 개수가 2개인 숫자를 모두 찾아 배열로 저장
        }
        if (pairs.size == 2) { // 개수가 2개인 숫자가 2가지라면
            val pair0 = getCardName(pairs[0].index) // 첫 번째 숫자
            val pair1 = getCardName(pairs[1].index) // 두 번째 숫자
            return "${pair0}, ${pair1} 투페어"
        }

        // 원페어 판별
        if (numberCounts.contains(2)) { // 어떤 숫자가 2개 있으면
            return "${getCardName(numberCounts.indexOf(2))} 원 페어"
        }

        // 하이카드 판별
        if (numberCounts[0] > 0) { // A가 있으면
            return "A 하이 카드"
        }

        return "${getCardName(numberCounts.lastIndexOf(1))} 하이 카드" // 수가 가장 높은 카드
    }

    // 스트레이트 판별 함수
    // 연속된 숫자 5개가 모두 1개 이상 있을 경우 판별
    fun isStraight(numbers: IntArray): Boolean {
        for (i in 0 until 9) {
            if (numbers[i] > 0 && numbers[i + 1] > 0 && numbers[i + 2] > 0 && numbers[i + 3] > 0 && numbers[i + 4] > 0) {
                return true
            }
        }

        // A가 끝에 있는 경우
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