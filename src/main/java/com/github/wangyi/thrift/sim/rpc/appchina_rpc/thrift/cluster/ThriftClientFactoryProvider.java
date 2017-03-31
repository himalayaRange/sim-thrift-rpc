package com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.cluster;

import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.beans.BeanUtils;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.cluster.pool.ClientFactory;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.cluster.pool.ClientFactoryProvider;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.client.ThriftClientConfig;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.support.Request;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.support.Response;

/**
 * 
 * ========================================================
 * 日 期：@2016-12-15
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明：便于配置
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */
public class ThriftClientFactoryProvider implements ClientFactoryProvider<Request, Response> {

	private List<String> hostPorts =new ArrayList<String>();
	private ThriftClientConfig config=new ThriftClientConfig();
	private ThriftClientFactoryProvider(){ }

	@Override
	public List<ClientFactory<Request, Response>> getFactories() {
		List<ClientFactory<Request, Response>> factoryList=new ArrayList<ClientFactory<Request, Response>>(hostPorts.size());
		for(int i=0;i<hostPorts.size();i++){
			String hostport=hostPorts.get(i);
			if(hostport==null||"".equals(hostport.trim())){
				throw new IllegalArgumentException("hostPort format error, the correct like:127.0.0.1:8080");
			}
			hostport=hostport.trim();
			String[] host_port=hostport.split(":");
			if(host_port.length!=2){
				throw new IllegalArgumentException("hostPort format error, the correct like:127.0.0.1:8080");
			}
			ThriftClientConfig newconfig=new ThriftClientConfig();
			BeanUtils.copyProperties(config, newconfig);
			newconfig.setHost(host_port[0]);
			newconfig.setPort(Integer.parseInt(host_port[1]));
			factoryList.add(new ThriftClientFactory(newconfig));
		}
		return factoryList;
	}
	public List<String> getHostPorts() {
		return hostPorts;
	}

	public void setHostPorts(List<String> hostPorts) {
		this.hostPorts = hostPorts;
	}

	public int getTimeout() {
		return config.getTimeout();
	}

	public void setTimeout(int timeout) {
		config.setTimeout(timeout);
	}

	public int getConnectionTimeout() {
		return config.getConnectionTimeout();
	}

	public void setConnectionTimeout(int connectionTimeout) {
		config.setConnectionTimeout(connectionTimeout);
	}

	public boolean isFramed() {
		return config.isFramed();
	}

	public void setFramed(boolean framed) {
		config.setFramed(framed);
	}

	public TProtocolFactory getProtocolFactory() {
		return config.getProtocolFactory();
	}

	public void setProtocolFactory(TProtocolFactory protocolFactory) {
		config.setProtocolFactory(protocolFactory);
	}

	public String getFrom() {
		return config.getFrom();
	}

	public void setFrom(String from) {
		config.setFrom(from);
	}

	public String getToken() {
		return config.getToken();
	}

	public void setToken(String token) {
		config.setToken(token);
	}

	@Override
	public String toString() {
		return "[hostPorts=" + hostPorts + ", config=" + config + "]";
	}

}