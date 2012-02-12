package com.bhrobotics.morcontrol.util;

import java.util.Vector;

public class StringUtils {
	public static String[] split(String original, String delimiter) {
		Vector vector = new Vector();
		
		int start = 0;
		while (true) {
			int end = original.indexOf(delimiter, start);
			
			if (end == -1) {
				vector.addElement(original.substring(start));
				break;
			} else {
				vector.addElement(original.substring(start, end));
				start = end + 1;
			}
		}
		
		String[] result = new String[vector.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (String) vector.elementAt(i);
		}
		
		return result;
	}
	
	public static boolean contains(String string, String search) {
		return string.indexOf(search) != -1;
	}
}
