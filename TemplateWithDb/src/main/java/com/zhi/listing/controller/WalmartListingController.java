package com.zhi.listing.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.listing.model.dp.ListingId;
import com.zhi.listing.model.form.WalmartListingPageForm;
import com.zhi.listing.model.vo.ListingPageVo;
import com.zhi.listing.service.WalmartListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/walmart")
public class WalmartListingController {

    @Resource
    private WalmartListingService walmartListingService;

    @PostMapping("/page")
    public Page<ListingPageVo> page(@RequestBody WalmartListingPageForm form) {
        form.setTenantId(-1L);
        return this.walmartListingService.pageListing(form);
    }

    @PostMapping("/removeMatchSku")
    public Boolean removeMatchSku(@RequestBody Long id) {
        log.info("请求{}方法，{}，请求参数:{}", "removeMatchSku", "解除匹配", id);
        Boolean result = walmartListingService.removeMatchSku(new ListingId(id), -1L);
        return result;
    }
}
