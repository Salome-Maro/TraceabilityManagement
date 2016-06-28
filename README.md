# Capra - A Traceability Management Tool

### Background
Capra is a configurable and extendable traceability management tool. It is created in the context of an ITEA funded project called AMALTHEA4Public whose main aim is to develop a platform that will improve the development of embedded multicore and many core systems.

### How it works

Capra uses the Eclipse Modelling Framework (EMF) as its base technology and stores the traceability model as an EMF model. The traceability metamodel is not fixed and can be defined by the user. It relies on the Eclipse Extension mechanism and provides an extension point for artifacts types to be supported. To add a new type of artifact, one simply needs to add an extension to this extension point and implement the provided interfaces.


# User Guide - End Users

### Pre-requisites

Before downloading and using Capra, download Eclipse Modelling Environment and make sure you have the following installed.

* [PlantUML](http://plantuml.com)
* [Xcore](https://wiki.eclipse.org/Xcore)
* [Mylyn](https://www.eclipse.org/mylyn/)
* [C/C++ Development Tools](https://www.eclipse.org/cdt/)
* [Java Development Tools](https://www.eclipse.org/jdt)


### Running Capra

* Make sure that all the projects have no errors.
* Click on Run --> Run Configurations and create a new Eclipse Application Configuration
* Select your running workspace
* Click Finish
* Once the new workspace opens create or import projects that you want to  use to create traceability links
* Go to perspectives and switch to the Capra perspective
* Follow [this video](https://www.dropbox.com/s/9p76ebqvax16uc1/HVAC-Capra%20Incomplete1.mov?dl=0) to create and visualize traceability links



# How to Extend the Tool - For Developers

