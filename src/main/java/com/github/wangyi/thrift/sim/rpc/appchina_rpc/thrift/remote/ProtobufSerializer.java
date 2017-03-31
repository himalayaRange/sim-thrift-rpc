package com.github.wangyi.thrift.sim.rpc.appchina_rpc.thrift.remote;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * 
 * ========================================================
 * 日 期：@2016-12-19
 * 作 者：wangyi
 * 版 本：1.0.0
 * 类说明： Google Protobuf序列化
 * TODO
 * ========================================================
 * 修订日期 :   
 * 修订人 :
 * 描述:
 */
public class ProtobufSerializer implements Serializer {

	/**
	 * serialize
	 * Protobuf序列化
	 **/
	@Override
	public  <T> byte[] getBytes(T obj) {
		 if (obj == null) {
	            throw new RuntimeException("序列化对象(" + obj + ")!");
	        }
	        @SuppressWarnings("unchecked")
	        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
	        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
	        byte[] protostuff = null;
	        try {
	            protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
	        } catch (Exception e) {
	            throw new RuntimeException("序列化(" + obj.getClass() + ")对象(" + obj + ")发生异常!", e);
	        } finally {
	            buffer.clear();
	        }
	        return protostuff;
	}

	/**
	 * deserialize
	 * Protobuf反序列话 
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(byte[] bytes) throws Exception {
		if(bytes==null ||bytes.length==0){
			throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
		}
		RuntimeSchema<Object> schema = RuntimeSchema.createFrom(Object.class);
		Object obj =null;
		try {
			obj = schema.newMessage();
			ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
		} catch (Exception e) {
			 throw new RuntimeException("反序列化过程中依据类型创建对象失败!", e);
		}
		return (T)obj;
	}

}
