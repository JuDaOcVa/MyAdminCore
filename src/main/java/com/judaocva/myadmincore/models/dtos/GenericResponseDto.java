package com.judaocva.myadmincore.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponseDto {

    private String message;

    private Object data;

    private boolean success;

    @Override
    public String toString() {
        return "GenericResponseDto{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
