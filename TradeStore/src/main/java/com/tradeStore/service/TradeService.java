package com.tradeStore.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tradeStore.bean.Trade;
import com.tradeStore.dao.TradeStoreDao;

@Service
public class TradeService {

	@Autowired
	TradeStoreDao tradeStoreDao;

	@Value("${trade.version.error}")
	private String versionValidationError;

	@Value("${trade.maturitydate.error}")
	private String maturittyDateValidationError;

	@Value("${trade.success}")
	private String validationSuccess;

	public boolean checkIfTradeEmpty() {
		return TradeStoreDao.tradeMap.isEmpty();
	}

	/*trade validation before adding and updating trade*/
	public String isValidTrade(Trade trade) {
		if (validateMaturityDate(trade)) {
			if (!checkIfTradeEmpty()) {
				Trade exsitingTrade = TradeStoreDao.tradeMap.get(trade.getTradeId());
				if (null != exsitingTrade) {
					if (validateVersion(trade, exsitingTrade)) {
						//If the version is same it will override the existing record.
						tradeStoreDao.updateTrade(trade);
					} else {
						//During transmission if the lower version is being received by the store it will reject the trade and throw an exception.
						return versionValidationError;
					}
				} else {
					tradeStoreDao.addNewTrade(trade);
				}
			} else {
				tradeStoreDao.addNewTrade(trade);
			}
		} else {
			//Store should not allow the trade which has less maturity date then today date.
			return maturittyDateValidationError;
		}
		return validationSuccess;
	}

	/* Trade version validation*/
	private boolean validateVersion(Trade trade, Trade exsitingTrade) {
		System.out.println("exsitingTrade" + exsitingTrade);
		if (trade.getVersion() < exsitingTrade.getVersion()) {
			return false;
		}
		return true;
	}

	/* Trade Maturity Date validation*/
	private boolean validateMaturityDate(Trade trade) {
		Date currentDate = new Date();
		return (currentDate.compareTo(trade.getMaturityDate()) <= 0);
	}

	/* Trade Expiry scheduler*/
	public void updateExpiryFlagOfTrade() {
		tradeStoreDao.updateExpiryFlagOfTrade();
	}

	public List<Trade> findAll() {
		return tradeStoreDao.findAll();
	}

}
