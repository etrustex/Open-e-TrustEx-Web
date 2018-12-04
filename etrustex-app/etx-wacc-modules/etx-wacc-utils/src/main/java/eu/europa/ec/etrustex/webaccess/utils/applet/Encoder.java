/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.utils.applet;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.zip.GZIPOutputStream;

/**
 * @author apladap
 *
 */
public class Encoder {

    public static <T extends Serializable> String encode(T fileElement) throws IOException {
        return fileElement != null ? encode(Collections.singletonList(fileElement)) : "";
    }

    public static String encode(Collection<? extends Serializable> fileList) throws IOException {
        if (fileList != null && !fileList.isEmpty()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(baos))) {
				oos.writeInt(fileList.size());
				for (Serializable serializable : fileList) {
					oos.writeObject(serializable);
				}
			}
			return DatatypeConverter.printBase64Binary(baos.toByteArray());
        } else {
            return "";
		}
    }
}
