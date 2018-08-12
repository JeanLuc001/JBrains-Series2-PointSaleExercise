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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (price == null)
		{
			if (other.price != null)
				return false;
		}
		else if (!price.equals(other.price))
			return false;
		return true;
	}
}