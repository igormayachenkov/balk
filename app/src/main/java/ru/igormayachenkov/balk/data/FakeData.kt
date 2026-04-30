package ru.igormayachenkov.balk.data

object FakeData {
    val balk = Balk(
        form = Form.Rectangle(
            width  = 0.05,
            height = 0.15,
            length = 3.0,
        ),
        support = Support.SimplySupported,
        load = Load.CenterPoint
    )
}