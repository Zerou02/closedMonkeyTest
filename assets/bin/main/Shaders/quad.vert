
attribute vec2 inPosition;

uniform mat4 m_proj;
void main() { gl_Position = m_proj * vec4(inPosition, 0, 1.0); }