package com.eprotea.icrengine;

import java.io.File;

public class ICREngine {
	static {
		try {
			String os = System.getProperty("os.name");
			String filename = os.startsWith("Windows") ? "icrengine.dll" : "libextracticr.so";
			System.load(new File("bin/" + filename).getAbsolutePath());
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
