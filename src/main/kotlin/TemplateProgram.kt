import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.events.*
import org.openrndr.math.*
import kotlin.math.cos

suspend fun main() = applicationAsync {
    program {

        var drawPosition: Vector2? = null

        mouse.moved.listen {
            drawPosition = it.position
        }

        extend {
            val a = rgb("#ff0000")
            drawer.clear(a)
            drawer.fill = ColorRGBa.WHITE
            drawPosition?.let {
                drawer.circle(it.x,it.y, 100.0 + cos(seconds) * 40.0)
            }
        }
    }
}