package com.eprotea.icrengine;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class UVCheckerTest {
	public static void main(String[] args) {
		new UVCheckerTest().testCheckValid();
	}

	// @Test
	public void testCheckValid() {
		File currentDir = new File("/media/thienlong/linux/UV Cheque/Altered UV Images");
		for (File file : currentDir.listFiles()) {
			if (!file.getName().endsWith(".TIF")) {
				continue;
			}
			try {
				byte[] chqImg = ICREngineTest.loadContentFile(file
						.getAbsolutePath());
				boolean result = UVChecker.checkValid(chqImg);
				System.out.println(file.getName().toString() + "\t" + result);
			} catch (Exception e) {
				System.out.println("can't check " + file.getName());
				e.printStackTrace();
			}
		}
	}

}
