/**
 *
 */
package eu.europa.ec.etrustex.webaccess.utils.applet;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author apladap
 */
public class Decoder {

    public static <T extends Serializable> List<T> decode(String str, Class<T> clazz) throws IOException, ClassNotFoundException {

        if (str != null && !str.isEmpty()) {
            byte[] data = DatatypeConverter.parseBase64Binary(str);
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            try (ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(bais))) {
                int listSize = ois.readInt();
                List<T> list = new ArrayList<>();
                for (int i = 0; i < listSize; i++) {
                    list.add((T) ois.readObject());
                }
                return list;
            }
        } else {
            return Collections.emptyList();
        }
    }

}
