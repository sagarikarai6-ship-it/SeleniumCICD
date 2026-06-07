package SeleniumProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
       /* System.out.println("Hello World!");
        
        ArrayList<String> names=new ArrayList<>();
		names.add("Saga");
		names.add("Rajath");
		names.add("Rashmika");
		names.add("VijayDevarakonda");
        
names.stream().filter(s->s.length()>6).forEach(s->System.out.println(s));
		
		System.out.println("*************************************");
			
		names.stream().filter(t->t.startsWith("R")).forEach(t->System.out.println(t));
		
		System.out.println("*************************************");
		
		names.stream().filter(t->t.startsWith("R")).skip(1).forEach(t->System.out.println(t));
		
		System.out.println("*************************************");
		
		//List<String> names1= Arrays.asList("saga","Rajath","Vijay","Rashmika");
		
		List<String> names3= names.stream().filter(t->t.startsWith("R")).map(t->t.toUpperCase()).collect(Collectors.toList());
		System.out.println(names3);
		
		System.out.println(names3.get(0));*/
    	
    	List<Integer> num=Arrays.asList(3,4,2,3,5,1,2,9,5,7,6);
    	num.stream().sorted().distinct().filter(s->s%2==1).forEach(s->System.out.println(s));
    	

    }
}
