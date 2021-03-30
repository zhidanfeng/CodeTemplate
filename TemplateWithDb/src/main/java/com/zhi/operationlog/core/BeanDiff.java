package com.zhi.operationlog.core;

import java.util.ArrayList;
import java.util.List;

public class BeanDiff {
    /**
     * 所有差异字段list
     */
    private List<FieldDiff> fieldDiffList = new ArrayList<>();

    public void addFieldDiff(FieldDiff fieldDiff) {
        this.fieldDiffList.add(fieldDiff);
    }

    public List<FieldDiff> getFieldDiffList() {
        return fieldDiffList;
    }

    public void setFieldDiffList(List<FieldDiff> fieldDiffList) {
        this.fieldDiffList = fieldDiffList;
    }
}
