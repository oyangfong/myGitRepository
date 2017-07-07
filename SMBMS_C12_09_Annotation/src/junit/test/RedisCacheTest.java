package junit.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.util.RedisCacheUtil;
/*
 * 二级缓存测试
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis-redis.xml"}) */
public class RedisCacheTest {
	private static final Logger logger = LoggerFactory
			.getLogger(RedisCacheTest.class);

	@Resource
	private UserService service;

	@Before
	public void before(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-mybatis-redis.xml");
		service=ctx.getBean("userService",UserService.class);	
	}
	@Test
	public void test() {
	
		List<User> list = service.selectAll();
		logger.info(list.get(2).getUserCode());
		
		List<User> list2 = service.selectAll();
		logger.info(list2.get(2).getUserCode());
	}

}
