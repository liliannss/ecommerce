package br.com.devs.javagirl.user.models.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ErrorDTO {

    private HttpStatus status;

    private String path;

    private String method;

    private List<String> message;

    private String errorId;

    private Date instantCreated;
}