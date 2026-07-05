package org.incava.sudokt.view

import org.incava.io.Qlog

class ColorString(val string: String, val width: Int = string.length) {
    // https://misc.flogisoft.com/bash/tip_colors_and_formatting
    // https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
    // https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit

    fun foreground(red: Int, green: Int, blue: Int) = decorate("38;2;$red;$green;$blue")
    fun background(red: Int, green: Int, blue: Int) = decorate("48;2;$red;$green;$blue")
    fun foreground(number: Int) = decorate("38;5;$number")
    fun background(number: Int) = decorate("48;5;$number")
    fun bold() = decorate("1")
    fun dim() = decorate("2")
    fun italic() = decorate("3")
    fun underline() = decorate("4")
    fun blink() = decorate("5")
    fun reverse() = decorate("7")
    fun hidden() = decorate("8")
    fun strikethrough() = decorate("9")

    private fun decorate(with: String): ColorString {
        val reset = escape("0")
        val tail = if (string.endsWith(reset)) "" else reset
        val wrapped = escape(with) + this.string + tail
        return ColorString(wrapped, width)
    }

    private fun escape(code: String) = "\u001b[${code}m"

    override fun toString() = string

    fun padEnd(width: Int): String {
        val offset = width - this.width
        return if (offset > 0) {
            string + " ".repeat(offset)
        } else {
            string
        }
    }
}
