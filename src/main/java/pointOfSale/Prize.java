package pointOfSale;

import java.math.BigDecimal;

public class Prize
{
//	private final BigDecimal price;

	public Prize(String priceAsString)
	{
		throw new IllegalArgumentException("Prize must be greater than zero.");
//		price = prizeFromString(priceAsString);
	}

//	private BigDecimal prizeFromString(String priceAsString)
//	{
//		BigDecimal number = BigDecimal(priceAsString);
//		if (number.signum() == -1)
//		{
//			throw new IllegalArgumentException("Prize must be greater than zero.");
//		}
//	}
}