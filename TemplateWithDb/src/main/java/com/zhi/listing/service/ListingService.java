package com.zhi.listing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.listing.model.dp.ListingId;
import com.zhi.listing.model.form.ListingPageForm;
import com.zhi.listing.model.vo.ListingPageVo;

public interface ListingService {
    Page<ListingPageVo> pageListing(ListingPageForm form);
    Boolean removeMatchSku(ListingId id, Long tenantId);
}
