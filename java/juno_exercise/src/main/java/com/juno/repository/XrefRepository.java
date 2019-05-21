package com.juno.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juno.entity.Xref;

// This will be AUTO IMPLEMENTED by Spring into a Bean called OrderRepository
// CRUD refers Create, Read, Update, Delete

public interface XrefRepository extends JpaRepository<Xref, String> {

	public Iterable<Xref> findByTimestampGreaterThan(Date fromTimestamp);
}
