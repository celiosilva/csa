package br.com.delogic.cbsa.controller.bean;

public class LabelValueModel {

    private String label;
    private String value;

    public LabelValueModel(String label, String val) {
        this.label = label;
        this.value = val;
    }

    public LabelValueModel() {}

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
