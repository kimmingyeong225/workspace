package ch03;

public class Powder extends Material {


	@Override
	public String toString() {
		return "재료는 파우더 입니다";
	}
	
	
	@Override
	public void doPrinting() {
		System.out.println("플라스틱 재료로 출력 합니다");
		
	}



}
