package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;

@Repository
public interface BorrowableRepository extends JpaRepository<BorrowableInstrument, Integer>{
}
