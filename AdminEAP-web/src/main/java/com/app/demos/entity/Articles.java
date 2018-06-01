package com.app.demos.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.annotation.Model;
import com.cnpc.framework.base.entity.BaseEntity;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.base.entity.Org;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dingchengliang
 */
@Entity 
@Model(id = "articles", name = "百问百答", parentMenu="TOOL")
@Table(name = "edu_articles")
public class Articles extends BaseEntity {

	// @Id
	// @Column(name = "id",columnDefinition = "BIGINT default 0")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer id;

	@Header(name = "标题")
	@Column(name = "title")
	private String title;
	
	
	@Header(name = "摘要")
	@Column(name = "digest")
	public String digest;

	@Header(name = "内容", tagType = "doc" , width=150)
	@Column(name = "body", length = 1024)
	public String body;

	@Header(name = "缩略图", tagType = "image")
	@Column(name = "imgsrc")
	public String imgsrc;
	
	@Header(name = "更多图片", tagType = "image")
	public String imgextra;
	

	@Header(name = "更新时间")
	@Column(name = "lmodify")
	@CreationTimestamp
	public Date lmodify;

	@Header(name = "来源", hidden=true) // 作者
	@Column(name = "source")
	public String source;

	@Header(name = "分类")
	@Column(name = "boardid") // 分类？
	private String boardid;


	@Header(name = "赞")
	@Column(name = "votecount", columnDefinition = "BIGINT default 0")
	private Integer votecount;

	@Header(name = "评论数")
	@Column(name = "replyCount", columnDefinition = "BIGINT default 0")
	private Integer replyCount;
 
//	@Transient
//	public Date ptime=new Date();
//	@Transient
//	public Date mtime=new Date();
//	
//	Articles(){
//		ptime=new Date();
//		mtime=new Date();
//	}
	/**
	 * @return the digest
	 */
	public String getDigest() {
		return digest;
	}

	/**
	 * @param digest the digest to set
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}

 

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the imgsrc
	 */
	public String getImgsrc() {
		return imgsrc;
	}

	/**
	 * @param imgsrc the imgsrc to set
	 */
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
//
//	/**
//	 * @return the lmodify
//	 */
//	public Date getLmodify() {
//		return lmodify;
//	}
//
//	/**
//	 * @param lmodify the lmodify to set
//	 */
//	public void setLmodify(Date lmodify) {
//		this.lmodify = lmodify;
//	}
//
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the boardid
	 */
	public String getBoardid() {
		return boardid;
	}

	/**
	 * @param boardid the boardid to set
	 */
	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	/**
	 * @return the votecount
	 */
	public Integer getVotecount() {
		return votecount;
	}

	/**
	 * @param votecount the votecount to set
	 */
	public void setVotecount(Integer votecount) {
		this.votecount = votecount;
	}

	/**
	 * @return the replyCount
	 */
	public Integer getReplyCount() {
		return replyCount;
	}

	/**
	 * @param replyCount the replyCount to set
	 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
