package org.techtown.mvpattern;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText edt;
    Button btn;
    TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText)findViewById(R.id.edtTxt);
        btn = (Button)findViewById(R.id.btn);
        resultTxt = (TextView)findViewById(R.id.txtView);

        btn.setBackgroundColor(Color.GRAY);

        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String MovieName = edt.getText().toString();

        if(MovieName.equals("닥터스트레인지")) {
            String reply[] = {"<b>닥터 스트레인지</b>,마법계의 어벤져스 <영화리뷰 474번째 이야기> 영제: Doctor Strange(2016) 장르: 어드벤처,경이 런타임: 115분 관람 장소: 일산 롯데시네마 감독: 스콧 데릭슨 출연: 베네딕트 컴버베치, 레이첼...",
                    "ㅎㅎ 그럼 축구얘기는 이만하고 오늘 소개할 노래에 대해서 설명토록 하겠습니다. 모험을 느낀 영화, 오늘 소개할 노래는 영화 <b>닥터 스트레인지</b> ost 노래모음입니다 아직 개봉되지 않은 영화인데요 어젠 가 그젠가 하이라이트 상영회를 했다고 합니다...",
                    "내한공연을 보러 서울에 올라갔을 때 친구와 만나 마블 신작 영화 <b>닥터 스트레인지</b>를 보게되었는데요! 장르: 판타지,대체 타임라인이 쥐불놀이로 대동단결 되어있나 했더니?ㅋㅋ <b>닥터 스트레인지</b>에 등장하는 일종의 마법진이 쥐불놀이와 닮았기...",
                    "마블 시네마틱 유니버스 현존 최고, 최강의 마법사 슈퍼히어로인 <b>닥터 스트레인지</b>가 드디어 등장했습니다. 장르: 액션,경이 영화를 보신 분들은 아시겠지만 이번 '<b>닥터 스트레인지</b>'에는 기존 마블 영화에서 단골로 출연하던 아우디가 아닌 람보르...",
                    "돌아왔어요~!! 어느샌가 히어로물 하면 마블영화라는 공식이 생겨나고 있죠~ 이번에 개봉하는 영화 <b>닥터스트레인지</b>~~ 장르: 판타지,경이 영화 <b>닥터스트레인지</b>는 10/26 개봉했습니다. 전세계의 집중을 받는 이번 영화의 주인공은 <b>닥터스트레인지</b>란..."};
            //영화 장르설정(14개)
            String feel[] = {"어드벤처", "액션", "판타지", "경이", "모험", "스릴러", "로맨스", "공포", "다큐", "드라마",
                    "추리", "애니메이션", "코미디", "뮤지컬"};

            int result[] = new int[14]; //배열을 0으로 초기화
            String word = null;

            for(int i = 0; i < reply.length; i++) {
                String regex = "[A-Za-z0-9<>!~ㅋㅎ()?:/]";
                reply[i] = reply[i].replaceAll(regex, "");

                //빅데이터 분석 처리!
                for(int j = 0; j < feel.length; j++) {
                    int count = 0;
                    //Pattern p = Pattern.compile(패턴) ->미리 패턴을 배열로 정의해놓고 함
                    Pattern p = Pattern.compile(feel[j]);

                    // Matcher m = p.matcher(문자열) ->이 문자열에서 위의 방법으로 패턴을 찾아내는 것이다
                    Matcher m = p.matcher(reply[i]);
                    //find()메소드 -> 패턴이 일치하면 true, 일치하지 않으면 false값 반환
                    while(m.find()) {
                        word = m.group(); //패턴이 나올 때 word에 저장
                        count++;
                    }
                    result[j] += count;

                    if(i == (reply.length-1) && j == (reply.length-1)) {
                        for(int x = 0; x < feel.length; x++) {
                            if(result[x] == 0) continue;
                            resultTxt.append(feel[x] + ":");
                            resultTxt.append(result[x] + "개\n");
                        }
                    }
                }
            }
        }else {
            Toast.makeText(MainActivity.this, "딴거 말고 닥터스트레인지 검색하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
