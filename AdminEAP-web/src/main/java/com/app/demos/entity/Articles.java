package com.app.demos.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.cnpc.framework.base.entity.Dict;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by billJiang on 2017/2/10. e-mail:jrn1012@petrochina.com.cn
 * qq:475572229
 */
@Entity
@Table(name = "tbl_articles")
public class Articles extends BaseEntity {

	@Id
	@Column(name = "id")
	private long id;

	@Header(name = "标题")
	@Column(name = "title")
	private String title;

	@Header(name = "摘要")
	@Column(name = "digest")
	private String digest;

	@Header(name = "缩略图")
	@Column(name = "imgsrc")
	private String imgsrc;

	@Header(name = "更新时间")
	@Column(name = "lmodify")
	private Date lmodify;

	@Header(name = "来源") //作者
	@Column(name = "source")
	private String source;

	@Header(name = "分类")
	@Column(name = "boardid") // 分类？
	private String boardid;

	@Header(name = "赞")
	@Column(name = "votecount")
	private long votecount;

	@Header(name = "评论数")
	@Column(name = "replyCount")
	private long replyCount;
	
	

	// "url_3w": "http://news.163.com/18/0525/11/DILAJBD1000187VE.html",
	// "url": "http://3g.163.com/news/18/0525/11/DILAJBD1000187VE.html",
	// priority: 20

	/*
	 *@Header(name = "民族")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dict_nation")
	private Dict nation;

	@Header(name = "学历", joinClass = Dict.class, dataSource = "EDUCATION")
	@Column(name = "education")
	private String education;

	@Header(name = "工资")
	@Column(name = "salary")
	private Double salary;

	@Header(name = "所在组织")
	@Column(name = "department")
	private String department;

	@Header(name = "邮箱")
	@Column(name = "email", length = 30)
	private String email;
 

	@Header(name = "备注")
	@Column(name = "remark", length = 1000)
	private String remark;

	@Header(name = "是否启用")
	@Column(name = "enable")
	private Boolean enable;
*/
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public String getEmail() {
	// return email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// public String getCode() {
	// return code;
	// }
	//
	// public void setCode(String code) {
	// this.code = code;
	// }
	//
	// public String getGender() {
	// return gender;
	// }
	//
	// public void setGender(String gender) {
	// this.gender = gender;
	// }
	//
	// public Date getBirthday() {
	// return birthday;
	// }
	//
	// public void setBirthday(Date birthday) {
	// this.birthday = birthday;
	// }
	//
	public Dict getNation() {
		return nation;
	}

	public void setNation(Dict nation) {
		this.nation = nation;
	}
	//
	// public String getEducation() {
	// return education;
	// }
	//
	// public void setEducation(String education) {
	// this.education = education;
	// }
	//
	// public Double getSalary() {
	// return salary;
	// }
	//
	// public void setSalary(Double salary) {
	// this.salary = salary;
	// }
	//
	// public String getDepartment() {
	// return department;
	// }
	//
	// public void setDepartment(String department) {
	// this.department = department;
	// }
	//
	// public String getRemark() {
	// return remark;
	// }
	//
	// public void setRemark(String remark) {
	// this.remark = remark;
	// }
	//
	// public Boolean getEnable() {
	// return enable;
	// }
	//
	// public void setEnable(Boolean enable) {
	// this.enable = enable;
	// }
}
