package pmt.project.visitors.ptw;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class Validator {

	public static String typeName = "";
//	Validation

	static boolean validate(ASTNode node) {

		return is_personally_valid(node) && is_socially_valid(node);
	}

	static boolean is_personally_valid(ASTNode node) {
		return is_in_primitive_list(node);
	}

	static boolean is_socially_valid(ASTNode node) {
		boolean conditions = false;
		conditions = conditions || Utilities.is_in_field_declaration(node);
		if (node.getParent() instanceof MethodInvocation) {
			MethodInvocation myMethod = (MethodInvocation) node.getParent();
			if (myMethod.getName().toString().equals("mutantLog")) {
				return false;
			}
		}

		if (node instanceof BooleanLiteral) {
			return true;
		} else if (node instanceof SimpleName) {
			conditions = conditions || Utilities.is_prefix(node) || Utilities.is_postfix(node);

			if (node.getParent() instanceof QualifiedName) {
				return false;
			}

			if (node.getParent() instanceof InfixExpression) {

				return !conditions;

			} else if (node.getParent() instanceof Assignment
					&& node.getLocationInParent().getId().equals("rightHandSide")) {

				return !conditions;
			} else if (node.getParent() instanceof VariableDeclarationFragment
					&& node.getLocationInParent().getId().equals("initializer")) {
				return !conditions;
			} else if (node.getParent() instanceof ReturnStatement) {

				return !conditions;
			}

		} else if (node instanceof NumberLiteral) {
			conditions = conditions || Utilities.is_prefix(node) || Utilities.is_postfix(node);
			return !conditions;
		} else if (node instanceof PrefixExpression) {
			PrefixExpression myNode = (PrefixExpression) node;
			conditions = conditions || myNode.getOperand() instanceof SimpleName
					|| myNode.getOperand() instanceof QualifiedName;

			return !conditions;
		} else if (node instanceof PostfixExpression) {
			PostfixExpression myNode = (PostfixExpression) node;
			conditions = conditions || myNode.getOperand() instanceof SimpleName
					|| myNode.getOperand() instanceof QualifiedName;
			return !conditions;
		} else if (node instanceof MethodInvocation) {
			conditions = conditions || Utilities.is_prefix(node) || Utilities.is_postfix(node);
			MethodInvocation myInvo = (MethodInvocation) node;
			if (myInvo.getName().toString().equals("valueOf") || myInvo.getName().toString().equals("intValue")
					|| myInvo.getName().toString().equals("booleanValue")
					|| myInvo.getName().toString().equals("longValue")
					|| myInvo.getName().toString().equals("doubleValue")
					|| myInvo.getName().toString().equals("floatValue")
					|| myInvo.getName().toString().equals("shortValue")) {
				return false;
			}
			if (node.getParent() instanceof InfixExpression) {

				return !conditions;

			}
			if (node.getParent() instanceof Assignment && node.getLocationInParent().getId().equals("rightHandSide")) {

				return !conditions;
			} else if (node.getParent() instanceof VariableDeclarationFragment
					&& node.getLocationInParent().getId().equals("initializer")) {
				return !conditions;
			} else if (node.getParent() instanceof ReturnStatement) {

				return !conditions;
			}
		} else if (node instanceof QualifiedName) {
			conditions = conditions || Utilities.is_prefix(node) || Utilities.is_postfix(node);
			if (node.getParent() instanceof InfixExpression) {

				return !conditions;

			}
			if (node.getParent() instanceof Assignment && node.getLocationInParent().getId().equals("rightHandSide")) {

				return !conditions;
			} else if (node.getParent() instanceof VariableDeclarationFragment
					&& node.getLocationInParent().getId().equals("initializer")) {
				return !conditions;
			} else if (node.getParent() instanceof ReturnStatement) {

				return !conditions;
			}

		} else if (node instanceof InfixExpression) {
			conditions = conditions || Utilities.is_prefix(node) || Utilities.is_postfix(node);

			return !conditions;
		}
		return false;

	}

	static boolean is_in_primitive_list(ASTNode node) {

		if (node instanceof BooleanLiteral) {
			typeName = "boolean";
		} else if (node instanceof SimpleName) {
			SimpleName myNode = (SimpleName) node;
			if (myNode.resolveTypeBinding() != null) {

				typeName = myNode.resolveTypeBinding().getName().toString();

			}
		} else if (node instanceof NumberLiteral) {
			NumberLiteral myNode = (NumberLiteral) node;
			if (myNode.resolveTypeBinding() != null) {

				typeName = myNode.resolveTypeBinding().getName().toString();

			}
		} else if (node instanceof PrefixExpression) {
			PrefixExpression myNode = (PrefixExpression) node;
			if (myNode.getOperand().resolveTypeBinding() != null) {

				typeName = myNode.getOperand().resolveTypeBinding().getName().toString();

			}

		} else if (node instanceof PostfixExpression) {
			PostfixExpression myNode = (PostfixExpression) node;
			if (myNode.getOperand().resolveTypeBinding() != null) {

				typeName = myNode.getOperand().resolveTypeBinding().getName().toString();

			}
		} else if (node instanceof MethodInvocation) {
			MethodInvocation myNode = (MethodInvocation) node;
			if (myNode.resolveTypeBinding() != null) {

				typeName = myNode.resolveTypeBinding().getName().toString();

			}
		} else if (node instanceof QualifiedName) {
			QualifiedName myNode = (QualifiedName) node;
			if (myNode.resolveTypeBinding() != null) {

				typeName = myNode.resolveTypeBinding().getName().toString();

			}
		} else if (node instanceof InfixExpression) {
			InfixExpression myNode = (InfixExpression) node;
			if (myNode.resolveTypeBinding() != null) {

				typeName = myNode.resolveTypeBinding().getName().toString();

			}
		}

		if (!typeName.isEmpty() && Handler.primitive_wrapper_list.primitiveList.contains(typeName)) {

			return true;

		}
		return false;
	}

}
