package org.rabcobank.report.enums;

/**
 * @author vinesh Enums to have filenames mapped to file types
 */
public enum FileTypes {
	CSV("records.csv", "ValidatedCSVReports.csv"), XML("records.xml", "ValidatedXMLReports.csv");

	private String inputFileName;
	private String outputFileName;

	private FileTypes(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

}
