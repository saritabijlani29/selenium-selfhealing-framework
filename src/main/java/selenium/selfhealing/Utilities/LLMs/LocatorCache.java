package selenium.selfhealing.Utilities.LLMs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import selenium.selfhealing.Utilities.Model.HealedLocator;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Store
//Get
//Load
public class LocatorCache {

    public String cacheFilePath;
    public List<HealedLocator> cache;

    public LocatorCache() throws IOException {
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir, "healedlocator.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        cacheFilePath = file.getAbsolutePath();
        cache = loadCacheFromFile();
    }

    public List<HealedLocator> loadCacheFromFile() {
        try {
            FileInputStream inputStream = new FileInputStream(cacheFilePath);
            if (inputStream.available() == 0) {
                return new ArrayList<>();
            }else{
                ObjectMapper mapper = new ObjectMapper();
                cache =  mapper.readValue(inputStream, new TypeReference<List<HealedLocator>>(){});
                return cache != null ? cache : new ArrayList<>();
            }
        } catch (Exception e) {
            throw new RuntimeException("File Loading failed", e);
        }
    }

    public void saveHealedLocatorIntoCache(String originalLocator, String workingLocatorType, String workingLocatorValue) {
        Date date = new Date();

        HealedLocator existingHealedLocator = cache.stream()
                .filter(item -> item.getOriginalLocator().equalsIgnoreCase(originalLocator))
                .findFirst().orElse(null);

        if (existingHealedLocator != null) {
            existingHealedLocator.setOriginalLocator(originalLocator);
            existingHealedLocator.setWorkingLocatorType(workingLocatorType);
            existingHealedLocator.setWorkingLocatorValue(workingLocatorValue);
            existingHealedLocator.setHealedAt(String.valueOf(date.getTime()));

        } else {
            HealedLocator newItem = new HealedLocator();
            newItem.setOriginalLocator(originalLocator);
            newItem.setWorkingLocatorType(workingLocatorType);
            newItem.setWorkingLocatorValue(workingLocatorValue);
            newItem.setHealedAt(String.valueOf(date.getTime()));
        }

        saveCacheIntoFile();

    }

    private void saveCacheIntoFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String userDir = System.getProperty("user.dir");
            File file = new File(userDir, "healedlocator.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, cache);
            System.out.println("Cache saved successfully at  " + file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("File Saving Failed  ",e);
        }
    }


}


