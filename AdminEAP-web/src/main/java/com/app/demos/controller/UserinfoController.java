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
import com.app.demos.entity.UserInfo;

/**
* 测试2管理控制器
* @author jrn
* 2018-05-24 14:46:31由代码生成器自动生成
*/
@Controller
@RequestMapping("/userinfo")
public class UserinfoController {

    @Resource
    private BaseService baseService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
    	System.out.print("\nxxxxxxxx\n");
        return "demos/generator_list";
    }

    @RefreshCSRFToken
    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/userinfo_edit";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "demos/userinfo_detail";
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public UserInfo get(@PathVariable("id") String id){
        return baseService.get(UserInfo.class, id);
    }

    @VerifyCSRFToken
    @RequestMapping(value="/save")
    @ResponseBody
    public Result save(UserInfo userinfo){
        if(StrUtil.isEmpty(userinfo.getId())){
            baseService.save(userinfo);
        }
        else{
            userinfo.setUpdateDateTime(new Date());
            baseService.update(userinfo);
        }
        return new Result(true);
    }



    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        UserInfo userinfo=this.get(id);
        try{
            baseService.delete(userinfo);
            return new Result();
        }
        catch(Exception e){
            return new Result(false,"该数据已经被引用，不可删除");
        }
    }



}
