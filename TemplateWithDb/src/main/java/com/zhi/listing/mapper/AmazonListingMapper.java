package com.zhi.listing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhi.listing.model.entity.AmazonListing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmazonListingMapper extends BaseMapper<AmazonListing> {
}
