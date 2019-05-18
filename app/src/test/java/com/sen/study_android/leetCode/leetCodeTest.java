package com.sen.study_android.leetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class leetCodeTest {

    @Test
    public void towSum() {
        int nums[] = new int[]{2, 7, 11, 15, 23, 445, 12, 34};
        int target = 23;
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }
        List<Integer> data;
        for (int i = 0; i < nums.length; i++) {
            Integer ret = (Integer) map.get(nums[i]);
            System.out.println("i=" + ret + "\tj=" + i);

        }

    }

    @Test
 public  void testLinkList() {
       MyLinkedList obj = new MyLinkedList();

//        obj.addAtHead(1);
//        obj.addAtTail(3);
//        obj.addAtIndex(0, 2);
//        obj.get(2);
//        obj.deleteAtIndex(1);
//        obj.get(2);

//        obj.addAtHead(1);
//        obj.addAtIndex(1, 2);
//        obj.get(1);
//
//        obj.get(0);
//        obj.get(2);


//        obj.get(0);
//        obj.addAtIndex(1, 2);
//        obj.get(0);
//        obj.get(1);
//        obj.addAtIndex(0,1);
//        obj.get(0);
//        obj.get(1);

//            obj.addAtHead(1);
//            obj.deleteAtIndex(0);


        obj.addAtHead(1);
        obj.addAtTail(3);
         obj.addAtIndex(1, 2);
         obj.get(-1);
         obj.deleteAtIndex(1);
         obj.get(-3);

    }
}
