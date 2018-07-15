package org.rabcobank.report.facade;

/**
 * @author vinesh
 *
 */
import org.rabcobank.report.business.CsvFileParser;
import org.rabcobank.report.business.XmlFileParser;
import org.rabcobank.report.enums.FileTypes;

public class ReportParserFacade {
	private ReportParserFacade() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Facade to invoke file parsers based on file types this can be extended to
	 * support multiple file types in future
	 *
	 */
	public static void parseAndGenerateReport(FileTypes fileType) {
		switch (fileType) {
		case XML:
			XmlFileParser xmlFileParse = new XmlFileParser();
			xmlFileParse.parser(fileType);
			break;
		case CSV:
			CsvFileParser csvFileParser = new CsvFileParser();
			csvFileParser.parser(fileType);
			break;
		}

	}

}
