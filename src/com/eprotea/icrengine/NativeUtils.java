package com.eprotea.icrengine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NativeUtils {
	private static final String os = System.getProperty("os.name")
			.toLowerCase();
	private static final boolean windows = os.startsWith("windows");
	private static final String osType = windows ? "windows" : "linux";
	private static final String arch = System.getProperty("os.arch");
	private static final Map<String, String[]> WINDOWS_DEPS;
	private static final Map<String, String[]> LINUX_DEPS;

	static {
		WINDOWS_DEPS = new HashMap<String, String[]>();
		WINDOWS_DEPS.put("uvanalyzer", new String[] {
				"opencv/opencv_core2410.dll", "opencv/opencv_highgui2410.dll",
				"opencv/opencv_imgproc2410.dll", "uvanalyzer.dll" });
		WINDOWS_DEPS.put("icrengine",
				new String[] { "opencv/opencv_core2410.dll",
						"opencv/opencv_highgui2410.dll",
						"opencv/opencv_imgproc2410.dll", "tbb/tbb.dll",
						"icrengine.dll" });

		LINUX_DEPS = new HashMap<String, String[]>();
		LINUX_DEPS.put("uvanalyzer", new String[] { "libuvanalyzer.so" });
		LINUX_DEPS.put("icrengine", new String[] { "libicrengine.so" });
	}

	public static void loadLibraries(String module) {
		Map<String, String[]> deps = windows ? WINDOWS_DEPS : LINUX_DEPS;
		loadLibraries(deps.get(module));
	}

	private static void loadLibraries(String[] libs) {
		for (String lib : libs) {
			System.load(new File("native_libs/" + osType + "/" + arch + "/"
					+ lib).getAbsolutePath());
		}
	}
}