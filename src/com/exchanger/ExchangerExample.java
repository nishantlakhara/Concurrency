package com.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
	public static void main(String[] args) {
		Exchanger exchanger = new Exchanger();

		ExchangerRunnable exchangerRunnable1 =
		        new ExchangerRunnable(exchanger, "A");

		ExchangerRunnable exchangerRunnable2 =
		        new ExchangerRunnable(exchanger, "B");
		
//		ExchangerRunnable exchangerRunnable3 =
//		        new ExchangerRunnable(exchanger, "C");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();
//		new Thread(exchangerRunnable3).start();
	}
}


