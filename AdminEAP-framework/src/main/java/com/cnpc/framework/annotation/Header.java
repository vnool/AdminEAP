package com.cnpc.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
// 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented
// 说明该注解将被包含在javadoc中
public @interface Header {

    /**
     * 字段说明
     * 
     * @return
     */
    String name() default "";

    /**
     * 格式
     */
    String format() default "";

    /**
     * 关联类
     */
    Class joinClass() default Exception.class;

    /**
     * 数据来源
     */
    String dataSource() default "";

    /*编辑框控件类型, 可以支持的有：
      'text', 'hidden', 'number', 'textarea', 'datepicker', 'icheck-radio', 'radio', 'checkbox',
      'icheck-checkbox', 'dictSelector', 'urlSelector', 'normalSelector', 'orgSelector'
      image, doc
      video
    ;*/
	String tagType() default "";

	/*是否在列表中显示*/
	boolean hidden() default false;

	int width() default 0;

	/** 查询条件操作 */
	String condition() default "";



}
