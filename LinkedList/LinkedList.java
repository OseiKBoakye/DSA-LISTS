
public class LinkedList<I extends Comparable<? super I>> implements ListInterface<I> {
	
	private int count;
	private LinkedListNode<I> head;
	private LinkedListNode<I> tail;
	
	public LinkedList() {
		this.count = 0;
		this.head = null;
		this.tail = null;
	}
	

	@Override
	public ListInterface<I> copy() {
		LinkedList<I> newList = new LinkedList<I>();
		
		if(head == null) {
			return newList;
		}
		
		
		
		LinkedListNode<I> nodeCopy = new LinkedListNode<I>(this.head.getElement());
		
		newList.head = nodeCopy;
		
		LinkedListNode<I> currentOrig = this.head.getNext();
		LinkedListNode<I> currentCopy = newList.head;
		
		while(currentOrig != null) {
			LinkedListNode<I> node = new LinkedListNode<I>(currentOrig.getElement());
			currentCopy.setNext(node);
			currentCopy = node;
			currentOrig = currentOrig.getNext();
			
		}
		newList.count = this.count;
		
		
		return newList;
		
		
	}

	@Override
	public int size() {
		return this.count;
	}

	@Override
	public boolean isEmpty() {
		return (this.count == 0);
	}

	@Override
	public void add(Comparable element) {
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		
		
		LinkedListNode node = new LinkedListNode(element);
		
		if (this.head == null) {
			this.head = node;
			this.tail = node;
			
		}
		else {
			LinkedListNode current = head;
			
			while (current.getNext() != null) {
				current = current.getNext();
			}
			
			current.setNext(node); 
			tail = node;
		}
		
		count++;
		
	}

	@Override
	public void add(Comparable element, int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		
		LinkedListNode node = new LinkedListNode(element);
		LinkedListNode previousNode;
		
		
		if (this.head == null) {
			this.head = node;
			this.tail = node;
			
		}
		
		else if (index == count-1) {
			previousNode = getNode(index-1);
			node.setNext(previousNode.getNext());
			previousNode.setNext(node);
			
			System.out.println("Succesfully added " + node.getElement()+ " at index " + index);
		}
		else if(index == 0) {
			node.setNext(head);
			head = node;
			
			System.out.println("Succesfully added " + node.getElement()+ " at index " + index);
		}
		else {
			 previousNode = getNode(index-1);
			LinkedListNode nextNode = previousNode.getNext();
			
			previousNode.setNext(node);
			node.setNext(nextNode);
			
			System.out.println("Succesfully added " + node.getElement()+ " at index " + index);
		}
		count++;
		
	}

	@Override
	public void addSorted(I element) {
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		LinkedListNode<I> node = new LinkedListNode<I>(element);
		
		if (isEmpty() ) {
			this.head= node;
			tail = node;
		}
		else if(node.getElement().compareTo(this.head.getElement()) < 0) {
			node.setNext(this.head);
			this.head = node;
		}
		else {
			LinkedListNode<I> current = this.head;
			
			while(current.getNext() != null &&  current.getNext().getElement().compareTo(element) < 0) {
				current = current.getNext();
			}
			
			node.setNext(current.getNext());;
			current.setNext(node);
			
			if (node.getNext() == null) {
	            tail = node;
	        }
			
		}
		
		count++;
		
	}

	@Override
	public I get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
//		LinkedListNode<I> current = head;
//		for (int i = 0 ; i < index; i++) {
//			current = current.getNext();
//		}
		
		LinkedListNode<I> current = getNode(index);
		return current.getElement();
		
		
	}
	
	
	
	private LinkedListNode<I> getNode(int index) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		LinkedListNode current = head;
		for (int i = 0 ; i < index; i++) {
			if (current == null) {
				throw new NullPointerException();
			}
			
			current = current.getNext();
		}
		 return current;
		
		
	}

	@Override
	public I replace(I element, int index) throws IndexOutOfBoundsException {
		
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		
		
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		I replacedNode=  getNode(index).getElement();
		
		LinkedListNode current = getNode(index);
		current.setElement(element);
		
		return  replacedNode;
		
	}

	@Override
	public I remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.count) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		int i=0;
		
		LinkedListNode current = head;
		
		while(i != index) {
			current = current.getNext();
			i++;
		}
		
		LinkedListNode predecessor;
		LinkedListNode successor;
		
		if(index == 0) {
			successor =current.getNext();
			this.head =successor;
			
		}
		else if(index == count-1) {
			predecessor =getNode(index-1);
			predecessor.setNext(null);
			this.tail = predecessor;
		}
		else {
			successor = current.getNext();
			predecessor = getNode(index-1);
			predecessor.setNext(successor);
		}
		
		count--;
		return (I) current.getElement();
		
	}

	@Override
	public void removeAll() {
		this.head = null;
		this.tail = null;
		this.count=0;
		
	}
	
	

}
