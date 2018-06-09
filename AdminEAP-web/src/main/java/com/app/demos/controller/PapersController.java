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
import com.app.demos.entity.Papers;

/**
* 试卷管理控制器
* @author jrn
* 2018-06-09 15:14:56由代码生成器自动生成
*/
@Controller
@RequestMapping("/papers")
public class PapersController {

    @Resource
    private BaseService baseService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "demos/papers_list";
    }

    @RefreshCSRFToken
    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/papers_edit";
    }

    @RefreshCSRFToken
    @RequestMapping(value="/editpaper",method = RequestMethod.GET)
    public String editpaper(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "../../resources/surveyeditor/example/index";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/papers_detail";
    }

    @RequestMapping(value="/get/{id}")
    @ResponseBody
    public Papers get(@PathVariable("id") String id){
        return baseService.get(Papers.class, id);
    }

    @VerifyCSRFToken
    @RequestMapping(value="/save")
    @ResponseBody
    public Result save(String obj){
        Papers papers= JSON.parseObject(obj,Papers.class);
        papers.setProduct(baseService.get(Dict.class,papers.getProduct().getId()));
        if(StrUtil.isEmpty(papers.getId())){
            baseService.save(papers);
        }
        else{
            papers.setUpdateDateTime(new Date());
            baseService.update(papers);
        }
        return new Result(true);
    }



    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        Papers papers=this.get(id);
        try{
            baseService.delete(papers);
            return new Result();
        }
        catch(Exception e){
            return new Result(false,"该数据已经被引用，不可删除");
        }
    }



}
