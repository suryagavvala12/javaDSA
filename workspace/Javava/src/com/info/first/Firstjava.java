package com.info.first;
import java.util.*;
public class Firstjava {

	public static void main(String[] args) 
	{
		int high=20;
		int low=10;
		Random r=new Random();
		for(int i=10;i<20;i++)
		{
			System.out.println(r.nextInt(high-low)+high);
		}
	}

}
