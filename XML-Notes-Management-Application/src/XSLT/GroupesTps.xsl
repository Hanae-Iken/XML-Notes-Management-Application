<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Groupes de TP</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    background-color: #f9f9f9;
                    margin: 0;
                    padding: 0;
                    }
                    .header {
                    background-color: #02306E;
                    color: white;
                    padding: 20px;
                    text-align: center;
                    position: relative;
                    }
                    .header img {
                    position: absolute;
                    left: 20px;
                    top: 50%;
                    transform: translateY(-50%);
                    height: 60px;
                    }
                    .header h1 {
                    margin: 0;
                    font-size: 24px;
                    }
                    .header p {
                    margin: 5px 0 0;
                    font-size: 16px;
                    }
                    .container {
                    padding: 20px;
                    }
                    h2 {
                    color: #02306E;
                    font-size: 20px;
                    margin-bottom: 10px;
                    }
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 20px;
                    background-color: white;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
                </style>
            </head>
            <body>
                <!-- En-tête avec logo et titre -->
                <div class="header">
                    <h1>Groupes de TP GINF2</h1>
                    <p>Université Abdelmalek Essaâdi - École Nationale des Sciences Appliquées de Tanger</p>
                </div>

                <!-- Contenu principal -->
                <div class="container">
                    <xsl:for-each select="Groupes/Groupe">
                        <h2>Groupe <xsl:value-of select="@numero"/></h2>
                        <table>
                            <tr>
                                <th>Code Apogée</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Date de Naissance</th>
                            </tr>
                            <xsl:for-each select="Etudiant">
                                <tr>
                                    <td><xsl:value-of select="CodeApogee"/></td>
                                    <td><xsl:value-of select="Nom"/></td>
                                    <td><xsl:value-of select="Prenom"/></td>
                                    <td><xsl:value-of select="DateNaissance"/></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>