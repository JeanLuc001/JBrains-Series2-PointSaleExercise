package pointOfSale;

import java.util.Optional;

public class SimpleInventory implements Inventory
{
	private Price price;

	@Override
	public Optional<Price> getProductPriceFromCode(String code)
	{
		return Optional.of(price);
	}

	@Override
	public void addProduct(String code, Price price)
	{
		this.price = price;
	}
}