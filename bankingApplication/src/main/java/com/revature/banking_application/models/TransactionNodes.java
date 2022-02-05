package com.revature.banking_application.models;

import com.revature.banking_application.models.AccountNodes.Node;

public class TransactionNodes {
	public class Node{
		Transaction data;
		Node next;
		
		public Node(Transaction data) {
			this.data = data;
			this.next = null;
		}

		public Transaction getData() {
			return data;
		}

		public void setData(Transaction data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}	
		
	}
	
	public Node head = null;
	public Node tail = null;
	
	
	public void addNode(Transaction data) {
		Node newNode = new Node(data);
			
		if(head == null) {
			head = newNode;
			tail = newNode;
		}else {
			tail.next = newNode;
			tail = newNode;
		}
			
	}
	public Node returnHead() {
		return head;
	}
	public Node returnTail() {
		return tail;
	}
}
