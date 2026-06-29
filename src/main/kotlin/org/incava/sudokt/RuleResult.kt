package org.incava.sudokt

data class RuleResult(val updated: Boolean = false, val description: String = "", val cells: List<Cell> = emptyList())