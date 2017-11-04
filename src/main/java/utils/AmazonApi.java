package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author zua
 */
public class AmazonApi {

    private final FlexAppProperties configuration;

    public AmazonApi() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("../properties/flex-app/amazon.properties"));

        configuration = new FlexAppProperties(properties);
        System.out.println(configuration.getAccessKey());
        System.out.println(configuration.getAssociateTag());
        System.out.println(configuration.getSecretKey());

    }

    public com.ECS.client.jax.Items makeApiCall(String searchIndex, String keywords)  {
        return null;
    }

}
