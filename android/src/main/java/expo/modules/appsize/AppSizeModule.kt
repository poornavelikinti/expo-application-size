package expo.modules.appsize

import android.app.usage.StorageStats
import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Process
import android.os.UserHandle
import android.os.storage.StorageManager
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.util.UUID

class AppSizeModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("AppSize")

    AsyncFunction("getAppSize") {
      val context = appContext.reactContext
        ?: throw Exception("No React Context available")

      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        throw Exception("API level 26+ required for StorageStatsManager")
      }

      // Get the system services
      val statsManager = context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
      val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager

      // Find the UUID & user for your app’s storage volume
      val storageUuid: UUID = storageManager.getUuidForPath(context.filesDir)
      val packageName = context.packageName
      val user: UserHandle = Process.myUserHandle()

      // Query the stats
      val stats: StorageStats = statsManager.queryStatsForPackage(storageUuid, packageName, user)

      // Return a JS-friendly map
      return@AsyncFunction mapOf(
        "appBytes" to stats.appBytes,     // code + resources size
        "dataBytes" to stats.dataBytes,   // your app’s private data
        "cacheBytes" to stats.cacheBytes  // cache folder
      )
    }
  }
}
