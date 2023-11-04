import org.openrndr.*
import org.openrndr.color.*
import org.openrndr.draw.*
import org.openrndr.events.*
import org.openrndr.math.*
import org.openrndr.shape.*
import org.openrndr.extra.compositor.*
import kotlin.math.*



suspend fun main() = applicationAsync {
    configure {
        title = "Mouse Circle"
    }

    program {
        var mousePosition: Vector2 = Vector2(0.0,0.0)
        var deskTrigger = false
        var myText = ""

        val urlString = js("window.location.search")
        val paramMap = getUrlParamMap(urlString)

        val primaryMsg = paramMap["primary"] ?: "welcome"
        val secondMsg = paramMap["second"] ?: "scroll down"
        val scalePreset: Double = paramMap["scale"]?.toDoubleOrNull() ?: 1.0

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
                circle(mousePosition,150.0*scalePreset)

                fill = mixedMoon
                circle(windowSize - mousePosition,140.0*scalePreset)
            }

            if (deskTrigger) if (distanceRate < 0.6) { myText = primaryMsg } else { deskTrigger = false }
            else if (distanceRate > 0.4) { myText = secondMsg } else { deskTrigger = true }

            alphabet15dotWriter(drawer) {
                newWriting {
                    style.color = ColorRGBa.GRAY
                    style.charGap = 0.6
                    style.scale = defaultStyle.scale * scalePreset
                    move(center.x-(textWidth(myText)/2.0),center.y - (textHeight/2.0))
                    writeLine(myText)
                }
            }
        }
    }
}
