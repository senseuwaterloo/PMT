package pmt.project.visitors.soc;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class Validator {

	public static String typeName = "";
//	Validation

	static boolean validate(ASTNode node) {

		return is_personally_valid(node) && is_socially_valid(node);
	}

	static boolean is_personally_valid(ASTNode node) {
		if(node instanceof InfixExpression) {
			InfixExpression infix_expression=(InfixExpression) node;
			if(infix_expression.getOperator().equals(InfixExpression.Operator.CONDITIONAL_OR)) {
				if(infix_expression.getRightOperand() instanceof MethodInvocation) {
					if(!(infix_expression.getLeftOperand() instanceof MethodInvocation)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	static boolean is_socially_valid(ASTNode node) {

		return true;
	}

}
