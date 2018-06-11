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
import com.cnpc.framework.util.SecurityUtil;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.app.demos.entity.Papers;
import com.app.demos.entity.Score;
import org.apache.shiro.SecurityUtils;
import com.cnpc.framework.base.entity.User;


/**
* 考试成绩管理控制器
* @author jrn
* 2018-06-09 15:18:31由代码生成器自动生成
*/
@Controller
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private BaseService baseService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "demos/score_list";
    }

    @RefreshCSRFToken
    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/score_edit";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/score_detail";
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Score get(@PathVariable("id") String id){
        return baseService.get(Score.class, id);
    }

    @VerifyCSRFToken
    @RequestMapping(value="/save")
    @ResponseBody
    public Result save(String obj){
        Score score= JSON.parseObject(obj,Score.class);
        score.setProduct(baseService.get(Dict.class,score.getProduct().getId()));
        if(StrUtil.isEmpty(score.getId())){
            baseService.save(score);
        }
        else{
            score.setUpdateDateTime(new Date());
            baseService.update(score);
        }
        return new Result(true);
    } 
    @RequestMapping(value="/savescore")
    @ResponseBody
    public Result savescore(String obj){ 
        Score score= JSON.parseObject(obj, Score.class);
        User user = SecurityUtil.getUser();   
       // user = baseService.get(Papers.class, score.getPaper().getId()); 
       // Papers paper = baseService.get(Papers.class, score.getPaper().getId()); 
        Papers paper = score.getPaper();
        score.setUid(user.getId() );
        score.setName(user.getName()); 
        score.setProduct(baseService.get(Dict.class, paper.getProduct().getId()));
        score.setScore(1);
        if(StrUtil.isEmpty(score.getId())){
            baseService.save(score);
        }
        else{
            score.setUpdateDateTime(new Date());
            baseService.update(score);
        }
        return new Result(true);
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        Score score=this.get(id);
        try{
            baseService.delete(score);
            return new Result();
        }
        catch(Exception e){
            return new Result(false,"该数据已经被引用，不可删除");
        }
    }



}
