package kr.ac.kumoh.s20190348.s23w1102composecounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count : LiveData<Int> = _count

    fun onAdd() {
        _count.value = _count.value?.plus(1)
    }
    fun onSub() {
        if ((_count.value ?: 0) > 0) {
            _count.value = _count.value?.minus(1)
        }
    }
}