package pointOfSale;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleInventoryTest
{
	@Test
	public void when_adding_product_then_price_available() throws Exception
	{
		Inventory sut = new SimpleInventory();
		Price price = new Price("55.73");
		String code = "00258288";
		sut.addProduct(code, price);
		assertEquals(price, sut.getProductPriceFromCode(code).get());
	}
}