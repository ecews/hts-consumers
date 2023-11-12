package org.ecews.htsconsumers.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HTSIndexElicitationDTO {
    private String uuid;
    private LocalDate dob;
    private Boolean isDateOfBirthEstimated;
    private Long sex;
    private String address;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private String altPhoneNumber;
    private String hangOutSpots;
    private Long physicalHurt;
    private Long threatenToHurt;
    private Long notificationMethod;
    private Long partnerTestedPositive;
    private Long sexuallyUncomfortable;
    private Boolean currentlyLiveWithPartner;
    private Long relationshipWithIndexClient;
    private LocalDate datePartnerCameForTesting;
    private Long facilityId;
    private String htsClientUuid;
    private LocalDateTime dateCreated;
    private String createdBy;
    private LocalDateTime dateModified;
    private String modifiedBy;
    private Integer archived;
    private String offeredIns;
    private String acceptedIns;
    private String extra;
}
