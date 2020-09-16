package org.kaliy.kafka.connect.rss.model;

import java.util.Optional;
import org.apache.kafka.connect.data.Struct;

import static org.kaliy.kafka.connect.rss.RssSchemas.ENVELOPE_FEED_ID;
import static org.kaliy.kafka.connect.rss.RssSchemas.ENVELOPE_PROVIDER_ID;
import static org.kaliy.kafka.connect.rss.RssSchemas.ENVELOPE_RAW_CONTENT;
import static org.kaliy.kafka.connect.rss.RssSchemas.ENVELOPE_SCHEMA;
import static org.kaliy.kafka.connect.rss.RssSchemas.FEED_SCHEMA;

public class Envelope {
    private String providerId;
    private String feedId;
    private Struct rawContent;

    public Envelope(String providerId, String feedId, Struct rawContent) {
        this.providerId = providerId;
        this.feedId = feedId;
        this.rawContent = rawContent;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public Struct getRawContent() {
        return rawContent;
    }

    public void setRawContent(Struct rawContent) {
        this.rawContent = rawContent;
    }

    public Optional<Struct> toStruct() {
        Struct envelope = new Struct(ENVELOPE_SCHEMA);
        envelope.put(ENVELOPE_FEED_ID, getFeedId());
        envelope.put(ENVELOPE_PROVIDER_ID, getProviderId());
        envelope.put(ENVELOPE_RAW_CONTENT, getRawContent());
        return Optional.of(envelope);
    }
}
