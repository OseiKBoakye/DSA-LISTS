import java.util.Arrays;

public class ArrayBasedList <I extends Comparable<? super I>> implements ListInterface{
	
	private int numElements = 0;
	private static final int SIZE = 10;
	private Object[] array = new Object[SIZE];
	
	
	

	

	@Override
	public ListInterface copy() {
		ArrayBasedList listCopy = new ArrayBasedList();
		
		if (numElements == 0) {
			return listCopy;
		}
		else {
			for (int i = 0; i < this.numElements; i++) {
				listCopy.add((Comparable)array[i]);
			}
		}
		
		
		
		return listCopy;
		
		
	}

	@Override
	public int size() {
		
		return this.numElements;
	}

	@Override
	public boolean isEmpty() {
		
		return (this.numElements==0);
	}

	@Override
	public void add(Comparable element) {
		if (this.isFull()) {
			this.resize();
		}
		
		this.array[this.numElements] = element;
		this.numElements++;
	}
	
	public boolean isFull() {
		return (this.numElements == this.array.length);
	}
	
	
	public void resize() {
		Object[] extendedArray = new Object[array.length*2];
		
		for(int i =0; i < this.numElements; i++) {
			extendedArray[i]= array[i];
		}
		
		array = extendedArray;
	}
	
	
	

	@Override
	public void add(Comparable element, int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >this.numElements) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		if(index == this.numElements) {
			add(element);
		}
		else {
			if(isFull()) {
				resize();
			}
			
			for (int i=this.numElements; i > index; i--) {
				this.array[i] = this.array[i-1];
			}
			this.array[index] = element;
			this.numElements++;
			
		}
			
	}

	@Override
	public void addSorted(Comparable element) {
		int index = 0;
		
		while (index < numElements) {
			Comparable a = (Comparable) array[index];
			
			if (a.compareTo(element)<0) {
				index++;
			}
			else {
				break;
			}
			
		}
		
		add(element, index);
		
	}
	

	@Override
	public Comparable get(int index) throws IndexOutOfBoundsException {
		
		if (index < 0 || index >this.numElements) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		Comparable replacedNum = (Comparable)array[index];
		return replacedNum;
	
	}

	@Override
	public Comparable replace(Comparable element, int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >this.numElements) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		Comparable replacedNum = (Comparable)this.array[index];
		
		
		this.array[index] = element;
		
		return replacedNum;
		
	}

	@Override
	public Comparable remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >=this.numElements) {
			throw new IndexOutOfBoundsException("index "+ index+" is out of bounds");
		}
		
		
		
		Comparable removedNum;
		
		if (this.numElements == 1) {
			
			removedNum = (Comparable) this.array[this.numElements-1];
			removeAll();
			return removedNum;
		}
		else if(index == this.numElements-1) {
			
			removedNum = (Comparable) this.array[index];
			this.numElements--;
			return removedNum;
		}
		else {
			removedNum = (Comparable) this.array[index];
			
			for (int i = index; i < numElements-1; i++) {
				this.array[i] = this.array[i+1];
			}
			
			this.numElements--;
			return removedNum;
		}
		
	}

	@Override
	public void removeAll() {
		this.numElements = 0;
		this.array = new Object[this.array.length];
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		ArrayBasedList<Integer> ab = new ArrayBasedList<>();
		
		System.out.println(ab.size());
		System.out.println(ab.isEmpty());
		
		ab.add(1);
		ab.add(10);
		ab.add(2);
		ab.add(4);
		ab.add(5);
		
		System.out.println(ab.size());
		System.out.println(ab.isFull());
		System.out.println(ab.isEmpty());
		
		ListInterface copy = ab.copy();
		System.out.println(copy.size());
	}
	

}
