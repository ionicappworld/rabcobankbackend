package org.rabcobank.report.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;
import org.rabco.report.pojo.CustomerStatements;
import org.rabcobank.report.enums.FileTypes;
import org.rabcobank.report.utils.ReportsValidationUtil;

/**
 * @author vinesh An abstract base class for all file specific parser . This
 *         class has methods implementation which will be common to all sub
 *         classes
 */
public abstract class RabcoGenericParser {
	public static final Logger logger = Logger.getLogger(RabcoGenericParser.class);

	private Boolean isFileGenerated = false;

	public abstract Boolean parser(FileTypes fileType);

	/**
	 * Common Validate and generate report for both CSV and XML input type
	 */
	public Boolean validateAndGenerateFinalReport(List<CustomerStatements> stataments, FileTypes fileType) {
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
		ReportsValidationUtil util = new ReportsValidationUtil();
		try (FileWriter fileWriter = new FileWriter(fileType.getOutputFileName());
				CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);) {

			List<Integer> reference = new ArrayList<>();
			stataments.forEach(stats -> {
				try {

					List<String> validatedStats = new ArrayList<>();
					validatedStats.add(String.valueOf(stats.getReference()));
					validatedStats.add(stats.getAccountNumber());
					validatedStats.add(stats.getDescription());
					validatedStats.add(String.valueOf(stats.getStartBalance()));
					validatedStats.add(String.valueOf(stats.getMutation()));
					validatedStats.add(String.valueOf(stats.getEndBalance()));
					validatedStats.add(util.validateDuplicate(stats, reference));
					reference.add(stats.getReference());
					validatedStats.add(util.validateEndBalance(stats));
					csvFilePrinter.printRecord(validatedStats);
					if (csvFilePrinter.toString().length() > 0) {
						isFileGenerated = true;
					}

				} catch (Exception e) {
					logger.info("File Close error !!!" + e.getMessage());
				}
			});

		} catch (IOException e) {
			logger.info("Error while closing csvPrinter !!!" + e.getMessage());
		}
		return isFileGenerated;

	}

}
