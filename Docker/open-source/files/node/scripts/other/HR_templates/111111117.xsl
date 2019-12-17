<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="ec:schema:xsd:OrderChangeReceived-0.1" xmlns:n2="ec:schema:xsd:OrderReceived-0.1" xmlns:n3="urn:oasis:names:specification:ubl:schema:xsd:Order-2" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:orc="urn:oasis:names:specification:ubl:schema:xsd:OrderChange-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="/">
		<html>
			<head>
				<title>Order Change Human Readable Format</title>
				<style type="text/css">
					<xsl:comment>@import  url("ORChF_v01_27042010.css");</xsl:comment>
				</style>
			</head>
			<body>
				<xsl:for-each select="$XML">
					<br/>
					<br/>
					<br/>
					<br/>
					<table style="border-bottom-color:black; border-left-color:black; border-right-color:black; border-top-color:black; " border="4" cellPadding="0" cellSpacing="0" width="1035">
						<tbody>
							<tr>
								<td style="border-bottom:none; border-left:none; border-right-color:black; border-right-style:solid; border-top:none; " align="center" class="cellRightBorder" colspan="2">
									<span style="font-family:Arial; ">
										<xsl:text>Buyer</xsl:text>
									</span>
								</td>
								<td align="center" class="cellRightBorder">
									<span style="font-family:Arial; font-size:larger; font-weight:bold; ">
										<xsl:text>ORDER CHANGE FORM</xsl:text>
									</span>
								</td>
								<td align="center" class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Supplier</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td align="center" class="cellRightTopBorder" colSpan="2" valign="top">
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyIdentification">
															<xsl:for-each select="cbc:ID">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyName">
															<xsl:for-each select="cbc:Name">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<span>
											<xsl:text> - </xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
										<br/>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PostalAddress">
															<xsl:for-each select="cbc:Postbox">
																<span>
																	<xsl:text>P/O </xsl:text>
																</span>
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
										<span>
											<xsl:text> - </xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cbc:EndpointID)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cbc:EndpointID">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
								</td>
								<td align="center" class="cellRightTopBorder" rowspan="3">
									<span style="font-family:Arial; ">
										<xsl:text>(to be mentioned in all correspondence)</xsl:text>
									</span>
									<br/>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>No:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cbc:ID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<span style="font-family:Arial; ">
										<xsl:text>&#160;</xsl:text>
									</span>
									<br/>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Sales OrderID:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cbc:SalesOrderID">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<br/>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Supplier code:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:SellerSupplierParty">
												<xsl:for-each select="cac:Party">
													<xsl:for-each select="cac:PartyLegalEntity">
														<xsl:for-each select="cbc:CompanyID">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<br/>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Country of origin:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:SellerSupplierParty">
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
									<br/>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Currency of payment:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cbc:DocumentCurrencyCode">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<br/>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Code country of origin / Currency</xsl:text>
									</span>
									<br/>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:SellerSupplierParty">
												<xsl:for-each select="cac:Party">
													<xsl:for-each select="cac:PostalAddress">
														<xsl:for-each select="cac:Country">
															<xsl:for-each select="cbc:IdentificationCode">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<span>
										<xsl:text> / </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cbc:DocumentCurrencyCode">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td align="center" class="cellTopBorder" colSpan="4">
									<span>
										<xsl:text>??</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyIdentification">
															<xsl:for-each select="cbc:ID">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PartyName/cbc:Name)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyName">
															<xsl:for-each select="cbc:Name">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:Contact/cbc:Name)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:Contact">
															<xsl:for-each select="cbc:Name">
																<span style="font-family:Arial; ">
																	<xsl:text>For the attention of:</xsl:text>
																</span>
																<span>
																	<xsl:text>&#160;</xsl:text>
																</span>
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<span>
											<xsl:text> - </xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
										<br/>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PostalAddress">
															<xsl:for-each select="cbc:Postbox">
																<span>
																	<xsl:text>P/O </xsl:text>
																</span>
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
										<span>
											<xsl:text> - </xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cbc:EndpointID)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cbc:EndpointID">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<br/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID)) = &apos;&apos; )">
										<br/>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyTaxScheme">
															<xsl:for-each select="cac:TaxScheme">
																<xsl:for-each select="cbc:ID">
																	<xsl:apply-templates/>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>: </xsl:text>
										</span>
									</xsl:if>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:SellerSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:SellerSupplierParty">
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
										<br/>
									</xsl:if>
								</td>
							</tr>
							<tr>
								<td align="left" class="cellRightTopBorder" colspan="2" vAlign="top">
									<span style="font-family:Arial; ">
										<xsl:text>DG / administrative unit:</xsl:text>
									</span>
									<br/>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:BuyerCustomerParty">
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
									<br/>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:BuyerCustomerParty">
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
									<br/>
								</td>
								<td align="center" class="cellNoBorder" colSpan="4" vAlign="middle"/>
							</tr>
							<tr>
								<td align="left" class="cellRightBorder" colSpan="2">
									<span style="font-family:Arial; ">
										<xsl:text>Tel.: </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:BuyerCustomerParty">
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
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Fax.: </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:BuyerCustomerParty">
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
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>E-mail: </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:BuyerCustomerParty">
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
								</td>
								<td align="center" class="cellNoBorder" colSpan="4" vAlign="middle">
									<span>
										<xsl:text>??</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellTopBorder" colspan="7">
									<span style="font-family:Arial; ">
										<xsl:text>Quotation date and reference:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:QuotationDocumentReference">
												<xsl:for-each select="cbc:IssueDate">
													<span>
														<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
														<xsl:text>/</xsl:text>
														<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
													</span>
													<span>
														<xsl:text> - </xsl:text>
													</span>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:QuotationDocumentReference">
												<xsl:for-each select="cbc:ID">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Additional date and reference of your offer:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:AdditionalDocumentReference">
												<xsl:for-each select="cbc:ID">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:AdditionalDocumentReference">
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
								<td class="cellTopBorder" colspan="7">
									<span style="font-family:Arial; ">
										<xsl:text>This order is governed by the provisions of Framework Contract No</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:Contract">
												<xsl:for-each select="cbc:ID">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<span style="font-family:Arial; ">
										<xsl:text>in force from</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:Contract">
												<xsl:for-each select="cac:ValidityPeriod">
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
									</xsl:for-each>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<span style="font-family:Arial; ">
										<xsl:text>to </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:Contract">
												<xsl:for-each select="cac:ValidityPeriod">
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
									</xsl:for-each>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>Acceptance of this order implies that the contractor waives its own terms of business or of execution of the works.</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellTopBorder" colSpan="7">
									<xsl:if test="not ( normalize-space(string(n2:OrderReceived/n3:Order/cac:AllowanceCharge[1])) = &apos;&apos; )">
										<br/>
										<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-color:black; border-top-style:solid; " border="2" cellPadding="0" cellSpacing="0">
											<tbody>
												<tr>
													<td style="border-bottom-color:black; border-bottom-style:solid; border-bottom-width:thin; border-right:none; " align="center" class="cellRightBorder" colSpan="7" width="1050">
														<span style="font-family:Arial; ">
															<xsl:text>CHARGES AT DOCUMENT LEVEL </xsl:text>
														</span>
													</td>
												</tr>
												<tr style="background-color:silver; font-family:Arial; ">
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Sequence</xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Reason code </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Reason additional text </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Base amount </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Multiplier factor </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Amount</xsl:text>
														</span>
													</td>
													<td class="cellNoBorder" width="150">
														<span>
															<xsl:text>Tax category (type / ID / rate) </xsl:text>
														</span>
													</td>
												</tr>
											</tbody>
										</table>
										<p style="margin-top:-57px; ">
											<xsl:for-each select="n1:OrderChangeReceived">
												<xsl:for-each select="orc:OrderChange">
													<xsl:if test="count(./cac:AllowanceCharge[string(./cbc:ChargeIndicator)=&apos;true&apos; ])  &gt;  0">
														<xsl:for-each select="cac:AllowanceCharge">
															<xsl:if test="string(./cbc:ChargeIndicator)=&apos;true&apos;">
																<br/>
																<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-style:none; " border="2" cellPadding="0" cellSpacing="0">
																	<tbody>
																		<tr>
																			<td class="cellRightBorder" valign="middle" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:SequenceNumeric">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" valign="middle" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:AllowanceChargeReasonCode">
																					<span>
																						<xsl:value-of select="document(&apos;AllowanceChargeReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																					</span>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" valign="middle" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:AllowanceChargeReason">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" valign="middle" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:BaseAmount">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:BaseAmount">
																					<xsl:for-each select="@currencyID">
																						<span>
																							<xsl:value-of select="string(.)"/>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" valign="middle" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:MultiplierFactorNumeric">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</td>
																			<td align="right" class="cellRightBorder" valign="middle" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:Amount">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:Amount">
																					<xsl:for-each select="@currencyID">
																						<span>
																							<xsl:value-of select="string(.)"/>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</td>
																			<td align="right" class="cellNoBorder" valign="middle" width="150">
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cac:TaxScheme">
																						<xsl:for-each select="cbc:ID">
																							<xsl:apply-templates/>
																							<span>
																								<xsl:text> / </xsl:text>
																							</span>
																						</xsl:for-each>
																					</xsl:for-each>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:ID">
																						<span>
																							<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																						</span>
																						<span>
																							<xsl:text> / </xsl:text>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																				<xsl:if test="not ( normalize-space(string(./cac:TaxCategory/cbc:Percent)) = &apos;&apos; )">
																					<xsl:for-each select="cac:TaxCategory">
																						<xsl:for-each select="cbc:Percent">
																							<xsl:apply-templates/>
																						</xsl:for-each>
																					</xsl:for-each>
																					<span>
																						<xsl:text>%</xsl:text>
																					</span>
																				</xsl:if>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:PerUnitAmount">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:if test="not ( normalize-space(string(./cac:TaxCategory/cbc:PerUnitAmount)) = &apos;&apos; )">
																					<xsl:for-each select="cac:TaxCategory">
																						<xsl:for-each select="cbc:PerUnitAmount">
																							<xsl:for-each select="@currencyID">
																								<span>
																									<xsl:value-of select="string(.)"/>
																								</span>
																							</xsl:for-each>
																						</xsl:for-each>
																					</xsl:for-each>
																					<span>
																						<xsl:text> per unit</xsl:text>
																					</span>
																				</xsl:if>
																				<br/>
																				<br/>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:TaxExemptionReasonCode">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																				<br/>
																				<br/>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:TaxExemptionReason">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</xsl:if>
														</xsl:for-each>
													</xsl:if>
												</xsl:for-each>
											</xsl:for-each>
										</p>
										<br/>
										<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-color:black; border-top-style:solid; " border="2" cellPadding="0" cellSpacing="0">
											<tbody>
												<tr>
													<td style="border-bottom-color:black; border-bottom-style:solid; border-bottom-width:thin; border-right:none; " align="center" class="cellRightBorder" colSpan="7" width="1050">
														<span style="font-family:Arial; ">
															<xsl:text>ALLOWANCES AT DOCUMENT LEVEL </xsl:text>
														</span>
													</td>
												</tr>
												<tr style="background-color:silver; font-family:Arial; ">
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Sequence</xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Reason code </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Reason additional text </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Base amount </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Multiplier factor </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Amount</xsl:text>
														</span>
													</td>
													<td class="cellNoBorder" width="150">
														<span>
															<xsl:text>Tax category (type / ID / rate) </xsl:text>
														</span>
													</td>
												</tr>
											</tbody>
										</table>
										<p style="margin-top:-18px; ">
											<xsl:for-each select="n1:OrderChangeReceived">
												<xsl:for-each select="orc:OrderChange">
													<xsl:if test="count(./cac:AllowanceCharge[string(./cbc:ChargeIndicator)=&apos;false&apos;])  &gt;  0">
														<xsl:for-each select="cac:AllowanceCharge">
															<xsl:if test="string(./cbc:ChargeIndicator)=&apos;false&apos;">
																<br/>
																<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-style:none; " border="2" cellPadding="0" cellSpacing="0">
																	<tbody>
																		<tr>
																			<td class="cellRightBorder" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:SequenceNumeric">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:AllowanceChargeReasonCode">
																					<span>
																						<xsl:value-of select="document(&apos;AllowanceChargeReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																					</span>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:AllowanceChargeReason">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:BaseAmount">
																					<span>
																						<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																					</span>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:BaseAmount">
																					<xsl:for-each select="@currencyID">
																						<span>
																							<xsl:value-of select="string(.)"/>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</td>
																			<td class="cellRightBorder" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:MultiplierFactorNumeric">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</td>
																			<td align="right" class="cellRightBorder" width="150">
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:Amount">
																					<span>
																						<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																					</span>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cbc:Amount">
																					<xsl:for-each select="@currencyID">
																						<span>
																							<xsl:value-of select="string(.)"/>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</td>
																			<td class="cellNoBorder" width="150">
																				<span>
																					<xsl:text>&#160; </xsl:text>
																				</span>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cac:TaxScheme">
																						<xsl:for-each select="cbc:ID">
																							<xsl:apply-templates/>
																							<span>
																								<xsl:text> / </xsl:text>
																							</span>
																						</xsl:for-each>
																					</xsl:for-each>
																				</xsl:for-each>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:ID">
																						<span>
																							<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																						</span>
																						<span>
																							<xsl:text> / </xsl:text>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																				<xsl:if test="not ( normalize-space(string(./cac:TaxCategory/cbc:Percent)) = &apos;&apos; )">
																					<xsl:for-each select="cac:TaxCategory">
																						<xsl:for-each select="cbc:Percent">
																							<xsl:apply-templates/>
																						</xsl:for-each>
																					</xsl:for-each>
																					<span>
																						<xsl:text>%</xsl:text>
																					</span>
																				</xsl:if>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:PerUnitAmount">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
																				<xsl:if test="not ( normalize-space(string(./cac:TaxCategory/cbc:PerUnitAmount)) = &apos;&apos; )">
																					<xsl:for-each select="cac:TaxCategory">
																						<xsl:for-each select="cbc:PerUnitAmount">
																							<xsl:for-each select="@currencyID">
																								<span>
																									<xsl:value-of select="string(.)"/>
																								</span>
																							</xsl:for-each>
																						</xsl:for-each>
																					</xsl:for-each>
																					<span>
																						<xsl:text> per unit</xsl:text>
																					</span>
																				</xsl:if>
																				<br/>
																				<br/>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:TaxExemptionReasonCode">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																				<br/>
																				<br/>
																				<xsl:for-each select="cac:TaxCategory">
																					<xsl:for-each select="cbc:TaxExemptionReason">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</xsl:if>
														</xsl:for-each>
													</xsl:if>
												</xsl:for-each>
											</xsl:for-each>
										</p>
									</xsl:if>
									<br/>
									<xsl:if test="count (n1:OrderChangeReceived/orc:OrderChange/cac:TaxTotal ) &gt; 0">
										<br/>
										<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-color:black; border-top-style:solid; " border="2" cellPadding="0" cellSpacing="0">
											<tbody>
												<tr>
													<td style="border-bottom-color:black; border-bottom-style:solid; border-bottom-width:thin; border-right:none; " align="center" class="cellRightBorder" colSpan="7" width="1050">
														<span style="font-family:Arial; ">
															<xsl:text>TAX SUBTOTALS AT DOCUMENT LEVEL </xsl:text>
														</span>
													</td>
												</tr>
												<tr style="font-family:Arial; " bgcolor="silver">
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Tax type </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Tax category </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Tax rate</xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Tax exemption reason code</xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Tax exemption reason </xsl:text>
														</span>
													</td>
													<td class="cellRightBorder" width="150">
														<span>
															<xsl:text>Taxable amount</xsl:text>
														</span>
													</td>
													<td class="cellNoBorder" width="150">
														<span>
															<xsl:text>Tax amount </xsl:text>
														</span>
													</td>
												</tr>
											</tbody>
										</table>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:TaxTotal">
													<xsl:for-each select="cac:TaxSubtotal">
														<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-style:none; margin-bottom:-16px; " border="2" cellPadding="0" cellSpacing="0">
															<tbody>
																<tr>
																	<td class="cellRightBorder" width="150">
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
																					<span>
																						<xsl:text>(</xsl:text>
																					</span>
																					<xsl:apply-templates/>
																					<span>
																						<xsl:text>)</xsl:text>
																					</span>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td class="cellRightBorder" width="150">
																		<xsl:for-each select="cac:TaxCategory">
																			<xsl:for-each select="cbc:ID">
																				<span>
																					<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																				</span>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td align="left" class="cellRightBorder" width="150">
																		<xsl:if test="count(./cac:TaxCategory/cbc:Percent)&gt;0">
																			<xsl:for-each select="cac:TaxCategory">
																				<xsl:for-each select="cbc:Percent">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</xsl:for-each>
																			<span>
																				<xsl:text>% </xsl:text>
																			</span>
																		</xsl:if>
																		<span>
																			<xsl:text>?? </xsl:text>
																		</span>
																		<xsl:if test="count(./cac:TaxCategory/cbc:PerUnitAmount)&gt;0">
																			<xsl:for-each select="cac:TaxCategory">
																				<xsl:for-each select="cbc:PerUnitAmount">
																					<span>
																						<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																					</span>
																				</xsl:for-each>
																			</xsl:for-each>
																			<xsl:for-each select="cac:TaxCategory">
																				<xsl:for-each select="cbc:PerUnitAmount">
																					<xsl:for-each select="@currencyID">
																						<span>
																							<xsl:value-of select="string(.)"/>
																						</span>
																						<span>
																							<xsl:text> per unit</xsl:text>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:if>
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																	</td>
																	<td class="cellRightBorder" width="150">
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																		<xsl:for-each select="cac:TaxCategory">
																			<xsl:for-each select="cbc:TaxExemptionReasonCode">
																				<span>
																					<xsl:value-of select="document(&apos;TaxExemptionReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																				</span>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td class="cellRightBorder" width="150">
																		<xsl:for-each select="cac:TaxCategory">
																			<xsl:for-each select="cbc:TaxExemptionReason">
																				<xsl:apply-templates/>
																			</xsl:for-each>
																		</xsl:for-each>
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																	</td>
																	<td align="right" class="cellRightBorder" width="150">
																		<xsl:for-each select="cbc:TaxableAmount">
																			<span>
																				<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																			</span>
																		</xsl:for-each>
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																		<xsl:for-each select="cbc:TaxableAmount">
																			<xsl:for-each select="@currencyID">
																				<span>
																					<xsl:value-of select="string(.)"/>
																				</span>
																			</xsl:for-each>
																		</xsl:for-each>
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																	</td>
																	<td align="right" class="cellNoBorder" width="150">
																		<xsl:for-each select="cbc:TaxAmount">
																			<span>
																				<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																			</span>
																		</xsl:for-each>
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																		<xsl:for-each select="cbc:TaxAmount">
																			<xsl:for-each select="@currencyID">
																				<span>
																					<xsl:value-of select="string(.)"/>
																				</span>
																			</xsl:for-each>
																		</xsl:for-each>
																		<span>
																			<xsl:text>&#160;</xsl:text>
																		</span>
																	</td>
																</tr>
															</tbody>
														</table>
														<br/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:TaxTotal"/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:if>
									<br/>
								</td>
							</tr>
							<tr>
								<td align="center" class="cellRightTopBorder" colSpan="3" rowSpan="2">
									<span style="font-family:Arial; ">
										<xsl:text>DESCRIPTION OF THE GOODS</xsl:text>
									</span>
									<br/>
									<span style="font-family:Arial; ">
										<xsl:text>and code</xsl:text>
									</span>
								</td>
								<td align="center" class="cellRightTopBorder" rowSpan="2">
									<span style="font-family:Arial; ">
										<xsl:text>UNIT</xsl:text>
									</span>
								</td>
								<td align="center" class="cellRightTopBorder" rowSpan="2">
									<span style="font-family:Arial; ">
										<xsl:text>QUANTITY</xsl:text>
									</span>
								</td>
								<td align="center" class="cellTopBorder" colSpan="2">
									<span style="font-family:Arial; ">
										<xsl:text>PRICE in </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cbc:PricingCurrencyCode">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
							</tr>
							<tr>
								<td align="center" class="cellRightTopBorder">
									<span style="font-family:Arial; ">
										<xsl:text>UNIT PRICE</xsl:text>
									</span>
								</td>
								<td align="center" class="cellTopBorder">
									<span style="font-family:Arial; ">
										<xsl:text>TOTAL</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellTopBorder" colSpan="7">
									<p style="margin-top:-18px; ">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:OrderLine">
													<span>
														<xsl:text>?? ??</xsl:text>
													</span>
													<table style="border-bottom-style:none; border-left-style:none; border-right-style:none; border-top-style:none; " border="2" cellPadding="0" cellSpacing="0">
														<tbody>
															<tr bgColor="silver">
																<td class="cellRightBorder" width="588">
																	<span>
																		<xsl:text>??</xsl:text>
																	</span>
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cbc:ID">
																			<span style="font-weight:bold; ">
																				<xsl:apply-templates/>
																			</span>
																		</xsl:for-each>
																	</xsl:for-each>
																	<span style="font-weight:bold; ">
																		<xsl:text>.</xsl:text>
																	</span>
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cbc:Name">
																				<xsl:apply-templates/>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td align="right" class="cellRightBorder" width="148">
																	<xsl:choose>
																		<xsl:when test="not (normalize-space(string(./cac:LineItem/cbc:Quantity/@unitCode)) = &apos;&apos; )">
																			<xsl:for-each select="cac:LineItem">
																				<xsl:for-each select="cbc:Quantity">
																					<xsl:for-each select="@unitCode">
																						<span>
																							<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																			<span>
																				<xsl:text>&#160;</xsl:text>
																			</span>
																		</xsl:when>
																		<xsl:otherwise>
																			<span>
																				<xsl:text>PIECE</xsl:text>
																			</span>
																		</xsl:otherwise>
																	</xsl:choose>
																</td>
																<td align="right" class="cellRightBorder" width="148">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cbc:Quantity">
																			<xsl:apply-templates/>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td align="right" class="cellRightBorder" width="100">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Price">
																			<xsl:for-each select="cbc:PriceAmount">
																				<span>
																					<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																				</span>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td align="right" class="cellNoBorder" width="48">
																	<xsl:for-each select="cac:LineItem">
																		<span>
																			<xsl:value-of select="format-number(round(cbc:LineExtensionAmount - ( sum( cac:AllowanceCharge[child::cbc:ChargeIndicator=true()]/cbc:Amount) )  + ( sum( cac:AllowanceCharge[child::cbc:ChargeIndicator=false()]/cbc:Amount ) ) * 100 ) div 100, '##0,00', 'format1')"/>
																		</span>
																	</xsl:for-each>
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
															</tr>
															<tr>
																<td class="cellRightBorder" width="588">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:BuyersItemIdentification">
																				<xsl:for-each select="cbc:ID">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																	<br/>
																	<br/>
																	<br/>
																</td>
																<td class="cellRightBorder" width="100">
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																</td>
																<td class="cellNoBorder" width="48">
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																</td>
															</tr>
															<tr>
																<td class="cellRightBorder" width="588">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=true()])  &gt;  0">
																			<span style="font-weight:bold; ">
																				<xsl:text>Charges at line level:</xsl:text>
																			</span>
																			<span>
																				<xsl:text>&#160;</xsl:text>
																			</span>
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator !=  boolean( false )">
																					<xsl:for-each select="cbc:AllowanceChargeReason">
																						<p>
																							<span style="font-family:Times New Roman; ">
																								<xsl:apply-templates/>
																							</span>
																						</p>
																					</xsl:for-each>
																					<xsl:for-each select="cac:TaxCategory">
																						<xsl:for-each select="cac:TaxScheme">
																							<xsl:for-each select="cbc:ID">
																								<xsl:apply-templates/>
																							</xsl:for-each>
																						</xsl:for-each>
																					</xsl:for-each>
																					<xsl:for-each select="cac:TaxCategory">
																						<xsl:for-each select="cbc:Percent">
																							<xsl:apply-templates/>
																						</xsl:for-each>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																		<br/>
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=false()])  &gt;  0">
																			<span style="font-weight:bold; ">
																				<xsl:text>Allowances at line level:</xsl:text>
																			</span>
																			<span>
																				<xsl:text>&#160;</xsl:text>
																			</span>
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator =  boolean( false )">
																					<xsl:for-each select="cbc:AllowanceChargeReason">
																						<p>
																							<span style="font-family:Times New Roman; ">
																								<xsl:apply-templates/>
																							</span>
																						</p>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																		<br/>
																	</xsl:for-each>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																</td>
																<td align="right" class="cellRightBorder" width="148">
																	<br/>
																	<xsl:for-each select="cac:LineItem">
																		<br/>
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=true()])  &gt;  0">
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator !=  boolean( false )">
																					<xsl:for-each select="cbc:MultiplierFactorNumeric">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																		<br/>
																		<br/>
																		<br/>
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=false()])  &gt;  0">
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator =  boolean( false )">
																					<xsl:for-each select="cbc:MultiplierFactorNumeric">
																						<br/>
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																		<br/>
																	</xsl:for-each>
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																</td>
																<td align="right" class="cellRightBorder" width="100">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=true()])  &gt;  0">
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator !=  boolean( false )">
																					<xsl:for-each select="cbc:Amount">
																						<p align="right">
																							<span>
																								<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																							</span>
																							<span>
																								<xsl:text>&#160;</xsl:text>
																							</span>
																						</p>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																		<br/>
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=false()])  &gt;  0">
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator =  boolean( false )">
																					<xsl:for-each select="cbc:Amount">
																						<p>
																							<span>
																								<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																							</span>
																							<span>
																								<xsl:text>&#160;</xsl:text>
																							</span>
																						</p>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																	</xsl:for-each>
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
																<td align="right" class="cellNoBorder" width="48">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=true()])  &gt;  0">
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator !=  boolean( false )">
																					<xsl:for-each select="cbc:Amount">
																						<p align="right">
																							<span>
																								<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																							</span>
																							<span>
																								<xsl:text>&#160;</xsl:text>
																							</span>
																						</p>
																					</xsl:for-each>
																				</xsl:if>
																				<br/>
																				<span>
																					<xsl:value-of select="round-half-to-even(cbc:Amount * cac:TaxCategory/cbc:Percent div 100  , 2 )"/>
																				</span>
																			</xsl:for-each>
																		</xsl:if>
																		<br/>
																		<xsl:if test="count(./cac:AllowanceCharge[./cbc:ChargeIndicator=false()])  &gt;  0">
																			<xsl:for-each select="cac:AllowanceCharge">
																				<xsl:if test="./cbc:ChargeIndicator =  boolean( false )">
																					<xsl:for-each select="cbc:Amount">
																						<p>
																							<span>
																								<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																								<xsl:text>&#160;</xsl:text>
																							</span>
																						</p>
																					</xsl:for-each>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:if>
																	</xsl:for-each>
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
															</tr>
															<tr>
																<td class="cellRightBorder" width="588">
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text> ?? </xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="100">
																	<span>
																		<xsl:text>?? </xsl:text>
																	</span>
																</td>
																<td align="right" class="cellNoBorder" width="48">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cbc:LineExtensionAmount">
																			<span>
																				<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																			</span>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
															</tr>
															<tr>
																<td class="cellRightBorder" width="588">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cbc:Note">
																			<span style="font-family:Times New Roman; ">
																				<xsl:apply-templates/>
																			</span>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="148">
																	<span>
																		<xsl:text>&#160; </xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="100">
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
																<td align="right" class="cellNoBorder" width="48"/>
															</tr>
															<tr>
																<td class="cellRightBorder" width="588">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Delivery">
																			<xsl:for-each select="cbc:LatestDeliveryDate">
																				<span style="font-family:Arial; ">
																					<xsl:text>Latest Delivery Date:</xsl:text>
																				</span>
																				<span>
																					<xsl:text>&#160;</xsl:text>
																				</span>
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
																	<br/>
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Delivery">
																			<xsl:for-each select="cac:RequestedDeliveryPeriod">
																				<xsl:for-each select="cbc:DurationMeasure">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																				<xsl:for-each select="cbc:DurationMeasure">
																					<xsl:for-each select="@unitCode">
																						<span>
																							<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td class="cellRightBorder" width="148"/>
																<td class="cellRightBorder" width="148"/>
																<td class="cellRightBorder" width="100"/>
																<td align="right" class="cellNoBorder" width="48"/>
															</tr>
														</tbody>
													</table>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</p>
									<br/>
									<table style="border-bottom-color:black; border-bottom-style:solid; border-left-style:none; border-right-style:none; border-top-color:black; border-top-style:solid; " border="2" cellPadding="0" cellSpacing="0">
										<tbody>
											<tr>
												<td style="border-bottom-color:black; border-bottom-style:solid; border-bottom-width:thin; border-right-style:none; " align="center" class="cellRightBorder" colSpan="6" width="1050">
													<span style="font-family:Arial; ">
														<xsl:text>CLASSIFIED TAX CATEGORY AT LINE LEVEL</xsl:text>
													</span>
												</td>
											</tr>
											<tr style="background-color:silver; font-family:Arial; ">
												<td class="cellRightBorder" width="175">
													<span>
														<xsl:text>Order Line ID</xsl:text>
													</span>
												</td>
												<td class="cellRightBorder" width="175">
													<span>
														<xsl:text>Tax type</xsl:text>
													</span>
												</td>
												<td class="cellRightBorder" width="175">
													<span>
														<xsl:text>Tax category</xsl:text>
													</span>
												</td>
												<td class="cellRightBorder" width="175">
													<span>
														<xsl:text>Tax rate</xsl:text>
													</span>
												</td>
												<td class="cellRightBorder" width="175">
													<span>
														<xsl:text>Tax exemption reason code</xsl:text>
													</span>
												</td>
												<td class="cellNoBorder" width="175">
													<span>
														<xsl:text>Tax exemption reason</xsl:text>
													</span>
												</td>
											</tr>
										</tbody>
									</table>
									<p style="margin-top:-2px; ">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:OrderLine">
													<table style="border-bottom-color:black; border-bottom-style:solid; border-bottom-width:thin; border-left-style:none; border-right-style:none; border-top-style:none; " border="2" cellPadding="0" cellSpacing="0">
														<tbody>
															<tr>
																<td align="center" class="cellRightBorder" width="175">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cbc:ID">
																			<xsl:apply-templates/>
																		</xsl:for-each>
																	</xsl:for-each>
																	<span>
																		<xsl:text>??</xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="175">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:ClassifiedTaxCategory">
																				<xsl:for-each select="cac:TaxScheme">
																					<xsl:for-each select="cbc:ID">
																						<xsl:apply-templates/>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																	<br/>
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:ClassifiedTaxCategory">
																				<xsl:for-each select="cac:TaxScheme">
																					<xsl:for-each select="cbc:Name">
																						<span>
																							<xsl:text>(</xsl:text>
																						</span>
																						<xsl:apply-templates/>
																						<span>
																							<xsl:text>)</xsl:text>
																						</span>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td class="cellRightBorder" width="175">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:ClassifiedTaxCategory">
																				<xsl:for-each select="cbc:ID">
																					<span>
																						<xsl:value-of select="document(&apos;TaxCategoryID.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																					</span>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td class="cellRightBorder" width="175">
																	<xsl:if test="not ( normalize-space(string(./cac:LineItem/cac:Item/cac:ClassifiedTaxCategory/cbc:Percent)) = &apos;&apos; )">
																		<xsl:for-each select="cac:LineItem">
																			<xsl:for-each select="cac:Item">
																				<xsl:for-each select="cac:ClassifiedTaxCategory">
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
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:ClassifiedTaxCategory">
																				<xsl:for-each select="cbc:PerUnitAmount">
																					<span>
																						<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
																					</span>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																	<span>
																		<xsl:text>&#160;</xsl:text>
																	</span>
																</td>
																<td class="cellRightBorder" width="175">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:ClassifiedTaxCategory">
																				<xsl:for-each select="cbc:TaxExemptionReasonCode">
																					<span>
																						<xsl:value-of select="document(&apos;TaxExemptionReasonCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																					</span>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
																<td class="cellNoBorder" width="175">
																	<xsl:for-each select="cac:LineItem">
																		<xsl:for-each select="cac:Item">
																			<xsl:for-each select="cac:ClassifiedTaxCategory">
																				<xsl:for-each select="cbc:TaxExemptionReason">
																					<xsl:apply-templates/>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</td>
															</tr>
														</tbody>
													</table>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</p>
									<br/>
								</td>
							</tr>
							<tr>
								<td align="justify" class="cellRightTopBorder" colSpan="3" rowSpan="8" vAlign="middle">
									<span style="font-family:Arial; ">
										<xsl:text>Pursuant to the provisions of Articles 3 and 4 of the Protocol on the Privileges and Immunities of the European Union the Commission is exempt from all taxes and dues, including value added tax, on payments due in respect of this Order Form. [In Belgium, use of this order form constitutes a request for VAT exemption. The invoice must include the following statement: &quot;Commande destinée à l&apos;usage officiel de l&apos;Union Européenne. Exonération de la TVA; article 42 alin. 3.3 du code de la TVA&quot;.] </xsl:text>
									</span>
								</td>
								<td align="left" class="cellTopBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Packaging </xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Insurance </xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Transport </xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Assembly</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td align="left" class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>VAT</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellTopBorder" colSpan="4">
									<span>
										<xsl:text>??</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td align="right" class="cellTopBorder" colSpan="4">
									<span style="font-family:Arial; font-weight:bold; ">
										<xsl:text>TOTAL:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:AnticipatedMonetaryTotal">
												<xsl:for-each select="cbc:PayableAmount">
													<span>
														<xsl:value-of select="format-number(round(number(.) * 100 ) div 100, '##0,00', 'format1')"/>
													</span>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:AnticipatedMonetaryTotal">
												<xsl:for-each select="cbc:PayableAmount">
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
										<xsl:text>&#160;</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellTopBorder" colSpan="4">
									<span>
										<xsl:text>??</xsl:text>
									</span>
								</td>
							</tr>
							<tr>
								<td class="cellRightTopBorder" colSpan="3">
									<span style="font-family:Arial; ">
										<xsl:text>Place of delivery or implementation and/or Incoterm:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:Delivery">
												<xsl:for-each select="cac:DeliveryLocation">
													<xsl:for-each select="cbc:Description">
														<xsl:apply-templates/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:Delivery">
												<xsl:for-each select="cac:DeliveryLocation">
													<xsl:for-each select="cac:Address">
														<xsl:for-each select="cbc:Department">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:BuildingName)) = &apos;&apos; )">
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:BuildingName">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:BuildingNumber)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:BuildingNumber">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:StreetName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:StreetName">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<span>
											<xsl:text>, </xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:AdditionalStreetName">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
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
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:Postbox)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:Postbox">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:PostalZone)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:PostalZone">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cbc:CityName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
															<xsl:for-each select="cbc:CityName">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery[1]/cac:DeliveryLocation/cac:Address/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:Delivery">
													<xsl:for-each select="cac:DeliveryLocation">
														<xsl:for-each select="cac:Address">
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
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:DeliveryTerms">
												<xsl:for-each select="cbc:ID">
													<xsl:apply-templates/>
													<span>
														<xsl:text>, </xsl:text>
													</span>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:DeliveryTerms">
												<xsl:for-each select="cbc:SpecialTerms">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td align="center" class="cellTopBorder" colSpan="4">
									<span style="font-family:Arial; font-weight:bold; ">
										<xsl:text>Supplier&apos;s signature</xsl:text>
									</span>
								</td>
								<td/>
							</tr>
							<tr>
								<td class="cellRightTopBorder" colSpan="3">
									<span style="font-family:Arial; ">
										<xsl:text>Dispatched by: </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:FreightForwarderParty">
												<xsl:for-each select="cac:PartyName">
													<xsl:for-each select="cbc:Name">
														<xsl:apply-templates/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td align="center" class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; font-style:italic; ">
										<xsl:text>(confirmation that the order has been accepted)</xsl:text>
									</span>
								</td>
								<td/>
							</tr>
							<tr>
								<td class="cellRightTopBorder" colSpan="3">
									<span style="font-family:Arial; ">
										<xsl:text>Final date of delivery or implementation: </xsl:text>
									</span>
									<xsl:choose>
										<xsl:when test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery/cbc:LatestDeliveryDate)) = &apos;&apos; ) or not (normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:Delivery/cac:RequestedDeliveryPeriod/cbc:DurationMeasure))= &apos;&apos; )">
											<xsl:for-each select="n1:OrderChangeReceived">
												<xsl:for-each select="orc:OrderChange">
													<xsl:for-each select="cac:Delivery">
														<xsl:for-each select="cbc:LatestDeliveryDate">
															<span>
																<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
																<xsl:text>/</xsl:text>
																<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
																<xsl:text>/</xsl:text>
																<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), '0000', 'format1')"/>
															</span>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
											<span>
												<xsl:text>&#160;</xsl:text>
											</span>
											<xsl:for-each select="n1:OrderChangeReceived">
												<xsl:for-each select="orc:OrderChange">
													<xsl:for-each select="cac:Delivery">
														<xsl:for-each select="cac:RequestedDeliveryPeriod">
															<xsl:for-each select="cbc:DurationMeasure">
																<xsl:apply-templates/>
															</xsl:for-each>
															<xsl:for-each select="cbc:DurationMeasure">
																<xsl:for-each select="@unitCode">
																	<span>
																		<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																	</span>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:when>
										<xsl:otherwise>
											<span style="font-weight:bold; ">
												<xsl:text>Please refere to each Order line.</xsl:text>
											</span>
										</xsl:otherwise>
									</xsl:choose>
								</td>
								<td class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Name:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:SellerSupplierParty">
												<xsl:for-each select="cac:Party">
													<xsl:for-each select="cac:Person">
														<xsl:for-each select="cbc:FamilyName">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:SellerSupplierParty">
												<xsl:for-each select="cac:Party">
													<xsl:for-each select="cac:Person">
														<xsl:for-each select="cbc:FirstName">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td/>
							</tr>
							<tr>
								<td class="cellRightTopBorder" colSpan="3">
									<span style="font-family:Arial; ">
										<xsl:text>Terms of payment: </xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:PaymentMeans">
												<xsl:for-each select="cbc:InstructionID">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Position:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:SellerSupplierParty">
												<xsl:for-each select="cac:Party">
													<xsl:for-each select="cac:Person">
														<xsl:for-each select="cbc:JobTitle">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td/>
							</tr>
							<tr>
								<td class="cellRightTopBorder" colSpan="3">
									<span style="font-family:Arial; ">
										<xsl:text>Guarantee:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:TransactionConditions">
												<xsl:for-each select="cbc:Description">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
								<td class="cellNoBorder" colSpan="4">
									<span style="font-family:Arial; ">
										<xsl:text>Date:</xsl:text>
									</span>
								</td>
								<td/>
							</tr>
							<tr>
								<td class="cellTopBorder" colSpan="7">
									<span style="font-family:Arial; ">
										<xsl:text>Requesting department:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
											<xsl:for-each select="cac:BuyerCustomerParty">
												<xsl:for-each select="cac:Party">
													<xsl:for-each select="cac:PartyName">
														<xsl:for-each select="cbc:Name">
															<xsl:apply-templates/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</td>
							</tr>
							<tr>
								<td style="font-family:Times New Roman; " class="cellNoBorder" colSpan="7">
									<span style="font-family:Arial; ">
										<xsl:text>Date of issue:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:for-each select="n1:OrderChangeReceived">
										<xsl:for-each select="orc:OrderChange">
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
								</td>
							</tr>
							<tr>
								<td class="cellNoBorder" colSpan="7">
									<span style="font-family:Arial; ">
										<xsl:text>Name/address of recepient:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PartyIdentification/cbc:ID)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyIdentification">
															<xsl:for-each select="cbc:ID">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
													<xsl:for-each select="cac:Party">
														<xsl:for-each select="cac:PartyName">
															<xsl:for-each select="cbc:Name">
																<xsl:apply-templates/>
															</xsl:for-each>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Department)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>&#160;</xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:BuildingName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:BuildingNumber)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:StreetName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:AdditionalStreetName)) = &apos;&apos; )">
										<span>
											<xsl:text> - </xsl:text>
										</span>
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cac:AddressLine/cbc:Line)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Postbox)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>,</xsl:text>
										</span>
									</xsl:if>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:PostalZone)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
										<span>
											<xsl:text>, </xsl:text>
										</span>
									</xsl:if>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="not ( normalize-space(string(n1:OrderChangeReceived/orc:OrderChange/cac:BuyerCustomerParty/cac:Party/cac:PostalAddress/cbc:Region)) = &apos;&apos; )">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cac:BuyerCustomerParty">
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
									<span style="font-family:Arial; ">
										<xsl:text>Remarks:</xsl:text>
									</span>
									<span>
										<xsl:text>&#160;</xsl:text>
									</span>
									<xsl:if test="count (n1:OrderChangeReceived/orc:OrderChange/cbc:Note ) &gt; 0">
										<xsl:for-each select="n1:OrderChangeReceived">
											<xsl:for-each select="orc:OrderChange">
												<xsl:for-each select="cbc:Note">
													<p>
														<xsl:apply-templates/>
													</p>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:if>
								</td>
							</tr>
						</tbody>
					</table>
					<br/>
					<br/>
					<br/>
					<xsl:if test="count (n1:OrderChangeReceived/eccac:ECDocumentReceivedData/eccac:ProcessingWarning/eccbc:FailedAssert) &gt;0">
						<xsl:for-each select="n1:OrderChangeReceived">
							<xsl:for-each select="eccac:ECDocumentReceivedData">
								<xsl:for-each select="eccac:ProcessingWarning">
									<xsl:apply-templates select="eccbc:FailedAssert"/>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:if>
					<br/>
					<br/>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
