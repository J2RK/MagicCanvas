uniform mat4 uMVPMatrix;
attribute vec4 vPosition;
uniform vec4 vColor;
varying vec4 frag_Color;
uniform vec2 u_resolution;
varying vec2 resolution;

void main() {
    gl_Position = uMVPMatrix * vPosition;
    resolution = u_resolution;
    frag_Color = vColor;
}