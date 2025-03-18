package org.example.demofotografia.repositories;

import org.example.demofotografia.entities.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {
}
