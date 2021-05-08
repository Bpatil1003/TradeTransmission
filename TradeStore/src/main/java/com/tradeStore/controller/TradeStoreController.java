package com.tradeStore.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradeStore.bean.Trade;
import com.tradeStore.exception.InvalidTradeException;
import com.tradeStore.service.TradeService;

@RestController
@RequestMapping("/TradeStore")
public class TradeStoreController {

	@Autowired
	TradeService tradeService;

	@Value("${trade.add.success}")
	private String addSuccess;

	@Value("${trade.success}")
	private String validationSuccess;

	@PostMapping(value = "/addTrade")
	public String addNewtrade(@RequestBody Trade trade) {
		String response = tradeService.isValidTrade(trade);
		if (response.equals(validationSuccess)) {
			response = addSuccess + " " + trade.toString();
		} else {
			throw new InvalidTradeException(trade.getTradeId() + "  " + response);
		}
		return response;
	}

	@Scheduled(cron = "${trade.expiry.schedule}")
	public void updateExpiryFlag() {
		//System.out.println("I am called : " + new Date());
		tradeService.updateExpiryFlagOfTrade();
	}

	@GetMapping("/trade")
	public List<Trade> findAllTrades() {
		return tradeService.findAll();
	}
}
