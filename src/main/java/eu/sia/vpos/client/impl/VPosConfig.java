package eu.sia.vpos.client.impl;

import eu.sia.vpos.client.Config;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;

import java.util.Properties;

public class VPosConfig implements Config {

    private String shopID;

    private String redirectKey;

    private String apiKey;

    private String proxyHost;

    private int proxyPort;

    private String proxyUsername;

    private String proxyPassword;

    private String url;

    private MacAlgorithms algorithm;

    public VPosConfig() {
    }

    public VPosConfig(Properties properties) {
        // TODO: loads from properties file
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VPosConfig withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public VPosConfig withShopID(String shopID) {
        this.shopID = shopID;
        return this;
    }


    public String getRedirectKey() {
        return redirectKey;
    }

    public void setRedirectKey(String redirectKey) {
        this.redirectKey = redirectKey;
    }

    public VPosConfig withRedirectKey(String redirectKey) {
        this.redirectKey = redirectKey;
        return this;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public VPosConfig withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public VPosConfig withProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
        return this;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public VPosConfig withProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public VPosConfig withProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
        return this;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public VPosConfig withProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
        return this;
    }

    public MacAlgorithms getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(MacAlgorithms algorithm) {
        this.algorithm = algorithm;
    }
}
