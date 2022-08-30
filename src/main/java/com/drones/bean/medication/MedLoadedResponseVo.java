package com.drones.bean.medication;


import java.util.List;

public record MedLoadedResponseVo(int totalItems, List<MedItemVo> items) {

    public MedLoadedResponseVo(List<MedItemVo> items) {
        this(items.size(), items);
    }
}
