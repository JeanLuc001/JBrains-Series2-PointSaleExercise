package pointOfSale;

import java.math.BigDecimal;
import java.util.Optional;

public interface Inventory
{
	Optional<BigDecimal> getProductPriceFromCodeAsBigDecimal(String code);

	Optional<Price> getProductPriceFromCode(String code);
}