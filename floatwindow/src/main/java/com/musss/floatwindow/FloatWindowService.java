package com.musss.floatwindow;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.torrydo.floatingbubbleview.CloseBubbleBehavior;
import com.torrydo.floatingbubbleview.FloatingBubbleListener;
import com.torrydo.floatingbubbleview.service.expandable.BubbleBuilder;
import com.torrydo.floatingbubbleview.service.expandable.ExpandableBubbleService;
import com.torrydo.floatingbubbleview.service.expandable.ExpandedBubbleBuilder;

public class FloatWindowService extends ExpandableBubbleService {



    @Override
    public void onCreate() {
        super.onCreate();
        minimize();
    }

    @Nullable
    @Override
    public BubbleBuilder configBubble() {
        View expandedView = LayoutInflater.from(this).inflate(R.layout.layout_float_window, null);
        LinearLayout linearLayout = expandedView.findViewById(R.id.layoutContainer);
        int chartCount = 0;
        for(FloatWindowEntity entity : FloatWindowManager.getInstance().getData()){
            if(entity.getTitle() != null){
                linearLayout.addView(createItemView(entity));
            } else {
                chartCount++;
                switch (chartCount){
                    case 1:
                        new FloatWindowChart(expandedView.findViewById(R.id.lineChart1), entity);
                        break;
                    case 2:
                        new FloatWindowChart(expandedView.findViewById(R.id.lineChart2), entity);
                        break;
                    case 3:
                        new FloatWindowChart(expandedView.findViewById(R.id.lineChart3), entity);
                        break;
                    case 4:
                        new FloatWindowChart(expandedView.findViewById(R.id.lineChart4), entity);
                        break;
                    case 5:
                        new FloatWindowChart(expandedView.findViewById(R.id.lineChart5), entity);
                        break;
                    case 6:
                        new FloatWindowChart(expandedView.findViewById(R.id.lineChart6), entity);
                        break;
                }
            }
        }
        linearLayout.addView(createItemView(new FloatWindowEntity("退出") {
            @Override
            public void onClick() {
                stopSelf();
            }
        }));

        return new BubbleBuilder(this)
                .bubbleView(expandedView)
                .bubbleDraggable(true)
                .forceDragging(true)
                .distanceToClose(500)
                .closeBehavior(CloseBubbleBehavior.FIXED_CLOSE_BUBBLE)
                .triggerClickablePerimeterPx(5f)
                .startLocation(100, 100)
                .enableAnimateToEdge(true)
                .bottomBackground(false)
                .addFloatingBubbleListener(new FloatingBubbleListener() {
                    @Override
                    public void onFingerDown(float x, float y) {}
                    @Override
                    public void onFingerUp(float x, float y) {}
                    @Override
                    public void onFingerMove(float x, float y) {}
                });
    }

    @Nullable
    @Override
    public ExpandedBubbleBuilder configExpandedBubble() {
        View expandedView = LayoutInflater.from(this).inflate(R.layout.layout_float_window, null);
        return new ExpandedBubbleBuilder(this)
                .expandedView(expandedView)
                .startLocation(0, 0)
                .draggable(true)
                .fillMaxWidth(true)
                .enableAnimateToEdge(true)
                .dimAmount(0.5f);
    }

    private View createItemView(FloatWindowEntity entity){
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_float_window, null, false);
        TextView msgText = inflate.findViewById(R.id.tvContent);
        msgText.setText(entity.getTitle());
        inflate.setOnClickListener(v -> {
            entity.onClick();
        });
        entity.setListener(() -> {
            msgText.post(() -> {
                msgText.setText(entity.getTitle());
            });
        });
        inflate.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                inflate.setAlpha(0.6f);
            } else if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL){
                inflate.setAlpha(1.0f);
            }
            return false;
        });
        return inflate;
    }
}

