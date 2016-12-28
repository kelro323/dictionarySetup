package Setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class totalDic {
	public final static String[] word={"명사", "동사" ,"부사", "능동태", "수동태", "내다-형태", "형용사"};//, "NPR", "CNOUNX", "불규칙"};
	
	public static Scanner scan = new Scanner(System.in);
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("dic.txt"));
		
		FileWriter fw = new FileWriter("totalOut.txt", true);
		int count = 0;
		while(true) {
			String line = br.readLine();
			if(line == null) {
				System.out.println("종료");
				break;
			}
			String[] lines = line.split(",");
			String stem = lines[0];
			String result = "";
			for(String w : word) {
				result = result + check(stem, w);
				System.out.println();
			}
			//NPR, CNOUNX가 사실상 안 쓰이는 항목이라 다 00 처리하고 불규칙을 따로 빼서 추가
			String irr = irrCheck(stem);
			String data = stem+","+result+"00"+irr+"\r\n";
			fw.write(data);
			count++;
			//5개 단위로 파일에 입력
			if(count%5 == 0) {
				fw.close();
				fw = new FileWriter("totalOut.txt", true); // append 가능형태로
			}
		}
		fw.close();
	}
	//기타 항목이 존재할 경우 잘못된 숫자를 입력하면 다 기타로 보내버림
	//기타가 존재하지 않는 항목인 경우 정해진 범위의 숫자외에는 입력 받지 않도록 처리(무한 루프 이용하여)
	//null이나 공란으로 입력한 경우(엔터만 친 경우)에는 체크하여 그냥 0으로 입력한 것으로 간주하여 처리 - 따라서 yes/no 선택에선 엔터 연타하면 자동으로 no로 처리
	
	private static String check(String stem, String sort) {
		String result ="0";
		int select;
		while(true) {
			System.out.println(stem+": "+sort+"입니까?");
			System.out.println("No<0> // Yes<1>");
			select = Integer.parseInt(nullHandler(scan.nextLine()));
			if(select==1 || select ==0) break;
		}
		if(select==0) {
			System.out.println(sort+"이/가 아닙니다.");
			return result;
		}else {
			if(sort.equals(word[0])||sort.equals(word[1])||sort.equals(word[2])||sort.equals(word[3])||sort.equals(word[4])) {
				System.out.println("어떤 "+sort+"형에 해당됩니까?");
			}
			else{
				System.out.println(sort+"형이 있습니다.");
			}
			if(sort.equals(word[0])) {
				int kind;
				while(true) {
					System.out.println("일반명사<1> // 고유명사<2> // 대명사<3> // 의존명사<4> // 수사<5>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind>=1 && kind<=5) break;
				}
						
				switch(kind) {
				case 1 : {
					System.out.println("사람관련<1> // 동식물관련<2> // 위치관련<3> // 성격관련<4> // 시간관련<5> // 산업관련<6> // 지식관련<7> // 기타<8>");
					int noun = Integer.parseInt(nullHandler(scan.nextLine()));
							
					switch(noun) {
					case 1 : result = "s"; break;
					case 2 : result = "a"; break;
					case 3 : result = "l"; break;
					case 4 : result = "c"; break;
					case 5 : result = "t"; break;
					case 6 : {
						System.out.println("제조화학<1> // 서비스업<2> // 의료제약<3> // 판매유통<4> // 교육<5> // 건설<6> // IT<7> // 미디어디자인<8> // 금융<9> // 기타<10>");
						int indst = Integer.parseInt(nullHandler(scan.nextLine()));
								
						switch(indst) {
						case 1 : result = "m"; break;
						case 2 : result = "v"; break;
						case 3 : result = "b"; break;
						case 4 : result = "u"; break;
						case 5 : result = "e"; break;
						case 6 : result = "k"; break;
						case 7 : result = "i"; break;
						case 8 : result = "z"; break;
						case 9 : result = "f"; break;
						default: result = "x"; 
						}
						break;
					}
					case 7 : {
						int know;
						while(true) {
							System.out.println("법률,경제<1> // 자연과학<2> // 예체능<3>");
							know = Integer.parseInt(nullHandler(scan.nextLine()));
							if(know>=1 && know<=3) break;
						}
						switch(know) {
						case 1 : result = "w"; break;
						case 2 : result = "j"; break;
						case 3 : result = "y"; break;
						}
					}
					default: result = "g";
					}
					break;
				}
				case 2 : {
					System.out.println("인명<1> // 지명<2> // 기타<3>");
					int proper = Integer.parseInt(nullHandler(scan.nextLine()));
					switch(proper) {
					case 1 : result = "h"; break;
					case 2 : result = "p"; break;
					default: result = "r";
					}
					break;
				}
				case 3 : result = "d"; break;
				case 4 : result = "o"; break;
				case 5 : result = "n"; break;
				}
			} else if(sort.equals(word[1])) {
				int kind;
				while(true) {
					System.out.println("자동사<1> // 타동사<2> // (보조)형용사<3> // 자동사+타동사<4> // 자동사+(보조)형용사<5> // 타동사+(보조)형용사<6>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind>=1 && kind<=6) break;
				}
						
				switch(kind) {
				case 1 : result = "i"; break;
				case 2 : result = "t"; break;
				case 3 : result = "j"; break;
				case 4 : result = "v"; break;
				case 5 : result = "d"; break;
				case 6 : result = "k"; break;
				}
			} else if(sort.equals(word[2])) {
				int kind;
				while(true) {
					System.out.println("관형사<1> // 부사<2>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind==1 || kind==2) break;
				}
						
				switch(kind) {
				case 1 : {
					System.out.println("지시 관형사<1> // 수 관형사<2> // 기타 관형사<3>");
					int busa = Integer.parseInt(nullHandler(scan.nextLine()));
					switch(busa) {
					case 1 : result = "d"; break;
					case 2 : result = "n"; break;
					default: result = "p"; break;
					}
					break;
				}
				case 2 : {
					int busa;
					while(true) {
						System.out.println("접속부사<1> // 일반부사<2> // 부정형 부사<3>");
						busa = Integer.parseInt(nullHandler(scan.nextLine()));
						if(busa>=1 && busa<=3) break;
					}
							
					switch(busa) {
					case 1 : result = "c"; break;
					case 2 : result = "a"; break;
					case 3 : result = "e"; 
					}
					break;
				}
				}
			} else if(sort.equals(word[3])) {
				int kind;
				while(true) {
					System.out.println("자동사<1> // 타동사<2> // (보조)형용사<3> // 자동사+타동사<4> // 자동사+(보조)형용사<5> // 타동사+(보조)형용사<6>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind>=1 && kind<=6) break;
				}
				switch(kind) {
				case 1 : result = "i"; break;
				case 2 : result = "t"; break;
				case 3 : result = "j"; break;
				case 4 : result = "v"; break;
				case 5 : result = "d"; break;
				case 6 : result = "k"; break;
				}
			} else if(sort.equals(word[4])) {
				int kind;
				while(true) {
					System.out.println("자동사<1> // 타동사<2> // (보조)형용사<3> // 자동사+타동사<4> // 자동사+(보조)형용사<5> // 타동사+(보조)형용사<6>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind>=1 && kind<=6) break;
				}
				switch(kind) {
				case 1 : result = "i"; break;
				case 2 : result = "t"; break;
				case 3 : result = "j"; break;
				case 4 : result = "v"; break;
				case 5 : result = "d"; break;
				case 6 : result = "k"; break;
				}
			} else if(sort.equals(word[5])) { //어간+'내다' 형태가 있는 것 ex)힘내다
				result = "1";
			} else {
				result = "1";
			}
			}
		return result;
	}

	
	private static String nullHandler(String s) {
		if(s==null || s.trim().equals("")) {
			s = "0";
		}
		return s;
	}
	
	private static String irrCheck(String stem) {
		int irr;
		int select;
		String result="X";
		while(true) {
			System.out.println(stem+": "+"불규칙 입니까?");
			System.out.println("No<0> // Yes<1>");
			select = Integer.parseInt(nullHandler(scan.nextLine()));
			if(select==1 || select ==0) break;
		}
		if(select==0) return result;
		else {
			while(true) {
				System.out.println("ㅂ불규칙<1/B> // ㅎ불규칙<2/H> // ㄹ불규칙<3/U> // 르불규칙<4/L> // ㅅ불규칙<5/S> // ㄷ불규칙<6/D> // 러불규칙<7/R>");
				irr = Integer.parseInt(nullHandler(scan.nextLine()));
				if(irr>=1 && irr<=7) break;
			}
				
			switch(irr) {
			case 1 : result = "B"; break;
			case 2 : result = "H"; break; 
			case 3 : result = "U"; break;
			case 4 : result = "L"; break;
			case 5 : result = "S"; break;
			case 6 : result = "D"; break;
			case 7 : result = "R"; 
			}
		}
		return result;
		}
	
}