package com.ipaynow.yishouyun.goods.repository

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 *
 * @author jerry
 * @created on 2018/11/6
 */

@SpringBootTest
@RunWith(SpringRunner::class)
class GoodsInfoTest {
    lateinit var goodsInfoRepository: GoodsInfoRepository

    @Test
    fun findByIdTest() {
        val mono = goodsInfoRepository.findById("1000050006")
        println("result:${mono}")
    }
}