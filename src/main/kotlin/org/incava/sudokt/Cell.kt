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

    fun number(): Int? {
        return number
    }

    fun setNumber(num: Int) {
        number = num
    }

    override fun toString(): String {
        return "Cell(id=$id, possible=$possible, number=${number()}, position=${position})"
    }
}