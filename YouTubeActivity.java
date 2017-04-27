package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by user on 2017-03-02.
 */

//YouTube Data API v3 서비스를 사용하기 API 키 필용
//새 키를 생성하려면   Google APIs Console 에서 발급
//public static final String DEVELOPER_KEY = "AIzaSyAyYTO7uI5231A45RuAMO6uLn-VRWhBYtU";
public class YouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private static final int RECOVERY_DIALOG_REQUEST =1;

    //해당 동영상 주소
    String videoaddress;
    Intent CourseBook_to_Youtube_Intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubeactivity);

        CourseBook_to_Youtube_Intent = getIntent();

        Log.d("youtube Test", "사용가능여부:" + YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this)); //SUCCSESS

        getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);

        if(GlobalVariable.CourseBook_Youtube_Switcher == 1){
            switch (CourseBook_to_Youtube_Intent.getStringExtra("CourseBook_to_Youtube")) {
                case "apdol1":
                    videoaddress = TrainningVideoList.apdol1_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "apdol2":
                    videoaddress = TrainningVideoList.apdol2_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "apdol3":
                    videoaddress = TrainningVideoList.apdol3_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "bitgyou1":
                    videoaddress = TrainningVideoList.bitgyou1_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "bitgyou2":
                    videoaddress = TrainningVideoList.bitgyou2_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "double1":
                    videoaddress = TrainningVideoList.double1_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "ggeoga1":
                    videoaddress = TrainningVideoList.ggeoga1_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "ggeoga2":
                    videoaddress = TrainningVideoList.ggeoga2_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "youpdol1":
                    videoaddress = TrainningVideoList.youpdol1_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "youpdol2":
                    videoaddress = TrainningVideoList.youpdol2_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "dwidol1":
                    videoaddress = TrainningVideoList.dwidol1_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
                case "dwidol2":
                    videoaddress = TrainningVideoList.dwidol2_address[GlobalVariable.CourseBook_Youtube_Casenum-1];
                    break;
            }
        }
        else {
            switch (GlobalVariable.casekinds) {
                case "apdol1":
                    videoaddress = TrainningVideoList.apdol1_address[GlobalVariable.casenum-1];
                    break;
                case "apdol2":
                    videoaddress = TrainningVideoList.apdol2_address[GlobalVariable.casenum-1];
                    break;
                case "apdol3":
                    videoaddress = TrainningVideoList.apdol3_address[GlobalVariable.casenum-1];
                    break;
                case "bitgyou1":
                    videoaddress = TrainningVideoList.bitgyou1_address[GlobalVariable.casenum-1];
                    break;
                case "bitgyou2":
                    videoaddress = TrainningVideoList.bitgyou2_address[GlobalVariable.casenum-1];
                    break;
                case "double1":
                    videoaddress = TrainningVideoList.double1_address[GlobalVariable.casenum-1];
                    break;
                case "ggeoga1":
                    videoaddress = TrainningVideoList.ggeoga1_address[GlobalVariable.casenum-1];
                    break;
                case "ggeoga2":
                    videoaddress = TrainningVideoList.ggeoga2_address[GlobalVariable.casenum-1];
                    break;
                case "youpdol1":
                    videoaddress = TrainningVideoList.youpdol1_address[GlobalVariable.casenum-1];
                    break;
                case "youpdol2":
                    videoaddress = TrainningVideoList.youpdol2_address[GlobalVariable.casenum-1];
                    break;
                case "dwidol1":
                    videoaddress = TrainningVideoList.dwidol1_address[GlobalVariable.casenum-1];
                    break;
                case "dwidol2":
                    videoaddress = TrainningVideoList.dwidol2_address[GlobalVariable.casenum-1];
                    break;
            }
        }
    }
    /**
     * 플레이어가 초기화될 때 호출됩니다.
     * 매개변수
     *
     * provider YouTubePlayer를 초기화화는 데 사용된 제공자입니다.
     * player 제공자에서 동영상 재생을 제어하는 데 사용할 수 있는 YouTubePlayer입니다
     * wasRestored
     *    YouTubePlayerView 또는 YouTubePlayerFragment가 상태를 복원하는 과정의 일부로서
     *    플레이어가 이전에 저장된 상태에서 복원되었는지 여부입니다.
     *    true는 일반적으로 사용자가 재생이 다시 시작될 거라고 예상하는 지점에서 재생을 다시 시작하고
     *    새 동영상이 로드되지 않아야 함을 나타냅니다.
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            //player.cueVideo("wKJ9KzGQq0w"); //video id.

            //Trainning 비디오 리스트 에서 동영상 주소 를 불러온다.
            player.cueVideo(videoaddress);  //http://www.youtube.com/watch?v=IA1hox-v0jQ

            //cueVideo(String videoId)
            //지정한 동영상의 미리보기 이미지를 로드하고 플레이어가 동영상을 재생하도록 준비하지만
            //play()를 호출하기 전에는 동영상 스트림을 다운로드하지 않습니다.
            //https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubePlayer
        }
    }
        @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
