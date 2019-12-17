<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="header-top">
    <span id="languages-script">
        <form action="" name="languageBar" >
            <select id="lang" name="lang" onChange="document.forms['languageBar'].submit();">
                <option <c:if test="${pageContext.response.locale.language eq 'bg'}">selected</c:if> value="bg">български</option>
                <option <c:if test="${pageContext.response.locale.language eq 'cs'}">selected</c:if> value="cs">čeština</option>
                <option <c:if test="${pageContext.response.locale.language eq 'da'}">selected</c:if> value="da">dansk</option>
                <option <c:if test="${pageContext.response.locale.language eq 'de'}">selected</c:if> value="de">Deutsch</option>
                <option <c:if test="${pageContext.response.locale.language eq 'et'}">selected</c:if> value="et">eesti keel</option>
                <option <c:if test="${pageContext.response.locale.language eq 'el'}">selected</c:if> value="el">ελληνικά</option>
                <option <c:if test="${pageContext.response.locale.language eq 'en'}">selected</c:if> value="en">English</option>
                <option <c:if test="${pageContext.response.locale.language eq 'es'}">selected</c:if> value="es">español</option>
                <option <c:if test="${pageContext.response.locale.language eq 'fr'}">selected</c:if> value="fr">français</option>
                <option <c:if test="${pageContext.response.locale.language eq 'hr'}">selected</c:if> value="hr">hrvatski jezik</option>
                <option <c:if test="${pageContext.response.locale.language eq 'it'}">selected</c:if> value="it">italiano</option>
                <option <c:if test="${pageContext.response.locale.language eq 'lv'}">selected</c:if> value="lv">latviešu valoda</option>
                <option <c:if test="${pageContext.response.locale.language eq 'lt'}">selected</c:if> value="lt">lietuvių kalba</option>
                <option <c:if test="${pageContext.response.locale.language eq 'hu'}">selected</c:if> value="hu">magyar</option>
                <option <c:if test="${pageContext.response.locale.language eq 'mt'}">selected</c:if> value="mt">Malti</option>
                <option <c:if test="${pageContext.response.locale.language eq 'nl'}">selected</c:if> value="nl">Nederlands</option>
                <option <c:if test="${pageContext.response.locale.language eq 'pl'}">selected</c:if> value="pl">polski</option>
                <option <c:if test="${pageContext.response.locale.language eq 'pt'}">selected</c:if> value="pt">português</option>
                <option <c:if test="${pageContext.response.locale.language eq 'ro'}">selected</c:if> value="ro">română</option>
                <option <c:if test="${pageContext.response.locale.language eq 'sk'}">selected</c:if> value="sk">slovenčina</option>
                <option <c:if test="${pageContext.response.locale.language eq 'sl'}">selected</c:if> value="sl">slovenščina</option>
                <option <c:if test="${pageContext.response.locale.language eq 'fi'}">selected</c:if> value="fi">suomi</option>
                <option <c:if test="${pageContext.response.locale.language eq 'sv'}">selected</c:if> value="sv">svenska</option>
            </select>

            <c:forEach items="${param}" var="par">
                <c:if test="${par.key != 'lang'}">
                    <input type="hidden" name="${par.key}" value="${par.value}"/>
                </c:if>
            </c:forEach>
        </form>
    </span>
</div>

<div class="header-middle"><img src="<c:url value="/images/icons/logo_en.gif"/>" alt="European Commission logo" width="172" height="119" id="banner-flag" class="logoCOMM" /></div>

<div class="header-bottom">
    <ul class="left">
        <li class="<c:if test="${pageContext.response.locale.language eq 'bg'}">selected</c:if>lang">
            <a href="http://europa.eu/index_en.htm">EUROPA</a> >
        </li>
        <li class="<c:if test="${pageContext.response.locale.language eq 'bg'}">selected</c:if>lang">
            <a href="http://ec.europa.eu/index_en.htm">European Commmission</a> >
        </li>
        <li class="<c:if test="${pageContext.response.locale.language eq 'bg'}">selected</c:if>lang">
            <a href="<c:url value="/"/>">eTrustEx</a>
        </li>
    </ul>

    <ul class="right">

        <li>
            <div class="dropdown">
                <button class="dropbtn"><spring:message code="label.help"/></button>
                <div class="dropdown-content">
                    <a href="${userGuideUrl}" target="_blank"><spring:message code="label.user.guide"/></a>
                    <a href="mailto:${supportEmail}" target="_top"><spring:message code="label.support.mail"/></a>
                </div>
            </div>
        </li>

        <c:if test="${isAdminLinkEnabled}">
            <li>
                <a href="<c:url value="/admin.do"/>"><spring:message code="label.admin.administration"/></a>
            </li>
        </c:if>

        <c:if test="${isLogoutLinkEnabled}">
            <li>
                <a href="<c:url value="/logout.do"/>"><spring:message code="label.logout"/></a>
            </li>
        </c:if>
    </ul>
</div>
