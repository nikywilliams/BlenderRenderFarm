# BlenderRenderFarm

This is a forray into creating a blender render farm using a bit of Java and any spare computers that are on the same network.

The goal of this project is to make setting up a blender render farm simple for simple projects. The network render option in Blender can be a bit complicated to get setup correctly and tends to be flaky at times. The .blend file must have almost everything configured and setup as this program does not allow for a lot of customization of the parameters passed to Blender. The only thing that needs to be provided in the config file are:

* Location of the Blender executable
* Location of the .blend files
* Location of where the final output images of the animation will be stored
* Type of render...animation or a single frame from the animation

Documentation of this is provided in the configuration file.
