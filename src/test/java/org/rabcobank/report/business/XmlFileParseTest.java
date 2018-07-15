package org.rabcobank.report.business;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Test;
import org.rabcobank.report.enums.FileTypes;

/**
 * @author vinesh
 *
 */
public class XmlFileParseTest {
	@Test
	public void parsertest() {
		XmlFileParser xmlFileParser = new XmlFileParser();
		Boolean isFileGenerated = xmlFileParser.parser(FileTypes.XML);
		File file = new File(FileTypes.XML.getOutputFileName());
		assertAll("csvParser", () -> assertEquals(true, isFileGenerated), () -> assertTrue(file.exists())

		);

	}

}
