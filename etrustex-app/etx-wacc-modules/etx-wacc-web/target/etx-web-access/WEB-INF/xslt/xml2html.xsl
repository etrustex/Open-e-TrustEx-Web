<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*" /> 

    <xsl:template match="/">
		<xsl:apply-templates select="node()"/>
    </xsl:template>

    <xsl:template match="node()">
    	<ul>
	        <xsl:choose>
	            <xsl:when test="count(child::*) > 0">
	            	<li>
	            		<xsl:value-of select="local-name()"/>
		                <xsl:apply-templates select="node()"/>
	                </li>
	            </xsl:when>
	            <xsl:otherwise>
	            	<li class="leaf">
	            		<span class="key"><xsl:value-of select="local-name()"/>:</span>
	    					<xsl:choose>
	    						<xsl:when test="text()"><span class="value"><xsl:value-of select="text()" /></span></xsl:when>
    							<xsl:otherwise><span class="value">N/A</span>
    							</xsl:otherwise>
    						</xsl:choose>
	            	</li>
	            </xsl:otherwise>
	        </xsl:choose>
        </ul>
    </xsl:template>
</xsl:stylesheet>
