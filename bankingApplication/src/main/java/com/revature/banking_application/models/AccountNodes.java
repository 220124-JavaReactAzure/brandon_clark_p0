package com.revature.banking_application.models;

public class AccountNodes {
	public class Node{
		BankAccount data;
		Node next;
		
		public Node(BankAccount data) {
			this.data = data;
			this.next = null;
		}

		public BankAccount getData() {
			return data;
		}

		public void setData(BankAccount data) {
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
	
	
	public void addNode(BankAccount data) {
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
	
//	public boolean unusedUserName(String userInput) {
//		Node runner = head;
//		
//		while(runner != null) {
//			if(runner.getData().getUsername().equalsIgnoreCase(userInput)) {
//				return true;
//			}
//			runner = runner.next;
//		}
//		
//		return false;
//	}
//	
//	public boolean unusedEmail(String userInput) {
//		Node runner2 = head;
//		
//		while(runner2 != null) {
//			if(runner2.getData().getEmail().equalsIgnoreCase(userInput)) {
//				return true;
//			}
//			runner2 = runner2.next;
//		}
//		
//		return false;
//	}
//	
//	public BankUser returnBankUser(String userInput) {
//		Node runner = head;
//		
//		while(runner != null) {
//			if(runner.getData().getUsername().equalsIgnoreCase(userInput)) {
//				return runner.data;
//			}
//			runner = runner.next;
//		}
//		
//		return null;
//	}
}
