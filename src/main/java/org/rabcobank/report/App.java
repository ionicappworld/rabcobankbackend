package org.rabcobank.report;

import org.rabcobank.report.business.CsvFileParser;
import org.rabcobank.report.business.XmlFileParse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	CsvFileParser CsvFileParserObj = new CsvFileParser();
    	CsvFileParserObj.readCsvFileData();
    	XmlFileParse xmlFileParseObj = new XmlFileParse(); 
    	xmlFileParseObj.readAndValidateXml();
    }
}
