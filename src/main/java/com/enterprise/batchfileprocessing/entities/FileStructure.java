package com.enterprise.batchfileprocessing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;




/**
 * This class is for
 * <p>
 * Project Name: batchfileprocessing
 *
 * @author Zahid Khan
 * @Time 2/9/2023
 * @since 1.0
 */
@Entity
@Table(name = "file")
@Data
public class FileStructure {
    @Id
    long id;
    private String guid;
    private String sensitiveData;
}
