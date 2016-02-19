package com.main;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * @Author Fangys
 * @Desc  concurrent��Դ�����
 * @Date 2016��2��18�� ����3:42:14
 * @Version 1.x 
 */
public class Concurrent {
	/**
	 * �ܵĽӿڣ�����ִ��runnable����
	 */
	Executor executor = null;
	/**
	 * ��Executor����չ�ӿ�
	 * ��Ҫ��չ��ִ��Runnable��Callable����ķ�ʽ����shutdown�ķ���
	 */
	ExecutorService executorService = null;
	/**
	 * ��ExecutorService����չ�ӿ�
	 * ��Ҫ��չ�˿�����������ȵ���ʽ���ӳٻ��ڣ�ִ��Runnable��Callable����
	 */
	ScheduledExecutorService scheduledExecutorService = null;
	/**
	 * ��ExecutorService�ӿڵ�ʵ���࣬�ǳ�����
	 * �ṩһЩĬ�ϵ�ִ��Runnable��Callable����ķ���
	 */
	AbstractExecutorService abstractExecutorService = null;
	/**
	 * ��AbstractExecutorService�����࣬���̳߳ص�ʵ��
	 */
	ThreadPoolExecutor threadPoolExecutor = null;
	/**
	 * ��ThreadPoolExecutor�����࣬ʵ��ScheduledExecutorService�ӿ�
	 * �����̳߳�ģʽ�Ķ�������ȣ���Timer������ĸ����ܰ�
	 */
	ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = null;
	
	/**
	 * Callable��Future��Runnable���������ʽ�������첽��ȡ����ִ�н��
	 * Executors�ǹ����࣬���ڴ�����������ʵ��
	 * CompletionService�ӿڿ�����Ϊ��Executor��һ����֧
	 * ע������CompletionService������Executor���ӽӿڣ�
	 * CompletionService�������������µ��첽������ʹ�����������Ľ�����뿪���ķ�����Future����ʹ�á�
	 */
	
	public void testCreateExecutorPool(){
		Executors.newFixedThreadPool(5); // ����һ���̶��߳������̳߳�
		Executors.newScheduledThreadPool(5); // ����һ���ɶ��߳̽���ʱ����ȵ��̳߳�
		Executors.newCachedThreadPool(); // ����һ���ɻ�������߳���������(Integer.MAX_VALUE)���̳߳�
		Executors.newSingleThreadExecutor(); // ����һ���ɸ��õĵ�һ�̵߳��̳߳�
	}
	
	/**
	 * �з��ؽ�����ҿ����׳��쳣������
	 */
	Callable callable = null;
	/**
	 * ��ʾ�첽ִ�еĽ��
	 */
	Future future = null;
	/**
	 * ʵ��Future��Runnable�Ƚӿ�,��һ���첽ִ�е�����
	 * ����ֱ��ִ�У����װ��Callableִ��
	 */
	FutureTask futureTask = null;
	/**
	 * �������µ��첽������ʹ�����������Ľ�����뿪���ķ���
	 * ����ִ��Callable��Runnable�����첽��ȡִ�н��
	 */
	CompletionService completionService = null;
	/**
	 * ʵ��CompletionService�ӿڣ�ʹ�ù���ʱ�����Executor��ִ��Callable��Runnable
	 */
	ExecutorCompletionService executorCompletionService = null;
	
	public static void test() throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		Future<String> future = completionService.submit(new Callable(){

			@Override
			public Object call() throws Exception {
				return "success";
			}
		});
		System.out.println("other logic..");
		String result = future.get();
		System.out.println("result:" + result);
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		test();
	}
	
}
