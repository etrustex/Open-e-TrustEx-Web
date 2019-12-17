<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
                xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
                xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2"
                xmlns:receiptExt="urn:assets:schema:extensions:receipt"
xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:mdrcp="urn:oasis:names:specification:ubl:schema:xsd:ReceiptAdvice-2" xmlns:n1="ec:schema:xsd:ReceiptAdviceReceived-0.1" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
  <xsl:param name="SV_OutputFormat" select="'HTML'"/>
  <xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
  <xsl:template match="/">
    <!-- ================================================= -->
    <!-- Please update TemplateVersion for each deployment -->
    <!-- ================================================= -->
    <xsl:variable name="TemplateVersion">3.21</xsl:variable>
    <!-- ================================================= -->
    <xsl:variable name="timeFormatPatternMillis" select="string('[H01]:[m01]:[s01].[f01]')"/>
    <xsl:variable name="timeFormatPatternNormal" select="string('[H01]:[m01]')"/>

    <html>
      <head>
        <title>Receipt Advice Human Readable Format</title>
        <style type="text/css">
          body {
          font-family: "Liberation Sans";
          font-size: small;
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

          .nowrap {
            white-space: nowrap;
          }
          
          .lilDesc {
            
          }


          @page {
          size: A4 landscape;
          margin-top: 22%;
          margin-bottom: 5%;

          @top-center {
          content: element(header);
          }

          @bottom-left {
          font-family: "Liberation Sans";
          font-size: medium;
          content: "v<xsl:value-of select="$TemplateVersion" />";
          }

          @bottom-right {
          font-family: "Liberation Sans";
          font-size: medium;
          content: "Page " counter(page) " of " counter(pages);
          }
          }

          .header {
          position: running(header);
          }
        </style>
      </head>
      <body>
        <xsl:for-each select="/n1:ReceiptAdviceReceived/mdrcp:ReceiptAdvice">
          <xsl:variable name="ReceiptTypeVar">
            <xsl:choose>
              <xsl:when test="cac:Shipment/cac:Consignment/cbc:SummaryDescription=&apos;Service&apos;">
                <xsl:value-of select="&apos;SVC&apos;" />
              </xsl:when>
              <xsl:otherwise>
                <xsl:value-of select="&apos;GDS&apos;" />
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>

          <xsl:variable name="ReceiptTitleDescVar">
            <xsl:choose>
              <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                <xsl:value-of select="'Receipt Advice'"/>
              </xsl:when>
              <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                <!-- Previously Goods Receipt -->
                <xsl:value-of select="'Receipt Advice'"/>
              </xsl:when>
            </xsl:choose>
          </xsl:variable>

          <xsl:variable name="eTrefleHideFlagVar">
            <xsl:for-each select="ext:UBLExtensions">
              <xsl:for-each select="ext:UBLExtension">
                <xsl:if test="cbc:ID=4">
                  <xsl:value-of select="ext:ExtensionContent/receiptExt:eTrefleDataFlag"/>
                </xsl:if>
              </xsl:for-each>
            </xsl:for-each>
          </xsl:variable>
          <xsl:variable name="PriceMixedMode">
            <xsl:choose>
              <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                <xsl:value-of select="&apos;QTY&apos;" />
              </xsl:when>
              <xsl:when test="($ReceiptTypeVar=&apos;SVC&apos;)">
                <xsl:variable name="countAmt">
                  <xsl:value-of select="count(cac:ReceiptLine[cac:Item/cac:AdditionalItemProperty[(cbc:Name=&apos;Type&apos;) and (cbc:Value=&apos;AMT&apos;)]])"/>
                </xsl:variable>
                <xsl:variable name="countTotal">
                  <xsl:value-of select="count(cac:ReceiptLine)"/>
                </xsl:variable>
                <xsl:choose>
                  <xsl:when test="$countAmt = $countTotal">
                    <xsl:value-of select="&apos;AMT&apos;" />
                  </xsl:when>
                  <xsl:when test="$countAmt = 0">
                    <xsl:value-of select="&apos;QTY&apos;" />
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="&apos;MIX&apos;" />
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:when>
              <xsl:otherwise>
                <xsl:value-of select="&apos;QTY&apos;" />
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>

          <div class="header">
            <table align="center" width="100%" cellpadding="1" cellspacing="0" border="1" class="noborder">
              <tbody>
                <tr>
                  <td class="cellNoBorder" colspan="3">
                    <xsl:text>&#160;</xsl:text>
                  </td>
                </tr>
                <tr>
                  <th class="cellNoBorder" width="33.33%" align="left">
                    <xsl:for-each select="tokenize(cac:DeliveryCustomerParty/cac:Party/cac:PartyName/cbc:Name,'#')">
                      <xsl:value-of select="."/>
                      <br/>
                    </xsl:for-each>
                  </th>
                  <td class="cellNoBorder" width="33.33%" align="center">
                    <b>
                      <xsl:value-of select="$ReceiptTitleDescVar"/>
                    </b>
                    <br/>
                    <xsl:value-of select="cbc:ID"/>
                  </td>
                  <td class="cellNoBorder" width="33.33%" align="right">
                    <b>Rendering date</b>
                    <br/>
                    <span class="nowrap">
                      <xsl:value-of select="format-dateTime(current-dateTime(), '[D01]/[M01]/[Y0001] at [H01]:[m01]')"/>
                    </span>
                  </td>
                </tr>
                <tr>
                  <td class="cellNoBorder" colspan="3">
                    <xsl:text>&#160;</xsl:text>
                  </td>
                </tr>
              </tbody>
            </table>

            <table align="center" width="100%" cellpadding="3" cellspacing="0" border="1" class="border">
              <tbody>
                <tr>
                  <th class="cellNoBorder" width="11%" align="right">Reception Date:</th>
                  <td class="cellNoBorder" width="19%">
                    <span style="white-space: nowrap">
                      <xsl:value-of select="substring(string((cac:ReceiptLine/cbc:ReceivedDate)[1]), 9, 2)"/>
                      <xsl:text>/</xsl:text>
                      <xsl:value-of select="substring(string((cac:ReceiptLine/cbc:ReceivedDate)[1]), 6, 2)"/>
                      <xsl:text>/</xsl:text>
                      <xsl:value-of select="substring(string((cac:ReceiptLine/cbc:ReceivedDate)[1]), 1, 4)"/>
                    </span>
                  </td>
                  <th class="cellNoBorder" width="11%" align="right">Responsible Center:</th>
                  <td class="cellNoBorder" width="15%">
                    <xsl:value-of select="cac:BuyerCustomerParty/cac:Party/cac:PartyName/cbc:Name"/>
                  </td>
                  <th class="cellNoBorder" width="10%" align="right">Supplier:</th>
                  <td class="cellNoBorder" width="24%">
                    <xsl:value-of select="cac:DespatchSupplierParty/cac:Party/cac:PartyIdentification/cbc:ID"/> - <xsl:value-of select="cac:DespatchSupplierParty/cac:Party/cac:PartyName/cbc:Name"/>
                  </td>
                </tr>
                <tr>
                  <th class="cellNoBorder" align="right">Order/Internal ref.:</th>
                  <td class="cellNoBorder">
                    <xsl:value-of select="cac:OrderReference/cbc:ID"/> /
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="cbc:DocumentType=&apos;Order Internal Reference&apos;">
                        <xsl:value-of select="cbc:ID"/>
                      </xsl:if>
                    </xsl:for-each>
                  </td>
                  <th class="cellNoBorder" align="right">Assignment:</th>
                  <td class="cellNoBorder">
                    <xsl:value-of select="cac:Shipment/cac:Consignment/cac:FinalDeliveryParty/cac:PartyName/cbc:Name"/>
                  </td>
                  <th class="cellNoBorder" align="right">
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="cbc:DocumentType=&apos;Procedure&apos;">Procedure:</xsl:if>
                    </xsl:for-each>
                  </th>
                  <td class="cellNoBorder">
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="(cbc:DocumentType=&apos;Procedure&apos;)">
                        <xsl:value-of select="cbc:ID"/>
                      </xsl:if>
                    </xsl:for-each>
                  </td>
                </tr>

                <tr>
                  <xsl:choose>
                    <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                      <th class="cellNoBorder" align="right">
                        Service Provision<br/>Start date:
                      </th>
                      <td class="cellNoBorder">
                        <span style="white-space: nowrap">
                          <xsl:value-of select="substring(string((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartDate)[1]), 9, 2)"/>
                          <xsl:text>/</xsl:text>
                          <xsl:value-of select="substring(string((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartDate)[1]), 6, 2)"/>
                          <xsl:text>/</xsl:text>
                          <xsl:value-of select="substring(string((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartDate)[1]), 1, 4)"/>

                          <!-- TIME -->
                          <xsl:if test="(cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartTime)[1] != &apos;&apos;">
                            <xsl:variable name="formattedTimeMillis" select="format-time((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartTime)[1], $timeFormatPatternMillis)" />
                            <xsl:variable name="formattedTimeNormal" select="format-time((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:StartTime)[1], $timeFormatPatternNormal)" />

                            <xsl:if test="$formattedTimeMillis != &apos;00:00:00.00&apos;">
                              <xsl:text>&#160;</xsl:text>
                              <xsl:value-of select="$formattedTimeNormal"/>
                            </xsl:if>
                          </xsl:if>
                        </span>
                      </td>
                      <th class="cellNoBorder" align="right">
                        Service Provision<br/>End  date:
                      </th>
                      <td class="cellNoBorder">
                        <span style="white-space: nowrap">
                          <xsl:value-of select="substring(string((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndDate)[1]), 9, 2)"/>
                          <xsl:text>/</xsl:text>
                          <xsl:value-of select="substring(string((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndDate)[1]), 6, 2)"/>
                          <xsl:text>/</xsl:text>
                          <xsl:value-of select="substring(string((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndDate)[1]), 1, 4)"/>

                          <!-- TIME -->
                          <xsl:if test="(cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndTime)[1] != &apos;&apos;">
                            <xsl:variable name="formattedTimeMillis" select="format-time((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndTime)[1], $timeFormatPatternMillis)" />
                            <xsl:variable name="formattedTimeNormal" select="format-time((cac:ReceiptLine/cac:Item/cac:AdditionalItemProperty/cac:UsabilityPeriod/cbc:EndTime)[1], $timeFormatPatternNormal)" />
                            <xsl:if test="$formattedTimeMillis != &apos;00:00:00.00&apos;">
                              <xsl:text>&#160;</xsl:text>
                              <xsl:value-of select="$formattedTimeNormal"/>
                            </xsl:if>
                          </xsl:if>
                        </span>
                      </td>
                    </xsl:when>
                    <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                      <th class="cellNoBorder" align="right">Work:</th>
                      <td class="cellNoBorder">
                        <xsl:text>&#160;</xsl:text>
                      </td>
                      <th class="cellNoBorder" align="right">Stock Ref:</th>
                      <td class="cellNoBorder">
                        <xsl:text>&#160;</xsl:text>
                      </td>
                    </xsl:when>
                  </xsl:choose>

                  <th class="cellNoBorder" align="right">
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="cbc:DocumentTypeCode=&apos;149&apos;">Contract No (Framework Contract/Direct Contract):</xsl:if>
                      <xsl:if test="cbc:DocumentTypeCode=&apos;220&apos;">Contract No (Framework Contract/Direct Contract):</xsl:if>
                    </xsl:for-each>
                  </th>
                  <td class="cellNoBorder">
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="(cbc:DocumentTypeCode=&apos;149&apos;) or (cbc:DocumentTypeCode=&apos;220&apos;)">
                        <xsl:value-of select="cbc:ID"/>
                      </xsl:if>
                    </xsl:for-each>
                  </td>
                </tr>

                <tr>
                  <th class="cellNoBorder" align="right">
                    Dispatch Advice ref:
                  </th>
                  <td class="cellNoBorder">
                    <xsl:choose>
                      <xsl:when test="cac:DespatchDocumentReference/cbc:ID!=&apos;&apos;">
                        <xsl:value-of select="cac:DespatchDocumentReference/cbc:ID"/>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:text>N/A</xsl:text>
                      </xsl:otherwise>
                    </xsl:choose>
                  </td>
                  <th class="cellNoBorder" align="right">
                    Dispatch Advice Date:
                  </th>
                  <td class="cellNoBorder">
                    <span style="white-space: nowrap">
                      <xsl:choose>
                        <xsl:when test="cac:DespatchDocumentReference/cbc:IssueDate != &apos;&apos;">
                          <xsl:value-of select="substring(string(cac:DespatchDocumentReference/cbc:IssueDate), 9, 2)"/>
                          <xsl:text>/</xsl:text>
                          <xsl:value-of select="substring(string(cac:DespatchDocumentReference/cbc:IssueDate), 6, 2)"/>
                          <xsl:text>/</xsl:text>
                          <xsl:value-of select="substring(string(cac:DespatchDocumentReference/cbc:IssueDate), 1, 4)"/>

                          <!-- TIME -->
                          <!-- TODO:  ABACASSETS-18574 Replace the Extension 6 with this when the 
                                    cac:DespatchDocumentReference/cbc:IssueTime is part of UBL -->
                          <!--
                        <xsl:if test="cac:DespatchDocumentReference/cbc:IssueTime  != &apos;&apos;">
                          <xsl:variable name="formattedTimeMillis" select="format-time(cac:DespatchDocumentReference/cbc:IssueTime , $timeFormatPatternMillis)" />
                          <xsl:variable name="formattedTimeNormal" select="format-time(cac:DespatchDocumentReference/cbc:IssueTime , $timeFormatPatternNormal)" />

                          <xsl:if test="$formattedTimeMillis != &apos;00:00:00.00&apos;">
                            <xsl:text>&#160;</xsl:text>
                            <xsl:value-of select="$formattedTimeNormal"/>
                          </xsl:if>
                        </xsl:if>
                        -->

                          <xsl:for-each select="ext:UBLExtensions">
                            <xsl:for-each select="ext:UBLExtension">
                              <xsl:if test="cbc:ID=6">
                                <xsl:text>&#160;</xsl:text>
                                <xsl:value-of select="ext:ExtensionContent/receiptExt:IssueTime"/>
                              </xsl:if>
                            </xsl:for-each>
                          </xsl:for-each>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:text>N/A</xsl:text>
                        </xsl:otherwise>
                      </xsl:choose>
                    </span>
                  </td>
                  <th class="cellNoBorder" align="right">
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="cbc:DocumentType=&apos;Specific Contract&apos;">
                        PO/SC/OF:
                      </xsl:if>

                      <xsl:if test="cbc:DocumentType=&apos;Purchase Order&apos;">
                        PO/SC/OF:
                      </xsl:if>
                    </xsl:for-each>
                  </th>
                  <td class="cellNoBorder">
                    <xsl:for-each select="cac:AdditionalDocumentReference">
                      <xsl:if test="cbc:DocumentType=&apos;Specific Contract&apos;">
                        <xsl:value-of select="cbc:ID"/>
                      </xsl:if>
                      <xsl:if test="cbc:DocumentType=&apos;Purchase Order&apos;">
                        <xsl:value-of select="cbc:ID"/>
                      </xsl:if>
                    </xsl:for-each>
                  </td>
                </tr>

                <!-- TOTAL AMOUNT To be Invoiced -->
                <xsl:if test="$ReceiptTypeVar=&apos;SVC&apos;">
                <tr>
                  <th class="cellNoBorder">
                    <xsl:text>&#160;</xsl:text>
                  </th>
                  <td class="cellNoBorder">
                    <xsl:text>&#160;</xsl:text>
                  </td>
                   
                  <!-- colspan 3 -->
                  <th class="cellNoBorder" align="right" colspan="3">
                    Total Amount To Be Invoiced:
                  </th>
                  <td class="cellNoBorder nowrap">
                    <xsl:for-each select="ext:UBLExtensions">
                      <xsl:for-each select="ext:UBLExtension">
                        <xsl:if test="cbc:ID=5">
                          <xsl:call-template name="formatNumber">
                            <xsl:with-param name="value" select="ext:ExtensionContent/receiptExt:DeliveryTems/receiptExt:Amount"/>
                          </xsl:call-template>
                        </xsl:if>
                      </xsl:for-each>
                    </xsl:for-each>
                    <xsl:text>&#160;</xsl:text>
                    <xsl:value-of select="cac:Shipment/cbc:FreeOnBoardValueAmount/@currencyID"/>
                  </td>
                </tr>
                </xsl:if>
                
              </tbody>
            </table>
          </div>

          <table align="center" width="100%" cellpadding="3" cellspacing="0" border="1" >
            <thead>
              <tr>
                <td class="cellNoBorder" colspan="12">
                  <xsl:text>&#160;</xsl:text>
                </td>
              </tr>
              <tr valign="top">
                <xsl:choose>
                  <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <!-- GOODS 1/2 -->
                    <th class="cellNoBorder" width="8%">Identifier</th>
                    <th class="cellNoBorder" width="4%">Order line</th>
                    <th class="cellNoBorder" width="4%">Receipt line</th>
                    <th class="cellNoBorder lilDesc" width="28%">Description</th>
                    <th class="cellNoBorder" width="4%">Main Asset</th>
                    <th class="cellNoBorder" width="7%">Ordered</th>
                    <th class="cellNoBorder" width="7%">Received Quantity</th>
                    <th class="cellNoBorder" width="8%">Consumed qty in GR seq</th>
                    <th class="cellNoBorder" width="9%">Asset Class</th>
                    <th class="cellNoBorder" width="8%">Unit Price</th>

                    <!-- Not present for Goods
                    <th class="cellNoBorder" width="4%">ToDeadline</th>
                    <th class="cellNoBorder" width="4%">Evaluation Mark</th>
                    <th class="cellNoBorder" width="4%">Received Date</th>
                    -->

                    <th class="cellNoBorder" width="9%">Request</th>
                    <th class="cellNoBorder" width="4%">Sticker</th>
                  </xsl:when>
                  <!-- Labels for SVC depending on MUX/AMT/QTY    [MIX] -->
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos; and $PriceMixedMode=&apos;MIX&apos;">
                    <!-- SERVICES 1/4 -->
                    <th class="cellNoBorder" width="1%"></th>
                    <th class="cellNoBorder" align="center" width="4%">Order line</th>
                    <th class="cellNoBorder" align="center" width="4%">Receipt line</th>
                    <th class="cellNoBorder lilDesc" align="center">Description</th>
                    <!--
                    <xsl:choose>
                      <xsl:when test="$eTrefleHideFlagVar=&apos;N&apos;">
                        <th class="cellNoBorder" align="center" width="29%">Description</th>
                      </xsl:when>
                      <xsl:otherwise>
                        <th class="cellNoBorder" align="center" width="41%">Description</th>
                      </xsl:otherwise>
                    </xsl:choose>
                    -->
                    <!-- missing Main Asset -->
                    <th class="cellNoBorder" align="center" width="8%">
                      Ordered <br/>qty/amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Received <br/>qty/amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Total received <br/>amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Consumed received<br/>qty/amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Remaining <br/>qty/amount
                    </th>
                    <!-- missing Asset Class -->
                    <th class="cellNoBorder" align="center" width="8%">Unit Price</th>

                    <!-- eTrefle special -->
                    <xsl:if test="$eTrefleHideFlagVar=&apos;N&apos;">
                      <th class="cellNoBorder" align="center" width="4%">To deadline</th>
                      <th class="cellNoBorder" align="center" width="4%">Evaluation mark</th>
                      <th class="cellNoBorder" align="center" width="4%">Received date</th>
                    </xsl:if>

                    <th class="cellNoBorder" width="1%"></th>
                    <!-- missing Sticker -->
                  </xsl:when>
                  <!-- Labels for SVC depending on MUX/AMT/QTY    [AMT] -->
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos; and $PriceMixedMode=&apos;AMT&apos;">
                    <!-- SERVICES 2/4 -->
                    <th class="cellNoBorder" width="1%"></th>
                    <th class="cellNoBorder" align="center" width="4%">Order line</th>
                    <th class="cellNoBorder" align="center" width="4%">Receipt line</th>
                    <th class="cellNoBorder lilDesc" align="center">Description</th>
                    
                    <!-- missing Main Asset -->
                    <th class="cellNoBorder" align="center" width="8%">
                      Ordered <br/>amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Received <br/>amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Total received <br/>amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Consumed received<br/>amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Remaining <br/>amount
                    </th>
                    <!-- missing Asset Class -->
                    <!-- Unit Price -->
                    <th class="cellNoBorder" align="center" width="1%"></th>

                    <!-- eTrefle special -->
                    <xsl:if test="$eTrefleHideFlagVar=&apos;N&apos;">
                      <th class="cellNoBorder" align="center" width="4%">To deadline</th>
                      <th class="cellNoBorder" align="center" width="4%">Evaluation mark</th>
                      <th class="cellNoBorder" align="center" width="4%">Received date</th>
                    </xsl:if>

                    <th class="cellNoBorder" width="1%"></th>
                    <!-- missing Sticker -->
                  </xsl:when>
                  <!-- Labels for SVC depending on MUX/AMT/QTY    [QTY] -->
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos; and $PriceMixedMode=&apos;QTY&apos;">
                    <!-- SERVICES 3/4 -->
                    <th class="cellNoBorder" width="1%"></th>
                    <th class="cellNoBorder" align="center" width="4%">Order line</th>
                    <th class="cellNoBorder" align="center" width="4%">Receipt line</th>

                    <th class="cellNoBorder lilDesc" align="center">Description</th>
                    
                    <!-- missing Main Asset -->
                    <th class="cellNoBorder" align="center" width="8%">
                      Ordered <br/>quantity
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Received <br/>quantity
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Total received <br/>amount
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Consumed<br/>received quantity
                    </th>
                    <th class="cellNoBorder" align="center" width="8%">
                      Remaining <br/>quantity
                    </th>
                    <!-- missing Asset Class -->
                    <th class="cellNoBorder" align="center" width="8%">Unit Price</th>

                    <!-- eTrefle special -->
                    <xsl:if test="$eTrefleHideFlagVar=&apos;N&apos;">
                    <th class="cellNoBorder" align="center" width="4%">To deadline</th>
                    <th class="cellNoBorder" align="center" width="4%">Evaluation mark</th>
                    <th class="cellNoBorder" align="center" width="4%">Received date</th>
                    </xsl:if>

                    <th class="cellNoBorder" width="1%"></th>
                    <!-- missing Sticker -->
                  </xsl:when>
                </xsl:choose>
              </tr>
              <tr>
                <xsl:choose>
                  <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <!-- GOODS 2/2 -->
                    <th class="cellNoBorder" width="8%"></th>
                    <th class="cellNoBorder" width="4%"></th>
                    <th class="cellNoBorder" width="4%"></th>
                    <th class="cellNoBorder" width="28%"></th>
                    <th class="cellNoBorder" width="4%"></th>
                    <th class="cellNoBorder" width="7%">
                      <!--Ordered-->
                    </th>
                    <th class="cellNoBorder" width="7%">
                      <!--Received-->
                    </th>
                    <th class="cellNoBorder" width="8%"></th>
                    <th class="cellNoBorder" width="9%"></th>
                    <th class="cellNoBorder" width="8%"></th>
                    <th class="cellNoBorder" width="9%"></th>
                    <th class="cellNoBorder" width="4%"></th>
                  </xsl:when>
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <!-- SERVICES 4/4 -->
                    <xsl:choose>
                      <xsl:when test="$eTrefleHideFlagVar=&apos;N&apos;">
                        <!-- For eTrefle the 3: ToDeadline, Evaluation Mark, Received Date-->
                        <th class="cellNoBorder" height="1px" colspan="14"></th>
                      </xsl:when>
                      <xsl:otherwise>
                        <!-- Default without penalty -->
                        <th class="cellNoBorder" height="1px" colspan="11"></th>
                      </xsl:otherwise>
                    </xsl:choose>
                  </xsl:when>
                </xsl:choose>
              </tr>
            </thead>
            <tbody>
              <xsl:for-each select="cac:ReceiptLine">
                <xsl:variable name="amountFlag">
                  <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                    <xsl:if test="(cbc:Name=&apos;Type&apos;) and (cbc:Value=&apos;AMT&apos;) and ($ReceiptTypeVar=&apos;SVC&apos;)">
                      <xsl:value-of select="&apos;AMT&apos;" />
                    </xsl:if>
                  </xsl:for-each>
                </xsl:variable>

                <xsl:variable name="penaltyFlag">
                  <xsl:choose>
                    <xsl:when test="cac:Shipment/cac:GoodsItem/cbc:ValueAmount &lt; 0">
                      <xsl:value-of select="&apos;true&apos;" />
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:value-of select="&apos;false&apos;" />
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:variable>
                <tr valign="top">
                  <td class="cellTopBorder" align="center">
                    <xsl:if test="$ReceiptTypeVar=&apos;GDS&apos;">
                      <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                        <xsl:if test="cbc:Name=&apos;Asset Flag&apos;">
                          <xsl:if test="cbc:Value=&apos;true&apos;">Asset</xsl:if>
                          <xsl:if test="cbc:Value!=&apos;true&apos;">
                            <xsl:text>&#160;</xsl:text>
                          </xsl:if>
                        </xsl:if>
                      </xsl:for-each>
                    </xsl:if>
                  </td>
                  <td class="cellTopBorder" align="center">
                    <xsl:value-of select="cac:OrderLineReference/cbc:LineID"/>
                  </td>
                  <td class="cellTopBorder" align="center">
                    <xsl:value-of select="cbc:ID"/>
                  </td>
                  <td class="cellTopBorder lilDesc">
                    <xsl:value-of select="cac:Item/cbc:Name"/>
                  </td>
                  <xsl:if test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <td class="cellTopBorder" align="center">
                      <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                        <xsl:if test="cbc:Name=&apos;Main Asset&apos;">
                          <xsl:value-of select="cbc:Value"/>
                        </xsl:if>
                      </xsl:for-each>
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:if>

                  <td class="cellTopBorder nowrap" align="center">
                    <xsl:if test="$penaltyFlag=&apos;false&apos;">
                      <xsl:call-template name="formatNumber">
                        <xsl:with-param name="value" select="cac:Shipment/cbc:Information"/>
                      </xsl:call-template>
                      <!-- Ordered Qty/Amount -->
                      <xsl:choose>
                        <xsl:when test="$amountFlag=&apos;AMT&apos;">
                          <xsl:text> </xsl:text>
                          <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:text> </xsl:text>
                          <xsl:call-template name="retrieveCodeTemplate">
                            <xsl:with-param name="unitCode" select="cbc:ReceivedQuantity/@unitCode"/>
                          </xsl:call-template>
                        </xsl:otherwise>
                      </xsl:choose>
                    </xsl:if>
                  </td>

                  <td class="cellTopBorder nowrap" align="center">
                    <!--Received-->
                    <xsl:choose>
                      <xsl:when test="$amountFlag=&apos;AMT&apos; and $penaltyFlag=&apos;false&apos;">
                        <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                          <xsl:if test="cbc:Name=&apos;Unit Price&apos;">
                            <xsl:call-template name="formatNumber">
                              <xsl:with-param name="value" select="cbc:Value"/>
                            </xsl:call-template>
                            <xsl:text> </xsl:text>
                          </xsl:if>
                        </xsl:for-each>
                        <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                      </xsl:when>
                      <xsl:when test="$amountFlag=&apos;AMT&apos; and $penaltyFlag=&apos;true&apos;">
                        <!-- For penalty shows the ValueAmount instead of unitPrice for Amount based-->
                        <xsl:for-each select="cac:Shipment/cac:GoodsItem">
                          <xsl:call-template name="formatNumber">
                            <xsl:with-param name="value" select="cbc:ValueAmount"/>
                          </xsl:call-template>
                          <xsl:text> </xsl:text>
                        </xsl:for-each>
                        <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:call-template name="formatNumber">
                          <xsl:with-param name="value" select="cbc:ReceivedQuantity"/>
                        </xsl:call-template>
                        <xsl:text> </xsl:text>
                        <xsl:call-template name="retrieveCodeTemplate">
                          <xsl:with-param name="unitCode" select="cbc:ReceivedQuantity/@unitCode"/>
                        </xsl:call-template>
                      </xsl:otherwise>
                    </xsl:choose>
                  </td>
                  <xsl:if test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <td class="cellTopBorder nowrap" align="center">
                      <xsl:for-each select="cac:Shipment/cac:GoodsItem">
                        <xsl:call-template name="formatNumber">
                          <xsl:with-param name="value" select="cbc:ValueAmount"/>
                        </xsl:call-template>
                        <xsl:text> </xsl:text>
                      </xsl:for-each>
                      <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                    </td>
                  </xsl:if>
                  <td class="cellTopBorder nowrap" align="center">
                    <xsl:if test="$penaltyFlag=&apos;false&apos;">
                      <xsl:if test="$ReceiptTypeVar=&apos;GDS&apos;">
                        <xsl:value-of select="cac:Shipment/cac:Consignment/cbc:SummaryDescription"/>
                      </xsl:if>
                      <xsl:if test="$ReceiptTypeVar=&apos;SVC&apos;">
                        <xsl:call-template name="formatNumber">
                          <xsl:with-param name="value" select="cac:Shipment/cac:Consignment/cbc:SummaryDescription"/>
                        </xsl:call-template>
                        <xsl:text> </xsl:text>
                      </xsl:if>
                      <xsl:choose>
                        <xsl:when test="$amountFlag=&apos;AMT&apos;">
                          <xsl:text> </xsl:text>
                          <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:text> </xsl:text>
                          <xsl:call-template name="retrieveCodeTemplate">
                            <xsl:with-param name="unitCode" select="cbc:ReceivedQuantity/@unitCode"/>
                          </xsl:call-template>
                        </xsl:otherwise>
                      </xsl:choose>
                    </xsl:if>
                  </td>
                  <xsl:if test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <td class="cellTopBorder nowrap" align="center">
                      <xsl:if test="$penaltyFlag=&apos;false&apos;">
                        <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                          <xsl:if test="cbc:Name=&apos;RemainingValue&apos;">
                            <xsl:call-template name="formatNumber">
                              <xsl:with-param name="value" select="cbc:Value"/>
                            </xsl:call-template>
                            <xsl:text> </xsl:text>
                          </xsl:if>
                        </xsl:for-each>
                        <xsl:choose>
                          <xsl:when test="$amountFlag=&apos;AMT&apos;">
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                          </xsl:when>
                          <xsl:otherwise>
                            <xsl:text> </xsl:text>
                            <xsl:call-template name="retrieveCodeTemplate">
                              <xsl:with-param name="unitCode" select="cbc:ReceivedQuantity/@unitCode"/>
                            </xsl:call-template>
                          </xsl:otherwise>
                        </xsl:choose>
                      </xsl:if>
                    </td>
                  </xsl:if>

                  <xsl:if test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <td class="cellTopBorder" align="center">
                      <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                        <xsl:if test="cbc:Name=&apos;Asset Class&apos;">
                          <xsl:value-of select="cbc:Value"/>
                        </xsl:if>
                      </xsl:for-each>
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:if>
                  <td class="cellTopBorder nowrap" align="center" nowrap="nowrap">
                    <xsl:choose>
                      <xsl:when test="$amountFlag=&apos;AMT&apos;">
                        <xsl:choose>
                          <xsl:when test="$PriceMixedMode=&apos;MIX&apos;">
                            <xsl:text>N/A</xsl:text>
                          </xsl:when>
                          <xsl:otherwise>
                            <!-- Print nothing on amount for the unit price -->
                            <xsl:text>&#160;</xsl:text>
                          </xsl:otherwise>
                        </xsl:choose>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                          <xsl:if test="cbc:Name=&apos;Unit Price&apos;">
                            <xsl:call-template name="formatNumber">
                              <xsl:with-param name="value" select="cbc:Value"/>
                            </xsl:call-template>
                            <xsl:text> </xsl:text>
                          </xsl:if>
                        </xsl:for-each>
                        <xsl:value-of select="cac:Shipment/cac:GoodsItem/cbc:ValueAmount/@currencyID"/>
                      </xsl:otherwise>
                    </xsl:choose>
                  </td>
                  <xsl:if test="$ReceiptTypeVar=&apos;SVC&apos; and $eTrefleHideFlagVar=&apos;N&apos;">
                    <xsl:variable name="currentId" select="cbc:ID"/>                    
                    <td class="cellTopBorder" align="center">
                      <!--ReceiptAdvice/UBLExtensions/UBLExtension/ReceiptLine/ToDeadline-->
                      <xsl:if test="$penaltyFlag=&apos;false&apos;">
                        <xsl:for-each select="/n1:ReceiptAdviceReceived/mdrcp:ReceiptAdvice">
                          <xsl:for-each select="ext:UBLExtensions">
                            <xsl:for-each select="ext:UBLExtension">
                              <xsl:if test="cbc:ID=3">
                                <xsl:for-each select="ext:ExtensionContent/receiptExt:ReceiptLines">
                                  <xsl:for-each select="receiptExt:ReceiptLine">
                                    <xsl:if test="receiptExt:ID=$currentId">
                                      <xsl:value-of select="receiptExt:MarkToDeadLine"/>
                                    </xsl:if>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:if>
                            </xsl:for-each>
                          </xsl:for-each>
                        </xsl:for-each>
                      </xsl:if>
                    </td>
                    <td class="cellTopBorder" align="center">
                      <!--ReceiptAdvice/UBLExtensions/UBLExtension/ReceiptLine/Evaluation Mark-->
                      <xsl:if test="$penaltyFlag=&apos;false&apos;">
                        <xsl:for-each select="/n1:ReceiptAdviceReceived/mdrcp:ReceiptAdvice">
                          <xsl:for-each select="ext:UBLExtensions">
                            <xsl:for-each select="ext:UBLExtension">
                              <xsl:if test="cbc:ID=3">
                                <xsl:for-each select="ext:ExtensionContent/receiptExt:ReceiptLines">
                                  <xsl:for-each select="receiptExt:ReceiptLine">
                                    <xsl:if test="receiptExt:ID=$currentId">
                                      <xsl:value-of select="receiptExt:EvaluationMark"/>
                                    </xsl:if>
                                  </xsl:for-each>
                                </xsl:for-each>
                              </xsl:if>
                            </xsl:for-each>
                          </xsl:for-each>
                        </xsl:for-each>
                      </xsl:if>
                    </td>
                    <td class="cellTopBorder" align="center">
                      <!--ReceiptAdvice/UBLExtensions/UBLExtension/ReceiptLine/ReceivedDate-->
                      <span style="white-space: nowrap">
                        <xsl:if test="$penaltyFlag=&apos;false&apos;">
                          <xsl:for-each select="/n1:ReceiptAdviceReceived/mdrcp:ReceiptAdvice">
                            <xsl:for-each select="ext:UBLExtensions">
                              <xsl:for-each select="ext:UBLExtension">
                                <xsl:if test="cbc:ID=3">
                                  <xsl:for-each select="ext:ExtensionContent/receiptExt:ReceiptLines">
                                    <xsl:for-each select="receiptExt:ReceiptLine">
                                      <xsl:if test="receiptExt:ID=$currentId">
                                        <span style="white-space: nowrap">
                                          <xsl:if test="string(receiptExt:ReceivedDate) != ''">
                                            <xsl:value-of select="substring(string(receiptExt:ReceivedDate), 9, 2)"/>
                                            <xsl:text>/</xsl:text>
                                            <xsl:value-of select="substring(string(receiptExt:ReceivedDate), 6, 2)"/>
                                            <xsl:text>/</xsl:text>
                                            <xsl:value-of select="substring(string(receiptExt:ReceivedDate), 1, 4)"/>
                                          </xsl:if>
                                        </span>
                                      </xsl:if>
                                    </xsl:for-each>
                                  </xsl:for-each>
                                </xsl:if>
                              </xsl:for-each>
                            </xsl:for-each>
                          </xsl:for-each>
                        </xsl:if>
                      </span>
                    </td>
                  </xsl:if>
                  <td class="cellTopBorder" align="center">
                    <xsl:if test="cac:DocumentReference/cbc:DocumentType=&apos;Request&apos;">
                      <xsl:value-of select="cac:DocumentReference/cbc:ID"/>
                    </xsl:if>
                    <xsl:text>&#160;</xsl:text>
                  </td>
                  <xsl:if test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <td class="cellTopBorder" align="center">
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:if>
                </tr>
                <tr>
                  <td class="cellNoBorder" colspan="12">
                    <xsl:text>&#160;</xsl:text>
                  </td>
                </tr>


                <xsl:choose>
                  <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <tr valign="top">
                      <td class="cellNoBorder" colspan="7" align="left">
                        <table width="100%" cellpadding="1" cellspacing="0" border="0">
                          <tbody>
                            <xsl:for-each select="cac:Item/cac:ItemInstance">
                              <tr>
                                <td width="40%">
                                  <xsl:value-of select="cbc:RegistrationID"/>
                                </td>
                                <td width="30%">
                                  <b>Key: </b>
                                  <xsl:value-of select="cbc:ProductTraceID"/>
                                </td>
                                <td width="30%">
                                  <b>Sn: </b>
                                  <xsl:value-of select="cbc:SerialID"/>
                                </td>
                              </tr>
                            </xsl:for-each>
                          </tbody>
                        </table>
                      </td>
                      <td class="cellNoBorder" colspan="3">
                        <xsl:value-of select="a"/>
                        <xsl:if test="cac:Item/cbc:Keyword!=&apos;&apos;">
                          <xsl:value-of select="cac:Item/cbc:Keyword"/> - <xsl:value-of select="cac:Item/cac:BuyersItemIdentification/cbc:ID"/> - <xsl:value-of select="cac:Item/cbc:Description"/>
                        </xsl:if>
                      </td>
                      <td class="cellNoBorder" colspan="2">
                        <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                          <xsl:if test="cbc:Name=&apos;Installation Date&apos;">
                            <b>Installation Date: </b>
                            <span style="white-space: nowrap">
                              <xsl:value-of select="cbc:Value"/>
                            </span>
                          </xsl:if>
                        </xsl:for-each>
                      </td>
                    </tr>
                  </xsl:when>
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <tr>
                      <td class="cellNoBorder" colspan="6" align="left">
                        <table width="100%" cellpadding="1" cellspacing="0" border="0">
                          <tbody>
                            <xsl:for-each select="cac:Item/cac:ItemInstance">
                              <tr>
                                <td width="40%">
                                  <xsl:value-of select="cbc:RegistrationID"/>
                                </td>
                                <td width="30%">
                                  <b>Key: </b>
                                  <xsl:value-of select="cbc:ProductTraceID"/>
                                </td>
                                <td width="30%">
                                  <b>Sn: </b>
                                  <xsl:value-of select="cbc:SerialID"/>
                                </td>
                              </tr>
                            </xsl:for-each>
                          </tbody>
                        </table>
                      </td>
                      <td class="cellNoBorder" colspan="2">
                        <xsl:value-of select="a"/>
                        <xsl:if test="cac:Item/cbc:Keyword!=&apos;&apos;">
                          <xsl:value-of select="cac:Item/cbc:Keyword"/> - <xsl:value-of select="cac:Item/cac:BuyersItemIdentification/cbc:ID"/> - <xsl:value-of select="cac:Item/cbc:Description"/>
                        </xsl:if>
                      </td>
                      <td class="cellNoBorder" colspan="1">
                        <xsl:for-each select="cac:Item/cac:AdditionalItemProperty">
                          <xsl:if test="cbc:Name=&apos;Installation Date&apos;">
                            <b>Installation Date: </b>
                            <span style="white-space: nowrap">
                              <xsl:value-of select="cbc:Value"/>
                            </span>
                          </xsl:if>
                        </xsl:for-each>
                      </td>
                    </tr>
                  </xsl:when>
                </xsl:choose>

                <tr>
                  <td class="cellNoBorder" colspan="12">
                    <xsl:text>&#160;</xsl:text>
                  </td>
                </tr>
              </xsl:for-each>
            </tbody>
          </table>

          <table align="center" width="100%" cellpadding="3" cellspacing="0" border="1" class="border">
            <tbody>
              <xsl:variable name="Signatures" select="count(cac:Signature)"/>
              <tr>
                <xsl:choose>
                  <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <td class="cellNoBorder" width="13%">
                      <b>Destination:</b>
                    </td>
                    <td class="cellNoBorder" width="27%">
                      <xsl:value-of select="cac:Shipment/cac:Delivery/cac:DeliveryLocation/cbc:Description"/>
                    </td>
                    <td class="cellLeftRightBorder" width="20%" rowspan="5" align="center" style="vertical-align:text-top;">
                      <b>For reception:</b>
                      <br/>
                      <br/>
                      <xsl:for-each select="(cac:Signature/cac:SignatoryParty)[$Signatures]">
                        <xsl:value-of select="cac:Person/cbc:Title"/>
                        <br/>
                        <xsl:value-of select="cac:Person/cbc:FamilyName"/>
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="cac:Person/cbc:FirstName"/>
                        <br/>
                        <br/>
                        <xsl:value-of select="cac:PartyName/cbc:Name"/>
                      </xsl:for-each>
                    </td>
                  </xsl:when>
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <td class="cellNoBorder" width="13%">
                      <b>Receiving service remarks:</b>
                    </td>
                    <td class="cellNoBorder" width="27%" rowspan="2">
                      <xsl:choose>
                        <xsl:when test="contains((cbc:Note)[2], &apos;PARTIAL_DELIVERY&apos;)">
                          <xsl:value-of select="replace((cbc:Note)[2], &apos;PARTIAL_DELIVERY&apos;, &apos;Partial Delivery&apos;)"/>
                        </xsl:when>
                        <xsl:when test="contains((cbc:Note)[2], &apos;ORDER_COMPLETED&apos;)">
                          <xsl:value-of select="replace((cbc:Note)[2], &apos;ORDER_COMPLETED&apos;, &apos;Order Completed&apos;)"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:value-of select="(cbc:Note)[2]"/>
                        </xsl:otherwise>
                      </xsl:choose>                      
                      <xsl:text>&#160;</xsl:text>
                    </td>
                    <td class="cellLeftRightBorder" width="20%" rowspan="5" align="center" style="vertical-align:text-top;">

                      <xsl:choose>
                        <xsl:when test="cac:DespatchDocumentReference/cbc:ID!=&apos;&apos;">
                          <b>Created from dispatch advice:</b>
                          <br/>
                          <br/>
                          system on behalf of <xsl:value-of select="cac:DespatchSupplierParty/cac:Party/cac:PartyName/cbc:Name"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <b>Service Entry Agent:</b>
                          <br/>
                          <br/>
                          <xsl:for-each select="cac:Signature/cac:SignatoryParty">
                            <xsl:if test="$ReceiptTypeVar=&apos;SVC&apos; and cac:Person/cbc:JobTitle=&apos;SOU&apos;">
                              <xsl:value-of select="cac:Person/cbc:Title"/>
                              <xsl:text>&#160;</xsl:text>
                              <xsl:value-of select="cac:Person/cbc:FamilyName"/>
                              <xsl:text>&#160;</xsl:text>
                              <xsl:value-of select="cac:Person/cbc:FirstName"/>
                            </xsl:if>
                          </xsl:for-each>
                          <!-- <xsl:value-of select="cac:SellerSupplierParty/cac:Party/cac:PartyName/cbc:Name"/> -->
                        </xsl:otherwise>

                      </xsl:choose>

                      <!--
                      <xsl:for-each select="(cac:Signature/cac:SignatoryParty)[1]"><br/>
												<xsl:value-of select="cac:Person/cbc:Title"/><br/>
												<xsl:value-of select="cac:Person/cbc:FamilyName"/><xsl:text> </xsl:text>
												<xsl:value-of select="cac:Person/cbc:FirstName"/>
											</xsl:for-each>
                      -->
                    </td>
                  </xsl:when>
                </xsl:choose>
                <td class="cellNoBorder" width="40%" rowspan="2" style="vertical-align:text-top;">
                  <b>PO/SC delivery: </b>
                  <xsl:choose>
                    <xsl:when test="cbc:DocumentStatusCode=&apos;partial_delivery&apos;">
                      <xsl:text>Partial Delivery</xsl:text>
                    </xsl:when>
                    <xsl:when test="cbc:DocumentStatusCode=&apos;order_completed&apos;">
                      <xsl:text>Order Completed</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:value-of select="cbc:DocumentStatusCode"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <xsl:choose>
                  <xsl:when test="$ReceiptTypeVar=&apos;GDS&apos;">
                    <td class="cellNoBorder">
                      <b>Receiving service remarks:</b>
                    </td>
                    <td class="cellNoBorder">
                      <xsl:value-of select="(cbc:Note)[2]"/>
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:when>
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <td class="cellNoBorder">
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:when>
                </xsl:choose>
              </tr>
              <tr>
                <td class="cellTopBorder" colspan="2">
                  <b>Visas:</b>
                  <br/>
                  <table width="99.99%" cellpadding="0" cellspacing="0" border="0">
                    <tbody>
                      <xsl:for-each select="cac:Signature/cac:SignatoryParty">
                        <tr>
                          <xsl:choose>
                            <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                              <xsl:if test="cac:Person/cbc:JobTitle=&apos;AOI&apos; or cac:Person/cbc:JobTitle=&apos;PRN&apos;">
                                <td width="32.5%">
                                  <xsl:choose>
                                    <xsl:when test="cac:Person/cbc:JobTitle=&apos;AOI&apos;">
                                      <xsl:text>Operational Initiator Approbation</xsl:text>
                                    </xsl:when>
                                    <xsl:when test="cac:Person/cbc:JobTitle=&apos;PRN&apos;">
                                      <xsl:text>Final validation</xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                      <xsl:value-of select="cac:Person/cbc:JobTitle"/>
                                    </xsl:otherwise>
                                  </xsl:choose>
                                </td>
                                <td width="45%">
                                  <xsl:value-of select="cac:Person/cbc:Title"/>
                                  <xsl:text>&#160;</xsl:text>
                                  <xsl:value-of select="cac:Person/cbc:FamilyName"/>
                                  <xsl:text>&#160;</xsl:text>
                                  <xsl:value-of select="cac:Person/cbc:FirstName"/>
                                </td>
                                <td width="22.5%">
                                  <span style="white-space: nowrap">
                                    <xsl:value-of select="substring(string(../cbc:ValidationDate), 9, 2)"/>
                                    <xsl:text>/</xsl:text>
                                    <xsl:value-of select="substring(string(../cbc:ValidationDate), 6, 2)"/>
                                    <xsl:text>/</xsl:text>
                                    <xsl:value-of select="substring(string(../cbc:ValidationDate), 1, 4)"/>

                                    <!-- TIME -->
                                    <xsl:if test="../cbc:ValidationTime  != &apos;&apos;">
                                      <xsl:variable name="formattedTimeMillis" select="format-time(../cbc:ValidationTime , $timeFormatPatternMillis)" />
                                      <xsl:variable name="formattedTimeNormal" select="format-time(../cbc:ValidationTime , $timeFormatPatternNormal)" />

                                      <xsl:if test="$formattedTimeMillis != &apos;00:00:00.00&apos;">
                                        <xsl:text>&#160;</xsl:text>
                                        <xsl:value-of select="$formattedTimeNormal"/>
                                      </xsl:if>
                                    </xsl:if>
                                  </span>
                                </td>
                              </xsl:if>
                            </xsl:when>
                            <xsl:otherwise>
                              <xsl:if test="($Signatures=1) or (position()!=$Signatures)">
                                <td width="32.5%">
                                  <xsl:value-of select="cac:Person/cbc:JobTitle"/>
                                </td>
                                <td width="45%">
                                  <xsl:value-of select="cac:Person/cbc:Title"/>
                                  <xsl:text>&#160;</xsl:text>
                                  <xsl:value-of select="cac:Person/cbc:FamilyName"/>
                                  <xsl:text>&#160;</xsl:text>
                                  <xsl:value-of select="cac:Person/cbc:FirstName"/>
                                </td>
                                <td width="22.5%">
                                  <span style="white-space: nowrap">
                                    <xsl:value-of select="substring(string(../cbc:ValidationDate), 9, 2)"/>
                                    <xsl:text>/</xsl:text>
                                    <xsl:value-of select="substring(string(../cbc:ValidationDate), 6, 2)"/>
                                    <xsl:text>/</xsl:text>
                                    <xsl:value-of select="substring(string(../cbc:ValidationDate), 1, 4)"/>

                                    <!-- TIME -->
                                    <xsl:if test="../cbc:ValidationTime  != &apos;&apos;">
                                      <xsl:variable name="formattedTimeMillis" select="format-time(../cbc:ValidationTime , $timeFormatPatternMillis)" />
                                      <xsl:variable name="formattedTimeNormal" select="format-time(../cbc:ValidationTime , $timeFormatPatternNormal)" />

                                      <xsl:if test="$formattedTimeMillis != &apos;00:00:00.00&apos;">
                                        <xsl:text>&#160;</xsl:text>
                                        <xsl:value-of select="$formattedTimeNormal"/>
                                      </xsl:if>
                                    </xsl:if>
                                  </span>
                                </td>
                              </xsl:if>
                            </xsl:otherwise>
                          </xsl:choose>
                        </tr>
                      </xsl:for-each>
                    </tbody>
                  </table>
                </td>
                <td class="cellTopBorder" style="vertical-align:text-top;" rowspan="3">
                  <b>Issue Date and Time: </b>
                  <span style="white-space: nowrap">
                    <xsl:value-of select="substring(string(cbc:IssueDate), 9, 2)"/>
                    <xsl:text>/</xsl:text>
                    <xsl:value-of select="substring(string(cbc:IssueDate), 6, 2)"/>
                    <xsl:text>/</xsl:text>
                    <xsl:value-of select="substring(string(cbc:IssueDate), 1, 4)"/>

                    <xsl:if test="cbc:IssueTime != ''">
                      at <xsl:value-of select="format-time(cbc:IssueTime, $timeFormatPatternNormal)"/>
                    </xsl:if>
                  </span>
                </td>
              </tr>
              <tr>
                <xsl:choose>
                  <xsl:when test="$ReceiptTypeVar=&apos;SVC&apos;">
                    <td class="cellNoBorder">
                      <xsl:text>&#160;</xsl:text>
                    </td>
                    <td class="cellNoBorder">
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:when>
                  <xsl:otherwise>
                    <td class="cellNoBorder">
                      <b>Manager:</b>
                    </td>
                    <td class="cellNoBorder">
                      <xsl:value-of select="cac:DeliveryCustomerParty/cac:Party/cac:Contact/cbc:Name"/>
                      <xsl:text>&#160;</xsl:text>
                    </td>
                  </xsl:otherwise>
                </xsl:choose>
              </tr>
              <tr>
                <td class="cellNoBorder">
                  <b>Created by:</b>
                </td>
                <!-- <td class="cellNoBorder"><xsl:value-of select="(cbc:Note)[1]"/></td> -->
                <td class="cellNoBorder">
                  <xsl:value-of select="cac:SellerSupplierParty/cac:Party/cac:PartyName/cbc:Name"/>
                  <xsl:text>&#160;</xsl:text>
                </td>
              </tr>
            </tbody>
          </table>

        </xsl:for-each>
      </body>
    </html>
  </xsl:template>
  <xsl:template name="formatNumber">
    <xsl:param name="value"/>
    <xsl:choose>
      <xsl:when test="$value != &apos;&apos;">
        <xsl:value-of select="format-number(number($value), '##0,00', 'format1')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>N/A</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
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