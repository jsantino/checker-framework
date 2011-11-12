package checkers.flow.controlflowgraph;

import java.util.Set;

import com.sun.source.tree.ExpressionTree;

/**
 * Represents a conditional basic block in a control graph, that is a basic
 * block that has the condition of an if-statement as contents. This basic block
 * has two special successors, namely the entry block of the then branch and the
 * entry block of the else branch. Furthermore, if the condition can throw an
 * exception, then there can also be further successors.
 * 
 * <p>
 * The successors returned by getSuccessors are the same set as the union of
 * getThenSuccessor, getElseSuccessor and getExceptionalSuccessors.
 * 
 * @author Stefan Heule
 * 
 */
public interface ConditionalBasicBlock extends BasicBlock {

	/**
	 * @return The entry block of the then branch.
	 */
	public BasicBlock getThenSuccessor();

	/**
	 * @return The entry block of the else branch.
	 */
	public BasicBlock getElseSuccessor();

	/**
	 * @return The exceptional successors of the condition.
	 */
	public Set<BasicBlock> getExceptionalSuccessors();

	/**
	 * @return The condition of the if statement.
	 */
	public ExpressionTree getCondition();
}
