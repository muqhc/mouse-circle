import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.events.*
import org.openrndr.math.*
import org.openrndr.extra.compositor.*
import org.openrndr.extra.fx.blend.Add
import org.openrndr.extra.fx.blend.Normal
import org.openrndr.extra.fx.blur.ApproximateGaussianBlur
import org.openrndr.extra.fx.distort.HorizontalWave
import org.openrndr.extra.fx.distort.VerticalWave
import org.openrndr.extra.fx.shadow.DropShadow
import org.openrndr.shape.*
import kotlin.math.*

val mainColor = ColorRGBa.ORANGE

suspend fun main() = applicationAsync {
    program {

        var drawPosition: Vector2 = Vector2(0.0,0.0)

        mouse.moved.listen {
            drawPosition = it.position
        }

        val composite = compose {
            draw {
                drawer.fill = mainColor
                drawer.stroke = null
                drawer.circle(drawPosition, 175.0)
            }
            
            layer {
                blend(Add()) {
                    clip = true
                }
                draw {
                    drawer.fill = mainColor
                    drawer.stroke = null
                    drawer.circle(drawPosition*(-1.0)+Vector2(width.toDouble(),height.toDouble()), 150.0)
                }
                post(ApproximateGaussianBlur()) {
                    window = 25
                    sigma = 10.00
                }
            }
        }

        extend {
            
        }
    }
}