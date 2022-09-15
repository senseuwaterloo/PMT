package pmt.handlers;

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

import pmt.project.mutator.MutantReplacement;
import pmt.project.mutator.Mutator;
import pmt.utilities.Utilities;

public class ProjectAnalyzer {

	public String str_final = "";

	public static List<MutantReplacement> myMutants = new ArrayList<>();

	public Mutator my_mutator;

	public void projectFinder(IProject project) throws JavaModelException {

		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		my_mutator = new Mutator();
		search_in_packages(packages);

//		try {
//			print_mutants();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
		FileWriter file_writer = new FileWriter("/Users/massi/Desktop/mutants.csv");
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

}
