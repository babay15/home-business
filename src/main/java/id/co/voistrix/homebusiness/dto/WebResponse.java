package id.co.voistrix.homebusiness.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class WebResponse {
    private Integer code;
    private String status;
    private Object data;
}
