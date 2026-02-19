package ru.igormayachenkov.balk.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.igormayachenkov.balk.data.Balk

@Parcelize
data class EditorState(
    val widthText: String,
    val width: Double?,
    val heightText: String,
    val height: Double?,
    val lengthText: String,
    val length: Double?,
): Parcelable{

    constructor(balk: Balk) : this(
        width     = balk.width,
        widthText = balk.width.toString(),
        height    = balk.height,
        heightText= balk.height.toString(),
        length    = balk.length,
        lengthText= balk.length.toString()
    )

    fun updateWidth(text: String) = copy(
        widthText = text,
        width = text.toDoubleOrNull()
    )
    fun updateHeight(text: String) = copy(
        heightText = text,
        height = text.toDoubleOrNull()
    )
    fun updateLength(text: String) = copy(
        lengthText = text,
        length = text.toDoubleOrNull()
    )

    val isValid : Boolean
        get() = width!=null && height!=null && length!=null

    fun copyToBalk(balk: Balk): Balk = balk.copy(
        width  = width!!,
        height = height!!,
        length = length!!
    )
}
