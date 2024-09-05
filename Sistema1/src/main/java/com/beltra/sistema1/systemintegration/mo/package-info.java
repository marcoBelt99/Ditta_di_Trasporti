@XmlSchema(
        namespace = "http://www.progetto.com/ditta", // aggiunto
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "tur", namespaceURI = "http://www.progetto.com/turno"),
                @XmlNs(prefix = "lin", namespaceURI = "http://www.progetto.com/linea"),
                @XmlNs(prefix = "common", namespaceURI = "http://www.progetto.com/common"),
                @XmlNs(prefix = "d", namespaceURI = "http://www.progetto.com/ditta") // aggiunto
        }

)

//@XmlNs(prefix = "", namespaceURI = "http://www.progetto.com/ditta"),

package com.beltra.sistema1.systemintegration.mo;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;


