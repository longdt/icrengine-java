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
			File currentDir = new File("D:\\s\\ValidChq");
			for (File file : currentDir.listFiles()) {
				if (!file.getName().endsWith(".jpeg")) {
					continue;
				}
				byte[] chqImg = loadContentFile(file.getAbsolutePath());
				Result result = engine.predictCA(chqImg);
				System.out.format("%s %,.2f %,.2f\n",file.getName().toString(), result.getAmount(), result.getConfidence());
				
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
