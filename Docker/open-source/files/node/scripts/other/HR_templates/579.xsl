<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:mdda="urn:oasis:names:specification:ubl:schema:xsd:DespatchAdvice-2" xmlns:n1="ec:schema:xsd:DespatchAdviceReceived-0.1" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="/">
    <!-- ================================================= -->
    <!-- Please update TemplateVersion for each deployment -->
    <!-- ================================================= -->
    <xsl:variable name="TemplateVersion">3.01</xsl:variable>
    <!-- ================================================= -->
		<html>
			<head>
				<title>Dispatch Advice Human Readable Format</title>
				<style type="text/css">
          BODY {
          color : WindowText;
          font-family : Arial;
          font-size : medium;
          size: landscape;
          }
          .cellNoBorder{
          border-style:none;
          }
          .cellRightBorder{
          border-left-style:none;
          border-bottom-style:none;
          border-top-style:none;
          border-right-style:thin;
          border-right-width:1px;
          border-right-color:black;
          }
          .cellRightTopBorder{
          border-left-style:none;
          border-bottom-style:none;
          border-top-style:thin;
          border-top-width:1px;
          border-top-color:black;
          border-right-style:thin;
          border-right-width:1px;
          border-right-color:black;
          }
          .cellTopBorder{
          border-left-style:none;
          border-bottom-style:none;
          border-right-style:none;
          border-top-style:thin;
          border-top-width:1px;
          border-top-color:black;
          }
          td {
          font-family: Arial;
          font-size: medium;
          vertical-align: text-top;
          }

          @page {
          size: A4 landscape;
          }
        </style>
			</head>
			<body>
				<xsl:for-each select="/n1:DespatchAdviceReceived/mdda:DespatchAdvice">
				
				<table style="border-bottom-color:black; border-left-color:black; border-right-color:black; border-top-color:black; " align="center" width="100%" cellpadding="2" cellspacing="0" border="2">
					<tbody>
						<tr>
							<td class="cellRightBorder" colspan="2" align="center"><em>Ship to</em></td>
							<td class="cellRightBorder labelBold" colspan="2" align="center"><h2>Dispatch Advice</h2></td>
							<td class="cellNoBorder" colspan="2" align="center"><em>Shipper</em></td>
						</tr>
						<tr>
							<td class="cellRightTopBorder" width="30%" align="left" colspan="2"><b><xsl:text>Organization: </xsl:text></b><xsl:value-of select="cac:DeliveryCustomerParty/cac:Party/cac:PartyName/cbc:Name"/></td>
							<td class="cellRightTopBorder" width="40%" align="left" colspan="2"><b><xsl:text>No: </xsl:text></b><xsl:value-of select="cbc:ID"/></td>
							<td class="cellTopBorder" width="30%" colspan="2" rowspan="5" valign="top">
								<xsl:for-each select="cac:DespatchSupplierParty/cac:Party">
									<xsl:if test="not ( normalize-space(cac:PartyName/cbc:Name) = &apos;&apos; )">
										<xsl:value-of select="cac:PartyName/cbc:Name"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:StreetName) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:AdditionalStreetName) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cac:AddressLine/cbc:Line) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cac:AddressLine/cbc:Line"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:Postbox) = &apos;&apos; )">
										<br/>Postbox: <xsl:value-of select="cac:PostalAddress/cbc:Postbox"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:PostalZone) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:PostalZone"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:CityName) = &apos;&apos; )">
										<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:CityName"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:Region) = &apos;&apos; )">
										<br/><xsl:value-of select="cac:PostalAddress/cbc:Region"/>
									</xsl:if>
									<xsl:if test="not ( normalize-space(cac:PostalAddress/cac:Country/cbc:IdentificationCode) = &apos;&apos; )">
										<xsl:text>,&#160;</xsl:text>
										<xsl:for-each select="cac:PostalAddress/cac:Country/cbc:IdentificationCode">
											<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
										</xsl:for-each>
									</xsl:if>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td class="cellRightTopBorder" align="left" colspan="2"><b><xsl:text>Department: </xsl:text></b><xsl:value-of select="cac:DeliveryCustomerParty/cac:Party/cac:PostalAddress/cbc:Department"/></td>
							<td class="cellRightTopBorder" align="left" colspan="2"><b><xsl:text>Customer Order No: </xsl:text></b><xsl:value-of select="cac:OrderReference/cbc:ID"/></td>
						</tr>
						<tr>
							<td class="cellRightTopBorder" align="left" rowspan="2" colspan="2"><b><xsl:text>Ship to Address:</xsl:text></b><br/><xsl:value-of select="cac:Shipment/cac:Delivery/cac:DeliveryLocation/cbc:Description"/></td>
							<td class="cellRightTopBorder" align="left" colspan="2"><b><xsl:text>Shipper Ref: </xsl:text></b>

                <xsl:for-each select="cac:AdditionalDocumentReference">
                  <xsl:choose>
                    <xsl:when test="cbc:DocumentTypeCode=&apos;149&apos;">
                     <!-- Nothing to show -->
                    </xsl:when>
                    <xsl:when test="cbc:DocumentTypeCode=&apos;916&apos;">
                      <!-- Nothing to show -->
                    </xsl:when>
                    <xsl:when test="cbc:DocumentType=&apos;Specific Contract&apos; or cbc:DocumentType=&apos;Framework Contract&apos;">
                      <!-- Nothing to show -->
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:value-of select="cbc:DocumentType"/>
                      <xsl:text>&#160;</xsl:text>
                      <xsl:value-of select="cbc:ID"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:for-each>
              </td>
						</tr>
						<tr>
							<td class="cellRightTopBorder" align="left" colspan="2"><b><xsl:text>Status: </xsl:text></b>
								<xsl:choose>
									<xsl:when test="( (cbc:DocumentStatusCode = &apos;partial_delivery&apos;) or (cbc:DocumentStatusCode = &apos;PARTIAL_DELIVERY&apos; ) )">Partial delivery</xsl:when>
									<xsl:when test="( (cbc:DocumentStatusCode = &apos;order_completed&apos;) or (cbc:DocumentStatusCode = &apos;ORDER_COMPLETED&apos; ) )">Order completed</xsl:when>
									<xsl:otherwise><xsl:value-of select="cbc:DocumentStatusCode"/></xsl:otherwise>
								</xsl:choose>
							</td>
						</tr>
						<tr>
							<td class="cellRightTopBorder" align="left" colspan="2"><b><xsl:text>Delivery Date: </xsl:text></b>
								<xsl:for-each select="cac:Shipment/cac:Delivery/cbc:ActualDeliveryDate">
									<xsl:value-of select="substring(string(.), 9, 2)"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="substring(string(.), 6, 2)"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="substring(string(.), 1, 4)"/>
								</xsl:for-each>
							</td>
							<td class="cellRightTopBorder" align="left" colspan="2"><b><xsl:text>Issue Date: </xsl:text></b>
								<xsl:for-each select="cbc:IssueDate">
									<xsl:value-of select="substring(string(.), 9, 2)"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="substring(string(.), 6, 2)"/>
									<xsl:text>/</xsl:text>
									<xsl:value-of select="substring(string(.), 1, 4)"/>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
              <td class="cellRightTopBorder" align="left" colspan="2">
                <b>
                  <xsl:text>Service Provision Start Date: </xsl:text>
                </b>
                <xsl:for-each select="cac:DespatchLine[position()=1]/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartDate">
                  <xsl:value-of select="substring(string(.), 9, 2)"/>
                  <xsl:text>/</xsl:text>
                  <xsl:value-of select="substring(string(.), 6, 2)"/>
                  <xsl:text>/</xsl:text>
                  <xsl:value-of select="substring(string(.), 1, 4)"/>
                </xsl:for-each>
              </td>
							<td class="cellRightTopBorder" align="left" colspan="2" rowspan="2"><b><xsl:text>Note: </xsl:text></b><xsl:value-of select="cbc:Note"/></td>
              <td class="cellRightTopBorder" align="left" colspan="2" rowspan="2">
                <b>
                  <xsl:text>Contract: </xsl:text>
                </b>
                <xsl:for-each select="cac:AdditionalDocumentReference">
                  <xsl:if test="cbc:DocumentTypeCode=&apos;149&apos;">
                    <xsl:value-of select="cbc:ID"/>
                  </xsl:if>
                </xsl:for-each>

              </td>
						</tr>
            <tr>
              <td class="cellRightTopBorder" align="left" colspan="2">
                <b>
                  <xsl:text>Service Provision End Date: </xsl:text>
                </b>
                <xsl:for-each select="cac:DespatchLine[position()=1]/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndDate">
                  <xsl:value-of select="substring(string(.), 9, 2)"/>
                  <xsl:text>/</xsl:text>
                  <xsl:value-of select="substring(string(.), 6, 2)"/>
                  <xsl:text>/</xsl:text>
                  <xsl:value-of select="substring(string(.), 1, 4)"/>
                </xsl:for-each>
              </td>
            </tr>
						<tr>
							<td class="cellRightTopBorder" colspan="3" valign="top"><b><xsl:text>Shipped from:</xsl:text></b><br/>
							<xsl:for-each select="cac:Shipment/cac:Consignment/cac:ConsignorParty">
								<xsl:if test="not ( normalize-space(cac:PartyName/cbc:Name) = &apos;&apos; )">
									<xsl:value-of select="cac:PartyName/cbc:Name"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:StreetName) = &apos;&apos; )">
									<br/><xsl:value-of select="cac:PostalAddress/cbc:StreetName"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:AdditionalStreetName) = &apos;&apos; )">
									<br/><xsl:value-of select="cac:PostalAddress/cbc:AdditionalStreetName"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cac:AddressLine/cbc:Line) = &apos;&apos; )">
									<br/><xsl:value-of select="cac:PostalAddress/cac:AddressLine/cbc:Line"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:PostalZone) = &apos;&apos; )">
									<br/><xsl:value-of select="cac:PostalAddress/cbc:PostalZone"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:CityName) = &apos;&apos; )">
									<xsl:text>&#160;</xsl:text><xsl:value-of select="cac:PostalAddress/cbc:CityName"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cbc:Region) = &apos;&apos; )">
									<br/><xsl:value-of select="cac:PostalAddress/cbc:Region"/>
								</xsl:if>
								<xsl:if test="not ( normalize-space(cac:PostalAddress/cac:Country/cbc:IdentificationCode) = &apos;&apos; )">
									<xsl:text>,&#160;</xsl:text>
									<xsl:for-each select="cac:PostalAddress/cac:Country/cbc:IdentificationCode">
										<xsl:value-of select="document(&apos;CountryIdentificationCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
									</xsl:for-each>
								</xsl:if>
							</xsl:for-each>
							</td>
							<td class="cellTopBorder" colspan="3" valign="top">
                <!--
                dragovi: Hidden for Services and now it's only Services
                A check should be seen when this information is required
								<b><xsl:text>Forwarder: </xsl:text></b><xsl:value-of select="cac:Shipment/cac:Consignment/cac:FreightForwarderParty/cac:PartyName/cbc:Name"/><br/>
								<b><xsl:text>Colli: </xsl:text></b><xsl:value-of select="sum(cac:Shipment/cac:TransportHandlingUnit/cbc:TotalPackageQuantity)"/><br/>
								<table>
									<tbody>
										<xsl:for-each select="cac:Shipment/cac:TransportHandlingUnit">
										<tr>
											<td>
												<xsl:for-each select="cbc:TotalPackageQuantity/@unitCode">
													<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.= current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
												</xsl:for-each>: <xsl:value-of select="cbc:TotalPackageQuantity"/>
											</td>
										</tr>
										</xsl:for-each>
									</tbody>
								</table><br/>
								<b><xsl:text>Gross Weight: </xsl:text></b><xsl:value-of select="cac:Shipment/cbc:GrossWeightMeasure"/>
								<xsl:text>&#160;</xsl:text>
								<xsl:for-each select="cac:Shipment/cbc:GrossWeightMeasure/@unitCode">
									<xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.= current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
								</xsl:for-each>
                -->
                <xsl:text>&#160;</xsl:text>
							</td>
						</tr>
					</tbody>
				</table>
				
				<br/>
				<table style="border-bottom-color:black; border-left-color:black; border-right-color:black; border-top-color:black; " align="center" width="100%" cellpadding="2" cellspacing="0" border="2">
					<tbody>
						<tr style="background-color:silver;">
							<td class="cellRightBorder labelBold" width="10%" align="center">Line ID</td>
							<!--<td class="cellRightBorder labelBold" width="18%" align="center">Item No.</td>-->
							<td class="cellRightBorder labelBold" width="40%">Description</td>
							<td class="cellRightBorder labelBold" width="10%" align="center">Unit</td>
							<td class="cellRightBorder labelBold" width="20%" align="center">Dispatched Quantity</td>
              <td class="cellRightBorder labelBold" width="20%" align="center">Dispatched Amount</td>
							<!--<td class="cellNoBorder labelBold" width="10%" align="center">Back Order</td>-->
						</tr>
						<xsl:for-each select="cac:DespatchLine">
						<tr>
							<td class="cellRightTopBorder" align="center"><xsl:value-of select="cbc:ID"/></td>
							<!--<td class="cellRightTopBorder"><xsl:value-of select="cac:Item/cac:SellersItemIdentification/cbc:ID"/></td>-->
							<td class="cellRightTopBorder"><xsl:value-of select="cac:Item/cbc:Name"/></td>
							<td class="cellRightTopBorder" align="center">
                <xsl:call-template name="retrieveCodeTemplate">
                  <xsl:with-param name="unitCode" select="cbc:DeliveredQuantity/@unitCode"/>
                </xsl:call-template>
							</td>
							<td class="cellRightTopBorder" align="center"><xsl:value-of select="cbc:DeliveredQuantity"/></td>
              <td class="cellRightTopBorder" align="center">
                <xsl:for-each select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount">
                    <xsl:value-of select="." />
                    <xsl:text>&#160;</xsl:text>
                    <xsl:value-of select="@currencyID" />
                </xsl:for-each>

                
              </td>
							<!--<td class="cellTopBorder" align="center"><xsl:value-of select="cbc:BackorderQuantity"/></td>-->
						</tr>
						</xsl:for-each>
					</tbody>
				</table>
				
				<xsl:if test="count (cac:DespatchLine/cac:Item/cac:ItemInstance) &gt; 0">
				<br/>
				<table style="border-bottom-color:black; border-left-color:black; border-right-color:black; border-top-color:black; " align="center" width="100%" cellpadding="2" cellspacing="0" border="2">
					<tbody>
						<tr style="background-color:silver;">
							<td class="cellNoBorder labelBold" colspan="2" align="center">Serial Numbers:</td>
						</tr>
						<xsl:for-each select="cac:DespatchLine">
						<xsl:if test="count (cac:Item/cac:ItemInstance) &gt; 0">
							<tr>
								<td class="cellRightTopBorder" align="left" width="35%"><xsl:value-of select="cac:Item/cbc:Name"/></td>
								<td class="cellTopBorder" width="65%">
									<xsl:for-each select="cac:Item/cac:ItemInstance">
										<xsl:value-of select="cbc:SerialID"/>
										<xsl:text>&#160;</xsl:text><xsl:text>&#160;</xsl:text><xsl:text>&#160;</xsl:text>
									</xsl:for-each>
								</td>
							</tr>
						</xsl:if>
						</xsl:for-each>
					</tbody>
				</table>
				</xsl:if>
				
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
  <xsl:template name="retrieveCodeTemplate">
    <xsl:param name="unitCode"/>
    <xsl:choose>
      <xsl:when test="not($unitCode != &apos;&apos; and $unitCode != &apos;NIL&apos;)">
        <xsl:text> </xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:for-each select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList/Row">
          <xsl:if test="Value[@ColumnRef=&apos;code&apos;]/SimpleValue = $unitCode">
            <xsl:value-of select="Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
          </xsl:if>
        </xsl:for-each>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
</xsl:stylesheet>