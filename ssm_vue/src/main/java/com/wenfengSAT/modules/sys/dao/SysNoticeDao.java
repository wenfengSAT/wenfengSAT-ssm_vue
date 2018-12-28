package com.wenfengSAT.modules.sys.dao;

import java.util.List;

import com.wenfengSAT.modules.sys.entity.SysNotice;




public interface SysNoticeDao extends BaseDao<SysNotice> {

	public List<SysNotice> getNotices(SysNotice notice);
}
