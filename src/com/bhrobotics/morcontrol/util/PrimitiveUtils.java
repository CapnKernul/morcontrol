package com.bhrobotics.morcontrol.util;

public abstract class PrimitiveUtils {
    public static boolean isPrimitive(Object obj) {
	return (obj instanceof Character) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Boolean);
    }

    public static char toPrimitive(Character obj) {
	return obj.charValue();
    }

    public static byte toPrimitive(Byte obj) {
	return obj.byteValue();
    }

    public static short toPrimitive(Short obj) {
	return obj.shortValue();
    }

    public static int toPrimitive(Integer obj) {
	return obj.intValue();
    }

    public static long toPrimitive(Long obj) {
	return obj.longValue();
    }

    public static float toPrimitive(Float obj) {
	return obj.floatValue();
    }

    public static double toPrimitive(Double obj) {
	return obj.doubleValue();
    }

    public static boolean toPrimitive(Boolean obj) {
	return obj.booleanValue();
    }

    public static Character toObject(char c) {
	return new Character(c);
    }

    public static Byte toObject(byte b) {
	return new Byte(b);
    }

    public static Short toObject(short s) {
	return new Short(s);
    }

    public static Integer toObject(int i) {
	return new Integer(i);
    }

    public static Long toObject(long l) {
	return new Long(l);
    }

    public static Float toObject(float f) {
	return new Float(f);
    }

    public static Double toObject(double d) {
	return new Double(d);
    }

    public static Boolean toObject(boolean b) {
	return new Boolean(b);
    }
}
