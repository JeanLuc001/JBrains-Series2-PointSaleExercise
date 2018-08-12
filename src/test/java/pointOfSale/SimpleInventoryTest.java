package pointOfSale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class SimpleInventoryTest
{
	Inventory sut;
	
	@Before
	public void init()
	{
		sut = new SimpleInventory();
	}
	
	@Test
	public void when_adding_product_then_price_available() throws Exception
	{
		Price price = new Price("55.73");
		String code = "00258288";
		sut.addProduct(code, price);
		assertEquals(price, sut.getProductPriceFromCode(code).get());
	}
	
	@Test
	public void when_adding_two_products_then_prices_are_available() throws Exception
	{

		Price price1 = new Price("55.73");
		String code1 = "00258288";
		Price price2 = new Price("4.3");
		String code2 = "1111111";
		sut.addProduct(code1, price1);
		sut.addProduct(code2, price2);
		assertEquals(price1, sut.getProductPriceFromCode(code1).get());
		assertEquals(price2, sut.getProductPriceFromCode(code2).get());
	}
	
	@Test
	public void when_querying_prize_for_non_existing_product_then_return_empty_Optional() throws Exception
	{
		String code = "02258288";
		assertFalse(sut.getProductPriceFromCode(code).isPresent());
	}
}