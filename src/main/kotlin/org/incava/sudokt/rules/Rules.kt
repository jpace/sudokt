package org.incava.sudokt.rules

import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule

class Rules {
    val byLevel: List<(PuzzleCells) -> Rule>

    init {
        byLevel = listOf(
            ::RuleSinglePossible,
            ::RuleRemoveNumberFromPossiblesInUnit,
            ::RuleNakedPairs,
            ::RuleHiddenPairs
        )
    }
}