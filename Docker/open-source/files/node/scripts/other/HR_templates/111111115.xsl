<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:mdord="urn:oasis:names:specification:ubl:schema:xsd:Order-2" xmlns:n1="ec:schema:xsd:OrderReceived-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="text()" name="insertBreaks">
		<xsl:param name="pText" select="."/>
		<xsl:choose>
			<xsl:when test="not(contains($pText, '&#xA;'))">
			<xsl:value-of select="$pText" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="substring-before($pText, '&#xA;')"/>
				<br/>
				<xsl:call-template name="insertBreaks">
					<xsl:with-param name="pText" select="substring-after($pText, '&#xA;')"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="/">
		<html>
			<head>
				<meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
				<title>Order for Services Human Readable Format</title>
				<style type="text/css">
body, td {
	font-family: "Liberation Sans";
	font-size: medium;
}
.preserve {
	font-family: "Liberation Sans";
	font-size: medium;
	vertical-align: text-top;
}

.header {
	font-family: "Liberation Sans";
	font-weight:bold;
	font-size:large;
	text-align: center;
}
	
@page {
	size: A4 portrait;
	margin-top: 5%;
	margin-bottom: 5%;
	 
	@bottom-center {
		font-family: "Liberation Sans";
	  font-size: small;
		content: "Page " counter(page) "/" counter(pages);
    vertical-align:top; 
    border-top-style:solid;
    width=100%;
	}
  
	@bottom-right {
	  font-size: x-small;
    text-align: right;
		content: element(footer);
    border-top-style:solid;
    width=100%;
  }
  
	@bottom-left {
	  font-size: x-small;
    text-align: right;
		content: "&#160;";
    border-top-style:solid;
    width=100%;
  }
}
.footer {
	position: running(footer);
	font-size:  x-small;
  text-align:right;
  vertical-align:top;
}

	</style>
			</head>
			<body>
			<div class="header">Data Appendix</div>				
			<br/>
			<xsl:for-each select="$XML">
					<xsl:for-each select="n1:OrderReceived/mdord:Order">
      			<div class="footer"><b><i>Data Appendix</i></b><br/>
						<xsl:text>Specific Contract n&#176; </xsl:text><xsl:value-of select="cbc:SalesOrderID"/><br/>				
						<xsl:text>Framework Contract n&#176; </xsl:text><xsl:value-of select="cac:Contract/cbc:ID"/><br/>
						<xsl:value-of select="cac:Contract/cbc:ContractTypeCode"/><xsl:text> version</xsl:text></div>				
						<b>
							<xsl:text>Contract Type: </xsl:text>
						</b>
						<xsl:for-each select="cac:Contract/cbc:ContractTypeCode">
							<xsl:value-of select="document(&apos;RequestForQuotationTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
						</xsl:for-each>
						<br/>
						<b>
							<xsl:text>Specific Contract Number: </xsl:text>
						</b>
						<xsl:value-of select="cbc:SalesOrderID"/>
						<br/>
						<b>
							<xsl:text>Framework Contract Number: </xsl:text>
						</b>
						<xsl:value-of select="cac:Contract/cbc:ID"/>
						<br/>
					<xsl:if test="cac:Contract/cbc:ContractTypeCode = &apos;OF&apos;">
							<b>
							<xsl:text>Management Center: </xsl:text>
							</b>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;ManagementCenterDescription&apos;)">
									<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
							</xsl:if>
						</xsl:for-each>
						<br/>
							<b>
								<xsl:text>Requesting Center: </xsl:text>
							</b>
							<xsl:for-each select="cbc:Note">
								<xsl:if test="starts-with(., &apos;RequestingCenterDescription&apos;)">
									<xsl:call-template name="insertBreaks">
										<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:for-each><br/>
							<b>
							<xsl:text>Commitment header Local reference: </xsl:text>
							</b>
							<xsl:for-each select="cbc:Note">
								<xsl:if test="starts-with(., &apos;CommitmentHeaderLocalRef&apos;)">
									<xsl:call-template name="insertBreaks">
										<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:for-each>
							<br/><br/>
					</xsl:if>
						<b>
							<xsl:text>The Parties</xsl:text>
						</b>
							<br/>
						<br/>
						<xsl:text>&#160;&#160;&#160;&#160;&#160;</xsl:text>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;BuyerCustomerPartyDetail&apos;)">
								<i>
									<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
								</i>
							</xsl:if>
						</xsl:for-each>
						<br/>
						<br/>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;PartyTextDescription&apos;)">
								<xsl:call-template name="insertBreaks">
									<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
								</xsl:call-template>
							</xsl:if>
						</xsl:for-each>
						<br/>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;CustomerPartySignatoryName&apos;)">
								<xsl:text> </xsl:text>
								<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
							</xsl:if>
						</xsl:for-each>
						<br/>
						<br/>
						<xsl:text>&#160;&#160;&#160;&#160;&#160;</xsl:text>
						<i>The Contractor</i>
						<br/>
						<br/>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;ConsortiumDescription&apos;)">
								<xsl:call-template name="insertBreaks">
									<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
								</xsl:call-template>
							</xsl:if>
						</xsl:for-each>
						<br/><br/>
			 <b><xsl:text>represented by </xsl:text></b>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;SupplierPartySignatoryName&apos;)">
								<xsl:call-template name="insertBreaks">
									<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
								</xsl:call-template>
							</xsl:if>
						</xsl:for-each>
						<br/>
						<br/>
						<b>
							<xsl:for-each select="cbc:Note">
								<xsl:if test="starts-with(., &apos;PartyClause&apos;)">
									<xsl:call-template name="insertBreaks">
										<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:for-each>
						</b>
						<br/>
						<br/>
						<b>
							<xsl:text>Framework Contract Signature Date: </xsl:text>
						</b>
						<xsl:for-each select="cac:Contract/cbc:IssueDate">
							<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
							<xsl:text>/</xsl:text>
							<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
							<xsl:text>/</xsl:text>
							<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000')"/>
						</xsl:for-each>
						<xsl:if  test="cac:Contract/cbc:ContractTypeCode = &apos;OF&apos;" >
						<b>
							<xsl:text> Framework Contract Start Date:  </xsl:text>
						</b>
							<xsl:for-each select="cbc:Note">
								<xsl:if test="starts-with(., &apos;FrameworkContractStartDate&apos;)">
									<xsl:call-template name="insertBreaks">
										<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:for-each>
						<b>
							<xsl:text> Framework Contract End Date: </xsl:text>
						</b>
							<xsl:for-each select="cbc:Note">
								<xsl:if test="starts-with(., &apos;FrameworkContractEndDate&apos;)">
									<xsl:call-template name="insertBreaks">
										<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:for-each>
						<br/>
						</xsl:if>
						<b>
							<xsl:text>Request Number: </xsl:text>
						</b>
						<xsl:value-of select="cac:QuotationDocumentReference/cbc:ID"/> dated
				<xsl:for-each select="cac:QuotationDocumentReference/cbc:IssueDate">
							<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
							<xsl:text>/</xsl:text>
							<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
							<xsl:text>/</xsl:text>
							<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000')"/>
						</xsl:for-each>.<br/>
						<b>
							<xsl:text>Lot Reference: </xsl:text>
						</b>
						<xsl:value-of select="cac:Contract/cac:ContractDocumentReference/cbc:ID"/>
						<br/>
						<b>
							<xsl:text>Subject: </xsl:text>
						</b>
								<xsl:for-each select="cbc:Note">
									<xsl:if test="starts-with(., &apos;Subject&apos;)">
										<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
									</xsl:if>
								</xsl:for-each>
						<br/>
						<b>
							<xsl:text>Start Date: </xsl:text>
						</b>
						<xsl:for-each select="cbc:Note">
							<xsl:if test="starts-with(., &apos;StartDateClause&apos;)">
								<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
							</xsl:if>
						</xsl:for-each>
						<br/>
						<b>
							<xsl:text>End Date: </xsl:text>
						</b>
						<xsl:choose>
							<xsl:when test="cac:Contract/cbc:ContractTypeCode = &apos;FP&apos; or cac:Contract/cbc:ContractTypeCode = &apos;FPM&apos; or cac:Contract/cbc:ContractTypeCode = &apos;QTM&apos;" >
								<xsl:for-each select="cbc:Note">
									<xsl:if test="starts-with(., &apos;EndDateClause&apos;)">
										<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
									</xsl:if>
								</xsl:for-each>
								</xsl:when>
								<xsl:otherwise>
									<xsl:for-each select="cac:Contract/cac:ValidityPeriod/cbc:EndDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000')"/>
									</xsl:for-each>
								</xsl:otherwise>
						</xsl:choose>
						<br/>
						<xsl:choose>
							<xsl:when test="cac:Contract/cbc:ContractTypeCode = &apos;QTM&apos;">
								<b>
									<xsl:text>Number of Working Days to Submit a Quote: </xsl:text>
								</b>
								<xsl:for-each select="cbc:Note">
									<xsl:if test="starts-with(., &apos;NumberofDays2SubmitQuote&apos;)">
										<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
									</xsl:if>
								</xsl:for-each>
								<br/>
								<b>
									<xsl:text>Number of Working Days to Approve a Quote: </xsl:text>
								</b>
								<xsl:for-each select="cbc:Note">
									<xsl:if test="starts-with(., &apos;NumberofDays2ApproveQuote&apos;)">
										<xsl:value-of select="substring-after(., &apos;#&apos;)"/>
									</xsl:if>
								</xsl:for-each>
						<br/>
							</xsl:when>
						</xsl:choose>
						<xsl:choose>
							<xsl:when test="(cac:Contract/cbc:ContractTypeCode = &apos;TM&apos; or cac:Contract/cbc:ContractTypeCode = &apos;QTM&apos; or cac:Contract/cbc:ContractTypeCode = &apos;PTM&apos; or cac:Contract/cbc:ContractTypeCode = &apos;OF&apos;)">
								<b>
									<xsl:text>Place of Delivery: </xsl:text>
								</b>
							<xsl:for-each select="cac:Delivery/cac:DeliveryLocation">
								<xsl:call-template name="insertBreaks">
									<xsl:with-param name="pText" select="cbc:Description"/>
								</xsl:call-template>
							</xsl:for-each>
								<br/>
								<b>
									<xsl:text>Service Providers</xsl:text>
								</b>
 								<table width="90%" cellpadding="2" cellspacing="0" border="0" align="center">
									<tbody>
										<tr>
											<td width="40%">
												<b>
													<xsl:text/>
												</b>
											</td>
											<td width="12%" align="right">
												<b>
													<xsl:text>Daily Rate </xsl:text>(<xsl:value-of select="cac:OrderLine[1]/cac:LineItem/cac:Price/cbc:PriceAmount/@currencyID"/>)</b>
											</td>
											<td width="14%" align="right">
												<b>
													<xsl:text>Number of Days</xsl:text>
												</b>
											</td>
											<td width="18%" align="right">
												<b>
													<xsl:text>Amount for Profile </xsl:text>(<xsl:value-of select="cac:OrderLine[1]/cac:LineItem/cbc:LineExtensionAmount/@currencyID"/>)</b>
											</td>
											<td width="14%" align="right">
												<b>
													<xsl:text>Subcontractor</xsl:text></b>
											</td>
										</tr>
										<xsl:for-each select="cac:OrderLine/cac:LineItem">
											<tr>
												<td>
													<xsl:value-of select="cac:Item/cbc:Name"/>
												</td>
												<td align="right">
													<xsl:value-of select="format-number(number(cac:Price/cbc:PriceAmount), '###.##0,00##', 'format1')"/>
												</td>
												<td align="right">
													<xsl:value-of select="cbc:Quantity"/>
												</td>
												<td align="right">
													<xsl:value-of select="format-number(number(cbc:LineExtensionAmount), '###.##0,00##', 'format1')"/>
												</td>
												<td align="center">
														<xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
																<xsl:if test="cbc:Name = &apos;ServiceProviderType&apos;">
																	<xsl:if test="cbc:Value = &apos;EMPLOYEE&apos;">
																	no
																	</xsl:if>
																	<xsl:if test="cbc:Value = &apos;SUBCONTRACTOR&apos;">
																	yes
																	</xsl:if>
															</xsl:if>
														</xsl:for-each>
												</td>
											</tr>
										</xsl:for-each>
									</tbody>
								</table>
							</xsl:when>
						</xsl:choose>
						<xsl:choose>
							<xsl:when test="cac:Contract/cbc:ContractTypeCode = &apos;FP&apos; or cac:Contract/cbc:ContractTypeCode = &apos;FPM&apos;">
								<b>
									<xsl:text>Place of Delivery: </xsl:text>
								</b>
							<xsl:for-each select="cac:Delivery/cac:DeliveryLocation">
								<xsl:call-template name="insertBreaks">
									<xsl:with-param name="pText" select="cbc:Description"/>
								</xsl:call-template>
							</xsl:for-each>
								<br/>
							</xsl:when>
						</xsl:choose>
						<br/>
						<table width="100%" cellpadding="2" cellspacing="0" border="0">
							<tbody>
								<tr>
									<td colspan="2">
										<b>
											<xsl:text>Total Amount: </xsl:text>
										</b>
										<xsl:value-of select="format-number(number(cac:AnticipatedMonetaryTotal/cbc:PayableAmount), '###.##0,00##', 'format1')"/>
										<xsl:text>&#160;</xsl:text>
										<xsl:value-of select="cac:AnticipatedMonetaryTotal/cbc:PayableAmount/@currencyID"/>
										<br/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Invoicing Procedure</xsl:text>
										</b>
										<br/>
										<xsl:call-template name="insertBreaks">
											<xsl:with-param name="pText" select="cac:TransactionConditions/cbc:Description"/>
										</xsl:call-template>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Bank Account: </xsl:text>
										</b>
										<xsl:value-of select="cac:PaymentMeans/cac:PayeeFinancialAccount/cbc:ID"/>
										<b>
											<xsl:text> held with </xsl:text>
										</b>
										<xsl:value-of select="cac:PaymentMeans/cac:PayeeFinancialAccount/cac:FinancialInstitutionBranch/cac:FinancialInstitution/cbc:ID"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Subcontracting</xsl:text>
										</b>
										<br/>
										<xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;SubcontractingNote&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
										<br/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Invoicing information</xsl:text>
										</b>
									</td>
								</tr>
								<tr>
									<td colspan="2">Department code: <xsl:value-of select="cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:Department"/>
										<br/>Address:</td>
								</tr>
								<tr>
									<td width="30px">
										<xsl:text>&#160;</xsl:text>
									</td>
									<td>
										<xsl:call-template name="insertBreaks">
											<xsl:with-param name="pText" select="cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:AddressLine/cbc:Line"/>
										</xsl:call-template>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Performance guarantees</xsl:text>
										</b>
									</td>
								</tr>
								<tr>
									<td colspan="2"><xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;PerformanceBonds&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each></td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Persons Responsible for the Customer</xsl:text>
										</b>
									</td>
								</tr>
								<tr>
									<td colspan="2">Administrative matters:</td>
								</tr>
								<tr>
									<td width="30px">
										<xsl:text>&#160;</xsl:text>
									</td>
									<td>
										<xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;AdministrativeMattersManager&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td colspan="2">Technical matters:</td>
								</tr>
								<tr>
									<td width="30px">
										<xsl:text>&#160;</xsl:text>
									</td>
									<td>
										<xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;TechnicalMattersManager&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>Persons Responsible for the Contractor</b>
									</td>
								</tr>
								<tr>
									<td width="30px">
										<xsl:text>&#160;</xsl:text>
									</td>
									<td>
										<xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;ContractorContractManager&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Additional Provisions</xsl:text>
										</b>
										<br/>
										<xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;ContractClause&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
										<br/>
									</td>
								</tr>
							<xsl:if test="(cac:Contract/cbc:ContractTypeCode = &apos;OF&apos;)">
									<tr>
									<td colspan="2">
										<br/>
										<b>
											<xsl:text>Remarks</xsl:text>
										</b>
										<br/>
										<xsl:for-each select="cbc:Note">
											<xsl:if test="starts-with(., &apos;OrderRemarks&apos;)">
												<xsl:call-template name="insertBreaks">
													<xsl:with-param name="pText" select="substring-after(., &apos;#&apos;)"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
										<br/>
									</td>
								</tr>
							</xsl:if>
							</tbody>
						</table>
					</xsl:for-each>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>