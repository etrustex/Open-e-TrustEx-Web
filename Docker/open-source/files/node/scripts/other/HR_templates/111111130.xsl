<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="ec:schema:xsd:QuotationRequest-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
  <xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
  <xsl:param name="SV_OutputFormat" select="'HTML'"/>
  <xsl:variable name="XML" select="/"/>
  <xsl:variable name="isPES" select="(n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PES&apos;))"/>
  <xsl:variable name="headerForProfileLevel" select="fn:exists(n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cac:Item/cac:AdditionalItemProperty[cbc:Name='MAXLVL'])"/>
  <xsl:variable name="training" select="(n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string (&apos;BLMC&apos;) or
                                        n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string (&apos;CTCT&apos;) or
                                        n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string (&apos;ELRN&apos;) or
                                        n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string (&apos;TMTC&apos;) )"/>
  <xsl:variable name="QTM_AND_INI" select=" (n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;) and  n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationPropertyCode = string(&apos;INI&apos;))"/>
  <xsl:variable name="FP_AND_INI" select=" (n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode  = string(&apos;INI&apos;) and  n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationPropertyCode = string(&apos;INI&apos;))"/>
  <xsl:variable name="hasAttLines" select="fn:exists(n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cac:Item/cac:ItemSpecificationDocumentReference/cbc:ID)"/>
  <xsl:variable name="isCompetition" select="fn:exists(n1:QuotationRequest/eccac:ECLot[eccbc:ModeCode='COMP'])"/>
  <xsl:variable name="QTM_AND_NOT_INI" select=" (n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;) and  not(n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationPropertyCode = string(&apos;INI&apos;)))"/>
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
                        <td style="border:1; border-color:black; " align="center" width="20%">
                          <br/>CUSTOMER<br/>
                          <br/>
                        </td>
                        <td style="border:1; border-color:black; " align="center" width="80%">
                          <h2>
                            <xsl:for-each select="n1:QuotationRequest">
                              <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                              </xsl:for-each>
                            </xsl:for-each>
                          </h2>
                          <h3 align="center">
                            <xsl:for-each select="n1:QuotationRequest">
                              <xsl:for-each select="cbc:ID">
                                <xsl:apply-templates/>
                              </xsl:for-each>
                            </xsl:for-each>
                          </h3>
                          <p align="right">
                            <xsl:text>Sent Date: </xsl:text>
                            <xsl:for-each select="n1:QuotationRequest">
                              <xsl:for-each select="cbc:IssueDate">
                                <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                <xsl:text>/</xsl:text>
                                <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                <xsl:text>/</xsl:text>
                                <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
                              </xsl:for-each>
                            </xsl:for-each>
                            <xsl:text> at </xsl:text>
                            <xsl:for-each select="n1:QuotationRequest">
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
                        <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
                            <xsl:for-each select="cbc:Title">
                              <xsl:value-of select="fn:normalize-unicode( . )"/>
                            </xsl:for-each>
                          </xsl:for-each>
                        </td>
                        <td style="border:1; border-color:black; " bgColor="#cccccc" width="15%">
                          <xsl:text>Request Date:</xsl:text>
                        </td>
                        <td style="border:1; border-color:black; " width="30%">
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                            <xsl:for-each select="n1:QuotationRequest">
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
                            <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                            <xsl:for-each select="n1:QuotationRequest">
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
                            <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                          <xsl:for-each select="n1:QuotationRequest">
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
                  <xsl:choose>
                    <xsl:when test="(n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;TM&apos;) or n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PTM&apos;)) and n1:QuotationRequest/eccbc:SummaryDescriptionCode = string(&apos;APR&apos;)">
                      <table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="9">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="9" width="20%">
                              <p>
                                <xsl:text>The </xsl:text>
                                <xsl:for-each select="n1:QuotationRequest">
                                  <xsl:for-each select="cac:OriginatorDocumentReference">
                                    <xsl:for-each select="cbc:DocumentTypeCode">
                                      <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                                <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                                <xsl:text> has been accepted with the following details:</xsl:text>
                              </p>
                              <table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
                                <thead>
                                  <tr>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Line</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Profile</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">

                                      <xsl:text>Profile Level</xsl:text>

                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Specific Expertise</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Name</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Subcontractor</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Subcontractor Company</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Number of Days</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Requested Start Date</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Comments</xsl:text>
                                    </th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <xsl:for-each select="n1:QuotationRequest">
                                    <xsl:for-each select="cac:RequestForQuotationLine">
                                      <xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
                                      <xsl:for-each select="cac:LineItem">
                                        <tr>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p>
                                              <xsl:for-each select="cbc:ID">
                                                <xsl:apply-templates/>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cbc:Description">
                                                  <xsl:value-of select="document(&apos;ProfileValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="(../cbc:Name = string(&apos;MAXLVL&apos;)) or (../cbc:Name = string(&apos;LEVL&apos;)) ">
                                                      <xsl:value-of select="document(&apos;ProfileLevel.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;DOEX&apos;)">
                                                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;LANA&apos;)">
                                                      <xsl:value-of select="fn:upper-case(fn:normalize-unicode(.))"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                              <xsl:text>, </xsl:text>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;FINA&apos;)">
                                                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;FRELA&apos;)">
                                                      <xsl:value-of select="if (../cbc:Value = &apos;true&apos;) then &apos;YES&apos; else &apos;NO&apos;"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;CMPNY&apos;)">
                                                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="right">
                                            <p>
                                              <xsl:for-each select="cbc:Quantity">
                                                <xsl:apply-templates/>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="right">
                                            <p>
                                              <xsl:for-each select="cac:Delivery">
                                                <xsl:for-each select="cac:RequestedDeliveryPeriod">
                                                  <xsl:for-each select="cbc:StartDate">
                                                    <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                    <xsl:text>/</xsl:text>
                                                    <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                    <xsl:text>/</xsl:text>
                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <xsl:choose>
                                              <xsl:when test="exists(  /n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cbc:Note  )">
                                                <p>
                                                  <xsl:for-each select="cbc:Note">
                                                    <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                  </xsl:for-each>
                                                </p>
                                              </xsl:when>
                                              <xsl:otherwise>
                                                <p>
                                                  <xsl:text>&#160;</xsl:text>
                                                </p>
                                              </xsl:otherwise>
                                            </xsl:choose>
                                          </td>
                                        </tr>
                                      </xsl:for-each>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </tbody>
                              </table>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </xsl:when>
                    <xsl:when test="n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;) and n1:QuotationRequest/eccbc:SummaryDescriptionCode =string(&apos;APR&apos;)">
                      <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="4">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="4" width="20%">
                              <p>
                                <xsl:text>The </xsl:text>
                                <xsl:for-each select="n1:QuotationRequest">
                                  <xsl:for-each select="cac:OriginatorDocumentReference">
                                    <xsl:for-each select="cbc:DocumentTypeCode">
                                      <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                                <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                                <xsl:text> has been accepted with the following details:</xsl:text>
                              </p>
                              <xsl:choose>
                                <xsl:when test="fn:exists(  n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cac:Item/cbc:Description  )">
                                  <br/>
                                  <br/>
                                  <table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
                                    <thead>
                                      <tr>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Line</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Profile</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">




                                          <xsl:text>Profile Level</xsl:text>

                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Specific Expertise</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Number of Days</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Comments</xsl:text>
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <xsl:for-each select="n1:QuotationRequest">
                                        <xsl:for-each select="cac:RequestForQuotationLine">
                                          <xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
                                          <xsl:for-each select="cac:LineItem">
                                            <tr>
                                              <td style="border:1; border-color:black; " align="center">
                                                <p>
                                                  <xsl:for-each select="cbc:ID">
                                                    <xsl:apply-templates/>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; ">
                                                <p>
                                                  <xsl:for-each select="cac:Item">
                                                    <xsl:for-each select="cbc:Description">
                                                      <xsl:value-of select="document(&apos;ProfileValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                    </xsl:for-each>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; " align="center">
                                                <p>
                                                  <xsl:for-each select="cac:Item">
                                                    <xsl:for-each select="cac:AdditionalItemProperty">
                                                      <xsl:for-each select="cbc:Value">
                                                        <xsl:if test="(../cbc:Name = string(&apos;MAXLVL&apos;)) or (../cbc:Name = string(&apos;LEVL&apos;)) ">
                                                          <xsl:value-of select="document(&apos;ProfileLevel.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                        </xsl:if>
                                                      </xsl:for-each>
                                                    </xsl:for-each>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; ">
                                                <p>
                                                  <xsl:for-each select="cac:Item">
                                                    <xsl:for-each select="cac:AdditionalItemProperty">
                                                      <xsl:for-each select="cbc:Value">
                                                        <xsl:if test="../cbc:Name = string(&apos;DOEX&apos;)">
                                                          <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                        </xsl:if>
                                                      </xsl:for-each>
                                                    </xsl:for-each>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; " align="right">
                                                <p>
                                                  <xsl:for-each select="cbc:Quantity">
                                                    <xsl:apply-templates/>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; " align="left">
                                                <xsl:choose>
                                                  <xsl:when test="exists(  /n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cbc:Note  )">
                                                    <p>
                                                      <xsl:for-each select="cbc:Note">
                                                        <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                      </xsl:for-each>
                                                    </p>
                                                  </xsl:when>
                                                  <xsl:otherwise>
                                                    <p>
                                                      <xsl:text>&#160;</xsl:text>
                                                    </p>
                                                  </xsl:otherwise>
                                                </xsl:choose>
                                              </td>
                                            </tr>
                                          </xsl:for-each>
                                        </xsl:for-each>
                                      </xsl:for-each>
                                    </tbody>
                                  </table>
                                </xsl:when>
                                <xsl:otherwise/>
                              </xsl:choose>
                            </td>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " bgcolor="#cccccc">
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
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cbc:Quantity">
                                  <xsl:apply-templates/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </td>
                            <td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="15%">
                              <xsl:text>Requested Start Date:</xsl:text>
                            </td>
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:Delivery">
                                  <xsl:for-each select="cac:RequestedDeliveryPeriod">
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
                            </td>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" width="30%">Total Price:</td>
                            <td style="border:1; border-color:black; " align="left" width="30%" colspan="3">
                              <xsl:value-of select="concat( /n1:QuotationRequest/cac:QuotedMonetaryTotal/cbc:LineExtensionAmount ,&quot; &quot;)"/>
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:QuotedMonetaryTotal">
                                  <xsl:for-each select="cbc:LineExtensionAmount">
                                    <xsl:for-each select="@currencyID">
                                      <xsl:value-of select="string(.)"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:for-each>
                            </td>

                          </tr>
                        </tbody>
                      </table>
                    </xsl:when>
                    <xsl:when test="n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;FP&apos;) and n1:QuotationRequest/eccbc:SummaryDescriptionCode =string(&apos;APR&apos;)">
                      <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="4">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="4" width="20%">
                              <xsl:text>The </xsl:text>
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:OriginatorDocumentReference">
                                  <xsl:for-each select="cbc:DocumentTypeCode">
                                    <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:for-each>
                              <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                              <xsl:text> has been accepted with the following details:</xsl:text>
                              <br/>
                              <br/>
                            </td>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
                              <xsl:text>Requested Start Date:</xsl:text>
                            </td>
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:Delivery">
                                  <xsl:for-each select="cac:RequestedDeliveryPeriod">
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
                            </td>
                            <td style="border:1; border-color:black; " bgcolor="#cccccc" width="20%">
                              <xsl:text>Total Price:</xsl:text>
                            </td>
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:value-of select="concat( /n1:QuotationRequest/cac:QuotedMonetaryTotal/cbc:LineExtensionAmount ,&quot; &quot;)"/>
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:QuotedMonetaryTotal">
                                  <xsl:for-each select="cbc:LineExtensionAmount">
                                    <xsl:for-each select="@currencyID">
                                      <xsl:value-of select="string(.)"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:for-each>
                            </td>
                          </tr>
                          <xsl:if test="fn:not($isPES)">
                            <tr>
                              <td style="border:1; border-color:black; " bgcolor="#cccccc" width="20%">
                                <xsl:choose>
                                  <xsl:when test="$FP_AND_INI">
                                    <xsl:text>Project Duration:</xsl:text>
                                  </xsl:when>
                                  <xsl:otherwise>
                                    <xsl:text>Project Duration:</xsl:text>
                                  </xsl:otherwise>
                                </xsl:choose>
                              </td>
                              <td style="border:1; border-color:black; " align="left" colspan="3" width="30%">
                                <xsl:value-of select="concat( n1:QuotationRequest/cac:Delivery/cac:RequestedDeliveryPeriod/cbc:DurationMeasure ,&quot; &quot;)"/>
                                <xsl:for-each select="n1:QuotationRequest">
                                  <xsl:for-each select="cac:Delivery">
                                    <xsl:for-each select="cac:RequestedDeliveryPeriod">
                                      <xsl:for-each select="cbc:DurationMeasure">
                                        <xsl:for-each select="@unitCode">
                                          <xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                        </xsl:for-each>
                                      </xsl:for-each>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </td>
                            </tr>
                          </xsl:if>
                        </tbody>
                      </table>
                    </xsl:when>
                    <xsl:when test="( n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;FP&apos;) or n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;RFO&apos;) or n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PES&apos;)) and n1:QuotationRequest/eccbc:SummaryDescriptionCode =string(&apos;NOF&apos;)">
                      <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="4">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="4" width="20%">
                              <p>
                                <xsl:text>Please update your </xsl:text>
                                <xsl:for-each select="n1:QuotationRequest">
                                  <xsl:for-each select="cac:OriginatorDocumentReference">
                                    <xsl:for-each select="cbc:DocumentTypeCode">
                                      <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                                <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                                <xsl:text> with the following details:</xsl:text>
                              </p>
                            </td>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="20%">
                              <xsl:text>Requested Date:</xsl:text>
                            </td>
                            <td style="border:1; border-color:black; " align="left" width="35%">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:Delivery">
                                  <xsl:for-each select="cac:RequestedDeliveryPeriod">
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
                            </td>
                            <td style="border:1; border-color:black; " bgcolor="#cccccc" width="15%">
                              <xsl:text>Total Price:</xsl:text>
                            </td>
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:value-of select="concat( /n1:QuotationRequest/cac:QuotedMonetaryTotal/cbc:LineExtensionAmount ,&quot; &quot;)"/>
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:QuotedMonetaryTotal">
                                  <xsl:for-each select="cbc:LineExtensionAmount">
                                    <xsl:for-each select="@currencyID">
                                      <xsl:value-of select="string(.)"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:for-each>
                            </td>
                          </tr>
                          <xsl:if test="fn:not($isPES)">
                            <tr>
                              <td style="border:1; border-color:black; " bgcolor="#cccccc" width="20%">
                                <xsl:choose>
                                  <xsl:when test="$FP_AND_INI">
                                    <xsl:text>Project Duration:</xsl:text>
                                  </xsl:when>
                                  <xsl:otherwise>
                                    <xsl:text>Project Duration:</xsl:text>
                                  </xsl:otherwise>
                                </xsl:choose>
                              </td>
                              <td style="border:1; border-color:black; " align="left" colspan="3" width="30%">
                                <p>
                                  <xsl:value-of select="fn:concat(/n1:QuotationRequest/cac:Delivery/cac:RequestedDeliveryPeriod/cbc:DurationMeasure,&quot; &quot;)"/>
                                  <xsl:for-each select="n1:QuotationRequest">
                                    <xsl:for-each select="cac:Delivery">
                                      <xsl:for-each select="cac:RequestedDeliveryPeriod">
                                        <xsl:for-each select="cbc:DurationMeasure">
                                          <xsl:for-each select="@unitCode">
                                            <xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                          </xsl:for-each>
                                        </xsl:for-each>
                                      </xsl:for-each>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </p>
                              </td>
                            </tr>
                          </xsl:if>
                        </tbody>
                      </table>
                    </xsl:when>
                    <xsl:when test="n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;QTM&apos;) and n1:QuotationRequest/eccbc:SummaryDescriptionCode =string(&apos;NOF&apos;)">
                      <br/>
                      <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="4">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="4" width="20%">
                              <p>
                                <xsl:text>Please update your </xsl:text>
                                <xsl:for-each select="n1:QuotationRequest">
                                  <xsl:for-each select="cac:OriginatorDocumentReference">
                                    <xsl:for-each select="cbc:DocumentTypeCode">
                                      <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:for-each>
                                <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                                <xsl:text> with the following details:</xsl:text>
                              </p>
                              <p/>
                              <xsl:choose>
                                <xsl:when test="fn:exists(  n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cac:Item/cbc:Description  )">
                                  <br/>
                                  <br/>
                                  <table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
                                    <thead>
                                      <tr>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Line</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Profile</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">



                                          <xsl:text>Profile Level</xsl:text>

                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Specific Expertise</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Number of Days</xsl:text>
                                        </th>
                                        <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                          <xsl:text>Comments</xsl:text>
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <xsl:for-each select="n1:QuotationRequest">
                                        <xsl:for-each select="cac:RequestForQuotationLine">
                                          <xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
                                          <xsl:for-each select="cac:LineItem">
                                            <tr>
                                              <td style="border:1; border-color:black; " align="center">
                                                <p>
                                                  <xsl:for-each select="cbc:ID">
                                                    <xsl:apply-templates/>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; ">
                                                <p>
                                                  <xsl:for-each select="cac:Item">
                                                    <xsl:for-each select="cbc:Description">
                                                      <xsl:value-of select="document(&apos;ProfileValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                    </xsl:for-each>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; " align="center">
                                                <p>
                                                  <xsl:for-each select="cac:Item">
                                                    <xsl:for-each select="cac:AdditionalItemProperty">
                                                      <xsl:for-each select="cbc:Value">
                                                        <xsl:if test="(../cbc:Name = string(&apos;MAXLVL&apos;)) or (../cbc:Name = string(&apos;LEVL&apos;)) ">
                                                          <xsl:value-of select="document(&apos;ProfileLevel.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                        </xsl:if>
                                                      </xsl:for-each>
                                                    </xsl:for-each>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; ">
                                                <p>
                                                  <xsl:for-each select="cac:Item">
                                                    <xsl:for-each select="cac:AdditionalItemProperty">
                                                      <xsl:for-each select="cbc:Value">
                                                        <xsl:if test="../cbc:Name = string(&apos;DOEX&apos;)">
                                                          <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                        </xsl:if>
                                                      </xsl:for-each>
                                                    </xsl:for-each>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; " align="right">
                                                <p>
                                                  <xsl:for-each select="cbc:Quantity">
                                                    <xsl:apply-templates/>
                                                  </xsl:for-each>
                                                </p>
                                              </td>
                                              <td style="border:1; border-color:black; " align="left">
                                                <xsl:choose>
                                                  <xsl:when test="exists(  /n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cbc:Note  )">
                                                    <p>
                                                      <xsl:for-each select="cbc:Note">
                                                        <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                      </xsl:for-each>
                                                    </p>
                                                  </xsl:when>
                                                  <xsl:otherwise>
                                                    <p>
                                                      <xsl:text>&#160;</xsl:text>
                                                    </p>
                                                  </xsl:otherwise>
                                                </xsl:choose>
                                              </td>
                                            </tr>
                                          </xsl:for-each>
                                        </xsl:for-each>
                                      </xsl:for-each>
                                    </tbody>
                                  </table>
                                </xsl:when>
                                <xsl:otherwise/>
                              </xsl:choose>
                            </td>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " bgcolor="#cccccc">
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
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cbc:Quantity">
                                  <xsl:apply-templates/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </td>
                            <td style="border:1; border-color:black; " align="left" bgcolor="#cccccc" width="15%">
                              <xsl:text>Requested Start Date:</xsl:text>
                            </td>
                            <td style="border:1; border-color:black; " align="left" width="30%">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:Delivery">
                                  <xsl:for-each select="cac:RequestedDeliveryPeriod">
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
                            </td>
                          </tr>

                        </tbody>
                      </table>
                    </xsl:when>
                    <xsl:when test="(n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;TM&apos;) or n1:QuotationRequest/eccac:ECRequestForQuotationDocumentReference/eccbc:RequestForQuotationTypeCode = string(&apos;PTM&apos;)) and n1:QuotationRequest/eccbc:SummaryDescriptionCode =string(&apos;NOF&apos;)">
                      <table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="9">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="9" width="20%">
                              <xsl:text>Please update your </xsl:text>
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:OriginatorDocumentReference">
                                  <xsl:for-each select="cbc:DocumentTypeCode">
                                    <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:for-each>
                              <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                              <xsl:text> with the following details:</xsl:text>
                              <br/>
                              <br/>
                              <table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
                                <thead>
                                  <tr>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Line</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Profile</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">

                                      <xsl:text>Profile Level</xsl:text>

                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Specific Expertise</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Name</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Subcontractor</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Subcontractor Company</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Number of Days</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Requested Start Date</xsl:text>
                                    </th>
                                    <th style="background-color:#cccccc; border:1; border-color:black; " align="center">
                                      <xsl:text>Comments</xsl:text>
                                    </th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <xsl:for-each select="n1:QuotationRequest">
                                    <xsl:for-each select="cac:RequestForQuotationLine">
                                      <xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
                                      <xsl:for-each select="cac:LineItem">
                                        <tr>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p>
                                              <xsl:for-each select="cbc:ID">
                                                <xsl:apply-templates/>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cbc:Description">
                                                  <xsl:value-of select="document(&apos;ProfileValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="(../cbc:Name = string(&apos;MAXLVL&apos;)) or (../cbc:Name = string(&apos;LEVL&apos;)) ">
                                                      <xsl:value-of select="document(&apos;ProfileLevel.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <p>
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;DOEX&apos;)">
                                                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;LANA&apos;)">
                                                    <xsl:value-of select="fn:upper-case(fn:normalize-unicode(.) )"/>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                            <xsl:text>, </xsl:text>
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;FINA&apos;)">
                                                    <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p align="center">
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;FRELA&apos;)">
                                                      <xsl:value-of select="if (../cbc:Value = &apos;true&apos;) then &apos;YES&apos; else &apos;NO&apos;"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p align="center">
                                              <xsl:for-each select="cac:Item">
                                                <xsl:for-each select="cac:AdditionalItemProperty">
                                                  <xsl:for-each select="cbc:Value">
                                                    <xsl:if test="../cbc:Name = string(&apos;CMPNY&apos;)">
                                                      <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                    </xsl:if>
                                                  </xsl:for-each>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="right">
                                            <xsl:for-each select="cbc:Quantity">
                                              <xsl:apply-templates/>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="right">
                                            <xsl:for-each select="cac:Delivery">
                                              <xsl:for-each select="cac:RequestedDeliveryPeriod">
                                                <xsl:for-each select="cbc:StartDate">
                                                  <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                  <xsl:text>/</xsl:text>
                                                  <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                  <xsl:text>/</xsl:text>
                                                  <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; ">
                                            <xsl:choose>
                                              <xsl:when test="exists(  /n1:QuotationRequest/cac:RequestForQuotationLine/cac:LineItem/cbc:Note  )">
                                                <xsl:for-each select="cbc:Note">
                                                  <xsl:value-of select="fn:normalize-unicode( . )"/>
                                                </xsl:for-each>
                                              </xsl:when>
                                              <xsl:otherwise>
                                                <xsl:text>&#160;</xsl:text>
                                              </xsl:otherwise>
                                            </xsl:choose>
                                          </td>
                                        </tr>
                                      </xsl:for-each>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </tbody>
                              </table>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </xsl:when>
                    
                    <!-- Activity Grid for HR General Training -->
                    <xsl:when test="$training">

                      <table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
                        <tbody>
                          <tr>
                            <th style="border:1; border-color:black; " bgColor="#999999" colspan="9">
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="eccbc:SummaryDescriptionCode">
                                  <xsl:value-of select="document(&apos;SummaryDescriptionCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </xsl:for-each>
                            </th>
                          </tr>
                          <tr>
                            <td style="border:1; border-color:black; " align="left" colspan="9" width="20%">
                              <xsl:text>Please update your </xsl:text>
                              <xsl:for-each select="n1:QuotationRequest">
                                <xsl:for-each select="cac:OriginatorDocumentReference">
                                  <xsl:for-each select="cbc:DocumentTypeCode">
                                    <xsl:value-of select="document(&apos;erDocumentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:for-each>
                              <xsl:value-of select="concat(&quot; &quot;, /n1:QuotationRequest/cac:OriginatorDocumentReference/cbc:ID)"/>
                              <xsl:text> with the following details:</xsl:text>
                              <br/>
                              <br/>
                              <table style="border:1; border-color:black; " border="1" cellspacing="0" width="100%">
                                <thead>
                                  <tr>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Line</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Activity</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Level</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Lan</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Expected Delivery Date</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Venue</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Premise</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Qty</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Unit Type</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Unit Price</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Total Price</xsl:text>
                                    </th>
                                    <th style="border:1; border-color:black; " align="center" bgcolor="#cccccc">
                                      <xsl:text>Comment</xsl:text>
                                    </th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <xsl:for-each select="n1:QuotationRequest">
                                    <xsl:for-each select="cac:RequestForQuotationLine">
                                      <xsl:sort select="cac:LineItem/cbc:ID" data-type="number" order="ascending"/>
                                      <xsl:for-each select="cac:LineItem">
                                        <tr>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cbc:ID">
                                              <xsl:apply-templates/>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cbc:Description">
                                                <xsl:value-of select="document(&apos;ActivityValueCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;SLVL&apos;)">
                                                    <xsl:apply-templates/>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;LANCD&apos;)">
                                                    <xsl:apply-templates/>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;DEDUDA&apos;)">
                                                    <xsl:choose>
                                                      <xsl:when test="string(.)= string(&apos;TBD&apos;)">
                                                        <xsl:text>TBD</xsl:text>
                                                      </xsl:when>
                                                      <xsl:otherwise>
                                                        <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                        <xsl:text>/</xsl:text>
                                                        <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                        <xsl:text>/</xsl:text>
                                                        <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
                                                      </xsl:otherwise>
                                                    </xsl:choose>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;VENUE&apos;)">
                                                    <xsl:apply-templates/>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;PRMS&apos;)">
                                                    <xsl:apply-templates/>
                                                  </xsl:if>
                                                </xsl:for-each>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cbc:Quantity">
                                              <xsl:value-of select="."/>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cbc:Quantity">
                                              <xsl:for-each select="@unitCode">
                                                <xsl:value-of select="document(&apos;UnitOfMeasureCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                              </xsl:for-each>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <p>
                                              <xsl:value-of select=" concat(fn:normalize-unicode(cac:Price/cbc:PriceAmount) ,&quot; &quot;,cac:Price/cbc:PriceAmount/@currencyID)"/>
                                            </p>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cbc:LineExtensionAmount">
                                              <xsl:value-of select="concat(fn:normalize-unicode(.) ,&quot; &quot;, @currencyID)"/>
                                            </xsl:for-each>
                                          </td>
                                          <td style="border:1; border-color:black; " align="center">
                                            <xsl:for-each select="cac:Item">
                                              <xsl:for-each select="cac:AdditionalItemProperty">
                                                <xsl:for-each select="cbc:Value">
                                                  <xsl:if test="../cbc:Name = string(&apos;DESC&apos;)">
                                                    <xsl:apply-templates/>
                                                  </xsl:if>
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
                  <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                    <tbody>
                      <tr>
                        <td style="border:1; border-color:black; " align="center" bgcolor="#999999">
                          <span style="font-weight:bold; ">
                            <xsl:text>Comments</xsl:text>
                          </span>
                        </td>
                      </tr>
                      <tr>
                        <td style="border:1; border-color:black; " align="left" width="20%">
                          <p>
                            <xsl:for-each select="n1:QuotationRequest">
                              <xsl:for-each select="cbc:Remarks">
                                <xsl:value-of select="fn:normalize-unicode( . )"/>
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
                        <xsl:for-each select="n1:QuotationRequest">
                          <xsl:for-each select="cac:RequestForQuotationLine">
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
                <td valign="top">
                  <table style="border:1; border-color:black; " border="1" cellpadding="3" cellspacing="0" width="100%">
                    <thead>
                      <tr>
                        <th style="border:1; border-color:black; " align="center" bgcolor="#999999" colspan="2">
                          <xsl:text>Attachment(s)</xsl:text>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <xsl:for-each select="n1:QuotationRequest">
                        <xsl:for-each select="cac:AdditionalDocumentReference">
                          <xsl:sort select="cbc:ID" data-type="text" order="ascending"/>
                          <tr>
                            <td style="border:1; border-color:black; ">
                              <p>
                                <xsl:for-each select="cbc:ID">
                                  <xsl:apply-templates/>
                                </xsl:for-each>
                              </p>
                            </td>
                            <td style="border:1; border-color:black; " align="center">
                              <p>
                                <xsl:for-each select="cbc:DocumentType">
                                  <xsl:value-of select="document(&apos;AttachmentTypeCode.xml&apos;)//SimpleCodeList[1]/Row/Value[@ColumnRef=&apos;code&apos;]/SimpleValue[.=  current()]/../../Value[@ColumnRef=&apos;name&apos;]/SimpleValue"/>
                                </xsl:for-each>
                              </p>
                            </td>
                          </tr>
                        </xsl:for-each>
                      </xsl:for-each>
                    </tbody>
                  </table>
                </td>
                <td valign="top">
                  <table style="border:1; border-color:black; " border="1" cellpadding="2" cellspacing="0" width="100%">
                    <thead>
                      <tr>
                        <th style="border:1; border-color:black; " align="center" bgcolor="#999999" colspan="2">
                          <xsl:text>Timeline</xsl:text>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <xsl:for-each select="n1:QuotationRequest">
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
                <td colspan="2" class="style2">
                  <xsl:text>We wish to remind you that this Document does not constitute any firm order until such time as a contractual document, signed by the Customer, has been received by you.</xsl:text>
                </td>
              </tr>
            </tbody>
          </table>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>