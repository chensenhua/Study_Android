cmake_minimum_required(VERSION 3.4.1)

add_library(helloworld SHARED HelloWorld.c)
target_link_libraries(helloworld log android)