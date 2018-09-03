package com.maradroid.dummyresponsegenerator.model

data class RecyclerWrapper(val type: Int, var data: Any, val jsonElementType: Int, val title: String, var level: Int, var expanded: Boolean) {
    constructor(type: Int, data: Any, jsonElementType: Int, title: String, level: Int): this(type, data, jsonElementType, title, level, false)
    constructor(type: Int, title: String, level: Int): this(type, Any(), -1, title, level, false)
    constructor(type: Int, title: String): this(type, Any(), -1, title, -1, false)
}
