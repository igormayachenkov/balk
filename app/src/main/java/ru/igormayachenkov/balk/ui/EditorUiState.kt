package ru.igormayachenkov.balk.ui

import ru.igormayachenkov.balk.data.Balk

data class EditorUiState(
    val widthText: String,
    val width: Double?,
){
    val isValid : Boolean
        get() = width!=null

    constructor(balk: Balk) : this(
        width     = balk.width,
        widthText = balk.width.toString()
    )

    fun updateWidthText(text: String) = copy(
        widthText = text,
        width = text.toDoubleOrNull()
    )
}
