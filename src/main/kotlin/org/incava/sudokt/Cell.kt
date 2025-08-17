package org.incava.sudokt

class Cell(val id: Int) {
    var number: Int? = null
    var possible = mutableSetOf<Int>()

    fun removePossible(num: Int) {
        possible.remove(num)
    }

    fun number(): Int? {
        return number
    }

    fun setNumber(num: Int) {
        number = num
    }

    fun row() = id / 9

    fun column() = id % 9

    fun box(): Int {
        val x = column() / 3
        val y = row() / 3
        return x + y * 3
    }

    override fun toString(): String {
        return "Cell(id=$id, possible=$possible, number=${number()}, row=${row()}, column=${column()}, box=${box()})"
    }

    fun updatePossible(possible: MutableSet<Int>) {
        this.possible = possible
    }
}