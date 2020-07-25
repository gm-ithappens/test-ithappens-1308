cmake_minimum_required(VERSION 3.5)

project(tela_inicial LANGUAGES CXX)

set(CMAKE_INCLUDE_CURRENT_DIR ON)

set(CMAKE_CXX_FLAGS "-fpermissive")

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

find_package(Qt5 COMPONENTS Widgets REQUIRED)

add_executable(tela_inicial main.cpp InitialWindow.cpp ManagerWindow.cpp AdminModule.cpp UserModule.cpp)

target_link_libraries(tela_inicial Qt5::Widgets)
