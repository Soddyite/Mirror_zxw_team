package com.example.dllo.mirror.controller.activity;

import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.DressResultAdapter;

/**
 * Created by zouliangyu on 16/6/16.
 * 佩戴效果界面
 */
public class DressResultActivity extends BaseActivity implements View.OnClickListener {
    private ListView listView;
    private DressResultAdapter dressResultAdapter;
    // 图片
    private int[] image = {R.mipmap.piao1, R.mipmap.piao2, R.mipmap.piao3,
            R.mipmap.piao4, R.mipmap.piao5};
    // videoview 视频, 视频上面图片及图片上面的播放键
    private VideoView videoView;
    private ImageView topIv;
    private ImageView playerIv;

    private MediaController mediaController;
    private String videoUrl; // 视频网址
    // 点击图片弹出图片popup
    private PopupWindow popupWindow;
    // popup里的图片
    private ImageView popupImageView;

    @Override
    protected int getLayout() {
        return R.layout.activity_dress_result;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.listview);
        bindView(R.id.dress_result_exit).setOnClickListener(this);
        bindView(R.id.dress_result_buy).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        // 播放的视频
        videoUrl = "http://222.161.205.182/youku/6975BB18A6E3F828B11ABE2BE7/0300200100576218C4CF2B052C4723710FE169-C540-E4DF-B74D-F9ABA4405961.mp4";
        dressResultAdapter = new DressResultAdapter(DressResultActivity.this);

        mediaController = new MediaController(DressResultActivity.this);

        // 视频头布局
        View viewVideo = LayoutInflater.from(DressResultActivity.this).inflate(
                R.layout.header_dress_video, null);
        videoView = (VideoView) viewVideo.findViewById(R.id.videoview);
        topIv = (ImageView) viewVideo.findViewById(R.id.video_top_iv);
        playerIv = (ImageView) viewVideo.findViewById(R.id.video_player_iv);
        playerIv.setOnClickListener(this);
        topIv.setOnClickListener(this);
        listView.addHeaderView(viewVideo);
        // 给适配器设置图片
        dressResultAdapter.setImage(image);
        listView.setAdapter(dressResultAdapter);
        // 设置popup
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        View viewPopup = LayoutInflater.from(DressResultActivity.this).inflate(R.layout.popup_dress_picture, null);
        popupImageView = (ImageView) viewPopup.findViewById(R.id.popup_dress_iv);
        viewPopup.setOnClickListener(this);
        popupWindow.setContentView(viewPopup);
        // listview点击
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!popupWindow.isShowing()) {
                    popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);
                    popupImageView.setImageResource(image[position - 1]);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_player_iv:
                playerIv.setVisibility(View.INVISIBLE);
                topIv.setVisibility(View.INVISIBLE);
                // 播放视频
                Uri uri = Uri.parse(videoUrl);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
                break;
            case R.id.video_top_iv:
                playerIv.setVisibility(View.INVISIBLE);
                topIv.setVisibility(View.INVISIBLE);
                // 播放视频
                Uri uri2 = Uri.parse(videoUrl);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri2);
                videoView.requestFocus();
                videoView.start();
                break;
            // popup点击
            case R.id.popup_dress:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                break;
            // 退出
            case R.id.dress_result_exit:
                finish();
                break;
            // 购买
            case R.id.dress_result_buy:
                Toast.makeText(this, "购买", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
