package doubleslash05.mini.team11.ui.common.widget.recipevideo;

import android.content.Context;
import android.view.View;
import android.widget.MediaController;

/**
 * @author HoYean Lee
 * 동영상을 컨트롤 할 수 있는 뷰
 */
class BasicMediaController extends MediaController {
    public BasicMediaController(Context context) {
        super(context);
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

//        removeAllViews();
    }
}