package com.eprotea.icrengine;

public class UVChecker {
	static {
		NativeUtils.loadLibraries("uvanalyzer");
	}
	public static native boolean checkValid(byte[] uvImg) throws ICRException;
}
