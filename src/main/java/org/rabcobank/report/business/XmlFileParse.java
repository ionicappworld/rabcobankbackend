package org.rabcobank.report.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;
import org.rabco.report.pojo.ChildRecords;
import org.rabco.report.pojo.Records;

public class XmlFileParse {
	final static Logger logger = Logger.getLogger(CsvFileParser.class);

	/**
	 * method to parse XML file from resource folder
	 */
	public void readAndValidateXml() {
		try {
			// getting the xml file to read
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("records.xml").getFile());
			// creating the JAXB context
			JAXBContext jContext = JAXBContext.newInstance(Records.class);
			// creating the unmarshall object
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			// calling the unmarshall method
			Records statments = (Records) unmarshallerObj.unmarshal(file);
			for (ChildRecords stats : statments.getChildrecords()) {
				logger.info(stats.toString());
			}

			writeValidtedReportXMl(statments);
		} catch (Exception e) {
			logger.info("Error while readAndValidateXml !!!" + e.getMessage());
		}
	}

	/**
	 * method to validate parsed data and generated validated report CSV file
	 * from resource folder
	 */
	private void writeValidtedReportXMl(Records records) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
		List<Integer> reference = new ArrayList<Integer>();

		try {
			fileWriter = new FileWriter("ValidatedXmlReports.csv");
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			for (ChildRecords record : records.getChildrecords()) {
				List<String> validatedStats = new ArrayList<String>();
				validatedStats.add(String.valueOf(record.getReference()));
				validatedStats.add(record.getAccountNumber());
				validatedStats.add(record.getDescription());
				validatedStats.add(String.valueOf(record.getStartBalance()));
				validatedStats.add(String.valueOf(record.getMutation()));
				validatedStats.add(String.valueOf(record.getEndBalance()));
				if (reference.contains(record.getReference())) {
					validatedStats.add("Duplicate");
				} else {
					validatedStats.add("Unique");
				}
				reference.add(record.getReference());
				logger.info("Sum of balance : " + (record.getStartBalance().add(record.getMutation())));
				logger.info("Total : "
						+ (record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()));
				validatedStats.add(
						((record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()) == 0
								? "End Balance is Correct" : "End balance is Wrong"));

				csvFilePrinter.printRecord(validatedStats);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				logger.info("Error while flushing/closing fileWriter/csvPrinter !!!" + e.getMessage());

			}
		}

	}

}
