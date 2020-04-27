
	
	public class Hashfn {
		
		public static String hashfn(String password)
		{
			
		//	System.out.println(password);
			
			char[] chars=password.toCharArray();
			
			String temp1="";
			
			for(char ch: chars)
			{
				int temp = (int) ch;
				temp++;
				ch=(char)temp;
		//		System.out.println(ch);
				temp1+=ch;
			}
				
			System.out.println(temp1);
			
			return temp1;
		}
	

}
