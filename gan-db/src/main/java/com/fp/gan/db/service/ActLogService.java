package com.fp.gan.db.service;

import java.util.List;

import com.fp.gan.db.entity.ActLog;


/**
 * 系统日志跟踪
 * @author 上午9:55:34 by sam
 *
 */
public interface ActLogService {

	public void saveLog(ActLog log);
	
	public List<ActLog> getActLogByOrder(long orderId);
	
}
