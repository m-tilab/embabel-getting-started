package com.embabel.example.model;

import java.util.List;

public record City(String name, String country, int population, List<String> attractions) {
    public City(CityBasicInfo info, CityPopulationInfo populationInfo, CityAttractions attractions) {
        this(info.name(), info.country(), populationInfo.population(), attractions.attractions());
    }
}
