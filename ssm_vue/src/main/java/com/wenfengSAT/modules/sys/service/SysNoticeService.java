package com.wenfengSAT.modules.sys.service;

import java.util.List;

import com.wenfengSAT.modules.sys.entity.SysNotice;




public interface SysNoticeService extends BaseService<SysNotice>{

	/**
	 * 获取所有的公告信息
	 */
	public List<SysNotice> getNotices(SysNotice notice);
	
	/**
	 * 根据ID查询
	 * @param 
	 * @return
	 */
	SysNotice queryObject(Long noticeId);
	
	/**
	 * 删除公告
	 */
	void deleteBatch(Long[] noticeIds);
	
}
