package javaProject_poker;

import java.util.Scanner;

public class PokerGame {

	public static void main(String[] args) {
		
		// BGM 객체 생성 및 음악 재생
		Music music = new Music();
		while(true) {
			try {
//				music.playMusic();
			} catch(Exception e) {
				
			}		
		
			Scanner sc = new Scanner(System.in);
			
			boolean flag = true;
			
			
			
			System.out.println("----------------------------------------------------------");
			System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
			System.out.println("★☆★☆★☆★☆★☆★☆★  [Poker World에 오신 것을] ★☆★★☆★☆★☆★☆★☆★☆");
			System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★  [환영합니다] ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
			System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
			System.out.println("----------------------------------------------------------");
			
			while (flag) {	
				Poker poker = new Poker();
				User1Poker u1 = new User1Poker();
				Jokbo myJokbo = new Jokbo();
				Jokbo user1Jokbo = new Jokbo();
				
				System.out.println("Poker 메뉴를 정해주세요");
				System.out.println("1. Poker start  /  2. 금고 관리  /  3. 프로그램 종료");
				int gameCoin = sc.nextInt();
				poker.setBetDie(false);
				
				switch (gameCoin) {
				
				case 1:	
					System.out.println("현재 잔액은 " + Poker.getMyMoney() + "원 입니다.");
					System.out.println();
					
					System.out.println("★최소 베팅 금액을 입력해 주세요★    (최소 베팅 금액은 1,000원부터 시작합니다.)");
					int minBet = sc.nextInt();
					poker.setBetMoney(minBet);
					
					if( minBet < 1000 ) {
						System.out.println("최소 베팅 금액이 너무 적습니다. 쫄?");
						System.out.println();
						break;
					}
					
					if( Poker.getMyMoney() > poker.getBetMoney() ) {
						System.out.println();
						poker.cardPack();
						poker.shuffle(100);
						poker.divided(4);
						u1.divided(4);
						
						poker.throw_card(poker.choice_card());
						u1.throw_card(0);
						
						System.out.println();
						poker.show_card();		
						poker.betting();
						if( poker.isBetDie() == true ) {
							int[] poker_num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
							Poker.setPoker_num(poker_num);
							
							String[] poker_kind = {"◆", "♠", "♥", "♣"};
							Poker.setPoker_kind(poker_kind);
							
							String[] poker_deck = new String[poker_num.length * poker_kind.length];
							Poker.setPoker_deck(poker_deck);
							
							Poker.setTotalBet(0);			
							break;
						}
						
						poker.divided(2);
						u1.divided(2);
						
						System.out.println();
						poker.show_card();
						poker.betting();
						if( poker.isBetDie() == true ) {
							int[] poker_num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
							Poker.setPoker_num(poker_num);
							
							String[] poker_kind = {"◆", "♠", "♥", "♣"};
							Poker.setPoker_kind(poker_kind);
							
							String[] poker_deck = new String[poker_num.length * poker_kind.length];
							Poker.setPoker_deck(poker_deck);
							
							Poker.setTotalBet(0);			
							break;
						}
						
						poker.divided(2);
						u1.divided(2);
						
						System.out.println();
						poker.show_card();
						poker.betting();
						if( poker.isBetDie() == true ) {
							int[] poker_num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
							Poker.setPoker_num(poker_num);
							
							String[] poker_kind = {"◆", "♠", "♥", "♣"};
							Poker.setPoker_kind(poker_kind);
							
							String[] poker_deck = new String[poker_num.length * poker_kind.length];
							Poker.setPoker_deck(poker_deck);
							
							Poker.setTotalBet(0);			
							break;
						}
						
						System.out.println();
						poker.ordered_card();
						u1.ordered_card();
						poker.show_card();
						u1.show_card();
						
						System.out.println();
						myJokbo.card_check(poker.getMy_deck());
						user1Jokbo.card_check(u1.getUser1_deck());
						
						System.out.println("내 패의 족보");
						myJokbo.jokbo(poker.getMy_deck(), myJokbo.getNum_check(), myJokbo.getShape_check());
						
						System.out.println();
						System.out.println("상대 패의 족보");
						user1Jokbo.jokbo(u1.getUser1_deck(), user1Jokbo.getNum_check(), user1Jokbo.getShape_check());
						
						System.out.println();
						if( myJokbo.getPoint() > user1Jokbo.getPoint() ) {
							System.out.println(myJokbo.getResult() + "로 이겼습니다!!! " + Poker.getTotalBet() + "원을 획득합니다.");
							poker.setMyMoney(Poker.getTotalBet());
							System.out.println("잔액은 " + Poker.getMyMoney() + "원 입니다.");
						} else {
							System.out.println("아쉽게 졌습니다 ㅠㅠ " + Poker.getTotalBet() / 2 + "원을 잃었습니다. 그래도 파이팅!");
							System.out.println("잔액은 " + Poker.getMyMoney() + "원 입니다.");
						}				
						System.out.println();
						
						int[] poker_num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
						Poker.setPoker_num(poker_num);
						
						String[] poker_kind = {"◆", "♠", "♥", "♣"};
						Poker.setPoker_kind(poker_kind);
						
						String[] poker_deck = new String[poker_num.length * poker_kind.length];
						Poker.setPoker_deck(poker_deck);
						
						Poker.setTotalBet(0);					
						break;
					} else {
						System.out.println("잔액이 너무 부족합니다. 충전을 해주세요");
						System.out.println();
						break;
					}
					
					
				case 2:
					System.out.println();
					System.out.println("다음 메뉴를 선택해 주세요");
					System.out.println("1. 잔액 확인  /  2. 금고 충전");
					int moneyNum = sc.nextInt();
					
					switch (moneyNum) {
					
					case 1:
						System.out.println("잔액 : " + Poker.getMyMoney() + "원");
						break;
						
					case 2:
						System.out.println("충전할 금액을 입력해 주세요 >> ");
						int chargeMoney = sc.nextInt();
						poker.setMyMoney(chargeMoney);
						System.out.println("충전이 완료되었습니다!");
						break;
						
					default:
						System.out.println("잘못 입력하셨습니다. 메인메뉴로 돌아갑니다.");
					}
					System.out.println();
					break;
					
				case 3:
					System.out.println("프로그램을 종료합니다.");
					flag = false;
					System.out.println();
					break;
					
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요");
					System.out.println();
				}
			}
		
			sc.close();
			break;
		}
		
//		poker.setMyMoney(50000);
//		poker.cardPack();
//		poker.shuffle(100);
//		poker.divided(4);
//		u1.divided(4);
//		
//		poker.throw_card(poker.choice_card());
//		u1.throw_card(0);
//		
//		System.out.println();
//		poker.show_card();		
//		poker.betting();
//		
//		poker.divided(2);
//		u1.divided(2);
//		
//		System.out.println();
//		poker.show_card();
//		poker.betting();
//		
//		poker.divided(2);
//		u1.divided(2);
//		
//		System.out.println();
//		poker.show_card();
//		poker.betting();
//		
//		System.out.println();
//		poker.ordered_card();
//		u1.ordered_card();
//		poker.show_card();
//		u1.show_card();
//		
//		System.out.println();
//		myJokbo.card_check(poker.getMy_deck());
//		user1Jokbo.card_check(u1.getUser1_deck());
//		
//		System.out.println();
//		myJokbo.jokbo(poker.getMy_deck(), myJokbo.getNum_check(), myJokbo.getShape_check());
//		user1Jokbo.jokbo(u1.getUser1_deck(), user1Jokbo.getNum_check(), user1Jokbo.getShape_check());
//		
//		System.out.println();
//		if( myJokbo.getPoint() > user1Jokbo.getPoint() ) {
//			System.out.println(myJokbo.getResult() + "로 이겼습니다!!! " + Poker.getTotalBet() / 2 + "를 획득합니다.");
//			poker.setMyMoney(Poker.getTotalBet());
//			System.out.println("잔액 : " + poker.getMyMoney());
//		} else {
//			System.out.println("아쉽게 졌습니다 ㅠㅠ 그래도 파이팅!");
//			System.out.println("잔액 : " + poker.getMyMoney());
//		}
		

	}

}

/*
 *  22.04.01.(금)
 *  1. Betting 클래스 만들건지 고민,,
 *  2. Main 메서드 포함한 여기에서 while 이용해 게임 돌릴 수 있게 하기
 *  3. PPT 만들기
 */

////test
//		// "◆", "♠", "♥", "♣"
//		String[] test1 = {"1◆", "9♥", "9◆", "10♣", "11◆", "12◆", "13♣"};
//		//


