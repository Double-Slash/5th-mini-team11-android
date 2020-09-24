package doubleslash05.mini.team11.ui.test;

import androidx.annotation.IdRes;

// 원래는 model > data에 있어야 함
// 캡슐화도 생략
public class TestData2 extends TestData {
    @IdRes public int imgResource;

    public TestData2(String text, @IdRes int img){
        super(text);
        imgResource = img;
    }
}