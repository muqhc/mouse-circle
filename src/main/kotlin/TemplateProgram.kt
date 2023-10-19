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



suspend fun main() = applicationAsync {
    program {
        var mousePosition: Vector2 = Vector2(0.0,0.0)

        mouse.moved.listen {
            mousePosition = it.position
        }

        extend {
            val myBackgroundColor = ColorRGBa.BLACK
            val myBackgroundColorBase = ColorRGBa.WHITE
            val myPrimaryColor = rgb("#FC9601")
            val myPrimaryColorBase = ColorRGBa.WHITE

            val windowSize = Vector2(width.toDouble(),height.toDouble())

            val mouseDistanceFromCenter = mousePosition.distanceTo(windowSize/2.0)
            val maxDistanceFromCenter = min(width,height).toDouble()/2.0
            val distanceRate = min(1.0,mouseDistanceFromCenter/maxDistanceFromCenter)

            val mixedPrimary = mix(myPrimaryColor,myPrimaryColorBase,distanceRate)
            val mixedBackground = mix(myBackgroundColor,myBackgroundColorBase,distanceRate)

            drawer.clear(mixedBackground)

            drawer.circles {
                stroke = null

                fill = mixedPrimary
                circle(mousePosition,150.0)

                fill = mixedBackground
                circle(windowSize - mousePosition,140.0)
            }
        }
    }
}