package reflect;

public class SampleClass {
	//value="abc", omitted
	@Test("abc")
	public String method1(String name, int age) {
		return name+"-"+age;
	}
	
	public void method2() {
		return;
	}
}
