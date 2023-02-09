package com.enterprise.batchfileprocessing.repository;

import com.enterprise.batchfileprocessing.entity.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is for
 * <p>
 * Project Name: batchfileprocessing
 *
 * @author Zahid Khan
 * @Time 2/9/2023
 * @since 1.0
 */
@Repository
public interface FileProcessingRepository extends JpaRepository<Schema,Long> {
}
