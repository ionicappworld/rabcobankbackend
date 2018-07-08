package org.rabco.report.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)

public class Records {
	

	@XmlElement(name = "record")
	private List<ChildRecords> childrecords = null;
	

	public Records(List<ChildRecords> childrecords, String reference) {
		super();
		this.childrecords = childrecords;
	}

	public Records() {
	}

	

	

	public List<ChildRecords> getChildrecords() {
		return childrecords;
	}

	public void setChildrecords(List<ChildRecords> childrecords) {
		this.childrecords = childrecords;
	}

}
