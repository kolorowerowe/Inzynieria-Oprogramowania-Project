package com.github.swapbook.repositories.specimens;

import com.github.swapbook.model.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Integer> {

}
