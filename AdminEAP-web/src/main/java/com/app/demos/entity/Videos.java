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
@Model(id = "videos", name = "培训视频", parentMenu="TOOL")
@Table(name = "edu_videos")
public class Videos extends BaseEntity {

	// @Id
	// @Column(name = "id",columnDefinition = "BIGINT default 0")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer id;

	private String vid;
	
	@Header(name = "标题")
	@Column(name = "title")
	public String title;
	
	
	@Header(name = "摘要") 
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
	public String topicImg;

   //播放次数
	public Integer playCount;
	
	@Header(name = "赞")
	@Column(name = "votecount", columnDefinition = "BIGINT default 0")
	private Integer votecount;

	@Header(name = "评论数")
	@Column(name = "replyCount", columnDefinition = "BIGINT default 0")
	private Integer replyCount;
	
	private String replyid;
 
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
}
