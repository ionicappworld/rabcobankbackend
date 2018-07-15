package org.rabcobank.report.utils;

/**
 * @author vinesh
 *Util class to have all validations logic related to Reports 
 */
import java.util.List;

import org.rabco.report.pojo.CustomerStatements;

public class ReportsValidationUtil {

	public String validateDuplicate(CustomerStatements stats, List<Integer> reference) {
		if (reference.contains(stats.getReference())) {
			return ("Duplicate");
		} else {
			return ("Unique");
		}
	}

	public String validateEndBalance(CustomerStatements stats) {

		return ((stats.getStartBalance().add(stats.getMutation())).compareTo(stats.getEndBalance()) == 0
				? "End Balance is Correct" : "End balance is Wrong");
	}

}
