package pmt.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.JavaModelException;

import pmt.project.mutator.ProjectMutator;
import pmt.project.setting.MutationSetting;

public class ProjectAnalyzer extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();

		detectInProjects(projects);

		return null;
	}

	private void detectInProjects(IProject[] projects) {

		for (IProject project : projects) {
			if (!project.getName().toString().equals(pmt.project.setting.ProjectSetting.project_name))
				continue;

			ProjectMutator project_mutator = new ProjectMutator();
//			MutationSetting.logging=true;
//			try {
//
//				project_mutator.search_in_projects(project);
//				project_mutator.appendDependencies(project);
//
//			} catch (JavaModelException | IOException e) {
//				e.printStackTrace();
//			}
//			MutationSetting.logging=false;

//			Build and run the project to find mutant_count.csv
//			project_logger = new ProjectLogger();
//
//			try {
//
//			project_mutator.search_in_projects(project);
//
//			} catch (JavaModelException e) {
//				e.printStackTrace();
//			}

			project_mutator = new ProjectMutator();
			MutationSetting.mutating = true;
			try {

				project_mutator.search_in_projects(project);

			} catch (JavaModelException e) {
				e.printStackTrace();
			}
			MutationSetting.mutating = false;

//			run benchmarks
//			project_logger = new ProjectLogger();
//
//			try {
//
//			project_mutator.search_in_projects(project);
//
//			} catch (JavaModelException e) {
//				e.printStackTrace();
//			}

//			evaluations
//			project_logger = new ProjectLogger();
//
//			try {
//
//			project_mutator.search_in_projects(project);
//
//			} catch (JavaModelException e) {
//				e.printStackTrace();
//			}

		}
	}
}
