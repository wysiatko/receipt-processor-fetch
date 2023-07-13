package com.lucas.receipt.processor.repositories;

import com.lucas.receipt.processor.repositories.entities.ReceiptDataObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptDataObject, Long> {
}
