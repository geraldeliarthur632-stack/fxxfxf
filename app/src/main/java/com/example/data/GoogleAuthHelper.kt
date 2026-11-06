package com.example.data

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.example.R
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

object GoogleAuthHelper {
    private const val TAG = "GoogleAuthHelper"

    suspend fun signInWithGoogle(
        context: Context,
        onSuccess: (email: String, displayName: String) -> Unit,
        onError: (String) -> Unit,
        onFallbackPrompt: () -> Unit
    ) {
        val activity = context as? Activity
        if (activity == null) {
            onFallbackPrompt()
            return
        }

        val clientId = try {
            context.getString(R.string.default_web_client_id)
        } catch (e: Exception) {
            Log.w(TAG, "default_web_client_id not found in resources, falling back to local account: ${e.message}")
            onFallbackPrompt()
            return
        }

        val credentialManager = CredentialManager.create(context)
        val signInOption = GetSignInWithGoogleOption.Builder(serverClientId = clientId).build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInOption)
            .build()

        try {
            val result = credentialManager.getCredential(activity, request)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data).idToken
                val authCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                val authResult = FirebaseAuth.getInstance().signInWithCredential(authCredential).await()
                val user = authResult.user
                val email = user?.email ?: "aluno.google@gmail.com"
                val name = user?.displayName ?: email.substringBefore("@")
                onSuccess(email, name)
            } else {
                onFallbackPrompt()
            }
        } catch (e: GetCredentialCancellationException) {
            Log.w(TAG, "Google Sign-In dismissed or cancelled: ${e.message}")
            onFallbackPrompt()
        } catch (e: Exception) {
            Log.w(TAG, "Credential Manager error, triggering account selector: ${e.message}")
            onFallbackPrompt()
        }
    }
}
