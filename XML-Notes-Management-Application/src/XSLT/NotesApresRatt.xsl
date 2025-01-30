<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"
                version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Notes GINF31 après rattrapage</title>
            </head>
            <body>
                <h2 style="text-align:center;" class="card-head">Résultat GINF31 après rattrapage</h2>
                <table style="border:solid #000000 1px; border-spacing: 0; border-collapse: collapse;">
                    <tr bgcolor="#929292">
                        <th style="border:solid #000000 1px; padding: 5px;">CIN</th>
                        <th style="border:solid #000000 1px; padding: 5px;">NOM</th>
                        <th style="border:solid #000000 1px; padding: 5px;">PRENOM</th>
                        <th style="border:solid #000000 1px; padding: 5px;">Moyenne</th>
                        <th style="border:solid #000000 1px; padding: 5px;">Résultat</th>
                    </tr>
                    <xsl:for-each select="Notes/Etudiant">
                        <tr>
                            <td style="border:solid #000000 1px; padding: 5px;">
                                <xsl:value-of select="CIN"/>
                            </td>
                            <td style="border:solid #000000 1px; padding: 5px;">
                                <xsl:value-of select="NOM"/>
                            </td>
                            <td style="border:solid #000000 1px; padding: 5px;">
                                <xsl:value-of select="PRENOM"/>
                            </td>
                            <xsl:variable name="moyenne" select="format-number(sum(Module/Moyenne) div count(Module), '0.000')"/>
                            <td style="border:solid #000000 1px; padding: 5px;">
                                <xsl:attribute name="style">
                                    <xsl:choose>
                                        <xsl:when test="$moyenne &gt;= 12">
                                            <xsl:text>border:solid #000000 1px; padding: 5px; background-color:green</xsl:text>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:text>border:solid #000000 1px; padding: 5px; background-color:red</xsl:text>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:value-of select="$moyenne"/>
                            </td>
                            <td style="border:solid #000000 1px; padding: 5px;">
                                <xsl:attribute name="style">
                                    <xsl:choose>
                                        <xsl:when test="$moyenne &gt;= 12">
                                            <xsl:text>border:solid #000000 1px; padding: 5px; background-color:green</xsl:text>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:text>border:solid #000000 1px; padding: 5px; background-color:red</xsl:text>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:choose>
                                    <xsl:when test="$moyenne &gt;= 12">
                                        <xsl:text>V</xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:text>NV</xsl:text>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
