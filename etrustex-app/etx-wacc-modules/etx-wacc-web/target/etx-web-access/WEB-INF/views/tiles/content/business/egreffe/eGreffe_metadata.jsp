<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h4>
    <c:if test="${not empty eGreffeMetadata.adoptionDate}">
        <b><spring:message code="label.adoptionDate" /></b>:
        <span class="business-egreffe-metadata-label-values">
            <u><fmt:formatDate value="${eGreffeMetadata.adoptionDate}" pattern="EEEE, MMMM d yyyy"/></u>
        </span>
    </c:if>
</h4>
<h4>
	<b><spring:message code="label.comments" /></b>:<c:choose>
											    <c:when test="${empty eGreffeMetadata.comments}">
											        <span class="business-egreffe-metadata-label-values"><i> <spring:message code="label.noComments" /></i></span>
											    </c:when>
											    <c:otherwise>
											        <span class="business-egreffe-metadata-label-values"><spring:message text="${eGreffeMetadata.comments}" htmlEscape="true"/></span>
											    </c:otherwise>
											</c:choose>
</h4>

<c:forEach items="${eGreffeMetadata.listOfWorks}" var="egreffeWork">
	<ul>
		<li>
            <c:if test="${fn:length(egreffeWork.partNumbers) > 0}">
                <div class="business-egreffe-document-title">
                    <span class="subject-detail"><spring:message text="${egreffeWork.summaryTittle}" htmlEscape="true"/></span>
                </div>
            </c:if>
			<table class="business-egreffe-metadata-table">
				<tr>
					<th></th>
					<c:forEach items="${egreffeWork.languages}" var="language">
						<th>
							<spring:message text="${language}" htmlEscape="true"/>
						</th>
					</c:forEach>
				</tr>
				<c:forEach items="${egreffeWork.partNumbers}" var="partNumber">
					<tr>
						<td class="filename-cell">
							<h4><spring:message text="${egreffeWork.act}" htmlEscape="true"/> - <spring:message code="label.part" />${partNumber}</h4>
						</td>
						<c:forEach items="${egreffeWork.languages}" var="language">
							<c:set var="documentsPerLanguage"  value="${egreffeWork.documentsList[language]}"/>
								<c:set var="documentsPerPartNumber"  value="${documentsPerLanguage[partNumber]}"/>
								
								<c:set var="pdfFileName" value=""/>
								<c:set var="docFileName" value=""/>
								<c:set var="docExtension" value=""/>
								<c:set var="documentIcon" value=""/>
								<c:set var="isPdfFormatFirst" value="${documentsPerPartNumber[0].format == 'PDF' || documentsPerPartNumber[0].format == 'PDFA_1_A' || documentsPerPartNumber[0].format == 'PDFA_1_B' || documentsPerPartNumber[0].format == 'PDFX'}" />
								
								<c:if test="${fn:length(documentsPerPartNumber) == 1}">
									<c:choose>
										<c:when test="${isPdfFormatFirst}">
											<c:set var="pdfFileName" value="${documentsPerPartNumber[0].attachmentFileName}"/>
										</c:when>
										<c:otherwise>
											<c:set var="docFileName" value="${documentsPerPartNumber[0].attachmentFileName}"/>
											<c:set var="docExtension" value="${documentsPerPartNumber[0].format}"/>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:length(documentsPerPartNumber) == 2}">
									<c:choose>
										<c:when test="${isPdfFormatFirst}">
											<c:set var="pdfFileName" value="${documentsPerPartNumber[0].attachmentFileName}"/>
											<c:set var="docFileName" value="${documentsPerPartNumber[1].attachmentFileName}"/>
											<c:set var="docExtension" value="${documentsPerPartNumber[1].format}"/>
										</c:when>
										<c:otherwise>
											<c:set var="docFileName" value="${documentsPerPartNumber[0].attachmentFileName}"/>
											<c:set var="docExtension" value="${documentsPerPartNumber[0].format}"/>
											<c:set var="pdfFileName" value="${documentsPerPartNumber[1].attachmentFileName}"/>
										</c:otherwise>
									</c:choose>
								</c:if>
								
								
								<c:choose>
										<c:when test="${not empty docExtension}">
											<c:choose>
												<c:when test="${fn:contains(docExtension, 'DOC')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-doc.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'DOCX')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-doc.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'PPSX')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-ppt.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'PPT')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-ppt.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'PPTX')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-ppt.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'XLS')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-xls.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'XLSX')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-xls.png"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'XML')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-xml.gif"/>
												</c:when>
												<c:when test="${fn:contains(docExtension, 'ZIP')}"> 
													<c:set var="documentIcon" value="/images/icons/icon-zip.png"/>
												</c:when>
												<c:otherwise>
													<c:set var="documentIcon" value="/images/icons/icon-unknown.png"/>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:set var="documentIcon" value="/images/icons/icon-unknown.png"/>
										</c:otherwise>
								</c:choose>
								
								<td class="lang-cell">
									<c:choose>
										<c:when test="${not empty docFileName}">
											<img src="<c:url value="${documentIcon}"/>" width="16" height="16" alt="${docFileName}" title="${docFileName}"/>
										</c:when>
										<c:otherwise>
											<img src="<c:url value="/images/icons/icon-blank.png"/>"  width="16" height="16"/>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${not empty pdfFileName}">
											<img src="<c:url value="/images/icons/icon-pdf.png"/>" width="16" height="16" alt="${pdfFileName}" title="${pdfFileName}"/>
										</c:when>
										<c:otherwise>
											<img src="<c:url value="/images/icons/icon-blank.png"/>"  width="16" height="16"/>
										</c:otherwise>
									</c:choose>
								</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</li>
	</ul>
</c:forEach>
