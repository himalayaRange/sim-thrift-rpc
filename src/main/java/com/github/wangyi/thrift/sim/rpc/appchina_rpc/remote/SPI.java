package com.github.wangyi.thrift.sim.rpc.appchina_rpc.remote;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * ========================================================
 * 日 期：@2016-12-12
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明： 服务声明
 * 		》》 接口上标明此类，表示接口的所有方法可提供服务=========接口上
 * 		》》方法上标明此，表示该方法可提供服务=================方法上
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SPI {

	/**
	 * 标注到类上，表示服务的绝对路径
	 * <br>
	 * 标注到方法上，表示相对路径
	 * @return
	 */
	public String value() default ""; 
	
	
	/**
	 * 黑名单列表
	 * <br>
	 * 不加的话表示对所有的IP都可正常访问
	 * @return
	 */
	public String blackList()  default ""; 
	
	
}
