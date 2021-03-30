package com.zhi.operationlog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhi.operationlog.core.FieldAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("nextop_materials_sku")
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSku {
    //@FieldAlias(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @FieldAlias(value = "SKU")
    @TableField("sku")
    private String sku;

    @FieldAlias(value = "sku中文名称")
    @TableField("cn_name")
    private String cnName;

    @FieldAlias(value = "启用/禁用")
    @TableField("status")
    private Integer status;
}
