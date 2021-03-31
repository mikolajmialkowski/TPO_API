package web1.model;

import java.util.Map;

public class CurrencyExchange {

    Map<String,Object> currency;

    public CurrencyExchange(Map<String, Object> currency) {
        this.currency = currency;
    }

    public Map<String, Object> getCurrency() {
        return currency;
    }
}
