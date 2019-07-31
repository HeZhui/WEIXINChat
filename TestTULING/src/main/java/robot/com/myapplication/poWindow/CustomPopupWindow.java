package robot.com.myapplication.poWindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import robot.com.myapplication.R;

/**
 * Created by Administrator on 2019/7/28.
 */

public class CustomPopupWindow extends PopupWindow implements View.OnClickListener {
    private RelativeLayout vChat_re, camera_re, pictures_re;
    private View mPopView;
    private OnItemClickListener mListener;

    public CustomPopupWindow(Context context) {
        super(context);
        init(context);
        setPopupWindow();
        vChat_re.setOnClickListener(this);
        camera_re.setOnClickListener(this);
        pictures_re.setOnClickListener(this);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from( context );
        //绑定布局
        mPopView = inflater.inflate( R.layout.custom_popup_window,null );
        vChat_re = (RelativeLayout)mPopView.findViewById( R.id.vChat_re );
        camera_re = (RelativeLayout)mPopView.findViewById( R.id.camera_re );
        pictures_re = (RelativeLayout)mPopView.findViewById( R.id.pictures_re );
    }

    /***
     * 设置窗口相关的属性
     */
    private void setPopupWindow(){
        //设置View
        this.setContentView( mPopView );
        //设置弹出窗口的宽度
        this.setWidth( RelativeLayout.LayoutParams.MATCH_PARENT );
        //设置弹出窗口的高度
        this.setHeight( RelativeLayout.LayoutParams.WRAP_CONTENT );
        //设置弹出窗口
        this.setFocusable( true );
        //设置动画
        this.setAnimationStyle( R.style.my_popWindow_anim_style );
        //设置背景透明
        this.setBackgroundDrawable( new ColorDrawable( 0x00000000000 ) );
        //如果触摸位置在窗口外面则销毁
        mPopView.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mPopView.findViewById( R.id.id_pop_layout ).getTop();
                int y = (int) event.getY();
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(y < height){
                        dismiss();
                    }
                }
                return true;
            }
        } );
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.setOnItemClick(v);
        }
    }
}
