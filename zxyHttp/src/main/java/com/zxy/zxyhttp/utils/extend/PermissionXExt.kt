package com.zxy.zxyhttp.utils.extend

import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by zsf on 2020/11/19 14:51
 * ******************************************
 * * 权限统一处理，复制使用方法到Activity'
 * ******************************************
 */
/**
 * 使用方式
requestPermission(
mutableListOf(
Manifest.permission.READ_CONTACTS,
Manifest.permission.CAMERA,
Manifest.permission.CALL_PHONE
), {

}, {
Toast.makeText(this, "不同意的: $it", Toast.LENGTH_LONG).show()
})
 */
fun FragmentActivity.requestPermission(permissions: MutableList<String>, onSuccess: () -> Unit, onFailed: (MutableList<String>) -> Unit) {
    var mContext = this

    GlobalScope.launch(Dispatchers.Main) {
        PermissionX.init(mContext)
            .permissions(permissions)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "请同意这些权限，用于我们接下来的操作",
                    "确定",
                    "取消"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "您需要在设置中手动允许必要的权限，否则不能使用这部分功能哦!",
                    "确定",
                    "取消"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {//全部同意
                    onSuccess()
                } else {//
                    onFailed(deniedList)
                }
            }
    }
}