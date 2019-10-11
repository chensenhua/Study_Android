package com.jieli.linuxc;

/**
 * create Data:2019-09-23
 * create by:chensenhua
 **/
public class HelloLinux {
    static {
        System.loadLibrary("helloworld");
    }



     public native  String helloLinuxC();
     public native  String helloLinuxC1();

    public native  String openFile(String path);
}
