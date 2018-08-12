package pointOfSale;

import org.junit.Test;

public class PrizeTest
{
	@Test(expected = IllegalArgumentException.class)
	public void when_negative_then_exception()
	{
		Prize sut = new Prize("-1.00");
	}
}