package eu.europa.ec.etrustex.webaccess.web.i18n;

import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Class responsible with adding user language as a long term cookie
 * and using it's value as default language if existent.
 * Used from spring interceptor:
 * {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor}
 */
public class EtxCookieLocaleResolver extends CookieLocaleResolver {

    private static final Logger logger = Logger.getLogger(EtxCookieLocaleResolver.class.getName());

    public static final int DEFAULT_COOKIE_MAX_AGE = Integer.MAX_VALUE;

    @Autowired
    protected UserSessionContext userSessionContext;

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        super.setLocale(request, response, locale);
        if (userSessionContext.getUser() == null) {
            return;
        }
        if (locale != null) {
            addEtxCookie(response, locale.toString());
        } else {
            removeEtxCookie(response);
        }
    }

    protected void addEtxCookie(HttpServletResponse response, String cookieValue) {
        Assert.notNull(response, "HttpServletResponse must not be null");
        Cookie cookie = createEtxCookie(cookieValue);
        cookie.setMaxAge(DEFAULT_COOKIE_MAX_AGE);
        if (isCookieSecure()) {
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
        logger.debug("Added cookie with name [" + getEtxCookieName() + "] and value [" + cookieValue + "]");
    }

    protected String getEtxCookieName() {
        return "etrustex." + userSessionContext.getUser().getLogin() + ".language";
    }

    protected Cookie createEtxCookie(String cookieValue) {
        Cookie cookie = new Cookie(getEtxCookieName(), cookieValue);
        if (getCookieDomain() != null) {
            cookie.setDomain(getCookieDomain());
        }
        cookie.setPath(getCookiePath());
        return cookie;
    }

    protected void removeEtxCookie(HttpServletResponse response) {
        Assert.notNull(response, "HttpServletResponse must not be null");
        Cookie cookie = createEtxCookie("");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        logger.debug("Removed cookie with name [" + getEtxCookieName() + "]");
    }

    @Override
    protected Locale determineDefaultLocale(HttpServletRequest request) {
        if (userSessionContext.getUser() != null) {
            Cookie cookie = WebUtils.getCookie(request, getEtxCookieName());
            if (cookie != null) {
                Locale locale = StringUtils.parseLocaleString(cookie.getValue());
                logger.debug("Parsed cookie value [" + cookie.getValue() + "] into locale '" + locale + "'");
                if (locale != null) {
                    request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
                    return locale;
                }
            }
        }
        return super.determineDefaultLocale(request);
    }
}
