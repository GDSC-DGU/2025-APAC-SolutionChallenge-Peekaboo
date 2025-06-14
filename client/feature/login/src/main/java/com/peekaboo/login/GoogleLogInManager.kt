package com.peekaboo.login

import android.app.Activity
import android.credentials.GetCredentialException
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
object GoogleLoginManager {
    suspend fun signInWithGoogle(activity: Activity): String? {
        val credentialManager = CredentialManager.create(activity)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
            val result: GetCredentialResponse = credentialManager.getCredential(
                request = request,
                context = activity // keep on Main thread!
            )

            val googleIdCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            googleIdCredential.idToken
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            null
        }
    }
}