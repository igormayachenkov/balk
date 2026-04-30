package ru.igormayachenkov.balk.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.Form
import ru.igormayachenkov.balk.data.Form.*
import ru.igormayachenkov.balk.data.Load
import ru.igormayachenkov.balk.data.LoadType

@Parcelize
data class LoadEditorState(
    val loadType: LoadType,
    val weightText: String,
    val weight: Double?,
): Parcelable{

    // From Load
    constructor(load: Load) : this(
        loadType  = load.loadType,
        weight    = load.weight,
        weightText= load.weight.toString()
    )

    fun updateType(loadType: LoadType) = copy(
        loadType = loadType,
    )
    fun updateWeight(text: String) = copy(
        weightText = text,
        weight = text.toDoubleOrNull()
    )

    val isValid : Boolean
        get() = weight!=null



    fun copyToBalk(balk: Balk): Balk = balk.copy(
        load = Load(
            loadType = loadType,
            weight = weight!!
        )
    )
}
