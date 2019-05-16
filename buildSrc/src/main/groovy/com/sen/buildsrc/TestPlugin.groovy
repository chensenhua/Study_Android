//package com.sen.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.org.apache.http.util.TextUtils


class TestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('pluginTest') {
            doLast {
                println '--------Hello World--------'
            }
        }


        println("apply project : " + project.getName())


        def map = ["id": 1];
        map.each {
            key, value ->
                println "--------for map--------key=$key  value=$value"

        }

        def parentRoot = project.getProjectDir().getPath()
        def path = parentRoot + File.separator + "build.gradle"
        println "path-->$path";

        def file = new File(path);
        file.eachLine {
            line ->
                if (line) {
                    println "read   project build.gradle-->$line"
                }
        }


    }

    /**
     * 打印
     *
     * @param msg
     */
    void println(String msg) {
        System.out.println  msg

    }



}

