package com.app.demos.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.app.demos.entity.Videos;

/**
* 视频教程管理控制器
* @author jrn
* 2018-05-29 23:08:51由代码生成器自动生成
*/
@Controller
@RequestMapping("/videos")
public class VideosController {

    @Resource
    private BaseService baseService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "demos/videos_list";
    }

    @RefreshCSRFToken
    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/videos_edit";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/videos_detail";
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Videos get(@PathVariable("id") String id){
        return baseService.get(Videos.class, id);
    }

    @VerifyCSRFToken
    @RequestMapping(value="/save")
    @ResponseBody
    public Result save(Videos videos){
        if(StrUtil.isEmpty(videos.getId())){
            baseService.save(videos);
        }
        else{
            videos.setUpdateDateTime(new Date());
            baseService.update(videos);
        }
        return new Result(true);
    }



    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        Videos videos=this.get(id);
        try{
            baseService.delete(videos);
            return new Result();
        }
        catch(Exception e){
            return new Result(false,"该数据已经被引用，不可删除");
        }
    }



}
