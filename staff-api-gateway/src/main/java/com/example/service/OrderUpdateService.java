package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderUpdateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Source source;

    public OrderUpdateService(Source source) {
        this.source = source;
    }

    @StreamListener(Processor.INPUT)
    public void updateProvided(SummaryIdHolder summaryIdHolder) {
        logger.info("{}をキューから受信しました", summaryIdHolder);
        source.output().send(MessageBuilder.withPayload(summaryIdHolder).build());
        logger.info("{}をキューに送信しました", summaryIdHolder);
    }

    private static class SummaryIdHolder {
        private Integer summaryId;

        public SummaryIdHolder() {
        }

        public Integer getSummaryId() {
            return summaryId;
        }

        public void setSummaryId(Integer summaryId) {
            this.summaryId = summaryId;
        }

        @Override
        public String toString() {
            return "SummaryIdHolder{" +
                    "summaryId=" + summaryId +
                    '}';
        }
    }
}
