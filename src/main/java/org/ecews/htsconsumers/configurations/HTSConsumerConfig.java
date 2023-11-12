package org.ecews.htsconsumers.configurations;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.ecews.htsconsumers.models.dtos.HTSClientDTO;
import org.ecews.htsconsumers.models.dtos.HTSIndexElicitationDTO;
import org.ecews.htsconsumers.models.dtos.HTSRiskStratificationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class HTSConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String KAFKA_SERVER_ADDRESS;
    @Bean
    public RecordMessageConverter htsConverter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages(
                "org.ecews.messenger.domain.dtos.HTSRiskStratificationDTO, " +
                        "org.ecews.messenger.domain.dtos.HTSClientDTO, " +
                        "org.ecews.messenger.domain.dtos.HTSIndexElicitationDTO ");
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("htsRiskStratification", HTSRiskStratificationDTO.class);
        mappings.put("htsClient", HTSClientDTO.class);
        mappings.put("htsIndexElicitation", HTSIndexElicitationDTO.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    public ConsumerFactory<String, Object> htsConsumerFactory() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_ADDRESS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> htsKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(htsConsumerFactory());
        factory.setMessageConverter(htsConverter());
        return factory;
    }
}
