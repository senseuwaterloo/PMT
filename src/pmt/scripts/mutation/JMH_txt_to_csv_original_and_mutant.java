package pmt.scripts.mutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMH_txt_to_csv_original_and_mutant {
	public static List<Test_mutant_hitting> records_original = new ArrayList<>();
	public static List<Test_mutant_hitting> records_mutant = new ArrayList<>();

	public static String path_to_original_folder="/Users/mj/Desktop/results/original/";
	public static String path_to_mutant_folder="/Users/mj/Desktop/results/mutant/";

	public static String path_to_output_csv="/Users/mj/Desktop/tmp/something.csv";
	public static int number_of_data_points = 30;

	public static void main(String[] args) throws IOException {

		File b = new File(path_to_original_folder);
		records_original.addAll(txt_to_csv(b));

		File a = new File(path_to_mutant_folder);
		records_mutant.addAll(txt_to_csv(a));

		write_to_file();

	}



	private static void write_to_file() throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(path_to_output_csv));

		StringBuilder sb = null;

		for (Test_mutant_hitting obj_o : records_original) {
			for (Test_mutant_hitting obj_m : records_mutant) {
				if(obj_o.name.equals(obj_m.name)) {
					obj_o.thrpt.addAll(obj_m.thrpt);
				}
			}
				sb = new StringBuilder();

				sb.append(obj_o.name);
				sb.append(",");
				List<String> arr = new ArrayList<>();
				obj_o.thrpt.forEach(x -> {
					arr.add(x + ",");
				});
				for (String str : arr) {
					sb.append(str);
				}

				br.write(sb.toString());
				br.write("\n");
			}


		br.close();
	}

	private static List<Test_mutant_hitting> txt_to_csv(File b) throws IOException, FileNotFoundException {
		List<Test_mutant_hitting> records = new ArrayList<>();
		for (File a : b.listFiles()) {


			try (BufferedReader br = new BufferedReader(new FileReader(a))) {
				String line;
				String name = "";
				String params = "";
				Test_mutant_hitting the_test = null;
				while ((line = br.readLine()) != null) {

					if (line.toString().startsWith("# JMH version: 1.21")) {
						the_test = null;
						continue;
					}
					if (line.toString().startsWith("# Benchmark: ")) {
						name = line.replace("# Benchmark: ", "").replace("_Benchmark.benchmark_", "");
						boolean found=false;
						for (Test_mutant_hitting obj:records) {
							if (name.equals(obj.name)){
								found=true;
								while ((line = br.readLine()) != null) {
									if (line.toString().startsWith("# Benchmark: ")) {
										name = line.replace("# Benchmark: ", "").replace("_Benchmark.benchmark_", "");
										the_test = new Test_mutant_hitting(name);
										break;
									}
								}
						}
						}
						if(!found) {
							the_test = new Test_mutant_hitting(name);
							continue;
						}


					}
					if(line==null)
						break;

					if (line.toString().startsWith("# Parameters: ")) {
						params = line.replace("# Parameters: ", "").replace("(", "").replace(")", "");
						String[] paramArr = params.split(",");
						for (String element : paramArr) {
							name = name + "_" + element.substring(element.indexOf("=") + 2);
						}
						the_test.name = name;
					}
					if (line.toString().startsWith("Iteration   1:")) {
						if (line.contains("ops/s")) {
							the_test.thrpt.add(line.substring(line.indexOf(":") + 1, line.indexOf(" ops/s")));
						}
						boolean warming_up=false;
						while ((line = br.readLine()) != null) {
							if(line.startsWith("Iteration")) {
								warming_up=false;
							}
							if(line.startsWith("# Warmup")) {
								warming_up=true;
							}
							if (line.startsWith("Result ")) {
								records.add(the_test);
								break;
							}
							if (line.contains("ops/s")&& !warming_up) {
								the_test.thrpt.add(line.substring(line.indexOf(":") + 1, line.indexOf(" ops/s")));
							}
						}

					}
				}
			}
		}
		return records;

	}

	static class Test_mutant_hitting {
		public String name = null;
//		public String thrpt = null;
		public List<String> thrpt = new ArrayList<>();
		Map<String, Integer> mutant_hitting = null;

		public Test_mutant_hitting(String name) {
			this.name = name;
			this.mutant_hitting = new HashMap<>();

		}
	}

}
