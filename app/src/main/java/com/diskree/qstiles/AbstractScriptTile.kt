package com.diskree.qstiles

import android.annotation.SuppressLint
import android.content.Intent
import android.service.quicksettings.TileService

abstract class AbstractScriptTile : TileService() {
    abstract fun getScriptName(): String

    @SuppressLint("SdCardPath")
    override fun onClick() {
        super.onClick()
        startForegroundService(Intent().apply {
            action = "com.termux.RUN_COMMAND"
            setClassName("com.termux", "com.termux.app.RunCommandService")
            putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/su")
            putExtra("com.termux.RUN_COMMAND_ARGUMENTS", arrayOf("-c", "sh /data/data/com.termux/files/home/.scripts/" + getScriptName() + ".sh"))
            putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home")
            putExtra("com.termux.RUN_COMMAND_BACKGROUND", true)
        })
    }
}