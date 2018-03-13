package com.lpan.study.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import com.lpan.R;
import com.lpan.study.context.AppContext;
import com.lpan.study.utils.CollectionUtils;
import com.lpan.study.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class SectionIndexerScrollerBar extends View {
	// 触摸事件
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	private List<String> letters;
	private int choose = -1;// 选中
	private Paint paint = new Paint();

	private final int TEXT_SIZE_SP = 13;

	private final int TEXT_PADDING_DIP = 3;

	private int mTextPxSize;

	private int mTextHeight;

	private int mTextPxPadding;

	private TextView mTextDialog;

	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	public SectionIndexerScrollerBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SectionIndexerScrollerBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SectionIndexerScrollerBar(Context context) {
		super(context);
		init();
	}

	private void init() {
		letters = new ArrayList<>();
		letters.add("↑");
		letters.add("A");
		letters.add("B");
		letters.add("C");
		letters.add("D");
		letters.add("E");
		letters.add("F");
		letters.add("G");
		letters.add("H");
		letters.add("I");
		letters.add("J");
		letters.add("K");
		letters.add("L");
		letters.add("M");
		letters.add("N");
		letters.add("O");
		letters.add("P");
		letters.add("Q");
		letters.add("R");
		letters.add("S");
		letters.add("T");
		letters.add("U");
		letters.add("V");
		letters.add("W");
		letters.add("X");
		letters.add("Y");
		letters.add("Z");
		mTextPxSize = (int) ViewUtils.spToPx(AppContext.getContext(), TEXT_SIZE_SP);

		paint.setTextSize(mTextPxSize);

		mTextHeight = (int) (paint.descent() - paint.ascent());

//		mTextPxPadding = ViewUtils.dp2Px(AppContext.getContext(),TEXT_PADDING_DIP);
	}

	public List<String> getLetters() {
		return letters;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setLetters(List<String> letters) {
		this.letters = letters;
		letters.add(0, "↑");
		setBackgroundColor(Color.TRANSPARENT);
		measure(getMeasuredWidthAndState(),getMeasuredHeightAndState());
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		int size = letters.size();
//		if (size > 0) {
//			int length = size * mTextPxSize + ( size + 1 ) * mTextPxPadding;
//			setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(length, MeasureSpec.getMode(heightMeasureSpec)));
//		}
		mTextPxPadding = (MeasureSpec.getSize(heightMeasureSpec) - 27 * mTextHeight) / 26;
	}

	/**
	 * 重写这个方法
	 */
	@SuppressLint("ResourceAsColor")
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 获取焦点改变背景颜色.
		int height = getHeight();// 获取对应高度
		int width = getWidth(); // 获取对应宽度
		if(letters != null){
			int size = letters.size();
			if(size != 0){
//				int length = size * mTextPxSize + (size-1) * mTextPxPadding;
				int singleHeight = (height - mTextPxPadding) / size;// 获取每一个字母的高度
//				int top = (height - length)/2;
				for (int i = 0; i < size; i++) {
					paint.setColor(AppContext.getContext().getResources().getColor(R.color.cropper_bottom_bg));
					paint.setTextSize(mTextPxSize);
					paint.setTypeface(Typeface.DEFAULT_BOLD);
					paint.setAntiAlias(true);
					// 选中的状态
					if (i == choose) {
						paint.setColor(Color.WHITE);
						paint.setFakeBoldText(true);
					}
					// x坐标等于中间-字符串宽度的一半.
					float xPos = width / 2 - paint.measureText(letters.get(i)) / 2;
					float yPos = singleHeight * (i+1) - mTextPxPadding;
//					top += mTextPxSize + mTextPxPadding;
					canvas.drawText(letters.get(i), xPos, yPos, paint);
					paint.reset();// 重置画笔
				}
			}
		}
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();// 点击y坐标
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * letters.size());// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

		switch (action) {
			case MotionEvent.ACTION_UP:
				setBackgroundDrawable(new ColorDrawable(0x00000000));
				choose = -1;//
				invalidate();
				if (mTextDialog != null) {
					mTextDialog.setVisibility(View.INVISIBLE);
				}
				break;

			default:
				if( !CollectionUtils.isEmpty(letters)){
					setBackgroundResource(R.drawable.scroll_bar_bg);
				}
				if (oldChoose != c) {
					if (c >= 0 && c < letters.size()) {
						if (listener != null) {
							listener.onTouchingLetterChanged(letters.get(c));
						}
						if (mTextDialog != null) {
							mTextDialog.setText(letters.get(c));
							mTextDialog.setVisibility(View.VISIBLE);
						}

						choose = c;
						invalidate();
					}
				}

				break;
		}
		return true;
	}

	/**
	 * 向外公开的方法
	 *
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * 接口
	 *
	 * @author coder
	 *
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}