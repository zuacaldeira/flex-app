package utils;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.ItemSearchRequest;
import de.malkusch.amazon.ecs.ProductAdvertisingAPI;
import de.malkusch.amazon.ecs.configuration.PropertiesConfiguration;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author zua
 */
public class AmazonApi {

    private final PropertiesConfiguration configuration;
    private final ProductAdvertisingAPI api;

    public AmazonApi() throws IOException {
        Properties properties = new Properties();
        System.out.println(new File("").getAbsolutePath());
        properties.load(getClass().getResourceAsStream("/amazon.properties"));

        configuration = new PropertiesConfiguration(properties);
        api = new ProductAdvertisingAPI(configuration,
                new AWSECommerceService().getAWSECommerceServicePortUS());
    }

    public com.ECS.client.jax.Items makeApiCall(String searchIndex, String keywords) throws RequestException {
        ItemSearchRequest itemSearchRquest = new ItemSearchRequest();
        itemSearchRquest.setSearchIndex(searchIndex);
        itemSearchRquest.setKeywords(keywords);
        return api.getItemSearch().call(itemSearchRquest);
    }

    PropertiesConfiguration getConfiguration() {
        return configuration;
    }

    ProductAdvertisingAPI getApi() {
        return api;
    }
    
    

}
