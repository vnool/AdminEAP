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
@Model(id = "videos", name = "培训视频", parentMenu="TOOL")
@Table(name = "edu_videos")
public class Videos extends BaseEntity {

	// @Id
	// @Column(name = "id",columnDefinition = "BIGINT default 0")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String vid;
	
	@Header(name = "标题")
	@Column(name = "title")
	public String title;
	
	
	@Header(name = "摘要") 
	@Column(name = "description")
	public String description;

	@Header(name = "内容", tagType = "doc" , width=150)
	@Column(name = "content", length = 1024)
	public String content;

	@Header(name = "缩略图", tagType = "imageurl")
	@Column(name = "cover")
	public String cover;
	
	@Header(name = "视频地址", tagType = "videourl")
	@Column(name = "mp4_url")
	public String mp4_url;
	

	@Header(name = "更新时间") 
	@CreationTimestamp
	public Date ptime;

	@Header(name = "来源") // 作者
	public String videosource;

	@Header(name = "主题") 
	@Column(name = "topicName")
	public String topicName;
	
	//topicDesc
	@Header(name = "头像") 
	@Column(name = "topicImg")
	public String topicImg;

	 @Header(name = "分类", tagType = "radio",condition="eq")
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "scope",referencedColumnName="code")
	 private Dict scope;
	 
	 @Header(name = "产品", tagType = "radio",condition="eq")
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "product", referencedColumnName="code")
	 private Dict product;
	 
	 
   //播放次数
	public Integer playCount;
	
	@Header(name = "赞")
	@Column(name = "votecount", columnDefinition = "BIGINT default 0")
	private Integer votecount;

	@Header(name = "评论数")
	@Column(name = "replyCount", columnDefinition = "BIGINT default 0")
	private Integer replyCount;
	
	public String replyid;
	
	
	 public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public String getVideosource() {
		return videosource;
	}

	public void setVideosource(String videosource) {
		this.videosource = videosource;
	}

	public Integer getVotecount() {
		return votecount;
	}

	public void setVotecount(Integer votecount) {
		this.votecount = votecount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getReplyid() {
		return replyid;
	}

	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getMp4_url() {
		return mp4_url;
	}

	public void setMp4_url(String mp4_url) {
		this.mp4_url = mp4_url;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicImg() {
		return topicImg;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Dict getScope() {
		return scope;
	}

	public void setScope(Dict scope) {
		this.scope = scope;
	}

	public Dict getProduct() {
		return product;
	}

	public void setProduct(Dict product) {
		this.product = product;
	}
	 
 
}
