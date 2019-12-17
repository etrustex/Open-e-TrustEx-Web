<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:eccac="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:eccbc="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:mdord="urn:oasis:names:specification:ubl:schema:xsd:Order-2" xmlns:n1="ec:schema:xsd:OrderReceived-0.1" xmlns:ns1="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
	<xsl:param name="SV_OutputFormat" select="'HTML'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator=","/>
	<xsl:template match="text()" name="insertBreaks">
		<xsl:param name="pText" select="."/>
		<xsl:choose>
			<xsl:when test="not(contains($pText, '&#xA;'))">
			<xsl:value-of select="$pText" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="substring-before($pText, '&#xA;')"/>
				<br/>
				<xsl:call-template name="insertBreaks">
					<xsl:with-param name="pText" select="substring-after($pText, '&#xA;')"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="/">
		<html>
			<head>
				<meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
				<title>EUIPO Order Human Readable Format</title>
				<style type="text/css">
body, td {
	font-family: "Liberation Sans";
	font-size: medium;
}
.preserve {
	font-family: "Liberation Sans";
	font-size: medium;
	vertical-align: text-top;
}

.header {
	font-family: "Liberation Sans";
	font-weight:bold;
	font-size:large;
	text-align: center;
}
	
@page {
	size: A4 portrait;
	margin-top: 5%;
	margin-bottom: 5%;
	 
	@bottom-center {
		font-family: "Liberation Sans";
	  font-size: small;
		content: "Page " counter(page) "/" counter(pages);
    vertical-align:top; 
    border-top-style:solid;
    width=100%;
	}
  
	@bottom-right {
	  font-size: x-small;
    text-align: right;
		content: element(footer);
    border-top-style:solid;
    width=100%;
  }
  
	@bottom-left {
	  font-size: x-small;
    text-align: right;
		content: "&#160;";
    border-top-style:solid;
    width=100%;
  }
}
.footer {
	position: running(footer);
	font-size:  x-small;
  text-align:right;
  vertical-align:top;
}

	</style>
			</head>
			<body>
			<div class="header">See contractual provisions in the attached documents.</div>							
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
