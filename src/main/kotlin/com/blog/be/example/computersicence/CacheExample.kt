package com.blog.be.example.computersicence

class LruCache<String, Any>(private val maxSize: Int) :
    LinkedHashMap<String, Any>(maxSize, 0.75f, true) {

    override operator fun get(key: String): Any? {
        return super.get(key)
    }

    override fun removeEldestEntry(eldest: Map.Entry<String, Any>): Boolean {
        return this.size > maxSize
    }
}
