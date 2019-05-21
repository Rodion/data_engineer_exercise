package com.juno.export;

import java.util.List;

import javax.persistence.Entity;

public interface Exporter {

	public void exportEntity(List<Entity> entities, String outputPath);
}
