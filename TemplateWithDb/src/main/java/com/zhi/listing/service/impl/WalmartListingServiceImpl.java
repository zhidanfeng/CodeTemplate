package com.zhi.listing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.listing.mapper.WalmartListingMapper;
import com.zhi.listing.model.dp.ListingId;
import com.zhi.listing.model.entity.BaseListing;
import com.zhi.listing.model.entity.WalmartListing;
import com.zhi.listing.model.form.ListingPageForm;
import com.zhi.listing.model.vo.ListingPageVo;
import com.zhi.listing.model.vo.WalmartListingPageVo;
import com.zhi.listing.service.ListingTemplateService;
import com.zhi.listing.service.WalmartListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Service("walmartListingServiceImpl")
@Service
public class WalmartListingServiceImpl extends ListingTemplateService<WalmartListingMapper, WalmartListing> implements WalmartListingService {
    @Override
    public Page<ListingPageVo> pageListing(ListingPageForm form) {
        log.info("walmart listing service impl");
        return super.page(form);
    }

    @Override
    protected void beforePageListing(ListingPageForm form) {
        log.info("walmart beforePageListing");
    }

    @Override
    protected Page<WalmartListing> doPageListing(ListingPageForm form) {
        log.info("walmart doPageListing");
        return null;
    }

    @Override
    protected Page<ListingPageVo> afterPageListing(Page<WalmartListing> page) {
        log.info("walmart afterPageListing");
        Page<ListingPageVo> pageResult = new Page<>();
        List<WalmartListingPageVo> list = new ArrayList<>();
        WalmartListingPageVo walmartListingPageVo = new WalmartListingPageVo();
        walmartListingPageVo.setId(1L);
        walmartListingPageVo.setCommonField("common");
        walmartListingPageVo.setWalmartField("walmart");
        list.add(walmartListingPageVo);
        pageResult.setRecords(new ArrayList<>(list));
        ListingId listingId = new ListingId(null);
        return pageResult;
    }

    @Override
    public Boolean removeMatchSku(ListingId id, Long tenantId) {
        return super.removeMatchSku(id.getValue(), tenantId, 1);
    }

    @Override
    protected Boolean removeMatchFromChannelSku(BaseListing listing) {
        return null;
    }

    @Override
    protected Wrapper<WalmartListing> removeMatchFromListing(BaseListing listing) {
        LambdaUpdateWrapper<WalmartListing> updateWrapper = Wrappers.lambdaUpdate(WalmartListing.class);
        updateWrapper.eq(WalmartListing::getId, listing.getId());
        updateWrapper.set(WalmartListing::getSku, null);
        updateWrapper.set(WalmartListing::getSkuId, null);
        return updateWrapper;
    }
}
