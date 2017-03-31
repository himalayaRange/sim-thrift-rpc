package com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api;

import com.github.wangyi.thrift.sim.rpc.appchina_rpc.remote.SPI;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.model.Order;


// @SPI 如果在类上面标注了@SPI,则整个接口将以默认的路径进行服务发布
//@SPI(blackList="192.168.223.1,192.168.1.176")
public interface OrderService {

	@SPI("INSERT-ORDER")
	public Order insert(Order order);

	@SPI(value="DELETE-ORDER",blackList="192.168.223.1,192.168.1.171")
	public String delete(String stid);
	
	@SPI("UPDATE-ORDER")
	public Order update(Order order);
	
	@SPI("QUERY-ORDER")
	public Order query(String stid);

}
