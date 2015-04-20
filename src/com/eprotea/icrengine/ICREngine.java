package com.eprotea.icrengine;


public class ICREngine {
	static {
		try {
			NativeUtils.loadLibraries("icrengine");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * load models. This method need call only one time
	 * @param modelsFolder
	 */
	public static native void loadModels(String modelsFolder) throws ICRException;
	
	public Result predictCA(byte[] chqImg) throws ICRException {
		double[] output = new double[2];
		predictCA(chqImg, output);
		return new Result(output[0], output[1]);
	}
	
	private native void predictCA(byte[] chqImg, double[] output) throws ICRException;
}
