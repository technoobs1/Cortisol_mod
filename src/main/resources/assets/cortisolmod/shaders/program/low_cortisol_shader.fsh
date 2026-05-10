#version 150

uniform sampler2D InSampler;
uniform float Intensity;

in vec2 texCoord;
out vec4 fragColor;

void main()
{
    vec4 color = texture(InSampler, texCoord);

    vec3 tint = vec3(1.10, 1.05, 0.90);

    color.rgb = mix(color.rgb, color.rgb * tint, Intensity);

    //fragColor = vec4(color.rgb, 1.0);
    fragColor = texture(InSampler, texCoord);
}