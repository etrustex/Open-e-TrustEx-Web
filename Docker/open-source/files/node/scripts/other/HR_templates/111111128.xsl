<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="ec:schema:xsd:ProposalRequest-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
<xsl:param name="SV_OutputFormat" select="'HTML'"/>
<xsl:variable name="XML" select="/"/>
<xsl:variable name="isCompetition" select="fn:exists(n1:ProposalRequest/eccac:ECLot[eccbc:ModeCode='COMP'])"/>

  <xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
<xsl:template match="/">
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
						<td style="border:1; border-color:black; " align="center" width="20%"><br/>CUSTOMER<br/><br/></td>
						<td style="border:1; border-color:black; " align="center" width="80%">
							<h2>
								<xsl:text>Willingness Receipt Form</xsl:text>
							</h2>
							<h3 align="center">
								<xsl:for-each select="n1:ProposalRequest">
									<xsl:for-each select="cbc:ID">
										<xsl:apply-templates/>
									</xsl:for-each>
								</xsl:for-each>
							</h3>
							<p align="right">
								<xsl:text>Sent Date: </xsl:text>
								<xsl:for-each select="n1:ProposalRequest">
									<xsl:for-each select="cbc:IssueDate">
										<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
										<xsl:text>/</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000', 'format1')"/>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:text> at </xsl:text>
								<xsl:for-each select="n1:ProposalRequest">
									<xsl:for-each select="cbc:IssueTime">
										<xsl:value-of select="format-number(number(substring(string(.), 1, 2)), '00', 'format1')"/>
										<xsl:text>:</xsl:text>
										<xsl:value-of select="format-number(number(substring(string(.), 4, 2)), '00', 'format1')"/>
									</xsl:for-each>
								</xsl:for-each>
							</p>
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
            <th style="border:1; border-color:black; " bgColor="#999999" colSpan="4">
              <xsl:text>Request Reference: </xsl:text>
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
                <xsl:for-each select="cbc:Title">
                  <xsl:value-of select="fn:normalize-unicode( . )"/>
                </xsl:for-each>
              </xsl:for-each>
            </td>
            <td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
              <xsl:text>Request Date:</xsl:text>
            </td>
            <td style="border:1; border-color:black; " width="30%">
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
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
              <xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
								<xsl:for-each select="n1:ProposalRequest">
									<xsl:for-each select="cac:SellerSupplierParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Person">
												<xsl:for-each select="cbc:FamilyName">
													<xsl:value-of select="fn:upper-case(fn:normalize-unicode(.))"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:text>, </xsl:text>
								<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
								<xsl:for-each select="n1:ProposalRequest">
									<xsl:for-each select="cac:OriginatorCustomerParty">
										<xsl:for-each select="cac:Party">
											<xsl:for-each select="cac:Person">
												<xsl:for-each select="cbc:FamilyName">
													<xsl:value-of select="fn:upper-case(fn:normalize-unicode(.))"/>
												</xsl:for-each>
											</xsl:for-each>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:text>, </xsl:text>
								<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
							<xsl:for-each select="n1:ProposalRequest">
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
			<p/>
		</td>
	</tr>
	<tr>
		<td colspan="2" width="100%">
			<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
				<tbody>
					<tr>
						<th style="border:1; border-color:black; " bgColor="#999999">
							<xsl:text>Willingness Receipt Form</xsl:text>
						</th>
					</tr>
					<tr>
						<td style="border:1; border-color:black; " align="left" width="30%">
							<p>
								<xsl:choose>
									<xsl:when test="n1:ProposalRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode =string(&apos;QTM&apos;) or n1:ProposalRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode =string(&apos;TM&apos;)  or n1:ProposalRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode =string(&apos;PTM&apos;)">
										<xsl:text>(This document has been sent automatically.)</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<br/>
									</xsl:otherwise>
								</xsl:choose>
							</p>
							<p>
								<xsl:text>The </xsl:text>
								<xsl:for-each select="n1:ProposalRequest">
									<xsl:for-each select="cac:OriginatorDocumentReference">
										<xsl:for-each select="cbc:DocumentTypeCode">
											<xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:value-of select="concat(&quot; &quot;, n1:ProposalRequest/cac:OriginatorDocumentReference/cbc:ID )"/>
								<xsl:text> has been received</xsl:text>
								<xsl:if test="n1:ProposalRequest/eccac:ProcessTimeline/eccac:DocumentTimeline/cbc:DocumentTypeCode=&apos;ADT3&apos;">
									<xsl:text> and the Proposal Date has been accepted</xsl:text>
								</xsl:if>
								<xsl:text>. Please continue the Request Process with the next document.</xsl:text>
							</p>
							<p>
								<xsl:if test="exists(n1:ProposalRequest/cbc:Remarks)">
									<xsl:text>Comments: </xsl:text>
									<xsl:for-each select="n1:ProposalRequest">
										<xsl:for-each select="cbc:Remarks">
											<xsl:apply-templates/>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:if>
							</p>
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
		<td colspan="2" valign="top">
			<table style="border:1; border-color:black; " border="1" cellpadding="2" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th style="border:1; border-color:black; " align="center" bgcolor="#999999" colspan="2">
							<xsl:text>Timeline</xsl:text>
						</th>
					</tr>
				</thead>
				<tbody>
					<xsl:for-each select="n1:ProposalRequest">
						<xsl:for-each select="eccac:ProcessTimeline">
							<xsl:for-each select="eccac:DocumentTimeline">
								<xsl:sort select="cbc:ExpiryDate" data-type="text" order="ascending"/>
								<tr>
									<td style="border:1; border-color:black; ">
										<xsl:for-each select="cbc:DocumentTypeCode">
											<xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each>
									</td>
									<td style="border:1; border-color:black; " align="right">
										<xsl:for-each select="cbc:ExpiryDate">
											<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00', 'format1')"/>
											<xsl:text>/</xsl:text>
											<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000', 'format1')"/>
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
		<td colspan="2" class="style2"><xsl:text>We wish to remind you that this Document does not constitute any firm order until such time as a contractual document, signed by the Customer, has been received by you.</xsl:text></td>
	</tr>
</tbody>
</table>
</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
