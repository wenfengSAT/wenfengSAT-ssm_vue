package com.wenfengSAT.modules.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenfengSAT.common.annotation.SysLog;
import com.wenfengSAT.common.utils.PageUtils;
import com.wenfengSAT.common.utils.Query;
import com.wenfengSAT.common.utils.R;
import com.wenfengSAT.common.validator.ValidatorUtils;
import com.wenfengSAT.common.validator.group.AddGroup;
import com.wenfengSAT.common.validator.group.UpdateGroup;
import com.wenfengSAT.modules.sys.entity.SysNotice;
import com.wenfengSAT.modules.sys.service.SysNoticeService;




@Controller
@RequestMapping("/sys/notice")
public class SysNoticeController extends AbstractController {

	@Autowired
	private SysNoticeService sysNoticeService;
	
	@ResponseBody 
	@RequestMapping("/getNotices")
	public List<SysNotice> getNotices(HttpServletRequest request,HttpServletResponse response){
		SysNotice notice = new SysNotice();
		List<SysNotice> result = sysNoticeService.getNotices(notice);
		return result;
	}
	
	/**
	 * 所有公告列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysNotice> noticeList = sysNoticeService.queryList(query);
		int total = sysNoticeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(noticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 保存公告
	 */
	@SysLog("发布公告")
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sys:notice:save")
	public R save(@RequestBody SysNotice notice){
		ValidatorUtils.validateEntity(notice, AddGroup.class);
		
		if(sysNoticeService.save(notice)){
			return R.ok();
		}else{
			return R.error();
		}
		//return sysNoticeService.save(notice) ? R.ok() : R.error();
	}
	
	/**
	 * 修改公告
	 */
	@SysLog("修改公告")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:notice:update")
	public R update(@RequestBody SysNotice notice){
		ValidatorUtils.validateEntity(notice, UpdateGroup.class);

		return sysNoticeService.update(notice) ? R.ok() : R.error();
	}
	
	/**
	 * 通过id获取公告
	 */
	@ResponseBody
	@RequestMapping("/info/{noticeId}")
	public R info(@PathVariable("noticeId") Long noticeId){
		SysNotice notice = sysNoticeService.queryObject(noticeId);
		return R.ok().put("notice", notice);
	}
	
	/**
	 * 删除公告
	 */
	@SysLog("删除公告")
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sys:notice:delete")
	public R delete(@RequestBody Long[] noticeIds){
		sysNoticeService.deleteBatch(noticeIds);
		return R.ok();
	}
	
}
