package eu.sia.vpos.client.impl;

import eu.sia.vpos.client.Config;
import eu.sia.vpos.client.utils.constants.ConfigConstants;
import eu.sia.vpos.client.utils.constants.Constants;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;

import java.util.Properties;

public class VPosConfig implements Config {

    private String shopID;

    private String redirectKey;

    private String redirectUrl;

    private String apiKey;

    private String proxyHost;

    private int proxyPort;

    private String proxyUsername;

    private String proxyPassword;

    private String apiUrl;

    private String algorithm;

    public VPosConfig() {
    }

    public VPosConfig(Properties properties) {
        this.shopID = properties.getProperty(ConfigConstants.SHOPID);
        this.apiUrl = properties.getProperty(ConfigConstants.APIURL);
        this.apiKey = properties.getProperty(ConfigConstants.APIRESULTKEY);
        this.redirectUrl = properties.getProperty(ConfigConstants.REDIRECTURL);
        this.redirectKey = properties.getProperty(ConfigConstants.REDIRECTKEY);
        this.proxyHost = properties.getProperty(ConfigConstants.PROXYHOST);
        String port = properties.getProperty(ConfigConstants.PROXYPORT);
        if(port != null){
            this.proxyPort = Integer.parseInt(port);
        }
        this.proxyPassword = properties.getProperty(ConfigConstants.PROXYPASS);
        this.proxyUsername = properties.getProperty(ConfigConstants.PROXYUSERNAME);
        String alg=properties.getProperty(ConfigConstants.MACALG, Constants.DEFAULT_ALG);
        if(MacAlgorithms.parse(alg))
            this.algorithm = alg;
        else{
            this.algorithm = Constants.DEFAULT_ALG;
        }

    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getRedirectKey() {
        return redirectKey;
    }

    public void setRedirectKey(String redirectKey) {
        this.redirectKey = redirectKey;
    }

    @Override
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String algorithm) {
        if(MacAlgorithms.parse(algorithm))
            this.algorithm = algorithm;
        else{
            this.algorithm = Constants.DEFAULT_ALG;
        }
    }
}
