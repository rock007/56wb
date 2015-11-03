package sam.wb.db.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sam.wb.db.entity.ActLog;
import sam.wb.db.repository.ActLogRepository;
import sam.wb.db.service.ActLogService;


@Component("actLogService")
public class ActlogServiceImp implements ActLogService{

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
