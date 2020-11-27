package com.zxy.zxymvvm.bean


/**
 * 公众号
 * @author ssq
 */
data class ArticleData(var errorCode: Int = -1,
                       var errorMsg: String = "",
                       var data: MutableList<Chapters>
) {
    data class Chapters(var courseId: String = "",
                        var id: String = "",
                        var name: String = "",
                        var order: Int = 0)

}

