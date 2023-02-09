package com.enterprise.batchfileprocessing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * This class is for Pipeline Possible Statuses Status.
 * <p>
 * Project Name: taas-core-file-processor-subscription
 *
 * @author Zahid Khan
 * @Time 1/10/2023
 * @since 1.0
 */


@Entity(name = "File_Processing_Schema")
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class Schema {
    @Id
    private final Long id;

    @Column(name = "FILENAME")
    private final String filename;

    @Column(name = "FILE_CREATION_DATETIME")
    private final String fileCreationDateTime;

    @Column(name = "CLOUD_FUSION_TRIGGER_DATETIME")
    private final String cloudFusionTriggerDateTime;
    @Column(name = "CLOUD_FUSION_COMPLETION_DATETIME")
    private final String cloudFusionCompletionTime;
    @Column(name = "FILE_SIZE")
    private final String fileSize;

    @Column(name = "FILE_RECORDS_COUNT")
    private final long fileRecordCount;

    @Column(name = "FILE_HASH")
    private final String fileHash;

    @Column(name = "FAILURE_REASON")
    private final String failureReason;

}
