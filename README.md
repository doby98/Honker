## :pushpin: Honker
>혼자서도 즐길 수 있는 포커 게임
</br>

## 1. 제작 기간 & 참여 인원
- (2022. 3. 21.~2022. 4. 6.)
- 개인 프로젝트
</br>

## 2. 사용 기술
- JAVA 8
</br>

## 3. 프로젝트 설명
>프로젝트의 핵심 기능은 카드패에 따른 족보 출력 및 승패 판정입니다.
>
>사용자는 기본적인 포커 룰에 따라 각 3차례씩 카드를 받고, 베팅을 진행할 수 있습니다.
>
>게임의 마지막까지 베팅을 완료한 경우에는 완성된 내 패와 비공개된 상대방의 패를 비교하여,
>
>이용자가 베팅한 금액의 두배를 얻거나, 잃을 수 있습니다.
</br>

## 4. 흐름 & 포커 족보
- 혼커 룰 설명</br>
![혼커 룰 설명](https://user-images.githubusercontent.com/101616249/180952851-0359a687-8c05-4520-9676-ceb60aad71b6.PNG)
</br>

---

- 혼커 족보 설명</br>
![혼커 족보 설명](https://user-images.githubusercontent.com/101616249/180952863-ad257483-982c-406a-b622-3b8e611c3981.PNG)
</br>

- 클래스 다이어그램
>![image](https://user-images.githubusercontent.com/101616249/180963350-9bad6768-1366-4d97-9b6c-30a9ac6e3cd2.png)
</br>

## 5. 핵심 기능

5-1. 게임 준비
- 포커 덱 만들기
>![포커 덱 만들기](https://user-images.githubusercontent.com/101616249/180955008-a68a9a98-e7b4-468f-917b-1d9d70c5c4ac.PNG)
</br>

- 포커 덱에서 이용자에게 보낸 카드를 버리기
>![포커 덱에서 보낸 카드 버리기](https://user-images.githubusercontent.com/101616249/180955152-1f80c653-1573-4013-b893-daeac83a0d4e.PNG)
</br>

- 베팅 메서드
>![베팅 메서드](https://user-images.githubusercontent.com/101616249/180955209-738ea73e-d443-468e-8b1f-d5621c78c089.PNG)

5-2. 패의 족보 판정하기(높은 족보 순으로 점수 부여)
- Jokbo.java 참고

## 6. 트러블 슈팅
- 가장 높은 족보인 '스트레이트플러쉬'에서 플러쉬와 스트레이트를 동시에 판별하기
>먼저, 동일한 문양 의 갯수가 5개일 때를 판별한 후, 같은 문양인 카드의 갯수가 5개 이상일 때,
>4칸 차이나는 카드 수의 차가 4일때 스트레이트로 판별</br>
>![image](https://user-images.githubusercontent.com/101616249/180962440-8aa467ab-c9e1-4c35-9c60-ae1456839f4f.png)</br>

---

- 숫자와 문양이 합쳐져 있는 카드에서 숫자만 도출하기
>정규표현식을 사용하여 숫자만 사용가능
>![image](https://user-images.githubusercontent.com/101616249/180962916-2f33dbd3-965a-42c2-bcc8-4f4cb3ebe0c8.png)

---




