# DZYActionSheet
ActionSheet for Android  粗仿iOS微信

效果如图

![ActionSheet](http://oav23hfp9.bkt.clouddn.com/16-8-17/56875479.jpg)


引入说明

step 0:
allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
step 1:
dependencies {
            compile 'com.github.yndongyong:DZYActionSheet:0.0.3'
    }


使用说明
private void openActionSheet() {
    DZYActionSheet actionSheet = new DZYActionSheet(this);
    String[] arrays = {"小视屏", "拍照", "从相册中选择"};
    actionSheet.show(this.getWindow().getDecorView(), arrays, new DZYActionSheet.OnDZYActionSheetListener() {
        @Override
        public void onClick(String text, int position) {
            Toast.makeText(MainActivity.this, "text: " + text + " ,position:" + position, Toast
                    .LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "user cancel !", Toast.LENGTH_SHORT).show();
        }
    });
}
