package eu.sia.vpos.client.impl.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VPosPaymentClient {

    private boolean proxy;
    private boolean ssl;

    private SSLContext sslContext;
    private String proxyName;
    private Integer proxyPort;
    private String proxyUser;
    private String proxyPass;
    private String urlWebApi;


    public VPosPaymentClient(String urlWebApi) {
        this.urlWebApi = urlWebApi;
        this.proxy = false;
        this.ssl = false;
    }


    public BPWXmlResponse executeCall(BPWXmlRequest input) throws VPosClientException {

        try {
            StringBuilder inputXml = new StringBuilder();
            inputXml.append(parseRequest(input));
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
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
            throw new VPosClientException(e.getMessage(), e.getCause());
        }
    }

    public BPWXmlResponse executeCall(MultiValueMap<String,String> params) throws VPosClientException {

        try {
            RestTemplate template = createRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
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
            JAXBContext jc = JAXBContext.newInstance(BPWXmlResponse.class);

            //XMLInputFactory xif = XMLInputFactory.newInstance();
            Reader reader = new StringReader(xmlResponse);
            //XMLStreamReader xsr = xif.createXMLStreamReader( reader);
            //xsr = new MyStreamReaderDelegate(xsr);
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
        factory.setConnectTimeout(50000);
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

    private static class MyStreamReaderDelegate extends StreamReaderDelegate {

        public MyStreamReaderDelegate(XMLStreamReader xsr) {
            super(xsr);
        }

        @Override
        public String getAttributeLocalName(int index) {
            return super.getAttributeLocalName(index).toLowerCase();
        }

        @Override
        public String getLocalName() {
            return super.getLocalName().toLowerCase();
        }

    }
}

