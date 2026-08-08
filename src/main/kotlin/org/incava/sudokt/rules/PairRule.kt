package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule

// runs a rule for two cells against their common unit
abstract class PairRule(val x: Cell, val y: Cell, val unitCells: List<Cell>) : Rule() {
}
