package com.sen.study_android.kotlin

import org.junit.Assert
import org.junit.Test
import kotlin.reflect.KProperty


interface  Base{
    fun print();
}


class BaseImpl(val x:Int):Base{
    override fun print() {
        print(x)
    }
}

class  Derived( b:Base):Base by b{



}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}'to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}


class Example{
    var p:String by Delegate()
}


class ExampleUnitTest {


    @Test
    fun testa() {
        Derived(BaseImpl(12)).print()
        val e= Example()
        println(e.p)
        e.p="test";
    }



}

