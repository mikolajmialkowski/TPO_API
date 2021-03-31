package web1.model;

import java.util.Map;

public class Weather {
    Map<String, Object > weather;
    Map<String, Object > mainMap;
    Map<String, Object > windMap;

    public Weather(Map<String, Object> weather, Map<String, Object> mainMap, Map<String, Object> windMap) {
        this.weather = weather;
        this.mainMap = mainMap;
        this.windMap = windMap;
    }

    public Map<String, Object> getWeather() {
        return weather;
    }

    public Map<String, Object> getMainMap() {
        return mainMap;
    }

    public Map<String, Object> getWindMap() {
        return windMap;
    }
}
