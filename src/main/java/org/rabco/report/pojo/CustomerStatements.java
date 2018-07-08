package org.rabco.report.pojo;

import java.math.BigDecimal;

public class CustomerStatements {
	/**
	 * 
	 */
	private Integer reference;
	private String accountNumber;
	private BigDecimal start_Balance;
	private BigDecimal mutation;	
	private String description;
	
	public CustomerStatements(Integer reference, String accountNumber,String description, BigDecimal start_Balance, BigDecimal mutation
			, BigDecimal end_Balance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.start_Balance = start_Balance;
		this.mutation = mutation;
		this.description = description;
		this.end_Balance = end_Balance;
	}
	private BigDecimal end_Balance;
	
	public Integer getRefernece() {
		return reference;
	}
	public void setRefernece(Integer reference) {
		this.reference = reference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getStart_Balance() {
		return start_Balance;
	}
	public void setStart_Balance(BigDecimal start_Balance) {
		this.start_Balance = start_Balance;
	}
	public BigDecimal getMutation() {
		return mutation;
	}
	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getEnd_Balance() {
		return end_Balance;
	}
	public void setEnd_Balance(BigDecimal end_Balance) {
		this.end_Balance = end_Balance;
	}
	@Override
	public String toString() {
		return "CustomerStatements [reference=" + reference + ", accountNumber=" + accountNumber + ", start_Balance="
				+ start_Balance + ", mutation=" + mutation + ", description=" + description + ", end_Balance="
				+ end_Balance + "]";
	}

}
