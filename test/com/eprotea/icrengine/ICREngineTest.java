package com.eprotea.icrengine;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class ICREngineTest {

	public static void main(String[] args) throws IOException {
		new ICREngineTest().testPredictCA();
	}

	@Test
	public void testPredictCA() throws IOException {
		ICREngine.loadModels("models");
		ICREngine engine = new ICREngine();
		try {
			File currentDir = new File(".");
			for (String file : currentDir.list()) {
				if (!file.endsWith(".jpeg")) {
					continue;
				}
				byte[] chqImg = loadContentFile(file);
				Result result = engine.predictCA(chqImg);
				System.out.println(result.getAmount());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] loadContentFile(String file) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) >= 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return out.toByteArray();
	}

}
