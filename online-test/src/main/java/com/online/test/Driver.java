package com.online.test;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		/*
		 * DataLayer jpa = new DataLayer();
		 * 
		 * jpa.initGame(6); jpa.addShip("SH1", 2, 1, 5, 4, 4); jpa.viewBattleField();
		 * //displaying
		 */

		Scanner scanner = new Scanner(System.in);
		System.out.println("enter how many rows and columns");
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int[][] intArr = new int[n][m];
		
		System.out.println("defalut values : ");
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(intArr[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("reading values : ");
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				intArr[i][j] = scanner.nextInt();
			}
		}
		
		System.out.println("printing row wise : ");
		System.out.println("displaying values : ");
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(intArr[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("printing column wise : ");
		
		for(int j=0; j<m; j++) {
			for(int i=0; i<n; i++) {
				System.out.print(intArr[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("printing array elements in wave from : ");
		
		for(int i=0; i<n; i++) {
			if(i%2==0) {
				for(int j=0; j<m; j++) {
					System.out.print(intArr[i][j]+" ");
				}
				System.out.println();
			}else {
				for(int j=m-1; j>=0; j--) {
					System.out.print(intArr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
