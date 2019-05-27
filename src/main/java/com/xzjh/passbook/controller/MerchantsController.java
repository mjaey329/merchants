package com.xzjh.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.xzjh.passbook.service.IMerchantsServ;
import com.xzjh.passbook.vo.CreateMerchantsRequest;
import com.xzjh.passbook.vo.PassTemplate;
import com.xzjh.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/merchants")
public class MerchantsController {

    /** 商户服务接口 */
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsController(IMerchantsServ merchantsServ){
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){
        log.info("CreateMerchants:{}",JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo:{}",id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @GetMapping("/findAll")
    public Response findMerchantsAll(){
        return merchantsServ.findAll();
    }


    /**
     * {"background":2,"desc":"详情: 慕课","end":1543433749495,"hasToken":false,"id":17,"limit":10000,"start":1541705749493,"summary":"简介: 慕课","title":"优惠券111"}
     * @param template
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/drop")
    public Response dropPassTemplate (@RequestBody PassTemplate template){
        return merchantsServ.dropPassTemplate(template);
    }
}
