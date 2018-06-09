package com.app.demos.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.annotation.Model;
import com.cnpc.framework.base.entity.BaseEntity;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.base.entity.Org;

import org.hibernate.annotations.CreationTimestamp;
 

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dingchengliang
 */
@Entity
@Model(id = "papers", name = "试卷", parentMenu = "exambox", curdShowType = "page", pages = "list,addUpdate")
@Table(name = "edu_papers")
public class Papers extends BaseEntity {

	// @Id
	// @Column(name = "id",columnDefinition = "BIGINT default 0")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer id;

	@Header(name = "标题", condition="like" )
	@Column(name = "title")
	private String title;

	@Header(name = "描述", condition="like")
	@Column(name = "digest")
	public String digest;

	@Header(name = "题目",  width = 150)
	@Column(name = "pages", length = 10240)
	public String pages;
	
	@Header(name = "答案",   width = 150)
	@Column(name = "correct", length = 10240)
	public String correct;
	

	@Header(name = "开始时间")
	@Column(name = "starttime") 
	public Date starttime;
	
	@Header(name = "结束时间")
	@Column(name = "deadline") 
	public Date deadline;
	
	@Header(name = "更新时间")
	@Column(name = "modify")
	@CreationTimestamp
	public Date  modify;

	@Header(name = "来源", hidden = true ) // 作者
	@Column(name = "source")
	public String source;

// //代码改成这样，多选  <input type=checkbox data-flag="dictSelector" data-code="scope" data-value="code" .... >
//	@Header(name = "内容分类")
//	@Column(name = "scope") // 分类？
//	private String scope;
//	
//	//代码改成这样，多选  <input type=checkbox data-flag="dictSelector" data-code="product" data-value="code"  ... >
//	@Header(name = "产品")
//	@Column(name = "product")
//	private String product;
	
	
	 // @Header(name = "分类", tagType = "radio",condition="eq")
	 // @ManyToOne(fetch = FetchType.LAZY)
	 // @JoinColumn(name = "scope",referencedColumnName="code")
	 //private Dict scope;
	 
	 @Header(name = "产品", tagType = "radio",condition="eq")
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "product", referencedColumnName="code")
	 private Dict product;
	

	@Header(name = "答题人数")
	@Column(name = "answercount", columnDefinition = "BIGINT default 0")
	private Integer answercount;
 
    @Header(name = "允许答题次数")
	@Column(name = "allowcount", columnDefinition = "BIGINT default 0")
	private Integer allowcount;
	

	@Header(name = "随机题序")
	@Column(name = "randomindex" )
	private Boolean randomindex;


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDigest() {
		return digest;
	}


	public void setDigest(String digest) {
		this.digest = digest;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public String getCorrect() {
		return correct;
	}


	public void setCorrect(String correct) {
		this.correct = correct;
	}


	public Date getStarttime() {
		return starttime;
	}


	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}


	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public Date getModify() {
		return modify;
	}


	public void setModify(Date modify) {
		this.modify = modify;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public Dict getProduct() {
		return product;
	}


	public void setProduct(Dict product) {
		this.product = product;
	}


	public Integer getAnswercount() {
		return answercount;
	}


	public void setAnswercount(Integer answercount) {
		this.answercount = answercount;
	}


	public Integer getAllowcount() {
		return allowcount;
	}


	public void setAllowcount(Integer allowcount) {
		this.allowcount = allowcount;
	}


	public Boolean getRandomindex() {
		return randomindex;
	}


	public void setRandomindex(Boolean randomindex) {
		this.randomindex = randomindex;
	}
	 
	 
}
