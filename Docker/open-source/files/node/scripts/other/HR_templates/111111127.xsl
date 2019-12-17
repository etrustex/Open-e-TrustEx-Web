<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="ec:schema:xsd:Proposal-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
<xsl:param name="SV_OutputFormat" select="'HTML'"/>
<xsl:variable name="XML" select="/"/>
<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
<xsl:template match="/">
<xsl:variable name="headerForProfileLevel" select="fn:exists(n1:Proposal/cac:QuotationLine/cac:LineItem/cac:Item/cac:AdditionalItemProperty[cbc:Name='MAXLVL'])"/>
<xsl:variable name="QTM_AND_INI" select=" (n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;) and  n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationPropertyCode = string(&apos;INI&apos;))"/>
<xsl:variable name="FP_AND_INI" select=" (n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;INI&apos;) and n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationPropertyCode = string(&apos;INI&apos;))"/>
<xsl:variable name="hasAttLines" select="fn:exists(n1:Proposal/cac:QuotationLine/cac:LineItem/cac:Item/cac:ItemSpecificationDocumentReference/cbc:ID)"/>
<xsl:variable name="isCompetition" select="fn:exists(n1:Proposal/eccac:ECLot[eccbc:ModeCode='COMP'])"/>
<xsl:variable name="QTM_AND_NOT_INI" select=" (n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;) and  not(n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationPropertyCode = string(&apos;INI&apos;)))"/>
<html>
<body>
<xsl:for-each select="$XML">
<table cellSpacing="10" width="890">
	<tbody>
		<tr>
			<td colspan="2">
				<table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td style="border:1; border-color:black; " align="center" width="20%">
								<xsl:text>e-Request</xsl:text>
								<br/>
								<br/>
								<xsl:text>SUPPLIER PORTAL</xsl:text>
								<br/>
							</td>
							<td style="border:1; border-color:black; " align="center" width="80%">
								<h2>
									<xsl:text>Proposal</xsl:text>
								</h2>
								<h3 align="center">
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cbc:ID">
											<xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
								</h3>
								<p align="right">
									<xsl:text>Sent Date: </xsl:text>
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cbc:IssueDate">
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:text> at </xsl:text>
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cbc:IssueTime">
											<xsl:value-of select="format-number(number(substring(string(.), 1, 2)), '00')"/>
											<xsl:text>:</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 4, 2)), '00')"/>
										</xsl:for-each>
									</xsl:for-each>
								</p>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
    <!-- Header -->
    <tr>
      <td colspan="2">
        <table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
          <tbody>
            <tr>
              <th style="border:1; border-color:black; " bgColor="#999999" colSpan="4">
                <xsl:text>Request Reference: </xsl:text>
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECRequestForQuotationDocumentReference">
                    <xsl:for-each select="cac:RequestForQuotationDocumentReference">
                      <xsl:for-each select="cbc:ID">
                        <xsl:apply-templates/>
                      </xsl:for-each>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </th>
            </tr>

            <tr>
              <td style="border:1; border-color:black; " bgColor="#cccccc" width="20%">
                <xsl:text>Subject:</xsl:text>
              </td>
              <td style="border:1; border-color:black; " width="35%">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="cbc:Title">
                    <xsl:value-of select="fn:normalize-unicode( . )"/>
                  </xsl:for-each>
                </xsl:for-each>
              </td>
              <td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
                <xsl:text>Request Date:</xsl:text>
              </td>
              <td style="border:1; border-color:black; " width="30%">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECRequestForQuotationDocumentReference">
                    <xsl:for-each select="cac:RequestForQuotationDocumentReference">
                      <xsl:for-each select="cbc:IssueDate">
                        <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
                        <xsl:text>/</xsl:text>
                        <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
                        <xsl:text>/</xsl:text>
                        <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000', 'format1')"/>
                      </xsl:for-each>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>
            </tr>

            <tr>
              <td style="border:1; border-color:black; " bgColor="#cccccc" width="20%">
                <xsl:text>Market Procedure:</xsl:text>
              </td>
              <td style="border:1; border-color:black; " width="35%">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECLot">
                    <xsl:for-each select="eccbc:MarketProcedureName">
                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>
              <td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
                <xsl:text>Mode:</xsl:text>
              </td>
              <td style="border:1; border-color:black; " width="30%">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECLot">
                    <xsl:for-each select="eccbc:ModeCode">
                      <xsl:value-of select="document(&apos;ModeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>
            </tr>

            <tr>
              <td style="border:1; border-color:black; " bgColor="#cccccc" width="20%">
                <xsl:text>Framework Contract:</xsl:text>
              </td>
              <td style="border:1; border-color:black; " width="35%">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECRequestForQuotationDocumentReference">
                    <xsl:for-each select="cac:Contract">
                      <xsl:for-each select="cbc:ID">
                        <xsl:value-of select="fn:normalize-unicode( . )"/>
                      </xsl:for-each>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>


              <td style="border:1; border-color:black; " bgColor="#cccccc">
                <xsl:text>Lot:</xsl:text>
              </td>
              <td style="border:1; border-color:black; ">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECLot">
                    <xsl:for-each select="cbc:ID">
                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>


            </tr>
            <tr>
              <td style="border:1; border-color:black; " bgColor="#cccccc">
                <xsl:text>Request Type:</xsl:text>
              </td>
              <td style="border:1; border-color:black; ">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECRequestForQuotationDocumentReference">
                    <xsl:for-each select="eccbc:RequestForQuotationTypeCode">
                      <xsl:value-of select="document(&apos;RequestForQuotationTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>
              <td style="border:1; border-color:black; " bgColor="#cccccc">
                <xsl:text>Request Property:</xsl:text>
              </td>
              <td style="border:1; border-color:black; ">
                <xsl:for-each select="n1:Proposal">
                  <xsl:for-each select="eccac:ECRequestForQuotationDocumentReference">
                    <xsl:for-each select="eccbc:RequestForQuotationPropertyCode">
                      <xsl:value-of select="document(&apos;RequestForQuotationPropertyCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                    </xsl:for-each>
                  </xsl:for-each>
                </xsl:for-each>
              </td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
    <!-- End of the Common Header -->
		<tr>
			<td colspan="2">
				<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
					<tbody>
						<tr>
							<th style="border:1; border-color:black; " bgColor="#999999" rowSpan="3" width="5%">
								<xsl:text>To</xsl:text>
							</th>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Organisation:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="35%">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:OriginatorCustomerParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:PartyName">
												<xsl:for-each select="cbc:Name">
													<xsl:value-of select="fn:normalize-unicode( . )"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Phone:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="30%">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:OriginatorCustomerParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Contact">
												<xsl:for-each select="cbc:Telephone">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Contact Person:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="35%">
								<p>
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cac:OriginatorCustomerParty">
											<xsl:for-each select="cac:Party">
												<xsl:for-each select="cac:Person">
													<xsl:for-each select="cbc:FamilyName">
														<xsl:value-of select="fn:upper-case( fn:normalize-unicode(.) )"/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:text>, </xsl:text>
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cac:OriginatorCustomerParty">
											<xsl:for-each select="cac:Party">
												<xsl:for-each select="cac:Person">
													<xsl:for-each select="cbc:FirstName">
														<xsl:value-of select="fn:normalize-unicode( . )"/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</p>
							</td>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Fax:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="30%">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:OriginatorCustomerParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Contact">
												<xsl:for-each select="cbc:Telefax">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Email:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " colspan="3">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:OriginatorCustomerParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Contact">
												<xsl:for-each select="cbc:ElectronicMail">
													<xsl:value-of select="fn:normalize-unicode( . )"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
					<tbody>
						<tr>
							<th style="border-color:black; " bgColor="#999999" rowSpan="3" width="5%">
								<xsl:text>From</xsl:text>
							</th>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Organisation:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="35%">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:SellerSupplierParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:PartyName">
												<xsl:for-each select="cbc:Name">
													<xsl:value-of select="fn:normalize-unicode( . )"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Phone:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="30%">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:SellerSupplierParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Contact">
												<xsl:for-each select="cbc:Telephone">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Contact Person:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="35%">
								<p>
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cac:SellerSupplierParty">
											<xsl:for-each select="cac:Party">
												<xsl:for-each select="cac:Person">
													<xsl:for-each select="cbc:FamilyName">
														<xsl:value-of select="fn:upper-case( fn:normalize-unicode(.) )"/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
									<xsl:text>, </xsl:text>
									<xsl:for-each select="n1:Proposal">
										<xsl:for-each select="cac:SellerSupplierParty">
											<xsl:for-each select="cac:Party">
												<xsl:for-each select="cac:Person">
													<xsl:for-each select="cbc:FirstName">
														<xsl:value-of select="fn:normalize-unicode( . )"/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</p>
							</td>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Fax:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " width="30%">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:SellerSupplierParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Contact">
												<xsl:for-each select="cbc:Telefax">
													<xsl:apply-templates/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
								<xsl:text>Email:</xsl:text>
							</td>
							<td style="border:1; border-color:black; " colspan="3">
								<xsl:for-each select="n1:Proposal">
									<xsl:for-each select="cac:SellerSupplierParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Contact">
												<xsl:for-each select="cbc:ElectronicMail">
													<xsl:value-of select="fn:normalize-unicode( . )"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<p/>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
				<xsl:choose>
					<xsl:when test="n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;)">
						<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<th style="border:1; border-color:black; " bgColor="#999999" colspan="10">
										<xsl:text>Proposal</xsl:text>
									</th>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
										<xsl:text>Proposal Number (Internal Supplier Reference):</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="2" width="35%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="eccbc:SupplierDocumentID">
												<xsl:value-of select="fn:normalize-unicode( . )"/>
											</xsl:for-each>
										</xsl:for-each>
									</td>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="15%">
										<xsl:text>Final Proposal:</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="6" width="30%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="cbc:CompletionIndicator">
												<xsl:choose>
													<xsl:when test="string( . ) =&apos;false&apos;">
														<xsl:text>Not final</xsl:text>
													</xsl:when>
													<xsl:otherwise>
														<xsl:text>Final</xsl:text>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:for-each>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
										<xsl:text>Total Price:</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="9" width="30%">
											<xsl:choose>
																<xsl:when test="n1:Proposal/cac:QuotedMonetaryTotal/cbc:PayableAmount = 0">
																<xsl:text>Please refer to the Financial Offer document</xsl:text>
																</xsl:when>
																<xsl:otherwise>
															  	<xsl:value-of select="concat( n1:Proposal/cac:QuotedMonetaryTotal/cbc:PayableAmount ,&quot; &quot;, n1:Proposal/cac:QuotedMonetaryTotal/cbc:PayableAmount/@currencyID)"/>
															     </xsl:otherwise>
															     </xsl:choose>
										
									
									</td>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
                    <xsl:choose>
                      <xsl:when test="$QTM_AND_INI">
                        <xsl:text>Project Duration (Days):</xsl:text>
                      </xsl:when>
                      <xsl:when test="$QTM_AND_NOT_INI">
                        <xsl:text>Project Duration (Days):</xsl:text>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:text>Volume (Days):</xsl:text>
                      </xsl:otherwise>
                    </xsl:choose>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="9" width="30%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="cbc:Quantity">
												<xsl:apply-templates/>
											</xsl:for-each>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" colspan="10" width="30%">
										<xsl:choose>
											<xsl:when test="fn:exists(  n1:Proposal/cac:QuotationLine/cac:LineItem/cac:Item/cbc:Description  )">
												<br/>
												<table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:text>Line</xsl:text>
															</th>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:text>Profile</xsl:text>
															</th>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:choose>
																						<xsl:when test="$headerForProfileLevel">
																							<xsl:text>Profile Level (Max)</xsl:text>
																						</xsl:when>
																						<xsl:otherwise>
																							<xsl:text>Profile Level</xsl:text>
																						</xsl:otherwise>
																					</xsl:choose>
															</th>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:text>Specific Expertise</xsl:text>
															</th>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:text>Daily Price</xsl:text>
															</th>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:text>Number of Days</xsl:text>
															</th>
															<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
																<xsl:text>Total Price</xsl:text>
															</th>
														</tr>
													</thead>
													<tbody>
														<xsl:for-each select="n1:Proposal">
															<xsl:for-each select="cac:QuotationLine">
																<xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
																<tr>
																	<td style="border:1; border-color:black; " align="center">
																		<xsl:for-each select="cac:LineItem">
																			<xsl:for-each select="cbc:ID">
																				<xsl:apply-templates/>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td style="border:1; border-color:black; ">
																		<xsl:for-each select="cac:LineItem">
																			<xsl:for-each select="cac:Item">
																				<xsl:for-each select="cbc:Description">
																					<xsl:value-of select="document(&apos;ProfileValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td style="border:1; border-color:black; " align="center">
																		<xsl:for-each select="cac:LineItem">
																			<xsl:for-each select="cac:Item">
																				<xsl:for-each select="cac:AdditionalItemProperty">
																					<xsl:for-each select="cbc:Value">
																							<xsl:if test="(../cbc:Name = string(&apos;MAXLVL&apos;)) or (../cbc:Name = string(&apos;LEVL&apos;)) ">
																							<xsl:value-of select="document(&apos;ProfileLevel.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																						</xsl:if>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td style="border:1; border-color:black; ">
																		<xsl:for-each select="cac:LineItem">
																			<xsl:for-each select="cac:Item">
																				<xsl:for-each select="cac:AdditionalItemProperty">
																					<xsl:for-each select="cbc:Value">
																						<xsl:if test="../cbc:Name = string(&apos;DOEX&apos;)">
																							<xsl:value-of select="."/>
																						</xsl:if>
																					</xsl:for-each>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td style="border:1; border-color:black; " align="right">
																		<xsl:value-of select="concat(cac:LineItem/cac:Price/cbc:PriceAmount ,&quot; &quot;, cac:LineItem/cac:Price/cbc:PriceAmount/@currencyID)"/>
																	</td>
																	<td style="border:1; border-color:black; " align="right">
																		<xsl:for-each select="cac:LineItem">
																			<xsl:for-each select="cbc:Quantity">
																				<xsl:apply-templates/>
																			</xsl:for-each>
																		</xsl:for-each>
																	</td>
																	<td style="border:1; border-color:black; " align="right">
																		<xsl:value-of select="concat(  cac:LineItem/cbc:LineExtensionAmount ,&quot; &quot;,cac:LineItem/cbc:LineExtensionAmount/@currencyID)"/>
																	</td>
																</tr>
															</xsl:for-each>
														</xsl:for-each>
													</tbody>
												</table>
											</xsl:when>
											<xsl:otherwise/>
										</xsl:choose>
									</td>
								</tr>
							</tbody>
						</table>
					</xsl:when>
					<xsl:when test="n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;TM&apos;) or n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PTM&apos;)">
						<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<th style="border:1; border-color:black; " bgColor="#999999" colspan="10">
										<xsl:text>Proposal</xsl:text>
									</th>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
										<xsl:text>Proposal Number (Internal Supplier Reference):</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="2" width="35%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="eccbc:SupplierDocumentID">
												<xsl:value-of select="fn:normalize-unicode( . )"/>
											</xsl:for-each>
										</xsl:for-each>
									</td>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="15%">
										<xsl:text>Final Proposal:</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="6" width="30%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="cbc:CompletionIndicator">
												<xsl:choose>
													<xsl:when test="string( . ) =&apos;false&apos;">
														<xsl:text>Not final</xsl:text>
													</xsl:when>
													<xsl:otherwise>
														<xsl:text>Final</xsl:text>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:for-each>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" colspan="10" width="30%">
										<br/>
										<table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
											<thead>
												<tr>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:text>Line</xsl:text>
													</th>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:text>Profile</xsl:text>
													</th>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:choose>
																						<xsl:when test="$headerForProfileLevel">
																							<xsl:text>Profile Level (Max)</xsl:text>
																						</xsl:when>
																						<xsl:otherwise>
																							<xsl:text>Profile Level</xsl:text>
																						</xsl:otherwise>
																					</xsl:choose>
													</th>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:text>Specific Expertise</xsl:text>
													</th>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:text>Name</xsl:text>
													</th>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:text>Subcontractor</xsl:text>
													</th>
                          <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                            <xsl:text>Subcontractor Company</xsl:text>
                          </th>
													<th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
														<xsl:text>Availability date</xsl:text>
													</th>
													
												</tr>
											</thead>
											<tbody>
												<xsl:for-each select="n1:Proposal">
													<xsl:for-each select="cac:QuotationLine">
														<xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
														<tr>
															<td style="border:1; border-color:black; " align="center">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cbc:ID">
																		<xsl:apply-templates/>
																	</xsl:for-each>
																</xsl:for-each>
															</td>
															<td style="border:1; border-color:black; ">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Item">
																		<xsl:for-each select="cbc:Description">
																			<xsl:value-of select="document(&apos;ProfileValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</td>
															<td style="border:1; border-color:black; " align="center">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Item">
																		<xsl:for-each select="cac:AdditionalItemProperty">
																			<xsl:for-each select="cbc:Value">
																					<xsl:if test="(../cbc:Name = string(&apos;MAXLVL&apos;)) or (../cbc:Name = string(&apos;LEVL&apos;)) ">
																					<xsl:value-of select="document(&apos;ProfileLevel.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</td>
															<td style="border:1; border-color:black; ">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Item">
																		<xsl:for-each select="cac:AdditionalItemProperty">
																			<xsl:for-each select="cbc:Value">
																				<xsl:if test="../cbc:Name = string(&apos;DOEX&apos;)">
																					<xsl:value-of select="."/>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</td>
															<td style="border:1; border-color:black; " align="left">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Item">
																		<xsl:for-each select="cac:AdditionalItemProperty">
																			<xsl:for-each select="cbc:Value">
																				<xsl:if test="../cbc:Name = string(&apos;LANA&apos;)">
																					<xsl:value-of select="fn:upper-case( fn:normalize-unicode(.) )"/>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
																<xsl:text>, </xsl:text>
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Item">
																		<xsl:for-each select="cac:AdditionalItemProperty">
																			<xsl:for-each select="cbc:Value">
																				<xsl:if test="../cbc:Name = string(&apos;FINA&apos;)">
																					<xsl:value-of select="fn:normalize-unicode(.)"/>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
																<xsl:text>&#160;</xsl:text>
															</td>
															<td style="border:1; border-color:black; " align="center">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Item">
																		<xsl:for-each select="cac:AdditionalItemProperty">
																			<xsl:for-each select="cbc:Value">
																				<xsl:if test="../cbc:Name = string(&apos;FRELA&apos;)">
																					<xsl:value-of select="if (../cbc:Value = &apos;true&apos;) then &apos;YES&apos; else &apos;NO&apos;"/>
																				</xsl:if>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</td>
                              <td style="border:1; border-color:black; " align="center">
                                <xsl:for-each select="cac:LineItem">
                                  <xsl:for-each select="cac:Item">
                                    <xsl:for-each select="cac:AdditionalItemProperty">
                                      <xsl:for-each select="cbc:Value">
                                        <xsl:if test="../cbc:Name = string(&apos;CMPNY&apos;)">
                                          <xsl:value-of select="fn:normalize-unicode( . )"/>
                                        </xsl:if>
                                      </xsl:for-each>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </td>
															<td style="border:1; border-color:black; " align="center">
																<xsl:for-each select="cac:LineItem">
																	<xsl:for-each select="cac:Delivery">
																		<xsl:for-each select="cac:EstimatedDeliveryPeriod">
																			<xsl:for-each select="cbc:StartDate">
																				<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
																				<xsl:text>/</xsl:text>
																				<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
																				<xsl:text>/</xsl:text>
																				<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
																<xsl:text>&#160;</xsl:text>
															</td>
														</tr>
													</xsl:for-each>
												</xsl:for-each>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</xsl:when>
					<xsl:when test="n1:Proposal/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;FP&apos;)">
						<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
							<tbody>
								<tr>
									<th style="border:1; border-color:black; " bgColor="#999999" colspan="10">
										<xsl:text>Proposal</xsl:text>
									</th>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
										<xsl:text>Proposal Number (Internal Supplier Reference):</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="2" width="35%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="eccbc:SupplierDocumentID">
												<xsl:value-of select="fn:normalize-unicode( . )"/>
											</xsl:for-each>
										</xsl:for-each>
									</td>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="15%">
										<xsl:text>Final Proposal:</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="6" width="30%">
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="cbc:CompletionIndicator">
												<xsl:choose>
													<xsl:when test="string( . ) =&apos;false&apos;">
														<xsl:text>Not final</xsl:text>
													</xsl:when>
													<xsl:otherwise>
														<xsl:text>Final</xsl:text>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:for-each>
										</xsl:for-each>
									</td>
								</tr>
								<tr>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
									<xsl:choose>
															<xsl:when test="$FP_AND_INI">
																<xsl:text>Project Duration:</xsl:text>
																</xsl:when>
																<xsl:otherwise>
															     <xsl:text>Project Duration:</xsl:text>
															     </xsl:otherwise>
															     </xsl:choose>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="2" width="35%">
										<xsl:value-of select="concat( /n1:Proposal/cac:Delivery/cac:EstimatedDeliveryPeriod/cbc:DurationMeasure ,&quot; &quot;)"/>
										<xsl:for-each select="n1:Proposal">
											<xsl:for-each select="cac:Delivery">
												<xsl:for-each select="cac:EstimatedDeliveryPeriod">
													<xsl:for-each select="cbc:DurationMeasure">
														<xsl:for-each select="@unitCode">
															<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</td>
									<td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="15%">
										<xsl:text>Total Price:</xsl:text>
									</td>
									<td style="border:1; border-color:black; " align="left" colspan="6" width="30%">
									
									<xsl:choose>
																<xsl:when test="n1:Proposal/cac:QuotedMonetaryTotal/cbc:PayableAmount = 0">
																<xsl:text>Please refer to the Financial Offer document</xsl:text>
																</xsl:when>
																<xsl:otherwise>
															<xsl:value-of select="concat( n1:Proposal/cac:QuotedMonetaryTotal/cbc:PayableAmount ,&quot; &quot;, /n1:Proposal/cac:QuotedMonetaryTotal/cbc:PayableAmount/@currencyID)"/>
															     </xsl:otherwise>
															     </xsl:choose>
										
									</td>
								</tr>
							</tbody>
						</table>
					</xsl:when>
				</xsl:choose>
			</td>
		</tr>
		<tr>
								<td colspan="2">
									<p/>
								</td>
							</tr>
							<xsl:if test="$hasAttLines">							
							<tr>
								<td colspan="2" valign="top">
									<table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th style="border:1; border-color:black; " align="center" bgcolor="#999999" colspan="3">
													<xsl:text>Line Attachment(s)</xsl:text>
												</th>
											</tr>
										</thead>
										<tbody>
											<xsl:for-each select="n1:Proposal">
												<xsl:for-each select="cac:QuotationLine">
													<xsl:sort select="cac:LineItem" data-type="text" order="ascending"/>
													<xsl:for-each select="cac:LineItem">
							<xsl:if test="fn:exists(./cac:Item/cac:ItemSpecificationDocumentReference/cbc:ID)">						
                              <tr>
                                <td style="border:1; border-color:black;" width="3%">
                                  <xsl:for-each select="cbc:ID">
                                    <xsl:apply-templates/>
                                  </xsl:for-each>
                                </td>
                                <td>
                                  <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                                    <tbody>
                                      <xsl:for-each select="cac:Item">
                                        <xsl:for-each select="cac:ItemSpecificationDocumentReference">
                                          <tr>
                                            <td style="border:1; border-color:black; " width="70%">
                                              <xsl:for-each select="cbc:ID">
                                                <xsl:apply-templates/>
                                              </xsl:for-each>
                                            </td>
                                            <td style="border:1; border-color:black; " align="center">
                                              <xsl:for-each select="cbc:DocumentType">
                                                <xsl:value-of select="document(&apos;AttachmentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                              </xsl:for-each>
                                            </td>
                                          </tr>
                                        </xsl:for-each>
                                      </xsl:for-each>
                                    </tbody>
                                  </table>
                                </td>
                              </tr>
                            </xsl:if>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</tbody>
									</table>
								</td>
							</tr>
							</xsl:if>
		<tr>
			<td colspan="2">
				<p/>
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="border:1; border-color:black; " align="center" bgcolor="#999999" colspan="2">
								<xsl:text>Attachment(s)</xsl:text>
							</th>
						</tr>
					</thead>
					<tbody>
						<xsl:for-each select="n1:Proposal">
							<xsl:for-each select="cac:AdditionalDocumentReference">
								<xsl:sort select="cbc:ID" data-type="text" order="ascending"/>
								<tr>
									<td style="border:1; border-color:black; ">
										<xsl:for-each select="cbc:ID">
											<xsl:apply-templates/>
										</xsl:for-each>
									</td>
									<td style="border:1; border-color:black; " align="center">
										<xsl:for-each select="cbc:DocumentType">
											<xsl:value-of select="document(&apos;AttachmentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each>
									</td>
								</tr>
							</xsl:for-each>
						</xsl:for-each>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>
</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>