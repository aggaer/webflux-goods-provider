package com.ipaynow.yishouyun.goods.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.mono
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.annotation.Resource

/**
 *
 * @author jerry
 * @created on 2018/11/6
 */
@SpringBootTest
@RunWith(SpringRunner::class)
@ExperimentalCoroutinesApi
class CoroutineTest {
    @Resource
    lateinit var goodsInfoRepository: GoodsInfoRepository

    @Test
    fun delay_with_coroutine() {
        println("start....")
        val mono = GlobalScope.mono {
            delay(1000)
             goodsInfoRepository.findById("1000050006")
        }
        println("end...")
        println("result:${mono.toFuture().join().get()}")
    }
}