package com.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestJava {
	/**
	 * 1. Create and print an array list of 7 items, 
2. Read from array list and store in hashmap with any unique key
3. Search for an element in that HashMap(should take element as an input and validate if it is present or not)
3. (if yes) Remove that element and print the remaining elements, else print that the element is not present
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> lst = Arrays.asList("test1","test2","test3","test4","test5","test6","test7");
		//7
		for(String list : lst)
			System.out.println(list);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for(int i=0;i<lst.size();i++) {
			if(!(hashMap.containsKey(lst.get(i))))
				hashMap.put(lst.get(i)+i, lst.get(i));
		}
		//6
		
		String sampleInput = "test1";
		for( String key : hashMap.keySet()) {
			if((hashMap.get(key).equalsIgnoreCase((sampleInput)))) {
				hashMap.remove(sampleInput);
				System.out.println("Element has been removed");
			}else {
				System.out.println("Element is not present");
			}
		}
		
	}
}
