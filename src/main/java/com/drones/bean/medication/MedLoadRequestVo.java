package com.drones.bean.medication;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public record MedLoadRequestVo(@Valid @NotEmpty List<MedLoadVo> items) {

}
