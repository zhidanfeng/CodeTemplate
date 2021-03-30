package com.zhi.operationlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.operationlog.dao.MaterialSkuDao;
import com.zhi.operationlog.entity.MaterialSku;
import com.zhi.operationlog.service.MaterialSkuService;
import org.springframework.stereotype.Service;


@Service
public class MaterialSkuServiceImpl extends ServiceImpl<MaterialSkuDao, MaterialSku> implements MaterialSkuService {


    @Override
    public void addMaterialSku(MaterialSku materialSku) {
        System.out.println("add material sku");
    }

    @Override
    public void updateMaterialSku(MaterialSku materialSku) {
        System.out.println("update material sku");
    }
}
