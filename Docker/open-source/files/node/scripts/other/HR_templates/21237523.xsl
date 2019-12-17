<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:n1="urn:oasis:names:specification:ubl:schema:xsd:ApplicationResponse-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
	xmlns:ec1="ec:schema:xsd:CommonAggregateComponents-0.1" xmlns:ec2="ec:schema:xsd:CommonBasicComponents-0.1" xmlns:ec4="ec:schema:xsd:CommonBasicComponents-2"
	xmlns:ec3="ec:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
	xmlns:ecc="ec:schema:xsd:ApplicationResponseReceived-0.1" xmlns:ccts="urn:un:unece:uncefact:documentation:2"
	xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:altova="http://www.altova.com">
	<xsl:param name="SV_OutputFormat" select="'HTML'" />
	<xsl:output version="4.0" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd" />
	<xsl:variable name="XML" select="/" />
	<xsl:decimal-format name="format1" grouping-separator="." decimal-separator="," />
	<xsl:template match="/">
		<html>
			<head>
				<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
				<title>Application response Human Readable Format</title>
				<style type="text/css">
					body {
					font-family: "Liberation Sans";
					font-size: small;
					}
					.labelBoldMedium {
					font-family: "Liberation
					Sans";
					font-size: medium;
					font-weight: bold;
					}
					.labelBoldSmall {
					font-family: "Liberation Sans";
					font-size: small;
					font-weight: bold;
					}
					.labelWhite {
					color:#f0f0f0;
					}
					.bg1{
					background-color:#595959;
					}
					.bg2{
					background-color:silver;
					}
					.bg3{
					background-color:#e1e1e1;
					}
					.bg4{
					background-color:red;
					}
					table.noBorder {
					font-family: "Liberation Sans";
					font-size:
					small;
					border-color:white;
					border-style:none;
					}
					td {
					vertical-align:text-top;
					}
				</style>
			</head>
			<body>
				<table border="2" cellPadding="0" cellSpacing="0" width="100%">
					<tbody>
						<tr>
							<td align="center" colSpan="8">
								<span class="labelBoldMedium">Application Response</span>
							</td>
						</tr>
						<tr>
							<td>
								<tr>
									<td>
										<table style="border:1; border-color:black; " border="1" cellPadding="3" cellSpacing="0" width="100%">
											<tbody>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>ID: </xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="n1:ApplicationResponse">
																	<xsl:for-each select="cbc:ID">
																		<xsl:apply-templates />
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Issue Date: </xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="n1:ApplicationResponse">
																	<xsl:value-of select="cbc:IssueDate" />
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Response Code:</xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="n1:ApplicationResponse">
																	<xsl:for-each select="cac:DocumentResponse">
																		<xsl:for-each select="cac:Response">
																			<xsl:value-of select="cbc:ResponseCode" />
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Description:</xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="n1:ApplicationResponse">
																	<xsl:for-each select="cac:DocumentResponse">
																		<xsl:for-each select="cac:Response">
																			<xsl:value-of select="cbc:Description" />
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Document Reference ID:</xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="n1:ApplicationResponse">
																	<xsl:for-each select="cac:DocumentResponse">
																		<xsl:for-each select="cac:DocumentReference">
																			<xsl:value-of select="cbc:ID" />
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Document Reference typeCode:</xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="n1:ApplicationResponse">
																	<xsl:for-each select="cac:DocumentResponse">
																		<xsl:for-each select="cac:DocumentReference">
																			<xsl:value-of select="cbc:DocumentTypeCode" />
																		</xsl:for-each>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Document Reference Status:</xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="ec1:ECDocumentReceivedData">
																	<xsl:for-each select="ec3:ECReferencedDocumentData">
																		<xsl:value-of select="ec4:Status" />
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</td>
												</tr>
												<tr>
													<td style="border:1; border-color:black; " bgColor="#cccccc" width="50%">
														<xsl:text>Document Reference Type:</xsl:text>
													</td>
													<td style="border:1; border-color:black; ">
														<xsl:for-each select="$XML">
															<xsl:for-each select="ecc:ApplicationResponseReceived">
																<xsl:for-each select="ec1:ECDocumentReceivedData">
																	<xsl:for-each select="ec3:ECReferencedDocumentData">
																		<xsl:value-of select="ec4:DocumentReferenceName" />
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
							</td>
						</tr>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>