package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.smbms.pojo.User;


@Repository
public interface UserMapper {
   public List<User> selectAll();
   public List<User> getUserList();
   //ch03
   public List<User> getUserListByPage(@Param("from") int from,@Param("pageSize") int pageSize);
  // @Cacheable("userCache")
   public User selectById(int id);
   public List<User> selectByUsername(String userName);
   public List<User> selectByUsername2(User user);
   public List<User> selectByMap(Map userMap);
   public List<User> selectByParam(@Param("userName") String userName,@Param("userRole") Integer userRole);
   //ch03
   public List<User> selectByParam2(@Param("userName") String userName,@Param("userRole") Integer userRole);
   //ch03
   public List<User> selectByParam3(@Param("userName") String userName,@Param("userRole") Integer userRole);
   public int add(User user);
   public void update(User user);
   public void delete(int id);
   
   //ch03
   //更新用户多个非空的值
   public void update2(User user);
   public List<User> GetUserbyConditionMap_foreach_map(Map<String,Object> conditionMap);
   
   public List<User> getUserListByRoleId(int roleId);
   
   //ch12
   public User getUserByUserCode(User user);
   
   public int checkUserExist(@Param ("userCode") String userCode);
   
   public int getTotalCount();
}
