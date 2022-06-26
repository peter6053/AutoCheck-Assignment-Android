package com.autocheck.model.popularModel

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)