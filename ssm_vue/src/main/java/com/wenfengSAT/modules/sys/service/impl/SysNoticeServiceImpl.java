package com.wenfengSAT.modules.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenfengSAT.modules.sys.dao.SysNoticeDao;
import com.wenfengSAT.modules.sys.entity.SysNotice;
import com.wenfengSAT.modules.sys.service.SysNoticeService;


@Service
public class SysNoticeServiceImpl implements SysNoticeService {

	@Autowired
	private SysNoticeDao mapper;
	
	@Override
	public List<SysNotice> getNotices(SysNotice notice) {
		
		List<SysNotice> notices = mapper.getNotices(notice);
		return notices;
	}

	@Override
	public List<SysNotice> queryList(Map<String, Object> map) {
		
		return mapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		
		return mapper.queryTotal(map);
	}

	@Override
	public boolean save(SysNotice t) {
		Date date = new Date();
		t.setGmtCreate(date);
		if(mapper.save(t)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean update(SysNotice t) {

		if(mapper.update(t)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public SysNotice queryObject(Long noticeId) {
		
		return mapper.queryObject(noticeId);
	}

	@Override
	public void deleteBatch(Long[] noticeIds) {

		mapper.deleteBatch(noticeIds);
	}

}
