package com.margin.endpoint.impl;

import com.margin.job.ScheduleJob;
import com.margin.service.SourceVersioningScheduleJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexEndpointImpl {

	@Autowired
	private SourceVersioningScheduleJobServiceImpl scheduleJobService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		List<ScheduleJob> jobList = scheduleJobService.getAll();
		for (int i = 0; i < jobList.size(); i++) {
			jobList.get(i).setJobId(String.valueOf(i));
		}
		model.addAttribute("jobs", jobList);
		return "index";
	}
}
