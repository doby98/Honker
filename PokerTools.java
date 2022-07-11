package javaProject_poker;

import java.util.Arrays;

public class PokerTools extends Poker {
	
	@Override
	public void delete_card( int count ) {	// 내 덱에서 카드 버리기
		System.out.println(Arrays.toString(getMy_deck()));
		String[] new_deck = new String[this.getMy_deck().length];
		
		for( int i = 0; i < count; i++ ) {
			new_deck[i] = this.getMy_deck()[i];
		}
		
		for( int j = count; j < this.getMy_deck().length - 1; j++ ) {
			new_deck[j] = this.getMy_deck()[j + 1];
		}
		
		this.setMy_deck(new_deck);		// poker_deck을 준 카드를 삭제해 재할당한다.		
		System.out.println("내 패 : " + Arrays.toString(this.getMy_deck()));
	}
}
