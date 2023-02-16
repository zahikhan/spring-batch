package com.enterprise.batchfileprocessing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String guid;
    private String sensitiveData;
}
