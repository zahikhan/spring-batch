package com.enterprise.batchfileprocessing.configurations;

import com.enterprise.batchfileprocessing.entities.FileStructure;
import org.springframework.batch.item.ItemProcessor;

/**
 * This class is for
 * <p>
 * Project Name: batchfileprocessing
 *
 * @author Zahid Khan
 * @Time 2/9/2023
 * @since 1.0
 */
public class FileRowProcessor implements ItemProcessor<FileStructure,FileStructure> {
    @Override
    public FileStructure process(FileStructure item) {
      return item;
    }
}
