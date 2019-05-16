package ac692x_case.jieli.com.groovylibrary

import org.gradle.api.Plugin
import org.gradle.api.Project

  class PluginImpl implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("apply project : " + project.getName())
    }

    /**
     * 打印
     * @param msg
     */
    void println(String msg){
        System.out.println("\n"
                + "========================="
                +"\n"
                + "==" + msg
                +"\n"
                + "========================="
                + "\n")
    }
}

