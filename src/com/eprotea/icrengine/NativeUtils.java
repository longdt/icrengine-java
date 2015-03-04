package com.eprotea.icrengine;

import java.io.File;

public class NativeUtils {
	private static String os = System.getProperty("os.name").toLowerCase();
	private static boolean windows = os.startsWith("windows");
	private static String LIBRARY_NAME = "icrengine";
	private static String arch = System.getProperty("os.arch");

	public static void loadLibraries() {
		if (windows) {
			loadWindowsLibs();
		} else {
			loadLinuxLibs();
		}
	}

	private static void loadWindowsLibs() {
		String[] dependencies = new String[] { "opencv/opencv_core2410.dll",
				"opencv/opencv_highgui2410.dll",
				"opencv/opencv_imgproc2410.dll", "tbb/tbb.dll" };
		for (String dependlib : dependencies) {
			System.load(new File("native_libs/windows/" + arch + "/"
					+ dependlib).getAbsolutePath());
		}
		System.load(new File("native_libs/windows/" + arch + "/" + LIBRARY_NAME
				+ ".dll").getAbsolutePath());
	}

	private static void loadLinuxLibs() {
	}

}