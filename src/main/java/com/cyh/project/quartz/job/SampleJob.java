package com.cyh.project.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例带多个参数的定时任务
 *
 * @Author Scott
 */
@Slf4j
public class SampleJob implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String phoneNumber = (String) map.get("phone");
        String name = (String) map.get("name");
		log.info(String.format("%s! %s Jeecg-Boot 普通定时任务 SampleJob !  时间:" + new Date(),name,phoneNumber));
	}
}
