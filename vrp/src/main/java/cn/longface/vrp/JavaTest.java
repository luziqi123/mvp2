package cn.longface.vrp;

public class JavaTest {

    public static void main(String[] args) {

        InstanceCallback<String> callback = new InstanceCallback<String>() {
            @Override
            public void onHasInstance(String instance) {

            }
        };
        System.out.println(callback.getClazz());
    }
}
