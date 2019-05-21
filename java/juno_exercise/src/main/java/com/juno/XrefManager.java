package com.juno;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juno.entity.Xref;
import com.juno.export.Exporter;
import com.juno.service.XrefService;

@Component
public class XrefManager {

	// private static final Logger logger =
	// LogManager.getLogger(Log4j2DemoApplication.class);

	@Autowired
	private XrefService xrefService;

	@Autowired
	private Exporter exporter;

	public void exportXrefsFromDate(String dfrom, String outputPath) throws ParseException {
		System.out.println("strat exportXrefsFromDate with " + dfrom + " " + outputPath);
		Iterable<Xref> xrefs = xrefService.findByParameters(dfrom);
		List<Entity> kyky = new ArrayList<Entity>();
		for (Xref xref : xrefs) {
			kyky.add(xref);
		}
		exporter.exportEntity(kyky, outputPath);
	}

}
