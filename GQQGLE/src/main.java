import java.util.Scanner;
import java.*;
import java.applet.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("請輸入關鍵字：");
        Scanner sc = new Scanner(System.in);
        String a = sc.toString();
        switch(a) {
        	case "梗圖":
        		Keyword aa = new Keyword(5.0);
        		break;
        	case "黃色":
        		Keyword bb = new Keyword(3);
        		break;
        }
	}
}
