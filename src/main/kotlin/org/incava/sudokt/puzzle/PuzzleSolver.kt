package org.incava.sudokt.puzzle

import org.incava.io.Qlog
import org.incava.sudokt.Puzzle
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult
import org.incava.sudokt.rules.RuleCellInferPossible
import org.incava.sudokt.rules.RuleGenerator

open class PuzzleSolver(val puzzle: Puzzle, val watcher: PuzzleSolverWatcher? = null) {
    val generator = RuleGenerator()
    val maxAttempts = 1_000_000
    val puzzleCells = PuzzleCells(puzzle.cells)
    var currentLevel = 1
    var attempts = 0

    fun run() {
        // this needs to be run on all cells before any other rules can be applied
        println("running rule 1: infer possible")
        runInferPossible()

        while (attempts < maxAttempts) {
            Qlog.info("attempts", attempts)
            Qlog.info("currentLevel", currentLevel)
            runRule()
            Qlog.info("currentLevel", currentLevel)
            if (isSolved()) {
                Qlog.info("solved!")
                Qlog.info("attempts", attempts)
                return
            } else if (currentLevel == generator.rules.size) {
                Qlog.info("ending at level $currentLevel")
                return
            }
        }
        Qlog.info("not solved!")
    }

    fun isSolved() = puzzle.cells.all { it.number != null }

    fun runInferPossible() {
        watcher?.onRuleStart(RuleCellInferPossible::class.java)
        val results: List<RuleResult> = puzzle.cells.map { cell ->
            // level 1
            val rule = RuleCellInferPossible(cell)
            runRule(rule)
        }
        Qlog.info("results", results)
        watcher?.onRuleEnd(RuleCellInferPossible::class.java)
    }

    fun runRule() {
        println("running rule $currentLevel")
        var failures = 0
        while (failures < 500) {
            attempts++
            val rule = generator.generate(currentLevel, puzzleCells) ?: continue
            watcher?.onRuleStart(rule.javaClass)
            if (runRule(rule).updated) {
                Qlog.info("matched after failures", failures)
                // back to the root level
                currentLevel = 1
                return
            } else {
                failures++
            }
            watcher?.onRuleEnd(rule.javaClass)
        }
        // go to the next level
        currentLevel++
    }

    fun runRule(rule: Rule): RuleResult {
        val result = rule.run()
        watcher?.onRuleExecuted(rule, result)
        if (result.updated) {
            println("cell(s) ${result.cells} updated for rule (${rule.javaClass.simpleName}")
            println(result.description)
        }
        return result
    }
}