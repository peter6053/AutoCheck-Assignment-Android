package com.autocheck.model.CarListModel

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)