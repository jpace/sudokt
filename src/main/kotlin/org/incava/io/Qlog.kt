package org.incava.io

import java.io.PrintStream
import java.time.Duration
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicLong

object Qlog {
    var out: PrintStream = System.out
    private val dateTimePattern: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS Z")

    fun info(msg: String, obj: Any?) {
        when (obj) {
            is Long -> {
                info(msg, obj)
            }
            is AtomicLong -> {
                info(msg, obj)
            }
            is Int -> {
                info(msg, obj)
            }
            is Duration -> {
                info(msg, obj)
            }
            is ZonedDateTime -> {
                info(msg, obj)
            }
            is Collection<*> -> {
                info(msg, obj)
            }
            is Map<*,*> -> {
                info(msg, obj)
            }
            else -> {
                println(ConsoleFormat.format(msg, obj))
            }
        }
    }

    fun info(msg: String, value: Long) = println(ConsoleFormat.format(msg, value))

    fun info(msg: String, value: AtomicLong) = println(ConsoleFormat.format(msg, value.get()))

    fun info(msg: String, value: Int) = println(ConsoleFormat.format(msg, value))

    fun info(msg: String, value: ZonedDateTime) = println(ConsoleFormat.format(msg, value.format(dateTimePattern)))

    fun info(msg: String) = println(whence(), msg)

    fun <T> info(msg: String, collection: Collection<T>) {
        val size = collection.size
        info("$msg.#", size)
        for (item in collection.withIndex()) {
            info("$msg[${item.index}]", item.value)
        }
    }

    fun <K, V> info(msg: String, map: Map<K, V>) {
        val size = map.size
        info("$msg.#", size)
        for ((key, value) in map) {
            info("$msg[$key]", value)
        }
    }

    fun blankLine() = printf("")

    fun println(msg: String) {
        val frame = whence()
        println(frame, msg)
    }

    fun println(frame: StackTraceElement?, msg: String) {
        val str = ConsoleFormat.format(frame, msg)
        out.println(str)
    }

    fun printf(fmt: String, vararg args: Any) {
        val str = String.format(fmt, *args)
        out.println(str)
    }

    private fun whence(): StackTraceElement? {
        val classes = listOf(Qlog.javaClass.name, "org.incava.ikdk.io.Console")
        var found = false
        Thread.currentThread().stackTrace.forEach {
            if (classes.contains(it.className)) {
                found = true
            } else if (found) {
                return it
            }
        }
        return null
    }
}