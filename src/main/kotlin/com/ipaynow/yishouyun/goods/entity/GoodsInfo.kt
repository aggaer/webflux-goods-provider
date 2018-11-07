package com.ipaynow.yishouyun.goods.entity

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class GoodsInfo(
    @Id
    var id: String,
    var categoryId: Long,
    var type: Byte,
    var storeId: String,
    var merchantId: String,
    var name: String,
    var description: String,
    var code: String,
    var subImgs: String,
    var unit: String,
    var mainImg: String,
    var attributeList: String,
    var status: Int,
    var sourceId: Int?,
    var associationId: String,
    var mealPreparationTime: Byte,
    var sortWeight: Int,
    var createTime: Instant,
    var updateTime: Instant
)