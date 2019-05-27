package com.xzjh.passbook.merchants;

import com.alibaba.fastjson.JSON;
import com.xzjh.passbook.dao.MerchantsDao;
import com.xzjh.passbook.service.IMerchantsServ;
import com.xzjh.passbook.vo.CreateMerchantsRequest;
import com.xzjh.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootTest
public class MerchantsApplicationTests {

	@Autowired
	private IMerchantsServ merchantsServ;
	@Autowired
	private MerchantsDao merchantsDao;

	@Test
	public void contextLoads() {
		System.out.println("hello,");
	}

	@Test
	public void testMerchantsServ (){
		System.out.println("MerchantsTest");
	}

	@Test
	public void testCreateMerchantServ(){

		CreateMerchantsRequest request = new CreateMerchantsRequest();
		request.setName("5-7测试");
		request.setLogoUrl("222");
		request.setBusinessLicenseUrl("222");
		request.setPhone("12333");
		request.setAddress("上海");


		System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));
	}




	@Test
	public void findAllMerchants(){
		System.out.println(JSON.toJSONString(merchantsServ.findAll()));
	}


	/**
	 * 需要启动本地kafka+
	 * 
	 */
	@Test
	public void testDropPassTemplate(){
		PassTemplate passTemplate = new PassTemplate();
		passTemplate.setId(19);
		passTemplate.setTitle("title:token");
		passTemplate.setSummary("简介：token");
		passTemplate.setDesc("详情：has token");
		passTemplate.setLimit(10000L);
		passTemplate.setHasToken(true);
		passTemplate.setBackground(2);
		passTemplate.setStart(new Date());
		passTemplate.setEnd(DateUtils.addDays(new Date(),10));

		System.out.println(
				JSON.toJSONString(merchantsServ.dropPassTemplate(passTemplate))
		);
	}


}
