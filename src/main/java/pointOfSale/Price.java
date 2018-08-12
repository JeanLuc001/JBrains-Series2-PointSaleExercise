package pointOfSale;

import java.math.BigDecimal;

public class Price
{
	private final BigDecimal price;

	public Price(String priceAsString)
	{
		price = prizeFromString(priceAsString);
	}

	private Price(BigDecimal price)
	{
		this(price.toString());
	}

	private BigDecimal prizeFromString(String priceAsString)
	{
		BigDecimal number;
		try
		{
			number = new BigDecimal(priceAsString);
		}
		catch (NumberFormatException e)
		{
			throw new IllegalArgumentException("Prize must be greater than zero.");
		}
		if (number.signum() == -1)
		{
			throw new IllegalArgumentException("Prize must be greater than zero.");
		}
		return number;
	}

	@Override
	public String toString()
	{
		return "$ " + price.toString();
	}

	public Price add(Price other)
	{
		return new Price(price.add(other.price));
	}
}