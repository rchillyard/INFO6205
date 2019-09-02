package edu.neu.coe.info6205.lab_1;

import java.util.*;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This class represents an immutable tree with immutable nodes.
 * Yes, it does work!
 *
 * @param <X> the underlying type of the nodes and tree.
 */
public class MyTree<X> {

		static class Node<X> {
				/**
				 * Constructor to create a new Node from an X value and a set of child nodes.
				 * @param x a value of X.
				 * @param children the set of children for the new Node.
				 */
				Node(X x, ImmutableList<Node<X>> children) {
						this.x = x;
						this.children = children;
				}

				/**
				 * Constructor to create a new Node from an X value and no children.
				 * @param x a value of X.
				 */
				Node(X x) {
						this(x, ImmutableList.of());
				}

				/**
				 * Method to add a child of value X to this tree.
				 * @param y a Node.
				 * @return a copy of this Node but with an additional child.
				 */
				public Node<X> addChild(Node<X> y) {
						ImmutableList.Builder<Node<X>> builder = ImmutableList.builder();
						// TO BE IMPLEMENTED ...
						return null;
						// ... END IMPLEMENTATION
				}

				/**
				 * Method to add a child of value X to this tree.
				 * @param xx the value of X.
				 * @return a copy of this Node but with an additional child.
				 */
				public Node<X> addChild(X xx) {
						return addChild(new Node<X>(xx));
				}

				/**
				 * Method to replace child y by z in this Node.
				 * @param y the Node which is to replace y.
				 * @return the new Node which is a copy of this Node but with y replaced by z.
				 */
				public Node<X> replace(Node<X> y, Node<X> z) {
						ArrayList<Node<X>> ns = Lists.newArrayList(children.iterator());
						boolean ok = Iterables.removeIf(ns, Predicates.equalTo(y));
						ImmutableList.Builder<Node<X>> builder = ImmutableList.builder();
						// TO BE IMPLEMENTED ...
						return null;
						// ... END IMPLEMENTATION
				}

				final X x;
				final ImmutableList<Node<X>> children;

				/**
				 * Method to replace child y by z in this Node.
				 * @param y the Node which is to replace y.
				 * @return the new Node which is a copy of this Node but with y replaced by z.
				 */
				public Node<X> replace(Node<X> y, X z) {
						return replace(y, new Node<X>(z));
				}
		}

		/**
		 * Public constructor for MyTree from an explicit root node.
		 * @param root the root node.
		 */
		public MyTree(Node<X> root) {
				this.root = root;
		}

		/**
		 *
		 * Public constructor for MyTree from an explicit value for the root.
		 * @param x an X value.
		 */
		public MyTree(X x) {
				this(new MyTree.Node<X>(x));
		}

		public Node<X> getRoot() {
				return root;
		}

		/**
		 * Method to add a child of value X to this tree.
		 * @param y a Node.
		 * @return a copy of this Node but with an additional child.
		 */
		public MyTree<X> addChild(Node<X> y) {
				return new MyTree<>(root.addChild(y));
		}

		final Node root;
}
