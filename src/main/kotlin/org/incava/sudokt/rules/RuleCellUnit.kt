package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.unit.CellsUnit

// runs a rule for a cell against its unit
abstract class RuleCellUnit(val cell: Cell, val unit: CellsUnit) : Rule()
