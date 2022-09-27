package pmt.project.visitors.hwo;

import java.io.IOException;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

import pmt.project.mutator.MutantReplacement;
import pmt.project.mutator.ProjectMutator;
import pmt.project.visitors.template.Handler;

public class Injector {
	public Handler myParent;

	public MethodInvocation replacement;

	public void set_unit(Handler myParent) throws JavaModelException {
		this.myParent = myParent;

	}

	@SuppressWarnings("unchecked")
	void inject_mutant_to_ast_rewriter(ASTNode node)
			throws JavaModelException, MalformedTreeException, BadLocationException, IOException {
		MethodDeclaration myMethodNode = (MethodDeclaration) node;

		MethodInvocation replacement = build_replacement_node(node, myParent.rewriter.getAST());
		Block block = myMethodNode.getBody();
		Statement newStatement = myParent.rewriter.getAST().newExpressionStatement(replacement);
		ListRewrite listRewrite = myParent.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
		listRewrite.insertFirst(newStatement, null);

	}

	@SuppressWarnings("unchecked")
	void inject_mutant_logger_to_ast_rewriter(ASTNode node)
			throws JavaModelException, MalformedTreeException, BadLocationException, IOException {
		MethodDeclaration myMethodNode = (MethodDeclaration) node;
		MutantReplacement myReplacement = logging_mutants(myMethodNode);
		ProjectMutator.myMutants.add(myReplacement);

		MethodInvocation replacement = (MethodInvocation) build_logging_node(node, myParent.rewriter.getAST(),
				myReplacement);
		Block block = myMethodNode.getBody();
		Statement newStatement = myParent.rewriter.getAST().newExpressionStatement(replacement);

		ListRewrite listRewrite = myParent.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
		listRewrite.insertFirst(newStatement, null);

	}

	@SuppressWarnings("unchecked")
	private ASTNode build_logging_node(ASTNode node, AST ast, MutantReplacement replacement) {
		// building mutant counter
		SimpleName SimpleName_mutantLog = ast.newSimpleName("mutantLog");

		QualifiedName qualifiedName_Mutation = ast.newQualifiedName(ast.newName("io"), ast.newSimpleName("reactivex"));
		QualifiedName qualifiedName_Mutation2 = ast.newQualifiedName(
				ast.newName(qualifiedName_Mutation.getFullyQualifiedName()), ast.newSimpleName("rxjava3"));
		QualifiedName qualifiedName_Mutation3 = ast.newQualifiedName(
				ast.newName(qualifiedName_Mutation2.getFullyQualifiedName()), ast.newSimpleName("core"));
		QualifiedName qualifiedName_Mutation4 = ast.newQualifiedName(
				ast.newName(qualifiedName_Mutation3.getFullyQualifiedName()), ast.newSimpleName("PerformanceMutation"));

		// Boolean.valueOf(node)
		MethodInvocation invocation = ast.newMethodInvocation();
		invocation.setName(SimpleName_mutantLog);
		invocation.setExpression(qualifiedName_Mutation4);

		invocation.arguments().add(ast.newNumberLiteral(Integer.toString(ProjectMutator.myMutants.size())));

		return invocation;

	}

	private MutantReplacement logging_mutants(MethodDeclaration node) {
		// loggin mutants
		String className = myParent.visitor.unit.getPath().toString().replace("/rxjava/src/main/java/", "")
				.replaceAll("/", ".");
		String methodName = node.getName().getFullyQualifiedName();

		String position = Integer.toString(node.getStartPosition());
		int lineNumber = myParent.visitor.parsedCompilationUnit.getLineNumber(node.getStartPosition()) - 1;
		String line = Integer.toString(lineNumber);
		return new MutantReplacement(node.toString(), className, methodName, "", line, position);
	}

	@SuppressWarnings("unchecked")
	MethodInvocation build_replacement_node(ASTNode node, AST ast) {
		MethodInvocation slowdown_invocation = (MethodInvocation) build_mutant_node(node.getAST());

		return slowdown_invocation;
//		return null;
	}

	@SuppressWarnings("unchecked")
	private ASTNode build_mutant_node(AST ast) {
		// building mutant counter
		SimpleName SimpleName_mutantLog = ast.newSimpleName("slowdown");

		QualifiedName qualifiedName_Mutation = ast.newQualifiedName(ast.newName("io"), ast.newSimpleName("reactivex"));
		QualifiedName qualifiedName_Mutation2 = ast.newQualifiedName(
				ast.newName(qualifiedName_Mutation.getFullyQualifiedName()), ast.newSimpleName("rxjava3"));
		QualifiedName qualifiedName_Mutation3 = ast.newQualifiedName(
				ast.newName(qualifiedName_Mutation2.getFullyQualifiedName()), ast.newSimpleName("core"));
		QualifiedName qualifiedName_Mutation4 = ast.newQualifiedName(
				ast.newName(qualifiedName_Mutation3.getFullyQualifiedName()), ast.newSimpleName("PerformanceMutation"));

		// Boolean.valueOf(node)
		MethodInvocation invocation = ast.newMethodInvocation();
		invocation.setName(SimpleName_mutantLog);
		invocation.setExpression(qualifiedName_Mutation4);

		return invocation;

	}

}
