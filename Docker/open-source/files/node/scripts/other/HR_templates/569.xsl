<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="ec:schema:xsd:Willingness-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
<xsl:param name="SV_OutputFormat" select="'HTML'"/>
<xsl:variable name="XML" select="/"/>
<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
<xsl:template match="/">
<html>
<body>
<table cellSpacing="10" width="890">
<tbody>
	<tr>
		<td>
			<table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td style="border:1; border-color:black; " align="center" width="20%">
							<xsl:text>e-Request</xsl:text>
							<br/><br/>
							<xsl:text>SUPPLIER PORTAL</xsl:text>
						</td>
						<td style="border:1; border-color:black; " align="center" width="80%">
							<h2>
								<xsl:text>Yes/No Willingness Document</xsl:text>
							</h2>
							<h3 align="center">
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
										<xsl:for-each select="cbc:ID">
											<xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</h3>
							<p align="right">
								<xsl:text>Sent Date: </xsl:text>
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
										<xsl:for-each select="cbc:IssueDate">
											<xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:text> at </xsl:text>
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
										<xsl:for-each select="cbc:IssueTime">
											<xsl:value-of select="format-number(number(substring(string(.), 1, 2)), '00', 'format1')"/>
											<xsl:text>:</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 4, 2)), '00', 'format1')"/>
										</xsl:for-each>
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
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
                <xsl:for-each select="cbc:Title">
                  <xsl:value-of select="fn:normalize-unicode( . )"/>
                </xsl:for-each>
              </xsl:for-each>
            </td>
            <td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
              <xsl:text>Request Date:</xsl:text>
            </td>
            <td style="border:1; border-color:black; " width="30%">
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
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
              <xsl:for-each select="n1:Willingness">
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
		<td>
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
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
									<xsl:for-each select="cac:OriginatorCustomerParty">
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
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
							<xsl:text>Phone:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " width="30%">
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
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
							</xsl:for-each>
						</td>
					</tr>
					<tr>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
							<xsl:text>Contact Person:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " align="left" width="30%">
							<p>
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
										<xsl:for-each select="cac:OriginatorCustomerParty">
											<xsl:for-each select="cac:Party">
												<xsl:for-each select="cac:Person">
													<xsl:for-each select="cbc:FamilyName">
														<xsl:value-of select="fn:upper-case(.)"/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:text>, </xsl:text>
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
										<xsl:for-each select="cac:OriginatorCustomerParty">
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
							</p>
						</td>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
							<xsl:text>Fax:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " width="30%">
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
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
							</xsl:for-each>
						</td>
					</tr>
					<tr>
						<td style="border:1; border-color:black; " bgcolor="#cccccc">
							<xsl:text>Email:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " colspan="3">
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
									<xsl:for-each select="cac:OriginatorCustomerParty">
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
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td>
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
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
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
						</td>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
							<xsl:text>Phone:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " width="30%">
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
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
							</xsl:for-each>
						</td>
					</tr>
					<tr>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
							<xsl:text>Contact Person:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " width="30%">
							<p>
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
										<xsl:for-each select="cac:SellerSupplierParty">
											<xsl:for-each select="cac:Party">
												<xsl:for-each select="cac:Person">
													<xsl:for-each select="cbc:FamilyName">
														<xsl:value-of select="fn:upper-case(.)"/>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:text>, </xsl:text>
								<xsl:for-each select="$XML">
									<xsl:for-each select="n1:Willingness">
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
							</p>
						</td>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
							<xsl:text>Fax:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " width="30%">
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
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
							</xsl:for-each>
						</td>
					</tr>
					<tr>
						<td style="border:1; border-color:black; " bgcolor="#cccccc">
							<xsl:text>Email:</xsl:text>
						</td>
						<td style="border:1; border-color:black; " colspan="3">
							<xsl:for-each select="$XML">
								<xsl:for-each select="n1:Willingness">
									<xsl:for-each select="cac:SellerSupplierParty">
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
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<br/>
		</td>
	</tr>
	<tr>
		<td width="100%">
			<table style="border:1; border-color:black; " border="1" cellPadding="2" cellSpacing="0" width="100%">
				<tbody>
					<tr>
						<th style="border:1; border-color:black; " bgColor="#999999" colSpan="4">
							<xsl:text>Yes/No Willingness Document</xsl:text>
						</th>
					</tr>
					<tr>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="20%">
						<xsl:choose>
							<xsl:when test="n1:Willingness/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;RFO&apos;) or n1:Willingness/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PES&apos;)">
								<xsl:text>Contractor&apos;s Willingness to Send a Formal Offer</xsl:text>
							</xsl:when>
							<xsl:otherwise>
								<xsl:text>Contractor&apos;s Willingness to Send a Proposal:</xsl:text>
							</xsl:otherwise>
						</xsl:choose>
						</td>
						<td style="border:1; border-color:black; " align="center" width="35%">
							<xsl:choose>
								<xsl:when test="n1:Willingness/cbc:Indicator  != boolean (false)">
									<span style="font-weight:bold;">
										<xsl:text>YES</xsl:text>
									</span>
								</xsl:when>
								<xsl:otherwise>
									<span style="font-weight:bold;">
										<xsl:text>NO</xsl:text>
									</span>
								</xsl:otherwise>
							</xsl:choose>
						</td>
						<td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
						<xsl:choose>
							<xsl:when test="n1:Willingness/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;RFO&apos;) or n1:Willingness/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PES&apos;)">
								<xsl:text>Formal Offer Date:</xsl:text>
							</xsl:when>
							<xsl:otherwise>
								<xsl:text>Proposal Date:</xsl:text>
							</xsl:otherwise>
						</xsl:choose>
						</td>
						<td style="border:1; border-color:black; " align="center" width="30%">
							<xsl:choose>
								<xsl:when test="n1:Willingness/cbc:Indicator  != boolean (false)">
									<xsl:for-each select="$XML">
										<xsl:for-each select="n1:Willingness/eccac:DocumentTimeline">
											<xsl:for-each select="cbc:ExpiryDate">
												<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
												<xsl:text>/</xsl:text>
												<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
											</xsl:for-each>
											<xsl:if test="cbc:ExpiryTime != &apos;&apos;">
												<xsl:text> GMT </xsl:text>
												<xsl:for-each select="cbc:ExpiryTime">
													<xsl:value-of select="format-number(number(substring(string(.), 1, 2)), '00')"/>
													<xsl:text>:</xsl:text>
													<xsl:value-of select="format-number(number(substring(string(.), 4, 2)), '00')"/>
													<xsl:text> </xsl:text>
													<xsl:value-of select="substring(string(.), 13)"/>
												</xsl:for-each>
											</xsl:if>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:when>
								<xsl:otherwise>
									<xsl:text>Not applicable</xsl:text>
								</xsl:otherwise>
							</xsl:choose>
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
</tbody>
</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
