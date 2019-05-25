package com.sen.study_android.leetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTest {

    @Test
    public void test() {

        Map map=new HashMap();

        MyHashSet obj = new MyHashSet();
        obj.add(1);
        obj.add(499);
        obj.remove(499);
        obj.contains(499);
        obj.remove(2);
        boolean param_3 = obj.contains(1);
    }
}


class MyHashSet {

    private List<Integer>[] data ;
    /** Initialize your data structure here. */
    private int count=1000;
    private int max=1000000;
    public MyHashSet() {
        data=new List [count];

    }

    public void add(int key) {
        if(key<0){
            return;
        }

        int p=key%count;

        if(data[p]==null){
            data[p]=new ArrayList<Integer>();
        }

        data[p].add(Integer.valueOf(key));

    }

    public void remove(int key) {
        if(key<0){
            return;
        }
        int p=key%count;
        int index=-1;

        if(data[p]!=null){
            for(int i=0;i<data[p].size();i++){
                if(data[p].get(i)==key){
                    index=i;
                    break;
                }

            }

            if(index>-1){
                data[p].remove(index);
            }
        }

    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        if(key<0){
            return false;
        }
        int p=key%count;
        Integer temp=-1;
        if(data[p]!=null){
            for(int i=0;i<data[p].size();i++){
                if(data[p].get(i)==key){
                    temp=data[p].get(i);
                    break;
                }

            }
        }

        return  temp!=-1;
    }
}

