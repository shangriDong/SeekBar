package com.dqs.shangri.seekbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Shangri on 2017/7/31.
 */

public class SeekBarRelativeLayout extends RelativeLayout {
    public SeekBarRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeekBarRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private TextView textView;
    private SeekBar seekBar;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    private int textViewPaddingLeft = 0;

    private void setText(String str) {
        textView.setText(str);
    }

    private void setMarginLeftForTextView(int progress) {
        if (seekBar != null && textView != null) {
            LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
            int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
            layoutParams.leftMargin = (int) (((float) progress / seekBar.getMax()) * width);

            layoutParams.leftMargin += seekBar.getPaddingRight() - textView.getWidth() / 2 + textViewPaddingLeft;

            setText(Integer.toString(progress));
            textView.setLayoutParams(layoutParams);
        }
    }

    public void setProgress(int process) {
        if (seekBar != null) {
            seekBar.setProgress(process);
        }
    }

    public void setEnabled(boolean enabled) {
        if (seekBar != null) {
            seekBar.setEnabled(enabled);
        }
    }

    public int getProcess() {
        if (seekBar != null) {
            return seekBar.getProgress();
        }
        return 0;
    }

    public void initSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.seek_bar_relative_layout_seek_bar);
        textView = (TextView) findViewById(R.id.seek_bar_relative_layout_text_view);

        textView.setVisibility(INVISIBLE);

        textViewPaddingLeft = ((LayoutParams) textView.getLayoutParams()).leftMargin;
        if (seekBar != null && textView != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    setMarginLeftForTextView(progress);
                    if (onSeekBarChangeListener != null) {
                        onSeekBarChangeListener.onProgressChanged(seekBar, progress, fromUser);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    if (onSeekBarChangeListener != null) {
                        onSeekBarChangeListener.onStartTrackingTouch(seekBar);
                    }

                    textView.setVisibility(VISIBLE);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (onSeekBarChangeListener != null) {
                        onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                    }
                    textView.setVisibility(INVISIBLE);
                }
            });
        }
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }
}
