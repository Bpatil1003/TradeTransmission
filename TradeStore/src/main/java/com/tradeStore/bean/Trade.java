package com.tradeStore.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tradeStore.exception.InvalidTradeException;

public class Trade {

	private String tradeId;

	private int version;

	private String counterParty;

	private String bookId;

	private Date maturityDate;

	private Date createdDate;

	private String expiredFlag;

	public Trade(String tradeId, int version, String counterParty, String bookId, String maturityDate){
		try {
			this.tradeId = tradeId;
			this.version = version;
			this.counterParty = counterParty;
			this.bookId = bookId;
			this.maturityDate = new SimpleDateFormat("dd/MM/yyyy").parse(maturityDate);
			this.createdDate = new Date(System.currentTimeMillis());
			this.expiredFlag = "N";
		} catch (ParseException e) {
			throw new InvalidTradeException(tradeId);
		}
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpiredFlag() {
		return expiredFlag;
	}

	public void setExpiredFlag(String expiredFlag) {
		this.expiredFlag = expiredFlag;
	}

	@Override
	public String toString() {
		return "Trade{" + "tradeId='" + tradeId + '\'' + ", version=" + version + ", counterParty='" + counterParty
				+ '\'' + ", bookId='" + bookId + '\'' + ", maturityDate=" + maturityDate + ", createdDate="
				+ createdDate + ", expiredFlag='" + expiredFlag + '\'' + '}';
	}
}
