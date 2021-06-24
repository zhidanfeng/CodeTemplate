package com.zhi.listing.model.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListingPageForm extends BasePageRequest implements Serializable {
    private List<Long> shopIds;

    private Integer matchType;

    private String code;

    private String sku;
}
