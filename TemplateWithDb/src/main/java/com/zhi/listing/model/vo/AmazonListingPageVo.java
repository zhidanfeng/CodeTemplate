package com.zhi.listing.model.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@ToString(callSuper = true)
public class AmazonListingPageVo extends ListingPageVo {
    private String amazonField;
}
