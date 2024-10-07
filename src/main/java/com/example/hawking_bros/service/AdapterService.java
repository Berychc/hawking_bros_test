package com.example.hawking_bros.service;

import com.example.hawking_bros.entity.Coordinates;
import com.example.hawking_bros.entity.MsgA;
import com.example.hawking_bros.entity.MsgB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
public class AdapterService {

    private final WebClient webClient;

    @Value("${gismeteo.api.token}")
    private String gismeteoApiToken;

    public AdapterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.gismeteo.ru").build();
    }

    public MsgB enrichMessage(MsgA message) {
        Coordinates coordinates = message.getCoordinates();
        Integer temperature = getWeatherData(coordinates);

        if (temperature == null) {
            throw new RuntimeException("Ошибка получения данных о погоде");
        }

        MsgB msgB = new MsgB();
        msgB.setTxt(message.getMsg());
        msgB.setCreateDt(LocalDateTime.now());
        msgB.setCurrentTemp(temperature);
        return msgB;
    }

    public Integer getWeatherData(Coordinates coordinates) {
        try {
            return webClient.get()
                    .uri("/v2/weather/current/?latitude={lat}&longitude={lon}", coordinates.getLatitude(), coordinates.getLongitude())
                    .header("X-Gismeteo-Token", gismeteoApiToken)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .onErrorReturn(-1)
                    .block();
        } catch (Exception e) {
            System.err.println("Ошибка получения данных погоды: " + e.getMessage());
            return null;
        }
    }
}
