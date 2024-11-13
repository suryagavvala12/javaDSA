package com.info.first;

import java.util.*;
public class Evnoroddwithoutusingmodules {

	public static void main(String[] args) 
	{
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		boolean isEven=true;
		for(int i=1;i<=n;i++)
		{
			isEven=!isEven;
		}
		if(!isEven)
		{
			System.out.println("oDD number");
		}
		else
		{
			System.out.println("even number");
		}
	}

}
