package eu.sia.vpos.client.impl.util;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import eu.sia.vpos.client.request.xml.BPWXmlRequest;
import eu.sia.vpos.client.response.xml.BPWXmlResponse;
import eu.sia.vpos.client.utils.constants.Errors;
import eu.sia.vpos.client.utils.exception.VPosClientException;

public class VPosPaymentClient {

    private boolean proxy;
    private boolean ssl;

    private SSLContext sslContext;
    private String proxyName;
    private Integer proxyPort;
    private String proxyUser;
    private String proxyPass;
    private final String urlWebApi;
    private final int timeout;
    private final int socketReadTimeout;

    public VPosPaymentClient(String urlWebApi, int timeout, int socketReadTimeout) {
        this.urlWebApi = urlWebApi;
        this.timeout = timeout;
        this.proxy = false;
        this.ssl = false;
        this.socketReadTimeout = socketReadTimeout;
    }

    public BPWXmlResponse executeCall(BPWXmlRequest input) throws VPosClientException {

        try {
            StringBuilder inputXml = new StringBuilder();
            inputXml.append(parseRequest(input));
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("data", inputXml.toString());
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "OUTPUT XML: \n" + inputXml.toString());
            RestTemplate template = createRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<String> responseEntity = template.exchange(this.urlWebApi, HttpMethod.POST, entity, String.class);

            String xmlResponse = responseEntity.getBody();
            if (HttpStatus.OK != responseEntity.getStatusCode()) {
                throw new VPosClientException("VPOS call failed with code " + responseEntity.getStatusCode().value());
            }

            return parseResponse(xmlResponse);

        } catch (VPosClientException pe) {
            throw pe;
        } catch (Exception e) {
            throw new VPosClientException(e.getMessage(), e);
        }
    }

    private String parseRequest(BPWXmlRequest input) throws VPosClientException {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BPWXmlRequest.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(input, sw);
        } catch (JAXBException e) {
            throw new VPosClientException(Errors.MALFORMED_REQUEST, e);
        }

        return sw.toString();
    }

    private BPWXmlResponse parseResponse(String xmlResponse) throws VPosClientException {
        Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "OUTPUT XML: \n" + xmlResponse);
        try {
            JAXBContext jc = JAXBContext.newInstance(BPWXmlResponse.class);

            Reader reader = new StringReader(xmlResponse);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (BPWXmlResponse) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new VPosClientException(Errors.MALFORMED_RESPONSE, e);
        }
    }

    private RestTemplate createRestTemplate() {

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        if (proxy) {
            HttpHost myProxy = new HttpHost(proxyName, proxyPort);
            if (proxyUser != null && proxyPass != null) {
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(proxyName, proxyPort),
                        new UsernamePasswordCredentials(proxyUser, proxyPass));
                clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
            } else
                clientBuilder.setProxy(myProxy);

        }
        if (ssl) {
            clientBuilder.setSSLContext(sslContext);
        }
        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(this.timeout);
        factory.setReadTimeout(this.socketReadTimeout);
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

    public void setProxy(String proxyName, Integer port, String proxyUser, String proxyPass) {
        this.proxy = true;
        this.proxyName = proxyName;
        this.proxyPort = port;
        this.proxyUser = proxyUser;
        this.proxyPass = proxyPass;
    }

    public void setSslContext(SSLContext context) {
        this.sslContext = context;
        this.ssl = true;
    }
}

