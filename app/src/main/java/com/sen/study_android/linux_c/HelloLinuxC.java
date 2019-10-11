package com.sen.study_android.linux_c;

import android.content.Context;
import android.util.Log;

import com.jieli.linuxc.HelloLinux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * create Data:2019-09-23
 * create by:chensenhua
 **/
public class HelloLinuxC {

    public void testHelloLinex() {
        HelloLinux helloLinux = new HelloLinux();
        String str = helloLinux.helloLinuxC();
        Log.e("sen", "hello linux:" + str);
        System.out.println("hello linux:" + str);
    }

    public void testOpenFile(Context context) {
        HelloLinux helloLinux = new HelloLinux();
        String path = context.getCacheDir().getPath() + File.separator + "cache.ext";
        Log.e("sen", "path:" + path);
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }


            try {
                OutputStream os = new FileOutputStream(path);
                os.write("hello linux 1".getBytes());
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String ret = helloLinux.openFile(path);


        Log.e("sen", "read:" + ret);
    }


}
