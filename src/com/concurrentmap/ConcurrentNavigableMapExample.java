package com.concurrentmap;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentNavigableMapExample {
	public static void main(String[] args) {
		ConcurrentNavigableMap map = new ConcurrentSkipListMap();

		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");

		ConcurrentNavigableMap headMap = map.headMap("2"); 	// strictly less than 2
		ConcurrentNavigableMap tailMap = map.tailMap("2");	// Greater than equal to 2
		ConcurrentNavigableMap subMap = map.subMap("2", "3");  // greater than equals to 2 and less than 3
	}
}
