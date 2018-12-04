<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
  function contentChanged() {
    if ($('#message\\.content').val().length > 4000) {
      $('#message\\.content').val($('#message\\.content').val().substr(0, 4000));
    }
  }
</script>
<div class="message-details">

    <div class="message-details-block">
        <h3><spring:message code="label.comments"/></h3>
        <div class="content-text-area">
            <form:textarea path="message.content"
                           onkeypress="javascript: contentChanged();"
                           onclick="this.onkeypress();"
                           onmouseout="this.onkeypress();"/>
        </div>
    </div>

    <div class="message-details-block">
        <tiles:insertAttribute name="attachmentList" />
    </div>

    <div class="business-edma-metadata" id="edmaMetadataSection">
        <div class="business-edma-metadata-column-left">
            <h5><spring:message code="label.senderInfo"/></h5>

            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell fixed-label-container label"><spring:message code="label.orgName"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderOrganisationName" id="senderOrganisationName" class="default-value-input"
                                    onkeyup="javascript: checkMandatoryFields();" onblur="javascript: checkMandatoryFields();" maxlength="100"/>
                    </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label" id="senderRegNumberLabel" ><spring:message code="label.compNatRegNumber"/></span>
                     <span class="grid-cell value-container" id="senderRegNumberContainer">
                        <form:input type="text" path="edmaMessage.senderRegNumber" id="senderRegNumber" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell hi-label"><spring:message code="label.contactPerson"/></span>
                     <span class="grid-cell mandatory-value-container">
                        <form:input type="text" path="edmaMessage.senderContactPerson" id="senderContactPerson" maxlength="100" class="default-value-input default-value-input-cp"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label"><spring:message code="label.position"/></span>
                     <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderPosition" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label"><spring:message code="label.phone"/></span>
                     <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderPhone" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label"><spring:message code="label.email"/></span>
                     <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderEmail" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label"><spring:message code="label.address"/></span>
                     <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderStreet" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label"><spring:message code="label.zip"/></span>
                     <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderZip" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label"><spring:message code="label.city"/></span>
                     <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.senderCity" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell label" id="senderCountryLabel" ><spring:message code="label.country"/></span>
                     <span class="grid-cell value-container"  id="senderCountryContainer">
                        <form:input type="text" id="senderCountry" path="edmaMessage.senderCountry" maxlength="100" class="default-value-input"/>
                     </span>
                </span>
            </div>

            <h5><spring:message code="label.msgInformation"/></h5>
            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell fixed-label-container label"><spring:message code="label.informationReference"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.msgInfoNo" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.fileReference"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.msgFileNo" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                     <span class="grid-cell hi-label"><spring:message code="label.instrument"/></span>
                     <span class="grid-cell mandatory-value-container">
                         <form:select id="msgInstrument" path="edmaMessage.msgInstrument" class="default-value-input default-value-input-in instrument">
                             <form:option value="" selected="selected"/>
                             <form:option value="Antitrust">
                                 <spring:message code="label.instrument.antitrust"/>
                             </form:option>
                             <form:option value="Merger">
                                 <spring:message code="label.instrument.merger"/>
                             </form:option>
                             <form:option value="State Aid">
                                 <spring:message code="label.instrument.stateAid"/>
                             </form:option>
                             <form:option value="Horizontal">
                                 <spring:message code="label.instrument.horizontal"/>
                             </form:option>
                         </form:select>
                     </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.inAttentionOf"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.msgInAttentionOf" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
             </div>

        </div>

        <div class="business-edma-metadata-column-right">
            <h5><spring:message code="label.onBehalf"/></h5>
            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell fixed-label-container label"><spring:message code="label.companyName"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfCompanyName" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.compNatRegNumber"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfRegNo" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.contactPerson"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfContactPerson" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.position"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfPosition" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.phone"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfPhone" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.email"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfEmail" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.address"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfStreet" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.zip"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfZip" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.city"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfCity" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell label"><spring:message code="label.country"/></span>
                    <span class="grid-cell value-container">
                        <form:input type="text" path="edmaMessage.onBehalfCountry" maxlength="100" class="default-value-input"/>
                    </span>
                </span>
            </div>
            <div class="grid">
                <span class="grid-row">
                    <span class="grid-cell fixed-label-container label"><spring:message code="label.distribution"/></span>
                    <span class="grid-cell value-container">
                        <form:textarea path="edmaMessage.msgDistributionList" id="msgDistributionList" class="default-value-input" rows="5" cols="50" maxlength=""></form:textarea>
                        <img src="<c:url value ="/images/icons/information.png"/>" class="info-icon" width="16" height="16" title='<spring:message code="label.distributionToolTip.text" htmlEscape="true"/>'/>
                    </span>
                </span>
            </div>
        </div>
    </div>
</div>


