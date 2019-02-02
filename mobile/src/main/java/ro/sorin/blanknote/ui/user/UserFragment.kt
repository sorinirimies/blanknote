package ro.sorin.blanknote.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.*
import com.sorinirimies.kotlinx.animateGone
import com.sorinirimies.kotlinx.animateVisible
import com.sorinirimies.kotlinx.snack
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.*
import ro.sorin.blanknote.R
import ro.sorin.blanknote.utils.fromMillisToTimeString
import timber.log.Timber
import java.util.*

class UserFragment : Fragment(), GoogleApiClient.OnConnectionFailedListener,
        FacebookCallback<LoginResult> {

    private lateinit var viewModel: UserViewModel
    private val callbackManager by lazy { CallbackManager.Factory.create() }
    private val loginComponents by lazy { listOf(btnFacebookLogin, btnGoogleLogin, tvSignInLabel) }
    private val userDetailsComponents by lazy {
        listOf(imgUserIcon, btnUserLogout, tvEmail,
                tvName, tvPhoneNumber, tvId, tvLastSignIn)
    }
    private val gApiClient by lazy(LazyThreadSafetyMode.NONE) {
        GoogleApiClient.Builder(requireActivity())
                .enableAutoManage(requireActivity() /* FragmentActivity */, this /* OnConnectionFailedListener*/)
                .addApi(Auth.GOOGLE_SIGN_IN_API, GoogleSignInOptions.Builder(
                        GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.app_center_id))
                        .requestProfile()
                        .requestEmail()
                        .build())
                .build()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.userLoggedInOut.observe(this, Observer { user ->
            user?.showUserIsLoggedIn() ?: showUserIsLoggedOut()
        })
    }

    private fun showUserIsLoggedOut() {
        userDetailsComponents.forEach { it.animateGone() }
        loginComponents.forEach { it.animateVisible() }
    }

    private fun FirebaseUser.showUserIsLoggedIn() {
        Picasso.get().load(photoUrl)
                .placeholder(R.drawable.vector_profile)
                .error(R.drawable.vector_profile)
                .into(imgUserIcon)
        tvName.text = displayName
        tvEmail.text = email
        tvPhoneNumber.text = phoneNumber
        tvId.text = id.toString()
        tvLastSignIn.text = metadata?.lastSignInTimestamp?.fromMillisToTimeString()
        userDetailsComponents.forEach { it.animateVisible() }
        loginComponents.forEach { it.animateGone() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Google login click listener*/
        btnGoogleLogin.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(gApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        /* Facebook login setup*/
        LoginManager.getInstance().registerCallback(callbackManager, this)
        btnFacebookLogin.setOnClickListener {
            LoginManager.getInstance()
                    .logInWithReadPermissions(this, Arrays.asList("email", "public_profile"))
        }

        /* Logout*/
        btnUserLogout.setOnClickListener {
            viewModel.logoutUser()
        }
    }

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        user?.showUserIsLoggedIn() ?: showUserIsLoggedOut()
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        Timber.e(result.errorMessage)
    }

    override fun onSuccess(result: LoginResult) {
        firebaseAuthWithFacebook(result.accessToken)
    }

    override fun onCancel() {
        snack(contUserLogin, "Login cancelled.")
    }

    override fun onError(error: FacebookException?) {
        Timber.e(error)
        snack(contUserLogin, "Facebook login failed!")
    }


    private fun firebaseAuthWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseSignIn(credential)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseSignIn(credential)
    }

    private fun firebaseSignIn(credential: AuthCredential) {
        if (activity == null) return
        activity?.let {
            viewModel.fireAuth.signInWithCredential(credential)
                    .addOnCompleteListener(it) { task ->
                        Timber.i("signInWithCredential:onComplete: ${task.isSuccessful}")
                        if (!task.isSuccessful) {
                            Timber.e(task.exception)
                            snack(contUserLogin, "Authentication failed.")
                        }
                    }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Facebook Login callback
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) return
        //Google login callback
        when (requestCode) {
            RC_SIGN_IN -> Auth.GoogleSignInApi.getSignInResultFromIntent(data).handleLoginResult()
        }
    }

    private fun GoogleSignInResult.handleLoginResult() {
        if (isSuccess) {
            signInAccount?.let {
                firebaseAuthWithGoogle(it)
            }
        } else {
            snack(contUserLogin, "Google sign in failed.")
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fireAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        viewModel.fireAuth.removeAuthStateListener(authStateListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().unregisterCallback(callbackManager)
    }

    companion object {
        fun newInstance() = UserFragment()
        const val TAG_USER = "user-fragment"
        const val RC_SIGN_IN = 78
    }
}
