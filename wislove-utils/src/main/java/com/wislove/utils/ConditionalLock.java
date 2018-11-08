package com.wislove.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionalLock {
 
    private final  Map<Thread, Object> relations = new ConcurrentHashMap<Thread, Object>();
    private final  ReentrantLock lock = new ReentrantLock(false);
    private final  Condition condition = lock.newCondition();
    private static final Logger logger = LoggerFactory.getLogger(ConditionalLock.class);
 
    public void await(Object object) {
        lock.lock();

        try {
            while (isAlreayLocked(object)) {
                condition.await();
            }
            logger.info("线程{}获得资源{}进入操作",Thread.currentThread(),object);
            relations.put(Thread.currentThread(), object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
 
    }
 
    public void singal() {
        lock.lock();
        try {
            condition.signal();
            logger.info("线程{}操作完成，让出资源",Thread.currentThread());
            relations.remove(Thread.currentThread());
        } finally {
            lock.unlock();
 
        }
    }
 
    private boolean isAlreayLocked(Object object) {
        Set<Entry<Thread, Object>> sets = relations.entrySet();
        for (Entry<Thread, Object> entry : sets) {
            Object other = entry.getValue();
            if (other == null) {
                continue;
            }
            if (object.equals(other)) {
                return true;
            }
        }
        return false;
 
    }
 

}
