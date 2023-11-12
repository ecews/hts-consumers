package org.ecews.htsconsumers.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecews.htsconsumers.models.dtos.HTSRiskStratificationDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;

@Slf4j
@Service
@AllArgsConstructor
public class HTSRiskStratificationService implements ConsumerService<HTSRiskStratificationDTO> {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public void save(HTSRiskStratificationDTO htsRiskStratification) {
        String sql = """
                INSERT INTO public.hts_risk_stratification (
                    code, person_uuid, age, testing_setting, modality, target_group, entry_point, 
                    community_entry_point, visit_date, dob, date_created, created_by, date_modified, 
                    modified_by, archived, facility_id, risk_assessment
                ) VALUES (
                    :code, :personUuid, :age, :testingSetting, :modality, :targetGroup, :entryPoint, 
                    :communityEntryPoint, :visitDate, :dob, :dateCreated, :createdBy, :dateModified, 
                    :modifiedBy, :archived, :facilityId, cast(:riskAssessment as jsonb)
                ) ON CONFLICT (code) DO UPDATE SET
                    person_uuid = EXCLUDED.person_uuid,age = EXCLUDED.age,testing_setting = EXCLUDED.testing_setting,
                    modality = EXCLUDED.modality,target_group = EXCLUDED.target_group, entry_point = EXCLUDED.entry_point,
                    community_entry_point = EXCLUDED.community_entry_point,visit_date = EXCLUDED.visit_date,
                    dob = EXCLUDED.dob,date_created = EXCLUDED.date_created,created_by = EXCLUDED.created_by,
                    date_modified = EXCLUDED.date_modified,modified_by = EXCLUDED.modified_by,
                    archived = EXCLUDED.archived,facility_id = EXCLUDED.facility_id,
                    risk_assessment = EXCLUDED.risk_assessment
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("code", htsRiskStratification.getCode())
                .addValue("personUuid", htsRiskStratification.getPersonUuid())
                .addValue("age", htsRiskStratification.getAge())
                .addValue("testingSetting", htsRiskStratification.getTestingSetting())
                .addValue("modality", htsRiskStratification.getModality())
                .addValue("targetGroup", htsRiskStratification.getTargetGroup())
                .addValue("entryPoint", htsRiskStratification.getEntryPoint())
                .addValue("communityEntryPoint", htsRiskStratification.getCommunityEntryPoint())
                .addValue("visitDate", htsRiskStratification.getVisitDate())
                .addValue("dob", htsRiskStratification.getDob())
                .addValue("dateCreated", htsRiskStratification.getDateCreated())
                .addValue("createdBy", htsRiskStratification.getCreatedBy())
                .addValue("dateModified", htsRiskStratification.getDateModified())
                .addValue("modifiedBy", htsRiskStratification.getModifiedBy())
                .addValue("archived", htsRiskStratification.getArchived())
                .addValue("facilityId", htsRiskStratification.getFacilityId())
                .addValue("riskAssessment", htsRiskStratification.getRiskAssessment());

        try {
            namedParameterJdbcTemplate.getJdbcOperations().execute("SET session_replication_role = 'replica'");
            namedParameterJdbcTemplate.update(sql, parameters);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            namedParameterJdbcTemplate.getJdbcOperations().execute("SET session_replication_role = 'origin'");
        }

        log.info("Finish syncing record ******* {}", htsRiskStratification);
    }
}
