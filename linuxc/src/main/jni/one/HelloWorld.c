//
// Created by ZPC18-003 on 2019-09-23.
//
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>



JNIEXPORT jstring
JNICALL
Java_com_jieli_linuxc_HelloLinux_helloLinuxC(
        JNIEnv *env,
        jobject obj
) {

    printf("hello linux in c\n");
    return (*env)->NewStringUTF(env, "hello linux");

}



//JNIEXPORT jint JNI_Onload(JavaVM *vm, void *reserved){
//
//}
JNIEXPORT jstring JNICALL
Java_com_jieli_linuxc_HelloLinux_openFile(JNIEnv *env, jobject thiz, jstring path) {

    const char *_path = (*env)->GetStringUTFChars(env, path, 0);
//
    printf("sensen-->%d", 212);

//
//


  


    FILE  *file  = fopen(_path, "r");


    if (file) {
        char value[14];
        int len = 14;
        fgets(value,len,file);
        return (*env)->NewStringUTF(env, value);

    } else {
        return (*env)->NewStringUTF(env, "open file faile");
    }


//    (*env)->ReleaseStringUTFChars(env, path, _path);
//
//
//    return (*env)->NewStringUTF(env, _path);
}

JNIEXPORT jstring JNICALL
Java_com_jieli_linuxc_HelloLinux_helloLinuxC1(JNIEnv *env, jobject thiz) {
    // TODO: implement helloLinuxC1()
}