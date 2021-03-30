package com.zhi.operationlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.core.entity.User;
import com.zhi.operationlog.entity.MaterialSku;

import java.util.List;

public interface MaterialSkuService extends IService<MaterialSku> {

    void addMaterialSku(MaterialSku materialSku);

    void updateMaterialSku(MaterialSku materialSku);
}
