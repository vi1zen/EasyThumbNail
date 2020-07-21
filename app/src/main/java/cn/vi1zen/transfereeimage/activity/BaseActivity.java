package cn.vi1zen.transfereeimage.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.FileUtils;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

import cn.vi1zen.transferee.transfer.TransferConfig;
import cn.vi1zen.transferee.transfer.Transferee;

/**
 * Created by Vans Z on 2017/2/13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected static final int READ_EXTERNAL_STORAGE = 100;
    protected static final int WRITE_EXTERNAL_STORAGE = 101;

    protected Transferee transferee;
    protected TransferConfig config;
    protected RecyclerView gvImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 修改状态栏颜色
        transferee = Transferee.getDefault(this);
        setContentView(getContentView());
        initView();
        testTransferee();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        transferee.destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != WRITE_EXTERNAL_STORAGE) {
            Toast.makeText(this, "请允许获取相册图片文件写入权限", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkWriteStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void testTransferee();

}
