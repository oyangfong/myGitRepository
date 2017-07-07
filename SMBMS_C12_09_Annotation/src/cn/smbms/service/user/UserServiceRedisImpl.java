package cn.smbms.service.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.tools.PageSupport;

@Service
public class UserServiceRedisImpl implements UserService {
	
	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Resource
	private UserMapper userMapper;

	public User login(User user) {
		User ret = null;
		try {
			ret = userMapper.getUserByUserCode(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return userMapper.selectAll();
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userMapper.add(user);
	}

	@Override
	public boolean checkUserExist(String userCode) {

		try {
			int ret = userMapper.checkUserExist(userCode);
			if (ret > 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public User selectById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectById(id);
	}

	@Override
	public boolean delete(int id) {
		try {
			userMapper.delete(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}

	@Override
	public boolean update(User user) {
		try {
			userMapper.update(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<User> getUserListByPage(PageSupport page) {
		int from =(page.getCurrentPageNo()-1)*page.getPageSize();
		page.setTotalCount(userMapper.getTotalCount());
		return userMapper.getUserListByPage(from, page.getPageSize());
	}

	@Override
	public boolean update2(User user) {
		try {
			userMapper.update2(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}

}
