package com.iKaoshi.service;

import java.util.Random;

public class suiji {
	//在num个数中 随机count
	 public static int[] getRandomFromArray(int num, int count) {  
		 
		    // ArrayList<Integer>arrayList =null;  
		   	int[] a=new int[num];
		   	int[] r=new int[count];
		   	for(int i=0;i<num;i++)
		   	{
		   		a[i]=i;
		   	}
		   	
		   	int j=0;
		   	Random ran1 = new Random();
		   	while(true)
		   	{
		   		
		   		j++;
		   		int i=ran1.nextInt(num);
		   		if(a[i]!=-1)
		   		{
		   			a[i]=-1;
		   			r[count-1]=i;
		   			count=count-1;
		   			System.out.println(i);
		   			if(count==0) break;
		   		}
		   		
		   	}
		    System.out.println("over");
		    return r;  
		}  
}
