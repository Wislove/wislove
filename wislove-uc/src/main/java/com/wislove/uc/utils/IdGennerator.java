package com.wislove.uc.utils;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * 自定义id生成器
 * @author 廖双龙
 *
 */
public class IdGennerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
		SnowflakeIdWorker snowflakeIdWorker = SnowflakeIdWorkerFactory.getInstance();
		long id = snowflakeIdWorker.nextId();
		return String.valueOf(id);
	}
}
