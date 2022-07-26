package javaProject_poker;

import java.util.Scanner;

//import java.util.Random;

public class Poker {	// 포커에 관한 전반적인 메서드와 멤버변수 
	Scanner sc = new Scanner(System.in);
	
	private static int[] poker_num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};	// 포커 숫자
	private static String[] poker_kind = {"◆", "♠", "♥", "♣"};				// 포커 문양
	private static String[] poker_deck = new String[poker_num.length * poker_kind.length];	// 포커 덱
	private String[] my_deck = new String[7]; 	// 내 패에 카드를 받기 위한 배열
	private int my_deck_count = 0;				// 내 패에 카드를 넣기 위한 수
	private static int myMoney = 0;				// 가지고 있는 금액
	private static int totalBet = 0;			// 베팅이 진행될 때 누적되는 베팅 금액
	private int betMoney = 1000;				// 베팅 시기에 설정된 베팅 금액
	private boolean betDie = false;				// 베팅에서 '다이'를 택했을 때 control
	
	public int getBetMoney() {
		return betMoney;
	}

	public void setBetMoney(int betMoney) {
		this.betMoney = betMoney;
	}

	public boolean isBetDie() {
		return betDie;
	}

	public void setBetDie(boolean betDie) {
		this.betDie = betDie;
	}

	public static int getMyMoney() {
		return myMoney;
	}

	public void setMyMoney(int myMoney) {
		Poker.myMoney += myMoney;
	}

	public static int getTotalBet() {
		return totalBet;
	}

	public static void setTotalBet(int totalBet) {
		Poker.totalBet = totalBet;
	}

	public String[] getMy_deck() {
		return my_deck;
	}

	public void setMy_deck(String[] my_deck) {
		this.my_deck = my_deck;
	}

	public int getMy_deck_count() {
		return my_deck_count;
	}

	public void setMy_deck_count(int my_deck_count) {
		this.my_deck_count = my_deck_count;
	}

	public static void setPoker_num(int[] poker_num) {
		Poker.poker_num = poker_num;
	}

	public static void setPoker_kind(String[] poker_kind) {
		Poker.poker_kind = poker_kind;
	}

	public static void setPoker_deck(String[] poker_deck) {
		Poker.poker_deck = poker_deck;
	}

	public int[] getPoker_num() {
		return poker_num;
	}

	public String[] getPoker_kind() {
		return poker_kind;
	}

	public String[] getPoker_deck() {
		return poker_deck;
	}

	// 포커 덱을 만드는 메서드
	public void cardPack() {	// 포커 덱 구성 숫자와 문양을 1대1 대응
		for( int i = 0; i < poker_kind.length; i++ ) {
			for( int j = 0; j < poker_num.length; j++ ) {
				poker_deck[i*13 + j] = poker_num[j] + poker_kind[i];	// poker의 숫자가 13단위로 이뤄지기 때문
			}
		}
		
		Poker.setPoker_deck(poker_deck);
//		System.out.println("카드 덱 : " + Arrays.toString(poker_deck));
	}
	
	// 포커 덱을 섞는 메서드
	public void shuffle( int count ) {	// poker_deck을 섞기.
		String tmp = "";
		
		for( int i = 0; i < count; i++ ) {
			int m = (int)(Math.random() * Poker.poker_deck.length);	// 0 ~ 51(pokoer_deck의 길이)의 수 중 랜덤
			int n = (int)(Math.random() * Poker.poker_deck.length);
			
			tmp = poker_deck[m];			// tmp에 m번째 카드 저장
			poker_deck[m] = poker_deck[n];	// m번째 카드에 n번째 저장
			poker_deck[n] = tmp;			// n번째 카드에 m번째 저장
		}
		
		Poker.setPoker_deck(poker_deck);
//		System.out.println("섞은 덱 : " + Arrays.toString(poker_deck));
	}
	
	// 포커덱에서 내 패에 카드를 나눠주는 메서드
	public void divided( int count ) {		// 카드를 나눠주기
		
		System.out.println("카드를 " + count + "장 나눠줍니다.");
		for( int i = 0; i < count; i ++ ) {	
			try {
				Thread.sleep(700);
				if( i % 2 == 0 ) {
					System.out.println("슉");
				} else {
					System.out.println("슈슉");
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
			
		for( int i = 0; i < count; i++ ) {	// count 수 만큼 나눠줄거!
			
			int take = (int)(Math.random() * Poker.poker_deck.length);	// 포커 덱에 존재하는 카드의 인덱스를 랜덤하게 하나를 뽑음 
			
			this.my_deck[my_deck_count] = Poker.poker_deck[take];		// my_deck에 차례로 뽑은 인덱스의 카드를 넣는다.
			my_deck_count++;
			
			this.delete_card(take);	
		}
		
		setMy_deck(my_deck);
		
//		System.out.println("내 패 : " + Arrays.toString(getMy_deck()));
//		System.out.println("나눠준 후 poker_deck : " + Arrays.toString(poker_deck));
//		System.out.println("나눠준 후 poker_deck.length : " + poker_deck.length);
		
	}
	
	// 포커덱에서 나눠준 카드를 덱에서 버리기
	public void delete_card( int count ) {	// 삭제를 위한 덱을 만들어 준 후, count 번째 카드를 my_deck에 넣고, 그를 제외한 나머지 카드를 다시 poker_deck에 넣는다.
		String[] delete_deck = new String[poker_deck.length - 1];
		
		for( int i = 0; i < count; i++ ) {
			delete_deck[i] = Poker.poker_deck[i];
		}
		
		for( int j = count; j < poker_deck.length - 1; j++ ) {
			delete_deck[j] = Poker.poker_deck[j + 1];
		}
		
		setPoker_deck(delete_deck);		// poker_deck을 준 카드를 삭제해 재할당한다. 
	}
	
	// 내 패에서 카드 버리기
	public void throw_card( int count ) {	// 내 덱에서 카드 버리기
//		System.out.println(Arrays.toString(getMy_deck()));
		String[] new_deck = new String[this.getMy_deck().length];
		
		for( int i = 0; i < count; i++ ) {
			new_deck[i] = this.getMy_deck()[i];
		}
		
		for( int j = count; j < this.getMy_deck().length - 1; j++ ) {
			new_deck[j] = this.getMy_deck()[j + 1];
		}
		
		this.my_deck_count--;
		this.setMy_deck(new_deck);		// poker_deck을 준 카드를 삭제해 재할당한다.		
//		System.out.println("내 패 : " + Arrays.toString(this.getMy_deck()));
	}
	
	// 패 오름차순으로 정렬하기
	public void ordered_card() {
		for( int i = 0; i < this.my_deck.length - 1; i++) {
			for( int j = 0; j < this.my_deck.length - 1 - i; j++ ) {
				if( Integer.parseInt((this.my_deck[j].replaceAll("[^0-9]", ""))) > Integer.parseInt((this.my_deck[j+1].replaceAll("[^0-9]", ""))) ) {	// "[^0-9]" : 0 ~ 9의 숫자가 아닌 문자열
					String tmp = my_deck[j];
					my_deck[j] = my_deck[j+1];
					my_deck[j+1] = tmp;
				}
			}
		}
		setMy_deck(my_deck);
//		System.out.println("정렬 후 내 패 : " + Arrays.toString(this.getMy_deck()));
	}
	
	// 지금 내 패를 보여주기
	public void show_card() {
		System.out.print("내       패	: <  " );
		for( int i = 0; i < my_deck.length; i++ ) {
			if( my_deck[i] != null ) {
				System.out.print( my_deck[i] + "  ");
			}
		}
		System.out.println(">");
	}
	
	// 내 패에서 버릴 카드를 입력 받을 메서드, Scanner 사용
	public int choice_card() {
		show_card();
		int choice_num;
		while(true) {
			System.out.println("버릴 카드를 선택해 주세요.");
			System.out.println("1. " + this.my_deck[0] + "  /  2. " + this.my_deck[1] +
					"  /  3. " + this.my_deck[2] + "  /  4. " + this.my_deck[3]);
			
			choice_num = sc.nextInt() - 1;
			if( choice_num >= 0 && choice_num < 4 ) {
				break;							
			} else {
				System.out.println("입력이 잘못되었습니다. 다시 입력해 주세요!");
			}
		}
		return choice_num;
	}
	
	// 베팅 메서드, Scanner 사용
	public void betting() {
		
		System.out.println("현재 잔액 : " + Poker.myMoney);
		int bet;
		
		while( true ) {					
			System.out.println("베팅을 정해주세요");
			System.out.println("1. 다이  /  2. 콜 : " + this.betMoney + "  /  3. 더블 : " + this.betMoney * 2 + "  /  4. 삥 : 돈이 부족함");
			
			bet = sc.nextInt();
			
			if( bet ==  1 ) { 	// 다이
				System.out.println("게임을 포기하셨습니다. ");
				System.out.println("베팅하신 돈은 허공으로,,,");
				System.out.println();
				Poker.totalBet = 0;
				this.setBetDie(true);
				break;
				
			} else if( bet == 2 ) {		// 콜
				if( Poker.myMoney >=  this.betMoney ) {
					System.out.println("콜을 하셨습니다. " + this.betMoney + "원이 베팅됩니다.");
					Poker.myMoney -= this.betMoney;
					Poker.totalBet += this.betMoney * 2;
					System.out.println("현재 베팅 금액은 " + Poker.getTotalBet() + "원 입니다☆");
					break;
				} else {
					System.out.println("잔액이 부족합니다. 베팅이 넘어갑니다.");
					break;
				}
				
			} else if( bet == 3 ) {		// 더블
				if( Poker.myMoney >= this.betMoney * 2) {
					System.out.println("더블을 하셨습니다. " + this.betMoney * 2 + "원이 베팅됩니다.");
					Poker.myMoney -= this.betMoney * 2;
					Poker.totalBet += this.betMoney * 4;
					this.betMoney = this.betMoney * 2;
					System.out.println("현재 베팅 금액은 " + Poker.getTotalBet() + "원 입니다☆");
					break;
				} else {
					System.out.println("잔액이 부족합니다. 베팅이 넘어갑니다.");
					System.out.println("현재 베팅 금액은 " + Poker.getTotalBet() + "원 입니다☆");
					break;
				}
				
			} else if( bet == 4 ) {		// 삥
				System.out.println("삥을 선택하셨습니다. 베팅이 넘어갑니다.");
				break;
				
			} else {
				System.out.println("잘못 입력하셨습니다. 입력 값을 확인해 주세요.");
				System.out.println();
			}
				
		}
		System.out.println();
					
	}
	
}
