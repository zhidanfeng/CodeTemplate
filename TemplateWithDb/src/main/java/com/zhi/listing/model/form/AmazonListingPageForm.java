package com.zhi.listing.model.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class AmazonListingPageForm extends ListingPageForm implements Serializable {
    private String amazon;
}
