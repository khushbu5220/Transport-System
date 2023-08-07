package com.csir.vehiclerequisition.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    private String ipAddress;

    private String webBrowser;

    private String geoLocation;

    private Date loginDt;

    private Long userId;

    public Session(){
        super();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getWebBrowser() {
        return webBrowser;
    }

    public void setWebBrowser(String webBrowser) {
        this.webBrowser = webBrowser;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public Date getLoginDt() {
        return loginDt;
    }

    public void setLoginDt(Date date) {
        this.loginDt = date;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", ipAddress='" + ipAddress + '\'' +
                ", webBrowser='" + webBrowser + '\'' +
                ", geoLocation='" + geoLocation + '\'' +
                ", loginDt='" + loginDt + '\'' +
                ", user=" + userId +
                '}';
    }

    public Session(Long sessionId, String ipAddress, String webBrowser, String geoLocation, Date loginDt, Long userId) {
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
        this.webBrowser = webBrowser;
        this.geoLocation = geoLocation;
        this.loginDt = loginDt;
        this.userId = userId;
    }
}