package net.zaolunzi.framework.bean;

/**
 * 返回数据对象
 * @Author: SelectBook
 * @Date: 2022/5/20 1:26
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;
    
    public Data(Object model) {
        this.model = model;
    }
    
    public Object getModel() {
        return model;
    }
}
