package com.shamith.BookApp.helper

import com.shamith.BookApp.data.ItemsItem

interface OnItemClickCallback {
    fun onItemClicked(data: ItemsItem)
}