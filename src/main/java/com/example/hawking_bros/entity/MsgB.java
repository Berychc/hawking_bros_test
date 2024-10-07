package com.example.hawking_bros.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MsgB {

    @JsonProperty("txt")
    @Schema(description = "Некое текстовое сообщение",
            required = true,
            example = "Привет")
    private String txt;

    @JsonProperty("createDt")
    @Schema(description = "Дата формирования сообщения",
            required = true,
            format = "rfc3339",
            example = "2020-06-10T10:15:30Z")
    private LocalDateTime createDt;

    @JsonProperty("currentTemp")
    @Schema(description = "Температура по Цельсию",
            required = true,
            example = "28")
    private Integer currentTemp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsgB msgB = (MsgB) o;
        return Objects.equals(txt, msgB.txt) && Objects.equals(createDt, msgB.createDt) && Objects.equals(currentTemp, msgB.currentTemp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(txt, createDt, currentTemp);
    }

    @Override
    public String toString() {
        return "MsgB{" +
                "txt='" + txt + '\'' +
                ", createDt=" + createDt +
                ", currentTemp=" + currentTemp +
                '}';
    }
}
