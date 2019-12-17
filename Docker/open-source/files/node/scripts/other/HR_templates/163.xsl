<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:mdcn="urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2" xmlns:n1="ec:schema:xsd:CreditNoteReceived-0.1" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="/">
		<html>
			<head>
				<meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
				<title>Credit Note Human Readable Format</title>
				<style type="text/css">
body { 	
	font-family: "Liberation Sans";
	font-size: small;
}
.labelBoldMedium {
	font-family: "Liberation Sans";
	font-size: medium;
	font-weight: bold;
}
.labelBoldSmall {
	font-family: "Liberation Sans";
	font-size: small;
	font-weight: bold;
}
.labelWhite {
	color:#f0f0f0;
}
.bg1{
	background-color:#595959;
}
.bg2{
	background-color:silver;
}
.bg3{
	background-color:#e1e1e1;
}
.bg4{
	background-color:red;
}
table.noBorder {
	font-family: "Liberation Sans";
	font-size: small;
	border-color:white;
	border-style:none;
}
td {
	vertical-align:text-top;
}
	</style>
</head>
<body>
<xsl:for-each select="$XML">
	<table border="1" cellPadding="0" cellSpacing="0" width="100%">
		<tbody>
			<tr>
				<td align="center" colSpan="8">
					<span class="labelBoldMedium">CREDIT NOTE</span>
				</td>
			</tr>
			<tr style="background-color:silver;">
				<td width="13%"><b><xsl:text>Credit note date</xsl:text></b></td>
				<td width="13%"><b><xsl:text>Registration date</xsl:text></b></td>
				<td width="24%" colspan="2"><b><xsl:text>Credit note number</xsl:text></b></td>
				<td width="15%"><b><xsl:text>Credit note period</xsl:text></b></td>
				<td width="21%" colspan="2"><b><xsl:text>Customer&apos;s assigned account ID</xsl:text></b></td>
				<td width="14%"><b><xsl:text>Tax point date</xsl:text></b></td>
			</tr>
			<tr>
				<td>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:IssueDate">
								<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
								<xsl:text>/</xsl:text>
								<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
								<xsl:text>/</xsl:text>
								<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="eccac:ECDocumentReceivedData">
							<xsl:for-each select="eccbc:RegistrationDate">
								<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
								<xsl:text>/</xsl:text>
								<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
								<xsl:text>/</xsl:text>
								<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td colspan="2">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:ID">
								<xsl:apply-templates/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td>
					<xsl:text>Start date:</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:InvoicePeriod">
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
						</xsl:for-each>
					</xsl:for-each>
					<br/>
					<xsl:text>End date:</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:InvoicePeriod">
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
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td colspan="2">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:AccountingSupplierParty">
								<xsl:for-each select="cbc:CustomerAssignedAccountID">
									<xsl:apply-templates/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
				</td>
				<td>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:TaxPointDate">
								<span>
									<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
								</span>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
			<tr class="bg2 labelBoldSmall">
				<td colSpan="2">Customer</td>
				<td colSpan="2">Customer contact</td>
				<td colSpan="2">Supplier</td>
				<td colSpan="2">Supplier contact</td>
			</tr>
			<tr>
				<td colSpan="2" valign="top">
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyIdentification">
											<xsl:for-each select="cbc:ID">
												<b>Party ID: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyLegalEntity">
											<xsl:for-each select="cbc:RegistrationName">
												<b>Registration name: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PartyName/cbc:Name)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyName">
											<xsl:for-each select="cbc:Name">
												<b>Name: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<br/>
					</xsl:if>
					<br/><b>Address:</b>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:Department">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:BuildingName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:BuildingNumber">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:StreetName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
						<xsl:text> - </xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:AdditionalStreetName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:Postbox">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cac:AddressLine">
												<xsl:for-each select="cbc:Line">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:PostalZone">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:CityName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cac:Country">
												<xsl:for-each select="cbc:IdentificationCode">
													<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:Region">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<br/>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cbc:EndpointID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cbc:EndpointID">
											<b>Endpoint ID: </b><xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyTaxScheme">
											<xsl:for-each select="cac:TaxScheme">
												<xsl:for-each select="cbc:ID">
													<b><xsl:apply-templates/>: </b>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyTaxScheme">
											<xsl:for-each select="cbc:CompanyID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PartyTaxScheme/cbc:ExemptionReason)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyTaxScheme">
											<xsl:for-each select="cbc:ExemptionReason">
												<b>Exemption: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyLegalEntity">
											<xsl:for-each select="cbc:CompanyID">
												<b>Party legal entity: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
				</td>
				<td valign="top" colSpan="2">
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:Contact/cbc:Name)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:Name">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:ID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:Contact/cbc:Telephone)) = &apos;&apos; )">
						<br/>
						<b>Tel: </b>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:Telephone">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:Contact/cbc:Telefax)) = &apos;&apos; )">
						<br/>
						<b>Fax: </b>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:Telefax">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingCustomerParty/cac:Party/cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
						<br/>
						<b>Email: </b>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingCustomerParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:ElectronicMail">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
				</td>
				<td colspan="2" valign="top">
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyIdentification">
											<xsl:for-each select="cbc:ID">
												<b>Party ID: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyLegalEntity">
											<xsl:for-each select="cbc:RegistrationName">
												<b>Registration name: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PartyName/cbc:Name)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyName">
											<xsl:for-each select="cbc:Name">
												<b>Name: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<br/>
					</xsl:if>
					<br/><b>Address:</b>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:Department">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:BuildingName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:BuildingNumber">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:StreetName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
						<xsl:text> - </xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:AdditionalStreetName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:Postbox">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cac:AddressLine">
												<xsl:for-each select="cbc:Line">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:PostalZone">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:CityName">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cac:Country">
												<xsl:for-each select="cbc:IdentificationCode">
													<span>
														<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
													</span>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PostalAddress">
											<xsl:for-each select="cbc:Region">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<br/>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cbc:EndpointID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cbc:EndpointID">
											<b>Endpoint ID: </b><xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyTaxScheme">
											<xsl:for-each select="cac:TaxScheme">
												<xsl:for-each select="cbc:ID">
													<b><xsl:apply-templates/>: </b>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyTaxScheme">
											<xsl:for-each select="cbc:CompanyID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:ExemptionReason)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyTaxScheme">
											<xsl:for-each select="cbc:ExemptionReason">
												<b>Exemption: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:PartyLegalEntity">
											<xsl:for-each select="cbc:CompanyID">
												<b>Party legal entity: </b><xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
				</td>
				<td colSpan="2" valign="top">
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:Contact/cbc:Name)) = &apos;&apos; )">
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:Name">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:ID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:Contact/cbc:Telephone)) = &apos;&apos; )">
						<br/>
						<b>Tel: </b>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:Telephone">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:Contact/cbc:Telefax)) = &apos;&apos; )">
						<br/>
						<b>Fax: </b>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:Telefax">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(n1:CreditNoteReceived/mdcn:CreditNote/cac:AccountingSupplierParty/cac:Party/cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
						<br/>
						<b>Email: </b>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="mdcn:CreditNote">
								<xsl:for-each select="cac:AccountingSupplierParty">
									<xsl:for-each select="cac:Party">
										<xsl:for-each select="cac:Contact">
											<xsl:for-each select="cbc:ElectronicMail">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
				</td>
			</tr>
			<tr class="bg2 labelBoldSmall">
				<td colSpan="8">Currency information</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:text>Document currency: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:DocumentCurrencyCode">
								<xsl:apply-templates/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<br/>
					<xsl:text>Tax currency: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:TaxCurrencyCode">
								<xsl:apply-templates/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<br/>
					<xsl:text>Currency of payment: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:PaymentMeans">
								<xsl:for-each select="cac:PayeeFinancialAccount">
									<xsl:for-each select="cbc:CurrencyCode">
										<xsl:apply-templates/>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
			<tr class="bg2 labelBoldSmall">
				<td colspan="8">Contractual information</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:text>Framework contract reference: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:ContractDocumentReference">
								<xsl:for-each select="cbc:ID">
									<xsl:apply-templates/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<br/>
					<xsl:text>Framework contract date: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:ContractDocumentReference">
								<xsl:for-each select="cbc:IssueDate">
									<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<br/>
					<xsl:text>Specific contract / Order form / purchase order reference: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:OrderReference">
								<xsl:for-each select="cbc:ID">
									<xsl:apply-templates/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<br/>
					<xsl:text>Specific contract / Order form / order date: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:OrderReference">
								<xsl:for-each select="cbc:IssueDate">
									<span>
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td colSpan="8">
				<xsl:for-each select="n1:CreditNoteReceived">
					<xsl:for-each select="mdcn:CreditNote">
					<xsl:if test="count(cac:BillingReference) != 0">
						<table class="noBorder" border="1" cellPadding="0" cellSpacing="0" width="100%">
							<tbody>
								<tr class="bg2">
									<td class="labelBoldSmall" colSpan="3">Billing references</td>
								</tr>
								<tr class="bg3">
									<td class="labelBoldSmall" width="25%">ID</td>
									<td class="labelBoldSmall" width="40%">Document type</td>
									<td class="labelBoldSmall" width="35%">Issue date</td>
								</tr>
								<xsl:for-each select="cac:BillingReference/cac:InvoiceDocumentReference">
									<tr>
										<td>
											<xsl:value-of select="cbc:ID"/>
											<xsl:text>&#160;</xsl:text>
										</td>
										<td>
											<xsl:for-each select="cbc:DocumentTypeCode">
											<xsl:value-of select="document(&apos;DocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
											</xsl:for-each>
											<xsl:text>&#160;</xsl:text>
										</td>
										<td>
											<xsl:for-each select="cbc:IssueDate">
												<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
											</xsl:for-each>
											<xsl:text>&#160;</xsl:text>
										</td>
									</tr>
									</xsl:for-each>
									<xsl:for-each select="cac:BillingReference/cac:CreditNoteDocumentReference">
									<tr>
										<td>
											<xsl:value-of select="cbc:ID"/>
											<xsl:text>&#160;</xsl:text>
										</td>
										<td>
											<xsl:for-each select="cbc:DocumentTypeCode">
											<xsl:value-of select="document(&apos;DocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
											</xsl:for-each>
											<xsl:text>&#160;</xsl:text>
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
											<xsl:text>&#160;</xsl:text>
										</td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="count(cac:AdditionalDocumentReference) != 0">
						<table class="noBorder" border="1" cellPadding="0" cellSpacing="0" width="100%">
							<tbody>
								<tr class="bg2">
									<td class="labelBoldSmall" colSpan="3">Additional references</td>
								</tr>
								<tr class="bg3">
									<td class="labelBoldSmall" width="25%">ID</td>
									<td class="labelBoldSmall" width="40%">Document type</td>
									<td class="labelBoldSmall" width="35%">Issue date</td>
								</tr>
								<xsl:for-each select="cac:AdditionalDocumentReference">
									<tr>
										<td>
											<xsl:for-each select="cbc:ID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</td>
										<td>
											<xsl:choose>
												<xsl:when test="count(cbc:DocumentType) != 0 and not ( normalize-space(string(cbc:DocumentType)) = &apos;&apos; )">
													<xsl:value-of select="cbc:DocumentType"/>
												</xsl:when>
												<xsl:otherwise>
													<xsl:for-each select="cbc:DocumentTypeCode">
													<xsl:value-of select="document(&apos;DocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
													</xsl:for-each>
												</xsl:otherwise>
											</xsl:choose>
											<xsl:text>&#160;</xsl:text>
										</td>
										<td>
											<xsl:for-each select="cbc:IssueDate">
												<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
											</xsl:for-each>
											<xsl:text>&#160;</xsl:text>
										</td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					</xsl:for-each>
				</xsl:for-each>
				</td>
			</tr>
			<tr class="bg1">
				<td class="labelBoldMedium labelWhite" colspan="8">Credit note lines</td>
			</tr>
			<tr>
				<td colspan="8">
					<table class="noBorder" border="1" cellPadding="2" cellSpacing="0" width="100%">
						<tbody>
							<tr class="bg2">
								<td class="labelBoldMedium" align="center" colspan="7">Lines description</td>
							</tr>
							<tr class="bg3">
								<td width="6%" align="center"><b><xsl:text>Line ID</xsl:text></b></td>
								<td width="45%"><b><xsl:text>Description</xsl:text></b></td>
								<td width="15%" align="center"><b><xsl:text>Order reference (line)</xsl:text></b></td>
								<td width="7%" align="center"><b><xsl:text>Quantity</xsl:text></b></td>
								<td width="13%" align="center"><b><xsl:text>Unit price</xsl:text></b></td>
								<td width="14%" align="center"><b><xsl:text>Amount</xsl:text></b></td>
							</tr>
							<xsl:for-each select="n1:CreditNoteReceived">
								<xsl:for-each select="mdcn:CreditNote">
									<xsl:for-each select="cac:CreditNoteLine">
										<tr>
											<td align="center">
												<xsl:for-each select="cbc:ID">
													<xsl:apply-templates/>
												</xsl:for-each>
											</td>
											<td>
												<xsl:value-of select="cac:Item/cbc:Name"/>
												<xsl:if test="not(normalize-space(string(cac:Item/cbc:Description)) = &apos;&apos;)">
													<br/><xsl:value-of select="cac:Item/cbc:Description"/>
												</xsl:if>
												<xsl:if test="not(normalize-space(string(cac:Item/cbc:ModelName)) = &apos;&apos;)">
													<br/>
													<i>Model:</i><xsl:text>&#160;</xsl:text>
													<xsl:value-of select="cac:Item/cbc:ModelName"/>
												</xsl:if>
												<xsl:if test="not(normalize-space(string(cac:Item/cac:CommodityClassification/cbc:CommodityCode)) = &apos;&apos;)">
													<br/><i>Commodity code:</i><xsl:text>&#160;</xsl:text>
													<xsl:value-of select="cac:Item/cac:CommodityClassification/cbc:CommodityCode"/>
												</xsl:if>
												<xsl:if test="not(normalize-space(string(cbc:Note)) = &apos;&apos;)">
													<br/><br/>
													<i>Note:</i><xsl:text>&#160;</xsl:text>
													<xsl:value-of select="cbc:Note"/>
												</xsl:if>
												<xsl:if test="count(cac:Item/cac:AdditionalItemProperty) &gt; 0">
													<br/>
													<xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
														<br/><i><xsl:value-of select="cbc:Name"/>: </i><xsl:value-of select="cbc:Value"/>
													</xsl:for-each>
												</xsl:if>
												<xsl:variable name="elements" select="count(cac:Item/cac:ItemInstance)" as="xs:integer"/>
												<xsl:if test="$elements &gt; 0">
													<br/>
													<i>SN:</i><xsl:text>&#160;</xsl:text>
													<xsl:for-each select="cac:Item/cac:ItemInstance/cbc:SerialID">
														<xsl:apply-templates/>
														<xsl:if test="$elements &gt; position()">, </xsl:if>
													</xsl:for-each>
												</xsl:if>
												<xsl:if test="count(cac:Item/cac:SellersItemIdentification) &gt; 0">
													<br/><br/>
													<i>Seller:</i><xsl:text>&#160;</xsl:text>
													<xsl:value-of select="cac:Item/cac:SellersItemIdentification/cbc:ID"/>
												</xsl:if>
												<xsl:if test="count(cac:Item/cac:ManufacturersItemIdentification) &gt; 0">
													<br/>
													<i>Manufacturer:</i><xsl:text>&#160;</xsl:text>
													<xsl:value-of select="cac:Item/cac:ManufacturersItemIdentification/cbc:ID"/>
												</xsl:if>
												<xsl:if test="count(cac:Item/cac:ManufacturerParty) &gt; 0">
													<xsl:for-each select="cac:Item/cac:ManufacturerParty">
														<br/>
														<i>Manufacturer Party <xsl:value-of select="position()"/>: </i><xsl:if test="count(cac:PartyIdentification/cbc:ID) &gt; 0"><xsl:value-of select="cac:PartyIdentification/cbc:ID"/>, </xsl:if><xsl:value-of select="cac:PartyName/cbc:Name"/>
													</xsl:for-each>
												</xsl:if>
											</td>
											<td align="center">
												<xsl:for-each select="cac:OrderLineReference">
													<xsl:for-each select="cac:OrderReference">
														<xsl:for-each select="cbc:ID">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
												<xsl:text>&#160;</xsl:text>
												<xsl:for-each select="cac:OrderLineReference">
													<xsl:for-each select="cbc:LineID">
														(<xsl:apply-templates/>)
													</xsl:for-each>
												</xsl:for-each>
											</td>
											<td align="center">
												<xsl:for-each select="cbc:CreditedQuantity">
													<xsl:apply-templates/>
												</xsl:for-each>
												<xsl:text>&#160;</xsl:text>
												<xsl:for-each select="cbc:CreditedQuantity">
													<xsl:for-each select="@unitCode">
														<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
													</xsl:for-each>
												</xsl:for-each>
											</td>
											<td align="right">
												<xsl:for-each select="cac:Price">
													<xsl:for-each select="cbc:PriceAmount">
														<span>
															<xsl:value-of select="format-number(round(number(.) * 10000) div 10000, '#.##0,00##', 'format1')"/>
														</span>
													</xsl:for-each>
												</xsl:for-each>
												<span>
													<xsl:text>&#160;</xsl:text>
												</span>
												<xsl:for-each select="cac:Price">
													<xsl:for-each select="cbc:PriceAmount">
														<xsl:for-each select="@currencyID">
															<span>
																<xsl:value-of select="string(.)"/>
															</span>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</td>
											<td align="right">
												<xsl:for-each select="cbc:LineExtensionAmount">
													<span>
														<xsl:value-of select="format-number(round(number(.) * 10000) div 10000, '#.##0,00##', 'format1')"/>
													</span>
												</xsl:for-each>
												<span>
													<xsl:text>&#160;</xsl:text>
												</span>
												<xsl:for-each select="cbc:LineExtensionAmount">
													<xsl:for-each select="@currencyID">
														<span>
															<xsl:value-of select="string(.)"/>
														</span>
													</xsl:for-each>
												</xsl:for-each>
											</td>
										</tr>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" rowspan="10" vAlign="middle">
					<b><xsl:text>Clauses and / or notes:</xsl:text></b>
					<br/>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:Note">
								<xsl:apply-templates/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td style="background-color:silver; " colspan="6">
					<b><xsl:text>Credit note totals</xsl:text></b>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">
					<xsl:text>Total line amount</xsl:text>
				</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:LineExtensionAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:LineExtensionAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">Total charge amount</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:ChargeTotalAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:ChargeTotalAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">Total allowance amount</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:AllowanceTotalAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:AllowanceTotalAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">
					<xsl:text>Tax exclusive amount</xsl:text>
				</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:TaxExclusiveAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:TaxExclusiveAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">
					<xsl:text>Total tax amount</xsl:text>
				</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<span>
								<xsl:value-of select="format-number(round(sum(cac:TaxTotal/cbc:TaxAmount) *100 ) div 100, '#.##0,00', 'format1')"/>
							</span>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<span>
								<xsl:value-of select="((./cac:TaxTotal)[1])/cbc:TaxAmount/@currencyID"/>
							</span>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">
					<xsl:text>Tax inclusive amount </xsl:text>
				</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:TaxInclusiveAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:TaxInclusiveAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">Payable rounding amount</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:PayableRoundingAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:PayableRoundingAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="3">Prepaid amount</td>
				<td align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:PrepaidAmount">
									<span>
										<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:PrepaidAmount">
									<xsl:for-each select="@currencyID">
										<span>
											<xsl:value-of select="string(.)"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td class="labelBoldMedium" align="right" colSpan="3">Total amount due</td>
				<td class="labelBoldMedium" align="right" colSpan="3">
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:PayableAmount">
									<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:LegalMonetaryTotal">
								<xsl:for-each select="cbc:PayableAmount">
									<xsl:for-each select="@currencyID">
										<xsl:value-of select="string(.)"/>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr class="bg1 labelBoldMedium labelWhite">
				<td colspan="8">Payment information</td>
			</tr>
			<tr>
				<td colSpan="4">Terms of payment:</td>
				<td colSpan="4">Means of payment:</td>
			</tr>
			<tr>
				<td colSpan="4">Channel of payment:</td>
				<td colSpan="4">Seller&apos;s bank:</td>
			</tr>
			<tr>
				<td colSpan="4">Account number:</td>
				<td colSpan="4">Payment instruction:</td>
			</tr>
			<tr>
				<td colSpan="4">Payment note:</td>
				<td colSpan="4">Credit account:</td>
			</tr>
			<tr>
				<td colSpan="4">
					<xsl:text>Accounting cost: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cbc:AccountingCost">
								<xsl:apply-templates/>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td colSpan="4">Sort code:</td>
			</tr>
			<tr>
				<td colSpan="4">
					<xsl:text>Tax exchange rate source currency: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:TaxExchangeRate">
								<xsl:for-each select="cbc:SourceCurrencyCode">
									<xsl:apply-templates/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td colSpan="4">
					<xsl:text>Tax exchange rate target currency: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:TaxExchangeRate">
								<xsl:for-each select="cbc:TargetCurrencyCode">
									<xsl:apply-templates/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td colSpan="4">
					<xsl:text>Tax exchange rate date: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:TaxExchangeRate">
								<xsl:for-each select="cbc:Date">
									<span>
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
									</span>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
				<td colSpan="4">
					<xsl:text>Tax exchange rate: </xsl:text>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:for-each select="cac:TaxExchangeRate">
								<xsl:for-each select="cbc:TargetCurrencyBaseRate">
									<xsl:apply-templates/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td colSpan="8"><xsl:text>&#160;</xsl:text></td>
			</tr>
			<tr class="bg1 labelBoldMedium labelWhite">
				<td colSpan="8">Additional document information</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="count(n1:CreditNoteReceived/mdcn:CreditNote/cac:AllowanceCharge) != 0">
						<table class="noBorder" border="1" cellPadding="0" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td class="bg2" align="center" colSpan="8">
										<span class="labelBoldMedium">Allowances and Charges at document level</span>
									</td>
								</tr>
								<tr class="bg3 labelBoldSmall">
									<td width="12%" align="center">Type</td>
									<td width="12%" align="center">Sequence</td>
									<td width="12%" align="center">Reason code</td>
									<td width="14%">Reason additional text</td>
									<td width="12%" align="center">Base amount</td>
									<td width="12%" align="center">Multiplier factor</td>
									<td width="12%" align="center">Amount</td>
									<td width="12%" align="center">Tax category<br/>(type / ID / rate)</td>
								</tr>
								<xsl:for-each select="n1:CreditNoteReceived">
									<xsl:for-each select="mdcn:CreditNote">
										<xsl:for-each select="cac:AllowanceCharge">
											<tr>
												<td align="center">
													<xsl:choose>
														<xsl:when test="cbc:ChargeIndicator = boolean(true)">
															<xsl:text>allowance</xsl:text>
														</xsl:when>
														<xsl:otherwise>
															<xsl:text>charge</xsl:text>
														</xsl:otherwise>
													</xsl:choose>
												</td>
												<td align="center">
													<xsl:for-each select="cbc:SequenceNumeric">
														<xsl:apply-templates/>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td align="center">
													<xsl:for-each select="cbc:AllowanceChargeReasonCode">
														<xsl:value-of select="document(&apos;AllowanceChargeReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
													</xsl:for-each>
												</td>
												<td>
													<xsl:for-each select="cbc:AllowanceChargeReason">
														<xsl:apply-templates/>
													</xsl:for-each>
												</td>
												<td align="right">
													<xsl:for-each select="cbc:BaseAmount">
														<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
													<xsl:for-each select="cbc:BaseAmount">
														<xsl:for-each select="@currencyID">
															<xsl:value-of select="string(.)"/>
														</xsl:for-each>
													</xsl:for-each>
												</td>
												<td align="center">
													<xsl:for-each select="cbc:MultiplierFactorNumeric">
														<xsl:apply-templates/>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td align="right">
													<xsl:for-each select="cbc:Amount">
														<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
													<xsl:for-each select="cbc:Amount">
														<xsl:for-each select="@currencyID">
															<xsl:value-of select="string(.)"/>
														</xsl:for-each>
													</xsl:for-each>
												</td>
												<td align="center">
													<xsl:for-each select="cac:TaxCategory">
														<xsl:for-each select="cac:TaxScheme">
															<xsl:for-each select="cbc:ID">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
													<span>
														<xsl:text> / </xsl:text>
													</span>
													<xsl:for-each select="cac:TaxCategory">
														<xsl:for-each select="cbc:ID">
															<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
														</xsl:for-each>
													</xsl:for-each>
													<xsl:if test="not(normalize-space(string(./cac:TaxCategory/cbc:Percent)) = &apos;&apos;)">
														<xsl:text> / </xsl:text>
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cbc:Percent">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
														<xsl:text>%</xsl:text>
													</xsl:if>
													<br/>
													<xsl:if test="not ( normalize-space(string(./cac:TaxCategory/cbc:PerUnitAmount)) = &apos;&apos; )">
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cbc:PerUnitAmount">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
														<xsl:text>&#160;</xsl:text>
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cbc:PerUnitAmount">
																<xsl:for-each select="@currencyID">
																	<xsl:value-of select="string(.)"/>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
														<xsl:text> per unit</xsl:text>
													</xsl:if>
												</td>
											</tr>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<br/>
				</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="count(n1:CreditNoteReceived/mdcn:CreditNote/cac:TaxTotal ) &gt; 0">
						<table class="noBorder" border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td class="bg2 labelBoldMedium" align="center" colspan="6">Tax subtotals at document level</td>
								</tr>
								<tr class="bg3 labelBoldSmall">
									<td width="12%" align="center">Tax type</td>
									<td width="14%" align="center">Tax category</td>
									<td width="10%" align="center">Tax rate</td>
									<td width="32%" align="center">Tax exemption reason</td>
									<td width="16%" align="center">Taxable amount</td>
									<td width="16%" align="center">Tax amount</td>
								</tr>
								<xsl:for-each select="n1:CreditNoteReceived">
									<xsl:for-each select="mdcn:CreditNote">
										<xsl:for-each select="cac:TaxTotal">
											<xsl:for-each select="cac:TaxSubtotal">
												<tr>
													<td align="center">
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cac:TaxScheme">
																<xsl:for-each select="cbc:ID">
																	<xsl:apply-templates/>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
														<br/>
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cac:TaxScheme">
																<xsl:for-each select="cbc:Name">
																	(<xsl:apply-templates/>)
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
													<td align="center">
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cbc:ID">
																<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
															</xsl:for-each>
														</xsl:for-each>
													</td>
													<td align="center">
														<xsl:if test="count(./cac:TaxCategory/cbc:Percent)&gt;0">
															<xsl:for-each select="cac:TaxCategory">
																<xsl:for-each select="cbc:Percent">
																	<xsl:apply-templates/>
																	<xsl:text>%</xsl:text>
																</xsl:for-each>
															</xsl:for-each>
															<br/>
														</xsl:if>
														<xsl:if test="count(./cac:TaxCategory/cbc:PerUnitAmount)&gt;0">
															<xsl:for-each select="cac:TaxCategory">
																<xsl:for-each select="cbc:PerUnitAmount">
																	<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
																</xsl:for-each>
															</xsl:for-each>
															<xsl:text>&#160;</xsl:text>
															<xsl:for-each select="cac:TaxCategory">
																<xsl:for-each select="cbc:PerUnitAmount">
																	<xsl:for-each select="@currencyID">
																		<xsl:value-of select="string(.)"/>
																		<xsl:text> per unit</xsl:text>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:if>
													</td>
													<td>
														<xsl:if test="not ( normalize-space(string(cac:TaxCategory/cbc:TaxExemptionReasonCode)) = &apos;&apos; )">
														<xsl:for-each select="cac:TaxCategory/cbc:TaxExemptionReasonCode">
																<xsl:value-of select="document(&apos;TaxExemptionReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
														</xsl:for-each>
														<br/>
														</xsl:if>
														<xsl:value-of select="cac:TaxCategory/cbc:TaxExemptionReason"/>
														<xsl:text>&#160;</xsl:text>
													</td>
													<td align="right">
														<xsl:for-each select="cbc:TaxableAmount">
															<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
														</xsl:for-each>
														<xsl:text>&#160;</xsl:text>
														<xsl:for-each select="cbc:TaxableAmount">
															<xsl:for-each select="@currencyID">
																<xsl:value-of select="string(.)"/>
															</xsl:for-each>
														</xsl:for-each>
													</td>
													<td align="right">
														<xsl:for-each select="cbc:TaxAmount">
															<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
														</xsl:for-each>
														<xsl:text>&#160;</xsl:text>
														<xsl:for-each select="cbc:TaxAmount">
															<xsl:for-each select="@currencyID">
																<xsl:value-of select="string(.)"/>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<br/>
				</td>
			</tr>
			<tr class="bg1">
				<td class="labelBoldMedium labelWhite" colSpan="8">Additional line information</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="count(n1:CreditNoteReceived/mdcn:CreditNote/cac:CreditNoteLine/cac:TaxTotal ) &gt; 0">
						<table class="noBorder" border="2" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td class="bg2 labelBoldMedium" align="center" colspan="6">Tax subtotals at line level</td>
								</tr>
								<tr class="bg3 labelBoldSmall">
									<td width="7%" align="center">Line ID</td>
									<td width="17%" align="center">Tax type</td>
									<td width="17%" align="center">Tax category</td>
									<td width="17%" align="center">Tax rate</td>
									<td width="20%" align="center">Taxable amount</td>
									<td width="20%" align="center">Tax amount</td>
								</tr>
								<xsl:for-each select="n1:CreditNoteReceived">
									<xsl:for-each select="mdcn:CreditNote">
										<xsl:for-each select="cac:CreditNoteLine">
											<tr>
												<td align="center">
													<xsl:for-each select="cbc:ID">
														<xsl:apply-templates/>
													</xsl:for-each>
												</td>
												<td align="center">
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cac:TaxSubtotal">
															<xsl:for-each select="cac:TaxCategory">
																<xsl:for-each select="cac:TaxScheme">
																	<xsl:for-each select="cbc:ID">
																		<xsl:apply-templates/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
													<br/>
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cac:TaxSubtotal">
															<xsl:for-each select="cac:TaxCategory">
																<xsl:for-each select="cac:TaxScheme">
																	<xsl:for-each select="cbc:Name">
																		(<xsl:apply-templates/>)
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</td>
												<td align="center">
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cac:TaxSubtotal">
															<xsl:for-each select="cac:TaxCategory">
																<xsl:for-each select="cbc:ID">
																	<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
												</td>
												<td align="center">
													<xsl:if test="not ( normalize-space(string(./cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:Percent)) = &apos;&apos; )">
														<xsl:for-each select="cac:TaxTotal">
															<xsl:for-each select="cac:TaxSubtotal">
																<xsl:for-each select="cac:TaxCategory">
																	<xsl:for-each select="cbc:Percent">
																		<xsl:apply-templates/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
														<xsl:text>%</xsl:text>
													</xsl:if>
													<br/>
													<xsl:if test="not ( normalize-space(string(./cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:PerUnitAmount)) = &apos;&apos; )">
														<xsl:for-each select="cac:TaxTotal">
															<xsl:for-each select="cac:TaxSubtotal">
																<xsl:for-each select="cac:TaxCategory">
																	<xsl:for-each select="cbc:PerUnitAmount">
																		<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
														<xsl:text>&#160;</xsl:text>
														<xsl:for-each select="cac:TaxTotal">
															<xsl:for-each select="cac:TaxSubtotal">
																<xsl:for-each select="cac:TaxCategory">
																	<xsl:for-each select="cbc:PerUnitAmount">
																		<xsl:for-each select="@currencyID">
																			<span>
																				<xsl:value-of select="string(.)"/>
																			</span>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
														<xsl:text> per unit</xsl:text>
													</xsl:if>
												</td>
												<td align="right">
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cac:TaxSubtotal">
															<xsl:for-each select="cbc:TaxableAmount">
																<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cac:TaxSubtotal">
															<xsl:for-each select="cbc:TaxableAmount">
																<xsl:for-each select="@currencyID">
																	<xsl:value-of select="string(.)"/>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</td>
												<td align="right">
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cbc:TaxAmount">
															<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
														</xsl:for-each>
													</xsl:for-each>
													<xsl:text>&#160;</xsl:text>
													<xsl:for-each select="cac:TaxTotal">
														<xsl:for-each select="cac:TaxSubtotal">
															<xsl:for-each select="cbc:TaxAmount">
																<xsl:for-each select="@currencyID">
																	<xsl:value-of select="string(.)"/>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</td>
											</tr>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="count(n1:CreditNoteReceived/mdcn:CreditNote/cac:CreditNoteLine/cac:Item/cac:ClassifiedTaxCategory ) &gt; 0">
						<table class="noBorder" border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td class="bg2 labelBoldMedium" align="center" colspan="6">Tax classified category at line level</td>
								</tr>
								<tr class="bg3 labelBoldSmall">
									<td width="12%" align="center">Tax type</td>
									<td width="15%" align="center">Tax category</td>
									<td width="15%" align="center">Tax rate</td>
									<td width="58%">Tax exemption reason</td>
								</tr>
								<xsl:for-each-group select="n1:CreditNoteReceived/mdcn:CreditNote/cac:CreditNoteLine/cac:Item/cac:ClassifiedTaxCategory" group-by="concat(cbc:ID,cbc:Percent)">
									<tr>
										<td align="center">
											<xsl:value-of select="cac:TaxScheme/cbc:ID"/>
										</td>
										<td align="center">
											<xsl:variable name="TaxCategoryID" select="cbc:ID"/>
											<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=$TaxCategoryID]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</td>
										<td align="center">
											<xsl:if test="not(normalize-space(string(cbc:Percent)) = &apos;&apos;)">
												<xsl:value-of select="cbc:Percent"/>
												<xsl:text>%</xsl:text>
												<xsl:text>&#160;</xsl:text>
											</xsl:if>
											<xsl:if test="not(normalize-space(string(cbc:PerUnitAmount)) = &apos;&apos;)">
												<xsl:value-of select="format-number(round(number(cbc:PerUnitAmount) * 100) div 100, '#.##0,00', 'format1')"/>
												<xsl:text>&#160;</xsl:text>
												<xsl:value-of select="cbc:PerUnitAmount/@currencyID"/>
												<xsl:text> per unit</xsl:text>
											</xsl:if>
										</td>
										<td>
											<xsl:if test="not ( normalize-space(string(cbc:TaxExemptionReasonCode)) = &apos;&apos; )">
											<xsl:variable name="TaxExemptionReasonCode" select="cbc:TaxExemptionReasonCode"/>
											<xsl:value-of select="document(&apos;TaxExemptionReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=$TaxExemptionReasonCode]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
											<br/>
											</xsl:if>
											<xsl:value-of select="cbc:TaxExemptionReason"/>
										</td>
									</tr>
								</xsl:for-each-group>
							</tbody>
						</table>
					</xsl:if>
					<xsl:for-each select="n1:CreditNoteReceived">
						<xsl:for-each select="mdcn:CreditNote">
							<xsl:if test="count(cac:CreditNoteLine/cac:Delivery) &gt; 0">
								<table class="noBorder" border="1" cellPadding="2" cellSpacing="0" width="100%">
								<tbody>
									<tr>
										<td class="bg2 labelBoldMedium" align="center" colSpan="2">Delivery at line level</td>
									</tr>
									<tr class="bg3 labelBoldSmall">
										<td width="7%" align="center">Line ID</td>
										<td width="93%">Delivery location</td>
									</tr>
									<xsl:for-each select="cac:CreditNoteLine">
										<tr>
											<td align="center">
												<xsl:value-of select="cbc:ID"/>
											</td>
											<td>
												<xsl:for-each select="cac:Delivery/cac:DeliveryLocation">
													<xsl:if test="not(normalize-space(string(cac:Address/cbc:Department)) = &apos;&apos;)">
														<xsl:value-of select="cac:Address/cbc:Department"/>
													</xsl:if>
													<xsl:if test="not(normalize-space(string(cac:Address/cbc:BuildingName)) = &apos;&apos;)">
														<br/><xsl:value-of select="cac:Address/cbc:BuildingName"/>
													</xsl:if>
													<xsl:if test="not(normalize-space(string(cac:Address/cbc:BuildingNumber)) = &apos;&apos;)">
														<xsl:text>&#160;</xsl:text>
														<xsl:value-of select="cac:Address/cbc:BuildingNumber"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cbc:StreetName)) = &apos;&apos; )">
														<br/><xsl:value-of select="cac:Address/cbc:StreetName"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cbc:AdditionalStreetName)) = &apos;&apos; )">
														<xsl:text> - </xsl:text>
														<xsl:value-of select="cac:Address/cbc:AdditionalStreetName"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
														<br/><xsl:value-of select="cac:Address/cac:AddressLine/cbc:Line"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cbc:Postbox)) = &apos;&apos; )">
														<xsl:text>&#160;</xsl:text>
														<xsl:value-of select="cac:Address/cbc:Postbox"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cbc:PostalZone)) = &apos;&apos; )">
														<br/><xsl:value-of select="cac:Address/cbc:PostalZone"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cbc:CityName)) = &apos;&apos; )">
														<xsl:text>&#160;</xsl:text>
														<xsl:value-of select="cac:Address/cbc:CityName"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
														<br/>
														<xsl:for-each select="cac:Address/cac:Country/cbc:IdentificationCode">
															<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
														</xsl:for-each>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cac:Address/cbc:Region)) = &apos;&apos; )">
														<xsl:text> - </xsl:text>
														<xsl:value-of select="cac:Address/cbc:Region"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cbc:Description)) = &apos;&apos; )">
														<br/><i>Description: </i><xsl:value-of select="cbc:Description"/>
													</xsl:if>
													<xsl:if test="not ( normalize-space(string(cbc:ID)) = &apos;&apos; )">
													<br/><i>ID: </i><xsl:value-of select="cbc:ID"/>
												</xsl:if>
												</xsl:for-each>
												<xsl:if test="not ( normalize-space(string(cac:Delivery/cac:EstimatedDeliveryPeriod/cbc:StartDate)) = &apos;&apos; )">
													<br/>
													<xsl:text>Estimated Delivery Period: </xsl:text>
													<xsl:for-each select="cac:Delivery/cac:EstimatedDeliveryPeriod/cbc:StartDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
												</xsl:if>
												<xsl:if test="not ( normalize-space(string(cac:Delivery/cac:EstimatedDeliveryPeriod/cbc:EndDate)) = &apos;&apos; )">
													<xsl:text> - </xsl:text>
													<xsl:for-each select="cac:Delivery/cac:EstimatedDeliveryPeriod/cbc:EndDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
												</xsl:if>
												<xsl:if test="not ( normalize-space(string(cac:Delivery/cac:RequestedDeliveryPeriod/cbc:StartDate)) = &apos;&apos; )">
													<br/>
													<xsl:text>Requested Delivery Period: </xsl:text>
													<xsl:for-each select="cac:Delivery/cac:RequestedDeliveryPeriod/cbc:StartDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
												</xsl:if>
												<xsl:if test="not ( normalize-space(string(cac:Delivery/cac:RequestedDeliveryPeriod/cbc:EndDate)) = &apos;&apos; )">
													<xsl:text> - </xsl:text>
													<xsl:for-each select="cac:Delivery/cac:RequestedDeliveryPeriod/cbc:EndDate">
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</xsl:for-each>
												</xsl:if>
												<xsl:text>&#160;</xsl:text>
											</td>
										</tr>
									</xsl:for-each>
								</tbody>
								</table>
							</xsl:if>
						</xsl:for-each>
					</xsl:for-each>
				</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<xsl:if test="count ( n1:CreditNoteReceived/eccac:ECDocumentReceivedData/eccac:ProcessingWarning/eccbc:FailedAssert ) &gt; 0">
		<br/>
		<table border="4" cellPadding="0" cellSpacing="0" width="100%">
			<tbody>
				<tr class="bg4">
					<td class="labelBoldMedium labelWhite" colSpan="8">MESSAGE WARNINGS:</td>
				</tr>
				<tr>
					<td>
						<xsl:for-each select="n1:CreditNoteReceived">
							<xsl:for-each select="eccac:ECDocumentReceivedData">
								<xsl:for-each select="eccac:ProcessingWarning">
									<xsl:for-each select="eccbc:FailedAssert">
										<p>
											<xsl:value-of select="document(&apos;ErrorCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=normalize-space(current())]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</p>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
	</xsl:if>
</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
