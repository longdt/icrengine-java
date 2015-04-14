package com.eprotea.icrengine;

public class UVChecker {
	static {
		NativeUtils.loadLibraries("uvanalyzer");
	}
	
	/**
	 * check whether a given uvImg is valid or not
	 * @param uvImg
	 * @return true if uvImg is valid
	 * @throws ICRException
	 */
	public static native boolean checkValid(byte[] uvImg) throws ICRException;
}
