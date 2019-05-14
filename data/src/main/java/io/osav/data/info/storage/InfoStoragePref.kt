package io.osav.data.info.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.osav.data.info.storage.model.InfoStored
import io.osav.data.tools.catchGson
import io.reactivex.*

class InfoStoragePref(
    val prefs: SharedPreferences,
    val gson: Gson,
    val logger: (String, Throwable) -> Unit
) : InfoStorage {

    companion object {
        const val INFO_KEY = "INFO_KEY_632881"
    }

    override fun putInfo(info: InfoStored): Single<InfoStored> {
        try {
            val infoJson = gson.toJson(info)
            prefs.edit()
                .putString(INFO_KEY, infoJson)
                .apply()
            return Single.just(info)
        } catch (throwable: Throwable) {
            return Single.error(Exception("Can't put Info to local storage", throwable))
        }

    }

    override fun getInfo(): Single<InfoStored> {
        return Single.create<InfoStored> { emitter ->
            val userJson = prefs.getString(INFO_KEY, "")
            if (userJson?.isEmpty() == true) {
                emitter.onError(Exception("No user in the store"))
            }
            try {
                emitter.onSuccess(gson.fromJson<InfoStored>(userJson, InfoStored::class.java))
            } catch (ex: JsonSyntaxException) {
                emitter.onError(Exception("Can't get Info from local storage", ex))
            }
        }
    }

    override fun observeInfo(): Flowable<InfoStored> {
        return Flowable.create<InfoStored>({ emitter ->

            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                val infoJson = prefs.getString(INFO_KEY, null) ?: ""
                if (infoJson.isEmpty()) {
                    emitter.onError(Exception("No Info in the Storage"))
                }
                catchGson<InfoStored> (logger){ gson.fromJson(infoJson, InfoStored::class.java)}?.let {
                    emitter.onNext(it)
                }
            }

            emitter.setCancellable { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
            prefs.registerOnSharedPreferenceChangeListener(listener)

        }, BackpressureStrategy.LATEST)
    }

    override fun clearStorage(): Completable {
        return Completable.fromAction{
            prefs.edit()
                .putString(INFO_KEY, "")
                .apply()
        }
    }
}