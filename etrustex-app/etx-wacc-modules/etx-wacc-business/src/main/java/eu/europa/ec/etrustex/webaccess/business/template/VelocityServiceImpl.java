package eu.europa.ec.etrustex.webaccess.business.template;

import eu.europa.ec.etrustex.webaccess.business.api.VelocityService;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.util.Map;

@Service
public class VelocityServiceImpl implements VelocityService {

    private final static Logger logger = Logger.getLogger(VelocityServiceImpl.class);

    @Autowired
    private VelocityEngine velocityEngine;

    @PostConstruct
    public void init() {
        Velocity.addProperty(VelocityEngine.RESOURCE_LOADER, "class");
        Velocity.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    @Override
    public String applyTemplate(String templateFile, Map<String, Object> model) {
        VelocityContext context = new VelocityContext(model);
        try {
            Template template = null;
            if (velocityEngine != null && velocityEngine.resourceExists(templateFile)) {
                template = velocityEngine.getTemplate(templateFile);
            } else if (Velocity.resourceExists(templateFile)) {
                template = Velocity.getTemplate(templateFile);
            }

            if (template != null) {
                StringWriter stringWriter = new StringWriter();
                template.merge(context, stringWriter);
                return stringWriter.toString();
            } else {
                logger.error("The following resource template could not be found: " + templateFile + "; Please report!");
            }
        } catch (Exception e) {
            logger.error("A Velocity error occurred; please report!", e);
        }
        return null;
    }

    @Override
    public boolean resourceExists(String templateFile) {
        if (velocityEngine != null && velocityEngine.resourceExists(templateFile)) {
            return true;
        }
        if (Velocity.resourceExists(templateFile)) {
            return true;
        }
        return false;
    }
}
