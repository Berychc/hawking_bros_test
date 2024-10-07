package com.example.hawking_bros.entity;

import com.example.hawking_bros.enums.Lng;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MsgA {

    @JsonProperty("msg")
    @Schema(description = "Некое текстовое сообщение",
            required = true,
            example = "Привет")
    private String msg;

    @JsonProperty("lng")
    @Schema(description = "Язык сообщения",
            required = true,
            example = "ru")
    private Lng lng;

    private Coordinates coordinates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsgA msgA = (MsgA) o;
        return Objects.equals(msg, msgA.msg) && lng == msgA.lng && Objects.equals(coordinates, msgA.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, lng, coordinates);
    }

    @Override
    public String toString() {
        return "MsgA{" +
                "msg='" + msg + '\'' +
                ", lng=" + lng +
                ", coordinates=" + coordinates +
                '}';
    }
}