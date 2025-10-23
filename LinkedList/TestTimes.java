
public class TestTimes implements TestTimesInterface {
	final int size = 10;
	private long[] testTimes;
	private int countTime;
	
	public TestTimes() {
		this.testTimes = new long[size];
		this.countTime = 0;
	}

	@Override
	public long getLastTestTime() {
		if(this.countTime == 0) {
			return 0;
		}
		
		return this.testTimes[Math.min(this.countTime-1, this.testTimes.length-1)];
		
	}

	@Override
	public long[] getTestTimes() {
		long[] times = new long[this.testTimes.length];
		
		if (this.countTime <= this.testTimes.length) {
			for (int i=0; i < countTime; i++) {
				times[i] = testTimes[i];
			}
			return times;
		}
		else {
			for (int i=0; i < testTimes.length;i++) {
				times[i] = testTimes[i];
			}
			return times;
		}
		
	}

	@Override
	public void resetTestTimes() {
		for (int i = 0; i < testTimes.length;i++) {
			testTimes[i] = 0;
		}
		this.countTime = 0;
	}

	@Override
	public void addTestTime(long testTime) {
		
		if(this.countTime < this.testTimes.length){
			this.testTimes[this.countTime] = testTime;
			this.countTime++;
		}
		
		else {
			for(int i = 0; i < testTimes.length-1; i++) {
				this.testTimes[i] = this.testTimes[i+1];
			}
			
			this.testTimes[testTimes.length-1] = testTime;
			
		}
			
	}
		
		
	

	@Override
	public double getAverageTestTime() {
		double sum=0;
		double size=0;
		
		if (this.countTime != 0) {
			for(int i = 0; i < testTimes.length; i++) {
				if(testTimes[i] != 0) {
					sum += (double)testTimes[i];
					size++;
				}
			}
			
			return sum / size;
		}
		
		return 0;
	}

}
