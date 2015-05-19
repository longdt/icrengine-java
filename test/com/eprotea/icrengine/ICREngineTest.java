package com.eprotea.icrengine;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class ICREngineTest {

	public static void main(String[] args) throws IOException, ICRException {
		new ICREngineTest().testPredictCA();
	}

	@Test
	public void testPredictCA() throws IOException, ICRException {
		ICREngine.loadModels("models");
		ICREngine engine = new ICREngine(ICREngine.CHEQUE_PH);
		File currentDir = new File("/home/thienlong/PH CHQ EX");
		for (File file : currentDir.listFiles()) {
			 if (!file.getName().endsWith(".TIF")) {
			 continue;
			 }
			try {
				byte[] chqImg = loadContentFile(file.getAbsolutePath());
				Result result = engine.predictCA(chqImg);
				System.out.format("%s %,.2f %f\n",
						file.getName().toString(), result.getAmount(),
						result.getConfidence());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] loadContentFile(String file) throws IOException {
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
