package ru.igormayachenkov.balk.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.Form

@Parcelize
data class FormEditorState(
    val widthText: String,
    val width: Double?,
    val heightText: String,
    val height: Double?,
    val lengthText: String,
    val length: Double?,
): Parcelable{

    constructor(form: Form.Rectangle) : this(
        width     = form.width,
        widthText = form.width.toString(),
        height    = form.height,
        heightText= form.height.toString(),
        length    = form.length,
        lengthText= form.length.toString()
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
        form = Form.Rectangle(
            width  = width!!,
            height = height!!,
            length = length!!
        )
    )
}
