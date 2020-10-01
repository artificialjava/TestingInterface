package testingInterface;

public class Test {
	private boolean passed;
	private String testName;
	private int testNumber;
	private int testCaseType;
	
	private String expectedStr;
	private String actualStr;
	
	private int expectedInt;
	private int actualInt;
	
	private double expectedDbl;
	private double actualDbl;
	
	private boolean expectedBool;
	private boolean actualBool;
	
	private Object expectedObj;
	private Object actualObj;
	
	private String inputGiven;
	
	public Test(String title, int trial, String input) {
		this.testName = title;
		this.testNumber = trial;
		this.inputGiven = input;
		this.testCaseType = 0;
		
	}
	
	public Test(String title, int trial, String expected, String actual, String input) {
		this(title, trial, input);
		this.actualStr = actual;
		this.expectedStr = expected;
		this.testCaseType = 1;
		
		if(expected.equals(actual)) {
			this.passed = true;
		} else {
			this.passed = false;
		}
	}

	public Test(String title, int trial, int expected, int actual, String input) {
		this(title, trial, input);
		this.actualInt = actual;
		this.expectedInt = expected;
		this.testCaseType = 2;
		
		if(expected == actual) {
			this.passed = true;
		} else {
			this.passed = false;
		}
	}
	
	public Test(String title, int trial, double expected, double actual, String input) {
		this(title, trial, input);
		this.actualDbl = actual;
		this.expectedDbl = expected;
		this.testCaseType = 3;
		
		if((Math.abs(expected - actual)) <= 0.000000000000001) {
			this.passed = true;
		} else {
			this.passed = false;
		}
	}
	
	public Test(String title, int trial, boolean expected, boolean actual, String input) {
		this(title, trial, input);
		this.actualBool = actual;
		this.expectedBool = expected;
		this.testCaseType = 4;
		
		if((expected && actual) || (!expected && !actual)) {
			this.passed = true;
		} else {
			this.passed = false;
		}
	}
	
	public Test(String title, int trial, Object expected, Object actual, String input) {
		this(title, trial, input);
		this.actualObj = actual;
		this.expectedObj = expected;
		this.testCaseType = 5;
		
		if(expected == actual) {
			this.passed = true;
		} else {
			this.passed = false;
		}
	}
	
	public String[] getInfo() {
		String[] testInfo = new String[2];
		switch(this.testCaseType) {
			case 0:
				break;
			case 1:
				testInfo[0] = this.expectedStr;
				testInfo[1] = this.actualStr;
				break;
			case 2:
				Integer actI = this.actualInt;
				Integer expI = this.expectedInt;
				testInfo[0] = expI.toString();
				testInfo[1] = actI.toString();
				break;
			case 3:
				Double actD = this.actualDbl;
				Double expD = this.expectedDbl;
				testInfo[0] = expD.toString();
				testInfo[1] = actD.toString();
				break;
			case 4:
				Boolean actB = this.actualBool;
				Boolean expB = this.expectedBool;
				testInfo[0] = expB.toString();
				testInfo[1] = actB.toString();
				break;
			case 5:
				Object actO = this.actualObj;
				Object expO = this.expectedObj;
				testInfo[0] = expO.toString();
				testInfo[1] = actO.toString();
				break;
		}
		return testInfo;
	}
	
	public boolean getPassed() {
		return this.passed;
	}
	
	public String getTitle() {
		return this.testName;
	}
	
	public int getTrial() {
		return this.testNumber;
	}
	
	public int getTestCase() {
		return this.testCaseType;
	}
	
	public String getInput() {
		if(this.inputGiven == null) {
			return "none";
		} else {
			return this.inputGiven;
		}
	}
}