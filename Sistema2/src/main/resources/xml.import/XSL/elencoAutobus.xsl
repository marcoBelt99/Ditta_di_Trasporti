<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tur="http://www.progetto.com/turno"
                xmlns:lin="http://www.progetto.com/linea"
                xmlns:aut="http://www.progetto.com/turno"
                xmlns:common="http://www.progetto.com/common"
                xmlns:d="http://www.progetto.com/ditta"
>

    <!-- Template principale -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Elenco degli autobus</title>
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
                <h1>Elenco degli autobus</h1>
                <h3><xsl:value-of select="//d:ditta/nome"/></h3>
                <table>
                    <tr>
                        <th>Codice</th>
                        <th>Targa</th>
                        <th>Modello</th>
                        <th>Capienza</th>
                    </tr>
                    <xsl:apply-templates select="//d:autobus"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- Template per ogni turno -->
    <xsl:template match="d:autobus">

        <tr>
            <td><xsl:value-of select="@codice"/></td>
            <td><xsl:value-of select="@targa"/></td>
            <td><xsl:value-of select="@modello"/></td>
            <td><xsl:value-of select="@capienza"/></td>
        </tr>

    </xsl:template>

</xsl:stylesheet>

