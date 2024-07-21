package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Version {

    private Integer versionId;
    private String updateTime;

    public Integer getVersionId() {
        return versionId;
    }

    @JsonProperty("VersionID")
    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("UpdateTime")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
