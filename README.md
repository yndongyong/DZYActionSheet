# DZYActionSheet
ActionSheet for Android  粗仿iOS微信

效果如图

![ActionSheet](http://oav23hfp9.bkt.clouddn.com/16-8-17/56875479.jpg)

使用说明

    private void openActionSheet() {
        DZYActionSheet actionSheet = new DZYActionSheet(this);
        String[] arrays = {"拍照", "从相册中选择"};
        actionSheet.show(this.getWindow().getDecorView(),arrays, new DZYActionSheet.OnDZYActionSheetListener() {
            @Override
            public void onClick(String text, int position) {
                Toast.makeText(MainActivity.this, "text: "+text+" ,position:"+position, Toast
                        .LENGTH_SHORT).show();
            }
        });
    }
