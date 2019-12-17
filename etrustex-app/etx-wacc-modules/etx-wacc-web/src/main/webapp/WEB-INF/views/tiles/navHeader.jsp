<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="header-navigation">
    <c:if test="${!hideUserDetails}">
        <form:form id="changeCurrentParty" name="changeCurrentParty" method="post" action="inbox.do">
            <div class="form-inline">
                <span class="partyname">
                    <spring:message code="label.party"/>:
                        <c:choose>
                            <c:when test="${fn:length(userSessionContext.messageParties) == 1}">
                                <spring:message text="${userSessionContext.messageParties[0].displayName}" htmlEscape="true"/>
                                <input type="hidden" id="selectedPartyId" value="${userSessionContext.messageParties[0].id}"/>
                            </c:when>
                            <c:otherwise>
                                <select id="selectedPartyId" name="selectedPartyId" class="form-control">
                                    <c:forEach items="${userSessionContext.messageParties}" var="entry">
                                        <option value="${entry.id}" <c:if test="${entry.id == ps.partyId}">selected</c:if>><spring:message text="${entry.displayName}" htmlEscape="true"/></option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                </span>
                <span class="username">
                    <label class="control-label">
                    <spring:message code="label.loggedIn"/>:
                    <c:choose>
                        <c:when test="${businessUserName != null}">
                            <spring:message text="${businessUserName}" htmlEscape="true"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message text="${user.name}" htmlEscape="true"/>
                            </c:otherwise>
                        </c:choose>
                     </label>
                </span>
            </div>
        </form:form>
    </c:if>
</div>