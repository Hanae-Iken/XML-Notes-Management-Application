<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <!-- Définition d'un paramètre pour la date -->
    <xsl:param name="dateGeneration" select="//DateGeneration"/>

    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4"
                                       page-width="210mm" page-height="297mm"
                                       margin="1cm">
                    <fo:region-body margin="1cm" margin-top="4cm"/>
                    <fo:region-before extent="1cm"/>
                    <fo:region-after extent="0.5cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4">
                <fo:static-content flow-name="xsl-region-before" font-family="Arial">
                    <fo:block>
                        <fo:inline-container inline-progression-dimension="33.33%" vertical-align="top">
                            <fo:block text-align="left">
                                <fo:block>ROYAUME DU MAROC</fo:block>
                                <fo:block>Université Abdelmalek Essaâdi</fo:block>
                                <fo:block>École Nationale des Sciences Appliquées de Tanger</fo:block>
                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container inline-progression-dimension="33.33%" text-align="center" vertical-align="middle">
                            <fo:block text-align="center">
                                <fo:external-graphic src="file:///C:\Users\Lenovo PC\Documents\Ginf2\s1\XML\ProjetXML-El-Otmani-Iken\Projet_XML\XML-Notes-Management-Application\src\Images\Ensat.png" content-height="2cm" />
                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container inline-progression-dimension="33.33%" vertical-align="top">
                            <fo:block text-align="right">
                                <fo:block>ROYAUME DU MAROC</fo:block>
                                <fo:block>Université Abdelmalek Essaâdi</fo:block>
                                <fo:block>École Nationale des Sciences Appliquées de Tanger</fo:block>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                </fo:static-content>

                <fo:static-content flow-name="xsl-region-after">
                    <fo:block text-align="center">
                        <fo:block text-align="left" font-size="10pt" margin-left="1cm">
                            <fo:block>Avis important: il ne peut être délivré qu'un seul exemplaire de cette attestation. Aucun duplicata ne sera fourni.</fo:block>
                        </fo:block>
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block text-align="center" font-weight="bold" font-size="18pt" margin-bottom="1pt" border="2pt solid black" background-color="lightgray">
                        RELEVÉ DE NOTES ET RÉSULTATS
                    </fo:block>
                    <fo:block text-align="center" margin-right="4cm" margin-left="4cm" margin-top="0.3cm" font-weight="bold" font-size="14pt" margin-bottom="0.5cm" border="1pt solid black" background-color="lightgray">
                        Session 1
                    </fo:block>

                    <fo:block>
                        <fo:table table-layout="fixed" width="100%">
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold">
                                            <xsl:value-of select="concat(//Etudiant[1]/PRENOM, ' ', //Etudiant[1]/NOM)"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-top="0.3cm">
                                        <fo:block>
                                            N°Étudiant : &#160; <xsl:value-of select="//Etudiant[1]/CIN"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <fo:block margin-top="0.2cm">
                            a obtenu les notes suivantes :
                        </fo:block>

                        <fo:table margin-top="0.4cm" border-style="double" border-width="1pt" table-layout="fixed">
                            <fo:table-column column-width="40%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="15%"/>
                            <fo:table-body>
                                <fo:table-row border-style="double" border-width="1pt">
                                    <fo:table-cell text-align="center">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block text-align="center" padding-top="0.1cm" padding-bottom="0.1cm">
                                            Note/Bareme
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block text-align="center" padding-top="0.1cm" padding-bottom="0.1cm">
                                            Session
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block text-align="center" padding-top="0.1cm" padding-bottom="0.1cm">
                                            Pts jury
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <xsl:for-each select="//Etudiant[1]/Module">
                                    <fo:table-row border-style="double" border-width="1pt" >
                                        <fo:table-cell padding-top="0.1cm" padding-bottom="0.1cm" >
                                            <fo:block>
                                                <fo:inline> <xsl:value-of select="@Code"/> :</fo:inline>
                                                <fo:inline> <xsl:value-of select="@Designation"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.1cm" padding-bottom="0.1cm">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="Moyenne"/>/20
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.1cm" padding-bottom="0.1cm">
                                            <fo:block>
                                                Session 1 2024/2025
                                            </fo:block>
                                        </fo:table-cell >
                                        <fo:table-cell padding-top="0.1cm" padding-bottom="0.1cm">
                                            <fo:block>

                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>

                        <fo:block font-weight="bold" margin-top="0.3cm">
                            <fo:inline>Résultat d'admission session 1 : </fo:inline>
                            <fo:inline><xsl:value-of select="//Etudiant[1]//MoyenneGénérale"/>/20</fo:inline>
                        </fo:block>

                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
