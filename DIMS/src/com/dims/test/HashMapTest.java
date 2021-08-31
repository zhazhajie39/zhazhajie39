package com.dims.test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public HashMapTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Integer> map = new HashMap<>();
        map.put("s1", 1);
        map.put("s2", 2);
        map.put("s3", 3);
        map.put("s4", 4);
        map.put("s5", 5);
        map.put(null, 9);
        map.put("s6", 6);
        map.put("s7", 7);
        map.put("s8", 8);
        System.out.println(map.get("s8"));//有键得到值
	}

}
