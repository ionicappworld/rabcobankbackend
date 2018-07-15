package org.rabcobank.report.helper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.rabco.report.pojo.CustomerStatements;
import org.rabcobank.report.enums.FileTypes;

/**
 * @author vinesh
 *
 */
public class RabcoGenericParserTest {
	List<CustomerStatements> stataments;

	@Before
	public void setUp() {
		stataments = new ArrayList<CustomerStatements>();
		CustomerStatements cs1 = new CustomerStatements(1234, "testing1", "testing2", new BigDecimal(11),
				new BigDecimal(11), new BigDecimal(22));
		CustomerStatements cs21 = new CustomerStatements(1234, "testing2", "testing3", new BigDecimal(3),
				new BigDecimal(-1), new BigDecimal(2));
		stataments.add(cs1);
		stataments.add(cs21);
	}

	@Test
	public void validateAndGenerateFinalReportTest() {

		RabcoGenericParser rabcoGeneriMock = Mockito.mock(RabcoGenericParser.class, Mockito.CALLS_REAL_METHODS);

		assertEquals(true, rabcoGeneriMock.validateAndGenerateFinalReport(stataments, FileTypes.CSV));
		assertEquals(true, rabcoGeneriMock.validateAndGenerateFinalReport(stataments, FileTypes.XML));

	}

}
