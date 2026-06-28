package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule

// A rule on a single cell, without context into the unit or puzzle,
// thus very simple (novice, level 1)
abstract class RuleCell(val cell: Cell) : Rule()