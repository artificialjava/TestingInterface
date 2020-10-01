package testingInterface;

import java.util.ArrayList;
import java.util.List;

public class TestContainer {
	private List<Test> tests;
	private int testAmount;
	
	public TestContainer() {
		this.tests = new ArrayList<Test>();
		testAmount = 1;
		// call to testing methods here
		test1();
		test2();
	}
	
	public List<Test> getTests() {
		return this.tests;
	}
	
	private void test1() {
		for(int i=0; i<50; i++) {
			Test temp = new Test("trial", testAmount, i, i, "red");
			tests.add(temp);
			this.testAmount++;
		}
	}
	
	private void test2() {
		for(int i=0; i<50; i++) {
			Test temp = new Test("fake", testAmount, i, i + 1, "blue");
			tests.add(temp);
			this.testAmount++;
		}
	}
}