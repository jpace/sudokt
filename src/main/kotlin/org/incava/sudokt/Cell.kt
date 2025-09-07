package org.incava.sudokt

class Cell(val id: Int) {
    var number: Int? = null
    var possible = mutableSetOf<Int>()
    val position = Position(id)

    fun removePossible(num: Int): Boolean {
        return possible.remove(num)
    }

    fun removePossibles(numbers: Collection<Int>): Boolean {
        return numbers.fold(false) { acc, num -> removePossible(num) || acc }
    }

    fun setPossibles(possible: Set<Int>): Boolean {
        return if (this.possible == possible) {
            false
        } else {
            this.possible = possible.toMutableSet()
            true
        }
    }

    override fun toString(): String {
        return "Cell(id=$id, possible=$possible, number=${number}, position=${position})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (id != other.id) return false
        if (number != other.number) return false
        if (possible != other.possible) return false
        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (number ?: 0)
        result = 31 * result + possible.hashCode()
        result = 31 * result + position.hashCode()
        return result
    }


}