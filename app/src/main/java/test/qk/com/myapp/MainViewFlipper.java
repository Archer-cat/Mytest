package test.qk.com.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * Created by Administrator on 2017/8/17.
 */

public class MainViewFlipper extends Activity implements View.OnClickListener {
    private ViewFlipper flippers;
    private ImageButton pre, next;
    private int[] imgList = {R.drawable.hd, R.drawable.ljl, R.drawable.hll, R.drawable.xqj, R.drawable.zl};

    private float startX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        flippers = (ViewFlipper) findViewById(R.id.flipper);
        pre = (ImageButton) findViewById(R.id.pre);
        next = (ImageButton) findViewById(R.id.next);

        pre.setOnClickListener(this);
        next.setOnClickListener(this);

        for (int i = 0; i < imgList.length; i++) {
            flippers.addView(getImg(imgList[i]));
        }
        flippers.setFlipInterval(5000);
        flippers.startFlipping();
    }

    private ImageView getImg(int imgList) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(imgList);
        return image;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指触到轮播区域引发的事件
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                flippers.stopFlipping();
                break;
            //手指在轮播区域上的左右滑动事件
            case MotionEvent.ACTION_MOVE:
                if (event.getX() - startX > 100) {
                    Toast.makeText(this, "查看上一张图片", Toast.LENGTH_LONG).show();
                    flippers.setInAnimation(this, R.anim.left_in);
                    flippers.setOutAnimation(this, R.anim.right_out);
                    flippers.showPrevious();
                    flippers.stopFlipping();
                }
                if (startX - event.getX() > 100) {
                    Toast.makeText(this, "查看下一张图片", Toast.LENGTH_LONG).show();
                    flippers.setInAnimation(this, R.anim.right_in);
                    flippers.setOutAnimation(this, R.anim.left_out);
                    flippers.showNext();
                    flippers.stopFlipping();
                }
                break;
            //手指离轮播区域引发的事件
            case MotionEvent.ACTION_UP:
                flippers.startFlipping();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pre:
                Toast.makeText(this, "上一页", Toast.LENGTH_SHORT).show();
                flippers.setInAnimation(this, R.anim.left_in);
                flippers.setOutAnimation(this, R.anim.right_out);
                flippers.showPrevious();
                flippers.stopFlipping();
                break;
            case R.id.next:
                Toast.makeText(this, "下一页", Toast.LENGTH_SHORT).show();
                flippers.setInAnimation(this, R.anim.right_in);
                flippers.setOutAnimation(this, R.anim.left_out);
                flippers.showNext();
                flippers.stopFlipping();
                break;
        }
    }
}
