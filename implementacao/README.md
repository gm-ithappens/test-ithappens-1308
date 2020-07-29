# Simple Store and Sale System - 4S

## Introduction
The system is a MVP that have some attributes based in this document ../avaliacao/prova-1303.pdf.

## Architecture

The software have three basics modules:
1. User Interface 
	The UI is developed in Qt techonology;
2. Data Base
	Data base is make in SQLite;
3. Core Business
	The core is a main loop that know about the system work.

## Dependency

```
$ sudo apt -y install build-essential qtcreator qt5-default libsqlite3-0 libsqlite3-dev
```

## Build and Run

To build project only bring directory build, if do not exists create.
```
$ mkdir build
```

When into build directory:
```
$ NUM_PROCESSOR=`cat /proc/cpuinfo | grep processor | wc -l`
$ cmake ..
$ make -j $NUM_PROCESSOR
```

To run
```
$ ./system3S
```
## Diagram

![Application Diagram](../documentacao/system3S.jpg?raw=true "Title")

## Developer informations
Developer: Raniere Machado
email    : raniere.ee@gmail.com
