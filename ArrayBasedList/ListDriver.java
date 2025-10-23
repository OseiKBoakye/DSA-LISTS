import java.util.Arrays;

public class ListDriver implements ListDriverInterface{

	@Override
	public ListInterface<Integer> createList(ListDriverInterface.ListType listType,
			ListDriverInterface.TestType forTestType) {
		ListInterface<Integer> list;
		
		if (listType == ListDriverInterface.ListType.ArrayBasedList) {
			list = new ArrayBasedList<>();
		}
		else {
			list = new LinkedList<>();
		}
		
		switch (forTestType) {
		case AddSortedOdd:
			return list;
		case AddSortedEven:
			return initializeList(list,1, 9999,2);
			
		case AddAll:
			return list;
		case AddAllAtIndexZero:
			return list;
		case RemoveAllEven:
			return initializeList(list,1, 10000,1);
			
		case RemoveAllOdd:
			return initializeList(list,1, 10000,1);
			
		default:
			return list;
		}
		
		
		
	}

	@Override
	public ListInterface<Integer> initializeList(ListInterface<Integer> list, int firstNumber, int lastNumber,
			int increment) {
		
		if (increment == 0) {
			return list;
		}
		
		if (increment > 0) {
			for (int i =firstNumber; i <= lastNumber; i = i + increment) {
				list.add(i);
			}
		}
		else {
			for(int i = firstNumber; i >= lastNumber; i += increment) {
				list.add(i);
			}
		}
		
		return list;
	}

	@Override
	public double memoryUsage() {
		double amount;
		Runtime run = Runtime.getRuntime();
		run.gc();
		
		long totalMemory = run.totalMemory();
		long remainingMemory = run.freeMemory();
		long usedMemory = totalMemory - remainingMemory;
		
		
		amount = (double) usedMemory / (1024.0 * 1024.0);
		return amount;
	}

	@Override
	public Object[] runTestCase(ListDriverInterface.ListType listType, ListDriverInterface.TestType testType,
			int numberOfTimes) {
		
		Object[] main = new Object[(numberOfTimes*2)+1];
		TestTimes times = new TestTimes();
		
		long startTime;
		long endTime;
		long finalTime;
		
		int i;
		
		for (i = 0; i < numberOfTimes; i++) {
			ListInterface<Integer> list = createList(listType,testType);
			ListInterface<Integer> listBeforeTest;
			ListInterface<Integer> listAfterTest;
			
			if(list instanceof ArrayBasedList) {
				listBeforeTest = new ArrayBasedList<>();
			}
			else {
				listBeforeTest = new LinkedList<>();
			}
			
			for (int j = 0; j < list.size(); j++) {
				Integer element = list.get(j);
				listBeforeTest.add(element);
			}
			
			main[i*2] = listBeforeTest;
			
			startTime = System.nanoTime();
			
			
			switch (testType) {
			case AddSortedOdd:
				for (int k = 1; k<=9999; k= k+2) {
					list.addSorted(k);
				}
				break;
				
			case AddSortedEven:
				for (int b = 2; b <= 10000; b +=2) {
					list.addSorted(b);
				}
				break;
			case AddAll:
				for (int p = 1; p <= 10000; p++) {
					list.add(p);
				}
				break;
			case AddAllAtIndexZero:
				for (int c = 1; c <= 10000; c++) {
					list.add(c, 0);
				}
				break;
			case RemoveAllEven:
				for (int d = list.size()-1; d >= 0; d --) {
					if (d % 2 == 1) {
						list.remove(d);
					}
					continue;
				}
				break;
			case RemoveAllOdd:
				for (int e = list.size()-1; e >= 0; e--) {
					if (e % 2 == 0) {
						list.remove(e);
					}
					continue;
				}
				break;
			default:
				break;
				
			}
			
			endTime = System.nanoTime();
			
			
			finalTime = endTime - startTime;
			
			times.addTestTime(finalTime);
			
			if(list instanceof ArrayBasedList) {
				listAfterTest = new ArrayBasedList<>();
			}
			else {
				listAfterTest = new LinkedList<>();
			}
			
			
			for  (int a = 0; a < list.size(); a++) {
				listAfterTest.add(list.get(a));
			}
			
			main[i*2 + 1] = listAfterTest;
			
		}
		
		
		main[numberOfTimes*2] = times;
		
		
		return main;
	}
	
	
	public static void main(String [] args) {
		ListDriver driver = new ListDriver();
		Object [] tests=driver.runTestCase(ListDriverInterface.ListType.ArrayBasedList, ListDriverInterface.TestType.RemoveAllOdd, 1);
//		ListInterface<Integer> tests= driver.createList(ListDriverInterface.ListType.ArrayBasedList, ListDriverInterface.TestType.AddSortedOdd);
//		
//		for (int i = 0; i < tests.size(); i++) {
//			System.out.println(tests.get(i));
//		}
		
//		Object [] t=driver.runTestCase(ListDriverInterface.ListType.ArrayBasedList, ListDriverInterface.TestType.AddSortedOdd, 1);
		ListInterface<Integer> tes = (ListInterface<Integer>) tests[1];
		
		for (int i=0; i < tes.size(); i++) {
			System.out.println(tes.get(i));
		}

	}

}
