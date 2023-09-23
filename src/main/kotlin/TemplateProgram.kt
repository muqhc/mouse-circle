import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.events.*
import org.openrndr.math.*
import kotlin.math.*

suspend fun main() = applicationAsync {
    program {

        var drawPosition: Vector2? = null

        mouse.moved.listen {
            drawPosition = it.position
        }

        val tx = max(width,height)/80
        val ty = max(width,height)/80

        extend {
            val background = rgb("#000000")
            drawer.clear(background)
            drawer.fill = ColorRGBa.WHITE
            drawPosition?.let {
                (0..tx).forEach { x -> (0..ty).forEach { y ->
                    drawer.circle(
                        x.toDouble()*80,y.toDouble()*80,
                        min(40.toDouble(),(((x-it.x).pow(2)+(y-it.y).pow(2))/400).toDouble()).toDouble()
                    )
                }}
            }
        }
    }
}