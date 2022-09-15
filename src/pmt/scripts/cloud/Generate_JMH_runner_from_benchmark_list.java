package pmt.scripts.cloud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generate_JMH_runner_from_benchmark_list {

	public static String benchmark_path = "/Users/mj/Desktop/tmp/benchmarks.txt";
	public static String workspace_path = "/Users/mj/Desktop/tmp/";
	public static String jmhLock_path = "/var/folders/zz/zyxvpxvq6csfxvn_n0000000000000/T/jmh.lock";
	public static String jmh_jar_path = "/Users/mj/workspace/RxJava/build/libs/rxjava-3.0.0-SNAPSHOT-jmh.jar";
	public static String run_configirations = "-i 1 -r 1 -wi 0 -w 0 -f 1 -gc true -foe true -to 1min -t 1 -bm ss";

	public static List<String> benchamrks = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		generate(benchmark_path);

	}

	private static void generate(String benchmarks) throws FileNotFoundException, IOException {
		File file = new File(benchmarks);

		Scanner in = new Scanner(file);
		while (in.hasNextLine()) {
			String data = in.nextLine();
			benchamrks.add(data);
		}
		try (FileWriter runnerSh = new FileWriter(workspace_path + "runner.sh")) {

			StringBuilder sb = new StringBuilder();

			for (String bench : benchamrks) {

				sb.append("echo \"Started running : " + bench + "\"\n");
				sb.append("pkill -9 java" + "\n");
				sb.append("rm " + jmhLock_path + "\n");
				sb.append("java -jar " + jmh_jar_path + " " + run_configirations + " " + bench + "$\n");

			}
			runnerSh.write(sb.toString());
			runnerSh.close();
		}
		in.close();
	}
}
