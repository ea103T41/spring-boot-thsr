package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "latest_version")
public class LatestVersion implements Serializable {

    private Integer id;
    private String versionType;
    private Integer versionId;
    private String updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    @Column(name = "version_id")
    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    @Column
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
