package net.zaolunzi.framework.bean;

/**
 * 封装表单参数
 * @Author: SelectBook
 * @Date: 2022/5/20 1:25
 */
public class FormParam {
    private String fieldName;
    private Object fieldValue;
    
    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public Object getFieldValue() {
        return fieldValue;
    }
}
