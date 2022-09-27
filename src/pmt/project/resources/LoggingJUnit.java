package pmt.project.resources;

public class LoggingJUnit {
	public static String PerformanceMutationJava="/*\n"
			+ " * Copyright (c) 2016-present, RxJava Contributors.\n"
			+ " *\n"
			+ " * Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in\n"
			+ " * compliance with the License. You may obtain a copy of the License at\n"
			+ " *\n"
			+ " * http://www.apache.org/licenses/LICENSE-2.0\n"
			+ " *\n"
			+ " * Unless required by applicable law or agreed to in writing, software distributed under the License is\n"
			+ " * distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See\n"
			+ " * the License for the specific language governing permissions and limitations under the License.\n"
			+ " */\n"
			+ "\n"
			+ "package io.reactivex.rxjava3.core;\n"
			+ "\n"
			+ "import java.util.Collections;\n"
			+ "import java.util.HashMap;\n"
			+ "import java.util.Map;\n"
			+ "import java.util.TreeMap;\n"
			+ "import java.util.concurrent.ConcurrentHashMap;\n"
			+ "\n"
			+ "import io.reactivex.rxjava3.disposables.Disposable;\n"
			+ "\n"
			+ "/**\n"
			+ " * \n"
			+ " *\n"
			+ " * @author\n"
			+ " * @version\n"
			+ " * @param\n"
			+ " * @return\n"
			+ " * @throws\n"
			+ " * @exception\n"
			+ " * @see\n"
			+ " * @since\n"
			+ " * @serial\n"
			+ " * @serialField\n"
			+ " * @serialData\n"
			+ " * \n"
			+ " */\n"
			+ "public class PerformanceMutation {\n"
			+ "\n"
			+ "	public static Map<Long, Long> mutant_hitting_counter = new TreeMap<Long,Long>();\n"
			+ "\n"
			+ "	/**\n"
			+ "	 * Some javadoc.\n"
			+ "	 *\n"
			+ "	 * @since something\n"
			+ "	 * @version something\n"
			+ "	 * \n"
			+ "	 * @see something\n"
			+ "	 * @author something\n"
			+ "	 * @param i something\n"
			+ "	 */\n"
			+ "	public static void mutantLog(long i) {\n"
			+ "		\n"
			+ "		boolean exist = false;\n"
			+ "		Long found=null;\n"
			+ "		if( !mutant_hitting_counter.keySet().isEmpty())\n"
			+ "		for (Long x : mutant_hitting_counter.keySet()) {\n"
			+ "			if (x.longValue() == i) {\n"
			+ "				\n"
			+ "				found =x;\n"
			+ "				exist = true;\n"
			+ "				break;\n"
			+ "			}\n"
			+ "		}\n"
			+ "		if (!exist) {\n"
			+ "			mutant_hitting_counter.put(new Long(i), new Long(1));\n"
			+ "\n"
			+ "		}else {\n"
			+ "			if(found !=null) {\n"
			+ "			long counter = mutant_hitting_counter.get(found).longValue()+1;\n"
			+ "			\n"
			+ "			mutant_hitting_counter.replace(found, new Long(counter));\n"
			+ "			}\n"
			+ "		}\n"
			+ "		\n"
			+ "	}\n"
			+ "\n"
			+ "}";


	public static String JUnitHittingCounter="/*\n"
			+ " * Copyright (c) 2016-present, RxJava Contributors.\n"
			+ " *\n"
			+ " * Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in\n"
			+ " * compliance with the License. You may obtain a copy of the License at\n"
			+ " *\n"
			+ " * http://www.apache.org/licenses/LICENSE-2.0\n"
			+ " *\n"
			+ " * Unless required by applicable law or agreed to in writing, software distributed under the License is\n"
			+ " * distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See\n"
			+ " * the License for the specific language governing permissions and limitations under the License.\n"
			+ " */\n"
			+ "\n"
			+ "package io.reactivex.rxjava3.core;\n"
			+ "\n"
			+ "import java.io.File;\n"
			+ "import java.io.FileWriter;\n"
			+ "import java.util.ArrayList;\n"
			+ "import java.util.Collections;\n"
			+ "\n"
			+ "/*\n"
			+ "The following code written in Java and can be used for measuring the throughput of JUnit tests.\n"
			+ "It is used in RQ2 of the paper namely \"Automated Generation and Evaluation of JMH Microbenchmark Suites from Unit Tests\", TSE2021\n"
			+ "In order to use:\n"
			+ "1- place this Java class inside the project.\n"
			+ "2- initialize the number of iteration (measurement_iterations) you wish to measure, and the measuring time (measurement_time), and the csv file path for result storing.\n"
			+ "3- inside the JUnit class you want to evaluate, create a new JUnit @Rule refrencing to 'JunitaPerformanceLogger'.\n"
			+ "4- run junit tests, and the results would be printed in output file.\n"
			+ "*/\n"
			+ "\n"
			+ "import java.util.HashMap;\n"
			+ "import java.util.Map;\n"
			+ "import java.util.TreeMap;\n"
			+ "import java.util.concurrent.ConcurrentHashMap;\n"
			+ "\n"
			+ "import org.junit.rules.TestRule;\n"
			+ "import org.junit.runner.Description;\n"
			+ "import org.junit.runners.model.Statement;\n"
			+ "\n"
			+ "/**\n"
			+ " * \n"
			+ " *\n"
			+ " * @author\n"
			+ " * @version\n"
			+ " * @param\n"
			+ " * @return\n"
			+ " * @throws\n"
			+ " * @exception\n"
			+ " * @see\n"
			+ " * @since\n"
			+ " * @serial\n"
			+ " * @serialField\n"
			+ " * @serialData\n"
			+ " * \n"
			+ " */\n"
			+ "public class JUnitHittingCounter implements TestRule {\n"
			+ "\n"
			+ "	/**\n"
			+ "	 * Some javadoc.\n"
			+ "	 *\n"
			+ "	 * @since something\n"
			+ "	 * @version something\n"
			+ "	 * \n"
			+ "	 * @see something\n"
			+ "	 * @author something\n"
			+ "	 * @return something\n"
			+ "	 * @param base        something\n"
			+ "	 * @param description something\n"
			+ "	 */\n"
			+ "\n"
			+ "	@Override\n"
			+ "	public Statement apply(Statement base, Description description) {\n"
			+ "		return new Statement() {\n"
			+ "			@SuppressWarnings(\"deprecation\")\n"
			+ "			@Override\n"
			+ "			public void evaluate() throws Throwable {\n"
			+ "\n"
			+ "				try {\n"
			+ "					base.evaluate();\n"
			+ "				} finally {\n"
			+ "\n"
			+ "					Thread.sleep(100);\n"
			+ "					String compeletName = description.getClassName() + \".\" + description.getMethodName();\n"
			+ "					File myFile = new File(\"/pmt-report/JUnitHittingCounter.csv\");\n"
			+ "					FileWriter csvWriter = new FileWriter(myFile, true);\n"
			+ "					if (!io.reactivex.rxjava3.core.PerformanceMutation.mutant_hitting_counter.isEmpty()) {\n"
			+ "\n"
			+ "						csvWriter.write(compeletName + \",\");\n"
			+ "						for (Long key : io.reactivex.rxjava3.core.PerformanceMutation.mutant_hitting_counter.keySet()) {\n"
			+ "							if (io.reactivex.rxjava3.core.PerformanceMutation.mutant_hitting_counter.get(key) != null) {\n"
			+ "								Long count = io.reactivex.rxjava3.core.PerformanceMutation.mutant_hitting_counter\n"
			+ "										.get(key);\n"
			+ "\n"
			+ "								csvWriter.write(key.toString() + \",\" + count.toString() + \",\");\n"
			+ "							}\n"
			+ "						}\n"
			+ "\n"
			+ "						csvWriter.write(\"\\n\");\n"
			+ "\n"
			+ "						csvWriter.close();\n"
			+ "\n"
			+ "\n"
			+ "						io.reactivex.rxjava3.core.PerformanceMutation.mutant_hitting_counter = new TreeMap<Long, Long>();\n"
			+ "						System.gc();\n"
			+ "					}\n"
			+ "				}\n"
			+ "\n"
			+ "			}\n"
			+ "\n"
			+ "		};\n"
			+ "	}\n"
			+ "}\n"
			+ "";



	public static String RxJavaTest="/*\n"
			+ " * Copyright (c) 2016-present, RxJava Contributors.\n"
			+ " *\n"
			+ " * Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in\n"
			+ " * compliance with the License. You may obtain a copy of the License at\n"
			+ " *\n"
			+ " * http://www.apache.org/licenses/LICENSE-2.0\n"
			+ " *\n"
			+ " * Unless required by applicable law or agreed to in writing, software distributed under the License is\n"
			+ " * distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See\n"
			+ " * the License for the specific language governing permissions and limitations under the License.\n"
			+ " */\n"
			+ "\n"
			+ "package io.reactivex.rxjava3.core;\n"
			+ "\n"
			+ "import java.util.concurrent.ConcurrentHashMap;\n"
			+ "import java.util.concurrent.TimeUnit;\n"
			+ "\n"
			+ "import org.junit.*;\n"
			+ "import org.junit.rules.Timeout;\n"
			+ "\n"
			+ "import io.reactivex.rxjava3.testsupport.SuppressUndeliverableRule;\n"
			+ "\n"
			+ "public abstract class RxJavaTest {\n"
			+ "    @Rule\n"
			+ "    public Timeout globalTimeout = new Timeout(15, TimeUnit.SECONDS);\n"
			+ "    @Rule\n"
			+ "    public final SuppressUndeliverableRule suppressUndeliverableRule = new SuppressUndeliverableRule();\n"
			+ "\n"
			+ "    @Rule\n"
			+ "    public JunitHittingCounter myCounter=new JunitHittingCounter();\n"
			+ "    \n"
			+ "    /**\n"
			+ "     * Announce creates a log print preventing Travis CI from killing the build.\n"
			+ "     */\n"
			+ "    @Test\n"
			+ "    @Ignore\n"
			+ "    public final void announce() {\n"
			+ "    }\n"
			+ "    \n"
			+ "}\n"
			+ "";

}
