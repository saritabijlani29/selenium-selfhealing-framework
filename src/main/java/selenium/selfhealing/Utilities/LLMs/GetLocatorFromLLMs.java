package selenium.selfhealing.Utilities.LLMs;

import com.fasterxml.jackson.databind.ObjectMapper;
import selenium.selfhealing.Utilities.Model.LocatorSuggestion;

import java.io.IOException;

public class GetLocatorFromLLMs {


    public static LocatorSuggestion GetHealedLocator(LLMClient llmclient, String pageSource, String locatorType, String originalLocator) throws IOException, InterruptedException {
        String prompt = "The Web Element with locatorType : " + locatorType + " ans its locator : " + originalLocator + "cannot found in the page." +
                "Based on the current page source, suggested the alternative locators that works" +
                "Important: Return a valid Json object with the keys id, name, xpath, cssSelector, className, linkText." +
                "Format the proper json with the double quotes Do not return any explanations or comments in the final output and just return json object." +
                "Page source" + pageSource;

        String response = llmclient.callLLMModel(prompt);
        System.out.println("response------- " + response);

        ObjectMapper mapper = new ObjectMapper();
        LocatorSuggestion LocatorSuggestion = mapper.readValue(response, LocatorSuggestion.class);
        return LocatorSuggestion;
    }
}
