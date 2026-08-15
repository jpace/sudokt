package org.incava.sudokt.unit

import org.incava.sudokt.Cell

open class CellsUnit(val cells: List<Cell>, val index: Int, val type: String) {
    val typeAsCapitalized = type.replaceFirstChar { it.uppercase() }
}