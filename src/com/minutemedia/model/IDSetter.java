package com.minutemedia.model;

import java.util.HashMap;
import java.util.Map;

public class IDSetter {
	private Map<String, Integer> hashMap = new HashMap<>();
	private int idCounter = 0;

	public IDSetter() {
		
	}
	
	public int setAnId(String name) {
		idCounter++;
		
		if(!hashMap.containsKey(name)) {
			hashMap.put(name, idCounter);
		}
		
		return hashMap.get(name);
	}

}
