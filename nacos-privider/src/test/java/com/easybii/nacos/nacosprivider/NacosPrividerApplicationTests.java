package com.easybii.nacos.nacosprivider;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class NacosPrividerApplicationTests {

	@Test
	void contextLoads() {
//		String s = WebImage.webImage();
//		Map map = GsonUtils.fromJson(s, Map.class);
//		List<LinkedTreeMap> words_result = (List<LinkedTreeMap>) map.get("words_result");
//		for (LinkedTreeMap linkedTreeMap : words_result) {
//
//			Object words = linkedTreeMap.get("words");
//			String s1 = words.toString();
//			System.out.println(s1);
//		}
	}

	@Test
	void testFileDownload() throws IOException, InterruptedException {

		Date date = new Date();
		Thread.sleep(50000);
		Date date1 = new Date();
		System.out.println(date.before(date1));

	}

}
