<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="header-top">
    <div class="language-selector">
        <noscript>
            <ul class="language-selector" id="language-selector">
                <li class="<c:if test="${pageContext.response.locale.language eq 'bg'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=bg" />" hreflang="bg" title="български" lang="bg" >bg</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'cs'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=cs" />" hreflang="cs" title="čeština" lang="cs">cs</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'da'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=da" />" hreflang="da" title="dansk" lang="da">da</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'de'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=de" />" hreflang="de" title="Deutsch" lang="de">de</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'et'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=et" />" hreflang="et" title="eesti keel" lang="et">et</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'el'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=el" />" hreflang="el" title="ελληνικά" lang="el">el</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'en'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=en" />" hreflang="en" title="English" lang="en">en</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'es'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=es" />" hreflang="es" title="español" lang="es">es</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'fr'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=fr" />" hreflang="fr" title="français" lang="fr">fr</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'hr'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=hr" />" hreflang="hr" title="hrvatski jezik" lang="hr">hr</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'it'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=it" />" hreflang="it" title="italiano" lang="it">it</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'lv'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=lv" />" hreflang="lv" title="latviešu valoda" lang="lv">lv</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'lt'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=lt" />" hreflang="lt" title="lietuvių kalba" lang="lt">lt</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'hu'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=hu" />" hreflang="hu" title="magyar" lang="hu">hu</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'mt'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=mt" />" hreflang="mt" title="Malti" lang="mt">mt</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'nl'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=nl" />" hreflang="nl" title="Nederlands" lang="nl">nl</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'pl'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=pl" />" hreflang="pl" title="polski" lang="pl">pl</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'pt'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=pt" />" hreflang="pt" title="português" lang="pt">pt</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'ro'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=ro" />" hreflang="ro" title="română" lang="ro">ro</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'sk'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=sk" />" hreflang="sk" title="slovenčina" lang="sk">sk</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'sl'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=sl" />" hreflang="sl" title="slovenščina" lang="sl">sl</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'fi'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=fi" />" hreflang="fi" title="suomi" lang="fi">fi</a>
                </li>
                <li class="<c:if test="${pageContext.response.locale.language eq 'sv'}">selected</c:if>lang">
                    <a href="<c:url value="?lang=sv" />" hreflang="sv" title="svenska" lang="sv">sv</a>
                </li>
            </ul>
        </noscript>
    </div>
    <form action="" name="languageBar" >
    <c:forEach items="${param}" var="par">
        <c:if test="${par.key != 'lang'}">
            <input type="hidden" name="${par.key}" value="${par.value}"/>
        </c:if>
    </c:forEach>
    <div id="languages-script">

        <select id="lang" name="lang" onChange="document.forms['languageBar'].submit();">

            <option
                <c:if test="${pageContext.response.locale.language eq 'bg'}">selected</c:if>
                value="bg">български</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'cs'}">selected</c:if>
                value="cs">čeština</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'da'}">selected</c:if>
                value="da">dansk</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'de'}">selected</c:if>
                value="de">Deutsch</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'et'}">selected</c:if>
                value="et">eesti keel</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'el'}">selected</c:if>
                value="el">ελληνικά</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'en'}">selected</c:if>
                value="en">English</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'es'}">selected</c:if>
                value="es">español</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'fr'}">selected</c:if>
                value="fr">français</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'hr'}">selected</c:if>
                value="hr">hrvatski jezik</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'it'}">selected</c:if>
                value="it">italiano</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'lv'}">selected</c:if>
                value="lv">latviešu valoda</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'lt'}">selected</c:if>
                value="lt">lietuvių kalba</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'hu'}">selected</c:if>
                value="hu">magyar</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'mt'}">selected</c:if>
                value="mt">Malti</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'nl'}">selected</c:if>
                value="nl">Nederlands</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'pl'}">selected</c:if>
                value="pl">polski</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'pt'}">selected</c:if>
                value="pt">português</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'ro'}">selected</c:if>
                value="ro">română</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'sk'}">selected</c:if>
                value="sk">slovenčina</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'sl'}">selected</c:if>
                value="sl">slovenščina</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'fi'}">selected</c:if>
                value="fi">suomi</option>
            <option
                <c:if test="${pageContext.response.locale.language eq 'sv'}">selected</c:if>
                value="sv">svenska</option>
        </select>
    </div>
    </form>
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
