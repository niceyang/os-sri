package com.sri.service.imple;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sri.entity.Cache;
import com.sri.reporsitory.CacheRepository;
import com.sri.service.CacheService;

/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * The service implementations implement the actual methods and will be injected into required components(like other services or restAPI layer)
 * <p>
 * There are many common method between the services(like CRUD), so the common part is define in a BaseImple
 * This service implements get the common methods by extends the BaseService
 * The E is the Entity type of the service
 */
@Service
public class CacheServiceImple extends BaseImple<Cache> implements CacheService {

    @Autowired
    private CacheRepository cacheRepository;

    @PostConstruct
    public void initParent() {
        super.repository = cacheRepository;
    }
    
    // Create an async job
	@Override
	public String createJob() {
		Cache nrecord = new Cache();
		nrecord.setFinished(0);
		nrecord.setUid(UUID.randomUUID().toString());
		cacheRepository.save(nrecord);
		return nrecord.getUid();
	}
	
	// Update a job
	@Override
	public boolean updateJob(String uuid, String data) {
		Cache record = cacheRepository.findByUid(uuid);
		if (record == null) {
			return false;
		}
		record.setData(data);
		record.setFinished(1);
		cacheRepository.save(record);
		return true;
	}
	
	// Remove a job
	@Override
	public boolean removeJob(String uuid) {
		Cache record = cacheRepository.findByUid(uuid);
		if (record == null) {
			return false;
		}
		cacheRepository.delete(record);
		return true;
	}

	// Find a job by uuid
	@Override
	public Cache findJob(String uuid) {
		return cacheRepository.findByUid(uuid);
	}
	
	// Check if a job is completed
	@Override
	public boolean isJobCompleted(String uuid) {
		Cache record = cacheRepository.findByUid(uuid);
		return record.getFinished() != 0;
	}
	
}
