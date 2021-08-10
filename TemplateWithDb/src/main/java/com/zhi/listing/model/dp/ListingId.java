package com.zhi.listing.model.dp;

import lombok.SneakyThrows;

import javax.xml.bind.ValidationException;

public class ListingId {
    private final Long id;

    public Long getValue() {
        return id;
    }

    @SneakyThrows
    public ListingId(Long id) {
        if (id == null) {
            throw new ValidationException("listing id 不能为空");
        }
        this.id = id;
    }
}
