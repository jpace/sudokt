package org.incava.sudokt

class Possibles {
    var values = mutableSetOf<Int>()

    fun remove(num: Int): Boolean {
        return values.remove(num)
    }

    fun remove(numbers: Collection<Int>): Boolean {
        return numbers.fold(false) { acc, num -> remove(num) || acc }
    }

    fun clear() {
        values.clear()
    }

    fun isEmpty() = values.isEmpty()

    fun intersect(other: Possibles) = this.values.intersect(other.values)

    fun replace(values: Set<Int>): Boolean {
        return if (this.values == values) {
            false
        } else {
            this.values.clear()
            this.values.addAll(values)
            true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Possibles
        return values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        return "$values"
    }

    val size: Int
        get() = values.size

    val first: Int
        get() = values.first()
}