package Setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class josaDic {
	public static final String[] word = {"�ְ�", "�����", "������", "������", "������", "�λ��", "ȣ��", "��������", "��Ÿ"};
	
	public static Scanner scan = new Scanner(System.in);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("josa.txt"));
		
		FileWriter fw = new FileWriter("josaOut.txt");
		
		while(true) {
			String line = br.readLine();
			if(line==null) {
				System.out.println("����");
				break;
			}
			String josa = line.trim();
			String result = "";
			for(String w : word) {
				result = result+check(josa, w);
			}
			String data = josa+","+result+"\r\n";
			fw.write(data);
		}
		fw.close();
	}
	
	private static String check(String josa, String sort) {
		String result = "0";
		while(true) {
			System.out.println(josa+": "+sort+"(��)�� ���˴ϱ�?");
			System.out.println("No<0> // Yes<1>");
			int select = Integer.parseInt(nullHandler(scan.nextLine()));
			if(select == 0 || select == 1) {
				if(select==0) {
					return result;
				}
				else {
					result = "1";
					return result;
				}
			}
			else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}
	
	private static String nullHandler(String s) {
		if(s==null || s.trim().equals("")) {
			s = "0";
		}
		return s;
	}
}
