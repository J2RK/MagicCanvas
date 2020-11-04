package com.j2rk.magiccanvas.feature.doodling.paint

import com.j2rk.magiccanvas.feature.doodling.CustomGLSurface
import com.j2rk.magiccanvas.feature.doodling.IOpenGLObject
import com.j2rk.magiccanvas.feature.doodling.MeshPoint
import com.j2rk.magiccanvas.feature.doodling.Vector
import kotlin.collections.ArrayList

class Brush(screenHeight: Float, surface: CustomGLSurface) : PaintBase(screenHeight, surface), IOpenGLObject {

    override fun calPoints() {
        glSurface.queueEvent {
            val out = ArrayList<MeshPoint>()
            segments.clear()
            if (meshPointQueue.size > 3) {
                smoother.resolve(ArrayList(meshPointQueue), out)
                var A: Vector
                var B: Vector
                var C: MeshPoint?
                var D: MeshPoint?
                for (i in out.indices) {
                    if (i == 0 || i == out.size - 1) {
                        val meshPoint = out[i]
                        segments.add(MeshPoint(
                            convertToGLCoords(meshPoint.point),
                            out[i].color,
                            meshPoint.age)
                        )
                    } else {
                        val currentMeshPoint = out[i]
                        val nextMeshPoint = out[i + 1]
                        A = currentMeshPoint.point
                        B = nextMeshPoint.point
                        val perpV = Vector.calNorPerpV(A, B)
                        val scaleFactor = (1 - Vector.dist(A, B) * 30 / screenHeight)
                        C = MeshPoint(
                                convertToGLCoords(
                                    Vector.add(B, Vector.scale(perpV, strokeThickness * scaleFactor))
                                ),
                                nextMeshPoint.color,
                                nextMeshPoint.age
                        )
                        D = MeshPoint(
                                convertToGLCoords(
                                    Vector.sub(B, Vector.scale(perpV, strokeThickness * scaleFactor))
                                ),
                                nextMeshPoint.color,
                                nextMeshPoint.age
                        )
                        segments.add(C)
                        segments.add(D)
                    }
                }
            }
        }
    }
}