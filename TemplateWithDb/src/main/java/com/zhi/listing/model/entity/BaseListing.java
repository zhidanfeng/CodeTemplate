package com.zhi.listing.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhi.core.entity.BaseEntity;
import lombok.Data;

@Data
public class BaseListing extends BaseEntity {
    @TableId(value = "id", type = IdType.INPUT )
    private Long id;

    @TableField("tenant_id")
    private Long tenantId;
    private Long skuId;
    private String sku;
    private String channelSkuCode;
    private Long shopId;
}
