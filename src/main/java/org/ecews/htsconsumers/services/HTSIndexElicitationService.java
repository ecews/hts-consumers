package org.ecews.htsconsumers.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecews.htsconsumers.models.dtos.HTSIndexElicitationDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class HTSIndexElicitationService implements ConsumerService<HTSIndexElicitationDTO> {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public void save(HTSIndexElicitationDTO htsIndex) {
        String sql = """
                INSERT INTO public.hts_index_elicitation (
                    uuid, dob, is_date_of_birth_estimated, sex, address, last_name, first_name,
                    middle_name, phone_number, alt_phone_number, hang_out_spots, physical_hurt,
                    threaten_to_hurt, notification_method, partner_tested_positive,
                    sexually_uncomfortable, currently_live_with_partner,
                    relationship_with_index_client, date_partner_came_for_testing,
                    facility_id, hts_client_uuid, date_created, created_by, date_modified,
                    modified_by, archived, extra, offered_ins, accepted_ins
                ) VALUES (
                    :uuid, :dob, :isDateOfBirthEstimated, :sex, :address, :lastName, :firstName,
                    :middleName, :phoneNumber, :altPhoneNumber, :hangOutSpots, :physicalHurt,
                    :threatenToHurt, :notificationMethod, :partnerTestedPositive,
                    :sexuallyUncomfortable, :currentlyLiveWithPartner,
                    :relationshipWithIndexClient, :datePartnerCameForTesting,
                    :facilityId, :htsClientUuid, :dateCreated, :createdBy, :dateModified,
                    :modifiedBy, :archived, :extra, :offeredIns, :acceptedIns
                )
                ON CONFLICT (uuid) DO UPDATE SET
                    dob = EXCLUDED.dob, is_date_of_birth_estimated = EXCLUDED.is_date_of_birth_estimated,
                    sex = EXCLUDED.sex, address = EXCLUDED.address, last_name = EXCLUDED.last_name,
                    first_name = EXCLUDED.first_name, middle_name = EXCLUDED.middle_name,
                    phone_number = EXCLUDED.phone_number, alt_phone_number = EXCLUDED.alt_phone_number,
                    hang_out_spots = EXCLUDED.hang_out_spots, physical_hurt = EXCLUDED.physical_hurt,
                    threaten_to_hurt = EXCLUDED.threaten_to_hurt, notification_method = EXCLUDED.notification_method,
                    partner_tested_positive = EXCLUDED.partner_tested_positive,
                    sexually_uncomfortable = EXCLUDED.sexually_uncomfortable,
                    currently_live_with_partner = EXCLUDED.currently_live_with_partner,
                    relationship_with_index_client = EXCLUDED.relationship_with_index_client,
                    date_partner_came_for_testing = EXCLUDED.date_partner_came_for_testing,
                    facility_id = EXCLUDED.facility_id, hts_client_uuid = EXCLUDED.hts_client_uuid,
                    date_created = EXCLUDED.date_created, created_by = EXCLUDED.created_by,
                    date_modified = EXCLUDED.date_modified, modified_by = EXCLUDED.modified_by,
                    archived = EXCLUDED.archived, extra = EXCLUDED.extra,
                    offered_ins = EXCLUDED.offered_ins, accepted_ins = EXCLUDED.accepted_ins
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("uuid", htsIndex.getUuid())
                .addValue("dob", htsIndex.getDob())
                .addValue("isDateOfBirthEstimated", htsIndex.getIsDateOfBirthEstimated())
                .addValue("sex", htsIndex.getSex())
                .addValue("address", htsIndex.getAddress())
                .addValue("lastName", htsIndex.getLastName())
                .addValue("firstName", htsIndex.getFirstName())
                .addValue("middleName", htsIndex.getMiddleName())
                .addValue("phoneNumber", htsIndex.getPhoneNumber())
                .addValue("altPhoneNumber", htsIndex.getAltPhoneNumber())
                .addValue("hangOutSpots", htsIndex.getHangOutSpots())
                .addValue("physicalHurt", htsIndex.getPhysicalHurt())
                .addValue("threatenToHurt", htsIndex.getThreatenToHurt())
                .addValue("notificationMethod", htsIndex.getNotificationMethod())
                .addValue("partnerTestedPositive", htsIndex.getPartnerTestedPositive())
                .addValue("sexuallyUncomfortable", htsIndex.getSexuallyUncomfortable())
                .addValue("currentlyLiveWithPartner", htsIndex.getCurrentlyLiveWithPartner())
                .addValue("relationshipWithIndexClient", htsIndex.getRelationshipWithIndexClient())
                .addValue("datePartnerCameForTesting", htsIndex.getDatePartnerCameForTesting())
                .addValue("facilityId", htsIndex.getFacilityId())
                .addValue("htsClientUuid", htsIndex.getHtsClientUuid())
                .addValue("dateCreated", htsIndex.getDateCreated())
                .addValue("createdBy", htsIndex.getCreatedBy())
                .addValue("dateModified", htsIndex.getDateModified())
                .addValue("modifiedBy", htsIndex.getModifiedBy())
                .addValue("archived", htsIndex.getArchived())
                .addValue("extra", htsIndex.getExtra())
                .addValue("offeredIns", htsIndex.getOfferedIns())
                .addValue("acceptedIns", htsIndex.getAcceptedIns());

        try {
            namedParameterJdbcTemplate.getJdbcOperations().execute("SET session_replication_role = 'replica'");
            namedParameterJdbcTemplate.update(sql, parameters);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            namedParameterJdbcTemplate.getJdbcOperations().execute("SET session_replication_role = 'origin'");
        }

        log.info("Finish syncing record ******* {}", htsIndex);
    }

}
