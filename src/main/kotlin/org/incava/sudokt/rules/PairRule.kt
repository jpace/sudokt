package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.unit.CellsUnit

// runs a rule for two cells against their common unit
abstract class PairRule(val x: Cell, val y: Cell, val unit: CellsUnit) : Rule()
