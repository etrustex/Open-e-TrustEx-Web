package eu.europa.ec.etrustex.webaccess.business.api;

import java.util.Map;

public interface VelocityService {

    /**
     * Merge template with the model.
     *
     * @param templateFile The template.
     * @param model        The model used in context.
     * @return Generated text.
     */
    String applyTemplate(String templateFile, Map<String, Object> model);

    /**
     * Find resource in velocity classpath.
     *
     * @param templateFile File name.
     * @return <code>true</code> if file exists in velocity classpath, <code>false</code> otherwise.
     */
    boolean resourceExists(String templateFile);
}
