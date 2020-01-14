package it.reply.cof.apos.utils;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.exception.COFException;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class AposPaymentClient {

    private static final String CLASSNAME = AposPaymentClient.class.getSimpleName();

    private String urlWebApi;

    public AposPaymentClient(String urlWebApi) {
        this.urlWebApi = urlWebApi;
    }

    public AposPaymentClient() {
        this.urlWebApi = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    }

    public BPWXmlResponse executeCall(BPWXmlRequest input) throws COFException {

        try {
            StringBuilder inputXml = new StringBuilder("DATA=");
            inputXml.append(parseRequest(input));

            ResteasyClient client = getClientBuilder();
            WebTarget target = client.target(this.urlWebApi);
            Invocation.Builder builder = target.request();
            Entity<String> entity = Entity.entity(inputXml.toString(), MediaType.APPLICATION_FORM_URLENCODED);
            Response response = builder.post(entity);
            String xmlResponse = response.readEntity(String.class);

            if (org.apache.http.HttpStatus.SC_OK != response.getStatus()) {
                //PGLogger.info("########### ERROR: HTTP STATUS " + response.getStatus() + " ###########", CLASSNAME);
                throw new COFException(Constants.TransactionStatus.AUTHORIZATION_IN_PROGRESS.getValue());
            }
            //PGLogger.info(xmlResponse, CLASSNAME);
            return parseResponse(xmlResponse);

        } catch (COFException pe) {
            throw pe;
        } catch (Exception e) {
            //PGLogger.debug("########### @POS CALL GENERIC ERROR #############" + e.getMessage(), CLASSNAME);
            throw new COFException(Constants.TransactionStatus.AUTHORIZATION_IN_PROGRESS.getValue(), e.getCause());
        }
    }

    private String parseRequest(BPWXmlRequest input) throws COFException {

        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BPWXmlRequest.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
            marshaller.marshal(input, sw);
        } catch (JAXBException e) {
            //PGLogger.info("########### ERROR IN PARSING @POS REQUEST #################", CLASSNAME);
            throw new COFException(Constants.TransactionStatus.AUTHORIZATION_IN_PROGRESS.getValue(), e.getCause());
        }
        return sw.toString();
    }

    private BPWXmlResponse parseResponse(String xmlResponse) throws COFException {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BPWXmlResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmlResponse);
            return (BPWXmlResponse) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            //PGLogger.info("########### ERROR IN PARSING @POS RESPONSE #################", CLASSNAME);
            throw new COFException(Constants.TransactionStatus.AUTHORIZATION_IN_PROGRESS.getValue(), e.getCause());
        }
    }

    private ResteasyClient getClientBuilder() {
            ResteasyClientBuilder restBuilder = new ResteasyClientBuilder();
            return restBuilder.build();
    }

}
