package com.wenfengSAT.modules.statistic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chart")
public class ChartController {

	@RequestMapping("/line")
	@ResponseBody
	public Map<String,Object> line(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Integer> series = new ArrayList<Integer>();
		for(int i=0;i<7;i++){
			int max=100;
	        int min=10;
	        Random random = new Random();
	        int number = random.nextInt(max)%(max-min+1) + min;
			series.add(number);
		}
		map.put("series", series);
		return map;
	}
}
