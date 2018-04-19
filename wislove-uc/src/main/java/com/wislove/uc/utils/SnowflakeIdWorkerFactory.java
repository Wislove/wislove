package com.wislove.uc.utils;

public class SnowflakeIdWorkerFactory {
	
	private static volatile SnowflakeIdWorker instance;
	
	private SnowflakeIdWorkerFactory(){}
	
	public static SnowflakeIdWorker getInstance(){
		if (instance == null){
			synchronized (SnowflakeIdWorkerFactory.class) {
				if (instance == null){
					instance = new SnowflakeIdWorker(0, 0);
				}
			}
		}
		
		return instance;
	}
}
