#version 150

uniform sampler2D DiffuseSampler;
uniform float Intensity;
uniform vec4 ColorModulate;

in vec2 texCoord;
out vec4 fragColor;

void main()
{
    vec4 color = texture(DiffuseSampler, texCoord);

    vec3 tint =vec3(1.16, 1.11, 0.82);

    color.rgb = mix(color.rgb, color.rgb * tint, Intensity);
    float contrast = 1.07;
    color.rgb= (color.rgb - 0.5) * 1.07 + 0.5;

    fragColor = vec4(color.rgb,1.);


}