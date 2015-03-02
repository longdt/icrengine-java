package com.eprotea.icrengine;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class ICREngineTest {

	@Test
	public void testPredictCA() throws IOException {
		ICREngine.loadModels("/media/thienlong/data/workspace/cpp-linux/extracticr/models");
		ICREngine engine = new ICREngine();
		Path start = FileSystems.getDefault().getPath("/home/thienlong/cheque/500 Cheques/ValidChq/");
		Files.walk(start).filter(p -> !p.toFile().isDirectory()).forEach(p -> {
			try {
				byte[] chqImg = loadContentFile(p.toString());
				Result result = engine.predictCA(chqImg);
				System.out.format("%s %,.2f %,.2f\n",p.getFileName().toString(), result.getAmount(), result.getConfidence());
//				System.out.println(p.getFileName() + " " + result.getAmount());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		
	}

	private byte[] loadContentFile(String file) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) >= 0) {
				out.write(buf, 0, len);
			}
		}
		return out.toByteArray();
	}

}
