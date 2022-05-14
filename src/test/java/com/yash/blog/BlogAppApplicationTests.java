package com.yash.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yash.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
	}
	
	public void repoTest() {
		String className = this.userRepo.getClass().getName();
		String pacakgeName = this.userRepo.getClass().getPackageName();
		System.out.println(className); 
		System.out.println(pacakgeName);
	}

}
