package doubleslash05.mini.team11.ui.test;

// 원래는 model > data에 있어야 함
// 캡슐화도 생략
public class TestData1 extends TestData {
    public String buttonText;

    public TestData1(String text, String buttonText) {
        super(text);

        this.text = text;
        this.buttonText = buttonText;
    }
}