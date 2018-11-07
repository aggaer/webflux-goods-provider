package com.ipaynow.yishouyun.goods.handler

import com.ipaynow.yishouyun.goods.entity.GoodsInfo
import com.ipaynow.yishouyun.goods.repository.GoodsInfoRepository
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.reactor.asMono
import kotlinx.coroutines.reactor.flux
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.annotation.Resource

/**
 * @author jerry
 * @created on 2018/11/6
 */

@SpringBootTest
@ExperimentalCoroutinesApi
@RunWith(SpringRunner::class)
class FunctionalGoodsHandlerTest {
    @Resource
    lateinit var goodsInfoRepository: GoodsInfoRepository

    @Test
    fun findGoodsInfo() {
        val goodsInfoFlux = GlobalScope.flux<GoodsInfo> {
            async { goodsInfoRepository.findByStoreIdAndStatusGreaterThan("000000100537579", 0) }.asMono(Unconfined).flux()
        }

        println(goodsInfoFlux.takeLast(3).blockFirst())
    }
}
