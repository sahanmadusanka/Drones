package com.drones.bean.medication;

import com.drones.repository.projection.MedLoadedProjection;

public record MedItemVo(Long id, String code, String name, int quantity, double totalWeight, String imagePath) {

    public MedItemVo(MedLoadedProjection medLoadedProjection) {
        this(medLoadedProjection.getId(),
                medLoadedProjection.getCode(),
                medLoadedProjection.getName(),
                medLoadedProjection.getQuantity(),
                calculateTotalWeight(medLoadedProjection.getQuantity(), medLoadedProjection.getWeight()),
                medLoadedProjection.getImage());
    }

    private static double calculateTotalWeight(Integer qty, Double medWeight) {
        var quantity = qty != null ? qty : 0;
        var weight = medWeight != null ? medWeight : 0;
        return quantity * weight;
    }

}
