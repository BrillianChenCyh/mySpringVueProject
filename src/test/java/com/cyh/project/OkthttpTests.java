package com.cyh.project;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OkthttpTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        String url = "http://localhost:8082/demo/userJson?ccc=张三";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        //只能是这个集合传body多个key value参数
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(map,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    void contextLoads1() {
        String url = "http://218.200.85.79:4505/BusinessService/MN_8051_MN/GetMN_8051_ExceptData?StartTime=2019-12-04 11:39:00&EndTime=2019-12-04 15:39:00";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.set("Authorization","ltadmin=31F89386CB6DB9FF813BA20EAA49365E");
        List<String> list = new ArrayList<>();
        list.add("hbjmyjxj201611");
        //传raw json的形式
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(list,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    void contextLoads2() {
        String url = "http://106.37.208.243:8067/RBACService/RBAC/GetGJZMNList";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.set("Authorization","140000_01=CAE8E393CC3C17B9B9A8881E083D88CF");
        List<String> list = new ArrayList<>();
        list.add("hbjmyjxj201611");
        //传raw json的形式
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(null,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(responseEntity.getBody());
    }

}
