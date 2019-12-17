<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sch="http://www.ascc.net/xml/schematron"
                xmlns:iso="http://purl.oclc.org/dsdl/schematron"
                xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2"
                xmlns:cct="urn:oasis:names:specification:ubl:schema:xsd:CoreComponentParameters-2"
                xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
                xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
                xmlns:udt="urn:un:unece:uncefact:data:draft:UnqualifiedDataTypesSchemaModule:2"
                xmlns:stat="urn:oasis:names:specification:ubl:schema:xsd:DocumentStatusCode-1.0"
                version="1.0"><!--Implementers: please note that overriding process-prolog or process-root is 
    the preferred method for meta-stylesheets to use where possible. The name or details of 
    this mode may change during 1Q 2007.-->


<!--PHASES-->


<!--PROLOG-->
<xsl:output xmlns:xs="http://www.w3.org/2001/XMLSchema"
               xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
               method="xml"
               omit-xml-declaration="no"
               standalone="yes"
               indent="yes"/>

   <!--KEYS-->


<!--DEFAULT RULES-->


<!--MODE: SCHEMATRON-FULL-PATH-->
<!--This mode can be used to generate an ugly though full XPath for locators-->
<xsl:template match="*" mode="schematron-get-full-path">
      <xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
      <xsl:text>/</xsl:text>
      <xsl:choose>
         <xsl:when test="namespace-uri()=''">
            <xsl:value-of select="name()"/>
            <xsl:variable name="p" select="1+    count(preceding-sibling::*[name()=name(current())])"/>
            <xsl:if test="$p&gt;1 or following-sibling::*[name()=name(current())]">[<xsl:value-of select="$p"/>]</xsl:if>
         </xsl:when>
         <xsl:otherwise>
            <xsl:text>*[local-name()='</xsl:text>
            <xsl:value-of select="local-name()"/>
            <xsl:text>']</xsl:text>
            <xsl:variable name="p"
                          select="1+   count(preceding-sibling::*[local-name()=local-name(current())])"/>
            <xsl:if test="$p&gt;1 or following-sibling::*[local-name()=local-name(current())]">[<xsl:value-of select="$p"/>]</xsl:if>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>
   <xsl:template match="@*" mode="schematron-get-full-path">
      <xsl:text>/</xsl:text>
      <xsl:choose>
         <xsl:when test="namespace-uri()=''">@<xsl:value-of select="name()"/>
         </xsl:when>
         <xsl:otherwise>
            <xsl:text>@*[local-name()='</xsl:text>
            <xsl:value-of select="local-name()"/>
            <xsl:text>' and namespace-uri()='</xsl:text>
            <xsl:value-of select="namespace-uri()"/>
            <xsl:text>']</xsl:text>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>

   <!--MODE: SCHEMATRON-FULL-PATH-2-->
<!--This mode can be used to generate prefixed XPath for humans-->
<xsl:template match="node() | @*" mode="schematron-get-full-path-2">
      <xsl:for-each select="ancestor-or-self::*">
         <xsl:text>/</xsl:text>
         <xsl:value-of select="name(.)"/>
         <xsl:if test="preceding-sibling::*[name(.)=name(current())]">
            <xsl:text>[</xsl:text>
            <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
            <xsl:text>]</xsl:text>
         </xsl:if>
      </xsl:for-each>
      <xsl:if test="not(self::*)">
         <xsl:text/>/@<xsl:value-of select="name(.)"/>
      </xsl:if>
   </xsl:template>

   <!--MODE: GENERATE-ID-FROM-PATH -->
<xsl:template match="/" mode="generate-id-from-path"/>
   <xsl:template match="text()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.text-', 1+count(preceding-sibling::text()), '-')"/>
   </xsl:template>
   <xsl:template match="comment()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.comment-', 1+count(preceding-sibling::comment()), '-')"/>
   </xsl:template>
   <xsl:template match="processing-instruction()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')"/>
   </xsl:template>
   <xsl:template match="@*" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.@', name())"/>
   </xsl:template>
   <xsl:template match="*" mode="generate-id-from-path" priority="-0.5">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:text>.</xsl:text>
      <xsl:value-of select="concat('.',name(),'-',1+count(preceding-sibling::*[name()=name(current())]),'-')"/>
   </xsl:template>

   <!--MODE: GENERATE-ID-2 -->
<xsl:template match="/" mode="generate-id-2">U</xsl:template>
   <xsl:template match="*" mode="generate-id-2" priority="2">
      <xsl:text>U</xsl:text>
      <xsl:number level="multiple" count="*"/>
   </xsl:template>
   <xsl:template match="node()" mode="generate-id-2">
      <xsl:text>U.</xsl:text>
      <xsl:number level="multiple" count="*"/>
      <xsl:text>n</xsl:text>
      <xsl:number count="node()"/>
   </xsl:template>
   <xsl:template match="@*" mode="generate-id-2">
      <xsl:text>U.</xsl:text>
      <xsl:number level="multiple" count="*"/>
      <xsl:text>_</xsl:text>
      <xsl:value-of select="string-length(local-name(.))"/>
      <xsl:text>_</xsl:text>
      <xsl:value-of select="translate(name(),':','.')"/>
   </xsl:template>
   <!--Strip characters--><xsl:template match="text()" priority="-1"/>

   <!--SCHEMA METADATA-->
<xsl:template match="/">
      <svrl:schematron-output xmlns:xs="http://www.w3.org/2001/XMLSchema"
                              xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                              title="Business rules for e-PRIOR Catalogue"
                              schemaVersion="">
         <svrl:ns-prefix-in-attribute-values uri="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2"
                                             prefix="qdt"/>
         <svrl:ns-prefix-in-attribute-values uri="urn:oasis:names:specification:ubl:schema:xsd:CoreComponentParameters-2"
                                             prefix="cct"/>
         <svrl:ns-prefix-in-attribute-values uri="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
                                             prefix="cbc"/>
         <svrl:ns-prefix-in-attribute-values uri="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
                                             prefix="cac"/>
         <svrl:ns-prefix-in-attribute-values uri="urn:un:unece:uncefact:data:draft:UnqualifiedDataTypesSchemaModule:2"
                                             prefix="udt"/>
         <svrl:ns-prefix-in-attribute-values uri="urn:oasis:names:specification:ubl:schema:xsd:DocumentStatusCode-1.0"
                                             prefix="stat"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M7"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M8"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M9"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M10"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M11"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M12"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M13"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M14"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M15"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M16"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M17"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M18"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M19"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M20"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M21"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M22"/>
         <svrl:active-pattern>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M23"/>
         <svrl:active-pattern>
            <xsl:attribute name="id">Catalogue_code_list_rules</xsl:attribute>
            <xsl:attribute name="name">Catalogue_code_list_rules</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M24"/>
      </svrl:schematron-output>
   </xsl:template>

   <!--SCHEMATRON PATTERNS-->
<svrl:text xmlns:xs="http://www.w3.org/2001/XMLSchema"
              xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Business rules for e-PRIOR Catalogue</svrl:text>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:Party" priority="1002" mode="M7">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:Party"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:PostalAddress) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:PostalAddress) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_check_postaladdress</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:PartyTaxScheme) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:PartyTaxScheme) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_check_partytaxscheme</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M7"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cac:ProviderParty" priority="1001" mode="M7">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:ProviderParty"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:PostalAddress) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:PostalAddress) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_check_postaladdress</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:PartyTaxScheme) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:PartyTaxScheme) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_check_partytaxscheme</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M7"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cac:ReceiverParty" priority="1000" mode="M7">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:ReceiverParty"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:PostalAddress) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:PostalAddress) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_check_postaladdress</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:PartyTaxScheme) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:PartyTaxScheme) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_check_partytaxscheme</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M7"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M7"/>
   <xsl:template match="@*|node()" priority="-2" mode="M7">
      <xsl:apply-templates select="@*|*" mode="M7"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:PartyTaxScheme" priority="1000" mode="M8">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:PartyTaxScheme"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( ( normalize-space(./cbc:CompanyID) = '' ) and ( ( local-name(..)='ProviderParty' ) or ( local-name(../..)='SellerSupplierParty' ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( ( normalize-space(./cbc:CompanyID) = '' ) and ( ( local-name(..)='ProviderParty' ) or ( local-name(../..)='SellerSupplierParty' ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.party_tax_scheme_check_companyid</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M8"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M8"/>
   <xsl:template match="@*|node()" priority="-2" mode="M8">
      <xsl:apply-templates select="@*|*" mode="M8"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:PostalAddress" priority="1000" mode="M9">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:PostalAddress"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:StreetName) = '' and normalize-space(./cbc:Postbox) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:StreetName) = '' and normalize-space(./cbc:Postbox) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.address_check_streetname</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:CityName) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:CityName) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.address_check_cityname</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:PostalZone) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:PostalZone) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.address_check_postalzone</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:Country) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:Country) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.address_check_country</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M9"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M9"/>
   <xsl:template match="@*|node()" priority="-2" mode="M9">
      <xsl:apply-templates select="@*|*" mode="M9"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="*[local-name()='Catalogue']/cbc:ID" priority="1000" mode="M10">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="*[local-name()='Catalogue']/cbc:ID"/>
      <xsl:variable name="apos" select="&#34;'&#34;"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(.) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(.) = '' )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.id_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( string-length(normalize-space(.)) &gt; 250 )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( string-length(normalize-space(.)) &gt; 250 )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.id_length_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( translate(translate(normalize-space(.), ' !&#34;&#34;#$%&amp;()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{{|}}~', ''), $apos, '') != '') "/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( translate(translate(normalize-space(.), ' !&#34;&#34;#$%&amp;()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{{|}}~', ''), $apos, '') != '')">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.id_invalid_char</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M10"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M10"/>
   <xsl:template match="@*|node()" priority="-2" mode="M10">
      <xsl:apply-templates select="@*|*" mode="M10"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="*[local-name()='Catalogue']" priority="1000" mode="M11">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="*[local-name()='Catalogue']"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( count(cac:ReferencedContract) &gt; 1 )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( count(cac:ReferencedContract) &gt; 1 )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.referencedcontract_check_number_warning</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( count(cac:ReferencedContract) &lt; 1 )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( count(cac:ReferencedContract) &lt; 1 )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.referencedcontract_check_number_error</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M11"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M11"/>
   <xsl:template match="@*|node()" priority="-2" mode="M11">
      <xsl:apply-templates select="@*|*" mode="M11"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:ReferencedContract" priority="1000" mode="M12">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:ReferencedContract"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:ID) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:ID) = '' )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.referencedcontract_check_id</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M12"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M12"/>
   <xsl:template match="@*|node()" priority="-2" mode="M12">
      <xsl:apply-templates select="@*|*" mode="M12"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:ValidityPeriod" priority="1000" mode="M13">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:ValidityPeriod"/>
      <xsl:variable name="startYear" select="number(substring(./cbc:StartDate,1,4))"/>
      <xsl:variable name="startMonth" select="number(substring(./cbc:StartDate,6,2))"/>
      <xsl:variable name="startDay" select="number(substring(./cbc:StartDate,9,2))"/>
      <xsl:variable name="endYear" select="number(substring(./cbc:EndDate,1,4))"/>
      <xsl:variable name="endMonth" select="number(substring(./cbc:EndDate,6,2))"/>
      <xsl:variable name="endDay" select="number(substring(./cbc:EndDate,9,2))"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="(normalize-space(./cbc:StartDate) = '') or (normalize-space(./cbc:EndDate) = '') or ( $startYear &lt; $endYear ) or ( ( $startYear = $endYear ) and ( $startMonth &lt; $endMonth ) ) or ( ( $startYear = $endYear ) and ( $startMonth = $endMonth ) and ( $startDay &lt;= $endDay ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="(normalize-space(./cbc:StartDate) = '') or (normalize-space(./cbc:EndDate) = '') or ( $startYear &lt; $endYear ) or ( ( $startYear = $endYear ) and ( $startMonth &lt; $endMonth ) ) or ( ( $startYear = $endYear ) and ( $startMonth = $endMonth ) and ( $startDay &lt;= $endDay ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_date_range</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M13"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M13"/>
   <xsl:template match="@*|node()" priority="-2" mode="M13">
      <xsl:apply-templates select="@*|*" mode="M13"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:StandardItemIdentification" priority="1000" mode="M14">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:StandardItemIdentification"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:ID/@schemeID) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:ID/@schemeID) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.standarditemidentification_check_schemeid</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M14"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M14"/>
   <xsl:template match="@*|node()" priority="-2" mode="M14">
      <xsl:apply-templates select="@*|*" mode="M14"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cbc:PriceAmount" priority="1000" mode="M15">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:PriceAmount"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(format-number(round(number(.) * 10 *10 *10 *10) div 10000, '##0.0000') &lt; 0)"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(format-number(round(number(.) * 10 *10 *10 *10) div 10000, '##0.0000') &lt; 0)">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.priceamount_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M15"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M15"/>
   <xsl:template match="@*|node()" priority="-2" mode="M15">
      <xsl:apply-templates select="@*|*" mode="M15"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:CommodityClassification" priority="1000" mode="M16">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:CommodityClassification"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( ( normalize-space(./cbc:ItemClassificationCode) = '' ) or ( normalize-space(./cbc:ItemClassificationCode/@listID) = '' ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( ( normalize-space(./cbc:ItemClassificationCode) = '' ) or ( normalize-space(./cbc:ItemClassificationCode/@listID) = '' ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.commodityclassification_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M16"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M16"/>
   <xsl:template match="@*|node()" priority="-2" mode="M16">
      <xsl:apply-templates select="@*|*" mode="M16"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:LineValidityPeriod" priority="1000" mode="M17">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:LineValidityPeriod"/>
      <xsl:variable name="lineStartYear" select="number(substring(./cbc:StartDate,1,4))"/>
      <xsl:variable name="lineStartMonth" select="number(substring(./cbc:StartDate,6,2))"/>
      <xsl:variable name="lineStartDay" select="number(substring(./cbc:StartDate,9,2))"/>
      <xsl:variable name="catalogueStartYear"
                    select="number(substring(../../cac:ValidityPeriod/cbc:StartDate,1,4))"/>
      <xsl:variable name="catalogueStartMonth"
                    select="number(substring(../../cac:ValidityPeriod/cbc:StartDate,6,2))"/>
      <xsl:variable name="catalogueStartDay"
                    select="number(substring(../../cac:ValidityPeriod/cbc:StartDate,9,2))"/>
      <xsl:variable name="lineEndYear" select="number(substring(./cbc:EndDate,1,4))"/>
      <xsl:variable name="lineEndMonth" select="number(substring(./cbc:EndDate,6,2))"/>
      <xsl:variable name="lineEndDay" select="number(substring(./cbc:EndDate,9,2))"/>
      <xsl:variable name="catalogueEndYear"
                    select="number(substring(../../cac:ValidityPeriod/cbc:EndDate,1,4))"/>
      <xsl:variable name="catalogueEndMonth"
                    select="number(substring(../../cac:ValidityPeriod/cbc:EndDate,6,2))"/>
      <xsl:variable name="catalogueEndDay"
                    select="number(substring(../../cac:ValidityPeriod/cbc:EndDate,9,2))"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( (normalize-space(./cbc:StartDate) = '') or ( ( $catalogueStartYear &lt; $lineStartYear ) or ( ( $catalogueStartYear = $lineStartYear ) and ( $catalogueStartMonth &lt; $lineStartMonth ) ) or ( ( $catalogueStartYear = $lineStartYear ) and ( $catalogueStartMonth = $lineStartMonth ) and ( $catalogueStartDay &lt;= $lineStartDay ) ) ) ) and ( (normalize-space(./cbc:EndDate) = '') or ( ( $lineEndYear &lt; $catalogueEndYear ) or ( ( $lineEndYear = $catalogueEndYear ) and ( $lineEndMonth &lt; $catalogueEndMonth ) ) or ( ( $lineEndYear = $catalogueEndYear ) and ( $lineEndMonth = $catalogueEndMonth ) and ( $lineEndDay &lt;= $catalogueEndDay ) ) ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( (normalize-space(./cbc:StartDate) = '') or ( ( $catalogueStartYear &lt; $lineStartYear ) or ( ( $catalogueStartYear = $lineStartYear ) and ( $catalogueStartMonth &lt; $lineStartMonth ) ) or ( ( $catalogueStartYear = $lineStartYear ) and ( $catalogueStartMonth = $lineStartMonth ) and ( $catalogueStartDay &lt;= $lineStartDay ) ) ) ) and ( (normalize-space(./cbc:EndDate) = '') or ( ( $lineEndYear &lt; $catalogueEndYear ) or ( ( $lineEndYear = $catalogueEndYear ) and ( $lineEndMonth &lt; $catalogueEndMonth ) ) or ( ( $lineEndYear = $catalogueEndYear ) and ( $lineEndMonth = $catalogueEndMonth ) and ( $lineEndDay &lt;= $catalogueEndDay ) ) ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.linevalidityperiod_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M17"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M17"/>
   <xsl:template match="@*|node()" priority="-2" mode="M17">
      <xsl:apply-templates select="@*|*" mode="M17"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cbc:MaximumQuantity" priority="1000" mode="M18">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:MaximumQuantity"/>
      <xsl:variable name="maximumQuantity" select="number(.)"/>
      <xsl:variable name="minimumQuantity" select="number(../cbc:MinimumQuantity)"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="((normalize-space(../cbc:MinimumQuantity) = '') or (normalize-space(.) = '') or ( $minimumQuantity &lt;= $maximumQuantity ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="((normalize-space(../cbc:MinimumQuantity) = '') or (normalize-space(.) = '') or ( $minimumQuantity &lt;= $maximumQuantity ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.quantity_range_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M18"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M18"/>
   <xsl:template match="@*|node()" priority="-2" mode="M18">
      <xsl:apply-templates select="@*|*" mode="M18"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="//*[contains(local-name(),'Quantity')]" priority="1000" mode="M19">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="//*[contains(local-name(),'Quantity')]"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(format-number(round(number(.) * 10 *10 *10 *10) div 10000, '##0.0000') &lt; 0)"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(format-number(round(number(.) * 10 *10 *10 *10) div 10000, '##0.0000') &lt; 0)">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.quantity_negativity_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M19"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M19"/>
   <xsl:template match="@*|node()" priority="-2" mode="M19">
      <xsl:apply-templates select="@*|*" mode="M19"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:Item" priority="1000" mode="M20">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:Item"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:ClassifiedTaxCategory) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:ClassifiedTaxCategory) = '' )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.item_check_classifiedtaxcategory</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cac:SellersItemIdentification) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cac:SellersItemIdentification) = '' )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.item_check_sellersitemidentification_catalogue</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( ( normalize-space(./cbc:Description) = '' ) and ( normalize-space(./cbc:Name) = '' ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( ( normalize-space(./cbc:Description) = '' ) and ( normalize-space(./cbc:Name) = '' ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.item_check_name_or_description</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M20"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M20"/>
   <xsl:template match="@*|node()" priority="-2" mode="M20">
      <xsl:apply-templates select="@*|*" mode="M20"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="*[local-name()='Catalogue']" priority="1000" mode="M21">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="*[local-name()='Catalogue']"/>
      <xsl:variable name="apos" select="&#34;'&#34;"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:VersionID) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:VersionID) = '' )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.versionid_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( string-length(normalize-space(./cbc:VersionID)) &gt; 5 )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( string-length(normalize-space(./cbc:VersionID)) &gt; 5 )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.versionid_length_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( translate(translate(./cbc:VersionID, ' !&#34;&#34;#$%&amp;()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{{|}}~', ''), $apos, '') != '') "/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( translate(translate(./cbc:VersionID, ' !&#34;&#34;#$%&amp;()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{{|}}~', ''), $apos, '') != '')">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.versionid_invalid_char</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M21"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M21"/>
   <xsl:template match="@*|node()" priority="-2" mode="M21">
      <xsl:apply-templates select="@*|*" mode="M21"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="cac:SellersItemIdentification" priority="1000" mode="M22">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:SellersItemIdentification"/>
      <xsl:variable name="apos" select="&#34;'&#34;"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( normalize-space(./cbc:ID) = '' )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( normalize-space(./cbc:ID) = '' )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.sellersitemidentificationid_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( string-length(normalize-space(./cbc:ID)) &gt; 250 )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( string-length(normalize-space(./cbc:ID)) &gt; 250 )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.sellersitemidentificationid_length_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( translate(translate(./cbc:ID, ' !&#34;&#34;#$%&amp;()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{{|}}~', ''), $apos, '') != '') "/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( translate(translate(./cbc:ID, ' !&#34;&#34;#$%&amp;()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{{|}}~', ''), $apos, '') != '')">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.sellersitemidentificationid_invalid_char</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M22"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M22"/>
   <xsl:template match="@*|node()" priority="-2" mode="M22">
      <xsl:apply-templates select="@*|*" mode="M22"/>
   </xsl:template>

   <!--PATTERN -->


	<!--RULE -->
<xsl:template match="*[local-name()='Catalogue']" priority="1000" mode="M23">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="*[local-name()='Catalogue']"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not ( count(//cac:Attachment) &gt; 0 )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not ( count(//cac:Attachment) &gt; 0 )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.attachment_check</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M23"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M23"/>
   <xsl:template match="@*|node()" priority="-2" mode="M23">
      <xsl:apply-templates select="@*|*" mode="M23"/>
   </xsl:template>

   <!--PATTERN Catalogue_code_list_rules-->


	<!--RULE -->
<xsl:template match="cbc:AddressFormatCode" priority="1014" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:AddressFormatCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' 1 2 3 4 5 6 7 8 9 ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' 1 2 3 4 5 6 7 8 9 ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_addressformatcode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:AllowanceChargeReasonCode" priority="1013" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:AllowanceChargeReasonCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_allowancechargereasoncode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:TaxExemptionReasonCode" priority="1012" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:TaxExemptionReasonCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( . = 'AAA Exempt' or . = 'AAB Exempt' or . = 'AAC Exempt' or . = 'AAE Reverse Charge' or . = 'AAF Exempt' or . = 'AAG Exempt' or . = 'AAH Margin Scheme' or . = 'AAI Margin Scheme' or . = 'AAJ Reverse Charge' or . = 'AAK Reverse Charge' or . = 'AAL Reverse Charge Exempt' or . = 'AAM Exempt New Means of Transport' or . = 'AAN Exempt Triangulation' or . = 'AAO Reverse Charge' ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( . = 'AAA Exempt' or . = 'AAB Exempt' or . = 'AAC Exempt' or . = 'AAE Reverse Charge' or . = 'AAF Exempt' or . = 'AAG Exempt' or . = 'AAH Margin Scheme' or . = 'AAI Margin Scheme' or . = 'AAJ Reverse Charge' or . = 'AAK Reverse Charge' or . = 'AAL Reverse Charge Exempt' or . = 'AAM Exempt New Means of Transport' or . = 'AAN Exempt Triangulation' or . = 'AAO Reverse Charge' ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_taxexemptionreasoncode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:TaxTypeCode" priority="1011" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:TaxTypeCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' StandardRated ZeroRated ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' StandardRated ZeroRated ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_taxtypecode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:DocumentTypeCode" priority="1010" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:DocumentTypeCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' 21 22 251 23 220 231 301 380 916 81 311 312 310 9 230 320 406 120 149 632 351 71 443 ATR ITT TQR ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' 21 22 251 23 220 231 301 380 916 81 311 312 310 9 230 320 406 120 149 632 351 71 443 ATR ITT TQR ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_documenttypecode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cac:ClassifiedTaxCategory/cbc:ID" priority="1009" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:ClassifiedTaxCategory/cbc:ID"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' A AA AB AC AD AE B C E G H O S Z ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' A AA AB AC AD AE B C E G H O S Z ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_taxcategoryid</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cac:TaxCategory/cbc:ID" priority="1008" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:TaxCategory/cbc:ID"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' A AA AB AC AD AE B C E G H O S Z ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' A AA AB AC AD AE B C E G H O S Z ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_taxcategoryid</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cac:TaxScheme/cbc:ID" priority="1007" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cac:TaxScheme/cbc:ID"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' VAT ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' VAT ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_taxschemeid</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:ActionCode" priority="1006" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:ActionCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' Add Delete Update ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' Add Delete Update ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_actioncode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:CommodityCode" priority="1005" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:CommodityCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' CV GN HS ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' CV GN HS ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_commoditycode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:LifeCycleStatusCode" priority="1004" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:LifeCycleStatusCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' Available DeletedAnnouncement ItemDeleted NewAnnouncement NewAvailable ItemTemporarilyUnavailable ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' Available DeletedAnnouncement ItemDeleted NewAnnouncement NewAvailable ItemTemporarilyUnavailable ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">warning</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_lifecyclestatuscode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="@unitCode" priority="1003" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="@unitCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' 05 06 08 10 11 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 40 41 43 44 45 46 47 48 53 54 56 57 58 59 60 61 62 63 64 66 69 71 72 73 74 76 77 78 80 81 84 85 87 89 90 91 92 93 94 95 96 97 98 1A 1B 1C 1D 1E 1F 1G 1H 1I 1J 1K 1L 1M 1X 2A 2B 2C 2G 2H 2I 2J 2K 2L 2M 2N 2P 2Q 2R 2U 2V 2W 2X 2Y 2Z 3B 3C 3E 3G 3H 3I 4A 4B 4C 4E 4G 4H 4K 4L 4M 4N 4O 4P 4Q 4R 4T 4U 4W 4X 5A 5B 5C 5E 5F 5G 5H 5I 5J 5K 5P 5Q A1 A10 A11 A12 A13 A14 A15 A16 A17 A18 A19 A2 A20 A21 A22 A23 A24 A25 A26 A27 A28 A29 A3 A30 A31 A32 A33 A34 A35 A36 A37 A38 A39 A4 A40 A41 A42 A43 A44 A45 A47 A48 A49 A5 A50 A51 A52 A53 A54 A55 A56 A57 A58 A59 A6 A60 A61 A62 A63 A64 A65 A66 A67 A68 A69 A7 A70 A71 A73 A74 A75 A76 A77 A78 A79 A8 A80 A81 A82 A83 A84 A85 A86 A87 A88 A89 A9 A90 A91 A93 A94 A95 A96 A97 A98 A99 AA AB ACR ACT AD AE AH AI AJ AK AL AM AMH AMP ANN AP APZ AQ AR ARE AS ASM ASU ATM ATT AV AW AY AZ B0 B1 B10 B11 B12 B13 B14 B15 B16 B17 B18 B19 B2 B20 B21 B22 B23 B24 B25 B26 B27 B28 B29 B3 B30 B31 B32 B33 B34 B35 B36 B37 B38 B39 B4 B40 B41 B42 B43 B44 B45 B46 B47 B48 B49 B5 B50 B51 B52 B53 B54 B55 B56 B57 B58 B59 B6 B60 B61 B62 B63 B64 B65 B66 B67 B68 B69 B7 B70 B71 B72 B73 B74 B75 B76 B77 B78 B79 B8 B80 B81 B82 B83 B84 B85 B86 B87 B88 B89 B9 B90 B91 B92 B93 B94 B95 B96 B97 B98 B99 BAR BB BD BE BFT BG BH BHP BIL BJ BK BL BLD BLL BO BP BQL BR BT BTU BUA BUI BW BX BZ C0 C1 C10 C11 C12 C13 C14 C15 C16 C17 C18 C19 C2 C20 C21 C22 C23 C24 C25 C26 C27 C28 C29 C3 C30 C31 C32 C33 C34 C35 C36 C37 C38 C39 C4 C40 C41 C42 C43 C44 C45 C46 C47 C48 C49 C5 C50 C51 C52 C53 C54 C55 C56 C57 C58 C59 C6 C60 C61 C62 C63 C64 C65 C66 C67 C68 C69 C7 C70 C71 C72 C73 C74 C75 C76 C77 C78 C79 C8 C80 C81 C82 C83 C84 C85 C86 C87 C88 C89 C9 C90 C91 C92 C93 C94 C95 C96 C97 C98 C99 CA CCT CDL CEL CEN CG CGM CH CJ CK CKG CL CLF CLT CMK CMQ CMT CNP CNT CO COU CQ CR CS CT CTG CTM CTN CU CUR CV CWA CWI CY CZ D03 D04 D1 D10 D11 D12 D13 D14 D15 D16 D17 D18 D19 D2 D20 D21 D22 D23 D24 D25 D26 D27 D28 D29 D30 D31 D32 D33 D34 D35 D36 D37 D38 D39 D40 D41 D42 D43 D44 D45 D46 D47 D48 D49 D5 D50 D51 D52 D53 D54 D55 D56 D57 D58 D59 D6 D60 D61 D62 D63 D64 D65 D66 D67 D68 D69 D7 D70 D71 D72 D73 D74 D75 D76 D77 D78 D79 D8 D80 D81 D82 D83 D85 D86 D87 D88 D89 D9 D90 D91 D92 D93 D94 D95 D96 D97 D98 D99 DAA DAD DAY DB DC DD DE DEC DG DI DJ DLT DMA DMK DMO DMQ DMT DN DPC DPR DPT DQ DR DRA DRI DRL DRM DS DT DTN DU DWT DX DY DZN DZP E01 E07 E08 E09 E10 E11 E12 E14 E15 E16 E17 E18 E19 E2 E20 E21 E22 E23 E25 E27 E28 E3 E30 E31 E32 E33 E34 E35 E36 E37 E38 E39 E4 E40 E41 E42 E43 E44 E45 E46 E47 E48 E49 E5 E50 E51 E52 E53 E54 E55 E56 E57 E58 E59 E60 E61 E62 E63 E64 E65 E66 E67 E68 E69 E70 E71 E72 E73 E74 E75 E76 E77 E78 E79 E80 E81 E82 E83 E84 E85 E86 E87 E88 E89 E90 E91 E92 E93 E94 E95 E96 E97 E98 E99 EA EB EC EP EQ EV F01 F02 F03 F04 F05 F06 F07 F08 F1 F10 F11 F12 F13 F14 F15 F16 F17 F18 F19 F20 F21 F22 F23 F24 F25 F26 F27 F28 F29 F30 F31 F32 F33 F34 F35 F36 F37 F38 F39 F40 F41 F42 F43 F44 F45 F46 F47 F48 F49 F50 F51 F52 F53 F54 F55 F56 F57 F58 F59 F60 F61 F62 F63 F64 F65 F66 F67 F68 F69 F70 F71 F72 F73 F74 F75 F76 F77 F78 F79 F80 F81 F82 F83 F84 F85 F86 F87 F88 F89 F9 F90 F91 F92 F93 F94 F95 F96 F97 F98 F99 FAH FAR FB FBM FC FD FE FF FG FH FIT FL FM FOT FP FR FS FTK FTQ G01 G04 G05 G06 G08 G09 G10 G11 G12 G13 G14 G15 G16 G17 G18 G19 G2 G20 G21 G23 G24 G25 G26 G27 G28 G29 G3 G30 G31 G32 G33 G34 G35 G36 G37 G38 G39 G40 G41 G42 G43 G44 G45 G46 G47 G48 G49 G50 G51 G52 G53 G54 G55 G56 G57 G58 G59 G60 G61 G62 G63 G64 G65 G66 G67 G68 G69 G7 G70 G71 G72 G73 G74 G75 G76 G77 G78 G79 G80 G81 G82 G83 G84 G85 G86 G87 G88 G89 G90 G91 G92 G93 G94 G95 G96 G97 G98 G99 GB GBQ GC GD GDW GE GF GFI GGR GH GIA GIC GII GIP GJ GK GL GLD GLI GLL GM GN GO GP GQ GRM GRN GRO GRT GT GV GW GWH GY GZ H03 H04 H05 H06 H07 H08 H09 H1 H10 H11 H12 H13 H14 H15 H16 H18 H19 H2 H20 H21 H22 H23 H24 H25 H26 H27 H28 H29 H30 H31 H32 H33 H34 H35 H36 H37 H38 H39 H40 H41 H42 H43 H44 H45 H46 H47 H48 H49 H50 H51 H52 H53 H54 H55 H56 H57 H58 H59 H60 H61 H62 H63 H64 H65 H66 H67 H68 H69 H70 H71 H72 H73 H74 H75 H76 H77 H78 H79 H80 H81 H82 H83 H84 H85 H87 H88 H89 H90 H91 H92 H93 H94 H95 H96 H98 H99 HA HAR HBA HBX HC HD HDW HDY HE HF HGM HH HI HIU HJ HK HKM HL HLT HM HMQ HMT HN HO HP HPA HS HT HTZ HUR HY IA IC IE IF II IL IM INH INK INQ IP ISD IT IU IV J10 J12 J13 J14 J15 J16 J17 J18 J19 J2 J20 J21 J22 J23 J24 J25 J26 J27 J28 J29 J30 J31 J32 J33 J34 J35 J36 J38 J39 J40 J41 J42 J43 J44 J45 J46 J47 J48 J49 J50 J51 J52 J53 J54 J55 J56 J57 J58 J59 J60 J61 J62 J63 J64 J65 J66 J67 J68 J69 J70 J71 J72 J73 J74 J75 J76 J78 J79 J81 J82 J83 J84 J85 J87 J89 J90 J91 J92 J93 J94 J95 J96 J97 J98 J99 JB JE JG JK JM JNT JO JOU JPS JR JWL K1 K10 K11 K12 K13 K14 K15 K16 K17 K18 K19 K2 K20 K21 K22 K23 K24 K25 K26 K27 K28 K3 K30 K31 K32 K33 K34 K35 K36 K37 K38 K39 K40 K41 K42 K43 K45 K46 K47 K48 K49 K5 K50 K51 K52 K53 K54 K55 K58 K59 K6 K60 K61 K62 K63 K64 K65 K66 K67 K68 K69 K70 K71 K73 K74 K75 K76 K77 K78 K79 K80 K81 K82 K83 K84 K85 K86 K87 K88 K89 K90 K91 K92 K93 K94 K95 K96 K97 K98 K99 KA KAT KB KBA KCC KD KDW KEL KF KG KGM KGS KHY KHZ KI KIC KIP KJ KJO KL KLK KMA KMH KMK KMQ KMT KNI KNS KNT KO KPA KPH KPO KPP KR KS KSD KSH KT KTM KTN KUR KVA KVR KVT KW KWH KWO KWT KX L10 L11 L12 L13 L14 L15 L16 L17 L18 L19 L2 L20 L21 L23 L24 L25 L26 L27 L28 L29 L30 L31 L32 L33 L34 L35 L36 L37 L38 L39 L40 L41 L42 L43 L44 L45 L46 L47 L48 L49 L50 L51 L52 L53 L54 L55 L56 L57 L58 L59 L60 L61 L62 L63 L64 L65 L66 L67 L68 L69 L70 L71 L72 L73 L74 L75 L76 L77 L78 L79 L80 L81 L82 L83 L84 L85 L86 L87 L88 L89 L90 L91 L92 L93 L94 L95 L96 L98 L99 LA LAC LBR LBT LC LD LE LEF LF LH LI LJ LK LM LN LO LP LPA LR LS LTN LTR LUB LUM LUX LX LY M0 M1 M10 M11 M12 M13 M14 M15 M16 M17 M18 M19 M20 M21 M22 M23 M24 M25 M26 M27 M29 M30 M31 M32 M33 M34 M35 M36 M37 M4 M5 M7 M9 MA MAH MAL MAM MAR MAW MBE MBF MBR MC MCU MD MF MGM MHZ MIK MIL MIN MIO MIU MK MLD MLT MMK MMQ MMT MND MON MPA MQ MQH MQS MSK MT MTK MTQ MTR MTS MV MVA MWH N1 N2 N3 NA NAR NB NBB NC NCL ND NE NEW NF NG NH NI NIL NIU NJ NL NMI NMP NN NPL NPR NPT NQ NR NRL NT NTT NU NV NX NY OA ODE OHM ON ONZ OP OT OZ OZA OZI P0 P1 P2 P3 P4 P5 P6 P7 P8 P9 PA PAL PB PD PE PF PFL PG PGL PI PK PL PLA PM PN PO PQ PR PS PT PTD PTI PTL PU PV PW PY PZ Q3 QA QAN QB QD QH QK QR QT QTD QTI QTL QTR R1 R4 R9 RA RD RG RH RK RL RM RN RO RP RPM RPS RS RT RU S3 S4 S5 S6 S7 S8 SA SAN SCO SCR SD SE SEC SET SG SHT SIE SK SL SMI SN SO SP SQ SQR SR SS SST ST STI STK STL STN SV SW SX T0 T1 T3 T4 T5 T6 T7 T8 TA TAH TC TD TE TF TI TIC TIP TJ TK TL TMS TN TNE TP TPR TQ TQD TR TRL TS TSD TSH TT TU TV TW TY U1 U2 UA UB UC UD UE UF UH UM VA VI VLT VP VQ VS W2 W4 WA WB WCD WE WEB WEE WG WH WHR WI WM WR WSD WTT WW X1 YDK YDQ YL YRD YT Z1 Z2 Z3 Z4 Z5 Z6 Z8 ZP ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' 05 06 08 10 11 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 40 41 43 44 45 46 47 48 53 54 56 57 58 59 60 61 62 63 64 66 69 71 72 73 74 76 77 78 80 81 84 85 87 89 90 91 92 93 94 95 96 97 98 1A 1B 1C 1D 1E 1F 1G 1H 1I 1J 1K 1L 1M 1X 2A 2B 2C 2G 2H 2I 2J 2K 2L 2M 2N 2P 2Q 2R 2U 2V 2W 2X 2Y 2Z 3B 3C 3E 3G 3H 3I 4A 4B 4C 4E 4G 4H 4K 4L 4M 4N 4O 4P 4Q 4R 4T 4U 4W 4X 5A 5B 5C 5E 5F 5G 5H 5I 5J 5K 5P 5Q A1 A10 A11 A12 A13 A14 A15 A16 A17 A18 A19 A2 A20 A21 A22 A23 A24 A25 A26 A27 A28 A29 A3 A30 A31 A32 A33 A34 A35 A36 A37 A38 A39 A4 A40 A41 A42 A43 A44 A45 A47 A48 A49 A5 A50 A51 A52 A53 A54 A55 A56 A57 A58 A59 A6 A60 A61 A62 A63 A64 A65 A66 A67 A68 A69 A7 A70 A71 A73 A74 A75 A76 A77 A78 A79 A8 A80 A81 A82 A83 A84 A85 A86 A87 A88 A89 A9 A90 A91 A93 A94 A95 A96 A97 A98 A99 AA AB ACR ACT AD AE AH AI AJ AK AL AM AMH AMP ANN AP APZ AQ AR ARE AS ASM ASU ATM ATT AV AW AY AZ B0 B1 B10 B11 B12 B13 B14 B15 B16 B17 B18 B19 B2 B20 B21 B22 B23 B24 B25 B26 B27 B28 B29 B3 B30 B31 B32 B33 B34 B35 B36 B37 B38 B39 B4 B40 B41 B42 B43 B44 B45 B46 B47 B48 B49 B5 B50 B51 B52 B53 B54 B55 B56 B57 B58 B59 B6 B60 B61 B62 B63 B64 B65 B66 B67 B68 B69 B7 B70 B71 B72 B73 B74 B75 B76 B77 B78 B79 B8 B80 B81 B82 B83 B84 B85 B86 B87 B88 B89 B9 B90 B91 B92 B93 B94 B95 B96 B97 B98 B99 BAR BB BD BE BFT BG BH BHP BIL BJ BK BL BLD BLL BO BP BQL BR BT BTU BUA BUI BW BX BZ C0 C1 C10 C11 C12 C13 C14 C15 C16 C17 C18 C19 C2 C20 C21 C22 C23 C24 C25 C26 C27 C28 C29 C3 C30 C31 C32 C33 C34 C35 C36 C37 C38 C39 C4 C40 C41 C42 C43 C44 C45 C46 C47 C48 C49 C5 C50 C51 C52 C53 C54 C55 C56 C57 C58 C59 C6 C60 C61 C62 C63 C64 C65 C66 C67 C68 C69 C7 C70 C71 C72 C73 C74 C75 C76 C77 C78 C79 C8 C80 C81 C82 C83 C84 C85 C86 C87 C88 C89 C9 C90 C91 C92 C93 C94 C95 C96 C97 C98 C99 CA CCT CDL CEL CEN CG CGM CH CJ CK CKG CL CLF CLT CMK CMQ CMT CNP CNT CO COU CQ CR CS CT CTG CTM CTN CU CUR CV CWA CWI CY CZ D03 D04 D1 D10 D11 D12 D13 D14 D15 D16 D17 D18 D19 D2 D20 D21 D22 D23 D24 D25 D26 D27 D28 D29 D30 D31 D32 D33 D34 D35 D36 D37 D38 D39 D40 D41 D42 D43 D44 D45 D46 D47 D48 D49 D5 D50 D51 D52 D53 D54 D55 D56 D57 D58 D59 D6 D60 D61 D62 D63 D64 D65 D66 D67 D68 D69 D7 D70 D71 D72 D73 D74 D75 D76 D77 D78 D79 D8 D80 D81 D82 D83 D85 D86 D87 D88 D89 D9 D90 D91 D92 D93 D94 D95 D96 D97 D98 D99 DAA DAD DAY DB DC DD DE DEC DG DI DJ DLT DMA DMK DMO DMQ DMT DN DPC DPR DPT DQ DR DRA DRI DRL DRM DS DT DTN DU DWT DX DY DZN DZP E01 E07 E08 E09 E10 E11 E12 E14 E15 E16 E17 E18 E19 E2 E20 E21 E22 E23 E25 E27 E28 E3 E30 E31 E32 E33 E34 E35 E36 E37 E38 E39 E4 E40 E41 E42 E43 E44 E45 E46 E47 E48 E49 E5 E50 E51 E52 E53 E54 E55 E56 E57 E58 E59 E60 E61 E62 E63 E64 E65 E66 E67 E68 E69 E70 E71 E72 E73 E74 E75 E76 E77 E78 E79 E80 E81 E82 E83 E84 E85 E86 E87 E88 E89 E90 E91 E92 E93 E94 E95 E96 E97 E98 E99 EA EB EC EP EQ EV F01 F02 F03 F04 F05 F06 F07 F08 F1 F10 F11 F12 F13 F14 F15 F16 F17 F18 F19 F20 F21 F22 F23 F24 F25 F26 F27 F28 F29 F30 F31 F32 F33 F34 F35 F36 F37 F38 F39 F40 F41 F42 F43 F44 F45 F46 F47 F48 F49 F50 F51 F52 F53 F54 F55 F56 F57 F58 F59 F60 F61 F62 F63 F64 F65 F66 F67 F68 F69 F70 F71 F72 F73 F74 F75 F76 F77 F78 F79 F80 F81 F82 F83 F84 F85 F86 F87 F88 F89 F9 F90 F91 F92 F93 F94 F95 F96 F97 F98 F99 FAH FAR FB FBM FC FD FE FF FG FH FIT FL FM FOT FP FR FS FTK FTQ G01 G04 G05 G06 G08 G09 G10 G11 G12 G13 G14 G15 G16 G17 G18 G19 G2 G20 G21 G23 G24 G25 G26 G27 G28 G29 G3 G30 G31 G32 G33 G34 G35 G36 G37 G38 G39 G40 G41 G42 G43 G44 G45 G46 G47 G48 G49 G50 G51 G52 G53 G54 G55 G56 G57 G58 G59 G60 G61 G62 G63 G64 G65 G66 G67 G68 G69 G7 G70 G71 G72 G73 G74 G75 G76 G77 G78 G79 G80 G81 G82 G83 G84 G85 G86 G87 G88 G89 G90 G91 G92 G93 G94 G95 G96 G97 G98 G99 GB GBQ GC GD GDW GE GF GFI GGR GH GIA GIC GII GIP GJ GK GL GLD GLI GLL GM GN GO GP GQ GRM GRN GRO GRT GT GV GW GWH GY GZ H03 H04 H05 H06 H07 H08 H09 H1 H10 H11 H12 H13 H14 H15 H16 H18 H19 H2 H20 H21 H22 H23 H24 H25 H26 H27 H28 H29 H30 H31 H32 H33 H34 H35 H36 H37 H38 H39 H40 H41 H42 H43 H44 H45 H46 H47 H48 H49 H50 H51 H52 H53 H54 H55 H56 H57 H58 H59 H60 H61 H62 H63 H64 H65 H66 H67 H68 H69 H70 H71 H72 H73 H74 H75 H76 H77 H78 H79 H80 H81 H82 H83 H84 H85 H87 H88 H89 H90 H91 H92 H93 H94 H95 H96 H98 H99 HA HAR HBA HBX HC HD HDW HDY HE HF HGM HH HI HIU HJ HK HKM HL HLT HM HMQ HMT HN HO HP HPA HS HT HTZ HUR HY IA IC IE IF II IL IM INH INK INQ IP ISD IT IU IV J10 J12 J13 J14 J15 J16 J17 J18 J19 J2 J20 J21 J22 J23 J24 J25 J26 J27 J28 J29 J30 J31 J32 J33 J34 J35 J36 J38 J39 J40 J41 J42 J43 J44 J45 J46 J47 J48 J49 J50 J51 J52 J53 J54 J55 J56 J57 J58 J59 J60 J61 J62 J63 J64 J65 J66 J67 J68 J69 J70 J71 J72 J73 J74 J75 J76 J78 J79 J81 J82 J83 J84 J85 J87 J89 J90 J91 J92 J93 J94 J95 J96 J97 J98 J99 JB JE JG JK JM JNT JO JOU JPS JR JWL K1 K10 K11 K12 K13 K14 K15 K16 K17 K18 K19 K2 K20 K21 K22 K23 K24 K25 K26 K27 K28 K3 K30 K31 K32 K33 K34 K35 K36 K37 K38 K39 K40 K41 K42 K43 K45 K46 K47 K48 K49 K5 K50 K51 K52 K53 K54 K55 K58 K59 K6 K60 K61 K62 K63 K64 K65 K66 K67 K68 K69 K70 K71 K73 K74 K75 K76 K77 K78 K79 K80 K81 K82 K83 K84 K85 K86 K87 K88 K89 K90 K91 K92 K93 K94 K95 K96 K97 K98 K99 KA KAT KB KBA KCC KD KDW KEL KF KG KGM KGS KHY KHZ KI KIC KIP KJ KJO KL KLK KMA KMH KMK KMQ KMT KNI KNS KNT KO KPA KPH KPO KPP KR KS KSD KSH KT KTM KTN KUR KVA KVR KVT KW KWH KWO KWT KX L10 L11 L12 L13 L14 L15 L16 L17 L18 L19 L2 L20 L21 L23 L24 L25 L26 L27 L28 L29 L30 L31 L32 L33 L34 L35 L36 L37 L38 L39 L40 L41 L42 L43 L44 L45 L46 L47 L48 L49 L50 L51 L52 L53 L54 L55 L56 L57 L58 L59 L60 L61 L62 L63 L64 L65 L66 L67 L68 L69 L70 L71 L72 L73 L74 L75 L76 L77 L78 L79 L80 L81 L82 L83 L84 L85 L86 L87 L88 L89 L90 L91 L92 L93 L94 L95 L96 L98 L99 LA LAC LBR LBT LC LD LE LEF LF LH LI LJ LK LM LN LO LP LPA LR LS LTN LTR LUB LUM LUX LX LY M0 M1 M10 M11 M12 M13 M14 M15 M16 M17 M18 M19 M20 M21 M22 M23 M24 M25 M26 M27 M29 M30 M31 M32 M33 M34 M35 M36 M37 M4 M5 M7 M9 MA MAH MAL MAM MAR MAW MBE MBF MBR MC MCU MD MF MGM MHZ MIK MIL MIN MIO MIU MK MLD MLT MMK MMQ MMT MND MON MPA MQ MQH MQS MSK MT MTK MTQ MTR MTS MV MVA MWH N1 N2 N3 NA NAR NB NBB NC NCL ND NE NEW NF NG NH NI NIL NIU NJ NL NMI NMP NN NPL NPR NPT NQ NR NRL NT NTT NU NV NX NY OA ODE OHM ON ONZ OP OT OZ OZA OZI P0 P1 P2 P3 P4 P5 P6 P7 P8 P9 PA PAL PB PD PE PF PFL PG PGL PI PK PL PLA PM PN PO PQ PR PS PT PTD PTI PTL PU PV PW PY PZ Q3 QA QAN QB QD QH QK QR QT QTD QTI QTL QTR R1 R4 R9 RA RD RG RH RK RL RM RN RO RP RPM RPS RS RT RU S3 S4 S5 S6 S7 S8 SA SAN SCO SCR SD SE SEC SET SG SHT SIE SK SL SMI SN SO SP SQ SQR SR SS SST ST STI STK STL STN SV SW SX T0 T1 T3 T4 T5 T6 T7 T8 TA TAH TC TD TE TF TI TIC TIP TJ TK TL TMS TN TNE TP TPR TQ TQD TR TRL TS TSD TSH TT TU TV TW TY U1 U2 UA UB UC UD UE UF UH UM VA VI VLT VP VQ VS W2 W4 WA WB WCD WE WEB WEE WG WH WHR WI WM WR WSD WTT WW X1 YDK YDQ YL YRD YT Z1 Z2 Z3 Z4 Z5 Z6 Z8 ZP ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_unitofmeasurecode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="@languageID" priority="1002" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="@languageID"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' aa ab af ak sq am ar an hy as av ae ay az ba bm eu be bn bh bi bo bs br bg my ca cs ch ce zh cu cv kw co cr cy cs da de dv nl dz el en eo et eu ee fo fa fj fi fr fr fy ff ka de gd ga gl gv el gn gu ht ha he hz hi ho hr hu hy ig is io ii iu ie ia id ik is it jv ja kl kn ks ka kr kk km ki rw ky kv kg ko kj ku lo la lv li ln lt lb lu lg mk mh ml mi mr ms mk mg mt mn mi ms my na nv nr nd ng ne nl nn nb no ny oc oj or om os pa fa pi pl pt ps qu rm ro rn ru sg sa si sk sl se sm sn sd so st es sq sc sr ss su sw sv ty ta tt te tg tl th bo ti to tn ts tk tr tw ug uk ur uz ve vi vo cy wa wo xh yi yo za zh zu ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' aa ab af ak sq am ar an hy as av ae ay az ba bm eu be bn bh bi bo bs br bg my ca cs ch ce zh cu cv kw co cr cy cs da de dv nl dz el en eo et eu ee fo fa fj fi fr fr fy ff ka de gd ga gl gv el gn gu ht ha he hz hi ho hr hu hy ig is io ii iu ie ia id ik is it jv ja kl kn ks ka kr kk km ki rw ky kv kg ko kj ku lo la lv li ln lt lb lu lg mk mh ml mi mr ms mk mg mt mn mi ms my na nv nr nd ng ne nl nn nb no ny oc oj or om os pa fa pi pl pt ps qu rm ro rn ru sg sa si sk sl se sm sn sd so st es sq sc sr ss su sw sv ty ta tt te tg tl th bo ti to tn ts tk tr tw ug uk ur uz ve vi vo cy wa wo xh yi yo za zh zu ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_languagecode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="cbc:IdentificationCode" priority="1001" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="cbc:IdentificationCode"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' AD AE AF AG AI AL AM AN AO AQ AR AS AT AU AW AX AZ BA BB BD BE BF BG BH BI BL BJ BM BN BO BR BS BT BV BW BY BZ CA CC CD CF CG CH CI CK CL CM CN CO CR CU CV CX CY CZ DE DJ DK DM DO DZ EC EE EG EH ER ES ET FI FJ FK FM FO FR GA GB GD GE GF GG GH GI GL GM GN GP GQ GR GS GT GU GW GY HK HM HN HR HT HU ID IE IL IM IN IO IQ IR IS IT JE JM JO JP KE KG KH KI KM KN KP KR KW KY KZ LA LB LC LI LK LR LS LT LU LV LY MA MC MD ME MF MG MH MK ML MM MN MO MP MQ MR MS MT MU MV MW MX MY MZ NA NC NE NF NG NI NL NO NP NR NU NZ OM PA PE PF PG PH PK PL PM PN PR PS PT PW PY QA RO RS RU RW SA SB SC SD SE SG SH SI SJ SK SL SM SN SO SR ST SV SY SZ TC TD TF TG TH TJ TK TL TM TN TO TR TT TV TW TZ UA UG UM US UY UZ VA VC VE VG VI VN VU WF WS YE YT ZA ZM ZW ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' AD AE AF AG AI AL AM AN AO AQ AR AS AT AU AW AX AZ BA BB BD BE BF BG BH BI BL BJ BM BN BO BR BS BT BV BW BY BZ CA CC CD CF CG CH CI CK CL CM CN CO CR CU CV CX CY CZ DE DJ DK DM DO DZ EC EE EG EH ER ES ET FI FJ FK FM FO FR GA GB GD GE GF GG GH GI GL GM GN GP GQ GR GS GT GU GW GY HK HM HN HR HT HU ID IE IL IM IN IO IQ IR IS IT JE JM JO JP KE KG KH KI KM KN KP KR KW KY KZ LA LB LC LI LK LR LS LT LU LV LY MA MC MD ME MF MG MH MK ML MM MN MO MP MQ MR MS MT MU MV MW MX MY MZ NA NC NE NF NG NI NL NO NP NR NU NZ OM PA PE PF PG PH PK PL PM PN PR PS PT PW PY QA RO RS RU RW SA SB SC SD SE SG SH SI SJ SK SL SM SN SO SR ST SV SY SZ TC TD TF TG TH TJ TK TL TM TN TO TR TT TV TW TZ UA UG UM US UY UZ VA VC VE VG VI VN VU WF WS YE YT ZA ZM ZW ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_countryidentificationcode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="@currencyID" priority="1000" mode="M24">
      <svrl:fired-rule xmlns:xs="http://www.w3.org/2001/XMLSchema"
                       xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="@currencyID"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="( ( not(contains(normalize-space(.),' ')) and contains( ' AED AFN ALL AMD ANG AOA ARS AUD AWG AZN BAM BBD BDT BGN BHD BIF BMD BND BOB BOV BRL BSD BTN BWP BYR BZD CAD CDF CHE CHF CHW CLF CLP CNY COP COU CRC CUP CVE CZK DJF DKK DOP DZD EEK EGP ERN ETB EUR FJD FKP GBP GEL GHS GIP GMD GNF GTQ GWP GYD HKD HNL HRK HTG HUF IDR ILS INR IQD IRR ISK JMD JOD JPY KES KGS KHR KMF KPW KRW KWD KYD KZT LAK LBP LKR LRD LSL LTL LVL LYD MAD MDL MGA MKD MMK MNT MOP MRO MUR MVR MWK MXN MXV MYR MZN NAD NGN NIO NOK NPR NZD OMR PAB PEN PGK PHP PKR PLN PYG QAR RON RSD RUB RWF SAR SBD SCR SDG SEK SGD SHP SKK SLL SOS SRD STD SVC SYP SZL THB TJS TMM TND TOP TRY TTD TWD TZS UAH UGX USD USN USS UYI UYU UZS VEF VND VUV WST XAF XAG XAU XBA XBB XBC XBD XCD XDR XFU XOF XPD XPF XTS XXX YER ZAR ZMK ZWR ZWD ',concat(' ',normalize-space(.),' ') ) ) )"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="( ( not(contains(normalize-space(.),' ')) and contains( ' AED AFN ALL AMD ANG AOA ARS AUD AWG AZN BAM BBD BDT BGN BHD BIF BMD BND BOB BOV BRL BSD BTN BWP BYR BZD CAD CDF CHE CHF CHW CLF CLP CNY COP COU CRC CUP CVE CZK DJF DKK DOP DZD EEK EGP ERN ETB EUR FJD FKP GBP GEL GHS GIP GMD GNF GTQ GWP GYD HKD HNL HRK HTG HUF IDR ILS INR IQD IRR ISK JMD JOD JPY KES KGS KHR KMF KPW KRW KWD KYD KZT LAK LBP LKR LRD LSL LTL LVL LYD MAD MDL MGA MKD MMK MNT MOP MRO MUR MVR MWK MXN MXV MYR MZN NAD NGN NIO NOK NPR NZD OMR PAB PEN PGK PHP PKR PLN PYG QAR RON RSD RUB RWF SAR SBD SCR SDG SEK SGD SHP SKK SLL SOS SRD STD SVC SYP SZL THB TJS TMM TND TOP TRY TTD TWD TZS UAH UGX USD USN USS UYI UYU UZS VEF VND VUV WST XAF XAG XAU XBA XBB XBC XBD XCD XDR XFU XOF XPD XPF XTS XXX YER ZAR ZMK ZWR ZWD ',concat(' ',normalize-space(.),' ') ) ) )">
               <xsl:attribute name="flag">error</xsl:attribute>
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-get-full-path"/>
               </xsl:attribute>
               <svrl:text>error.invalid_codetable_value_currencycode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M24"/>
   <xsl:template match="@*|node()" priority="-2" mode="M24">
      <xsl:apply-templates select="@*|*" mode="M24"/>
   </xsl:template>
</xsl:stylesheet>