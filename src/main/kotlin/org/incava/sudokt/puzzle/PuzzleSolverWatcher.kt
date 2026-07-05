package org.incava.sudokt.puzzle

import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult
import org.incava.sudokt.rules.RuleCellInferPossible

interface PuzzleSolverWatcher {
    fun onRuleStart(cls: Class<out Rule>)
    fun onRuleEnd(cls: Class<out Rule>)
    fun onRuleExecuted(rule: Rule, result: RuleResult)
}