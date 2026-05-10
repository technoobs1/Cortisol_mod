#version 150

uniform sampler2D DiffuseSampler;
uniform float YellowLevel;

in vec2 texCoord;
out vec4 fragColor;

void main()
{
    vec4 color = texture(DiffuseSampler, texCoord);


    color.r *= 1.0 + 0.2 * YellowLevel;
    color.g *= 1.0 + 0.15 * YellowLevel;
    color.b *= 1.0 - 0.25 * YellowLevel;

    fragColor = color;
}