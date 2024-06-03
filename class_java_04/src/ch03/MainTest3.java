package ch03;

public class MainTest3 {

	public static void main(String[] args) {
	
		// <T extend 클래스> 사용하기
		
		// 상위 클래스의 필요성
		// T 자료형은 범위를 제한할 수 없음
		// 위 문법 사용해서 상위 클래스에 속한 자료형만 대체 문자 안에 들어올 수 있다
		
		// ch03 패키지 자료형 사용
		GenericePrinter<Powder> genericePrinter1 = new GenericePrinter<>();
		genericePrinter1.setMaterial(new Powder());
		System.out.println(genericePrinter1.toString());

		////////////////////////////////////////////
		
		// 컴파일시점에서 부터 오류 발생을 한다
		// GenericePrinter<Water> genericePrinter2 = new GenericePrinter<>();
		
		
	}

}
