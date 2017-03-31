package com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.server;

import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TTupleProtocol;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.server.GenericServer;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.server.ServerProcessor;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.ThriftGenericConfig;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.support.Request;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.support.Response;
/**
 * 
 * ========================================================
 * 日 期：@2016-12-15
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明：封装Server通用配置
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */
public  abstract class ConfigurableThriftServer extends GenericServer<Request, Response> {
	
	protected int port=ThriftGenericConfig.DEFAULT_PORT;
	
	protected int workerThreads=ThriftGenericConfig.WORKERTHREADS;
	
	protected int clientTimeout=ThriftGenericConfig.CLIENTTIMEOUT;
	
	protected TProtocolFactory protocolFactory=new TTupleProtocol.Factory();
	
	protected boolean security = ThriftGenericConfig.SECURITY;
	
	protected Map<String,String> allowedFromTokens=new HashMap<String,String>();
	
	protected int stopTimeoutVal=ThriftGenericConfig.STOPTIMEOUTVAL;
	
	public ConfigurableThriftServer() {
		super();
	}
	
	public ConfigurableThriftServer(ServerProcessor<Request, Response> processor){
		super(processor);
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getWorkerThreads() {
		return workerThreads;
	}
	public void setWorkerThreads(int workerThreads) {
		this.workerThreads = workerThreads;
	}
	public TProtocolFactory getProtocolFactory() {
		return protocolFactory;
	}
	public void setProtocolFactory(TProtocolFactory protocolFactory) {
		this.protocolFactory = protocolFactory;
	}
	public boolean isSecurity() {
		return security;
	}
	public void setSecurity(boolean security) {
		this.security = security;
	}
	public Map<String, String> getAllowedFromTokens() {
		return allowedFromTokens;
	}
	public void setAllowedFromTokens(Map<String, String> allowedFromTokens) {
		this.allowedFromTokens = allowedFromTokens;
	}
	
	public int getClientTimeout() {
		return clientTimeout;
	}

	public void setClientTimeout(int clientTimeout) {
		this.clientTimeout = clientTimeout;
	}
	
	public int getStopTimeoutVal() {
		return stopTimeoutVal;
	}

	public void setStopTimeoutVal(int stopTimeoutVal) {
		this.stopTimeoutVal = stopTimeoutVal;
	}

	@Override
	public String toString() {
		return "[port=" + port + ", workerThreads=" + workerThreads + ", protocolFactory="
				+ protocolFactory.getClass() + ", clientTimeout=" + clientTimeout + ", stopTimeoutVal=" + stopTimeoutVal
				+ ", security=" + security + ", allowedFromTokens=" + allowedFromTokens + "]";
	}
 

}
