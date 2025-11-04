package com.embabel.example.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.example.model.City;
import com.embabel.example.model.CityAttractions;
import com.embabel.example.model.CityBasicInfo;
import com.embabel.example.model.CityPopulationInfo;
import com.embabel.example.model.EmbabelEnquiryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@Agent(name = "city-attraction-provider",
        description = "Provides City information and Attractions",
        version = "1.0.0",
        beanName = "cityAttractionsProviderAgent")
public class CityAttractionsInfoProviderAgent {
    private static final Logger log = LoggerFactory.getLogger(CityAttractionsInfoProviderAgent.class);

    @Action
    public CityBasicInfo getCityBasicInfo(EmbabelEnquiryRequest enquiryRequest, OperationContext context) {
        return context.ai()
                .withDefaultLlm()
                //.withLlm(OpenAiModels.GPT_41)
                //.withLlm("qwen3:8b")
                //.withFirstAvailableLlmOf("qwen3:8b", OpenAiModels.GPT_41)
                //.withLlmByRole("faster")
                .createObjectIfPossible(
                        """
                        Create a CityBasicInfo from this user input, extracting their details:
                        %s""".formatted(enquiryRequest.request()),
                        CityBasicInfo.class
                );
    }

    @Action
    public CityPopulationInfo getCityPopulationInfo(CityBasicInfo cityBasicInfo, OperationContext context) {
        return context.ai()
                .withDefaultLlm()
                //.withLlm(OpenAiModels.GPT_41)
                //.withLlm("qwen3:8b")
                //.withFirstAvailableLlmOf("qwen3:8b", OpenAiModels.GPT_41)
                //.withLlmByRole("faster")
                .createObjectIfPossible(
                        """
                        Create a CityPopulationInfo from this user input, extracting their details:
                        %s""".formatted(cityBasicInfo.name()),
                        CityPopulationInfo.class
                );
    }

    @AchievesGoal(description = "Provides City information for the given city name")
    @Action
    public City getCityInfo(CityBasicInfo basicInfo, CityPopulationInfo populationInfo, OperationContext context) {
        CityAttractions cityAttractions = context.ai()
                .withDefaultLlm()
                //.withLlmByRole("better")
                .createObjectIfPossible(
                        """
                                Get the Attractions of the City: %s.
                                Create a CityAttractions from that info.
                                """.formatted(basicInfo.name()),
                        CityAttractions.class
                );
        Assert.notNull(cityAttractions, "CityAttractions cannot be null");
        return new City(basicInfo, populationInfo, cityAttractions);
    }


}