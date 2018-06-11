package com.app.demos.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.annotation.Model;
import com.cnpc.framework.base.entity.BaseEntity;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.base.entity.Org;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dingchengliang
 */
@Entity
@Model(id = "score", name = "考试成绩", parentMenu = "exambox", curdShowType = "page", pages = "list,addUpdate")
@Table(name = "edu_scores")
public class Score extends BaseEntity {

	private static final long serialVersionUID = 5569761987303812250L;
	
	@Header(name = "姓名", condition="like" )
	@Column(name = "name")
	private String name;

	@Header(name = "用户id" )
	@Column(name = "uid")
	public String uid;

	@Header(name = "成绩",  width = 150)
	@Column(name = "score" )
	public Integer score;
 
	
	@Header(name = "试卷" ,condition="eq") 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paper", referencedColumnName="id")
	public Papers paper;
	
	
	@Header(name = "回答" )
	@Column(name = "answer")
	public String answer;

	@Header(name = "考试时间")
	@Column(name = "createtime") 
	public Date createtime;
	
 

// //代码改成这样，多选  <input type=checkbox data-flag="dictSelector" data-code="scope" data-value="code" .... >
//	@Header(name = "内容分类")
//	@Column(name = "scope") // 分类？
//	private String scope;
//	
//	//代码改成这样，多选  <input type=checkbox data-flag="dictSelector" data-code="product" data-value="code"  ... >
//	@Header(name = "产品")
//	@Column(name = "product")
//	private String product;
 
	 
	 @Header(name = "产品", tagType = "radio",condition="eq")
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "product", referencedColumnName="code")
	 private Dict product;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
 
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}



	public Dict getProduct() {
		return product;
	}



	public void setProduct(Dict product) {
		this.product = product;
	}



	public Papers getPaper() {
		return paper;
	}



	public void setPaper(Papers paper) {
		this.paper = paper;
	}


	public Integer getScore() {
		return score;
	}
 
	public void setScore(Integer score) {
		this.score = score;
	}
 
 
	 
	 
}
