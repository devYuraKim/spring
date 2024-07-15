package com.devyurakim.devschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@EnableJpaRepositories, @EntityScan 없어도 잘 실행되는데, configuration을 explicit하고 clear하게 모여주도록 명시한다.
* 특히 다른 패키지에 있을 때에는 이와 같은 명시가 필요하다*/
//@EnableJpaRepositories("com.devyurakim.devschool.repository")
//@EntityScan("com.devyurakim.devschool.model")
public class DevschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevschoolApplication.class, args);
	}

}
