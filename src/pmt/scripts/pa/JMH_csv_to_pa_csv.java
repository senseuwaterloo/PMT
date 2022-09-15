package pmt.scripts.pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JMH_csv_to_pa_csv {

	public static String path_to_original_csv = "/Users/mj/Desktop/tmp/something.csv";
	public static String path_to_original_pa_csv = "/Users/mj/Desktop/tmp/original-pa.csv";
	public static String path_to_mutant_pa_csv = "/Users/mj/Desktop/tmp/mutant-pa.csv";
	public static int number_of_data_points = 30;
	public static String project_name = "rxjava";
	public static String project_commit = "mega-hwo_v2.0";

	public static void main(String[] args) throws IOException {
		generate_pa_csv();

	}

	private static void generate_pa_csv() throws IOException, FileNotFoundException {
		Map<String, List<Double>> myMap = new HashMap<String, List<Double>>();
		List<List<String>> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path_to_original_csv))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				records.add(Arrays.asList(values));
			}
		}

		for (int i = 0; i < records.size(); i++) {
			List<String> newLine = records.get(i);
			myMap.put(newLine.get(0), new ArrayList<Double>());
			for (int j = 1; j <= number_of_data_points; j++) {
				String str = newLine.get(j);

				myMap.get(newLine.get(0)).add(Double.parseDouble(str));
			}
			for (int j = 1; j <= number_of_data_points; j++) {
				String str = newLine.get(j + number_of_data_points);

				myMap.get(newLine.get(0)).add(Double.parseDouble(str));
			}
		}

		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		Iterator<List<Double>> values_it = myMap.values().iterator();
		Iterator<String> names_it = myMap.keySet().iterator();

		while (values_it.hasNext() && names_it.hasNext()) {

			String name = names_it.next();
			List<Double> values = values_it.next();

			for (int j = 0; j < number_of_data_points; j++) {
				sb.append(project_name).append(";");
				sb.append(project_commit).append(";");
				sb.append(name).append(";");
				sb.append("NA").append(";");
				sb.append("none").append(";");
				sb.append("1").append(";");
				sb.append("1").append(";");
				sb.append(j + 1).append(";");
				sb.append("thrpt").append(";");
				sb.append("ops/s").append(";");
				sb.append("1").append(";");
				sb.append(values.get(j)).append("\n");

			}
			for (int j = 0; j < number_of_data_points; j++) {
				sb2.append(project_name).append(";");
				sb2.append(project_commit).append(";");
				sb2.append(name).append(";");
				sb2.append("NA").append(";");
				sb2.append("none").append(";");
				sb2.append("1").append(";");
				sb2.append("1").append(";");
				sb2.append(j + 1).append(";");
				sb2.append("thrpt").append(";");
				sb2.append("ops/s").append(";");
				sb2.append("1").append(";");
				sb2.append(values.get(j + number_of_data_points)).append("\n");

			}

		}
		BufferedWriter br1 = new BufferedWriter(new FileWriter(path_to_original_pa_csv));
		br1.write(sb.toString());
		br1.close();

		BufferedWriter br2 = new BufferedWriter(new FileWriter(path_to_mutant_pa_csv));
		br2.write(sb2.toString());
		br2.close();
	}

}
