package study.test.practice.web.weather.outter.dto.response;

public class WeatherOpenApiResponseBody {

    private String dataType;
    private WeatherOpenApiResponseBodyItems items;
    private int pageNo;
    private int numOfRows;
    private int totalCount;

    private WeatherOpenApiResponseBody() {
    }

    public WeatherOpenApiResponseBody(String dataType,
                                      WeatherOpenApiResponseBodyItems items,
                                      int pageNo,
                                      int numOfRows,
                                      int totalCount) {
        this.dataType = dataType;
        this.items = items;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.totalCount = totalCount;
    }

    public String getDataType() {
        return dataType;
    }

    public WeatherOpenApiResponseBodyItems getItems() {
        return items;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
