package com.kaylayshi.notekeeper.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
