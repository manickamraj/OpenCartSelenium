import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestDriver {
	public static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		
		
		int random = (int) Math.round(Math.random()*100000);
		String adder = Integer.toString(random);
		System.out.println(random);
		System.out.println(adder);

}
}
