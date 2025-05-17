package conversormoneda.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import conversormoneda.exception.CurrencyConversionException;
import conversormoneda.model.Currency;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ExchangeRateClient implements CurrencyConverterService {
    private static final String API_KEY = "9ac1fb2c6209abdba31700c0"; // Reemplaza con tu API key real
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + "9ac1fb2c6209abdba31700c0" + "/latest/";

    @Override
    public double convert(Currency from, Currency to, double amount) {
        try {
            JsonObject rates = getExchangeRates(from.getCode());
            double rate = rates.getAsJsonObject("conversion_rates")
                    .get(to.getCode())
                    .getAsDouble();
            return amount * rate;
        } catch (Exception e) {
            throw new CurrencyConversionException("Error al convertir moneda: " + e.getMessage());
        }
    }

    @Override
    public boolean isAvailable() {
        try {
            getExchangeRates("USD");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private JsonObject getExchangeRates(String baseCurrency) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL + baseCurrency);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                return JsonParser.parseString(result).getAsJsonObject();
            }
        } catch (Exception e) {
            throw new CurrencyConversionException("Error al obtener tipos de cambio: " + e.getMessage());
        }
    }
}