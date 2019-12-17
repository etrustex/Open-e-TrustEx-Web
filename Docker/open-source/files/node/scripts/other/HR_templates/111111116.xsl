<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:orc="urn:oasis:names:specification:ubl:schema:xsd:OrderCancellation-2" xmlns:n1="ec:schema:xsd:OrderCancellationReceived-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output version="4.01" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="/">
		<html>
			<head>
				<meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
				<title>Order Cancellation Human Readable Format</title>
				<style type="text/css">
	body { 	
		font-family: Arial, "Arial Unicode MS";
		font-size: medium;
		size: landscape;
      vertical-align: top;
	}

    td{
      padding: 10px;
      text-align: left;
      margin: 0px;
      vertical-align: middle;
      border-style:solid; 
      border-width:1px; 
      border-color:#cccccc;   
      width: 365px;    
    }
    
        th{
        font-weight: bold;
      padding: 10px;
      text-align: center;
      margin: 0px;
      vertical-align: middle;
      border-style:solid; 
      border-width:1px; 
      border-color:#cccccc;   
      width: 100%;    
    }
       
     .contentCell{ 
        vertical-align: top;
    }        

     .highlightCell{ 
        font-weight: bold;
        background-color: #cccccc;
        padding-top: 0px;
        padding-bottom: 0px;
        border-top-color:#cccccc;
        border-bottom-color:#cccccc;
    }        
    
     .highlight{ 
        font-weight: bold;
    }        
    
     .lightCell{ 
        font-weight: bold;
        background-color: #e8e8e8;
        padding-top: 0px;
        padding-bottom: 0px;
    }        

    .cellBorder{ 
        border-style:solid; 
        border-width:4px; 
        border-top-color:#cccccc;
        border-bottom-color:#555555;
        border-right-color:#555555;
        border-left-color:#cccccc; 
    }
    
    @page {
	size: A4 landscape;
	margin-top: 5%;
	margin-left: 6%;
	margin-right: 5%;
	margin-bottom: 5%;
    vertical-align: top;
    text-align: center;
    align: center;
    }

    </style>
			</head>
			<body>
				<xsl:for-each select="$XML">
					<table style="width: 1460px;" class="cellBorder" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<th colspan="4">Cancellation Form</th>
							</tr>
							<tr>
								<td class="highlightCell" colspan="2">Response ID:</td>
								<td class="highlightCell" colspan="2">Response issue date:</td>
							</tr>
							<tr class="highlight">
								<td colspan="2">
									<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cbc:ID"/>
								</td>
								<td  colspan="2">
									<xsl:value-of select="format-number(number(substring(string(n1:OrderCancellationReceived/orc:OrderCancellation/cbc:IssueDate), 9, 2)), '00', 'format1')"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="format-number(number(substring(string(n1:OrderCancellationReceived/orc:OrderCancellation/cbc:IssueDate), 6, 2)), '00', 'format1')"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="format-number(number(substring(string(string(n1:OrderCancellationReceived/orc:OrderCancellation/cbc:IssueDate)), 1, 4)), '0000', 'format1')"/>
								</td>
							</tr>
							<tr>
								<td class="highlightCell">Customer</td>
								<td class="highlightCell">Customer contact</td>
								<td class="highlightCell">Supplier</td>
								<td class="highlightCell">Supplier contact</td>
							</tr>
							<tr>
								<td class="contentCell">
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
										<span class="highlight">Party ID:
            </span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyIdentification/cbc:ID"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyName/cbc:Name)) = &apos;&apos; )">
										<span class="highlight">Name:
            </span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyName/cbc:Name"/>
										<br/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
										<span class="highlight">Registration Name:
            </span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName"/><br/>
									</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<span class="highlight">Country of Origin:</span>
											<xsl:for-each select="n1:OOrderCancellationReceived">
												<xsl:for-each select="orc:OrderCancellation">
													<xsl:for-each select="cac:BuyerCustomerParty">
														<xsl:for-each select="cac:Party">
															<xsl:for-each select="cac:PostalAddress">
																<xsl:for-each select="cac:Country">
																	<xsl:for-each select="cbc:IdentificationCode">
																			<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/><br/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
										<span class="highlight">Party Legal Entity:
            </span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID"/>
										<br/>
									</xsl:if>
									<br/>
									<xsl:if test="not ( normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos;)">
										<span class="highlight">Address:</span>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
											<br/>
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:StreetName"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
											<br/>
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
											<br/>
											Postbox: <xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Postbox"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
											<br/>
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:PostalZone"/>
											<xsl:text>&#160;</xsl:text>
										</xsl:if>
										<xsl:if test="not ( normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
											<br/>
											<xsl:for-each select="n1:OrderCancellationReceived">
												<xsl:for-each select="orc:OrderCancellation">
													<xsl:for-each select="cac:BuyerCustomerParty">
														<xsl:for-each select="cac:Party">
															<xsl:for-each select="cac:PostalAddress">
																<xsl:for-each select="cac:Country">
																	<xsl:for-each select="cbc:IdentificationCode">
																			<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/><br/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:if>
									<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
										<span class="highlight">VAT:
            </span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID"/>
										<br/>
									</xsl:if>&#160;
								</td>
								<td class="contentCell">
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:Name)) = &apos;&apos; )">
										<span class="highlight">Name:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:Name"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
										<span class="highlight">Department:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Department"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:Telephone)) = &apos;&apos; )">
										<span class="highlight">Tel.:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:Telephone"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
										<span class="highlight">E-mail:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:ElectronicMail"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:Telefax)) = &apos;&apos; )">
										<span class="highlight">Fax:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:BuyerCustomerParty/cac:Party/cac:Contact/cbc:Telefax"/>
										<br/>
									</xsl:if>
								</td>
								<td class="contentCell">
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
										<span class="highlight">Party ID:
            </span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyIdentification/cbc:ID"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyName/cbc:Name)) = &apos;&apos; )">
										<span class="highlight">Name:
            </span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyName/cbc:Name"/>
										<br/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
										<span class="highlight">Registration Name:</span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName"/>
										<br/>
									</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<span class="highlight">Country of Origin:</span>
											<xsl:for-each select="n1:OrderCancellationReceived">
												<xsl:for-each select="orc:OrderCancellation">
													<xsl:for-each select="cac:SellerSupplierParty">
														<xsl:for-each select="cac:Party">
															<xsl:for-each select="cac:PostalAddress">
																<xsl:for-each select="cac:Country">
																	<xsl:for-each select="cbc:IdentificationCode">
																			<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/><br/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID)) = &apos;&apos; )">
										<span class="highlight">Party Legal Entity:</span>
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID"/>
										<br/>
									</xsl:if>
										<br/>
									<xsl:if test="not ( normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos;)">
										<span class="highlight">Address:</span>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
											<br/>
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:StreetName"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
											<br/>
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
											<br/>
											Postbox: <xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Postbox"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
											<br/>
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:PostalZone"/>
											<xsl:text>&#160;</xsl:text>
										</xsl:if>
										<xsl:if test="not ( normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
											<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName"/>
										</xsl:if>
										<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
											<br/>
											<xsl:for-each select="n1:OrderCancellationReceived">
												<xsl:for-each select="orc:OrderCancellation">
													<xsl:for-each select="cac:SellerSupplierParty">
														<xsl:for-each select="cac:Party">
															<xsl:for-each select="cac:PostalAddress">
																<xsl:for-each select="cac:Country">
																	<xsl:for-each select="cbc:IdentificationCode">
																			<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/><br/>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:if>
									<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
										<span class="highlight">VAT:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID"/>
										<br/>
									</xsl:if>&#160;
								</td>
								<td class="contentCell">
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Name)) = &apos;&apos; )">
										<span class="highlight">Name:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Name"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
										<span class="highlight">Department:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Department"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Telephone)) = &apos;&apos; )">
										<span class="highlight">Tel.:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Telephone"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:ElectronicMail)) = &apos;&apos; )">
										<span class="highlight">E-mail:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:ElectronicMail"/>
										<br/>
									</xsl:if>
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Telefax)) = &apos;&apos; )">
										<span class="highlight">Fax:</span>&#160;<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Telefax"/>
										<br/>
									</xsl:if>&#160;
								</td>
							</tr>
							<tr>
								<td class="highlightCell" colspan="4">Order Reference</td>
							</tr>
							<tr>
								<td class="lightCell" colspan="2">ID</td>
								<td class="lightCell" colspan="2">Issue Date</td>
							</tr>
							<tr>
								<td colspan="2">
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:OrderReference/cbc:ID)) = &apos;&apos; )">
										<xsl:value-of select="n1:OrderCancellationReceived/orc:OrderCancellation/cac:OrderReference/cbc:ID"/>
									</xsl:if>
								</td>
								<td colspan="2">
									<xsl:if test="not ( 	normalize-space(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:OrderReference/cbc:IssueDate)) = &apos;&apos; )">
										<xsl:value-of select="format-number(number(substring(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:OrderReference/cbc:IssueDate), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:OrderReference/cbc:IssueDate), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(string(n1:OrderCancellationReceived/orc:OrderCancellation/cac:OrderReference/cbc:IssueDate)), 1, 4)), '0000', 'format1')"/>
									</xsl:if>&#160;
								</td>
							</tr>
								<tr>
									<td colspan="4">
										<span class="highlight">
											<xsl:text>Cancellation Note:&#160;</xsl:text>
										</span>
										<xsl:if test="count(n1:OrderCancellationReceived/orc:OrderCancellation/cbc:CancellationNote)&gt;0">
											<xsl:for-each select="n1:OrderCancellationReceived">
												<xsl:for-each select="orc:OrderCancellation">
													<xsl:for-each select="cbc:CancellationNote">
														<xsl:apply-templates/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:if>
									</td>
								</tr>
							<tr>
								<td colspan="4">
									<span class="highlight">
										<xsl:text>Note:&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderCancellationReceived">
										<xsl:for-each select="orc:OrderCancellation">
											<xsl:for-each select="cbc:Note">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
							</tr>
						</tbody>
					</table>
					<br/>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
