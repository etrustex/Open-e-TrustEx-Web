<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    const forceDownloadAsCleartext = true
</script>
<div class="message-details">
    <div class="message-details-block">
        <h3><spring:message code="label.message.body"/></h3>
        <div class="content-text-area">
            <span><spring:message text="${msg.content}" htmlEscape="true"/></span>
        </div>
    </div>

    <div class="message-details-block">
        <tiles:insertAttribute name="attachmentList" />
    </div>

    <div class="business-edma-metadata">
        <div class="business-edma-metadata-column left">
            <h5><spring:message code="label.senderInfo"/></h5>

            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell hi-label fixed-label-container"><spring:message code="label.orgName"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text=" ${edmaMessage.senderOrganisationName}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.contactPerson"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderContactPerson}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.position"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderPosition}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.phone"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderPhone}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.email"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderEmail}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.address"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderStreet}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.zip"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderZip}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.city"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderCity}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.country"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.senderCountry}" htmlEscape="true"/>
                    </span>
                </span>
            </div>
            <h5><spring:message code="label.distribution"/></h5>
            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell-textarea label value-container-textarea">
                        <spring:message text="${edmaMessage.msgDistributionList}" htmlEscape="true"/>
                    </span>
                </span>
            </div>
        </div>

        <div class="business-edma-metadata-column right">
            <h5><spring:message code="label.onBehalf"/></h5>
            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell hi-label fixed-label-container"><spring:message code="label.companyName"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfCompanyName}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.contactPerson"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfContactPerson}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.position"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfPosition}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.phone"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfPhone}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.email"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfEmail}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.address"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfStreet}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.zip"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfZip}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.city"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfCity}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.country"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.onBehalfCountry}" htmlEscape="true"/>
                    </span>
                </span>
            </div>
            <h5><spring:message code="label.msgInformation"/></h5>
            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.case.number"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.msgFileNo}" htmlEscape="true"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell hi-label"><spring:message code="label.instrument"/></span>
                    <span class="grid-cell label value-container">
                        <spring:message text="${edmaMessage.msgInstrument}" htmlEscape="true"/>
                    </span>
                </span>
            </div>
        </div>
    </div>
</div>
