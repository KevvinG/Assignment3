package sheridan.grzelake.assignment3.model

import java.io.Serializable

data class Flower(
    val label: String,
    val text: String,
    var picture: String,
    val id: Long = 0L
) : Serializable