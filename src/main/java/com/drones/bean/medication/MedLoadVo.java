package com.drones.bean.medication;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public record MedLoadVo(
        @Pattern(regexp = "^([A-Z]*)(_*)(\\d*)", message = "Invalid medication code format") String code,
        @Min(value = 1, message = "Order value must be higher than 0") Integer quantity) {

}
