package com.xzjh.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.xzjh.passbook.constant.Constants;
import com.xzjh.passbook.constant.ErrorCode;
import com.xzjh.passbook.dao.MerchantsDao;
import com.xzjh.passbook.entity.Merchants;
import com.xzjh.passbook.service.IMerchantsServ;
import com.xzjh.passbook.vo.CreateMerchantsRequest;
import com.xzjh.passbook.vo.CreateMerchantsResponse;
import com.xzjh.passbook.vo.PassTemplate;
import com.xzjh.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商户服务接口实现
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ {

    private final MerchantsDao merchantsDao;

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public MerchantsServImpl(MerchantsDao merchantsDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode !=ErrorCode.SUCCESS){
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        Merchants merchants  = merchantsDao.findById(id);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        response.setData(merchants);

        return response;
    }

    @Override
    public Response findAll() {
        Response response = new Response();
        List<Merchants> merchants = merchantsDao.findAll();
        System.out.println("hellp:"+merchants);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        response.setData(merchants);
        return response;
    }


    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else {
            String passTemplate  = JSON.toJSONString(template);
            kafkaTemplate.send(  Constants.TEMPLATE_TOPIC,
                                  Constants.TEMPLATE_TOPIC,
                                  passTemplate);
            log.info("dropPassTemplate:{}", passTemplate);
        }
        return response;
    }

    @Override
    public Response selectPassTemplate(Integer id) {
        return null;
    }
}
