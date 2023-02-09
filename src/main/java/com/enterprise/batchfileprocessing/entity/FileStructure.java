package com.enterprise.batchfileprocessing.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * This class is for
 * <p>
 * Project Name: batchfileprocessing
 *
 * @author Zahid Khan
 * @Time 2/9/2023
 * @since 1.0
 */
@Builder
@Getter
public class FileStructure {
    private final String guid;
    private final String sensitiveData;
}
