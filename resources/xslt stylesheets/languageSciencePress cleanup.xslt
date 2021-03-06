<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
		
	<xsl:template match="languageExamples/example">
	</xsl:template>
			
	<xsl:template match="languageExample[not(*)]">
	</xsl:template>
		
	<!-- Identity template : copy all text nodes, elements and attributes -->   
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
    </xsl:template>
				
</xsl:stylesheet>