package com.example.summerad;

import java.io.Serializable;

import lombok.Data;


public class PubNotice {
    private Integer pubAnnouncementId;

    private String pubAnnouncementContent;

    private Integer pubAnnouncementTextcolorR;
    private Integer pubAnnouncementTextcolorG;
    private Integer pubAnnouncementTextcolorB;

    private Integer pubAnnouncementTextsize;

    private String pubAnnouncementBgcolor;

    public Integer getPubAnnouncementId() {
        return pubAnnouncementId;
    }

    public void setPubAnnouncementId(Integer pubAnnouncementId) {
        this.pubAnnouncementId = pubAnnouncementId;
    }

    public String getPubAnnouncementContent() {
        return pubAnnouncementContent;
    }

    public void setPubAnnouncementContent(String pubAnnouncementContent) {
        this.pubAnnouncementContent = pubAnnouncementContent;
    }

    public Integer getPubAnnouncementTextcolorR() {
        return pubAnnouncementTextcolorR;
    }

    public void setPubAnnouncementTextcolorR(Integer pubAnnouncementTextcolorR) {
        this.pubAnnouncementTextcolorR = pubAnnouncementTextcolorR;
    }

    public Integer getPubAnnouncementTextcolorG() {
        return pubAnnouncementTextcolorG;
    }

    public void setPubAnnouncementTextcolorG(Integer pubAnnouncementTextcolorG) {
        this.pubAnnouncementTextcolorG = pubAnnouncementTextcolorG;
    }

    public Integer getPubAnnouncementTextcolorB() {
        return pubAnnouncementTextcolorB;
    }

    public void setPubAnnouncementTextcolorB(Integer pubAnnouncementTextcolorB) {
        this.pubAnnouncementTextcolorB = pubAnnouncementTextcolorB;
    }

    public Integer getPubAnnouncementTextsize() {
        return pubAnnouncementTextsize;
    }

    public void setPubAnnouncementTextsize(Integer pubAnnouncementTextsize) {
        this.pubAnnouncementTextsize = pubAnnouncementTextsize;
    }

    public String getPubAnnouncementBgcolor() {
        return pubAnnouncementBgcolor;
    }

    public void setPubAnnouncementBgcolor(String pubAnnouncementBgcolor) {
        this.pubAnnouncementBgcolor = pubAnnouncementBgcolor;
    }

    public Integer getPubAnnouncementBghigh() {
        return pubAnnouncementBghigh;
    }

    public void setPubAnnouncementBghigh(Integer pubAnnouncementBghigh) {
        this.pubAnnouncementBghigh = pubAnnouncementBghigh;
    }

    public String getPubAnnouncementTexthigh() {
        return pubAnnouncementTexthigh;
    }

    public void setPubAnnouncementTexthigh(String pubAnnouncementTexthigh) {
        this.pubAnnouncementTexthigh = pubAnnouncementTexthigh;
    }

    public String getPubAnnouncementPlayspeed() {
        return pubAnnouncementPlayspeed;
    }

    public void setPubAnnouncementPlayspeed(String pubAnnouncementPlayspeed) {
        this.pubAnnouncementPlayspeed = pubAnnouncementPlayspeed;
    }

    private Integer pubAnnouncementBghigh;

    private String pubAnnouncementTexthigh;

    private String pubAnnouncementPlayspeed;

    public PubNotice() {
    }

//    public PubNotice(Integer pubNoticeId, String pubNoticeContent, String pubNoticeTextcolor, Integer pubNoticeTextsize, String pubNoticeBgcolor, Integer pubNoticeBghigh, String pubNoticeTexthigh, String pubNoticePlayspeed) {
//        this.pubNoticeId = pubNoticeId;
//        this.pubNoticeContent = pubNoticeContent;
//        this.pubNoticeTextcolor = pubNoticeTextcolor;
//        this.pubNoticeTextsize = pubNoticeTextsize;
//        this.pubNoticeBgcolor = pubNoticeBgcolor;
//        this.pubNoticeBghigh = pubNoticeBghigh;
//        this.pubNoticeTexthigh = pubNoticeTexthigh;
//        this.pubNoticePlayspeed = pubNoticePlayspeed;
//    }
}
