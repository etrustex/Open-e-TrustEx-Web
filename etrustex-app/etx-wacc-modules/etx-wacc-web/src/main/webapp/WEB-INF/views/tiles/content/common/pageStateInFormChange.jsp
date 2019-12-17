<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<input type=hidden name="v" value="${ps.view}"/>
<input type=hidden name="s" value="<spring:message text="${ps.subject}" htmlEscape="true"/>"/>
<input type=hidden name="mid" value="${ps.messageId}"/>
<input type=hidden name="pid" value="${ps.partyId}"/>
<input type=hidden name="bad" value="${ps.backAction}"/>
<input type=hidden name="bv" value="${ps.backView}"/>
<input type=hidden name="u" value="${ps.unreadOnly}"/>
<input type=hidden name="p" value="1"/>
<input type=hidden name="st" value="${ps.status}"/>