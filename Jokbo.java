package javaProject_poker;


// 완성된 패의 포커 족보를 확인하는 메서드
// 싱글톤 사용
// 완성된 패를 파라미터로 가지는 족보 결과 리턴 메서드

public class Jokbo {
	private int[] num_check = new int[13];		// 완성된 패의 카드 숫자별 갯수를 저장
	private int[] shape_check = new int[4];		// 완성된 패의 카드 문양별 갯수를 저장( 스 하 다 클 )
	private String result = "";					// 패의 최고 족보를 저장		
	private int point = 0;						// 족보에 따른 점수를 저장하는 변수
	
	public int getPoint() {
		return point;
	}

//	public void setPoint(int point) {
//		this.point = point;
//	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int[] getNum_check() {
		return num_check;
	}

	public void setNum_check(int[] num_check) {
		this.num_check = num_check;
	}

	public int[] getShape_check() {
		return shape_check;
	}

	public void setShape_check(int[] shape_check) {
		this.shape_check = shape_check;
	}

//	// ------- 싱글톤 객체 생성을 위한 준비 시작 -------
//	private static Jokbo current;
//	
//	public static Jokbo getInstance() {
//		if( current == null ) {
//			current = new Jokbo();
//		}
//		
//		return current;
//	}
//	
//	public static void freeInstance() {
//		current = null;
//	}
//	
//	private Jokbo() {
//		super();
//	}
//	// ------- 싱글톤 객체 생성을 위한 준비 끝 -------
	
	// 족보 결과를 위해 카드의 숫자와 모양을 정리
	public void card_check( String[] deck ) {
		
		for( int i = 0; i < deck.length; i++ ) {
			int num = Integer.parseInt(deck[i].replaceAll("[^0-9]", "")) - 1;
			this.num_check[num]++;
		}
		
		for( int j = 0; j < deck.length; j++ ) {
			String shape = deck[j].replaceAll("[0-9]", "");
			
			if( shape.equals("♠") ) {
				this.shape_check[0]++;
			} else if( shape.equals("♥") ) {
				this.shape_check[1]++;
			} else if( shape.equals("◆") ) {
				this.shape_check[2]++;
			} else if( shape.equals("♣") ) {
				this.shape_check[3]++;
			}				
		}
		
		this.setNum_check(num_check);
		this.setShape_check(shape_check);
//		System.out.println("num check : " + Arrays.toString(this.num_check));
//		System.out.println("shape check : " + Arrays.toString(this.shape_check));

	}
	
	// 패의 최고 족보를 도출하고, 족보에 따른 점수를 부여하는 메서드
	public void jokbo( String[] deck, int[] num_check, int[] shape_check ) {
//		System.out.println("족보 시작");
			
		int flag = 0;
		String shape = "";
		
		// ----- 스트레이트플러쉬( 연속된 숫자 5개가 문양도 같을 때 ) 시작 -----
		// ----- 3,000 포인트 지급 -----
		for( int i = 0; i < this.shape_check.length; i ++ ) {	// 플러쉬 조건 먼저 부합하는지 체크
			if( this.shape_check[i] >= 5 ) {
				flag = i;
			}
		}
		
		int[] flush = new int[shape_check[flag]];				// 플러쉬 조건에 해당하는 카드들의 숫자를 저장할 flush 배열
		int flush_count = 0;
		
		switch (flag) {											// 플러쉬 조건에 해당하는 문양을 찾기
		case 0:
			shape = "♠";
			break;
			
		case 1:
			shape = "♥";
			break;
			
		case 2:
			shape = "◆";
			break;
			
		case 3:
			shape = "♣";
			break;
		}
		
		for( int i = 0; i < deck.length; i++ ) {				// 플러쉬 조건에 해당하는 카드들의 숫자를 flush에 저장 
			if( deck[i].replaceAll("[0-9]", "").equals(shape) ) {
				flush[flush_count] = Integer.parseInt(deck[i].replaceAll("[^0-9]", ""));
				flush_count++;
			}
		}
		
		if( flush.length >= 5 ) {								// flush 배열의 길이가 5 이상일 때, 4칸 차이 나는 카드들의 차가 4이면 그 수는 연속 따라서 스티플 
			for( int i = 0; i < flush.length - 4; i++ ) {
				if( flush[flush.length -1 - i ] - flush[flush.length - 5 - i] == 4 ) {
					this.setResult((flush.length - 1 - i) + "스티플");						// flush.length - 1 - i 스티플	
					System.out.println("족보 결과 : " + result);
					
					this.point += 3000 + (flush.length - 1 - i);		// 포인트를 적용한다.
//					System.out.println("point : " + point);
					return;
				}
			}
		}
		// ----- 스트레이트플러쉬( 연속된 숫자 5개가 문양도 같을 때 ) 끝 -----
		
		// ----- 포카드( 같은 숫자가 4개 ) 시작 -----
		// ----- 1,000 포인트 지급 -----
		for( int i = 0; i < num_check.length; i++ ) {
			if( num_check[i] == 4 ) {		// 같은 숫자를 모아둔 num_check 배열에서 4를 원소로 가질 때,
				this.setResult((i + 1) + "포카드");		// i+1 포카드
				System.out.println("족보 결과 : " + result);
				
				this.point += 1000 + (i + 1);
//				System.out.println("point : " + point);
				return;
			}
		}
		// ----- 포카드( 같은 숫자가 4개 ) 끝 -----
		
		// ----- 풀하우스( 같은 숫자 3개 + 같은 숫자 2개 ) 시작 -----
		// ----- 500 포인트 지급 -----
		for( int i = num_check.length - 1; i >= 0; i-- ) {
			for( int j = 0; j < num_check.length; j++ ) {
				if( num_check[i] >= 3 && i != j && num_check[j] >= 2 ) {
					this.setResult((i + 1) + "풀하우스");		// i + 1 풀하우스
					System.out.println("족보 결과 : " + result);
					
					this.point += 500 + (i + 1);
//					System.out.println("point : " + point);
					return;
				}
			}
		}
		// ----- 풀하우스( 같은 숫자 3개 + 같은 숫자 2개 ) 끝 -----
		
		// ----- 플러쉬 ( 같은 문양 5개 ) 시작 -----
		// ----- 300 포인트 지급 -----
		String[] shape_list = {"♠", "♥", "◆", "♣"};
		
		for( int i = 0; i < shape_check.length; i++ ) {
			if( this.shape_check[i] >= 5 ) {
				for( int j = deck.length - 1; j >= 0; j-- ) {
					if( deck[j].replaceAll("[0-9]", "").equals(shape_list[i])) {
						int jNum = Integer.parseInt(deck[j].replaceAll("[^0-9]", ""));
						this.setResult(jNum + "플러쉬");
						System.out.println("족보 결과 : " + result);
						
						this.point += 300 + jNum;
//						System.out.println("point : " + point);
						return;
					}
				}			
			}
		}				
		// ----- 플러쉬 ( 같은 문양 5개 ) 끝 -----
		
		// ----- 스트레이트( 연속된 숫자 5개 이상 ) 시작 -----
		// ----- 100 포인트 지급 -----
		int[] straight = new int[deck.length];				//  카드들의 숫자를 저장할 straight 배열
		int straight_count = 0;
		
		for( int i = 0; i < deck.length; i++ ) {
			int number = Integer.parseInt(deck[i].replaceAll("[^0-9]", ""));			
			if( straight_count == 0 ) {
				straight[straight_count] = number;
				straight_count++;
			} else if( number != straight[straight_count - 1] ) {
				straight[straight_count] = number;
				straight_count++;
			}
		}
		
		for( int i = straight.length - 5; i >= 0; i-- ) {
			if( straight[i] + 1 == straight[i+1] && straight[i+1] + 1 == straight[i+2] 
					&& straight[i+2] + 1 == straight[i+3] && straight[i+3] + 1 == straight[i+4] ) {
				this.setResult(straight[i+4] + "스트레이트"); 		// straight[i+4] 스트레이트
				System.out.println("족보 결과 : " + result);
				
				this.point += 100 + straight[i+4];
//				System.out.println("point : " + point);
				return;
			}
		}
		// ----- 스트레이트( 연속된 숫자 5개 이상 ) 끝 -----
		
		// ----- 트리플( 같은 숫자 3개 ) 시작 -----
		// ----- 70 포인트 지급 -----
		for( int i = num_check.length - 1; i >= 0; i-- ) {
			if( num_check[i] >= 3 ) {
				this.setResult((i + 1) + "트리플");			// i + 1 트리플
				System.out.println("족보 결과 : " + result);
				
				this.point += 70 + (i + 1);
//				System.out.println("point : " + point);
				return;
			}
		}		
		// ----- 트리플( 같은 숫자 3개 ) 끝 -----
		
		// ----- 투페어( 페어 2쌍 ) 시작 -----
		// ----- 50 포인트 지급 -----
		for( int i = num_check.length - 1; i >= 0; i-- ) {
			for( int j = 0; j < num_check.length; j++ ) {
				if( num_check[i] >= 2 && i != j && num_check[j] >= 2 ) {
					this.setResult((i + 1) + "투페어");		// i + 1 투페어
					System.out.println("족보 결과 : " + result);
					
					this.point += 50 + (i + 1);
//					System.out.println("point : " + point);
					return;
				}
			}
		}		
		// ----- 투페어( 페어 2쌍 ) 끝 -----
		
		// ----- 페어( 페어 2쌍 ) 시작 -----
		// ----- 30 포인트 지급 -----
		for( int i = num_check.length - 1; i >= 0; i-- ) {
			if( num_check[i] >= 2 ) {
				this.setResult((i + 1) + "페어"); 			// i + 1 페어
				System.out.println("족보 결과 : " + result);
				
				this.point += 30 + (i + 1);
//				System.out.println("point : " + point);
				return;
			}
		}
		// ----- 페어( 페어 2쌍 ) 끝 -----
		
		// ----- 나머지( 탑 ) 시작 -----
		// ----- 10 포인트 지급 -----
		for( int i = num_check.length - 1; i >= 0; i-- ) {
			if( num_check[i] != 0 ) {
				this.setResult((i + 1) + "탑"); 			// i + 1 탑
				System.out.println("족보 결과 : " + result);
				
				this.point += 10 + (i + 1);
//				System.out.println("point : " + point);
				return;
			}
		}				
	}			
}
