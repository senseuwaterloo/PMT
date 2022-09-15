package pmt.utilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jface.text.Document;

public class Utilities {

	public static ICompilationUnit getWorkCopy(ICompilationUnit unit) {
		ICompilationUnit workingCopy = null;
		try {
			workingCopy = unit.getWorkingCopy(new WorkingCopyOwner() {
			}, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workingCopy;

	}

	public static IBuffer getBuffer(ICompilationUnit workingCopy) {
		IBuffer buffer = null;
		try {
			buffer = ((IOpenable) workingCopy).getBuffer();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;

	}

	public static void setChanges(ICompilationUnit workingCopy) {

		try {
			workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workingCopy.commitWorkingCopy(false, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workingCopy.discardWorkingCopy();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ASTNode findForParent(ASTNode node) {
		if (node.getParent() != null) {
			if (node.getParent() instanceof ForStatement) {
				return node.getParent();
			}

			return findForParent(node.getParent());
		}
		return null;
	}

	public static ASTNode findParentMethodDeclaration(ASTNode node) {

		if (node.getParent() != null) {
			Integer parentNodeType = node.getParent().getNodeType();

			if ((parentNodeType == ASTNode.METHOD_DECLARATION) || (parentNodeType == ASTNode.INITIALIZER) || (parentNodeType == ASTNode.TYPE_DECLARATION)) {
				return node.getParent();
			}

			return findParentMethodDeclaration(node.getParent());
		} else {
			return null;
		}
	}

	public static String getMethodName(ASTNode node) {

		String methodName;
		if (node.getNodeType() == ASTNode.METHOD_DECLARATION) {
			MethodDeclaration parentMethod = (MethodDeclaration) node;
			IMethodBinding parentMethodBinding = parentMethod.resolveBinding();

			methodName = (parentMethodBinding != null) ? parentMethodBinding.getKey()
					: getMethodNameWithoutBinding(parentMethod, false);

		} else if (node.getNodeType() == ASTNode.INITIALIZER) {
			methodName = "!NAME_NA!"; // name not applicable
		} else
			methodName = "!UNEXPECTED_KIND!";

		return methodName;
	}

	public static String getMethodNameWithoutBinding(MethodDeclaration method, Boolean quotes) {

		String methodName = new String();

		methodName = ((quotes) ? "\"" : "") + method.getName().toString();
		methodName += "(";

		for (Object param : method.parameters()) {
			SingleVariableDeclaration svParam = (SingleVariableDeclaration) param;
			methodName += svParam.getType().toString() + ",";
		}
		methodName += ")" + ((quotes) ? "\"" : "");

		methodName = methodName.replace(",)", ")");

		return methodName;

	}

	public static CompilationUnit parse_ICompilationUnit(ICompilationUnit unit) {
		@SuppressWarnings("deprecation")
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}

	public static CompilationUnit parse_Document(Document doc) {
		@SuppressWarnings("deprecation")
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(doc.get().toString().toCharArray());
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
	public static CompilationUnit parse_from_file(String path) {
		@SuppressWarnings("deprecation")
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath myPath = Path.fromOSString(path);
        IFile file = workspace.getRoot().getFile(myPath);
//        CompilationUnit compilationUnit =  (CompilationUnit) JavaCore.create(file);
        ICompilationUnit element =  JavaCore.createCompilationUnitFrom(file);
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setSource(element);
        CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
        return astRoot;
	}

}
