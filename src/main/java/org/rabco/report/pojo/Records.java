package org.rabco.report.pojo;

/**
 * @author vinesh
 *
 */
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)

public class Records {

	@XmlElement(name = "record")
	private List<CustomerStatements> childrecords = null;

	public Records(List<CustomerStatements> childrecords) {
		super();
		this.childrecords = childrecords;
	}

	public Records() {
	}

	public List<CustomerStatements> getChildrecords() {
		return childrecords;
	}

	public void setChildrecords(List<CustomerStatements> childrecords) {
		this.childrecords = childrecords;
	}

}
