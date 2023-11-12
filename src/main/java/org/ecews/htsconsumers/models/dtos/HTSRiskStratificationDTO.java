package org.ecews.htsconsumers.models.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HTSRiskStratificationDTO {
    private Long id;
    private String code;
    private String personUuid;
    private String age;
    private String testingSetting;
    private String modality;
    private String targetGroup;
    private String entryPoint;
    private String communityEntryPoint;
    private LocalDate visitDate;
    private LocalDate dob;
    private LocalDateTime dateCreated;
    private String createdBy;
    private LocalDateTime dateModified;
    private String modifiedBy;
    private int archived;
    private Long facilityId;
    private String riskAssessment;
}
