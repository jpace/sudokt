package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class Rules {
    val byLevel: List<(Cells) -> Rule>

    init {
        byLevel = listOf(
            ::RuleInferPossible,
            ::RuleSinglePossible,
            ::RuleRemoveNumberFromPossiblesInUnit,
            ::RuleNakedPairs,
            ::RuleHiddenPairs
        )
    }
}