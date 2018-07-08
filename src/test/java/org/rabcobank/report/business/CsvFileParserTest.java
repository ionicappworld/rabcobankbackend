package org.rabcobank.report.business;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.rabco.report.pojo.CustomerStatements;

public class CsvFileParserTest {
	


	

	@Test
	public void readCsvFileData() {
		CSVParser parser = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("records.csv").getFile());
			CSVFormat csvFileFormat = CSVFormat.RFC4180.withHeader().withDelimiter(',');
			parser = new CSVParser(new FileReader(file), csvFileFormat);
			assertTrue(!parser.getRecords().isEmpty());			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				parser.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void writeValidtedReportCsv() {
		
		List<CustomerStatements> stataments = new ArrayList<CustomerStatements>();
		CustomerStatements cs1 = new CustomerStatements(1234,"testing1","testing2",new BigDecimal(11),new BigDecimal(11),new BigDecimal(22));
		CustomerStatements cs21 = new CustomerStatements(1234,"testing2","testing3",new BigDecimal(3),new BigDecimal(-1),new BigDecimal(2));
		stataments.add(cs1);
		stataments.add(cs21);

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
		try {
			fileWriter = new FileWriter("ValidatedCSVReports.csv");
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			List<Integer> reference = new ArrayList<Integer>();

			for (CustomerStatements stats : stataments) {
				List<String> validatedStats = new ArrayList<String>();
				validatedStats.add(String.valueOf(stats.getRefernece()));
				validatedStats.add(stats.getAccountNumber());
				validatedStats.add(stats.getDescription());
				validatedStats.add(String.valueOf(stats.getStart_Balance()));
				validatedStats.add(String.valueOf(stats.getMutation()));
				validatedStats.add(String.valueOf(stats.getEnd_Balance()));			
				reference.add(stats.getRefernece());	
				System.out.println("Sum of balance : " +(stats.getStart_Balance().add(stats.getMutation())));
				System.out.println("Total : " +(stats.getStart_Balance().add(stats.getMutation())).compareTo(stats.getEnd_Balance()));
				assertTrue(stats.getStart_Balance().add(stats.getMutation()).compareTo(stats.getEnd_Balance())== 0);

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
