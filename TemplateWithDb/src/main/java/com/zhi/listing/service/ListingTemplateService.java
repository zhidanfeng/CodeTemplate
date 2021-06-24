package com.zhi.listing.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.listing.model.entity.BaseListing;
import com.zhi.listing.model.form.ListingPageForm;
import com.zhi.listing.model.vo.ListingPageVo;

public abstract class ListingTemplateService<M extends BaseMapper<T>, T extends BaseListing> extends ServiceImpl<M, T> {
    /***
     * @name: beforePageListing
     * @description: 在该方法中可以在查询数据库之前设置额外值，或者数据校验之类的处理
     * @param form:
     * @return: void
     * @since: 2021/6/24 2:26 下午
     * @author: zhidanfeng
     */
    protected abstract void beforePageListing(ListingPageForm form);
    /***
     * @name: doPageListing
     * @description: 从数据库中根据条件获取listing的基本数据
     * @param form:
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
     * @since: 2021/6/24 2:25 下午
     * @author: zhidanfeng
     */
    protected abstract Page<T> doPageListing(ListingPageForm form);
    /***
     * @name: afterPageListing
     * @description: 可在该方法中做一些数据库entity与vo间的转换
     * @param page:
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.zhi.listing.model.vo.ListingPageVo>
     * @since: 2021/6/24 2:23 下午
     * @author: zhidanfeng
     */
    protected abstract Page<ListingPageVo> afterPageListing(Page<T> page);
    /***
     * @name: removeMatchFromChannelSku
     * @description: 解除渠道SKU表中的公司SKU与渠道SKU间的关联关系
     * @param listing:
     * @return: java.lang.Boolean
     * @since: 2021/6/24 2:22 下午
     * @author: zhidanfeng
     */
    protected abstract Boolean removeMatchFromChannelSku(BaseListing listing);
    /***
     * @name: removeMatchFromListing
     * @description: 解除listing表中的公司SKU与listing间的关联关系
     * @param listing:
     * @return: com.baomidou.mybatisplus.core.conditions.Wrapper<T>
     * @since: 2021/6/24 2:22 下午
     * @author: zhidanfeng
     */
    protected abstract Wrapper<T> removeMatchFromListing(BaseListing listing);

    public Page<ListingPageVo> page(ListingPageForm form) {
        beforePageListing(form);
        Page<T> page = doPageListing(form);
        return afterPageListing(page);
    }

    public Boolean removeMatchSku(Long key, Long tenantId, Integer listingType) {
        BaseListing listing = super.getById(key);
        Boolean channelSkuRemoveFlag = this.removeMatchFromChannelSku(listing);
        if (null == channelSkuRemoveFlag) {
            // 删除渠道SKU
            //this.channelSkuService.removeChannelSku(listing.getShopId(), listing.getSku(), listing.getCode(), listing.getTenantId(), PlatformTypeEnum.WALMART);
        }
        // 解除listing与公司SKU间的关联关系
        Wrapper<T> updateWrapper = this.removeMatchFromListing(listing);
        if (updateWrapper != null) {
            super.update(updateWrapper);
        }
        return true;
    }
}
