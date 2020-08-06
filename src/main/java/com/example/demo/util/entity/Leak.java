package com.example.demo.util.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author qp
 * 漏洞实体类
 */
@TableName("t_leak")
public class Leak {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 来源网站
     */
    private String url;

    /**
     * CNNVD编号
     */
    @TableField("cnnvdTitle")
    private String cnnvdTitle;

    /**
     * CVE编号
     */
    @TableField("cveTitle")
    private String cveTitle;

    /**
     * 发布时间
     */
    @TableField("newsTime")
    private Date newsTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 漏洞来源
     */
    @TableField("leadSources")
    private String leadSources;

    /**
     * 危害等级
     */
    @TableField("hazardLevel")
    private String hazardLevel;

    /**
     * 漏洞类型
     */
    @TableField("leakType")
    private String leakType;

    /**
     * 威胁类型
     */
    @TableField("threatType")
    private String threatType;

    /**
     * 厂商
     */
    @TableField("firm")
    private String firm;

    /**
     * 漏洞简介
     */
    @TableField("leakProfile")
    private String leakProfile;

    /**
     * 漏洞公告
     */
    @TableField("leakKnow")
    private String leakKnow;

    /**
     * 参考网址
     */
    @TableField("referenceWebsite")
    private String referenceWebsite;

    /**
     * 受影响实体
     */
    @TableField("affectedEntity")
    private String affectedEntity;

    /**
     * 补丁
     */
    private String patch;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnnvdTitle() {
        return cnnvdTitle;
    }

    public void setCnnvdTitle(String cnnvdTitle) {
        this.cnnvdTitle = cnnvdTitle;
    }

    public String getCveTitle() {
        return cveTitle;
    }

    public void setCveTitle(String cveTitle) {
        this.cveTitle = cveTitle;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLeadSources() {
        return leadSources;
    }

    public void setLeadSources(String leadSources) {
        this.leadSources = leadSources;
    }

    public String getHazardLevel() {
        return hazardLevel;
    }

    public void setHazardLevel(String hazardLevel) {
        this.hazardLevel = hazardLevel;
    }

    public String getLeakType() {
        return leakType;
    }

    public void setLeakType(String leakType) {
        this.leakType = leakType;
    }

    public String getThreatType() {
        return threatType;
    }

    public void setThreatType(String threatType) {
        this.threatType = threatType;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getLeakProfile() {
        return leakProfile;
    }

    public void setLeakProfile(String leakProfile) {
        this.leakProfile = leakProfile;
    }

    public String getLeakKnow() {
        return leakKnow;
    }

    public void setLeakKnow(String leakKnow) {
        this.leakKnow = leakKnow;
    }

    public String getReferenceWebsite() {
        return referenceWebsite;
    }

    public void setReferenceWebsite(String referenceWebsite) {
        this.referenceWebsite = referenceWebsite;
    }

    public String getAffectedEntity() {
        return affectedEntity;
    }

    public void setAffectedEntity(String affectedEntity) {
        this.affectedEntity = affectedEntity;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Leak{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", cnnvdTitle='" + cnnvdTitle + '\'' +
                ", cveTitle='" + cveTitle + '\'' +
                ", newsTime=" + newsTime +
                ", updateTime=" + updateTime +
                ", leadSources='" + leadSources + '\'' +
                ", hazardLevel='" + hazardLevel + '\'' +
                ", leakType='" + leakType + '\'' +
                ", threatType='" + threatType + '\'' +
                ", firm='" + firm + '\'' +
                ", leakProfile='" + leakProfile + '\'' +
                ", leakKnow='" + leakKnow + '\'' +
                ", referenceWebsite='" + referenceWebsite + '\'' +
                ", affectedEntity='" + affectedEntity + '\'' +
                ", patch='" + patch + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
