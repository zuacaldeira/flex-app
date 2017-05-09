package flex.frontend.ui.login;


import org.scribe.builder.api.Api;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.builder.api.TwitterApi;
import org.vaadin.addon.oauthpopup.buttons.GitHubApi;

public class ApiInfo {

    // Twitter test application at http://localhost:8080

    static final ApiInfo TWITTER_API = new ApiInfo("Twitter",
            TwitterApi.class,
            "31ssXGMU4WW6KPxWwT6IMQ",
            "FR3wJmGyGAdpQMxB3vMreED2UnsHVb6nPF16f1RrtU",
            "https://api.twitter.com/1.1/account/settings.json");

    // Facebook test application at http://localhost:8080
    static final ApiInfo FACEBOOK_API = new ApiInfo("Facebook",
            FacebookApi.class,
            "1704694336496974", // AppID
            "d8cc0ed762093fa24472ecba473d03b4", // App-Secret
            "https://graph.facebook.com/me");

    static final ApiInfo FACEBOOK_DEV_API = new ApiInfo("Facebook",
            FacebookApi.class,
            "1704817123151362", // AppID
            "7349b4773f7086df12382c8618a909b7", // App-Secret
            "https://graph.facebook.com/me");

    static final ApiInfo FACEBOOK_ORIGINAL_API = new ApiInfo("Facebook",
            FacebookApi.class,
            "170732353126405", // AppID
            "dd59293cda395bf38a88044c22937e7e", // App-Secret
            "https://graph.facebook.com/me");

    // LinkedIn test application at http://localhost:8080
    static final ApiInfo LINKEDIN_API = new ApiInfo("LinkedIn",
            LinkedInApi.class,
            "bp0aa1rxk2re",
            "Q2Na42cZmVs3OWnI",
            "https://api.linkedin.com/v1/people/~");

    static final ApiInfo GITHUB_API = new ApiInfo("GitHub",
            GitHubApi.class,
            "97a7e251c538106e7922",
            "6a36b0992e5e2b00a38c44c21a6e0dc8ae01d83b",
            "https://api.github.com/user");

    private final String name;
    private final Class<? extends Api> scribeApi;
    private final String apiKey;
    private final String apiSecret;
    private final String url;

    public ApiInfo(String name, Class<? extends Api> scribeApi,
            String apiKey, String apiSecret, String url) {
        super();
        this.name = name;
        this.scribeApi = scribeApi;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Api> getScribeApi() {
        return scribeApi;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getUrl() {
        return url;
    }

    
}
