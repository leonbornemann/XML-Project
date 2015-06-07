<?xml version="1.0"?>
	<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
		<xsl:template match="child">
			<neuKind>
				Kindknoten gefunden
			</neuKind>
		</xsl:template>
		<xsl:template match="root">
			<neuRoot> 
				Elternknoten gefunden 
			<xsl:apply-templates/>
			</neuRoot>
		</xsl:template>
		<xsl:template match=""*|/">
		<xsl:apply-templates/>
		</xsl:template>
	</xsl:stylesheet>