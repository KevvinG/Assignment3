package sheridan.grzelake.assignment3.model

import java.io.Serializable

data class Flower(
    val label: String,
    val text: String,
    var picture: String,
    var price: String,
    val id: Long = 0L
) : Serializable