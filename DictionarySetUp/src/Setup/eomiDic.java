package Setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//즐겨찾기 위키 사이트 보고 판단
public class eomiDic {
public static final String[] word = {"종결", "연결", "전성", "시제 선어말", "높임 선어말"};
	
	public static Scanner scan = new Scanner(System.in);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("eomi.txt"));
		
		FileWriter fw = new FileWriter("eomiOut.txt");
		
		while(true) {
			String line = br.readLine();
			if(line==null) {
				System.out.println("종료");
				break;
			}
			String eomi = line.trim();
			String result = "";
			for(String w : word) {
				result = result+check(eomi, w);
			}
			String data = eomi+","+result+"\r\n";
			fw.write(data);
		}
		fw.close();
	}
	
	private static String check(String eomi, String sort) {
		String result = "0";
		while(true) {
			System.out.println(eomi+": "+sort+"(으)로 사용됩니까?");
			System.out.println("No<0> // Yes<1>");
			int select = Integer.parseInt(nullHandler(scan.nextLine()));
			if(select == 0 || select == 1) {
				if(select==0) {
					return result;
				}
				else {
					if(sort.equals(word[0])) {
						int jong;
						while(true) {
							System.out.println("평서형<1> // 감탄형<2> // 의문형<3> // 명령형<4> // 청유형<5>");
							jong = Integer.parseInt(nullHandler(scan.nextLine()));
							if(jong>=1 && jong<=5) break;
						}
						
						switch(jong) {
						case 1 : result = "d"; break;
						case 2 : result = "w"; break;
						case 3 : result = "q"; break;
						case 4 : result = "o"; break;
						case 5 : result = "s"; 
						}
						break;
					}
					else if(sort.equals(word[1])) {
						int yeon;
						while(true) {
							System.out.println("대등적<1> // 종속적<2> // 보조적<3>");
							yeon = Integer.parseInt(nullHandler(scan.nextLine()));
							if(yeon>=1 && yeon<=3) break;
						}
						
						switch(yeon) {
						case 1 : result = "e"; break;
						case 2 : result = "u"; break;
						case 3 : result = "b"; 
						}
						break;
					} else if(sort.equals(word[2])){
						int jeon;
						while(true) {
							System.out.println("명사형<1> // 관형사형<2> // 부사형<3>");
							jeon = Integer.parseInt(nullHandler(scan.nextLine()));
							if(jeon>=1 && jeon<=3) break;
						}
						
						switch(jeon) {
						case 1 : result = "n"; break;
						case 2 : result = "p"; break;
						case 3 : result = "a"; 
						}
						break;
					} else if(sort.equals(word[3])) {
						int sije;
						while(true) {
							System.out.println("현재<1> // 과거<2> // 회상<3> // 추측<4> // 미래<5> // 미래(의지)<6>");
							sije = Integer.parseInt(nullHandler(scan.nextLine()));
							if(sije>=1 && sije<=6) break;
						}
						
						switch(sije) {
						case 1 : result = "n"; break;
						case 2 : result = "p"; break;
						case 3 : result = "r"; break;
						case 4 : result = "s"; break;
						case 5 : result = "f"; break;
						case 6 : result = "w"; 
						}
						break;
					} else {
						int rf;
						while(true) {
							System.out.println("주체 높임<1> // 공손<2>");
							rf = Integer.parseInt(nullHandler(scan.nextLine()));
							if(rf>=1 && rf<=2) break;
						}
						switch(rf) {
						case 1 : result = "r"; break;
						case 2 : result = "p"; break;
						}
						break;
					}
				}
			}
			else {
				System.out.println("잘못 입력하셨습니다.");
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
}