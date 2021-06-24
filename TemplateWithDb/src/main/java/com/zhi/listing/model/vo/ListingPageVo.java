package com.zhi.listing.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@ToString(callSuper = true)
public class ListingPageVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String commonField;
}
