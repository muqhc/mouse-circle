import org.openrndr.applicationAsync
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
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
            if (drawPosition != null)
                drawer.circle(drawPosition, 100.0 + cos(seconds) * 40.0)
        }
    }
}