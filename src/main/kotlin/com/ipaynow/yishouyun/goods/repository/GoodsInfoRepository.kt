package com.ipaynow.yishouyun.goods.repository

import com.ipaynow.yishouyun.goods.entity.GoodsInfo
import org.springframework.data.jpa.repository.JpaRepository

/**
 *
 * @author jerry
 * @created on 2018/11/6
 */
interface GoodsInfoRepository : JpaRepository<GoodsInfo, String> {

    fun findByStoreIdAndCategoryIdAndStatusGreaterThan(storeId: String, categoryId: Long, status: Int): List<GoodsInfo>

    fun findByStoreIdAndStatusGreaterThan(storeId: String, status: Int): List<GoodsInfo>
}