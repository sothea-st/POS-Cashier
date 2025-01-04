/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feature.Stock.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sothea
 */
public class ImageDataSuccess {

    private String msg;
    private ImageResponse[] data;

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }
    

    @JsonProperty("msg")
    public void setMsg(String value) {
        this.msg = value;
    }

    @JsonProperty("data")
    public ImageResponse[] getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(ImageResponse[] value) {
        this.data = value;
    }
}
