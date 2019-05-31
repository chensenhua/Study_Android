package com.sen.study_android.leetCode;


import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class BJBSLrcDecoder {

    public byte[] decode(byte[] data) {
        byte[] key = {0x03, 0x03, (byte) 0x87, 0x19};
        byte[] ret = new byte[data.length];
        for (int i = 0; i < data.length / 4; i++) {
            ret[0 + i * 4] = (byte) (data[0 + i * 4] ^ key[0]);
            ret[1 + i * 4] = (byte) (data[1 + i * 4] ^ key[1]);
            ret[2 + i * 4] = (byte) (data[2 + i * 4] ^ key[2]);
            ret[3 + i * 4] = (byte) (data[3 + i * 4] ^ key[3]);
        }

        int start = (data.length / 4) * 4;
        for (int i = 0; i < data.length % 4; i++) {
            ret[start + i] = (byte) (data[start + i] ^ key[i]);
        }

        return ret;
    }


    @Test
    public void decodeFile() throws IOException {

        Properties p = System.getProperties();

        System.out.println("AutoParcelProcessor get  user.dir-->" + "\t" + p.getProperty("user.dir"));


        String path = p.getProperty("user.dir") + File.separator + "song.smp";
        String outPath = p.getProperty("user.dir") + File.separator + "song.mp3";
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(outPath);
        byte[] data = new byte[1024];

        int len = -1;

        while ((len = fis.read(data)) != -1) {
            byte [] temp=new byte[len];
            System.arraycopy(data,0,temp,0,len);
            fos.write(decode(temp));
            fos.flush();
        }
        fos.close();
        fis.close();


    }
}
