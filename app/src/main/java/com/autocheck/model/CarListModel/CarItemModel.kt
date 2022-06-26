package com.autocheck.model.CarListModel

data class CarItemModel(
    val pagination: Pagination,
    val result: List<Result>
)