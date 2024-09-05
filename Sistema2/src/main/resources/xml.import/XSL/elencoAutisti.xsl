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
                <title>Elenco degli autisti</title>
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
                <h1>Elenco degli autisti</h1>
                <h3><xsl:value-of select="//d:ditta/nome"/></h3>
                <!--                <h5><xsl:value-of select="//d:autisti.xsd"/></h5>-->
                <table>
                    <tr>
                        <th>Codice</th>
                        <th>Matricola</th>
                        <th>Nome</th>
                        <th>Cognome</th>
                        <th>Telefono</th>
                    </tr>
                    <xsl:apply-templates select="//d:autista"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- Template per ogni turno -->
    <xsl:template match="d:autista">

        <tr>
            <td><xsl:value-of select="@codice"/></td>
            <td><xsl:value-of select="@matricola"/></td>
            <td><xsl:value-of select="common:nome"/></td>
            <td><xsl:value-of select="common:cognome"/></td>
            <td><xsl:value-of select="common:telefono"/></td>
        </tr>

    </xsl:template>

</xsl:stylesheet>

