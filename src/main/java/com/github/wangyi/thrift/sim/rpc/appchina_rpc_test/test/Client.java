package com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.AddService;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.MoodService;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.OrderService;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.model.GENDER;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.model.Mood;
import com.github.wangyi.thrift.sim.rpc.appchina_rpc_test.main.api.model.Order;
/*
 * 
 * ========================================================
 * 日 期：@2016-12-16
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明：RPC 请求
 * 		单线程：
 * 		java序列化：30994ms
 * 		Kryo序列化：28881ms
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */
public class Client {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws BeansException, Exception  {
		final ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-thrift-rpc-customer.xml");
		long start = System.currentTimeMillis();
		// 多线程测试
		AddService addService= (AddService)ctx.getBean("addService");
		AddService otherAddService=(AddService) ctx.getBean("otherAddService");
		MoodService moodService =(MoodService) ctx.getBean("moodService");
		OrderService orderService =(OrderService) ctx.getBean("orderService");
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		threadPool.submit(new MutilThead(addService, otherAddService, moodService,orderService));
		threadPool.shutdown(); // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
		try {
			threadPool.awaitTermination(60, TimeUnit.SECONDS);//请求关闭，发生超时或者当前线程中断，无论哪一个发生都将导致阻塞，直到所有任务完成执行
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("times:" + (System.currentTimeMillis() - start));
	}
	
	public static void testPref(AddService addService)throws Exception{
		for(int i=0;i<100;i++){
			System.err.println("current rpc 请求："+addService.add(i));
		}
	}
	
	public static void testError(AddService addService)throws Exception{
		try {
			addService.exception();
			System.err.println("no error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testMood(MoodService moodService) {
		for(int i=0;i<500;i++){
			try {
					List<Mood> moodList=new LinkedList<Mood>();
					moodList.add(new Mood());
					moodList.add(new Mood());
					moodList.add(new Mood());
					moodList.add(new Mood());
					moodList.add(new Mood());
					moodService.test();
					System.out.println(moodService.test(1));
					System.out.println(moodService.test("12"));
					System.out.println(moodService.test(1, "name"));
					System.out.println(moodService.test(new Mood()));
					System.out.println(moodService.test(moodList));
					System.out.println(moodService.test(new Mood[] { new Mood() }));
					System.out.println(moodService.test(new int[] { 1, 2, 3 }));
					System.out.println(moodService.test(0, 1));
					System.out.println(moodService.test(GENDER.W));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
			}
		}
	}
	
	public static void testOrder(OrderService order){
		for(int i=0;i<500;i++){
			try {
				Order req=new Order();
				req.setStid("88888");
				req.setName("三星盖乐世S7 edge");
				req.setPrice(6888.00);
				req.setCreateTime(new Date());
				
				Order insert = order.insert(req);
				System.err.println(insert.toString());
				
				String delete = order.delete("88888");
				System.err.println(delete);
				
				Order update = order.update(req);
				System.err.println(update.toString());
				
				Order query = order.query("88888");
				System.err.println(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static class MutilThead extends Thread{
		private AddService as;
		private AddService os;
		private MoodService ms;
		private OrderService order;
		
		public MutilThead(){ }
		
		public MutilThead(AddService as,AddService os, MoodService ms,OrderService order) {
			this.as = as;
			this.os=os;
			this.ms = ms;
			this.order=order;
		}

		@Override
		public void run() {
			try {
				testError(as);
				testError(os);
				testMood(ms);
				testPref(as);
				testOrder(order);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
}
