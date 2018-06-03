package com.cnpc.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({  ElementType.TYPE})
// 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented
// 说明该注解将被包含在javadoc中
public @interface Model {

    /**
     * 模型名称
     * 
     * @return
     */
    String name() default "";

    /**
     * 业务ID
     */
    String id() default "";

     /*父菜单的id
      * */
	String parentMenu() default "";
    
	
	/** 页面类型  tab, dialog,  page */
	String curdShowType() default "";

	/*list,addUpdate,add, update, show */
	String pages() default "list,addUpdate";
  


}
