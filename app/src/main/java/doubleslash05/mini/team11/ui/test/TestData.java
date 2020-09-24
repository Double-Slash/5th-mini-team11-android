package doubleslash05.mini.team11.ui.test;

// 원래는 model > data에 있어야 함
// 캡슐화도 생략
public abstract class TestData {
    public String text;

    public TestData(String text) {
        this.text = text;
    }
}