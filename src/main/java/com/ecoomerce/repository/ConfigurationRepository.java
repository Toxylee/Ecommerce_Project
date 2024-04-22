package com.ecoomerce.repository;

import com.ecoomerce.model.Category;
import com.ecoomerce.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
Optional<Configuration> findByConfigurationCode (String configCode);

}