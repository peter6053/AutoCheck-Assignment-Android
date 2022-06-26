package com.autocheck.model.CarMediaModel

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)