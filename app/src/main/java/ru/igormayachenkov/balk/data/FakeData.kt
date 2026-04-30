package ru.igormayachenkov.balk.data

import androidx.compose.ui.Modifier

object FakeData {
    val balk = Balk(
        form = Form.Rectangle(
            width  = 0.05,
            height = 0.15,
            length = 3.0,
        ),
        support = Support.SimplySupported,
        load = Load(loadType = LoadType.CenterPoint, weight = 300.0)
    )
}