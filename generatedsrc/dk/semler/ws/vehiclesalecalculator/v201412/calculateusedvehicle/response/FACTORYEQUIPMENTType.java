//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.11 at 12:10:22 PM PKT 
//


package dk.semler.ws.vehiclesalecalculator.v201412.calculateusedvehicle.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				This is a specification of importer defined dealer equipment and dealer equipment.		
 * 			
 * 
 * <p>Java class for FACTORYEQUIPMENTType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FACTORYEQUIPMENTType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PRNUMBER"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DESCRIPTION"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="55"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="STANDARDEQUIPMENT" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="SALESPRICE" type="{}PRICEType"/&gt;
 *         &lt;element name="PARTOFPACKET" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FACTORYEQUIPMENTType", propOrder = {
    "prnumber",
    "description",
    "standardequipment",
    "salesprice",
    "partofpacket"
})
public class FACTORYEQUIPMENTType {

    @XmlElement(name = "PRNUMBER", required = true)
    protected String prnumber;
    @XmlElement(name = "DESCRIPTION", required = true)
    protected String description;
    @XmlElement(name = "STANDARDEQUIPMENT")
    protected boolean standardequipment;
    @XmlElement(name = "SALESPRICE", required = true)
    protected PRICEType salesprice;
    @XmlElement(name = "PARTOFPACKET")
    protected String partofpacket;

    /**
     * Gets the value of the prnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRNUMBER() {
        return prnumber;
    }

    /**
     * Sets the value of the prnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRNUMBER(String value) {
        this.prnumber = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPTION(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the standardequipment property.
     * 
     */
    public boolean isSTANDARDEQUIPMENT() {
        return standardequipment;
    }

    /**
     * Sets the value of the standardequipment property.
     * 
     */
    public void setSTANDARDEQUIPMENT(boolean value) {
        this.standardequipment = value;
    }

    /**
     * Gets the value of the salesprice property.
     * 
     * @return
     *     possible object is
     *     {@link PRICEType }
     *     
     */
    public PRICEType getSALESPRICE() {
        return salesprice;
    }

    /**
     * Sets the value of the salesprice property.
     * 
     * @param value
     *     allowed object is
     *     {@link PRICEType }
     *     
     */
    public void setSALESPRICE(PRICEType value) {
        this.salesprice = value;
    }

    /**
     * Gets the value of the partofpacket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARTOFPACKET() {
        return partofpacket;
    }

    /**
     * Sets the value of the partofpacket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARTOFPACKET(String value) {
        this.partofpacket = value;
    }

    public FACTORYEQUIPMENTType withPRNUMBER(String value) {
        setPRNUMBER(value);
        return this;
    }

    public FACTORYEQUIPMENTType withDESCRIPTION(String value) {
        setDESCRIPTION(value);
        return this;
    }

    public FACTORYEQUIPMENTType withSTANDARDEQUIPMENT(boolean value) {
        setSTANDARDEQUIPMENT(value);
        return this;
    }

    public FACTORYEQUIPMENTType withSALESPRICE(PRICEType value) {
        setSALESPRICE(value);
        return this;
    }

    public FACTORYEQUIPMENTType withPARTOFPACKET(String value) {
        setPARTOFPACKET(value);
        return this;
    }

}
