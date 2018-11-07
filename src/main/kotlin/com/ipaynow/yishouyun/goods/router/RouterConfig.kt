package com.ipaynow.yishouyun.goods.router

import com.ipaynow.yishouyun.goods.handler.FunctionalGoodsHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

/**
 *
 * @author jerry
 * @created on 2018/11/6
 */
@Configuration
class RouterConfig {

    @Bean
    fun routers(goodsHandler: FunctionalGoodsHandler): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON_UTF8).nest {
                "test".nest {
                    GET("/") { ok().body(Mono.just("test ok"), String::class.java) }
                }
                "/api".nest {
                    GET("/goods/{goodsId}", goodsHandler::findGoodsInfo)
                    GET("/stores/{storeId}/goods", goodsHandler::fetchStoreGoods)
                    POST("/stores/{storeId}/categories/{categoryId}/goods", goodsHandler::fetchGoodsByCategory)
                }
            }
        }
    }
}

