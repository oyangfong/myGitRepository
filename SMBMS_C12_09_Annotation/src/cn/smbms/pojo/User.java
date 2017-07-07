package cn.smbms.pojo;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.annotation.JSONField;
/*
 * 1、 在缓存的过程中，需要把对象序列化转化为字节数组存入，那么需要缓存的Bean类就需要实现Serializable接口。
 * 2、 传输的数据有父类的话，那么父类也需要实现Serializable接口。
 * 3、 在使用缓存前,需要打开Redis服务。
 */
public class User implements Serializable{
	private Integer id; // id
	@NotEmpty(message="用户编码不能为空")
	private String userCode; // 用户编码
	@NotEmpty(message="用户名称不能为空")
	private String userName; // 用户名称
	@NotNull(message="密码不能为空")
	@Length(min=6,max=10,message="用户密码长度为6-10")
	private String userPassword; // 用户密码
	private Integer gender; // 性别
	
	//注解方式 --解决JSON数据传递的日期格式问题
	@JSONField(format="yyyy-MM-dd")
	private Date birthday; // 出生日期
	
	private String phone; // 电话
	private String address; // 地址
	private Integer userRole; // 用户角色
	private Integer createdBy; // 创建者
	private Date creationDate; // 创建时间
	private Integer modifyBy; // 更新者
	private Date modifyDate; // 更新时间

	private Integer age;// 年龄
    private String idPicPath;//上传证件照片  **-ch11 (2017-6-20)-**
    private String workIdPicPath;//上传工作证件照片  **-ch11 (2017-6-29)-**
	private String userRoleName; // 用户角色名称

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public Integer getAge() {
		if (birthday != null) {
			Calendar calendar = Calendar.getInstance();
			int yearNow = calendar.get(Calendar.YEAR);
			calendar.setTime(birthday);
			int yearBirth = calendar.get(Calendar.YEAR);
			int age = yearNow - yearBirth;
			return age;
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getIdPicPath() {
		return idPicPath;
	}

	public void setIdPicPath(String idPicPath) {
		this.idPicPath = idPicPath;
	}

	public String getWorkIdPicPath() {
		return workIdPicPath;
	}

	public void setWorkIdPicPath(String workIdPicPath) {
		this.workIdPicPath = workIdPicPath;
	}
}
