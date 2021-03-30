package com.zhi.operationlog.controller;

import com.zhi.core.entity.User;
import com.zhi.operationlog.entity.MaterialSku;
import com.zhi.operationlog.service.MaterialSkuService;
import com.zhi.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/material/sku")
public class MaterialSkuController {
    @Resource
    private MaterialSkuService materialSkuService;

    @PostMapping("/add")
    public void add(@RequestBody MaterialSku materialSku) {
        this.materialSkuService.addMaterialSku(materialSku);
    }

    @PostMapping("/update")
    public void update(@RequestBody MaterialSku materialSku) {
        this.materialSkuService.updateMaterialSku(materialSku);
    }
}
