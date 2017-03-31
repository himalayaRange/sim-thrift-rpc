package com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.cluster.client;

import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.client.ClientIOException;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.client.RemoteException;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.client.ThreadSafetyClient;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc.base.cluster.pool.DistributePool;

/**
 * 
 * ========================================================
 * 日 期：@2016-12-12
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明：随机方式客户端
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */
public class DistributeClient<P,R> extends DistributePool<P, R> implements ThreadSafetyClient<P, R> {

	public DistributeClient() {}

	@Override
	public R send(P param) throws ClientIOException, RemoteException {
		BorrowedClient<P, R> client = null;
		try {
			client = this.borrowClient();
			return client.client.send(param);
		} catch (ClientIOException e) {
			this.invalidateClient(client);
			throw e;
		} finally {
			this.returnClient(client);
		}
	}
	
}
