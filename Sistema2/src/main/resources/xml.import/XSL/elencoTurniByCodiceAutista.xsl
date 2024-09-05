<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tur="http://www.progetto.com/turno"
                xmlns:lin="http://www.progetto.com/linea"
                xmlns:aut="http://www.progetto.com/turno"
                xmlns:common="http://www.progetto.com/common"
                xmlns:d="http://www.progetto.com/ditta">

    <!-- Parametro per filtrare per codice turno -->
    <xsl:param name="codiceAutista" />

<!--    <xsl:key name="autistaByCodice" match="autisti/turno" use="@codice" />-->

    <!-- Template principale -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Turni di lavoro</title>
                <style>
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 20px;
                    }
                    th, td {
                    border: 1px solid #ddd;
                    padding: 8px;
                    }
                    th {
                    background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>Turni di lavoro</h1>
                <h3><xsl:value-of select="//d:ditta/nome"/></h3>
                <table>
                    <tr>
                        <th>ID Turno</th>
                        <th>Data</th>
                        <th>Ora Inizio</th>
                        <th>Ora Fine</th>
                        <th>Autista</th>
                        <th>Linea (destinazione)</th>
                        <th>Autobus</th>
                    </tr>
                    <xsl:apply-templates select="//tur:turno[tur:autista/@codice=$codiceAutista]"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- Template per ogni turno -->
    <xsl:template match="tur:turno">
        <tr>
            <td><xsl:value-of select="@id"/></td>
            <td><xsl:value-of select="tur:data"/></td>
            <td><xsl:value-of select="tur:ora_inizio"/></td>
            <td><xsl:value-of select="tur:ora_fine"/></td>
            <td>
                <xsl:variable name="codiceAutista" select="tur:autista/@codice"/>
                <xsl:value-of select="//d:autista[@codice=$codiceAutista]/common:nome"/>
                <xsl:text> </xsl:text>
                <xsl:value-of select="//d:autista[@codice=$codiceAutista]/common:cognome"/>
            </td>
            <td>
                <xsl:variable name="codiceLinea" select="tur:linea/@codice"/>
                <xsl:value-of select="//d:linea[@codice=$codiceLinea]/lin:destinazione"/>
            </td>
            <td><xsl:value-of select="tur:autobus/@codice"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
