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
            val myPrimaryColor = rgb("#FC9601")
            val windowSize = Vector2(width.toDouble(),height.toDouble())

            drawer.clear(myBackgroundColor)

            //drawer.circles {
            //    stroke = null
//
            //    fill = myPrimaryColor
            //    circle(mousePosition,150.0)
//
            //    fill = myBackgroundColor
            //    circle(windowSize - mousePosition,150.0)
            //}
        }
    }
}