import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		while(sc.hasNextLine()) {
			String keyword =sc.next();
			GoogleQuery googleQuery = new GoogleQuery(keyword);
			googleQuery.query();
			String a = "";
			
			
		}
		sc.close();
	}

}


