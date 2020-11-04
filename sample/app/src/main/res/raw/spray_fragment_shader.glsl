precision mediump float;
varying vec4 frag_Color;
varying vec2 resolution;

void main() {
    vec4 color = frag_Color;
    vec2 st = gl_FragCoord.xy/resolution.xy;
    float rnd = fract(sin(dot(st.xy,vec2(12.9898,78.233)))*43758.5453123);
    gl_FragColor = vec4(vec3(rnd),1.0);
}

