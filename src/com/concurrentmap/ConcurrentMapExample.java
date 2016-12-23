package com.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapExample {
	public static void main(String[] args) {
		ConcurrentMap concurrentMap = new ConcurrentHashMap();

		concurrentMap.put("key", "value");

		Object value = concurrentMap.get("key");
		System.out.println(value);
	}
}
