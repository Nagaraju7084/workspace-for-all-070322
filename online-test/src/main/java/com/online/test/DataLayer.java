package com.online.test;

import java.util.ArrayList;
import java.util.List;

public class DataLayer {
	private String id = "SH1";
	private Object[][] objArr = new Object[6][6];
	
	int[] playerA = new int[objArr[0].length];
	int[] playerB = new int[objArr[1].length];
	
	List<Integer> list = new ArrayList<>();
	
	
	public void add() {
		for(int i=0; i<50; i++) {
			list.add(i);
		}
		System.out.println(list);
	}
	
	
	public void initGame(int n) {
		add();
		int leftHalf = objArr.length / 2;
		//data should be intialized
		if(n < 0 || n > objArr.length)
			throw new ArrayIndexOutOfBoundsException(n);
		for(int i=0; i<leftHalf; i++) {
			for(int j=0; j<leftHalf; j++) {
				objArr[i][j] = objArr[list.get(i)][list.get(j)];
			}
		}
		
		for(int i=0; i<leftHalf; i++) {
			for(int j=0; j<leftHalf; j++) {
				objArr[i][j] = objArr[n/2][n/2];
			}
		}
		
		int rightHalf = playerB.length / 2;
		
		for(int i=0; i<rightHalf; i++) {
			for(int j=0; j<rightHalf; j++) {
				objArr[i][j] = objArr[list.get(i)][list.get(j)];
			}
		}
		
		for(int i=0; i<leftHalf; i++) {
			for(int j=0; j<leftHalf; j++) {
				objArr[i][j] = objArr[n/2][n/2];
			}
		}
	}
	
	/*
	 * >> addShip(“SH1”, size = 2, 1, 5, 4, 4) addShip(id, size, x position PlayerA,
	 * y position PlayerA, x position PlayerB, y position PlayerB)
	 */
					
	public void addShip(String id, int size, int playerAX, int playerAY, int playerBX, int playerBY) {
		if (this.id.equals(id)) {
			for (int i = 0; i < objArr.length; i++) {
				for (int j = 0; j < objArr.length; j++) {
					objArr[i][j] = objArr[playerAX][playerAY];
				}
			}
			for (int i = 0; i < playerB.length; i++) {
				for (int j = 0; j < playerB.length; j++) {
					objArr[i][j] = objArr[playerBX][playerBY];
				}
			}
		}
	}
	
	public void viewBattleField() {
		for (int i = 0; i < objArr.length; i++) {
			for (int j = 0; j < objArr.length; j++) {
				System.out.print(objArr[i][j]+" "); //intially null if there is no data/value
			}
		}
	}
	
}
