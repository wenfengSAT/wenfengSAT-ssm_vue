package com.wenfengSAT.modules.job.dao;

import java.util.Map;

import com.wenfengSAT.modules.job.entity.ScheduleJobEntity;
import com.wenfengSAT.modules.sys.dao.BaseDao;

/**
 * 定时任务
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
