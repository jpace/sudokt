package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult
import org.incava.sudokt.impl.PuzzleData

// runs a rule for two cells against their common unit
abstract class RuleCellPair(val x: Cell, val y: Cell, val unitCells: List<Cell>) : Rule() {
}
