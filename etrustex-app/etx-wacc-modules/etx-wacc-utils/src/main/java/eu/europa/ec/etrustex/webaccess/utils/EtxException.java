package eu.europa.ec.etrustex.webaccess.utils;

/**
 * Exception for the persistence layer. All DAO methods should throw
 * this exception only.
 *
 * @author apladap
 */
public class EtxException extends RuntimeException {

    private static final long serialVersionUID = 5555896615720216763L;

    public EtxException() {
    }

    public EtxException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtxException(String message) {
        super(message);
    }

}
