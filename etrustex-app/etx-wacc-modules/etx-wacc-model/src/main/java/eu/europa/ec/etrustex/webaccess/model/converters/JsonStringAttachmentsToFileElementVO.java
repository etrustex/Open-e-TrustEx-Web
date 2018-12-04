package eu.europa.ec.etrustex.webaccess.model.converters;

import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.vo.FileElementVO;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by canofcr on 23/08/2017.
 */
public class JsonStringAttachmentsToFileElementVO {

    private static JsonArray jsonFromString(String jsonObjectStr) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonArray array = jsonReader.readArray();
        jsonReader.close();

        return array;
    }

    public static List<FileElementVO> convert(String jsonStringAttachments){

        List<FileElementVO> fileElementVOS= new LinkedList<>();
        JsonArray jsonArray= jsonFromString(jsonStringAttachments);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject=jsonArray.getJsonObject(i);
            FileElementVO fileElementVO = new FileElementVO();
            fileElementVO.setId(jsonObject.containsKey("id") ? jsonObject.getJsonNumber("id").longValue() : null);
            fileElementVO.setFileReferenceId(jsonObject.containsKey("fileReferenceId")?jsonObject.getString("fileReferenceId"):null);
            fileElementVO.setFileSize(jsonObject.containsKey("fileSize")? jsonObject.getJsonNumber("fileSize").longValue() : null);
            fileElementVO.setFileName(jsonObject.containsKey("fileName")?jsonObject.getString("fileName"):null);
            fileElementVO.setComment(jsonObject.containsKey("comment")?jsonObject.getString("comment"):null);
            fileElementVO.setConfidential(jsonObject.containsKey("confidential")?jsonObject.getBoolean("confidential"):null);
            fileElementVO.setTransmissionChecksum( jsonObject.containsKey("transmissionChecksum")? jsonObject.getString("transmissionChecksum").getBytes() : null);
            fileElementVO.setTransmissionChecksumMethod(jsonObject.containsKey("transmissionChecksumMethod")?jsonObject.getString("transmissionChecksumMethod"):null);
            fileElementVO.setMimeType(jsonObject.containsKey("mimeType")?jsonObject.getString("mimeType"):null);
            fileElementVO.setPath(jsonObject.containsKey("path") ? jsonObject.getString("path") : null);
            boolean isPayload = jsonObject.containsKey("isPayload")&& jsonObject.getBoolean("isPayload");
            if (isPayload) {
                fileElementVO.setType(AttachmentType.METADATA);
            }
            fileElementVOS.add(fileElementVO);
        }



        return fileElementVOS;
    }
}
