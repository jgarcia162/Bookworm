package jose.com.bookworm;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Joe on 10/30/17.
 */

public class CustomScrollingTextView extends LinearLayout{
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    int DEFAULT_DIRECTION;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    int DEFAULT_ASYMPTOTE;
    public static final int MATRIX = 0;
    public static final int FIT_XY = 1;
    public static final int FIT_START = 2;
    public static final int FIT_CENTER = 3;
    public static final int FIT_END = 4;
    public static final int CENTER = 5;
    public static final int CENTER_CROP = 6;
    public static final int CENTER_INSIDE = 7;
    int DEFAULT_SCALE_TYPE;
    private final int DEFAULT_RESOURCE_ID;
    private final int DEFAULT_DURATION;
    private int DIRECTION_MULTIPLIER;
    private int duration;
    private int resourceId;
    private int direction;
    private int scaleType;
    private ValueAnimator animator;
    private ImageView firstImage;
    private ImageView secondImage;
    private boolean isBuilt;
    public static final String TAG = CustomScrollingTextView.class.getSimpleName();

    public CustomScrollingTextView(Context context) {
        super(context);
        this.DEFAULT_DIRECTION = 3;
        this.DEFAULT_ASYMPTOTE = 0;
        this.DEFAULT_SCALE_TYPE = 3;
        this.DEFAULT_RESOURCE_ID = -1;
        this.DEFAULT_DURATION = 3000;
        this.DIRECTION_MULTIPLIER = -1;
        this.isBuilt = false;
        this.init(context);
    }

    public CustomScrollingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomScrollingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.DEFAULT_DIRECTION = 3;
        this.DEFAULT_ASYMPTOTE = 0;
        this.DEFAULT_SCALE_TYPE = 3;
        this.DEFAULT_RESOURCE_ID = -1;
        this.DEFAULT_DURATION = 3000;
        this.DIRECTION_MULTIPLIER = -1;
        this.isBuilt = false;
        this.setViewAttributes(context, attrs, defStyleAttr);
        this.init(context);
    }

    private void setViewAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, com.cunoraz.library.R.styleable.ContinuousScrollableImageView, defStyleAttr, 0);
        this.resourceId = array.getResourceId(com.cunoraz.library.R.styleable.ContinuousScrollableImageView_imageSrc, -1);
        this.direction = array.getInt(com.cunoraz.library.R.styleable.ContinuousScrollableImageView_direction, this.DEFAULT_DIRECTION);
        this.duration = array.getInt(com.cunoraz.library.R.styleable.ContinuousScrollableImageView_duration, 3000);
        this.scaleType = array.getInt(com.cunoraz.library.R.styleable.ContinuousScrollableImageView_scaleType, this.DEFAULT_SCALE_TYPE);
        this.setDirectionFlags(this.direction);
        array.recycle();
    }

    private void setDirectionFlags(int direction) {
        switch(direction) {
            case 0:
                this.DIRECTION_MULTIPLIER = 1;
                this.DEFAULT_ASYMPTOTE = 1;
                break;
            case 1:
                this.DIRECTION_MULTIPLIER = -1;
                this.DEFAULT_ASYMPTOTE = 0;
                break;
            case 2:
                this.DIRECTION_MULTIPLIER = -1;
                this.DEFAULT_ASYMPTOTE = 1;
                break;
            case 3:
                this.DIRECTION_MULTIPLIER = 1;
                this.DEFAULT_ASYMPTOTE = 0;
        }

    }

    private void init(Context context) {
        inflate(context, com.cunoraz.library.R.layout.continuos_scrollable_imageview_layout, this);
        this.build();
    }

    public void setDuration(int duration) {
        this.duration = duration;
        this.isBuilt = false;
        this.build();
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.isBuilt = false;
        this.setDirectionFlags(direction);
        this.build();
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
        this.firstImage.setImageResource(this.resourceId);
        this.secondImage.setImageResource(this.resourceId);
    }

    public void setScaleType(int scaleType) {
        if(this.firstImage != null && this.secondImage != null) {
            ImageView.ScaleType type = ImageView.ScaleType.CENTER;
            switch(scaleType) {
                case 0:
                    type = ImageView.ScaleType.MATRIX;
                    break;
                case 1:
                    type = ImageView.ScaleType.FIT_XY;
                    break;
                case 2:
                    type = ImageView.ScaleType.FIT_START;
                    break;
                case 3:
                    type = ImageView.ScaleType.FIT_CENTER;
                    break;
                case 4:
                    type = ImageView.ScaleType.FIT_END;
                    break;
                case 5:
                    type = ImageView.ScaleType.CENTER;
                    break;
                case 6:
                    type = ImageView.ScaleType.CENTER_CROP;
                    break;
                case 7:
                    type = ImageView.ScaleType.CENTER_INSIDE;
            }

            this.scaleType = scaleType;
            this.firstImage.setScaleType(type);
            this.secondImage.setScaleType(type);
        } else {
            throw new NullPointerException();
        }
    }

    private void build() {
        if(!this.isBuilt) {
            this.isBuilt = true;
            this.setImages();
            if(this.animator != null) {
                this.animator.removeAllUpdateListeners();
            }

            this.animator = ValueAnimator.ofFloat(new float[]{0.0F, (float)this.DIRECTION_MULTIPLIER * -1.0F});
            this.animator.setRepeatCount(-1);
            this.animator.setInterpolator(new LinearInterpolator());
            this.animator.setDuration((long)this.duration);
            switch(this.DEFAULT_ASYMPTOTE) {
                case 0:
                    this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float progress = (float)CustomScrollingTextView.this.DIRECTION_MULTIPLIER * -((Float)animation.getAnimatedValue()).floatValue();
                            float width = (float)(CustomScrollingTextView.this.DIRECTION_MULTIPLIER * -CustomScrollingTextView.this.firstImage.getWidth());
                            float translationX = width * progress;
                            CustomScrollingTextView.this.firstImage.setTranslationX(translationX);
                            CustomScrollingTextView.this.secondImage.setTranslationX(translationX - width);
                        }
                    });
                    break;
                case 1:
                    this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float progress = (float)CustomScrollingTextView.this.DIRECTION_MULTIPLIER * -((Float)animation.getAnimatedValue()).floatValue();
                            float height = (float)(CustomScrollingTextView.this.DIRECTION_MULTIPLIER * -CustomScrollingTextView.this.firstImage.getHeight());
                            float translationY = height * progress;
                            CustomScrollingTextView.this.firstImage.setTranslationY(translationY);
                            CustomScrollingTextView.this.secondImage.setTranslationY(translationY - height);
                        }
                    });
            }

            this.animator.start();
        }
    }

    private void setImages() {
        if(this.resourceId == -1) {
            Log.e(TAG, "image must be initialized before it can be used. You can use in XML like this: (app:imageSrc=\"@drawable/yourImage\") ");
        } else {
            this.firstImage = (ImageView)this.findViewById(com.cunoraz.library.R.id.first_image);
            this.secondImage = (ImageView)this.findViewById(com.cunoraz.library.R.id.second_image);
            this.firstImage.setImageResource(this.resourceId);
            this.secondImage.setImageResource(this.resourceId);
            this.setScaleType(this.scaleType);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.animator != null) {
            this.animator.cancel();
        }

    }

    public static final class Builder {
        private CustomScrollingTextView scrollableImageView;

        public Builder(Activity activity) {
            this.scrollableImageView = new CustomScrollingTextView(activity);
        }

        public CustomScrollingTextView.Builder setDuration(int duration) {
            this.scrollableImageView.setDuration(duration);
            return this;
        }

        public CustomScrollingTextView.Builder setResourceId(int resourceId) {
            this.scrollableImageView.setResourceId(resourceId);
            return this;
        }

        public CustomScrollingTextView.Builder setDirection(int direction) {
            this.scrollableImageView.setDirection(direction);
            return this;
        }

        public CustomScrollingTextView.Builder setScaleType(int scaleType) {
            this.scrollableImageView.setScaleType(scaleType);
            return this;
        }

        public CustomScrollingTextView build() {
            return this.scrollableImageView;
        }
    }
}
