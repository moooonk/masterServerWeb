package com.masterserver.xml;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlNode {
	private List<XmlNode> _childs = new LinkedList<XmlNode>();
	private Map<String, String> _attrs;
	private String name;
	
	public XmlNode(String name) {
		this.name = name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void putInt(String name, int value) {
		attrs().put(name, String.valueOf(value));
	}
	
	public void putLong(String name, long value) {
		attrs().put(name, String.valueOf(value));
	}

	public void putBool(String name, boolean value) {
		attrs().put(name, value ? "1" : "0");
	}

	public void putString(String name, String value) {
		attrs().put(name, value);
	}

	public void addNodes(Collection<XmlNode> nodes){
		childs().addAll(nodes);
	}
	
	public void addNode(XmlNode xmlNode) {
		childs().add(xmlNode);
	}

	private Map<String, String> attrs() {
		if (this._attrs == null) {
			this._attrs = new LinkedHashMap<String, String>(8);
		}
		return this._attrs;
	}

	private List<XmlNode> childs() {
		if (this._childs == null) {
			this._childs = new LinkedList<XmlNode>();
		}
		return this._childs;
	}

	public String toXml() {
		Element ele = this.toElement(XmlHelper.newDocment());
		String xmlString = XmlHelper.trans(ele);
		int index = xmlString.indexOf("?>");
		if(index == -1){
			return xmlString;
		}
		return xmlString.substring(index+2);
	}

	public Element toElement(Document doc) {
		Element ele = doc.createElement(name);
		if (_childs != null) {
			for (XmlNode xmlNode : _childs) {
				ele.appendChild(xmlNode.toElement(doc));
			}
		}

		if (_attrs != null) {
			for (Map.Entry<String, String> e : _attrs.entrySet()) {
				ele.setAttribute(e.getKey(), e.getValue());
			}
		}
		return ele;
	}
}
