<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"
                version="1.0">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Notes GINF31 après rattrapage</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    background-color: #f9f9f9;
                    margin: 0;
                    padding: 0;
                    display: flex;
                    flex-direction: column;
                    align-items: center; /* Centre horizontalement */
                    justify-content: center; /* Centre verticalement */
                    min-height: 100vh; /* Prend toute la hauteur de la page */
                    }
                    .header {
                    background-color: #02306E;
                    color: white;
                    padding: 20px;
                    text-align: center;
                    width: 100%; /* Prend toute la largeur */
                    margin-bottom: 20px;
                    }
                    .header h2 {
                    margin: 0;
                    font-size: 24px;
                    }
                    table {
                    width: 80%; /* Largeur du tableau */
                    border-collapse: collapse;
                    background-color: white;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    margin: 20px 0; /* Espacement autour du tableau */
                    }
                    th, td {
                    border: 1px solid #ddd;
                    padding: 12px;
                    text-align: left;
                    }
                    th {
                    background-color: #02306E;
                    color: white;
                    font-weight: bold;
                    }
                    tr:nth-child(even) {
                    background-color: #f2f2f2;
                    }
                    tr:hover {
                    background-color: #ddd;
                    }
                    .success {
                    background-color: #4CAF50 !important; /* Vert pour réussite */
                    color: white;
                    }
                    .failure {
                    background-color: #F44336 !important; /* Rouge pour échec */
                    color: white;
                    }
                </style>
            </head>
            <body>
                <!-- En-tête -->
                <div class="header">
                    <h2>Résultat GINF31 après rattrapage</h2>
                </div>

                <!-- Tableau des notes -->
                <table>
                    <tr>
                        <th>CIN</th>
                        <th>NOM</th>
                        <th>PRENOM</th>
                        <th>Moyenne</th>
                        <th>Résultat</th>
                    </tr>
                    <xsl:for-each select="Notes/Etudiant">
                        <xsl:variable name="moyenne" select="format-number(sum(Module/Moyenne) div count(Module), '0.000')"/>
                        <tr>
                            <td><xsl:value-of select="CIN"/></td>
                            <td><xsl:value-of select="NOM"/></td>
                            <td><xsl:value-of select="PRENOM"/></td>
                            <td>
                                <xsl:attribute name="class">
                                    <xsl:choose>
                                        <xsl:when test="$moyenne &gt;= 12">success</xsl:when>
                                        <xsl:otherwise>failure</xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:value-of select="$moyenne"/>
                            </td>
                            <td>
                                <xsl:attribute name="class">
                                    <xsl:choose>
                                        <xsl:when test="$moyenne &gt;= 12">success</xsl:when>
                                        <xsl:otherwise>failure</xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:choose>
                                    <xsl:when test="$moyenne &gt;= 12">V</xsl:when>
                                    <xsl:otherwise>NV</xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>