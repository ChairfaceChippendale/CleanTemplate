package io.osav.data.dog.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.osav.data.dog.DogStorage
import io.osav.data.dog.local.model.DogPref
import io.osav.data.dog.local.model.toDog
import io.osav.data.dog.local.model.toDogPref
import io.osav.data.tools.catchGson
import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.*

class DogStoragePref(
    val prefs: SharedPreferences,
    val gson: Gson,
    val logger: (String, Throwable) -> Unit
) : DogStorage {

    companion object {
        const val DOG_KEY = "DOG_KEY_632881"
    }

    override fun putDog(dog: Dog): Single<Dog> {
        try {
            val dogJson = gson.toJson(dog.toDogPref())
            prefs.edit()
                .putString(DOG_KEY, dogJson)
                .apply()
            return Single.just(dog)
        } catch (throwable: Throwable) {
            return Single.error(Exception("Can't put Dog to local Storage", throwable))
        }

    }

    override fun getDog(): Single<Dog> {
        return Single.create<Dog> { emitter ->
            val userJson = prefs.getString(DOG_KEY, "")
            if (userJson?.isEmpty() == true) {
                emitter.onError(Exception("No Dog in the Storage"))
            }
            try {
                emitter.onSuccess(gson.fromJson<DogPref>(userJson, DogPref::class.java).toDog())
            } catch (ex: JsonSyntaxException) {
                emitter.onError(Exception("Can't get Dog from the local Storage", ex))
            }
        }
    }

    override fun observeDog(): Flowable<Dog> {
        return Flowable.create<Dog>({ emitter ->

            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                if (key == DOG_KEY) {
                    val dogJson = prefs.getString(DOG_KEY, null) ?: ""
                    if (dogJson.isEmpty()) {
                        emitter.onError(Exception("No Dog in the Storage"))
                    } else {
                        catchGson<DogPref>(logger) { gson.fromJson(dogJson, DogPref::class.java) }?.let {
                            emitter.onNext(it.toDog())
                        } ?: emitter.onError(Exception("Can't parse Dog from the Storage"))
                    }
                }
            }

            emitter.setCancellable { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
            prefs.registerOnSharedPreferenceChangeListener(listener)

        }, BackpressureStrategy.LATEST)
    }

    override fun clearStorage(): Completable {
        return Completable.fromAction{
            prefs.edit()
                .putString(DOG_KEY, "")
                .apply()
        }
    }
}