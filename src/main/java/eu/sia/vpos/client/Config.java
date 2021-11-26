package eu.sia.vpos.client;

import javax.net.ssl.SSLContext;

public interface Config {

    String getApiUrl();

    void setApiUrl(String apiUrl);

    String getShopID();

    void setShopID(String shopID);

    String getRedirectKey();

    void setRedirectKey(String redirectKey);

    String getRedirectUrl();

    void setRedirectUrl(String redirectUrl);

    String getApiKey();

    void setApiKey(String apiKey);

    String getProxyHost();

    void setProxyHost(String proxyHost);

    Integer getProxyPort();

    void setProxyPort(int proxyPort);

    String getProxyUsername();

    void setProxyUsername(String proxyUsername);

    String getProxyPassword();

    void setProxyPassword(String proxyPassword);

    String getAlgorithm();

    void setAlgorithm(String algorithm);

    SSLContext getSslContext();

    void setSslContext(SSLContext sslContext);

    String getTimeout();

    void setTimeout(String timeout);

    boolean isDisableResponseMACCheck();

    void setDisableResponseMACCheck(boolean disableResponseMACCheck);

    String getSocketReadTimeout();

    void setSocketReadTimeout(String socketReadTimeout);

}
