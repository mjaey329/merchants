package com.xzjh.passbook.dao;

import com.xzjh.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Merchants Dao 接口
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer>{
    /**
     * 根据 id 获取商户对象
     * @param id 商户id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);

    /**
     * 根据 name 获取商户对象
     * @param name
     * @return {@link Merchants}
     */
    Merchants findByName(String name);

}
