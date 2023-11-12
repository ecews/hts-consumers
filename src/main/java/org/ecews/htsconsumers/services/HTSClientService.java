package org.ecews.htsconsumers.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecews.htsconsumers.models.dtos.HTSClientDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class HTSClientService implements ConsumerService<HTSClientDTO> {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public void save(HTSClientDTO htsClient) {
        String sql = """
                INSERT INTO public.hts_client
                (target_group, client_code, date_visit, referred_from, person_uuid, testing_setting,
                prep_given, other_drugs, hiv_test_result, first_time_visit, num_children, num_wives,
                type_counseling, index_client, prep_offered, prep_accepted, previously_tested, extra,
                pregnant, breast_feeding, relation_with_index_client, test1, confirmatory_test,
                tie_breaker_test, test2, confirmatory_test2, tie_breaker_test2, hiv_test_result2,
                knowledge_assessment, risk_assessment, tb_screening, sti_screening, facility_id,
                captured_by, uuid, hepatitis_testing, recency, syphilis_testing,
                index_notification_services_elicitation, post_test_counseling, sex_partner_risk_assessment,
                others, cd4, date_created, created_by, date_modified, modified_by, archived,
                index_client_code, risk_stratification_code)
                VALUES
                (:targetGroup, :clientCode, :dateVisit, :referredFrom, :personUuid, :testingSetting,
                :prepGiven, :otherDrugs, :hivTestResult, :firstTimeVisit, :numChildren, :numWives,
                :typeCounseling, :indexClient, :prepOffered, :prepAccepted, :previouslyTested, cast(:extra as jsonb),
                :pregnant, :breastFeeding, :relationWithIndexClient, cast(:test1 as jsonb), cast(:confirmatoryTest as jsonb),
                cast(:tieBreakerTest as jsonb), cast(:test2 as jsonb), cast(:confirmatoryTest2 as jsonb), cast(:tieBreakerTest2 as jsonb), :hivTestResult2,
                cast(:knowledgeAssessment as jsonb), cast(:riskAssessment as jsonb), cast(:tbScreening as jsonb), cast(:stiScreening as jsonb), :facilityId,
                :capturedBy, :uuid, cast(:hepatitisTesting as jsonb), cast(:recency as jsonb), cast(:syphilisTesting as jsonb),
                cast(:indexNotificationServicesElicitation as jsonb), cast(:postTestCounseling as jsonb), cast(:sexPartnerRiskAssessment as jsonb),
                cast(:others as jsonb), cast(:cd4 as jsonb), :dateCreated, :createdBy, :dateModified, :modifiedBy, :archived,
                :indexClientCode, :riskStratificationCode)
                ON CONFLICT (uuid) DO UPDATE SET
                target_group = EXCLUDED.target_group, client_code = EXCLUDED.client_code,
                date_visit = EXCLUDED.date_visit,referred_from = EXCLUDED.referred_from,
                person_uuid = EXCLUDED.person_uuid,testing_setting = EXCLUDED.testing_setting,
                prep_given = EXCLUDED.prep_given,other_drugs = EXCLUDED.other_drugs,
                hiv_test_result = EXCLUDED.hiv_test_result,first_time_visit = EXCLUDED.first_time_visit,
                num_children = EXCLUDED.num_children, num_wives = EXCLUDED.num_wives,
                type_counseling = EXCLUDED.type_counseling,index_client = EXCLUDED.index_client,
                prep_offered = EXCLUDED.prep_offered,prep_accepted = EXCLUDED.prep_accepted,
                previously_tested = EXCLUDED.previously_tested,extra = EXCLUDED.extra,
                pregnant = EXCLUDED.pregnant,breast_feeding = EXCLUDED.breast_feeding,
                relation_with_index_client = EXCLUDED.relation_with_index_client,test1 = EXCLUDED.test1,
                confirmatory_test = EXCLUDED.confirmatory_test,tie_breaker_test = EXCLUDED.tie_breaker_test,
                test2 = EXCLUDED.test2,confirmatory_test2 = EXCLUDED.confirmatory_test2,
                tie_breaker_test2 = EXCLUDED.tie_breaker_test2,hiv_test_result2 = EXCLUDED.hiv_test_result2,
                knowledge_assessment = EXCLUDED.knowledge_assessment,risk_assessment = EXCLUDED.risk_assessment,
                tb_screening = EXCLUDED.tb_screening, sti_screening = EXCLUDED.sti_screening,
                facility_id = EXCLUDED.facility_id,captured_by = EXCLUDED.captured_by,
                hepatitis_testing = EXCLUDED.hepatitis_testing, recency = EXCLUDED.recency,
                syphilis_testing = EXCLUDED.syphilis_testing,
                index_notification_services_elicitation = EXCLUDED.index_notification_services_elicitation,
                post_test_counseling = EXCLUDED.post_test_counseling,sex_partner_risk_assessment = EXCLUDED.sex_partner_risk_assessment,
                others = EXCLUDED.others,cd4 = EXCLUDED.cd4,date_created = EXCLUDED.date_created,
                created_by = EXCLUDED.created_by,date_modified = EXCLUDED.date_modified, modified_by = EXCLUDED.modified_by,
                archived = EXCLUDED.archived,index_client_code = EXCLUDED.index_client_code,
                risk_stratification_code = EXCLUDED.risk_stratification_code""";


        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("targetGroup", htsClient.getTargetGroup())
                .addValue("clientCode", htsClient.getClientCode())
                .addValue("dateVisit", htsClient.getDateVisit())
                .addValue("referredFrom", htsClient.getReferredFrom())
                .addValue("personUuid", htsClient.getPersonUuid())
                .addValue("testingSetting", htsClient.getTestingSetting())
                .addValue("prepGiven", htsClient.getPrepGiven())
                .addValue("otherDrugs", htsClient.getOtherDrugs())
                .addValue("hivTestResult", htsClient.getHivTestResult())
                .addValue("firstTimeVisit", htsClient.isFirstTimeVisit())
                .addValue("numChildren", htsClient.getNumChildren())
                .addValue("numWives", htsClient.getNumWives())
                .addValue("typeCounseling", htsClient.getTypeCounseling())
                .addValue("indexClient", htsClient.getIndexClient())
                .addValue("prepOffered", htsClient.isPrepOffered())
                .addValue("prepAccepted", htsClient.isPrepAccepted())
                .addValue("previouslyTested", htsClient.isPreviouslyTested())
                .addValue("extra", htsClient.getExtra())
                .addValue("pregnant", htsClient.getPregnant())
                .addValue("breastFeeding", htsClient.isBreastFeeding())
                .addValue("relationWithIndexClient", htsClient.getRelationWithIndexClient())
                .addValue("test1", htsClient.getTest1())
                .addValue("confirmatoryTest", htsClient.getConfirmatoryTest())
                .addValue("tieBreakerTest", htsClient.getTieBreakerTest())
                .addValue("test2", htsClient.getTest2())
                .addValue("confirmatoryTest2", htsClient.getConfirmatoryTest2())
                .addValue("tieBreakerTest2", htsClient.getTieBreakerTest2())
                .addValue("hivTestResult2", htsClient.getHivTestResult2())
                .addValue("knowledgeAssessment", htsClient.getKnowledgeAssessment())
                .addValue("riskAssessment", htsClient.getRiskAssessment())
                .addValue("tbScreening", htsClient.getTbScreening())
                .addValue("stiScreening", htsClient.getStiScreening())
                .addValue("facilityId", htsClient.getFacilityId())
                .addValue("capturedBy", htsClient.getCapturedBy())
                .addValue("uuid", htsClient.getUuid())
                .addValue("hepatitisTesting", htsClient.getHepatitisTesting())
                .addValue("recency", htsClient.getRecency())
                .addValue("syphilisTesting", htsClient.getSyphilisTesting())
                .addValue("indexNotificationServicesElicitation", htsClient.getIndexNotificationServicesElicitation())
                .addValue("postTestCounseling", htsClient.getPostTestCounseling())
                .addValue("sexPartnerRiskAssessment", htsClient.getSexPartnerRiskAssessment())
                .addValue("others", htsClient.getOthers())
                .addValue("cd4", htsClient.getCd4())
                .addValue("dateCreated", htsClient.getDateCreated())
                .addValue("createdBy", htsClient.getCreatedBy())
                .addValue("dateModified", htsClient.getDateModified())
                .addValue("modifiedBy", htsClient.getModifiedBy())
                .addValue("archived", htsClient.getArchived())
                .addValue("indexClientCode", htsClient.getIndexClientCode())
                .addValue("riskStratificationCode", htsClient.getRiskStratificationCode());

        try {
            namedParameterJdbcTemplate.getJdbcOperations().execute("SET session_replication_role = 'replica'");
            namedParameterJdbcTemplate.update(sql, parameters);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            namedParameterJdbcTemplate.getJdbcOperations().execute("SET session_replication_role = 'origin'");
        }

        log.info("Finish syncing record ******* {}", htsClient);
    }
}
