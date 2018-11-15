package com.zane.scaffold.entity.mp;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("tbl_employee")
public class Mp extends Model<Mp> {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID, value = "id")
	private String id;

	@TableField(value = "last_name")
	private String last_name;

	@TableField(value = "email")
	private String email;

	@TableField(value = "gender")
	private String gender;

	@TableField(value = "age")
	private String age;

	@TableField(value = "delete_flag")
	private String delete_flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	@Override
	public String toString() {
		return "Mp [id=" + id + ", last_name=" + last_name + ", email=" + email + ", gender=" + gender + ", age=" + age
				+ ", delete_flag=" + delete_flag + "]";
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
