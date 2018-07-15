package org.rabcobank.report.business;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.rabco.report.pojo.CustomerStatements;
import org.rabcobank.report.enums.FileTypes;
import org.rabcobank.report.helper.RabcoGenericParser;

/**
 * @author vinesh
 *
 */
public class CsvFileParser extends RabcoGenericParser {
	static final Logger logger = Logger.getLogger(CsvFileParser.class);

	/**
	 * method to parse CSV file from resource folder
	 */
	@Override
	public Boolean parser(FileTypes fileType) {
		Boolean isFileGenerated = false;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileType.getInputFileName()).getFile());
		CSVFormat csvFileFormat = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		try (CSVParser parser = new CSVParser(new FileReader(file), csvFileFormat);)

		{

			List<CustomerStatements> customerStatements = new ArrayList<>();
			parser.forEach(record -> {
				CustomerStatements customerRecords = new CustomerStatements(Integer.parseInt(record.get("Reference")),
						record.get("AccountNumber"), record.get("Description"),
						new BigDecimal(record.get("Start Balance")), new BigDecimal(record.get("Mutation")),
						new BigDecimal(record.get("End Balance")));

				customerStatements.add(customerRecords);
			});

			isFileGenerated = validateAndGenerateFinalReport(customerStatements, fileType);
		} catch (IOException e) {
			logger.info("File Not Found" + e.getMessage());

		}
		return isFileGenerated;
	}

}
