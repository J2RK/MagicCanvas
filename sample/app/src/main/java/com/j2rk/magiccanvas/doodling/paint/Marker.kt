package com.j2rk.magiccanvas.feature.doodling.paint

import com.j2rk.magiccanvas.doodling.CustomGLSurface
import com.j2rk.magiccanvas.doodling.MeshPoint
import com.j2rk.magiccanvas.doodling.Vector
import com.j2rk.magiccanvas.doodling.paint.PaintBase


class Marker(screenHeight: Float, surface: CustomGLSurface) : PaintBase(screenHeight, surface) {

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
                        segments.add(MeshPoint(convertToGLCoords(out[i].point), out[i].color, out[i].age))
                    } else {
                        val currentMeshPoint = out[i]
                        val nextMeshPoint = out[i + 1]
                        A = currentMeshPoint.point
                        B = nextMeshPoint.point
                        val perpV = Vector.calNorPerpV(A, B)
                        C = MeshPoint(
                                convertToGLCoords(
                                        Vector.add(B, Vector.scale(perpV, strokeThickness.toDouble()))
                                ),
                                nextMeshPoint.color,
                                nextMeshPoint.age
                        )
                        D = MeshPoint(
                                convertToGLCoords(
                                        Vector.sub(B, Vector.scale(perpV, strokeThickness.toDouble()))
                                ),
                                nextMeshPoint.color,
                                nextMeshPoint.age
                        )
                        C.color.setAlpha(0.5f)
                        D.color.setAlpha(0.5f)
                        segments.add(C)
                        segments.add(D)
                    }
                }
            }
        }
    }
}