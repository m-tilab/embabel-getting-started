package com.example.embabel.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.agent.domain.io.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;

@Agent(name = "city-attraction-provider",
        description = "Provides City information and Attractions",
        version = "1.0.0",
        beanName = "cityAttractionsProviderAgent")
public class CityAttractionsInfoProviderAgent {
    private static final Logger log = LoggerFactory.getLogger(CityAttractionsInfoProviderAgent.class);

    @Action
    public CityBasicInfo getCityBasicInfo(UserInput userInput, OperationContext context) {
        return context.ai()
                .withDefaultLlm()
                //.withLlm(OpenAiModels.GPT_41)
                //.withLlm("qwen3:8b")
                //.withFirstAvailableLlmOf("qwen3:8b", OpenAiModels.GPT_41)
                //.withLlmByRole("faster")
                .createObjectIfPossible(
                        """
                        Create a CityBasicInfo from this user input, extracting their details:
                        %s""".formatted(userInput.getContent()),
                        CityBasicInfo.class
                );
    }

    @Action
    public CityPopulationInfo getCityPopulationInfo(UserInput userInput, OperationContext context) {
        return context.ai()
                .withDefaultLlm()
                //.withLlm(OpenAiModels.GPT_41)
                //.withLlm("qwen3:8b")
                //.withFirstAvailableLlmOf("qwen3:8b", OpenAiModels.GPT_41)
                //.withLlmByRole("faster")
                .createObjectIfPossible(
                        """
                        Create a CityPopulationInfo from this user input, extracting their details:
                        %s""".formatted(userInput.getContent()),
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

    public record CityBasicInfo(String name, String country) {
    }

    public record CityPopulationInfo(int population) {
    }

    public record CityAttractions(List<String> attractions) {
    }


    public record City(String name, String country, int population, List<String> attractions) {
        City(CityBasicInfo info, CityPopulationInfo populationInfo, CityAttractions attractions) {
            this(info.name(), info.country(), populationInfo.population(), attractions.attractions());
        }
    }
}