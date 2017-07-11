package com.fp.gan.db.service.imp;

import java.util.List;

import com.fp.gan.db.entity.ActLog;
import com.fp.gan.db.repository.ActLogRepository;
import com.fp.gan.db.service.ActLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("actLogService")
public class ActlogServiceImp implements ActLogService {

	@Autowired
	private ActLogRepository actLogRepository;
	
	@Override
	public void saveLog(ActLog log) {
		
		actLogRepository.save(log);
	}

	@Override
	public List<ActLog> getActLogByOrder(long orderId) {
	
		return null;		
		//return actLogRepository.findAll(ids);
	}

	
}
