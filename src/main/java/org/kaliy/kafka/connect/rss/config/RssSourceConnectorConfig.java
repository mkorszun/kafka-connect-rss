package org.kaliy.kafka.connect.rss.config;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.apache.kafka.common.config.ConfigDef.NO_DEFAULT_VALUE;

public class RssSourceConnectorConfig extends AbstractConfig {

    public static final String RSS_URL_CONFIG = "rss.urls";
    private static final String RSS_URL_DOC = "RSS or Atom feed URL";
    public static final String RSS_URL_SEPARATOR = " ";

    public static final String TOPIC_CONFIG = "topic";
    private static final String TOPIC_DOC = "Topic to write to";

    public static final String SLEEP_CONFIG = "sleep.seconds";
    private static final String SLEEP_DOC = "Time in seconds that connector will wait until querying feed again";

    public static final String PROVIDER = "provider";
    public static final String PROVIDER_DOC = "Name of the provider";

    public static final String OFFSET_KEY = "sent_items";
    public static final String OFFSET_DELIMITER = "|";
    public static final String OFFSET_DELIMITER_REGEX = "\\|";

    private final List<String> urls;
    private final String topic;
    private final int sleepInSeconds;
    private final String provider;

    public RssSourceConnectorConfig(Map<?, ?> originals) {
        super(config(), originals);
        this.urls = Arrays.asList(getString(RSS_URL_CONFIG).split(RSS_URL_SEPARATOR));
        this.topic = getString(TOPIC_CONFIG);
        this.sleepInSeconds = getInt(SLEEP_CONFIG);
        this.provider = getString(PROVIDER);
    }

    public static ConfigDef config() {
        return new ConfigDef()
                .define(RSS_URL_CONFIG, ConfigDef.Type.STRING, NO_DEFAULT_VALUE, new UrlListValidator(), ConfigDef.Importance.HIGH, RSS_URL_DOC)
                .define(TOPIC_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TOPIC_DOC)
                .define(SLEEP_CONFIG, ConfigDef.Type.INT, 60, new PositiveIntegerValidator(), ConfigDef.Importance.MEDIUM, SLEEP_DOC)
                .define(PROVIDER, ConfigDef.Type.STRING, ConfigDef.Importance.LOW, PROVIDER_DOC);
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getTopic() {
        return topic;
    }

    public int getSleepInSeconds() {
        return sleepInSeconds;
    }

    public String getProvider() {
        return provider;
    }
}
