package com.example.demo.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 实体类
 */
@TableName("soil")
public class Soil {
    @TableId(value = "id")
    private int id;
    private String title;
    private String origin;
    private String url;
    private Date releaseDate;
    private String summary;
    private String text;
    private String author;
    private String category;
    @TableField("deleted")
    private int delated;
    @TableField("get_time")
    private Date getTime;
    private Date createTime;
    private Date updateTime;


    @Override
    public String toString() {
        return "Soil{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", origin='" + origin + '\'' +
                ", url='" + url + '\'' +
                ", releaseDate=" + releaseDate +
                ", summary='" + summary + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", delated=" + delated +
                ", getTime=" + getTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDelated() {
        return delated;
    }

    public void setDelated(int delated) {
        this.delated = delated;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
