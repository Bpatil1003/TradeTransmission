package com.tradeStore.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tradeStore.bean.Trade;

@Repository
public class TradeStoreDaoImpl implements TradeStoreDao {

	@Override
	public Trade addNewTrade(Trade trade) {
		tradeMap.put(trade.getTradeId(), trade);
		return trade;
	}

	@Override
	public Trade updateTrade(Trade trade) {
		tradeMap.replace(trade.getTradeId(), trade);
		return trade;
	}

	@Override
	public Map<String, Trade> loadTrades() {
		try {
			tradeMap.put("T1", new Trade("T1", 1, "CP-1", "B1", "20/05/2020"));
			tradeMap.put("T2", new Trade("T2", 2, "CP-2", "B1", "20/05/2021"));
			tradeMap.put("T3", new Trade("T3", 3, "CP-3", "B2", "20/05/2014"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tradeMap;
	}

	@Override
	public void updateExpiryFlagOfTrade() {
		Date currentDate = new Date();
		tradeMap.forEach((k, v) -> {
			if (currentDate.compareTo(v.getMaturityDate()) > 0) {
				v.setExpiredFlag("Y");
			}
		});
	}

	@Override
	public List<Trade> findAll() {
		return tradeMap.values().stream().collect(Collectors.toList());
	}
}
