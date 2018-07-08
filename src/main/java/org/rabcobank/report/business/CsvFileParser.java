package org.rabcobank.report.business;

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
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.rabco.report.pojo.CustomerStatements;

public class CsvFileParser {
	
	private static final String validStatus_Duplicate ="Duplicate";
	private static final String validStatus_Unique ="Unique";
	final static Logger logger = Logger.getLogger(CsvFileParser.class);


	public void readCsvFileData() {
		CSVParser parser = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("records.csv").getFile());
			CSVFormat csvFileFormat = CSVFormat.RFC4180.withHeader().withDelimiter(',');

			parser = new CSVParser(new FileReader(file), csvFileFormat);

			List<CustomerStatements> CustomerStatements = new ArrayList<CustomerStatements>();
			for (CSVRecord record : parser) {
				CustomerStatements customerRecords = new CustomerStatements(Integer.parseInt(record.get("Reference")),
						record.get("AccountNumber"), record.get("Description"),
						new BigDecimal(record.get("Start Balance")), new BigDecimal(record.get("Mutation")),
						new BigDecimal(record.get("End Balance")));

				CustomerStatements.add(customerRecords);
			}
			
			writeValidtedReportCsv(CustomerStatements);
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
				logger.info("Error while closing fileReader/csvFileParser !!!"+e.getMessage());
				
			}
		}
	}

	private void writeValidtedReportCsv(List<CustomerStatements> stataments) {

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
				if(reference.contains(stats.getRefernece())){
				validatedStats.add(validStatus_Duplicate);	
				}else{
			    validatedStats.add(validStatus_Unique);
				}
				reference.add(stats.getRefernece());
				logger.info("Sum of balance : " +(stats.getStart_Balance().add(stats.getMutation())));
				logger.info("Total : " +(stats.getStart_Balance().add(stats.getMutation())).compareTo(stats.getEnd_Balance()));
				validatedStats.add(((stats.getStart_Balance().add(stats.getMutation())).compareTo(stats.getEnd_Balance())== 0 ? "End Balance is Correct" : "End balance is Wrong"));

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
				logger.info("Error while flushing/closing fileWriter/csvPrinter !!!"+e.getMessage());
			}
		}

	}
}
