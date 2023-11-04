import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.draw.*
import org.openrndr.events.*
import org.openrndr.math.*
import org.openrndr.shape.*
import org.openrndr.extra.compositor.*
import kotlin.math.*

fun separateOneDigit(num: Int): Pair<Int,Int> = (num / 10) to (num % 10)

fun separateDigit(num: Int): List<Int> = sequence {
    var stk = num
    while (stk > 0) {
        val (nstk,y) = separateOneDigit(stk)
        yield(y)
        stk = nstk
    }
}.toList().reversed()

fun String.getUrlParamMap() =
    split('?').getOrNull(1)?.split('&')?.associate {
        val parts = it.split('=')
        val name = parts[0]
        val value = parts[1]
        name to value
    } ?: mapOf()





