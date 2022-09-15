package pmt.project.visitors.ptw;

import java.io.IOException;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

import pmt.handlers.ProjectAnalyzer;
import pmt.project.mutator.MutantReplacement;


public class Injector {
	public Handler myParent;

	public MethodInvocation replacement;

	public void set_unit(Handler myParent) throws JavaModelException {
		this.myParent = myParent;

	}

	@SuppressWarnings("unchecked")
	void inject_mutant_to_ast_rewriter(ASTNode node)
			throws JavaModelException, MalformedTreeException, BadLocationException, IOException {
		Visitor.str_wrapper = Utilities.extractWrapperType(Validator.typeName);
		Visitor.str_primitiveValue = Validator.typeName + "Value";

		MutantReplacement myReplacement = logging_mutants(node);

		ProjectAnalyzer.myMutants.add(myReplacement);


			MethodInvocation replacement = (MethodInvocation)build_logging_node(node, myParent.rewriter.getAST(),myReplacement);
		myParent.rewriter.replace(node,replacement , null);

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

		if (node instanceof BooleanLiteral) {
			BooleanLiteral myNode = (BooleanLiteral) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		} else if (node instanceof SimpleName) {
			SimpleName myNode = (SimpleName) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		} else if (node instanceof NumberLiteral) {
			NumberLiteral myNode = (NumberLiteral) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		} else if (node instanceof PrefixExpression) {
			PrefixExpression myNode = (PrefixExpression) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		} else if (node instanceof PostfixExpression) {
			PostfixExpression myPS = (PostfixExpression) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myPS);
		} else if (node instanceof MethodInvocation) {
			MethodInvocation myNode = (MethodInvocation) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		} else if (node instanceof QualifiedName) {
			QualifiedName myNode = (QualifiedName) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		} else if (node instanceof InfixExpression) {
			InfixExpression myNode = (InfixExpression) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation.arguments().add(myNode);
		}
		invocation.arguments().add(ast.newNumberLiteral(Integer.toString(replacement.get_hash())));

		return invocation;

	}

	private MutantReplacement logging_mutants(ASTNode node) {
		// loggin mutants
		String className = myParent.visitor.unit.getPath().toString().replace("/rxjava/src/main/java/", "").replaceAll("/",
				".");
		String methodName = "";
		if (pmt.utilities.Utilities.findParentMethodDeclaration(node) instanceof MethodDeclaration) {
			MethodDeclaration myMethod = (MethodDeclaration) pmt.utilities.Utilities.findParentMethodDeclaration(node);
			methodName = myMethod.getName().getFullyQualifiedName();
		}
		String position = Integer.toString(node.getStartPosition());
		int lineNumber = myParent.visitor.parsedCompilationUnit.getLineNumber(node.getStartPosition()) - 1;
		String line = Integer.toString(lineNumber);
		return new MutantReplacement(node.toString(), className, methodName, Validator.typeName.toString(), line,
				position);
	}

	@SuppressWarnings("unchecked")
	MethodInvocation build_replacement_node(ASTNode node, AST ast) {
		SimpleName SimpleName_valueOf = ast.newSimpleName(Visitor.str_valueOf);

		SimpleName SimpleName_booleanValue = ast.newSimpleName(Visitor.str_primitiveValue);

		SimpleName SimpleName_Boolean = ast.newSimpleName(Visitor.str_wrapper);

		// Boolean.valueOf(node)
		MethodInvocation invocation_valueOf = ast.newMethodInvocation();
		invocation_valueOf.setName(SimpleName_valueOf);
		invocation_valueOf.setExpression(SimpleName_Boolean);

		if (node instanceof BooleanLiteral) {
			BooleanLiteral myNode = (BooleanLiteral) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		} else if (node instanceof SimpleName) {
			SimpleName myNode = (SimpleName) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		} else if (node instanceof NumberLiteral) {
			NumberLiteral myNode = (NumberLiteral) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		} else if (node instanceof PrefixExpression) {
			PrefixExpression myNode = (PrefixExpression) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		} else if (node instanceof PostfixExpression) {
			PostfixExpression myPS = (PostfixExpression) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myPS);
		} else if (node instanceof MethodInvocation) {
			MethodInvocation myNode = (MethodInvocation) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		} else if (node instanceof QualifiedName) {
			QualifiedName myNode = (QualifiedName) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		} else if (node instanceof InfixExpression) {
			InfixExpression myNode = (InfixExpression) ASTNode.copySubtree(myParent.rewriter.getAST(), node);
			invocation_valueOf.arguments().add(myNode);
		}

		// Boolean.valueOf(node).booleanValue()
		MethodInvocation invocation_booleanValue = ast.newMethodInvocation();
		invocation_booleanValue.setName(SimpleName_booleanValue);
		ParenthesizedExpression ps = ast.newParenthesizedExpression();
		ps.setExpression(invocation_valueOf);
		invocation_booleanValue.setExpression(ps);
		return invocation_booleanValue;

	}

}
