package com.ipaynow.yishouyun.goods.handler

import com.ipaynow.yishouyun.goods.entity.GoodsInfo
import com.ipaynow.yishouyun.goods.repository.GoodsInfoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.reactor.mono
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

/**
 *
 * @author jerry
 * @created on 2018/11/6
 */

@Component
@Suppress("EXPERIMENTAL_API_USAGE")
class FunctionalGoodsHandler(private val goodsInfoRepository: GoodsInfoRepository) {

    fun fetchGoodsByCategory(request: ServerRequest): Mono<ServerResponse> {
        val storeId = request.pathVariable("storeId")
        val categoryId = request.pathVariable("categoryId").toLong()
        val goodsInfoFlux = GlobalScope.mono {
            goodsInfoRepository.findByStoreIdAndCategoryIdAndStatusGreaterThan(
                storeId,
                categoryId,
                0
            )
        }
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(goodsInfoFlux, ParameterizedTypeReference.forType(List::class.java))
    }

    fun findGoodsInfo(request: ServerRequest): Mono<ServerResponse> {
        val goodsId = request.pathVariable("goodsId")
        val goodsInfoMono = GlobalScope.mono {
            goodsInfoRepository.findById(goodsId).orElseThrow { RuntimeException("$goodsId 对应的商品信息不存在") }
        }
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(goodsInfoMono, GoodsInfo::class.java)
    }

    fun fetchStoreGoods(request: ServerRequest): Mono<ServerResponse> {
        val storeId = request.pathVariable("storeId")
        val goodsInfoFlux = GlobalScope.mono {
            goodsInfoRepository.findByStoreIdAndStatusGreaterThan(storeId, 0)
        }
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(goodsInfoFlux, ParameterizedTypeReference.forType(List::class.java))
    }
}