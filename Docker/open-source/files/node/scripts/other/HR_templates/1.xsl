<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2005/xpath-functions"
xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988"
xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1"
xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:mdinv="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"
xmlns:n1="ec:schema:xsd:InvoiceReceived-0.1" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2"
xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:functx="http://www.functx.com">
<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" use-character-maps="m1" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
<xsl:character-map name="m1">
  <xsl:output-character character="&#127;" string=" "/>
   <xsl:output-character character="&#128;" string=" "/>
   <xsl:output-character character="&#129;" string=" "/>
   <xsl:output-character character="&#130;" string=" "/>
   <xsl:output-character character="&#131;" string=" "/>
   <xsl:output-character character="&#132;" string=" "/>
   <xsl:output-character character="&#133;" string=" "/>
   <xsl:output-character character="&#134;" string=" "/>
   <xsl:output-character character="&#135;" string=" "/>
   <xsl:output-character character="&#136;" string=" "/>
   <xsl:output-character character="&#137;" string=" "/>
   <xsl:output-character character="&#138;" string=" "/>
   <xsl:output-character character="&#139;" string=" "/>
   <xsl:output-character character="&#140;" string=" "/>
   <xsl:output-character character="&#141;" string=" "/>
   <xsl:output-character character="&#142;" string=" "/>
   <xsl:output-character character="&#143;" string=" "/>
   <xsl:output-character character="&#144;" string=" "/>
   <xsl:output-character character="&#145;" string=" "/>
   <xsl:output-character character="&#146;" string=" "/>
   <xsl:output-character character="&#147;" string=" "/>
   <xsl:output-character character="&#148;" string=" "/>
   <xsl:output-character character="&#149;" string=" "/>
   <xsl:output-character character="&#150;" string=" "/>
   <xsl:output-character character="&#151;" string=" "/>
   <xsl:output-character character="&#152;" string=" "/>
   <xsl:output-character character="&#153;" string=" "/>
   <xsl:output-character character="&#154;" string=" "/>
   <xsl:output-character character="&#155;" string=" "/>
   <xsl:output-character character="&#156;" string=" "/>
   <xsl:output-character character="&#157;" string=" "/>
   <xsl:output-character character="&#158;" string=" "/>
   <xsl:output-character character="&#159;" string=" "/>
   <xsl:output-character character="&#x80;" string="&#x20AC;"/>
</xsl:character-map>
<xsl:param name="SV_OutputFormat" select="'HTML'"/>
<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>

<xsl:template name="split">
	<xsl:param name="pText" select="."/>
	<xsl:param name="pLength" select="."/>
	<xsl:if test="string-length($pText)&lt;=$pLength+5">
		<xsl:value-of select="$pText"/>
	</xsl:if>
	<xsl:if test="string-length($pText)&gt;$pLength+5">
		<xsl:value-of select="substring($pText,1,$pLength)"/><br/>
		<xsl:call-template name="split">
			<xsl:with-param name="pText" select="substring($pText,$pLength+1)"/>
			<xsl:with-param name="pLength" select="$pLength"/>
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template name="formatDate">
	<xsl:param name="date" select="."/>
	<xsl:value-of select="format-date($date, '[D01]/[M01]/[Y0001]', (), (), 'fr')"/>
</xsl:template>

<xsl:template match="cac:Party | cac:PayeeParty | TaxRepresentativeParty">
	<xsl:variable name="elements" select="count(./cac:PartyIdentification/cbc:ID)" as="xs:integer"/>
	<b><xsl:text>Party ID: </xsl:text></b>
	<xsl:for-each select="./cac:PartyIdentification/cbc:ID">
		<xsl:value-of select="."/>
		<xsl:if test="./@schemeID != &apos;&apos;"> (<xsl:value-of select="./@schemeID"/>)</xsl:if>
		<xsl:if test="$elements &gt; position()">, </xsl:if>
	</xsl:for-each>
	<br/>
</xsl:template>

<xsl:template match="/">
<html>
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
	<title>Invoice Human Readable Format</title>
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
<xsl:for-each select="/n1:InvoiceReceived">
	<table border="2" cellPadding="0" cellSpacing="0" width="100%">
		<tbody>
			<tr>
				<td align="center" colSpan="8">
					<span class="labelBoldMedium">INVOICE</span>
				</td>
			</tr>
			<tr style="background-color:silver;">
				<td width="13%"><b><xsl:text>Invoice issue date</xsl:text></b></td>
				<td width="13%"><b><xsl:text>Registration date</xsl:text></b></td>
				<td width="24%" colspan="2"><b><xsl:text>Invoice number</xsl:text></b></td>
				<td width="15%"><b><xsl:text>Invoice period</xsl:text></b></td>
				<td width="21%" colspan="2"><b><xsl:text>Customer&apos;s assigned account ID</xsl:text></b></td>
				<td width="14%"><b><xsl:text>Tax point date</xsl:text></b></td>
			</tr>
			<tr>
				<td>
					<xsl:call-template name="formatDate">
						<xsl:with-param name="date" select="mdinv:Invoice/cbc:IssueDate"/>
					</xsl:call-template>
				</td>
				<td>
					<xsl:call-template name="formatDate">
						<xsl:with-param name="date" select="eccac:ECDocumentReceivedData/eccbc:RegistrationDate"/>
					</xsl:call-template>
				</td>
				<xsl:for-each select="mdinv:Invoice">
				<td colspan="2"><xsl:value-of select="cbc:ID"/></td>
				<td>
					<xsl:for-each select="cac:InvoicePeriod">
						<xsl:text>Start date: </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cbc:StartDate"/>
						</xsl:call-template>
						<br/>
						<xsl:text>End date: </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cbc:EndDate"/>
						</xsl:call-template>
						<br/>
					</xsl:for-each>
				</td>
				<td colspan="2">
					<xsl:value-of select="cac:AccountingSupplierParty/cbc:CustomerAssignedAccountID"/>
					<xsl:text>&#160;</xsl:text>
				</td>
				<td>
					<xsl:call-template name="formatDate">
						<xsl:with-param name="date" select="cbc:TaxPointDate"/>
					</xsl:call-template>
					<xsl:text>&#160;</xsl:text>
				</td>
				</xsl:for-each>
			</tr>
			<tr class="bg2 labelBoldSmall">
				<td colSpan="2">Customer</td>
				<td colSpan="2">Customer contact</td>
				<td colSpan="2">Supplier</td>
				<td colSpan="2">Supplier contact</td>
			</tr>
			<xsl:for-each select="mdinv:Invoice">
			<tr>
				<xsl:for-each select="cac:AccountingCustomerParty/cac:Party">
				<td colSpan="2" valign="top">
					<xsl:if test="count (cac:PartyIdentification/cbc:ID) &gt; 0">
						<xsl:apply-templates select="."/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
						<b><xsl:text>Registration name: </xsl:text></b><xsl:value-of select="cac:PartyLegalEntity/cbc:RegistrationName"/>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyName/cbc:Name)) = &apos;&apos; )">
						<b><xsl:text>Name: </xsl:text></b><xsl:value-of select="cac:PartyName/cbc:Name"/>
						<br/>
					</xsl:if>
					<xsl:if test="exists(cac:PostalAddress)">
						<br/><b>Address:</b>
					</xsl:if>
					<xsl:choose>
						<xsl:when test="not ( normalize-space(string(/n1:InvoiceReceived/mdinv:Invoice/cbc:BuyerReference)) = &apos;&apos; )">
							<br/>Department code: <xsl:value-of select="/n1:InvoiceReceived/mdinv:Invoice/cbc:BuyerReference"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
								<br/>Department code: <xsl:value-of select="cac:PostalAddress/cbc:Department"/>
							</xsl:if>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="cac:PostalAddress/cbc:BuildingName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="cac:PostalAddress/cbc:BuildingNumber"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
						<xsl:text> - </xsl:text>
						<xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="cac:PostalAddress/cbc:Postbox"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PostalAddress/cac:AddressLine/cbc:Line"/>
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
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PostalAddress/cbc:Region"/>
					</xsl:if>
					<br/>
					<xsl:if test="not ( normalize-space(string(cbc:EndpointID)) = &apos;&apos; )">
						<br/><b>Endpoint ID: </b><xsl:value-of select="cbc:EndpointID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="cac:PartyTaxScheme/cac:TaxScheme/cbc:ID">
							<b><xsl:apply-templates/>: </b>
						</xsl:for-each>
						<xsl:value-of select="cac:PartyTaxScheme/cbc:CompanyID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyTaxScheme/cbc:ExemptionReason)) = &apos;&apos; )">
						<br/><b>Exemption: </b><xsl:value-of select="cac:PartyTaxScheme/cbc:ExemptionReason"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
						<br/><b>Party Legal Entity: </b><xsl:value-of select="cac:PartyLegalEntity/cbc:CompanyID"/>
					</xsl:if>
				</td>
				<td class="cellRightTopBorder" valign="top" colSpan="2">
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Name)) = &apos;&apos; )">
						<xsl:value-of select="cac:Contact/cbc:Name"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:Contact/cbc:ID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telephone)) = &apos;&apos; )">
						<br/><b>Tel: </b>
						<xsl:value-of select="cac:Contact/cbc:Telephone"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telefax)) = &apos;&apos; )">
						<br/><b>Fax: </b>
						<xsl:value-of select="cac:Contact/cbc:Telefax"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
						<br/><b>Email: </b>
						<xsl:value-of select="cac:Contact/cbc:ElectronicMail"/>
					</xsl:if>
				</td>
				</xsl:for-each>
				<xsl:for-each select="cac:AccountingSupplierParty/cac:Party">
				<td colspan="2" valign="top">
					<xsl:if test="count (cac:PartyIdentification/cbc:ID) &gt; 0">
						<xsl:apply-templates select="."/>
					</xsl:if>
					<xsl:variable name="CustomerID" select="normalize-space(/n1:InvoiceReceived/eccac:ECDocumentReceivedData/eccbc:CustomerID)"/>
					<xsl:if test="count(document(&apos;NoLef.xml&apos;)//SimpleCodeList/Row/Value[.=$CustomerID]) = 0">
						<xsl:for-each select="/n1:InvoiceReceived/eccac:ECDocumentReceivedData/eccbc:SupplierAdditionalIDs/eccbc:AdditionalID">
							<xsl:if test="./@schemeID=&apos;EC:LEF&apos;">
								<b>LEF ID: </b><xsl:value-of select="."/><br/>
							</xsl:if>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
						<b>Registration name: </b><xsl:value-of select="cac:PartyLegalEntity/cbc:RegistrationName"/>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyName/cbc:Name)) = &apos;&apos; )">
						<b>Name: </b><xsl:value-of select="cac:PartyName/cbc:Name"/>
						<br/>
					</xsl:if>
					<xsl:if test="exists(cac:PostalAddress)">
						<br/><b>Address:</b>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
						<br/>Department code: <xsl:value-of select="cac:PostalAddress/cbc:Department"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:BuildingName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:BuildingNumber"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
						<xsl:text> - </xsl:text><xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:Postbox"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PostalAddress/cac:AddressLine/cbc:Line"/>
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
					<xsl:if test="not ( normalize-space(string(cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PostalAddress/cbc:Region"/>
					</xsl:if>
					<br/>
					<xsl:if test="not ( normalize-space(string(cbc:EndpointID)) = &apos;&apos; )">
						<br/><b>Endpoint ID: </b><xsl:value-of select="cbc:EndpointID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
						<br/><b><xsl:value-of select="cac:PartyTaxScheme/cac:TaxScheme/cbc:ID"/>: </b>
						<xsl:value-of select="cac:PartyTaxScheme/cbc:CompanyID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyTaxScheme/cbc:ExemptionReason)) = &apos;&apos; )">
						<br/><b>Exemption: </b><xsl:value-of select="cac:PartyTaxScheme/cbc:ExemptionReason"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
						<br/><b>Party Legal Entity: </b><xsl:value-of select="cac:PartyLegalEntity/cbc:CompanyID"/>
					</xsl:if>
				</td>
				<td colSpan="2" valign="top">
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Name)) = &apos;&apos; )">
						<xsl:value-of select="cac:Contact/cbc:Name"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:Contact/cbc:ID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telephone)) = &apos;&apos; )">
						<br/><b>Tel: </b>
						<xsl:value-of select="cac:Contact/cbc:Telephone"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:Telefax)) = &apos;&apos; )">
						<br/><b>Fax: </b>
						<xsl:value-of select="cac:Contact/cbc:Telefax"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
						<br/><b>Email: </b>
						<xsl:value-of select="cac:Contact/cbc:ElectronicMail"/>
					</xsl:if>
				</td>
				</xsl:for-each>
			</tr>
			<tr class="bg2">
				<td colSpan="4"><b><xsl:text>Currency information</xsl:text></b></td>
				<td bgColor="#c0c0c0" colSpan="2"><b><xsl:text>Payee party</xsl:text></b></td>
				<td bgColor="#c0c0c0" colSpan="2"><b><xsl:text>Payee party contact</xsl:text></b></td>
			</tr>
			<tr>
				<td colSpan="4">
					<xsl:text>Document currency: </xsl:text>
					<xsl:value-of select="cbc:DocumentCurrencyCode"/>
					<br/>
					<xsl:text>Tax currency: </xsl:text>
					<xsl:value-of select="cbc:TaxCurrencyCode"/>
					<br/>
					<xsl:text>Currency of payment: </xsl:text>
					<xsl:value-of select="cac:PaymentMeans/cac:PayeeFinancialAccount/cbc:CurrencyCode"/>
					<xsl:text>&#160;</xsl:text>
				</td>
				<td colSpan="2">
					<xsl:if test="count (cac:PayeeParty/cac:PartyIdentification/cbc:ID) &gt; 0">
						<xsl:apply-templates select="cac:PayeeParty"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
						<b>Registration name: </b><xsl:value-of select="cac:PayeeParty/cac:PartyLegalEntity/cbc:RegistrationName"/>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PartyName/cbc:Name)) = &apos;&apos; )">
						<b>Name: </b><xsl:value-of select="cac:PayeeParty/cac:PartyName/cbc:Name"/>
						<br/>
					</xsl:if>
					<xsl:if test="exists(cac:PayeeParty/cac:PostalAddress)">
						<br/><b>Address:</b>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:Department"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:BuildingName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:BuildingNumber"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:StreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
						<xsl:text> - </xsl:text><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:AdditionalStreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:Postbox"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cac:AddressLine/cbc:Line"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:PostalZone"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:CityName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="cac:PayeeParty/cac:PostalAddress/cac:Country/cbc:IdentificationCode">
							<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:PostalAddress/cbc:Region"/>
					</xsl:if>
					<br/>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cbc:EndpointID)) = &apos;&apos; )">
						<br/><b>Endpoint ID: </b><xsl:value-of select="cac:PayeeParty/cbc:EndpointID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID)) = &apos;&apos; )">
						<br/><b><xsl:value-of select="cac:PayeeParty/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID"/>: </b>
						<xsl:value-of select="cac:PayeeParty/cac:PartyTaxScheme/cbc:CompanyID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
						<br/><b><xsl:text>Party Legal Entity: </xsl:text></b><xsl:value-of select="cac:PayeeParty/cac:PartyLegalEntity/cbc:CompanyID"/>
					</xsl:if>
					<xsl:text>&#160;</xsl:text>
				</td>
				<td colSpan="2">
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:Name)) = &apos;&apos; )">
						<xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:Name"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:ID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:Name)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:Name"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:ID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:Telephone)) = &apos;&apos; )">
						<br/><b>Tel: </b>
						<xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:Telephone"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:Telefax)) = &apos;&apos; )">
						<br/><b>Fax: </b>
						<xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:Telefax"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:PayeeParty/cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
						<br/><b>Email: </b>
						<xsl:value-of select="cac:PayeeParty/cac:Contact/cbc:ElectronicMail"/>
					</xsl:if>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
			<xsl:if test="cac:TaxRepresentativeParty !=''">
			<tr class="bg2">
				<td colSpan="4"></td>
				<td bgColor="#c0c0c0" colSpan="2"><b><xsl:text>Tax Representative party</xsl:text></b></td>
				<td bgColor="#c0c0c0" colSpan="2"><b><xsl:text>Tax Representative contact</xsl:text></b></td>
			</tr>
			<tr>
				<td colSpan="4"></td>
				<td colSpan="2">
					<xsl:if test="count (cac:TaxRepresentativeParty/cac:PartyIdentification/cbc:ID) &gt; 0">
						<xsl:apply-templates select="cac:TaxRepresentativeParty"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cacTaxRepresentativeParty/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
						<b>Registration name: </b><xsl:value-of select="cac:TaxRepresentativeParty/cac:PartyLegalEntity/cbc:RegistrationName"/>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PartyName/cbc:Name)) = &apos;&apos; )">
						<b>Name: </b><xsl:value-of select="cac:TaxRepresentativeParty/cac:PartyName/cbc:Name"/>
						<br/>
					</xsl:if>
					<xsl:if test="exists(cac:TaxRepresentativeParty/cac:PostalAddress)">
						<br/><b>Address:</b>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:Department"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:BuildingName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:BuildingNumber"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:StreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
						<xsl:text> - </xsl:text><xsl:value-of select="cacTaxRepresentativeParty/cac:PostalAddress/cbc:AdditionalStreetName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:Postbox"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cac:AddressLine/cbc:Line"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:PostalZone"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:CityName"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
						<br/>
						<xsl:for-each select="cac:TaxRepresentativeParty/cac:PostalAddress/cac:Country/cbc:IdentificationCode">
							<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
						</xsl:for-each>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:PostalAddress/cbc:Region"/>
					</xsl:if>
					<br/>
					<xsl:if test="not ( normalize-space(string(cacTaxRepresentativeParty/cbc:EndpointID)) = &apos;&apos; )">
						<br/><b>Endpoint ID: </b><xsl:value-of select="cac:TaxRepresentativeParty/cbc:EndpointID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID)) = &apos;&apos; )">
						<br/><b><xsl:value-of select="cac:TaxRepresentativeParty/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID"/>: </b>
						<xsl:value-of select="cac:TaxRepresentativeParty/cac:PartyTaxScheme/cbc:CompanyID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
						<br/><b><xsl:text>Party Legal Entity :</xsl:text></b><xsl:value-of select="cac:TaxRepresentativeParty/cac:PartyLegalEntity/cbc:CompanyID"/>
					</xsl:if>
					<xsl:text>&#160;</xsl:text>
				</td>
				<td colSpan="2">
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:Name)) = &apos;&apos; )">
						<xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:Name"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:ID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:Name)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:Name"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:ID)) = &apos;&apos; )">
						<br/><xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:ID"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:Telephone)) = &apos;&apos; )">
						<br/><b>Tel: </b>
						<xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:Telephone"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:Telefax)) = &apos;&apos; )">
						<br/><b>Fax: </b>
						<xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:Telefax"/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:TaxRepresentativeParty/cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
						<br/><b>Email: </b>
						<xsl:value-of select="cac:TaxRepresentativeParty/cac:Contact/cbc:ElectronicMail"/>
					</xsl:if>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>			
			</xsl:if>
			<tr class="bg2">
				<td colspan="8"><b><xsl:text>Contractual information</xsl:text></b></td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:text>Framework contract reference: </xsl:text><xsl:value-of select="cac:ContractDocumentReference/cbc:ID"/>
					<br/>
					<xsl:text>Framework contract date: </xsl:text>
					<xsl:call-template name="formatDate">
						<xsl:with-param name="date" select="cac:ContractDocumentReference/cbc:IssueDate"/>
					</xsl:call-template>
					<xsl:if test="not ( normalize-space(string(cac:OrderReference/cbc:ID)) = &apos;&apos; )">
						<br/>
						<xsl:text>Specific Contract/Order reference: </xsl:text>
						<xsl:value-of select="cac:OrderReference/cbc:ID"/>
						<xsl:if test="not ( normalize-space(string(cac:OrderReference/cbc:IssueDate)) = &apos;&apos; )">
							<br/>
							<xsl:text>Specific Contract/Order date: </xsl:text>
							<xsl:call-template name="formatDate">
								<xsl:with-param name="date" select="cac:OrderReference/cbc:IssueDate"/>
							</xsl:call-template>
						</xsl:if>
					</xsl:if>
				</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="(count(cac:BillingReference) != 0)">
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
											<xsl:call-template name="formatDate">
												<xsl:with-param name="date" select="cbc:IssueDate"/>
											</xsl:call-template>
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
											<xsl:call-template name="formatDate">
												<xsl:with-param name="date" select="cbc:IssueDate"/>
											</xsl:call-template>
											<xsl:text>&#160;</xsl:text>
										</td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="(count(cac:AdditionalDocumentReference) != 0 and count(cac:AdditionalDocumentReference/cbc:DocumentType[text()=&apos;Receipt Advice&apos;]) &gt; 0)">
						<table class="noBorder" border="1" cellPadding="0" cellSpacing="0" width="100%">
							<tbody>
								<tr class="bg2">
									<td class="labelBoldSmall" colSpan="3">Receipt references</td>
								</tr>
								<tr class="bg3">
									<td class="labelBoldSmall" width="25%">ID</td>
									<td class="labelBoldSmall" width="40%">Document type</td>
									<td class="labelBoldSmall" width="35%">Issue date</td>
								</tr>
								<xsl:for-each select="cac:AdditionalDocumentReference">
								<xsl:if test="cbc:DocumentType = &apos;Receipt Advice&apos;">
								<tr>
									<td><xsl:value-of select="cbc:ID"/></td>
									<td><xsl:choose>
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
										<xsl:call-template name="formatDate">
											<xsl:with-param name="date" select="cbc:IssueDate"/>
										</xsl:call-template>
										<xsl:text>&#160;</xsl:text>
									</td>
								</tr>
								</xsl:if>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="(count(cac:AdditionalDocumentReference) != 0 and count(cac:AdditionalDocumentReference/cbc:DocumentType[text()!=&apos;Receipt Advice&apos;]) &gt; 0)">
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
								<xsl:if test="cbc:DocumentType != &apos;Receipt Advice&apos;">
								<tr>
									<td><xsl:value-of select="cbc:ID"/></td>
									<td><xsl:choose>
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
										<xsl:call-template name="formatDate">
											<xsl:with-param name="date" select="cbc:IssueDate"/>
										</xsl:call-template>
										<xsl:text>&#160;</xsl:text>
									</td>
								</tr>
								</xsl:if>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
				</td>
			</tr>
			<tr class="bg1">
				<td class="labelBoldMedium labelWhite" colspan="8">Invoice lines</td>
			</tr>
			<tr class="bg2">
				<td class="labelBoldMedium" align="center" colspan="8">Lines description</td>
			</tr>
			<tr>
				<td colspan="8">
					<table class="noBorder" border="1" cellPadding="2" cellSpacing="0" width="100%">
					<tbody>
						<tr class="bg3">
							<td width="4%" align="center"><b><xsl:text>Line ID</xsl:text></b></td>
							<td width="25%"><b><xsl:text>Description</xsl:text></b></td>
							<td width="6%" align="center"><b><xsl:text>Actual delivery date</xsl:text></b></td>
<!--
							<xsl:if test="count(cac:InvoiceLine/cac:Delivery/cbc:LatestDeliveryDate) &gt; 0">
								<td width="6%" align="center"><b><xsl:text>Latest delivery date</xsl:text></b></td>
							</xsl:if>
-->
							<xsl:if test="count(cac:InvoiceLine/cac:OrderLineReference/cac:OrderReference/cbc:ID) &gt; 0 and count(cac:OrderReference/cbc:ID) = 0">
								<td width="10%" align="center"><b><xsl:text>Order</xsl:text></b></td>
							</xsl:if>
							<xsl:if test="count(cac:InvoiceLine/cac:OrderLineReference/cac:OrderReference/cbc:ID) &gt; 0 or count(cac:OrderReference/cbc:ID) &gt; 0">
								<td width="4%" align="center"><b><xsl:text>Order line</xsl:text></b></td>
							</xsl:if>
							<xsl:if test="count(cac:InvoiceLine/cac:OrderLineReference/cac:OrderReference/cbc:IssueDate) &gt; 0 and count(cac:OrderReference/cbc:ID) = 0">
								<td width="6%" align="center"><b><xsl:text>Order date</xsl:text></b></td>
							</xsl:if>
							<xsl:if test="count(cac:AdditionalDocumentReference/cbc:DocumentType[text()=&apos;Receipt Advice&apos;]) &gt; 0">
								<td width="10%" align="center"><b><xsl:text>Receipt reference (line)</xsl:text></b></td>
							</xsl:if>
							<td width="4%" align="center"><b><xsl:text>Quantity</xsl:text></b></td>
							<td width="12%" align="center"><b><xsl:text>Unit price</xsl:text></b></td>
							<td width="12%" align="center"><b><xsl:text>Amount</xsl:text></b></td>
						</tr>
						<xsl:for-each select="cac:InvoiceLine">
						<tr>
							<td align="center">
								<xsl:value-of select="cbc:ID"/>
							</td>
							<td>
								<xsl:value-of select="cac:Item/cbc:Name"/>
								<xsl:if test="not(normalize-space(string(cac:Item/cbc:Description)) = &apos;&apos;)">
									<br/><xsl:call-template name="split">
										<xsl:with-param name="pText" select="cac:Item/cbc:Description"/>
										<xsl:with-param name="pLength" select="35"/>
									</xsl:call-template>
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
								<xsl:for-each select="cbc:Note">
									<xsl:if test="not(normalize-space(string(.)) = &apos;&apos;)">
										<br/>
										<i>Note:</i><xsl:text>&#160;</xsl:text>
										<xsl:value-of select="."/>
									</xsl:if>
								</xsl:for-each>
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
								<xsl:if test="not(normalize-space(cac:Item/cac:SellersItemIdentification/cbc:ID) = &apos;&apos;)">
									<br/><br/>
									<i>Seller:</i><xsl:text>&#160;</xsl:text>
									<xsl:value-of select="cac:Item/cac:SellersItemIdentification/cbc:ID"/>
								</xsl:if>
								<xsl:if test="not(normalize-space(cac:Item/cac:ManufacturersItemIdentification/cbc:ID) = &apos;&apos;)">
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
								<xsl:call-template name="formatDate">
									<xsl:with-param name="date" select="cac:Delivery/cbc:ActualDeliveryDate"/>
								</xsl:call-template>
								<xsl:text>&#160;</xsl:text>
							</td>
<!--
							<xsl:if test="count(../cac:InvoiceLine/cac:Delivery/cbc:LatestDeliveryDate) &gt; 0 ">
							<td align="center">
								<xsl:call-template name="formatDate">
									<xsl:with-param name="date" select="cac:Delivery/cbc:LatestDeliveryDate"/>
								</xsl:call-template>
								<xsl:text>&#160;</xsl:text>
							</td>
							</xsl:if>
-->
							<xsl:if test="count(../cac:InvoiceLine/cac:OrderLineReference/cac:OrderReference/cbc:ID) &gt; 0 and count(../cac:OrderReference/cbc:ID) = 0">
							<td align="center">
								<xsl:value-of select="cac:OrderLineReference/cac:OrderReference/cbc:ID"/>
								<xsl:text>&#160;</xsl:text>
							</td>
							</xsl:if>
							<xsl:if test="count(../cac:InvoiceLine/cac:OrderLineReference/cac:OrderReference/cbc:ID) &gt; 0 or count(../cac:OrderReference/cbc:ID) &gt; 0">
							<td align="center">
								<xsl:value-of select="cac:OrderLineReference/cbc:LineID"/>
								<xsl:text>&#160;</xsl:text>
							</td>
							</xsl:if>
							<xsl:if test="count(../cac:InvoiceLine/cac:OrderLineReference/cac:OrderReference/cbc:IssueDate) &gt; 0 and count(../cac:OrderReference/cbc:ID) = 0">
							<td align="center">
								<xsl:call-template name="formatDate">
									<xsl:with-param name="date" select="cac:OrderLineReference/cac:OrderReference/cbc:IssueDate"/>
								</xsl:call-template>
								<xsl:text>&#160;</xsl:text>
							</td>
							</xsl:if>
							
							<xsl:if test="count(../cac:AdditionalDocumentReference/cbc:DocumentType[text()=&apos;Receipt Advice&apos;]) &gt; 0">
							<td align="center">
								<xsl:for-each select="cac:Item/cac:ItemSpecificationDocumentReference">
									<xsl:if test="cbc:DocumentType = &apos;Receipt Advice&apos;">
										<xsl:value-of select="cbc:ID"/>
									</xsl:if>
								</xsl:for-each>
								<xsl:for-each select="cac:Item/cac:ItemSpecificationDocumentReference">
									<xsl:if test="cbc:DocumentType = &apos;Receipt Advice Line&apos;">
										(<xsl:value-of select="cbc:ID"/>)
									</xsl:if>
								</xsl:for-each>
								<xsl:text>&#160;</xsl:text>
							</td>
							</xsl:if>
							<td align="center">
								<xsl:value-of select="cbc:InvoicedQuantity"/>
								<xsl:text>&#160;</xsl:text>
								<xsl:for-each select="cbc:InvoicedQuantity">
									<xsl:for-each select="@unitCode">
										<span>
											<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</span>
									</xsl:for-each>
								</xsl:for-each>
							</td>
							<td align="right">
								<xsl:for-each select="cac:Price">
									<xsl:for-each select="cbc:PriceAmount">
										<xsl:value-of select="format-number(round(number(.) * 10000) div 10000, '#.##0,00##', 'format1')"/>
										<xsl:text>&#160;</xsl:text>
										<xsl:value-of select="string(@currencyID)"/>
									</xsl:for-each>
								</xsl:for-each>
							</td>
							<td align="right">
								<xsl:for-each select="cbc:LineExtensionAmount">
									<xsl:value-of select="format-number(round(number(.) * 10000) div 10000, '#.##0,00##', 'format1')"/>
									<xsl:text>&#160;</xsl:text>
									<xsl:value-of select="string(@currencyID)"/>
								</xsl:for-each>
							</td>
						</tr>
						</xsl:for-each>
					</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" rowspan="10" vAlign="middle">
					<b><xsl:text>Clauses and / or notes:</xsl:text></b>
					<br/><xsl:value-of select="cbc:Note"/>
				</td>
				<td style="background-color:silver; " colspan="4">
					<b><xsl:text>Invoice totals</xsl:text></b>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Total line amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:text>&#160;</xsl:text>
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:LineExtensionAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Total charge amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:ChargeTotalAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Total allowance amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:AllowanceTotalAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Tax exclusive amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:TaxExclusiveAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Total tax amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:value-of select="format-number( round( sum( cac:TaxTotal/cbc:TaxAmount ) * 100) div 100, '#.##0,00', 'format1')"/>
					<xsl:text>&#160;</xsl:text>
					<xsl:value-of select="((./cac:TaxTotal)[1])/cbc:TaxAmount/@currencyID"/>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Tax inclusive amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:TaxInclusiveAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Payable rounding amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:PayableRoundingAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<xsl:text>Prepaid amount</xsl:text>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:PrepaidAmount">
						<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
			<tr>
				<td align="right" colSpan="2">
					<span class="labelBoldMedium">
						<xsl:text>Total amount due</xsl:text>
					</span>
				</td>
				<td align="right" colSpan="2">
					<xsl:for-each select="cac:LegalMonetaryTotal/cbc:PayableAmount">
						<span class="labelBoldMedium">
							<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
							<xsl:text>&#160;</xsl:text>
							<xsl:value-of select="string(@currencyID)"/>
						</span>
					</xsl:for-each>
				</td>
			</tr>
			<tr style="background-color:#595959;">
				<td class="cellRightTopBorder" colSpan="8">
					<span class="labelBoldMedium labelWhite">
						<xsl:text>Payment information</xsl:text>
					</span>
				</td>
			</tr>
			<xsl:if test="count(cac:PaymentMeans) != 0">
				<tr>
					<td class="cellRightTopBorder" colspan="8">
						<table width="100%" border="1" cellpadding="2" cellspacing="0">
						<tbody>
							<tr style="background-color:#e1e1e1;">
								<td class="cellRightTopBorder"><b><xsl:text>Payment due date</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Means of payment</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Channel of payment</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Account number</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Credit account</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Seller&apos;s bank</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Payment instruction</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Payment note</xsl:text></b></td>
								<td class="cellRightTopBorder"><b><xsl:text>Sort code</xsl:text></b></td>
							</tr>
							<xsl:for-each select="cac:PaymentMeans">
								<tr>
									<td class="cellRightTopBorder">
										<xsl:call-template name="formatDate">
											<xsl:with-param name="date" select="cbc:PaymentDueDate"/>
										</xsl:call-template>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cbc:PaymentMeansCode">
											<span>
												<xsl:value-of select="document(&apos;PaymentMeansCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
											</span>
										</xsl:for-each>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cbc:PaymentChannelCode">
											<xsl:apply-templates/>
										</xsl:for-each>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cac:PayeeFinancialAccount">
											<xsl:for-each select="cbc:ID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cac:CreditAccount">
											<xsl:for-each select="cbc:AccountID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cac:PayeeFinancialAccount">
											<xsl:for-each select="cac:FinancialInstitutionBranch">
												<xsl:for-each select="cac:FinancialInstitution">
													<xsl:for-each select="cbc:ID">
														<xsl:apply-templates/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
										<xsl:for-each select="cac:PayeeFinancialAccount">
											<xsl:for-each select="cac:FinancialInstitutionBranch">
												<xsl:for-each select="cac:FinancialInstitution">
													<xsl:for-each select="cbc:Name">
														<xsl:apply-templates/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cbc:InstructionID">
											<xsl:apply-templates/>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cbc:InstructionNote">
											<xsl:apply-templates/>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
									</td>
									<td class="cellRightTopBorder">
										<xsl:for-each select="cac:PayeeFinancialAccount">
											<xsl:for-each select="cac:FinancialInstitutionBranch">
												<xsl:for-each select="cbc:ID">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<xsl:text>&#160;</xsl:text>
									</td>
								</tr>
							</xsl:for-each>
						</tbody>
					</table>
					</td>
				</tr>
			</xsl:if>
			<tr>
				<td class="cellRightTopBorder" colSpan="8">
					<xsl:text>Accounting cost: </xsl:text>
					<xsl:value-of select="cbc:AccountingCost"/>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<xsl:text>Terms of payment: </xsl:text>
						<xsl:for-each select="cac:PaymentTerms">
							<xsl:value-of select="cbc:Note"/><xsl:text>&#59;</xsl:text>
						</xsl:for-each>
				</td>
				<td colspan="4">
					<xsl:for-each select="cac:PaymentTerms/cbc:Amount">
						<xsl:text>Amount of payment: </xsl:text><xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
						<xsl:text>&#160;</xsl:text>
						<xsl:value-of select="string(@currencyID)"/>
					</xsl:for-each>
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
			<tr>
				<td class="cellRightTopBorder" colSpan="4">
					<xsl:text>Tax exchange rate source currency: </xsl:text>
					<xsl:value-of select="cac:TaxExchangeRate/cbc:SourceCurrencyCode"/>
				</td>
				<td colSpan="4">
					<xsl:text>Tax exchange rate target currency: </xsl:text>
					<xsl:value-of select="cac:TaxExchangeRate/cbc:TargetCurrencyCode"/>
				</td>
			</tr>
			<tr>
				<td class="cellRightTopBorder" colSpan="4">
					<xsl:text>Tax exchange rate date: </xsl:text>
					<xsl:call-template name="formatDate">
						<xsl:with-param name="date" select="cac:TaxExchangeRate/cbc:Date"/>
					</xsl:call-template>
				</td>
				<td colSpan="4">
					<xsl:text>Tax exchange rate: </xsl:text>
					<xsl:value-of select="cac:TaxExchangeRate/cbc:TargetCurrencyBaseRate"/>
				</td>
			</tr>
			<tr style="background-color:#595959;">
				<td class="cellRightTopBorder" colSpan="8">
					<span class="labelBoldMedium labelWhite">
						<xsl:text>Delivery information</xsl:text>
					</span>
				</td>
			</tr>
			<tr>
				<td class="cellRightTopBorder" colSpan="4">
					<xsl:text>Delivery terms: </xsl:text>
					<xsl:value-of select="cac:DeliveryTerms/cbc:ID"/>
				</td>
				<td colSpan="4">
					<xsl:text>Delivery special terms: </xsl:text>
					<xsl:value-of select="cac:DeliveryTerms/cbc:SpecialTerms"/>
				</td>
			</tr>
			<xsl:for-each select="cac:Delivery">
			<tr>
				<td class="cellRightTopBorder" colSpan="4">
					<xsl:text>Delivery location:</xsl:text><br/>
					<xsl:for-each select="cac:DeliveryLocation">
						<xsl:if test="not ( normalize-space(string(cac:Address/cbc:Department)) = &apos;&apos; )">
							<xsl:value-of select="cac:Address/cbc:Department"/>
							<br/>
						</xsl:if>
						<xsl:if test="not ( normalize-space(string(cac:Address/cbc:BuildingName)) = &apos;&apos; )">
							<xsl:value-of select="cac:Address/cbc:BuildingName"/>
						</xsl:if>
						<xsl:if test="not ( normalize-space(string(cac:Address/cbc:BuildingNumber)) = &apos;&apos; )">
							<xsl:text>&#160;</xsl:text>
							<xsl:value-of select="cac:Address/cbc:BuildingNumber"/>
							<br/>
						</xsl:if>
						<xsl:if test="not ( normalize-space(string(cac:Address/cbc:StreetName)) = &apos;&apos; )">
							<xsl:value-of select="cac:Address/cbc:StreetName"/>
						</xsl:if>
						<xsl:if test="not ( normalize-space(string(cac:Address/cbc:AdditionalStreetName)) = &apos;&apos; )">
							<xsl:text> - </xsl:text>
							<xsl:value-of select="cac:Address/cbc:AdditionalStreetName"/>
							<br/>
						</xsl:if>
						<xsl:if test="not ( normalize-space(string(cac:Address/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
							<xsl:value-of select="cac:Address/cac:AddressLine/cbc:Line"/>
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
				</td>
				<td colSpan="4">
					<xsl:text>Actual delivery date: </xsl:text>					
					<xsl:call-template name="formatDate">
						<xsl:with-param name="date" select="cbc:ActualDeliveryDate"/>
					</xsl:call-template>
					<br/>
					<xsl:if test="not ( normalize-space(string(cbc:LatestDeliveryDate)) = &apos;&apos; )">
						<xsl:text>Latest delivery date: </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cbc:LatestDeliveryDate"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:RequestedDeliveryPeriod/cbc:StartDate)) = &apos;&apos; )">
						<xsl:text>Requested delivery period: </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cac:RequestedDeliveryPeriod/cbc:StartDate"/>
						</xsl:call-template>
						<xsl:text> - </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cac:RequestedDeliveryPeriod/cbc:EndDate"/>
						</xsl:call-template>
						<br/>
					</xsl:if>
					<xsl:if test="not ( normalize-space(string(cac:EstimatedDeliveryPeriod/cbc:StartDate)) = &apos;&apos; )">
						<xsl:text>Estimated delivery period: </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cac:EstimatedDeliveryPeriod/cbc:StartDate"/>
						</xsl:call-template>
						<xsl:text> - </xsl:text>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="date" select="cac:EstimatedDeliveryPeriod/cbc:EndDate"/>
						</xsl:call-template>
					</xsl:if>					
				</td>
			</tr>
			</xsl:for-each>
			<tr class="bg1 labelBoldMedium labelWhite">
				<td colSpan="8">Additional document information</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="not ( normalize-space(string(cac:PrepaidPayment)) = &apos;&apos; )">
						<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:solid; border-top-width:none; " border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr class="bg2 labelBoldMedium">
									<td align="center" colSpan="4">Prepaid payments at document level</td>
								</tr>
								<tr class="bg3">
									<td align="center" width="5%"><b><xsl:text>ID</xsl:text></b></td>
									<td width="30%"><b><xsl:text>Received date</xsl:text></b></td>
									<td width="30%"><b><xsl:text>Paid date</xsl:text></b></td>
									<td width="35%"><b><xsl:text>Amount</xsl:text></b></td>
								</tr>
								<xsl:for-each select="cac:PrepaidPayment">
									<tr>
										<td align="center">
											<xsl:for-each select="cbc:ID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</td>
										<td>
											<xsl:call-template name="formatDate">
												<xsl:with-param name="date" select="cbc:ReceivedDate"/>
											</xsl:call-template>
										</td>
										<td>
											<xsl:call-template name="formatDate">
												<xsl:with-param name="date" select="cbc:PaidDate"/>
											</xsl:call-template>
										</td>
										<td align="right">
											<xsl:for-each select="cbc:PaidAmount">
												<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
											</xsl:for-each>
											<xsl:text>&#160;</xsl:text>
											<xsl:for-each select="cbc:PaidAmount">
												<xsl:for-each select="@currencyID">
													<xsl:value-of select="string(.)"/>
												</xsl:for-each>
											</xsl:for-each>
										</td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="count(cac:AllowanceCharge) != 0">
						<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-width:thin;" border="1" cellPadding="0" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td align="center" bgColor="#c0c0c0" colSpan="8">
										<span class="labelBoldMedium">Allowances and Charges at document level</span>
									</td>
								</tr>
								<tr style="background-color:#e1e1e1; ">
									<td width="12%" align="center"><b><xsl:text>Type</xsl:text></b></td>
									<td width="12%" align="center"><b><xsl:text>Sequence</xsl:text></b></td>
									<td width="12%" align="center"><b><xsl:text>Reason code</xsl:text></b></td>
									<td width="14%"><b><xsl:text>Reason additional text</xsl:text></b></td>
									<td width="12%" align="center"><b><xsl:text>Base amount</xsl:text></b></td>
									<td width="12%" align="center"><b><xsl:text>Multiplier factor</xsl:text></b></td>
									<td width="12%" align="center"><b><xsl:text>Amount</xsl:text></b></td>
									<td width="12%" align="center"><b><xsl:text>Tax category</xsl:text><br/><xsl:text>(type / ID / rate)</xsl:text></b></td>
								</tr>
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
											</xsl:for-each><xsl:text>&#160;</xsl:text>
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
											<xsl:text> / </xsl:text>
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
							</tbody>
						</table>
					</xsl:if>
					<br/>
				</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="count(cac:TaxTotal ) &gt; 0">
						<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none; " border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr class="bg2 labelBoldMedium">
									<td align="center" colSpan="6">Tax subtotals at document level</td>
								</tr>
								<tr class="bg3">
									<td width="12%" align="center"><b><xsl:text>Tax type</xsl:text></b></td>
									<td width="14%" align="center"><b><xsl:text>Tax category</xsl:text></b></td>
									<td width="10%" align="center"><b><xsl:text>Tax rate</xsl:text></b></td>
									<td width="32%" align="center"><b><xsl:text>Tax exemption reason</xsl:text></b></td>
									<td width="16%" align="center"><b><xsl:text>Taxable amount</xsl:text></b></td>
									<td width="16%" align="center"><b><xsl:text>Tax amount</xsl:text></b></td>
								</tr>
								<xsl:for-each select="cac:TaxTotal/cac:TaxSubtotal">
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
							</tbody>
						</table>
					</xsl:if>
					<br/>
				</td>
			</tr>
			<tr class="bg1 labelBoldMedium labelWhite">
				<td colSpan="8">Additional line information</td>
			</tr>
			<tr>
				<td colSpan="8">
					<xsl:if test="count(cac:InvoiceLine/cac:AllowanceCharge) != 0">
						<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none; " border="1" cellPadding="0" cellSpacing="0" width="100%">
							<tbody>
								<tr class="bg2 labelBoldMedium">
									<td align="center" colSpan="8">Line Allowances and Charges</td>
								</tr>
								<tr class="bg3">
									<td width="80" align="center"><b><xsl:text>Line ID</xsl:text></b></td>
									<td width="80" align="center"><b><xsl:text>Sequence</xsl:text></b></td>
									<td width="100"><b><xsl:text>Operator</xsl:text></b></td>
									<td width="180"><b><xsl:text>Reason code</xsl:text></b></td>
									<td width="170"><b><xsl:text>Reason additional text</xsl:text></b></td>
									<td width="150" align="center"><b><xsl:text>Base amount</xsl:text></b></td>
									<td width="120" align="center"><b><xsl:text>Multiplier factor</xsl:text></b></td>
									<td width="145" align="center"><b><xsl:text>Amount</xsl:text></b></td>
								</tr>
								<xsl:for-each select="cac:InvoiceLine">
									<xsl:if test="count (cac:AllowanceCharge) != 0">
										<xsl:variable name="rowspan" select="(count(cac:AllowanceCharge)*2)"/>
										<tr>
											<td rowspan="$rowspan" align="center" width="80">
												<xsl:value-of select="cbc:ID"/>
											</td>
											<td colspan="7">
												<xsl:for-each select="cac:AllowanceCharge">
													<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none; " border="1" cellPadding="0" cellSpacing="0" width="100%">
														<tbody>
															<tr>
																<td width="83">
																	<xsl:value-of select="cbc:SequenceNumeric"/>
																	<xsl:text>&#160;</xsl:text>
																</td>
																<td width="101">
																	<xsl:choose>
																		<xsl:when test="cbc:ChargeIndicator = boolean(true)">
																			<xsl:text>allowance</xsl:text>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:text>charge</xsl:text>
																		</xsl:otherwise>
																	</xsl:choose>
																	<xsl:text>&#160;</xsl:text>
																</td>
																<td width="180">
																	<xsl:for-each select="cbc:AllowanceChargeReasonCode">
																		<xsl:value-of select="document(&apos;AllowanceChargeReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																	</xsl:for-each>
																	<xsl:text>&#160;</xsl:text>
																</td>
																<td width="171">
																	<xsl:value-of select="cbc:AllowanceChargeReason"/>
																</td>
																<td align="right" width="150">
																	<xsl:if test="count(cbc:BaseAmount) != 0">
																		<xsl:value-of select="format-number(round(number(cbc:BaseAmount) * 100) div 100, '#.##0,00', 'format1')"/>
																		<xsl:text>&#160;</xsl:text>
																		<xsl:value-of select="string(cbc:BaseAmount/@currencyID)"/>
																	</xsl:if>
																	<xsl:text>&#160;</xsl:text>
																</td>
																<td align="center" width="123">
																	<xsl:value-of select="cbc:MultiplierFactorNumeric"/>
																	<xsl:text>&#160;</xsl:text>
																</td>
																<td align="right" width="145">
																	<xsl:value-of select="format-number(round(number(cbc:Amount) * 100) div 100, '#.##0,00', 'format1')"/>
																	<xsl:text>&#160;</xsl:text>
																	<xsl:value-of select="string(cbc:Amount/@currencyID)"/>
																</td>
															</tr>
															<tr>
																<td colspan="7">
																	<span style="background-color:#e1e1e1; ">
																		<xsl:text>Tax category (type / ID / rate)</xsl:text>
																	</span>
																	<br/>
																	<xsl:for-each select="cac:TaxCategory">
																		<xsl:for-each select="cac:TaxScheme">
																			<xsl:for-each select="cbc:ID">
																				<xsl:apply-templates/>
																			</xsl:for-each>
																		</xsl:for-each>
																		<xsl:text> / </xsl:text>
																		<xsl:for-each select="cbc:ID">
																				<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																		</xsl:for-each>
																		<xsl:text> / </xsl:text>
																		<xsl:if test="not ( normalize-space(string(./cbc:Percent)) = &apos;&apos; )">
																			<xsl:for-each select="cbc:Percent">
																				<xsl:apply-templates/>%
																			</xsl:for-each>
																		</xsl:if>
																		<xsl:if test="not ( normalize-space(string(./cbc:PerUnitAmount)) = &apos;&apos; )">
																			<xsl:text>&#160;</xsl:text>
																			<xsl:for-each select="cbc:PerUnitAmount">
																				<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
																				<xsl:for-each select="@currencyID">
																					<xsl:text>&#160;</xsl:text>
																					<xsl:value-of select="string(.)"/>
																				</xsl:for-each>
																			</xsl:for-each>
																			<xsl:text> per unit</xsl:text>
																		</xsl:if>
																		<br/>
																		<xsl:if test="not ( normalize-space(string(./cbc:TaxExemptionReasonCode)) = &apos;&apos; )">
																			<xsl:for-each select="cbc:TaxExemptionReasonCode">
																				<xsl:value-of select="document(&apos;TaxExemptionReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																			</xsl:for-each>
																			<br/>
																		</xsl:if>
																		<xsl:value-of select="cbc:TaxExemptionReason"/>
																	</xsl:for-each>
																	<br/>
																</td>
															</tr>
														</tbody>
													</table>
												</xsl:for-each>
											</td>
										</tr>
									</xsl:if>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="count(cac:InvoiceLine/cac:TaxTotal ) &gt; 0">
						<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none; " border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td align="center" bgColor="#c0c0c0" colspan="6">
										<span class="labelBoldMedium">Tax subtotals at line level</span>
									</td>
								</tr>
								<tr style="background-color:#e1e1e1; ">
									<td width="7%" align="center"><b><xsl:text>Line ID</xsl:text></b></td>
									<td width="17%" align="center"><b><xsl:text>Tax type</xsl:text></b></td>
									<td width="17%" align="center"><b><xsl:text>Tax category</xsl:text></b></td>
									<td width="17%" align="center"><b><xsl:text>Tax rate</xsl:text></b></td>
									<td width="20%" align="center"><b><xsl:text>Taxable amount</xsl:text></b></td>
									<td width="20%" align="center"><b><xsl:text>Tax amount</xsl:text></b></td>
								</tr>
								<xsl:for-each select="cac:InvoiceLine">
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
											<span>
												<xsl:text>&#160;</xsl:text>
											</span>
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
												<span>
													<xsl:text>%</xsl:text>
												</span>
											</xsl:if>
											<br/>
											<xsl:if test="not ( normalize-space(string(./cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:PerUnitAmount)) = &apos;&apos; )">
												<xsl:for-each select="cac:TaxTotal">
													<xsl:for-each select="cac:TaxSubtotal">
														<xsl:for-each select="cac:TaxCategory">
															<xsl:for-each select="cbc:PerUnitAmount">
																<span>
																	<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00##', 'format1')"/>
																</span>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
												<span>
													<xsl:text>&#160;</xsl:text>
												</span>
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
												<span>
													<xsl:text> per unit</xsl:text>
												</span>
											</xsl:if>
										</td>
										<td align="right">
											<xsl:for-each select="cac:TaxTotal">
												<xsl:for-each select="cac:TaxSubtotal">
													<xsl:for-each select="cbc:TaxableAmount">
														<span>
															<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
														</span>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
											<span>
												<xsl:text>&#160;</xsl:text>
											</span>
											<xsl:for-each select="cac:TaxTotal">
												<xsl:for-each select="cac:TaxSubtotal">
													<xsl:for-each select="cbc:TaxableAmount">
														<xsl:for-each select="@currencyID">
															<span>
																<xsl:value-of select="string(.)"/>
															</span>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</td>
										<td align="right">
											<xsl:for-each select="cac:TaxTotal">
												<xsl:for-each select="cbc:TaxAmount">
													<span>
														<xsl:value-of select="format-number(round(number(.) * 100) div 100, '#.##0,00', 'format1')"/>
													</span>
												</xsl:for-each>
											</xsl:for-each>
											<span>
												<xsl:text>&#160;</xsl:text>
											</span>
											<xsl:for-each select="cac:TaxTotal">
												<xsl:for-each select="cac:TaxSubtotal">
													<xsl:for-each select="cbc:TaxAmount">
														<xsl:for-each select="@currencyID">
															<span>
																<xsl:value-of select="string(.)"/>
															</span>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="count(cac:InvoiceLine/cac:Item/cac:ClassifiedTaxCategory ) &gt; 0">
						<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none;" border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td align="center" bgColor="#c0c0c0" colspan="6">
										<span class="labelBoldMedium">Tax classified category at line level</span>
									</td>
								</tr>
								<tr style="background-color:#e1e1e1; ">
									<td width="12%" align="center"><b><xsl:text>Tax type</xsl:text></b></td>
									<td width="15%" align="center"><b><xsl:text>Tax category</xsl:text></b></td>
									<td width="15%" align="center"><b><xsl:text>Tax rate</xsl:text></b></td>
									<td width="58%"><b><xsl:text>Tax exemption reason</xsl:text></b></td>
								</tr>
								<xsl:for-each-group select="cac:InvoiceLine/cac:Item/cac:ClassifiedTaxCategory" group-by="concat(cbc:ID,cbc:Percent)">
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
											<xsl:if test="not(normalize-space(string(cbc:TaxExemptionReasonCode)) = &apos;&apos;)">
												<xsl:variable name="TaxExemptionReasonCode" select="cbc:TaxExemptionReasonCode"/>
												<xsl:value-of select="document(&apos;TaxExemptionReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=$TaxExemptionReasonCode]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
												<br/>
											</xsl:if>
											<xsl:value-of select="cbc:TaxExemptionReason"/>
											<xsl:text>&#160;</xsl:text>
										</td>
									</tr>
								</xsl:for-each-group>
							</tbody>
						</table>
					</xsl:if>
					<xsl:if test="count(cac:InvoiceLine/cac:Delivery) &gt; 0">
						<table class="noBorder" border="1" cellPadding="2" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<td class="bg2 labelBoldMedium" align="center" colSpan="3">Delivery at line level</td>
								</tr>
								<tr class="bg3 labelBoldSmall">
									<td width="7%" align="center">Line ID</td>
									<td width="63%">Delivery location</td>
									<td width="30%">Delivery dates</td>
								</tr>
								<xsl:for-each select="cac:InvoiceLine/cac:Delivery">
									<tr>
										<td align="center">
											<xsl:value-of select="../cbc:ID"/>
										</td>
										<td>
											<xsl:for-each select="cac:DeliveryLocation">
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
											<xsl:text>&#160;</xsl:text>
										</td>
										<td>
											<xsl:if test="not ( normalize-space(string(cac:RequestedDeliveryPeriod/cbc:StartDate)) = &apos;&apos; )">
												<br/>
												<xsl:text>Requested delivery period: </xsl:text>
												<xsl:call-template name="formatDate">
													<xsl:with-param name="date" select="cac:RequestedDeliveryPeriod/cbc:StartDate"/>
												</xsl:call-template>
												<xsl:text> - </xsl:text>
												<xsl:call-template name="formatDate">
													<xsl:with-param name="date" select="cac:RequestedDeliveryPeriod/cbc:EndDate"/>
												</xsl:call-template>
												<br/>
											</xsl:if>
											<xsl:if test="not ( normalize-space(string(cac:EstimatedDeliveryPeriod/cbc:StartDate)) = &apos;&apos; )">
												<br/>
												<xsl:text>Estimated delivery period: </xsl:text>
												<xsl:call-template name="formatDate">
													<xsl:with-param name="date" select="cac:EstimatedDeliveryPeriod/cbc:StartDate"/>
												</xsl:call-template>
												<xsl:text> - </xsl:text>
												<xsl:call-template name="formatDate">
													<xsl:with-param name="date" select="cac:EstimatedDeliveryPeriod/cbc:EndDate"/>
												</xsl:call-template>
											</xsl:if>
											<xsl:text>&#160;</xsl:text>
										</td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
					</xsl:if>
				</td>
			</tr>
			</xsl:for-each>
			<xsl:variable name="CustomerID" select="normalize-space(eccac:ECDocumentReceivedData/eccbc:CustomerID)"/>
			<xsl:if test="count(document(&apos;NoWarnings.xml&apos;)//SimpleCodeList/Row/Value[.=$CustomerID]) = 0">
				<xsl:if test="count ( eccac:ECDocumentReceivedData/eccac:ProcessingWarning/eccbc:FailedAssert ) &gt; 0">
					<tr>
						<td colspan="8"><br/></td>
					</tr>
					<tr class="bg4">
						<td colSpan="8">
							<span class="labelBoldMedium labelWhite">MESSAGE WARNINGS</span>
						</td>
					</tr>
					<tr>
						<td colspan="8">
						<xsl:for-each select="eccac:ECDocumentReceivedData/eccac:ProcessingWarning/eccbc:FailedAssert">
							<p><xsl:value-of select="document(&apos;ErrorCode.xml&apos;)//SimpleCodeList/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=normalize-space(current())]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/></p>
						</xsl:for-each>
						</td>
					</tr>
				</xsl:if>
			</xsl:if>
			
		</tbody>
	</table>
</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>