package org.rabcobank.report.business;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.rabcobank.report.enums.FileTypes;

/**
 * @author vinesh
 *
 */
public class CsvFileParserTest {

	@Test
	public void parsertest() {
		CsvFileParser csvFileParser = new CsvFileParser();
		Boolean isFileGenerated = csvFileParser.parser(FileTypes.CSV);
		File file = new File(FileTypes.CSV.getOutputFileName());
		assertAll("csvParser", () -> assertEquals(true, isFileGenerated), () -> assertTrue(file.exists())

		);

	}

	@Test
	public void parserTestException() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			CsvFileParser csvFileParser = new CsvFileParser();
			csvFileParser.parser(FileTypes.XML);
		});

	}

}
