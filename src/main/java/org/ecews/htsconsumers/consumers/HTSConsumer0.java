package org.ecews.htsconsumers.consumers;

import lombok.AllArgsConstructor;
import org.ecews.htsconsumers.models.dtos.HTSClientDTO;
import org.ecews.htsconsumers.models.dtos.HTSIndexElicitationDTO;
import org.ecews.htsconsumers.models.dtos.HTSRiskStratificationDTO;
import org.ecews.htsconsumers.services.HTSClientService;
import org.ecews.htsconsumers.services.HTSIndexElicitationService;
import org.ecews.htsconsumers.services.HTSRiskStratificationService;
import org.ecews.htsconsumers.utilities.constants.ConsumerGroup;
import org.ecews.htsconsumers.utilities.constants.Topics;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(groupId = ConsumerGroup.HTS_CONSUMER_GROUP, topics = Topics.HTS)
@AllArgsConstructor
public class HTSConsumer0 {

    private final HTSClientService htsClientService;
    private final HTSIndexElicitationService htsIndexElicitationService;
    private final HTSRiskStratificationService htsRiskStratificationService;
    @KafkaHandler
    public void handleHTSRiskStratification0(HTSRiskStratificationDTO htsRiskStratificationDTO) {
        htsRiskStratificationService.save(htsRiskStratificationDTO);
    }

    @KafkaHandler
    public void handleHTSClient0(HTSClientDTO htsClientDTO) {
        htsClientService.save(htsClientDTO);
    }

    @KafkaHandler
    public void handleLabResult0(HTSIndexElicitationDTO htsIndexElicitationDTO) {
        htsIndexElicitationService.save(htsIndexElicitationDTO);
    }

}
