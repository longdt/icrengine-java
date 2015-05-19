package com.eprotea.icrengine;


public class ICREngine {
	public static final int CHEQUE_MY = 0;
	public static final int CHEQUE_PH = 1;
	private int chqType;
	
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
	
	public ICREngine(int chqType) {
		this.chqType = chqType;
	}

	public Result predictCA(byte[] chqImg) throws ICRException {
		double[] output = new double[2];
		predictCA(chqType, chqImg, output);
		return new Result(output[0], output[1]);
	}
	
	private native void predictCA(int chqType, byte[] chqImg, double[] output) throws ICRException;
}
