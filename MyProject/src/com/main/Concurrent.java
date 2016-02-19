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
 * @Desc  concurrent包源码分析
 * @Date 2016年2月18日 下午3:42:14
 * @Version 1.x 
 */
public class Concurrent {
	/**
	 * 总的接口，用来执行runnable任务
	 */
	Executor executor = null;
	/**
	 * 是Executor的扩展接口
	 * 主要扩展了执行Runnable或Callable任务的方式，及shutdown的方法
	 */
	ExecutorService executorService = null;
	/**
	 * 是ExecutorService的扩展接口
	 * 主要扩展了可以用任务调度的形式（延迟或定期）执行Runnable或Callable任务
	 */
	ScheduledExecutorService scheduledExecutorService = null;
	/**
	 * 是ExecutorService接口的实现类，是抽象类
	 * 提供一些默认的执行Runnable或Callable任务的方法
	 */
	AbstractExecutorService abstractExecutorService = null;
	/**
	 * 是AbstractExecutorService的子类，是线程池的实现
	 */
	ThreadPoolExecutor threadPoolExecutor = null;
	/**
	 * 是ThreadPoolExecutor的子类，实现ScheduledExecutorService接口
	 * 基于线程池模式的多任务调度，是Timer工具类的高性能版
	 */
	ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = null;
	
	/**
	 * Callable与Future是Runnable的另外的形式，用来异步获取任务执行结果
	 * Executors是工具类，用于创建上述各种实例
	 * CompletionService接口可以认为是Executor的一个分支
	 * 注意这里CompletionService并不是Executor的子接口，
	 * CompletionService是用来将生产新的异步任务与使用已完成任务的结果分离开来的服务与Future搭配使用。
	 */
	
	public void testCreateExecutorPool(){
		Executors.newFixedThreadPool(5); // 创建一个固定线程数的线程池
		Executors.newScheduledThreadPool(5); // 创建一个可对线程进行时间调度的线程池
		Executors.newCachedThreadPool(); // 创建一个可缓冲的无线程数量界限(Integer.MAX_VALUE)的线程池
		Executors.newSingleThreadExecutor(); // 创建一个可复用的单一线程的线程池
	}
	
	/**
	 * 有返回结果并且可能抛出异常的任务
	 */
	Callable callable = null;
	/**
	 * 表示异步执行的结果
	 */
	Future future = null;
	/**
	 * 实现Future、Runnable等接口,是一个异步执行的任务
	 * 可以直接执行，或包装成Callable执行
	 */
	FutureTask futureTask = null;
	/**
	 * 将生产新的异步任务与使用已完成任务的结果分离开来的服务，
	 * 用来执行Callable或Runnable，并异步获取执行结果
	 */
	CompletionService completionService = null;
	/**
	 * 实现CompletionService接口，使用构造时传入的Executor来执行Callable或Runnable
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
