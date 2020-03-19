package com.cyh.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jms.Destination;

import com.cyh.project.config.WebSocketServer;
import com.cyh.project.quartz.job.SampleJob;
import com.cyh.project.quartz.job.SampleParamJob;
import org.apache.activemq.command.ActiveMQQueue;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cyh.project.activemq.Product;
import com.cyh.project.entity.User;
import com.cyh.project.service.EmailService;
import com.cyh.project.service.UserService;

/**
 * @author: chenyinghui
 * @Date: 2019/11/16 19:30
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

//    @Autowired
//    private Product product;

    @GetMapping("/index")
    public String index() {
        return "this is" + serverPort;
    }

    @GetMapping("/index2")
    public String index2() {
        int i = 1 / 0;
        return "123";
    }

    /**
     * 测试mysql数据库1
     *
     * @param name :
     * @param age  :
     * @return int
     * @throws
     * @Author chenyinghui
     * @Date 2020/3/7 16:57
     */
    @GetMapping("/insert")
    public int insert(String name, Integer age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        int result = userService.insert(user);
        return result;
    }

    /**
     * 测试mysql数据库2
     *
     * @param name :
     * @param age  :
     * @return int
     * @throws
     * @Author chenyinghui
     * @Date 2020/3/7 16:57
     */
    @GetMapping("/insert2")
    public int insert2(String name, Integer age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        int result = userService.insert2(user);
        return result;
    }

    /**
     * 测试sqlServer数据库
     *
     * @return java.util.List<com.cyh.project.entity.User>
     * @throws
     * @Author chenyinghui
     * @Date 2020/3/7 17:39
     */
    @ResponseBody
    @GetMapping("/sqlserverSelect")
    public List<User> sqlserverSelect() {
        return userService.sqlserverSelect();
    }

    /**
     * 测试传参
     *
     * @param ccc   :
     * @param email :
     * @return com.cyh.project.entity.User
     * @throws
     * @Author chenyinghui
     * @Date 2020/3/7 17:08
     */
    @ResponseBody
    @PostMapping("/userJson")
    public User userJson(String ccc, @RequestBody String[] email) {
        User user = new User();
        user.setAge(18);
        user.setName("张三");
        user.setDate(new Date());
        return user;
    }

    /**
     * 发送邮件
     *
     * @return java.lang.String
     * @throws @Date 2019/11/21 21:25
     * @Author chenyinghui
     */
    @ResponseBody
    @GetMapping("/sendMail")
    public String sendMail() {
        emailService.sendSimpleMail("2944989706@qq.com", "你好", "今天去大保健哈!");
        return "成功!";
    }

    /**
     * 发送带附件的邮件
     *
     * @return java.lang.String
     * @throws @Date 2019/11/21 21:34
     * @Author chenyinghui
     */
    @ResponseBody
    @GetMapping("/attach")
    public String sendAttachMail() {
        File file = new File("src/main/resources/static/xxx.png");
        emailService.sendAttachMail("2944989706@qq.com", "你好", "今天去大保健哈!", file);
        return "成功!";
    }

    /**
     * redis缓存-数据库
     *
     * @param name :
     * @return com.example.demo2.emtity.User
     * @throws @Date 2019/12/18 18:18
     * @Author chenyinghui
     */
    @ResponseBody
    @GetMapping("/saveUser")
    public User saveUser(String name) {
        return userService.findByName(name);
    }

    /**
     * activeMq发送消息
     *
     * @return java.lang.String
     * @throws
     * @Author chenyinghui
     * @Date 2020/3/7 17:38
     */
    @ResponseBody
    @GetMapping("/sendMsg")
    public String sendMsg() {
        // 点对点消息
        Destination destination = new ActiveMQQueue("myqueues");
        for (int i = 0; i < 3; i++) {
//            product.sendMessage(destination, "发消息啦!");
        }
        return "ok";
    }

    /**
     * webSocket服务端发送客户端消息
     *
     * @param message
     * @param toUserId
     * @return
     * @throws IOException
     */
    @RequestMapping("/push/{toUserId}")
    @ResponseBody
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

    /**
     * 开启定时任务
     * scheduler.start();
     * scheduler.scheduleJob(jobDetail, trigger);
     * <p>
     * 暂停任务
     * scheduler.pauseJob(JobKey.jobKey(jobClassName));
     * <p>
     * 删除定时任务
     * scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
     * scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
     * scheduler.deleteJob(JobKey.jobKey(jobClassName));
     */

    @Autowired
    private Scheduler scheduler;

    /**
     * 带简单参数的定时任务
     *
     * @return
     */
    @GetMapping("/startSampleParamQuartz")
    @ResponseBody
    public String startSampleParamQuartz() {
        try {
            // 启动调度器
            scheduler.start();
            String parameter = "23333";
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(SampleParamJob.class)
                    //.withIdentity("com.example.demo2.quartz.job.SampleParamJob")
                    .usingJobData("parameter", parameter)
                    .build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("com.cyh.project.quartz.job.SampleParamJob").withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 带多个参数的定时任务
     *
     * @return
     */
    @GetMapping("/startParamQuartz")
    @ResponseBody
    public String startParamQuartz() {
        try {
            // 启动调度器
            scheduler.start();
            JobDataMap map = new JobDataMap();
            map.put("phone", "13517228736");
            map.put("name", "23333");
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                    .usingJobData(map)
                    .build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
