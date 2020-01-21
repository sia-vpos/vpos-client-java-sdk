package it.reply.cof.apos.utils;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.utils.constants.Errors;
import it.reply.cof.utils.exception.COFException;
import org.apache.http.ssl.SSLContexts;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AposPaymentClient {

    private boolean proxy;
    private boolean ssl;

    private String proxyName;
    private Integer proxyPort;
    private String urlWebApi;
    private String ppp;
    private File kFile;
    private KeyStore ks;

    public AposPaymentClient() {
        this("https://atpostest.ssb.it/atpos/apibo/apiBOXML.app");
    }

    public AposPaymentClient(String urlWebApi) {
        this.urlWebApi = urlWebApi;
    }

    public AposPaymentClient(String urlWebApi, String proxyName, Integer port) {
        this(urlWebApi);
        this.proxy = true;
        this.proxyName = proxyName;
        this.proxyPort = port;
    }

    public AposPaymentClient(String urlWebApi, String ppp, KeyStore ks, File kFile) {
        this(urlWebApi);
        this.ssl = true;
        this.ppp = ppp;
        this.ks = ks;
        this.kFile = kFile;
    }

    public BPWXmlResponse executeCall(BPWXmlRequest input) throws COFException {

        try {
            StringBuilder inputXml = new StringBuilder("data=");
            inputXml.append(parseRequest(input));
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "OUTPUT XML: \n" + inputXml.toString());

            ResteasyClient client = getClientBuilder();
            WebTarget target = client.target(this.urlWebApi);
            Invocation.Builder builder = target.request();
            Entity<String> entity = Entity.entity(inputXml.toString(), MediaType.APPLICATION_FORM_URLENCODED);
            Response response = builder.post(entity);
            String xmlResponse = response.readEntity(String.class);

            if (org.apache.http.HttpStatus.SC_OK != response.getStatus()) {
                throw new COFException("VPOS call failed with code " + response.getStatus());
            }

            return parseResponse(xmlResponse);

        } catch (COFException pe) {
            throw pe;
        } catch (Exception e) {
            throw new COFException(e.getMessage(), e.getCause());
        }
    }

    private String parseRequest(BPWXmlRequest input) throws COFException {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BPWXmlRequest.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(input, sw);
        } catch (JAXBException e) {
            throw new COFException(Errors.MALFORMED_REQUEST, e.getCause());
        }

        return sw.toString();
    }

    private BPWXmlResponse parseResponse(String xmlResponse) throws COFException {
        Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "OUTPUT XML: \n" + xmlResponse);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BPWXmlResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlResponse);
            return (BPWXmlResponse) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new COFException(Errors.MALFORMED_RESPONSE, e);
        }
    }

    private ResteasyClient getClientBuilder() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, KeyManagementException {
        ResteasyClientBuilder restBuilder = new ResteasyClientBuilder();

        if (proxy)
            restBuilder.defaultProxy(proxyName, proxyPort);

        if (ssl) {
            SSLContext sslcontext = SSLContexts.custom().setProtocol("TLS")
                    .loadKeyMaterial(ks, ppp.toCharArray())
                    .loadTrustMaterial(kFile, ppp.toCharArray()).build();
            restBuilder.sslContext(sslcontext);
        }

        return restBuilder.build();
    }

    public void setProxy(String proxyName, int proxyPort) {
        proxy = true;
        this.proxyName = proxyName;
        this.proxyPort = proxyPort;
    }

}
