package hello.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class JdbcApplicationTests {

	@Autowired
	private ApplicationContext ac;

	@Test
	@DisplayName("등록된 모든 Bean 조회")
	void contextLoads() {
		if (ac != null) {
			String[] beans = ac.getBeanDefinitionNames();

			for (String bean : beans) {
				System.out.println("bean = " + bean);
			}
		}
	}

}
