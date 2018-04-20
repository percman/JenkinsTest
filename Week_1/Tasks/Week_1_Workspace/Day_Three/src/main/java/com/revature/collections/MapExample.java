package com.revature.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapExample {
	
	public static void hashMapExample() {
		Map<String, String> capitalsToStates = new HashMap<>();
		capitalsToStates.put("Atlanta", "Georgia");
		capitalsToStates.put("Salem", "Oregon");
		capitalsToStates.put("Sacremento", "California");
		capitalsToStates.put("DC", null);
		capitalsToStates.put(null, "Ohio");
		capitalsToStates.put(null, "North Dakota");
		
		
		// Option 1 for iteration: This wonky thing
		Iterator<Map.Entry<String, String>> it = capitalsToStates.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			System.out.println(pair.getKey() + " => " + pair.getValue());
		}
		
		
		System.out.println("============");
		System.out.println("Using Functional Programming");
		
		
		// Option 2: Faaaaaancy
		capitalsToStates.forEach((k, v) -> System.out.println(k + " => " + v));
		
	}

}
