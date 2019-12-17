<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:mdrem="urn:oasis:names:specification:ubl:schema:xsd:Reminder-2" xmlns:n1="ec:schema:xsd:ReminderReceived-0.1" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="/">
		<html>
			<head>
				<title>Payment Reminder Human Readable Format</title>
				<style type="text/css">
body { 	
	font-family: "Liberation Sans";
	font-size: medium;
	size: landscape;
}

table {
	font-family: "Liberation Sans";
	font-size: medium;
	border-right: none;
	border-left: none;
	border-top: none;
	border-bottom: none;
}

.labelBold {
	color: WindowText;
	font-family: "Liberation Sans";
	font-size: medium;
	font-weight: bold;
}

.labelWhite {
	color: #f0f0f0;
	background-color: #595959;
}

table.border {
	border:2px solid black;
}

table.noborder {
	border:none 0px black;
}

.cellNoBorder{
	border-style:none;
	border-left-style:none;
	border-top-style:none;
	border-right-style:none;
	border-bottom-style:none;
}

.cellLeftRightBorder {
	border-left-style:thin;
	border-left-width:1px;
	border-left-color:black;
	border-right-style:thin;
	border-right-width:1px;
	border-right-color:black;
	border-top-style:none;
	border-bottom-style:none;
}

.cellTopBorder {
	border-left-style:none;
	border-bottom-style:none;
	border-right-style:none;
	border-top-style:thin;
	border-top-width:1px;
	border-top-color:black;
}

				</style>
			</head>
			<body>
				<xsl:for-each select="/n1:ReminderReceived">
					<table align="center" width="100%" cellpadding="2" cellspacing="0" border="1" class="border">
						<tbody>
							<tr>
								<th align="center" colSpan="8">PAYMENT REMINDER</th>
							</tr>
							<tr class="labelBold labelWhite">
								<td width="18%" colspan="2">ReceivedDate</td>
								<td width="17%">RegistrationDate</td>
								<td width="15%">CustomerID</td>
								<td width="15%">SupplierID</td>
								<td width="10%">DepartmentID</td>
								<td width="15%">AccountPayableID</td>
								<td width="10%">InternalID</td>
							</tr>
							
							<xsl:for-each select="eccac:ECDocumentReceivedData">
							<tr>
								<td colSpan="2">
									<xsl:for-each select="cbc:ReceivedDate">
										<span>
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
										</span>
									</xsl:for-each>
								</td>
								<td>
									<xsl:for-each select="eccbc:RegistrationDate">
										<span>
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
										</span>
									</xsl:for-each>
								</td>
								<td><xsl:value-of select="eccbc:CustomerID"/></td>
								<td><xsl:value-of select="eccbc:SupplierID"/></td>
								<td><xsl:value-of select="eccbc:DepartmentID"/></td>
								<td><xsl:value-of select="eccbc:AccountPayableID"/></td>
								<td><xsl:value-of select="eccbc:InternalID"/></td>
							</tr>
							</xsl:for-each>
							
							<tr class="labelBold labelWhite">
								<td colspan="3">Reminder ID</td>
								<td>Reminder Period</td>
								<td>Reminder issue date</td>
								<td colspan="2">Customer&apos;s Assigned AccountID</td>
								<td>Tax point date</td>
							</tr>
							
							<xsl:for-each select="mdrem:Reminder">
							<tr>
								<td colSpan="3"><xsl:value-of select="cbc:ID"/></td>
								<td>start date:
									<xsl:for-each select="cac:ReminderPeriod">
										<xsl:for-each select="cbc:StartDate">
											<span>
												<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
											</span>
										</xsl:for-each>
									</xsl:for-each>
									<br/><br/>end date:
									<xsl:for-each select="cac:Period">
										<xsl:for-each select="cbc:EndDate">
											<span>
												<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
											</span>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td>
									<xsl:for-each select="cbc:IssueDate">
										<span>
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
										</span>
									</xsl:for-each>
								</td>
								<td colspan="2">
									<xsl:for-each select="cac:AccountingSupplierParty">
										<xsl:for-each select="cbc:CustomerAssignedAccountID">
											<xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:text>&#160;</xsl:text>
								</td>
								<td>
									<xsl:for-each select="cbc:TaxPointDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each>
									<xsl:text>&#160;</xsl:text>
								</td>
							</tr>
							
							<tr class="labelBold labelWhite">
								<td colSpan="2">Buyer</td>
								<td colSpan="2">Buyer Contact</td>
								<td colSpan="2">Supplier</td>
								<td colSpan="2">Supplier Contact</td>
							</tr>
							
							<tr>
								<xsl:for-each select="cac:AccountingCustomerParty/cac:Party">
								<td colSpan="2" valign="top">
									<xsl:if test="not ( normalize-space(string(cac:PartyName/cbc:Name)) = &apos;&apos; )">
										<xsl:value-of select="cac:PartyName/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<xsl:text> - </xsl:text><xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:PostalZone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
										<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:CityName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<br/>
										<xsl:for-each select="cac:PostalAddress/cac:Country/cbc:IdentificationCode">
											<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each><br/>
									</xsl:if>
								</td>
								<td valign="top" colSpan="2">
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Name)) = &apos;&apos; )">
										<xsl:value-of select="cac:Contact/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telephone)) = &apos;&apos; )">
										<br/><xsl:text>Tel.: </xsl:text><xsl:value-of select="cac:Contact/cbc:Telephone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
										<br/><xsl:text>E-mail: </xsl:text><xsl:value-of select="cac:Contact/cbc:ElectronicMail"/>
									</xsl:if>
								</td>
								</xsl:for-each>
								<xsl:for-each select="cac:AccountingSupplierParty/cac:Party">
								<td colspan="2" valign="top">
									<xsl:if test="not ( normalize-space(string(cac:PartyName/cbc:Name)) = &apos;&apos; )">
										<xsl:value-of select="cac:PartyName/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<xsl:text> - </xsl:text><xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:PostalZone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
										<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:CityName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<br/>
										<xsl:for-each select="cac:PostalAddress/cac:Country/cbc:IdentificationCode">
											<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each><br/>
									</xsl:if>
								</td>
								<td colSpan="2" valign="top">
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Name)) = &apos;&apos; )">
										<xsl:value-of select="cac:Contact/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telephone)) = &apos;&apos; )">
										<br/><xsl:text>Tel.: </xsl:text><xsl:value-of select="cac:Contact/cbc:Telephone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
										<br/><xsl:text>E-mail: </xsl:text><xsl:value-of select="cac:Contact/cbc:ElectronicMail"/>
									</xsl:if>
								</td>
								</xsl:for-each>
							</tr>
							
							<tr class="labelBold labelWhite">
								<td colSpan="4">Currency Information</td>
								<td colSpan="2">Payee Party</td>
								<td colSpan="2">Payee ContactParty</td>
							</tr>
							
							<xsl:for-each select="cac:PayeeParty">
							<tr>
								<td colSpan="4">
									Document currency: <xsl:value-of select="cbc:DocumentCurrencyCode"/>
									<br/><br/>
									Tax currency: <xsl:value-of select="cbc:TaxCurrencyCode"/>
									<br/><br/>
									Currency of payment: <xsl:value-of select="cac:PaymentMeans/cac:PayeeFinancialAccount/cbc:CurrencyCode"/>
								</td>
								<td colSpan="2" valign="top">
									<xsl:if test="not ( normalize-space(string(cac:PartyName/cbc:Name)) = &apos;&apos; )">
										<xsl:value-of select="cac:PartyName/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<xsl:text> - </xsl:text><xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:PostalZone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
										<xsl:text>&#160;</xsl:text>
										<xsl:value-of select="cac:PostalAddress/cbc:CityName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<br/>
										<xsl:for-each select="cac:PostalAddress/cac:Country/cbc:IdentificationCode">
											<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each>
									</xsl:if>
								</td>
								<td colSpan="2" valign="top">
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Name)) = &apos;&apos; )">
										<xsl:value-of select="cac:Contact/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telephone)) = &apos;&apos; )">
										<br/><xsl:text>Tel.: </xsl:text><xsl:value-of select="cac:Contact/cbc:Telephone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
										<br/><xsl:text>E-mail: </xsl:text><xsl:value-of select="cac:Contact/cbc:ElectronicMail"/>
									</xsl:if>
								</td>
							</tr>
							</xsl:for-each>
							
							<tr class="labelBold labelWhite">
								<td colspan="8">Payment Terms Information</td>
							</tr>
							<tr style="background-color:silver; ">
								<td colspan="2">Notes</td>
								<td colspan="2">Settlement Period</td>
								<td colSpan="2">Penalty Period</td>
								<td colSpan="2">Penalty fees</td>
							</tr>
							
							<xsl:for-each select="cac:PaymentTerms">
							<tr>
								<td colspan="2" valign="top"><xsl:value-of select="cbc:Note"/></td>
								<td colspan="2">
									start date: 
									<xsl:for-each select="cac:SettlementPeriod/cbc:StartDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each><br/><br/>
									end date: 
									<xsl:for-each select="cac:SettlementPeriod/cbc:EndDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each>
								</td>
								<td colspan="2">
									start date: 
									<xsl:for-each select="cac:PenaltyPeriod/cbc:StartDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each>
									<br/><br/>
									end date: 
									<xsl:for-each select="cac:PenaltyPeriod/cbc:EndDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each>
								</td>
								<td colspan="2">
									Penalty surcharge: <xsl:value-of select="cbc:PenaltySurchargePercent"/>%<br/><br/>
									Penalty Amount: <xsl:value-of select="cbc:Amount"/>
								</td>
							</tr>
							</xsl:for-each>
							
							<tr class="labelBold labelWhite">
								<td colspan="8">Reminder Lines</td>
							</tr>
							<tr>
								<td colspan="8" style="padding: 0">
									<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none; " border="1" cellPadding="2" cellSpacing="0" width="100%">
										<tbody>
											<tr style="background-color:silver; ">
												<td colspan="5" align="center" class="labelBold">Line description</td>
											</tr>
											<tr style="background-color:#e1e1e1; ">
												<td width="10%" align="center">Line ID</td>
												<td width="30%">Invoice Reference</td>
												<td width="15%" align="center">Issue date</td>
												<td width="30%">Credite Note Reference</td>
												<td width="15%" align="center">Issue date</td>
											</tr>
											<xsl:for-each select="cac:ReminderLine">
											<tr>
												<td align="center"><xsl:value-of select="cbc:ID"/></td>
												<xsl:for-each select="cac:BillingReference">
												<td><xsl:value-of select="cac:InvoiceDocumentReference/cbc:ID"/><xsl:text>&#160;</xsl:text></td>
												<td align="center">
													<xsl:for-each select="cac:InvoiceDocumentReference/cbc:IssueDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td><xsl:value-of select="cac:CreditNoteDocumentReference/cbc:ID"/><xsl:text>&#160;</xsl:text></td>
												<td align="center">
													<xsl:for-each select="cac:CreditNoteDocumentReference/cbc:IssueDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
												</td>
												</xsl:for-each>
											</tr>
											</xsl:for-each>
										</tbody>
									</table>
								</td>
							</tr>
							
							<tr style="background-color:silver; " class="labelBold">
								<td colspan="8" align="center">Notes</td>
							</tr>
							<tr>
								<td colspan="8" vAlign="top">
									<xsl:for-each select="cbc:Note">
										<xsl:apply-templates/><br/><br/>
									</xsl:for-each>
								</td>
							</tr>
							
							<tr class="labelBold labelWhite">
								<td colSpan="8">Payment Information</td>
							</tr>
							
							<xsl:if test="count(cac:PaymentMeans) != 0">
							<tr>
								<td style="padding: 0" colspan="8">
									<table width="100%" border="1" cellpadding="2" cellspacing="0">
									<tbody>
										<tr style="background-color:silver; ">
											<td>Payment due date</td>
											<td>Means of Payment</td>
											<td>Channel of payment</td>
											<td>Account number</td>
											<td>Credit account</td>
											<td>Seller&apos;s bank</td>
											<td>Payment instruction</td>
											<td>Payment note</td>
											<td>Sort code</td>
										</tr>
										<xsl:for-each select="cac:PaymentMeans">
											<tr>
												<td>
													<xsl:for-each select="cbc:PaymentDueDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
												</td>
												<td>
													<xsl:for-each select="cbc:PaymentMeansCode">
														<xsl:value-of select="document(&apos;PaymentMeansCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
													</xsl:for-each>
												</td>
												<td>
													<xsl:value-of select="cbc:PaymentChannelCode"/>
												</td>
												<td>
													<xsl:value-of select="cac:PayeeFinancialAccount/cbc:ID"/>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td>
													<xsl:value-of select="cac:CreditAccount/cbc:AccountID"/>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td>
													<xsl:value-of select="cac:PayeeFinancialAccount/cac:FinancialInstitutionBranch/cac:FinancialInstitution/cbc:ID"/>
													<xsl:text>&#160;</xsl:text>
													<xsl:value-of select="cac:PayeeFinancialAccount/cac:FinancialInstitutionBranch/cac:FinancialInstitution/cbc:Name"/>
												</td>
												<td>
													<xsl:value-of select="cbc:InstructionID"/>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td>
													<xsl:value-of select="cbc:InstructionNote"/>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td>
													<xsl:value-of select="cac:PayeeFinancialAccount/cac:FinancialInstitutionBranch/cbc:ID"/>
													<xsl:text>&#160;</xsl:text>
												</td>
											</tr>
										</xsl:for-each>
									</tbody>
								</table>
								</td>
							</tr>
							</xsl:if>
							
							<tr class="labelBold labelWhite">
								<td colSpan="8">Prepayment Information</td>
							</tr>
							
							<tr style="background-color:silver; ">
								<td align="center">Payment number</td>
								<td align="center" colspan="2">Paid amount</td>
								<td align="center">Paid date</td>
								<td align="center">Received date</td>
								<td colspan="3">Instruction ID</td>
							</tr>
							<xsl:for-each select="cac:PrepaidPayment">
								<tr>
									<td align="center"><xsl:value-of select="cbc:ID"/></td>
									<td align="center" colspan="2">
										<xsl:value-of select="cbc:PaidAmount"/>
										<xsl:text>&#160;</xsl:text>
										<xsl:value-of select="cbc:PaidAmount/@currencyID"/>
									</td>
									<td align="center">
										<xsl:for-each select="cbc:PaidDate">
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
										</xsl:for-each>

									</td>
									<td align="center">
										<xsl:for-each select="cbc:ReceivedDate">
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
										</xsl:for-each>
									</td>
									<td colspan="3"><xsl:value-of select="cbc:InstructionID"/></td>
								</tr>
							</xsl:for-each>
							
							<tr>
								<td colSpan="8">
									<xsl:text>Accounting cost: </xsl:text>
									<xsl:value-of select="cbc:AccountingCost"/>
								</td>
							</tr>
							<tr>
								<td colSpan="4">
									<xsl:text>Tax exchange rate source currency: </xsl:text>
									<xsl:value-of select="cac:TaxExchangeRate/cbc:SourceCurrencyCode"/>
								</td>
								<td colSpan="4">
									<xsl:text>Tax exchange rate target currency: </xsl:text>
									<xsl:value-of select="cac:TaxExchangeRate/cbc:TargetCurrencyCode"/>
								</td>
							</tr>
							<tr>
								<td colSpan="4">
									<xsl:text>Tax exchange rate date: </xsl:text>
									<xsl:for-each select="cac:TaxExchangeRate/cbc:Date">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each>
								</td>
								<td colSpan="4">
									<xsl:text>Tax exchange rate: </xsl:text>
									<xsl:value-of select="cac:TaxExchangeRate/cbc:TargetCurrencyBaseRate"/>
								</td>
							</tr>
							
							</xsl:for-each>
						</tbody>
					</table>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
