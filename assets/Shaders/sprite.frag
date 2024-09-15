uniform sampler2D m_tex;
in vec2 fTexCoord;

void main() {
  vec4 sampled = texture2D(m_tex,fTexCoord);
  gl_FragColor = sampled;
}