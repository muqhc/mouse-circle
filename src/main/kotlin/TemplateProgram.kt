import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.draw.*
import org.openrndr.events.*
import org.openrndr.math.*
import org.openrndr.shape.*
import org.openrndr.extra.compositor.*
import org.openrndr.extra.fx.blend.Add
import org.openrndr.extra.fx.blend.Normal
import org.openrndr.extra.fx.blur.ApproximateGaussianBlur
import org.openrndr.extra.fx.distort.HorizontalWave
import org.openrndr.extra.fx.distort.VerticalWave
import org.openrndr.extra.fx.shadow.DropShadow
import kotlin.math.*



suspend fun main() = applicationAsync {
    configure {
        title = "Mouse Circle"
    }

    program {
        var mousePosition: Vector2 = Vector2(0.0,0.0)
        var deskTrigger = false
        var myText = ""

        mouse.moved.listen {
            mousePosition = it.position
        }

        mouse.dragged.listen {
            mousePosition = it.position
        }

        extend {
            val myBackgroundColor = ColorRGBa.BLACK
            val myBackgroundColorBase = ColorRGBa.WHITE
            val myPrimaryColor = rgb("#FC9601")
            val myPrimaryColorBase = ColorRGBa.WHITE

            val windowSize = Vector2(width.toDouble(),height.toDouble())
            val center = windowSize/2.0

            val mouseDistanceFromCenter = mousePosition.distanceTo(windowSize/2.0)
            val maxDistanceFromCenter = min(width,height).toDouble()/2.0
            val distanceRate = min(1.0,mouseDistanceFromCenter/maxDistanceFromCenter)
            val distanceRateSun = max(0.0,min(1.0,mouseDistanceFromCenter/maxDistanceFromCenter + 0.3))
            val distanceRateMoon = max(0.0,min(1.0,mouseDistanceFromCenter/maxDistanceFromCenter - 0.05))

            val mixedSun = mix(myPrimaryColor,myPrimaryColorBase,distanceRateSun)
            val mixedBackground = mix(myBackgroundColor,myBackgroundColorBase,distanceRate)
            val mixedBackgroundReversed = mix(myBackgroundColor,myBackgroundColorBase,1.0-distanceRate)
            val mixedMoon = mix(myBackgroundColor,myBackgroundColorBase,distanceRateMoon)

            drawer.clear(mixedBackground)

            drawer.circles {
                stroke = null

                fill = mixedSun
                circle(mousePosition,150.0)

                fill = mixedMoon
                circle(windowSize - mousePosition,140.0)
            }

            drawer.fill = mixedBackgroundReversed
            drawer.fontMap = FontImageMap.fromUrl("https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu4mxK.woff2",48.0)
            if (deskTrigger) if (distanceRate < 0.6) { myText = "scroll down" } else { deskTrigger = false }
            else if (distanceRate > 0.4) { myText = "here" } else { deskTrigger = true }
            drawer.writer {
                newLine()
                move(center.x-(textWidth(myText)/2.0),center.y)
                text(myText)
            }
        }
    }
}