package org.incava.sudokt

abstract class Rule {
    abstract fun description(): String
    abstract fun run(): RuleResult
}