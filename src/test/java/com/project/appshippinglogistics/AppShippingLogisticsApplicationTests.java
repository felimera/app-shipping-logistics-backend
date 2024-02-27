package com.project.appshippinglogistics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppShippingLogisticsApplicationTests {

	@Test
	void contextLoads() {
		AppShippingLogisticsApplication myClass=new AppShippingLogisticsApplication();
		Assertions.assertThat(myClass);
	}

}
