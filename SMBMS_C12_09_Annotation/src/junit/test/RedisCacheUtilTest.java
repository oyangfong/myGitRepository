package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.smbms.util.RedisCacheUtil;

public class RedisCacheUtilTest {

	@Test
	public void test() {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-mybatis-redis.xml");
		RedisCacheUtil redisCacheUtil=ctx.getBean("redisCacheUtil",RedisCacheUtil.class);
		redisCacheUtil.setCacheObject("username", "hahahahah");
		String ret=redisCacheUtil.getCacheObject("username");
		System.out.println(ret);
	}

}
