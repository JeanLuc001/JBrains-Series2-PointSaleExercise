package pointOfSale;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleInventory implements Inventory
{
	private final Map<String, Price> priceByCode;

	public SimpleInventory()
	{
		priceByCode = new HashMap<>();
	}

	@Override
	public Optional<Price> getProductPriceFromCode(String code)
	{
		return Optional.ofNullable(priceByCode.get(code));
	}

	@Override
	public void addProduct(String code, Price price)
	{
		if (code == null || price == null)
		{
			throw new IllegalArgumentException("neither code nor price can be null");
		}
		priceByCode.put(code, price);
	}
}