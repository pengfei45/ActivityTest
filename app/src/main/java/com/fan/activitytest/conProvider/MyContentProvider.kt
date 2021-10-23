package com.fan.activitytest.conProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private val table1Dir = 0

    private val table1Item = 1

    private val table2Dir = 2

    private val table2Item = 3

    init {
        uriMatcher.addURI("com.fan.app.provider", "table1", table1Dir)
        uriMatcher.addURI("com.fan.app.provider ", "table1/#", table1Item)
        uriMatcher.addURI("com.fan.app.provider ", "table2", table2Dir)
        uriMatcher.addURI("com.fan.app.provider ", "table2/#", table2Item)
    }

    /**
     * 初始化 ContentProvider 的时候调用，通常会在这里完成对数据库的创建和升级等操作。
     * @return true ContentProvider初始化成功，false 失败;
     */
    override fun onCreate(): Boolean {
        return false
    }

    /**
     * 从 ContentProvider中查询数据。
     * @param uri 用于确定查询哪张表
     * @param projection 用于确定查询哪些列
     * @param selection
     * @param selectionArgs 与selection一起，用于约束查询哪些行
     * @param sortOrder 用于对结果进行排序，中返回
     * @return 包含查询结果的Cursor对象
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Item -> {
                //查询table1表中的单条数据
            }
            table1Dir -> {
                //查询table1表中的所有数据
            }
            table2Dir -> {
                // 查询table2表中的所有数据
            }
            table2Item -> {
                // 查询table2表中的单条数据
            }
        }
        return null
    }

    /**
     * 根据传入的内容URI 返回相应的 MIME类型。
     */
    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        table1Dir -> "vnd.android.cursor.dir/vnd.com.fan.app.provider.table1"
        table1Item -> "vnd.android.cursor.item/vnd.com.fan.app.provider.table1"
        table2Dir -> "vnd.android.cursor.dir/vnd.com.fan.app.provider.table2"
        table2Item -> "vnd.android.cursor.item/vnd.com.fan.app.provider.table2"
        else -> null
    }


    /**
     * 向ContenProvider中添加一条数据。
     * @param uri 用于确定要添加到的表
     * @param values 待添加的数据保存在values参数中
     * @return 添加完成后，返回一个用于表示这条新记录的 URI
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    /**
     * 从 ContentProvider 中删除数据
     * @param uri 用于确定删除哪一张表中的数据
     * @param selection
     * @param selectionArgs 与selection一起，用于约束删除哪些行
     * @return 被删除的行数将作为返回值返回
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    /**
     * 更新ContentProvider 中已有的数据。
     * @param uri 用于确定更新哪一张表中的数据
     * @param values 更新数据保存在values参数中
     * @param selection
     * @param selectionArgs 与selection一起，用于约束更新哪些行
     * @return 受影响的行数将作为返回值返回
     */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}