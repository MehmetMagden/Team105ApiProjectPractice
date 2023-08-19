package pojos;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "message",
        "Token_remaining_time",
        "lists"
})

public class Deneme111Pojo {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("Token_remaining_time")
    private Integer tokenRemainingTime;
    @JsonProperty("lists")
    private java.util.List<pojos.List> lists;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("Token_remaining_time")
    public Integer getTokenRemainingTime() {
        return tokenRemainingTime;
    }

    @JsonProperty("Token_remaining_time")
    public void setTokenRemainingTime(Integer tokenRemainingTime) {
        this.tokenRemainingTime = tokenRemainingTime;
    }

    @JsonProperty("lists")
    public java.util.List<pojos.List> getLists() {
        return lists;
    }

    @JsonProperty("lists")
    public void setLists(java.util.List<pojos.List> lists) {
        this.lists = lists;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Deneme111Pojo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("tokenRemainingTime");
        sb.append('=');
        sb.append(((this.tokenRemainingTime == null)?"<null>":this.tokenRemainingTime));
        sb.append(',');
        sb.append("lists");
        sb.append('=');
        sb.append(((this.lists == null)?"<null>":this.lists));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
