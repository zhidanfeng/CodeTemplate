package com.zhi.operationlog.core;

import lombok.Data;

@Data
public class FieldDiff {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段英文名
     */
    private String fieldEnName;

    /**
     * 字段中文名
     */
    private String fieldCnName;

    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 旧值
     */
    private Object oldValue;

    /**
     * 新值
     */
    private Object newValue;

    public FieldDiff(String tableName, String fieldEnName, String fieldCnName, Object oldValue, Object newValue) {
        this.tableName = tableName;
        this.fieldEnName = fieldEnName;
        this.fieldCnName = fieldCnName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // 这里省略get set 方法

    @Override
    public String toString() {
        String oldVal = this.oldValue == null ? "" : this.oldValue.toString();
        String newVal = this.newValue == null ? "" : this.newValue.toString();
        return String.format("将表【%s】中的字段【%s】从【%s】修改为【%s】", this.tableName, this.fieldCnName, oldVal, newVal);
    }
}
