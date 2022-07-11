package javaProject_poker;

public class User1Poker extends Poker {
	private String[] user1_deck = new String[7];
	private int user1_deck_count = 0;
	
	public String[] getUser1_deck() {
		return user1_deck;
	}
	public void setUser1_deck(String[] user1_deck) {
		this.user1_deck = user1_deck;
	}
	public int getUser1_deck_count() {
		return user1_deck_count;
	}
	public void setUser1_deck_count(int user1_deck_count) {
		this.user1_deck_count = user1_deck_count;
	}
	
	@Override
	public void divided(int count) {
		
		for( int i = 0; i < count; i++ ) {	// count 수 만큼 나눠줄거!
			
			int take = (int)(Math.random() * getPoker_deck().length);	// 포커 덱에 존재하는 카드의 인덱스를 랜덤하게 하나를 뽑음 
			
			this.user1_deck[user1_deck_count] = getPoker_deck()[take];		// my_deck에 차례로 뽑은 인덱스의 카드를 넣는다.
			user1_deck_count++;
			
			this.delete_card(take);	
		}
		
		setUser1_deck(user1_deck);
		
//		System.out.println("User1 패 : " + Arrays.toString(getUser1_deck()));
//		System.out.println("나눠준 후 poker_deck : " + Arrays.toString(getPoker_deck()));
//		System.out.println("나눠준 후 poker_deck.length : " + getPoker_deck().length);
	}
	
	@Override
	public void delete_card(int count) {
		String[] delete_deck = new String[getPoker_deck().length - 1];
		
		for( int i = 0; i < count; i++ ) {
			delete_deck[i] = getPoker_deck()[i];
		}
		
		for( int j = count; j < getPoker_deck().length - 1; j++ ) {
			delete_deck[j] = getPoker_deck()[j + 1];
		}
		
		setPoker_deck(delete_deck);		// poker_deck을 준 카드를 삭제해 재할당한다. 
	}	
	
	@Override
	public void throw_card(int count) {
		String[] new_deck = new String[this.getUser1_deck().length];
		
		for( int i = 0; i < count; i++ ) {
			new_deck[i] = this.getUser1_deck()[i];
		}
		
		for( int j = count; j < this.getMy_deck().length - 1; j++ ) {
			new_deck[j] = this.getUser1_deck()[j + 1];
		}
		
		this.user1_deck_count--;
		setUser1_deck(new_deck);
//		System.out.println("User1 패 : " + Arrays.toString(this.getUser1_deck()));
	}
	
	@Override
	public void ordered_card() {
		for( int i = 0; i < this.user1_deck.length - 1; i++) {
			for( int j = 0; j < this.user1_deck.length - 1 - i; j++ ) {
				if( Integer.parseInt((this.user1_deck[j].replaceAll("[^0-9]", ""))) > Integer.parseInt((this.user1_deck[j+1].replaceAll("[^0-9]", ""))) ) {	// "[^0-9]" : 0 ~ 9의 숫자가 아닌 문자열
					String tmp = user1_deck[j];
					user1_deck[j] = user1_deck[j+1];
					user1_deck[j+1] = tmp;
				}
			}
		}
		setUser1_deck(user1_deck);
//		System.out.println("정렬 후 User1 패 : " + Arrays.toString(this.getUser1_deck()));
	}
	
	@Override
	public void show_card() {
		System.out.print("User1 패 : < " );
		for( int i = 0; i < user1_deck.length; i ++ ) {
			if( user1_deck != null ) {
				System.out.print( user1_deck[i] + "  ");
			}
		}
		System.out.println(">");
	}
	
}







