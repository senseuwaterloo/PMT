package pmt.project.visitors.efl;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ForStatement;

public class Validator {

	public static String typeName = "";
//	Validation

	static boolean validate(ASTNode node) {

		return is_personally_valid(node) && is_socially_valid(node);
	}

	static boolean is_personally_valid(ASTNode node) {
		ForStatement for_statement=(ForStatement)node;
		boolean valid=false;
//		if(!(for_statement.getNodeType() == ASTNode.ENHANCED_FOR_STATEMENT)) {
//			if(for_statement.getExpression() instanceof InfixExpression) {
//				InfixExpression expression=(InfixExpression)for_statement.getExpression();
//				boolean is_valid_expression=false;
//				if(expression.getOperator().equals(InfixExpression.Operator.LESS) || expression.getOperator().equals(InfixExpression.Operator.LESS_EQUALS)) {
//					if(expression.getRightOperand() instanceof MethodInvocation) {
//						MethodInvocation right_operand=(MethodInvocation) expression.getRightOperand();
//						it=right_operand.getExpression().resolveTypeBinding();
//					}
//				}
//
//			}
//		}
		return valid;
	}

	static boolean is_socially_valid(ASTNode node) {

		return true;
	}

}
