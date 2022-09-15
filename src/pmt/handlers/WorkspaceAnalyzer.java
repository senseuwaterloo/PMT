package pmt.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class WorkspaceAnalyzer extends AbstractHandler {

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
			
			ProjectAnalyzer projectAnalyzer = new ProjectAnalyzer();

			try {

				projectAnalyzer.projectFinder(project);

			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}
}
