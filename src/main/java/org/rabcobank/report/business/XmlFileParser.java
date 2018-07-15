package org.rabcobank.report.business;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.rabco.report.pojo.Records;
import org.rabcobank.report.enums.FileTypes;
import org.rabcobank.report.helper.RabcoGenericParser;

/**
 * @author vinesh
 *
 */
public class XmlFileParser extends RabcoGenericParser {
	static final Logger logger = Logger.getLogger(XmlFileParser.class);

	/**
	 * method to parse XML file from resource folder
	 */
	@Override
	public Boolean parser(FileTypes fileType) {
		Boolean isFileGenerated = false;
		try {
			// getting the xml file to read

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileType.getInputFileName()).getFile());
			// creating the JAXB context
			JAXBContext jContext = JAXBContext.newInstance(Records.class);
			// creating the unmarshall object
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			// calling the unmarshall method
			Records statments = (Records) unmarshallerObj.unmarshal(file);

			isFileGenerated = validateAndGenerateFinalReport(statments.getChildrecords(), fileType);
		} catch (Exception e) {
			logger.info("Error while readAndValidateXml !!!" + e.getMessage());
		}
		return isFileGenerated;
	}

}
