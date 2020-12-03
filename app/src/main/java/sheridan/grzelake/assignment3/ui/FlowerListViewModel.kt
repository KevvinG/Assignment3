package sheridan.grzelake.assignment3.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.liveData
import sheridan.grzelake.assignment3.model.Flower
import sheridan.grzelake.assignment3.network.FlowerApi
import sheridan.grzelake.assignment3.network.FlowerJson


class FlowerListViewModel : ViewModel() {

    private var flowerListData: LiveData<List<Flower>>? = null

    private val _selectedFlower = MutableLiveData<Flower>()

    val selectedFlower get() = _selectedFlower

    fun getFlowers(): LiveData<List<Flower>> {
        return flowerListData ?: liveData {
            val catalog = FlowerApi.retrofitService.getCatalog()
            val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
                flowerJson.asFlower(index)
            }
            emit(flowers)
        }.also {
            flowerListData = it
        }
    }

    fun onFlowerClicked(id: Flower) {
        _selectedFlower.value = id
    }

    fun onNavComplete() {
        _selectedFlower.value = null
    }


}

fun FlowerJson.asFlower(index: Int): Flower{
    return Flower(label, text, pictures.large, price, index.toLong())
}