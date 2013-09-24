
package com.actimind.diagnostic.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="use" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="collector" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="validator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="db" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="connector" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="global" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="stats">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="do" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;anyAttribute/>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="stat" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="group-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="collect">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="every" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="validate" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="error-message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="normal-message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="paranoid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="check" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="date-parsers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parser" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="listeners">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="listen" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "use",
    "global",
    "stats",
    "dateParsers",
    "listeners"
})
@XmlRootElement(name = "monitor")
public class Monitor {

    protected List<Monitor.Use> use;
    protected Monitor.Global global;
    @XmlElement(required = true)
    protected Monitor.Stats stats;
    @XmlElement(name = "date-parsers", required = true)
    protected Monitor.DateParsers dateParsers;
    @XmlElement(required = true)
    protected Monitor.Listeners listeners;

    /**
     * Gets the value of the use property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the use property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Monitor.Use }
     * 
     * 
     */
    public List<Monitor.Use> getUse() {
        if (use == null) {
            use = new ArrayList<Monitor.Use>();
        }
        return this.use;
    }

    /**
     * Gets the value of the global property.
     * 
     * @return
     *     possible object is
     *     {@link Monitor.Global }
     *     
     */
    public Monitor.Global getGlobal() {
        return global;
    }

    /**
     * Sets the value of the global property.
     * 
     * @param value
     *     allowed object is
     *     {@link Monitor.Global }
     *     
     */
    public void setGlobal(Monitor.Global value) {
        this.global = value;
    }

    /**
     * Gets the value of the stats property.
     * 
     * @return
     *     possible object is
     *     {@link Monitor.Stats }
     *     
     */
    public Monitor.Stats getStats() {
        return stats;
    }

    /**
     * Sets the value of the stats property.
     * 
     * @param value
     *     allowed object is
     *     {@link Monitor.Stats }
     *     
     */
    public void setStats(Monitor.Stats value) {
        this.stats = value;
    }

    /**
     * Gets the value of the dateParsers property.
     * 
     * @return
     *     possible object is
     *     {@link Monitor.DateParsers }
     *     
     */
    public Monitor.DateParsers getDateParsers() {
        return dateParsers;
    }

    /**
     * Sets the value of the dateParsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Monitor.DateParsers }
     *     
     */
    public void setDateParsers(Monitor.DateParsers value) {
        this.dateParsers = value;
    }

    /**
     * Gets the value of the listeners property.
     * 
     * @return
     *     possible object is
     *     {@link Monitor.Listeners }
     *     
     */
    public Monitor.Listeners getListeners() {
        return listeners;
    }

    /**
     * Sets the value of the listeners property.
     * 
     * @param value
     *     allowed object is
     *     {@link Monitor.Listeners }
     *     
     */
    public void setListeners(Monitor.Listeners value) {
        this.listeners = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="parser" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parser"
    })
    public static class DateParsers {

        protected List<String> parser;

        /**
         * Gets the value of the parser property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parser property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParser().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getParser() {
            if (parser == null) {
                parser = new ArrayList<String>();
            }
            return this.parser;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "param"
    })
    public static class Global {

        protected List<ParamsType> param;

        /**
         * Gets the value of the param property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the param property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParam().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ParamsType }
         * 
         * 
         */
        public List<ParamsType> getParam() {
            if (param == null) {
                param = new ArrayList<ParamsType>();
            }
            return this.param;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="listen" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "listen"
    })
    public static class Listeners {

        protected List<Monitor.Listeners.Listen> listen;

        /**
         * Gets the value of the listen property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the listen property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getListen().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Monitor.Listeners.Listen }
         * 
         * 
         */
        public List<Monitor.Listeners.Listen> getListen() {
            if (listen == null) {
                listen = new ArrayList<Monitor.Listeners.Listen>();
            }
            return this.listen;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "param"
        })
        public static class Listen {

            protected List<ParamsType> param;
            @XmlAttribute(name = "with")
            protected String with;

            /**
             * Gets the value of the param property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the param property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getParam().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ParamsType }
             * 
             * 
             */
            public List<ParamsType> getParam() {
                if (param == null) {
                    param = new ArrayList<ParamsType>();
                }
                return this.param;
            }

            /**
             * Gets the value of the with property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getWith() {
                return with;
            }

            /**
             * Sets the value of the with property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWith(String value) {
                this.with = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="do" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;anyAttribute/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="stat" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="group-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="collect">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="every" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="validate" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="error-message" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="normal-message" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element name="paranoid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="check" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "_do",
        "stat",
        "check"
    })
    public static class Stats {

        @XmlElement(name = "do")
        protected List<Monitor.Stats.Do> _do;
        protected List<Monitor.Stats.Stat> stat;
        protected List<String> check;

        /**
         * Gets the value of the do property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the do property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Monitor.Stats.Do }
         * 
         * 
         */
        public List<Monitor.Stats.Do> getDo() {
            if (_do == null) {
                _do = new ArrayList<Monitor.Stats.Do>();
            }
            return this._do;
        }

        /**
         * Gets the value of the stat property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stat property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStat().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Monitor.Stats.Stat }
         * 
         * 
         */
        public List<Monitor.Stats.Stat> getStat() {
            if (stat == null) {
                stat = new ArrayList<Monitor.Stats.Stat>();
            }
            return this.stat;
        }

        /**
         * Gets the value of the check property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the check property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCheck().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCheck() {
            if (check == null) {
                check = new ArrayList<String>();
            }
            return this.check;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;anyAttribute/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "param"
        })
        public static class Do {

            protected List<ParamsType> param;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the param property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the param property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getParam().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ParamsType }
             * 
             * 
             */
            public List<ParamsType> getParam() {
                if (param == null) {
                    param = new ArrayList<ParamsType>();
                }
                return this.param;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="group-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="collect">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="every" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="validate" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="error-message" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="normal-message" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="param" type="{}paramsType" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element name="paranoid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "name",
            "groupCode",
            "collect",
            "every",
            "validate",
            "param",
            "paranoid"
        })
        public static class Stat {

            @XmlElement(required = true)
            protected String name;
            @XmlElement(name = "group-code")
            protected String groupCode;
            @XmlElement(required = true)
            protected Monitor.Stats.Stat.Collect collect;
            @XmlElement(required = true)
            protected String every;
            protected List<Monitor.Stats.Stat.Validate> validate;
            protected List<ParamsType> param;
            @XmlElement(required = true)
            protected String paranoid;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the groupCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGroupCode() {
                return groupCode;
            }

            /**
             * Sets the value of the groupCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGroupCode(String value) {
                this.groupCode = value;
            }

            /**
             * Gets the value of the collect property.
             * 
             * @return
             *     possible object is
             *     {@link Monitor.Stats.Stat.Collect }
             *     
             */
            public Monitor.Stats.Stat.Collect getCollect() {
                return collect;
            }

            /**
             * Sets the value of the collect property.
             * 
             * @param value
             *     allowed object is
             *     {@link Monitor.Stats.Stat.Collect }
             *     
             */
            public void setCollect(Monitor.Stats.Stat.Collect value) {
                this.collect = value;
            }

            /**
             * Gets the value of the every property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEvery() {
                return every;
            }

            /**
             * Sets the value of the every property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEvery(String value) {
                this.every = value;
            }

            /**
             * Gets the value of the validate property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the validate property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getValidate().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Monitor.Stats.Stat.Validate }
             * 
             * 
             */
            public List<Monitor.Stats.Stat.Validate> getValidate() {
                if (validate == null) {
                    validate = new ArrayList<Monitor.Stats.Stat.Validate>();
                }
                return this.validate;
            }

            /**
             * Gets the value of the param property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the param property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getParam().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ParamsType }
             * 
             * 
             */
            public List<ParamsType> getParam() {
                if (param == null) {
                    param = new ArrayList<ParamsType>();
                }
                return this.param;
            }

            /**
             * Gets the value of the paranoid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getParanoid() {
                return paranoid;
            }

            /**
             * Sets the value of the paranoid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setParanoid(String value) {
                this.paranoid = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Collect {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "with")
                protected String with;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the with property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getWith() {
                    return with;
                }

                /**
                 * Sets the value of the with property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setWith(String value) {
                    this.with = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="with" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="error-message" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="normal-message" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Validate {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "with")
                protected String with;
                @XmlAttribute(name = "error-message")
                protected String errorMessage;
                @XmlAttribute(name = "normal-message")
                protected String normalMessage;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the with property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getWith() {
                    return with;
                }

                /**
                 * Sets the value of the with property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setWith(String value) {
                    this.with = value;
                }

                /**
                 * Gets the value of the errorMessage property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getErrorMessage() {
                    return errorMessage;
                }

                /**
                 * Sets the value of the errorMessage property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setErrorMessage(String value) {
                    this.errorMessage = value;
                }

                /**
                 * Gets the value of the normalMessage property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNormalMessage() {
                    return normalMessage;
                }

                /**
                 * Sets the value of the normalMessage property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNormalMessage(String value) {
                    this.normalMessage = value;
                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="collector" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="validator" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="db" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="connector" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Use {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "collector")
        protected String collector;
        @XmlAttribute(name = "validator")
        protected String validator;
        @XmlAttribute(name = "db")
        protected String db;
        @XmlAttribute(name = "connector")
        protected String connector;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the collector property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCollector() {
            return collector;
        }

        /**
         * Sets the value of the collector property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCollector(String value) {
            this.collector = value;
        }

        /**
         * Gets the value of the validator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValidator() {
            return validator;
        }

        /**
         * Sets the value of the validator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValidator(String value) {
            this.validator = value;
        }

        /**
         * Gets the value of the db property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDb() {
            return db;
        }

        /**
         * Sets the value of the db property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDb(String value) {
            this.db = value;
        }

        /**
         * Gets the value of the connector property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConnector() {
            return connector;
        }

        /**
         * Sets the value of the connector property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConnector(String value) {
            this.connector = value;
        }

    }

}
