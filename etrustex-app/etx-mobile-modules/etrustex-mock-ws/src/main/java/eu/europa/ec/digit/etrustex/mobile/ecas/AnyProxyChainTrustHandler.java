package eu.europa.ec.digit.etrustex.mobile.ecas;

import eu.cec.digit.ecas.client.validation.ProxyChainTrustHandlerIntf;

import java.io.Serializable;

/**
 * Proxy chain trust handler that accepts any proxy.
 *
 * @author autredr
 * @author burjalu
 */
public class AnyProxyChainTrustHandler implements ProxyChainTrustHandlerIntf, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String[] getTrustedProxies() {
        return new String[]{};
    }

    @Override
    public void setTrustedProxies(String[] trustedProxies) {
        // noop implementation
    }

    @Override
    public void validate(String[] proxyChain) {
        // noop implementation
    }

}
