package com.juno.export;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.juno.export.writer.OpenCSVWriter;

@Configuration
public class ExportToFileConfig {

	@Bean
	@ConfigurationProperties(prefix = "export.file")
	public ExportToFileProperties exportToFileProperties() {
		return new ExportToFileProperties();
	}

	@Bean
	public Exporter getExporter() {
		return new OpenCSVWriter();
	}
}