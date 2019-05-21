package com.juno.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juno.entity.Xref;
import com.juno.export.Exporter;
import com.juno.repository.XrefRepository;

@Component
public class XrefService {

	@Autowired
	private XrefRepository xrefRepository;

	@Transactional
	public Iterable<Xref> findByParameters(String fromDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date frefFomDate = sdf.parse(fromDate);
		Iterable<Xref> xrefs = xrefRepository.findByTimestampGreaterThan(frefFomDate);
		return xrefs;
	}
}
