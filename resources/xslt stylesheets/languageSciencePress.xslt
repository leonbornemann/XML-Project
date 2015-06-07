<?xml version="1.0"?>
	<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
		<xsl:template match="example">
			<xsl:if test="source">
				<sourcetext>
					<xsl:value-of select="/source/text()"/>
				</sourcetext>
			</xsl:if>
			<xsl:apply-templates/>
		</xsl:template>
	</xsl:stylesheet>