package org.rabcobank.report;

import org.rabcobank.report.enums.FileTypes;
import org.rabcobank.report.facade.ReportParserFacade;

/**
 * @author vinesh
 *
 */
public class App {
	public static void main(String[] args) {
		ReportParserFacade.parseAndGenerateReport(FileTypes.CSV);
		ReportParserFacade.parseAndGenerateReport(FileTypes.XML);

	}
}
