package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.BusLocationModel;
import models.ConfigModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Initializations {

    public static ArrayList<BusLocationModel> initBusLocations() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ConfigModel configModel = mapper.readValue(Objects
                        .requireNonNull(Initializations.class.getClassLoader().getResource("mockResources.json")),
                ConfigModel.class);
        return configModel.getBusLocationModels();
    }
}
