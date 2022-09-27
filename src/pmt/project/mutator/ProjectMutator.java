package pmt.project.mutator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import pmt.project.resources.LoggingJUnit;
import pmt.project.setting.MutationSetting;
import pmt.project.setting.ProjectSetting;
import pmt.utilities.Utilities;

public class ProjectMutator {

	public String str_final = "";

	public static List<MutantReplacement> myMutants = new ArrayList<>();

	public PatternMutator my_mutator;

	public void search_in_projects(IProject project) throws JavaModelException {

		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		my_mutator = new PatternMutator();
		search_in_packages(packages);
		if (MutationSetting.logging) {
			try {
				print_mutants();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void search_in_packages(IPackageFragment[] packages) throws JavaModelException {

		for (IPackageFragment mypackage : packages) {
			String package_path = mypackage.getPath().toString();
			if (!pmt.project.setting.ToolSettings.including_junit_tests) {
				if (package_path.contains("/test/"))
					continue;
			}
			if (!pmt.project.setting.ToolSettings.including_jmh_tests) {
				if (package_path.contains("/jmh/"))
					continue;
			}

			find_target_files(mypackage);

		}

	}

	private void find_target_files(IPackageFragment packageFragment) throws JavaModelException {
		for (ICompilationUnit unit : packageFragment.getCompilationUnits()) {
			String className = unit.getElementName().toString();
			boolean target_class = false;
			if (pmt.project.setting.ClassSetting.class_name.equals("")) {
				target_class = true;
			} else if (pmt.project.setting.ClassSetting.class_name.equals(className)) {
				target_class = true;
			}

			if (target_class) {
				CompilationUnit parsedCompilationUnit = Utilities.parse_ICompilationUnit(unit);

				my_mutator.create_mutants(packageFragment, unit, parsedCompilationUnit);
			}

		}
	}

	private void print_mutants() throws IOException {
		FileWriter file_writer = new FileWriter(ProjectSetting.path_to_project + "/mutants.csv");
		StringBuilder sb = new StringBuilder();
		for (MutantReplacement a : myMutants) {

			String finalString = Integer.toString(myMutants.indexOf(a) + 1) + "," + a.className.replace(".java", "")
					+ "." + a.methodName + "." + a.line;

			sb.append(finalString);
			sb.append("\n");

		}
		file_writer.write(sb.toString());
		file_writer.close();
//		System.out.println(Integer.toString(myMutants.size()));

	}

// appending resources
	public void appendDependencies(IProject project) throws JavaModelException, IOException {
		copy_performance_logger(LoggingJUnit.PerformanceMutationJava,
				ProjectSetting.path_to_project + "src/main/java/io/reactivex/rxjava3/core/PerformanceMutation.java");

		copy_performance_logger(LoggingJUnit.JUnitHittingCounter,
				ProjectSetting.path_to_project + "src/test/java/io/reactivex/rxjava3/core/JUnitHittingCounter.java");

		copy_performance_logger(LoggingJUnit.RxJavaTest,
				ProjectSetting.path_to_project + "src/test/java/io/reactivex/rxjava3/core/RxJavaTest.java");
	}

	private void copy_performance_logger(String resource, String full_path) throws IOException {
		FileWriter my_writer = new FileWriter(new File(full_path));
		my_writer.write(resource);
		my_writer.close();

	}

}
