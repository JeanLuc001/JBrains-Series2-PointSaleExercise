package pointOfSale;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PriceTest
{
	@Test(expected = IllegalArgumentException.class)
	public void when_negative_then_exception()
	{
		new Price("-1.00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void when_invalid_number_then_exception() throws Exception
	{
		new Price("blabla");
	}

	@Test
	public void when_format_then_expect_correct_string() throws Exception
	{
		Price sut = new Price("12.99");
		assertEquals("$ 12.99", sut.toString());
	}

	@Test
	public void when_add_two_prices_then_expect_correct_string() throws Exception
	{
		Price s1 = new Price("12.99");
		Price s2 = new Price("5.29");
		Price s3 = s1.add(s2);
		assertEquals("$ 18.28", s3.toString());
	}

	@Test
	public void test_equal() throws Exception
	{
		Price s1 = new Price("5.29");
		Price s2 = new Price("5.29");
		assertEquals(s1, s2);
	}
}