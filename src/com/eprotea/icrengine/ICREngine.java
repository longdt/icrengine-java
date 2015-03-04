package com.eprotea.icrengine;


public class ICREngine {
	static {
		try {
			NativeUtils.loadLibraries();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static native void loadModels(String modelsFolder);
	
	public Result predictCA(byte[] chqImg) {
		double[] output = new double[2];
		predictCA(chqImg, output);
		return new Result(output[0], output[1]);
	}
	
	private native void predictCA(byte[] chqImg, double[] output);
}
