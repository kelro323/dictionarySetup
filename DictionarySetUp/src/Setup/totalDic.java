package Setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class totalDic {
	public final static String[] word={"���", "����" ,"�λ�", "�ɵ���", "������", "����-����", "�����"};//, "NPR", "CNOUNX", "�ұ�Ģ"};
	
	public static Scanner scan = new Scanner(System.in);
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("dic.txt"));
		
		FileWriter fw = new FileWriter("totalOut.txt", true);
		int count = 0;
		while(true) {
			String line = br.readLine();
			if(line == null) {
				System.out.println("����");
				break;
			}
			String[] lines = line.split(",");
			String stem = lines[0];
			String result = "";
			for(String w : word) {
				result = result + check(stem, w);
				System.out.println();
			}
			//NPR, CNOUNX�� ��ǻ� �� ���̴� �׸��̶� �� 00 ó���ϰ� �ұ�Ģ�� ���� ���� �߰�
			String irr = irrCheck(stem);
			String data = stem+","+result+"00"+irr+"\r\n";
			fw.write(data);
			count++;
			//5�� ������ ���Ͽ� �Է�
			if(count%5 == 0) {
				fw.close();
				fw = new FileWriter("totalOut.txt", true); // append �������·�
			}
		}
		fw.close();
	}
	//��Ÿ �׸��� ������ ��� �߸��� ���ڸ� �Է��ϸ� �� ��Ÿ�� ��������
	//��Ÿ�� �������� �ʴ� �׸��� ��� ������ ������ ���ڿܿ��� �Է� ���� �ʵ��� ó��(���� ���� �̿��Ͽ�)
	//null�̳� �������� �Է��� ���(���͸� ģ ���)���� üũ�Ͽ� �׳� 0���� �Է��� ������ �����Ͽ� ó�� - ���� yes/no ���ÿ��� ���� ��Ÿ�ϸ� �ڵ����� no�� ó��
	
	private static String check(String stem, String sort) {
		String result ="0";
		int select;
		while(true) {
			System.out.println(stem+": "+sort+"�Դϱ�?");
			System.out.println("No<0> // Yes<1>");
			select = Integer.parseInt(nullHandler(scan.nextLine()));
			if(select==1 || select ==0) break;
		}
		if(select==0) {
			System.out.println(sort+"��/�� �ƴմϴ�.");
			return result;
		}else {
			if(sort.equals(word[0])||sort.equals(word[1])||sort.equals(word[2])) {
				System.out.println("� "+sort+"���� �ش�˴ϱ�?");
			}
			else{
				System.out.println(sort+"���� �ֽ��ϴ�.");
			}
			if(sort.equals(word[0])) {
				int kind;
				while(true) {
					System.out.println("�Ϲݸ��<1> // �������<2> // ����<3> // �������<4> // ����<5>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind>=1 && kind<=5) break;
				}
						
				switch(kind) {
				case 1 : {
					System.out.println("�������<1> // ���Ĺ�����<2> // ��ġ����<3> // ���ݰ���<4> // �ð�����<5> // �������<6> // ���İ���<7> // ��Ÿ<8>");
					int noun = Integer.parseInt(nullHandler(scan.nextLine()));
							
					switch(noun) {
					case 1 : result = "s"; break;
					case 2 : result = "a"; break;
					case 3 : result = "l"; break;
					case 4 : result = "c"; break;
					case 5 : result = "t"; break;
					case 6 : {
						System.out.println("����ȭ��<1> // ���񽺾�<2> // �Ƿ�����<3> // �Ǹ�����<4> // ����<5> // �Ǽ�<6> // IT<7> // �̵�������<8> // ����<9> // ��Ÿ<10>");
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
							System.out.println("����,����<1> // �ڿ�����<2> // ��ü��<3>");
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
					System.out.println("�θ�<1> // ����<2> // ��Ÿ<3>");
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
					System.out.println("�ڵ���<1> // Ÿ����<2> // (����)�����<3> // �ڵ���+Ÿ����<4> // �ڵ���+(����)�����<5> // Ÿ����+(����)�����<6>");
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
					System.out.println("������<1> // �λ�<2>");
					kind = Integer.parseInt(nullHandler(scan.nextLine()));
					if(kind==1 || kind==2) break;
				}
						
				switch(kind) {
				case 1 : {
					System.out.println("���� ������<1> // �� ������<2> // ��Ÿ ������<3>");
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
						System.out.println("���Ӻλ�<1> // �Ϲݺλ�<2> // ������ �λ�<3>");
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
				result = "1";
			} else if(sort.equals(word[4])) {
				result = "1";
			} else if(sort.equals(word[5])) { //�+'����' ���°� �ִ� �� ex)������
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
			System.out.println(stem+": "+"�ұ�Ģ �Դϱ�?");
			System.out.println("No<0> // Yes<1>");
			select = Integer.parseInt(nullHandler(scan.nextLine()));
			if(select==1 || select ==0) break;
		}
		if(select==0) return result;
		else {
			while(true) {
				System.out.println("���ұ�Ģ<1/B> // ���ұ�Ģ<2/H> // ���ұ�Ģ<3/U> // ���ұ�Ģ<4/L> // ���ұ�Ģ<5/S> // ���ұ�Ģ<6/D> // ���ұ�Ģ<7/R>");
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
