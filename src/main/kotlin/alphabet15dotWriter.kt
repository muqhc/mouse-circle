import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.draw.*
import org.openrndr.events.*
import org.openrndr.math.*
import org.openrndr.shape.*
import org.openrndr.extra.compositor.*
import kotlin.math.*


class A15DWriteStyle() {
    var scale: Double = 20.0
    var dotScale: Double = 1.3
    var charGap: Double = 1.0
    var lineGap: Double = 3.0
    var alphabet15dotMap = alphabet15dotASCIIMap
}

class Alphabet15dotWriter(val drawer: Drawer, val defaultStyle: A15DWriteStyle = A15DWriteStyle()) {
    var header = -1

    inner class A15DLine(val style: A15DWriteStyle) {
        var cursorX = 0.0
        var cursorY = 0.0
        fun move(x: Double, y: Double) {
            cursorX = x
            cursorY = y
        }
        fun text(text: String) {
            text.toList().forEach { c ->
                (style.alphabet15dotMap[c] ?: alphabet15dotASCIIMap['$']!!).forEach { dL ->
                    if (dL.size == 1) {
                        val (x,y) = separateOneDigit(dL[0])

                        TODO("draw dot at (x,y)")
                    }
                    else dL.toList().zipWithNext().forEach { (dFrom,dTo) ->
                        val (x1,y1) = separateOneDigit(dFrom)
                        val (x2,y2) = separateOneDigit(dTo)
                        
                        TODO("draw line from (x1,y1) to (x2,y2)")
                    }
                }
            }
        }
    }

    fun newLine(style: A15DWriteStyle = defaultStyle, action: A15DLine.() -> Unit) {
        A15DLine(style).action()
    }
}



