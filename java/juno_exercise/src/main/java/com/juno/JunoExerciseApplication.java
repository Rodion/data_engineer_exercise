package com.juno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JunoExerciseApplication implements ApplicationRunner {

	private final static String INPUT_PARAMETER_DFROM = "Dfrom";
	private final static String OUTPUT_PATH = "Dtofolder";

	@Autowired
	private XrefManager xrefManager;

	public static void main(String[] args) {
		SpringApplication.run(JunoExerciseApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("JunoExerciseApplication main" + args);
		if (args.getOptionNames().contains(INPUT_PARAMETER_DFROM) && args.getOptionNames().contains(OUTPUT_PATH)) {
			String dfrom = args.getOptionValues(INPUT_PARAMETER_DFROM).get(0);
			String outputPath = args.getOptionValues(OUTPUT_PATH).get(0);
			if (dfrom != null && outputPath !=null) {
				xrefManager.exportXrefsFromDate(dfrom, outputPath);
			}
		}
	}

}
