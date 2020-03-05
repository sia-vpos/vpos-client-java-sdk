package eu.sia.vpos.client.impl.util;

import eu.sia.vpos.client.request.xml.BPWXmlRequest;
import eu.sia.vpos.client.response.xml.BPWXmlResponse;
import eu.sia.vpos.client.utils.constants.Errors;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VPosPaymentClient {

    private boolean proxy;
    private boolean ssl;

    private String proxyName;
    private Integer proxyPort;
    private String proxyUser;
    private String proxyPass;
    private String urlWebApi;




    public VPosPaymentClient(String urlWebApi) {
        this.urlWebApi = urlWebApi;
        this.proxy = false;
    }


    public BPWXmlResponse executeCall(BPWXmlRequest input) throws VPosClientException {

        try {
            StringBuilder inputXml = new StringBuilder("data=");
            inputXml.append(parseRequest(input));
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "OUTPUT XML: \n" + inputXml.toString());
            RestTemplate template = createRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<String> entity = new HttpEntity<>(inputXml.toString(), headers);
            ResponseEntity<String> responseEntity = template.exchange(this.urlWebApi, HttpMethod.POST, entity, String.class);

            String xmlResponse = responseEntity.getBody();
            if (HttpStatus.OK != responseEntity.getStatusCode()) {
                throw new VPosClientException("VPOS call failed with code " + responseEntity.getStatusCode().value());
            }

            return parseResponse(xmlResponse);

        } catch (VPosClientException pe) {
            throw pe;
        } catch (Exception e) {
            throw new VPosClientException(e.getMessage(), e.getCause());
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
            throw new VPosClientException(Errors.MALFORMED_REQUEST, e.getCause());
        }

        return sw.toString();
    }

    private BPWXmlResponse parseResponse(String xmlResponse) throws VPosClientException {
        Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "OUTPUT XML: \n" + xmlResponse);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BPWXmlResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlResponse);
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
        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
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

}
