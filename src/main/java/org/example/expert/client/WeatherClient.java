package org.example.expert.client;

import org.example.expert.client.dto.WeatherDto;
import org.example.expert.domain.common.exception.ServerException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherClient {

    private final RestTemplate restTemplate;

    public WeatherClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * 🚀 오늘 날씨 정보를 반환하는 메서드
     * @return 오늘 날씨를 String 으로 반환
     */
    public String getTodayWeather() {
        // 1. 날씨 데이터 요청을 보내고 WeatherDTO 배열로 가져옴
        ResponseEntity<WeatherDto[]> responseEntity =
                restTemplate.getForEntity(buildWeatherApiUri(), WeatherDto[].class);
        WeatherDto[] weatherArray = responseEntity.getBody();

        // 2. 가져오기 실패하면 예외 처리
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new ServerException("날씨 데이터를 가져오는데 실패했습니다. 상태 코드: " + responseEntity.getStatusCode());
        }

        if (weatherArray == null || weatherArray.length == 0) {
            throw new ServerException("날씨 데이터가 없습니다.");
        }

        // 3. 현재 날짜를 MM-dd 로 가져오기
        String today = getCurrentDate();

        // 4. 각각의 DTO 에 접근하여 오늘날짜의 데이터를 문자열로 반환
        for (WeatherDto weatherDto : weatherArray) {
            if (today.equals(weatherDto.getDate())) {
                return weatherDto.getWeather();
            }
        }

        // 5. 오늘 날짜의 데이터가 없을 경우, 예외 처리
        throw new ServerException("오늘에 해당하는 날씨 데이터를 찾을 수 없습니다.");
    }

    /**
     * 🚀 외부 날씨 API 의 URI 를 생성하여 반환하는 메서드
     * @return 외부 날씨 API URI
     */
    private URI buildWeatherApiUri() {
        return UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json")
                .encode()
                .build()
                .toUri();
    }

    /**
     * 🚀 현재 날짜를 MM-dd 패턴으로 반환하는 메서드
     * @return 현재 날짜를 문자열로 반환
     */
    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        return LocalDate.now().format(formatter);
    }
}
