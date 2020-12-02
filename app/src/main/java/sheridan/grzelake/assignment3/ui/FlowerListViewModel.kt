package sheridan.grzelake.assignment3.ui

import androidx.lifecycle.*
import androidx.lifecycle.liveData
import sheridan.grzelake.assignment3.model.Flower
import sheridan.grzelake.assignment3.network.FlowerApi
import sheridan.grzelake.assignment3.network.FlowerJson


class FlowerListViewModel : ViewModel() {

    private var flowerListData: LiveData<List<Flower>>? = null

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
}

fun FlowerJson.asFlower(index: Int): Flower{
    return Flower(label, text, pictures.large, index.toLong())
}