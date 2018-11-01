package jose.com.bookworm;

import android.app.Application;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import jose.com.bookworm.model.MyObjectBox;

/**
 * Created by Joe on 10/23/17.
 */

public class App extends Application {

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if(BuildConfig.DEBUG){
            new AndroidObjectBrowser(boxStore).start(this);
        }
    }

    public BoxStore getBoxStore(){
        return boxStore;
    }
}
