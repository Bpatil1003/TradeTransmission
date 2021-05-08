package com.tradeStore.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tradeStore.bean.Trade;

public interface TradeStoreDao {

	public  static Map<String,Trade> tradeMap =new ConcurrentHashMap<>();
	
	public Trade addNewTrade(Trade trade);
	
	public Trade updateTrade(Trade trade);
	
	public Map<String,Trade> loadTrades();
	
	public void updateExpiryFlagOfTrade();
	
	public List<Trade> findAll();
	 
}
