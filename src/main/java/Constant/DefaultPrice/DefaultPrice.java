package Constant.DefaultPrice;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author FRONT-END.06
 */
public class DefaultPrice {
     private long id;
    private Object userID;
    private long defaultPriceUsd;
    private long defaultPriceKhr;
    private boolean status;
    private String createDate;
    private long createBy;
    private boolean deleted;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("userId")
    public Object getUserID() { return userID; }
    @JsonProperty("userId")
    public void setUserID(Object value) { this.userID = value; }

    @JsonProperty("defaultPriceUsd")
    public long getDefaultPriceUsd() { return defaultPriceUsd; }
    @JsonProperty("defaultPriceUsd")
    public void setDefaultPriceUsd(long value) { this.defaultPriceUsd = value; }

    @JsonProperty("defaultPriceKhr")
    public long getDefaultPriceKhr() { return defaultPriceKhr; }
    @JsonProperty("defaultPriceKhr")
    public void setDefaultPriceKhr(long value) { this.defaultPriceKhr = value; }

    @JsonProperty("status")
    public boolean getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(boolean value) { this.status = value; }

    @JsonProperty("createDate")
    public String getCreateDate() { return createDate; }
    @JsonProperty("createDate")
    public void setCreateDate(String value) { this.createDate = value; }

    @JsonProperty("createBy")
    public long getCreateBy() { return createBy; }
    @JsonProperty("createBy")
    public void setCreateBy(long value) { this.createBy = value; }

    @JsonProperty("deleted")
    public boolean getDeleted() { return deleted; }
    @JsonProperty("deleted")
    public void setDeleted(boolean value) { this.deleted = value; }
}
