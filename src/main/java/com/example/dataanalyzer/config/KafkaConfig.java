package com.example.dataanalyzer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${topics}")
    private List<String> topics;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserialazr;
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserialazr;
//    @Value("${trusted-packages}")
    private String trustedPackages = "*";

    @Bean
    public Map<String, Object> receiverProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId
        );
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                keyDeserialazr
        );
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                valueDeserialazr
        );
        props.put(
                "spring.json.trusted.packages",
                trustedPackages
        );
        return props;
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        ReceiverOptions<String, Object> receiverOptions = ReceiverOptions
                .create(receiverProperties());
        return receiverOptions.subscription(topics)
                .addAssignListener(partitions ->
                        System.out.println("onPartitionAssigned: "
                                + partitions))
                .addRevokeListener(partitions ->
                        System.out.println("onPartitionRevoked: "
                                + partitions));
    }

    @Bean
    public KafkaReceiver<String, Object> receiver(
            ReceiverOptions<String, Object> receiverOptions
    ) {
        return KafkaReceiver.create(receiverOptions);
    }

}