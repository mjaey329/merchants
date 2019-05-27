package com.xzjh.passbook.service;

import com.xzjh.passbook.vo.CreateMerchantsRequest;
import com.xzjh.passbook.vo.PassTemplate;
import com.xzjh.passbook.vo.Response;

/**
 * 对商户服务接口定义
 */
public interface IMerchantsServ {

    /**
     * 创建商户服务
     * @param request {@link CreateMerchantsRequest} 创建商户请求
     * @return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * 根据 id 构造商户信息
     * @param id 商户id
     * @return
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * 查询所有商户
     * @return
     */
    Response findAll();

    /**
     * 投放优惠券
     * @param passTemplate {@link PassTemplate} 优惠券对象
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);

    /**
     * 根据商户 id 查询已投放的优惠价
     * @param id 商户id
     * @return
     */
    Response selectPassTemplate(Integer id);
}
