<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

		<xsl:template match="/">
		<root>
		<xsl:apply-templates/>
		</root>
		
		</xsl:template>
		<xsl:template match="example">
			
			<xsl:if test="language">
			  <language>
			  	<xsl:value-of select="language/text()"/>
			  </language>
			</xsl:if> 
			
			<xsl:choose>
				<xsl:when test="source/text()">
					<sourcetext>
						<xsl:value-of select="source/text()"/>
					</sourcetext>
				</xsl:when>
				<xsl:when test="alignedwords/*">
					<sourcetext> 
						<xsl:for-each select="alignedwords/word">
							<xsl:for-each select="morpheme">
								<xsl:for-each select="block">
									<xsl:if test="@type='src'">
										<xsl:value-of select="text()"/>
									</xsl:if>
								</xsl:for-each>
							</xsl:for-each>
							<xsl:text>#x20;</xsl:text>
						</xsl:for-each>
					</sourcetext>
				</xsl:when>	
			</xsl:choose>
			<xsl:apply-templates/>
		</xsl:template>
		
		
		
		<xsl:template match="text()|@*">
		</xsl:template>
		
</xsl:stylesheet>