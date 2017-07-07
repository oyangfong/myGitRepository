package cn.smbms.service.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;
import cn.smbms.tools.PageSupport;

public interface UserService {
  public User login(User user);
  public List<User> selectAll();
  public int add(User user);
  public boolean delete(int id);
  public boolean update(User user);
  public boolean update2(User user);
  public boolean checkUserExist(String userCode);
  
  public User selectById(int id);
  public List<User> getUserListByPage(PageSupport page);
}
