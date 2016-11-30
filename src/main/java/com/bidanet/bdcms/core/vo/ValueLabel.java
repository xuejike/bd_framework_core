package com.bidanet.bdcms.core.vo;

/**
 * Created by CF on 2016/5/30.
 */
public class ValueLabel {
    private String value;
    private String label;

    public ValueLabel(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public ValueLabel() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
