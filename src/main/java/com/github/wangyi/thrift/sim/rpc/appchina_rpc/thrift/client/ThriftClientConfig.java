package com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.client;

import java.util.concurrent.TimeUnit;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TTupleProtocol;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.ThriftGenericConfig;

/**
 * 
 * ========================================================
 * 日 期：@2016-12-14
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明： Thrift基础配置
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */
public class ThriftClientConfig {

	public String host= ThriftGenericConfig.DEFAULT_HOST;
	
	public int port = ThriftGenericConfig.DEFAULT_PORT;
	
	public int timeout=(int)TimeUnit.MINUTES.toMillis(1);
	
	public int connectionTimeout= (int) TimeUnit.SECONDS.toMillis(1);
	
	public boolean framed = false;
	
	public TProtocolFactory protocolFactory =new TTupleProtocol.Factory();
	
	public String from = "";
	
	public String token = "";

	public ThriftClientConfig(){}

	public ThriftClientConfig(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public boolean isFramed() {
		return framed;
	}

	public void setFramed(boolean framed) {
		this.framed = framed;
	}

	public TProtocolFactory getProtocolFactory() {
		return protocolFactory;
	}

	public void setProtocolFactory(TProtocolFactory protocolFactory) {
		this.protocolFactory = protocolFactory;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "[host=" + host + ", port=" + port + ", timeout=" + timeout
				+ ", connectionTimeout=" + connectionTimeout + ", framed=" + framed + ", protocolFactory="
				+ protocolFactory.getClass() + ", from=" + from + ", token=" + token + "]";
	}
	
}
