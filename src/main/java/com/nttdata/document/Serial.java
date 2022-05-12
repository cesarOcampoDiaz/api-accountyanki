package com.nttdata.document;


import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class Serial implements Serializer<Transaction> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Transaction transaction) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Transaction data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
