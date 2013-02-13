package com.bhrobotics.morcontrol.util;

public class OperatingSystem {
    public static boolean isCRio() {
	return System.getProperty("microedition.platform") != null;
    }
}
