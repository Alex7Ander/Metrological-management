package ru.pavlov.MetrologicalManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.MetrologicalManagement.domain.VerificationProcedure;

public interface VerificationProcedureRepo extends JpaRepository<VerificationProcedure, Long> {

	
}
