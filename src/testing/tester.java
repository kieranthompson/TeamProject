package testing;

import java.sql.Date;

import core.*;
import database.*;

public class tester {
		public static void main(String []args){
			System.out.println("Hello World");
			// int[] a = {1,2,3,7,6,5,9,5,6,7,5,2,-1};//expected 6th position
			int[] a={2, 7, 4, 5, -3, 8, 9, -1};
			System.out.println("(Best) Balance point for a is index "+BalanceBest(a));
		}
		public static int BalanceBest(int[] a)
		{
			int leftSum = a[0];
			int rightSum = 0;
			for(int i=0; i<a.length;i++)//notice we start from 2nd as 1st value is set
				rightSum += a[i];//each sum is sum of previous sum plus current value

			for(int i=0; i<a.length-1;i++)
			{
				if(leftSum==rightSum)
					return i;
				leftSum+=a[i+1];
				rightSum-=a[i];
			}
			return -1;//otherwise we return -1 as not found
		}
}
