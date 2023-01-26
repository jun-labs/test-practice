package study.test.practice.web.weather.presentation;

import java.util.HashMap;
import java.util.Map;

/**
 * T1H : 기온(℃)
 * RN1 : 1시간 강수량(범주(1 mm))
 * SKY : 하늘상태(코드값)
 * UUU : 동서바람성분(m/s)
 * VVV : 남북바람성분(m/s)
 * REH : 습도(%)
 * PTY : 강수형태(코드값)
 * LGT : 낙뢰(코드값)
 * VEC : 풍향(deg)
 * WSD : 풍속(m/s)
 */
public class Convertor {

    private static final Map<String, String> map = new HashMap<>();
    private static final Map<String, String> rainyType = new HashMap<>();

    static {
        rainyType.put("0", "없음");
        rainyType.put("1", "비");
        rainyType.put("2", "비/눈");
        rainyType.put("3", "눈");
        rainyType.put("4", "소나기");
        rainyType.put("5", "빗방울");
        rainyType.put("6", "빗방울/눈날림");
        rainyType.put("7", "눈날림");

        map.put("PTY", "rainyType");
        map.put("T1H", "temperature");
        map.put("SKY", "stateOfTheSky");
        map.put("RN1", "rainPerHour");
        map.put("REH", "humidity");
        map.put("LGT", "thunderbolt");
        map.put("VEC", "windDirection(m/s)");
        map.put("WSD", "windSpeed");
        map.put("VVV", "windSpeed(North-South, m/s)");
        map.put("UUU", "windSpeed(West-East, m/s)");
    }

    public static String getValue(String key) {
        return map.get(key);
    }

    public static String getConvertTo(String obsrValue) {
        return rainyType.get(obsrValue);
    }
}

