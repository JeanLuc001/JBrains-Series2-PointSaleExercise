package pointOfSale;

import java.util.Optional;

public interface Inventory
{
	public Optional<Price> getProductPriceFromCode(String code);

	public void addProduct(String code, Price price);
}