package pointOfSale;

import java.util.Optional;

public interface Inventory
{
	Optional<Price> getProductPriceFromCode(String code);
}