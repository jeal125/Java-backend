package conversormoneda.service;

import conversormoneda.model.Currency;

public interface CurrencyConverterService {
    double convert(Currency from, Currency to, double amount);
    boolean isAvailable();
}
