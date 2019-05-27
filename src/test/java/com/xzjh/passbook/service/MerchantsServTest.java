package com.xzjh.passbook.service;

import com.alibaba.fastjson.JSON;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.xzjh.passbook.vo.CreateMerchantsRequest;
import com.xzjh.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 商户服务测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {

    @Autowired
    private IMerchantsServ merchantsServ;
    @Test
    public void testMerchantsServ (){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("型走江湖");
        request.setLogoUrl("ww.888.ddd");
        request.setBusinessLicenseUrl("www");
        request.setPhone("1221212");
        request.setAddress("USAA");
        System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));
    }

    @Test
    public void buildMerchantsInfoById(){
        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(17)));
    }

    /**
     @Windows
    .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic dmj --from-beginning
     @Linux
     bin/kafka-console-consumer.sh --bootstrap-server mastet:9092 --topic test --from-beginning
     *
     * bin/kafka-console-producer.sh --broker-list master:9092 --topic test
     * .\bin\windows\kafka-console-producer.bat --broker-list 192.168.146.128:9092 --topic dmj
     */
    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(18);
        passTemplate.setTitle("优惠券-token");
        passTemplate.setSummary("简介: 慕课");
        passTemplate.setDesc("详情: 慕课");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(true);
        passTemplate.setBackground(2);
        passTemplate.setStart(DateUtils.addDays(new Date(), -10));
        passTemplate.setEnd(DateUtils.addDays(new Date(), 10));

        System.out.println(JSON.toJSONString(passTemplate));
        System.out.println(JSON.toJSONString(
                merchantsServ.dropPassTemplate(passTemplate)
        ));

    }
}
