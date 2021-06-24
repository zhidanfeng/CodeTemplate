package com.zhi.listing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.listing.mapper.AmazonListingMapper;
import com.zhi.listing.model.entity.AmazonListing;
import com.zhi.listing.model.entity.BaseListing;
import com.zhi.listing.model.form.ListingPageForm;
import com.zhi.listing.model.vo.AmazonListingPageVo;
import com.zhi.listing.model.vo.ListingPageVo;
import com.zhi.listing.service.AmazonListingService;
import com.zhi.listing.service.ListingTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AmazonListingServiceImpl extends ListingTemplateService<AmazonListingMapper, AmazonListing> implements AmazonListingService {
    @Override
    public Page<ListingPageVo> pageListing(ListingPageForm form) {
        log.info("amazon listing service impl");
        return super.page(form);
    }

    @Override
    public Boolean removeMatchSku(Long key, Long tenantId) {
        return super.removeMatchSku(key, tenantId, 2);
    }

    @Override
    protected void beforePageListing(ListingPageForm form) {
        log.info("amazon beforePageListing");
    }

    @Override
    protected Page<AmazonListing> doPageListing(ListingPageForm form) {
        log.info("amazon doPageListing");
        return null;
    }

    @Override
    protected Page<ListingPageVo> afterPageListing(Page<AmazonListing> page) {
        log.info("walmart afterPageListing");
        Page<ListingPageVo> pageResult = new Page<>();
        List<AmazonListingPageVo> list = new ArrayList<>();
        AmazonListingPageVo walmartListingPageVo = new AmazonListingPageVo();
        walmartListingPageVo.setId(1L);
        walmartListingPageVo.setCommonField("common");
        walmartListingPageVo.setAmazonField("amazon");
        list.add(walmartListingPageVo);
        pageResult.setRecords(new ArrayList<>(list));
        return pageResult;
    }

    @Override
    protected Boolean removeMatchFromChannelSku(BaseListing listing) {
        return null;
    }

    @Override
    protected Wrapper<AmazonListing> removeMatchFromListing(BaseListing listing) {
        return null;
    }
}
