package com.lpan.study.view;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by wangchao on 16/5/24.
 */
public class SpanClickMoveMethod extends ScrollingMovementMethod {

    private static SpanClickMoveMethod sInstance;

    public static SpanClickMoveMethod getInstance() {
        if (sInstance == null) {
            sInstance = new SpanClickMoveMethod();
        }
        return sInstance;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);

            if (off > 0 && off < widget.getText().length() && link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    link[0].onClick(widget);
                } else if (action == MotionEvent.ACTION_DOWN) {
                    int start = buffer.getSpanStart(link[0]);
                    int end = buffer.getSpanEnd(link[0]);
                    Selection.setSelection(buffer, start, end);
                }

                return true;
            } else {
                Selection.removeSelection(buffer);
            }
        }

        return false;
    }

    @Override
    public boolean canSelectArbitrarily() {
        return true;
    }

}
