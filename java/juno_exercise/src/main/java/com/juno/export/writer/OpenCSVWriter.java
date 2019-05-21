package com.juno.export.writer;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.juno.export.ExportToFileProperties;
import com.juno.export.Exporter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class OpenCSVWriter implements Exporter {

	@Autowired
	private ExportToFileProperties exportToFileProperties;

	@Override
	public void exportEntity(List<Entity> entities,String outputPath) {

		try (Writer writer = Files.newBufferedWriter(Paths.get(outputPath+exportToFileProperties.getPathcsv()));) {
			StatefulBeanToCsv<Entity> beanToCsv = new StatefulBeanToCsvBuilder<Entity>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCsv.write(entities);
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}