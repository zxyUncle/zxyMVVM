# MVVM+协程+Retrfit+Okhttp+NavigationFragment跳转（多Fragment）

[![](https://jitpack.io/v/zxyUncle/zxyMVVM.svg)](https://jitpack.io/#zxyUncle/zxyMVVM)
Gradle
-----
Step 1



	dependencies {
	  implementation 'com.github.zxyUncle:zxyMVVM:1.0.0'
	}
1、网路请求ViewModel
-----

    class VMMainActivity : BaseViewModel() {
    var data = MutableLiveData<BaseBean<ArrayList<ArticleData>>>()
    fun getData() {
        reqeustApi({
            data.value = getWXArticle()
        })
    }

2、NavigationFragment跳转,可以跳转到Fragment，也可以跳转到Activity
-----
1、初始化navigation配置文件

       NavigationObj.navInit(this, R.id.mFrameLayout, R.navigation.nav_graph)
2、Activity跳转到Fragment

       NavigationObj.navSkip(R.id.firstFragment,bundle)
3、Fragment跳转到Framgnet

       NavigationObj.navSkipFragment(view,R.id.firstFragment)
4、跳转到已经存在的Fragment，复用Fragment

       NavigationObj.navSkipExist(R.id.firstFragment)

5、不明白的看demo，使用源码Module，不要使用地址依赖






