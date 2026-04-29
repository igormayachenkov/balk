package ru.igormayachenkov.balk.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.Form
import ru.igormayachenkov.balk.data.Form.*

/**
 * Keep all possible values for all Form classes as a list
 * to avoid data reset on class switching
 */

enum class FormClass {
    RECTANGLE, CIRCLE
}

private const val DefaultWidth  = 0.1
private const val DefaultHeight = 0.1
private const val DefaultRadius = 0.1

@Parcelize
data class FormEditorState(
    val formClass: FormClass,
    // Rectangle
    val widthText: String,
    val width: Double?,
    val heightText: String,
    val height: Double?,
    // Circle
    val radiusText: String,
    val radius: Double?,
    // Common
    val lengthText: String,
    val length: Double?,
): Parcelable{

    // From Rectangle
    constructor(form: Form.Rectangle) : this(
        formClass = FormClass.RECTANGLE,
        width     = form.width,
        widthText = form.width.toString(),
        height    = form.height,
        heightText= form.height.toString(),
        radius    = DefaultRadius,
        radiusText= DefaultRadius.toString(),
        length    = form.length,
        lengthText= form.length.toString()
    )
    // From Circle
    constructor(form: Form.Circle) : this(
        formClass = FormClass.CIRCLE,
        width     = DefaultWidth,
        widthText = DefaultWidth.toString(),
        height    = DefaultHeight,
        heightText= DefaultHeight.toString(),
        radius    = form.radius,
        radiusText= form.radius.toString(),
        length    = form.length,
        lengthText= form.length.toString()
    )
    // UNIFIED CONSTRUCTOR from Form class
    companion object {
        fun fromForm(form:Form) = when(form){
            is Form.Rectangle -> FormEditorState(form)
            is Form.Circle    -> FormEditorState(form)
        }
    }

    fun updateClass(formClass: FormClass) = copy(
        formClass = formClass,
    )
    fun updateWidth(text: String) = copy(
        widthText = text,
        width = text.toDoubleOrNull()
    )
    fun updateHeight(text: String) = copy(
        heightText = text,
        height = text.toDoubleOrNull()
    )
    fun updateRadius(text: String) = copy(
        radiusText = text,
        radius = text.toDoubleOrNull()
    )
    fun updateLength(text: String) = copy(
        lengthText = text,
        length = text.toDoubleOrNull()
    )

    val isValid : Boolean
        get() = when(formClass){
            FormClass.RECTANGLE -> width!=null && height!=null && length!=null
            FormClass.CIRCLE    -> radius!=null && length!=null
        }


    fun copyToBalk(balk: Balk): Balk = balk.copy(
        form = when(formClass) {
            FormClass.RECTANGLE -> Rectangle(
                width = width!!,
                height = height!!,
                length = length!!
            )
            FormClass.CIRCLE -> Circle(
                radius = radius!!,
                length = length!!
            )
        }
    )
}
