package com.inventory;

import com.inventory.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryApplicationTests {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Test
	void contextLoads() {
	}

}
