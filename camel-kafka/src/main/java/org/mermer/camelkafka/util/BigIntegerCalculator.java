package org.mermer.camelkafka.util;

import java.math.BigInteger;

public class BigIntegerCalculator {

	public BigInteger add(BigInteger a, BigInteger b){

		BigInteger c = BigInteger.valueOf(1000L);
		System.out.println(c);
		return a.add(b);

	}

	public static void main(String[] args) {
		BigIntegerCalculator c = new BigIntegerCalculator();
		System.out.println(c.add(BigInteger.valueOf(1000L), BigInteger.valueOf(2000L)));

	}
}
