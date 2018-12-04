package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author apladap
 */

@Service
@SuppressWarnings("rawtypes")
public class EGREFFEUtils {

    @Autowired
    private AttachmentHandler attachmentHandler;

    private Logger logger = Logger.getLogger(EGREFFEUtils.class);

    public EgreffeTransmissionVO getListOfWorks(String rawXml, long messageID) throws JAXBException, UnsupportedEncodingException, XMLStreamException {
        String payloadXml = rawXml.replace("urn:eu:ec:cm:common:extensions:v10", "urn:eu:ec:cm:common:extensions:v11");
        TransmissionRequestType transmissionRequestType = (TransmissionRequestType) new XMLHelper().xmlStringToJaxbObject(payloadXml, new ObjectFactory().createTransmissionRequestType());
        EgreffeTransmissionVO eGreffeTransmission = new EgreffeTransmissionVO();
        if(transmissionRequestType.getTransmission() != null){
        	eGreffeTransmission.setComments(transmissionRequestType.getTransmission().getComment() != null?transmissionRequestType.getTransmission().getComment().getValue():"");
            // Go and fetch the list for all of the files attached to the given message ID. This hashmap contains as a key the filename and as a value the file reference id
            HashMap<String, String> messageAttachmentsHashMap = new HashMap<>();

            messageAttachmentsHashMap = attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageID);

            //Parsing ec_workflow element
            if (transmissionRequestType.getTransmission().getWorkflow() != null) {
                List<WorkflowExtensionBaseType> extensions = transmissionRequestType.getTransmission().getWorkflow().getExtension();
                for (WorkflowExtensionBaseType workflowExtensionBaseType : extensions) {
                    if (workflowExtensionBaseType instanceof WorkflowExtensionType) {
                        WorkflowExtensionType workflowExtensionType = (WorkflowExtensionType) workflowExtensionBaseType;
                        if (workflowExtensionType.getAdoptionDateTime() != null) {
                            eGreffeTransmission.setAdoptionDate(workflowExtensionType.getAdoptionDateTime().toGregorianCalendar().getTime());
                        }
                    }
                }

                //Case 1 - The work items are children of the ec_workflow element
                List workList = transmissionRequestType.getTransmission().getWorkflow().getWorkOrWorkReference();
                eGreffeTransmission.setListOfWorks(processData(eGreffeTransmission, messageAttachmentsHashMap, workList));
            }


            //Parsing procedure_interistitutional element
            if (transmissionRequestType.getTransmission().getProcedureInterinstitutional() != null) {
                List<TEventLegal> eventsList = transmissionRequestType.getTransmission().getProcedureInterinstitutional().getEventLegal();

                for (TEventLegal eventLegal : eventsList) {
                    List workList = eventLegal.getWorkOrWorkRef();
                    eGreffeTransmission.setListOfWorks(processData(eGreffeTransmission, messageAttachmentsHashMap, workList));
                }
            }
        }

        return eGreffeTransmission;
    }


    /**
     * @param transmission
     * @param workList
     */
    private List<EgreffeWork> processData(EgreffeTransmissionVO transmission, HashMap<String, String> messageAttachmentsHashMap, List workList) {
        List<EgreffeWork> eGreffeWorkList = new ArrayList<>();

        if (workList != null) {
            for (Object workOrWorkReference : workList) {
                if (workOrWorkReference instanceof TWork) {
                    TWork work = (TWork) workOrWorkReference;

                    //Create one element per act, procedure etc.
                    EgreffeWork egreffeWork = new EgreffeWork();
                    egreffeWork.setAct(work.getIdentifierWork()!=null?work.getIdentifierWork().getValue():"");
                    egreffeWork.setVersion(work.getVersionWork()!=null?work.getVersionWork().getValue():"");

                    List<TWorkExtensionBase> extensionsList = work.getExtension();
                    WorkExtensionType extension = null;
                    for (TWorkExtensionBase extensionBase : extensionsList) {
                        if (extensionBase instanceof WorkExtensionType) {
                            extension = (WorkExtensionType) extensionBase;
                        }
                    }
                    egreffeWork.setSummaryTittle((extension != null && extension.getSummaryTitle() != null) ? extension.getSummaryTitle().getValue() : "");

                    List<String> languagesList = new ArrayList<>();
                    List<Integer> partNumberList = new ArrayList<>();
                    HashMap<String, HashMap<Integer, List<EgreffeDocument>>> documentsHashMap = new HashMap<>();

                    List<TExpression> expressionList = work.getExpression();
                    //The list of Expressions contains the languages so lets order them here
                    Collections.sort(expressionList, new Comparator<TExpression>() {
                        public int compare(TExpression ex1, TExpression ex2) {
                            //Checks if any of the compared languages are null
                        	if(ex1.getLanguage() != null && ex2.getLanguage() != null){
                        		 return ex1.getLanguage().getValue().compareTo(ex2.getLanguage().getValue());
                        	}else if(ex1.getLanguage() == null && ex2.getLanguage() == null){
                        		return 0;
                        	}else if(ex1.getLanguage() == null){
                        		return 1;
                        	}else {
                        		return -1;
                        	}
                        }
                    });
                    for (TExpression expression : expressionList) {
                    	List<TManifestation> manifestationList = expression.getManifestation();
                    	if(manifestationList != null){
                            languagesList.add(expression.getLanguage() != null ? expression.getLanguage().getValue() : null);

                            HashMap<Integer, List<EgreffeDocument>> listOfDocumentsPerPartNumber = new HashMap<>();
                            List<EgreffeDocument> documentsListPerLanguage = new ArrayList<>();

                            for (TManifestation manifestation : manifestationList) {
                                for (TReferenceManifestation tReferenceManifestation : manifestation.getReferenceManifestation()) {
                                    EgreffeDocument document = new EgreffeDocumentFactory().createNewObject();

                                    document.setFormat(tReferenceManifestation.getFormat());
                                    document.setPageCount(tReferenceManifestation.getPageCount());
                                    document.setPartNumber(tReferenceManifestation.getSequence().intValue());
                                    document.setValue(tReferenceManifestation.getValue());

                                    //Search by filename(key) the hashmap and grab the file reference id
                                    if (messageAttachmentsHashMap.containsKey(tReferenceManifestation.getValue())) {
                                        document.setAttachmentReferenceID(tReferenceManifestation.getValue());
                                        document.setAttachmentFileName(messageAttachmentsHashMap.get(tReferenceManifestation.getValue()));
                                    }

                                    //Keep a list with the different part numbers of an ACT
                                    if (!partNumberList.contains(tReferenceManifestation.getSequence().intValue())) {
                                        partNumberList.add(tReferenceManifestation.getSequence().intValue());
                                    }
                                    //We construct here a the list of files per language
                                    documentsListPerLanguage.add(document);
                                }
                            }
                            //Since we have the list of part numbers and list of files we'll construct
                            //a hash map which will contains the list of documents/part number
                            for (Integer partNumber : partNumberList) {
                                List<EgreffeDocument> documentListPerPartAndPerLanguage = new ArrayList<>();
                                for (EgreffeDocument doc : documentsListPerLanguage) {
                                    if (doc.getPartNumber() == partNumber.intValue()) {
                                        //Cross-Check the attachment in the payload with attachments in Bundle
                                        if (messageAttachmentsHashMap.containsKey(doc.getValue())) {
                                            documentListPerPartAndPerLanguage.add(doc);
                                        }
                                    }
                                }
                                listOfDocumentsPerPartNumber.put(partNumber, documentListPerPartAndPerLanguage);
                            }
                            //This check ensures that we are not going to show any files if there's no language element present at the payload
                            if(expression.getLanguage()!=null){
                            	documentsHashMap.put(expression.getLanguage().getValue(), listOfDocumentsPerPartNumber);
                            }
                    	}
                    }
                    //Set the language list
                    //See ETX-837
                    if(partNumberList == null || partNumberList.isEmpty()){
                    	languagesList.clear();
                    }
                    egreffeWork.setLanguages(languagesList);

                    //Order here the list of part numbers
                    Collections.sort(partNumberList, new Comparator<Integer>() {
                        public int compare(Integer int1, Integer int2) {
                            return int1.compareTo(int2);
                        }
                    });
                    //Set the part number list
                    egreffeWork.setPartNumbers(partNumberList);
                    //Set the documents list
                    egreffeWork.setDocumentsList(documentsHashMap);

                    eGreffeWorkList.add(egreffeWork);
                }
            }
        }

        return eGreffeWorkList;
    }

    public Object xmlStringToJaxbObject(String payloadXml, Object jaxbObject) throws JAXBException, UnsupportedEncodingException, XMLStreamException {
        JAXBContext jaxbContext = JAXBContext.newInstance(jaxbObject.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream is = new ByteArrayInputStream(payloadXml.getBytes("UTF-8"));
        JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(new StreamSource(is), jaxbObject.getClass());

        return root.getValue();
    }

    private class XMLHelper {
        public Object xmlStringToJaxbObject(String payloadXml, Object jaxbObject) throws JAXBException, UnsupportedEncodingException, XMLStreamException {
            JAXBContext jaxbContext = JAXBContext.newInstance(jaxbObject.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream is = new ByteArrayInputStream(payloadXml.getBytes("UTF-8"));
            JAXBElement<Object>  root = (JAXBElement<Object>) unmarshaller.unmarshal(new StreamSource(is), jaxbObject.getClass());

            return root.getValue();
        }
    }
}
