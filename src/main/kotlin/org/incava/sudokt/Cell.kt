package org.incava.sudokt

class Cell(val id: Int) {
    var number: Int? = null
    var possible = mutableSetOf<Int>()
    val position = Position(id)

    fun removePossible(num: Int) {
        possible.remove(num)
    }

    fun removePossible(numbers: Set<Int>) {
        possible.removeAll(numbers)
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

    fun updatePossible(possible: MutableSet<Int>) {
        this.possible = possible
    }
}