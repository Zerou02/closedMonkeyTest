
attribute vec2 inPosition;
attribute vec2 inTexCoord;

uniform mat4 m_proj;

// fucking j-monkey 'varying' deprecated since 2001, removed since 2009
varying vec2 fTexCoord;
void main() {
  fTexCoord = inTexCoord;
  gl_Position = m_proj * vec4(inPosition, 0, 1.0);
}