<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

		<xsl:template match="/">
			<languageExamples>
				<xsl:apply-templates/>
			</languageExamples>		
		</xsl:template>
		
		<xsl:template match="example">
			<xsl:choose>
				<xsl:when test="language">
				  <languageExample>
				  	<xsl:attribute name="language">
    					<xsl:value-of select="normalize-space(language/text())"/>
    				</xsl:attribute>
    				<xsl:if test="alignedwords/* and translation/text()">
    					<example>
    						<xsl:apply-templates select="child::alignedwords"/>
   							<translation>
					  			<xsl:value-of select="translation/text()"/>
					  		</translation>
				  		</example>
    				</xsl:if>
				  	<xsl:apply-templates select="following-sibling::examples"/>
				  </languageExample>
				</xsl:when> 
				<xsl:otherwise>
					<example>
						<xsl:apply-templates/>
						<translation>
					  	<xsl:value-of select="translation/text()"/>
					  	</translation>
				  	</example>
				  	<xsl:apply-templates select="following-sibling::examples"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:template>
		
		<xsl:template match="exampleitem">
			<xsl:apply-templates select="./child::example"/>
		</xsl:template>
		
		<xsl:template match="alignedwords">
			<original>
				<xsl:apply-templates mode = "original"/>
			</original>
			<detailedTranslation>
				<xsl:apply-templates mode = "translation"/>
			</detailedTranslation>
		</xsl:template>
		
		<xsl:template match="morpheme" mode = "original">
			<xsl:apply-templates mode = "original"/>
		</xsl:template>
		
		<xsl:template match="morpheme" mode = "translation">
			<xsl:apply-templates mode = "translation"/>
		</xsl:template>
		
		<xsl:template match="word" mode = "translation">
			<xsl:apply-templates mode = "translation"/>
			<xsl:text>&#x20;</xsl:text>
		</xsl:template>
		
		<xsl:template match="word" mode = "original">
			<xsl:apply-templates mode = "original"/>
			<xsl:text>&#x20;</xsl:text>
		</xsl:template>
		
		<xsl:template match="block" mode = "original">
			<xsl:if test="@type='src'">
				<xsl:value-of select="text()"/>
			</xsl:if>
		</xsl:template>
		
		<xsl:template match="block" mode = "translation">
			<xsl:if test="@type='imt'">
				<xsl:value-of select="text()"/>
			</xsl:if>
		</xsl:template>
		
		<xsl:template match="text()|@*">
			<xsl:apply-templates/>
		</xsl:template>
		
		<xsl:template match="text()|@*" mode = "original">
			<xsl:apply-templates mode = "original"/>
		</xsl:template>
		
		<xsl:template match="text()|@*" mode = "translation">
			<xsl:apply-templates mode = "translation"/>
		</xsl:template>
		
</xsl:stylesheet>