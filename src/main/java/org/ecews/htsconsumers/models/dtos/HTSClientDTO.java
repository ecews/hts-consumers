package org.ecews.htsconsumers.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HTSClientDTO {

    private String targetGroup;
    private String clientCode;
    private LocalDate dateVisit;
    private Long referredFrom;
    private String personUuid;
    private String testingSetting;
    private String prepGiven;
    private String otherDrugs;
    private String hivTestResult;
    private boolean firstTimeVisit;
    private Integer numChildren;
    private Integer numWives;
    private Long typeCounseling;
    private Boolean indexClient;
    private boolean prepOffered;
    private boolean prepAccepted;
    private boolean previouslyTested;
    private String  extra;
    private Long pregnant;
    private boolean breastFeeding;
    private Long relationWithIndexClient;
    private String test1;
    private String confirmatoryTest;
    private String tieBreakerTest;
    private String test2;
    private String confirmatoryTest2;
    private String tieBreakerTest2;
    private String hivTestResult2;
    private String knowledgeAssessment;
    private String riskAssessment;
    private String tbScreening;
    private String stiScreening;
    private Long facilityId;
    private String capturedBy;
    private String uuid;
    private String hepatitisTesting;
    private String recency;
    private String syphilisTesting;
    private String indexNotificationServicesElicitation;
    private String postTestCounseling;
    private String sexPartnerRiskAssessment;
    private String others;
    private String cd4;
    private LocalDateTime dateCreated;
    private String createdBy;
    private LocalDateTime dateModified;
    private String modifiedBy;
    private Integer archived;
    private String indexClientCode;
    private String riskStratificationCode;
}
