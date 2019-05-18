package com.sen.study_android.kotlin

enum class Direction{
    NORTH,SOUTH,WEST,EAST;
}

enum class Color(val i: Int) {
    RED(0xFF0000),
    BLUE(0x0000FF),
}



fun main(args: Array<String>) {
    System.out.println(Direction.WEST)
    System.out.println(Color.BLUE.i)

}