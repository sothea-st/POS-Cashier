package feature.Stock.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageResponse {

    private String fileName;
    private String uuid;

    @JsonProperty("fileName")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("fileName")
    public void setFileName(String value) {
        this.fileName = value;
    }

    @JsonProperty("uuid")
    public String getUUID() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUUID(String value) {
        this.uuid = value;
    }
}
