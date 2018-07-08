package org.rabcobank.report.business;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;
import org.rabco.report.pojo.ChildRecords;
import org.rabco.report.pojo.CustomerStatements;
import org.rabco.report.pojo.Records;

public class XmlFileParseTest {
	/**
	 * test case to test if the parser object is not empty when file is available 
	 */
	@Test
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
				System.out.println(stats.getReference());
			}
			assertTrue(!statments.getChildrecords().isEmpty());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test case to pass on correct backend computation 
	 */

	@Test
	public void writeValidtedReportXMl() {
		Records records = new Records();
		List<ChildRecords> childrecords = new ArrayList<ChildRecords>() ;
		ChildRecords cs1 = new ChildRecords();
		cs1.setAccountNumber("testing1");
		cs1.setDescription("testing1");
		cs1.setEndBalance(new BigDecimal(22));
		cs1.setMutation(new BigDecimal(11));
		cs1.setReference(1234);
		cs1.setStartBalance(new BigDecimal(11));
		ChildRecords cs2 = new ChildRecords();
		cs2.setAccountNumber("testing2");
		cs2.setDescription("testing2");
		cs2.setEndBalance(new BigDecimal(-2));
		cs2.setMutation(new BigDecimal(-6));
		cs2.setReference(1234);
		cs2.setStartBalance(new BigDecimal(4));
		childrecords.add(cs1);
		childrecords.add(cs2);
		records.setChildrecords(childrecords);
		
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");

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
				System.out.println("Sum of balance : " +(record.getStartBalance().add(record.getMutation())));
				System.out.println("Total : " +(record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()));
				assertTrue(record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance())== 0);

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
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}

	}
	

	/**
	 * test case to fail on wrong backend computation 
	 */
	@Test
	public void writeValidtedReportFail() {
		Records records = new Records();
		List<ChildRecords> childrecords = new ArrayList<ChildRecords>() ;
		ChildRecords cs1 = new ChildRecords();
		cs1.setAccountNumber("testing1");
		cs1.setDescription("testing1");
		cs1.setEndBalance(new BigDecimal(45));
		cs1.setMutation(new BigDecimal(11));
		cs1.setReference(1234);
		cs1.setStartBalance(new BigDecimal(11));
		ChildRecords cs2 = new ChildRecords();
		cs2.setAccountNumber("testing2");
		cs2.setDescription("testing2");
		cs2.setEndBalance(new BigDecimal(-2));
		cs2.setMutation(new BigDecimal(-6));
		cs2.setReference(1234);
		cs2.setStartBalance(new BigDecimal(7));
		childrecords.add(cs1);
		childrecords.add(cs2);
		records.setChildrecords(childrecords);
		
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");

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
				System.out.println("Sum of balance : " +(record.getStartBalance().add(record.getMutation())));
				System.out.println("Total : " +(record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()));
				assertFalse(record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance())== 0);

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
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}

	}

}
